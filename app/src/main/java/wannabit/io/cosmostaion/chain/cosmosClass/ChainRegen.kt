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
class ChainRegen : BaseChain(), Parcelable {

    override var name: String = "Regen"
    override var tag: String = "regen118"
    override var logo: Int = R.drawable.chain_regen
    override var swipeLogo: Int = R.drawable.chain_swipe_regen
    override var apiName: String = "regen"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var supportCosmosGrpc: Boolean = true
    override var stakeDenom: String = "uregen"
    override var accountPrefix: String = "regen"
    override var grpcHost: String = "grpc-regen.cosmostation.io"
}