package wannabit.io.cosmostaion.chain.cosmosClass

import com.google.common.collect.ImmutableList
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.PubKeyType

class ChainCoreum : CosmosLine() {

    override var name: String = "Coreum"
    override var tag: String = "coreum990"
    override var logo: Int = R.drawable.chain_coreum
    override var swipeLogo: Int = R.drawable.chain_swipe_coreum
    override var apiName: String = "coreum"
    override var stakeDenom: String? = "ucore"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/990'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(990, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )
    override var accountPrefix: String? = "core"

    override var grpcHost: String = "grpc-coreum.cosmostation.io"
}