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
class ChainDymensionEvm : EthereumLine(), Parcelable {

    override var supportCosmos: Boolean = true
    override var name: String = "Dymension"
    override var tag: String = "dymension60"
    override var logo: Int = R.drawable.chain_dymension
    override var swipeLogo: Int = R.drawable.chain_swipe_dymension
    override var apiName: String = "dymension"
    override var stakeDenom: String? = "adym"

    override var coinSymbol: String = "DYM"
    override var coinGeckoId: String = "dymension"
    override var coinLogo: Int = R.drawable.token_dymension
    override var addressLogo: Int = R.drawable.icon_dymension_address

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var accountPrefix: String? = "dym"

    override var grpcHost: String = "grpc-dymension.cosmostation.io"
    override var rpcUrl: String = "https://rpc-dymension-evm.cosmostation.io"

    override var explorerURL = "https://www.mintscan.io/dymension/"
    override var addressURL = explorerURL + "address/"
    override var txURL = explorerURL + "tx/"
}