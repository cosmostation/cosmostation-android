package wannabit.io.cosmostaion.database

import SecureSharedPreferences
import android.content.Context
import wannabit.io.cosmostaion.ui.main.CosmostationApp

object Prefs {
    private const val PREFERENCES_NAME = "PREFS"
    private const val LAST_ACCOUNT = "PRE_LAST_ACCOUNT"
    private const val LAST_PRICE_TIME = "PRE_LAST_PRICE_TIME"
    private const val FCM_TOKEN = "PRE_FCM_TOKEN_NEW"
    private const val ALARM_ENABLE_STATUS = "PRE_ALARM_STATUS"
    private const val CURRENT_USER_ID = "PRE_USER_ID"
    private const val DATABASE_PASSPHRASE = "DB_PASSPHRASE"
    private val preference = CosmostationApp.instance.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

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

    var lastAccountId: Long
        get() = preference.getLong(LAST_ACCOUNT, -1)
        set(value) = preference.edit().putLong(LAST_ACCOUNT, value).apply()

    var lastPriceTime: String
        get() = preference.getString(LAST_PRICE_TIME, "0") ?: "0"
        set(value) = preference.edit().putString(LAST_PRICE_TIME, value).apply()
}