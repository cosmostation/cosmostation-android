package wannabit.io.cosmostaion.data.model.res

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ChainResponse(val chains: List<Chain>?)

@JsonClass(generateAdapter = true)
data class Chain(
    val chain: String?,
    val chain_id: String,
    val prefix: String?
)
