package defpackage.launcher

import androidx.core.app.AlarmManagerCompat
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import org.jetbrains.anko.alarmManager

class LauncherModule(reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext) {

    override fun getName() = "LauncherPlugin"

    @ReactMethod
    fun openExact() {
        AlarmManagerCompat.setExact()
    }

    @ReactMethod
    fun openAndAllowWhileIdle() {
        with(reactApplicationContext) {
            AlarmManagerCompat.setAndAllowWhileIdle(
                alarmManager,
                0,
                0,
                pendingReceiverFor<LauncherReceiver>()
            )
        }
    }

    @ReactMethod
    fun openExactAndAllowWhileIdle() {
        AlarmManagerCompat.setExactAndAllowWhileIdle()
    }
}