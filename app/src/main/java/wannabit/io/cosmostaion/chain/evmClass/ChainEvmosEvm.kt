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
class ChainEvmosEvm : EthereumLine(), Parcelable {

    override var supportCosmos: Boolean = true
    override var name: String = "Evmos"
    override var tag: String = "evmos60"
    override var logo: Int = R.drawable.chain_evmos
    override var swipeLogo: Int = R.drawable.chain_swipe_evmos
    override var apiName: String = "evmos"
    override var stakeDenom: String? = "aevmos"

    override var coinSymbol: String = "EVMOS"
    override var coinGeckoId: String = "evmos"
    override var coinLogo: Int = R.drawable.token_evmos
    override var addressLogo: Int = R.drawable.icon_evmos_address

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var accountPrefix: String? = "evmos"

    override var grpcHost: String = "grpc-evmos.cosmostation.io"
    override var rpcUrl: String = "https://rpc-evmos-evm.cosmostation.io"
}