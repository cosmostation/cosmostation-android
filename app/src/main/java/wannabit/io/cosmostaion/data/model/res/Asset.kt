package wannabit.io.cosmostaion.data.model.res

import android.graphics.Color
import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
data class AssetResponse(val assets: List<Asset>?)

@Parcelize
@JsonClass(generateAdapter = true)
data class Asset(
    val chain: String?,
    val type: String?,
    val denom: String?,
    val name: String?,
    val symbol: String?,
    val description: String?,
    val decimals: Int?,
    val image: String?,
    val coinGeckoId: String?,
    val color: String?,
    val ibc_info: IbcInfo?,
) : Parcelable {

    fun beforeChain(apiName: String): String? {
        ibc_info?.path?.let {
            val chainPath = it.split(">")
            chainPath.lastIndexOf(apiName).apply {
                if (this > 0) {
                    return chainPath[this - 1]
                }
            }
        }
        return null
    }

    fun justBeforeChain(): String? {
        ibc_info?.path?.let {
            val chainPath = it.split(">")
            if (chainPath.count() > 1) {
                return chainPath[chainPath.count() - 2]
            }
        }
        return null
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
data class IbcInfo(
    val path: String?,
    val client: Client?,
    val counterparty: CounterParty?,
    val enable: Boolean? = true
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Client(
    val channel: String?, val port: String?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class CounterParty(
    val channel: String?, val port: String?, val chain: String?, val denom: String?
) : Parcelable

data class AssetPath(
    var channel: String?, var port: String?
) {
    fun ibcContract(): String {
        port?.let {
            return it.replace("wasm.".toRegex(), "")
        }
        return ""
    }
}
