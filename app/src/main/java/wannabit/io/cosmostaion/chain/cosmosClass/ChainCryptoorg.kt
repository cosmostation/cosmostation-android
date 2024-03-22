package wannabit.io.cosmostaion.chain.cosmosClass

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import kotlinx.parcelize.Parcelize
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.PubKeyType

@Parcelize
class ChainCryptoorg : CosmosLine(), Parcelable {

    override var name: String = "Cronos POS"
    override var tag: String = "crypto-org394"
    override var logo: Int = R.drawable.chain_cryptoorg
    override var swipeLogo: Int = R.drawable.chain_swipe_cryptoorg
    override var apiName: String = "crypto-org"
    override var stakeDenom: String? = "basecro"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/394'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true),
        ChildNumber(394, true),
        ChildNumber.ZERO_HARDENED,
        ChildNumber.ZERO
    )
    override var accountPrefix: String? = "cro"

    override var grpcHost: String = "grpc-crypto-org.cosmostation.io"
}