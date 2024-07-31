package wannabit.io.cosmostaion.chain.cosmosClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.PubKeyType

@Parcelize
class ChainGravityBridge : BaseChain(), Parcelable {

    override var name: String = "G-Bridge"
    override var tag: String = "gravity-bridge118"
    override var logo: Int = R.drawable.chain_gravitybridge
    override var swipeLogo: Int = R.drawable.chain_swipe_gravitybridge
    override var apiName: String = "gravity-bridge"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_GRPC
    override var stakeDenom: String = "ugraviton"
    override var accountPrefix: String = "gravity"
    override var grpcHost: String = "grpc-gravity-bridge.cosmostation.io"
    override var lcdUrl: String = "https://lcd-gravity-bridge.cosmostation.io/"
}