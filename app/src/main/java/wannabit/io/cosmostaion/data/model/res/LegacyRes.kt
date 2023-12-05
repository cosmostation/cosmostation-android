package wannabit.io.cosmostaion.data.model.res

import com.squareup.moshi.Json

data class LegacyRes(
    val txhash: String?,
    val code: Int?,
    @Json(name = "raw_log") val rawLog: String?,
    val hash: String?,
)
