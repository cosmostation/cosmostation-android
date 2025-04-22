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

    fun assetColor(): Int {
        return Color.parseColor("#ffffff")
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
    val channel: String?, val port: String?, val chain: String?, private val denom: String?
) : Parcelable {
    val dpDenom: String?
        get() = denom?.let {
            if (it.startsWith("cw20:")) it.removePrefix("cw20:") else it
        }
}

data class AssetPath(
    var direction: DirectionIBC? = DirectionIBC.UNKNOWN,
    var ibcInfo: IbcInfo?
) {

    fun getChannel(): String? {
        return when (direction) {
            DirectionIBC.BACKWARD -> ibcInfo?.client?.channel
            DirectionIBC.FORWARD -> ibcInfo?.counterparty?.channel
            else -> null
        }
    }

    fun getPort(): String? {
        return when (direction) {
            DirectionIBC.BACKWARD -> ibcInfo?.client?.port?.replace("wasm.", "")
            DirectionIBC.FORWARD -> ibcInfo?.counterparty?.port?.replace("wasm.", "")
            else -> null
        }
    }
}

enum class DirectionIBC { UNKNOWN, FORWARD, BACKWARD }
