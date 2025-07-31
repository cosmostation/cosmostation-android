package wannabit.io.cosmostaion.chain.fetcher

import com.google.gson.JsonObject
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.database.Prefs
import java.math.BigDecimal
import java.math.RoundingMode

class SolanaFetcher(private val chain: BaseChain) {

    var solanaAccountInfo = JsonObject()
    val solanaTokenInfo: MutableList<Pair<String, JsonObject>> = mutableListOf()

    var splTokens = mutableListOf<Token>()

    fun allAssetValue(isUsd: Boolean? = false): BigDecimal {
        return solanaBalanceValue(isUsd).add(allSplTokenValue(isUsd))
    }

    fun solanaBalanceAmount(): BigDecimal {
        if (!solanaAccountInfo["value"].isJsonNull) {
            return solanaAccountInfo["value"].asJsonObject["lamports"].asLong.toBigDecimal()
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

    fun splTokenValue(address: String?, isUsd: Boolean? = false): BigDecimal {
        splTokens.firstOrNull { it.address == address }?.let { splToken ->
            val price = BaseData.getPrice(splToken.coinGeckoId, isUsd)
            return price.multiply(splToken.amount?.toBigDecimal())
                .movePointLeft(splToken.decimals).setScale(6, RoundingMode.DOWN)
        } ?: run {
            return BigDecimal.ZERO
        }
    }

    fun allSplTokenValue(isUsd: Boolean? = false): BigDecimal {
        var result = BigDecimal.ZERO

        solanaTokenInfo.forEach { splToken ->
            BaseData.getToken(chain, chain.apiName, splToken.second["mint"].asString)?.let { token ->
                val price = BaseData.getPrice(token.coinGeckoId, isUsd)
                val value =
                    price.multiply(token.amount?.toBigDecimal()).movePointLeft(token.decimals)
                        .setScale(6, RoundingMode.DOWN)
                result = result.add(value)
            }
        }
        return result
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