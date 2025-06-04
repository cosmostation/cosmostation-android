package wannabit.io.cosmostaion.chain.testnetClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.evmClass.ChainSaharaAiEvm

@Parcelize
class ChainSaharaAiEvmTestnet : ChainSaharaAiEvm(), Parcelable {

    override var name: String = "Sahara AI Testnet"
    override var tag: String = "saharaai60_T"
    override var isTestnet: Boolean = true
    override var apiName: String = "saharaai-testnet"

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var stakeDenom: String = "usah"
    override var grpcHost: String = ""
    override var lcdUrl: String = "https://sahara-api.testnet.moonlet.cloud/public/"

    override var evmRpcURL: String = "https://testnet.saharalabs.ai"
}