package wannabit.io.cosmostaion.chain.cosmosClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.FetcherLcd
import wannabit.io.cosmostaion.chain.OktFetcher
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.common.BaseKey
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.common.CosmostationConstants

@Parcelize
open class ChainOkt996Keccak : BaseChain(), Parcelable {

    var oktFetcher: OktFetcher? = null

    override var name: String = "OKT"
    override var tag: String = "okt996_Keccak"
    override var logo: Int = R.drawable.chain_okt
    override var swipeLogo: Int = R.drawable.chain_swipe_okt
    override var isDefault: Boolean = false
    override var apiName: String = "okc"

    override var accountKeyType = AccountKeyType(PubKeyType.ETH_KECCAK256, "m/44'/996'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(996, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var supportCosmosLcd: Boolean = true
    override var stakeDenom: String = "okt"
    override var accountPrefix: String = "ex"
    override var supportStaking = false
    override var lcdUrl: String = "https://exchainrpc.okex.org/okexchain/v1/"

    override fun setInfoWithPrivateKey(privateKey: ByteArray?) {
        this.privateKey = privateKey
        publicKey = BaseKey.getPubKeyFromPKey(privateKey)
        evmAddress =
            BaseKey.getAddressFromPubKey(publicKey, accountKeyType.pubkeyType, accountPrefix)
        address = ByteUtils.convertEvmToBech32(evmAddress, accountPrefix)
    }

    override fun lcdFetcher(): FetcherLcd? {
        if (oktFetcher == null) {
            oktFetcher = OktFetcher(this)
        }
        return oktFetcher
    }

    override fun assetImg(originSymbol: String): String {
        return CosmostationConstants.CHAIN_BASE_URL + "okc/asset/" + originSymbol.lowercase() + ".png"
    }
}

const val OKT_BASE_FEE = "0.008000000000000000"
const val OKT_GECKO_ID = "oec-token"