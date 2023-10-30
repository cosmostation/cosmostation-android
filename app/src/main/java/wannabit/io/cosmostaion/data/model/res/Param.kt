package wannabit.io.cosmostaion.data.model.res

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Param(
    @Json(name = "chain_id") val chainId: String?,
    @Json(name = "block_time") val blockTime: Double?,
    @Json(name = "gas_price") val gasPrice: GasPrice?,
)

@JsonClass(generateAdapter = true)
data class GasPrice(
    val chain: String?,
    val base: String?,
    val rate: List<String>?
)