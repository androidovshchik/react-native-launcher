package defpackage.launcher

import androidx.core.app.AlarmManagerCompat
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class LauncherModule(reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext) {

    override fun getName() = "LauncherPlugin"

    @ReactMethod
    fun openApp(enable: Boolean) {
        AlarmManagerCompat.setExactAndAllowWhileIdle()
    }
}