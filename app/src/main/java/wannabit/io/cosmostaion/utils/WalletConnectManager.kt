package wannabit.io.cosmostaion.utils

import android.content.Context
import android.preference.PreferenceManager
import org.apache.commons.lang3.StringUtils
import wannabit.io.cosmostaion.base.BaseConstant

object WalletConnectManager {

    private const val WC_WHITELIST_DELIMITER = ",^^,"

    @JvmStatic
    fun getWhiteList(context: Context): List<String> {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val urls = sharedPreferences.getString(BaseConstant.PRE_WC_WHITE_LIST, StringUtils.EMPTY)
        return urls?.split(WC_WHITELIST_DELIMITER)?.filter { it.isNotEmpty() }.orEmpty()
    }

    @JvmStatic
    fun addWhiteList(context: Context, url: String) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = sharedPreferences.edit()
        val whiteLists = getWhiteList(context).toMutableList()
        whiteLists.add(url)
        editor.putString(
            BaseConstant.PRE_WC_WHITE_LIST,
            StringUtils.join(whiteLists, WC_WHITELIST_DELIMITER)
        )
        editor.apply()
    }

    @JvmStatic
    fun removeWhiteList(context: Context, url: String) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = sharedPreferences.edit()
        val whiteLists = getWhiteList(context).toMutableList()
        whiteLists.remove(url)
        editor.putString(
            BaseConstant.PRE_WC_WHITE_LIST,
            StringUtils.join(whiteLists, WC_WHITELIST_DELIMITER)
        )
        editor.apply()
    }
}