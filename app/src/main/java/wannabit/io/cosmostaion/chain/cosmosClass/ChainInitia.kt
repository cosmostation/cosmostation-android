package wannabit.io.cosmostaion.chain.cosmosClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.fetcher.CosmosFetcher
import wannabit.io.cosmostaion.chain.fetcher.InitiaFetcher

@Parcelize
open class ChainInitia : BaseChain(), Parcelable {

    override var name: String = "Initia"
    override var tag: String = "initia"
    override var apiName: String = "initia"

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var stakeDenom: String = "uinit"
    override var accountPrefix: String = "init"
    override var grpcHost: String = ""
    override var lcdUrl: String = "https://rest.initia.xyz/"

    override fun cosmosFetcher(): CosmosFetcher? {
        if (cosmosFetcher == null) {
            cosmosFetcher = InitiaFetcher(this)
        }
        return cosmosFetcher
    }

    fun initiaFetcher(): InitiaFetcher? {
        if (cosmosFetcher == null) {
            cosmosFetcher = InitiaFetcher(this)
        }
        return cosmosFetcher as? InitiaFetcher
    }
}