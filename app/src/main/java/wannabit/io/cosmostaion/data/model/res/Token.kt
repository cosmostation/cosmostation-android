package wannabit.io.cosmostaion.data.model.res

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
data class Cw20TokenResponse(val assets: List<Token>?)

@JsonClass(generateAdapter = true)
data class Erc20TokenResponse(val assets: List<Token>?)

@JsonClass(generateAdapter = true)
data class Grc20TokenResponse(val assets: List<Token>?)

@JsonClass(generateAdapter = true)
data class Cw721Response(val assets: List<Cw721>?)

@Parcelize
data class Token(
    val chainName: String,
    val chainId: Long? = 0,
    val name: String,
    val address: String,
    val symbol: String,
    val decimals: Int,
    val description: String,
    val image: String,
    val coinGeckoId: String?,
    val wallet_preload: Boolean? = false
) : Parcelable {

    var amount: String? = "0"
        get() = field ?: "0"

    var fetched: Boolean = false

    var type: String? = ""
        get() = field ?: ""
}

@Parcelize
data class Cw721(
    val chain: String,
    val id: Long,
    val name: String,
    val contractAddress: String
): Parcelable
