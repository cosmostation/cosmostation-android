package wannabit.io.cosmostaion.chain.majorClass

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.fetcher.NamadaFetcher
import wannabit.io.cosmostaion.common.BaseKey

@Parcelize
class ChainNamada : BaseChain(), Parcelable {

    @IgnoredOnParcel
    var namadaFetcher: NamadaFetcher? = null

    override var name: String = "Namada"
    override var tag: String = "namada877"
    override var logo: Int = R.drawable.chain_namada
    override var apiName: String = "namada"

    override var accountKeyType = AccountKeyType(PubKeyType.NAMADA_ED25519, "m/44'/877'/0'/0'/X")
    override var accountPrefix: String = "tnam"
    override var coinSymbol: String = "NAM"
    override var coinGeckoId: String = ""
    override var coinLogo: Int = R.drawable.token_nam

    override var lcdUrl: String = "https://index-namada.5elementsnodes.com/"
    override var mainUrl: String = "https://rpc-namada.5elementsnodes.com"

    override fun setInfoWithPrivateKey(privateKey: ByteArray?) {
        this.privateKey = privateKey
        publicKey = BaseKey.getPubKeyFromPKey(privateKey, accountKeyType.pubkeyType)
        mainAddress =
            BaseKey.getAddressFromPubKey(publicKey, accountKeyType.pubkeyType, accountPrefix)
    }

    fun namadaFetcher(): NamadaFetcher? {
        if (namadaFetcher != null) return namadaFetcher
        namadaFetcher = NamadaFetcher(this)
        return namadaFetcher
    }
}

const val NAMADA_MAIN_DENOM = "tnam1q9gr66cvu4hrzm0sd5kmlnjje82gs3xlfg3v6nu7"