package wannabit.io.cosmostaion.chain.cosmosClass

import com.google.common.collect.ImmutableList
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.PubKeyType

class ChainProvenance : CosmosLine() {

    override var name: String = "Provenance"
    override var tag: String = "provenance505"
    override var logo: Int = R.drawable.chain_provenance
    override var swipeLogo: Int = R.drawable.chain_swipe_provenance
    override var apiName: String = "provenance"
    override var stakeDenom: String? = "upasg"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/505'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(505, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )
    override var accountPrefix: String? = "pb"

    override var grpcHost: String = "grpc-provenance.cosmostation.io"
}