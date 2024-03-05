package wannabit.io.cosmostaion.chain.evmClass

import com.google.common.collect.ImmutableList
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.chain.PubKeyType

class ChainAltheaEvm : EthereumLine() {

    override var supportCosmos: Boolean = true
    override var name: String = "Althea"
    override var tag: String = "althea60"
    override var logo: Int = R.drawable.chain_althea
    override var swipeLogo: Int = R.drawable.chain_swipe_althea
    override var apiName: String = "althea"
    override var stakeDenom: String? = "ualtg"

    override var coinSymbol: String = "ALTG"
    override var coinGeckoId: String = ""
    override var coinLogo: Int = R.drawable.token_althea
    override var addressLogo: Int = R.drawable.icon_althea_address

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var accountPrefix: String? = "althea"

    override var grpcHost: String = "grpc-althea.cosmostation.io"
    override var rpcUrl: String = "https://rpc-althea-app.cosmostation.io"

    override var explorerURL = "https://www.mintscan.io/althea/"
    override var addressURL = explorerURL + "address/"
    override var txURL = explorerURL + "tx/"
}