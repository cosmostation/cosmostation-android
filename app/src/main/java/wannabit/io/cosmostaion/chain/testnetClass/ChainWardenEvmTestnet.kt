package wannabit.io.cosmostaion.chain.testnetClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.evmClass.ChainWardenEvm

@Parcelize
class ChainWardenEvmTestnet : ChainWardenEvm(), Parcelable {

    override var name: String = "Warden Protocol Testnet"
    override var tag: String = "warden60_T"
    override var isTestnet: Boolean = true
    override var apiName: String = "warden-testnet"

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var stakeDenom: String = "award"
    override var accountPrefix: String = "warden"
    override var grpcHost: String = "grpc.chiado.wardenprotocol.org"
    override var lcdUrl: String = "https://api.chiado.wardenprotocol.org/"

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "WARD"
    override var evmRpcURL: String = "https://evm.chiado.wardenprotocol.org"
}