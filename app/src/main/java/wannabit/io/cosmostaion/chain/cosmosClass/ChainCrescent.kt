package wannabit.io.cosmostaion.chain.cosmosClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.PubKeyType

@Parcelize
class ChainCrescent : BaseChain(), Parcelable {

    override var name: String = "Crescent"
    override var tag: String = "crescent118"
    override var logo: Int = R.drawable.chain_cresent
    override var swipeLogo: Int = R.drawable.chain_swipe_cresent
    override var apiName: String = "crescent"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var supportCosmosGrpc: Boolean = true
    override var stakeDenom: String = "ucre"
    override var accountPrefix: String = "cre"
    override var grpcHost: String = "grpc-crescent.cosmostation.io"
}