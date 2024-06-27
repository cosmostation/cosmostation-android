package wannabit.io.cosmostaion.chain

import android.net.Uri
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseKey
import java.io.Serializable
import java.math.BigDecimal

open class BaseChain : Serializable {

    open var name: String = ""
    open var tag: String = ""
    open var logo: Int = R.drawable.token_default
    open var swipeLogo: Int = -1
    open var isTestnet: Boolean = false
    open var isDefault: Boolean = true
    open var apiName: String = ""
    open var accountPrefix: String? = ""

    open lateinit var accountKeyType: AccountKeyType
    open var publicKey: ByteArray? = null
    open var privateKey: ByteArray? = null
    open var setParentPath: List<ChildNumber> = mutableListOf()

    // cosmos
    open var supportCosmosGrpc = false
    open var supportCosmosLcd = false
    open var chainIdCosmos: String = ""
    open var address: String? = ""
    open var stakeDenom: String? = ""
    open var supportCw20 = false
    open var supportErc20 = false
    open var supportStaking = true
    open var supportNft = false
    open var grpcHost: String = ""
    open var grpcPort = 443
    open var rpcUrl = ""

    open var supportEvm = false
    open var chainIdEvm: String = ""

    lateinit var grpcFetcher: FetcherGrpc

    open var fetched = false

    fun setInfoWithSeed(seed: ByteArray?, parentPath: List<ChildNumber>, lastPath: String) {
        privateKey = BaseKey.getPrivateKey(seed, parentPath, lastPath)
        publicKey = BaseKey.getPubKeyFromPKey(privateKey)
        address = BaseKey.getAddressFromPubKey(publicKey, accountKeyType.pubkeyType, accountPrefix)
    }

    fun setInfoWithPrivateKey(privateKey: ByteArray?) {
        this.privateKey = privateKey
        publicKey = BaseKey.getPubKeyFromPKey(privateKey)
        address = BaseKey.getAddressFromPubKey(publicKey, accountKeyType.pubkeyType, accountPrefix)
    }

    fun initFetcher() {
        if (supportCosmosGrpc) {
            grpcFetcher = FetcherGrpc(this)
        }
    }

    open fun tokenValue(address: String, isUsd: Boolean? = false): BigDecimal {
        return BigDecimal.ZERO
    }

    open fun allTokenValue(isUsd: Boolean? = false): BigDecimal {
        return BigDecimal.ZERO
    }

    open fun allAssetValue(isUsd: Boolean?): BigDecimal {
        return BigDecimal.ZERO
    }

    open fun allValue(isUsd: Boolean?): BigDecimal {
        return BigDecimal.ZERO
    }

    open fun explorerAccount(): Uri? {
        return null
    }

    open fun explorerTx(hash: String?): Uri? {
        return null
    }

    open fun explorerProposal(id: String?): Uri? {
        return null
    }
}

data class AccountKeyType(
    var pubkeyType: PubKeyType, var hdPath: String
)

enum class PubKeyType { ETH_KECCAK256, COSMOS_SECP256K1, BERA_SECP256K1, SUI_ED25519, NONE }

//fun allIbcChains(): MutableList<CosmosLine> {
//    val lines = mutableListOf<CosmosLine>()
//    lines.addAll(allCosmosLines())
//    lines.addAll(allEvmLines().filter { it.supportCosmos })
//    return lines
//}