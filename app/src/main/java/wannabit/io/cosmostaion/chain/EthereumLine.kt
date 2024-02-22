package wannabit.io.cosmostaion.chain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import org.web3j.protocol.Web3j
import wannabit.io.cosmostaion.chain.evmClass.ChainEthereum
import wannabit.io.cosmostaion.chain.evmClass.ChainKavaEvm
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseKey
import wannabit.io.cosmostaion.data.model.res.Token
import java.math.BigDecimal
import java.math.RoundingMode

@Parcelize
open class EthereumLine : CosmosLine(), Parcelable {

    open var supportCosmos = false

    open var coinSymbol = ""
    open var coinGeckoId = ""
    open var coinLogo = -1

    open var rpcURL = ""
    open var explorerURL = ""
    open var addressURL = ""
    open var txURL = ""

    var web3j: Web3j? = null
    var evmBalance = BigDecimal.ZERO

    var evmTokens = mutableListOf<Token>()

    override fun setInfoWithSeed(
        seed: ByteArray?, parentPath: List<ChildNumber>, lastPath: String
    ) {
        privateKey = BaseKey.getPrivateKey(seed, parentPath, lastPath)
        publicKey = BaseKey.getPubKeyFromPKey(privateKey)
        address = BaseKey.getAddressFromPubKey(publicKey, accountKeyType.pubkeyType, accountPrefix)
    }

    override fun setInfoWithPrivateKey(privateKey: ByteArray?) {
        this.privateKey = privateKey
        publicKey = BaseKey.getPubKeyFromPKey(privateKey)
        address = BaseKey.getAddressFromPubKey(publicKey, accountKeyType.pubkeyType, accountPrefix)
    }

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
}

fun allEvmLines(): MutableList<EthereumLine> {
    val lines = mutableListOf<EthereumLine>()
    lines.add(ChainEthereum())
    lines.add(ChainKavaEvm())
    return lines
}

val DEFAULT_DISPLAY_EVM = mutableListOf("ethereum60")

val EVM_BASE_FEE = BigDecimal("588000000000000")