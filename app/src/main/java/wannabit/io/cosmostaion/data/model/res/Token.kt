package wannabit.io.cosmostaion.data.model.res

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import wannabit.io.cosmostaion.common.CosmostationConstants

@Parcelize
data class Token(
    val chainId: Int,
    val chainName: String,
    val address: String,
    val symbol: String,
    val decimals: Int,
    val description: String,
    val image: String,
    val default: Boolean,
    val coinGeckoId: String
) : Parcelable {

    var amount: String? = null
        get() = field ?: "0"

    fun assetImg(): String {
        return CosmostationConstants.CHAIN_BASE_URL + image
    }
}
