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
class ChainRealioEvm : BaseChain(), Parcelable {

    override var name: String = "Realio"
    override var tag: String = "realio60"
    override var logo: Int = R.drawable.chain_realio
    override var apiName: String = "realio"

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var stakeDenom: String = "ario"
    override var accountPrefix: String = "realio"
    override var grpcHost: String = ""
    override var lcdUrl: String = "https://realio-api.genznodes.dev/"

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "RIO"
    override var addressLogo: Int = R.drawable.icon_ethereum_address
    override var evmRpcURL: String = "https://realio-rpc-evm.genznodes.dev"
}