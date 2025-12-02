package wannabit.io.cosmostaion.chain.majorClass

import android.content.Context
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.fetcher.AptosFetcher
import wannabit.io.cosmostaion.common.BaseKey


@Parcelize
class ChainAptos : BaseChain(), Parcelable {

    override var name: String = "Aptos"
    override var tag: String = "aptosMainnet"
    override var apiName: String = "aptos"

    override var accountKeyType = AccountKeyType(PubKeyType.APTOS_ED25519, "m/44'/637'/0'/0/X")

    override var stakeDenom: String = APTOS_MAIN_DENOM
    override var coinSymbol: String = "APT"

    override var apiUrl: String = "https://api.mainnet.aptoslabs.com/v1/"
    override var mainUrl: String = "https://api.mainnet.aptoslabs.com/v1/graphql"

    override suspend fun setInfoWithPrivateKey(context: Context, privateKey: ByteArray?) {
        this.privateKey = privateKey
        publicKey = BaseKey.getPubKeyFromPKey(privateKey, accountKeyType.pubkeyType)
        mainAddress = BaseKey.getAddressFromPubKey(context, publicKey, accountKeyType.pubkeyType)
    }

    fun aptosFetcher(): AptosFetcher? {
        if (aptosFetcher != null) return aptosFetcher
        aptosFetcher = AptosFetcher(this)
        return aptosFetcher
    }
}

const val APTOS_MAIN_DENOM = "0x1::aptos_coin::AptosCoin"

const val APTOS_DEFAULT_FEE = "5000"
