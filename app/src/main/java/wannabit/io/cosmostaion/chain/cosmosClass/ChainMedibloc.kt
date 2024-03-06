package wannabit.io.cosmostaion.chain.cosmosClass

import com.google.common.collect.ImmutableList
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.PubKeyType

class ChainMedibloc : CosmosLine() {

    override var name: String = "Medibloc"
    override var tag: String = "medibloc371"
    override var logo: Int = R.drawable.chain_medibloc
    override var swipeLogo: Int = R.drawable.chain_swipe_medibloc
    override var apiName: String = "medibloc"
    override var stakeDenom: String? = "umed"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/371'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(371, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )
    override var accountPrefix: String? = "panacea"

    override var grpcHost: String = "grpc-medibloc.cosmostation.io"
}