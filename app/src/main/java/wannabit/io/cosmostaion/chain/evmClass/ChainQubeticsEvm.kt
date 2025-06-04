package wannabit.io.cosmostaion.chain.evmClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.PubKeyType

@Parcelize
open class ChainQubeticsEvm : BaseChain(), Parcelable {

    override var name: String = "Qubetics"
    override var tag: String = "qubetics0"
    override var apiName: String = "qubetics"

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var stakeDenom: String = "tics"
    override var accountPrefix: String = "qubetics"
    override var grpcHost: String = ""
    override var lcdUrl: String = ""

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "TICS"
    override var evmRpcURL: String = ""
}