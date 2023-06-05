import android.content.SharedPreferences
import wannabit.io.cosmostaion.database.CipherHelper

/**
 * A [android.content.SharedPreferences] wrapper that helps easy reading/writing values.
 *
 * @author Francesco Jo(nimbusob@gmail.com)
 * @since 22 - Mar - 2018
 */
class SecureSharedPreferences(private val sharedPref: SharedPreferences) {
    fun contains(key: String) = sharedPref.contains(key)

    fun get(key: String, defaultValue: Boolean): Boolean = getInternal(key, defaultValue)

    fun get(key: String, defaultValue: Int): Int = getInternal(key, defaultValue)

    fun get(key: String, defaultValue: Long): Long = getInternal(key, defaultValue)

    fun get(key: String, defaultValue: String): String = getInternal(key, defaultValue)

    /**
     * Client codes must handle ClassCastException by their own at the call site if happens.
     */
    private fun <T : Any> getInternal(key: String, defaultValue: T): T {
        val str = sharedPref.getString(key, "")
        if (str.isNullOrEmpty()) {
            return defaultValue
        }

        try {
            val value = CipherHelper.decrypt(str)

            @Suppress(
                "PlatformExtensionReceiverOfInline", "UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY"
            ) return when (defaultValue) {
                is Boolean -> value.toBoolean()
                is Int -> value.toInt()
                is Long -> value.toLong()
                is String -> value
                else -> throw IllegalArgumentException("defaultValue only could be one of these types: Boolean, Int, Long, String")
            } as T
        } catch (e: Exception) {
            putInternal(key, null)
        }

        return defaultValue
    }

    fun putBoolean(key: String, value: Boolean?) = putInternal(key, value)

    fun putInt(key: String, value: Int?) = putInternal(key, value)

    fun putLong(key: String, value: Long?) = putInternal(key, value)

    fun putString(key: String, value: String?) = putInternal(key, value)

    private fun putInternal(key: String, value: Any?) {
        try {
            sharedPref.edit().run {
                if (value == null) {
                    remove(key)
                } else {
                    putString(key, CipherHelper.encrypt(value.toString()))
                }

                apply()
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    companion object {
        fun wrap(sharedPref: SharedPreferences) = SecureSharedPreferences(sharedPref)
    }
}
