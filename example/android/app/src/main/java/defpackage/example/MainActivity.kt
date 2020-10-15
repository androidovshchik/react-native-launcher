package defpackage.example

import com.facebook.react.ReactActivity
import com.facebook.react.ReactActivityDelegate

class MainDelegate(activity: ReactActivity, mainComponentName: String?) :
    ReactActivityDelegate(activity, mainComponentName) {

    override fun getLaunchOptions() = plainActivity.intent.extras
}

class MainActivity : ReactActivity() {

    /**
     * Returns the name of the main component registered from JavaScript. This is used to schedule
     * rendering of the component.
     */
    override fun getMainComponentName() = "example"

    override fun createReactActivityDelegate(): ReactActivityDelegate {
        return MainDelegate(this, mainComponentName)
    }
}