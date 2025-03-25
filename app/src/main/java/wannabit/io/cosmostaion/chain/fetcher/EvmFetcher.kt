package wannabit.io.cosmostaion.chain.fetcher

import org.web3j.protocol.Web3j
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.database.Prefs
import java.math.BigDecimal
import java.math.RoundingMode

class EvmFetcher(var chain: BaseChain) {

    var evmBalance: BigDecimal = BigDecimal.ZERO
    var evmTokens = mutableListOf<Token>()

    var web3j: Web3j? = null

    fun tokenValue(address: String, isUsd: Boolean? = false): BigDecimal {
        evmTokens.firstOrNull { it.contract == address }?.let { tokenInfo ->
            val price = BaseData.getPrice(tokenInfo.coinGeckoId, isUsd)
            return price.multiply(tokenInfo.amount?.toBigDecimal())
                .movePointLeft(tokenInfo.decimals).setScale(6, RoundingMode.DOWN)
        } ?: run {
            return BigDecimal.ZERO
        }
    }

    fun allTokenValue(isUsd: Boolean? = false): BigDecimal {
        var result = BigDecimal.ZERO
        evmTokens.forEach { tokenInfo ->
            val price = BaseData.getPrice(tokenInfo.coinGeckoId, isUsd)
            val value =
                price.multiply(tokenInfo.amount?.toBigDecimal()).movePointLeft(tokenInfo.decimals)
                    .setScale(6, RoundingMode.DOWN)
            result = result.add(value)
        }
        return result
    }

    fun allAssetValue(isUsd: Boolean?): BigDecimal {
        BaseData.getAssetWithSymbol(chain.apiName, chain.coinSymbol)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            return evmBalance.multiply(price).movePointLeft(18).setScale(6, RoundingMode.DOWN)
        }
        return BigDecimal.ZERO
    }

    fun valueCoinCnt(): Int {
        return if (evmBalance > BigDecimal.ZERO) 1 else 0
    }

    fun valueTokenCnt(): Int {
        return evmTokens.count { BigDecimal.ZERO < it.amount?.toBigDecimal() }
    }

    fun getEvmRpc(): String {
        val endpoint = Prefs.getEvmRpcEndpoint(chain)
        return if (endpoint?.isNotEmpty() == true) {
            endpoint
        } else {
            chain.evmRpcURL
        }
    }
}