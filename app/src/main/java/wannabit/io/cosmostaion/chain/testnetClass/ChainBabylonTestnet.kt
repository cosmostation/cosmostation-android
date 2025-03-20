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
import wannabit.io.cosmostaion.chain.fetcher.BabylonFetcher
import wannabit.io.cosmostaion.chain.fetcher.OktFetcher

@Parcelize
class ChainBabylonTestnet : BaseChain(), Parcelable {

    var babylonFetcher: BabylonFetcher? = null

    override var name: String = "Babylon Testnet"
    override var tag: String = "babylon118_T"
    override var logo: Int = R.drawable.chain_babylon_testnet
    override var isTestnet: Boolean = true
    override var apiName: String = "babylon-testnet"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_GRPC
    override var stakeDenom: String = "ubbn"
    override var accountPrefix: String = "bbn"
    override var grpcHost: String = "grpc-office-babylon.cosmostation.io"
    override var lcdUrl: String = "https://lcd-office.cosmostation.io/babylon-testnet/"

    fun babylonFetcher(): BabylonFetcher? {
        if (babylonFetcher == null) {
            babylonFetcher = BabylonFetcher(this)
        }
        return babylonFetcher
    }
}