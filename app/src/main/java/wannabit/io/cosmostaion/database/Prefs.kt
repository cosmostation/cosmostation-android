package wannabit.io.cosmostaion.database

import SecureSharedPreferences
import android.content.Context
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONException
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.DEFAULT_DISPLAY_CHAIN
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.ui.main.CosmostationApp
import java.util.Calendar
import androidx.core.content.edit

object Prefs {
    private const val PREFERENCES_NAME = "PREFS"
    private const val DB_VERSION = "PRE_DB_VERSION"
    private const val LAST_ACCOUNT = "PRE_LAST_ACCOUNT"
    private const val DISPLAY_CHAINS = "PRE_DISPLAY_CHAINS"
    private const val LAST_PRICE_TIME = "PRE_LAST_PRICE_TIME"
    private const val LAST_PARAM_TIME = "PRE_LAST_PARAM_TIME"
    private const val LAST_CURRENCY = "PRE_CURRENCY"
    private const val LANGUAGE = "PRE_LANGUAGE"
    private const val THEME = "PRE_THEME"
    private const val STYLE = "PRE_STYLE"
    private const val PRICE_STYLE = "PRE_PRICE_STYLE"
    private const val LAST_TIME = "PRE_LAST_TIME"
    private const val APP_LOCK = "PRE_APP_LOCK"
    private const val SWAP_WARN = "PRE_SWAP_WARN"
    private const val SWAP_INFO_TIME = "PRE_SWAP_INFO_TIME"
    private const val SWAP_USER_SET = "PRE_SWAP_USER_SET"
    private const val HIDE_VALUE = "PRE_HIDE_VALUE"
    private const val DISPLAY_LEGACY = "PRE_DISPLAY_LEGACY"
    private const val BIO = "PRE_BIO"
    private const val FCM_TOKEN = "PRE_FCM_TOKEN_NEW"
    private const val ALARM_ENABLE_STATUS = "PRE_ALARM_STATUS"
    private const val DATABASE_PASSPHRASE = "DB_PASSPHRASE"
    private const val BACKGROUND_IMAGE = "PRE_BACKGROUND_IMAGE"
    private const val FOREGROUND_TO_BACKGROUND = "PRE_FOREGROUND_TO_BACKGROUND"
    private const val DISPLAY_ERC20_TOKENS = "PRE_DISPLAY_ERC20_TOKENS"
    private const val DISPLAY_CW20_TOKENS = "PRE_DISPLAY_CW20_TOKENS"
    private const val DISPLAY_GRC20_TOKENS = "PRE_DISPLAY_GRC20_TOKENS"
    private const val GRPC_ENDPOINT = "PRE_GRPC_ENDPOINT"
    private const val EVM_RPC_ENDPOINT = "PRE_EVM_RPC_ENDPOINT"
    private const val LCD_ENDPOINT = "PRE_LCD_ENDPOINT"
    private const val FCM_SYNC_TIME = "PRE_FCM_SYNC_TIME"
    private const val INJECT_WARN = "PRE_INJECT_WARN"
    private const val DISPLAY_TESTNET = "PRE_DISPLAY_TESTNET"
    private const val ENDPOINT_TYPE = "PRE_ENDPOINT_TYPE"
    private const val CHAIN_FILTER = "PRE_CHAIN_FILTER"
    private const val DAPP_INFO_HIDE = "PRE_DAPP_INFO_HIDE"
    private const val DAPP_FILTER = "PRE_DAPP_FILTER"
    private const val DAPP_PINNED = "PRE_DAPP_PINNED"
    private const val DAPP_HIDE = "PRE_DAPP_HIDE"
    private const val UPDATE_ADDRESS_BOOK = "PRE_UPDATE_ADDRESS_BOOK"
    private const val ADS_SHOW_OPTION = "PRE_ADS_SHOW_OPTION"


    private val preference =
        CosmostationApp.instance.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    var fcmToken: String
        get() = preference.getString(FCM_TOKEN, "") ?: ""
        set(value) = preference.edit().putString(FCM_TOKEN, value).apply()

    var alarmEnable: Boolean
        get() = preference.getBoolean(ALARM_ENABLE_STATUS, false)
        set(value) = preference.edit().putBoolean(ALARM_ENABLE_STATUS, value).apply()

    var passphrase: String
        get() = SecureSharedPreferences.wrap(preference).get(DATABASE_PASSPHRASE, "")
        set(value) = SecureSharedPreferences.wrap(preference).putString(DATABASE_PASSPHRASE, value)

