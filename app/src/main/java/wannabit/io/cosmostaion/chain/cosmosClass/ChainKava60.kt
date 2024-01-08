package wannabit.io.cosmostaion.chain.cosmosClass

import com.google.common.collect.ImmutableList
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.ChainType
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.common.CosmostationConstants

open class ChainKava60 : CosmosLine() {

    override var chainType: ChainType? = ChainType.COSMOS_TYPE
    override var name: String = "Kava"
    override var tag: String = "kava60"
    override var logo: Int = R.drawable.chain_kava
    override var swipeLogo: Int = R.drawable.chain_swipe_kava
    override var apiName: String = "kava"
    override var stakeDenom: String? = "ukava"

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )
    override var accountPrefix: String? = "kava"
    override var supportErc20: Boolean = true
    override var evmCompatible: Boolean = true

    override var grpcHost: String = "grpc-kava.cosmostation.io"
    override var rpcUrl: String = "https://rpc-kava-app.cosmostation.io"
}

const val KAVA_MINT_IMG_URL = CosmostationConstants.CHAIN_BASE_URL + "kava/module/mint/"
const val KAVA_LEND_IMG_URL = CosmostationConstants.CHAIN_BASE_URL + "kava/module/lend/"