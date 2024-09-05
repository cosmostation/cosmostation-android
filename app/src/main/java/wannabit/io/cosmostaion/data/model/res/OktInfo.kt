package wannabit.io.cosmostaion.data.model.res

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OktToken(
    val description: String?,
    val symbol: String,
    val original_symbol: String
) : Parcelable