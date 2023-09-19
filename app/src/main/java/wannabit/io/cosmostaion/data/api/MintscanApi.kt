package wannabit.io.cosmostaion.data.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import wannabit.io.cosmostaion.data.model.AssetResponse
import wannabit.io.cosmostaion.data.model.CosmosHistory
import wannabit.io.cosmostaion.data.model.Price
import wannabit.io.cosmostaion.data.model.TokenResponse

interface MintscanApi {
    @GET("v2/utils/market/prices")
    suspend fun price(): Response<List<Price>>

    @GET("v3/assets")
    suspend fun asset(): Response<AssetResponse>

    @GET("v3/assets/{chain}/cw20")
    suspend fun cw20token(@Path("chain") chain:String): TokenResponse

    @GET("v1/{chain}/account/{address}/txs")
    suspend fun cosmosHistory(@Path("chain") chain:String, @Path("address") address: String?, @Query("limit") limit: String, @Query("from") id: Int): Response<List<CosmosHistory>>
}