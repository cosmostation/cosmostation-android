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
class ChainCoreum : BaseChain(), Parcelable {

    override var name: String = "Coreum"
    override var tag: String = "coreum118"
    override var logo: Int = R.drawable.chain_coreum
    override var swipeLogo: Int = R.drawable.chain_swipe_coreum
    override var apiName: String = "coreum"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/990'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(990, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var supportCosmosGrpc: Boolean = true
    override var stakeDenom: String = "ucore"
    override var accountPrefix: String = "core"
    override var grpcHost: String = "grpc-coreum.cosmostation.io"
}