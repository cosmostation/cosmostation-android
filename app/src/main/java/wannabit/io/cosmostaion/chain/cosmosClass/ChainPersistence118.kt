package wannabit.io.cosmostaion.chain.cosmosClass

import com.google.common.collect.ImmutableList
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.PubKeyType

open class ChainPersistence118 : CosmosLine() {

    override var name: String = "Persistence"
    override var tag: String = "persistence118"
    override var logo: Int = R.drawable.chain_persistence
    override var swipeLogo: Int = R.drawable.chain_swipe_persistence
    override var apiName: String = "persistence"
    override var stakeDenom: String? = "uxprt"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )
    override var accountPrefix: String? = "persistence"

    override var grpcHost: String = "grpc-persistence.cosmostation.io"
}