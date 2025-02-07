package wannabit.io.cosmostaion.chain.cosmosClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.fetcher.CosmosFetcher
import wannabit.io.cosmostaion.chain.fetcher.ZenrockFetcher

@Parcelize
class ChainZenrock : BaseChain(), Parcelable {

    override var name: String = "Zenrock"
    override var tag: String = "zenrock118"
    override var logo: Int = R.drawable.chain_zenrock
    override var apiName: String = "zenrock"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/118'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var stakeDenom: String = "urock"
    override var accountPrefix: String = "zen"
    override var grpcHost: String = "grpc.zenrock.nodestake.org"
    override var lcdUrl: String = "https://api.zenrock.nodestake.org/"

    override fun cosmosFetcher(): CosmosFetcher? {
        if (cosmosFetcher == null) {
            cosmosFetcher = ZenrockFetcher(this)
        }
        return cosmosFetcher
    }

    fun zenrockFetcher(): ZenrockFetcher? {
        if (cosmosFetcher == null) {
            cosmosFetcher = ZenrockFetcher(this)
        }
        return cosmosFetcher as? ZenrockFetcher
    }
}