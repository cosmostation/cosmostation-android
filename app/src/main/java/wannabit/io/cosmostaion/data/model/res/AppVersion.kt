package wannabit.io.cosmostaion.data.model.res

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AppVersion(
    @Json(name = "app_name") val appName: String,
    @Json(name = "device_type") val deviceType: String,
    val version: Int,
    val enable: Boolean,
    val timestamp: String
)
