package wannabit.io.cosmostaion.database

import SecureSharedPreferences
import android.content.Context
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonSyntaxException
import org.json.JSONArray
import org.json.JSONException
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.DEFAULT_DISPLAY_CHAIN
import wannabit.io.cosmostaion.data.model.res.SkipChainResponse
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.ui.main.CosmostationApp

object Prefs {
    private const val PREFERENCES_NAME = "PREFS"
    private const val DB_VERSION = "PRE_DB_VERSION"
    private const val LAST_ACCOUNT = "PRE_LAST_ACCOUNT"
    private const val DISPLAY_CHAINS = "PRE_DISPLAY_CHAINS"
    private const val LAST_PRICE_TIME = "PRE_LAST_PRICE_TIME"
    private const val LAST_CURRENCY = "PRE_CURRENCY"
    private const val LANGUAGE = "PRE_LANGUAGE"
    private const val STYLE = "PRE_STYLE"
    private const val PRICE_STYLE = "PRE_PRICE_STYLE"
    private const val LAST_TIME = "PRE_LAST_TIME"
    private const val APP_LOCK = "PRE_APP_LOCK"
    private const val SWAP_WARN = "PRE_SWAP_WARN"
    private const val SWAP_INFO_TIME = "PRE_SWAP_INFO_TIME"
    private const val SWAP_USER_SET = "PRE_SWAP_USER_SET"
    private const val SKIP_CHAIN_INFO = "PRE_SKIP_CHAIN_INFO"
    private const val SKIP_ASSET_INFO = "PRE_SKIP_ASSET_INFO"
    private const val HIDE_VALUE = "PRE_HIDE_VALUE"
    private const val DISPLAY_LEGACY = "PRE_DISPLAY_LEGACY"
    private const val BIO = "PRE_BIO"
    private const val FCM_TOKEN = "PRE_FCM_TOKEN_NEW"
    private const val ALARM_ENABLE_STATUS = "PRE_ALARM_STATUS"
    private const val DATABASE_PASSPHRASE = "DB_PASSPHRASE"
    private const val BACKGROUND_IMAGE = "PRE_BACKGROUND_IMAGE"
    private const val FOREGROUND_TO_BACKGROUND = "PRE_FOREGROUND_TO_BACKGROUND"
    private const val DISPLAY_ERC20_TOKENS = "PRE_DISPLAY_ERC20_TOKENS"
    private const val GRPC_ENDPOINT = "PRE_GRPC_ENDPOINT"
    private const val EVM_RPC_ENDPOINT = "PRE_EVM_RPC_ENDPOINT"
    private const val LCD_ENDPOINT = "PRE_LCD_ENDPOINT"
    private const val FCM_SYNC_TIME = "PRE_FCM_SYNC_TIME"
    private const val INJECT_WARN = "PRE_INJECT_WARN"
    private const val DISPLAY_TESTNET = "PRE_DISPLAY_TESTNET"
    private const val ENDPOINT_TYPE = "PRE_ENDPOINT_TYPE"


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

    var currency: Int
        get() = preference.getInt(LAST_CURRENCY, 0)
        set(value) = preference.edit().putInt(LAST_CURRENCY, value).apply()

    var language: Int
        get() = preference.getInt(LANGUAGE, 0)
        set(value) = preference.edit().putInt(LANGUAGE, value).apply()

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

    var skipChainInfo: SkipChainResponse?
        get() {
            val jsonString = preference.getString(SKIP_CHAIN_INFO, null)
            return if (jsonString != null) {
                Gson().fromJson(jsonString, SkipChainResponse::class.java)
            } else {
                null
            }
        }
        set(value) {
            val jsonString = Gson().toJson(value)
            preference.edit().putString(SKIP_CHAIN_INFO, jsonString).apply()
        }

    var skipAssetInfo: JsonObject?
        get() {
            val jsonString = preference.getString(SKIP_ASSET_INFO, null)
            return try {
                Gson().fromJson(jsonString, JsonObject::class.java)
            } catch (e: JsonSyntaxException) {
                null
            }
        }
        set(value) {
            val jsonString = Gson().toJson(value)
            preference.edit().putString(SKIP_ASSET_INFO, jsonString).apply()
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

    fun setGrpcEndpoint(chain: BaseChain?, endpoint: String) {
        val key = GRPC_ENDPOINT + ":" + chain?.name
        preference.edit().putString(key, endpoint).apply()
    }

    fun getGrpcEndpoint(chain: BaseChain): String {
        return preference.getString(GRPC_ENDPOINT + ":" + chain.name, "") ?: ""
    }

    fun setEvmRpcEndpoint(chain: BaseChain?, endpoint: String) {
        val key = EVM_RPC_ENDPOINT + ":" + chain?.name
        preference.edit().putString(key, endpoint).apply()
    }

    fun getEvmRpcEndpoint(chain: BaseChain): String? {
        return preference.getString(EVM_RPC_ENDPOINT + ":" + chain.name, "")
    }

    fun setLcdEndpoint(chain: BaseChain?, endpoint: String) {
        val key = LCD_ENDPOINT + ":" + chain?.name
        preference.edit().putString(key, endpoint).apply()
    }

    fun getLcdEndpoint(chain: BaseChain): String {
        return preference.getString(LCD_ENDPOINT + ":" + chain.name, "") ?: ""
    }
}