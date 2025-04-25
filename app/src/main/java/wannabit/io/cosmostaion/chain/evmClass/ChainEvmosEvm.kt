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
class ChainEvmosEvm : BaseChain(), Parcelable {

    override var name: String = "Evmos"
    override var tag: String = "evmos60"
    override var apiName: String = "evmos"

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var stakeDenom: String = "aevmos"
    override var accountPrefix: String = "evmos"
    override var grpcHost: String = "evmos-grpc.stake-town.com"
    override var lcdUrl: String = "https://evmos-api.polkachu.com/"

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "EVMOS"
    override var evmRpcURL: String = "https://evmos-evm.publicnode.com"
}