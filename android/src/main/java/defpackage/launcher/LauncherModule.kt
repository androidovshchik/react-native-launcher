package defpackage.launcher

import android.app.AlarmManager
import android.net.Uri
import android.os.SystemClock
import androidx.core.app.AlarmManagerCompat
import com.facebook.react.bridge.*
import org.jetbrains.anko.alarmManager
import org.jetbrains.anko.intentFor
import kotlin.math.min

class LauncherModule(reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext) {

    override fun getName() = "LauncherPlugin"

    @ReactMethod
    fun openExact(delay: Int, map: ReadableMap?) {
        val bootTime = SystemClock.elapsedRealtime()
        with(reactApplicationContext) {
            AlarmManagerCompat.setExact(
                alarmManager,
                AlarmManager.ELAPSED_REALTIME,
                bootTime + delay,
                pendingReceiverFor(
                    intentFor<LauncherReceiver>().apply {
                        data = Uri.parse("$packageName://$bootTime")
                        if (map != null) {
                            putExtras(Arguments.toBundle(map)!!)
                        }
                    },
                    min(bootTime, Int.MAX_VALUE.toLong()).toInt()
                )
            )
        }
    }

    @ReactMethod
    fun openAndAllowWhileIdle(delay: Int, map: ReadableMap?) {
        val bootTime = SystemClock.elapsedRealtime()
        with(reactApplicationContext) {
            AlarmManagerCompat.setExact(
                alarmManager,
                AlarmManager.ELAPSED_REALTIME,
                bootTime + delay,
                pendingReceiverFor(
                    intentFor<LauncherReceiver>().apply {
                        data = Uri.parse("$packageName://$bootTime")
                        if (map != null) {
                            putExtras(Arguments.toBundle(map)!!)
                        }
                    },
                    min(bootTime, Int.MAX_VALUE.toLong()).toInt()
                )
            )
        }
    }

    @ReactMethod
    fun openExactAndAllowWhileIdle(delay: Int, map: ReadableMap?) {
        val bootTime = SystemClock.elapsedRealtime()
        with(reactApplicationContext) {
            AlarmManagerCompat.setExact(
                alarmManager,
                AlarmManager.ELAPSED_REALTIME,
                bootTime + delay,
                pendingReceiverFor(
                    intentFor<LauncherReceiver>().apply {
                        data = Uri.parse("$packageName://$bootTime")
                        if (map != null) {
                            putExtras(Arguments.toBundle(map)!!)
                        }
                    },
                    min(bootTime, Int.MAX_VALUE.toLong()).toInt()
                )
            )
        }
    }
}