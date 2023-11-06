package wannabit.io.cosmostaion.data.model.res

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
) {

    fun beforeChain(apiName: String): String? {
        path?.let {
            val chainPath = it.split(">")
            chainPath.lastIndexOf(apiName).apply {
                if (this > 0) {
                    return chainPath[this - 1]
                }
            }
        }
        return null
    }
}

@JsonClass(generateAdapter = true)
data class CounterParty(
    val channel: String?,
    val port: String?,
    val denom: String?
)

data class AssetPath(
    var channel: String?,
    var port: String?
)
