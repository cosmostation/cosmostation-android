package wannabit.io.cosmostaion.data.model.res

import com.squareup.moshi.Json

data class OktAccountResponse(
    val type: String?,
    val value: Value?
)

data class Value(
    val address: String?,
    @Json(name = "eth_address") val ethAddress: String?,
    val coins: MutableList<OktCoin>?,
    @Json(name = "account_number") val accountNumber: String?,
    val sequence: String?,
    @Json(name = "code_hash") val codeHash: String?,
)

data class OktCoin(val denom: String, val amount: String)

data class OktDepositedResponse(
    @Json(name = "validator_address") val validatorAddress: MutableList<String>?,
    val tokens: String?
)

data class OktWithdrawResponse(
    val quantity: String?,
    @Json(name = "completion_time") val completionTime: String?
)

data class OktTokenResponse(
    val data: MutableList<OktToken>
)

data class OktToken(
    val description: String?,
    val symbol: String,
    @Json(name = "original_symbol") val originalSymbol: String
)

data class OktHistoryResponse(
    val data: MutableList<OktHistory>
)

data class OktHistory(
    val transactionLists: MutableList<TransactionList>
)

data class TransactionList(
    val txId: String?,
    val height: String?,
    val transactionTime: String,
    val state: String?
)