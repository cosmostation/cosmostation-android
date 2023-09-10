package wannabit.io.cosmostaion.chain

import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.common.BaseKey

open class BaseChain {

    open var chainType: ChainType? = null
    open var name: String = ""
    open var id: String = ""
    open var logo: Int = -1
    open var swipeLogo: Int = -1
    open var isDefault: Boolean = true
    open var apiName: String = ""
    open var accountPrefix: String? = ""
    open var grpcHost: String = ""

    open lateinit var accountKeyType: AccountKeyType
    open var setParentPath: List<ChildNumber> = mutableListOf()
    open var address: String? = ""

    open var fetched = false

    fun getHDPath(lastPath: String): String {
        return accountKeyType.hdPath.replace("X", lastPath)
    }

    fun setInfoWithSeed(seed: ByteArray?, parentPath: List<ChildNumber>, lastPath: String) {
        val privateKey = BaseKey.getPrivateKey(seed, parentPath, lastPath)
        val publicKey = BaseKey.getPubKeyFromPKey(privateKey)
        address = BaseKey.getAddressFromPubKey(publicKey, accountKeyType.pubkeyType, accountPrefix)
    }

    fun setInfoWithPrivateKey(privateKey: ByteArray?) {
        val publicKey = BaseKey.getPubKeyFromPKey(privateKey)
        address = BaseKey.getAddressFromPubKey(publicKey, accountKeyType.pubkeyType, accountPrefix)
    }
}

enum class ChainType { COSMOS_TYPE, ETH_TYPE, SUI_TYPE }

data class AccountKeyType(
    var pubkeyType: PubKeyType,
    var hdPath: String
)

enum class PubKeyType { ETH_KECCAK256, COSMOS_SECP256K1, SUI_ED25519, NONE }