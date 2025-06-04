package wannabit.io.cosmostaion.chain.testnetClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.evmClass.ChainQubeticsEvm

@Parcelize
class ChainQubeticsEvmTestnet : ChainQubeticsEvm(), Parcelable {

    override var name: String = "Qubetics Testnet"
    override var tag: String = "qubetics60_T"
    override var isTestnet: Boolean = true
    override var apiName: String = "qubetics-testnet"

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var stakeDenom: String = "tics"
    override var grpcHost: String = "grpc-testnet.qubetics.work"
    override var lcdUrl: String = "https://swagger-testnet.qubetics.work/"

    override var evmRpcURL: String = "https://rpc-testnet.qubetics.work"
}