package wannabit.io.cosmostaion.chain.evmClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.fetcher.OktFetcher
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.common.CosmostationConstants

@Parcelize
open class ChainOktEvm : BaseChain(), Parcelable {

    var oktFetcher: OktFetcher? = null

    override var name: String = "OKT"
    override var tag: String = "okt60_Keccak"
    override var logo: Int = R.drawable.chain_evm_okt
    override var swipeLogo: Int = R.drawable.chain_evm_swipe_okt
    override var apiName: String = "okc"

    override var cosmosEndPointType: CosmosEndPointType? = CosmosEndPointType.USE_LCD
    override var stakeDenom: String = "okt"
    override var accountPrefix: String = "ex"
    override var supportStaking = false
    override var lcdUrl: String = "https://exchainrpc.okex.org/okexchain/v1/"

    override var supportEvm: Boolean = true
    override var coinSymbol: String = "OKT"
    override var coinGeckoId: String = "oec-token"
    override var coinLogo: Int = R.drawable.token_okt
    override var addressLogo: Int = R.drawable.icon_okt_address
    override var evmRpcURL: String = "https://exchainrpc.okex.org"

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/60'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    fun oktFetcher(): OktFetcher? {
        if (oktFetcher == null) {
            oktFetcher = OktFetcher(this)
        }
        return oktFetcher
    }

    override fun assetImg(originSymbol: String): String {
        return CosmostationConstants.CHAIN_BASE_URL + "okc/asset/" + originSymbol.lowercase() + ".png"
    }
}