package wannabit.io.cosmostaion.database

import SecureSharedPreferences
import android.content.SharedPreferences
import android.preference.PreferenceManager
import wannabit.io.cosmostaion.ui.main.CosmostationApp

object Prefs {
    private const val FCM_TOKEN = "PRE_FCM_TOKEN_NEW"
    private const val ALARM_ENABLE_STATUS = "PRE_ALARM_STATUS"
    private const val CURRENT_USER_ID = "PRE_USER_ID"
    private const val DATABASE_PASSPHRASE = "DB_PASSPHRASE"
    private val preference: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(CosmostationApp.instance)

    var lastUserId: Long
        get() = preference.getLong(CURRENT_USER_ID, -1)
        set(value) = preference.edit().putLong(CURRENT_USER_ID, value).apply()

    var fcmToken: String
        get() = preference.getString(FCM_TOKEN, "") ?: ""
        set(value) = preference.edit().putString(FCM_TOKEN, value).apply()

    var alarmEnable: Boolean
        get() = preference.getBoolean(ALARM_ENABLE_STATUS, false)
        set(value) = preference.edit().putBoolean(ALARM_ENABLE_STATUS, value).apply()

    var passphrase: String
        get() = SecureSharedPreferences.wrap(preference).get(DATABASE_PASSPHRASE, "")
        set(value) = SecureSharedPreferences.wrap(preference).putString(DATABASE_PASSPHRASE, value)
}