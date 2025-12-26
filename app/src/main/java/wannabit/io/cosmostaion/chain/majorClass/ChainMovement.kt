package wannabit.io.cosmostaion.chain.majorClass

import android.content.Context
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.common.BaseKey

@Parcelize
class ChainMovement : BaseChain(), Parcelable {

    override var name: String = "Movement"
    override var tag: String = "movementMainnet"
    override var apiName: String = "movement"
    override var isMoveChain: Boolean = true

    override var accountKeyType = AccountKeyType(PubKeyType.APTOS_ED25519, "m/44'/637'/0'/0/X")

    override var stakeDenom: String = APTOS_MAIN_DENOM
    override var coinSymbol: String = "MOVE"

    override var apiUrl: String = "https://mainnet.movementnetwork.xyz/v1/"
    override var mainUrl: String = "https://indexer.mainnet.movementnetwork.xyz/v1/graphql"

    override suspend fun setInfoWithPrivateKey(context: Context, privateKey: ByteArray?) {
        this.privateKey = privateKey
        publicKey = BaseKey.getPubKeyFromPKey(privateKey, accountKeyType.pubkeyType)
        mainAddress = BaseKey.getAddressFromPubKey(context, publicKey, accountKeyType.pubkeyType)
    }
}