package wannabit.io.cosmostaion.chain.majorClass

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
open class ChainBitCoin84 : BaseChain(), Parcelable {

    @IgnoredOnParcel
    var btcFetcher: BtcFetcher? = null

    open var pubKeyHash: Byte = 0x00
    open var scriptHash: Byte = 0x05
    open var bech32PrefixPattern: String = "bc"

    override var name: String = "BitCoin"
    override var tag: String = "bitcoin84"
    override var logo: Int = R.drawable.chain_bitcoin
    override var apiName: String = "bitcoin"

    override var accountKeyType = AccountKeyType(PubKeyType.BTC_NATIVE_SEGWIT, "m/84'/0'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(84, true), ChildNumber(0, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO
    )

    override var coinSymbol: String = "BTC"
    override var coinGeckoId: String = "bitcoin"
    override var coinLogo: Int = R.drawable.token_btc

    override var mainUrl: String = "https://rpc-office.cosmostation.io/bitcoin-mainnet"

    override fun setInfoWithPrivateKey(privateKey: ByteArray?) {
        this.privateKey = privateKey
        publicKey = BaseKey.getPubKeyFromPKey(privateKey, accountKeyType.pubkeyType)
        mainAddress = BaseKey.getAddressFromPubKey(publicKey, accountKeyType.pubkeyType, bech32PrefixPattern, pubKeyHash, scriptHash)
    }

    fun btcFetcher(): BtcFetcher? {
        if (btcFetcher != null) return btcFetcher
        btcFetcher = BtcFetcher(this)
        return btcFetcher
    }
}