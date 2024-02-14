package wannabit.io.cosmostaion.chain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import org.web3j.protocol.Web3j
import wannabit.io.cosmostaion.chain.evmClass.ChainEthereum
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseKey
import wannabit.io.cosmostaion.data.model.res.Token
import java.math.BigDecimal
import java.math.RoundingMode

@Parcelize
open class EthereumLine : BaseChain(), Parcelable {

    open var coinSymbol = ""
    open var coinGeckoId = ""
    open var coinLogo = ""

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

    open fun allAssetValue(isUsd: Boolean?): BigDecimal {
        val price = BaseData.getPrice(coinGeckoId, isUsd)
        return evmBalance.multiply(price).movePointLeft(18).setScale(6, RoundingMode.DOWN)
    }

    fun allValue(isUsd: Boolean?): BigDecimal {
        return allAssetValue(isUsd).add(allTokenValue(isUsd))
    }
}

fun allEvmLines(): MutableList<EthereumLine> {
    val lines = mutableListOf<EthereumLine>()
    lines.add(ChainEthereum())
    return lines
}

val DEFAULT_DISPLAY_EVM = mutableListOf("ethereum60")