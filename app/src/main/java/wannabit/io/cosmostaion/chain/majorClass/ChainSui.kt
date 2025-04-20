package wannabit.io.cosmostaion.chain.majorClass

import android.content.Context
import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.fetcher.SuiFetcher
import wannabit.io.cosmostaion.chain.fetcher.assetImg
import wannabit.io.cosmostaion.chain.fetcher.suiCoinSymbol
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseKey

@Parcelize
class ChainSui : BaseChain(), Parcelable {

    @IgnoredOnParcel
    var suiFetcher: SuiFetcher? = null

    override var name: String = "Sui"
    override var tag: String = "suiMainnet"
    override var apiName: String = "sui"

    override var accountKeyType = AccountKeyType(PubKeyType.SUI_ED25519, "m/44'/784'/0'/0/X")

    override var stakeDenom: String = SUI_MAIN_DENOM
    override var coinSymbol: String = "SUI"

    override var mainUrl: String = "https://sui-mainnet-us-2.cosmostation.io"

    override suspend fun setInfoWithPrivateKey(context: Context, privateKey: ByteArray?) {
        this.privateKey = privateKey
        publicKey = BaseKey.getPubKeyFromPKey(privateKey, accountKeyType.pubkeyType)
        mainAddress = BaseKey.getAddressFromPubKey(context, publicKey, accountKeyType.pubkeyType)
    }

    fun suiFetcher(): SuiFetcher? {
        if (suiFetcher != null) return suiFetcher
        suiFetcher = SuiFetcher(this)
        return suiFetcher
    }

    override fun assetImg(originSymbol: String): String {
        suiFetcher()?.let { fetcher ->
            val asset = BaseData.getAsset(apiName, originSymbol)
            val metaData = fetcher.suiCoinMeta[originSymbol]

            if (asset != null) {
                return asset.image ?: ""
            } else if (metaData != null) {
                return metaData.assetImg()
            }
        }
        return ""
    }

    fun assetSymbol(denom: String): String? {
        suiFetcher()?.let { fetcher ->
            val asset = BaseData.getAsset(apiName, denom)
            val metaData = fetcher.suiCoinMeta[denom]

            if (asset != null) {
                return asset.symbol
            } else if (metaData != null) {
                return metaData["symbol"].asString
            }
        }
        return denom.suiCoinSymbol() ?: "UnKnown"
    }

    fun assetDecimal(denom: String): Int {
        suiFetcher()?.let { fetcher ->
            val asset = BaseData.getAsset(apiName, denom)
            val metaData = fetcher.suiCoinMeta[denom]

            if (asset != null) {
                return asset.decimals ?: 9
            } else if (metaData != null) {
                return metaData["decimals"]?.asInt ?: 9
            }
        }
        return 9
    }

    fun assetGeckoId(denom: String): String {
        BaseData.getAsset(apiName, denom)?.let { asset ->
            return asset.coinGeckoId ?: ""
        }
        return ""
    }
}

const val SUI_TYPE_COIN = "0x2::coin::Coin"
const val SUI_MAIN_DENOM = "0x2::sui::SUI"

const val SUI_MIN_STAKE = "1000000000"
const val SUI_FEE_SEND = "4000000"
const val SUI_FEE_STAKE = "50000000"
const val SUI_FEE_UNSTAKE = "50000000"
const val SUI_FEE_DEFAULT = "70000000"

const val SUI_API = "https://us-central1-splash-wallet-60bd6.cloudfunctions.net"