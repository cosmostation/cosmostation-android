package wannabit.io.cosmostaion.data.model.res

import android.graphics.Color
import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import wannabit.io.cosmostaion.common.CosmostationConstants

@JsonClass(generateAdapter = true)
data class AssetResponse(val assets: List<Asset>?)

@Parcelize
@JsonClass(generateAdapter = true)
data class Asset(
    val chain: String?,
    val denom: String?,
    val type: String?,
    val origin_denom: String?,
    val symbol: String?,
    val decimals: Int?,
    val description: String?,
    val image: String?,
    val coinGeckoId: String?,
    val color: String?,
    val enable: Boolean?,
    val path: String?,
    val channel: String?,
    val port: String?,
    val counter_party: CounterParty?,
) : Parcelable {

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

    fun assetImg(): String {
        return CosmostationConstants.CHAIN_BASE_URL + image
    }

    fun assetColor(): Int {
        color?.let {
            return Color.parseColor(it)
        } ?: run {
            return Color.parseColor("#ffffff")
        }
    }
}

@Parcelize
@JsonClass(generateAdapter = true)
data class CounterParty(
    val channel: String?,
    val port: String?,
    val denom: String?
) : Parcelable

data class AssetPath(
    var channel: String?,
    var port: String?
) {
    fun ibcContract(): String {
        port?.let {
            return it.replace("wasm.".toRegex(), "")
        }
        return ""
    }
}
