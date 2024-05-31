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
class ChainBeraTestEvm : ChainBeraEvm(), Parcelable {

    override var supportCosmos: Boolean = true
    override var name: String = "Bera Testnet"
    override var tag: String = "bera60-test"
    override var logo: Int = R.drawable.chain_evm_bera
    override var swipeLogo: Int = R.drawable.chain_swipe_evm_bera
    override var apiName: String = "berachain-testnet"
    override var stakeDenom: String? = "abgt"

    override var coinSymbol: String = "BERA"
    override var coinGeckoId: String = ""
    override var coinLogo: Int = R.drawable.token_bera
    override var addressLogo: Int = R.drawable.icon_xpla_address

    override var accountKeyType = AccountKeyType(PubKeyType.BERA_SECP256K1, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var accountPrefix: String? = "bera"

    override var grpcHost: String = "grpc-office-berachain.cosmostation.io"
    override var rpcUrl: String = "https://rpc-office-evm.cosmostation.io/berachain-testnet/"
}