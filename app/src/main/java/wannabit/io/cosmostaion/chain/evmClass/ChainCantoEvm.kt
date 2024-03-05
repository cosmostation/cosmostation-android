package wannabit.io.cosmostaion.chain.evmClass

import com.google.common.collect.ImmutableList
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.chain.PubKeyType

class ChainCantoEvm : EthereumLine() {

    override var supportCosmos: Boolean = true
    override var name: String = "Canto"
    override var tag: String = "canto60"
    override var logo: Int = R.drawable.chain_canto
    override var swipeLogo: Int = R.drawable.chain_swipe_canto
    override var apiName: String = "canto"
    override var stakeDenom: String? = "acanto"

    override var coinSymbol: String = "CANTO"
    override var coinGeckoId: String = "canto"
    override var coinLogo: Int = R.drawable.token_canto
    override var addressLogo: Int = R.drawable.icon_canto_address

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var accountPrefix: String? = "canto"

    override var grpcHost: String = "grpc-canto.cosmostation.io"
    override var rpcUrl: String = "https://rpc-canto-app.cosmostation.io"

    override var explorerURL = "https://www.mintscan.io/canto/"
    override var addressURL = explorerURL + "address/"
    override var txURL = explorerURL + "tx/"
}