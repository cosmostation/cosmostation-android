package wannabit.io.cosmostaion.appextensions

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri

fun Context.getPrivateSharedPreferences(): SharedPreferences {
    return this.getSharedPreferences(packageName + "_preference", Context.MODE_PRIVATE)
}

fun SharedPreferences.setProperty(tag: String, value: Int) {
    try {
        val spe = edit()
        spe.putInt(tag, value).apply()
    } catch (ex: Exception) {
    }
}

fun SharedPreferences.setProperty(tag: String, value: Boolean) {
    try {
        val spe = edit()
        spe.putBoolean(tag, value).apply()
    } catch (ex: Exception) {
    }
}

fun SharedPreferences.getProperty(tag: String, default_value: Int): Int {
    var result = default_value
    try {
        result = getInt(tag, default_value)
    } catch (ex: Exception) {
    }
    return result
}

fun SharedPreferences.getProperty(tag: String, default_value: Boolean): Boolean {
    var result = default_value
    try {
        result = getBoolean(tag, default_value)
    } catch (ex: Exception) {
    }
    return result
}

fun Context.openAppInGooglePlay(appPackageName: String? = null) {
    val packName = appPackageName ?: packageName
    try {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=$packName")
            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    } catch (anfe: android.content.ActivityNotFoundException) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$packName")
            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }
}