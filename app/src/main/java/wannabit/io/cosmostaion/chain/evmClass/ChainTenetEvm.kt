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
class ChainTenetEvm : BaseChain(), Parcelable {

    override var name: String = "Tenet"
    override var tag: String = "tenet60"
    override var apiName: String = "tenet"

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var stakeDenom: String = "atenet"
    override var accountPrefix: String = "tenet"
    override var grpcHost: String = ""
    override var lcdUrl: String = "https://tenet-rest.publicnode.com/"

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "TENET"
    override var evmRpcURL: String = "https://tenet-evm.publicnode.com"
}