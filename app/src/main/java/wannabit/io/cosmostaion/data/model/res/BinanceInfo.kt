package wannabit.io.cosmostaion.data.model.res

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class AccountResponse(
    val address: String,
    val account_number: Int,
    val sequence: Int,
    val balances: List<Balance>?
)

data class Balance(
    val free: String,
    val frozen: String,
    val locked: String,
    val symbol: String
)

@Parcelize
data class BnbToken(
    val name: String,
    val symbol: String,
    val original_symbol: String
) : Parcelable

data class SwapIdResponse(
    val swapId: String
)