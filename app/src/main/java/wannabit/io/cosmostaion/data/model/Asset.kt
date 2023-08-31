package wannabit.io.cosmostaion.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AssetResponse(val assets: List<Asset>?)

@JsonClass(generateAdapter = true)
data class Asset(
    val chain: String?,
    val denom: String?,
    val type: String?,
    @Json(name = "origin_chain") val originChain: String?,
    @Json(name = "origin_denom") val originDenom: String?,
    @Json(name = "origin_type") val originType: String?,
    val symbol: String?,
    val decimals: Int?,
    val description: String?,
    val image: String?,
    val coinGeckoId: String?,

    val enable: Boolean?,
    val path: String?,
    val channel: String?,
    val port: String?,
    @Json(name = "counter_party") val counterParty: CounterParty?,
)

@JsonClass(generateAdapter = true)
data class CounterParty(
    val channel: String?,
    val port: String?,
    val denom: String?
)
