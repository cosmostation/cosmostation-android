package wannabit.io.cosmostaion.data.model.res

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Token(
    val chain: String,
    val type: String,
    val contract: String,
    val name: String,
    val symbol: String,
    val description: String,
    val decimals: Int,
    val image: String,
    val coinGeckoId: String,
    val wallet_preload: Boolean? = false
) : Parcelable {

    var amount: String? = null
        get() = field ?: "0"

    var fetched: Boolean = false
}
