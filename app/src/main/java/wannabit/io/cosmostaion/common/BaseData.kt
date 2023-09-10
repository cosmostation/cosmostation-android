package wannabit.io.cosmostaion.common

import wannabit.io.cosmostaion.data.model.Asset
import wannabit.io.cosmostaion.data.model.Price
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.BaseAccount
import java.math.BigDecimal
import java.math.RoundingMode

object BaseData {

    var baseAccount: BaseAccount? = null
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

    fun setLastAccount(account: BaseAccount) {
        Prefs.lastAccountId = account.id
    }

    suspend fun getLastAccount(): BaseAccount? {
        val id = Prefs.lastAccountId
        val account = AppDatabase.getInstance().baseAccountDao().selectById(id)
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
}