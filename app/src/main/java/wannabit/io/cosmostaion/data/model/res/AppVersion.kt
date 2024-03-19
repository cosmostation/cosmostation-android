package wannabit.io.cosmostaion.data.model.res

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AppVersion(
    val app_name: String,
    val device_type: String,
    val version: Int,
    val enable: Boolean,
    val timestamp: String
)
