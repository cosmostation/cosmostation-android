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

data class OktValidatorResponse(
    @Json(name = "operator_address") val operatorAddress: String?,
    @Json(name = "consensus_pubkey") val consensusPubkey: String?,
    val jailed: Boolean?,
    val status: Int?,
    val tokens: String?,
    @Json(name = "delegator_shares") val delegatorShares: String,
    val description: Description?,
    @Json(name = "unbonding_height") val unbondingHeight: String?,
    @Json(name = "unbonding_time") val unbondingTime: String?,
    val commission: Commission?,
    @Json(name = "min_self_delegation") val minSelfDelegation: String?
) {

    data class Description(val moniker: String?)

    data class Commission(
        @Json(name = "commission_rates") val commissionRates: CommissionRates
    ) {
        data class CommissionRates(
            val rate: String?,
            @Json(name = "max_rate") val maxRate: String?,
            @Json(name = "max_change_rate") val maxChangeRate: String?,
        )
    }
}