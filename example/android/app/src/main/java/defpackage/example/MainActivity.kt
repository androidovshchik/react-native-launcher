package defpackage.example

import android.content.Intent
import android.os.Bundle
import com.facebook.react.ReactActivity
import com.facebook.react.ReactActivityDelegate

class MainDelegate(activity: ReactActivity, mainComponentName: String?) :
    ReactActivityDelegate(activity, mainComponentName) {

    private var params: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        params = plainActivity.intent.extras
        super.onCreate(savedInstanceState)
    }

    override fun onNewIntent(intent: Intent?): Boolean {
        params = intent?.extras
        return super.onNewIntent(intent)
    }

    override fun getLaunchOptions() = params
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