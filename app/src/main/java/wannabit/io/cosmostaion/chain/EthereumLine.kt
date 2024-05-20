package wannabit.io.cosmostaion.chain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.web3j.protocol.Web3j
import wannabit.io.cosmostaion.chain.evmClass.ChainAltheaEvm
import wannabit.io.cosmostaion.chain.evmClass.ChainArbitrum
import wannabit.io.cosmostaion.chain.evmClass.ChainAvalanche
import wannabit.io.cosmostaion.chain.evmClass.ChainBaseEvm
import wannabit.io.cosmostaion.chain.evmClass.ChainBinanceSmart
import wannabit.io.cosmostaion.chain.evmClass.ChainCantoEvm
import wannabit.io.cosmostaion.chain.evmClass.ChainCronos
import wannabit.io.cosmostaion.chain.evmClass.ChainDymensionEvm
import wannabit.io.cosmostaion.chain.evmClass.ChainEthereum
import wannabit.io.cosmostaion.chain.evmClass.ChainEvmosEvm
import wannabit.io.cosmostaion.chain.evmClass.ChainHumansEvm
import wannabit.io.cosmostaion.chain.evmClass.ChainKavaEvm
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.chain.evmClass.ChainOptimism
import wannabit.io.cosmostaion.chain.evmClass.ChainPolygon
import wannabit.io.cosmostaion.chain.evmClass.ChainXplaEvm
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.database.Prefs
import java.math.BigDecimal
import java.math.RoundingMode

@Parcelize
open class EthereumLine : CosmosLine(), Parcelable {

    open var chainIdEvm: String = ""

    open var supportCosmos = false

    open var coinSymbol = ""
    open var coinGeckoId = ""
    open var coinLogo = -1
    open var addressLogo = -1

    var evmBalance = BigDecimal.ZERO

    var evmTokens = mutableListOf<Token>()

    var web3j: Web3j? = null

    override fun allAssetValue(isUsd: Boolean?): BigDecimal {
        return if (supportCosmos) {
            super.allAssetValue(isUsd)
        } else {
            val price = BaseData.getPrice(coinGeckoId, isUsd)
            evmBalance.multiply(price).movePointLeft(18).setScale(6, RoundingMode.DOWN)
        }
    }

    override fun allValue(isUsd: Boolean?): BigDecimal {
        return allAssetValue(isUsd).add(allTokenValue(isUsd))
    }

    override fun tokenValue(address: String, isUsd: Boolean?): BigDecimal {
        evmTokens.firstOrNull { it.address == address }?.let { tokenInfo ->
            val price = BaseData.getPrice(tokenInfo.coinGeckoId, isUsd)
            return price.multiply(tokenInfo.amount?.toBigDecimal())
                .movePointLeft(tokenInfo.decimals).setScale(6, RoundingMode.DOWN)
        } ?: run {
            return BigDecimal.ZERO
        }
    }

    override fun allTokenValue(isUsd: Boolean?): BigDecimal {
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

    fun getEvmRpc(): String {
        val endpoint = Prefs.getEvmRpcEndpoint(this)
        return if (endpoint?.isNotEmpty() == true) {
            endpoint
        } else {
            rpcUrl
        }
    }
}

fun allEvmLines(): MutableList<EthereumLine> {
    val lines = mutableListOf<EthereumLine>()
    lines.add(ChainEthereum())
    lines.add(ChainArbitrum())
    lines.add(ChainAvalanche())
    lines.add(ChainBaseEvm())
    lines.add(ChainBinanceSmart())
    lines.add(ChainAltheaEvm())
    lines.add(ChainCronos())
    lines.add(ChainDymensionEvm())
    lines.add(ChainEvmosEvm())
    lines.add(ChainHumansEvm())
    lines.add(ChainKavaEvm())
    lines.add(ChainOktEvm())
    lines.add(ChainOptimism())
    lines.add(ChainPolygon())
    lines.add(ChainXplaEvm())

//    lines.add(ChainCantoEvm())

    lines.forEach { line ->
        if (line.chainIdCosmos.isEmpty()) {
            line.getChainListParam()?.get("chain_id_cosmos")?.asString?.let { cosmosChainId ->
                line.chainIdCosmos = cosmosChainId
            }
        }

        if (line.chainIdEvm.isEmpty()) {
            line.getChainListParam()?.get("chain_id_evm")?.asString?.let { evmChainId ->
                line.chainIdEvm = evmChainId
            }
        }
    }
    return lines
}

val DEFAULT_DISPLAY_EVM = mutableListOf("ethereum60", "dymension60", "kava60")

val EVM_BASE_FEE = BigDecimal("588000000000000")