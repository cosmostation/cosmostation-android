package wannabit.io.cosmostaion.chain.testnetClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.cosmosClass.ChainZigChain

@Parcelize
class ChainZigChainTestnet : ChainZigChain(), Parcelable {

    override var name: String = "ZigChain Testnet"
    override var tag: String = "zigChain118_T"
    override var isTestnet: Boolean = true
    override var apiName: String = "zigchain-testnet"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var stakeDenom: String = "uzig"
    override var accountPrefix: String = "zig"
    override var grpcHost: String = ""
    override var lcdUrl: String = "https://testnet-api.zigchain.com/"
}