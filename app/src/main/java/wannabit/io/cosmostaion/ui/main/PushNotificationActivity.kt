package wannabit.io.cosmostaion.ui.main

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import wannabit.io.cosmostaion.database.Prefs

class PushNotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        Prefs.foreToBack = false
        val url = intent.getStringExtra("url") ?: ""
        if (url.isNotEmpty()) {
            openUrlInCustomTab(this, url)
        }
        finish()
    }

    private fun openUrlInCustomTab(context: Context, url: String) {
        val uri = url.toUri()
        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.launchUrl(context, uri)
    }

    override fun setRequestedOrientation(requestedOrientation: Int) {
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            super.setRequestedOrientation(requestedOrientation)
        }
    }
}