package defpackage.launcher

import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ViewManager

class LauncherPackage : ReactPackage {

    override fun createNativeModules(reactContext: ReactApplicationContext): List<NativeModule> {
        return listOf(LauncherModule(reactContext))
    }

    override fun createViewManagers(reactContext: ReactApplicationContext) =
        emptyList<ViewManager<*, *>>()
}