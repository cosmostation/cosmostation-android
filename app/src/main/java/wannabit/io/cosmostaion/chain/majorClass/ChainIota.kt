package wannabit.io.cosmostaion.chain.majorClass

import android.content.Context
import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.fetcher.IotaFetcher
import wannabit.io.cosmostaion.chain.fetcher.assetImg
import wannabit.io.cosmostaion.chain.fetcher.iotaCoinSymbol
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseKey

@Parcelize
class ChainIota : BaseChain(), Parcelable {

    @IgnoredOnParcel
    var iotaFetcher: IotaFetcher? = null

    override var name: String = "Iota"
    override var tag: String = "iotaMainnet"
    override var apiName: String = "iota"

    override var accountKeyType = AccountKeyType(PubKeyType.IOTA_ED25519, "m/44'/4218'/0'/0/X")

    override var stakeDenom: String = IOTA_MAIN_DENOM
    override var coinSymbol: String = "IOTA"

    override var mainUrl: String = "https://api.testnet.iota.cafe"

    override suspend fun setInfoWithPrivateKey(context: Context, privateKey: ByteArray?) {
        this.privateKey = privateKey
        publicKey = BaseKey.getPubKeyFromPKey(privateKey, accountKeyType.pubkeyType)
        mainAddress = BaseKey.getAddressFromPubKey(context, publicKey, accountKeyType.pubkeyType)
    }

    fun iotaFetcher(): IotaFetcher? {
        if (iotaFetcher != null) return iotaFetcher
        iotaFetcher = IotaFetcher(this)
        return iotaFetcher
    }

    override fun assetImg(originSymbol: String): String {
        iotaFetcher()?.let { fetcher ->
            val asset = BaseData.getAsset(apiName, originSymbol)
            val metaData = fetcher.iotaCoinMeta[originSymbol]

            if (asset != null) {
                return asset.image ?: ""
            } else if (metaData != null) {
                return metaData.assetImg()
            }
        }
        return ""
    }

    fun assetSymbol(denom: String): String? {
        iotaFetcher()?.let { fetcher ->
            val asset = BaseData.getAsset(apiName, denom)
            val metaData = fetcher.iotaCoinMeta[denom]

            if (asset != null) {
                return asset.symbol
            } else if (metaData != null) {
                return metaData["symbol"].asString
            }
        }
        return denom.iotaCoinSymbol() ?: "UnKnown"
    }

    fun assetDecimal(denom: String): Int {
        iotaFetcher()?.let { fetcher ->
            val asset = BaseData.getAsset(apiName, denom)
            val metaData = fetcher.iotaCoinMeta[denom]

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

const val IOTA_TYPE_COIN = "0x2::coin::Coin"
const val IOTA_MAIN_DENOM = "0x2::iota::IOTA"

const val IOTA_MIN_STAKE = "1000000000"
const val IOTA_FEE_SEND = "4000000"
const val IOTA_FEE_STAKE = "50000000"
const val IOTA_FEE_UNSTAKE = "50000000"
const val IOTA_FEE_DEFAULT = "70000000"