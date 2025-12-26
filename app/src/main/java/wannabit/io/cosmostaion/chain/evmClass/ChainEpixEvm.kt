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
class ChainEpixEvm : BaseChain(), Parcelable {

    override var name: String = "Epix"
    override var tag: String = "epix60"
    override var apiName: String = "epix"

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var stakeDenom: String = "aepix"
    override var accountPrefix: String = "epix"
    override var grpcHost: String = "grpc.epix.zone:15067"
    override var lcdUrl: String = "https://api.epix.zone/"

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "EPIX"
    override var evmRpcURL: String = "https://evmrpc.epix.zone/"
}