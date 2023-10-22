package wannabit.io.cosmostaion.data.model.res

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Param(
    @Json(name = "chain_id") val chainId: String?,
    @Json(name = "block_time") val blockTime: Double?,
    val params: Params?
)

@JsonClass(generateAdapter = true)
data class Params(
    @Json(name = "chainlist_params") val chainListParam: ChainListParam?
)

data class ChainListParam(
    val fee: Fee?,
    val isSimulable: Boolean,
    @Json(name = "simul_gas_multiply") val simulGas: String?,
    @Json(name = "fee_threshold") val feeThreshold: String?,
)

data class Fee(
    val base: String?,
    val rate: List<String>?
)