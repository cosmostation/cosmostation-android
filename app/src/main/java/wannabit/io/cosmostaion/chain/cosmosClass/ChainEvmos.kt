package wannabit.io.cosmostaion.chain.cosmosClass

import com.google.common.collect.ImmutableList
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.ChainType
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.PubKeyType

class ChainEvmos : CosmosLine() {

    override var chainType: ChainType? = ChainType.COSMOS_TYPE
    override var name: String = "Evmos"
    override var tag: String = "evmos60"
    override var logo: Int = R.drawable.chain_evmos
    override var swipeLogo: Int = R.drawable.chain_swipe_evmos
    override var apiName: String = "evmos"
    override var stakeDenom: String? = "aevmos"

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true),
        ChildNumber(60, true),
        ChildNumber.ZERO_HARDENED,
        ChildNumber.ZERO
    )
    override var accountPrefix: String? = "evmos"
    override var supportErc20: Boolean = true
    override var evmCompatible: Boolean = true

    override var grpcHost: String = "grpc-evmos.cosmostation.io"
    override var rpcUrl: String = "https://rpc-evmos-app.cosmostation.io"
}