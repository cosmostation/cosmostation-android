package wannabit.io.cosmostaion.chain.fetcher

import com.google.gson.JsonObject
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.database.Prefs
import java.math.BigDecimal
import java.math.RoundingMode

class SolanaFetcher(private val chain: BaseChain) {

    var solanaAccountInfo = JsonObject()

    fun allAssetValue(isUsd: Boolean? = false): BigDecimal {
        return solanaBalanceValue(isUsd)
    }

    fun solanaBalanceAmount(): BigDecimal? {
        if (solanaAccountInfo.has("result")) {
            return solanaAccountInfo["result"].asJsonObject["value"].asJsonObject["lamports"].asLong.toBigDecimal()
        }
        return BigDecimal.ZERO
    }

    fun solanaBalanceValue(isUsd: Boolean? = false): BigDecimal {
        val amount = solanaBalanceAmount()
        if (amount == BigDecimal.ZERO) return BigDecimal.ZERO
        BaseData.getAsset(chain.apiName, chain.coinSymbol)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            return price.multiply(amount).movePointLeft(asset.decimals ?: 6)
                .setScale(6, RoundingMode.DOWN)
        }
        return BigDecimal.ZERO
    }

    fun solanaRpc(): String {
        val endpoint = Prefs.getEvmRpcEndpoint(chain)
        return if (endpoint?.isNotEmpty() == true) {
            endpoint
        } else {
            chain.mainUrl
        }
    }
}