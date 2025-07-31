package wannabit.io.cosmostaion.chain.majorClass

import android.content.Context
import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.fetcher.SolanaFetcher
import wannabit.io.cosmostaion.common.BaseKey

@Parcelize
class ChainSolana : BaseChain(), Parcelable {

    override var name: String = "Solana"
    override var tag: String = "solana501"
    override var apiName: String = "solana"

    override var accountKeyType = AccountKeyType(PubKeyType.SOLANA_ED25519, "m/44'/501'/0'/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true), ChildNumber(501, true), ChildNumber.ZERO_HARDENED
    )

    override var coinSymbol: String = "SOL"

    override var mainUrl: String = "https://api.mainnet-beta.solana.com"

    override suspend fun setInfoWithPrivateKey(context: Context, privateKey: ByteArray?) {
        this.privateKey = privateKey
        publicKey = BaseKey.getPubKeyFromPKey(privateKey, accountKeyType.pubkeyType)
        mainAddress = BaseKey.getAddressFromPubKey(context, publicKey, accountKeyType.pubkeyType)
    }

    fun solanaFetcher(): SolanaFetcher? {
        if (solanaFetcher == null) {
            solanaFetcher = SolanaFetcher(this)
        }
        return solanaFetcher
    }
}

const val SOLANA_PROGRAM_ID = "TokenkegQfeZyiNwAJbNbGKPFXCWuBvf9Ss623VQ5DA"
