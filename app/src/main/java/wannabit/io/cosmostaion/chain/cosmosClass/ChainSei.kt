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
class ChainSei : BaseChain(), Parcelable {

    override var name: String = "Sei"
    override var tag: String = "sei118"
    override var logo: Int = R.drawable.chain_sei
    override var swipeLogo: Int = R.drawable.chain_swipe_sei
    override var apiName: String = "sei"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_GRPC
    override var stakeDenom: String = "usei"
    override var accountPrefix: String = "sei"
    override var grpcHost: String = "grpc-sei.cosmostation.io"
    override var lcdUrl: String = "https://lcd-sei.cosmostation.io/"
}