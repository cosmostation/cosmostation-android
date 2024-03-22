package wannabit.io.cosmostaion.chain.evmClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.chain.PubKeyType

@Parcelize
class ChainXplaEvm : EthereumLine(), Parcelable {

    override var supportCosmos: Boolean = true
    override var name: String = "Xpla"
    override var tag: String = "xplaKeccak256"
    override var logo: Int = R.drawable.chain_evm_xpla
    override var swipeLogo: Int = R.drawable.chain_swipe_evm_xpla
    override var apiName: String = "xpla"
    override var stakeDenom: String? = "axpla"

    override var coinSymbol: String = "XPLA"
    override var coinGeckoId: String = "xpla"
    override var coinLogo: Int = R.drawable.token_xpla
    override var addressLogo: Int = R.drawable.icon_xpla_address

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var accountPrefix: String? = "xpla"

    override var grpcHost: String = "grpc-xpla.cosmostation.io"
    override var rpcUrl: String = "https://rpc-xpla-evm.cosmostation.io"

    override var explorerURL = "https://www.mintscan.io/xpla/"
    override var addressURL = explorerURL + "address/"
    override var txURL = explorerURL + "tx/"
}