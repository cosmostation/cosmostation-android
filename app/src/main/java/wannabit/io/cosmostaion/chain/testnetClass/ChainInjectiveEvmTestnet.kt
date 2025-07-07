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
class ChainInjectiveEvmTestnet : BaseChain(), Parcelable {

    override var name: String = "Injective Testnet"
    override var tag: String = "injective60_T"
    override var isTestnet: Boolean = true
    override var apiName: String = "injective-testnet"

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var stakeDenom: String = "inj"
    override var accountPrefix: String = "inj"
    override var grpcHost: String = "testnet.sentry.chain.grpc.injective.network"
    override var lcdUrl: String = "https://lcd-office.cosmostation.io/injective-testnet/"

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "INJ"
    override var evmRpcURL: String = "https://rpc-office-evm.cosmostation.io/injective-testnet/"
}