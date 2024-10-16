package wannabit.io.cosmostaion.chain.evmClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.fetcher.CosmosFetcher
import wannabit.io.cosmostaion.chain.fetcher.KavaFetcher
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.common.CosmostationConstants

@Parcelize
open class ChainKavaEvm : BaseChain(), Parcelable {

    override var name: String = "Kava"
    override var tag: String = "kava60"
    override var logo: Int = R.drawable.chain_evm_kava
    override var swipeLogo: Int = R.drawable.chain_swipe_evm_kava
    override var apiName: String = "kava"

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_GRPC
    override var stakeDenom: String = "ukava"
    override var accountPrefix: String = "kava"
    override var grpcHost: String = "grpc-kava.cosmostation.io"
    override var lcdUrl: String = "https://lcd-kava.cosmostation.io/"

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "KAVA"
    override var coinGeckoId: String = "kava"
    override var coinLogo: Int = R.drawable.token_kava
    override var addressLogo: Int = R.drawable.icon_kava_address
    override var evmRpcURL: String = "https://rpc-kava-evm.cosmostation.io"

    override fun cosmosFetcher(): CosmosFetcher? {
        if (cosmosFetcher == null) {
            cosmosFetcher = KavaFetcher(this)
        }
        return cosmosFetcher
    }
}

const val KAVA_MINT_IMG_URL = CosmostationConstants.CHAIN_BASE_URL + "kava/module/mint/"
const val KAVA_LEND_IMG_URL = CosmostationConstants.CHAIN_BASE_URL + "kava/module/lend/"