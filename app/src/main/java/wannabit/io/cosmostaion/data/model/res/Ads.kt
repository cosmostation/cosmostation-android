package wannabit.io.cosmostaion.data.model.res

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AdsResponse(val ads: List<Ads>?)

@JsonClass(generateAdapter = true)
data class Ads(
    val id: String,
    val priority: Int? = 1,
    val startAt: String? = "",
    val endAt: String? = "",
    val images: Image,
    val linkUrl: String? = "",
    val title: String? = "",
    val view_detail: String? = "",
    val color: String? = "#FFFFFF"
)

data class Image(
    val extension: String? = "",
    val mobile: String? = ""
)
