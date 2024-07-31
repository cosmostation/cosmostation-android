package wannabit.io.cosmostaion.data.api

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query
import wannabit.io.cosmostaion.BuildConfig
import wannabit.io.cosmostaion.data.model.req.SkipMsgReq
import wannabit.io.cosmostaion.data.model.req.SkipRouteReq
import wannabit.io.cosmostaion.data.model.res.SkipChainResponse
import wannabit.io.cosmostaion.data.model.res.SkipMsgResponse
import wannabit.io.cosmostaion.data.model.res.SkipRouteResponse

interface SkipApi {

    @Headers(
        "Content-Type: application/json",
        "Authorization: ${BuildConfig.SKIP_API_KEY}"
    )
    @GET("v2/info/chains")
    suspend fun skipChains(): SkipChainResponse

    @Headers(
        "Content-Type: application/json",
        "Authorization: ${BuildConfig.SKIP_API_KEY}"
    )
    @GET("v2/fungible/assets")
    suspend fun skipAssets(@Query("chain_ids") chain_ids: String?): JsonObject

    @Headers(
        "Content-Type: application/json",
        "Authorization: ${BuildConfig.SKIP_API_KEY}"
    )
    @POST("v2/fungible/route")
    suspend fun skipRoute(@Body data: SkipRouteReq): Response<SkipRouteResponse>

    @POST("v2/fungible/msgs")
    suspend fun skipMsg(@Body data: SkipMsgReq): Response<SkipMsgResponse>
}