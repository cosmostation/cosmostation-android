package wannabit.io.cosmostaion.chain

import android.os.Parcelable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.parcelize.Parcelize
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import wannabit.io.cosmostaion.chain.evmClass.ChainCantoEvm
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

    open var supportCosmos = false

    open var coinSymbol = ""
    open var coinGeckoId = ""
    open var coinLogo = -1
    open var addressLogo = -1

    open var explorerURL = ""
    open var addressURL = ""
    open var txURL = ""

    var evmBalance = BigDecimal.ZERO

    var evmTokens = mutableListOf<Token>()

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

    override fun web3j(): Web3j? {
        return runBlocking(Dispatchers.IO) {
            try {
                val web3j = Web3j.build(HttpService(rpcUrl))
                web3j.web3ClientVersion().sendAsync().get().web3ClientVersion
                web3j
            } catch (e: Exception) {
                null
            }
        }
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
//    lines.add(ChainAltheaEvm())
    lines.add(ChainCantoEvm())
    lines.add(ChainDymensionEvm())
    lines.add(ChainEvmosEvm())
    lines.add(ChainHumansEvm())
    lines.add(ChainKavaEvm())
    lines.add(ChainOktEvm())
    lines.add(ChainOptimism())
    lines.add(ChainPolygon())
    lines.add(ChainXplaEvm())

    lines.forEach { line ->
        if (line.chainId.isEmpty()) {
            line.chainId =
                BaseData.chains?.firstOrNull { it.chain == line.apiName }?.chain_id.toString()
        }
    }
    return lines
}

val DEFAULT_DISPLAY_EVM = mutableListOf("ethereum60", "dymension60", "kava60")

val EVM_BASE_FEE = BigDecimal("588000000000000")