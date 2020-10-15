package defpackage.launcher

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.jetbrains.anko.newTask

class LauncherReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        with(context) {
            packageManager.getLaunchIntentForPackage(packageName)?.let {
                startActivity(it.putExtra("launcher", true).newTask())
            }
        }
    }
}