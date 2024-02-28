package wannabit.io.cosmostaion.chain.evmClass

import com.google.common.collect.ImmutableList
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.chain.PubKeyType

class ChainKavaEvm : EthereumLine() {

    override var supportCosmos: Boolean = true
    override var name: String = "Kava"
    override var tag: String = "kava60"
    override var logo: Int = R.drawable.chain_kava
    override var swipeLogo: Int = R.drawable.chain_swipe_kava
    override var apiName: String = "kava"
    override var stakeDenom: String? = "ukava"

    override var coinSymbol: String = "KAVA"
    override var coinGeckoId: String = "kava"
    override var coinLogo: Int = R.drawable.token_kava

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var accountPrefix: String? = "kava"

    override var grpcHost: String = "grpc-kava.cosmostation.io"
    override var rpcUrl: String = "https://rpc-kava-app.cosmostation.io"

    override var explorerURL = "https://kavascan.io/"
    override var addressURL = explorerURL + "address/"
    override var txURL = explorerURL + "tx/"
}