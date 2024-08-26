package wannabit.io.cosmostaion.common

import com.google.gson.JsonObject
import org.apache.commons.lang3.StringUtils
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.data.model.res.Asset
import wannabit.io.cosmostaion.data.model.res.Price
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.RefAddress
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.Calendar
import java.util.concurrent.TimeUnit

object BaseData {

    var baseAccount: BaseAccount? = null
    var chainParam: JsonObject? = null
    var prices: List<Price>? = mutableListOf()
    var usdPrices: List<Price>? = mutableListOf()
    var assets: List<Asset>? = mutableListOf()

    var isBackGround = false
    var appSchemeUrl = ""

    fun getPrice(coinGeckoId: String?, isUsd: Boolean? = false): BigDecimal {
        val price = if (isUsd == true) {
            usdPrices?.firstOrNull { it.coinGeckoId == coinGeckoId }
        } else {
            prices?.firstOrNull { it.coinGeckoId == coinGeckoId }
        }
        if (price != null) {
            val currentPrice = price.current_price ?: 0.0
            return currentPrice.toBigDecimal().setScale(12, RoundingMode.HALF_DOWN)
        }
        return BigDecimal.ZERO.setScale(12, RoundingMode.HALF_DOWN)
    }

    fun lastUpDown(coinGeckoId: String?): BigDecimal {
        val price = prices?.firstOrNull { it.coinGeckoId == coinGeckoId }
        if (price != null) {
            return (price.daily_price_change_in_percent ?: 0.0).toBigDecimal()
                .setScale(2, RoundingMode.HALF_DOWN)
        }
        return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_DOWN)
    }

    fun getAsset(chainName: String, denom: String): Asset? {
        return assets?.firstOrNull { asset -> asset.chain == chainName && asset.denom?.lowercase() == denom.lowercase() }
    }

    fun getToken(chain: BaseChain, chainName: String, address: String): Token? {
        return if (chain.supportCw20) {
            chain.cosmosFetcher()?.tokens?.firstOrNull { token -> token.chainName == chainName && token.address == address }
        } else {
            chain.evmRpcFetcher()?.evmTokens?.firstOrNull { token -> token.chainName == chainName && token.address == address }
        }
    }

    fun getLastAccount(): BaseAccount? {
        val id = Prefs.lastAccountId
        val accounts = AppDatabase.getInstance().baseAccountDao().selectAll()
        val validAccounts = accounts.filter {
            StringUtils.isNotBlank(it.uuid) && StringUtils.isNotBlank(it.resource) && StringUtils.isNotBlank(
                it.spec
            )
        }
        val findAccount = validAccounts.find { it.id == id }
        return findAccount ?: validAccounts.firstOrNull()
    }

    fun setLastPriceTime() {
        val now = System.currentTimeMillis()
        Prefs.lastPriceTime = now.toString()
    }

    fun priceUpdateIfNeed(): Boolean {
        if (prices?.isEmpty() == true) return true
        val now = System.currentTimeMillis()
        val min: Long = 60000
        val last = Prefs.lastPriceTime.toLong() + (min * 2)
        return last < now
    }

    fun setLastParamTime() {
        val now = System.currentTimeMillis()
        Prefs.lastParamTime = now.toString()
    }

    fun paramUpdateIfNeed(): Boolean {
        if (prices?.isEmpty() == true) return true
        val now = System.currentTimeMillis()
        val min: Long = 60000
        val last = Prefs.lastParamTime.toLong() + (min * 2)
        return last < now
    }

    fun setLastTime() {
        val now = Calendar.getInstance().timeInMillis
        Prefs.lastTime = now
    }

    fun setSwapWarn() {
        val currentDate = Calendar.getInstance()
        currentDate.add(Calendar.DAY_OF_MONTH, 7)
        Prefs.swapWarn = currentDate.timeInMillis
    }

    fun getSwapWarn(): Boolean {
        val last = Prefs.swapWarn
        val now = Calendar.getInstance().timeInMillis
        return last < now
    }

    fun setLastSwapInfoTime() {
        val now = Calendar.getInstance().timeInMillis
        Prefs.swapInfoTime = now
    }

    fun needSwapInfoUpdate(): Boolean {
        val now = Calendar.getInstance().timeInMillis
        val day: Long = TimeUnit.DAYS.toMillis(1)
        val last = Prefs.swapInfoTime + (day * 3)
        return last < now
    }

    fun setLastPushTime() {
        val now = Calendar.getInstance().timeInMillis
        Prefs.pushLastTime = now
    }

    fun pushRefreshIfNeed(): Boolean {
        val now = System.currentTimeMillis()
        val min: Long = 60000
        val last = Prefs.lastPriceTime.toLong() + (min * 60 * 24 * 3)
        return last < now
    }

    fun setInjectWarn() {
        val currentDate = Calendar.getInstance()
        currentDate.add(Calendar.DAY_OF_MONTH, 7)
        Prefs.injectWarn = currentDate.timeInMillis
    }

    fun getInjectWarn(): Boolean {
        val last = Prefs.injectWarn
        val now = Calendar.getInstance().timeInMillis
        return last < now
    }

    fun currencyName(): String {
        when (Prefs.currency) {
            0 -> return "USD"
            1 -> return "EUR"
            2 -> return "KRW"
            3 -> return "JPY"
            4 -> return "CNY"
            5 -> return "RUB"
            6 -> return "THB"
            7 -> return "GBP"
            8 -> return "INR"
            9 -> return "BRL"
            10 -> return "IDR"
            11 -> return "DKK"
            12 -> return "NOK"
            13 -> return "SEK"
            14 -> return "CHF"
            15 -> return "AUD"
            16 -> return "CAD"
            17 -> return "MYR"
            else -> return ""
        }
    }

    fun currencySymbol(): String {
        when (Prefs.currency) {
            0 -> return "$"
            1 -> return "€"
            2 -> return "₩"
            3 -> return "¥"
            4 -> return "¥"
            5 -> return "₽"
            6 -> return "฿"
            7 -> return "£"
            8 -> return "₹"
            9 -> return "R$"
            10 -> return "Rp"
            11 -> return "Kr"
            12 -> return "Kr"
            13 -> return "Kr"
            14 -> return "sFr"
            15 -> return "AU$"
            16 -> return "$"
            17 -> return "RM"
            else -> return ""
        }
    }

    suspend fun updateRefAddressesMain(refAddress: RefAddress) {
        val refDao = AppDatabase.getInstance().refAddressDao()

        val existRefAddress =
            refDao.getRefAddress(refAddress.accountId, refAddress.chainTag, refAddress.dpAddress)
        if (existRefAddress != null) {
            refDao.updateMain(
                refAddress.lastMainValue,
                refAddress.lastMainAmount,
                refAddress.lastCoinCnt,
                refAddress.accountId,
                refAddress.chainTag,
                refAddress.dpAddress,
                refAddress.evmAddress
            )
        } else {
            refDao.insert(refAddress)
        }
    }

    suspend fun updateRefAddressesToken(refAddress: RefAddress) {
        val refDao = AppDatabase.getInstance().refAddressDao()

        val existRefAddress =
            refDao.getRefAddress(refAddress.accountId, refAddress.chainTag, refAddress.dpAddress)
        if (existRefAddress != null) {
            refDao.updateToken(
                refAddress.lastTokenValue,
                refAddress.accountId,
                refAddress.chainTag,
                refAddress.dpAddress,
                refAddress.evmAddress
            )
        } else {
            refDao.insert(refAddress)
        }
    }
}