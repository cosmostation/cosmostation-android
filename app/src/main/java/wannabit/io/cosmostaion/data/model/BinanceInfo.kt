package wannabit.io.cosmostaion.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NodeInfoResponse(@Json(name = "node_info") val nodeInfo: NodeInfo?)

data class NodeInfo(
    val network: String,
    val version: String,
    val moniker: String
)


data class AccountResponse(
    val address: String,
    @Json(name = "account_number") val accountNumber: Int,
    val sequence: Int,
    val balances: List<Balance>?
)

data class Balance(
    val free: String,
    val frozen: String,
    val locked: String,
    val symbol: String
)

data class BnbToken(
    val name: String,
    val symbol: String,
    @Json(name = "original_symbol") val originalSymbol: String
)