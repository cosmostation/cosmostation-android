package wannabit.io.cosmostaion.chain.testnetClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.PubKeyType

@Parcelize
class ChainMantraEvmTestnet : BaseChain(), Parcelable {

    override var name: String = "Dukong"
    override var tag: String = "dukong60_T"
    override var isTestnet: Boolean = true
    override var apiName: String = "mantra-testnet"

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_GRPC
    override var stakeDenom: String = "uom"
    override var accountPrefix: String = "mantra"
    override var grpcHost: String = "grpc-office-mantra.cosmostation.io"
    override var lcdUrl: String = "https://lcd-office.cosmostation.io/mantra-testnet/"

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "OM"
    override var evmRpcURL: String = "https://rpc.evm.mantra.testnet.cosmostation.io"
}