package wannabit.io.cosmostaion.chain.fetcher

import com.cosmos.base.v1beta1.CoinProto
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.database.Prefs
import java.math.BigDecimal
import java.math.RoundingMode

class GnoFetcher(private val chain: BaseChain) {

    var gnoPublicKey: String? = null
    var gnoAccountNumber: Long? = null
    var gnoSequence: Long? = null
    var gnoBalances: MutableList<CoinProto.Coin>? = null

    var grc20Tokens = mutableListOf<Token>()

    fun allAssetValue(isUsd: Boolean? = false): BigDecimal {
        return balanceValueSum(isUsd)
    }

    fun denomValue(denom: String, isUsd: Boolean? = false): BigDecimal? {
        return balanceValue(denom, isUsd)
    }

    fun grc20TokenValue(address: String, isUsd: Boolean? = false): BigDecimal {
        grc20Tokens.firstOrNull { it.address == address }?.let { tokenInfo ->
            val price = BaseData.getPrice(tokenInfo.coinGeckoId, isUsd)
            return price.multiply(tokenInfo.amount?.toBigDecimal())
                .movePointLeft(tokenInfo.decimals).setScale(6, RoundingMode.DOWN)
        } ?: run {
            return BigDecimal.ZERO
        }
    }

    fun allGrc20TokenValue(baseAccountId: Long, isUsd: Boolean? = false): BigDecimal {
        var result = BigDecimal.ZERO

        val userDisplayToken = Prefs.getDisplayGrc20s(baseAccountId, chain.tag)
        val displayTokenList = if (userDisplayToken == null) {
            chain.gnoRpcFetcher?.grc20Tokens?.filter { it.wallet_preload ?: false }
        } else {
            chain.gnoRpcFetcher?.grc20Tokens?.filter { userDisplayToken.contains(it.address) }
        }

        displayTokenList?.forEach { token ->
            val price = BaseData.getPrice(token.coinGeckoId, isUsd)
            val value = price.multiply(token.amount?.toBigDecimal()).movePointLeft(token.decimals)
                .setScale(6, RoundingMode.DOWN)
            result = result.add(value)
        }
        return result
    }

    fun displayTokenCnt(baseAccountId: Long): Int {
        val userDisplayToken = Prefs.getDisplayGrc20s(baseAccountId, chain.tag)
        val displayTokenList = if (userDisplayToken == null) {
            chain.gnoRpcFetcher?.grc20Tokens?.filter { it.wallet_preload ?: false }
        } else {
            chain.gnoRpcFetcher?.grc20Tokens?.filter { userDisplayToken.contains(it.address) }
        }
        return displayTokenList?.count() ?: 0
    }

    fun balanceAmount(denom: String): BigDecimal {
        if (gnoBalances?.isNotEmpty() == true) {
            return gnoBalances?.firstOrNull { it.denom == denom }?.amount?.toBigDecimal()
                ?: BigDecimal.ZERO
        }
        return BigDecimal.ZERO
    }

    fun balanceValue(denom: String, isUsd: Boolean? = false): BigDecimal {
        BaseData.getAsset(chain.apiName, denom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            val amount = balanceAmount(denom)
            asset.decimals?.let { decimal ->
                return price.multiply(amount).movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
            } ?: run {
                return BigDecimal.ZERO
            }
        }
        return BigDecimal.ZERO
    }

    private fun balanceValueSum(isUsd: Boolean? = false): BigDecimal {
        var sum = BigDecimal.ZERO
        if (gnoBalances?.isNotEmpty() == true) {
            gnoBalances?.forEach { balance ->
                sum = sum.add(balanceValue(balance.denom, isUsd))
            }
        }
        return sum
    }

    fun gnoRpc(): String {
        val endpoint = Prefs.getEvmRpcEndpoint(chain)
        return if (endpoint?.isNotEmpty() == true) {
            endpoint
        } else {
            chain.mainUrl
        }
    }
}