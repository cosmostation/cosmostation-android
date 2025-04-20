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
class ChainTabichainTestnet : BaseChain(), Parcelable {

    override var name: String = "Tabichain Testnet"
    override var tag: String = "tabichain118_T"
    override var isTestnet: Boolean = true
    override var apiName: String = "tabichain-testnet"

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var stakeDenom: String = "atabi"
    override var accountPrefix: String = "tabis"
    override var grpcHost: String = ""
    override var lcdUrl: String = "https://api.testnetv2.tabichain.com/"

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "TABI"
    override var evmRpcURL: String = "https://rpc.testnetv2.tabichain.com"
}