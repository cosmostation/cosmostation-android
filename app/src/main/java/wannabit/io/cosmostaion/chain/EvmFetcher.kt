package wannabit.io.cosmostaion.chain

import org.web3j.protocol.Web3j
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.database.Prefs
import java.math.BigDecimal
import java.math.RoundingMode

class EvmFetcher(chain: BaseChain) {

    var chain: BaseChain

    var evmBalance: BigDecimal = BigDecimal.ZERO
    var evmTokens = mutableListOf<Token>()

    var web3j: Web3j? = null

    init {
        this.chain = chain
    }

    fun tokenValue(address: String, isUsd: Boolean? = false): BigDecimal {
        evmTokens.firstOrNull { it.address == address }?.let { tokenInfo ->
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
        val price = BaseData.getPrice(chain.coinGeckoId, isUsd)
        return evmBalance.multiply(price).movePointLeft(18).setScale(6, RoundingMode.DOWN)
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