package wannabit.io.cosmostaion.ui.main

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import wannabit.io.cosmostaion.database.Prefs

class PushNotificationActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Prefs.foreToBack = false
        val url = intent.getStringExtra("url") ?: ""
        if (url.isNotEmpty()) {
            openUrlInCustomTab(this, url)
        }
        finish()
    }

    private fun openUrlInCustomTab(context: Context, url: String) {
        val uri = Uri.parse(url)
        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.launchUrl(context, uri)
    }
}