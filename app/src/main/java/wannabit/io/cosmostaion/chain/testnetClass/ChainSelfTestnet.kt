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
class ChainSelfTestnet : BaseChain(), Parcelable {

    override var name: String = "Selfchain Testnet"
    override var tag: String = "selfchain118_T"
    override var logo: Int = R.drawable.chain_selfchain_testnet
    override var isTestnet: Boolean = true
    override var apiName: String = "selfchain-testnet"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_GRPC
    override var stakeDenom: String = "uslf"
    override var accountPrefix: String = "self"
    override var grpcHost: String = "grpc.testnet.selfchain.cosmostation.io"
    override var lcdUrl: String = "https://lcd.testnet.selfchain.cosmostation.io/"
}