    var version: Int
        get() = preference.getInt(DB_VERSION, -1)
        set(value) = preference.edit().putInt(DB_VERSION, value).apply()

    var lastAccountId: Long
        get() = preference.getLong(LAST_ACCOUNT, -1)
        set(value) = preference.edit().putLong(LAST_ACCOUNT, value).apply()

    fun setDisplayChains(baseAccount: BaseAccount, chainNames: List<String>) {
        val encoded = try {
            val jsonString = JSONArray(chainNames).toString()
            jsonString.toByteArray(Charsets.UTF_8)
        } catch (e: JSONException) {
            null
        }

        if (encoded != null) {
            val key = "${baseAccount.id} $DISPLAY_CHAINS"
            preference.edit().putString(key, String(encoded)).apply()
        }
    }

    fun getDisplayChains(baseAccount: BaseAccount): MutableList<String> {
        val key = "${baseAccount.id} $DISPLAY_CHAINS"
        val savedDataString = preference.getString(key, null)

        if (!savedDataString.isNullOrEmpty()) {
            try {
                val jsonArray = JSONArray(savedDataString)
                val result = ArrayList<String>()
                for (i in 0 until jsonArray.length()) {
                    result.add(jsonArray.getString(i))
                }
                return result
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        return DEFAULT_DISPLAY_CHAIN
    }

    var lastPriceTime: String
        get() = preference.getString(LAST_PRICE_TIME, "0") ?: "0"
        set(value) = preference.edit().putString(LAST_PRICE_TIME, value).apply()

    var lastParamTime: String
        get() = preference.getString(LAST_PARAM_TIME, "0") ?: "0"
        set(value) = preference.edit().putString(LAST_PARAM_TIME, value).apply()

    var currency: Int
        get() = preference.getInt(LAST_CURRENCY, 0)
        set(value) = preference.edit().putInt(LAST_CURRENCY, value).apply()

    var language: Int
        get() = preference.getInt(LANGUAGE, 0)
        set(value) = preference.edit().putInt(LANGUAGE, value).apply()

    var theme: Int
        get() = preference.getInt(THEME, 0)
        set(value) = preference.edit().putInt(THEME, value).apply()

    var style: Int
        get() = preference.getInt(STYLE, 0)
        set(value) = preference.edit().putInt(STYLE, value).apply()

    var priceStyle: Int
        get() = preference.getInt(PRICE_STYLE, 0)
        set(value) = preference.edit().putInt(PRICE_STYLE, value).apply()

    var lastTime: Long
        get() = preference.getLong(LAST_TIME, 0)
        set(value) = preference.edit().putLong(LAST_TIME, value).apply()

    var appLock: Boolean
        get() = preference.getBoolean(APP_LOCK, false)
        set(value) = preference.edit().putBoolean(APP_LOCK, value).apply()

    var swapWarn: Long
        get() = preference.getLong(SWAP_WARN, 0)
        set(value) = preference.edit().putLong(SWAP_WARN, value).apply()

    var swapInfoTime: Long
        get() = preference.getLong(SWAP_INFO_TIME, 0)
        set(value) = preference.edit().putLong(SWAP_INFO_TIME, value).apply()

    var lastSwapSet: List<String>
        get() {
            val jsonString = preference.getString(SWAP_USER_SET, null)
            if (!jsonString.isNullOrEmpty()) {
                val typeToken = object : TypeToken<List<String>>() {}.type
                return Gson().fromJson(jsonString, typeToken)
            }
            return listOf("", "", "", "")
        }
        set(value) {
            val encoded = Gson().toJson(value)
            preference.edit().putString(SWAP_USER_SET, encoded).apply()
        }

    var hideValue: Boolean
        get() = preference.getBoolean(HIDE_VALUE, false)
        set(value) = preference.edit().putBoolean(HIDE_VALUE, value).apply()

    var displayLegacy: Boolean
        get() = preference.getBoolean(DISPLAY_LEGACY, false)
        set(value) = preference.edit().putBoolean(DISPLAY_LEGACY, value).apply()

    var usingBio: Boolean
        get() = preference.getBoolean(BIO, false)
        set(value) = preference.edit().putBoolean(BIO, value).apply()

    var background: Int
        get() = preference.getInt(BACKGROUND_IMAGE, 0)
        set(value) = preference.edit().putInt(BACKGROUND_IMAGE, value).apply()

    var foreToBack: Boolean
        get() = preference.getBoolean(FOREGROUND_TO_BACKGROUND, true)
        set(value) = preference.edit().putBoolean(FOREGROUND_TO_BACKGROUND, value).apply()

    var pushLastTime: Long
        get() = preference.getLong(FCM_SYNC_TIME, 0)
        set(value) = preference.edit().putLong(FCM_SYNC_TIME, value).apply()

    var injectWarn: Long
        get() = preference.getLong(INJECT_WARN, 0)
        set(value) = preference.edit().putLong(INJECT_WARN, value).apply()

    var displayTestnet: Boolean
        get() = preference.getBoolean(DISPLAY_TESTNET, false)
        set(value) = preference.edit().putBoolean(DISPLAY_TESTNET, value).apply()

    var chainFilter: Boolean
        get() = preference.getBoolean(CHAIN_FILTER, false)
        set(value) = preference.edit().putBoolean(CHAIN_FILTER, value).apply()

    var dappFilter: Int
        get() = preference.getInt(DAPP_FILTER, 0)
        set(value) = preference.edit().putInt(DAPP_FILTER, value).apply()

    var isUpdateAddressBook: Boolean
        get() = preference.getBoolean(UPDATE_ADDRESS_BOOK, false)
        set(value) = preference.edit().putBoolean(UPDATE_ADDRESS_BOOK, value).apply()

    var adsShowOption: Boolean
        get() = preference.getBoolean(ADS_SHOW_OPTION, false)
        set(value) = preference.edit { putBoolean(ADS_SHOW_OPTION, value) }

    fun setDappHideTime(id: Int) {
        val key = "$DAPP_HIDE:$id"
        val currentDate = Calendar.getInstance()
        currentDate.add(Calendar.DAY_OF_MONTH, 7)
        preference.edit().putLong(key, currentDate.timeInMillis).apply()
    }

    fun getDappHideTime(id: Int): Long {
        val key = "$DAPP_HIDE:$id"
        return preference.getLong(key, 0L)
    }

    fun setDisplayErc20s(
        baseAccountId: Long, chainTag: String, contractAddresses: List<String>
    ) {
        val encoded = try {
            val jsonString = JSONArray(contractAddresses).toString()
            jsonString.toByteArray(Charsets.UTF_8)
        } catch (e: JSONException) {
            null
        }

        if (encoded != null) {
            val key = "$baseAccountId $chainTag $DISPLAY_ERC20_TOKENS"
            preference.edit().putString(key, String(encoded)).apply()
        }
    }

    fun getDisplayErc20s(baseAccountId: Long, chainTag: String): MutableList<String>? {
        val key = "$baseAccountId $chainTag $DISPLAY_ERC20_TOKENS"
        val savedDataString = preference.getString(key, null)

        if (!savedDataString.isNullOrEmpty()) {
            try {
                val jsonArray = JSONArray(savedDataString)
                val result = ArrayList<String>()
                for (i in 0 until jsonArray.length()) {
                    result.add(jsonArray.getString(i))
                }
                return result
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        return null
    }

    fun setDisplayCw20s(
        baseAccountId: Long, chainTag: String, contractAddresses: List<String>
    ) {
        val encoded = try {
            val jsonString = JSONArray(contractAddresses).toString()
            jsonString.toByteArray(Charsets.UTF_8)
        } catch (e: JSONException) {
            null
        }

        if (encoded != null) {
            val key = "$baseAccountId $chainTag $DISPLAY_CW20_TOKENS"
            preference.edit().putString(key, String(encoded)).apply()
        }
    }

    fun getDisplayCw20s(baseAccountId: Long, chainTag: String): MutableList<String>? {
        val key = "$baseAccountId $chainTag $DISPLAY_CW20_TOKENS"
        val savedDataString = preference.getString(key, null)

        if (!savedDataString.isNullOrEmpty()) {
            try {
                val jsonArray = JSONArray(savedDataString)
                val result = ArrayList<String>()
                for (i in 0 until jsonArray.length()) {
                    result.add(jsonArray.getString(i))
                }
                return result
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        return null
    }

    fun setDisplayGrc20s(
        baseAccountId: Long, chainTag: String, contractAddresses: List<String>
    ) {
        val encoded = try {
            val jsonString = JSONArray(contractAddresses).toString()
            jsonString.toByteArray(Charsets.UTF_8)
        } catch (e: JSONException) {
            null
        }

        if (encoded != null) {
            val key = "$baseAccountId $chainTag $DISPLAY_GRC20_TOKENS"
            preference.edit().putString(key, String(encoded)).apply()
        }
    }

    fun getDisplayGrc20s(baseAccountId: Long, chainTag: String): MutableList<String>? {
        val key = "$baseAccountId $chainTag $DISPLAY_GRC20_TOKENS"
        val savedDataString = preference.getString(key, null)

        if (!savedDataString.isNullOrEmpty()) {
            try {
                val jsonArray = JSONArray(savedDataString)
                val result = ArrayList<String>()
                for (i in 0 until jsonArray.length()) {
                    result.add(jsonArray.getString(i))
                }
                return result
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        return null
    }

    fun setEndpointType(chain: BaseChain?, endpoint: CosmosEndPointType) {
        val key = ENDPOINT_TYPE + ":" + chain?.name
        preference.edit().putString(key, endpoint.name).apply()
    }

    fun getEndpointType(chain: BaseChain): CosmosEndPointType? {
        return when (preference.getString(ENDPOINT_TYPE + ":" + chain.name, "")) {
            CosmosEndPointType.USE_GRPC.name -> {
                CosmosEndPointType.USE_GRPC
            }

            CosmosEndPointType.USE_LCD.name -> {
                CosmosEndPointType.USE_LCD
            }

            else -> {
                chain.cosmosEndPointType
            }
        }
    }

    fun removeEndpointType(chain: BaseChain) {
        val key = ENDPOINT_TYPE + ":" + chain.name
        preference.edit().remove(key).apply()
    }

    fun setGrpcEndpoint(chain: BaseChain?, endpoint: String) {
        val key = GRPC_ENDPOINT + ":" + chain?.name
        preference.edit().putString(key, endpoint).apply()
    }

    fun getGrpcEndpoint(chain: BaseChain): String {
        return preference.getString(GRPC_ENDPOINT + ":" + chain.name, "") ?: ""
    }

    fun removeGrpcEndpoint(chain: BaseChain) {
        val key = GRPC_ENDPOINT + ":" + chain.name
        preference.edit().remove(key).apply()
    }

    fun setEvmRpcEndpoint(chain: BaseChain?, endpoint: String) {
        val key = EVM_RPC_ENDPOINT + ":" + chain?.name
        preference.edit().putString(key, endpoint).apply()
    }

    fun getEvmRpcEndpoint(chain: BaseChain): String? {
        return preference.getString(EVM_RPC_ENDPOINT + ":" + chain.name, "")
    }

    fun removeEvmRpcEndpoint(chain: BaseChain) {
        val key = EVM_RPC_ENDPOINT + ":" + chain.name
        preference.edit().remove(key).apply()
    }

    fun setLcdEndpoint(chain: BaseChain?, endpoint: String) {
        val key = LCD_ENDPOINT + ":" + chain?.name
        preference.edit().putString(key, endpoint).apply()
    }

    fun getLcdEndpoint(chain: BaseChain): String {
        return preference.getString(LCD_ENDPOINT + ":" + chain.name, "") ?: ""
    }

    fun removeLcdEndpoint(chain: BaseChain) {
        val key = LCD_ENDPOINT + ":" + chain.name
        preference.edit().remove(key).apply()
    }

    fun setDappInfoHideTime(id: Int) {
        val key = "$DAPP_INFO_HIDE:$id"
        val currentDate = Calendar.getInstance()
        currentDate.add(Calendar.DAY_OF_MONTH, 7)
        preference.edit().putLong(key, currentDate.timeInMillis).apply()
    }

    fun getDappInfoHideTime(id: Int): Long {
        val key = "$DAPP_INFO_HIDE:$id"
        return preference.getLong(key, 0L)
    }

    fun setPinnedDapps(chains: List<Int>) {
        val encoded = try {
            val jsonString = JSONArray(chains).toString()
            jsonString.toByteArray(Charsets.UTF_8)
        } catch (e: JSONException) {
            null
        }

        if (encoded != null) {
            val key = DAPP_PINNED
            preference.edit().putString(key, String(encoded)).apply()
        }
    }

    fun getPinnedDapps(): MutableList<Int> {
        val key = DAPP_PINNED
        val savedDataString = preference.getString(key, null)

        if (!savedDataString.isNullOrEmpty()) {
            try {
                val jsonArray = JSONArray(savedDataString)
                val result = ArrayList<Int>()
                for (i in 0 until jsonArray.length()) {
                    result.add(jsonArray.getInt(i))
                }
                return result
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        return mutableListOf()
    }
}