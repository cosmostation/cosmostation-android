package wannabit.io.cosmostaion.chain.majorClass

import android.content.Context
import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.fetcher.BtcFetcher
import wannabit.io.cosmostaion.common.BaseKey

@Parcelize
open class ChainBitCoin86 : BaseChain(), Parcelable {

    @IgnoredOnParcel
    var btcFetcher: BtcFetcher? = null

    override var name: String = "Bitcoin"
    override var tag: String = "bitcoin86"
    override var logo: Int = R.drawable.chain_bitcoin
    override var apiName: String = "bitcoin"

    override var accountKeyType = AccountKeyType(PubKeyType.BTC_TAPROOT, "m/86'/0'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(86, true), ChildNumber(0, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var coinSymbol: String = "BTC"
    override var coinLogo: Int = R.drawable.token_btc

    override var mainUrl: String = "https://rpc-office.cosmostation.io/bitcoin-mainnet"

    override suspend fun setInfoWithPrivateKey(context: Context, privateKey: ByteArray?) {
        this.privateKey = privateKey
        publicKey = BaseKey.getPubKeyFromPKey(privateKey, accountKeyType.pubkeyType)
        mainAddress = BaseKey.getAddressFromPubKey(context, publicKey, accountKeyType.pubkeyType, network = "mainnet")
    }

    fun btcFetcher(): BtcFetcher? {
        if (btcFetcher != null) return btcFetcher
        btcFetcher = BtcFetcher(this)
        return btcFetcher
    }
}