package wannabit.io.cosmostaion.data.api

import retrofit2.Response
import retrofit2.http.GET
import wannabit.io.cosmostaion.data.model.AssetResponse
import wannabit.io.cosmostaion.data.model.Price

interface MintscanApi {
    @GET("v2/utils/market/prices")
    suspend fun price(): Response<List<Price>>

    @GET("v3/assets")
    suspend fun asset(): Response<AssetResponse>
}