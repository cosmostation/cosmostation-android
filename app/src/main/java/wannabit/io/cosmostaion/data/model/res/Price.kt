package wannabit.io.cosmostaion.data.model.res

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Price(
    val denom: String?,
    val coinGeckoId: String?,
    val current_price: Double?,
    val daily_price_change_in_percent: Double?,
)
