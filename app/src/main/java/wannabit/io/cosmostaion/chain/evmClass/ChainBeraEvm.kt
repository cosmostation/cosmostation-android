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
open class ChainBeraEvm : EthereumLine(), Parcelable {

    override var supportCosmos: Boolean = true
    override var name: String = "Bera"
    override var tag: String = "bera60"
    override var logo: Int = R.drawable.chain_evm_bera
    override var swipeLogo: Int = R.drawable.chain_swipe_evm_bera
    override var apiName: String = "berachain"
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

    override var grpcHost: String = ""
    override var rpcUrl: String = ""
}

const val BERA_CONT_BANK = "0x4381dC2aB14285160c808659aEe005D51255adD7"
const val BERA_CONT_STAKING = "0xd9A998CaC66092748FfEc7cFBD155Aae1737C2fF"