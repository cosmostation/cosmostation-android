package wannabit.io.cosmostaion.appextensions

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import wannabit.io.cosmostaion.BuildConfig
import java.io.IOException

object PopupManager {

    private const val INBOX_URL = "https://api.fdvr.co/v2/inbox"
    private const val KEY_START_APP_COUNTER = "KEY_START_APP_COUNTER"
    private const val KEY_RATE_US_DONE = "KEY_RATE_US_DONE"
    private const val KEY_INSTALL_BROWSER_DONE = "KEY_INSTALL_BROWSER_DONE"
    private const val BROWSER_PACKAGE_NAME = "com.fulldive.mobile"
    private const val SUCCESS_RATING_VALUE = 4

    private val client = OkHttpClient()
    private val popupsFlow = listOf(
        StartAppDialog.InstallBrowser,
        StartAppDialog.RateUs,
        StartAppDialog.InstallBrowser,
        StartAppDialog.RateUs,
        StartAppDialog.InstallBrowser,
        StartAppDialog.RateUs,
        StartAppDialog.InstallBrowser,
        StartAppDialog.RateUs,
        StartAppDialog.Empty,
        StartAppDialog.Empty
    )

    fun onAppStarted(context: Context) {
        val sharedPreferences = context.getPrivateSharedPreferences()
        val startCounter = sharedPreferences.getProperty(KEY_START_APP_COUNTER, 0)
        sharedPreferences.setProperty(KEY_START_APP_COUNTER, startCounter + 1)

        val rateUsDone = sharedPreferences.getProperty(KEY_RATE_US_DONE, false)
        val installBrowserDone = sharedPreferences.getProperty(KEY_INSTALL_BROWSER_DONE, false)

        if (!rateUsDone || !installBrowserDone) {
            when (getShowingPopup(startCounter)) {
                StartAppDialog.RateUs -> {
                    if (!rateUsDone) {
                        showRateUsDialog(context) {
                            onRateUsPositiveClicked(
                                context,
                                sharedPreferences,
                                it
                            )
                        }
                    }
                }
                StartAppDialog.InstallBrowser -> {
                    if ((!installBrowserDone) && !isBrowserInstalled(context)) {
                        showInstallBrowserDialog(context) {
                            onInstallAppPositiveClicked(context, sharedPreferences)
                        }
                    }
                }
                else -> {
                }
            }
        }
    }

    private fun isBrowserInstalled(context: Context): Boolean {
        val app = try {
            context.packageManager.getApplicationInfo(BROWSER_PACKAGE_NAME, 0)
        } catch (e: Exception) {
            null
        }
        return app?.enabled ?: false
    }

    private fun onRateUsPositiveClicked(
        context: Context,
        sharedPreferences: SharedPreferences,
        rating: Int
    ) {
        if (rating < SUCCESS_RATING_VALUE) {
            showRateReportDialog(context) { message ->
                sendMessage(message)
                sharedPreferences.setProperty(KEY_RATE_US_DONE, true)
            }
        } else {
            context.openAppInGooglePlay(BuildConfig.APPLICATION_ID)
            sharedPreferences.setProperty(KEY_RATE_US_DONE, true)
        }
    }

    private fun onInstallAppPositiveClicked(
        context: Context,
        sharedPreferences: SharedPreferences
    ) {
        context.openAppInGooglePlay(BROWSER_PACKAGE_NAME)
        sharedPreferences.setProperty(KEY_INSTALL_BROWSER_DONE, true)
    }

    private fun sendMessage(message: String) {
        Thread {
            try {
                val res = post(INBOX_URL, getJSON(message))
                Log.d("sendMessageTest", res)
            } catch (ex: java.lang.Exception) {
                ex.printStackTrace()
                Log.d("sendMessageTest", "$ex.message")
            }
        }
            .start()
    }

    private fun getJSON(message: String): String {
        return "{\"payload\":{\"message\":\"$message\"},\"type\":\"report-message\"}"
    }

    private fun getShowingPopup(startCounter: Int): StartAppDialog {
        return if (popupsFlow.lastIndex >= startCounter) {
            popupsFlow[startCounter]
        } else {
            popupsFlow[startCounter % popupsFlow.size]
        }
    }

    private fun showRateUsDialog(
        context: Context,
        positiveClickListener: (value: Int) -> Unit
    ) {
        RateUsDialogBuilder
            .show(context) { value ->
                positiveClickListener.invoke(value)
            }
    }

    private fun showRateReportDialog(
        context: Context,
        positiveClickListener: (message: String) -> Unit
    ) {
        RateReportDialogBuilder
            .show(context) { message ->
                positiveClickListener.invoke(message)
            }
    }

    private fun showInstallBrowserDialog(
        context: Context,
        positiveClickListener: () -> Unit
    ) {
        InstallBrowserDialogBuilder
            .show(context) {
                positiveClickListener.invoke()
            }
    }

    @Throws(IOException::class)
    private fun post(url: String, json: String): String {
        val body: RequestBody = json.toRequestBody("application/json; charset=utf-8".toMediaType())
        val request: Request = Request.Builder()
            .url(url)
            .post(body)
            .build()
        var result = ""
        client.newCall(request).execute().use { response ->
            result = response.body.toString()
        }
        return result
    }
}

sealed class StartAppDialog(val id: String) {
    object RateUs : StartAppDialog("RateUs")
    object InstallBrowser : StartAppDialog("InstallBrowser")
    object Empty : StartAppDialog("Empty")
}