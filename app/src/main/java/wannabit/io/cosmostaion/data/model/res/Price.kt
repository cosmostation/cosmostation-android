package wannabit.io.cosmostaion.data.model.res

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Price(
    val denom: String?,
    val coinGeckoId: String?,
    @Json(name = "current_price") val currentPrice: Double?,
    @Json(name = "daily_price_change_in_percent") val dailyPercent: Double?,
)
