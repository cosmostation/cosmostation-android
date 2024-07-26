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
class ChainJuno : BaseChain(), Parcelable {

    override var name: String = "Juno"
    override var tag: String = "juno118"
    override var logo: Int = R.drawable.chain_juno
    override var swipeLogo: Int = R.drawable.chain_swipe_juno
    override var apiName: String = "juno"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_GRPC
    override var supportCw20: Boolean = true
    override var stakeDenom: String = "ujuno"
    override var accountPrefix: String = "juno"
    override var grpcHost: String = "grpc-juno.cosmostation.io"
}