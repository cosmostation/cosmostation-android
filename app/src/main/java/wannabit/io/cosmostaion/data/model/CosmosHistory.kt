package wannabit.io.cosmostaion.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CosmosHistory(
    val header: HistoryHeader,
    val data: HistoryData
)

@JsonClass(generateAdapter = true)
data class HistoryHeader(
    val id: Int,
    @Json(name = "chain_id") val chainId: String?,
    @Json(name = "block_id") val blockId: Int?,
    val timestamp: String?
)

@JsonClass(generateAdapter = true)
data class HistoryData(
    val height: String?,
    val txhash: String?,
    val code: Int?,
    val tx: Tx?,
    val timestamp: String?,
    var logs: MutableList<Any>
)

@JsonClass(generateAdapter = true)
data class Tx(
    @Json(name = "@type") val type: String?,
    val body: Body?
)

@JsonClass(generateAdapter = true)
data class Body(
    val messages: MutableList<Any>,
    val msg: MutableList<Any>,
    val memo: String?
)
