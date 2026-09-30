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
class ChainHaqqEvm : BaseChain(), Parcelable {

    override var name: String = "Haqq"
    override var tag: String = "haqq60"
    override var apiName: String = "haqq"

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_GRPC
    override var stakeDenom: String = "aISLM"
    override var accountPrefix: String = "haqq"
    override var grpcHost: String = "grpc-haqq.mainnet.cosmoslabs.kr"
    override var lcdUrl: String = "https://lcd-haqq.mainnet.cosmoslabs.kr/"

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "ISLM"
    override var evmRpcURL: String = "https://rpc.eth.haqq.network"
}