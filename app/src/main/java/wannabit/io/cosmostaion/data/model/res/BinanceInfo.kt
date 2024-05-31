package wannabit.io.cosmostaion.data.model.res

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