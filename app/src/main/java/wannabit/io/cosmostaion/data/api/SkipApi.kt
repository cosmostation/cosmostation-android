package wannabit.io.cosmostaion.data.api

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import wannabit.io.cosmostaion.data.model.req.SkipMsgReq
import wannabit.io.cosmostaion.data.model.req.SkipRouteReq
import wannabit.io.cosmostaion.data.model.res.SkipChainResponse
import wannabit.io.cosmostaion.data.model.res.SkipMsgResponse
import wannabit.io.cosmostaion.data.model.res.SkipRouteResponse

interface SkipApi {

    @GET("v1/info/chains")
    suspend fun skipChains(): SkipChainResponse

    @GET("v1/fungible/assets")
    suspend fun skipAssets(): JsonObject

    @POST("v1/fungible/route")
    suspend fun skipRoute(@Body data: SkipRouteReq): Response<SkipRouteResponse>

    @POST("v1/fungible/msgs")
    suspend fun skipMsg(@Body data: SkipMsgReq): Response<SkipMsgResponse>
}