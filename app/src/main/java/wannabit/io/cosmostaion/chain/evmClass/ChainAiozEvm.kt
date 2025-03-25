package wannabit.io.cosmostaion.chain.evmClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.PubKeyType

@Parcelize
class ChainAiozEvm : BaseChain(), Parcelable {

    override var name: String = "Aioz"
    override var tag: String = "aioz60"
    override var logo: Int = R.drawable.chain_aioz
    override var apiName: String = "aioz"

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var stakeDenom: String = "attoaioz"
    override var accountPrefix: String = "aioz"
    override var grpcHost: String = ""
    override var lcdUrl: String = "https://lcd-dataseed.aioz.network/"

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "AIOZ"
    override var addressLogo: Int = R.drawable.icon_ethereum_address
    override var evmRpcURL: String = "https://eth-dataseed.aioz.network"
}