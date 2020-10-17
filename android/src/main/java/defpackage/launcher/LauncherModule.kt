package defpackage.launcher

import android.annotation.TargetApi
import android.app.Activity
import android.app.AlarmManager
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.SystemClock
import android.provider.Settings
import androidx.core.app.AlarmManagerCompat
import com.facebook.react.bridge.*
import org.jetbrains.anko.alarmManager
import org.jetbrains.anko.intentFor

@Suppress("unused", "DEPRECATION")
class LauncherModule(reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext), ActivityEventListener {

    private var mPromise: Promise? = null

    override fun getName() = "LauncherPlugin"

    @ReactMethod
    fun canDrawOverlays(callback: Callback) {
        with(reactApplicationContext) {
            // Notice that it may be not working on Android O
            // See https://stackoverflow.com/questions/46173460/why-in-android-8-method-settings-candrawoverlays-returns-false-when-user-has
            callback.invoke(!isMarshmallowPlus() || Settings.canDrawOverlays(applicationContext))
        }
    }

    @ReactMethod
    @TargetApi(Build.VERSION_CODES.M)
    fun requestDrawOverlays(promise: Promise?) {
        with(reactApplicationContext) {
            if (isMarshmallowPlus()) {
                val activity = currentActivity
                if (activity != null) {
                    mPromise = promise
                    addActivityEventListener(this@LauncherModule)
                    activity.startActivityForResult(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION).apply {
                        data = Uri.parse("package:$packageName")
                    }, REQUEST_OVERLAY)
                } else {
                    promise?.reject("Activity is null")
                }
            } else {
                promise?.resolve(Activity.RESULT_OK)
            }
        }
    }

    @ReactMethod
    fun openExact(delay: Int, map: ReadableMap?) {
        with(reactApplicationContext) {
            AlarmManagerCompat.setExact(
                alarmManager,
                AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + delay,
                pendingReceiverFor(
                    intentFor<LauncherReceiver>().apply {
                        data = Uri.parse("$packageName://$delay")
                        if (map != null) {
                            putExtras(Arguments.toBundle(map)!!)
                        }
                    },
                    delay
                )
            )
        }
    }

    @ReactMethod
    fun openAndAllowWhileIdle(delay: Int, map: ReadableMap?) {
        with(reactApplicationContext) {
            AlarmManagerCompat.setExact(
                alarmManager,
                AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + delay,
                pendingReceiverFor(
                    intentFor<LauncherReceiver>().apply {
                        data = Uri.parse("$packageName://$delay")
                        if (map != null) {
                            putExtras(Arguments.toBundle(map)!!)
                        }
                    },
                    delay
                )
            )
        }
    }

    @ReactMethod
    fun openExactAndAllowWhileIdle(delay: Int, map: ReadableMap?) {
        with(reactApplicationContext) {
            AlarmManagerCompat.setExact(
                alarmManager,
                AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + delay,
                pendingReceiverFor(
                    intentFor<LauncherReceiver>().apply {
                        data = Uri.parse("$packageName://$delay")
                        if (map != null) {
                            putExtras(Arguments.toBundle(map)!!)
                        }
                    },
                    delay
                )
            )
        }
    }

    @ReactMethod
    fun cancelOpen(delay: Int, map: ReadableMap?) {
        with(reactApplicationContext) {
            alarmManager.cancel(
                pendingReceiverFor(
                    intentFor<LauncherReceiver>().apply {
                        data = Uri.parse("$packageName://$delay")
                        if (map != null) {
                            putExtras(Arguments.toBundle(map)!!)
                        }
                    },
                    delay
                )
            )
        }
    }

    override fun onNewIntent(intent: Intent?) {
    }

    override fun onActivityResult(
        activity: Activity?,
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        if (requestCode == REQUEST_OVERLAY) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    mPromise?.resolve(resultCode)
                }
                Activity.RESULT_CANCELED -> {
                    mPromise?.reject("Permission request was cancelled")
                }
            }
            reactApplicationContext.removeActivityEventListener(this)
            mPromise = null
        }
    }

    companion object {

        private const val REQUEST_OVERLAY = 100
    }
}