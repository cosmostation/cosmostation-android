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

    fun getPrice(coinGeckoId: String): BigDecimal? {
        val price = prices?.firstOrNull { it.coinGeckoId == coinGeckoId }
        return price?.currentPrice?.toBigDecimal()?.setScale(12, RoundingMode.HALF_UP)
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
        if (prices == null) return true
        val now = System.currentTimeMillis()
        val min: Long = 60000
        val last = Prefs.lastPriceTime.toLong() + (min * 2)
        return last < now
    }
}