package wannabit.io.cosmostaion.chain.evmClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.PubKeyType

@Parcelize
class ChainDymensionEvm : BaseChain(), Parcelable {

    override var name: String = "Dymension"
    override var tag: String = "dymension60"
    override var logo: Int = R.drawable.chain_dymension
    override var swipeLogo: Int = R.drawable.chain_swipe_dymension
    override var apiName: String = "dymension"

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var supportCosmosGrpc: Boolean = true
    override var stakeDenom: String = "adym"
    override var accountPrefix: String? = "dym"
    override var grpcHost: String = "grpc-dymension.cosmostation.io"

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "DYM"
    override var coinGeckoId: String = "dymension"
    override var coinLogo: Int = R.drawable.token_eth
    override var evmRpcURL: String = "https://rpc-dymension-evm.cosmostation.io"

    init {
        initFetcher()
    }
}