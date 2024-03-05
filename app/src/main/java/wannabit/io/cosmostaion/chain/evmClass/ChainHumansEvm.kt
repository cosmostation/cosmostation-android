package wannabit.io.cosmostaion.chain.evmClass

import com.google.common.collect.ImmutableList
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.chain.PubKeyType

class ChainHumansEvm : EthereumLine() {

    override var supportCosmos: Boolean = true
    override var name: String = "Humans"
    override var tag: String = "humans60"
    override var logo: Int = R.drawable.chain_humans
    override var swipeLogo: Int = R.drawable.chain_swipe_humans
    override var apiName: String = "humans"
    override var stakeDenom: String? = "aheart"

    override var coinSymbol: String = "HEART"
    override var coinGeckoId: String = "humans-ai"
    override var coinLogo: Int = R.drawable.token_humans
    override var addressLogo: Int = R.drawable.icon_humans_address

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var accountPrefix: String? = "human"

    override var grpcHost: String = "grpc-humans.cosmostation.io"
    override var rpcUrl: String = "https://rpc-humans-app.cosmostation.io"

    override var explorerURL = "https://www.mintscan.io/humans/"
    override var addressURL = explorerURL + "address/"
    override var txURL = explorerURL + "tx/"
}