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
class ChainCronos : BaseChain(), Parcelable {

    override var name: String = "Cronos"
    override var tag: String = "cronos60"
    override var logo: Int = R.drawable.chain_cronos
    override var swipeLogo: Int = R.drawable.chain_swipe_cronos
    override var apiName: String = "cronos"

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "CRO"
    override var coinGeckoId: String = "crypto-com-chain"
    override var coinLogo: Int = R.drawable.token_cro
    override var supportStaking = false

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var evmRpcURL: String = "https://evm.cronos.org"
}