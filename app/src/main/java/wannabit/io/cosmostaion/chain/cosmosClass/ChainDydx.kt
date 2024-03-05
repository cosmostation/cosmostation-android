package wannabit.io.cosmostaion.chain.cosmosClass

import com.google.common.collect.ImmutableList
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.PubKeyType

class ChainDydx : CosmosLine() {

    override var name: String = "Dydx"
    override var tag: String = "dydx118"
    override var logo: Int = R.drawable.chain_dydx
    override var swipeLogo: Int = R.drawable.chain_swipe_dydx
    override var apiName: String = "dydx"
    override var stakeDenom: String? = "adydx"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )
    override var accountPrefix: String? = "dydx"

    override var grpcHost: String = "grpc-dydx.cosmostation.io"
}

const val DYDX_USDC_DENOM = "ibc/8E27BA2D5493AF5636760E354E46004562C46AB7EC0CC4C1CA14E9E20E2545B5"