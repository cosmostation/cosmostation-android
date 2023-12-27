package wannabit.io.cosmostaion.data.model.res

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Param(
    @Json(name = "chain_id") val chainId: String?,
    @Json(name = "block_time") val blockTime: Double?,
    val params: Params?
)

data class Params(
    @Json(name = "minting_inflation") val mintInflation: MintInflation?,
    @Json(name = "staking_params") val stakingParams: StakingParams?,
    @Json(name = "chainlist_params") val chainlistParams: ChainListParams?,
    val apr: String?,
) {
    data class MintInflation(val inflation: String?)
    data class StakingParams(val params: StakingParam) {
        data class StakingParam(
            @Json(name = "unbonding_time") val unbondingTime: String?
        )
    }
    data class ChainListParams(
        val fee: Fee,
        @Json(name = "grpc_endpoint") val grpcEndpoint: MutableList<GrpcEndpoint>?,
        val about: About,
        val description: Description,
        val daos: MutableList<Dao>?,
        val vaults: MutableList<Vault>?
    ) {
        data class Fee(
            val base: String?,
            val rate: List<String>?,
            val isSimulable: Boolean,
            @Json(name = "simul_gas_multiply") val simulGasMultiply: Double?,
            @Json(name = "fee_threshold") val feeThreshold: String?
        )

        data class GrpcEndpoint(
            val provider: String?,
            val url: String
        )

        data class About(
            val website: String?,
            val blog: String?,
            val github: String?,
            val twitter: String?,
            val coingecko: String?
        )

        data class Description(
            val ko: String?,
            val en: String?,
            val ja: String
        )
    }
}


