package defpackage.example

import android.content.Intent
import android.os.Bundle
import com.facebook.react.ReactActivity
import com.facebook.react.ReactActivityDelegate

/**
 * The way of getting launch arguments into component properties
 */
class MainDelegate(activity: ReactActivity, mainComponentName: String?) :
    ReactActivityDelegate(activity, mainComponentName) {

    private var args: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        args = plainActivity.intent.extras
        super.onCreate(savedInstanceState)
    }

    override fun onNewIntent(intent: Intent?): Boolean {
        args = intent?.extras
        return super.onNewIntent(intent)
    }

    override fun getLaunchOptions() = args
}

class MainActivity : ReactActivity() {

    /**
     * Returns the name of the main component registered from JavaScript. This is used to schedule
     * rendering of the component.
     */
    override fun getMainComponentName() = "example"

    override fun createReactActivityDelegate() = MainDelegate(this, mainComponentName)
}