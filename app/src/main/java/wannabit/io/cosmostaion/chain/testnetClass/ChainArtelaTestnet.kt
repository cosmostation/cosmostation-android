package wannabit.io.cosmostaion.chain.testnetClass

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
class ChainArtelaTestnet : BaseChain(), Parcelable {

    override var name: String = "Artela Testnet"
    override var tag: String = "artela60_T"
    override var isTestnet: Boolean = true
    override var apiName: String = "artela-testnet"

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_GRPC
    override var stakeDenom: String = "uart"
    override var accountPrefix: String = "art"
    override var grpcHost: String = "grpc-office-artela.cosmostation.io"
    override var lcdUrl: String = "https://lcd-office.cosmostation.io/artela-testnet/"

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "ART"
    override var evmRpcURL: String = "https://rpc-office-evm.cosmostation.io/artela-testnet/"
}