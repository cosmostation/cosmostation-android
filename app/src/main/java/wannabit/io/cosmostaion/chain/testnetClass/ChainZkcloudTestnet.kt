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
class ChainZkcloudTestnet : BaseChain(), Parcelable {

    override var name: String = "ZkCloud Testnet"
    override var tag: String = "zkcloud118_T"
    override var logo: Int = R.drawable.chain_zkcloud_testnet
    override var isTestnet: Boolean = true
    override var apiName: String = "zkcloud-testnet"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var stakeDenom: String = "uproof"
    override var accountPrefix: String = "zkcloud"
    override var grpcHost: String = "grpc.testnet.zkcloud.com"
    override var lcdUrl: String = "https://api.testnet.zkcloud.com/"
}