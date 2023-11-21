package wannabit.io.cosmostaion.common

import android.content.Context
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.data.model.res.Asset
import wannabit.io.cosmostaion.data.model.res.Chain
import wannabit.io.cosmostaion.data.model.res.Price
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.RefAddress
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.Calendar

object BaseData {

    var baseAccount: BaseAccount? = null
    var chains: List<Chain>? = mutableListOf()
    var prices: List<Price>? = mutableListOf()
    var assets: List<Asset>? = mutableListOf()

    fun getPrice(coinGeckoId: String?): BigDecimal {
        val price = prices?.firstOrNull { it.coinGeckoId == coinGeckoId }
        if (price != null) {
            val currentPrice = price.currentPrice ?: 0.0
            return currentPrice.toBigDecimal().setScale(12, RoundingMode.HALF_UP)
        }
        return BigDecimal.ZERO.setScale(12, RoundingMode.HALF_UP)
    }

    fun lastUpDown(coinGeckoId: String?): BigDecimal {
        val price = prices?.firstOrNull { it.coinGeckoId == coinGeckoId }
        if (price != null) {
            return (price.dailyPercent ?: 0.0).toBigDecimal().setScale(2, RoundingMode.HALF_UP)
        }
        return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)
    }

    fun getAsset(chainName: String, denom: String): Asset? {
        return assets?.firstOrNull { asset -> asset.chain == chainName && asset.denom?.lowercase() == denom.lowercase() }
    }

    fun getLastAccount(): BaseAccount? {
        val id = Prefs.lastAccountId
        val account = AppDatabase.getInstance().baseAccountDao().selectAccount(id)
        return account ?: AppDatabase.getInstance().baseAccountDao().selectAll().firstOrNull()
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

    fun setLastTime() {
        val now = Calendar.getInstance().timeInMillis
        Prefs.lastTime = now
    }

    fun isAutoPass(): Boolean {
        val now = Calendar.getInstance().timeInMillis
        return when (Prefs.autoPass) {
            1 -> { Prefs.lastTime + BaseConstant.CONSTANT_M * 5 > now }
            2 -> { Prefs.lastTime + BaseConstant.CONSTANT_M * 10 > now }
            3 -> { Prefs.lastTime + BaseConstant.CONSTANT_M * 30 > now }
            else -> false
        }
    }

    fun currencyName(): String {
        when (Prefs.currency) {
            0 -> return "USD"
            1 -> return "EUR"
            2 -> return "KRW"
            3 -> return "JPY"
            4 -> return "CNY"
            5 -> return "RUB"
            6 -> return "GBP"
            7 -> return "INR"
            8 -> return "BRL"
            9 -> return "IDR"
            10 -> return "DKK"
            11 -> return "NOK"
            12 -> return "SEK"
            13 -> return "CHF"
            14 -> return "AUD"
            15 -> return "CAD"
            16 -> return "MYR"
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
            6 -> return "£"
            7 -> return "₹"
            8 -> return "R$"
            9 -> return "Rp"
            10 -> return "Kr"
            11 -> return "Kr"
            12 -> return "Kr"
            13 -> return "sFr"
            14 -> return "AU$"
            15 -> return "$"
            16 -> return "RM"
            else -> return ""
        }
    }

    fun autoPass(c: Context): String {
        return when (Prefs.autoPass) {
            0 -> c.getString(R.string.str_never)
            1 -> c.getString(R.string.str_5_min)
            2 -> c.getString(R.string.str_10_min)
            else -> c.getString(R.string.str_30_min)
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
                refAddress.dpAddress
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
                refAddress.dpAddress
            )
        } else {
            refDao.insert(refAddress)
        }
    }
}