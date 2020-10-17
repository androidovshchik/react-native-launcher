package defpackage.launcher

import android.annotation.TargetApi
import android.app.Activity
import android.app.AlarmManager
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.provider.Settings
import androidx.core.app.AlarmManagerCompat
import com.facebook.react.bridge.*
import org.jetbrains.anko.alarmManager
import org.jetbrains.anko.intentFor

@Suppress("unused", "DEPRECATION")
class LauncherModule(reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext), ActivityEventListener {

    private var args: Bundle? = null

    private var mPromise: Promise? = null

    init {
        reactContext.addActivityEventListener(this)
    }

    override fun getName() = "LauncherPlugin"

    override fun onNewIntent(intent: Intent?) {
        args = intent?.extras
    }

    @ReactMethod
    fun canDrawOverlays(promise: Promise) {
        with(reactApplicationContext) {
            val isGranted = !isMarshmallowPlus() || Settings.canDrawOverlays(applicationContext)
            if (isOreo()) {
                promise.reject(isGranted.toString())
            } else {
                promise.resolve(isGranted)
            }
        }
    }

    @ReactMethod
    @TargetApi(Build.VERSION_CODES.M)
    fun requestDrawOverlays(promise: Promise?) {
        with(reactApplicationContext) {
            if (isMarshmallowPlus()) {
                currentActivity?.let {
                    mPromise = promise
                    it.startActivityForResult(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION).apply {
                        data = Uri.parse("package:$packageName")
                    }, REQUEST_OVERLAY)
                    return
                }
                promise?.reject("Current activity is null")
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
    fun getLaunchArgs(callback: Callback) {
        val args = args ?: currentActivity?.intent?.extras
        callback.invoke(if (args != null) Arguments.fromBundle(args) else null)
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
            mPromise = null
        }
    }

    companion object {

        private const val REQUEST_OVERLAY = 100
    }
}