package defpackage.launcher

import android.app.AlarmManager
import android.net.Uri
import android.os.SystemClock
import androidx.core.app.AlarmManagerCompat
import com.facebook.react.bridge.*
import org.jetbrains.anko.alarmManager
import org.jetbrains.anko.intentFor

@Suppress("unused")
class LauncherModule(reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext) {

    override fun getName() = "LauncherPlugin"

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
}