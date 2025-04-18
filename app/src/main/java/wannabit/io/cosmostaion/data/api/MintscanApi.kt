package wannabit.io.cosmostaion.data.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import wannabit.io.cosmostaion.data.model.req.MoonPayReq
import wannabit.io.cosmostaion.data.model.req.PushSyncReq
import wannabit.io.cosmostaion.data.model.res.AppVersion
import wannabit.io.cosmostaion.data.model.res.AssetResponse
import wannabit.io.cosmostaion.data.model.res.CosmosHistory
import wannabit.io.cosmostaion.data.model.res.CosmosProposal
import wannabit.io.cosmostaion.data.model.res.Cw20TokenResponse
import wannabit.io.cosmostaion.data.model.res.Cw721Response
import wannabit.io.cosmostaion.data.model.res.Erc20TokenResponse
import wannabit.io.cosmostaion.data.model.res.Grc20TokenResponse
import wannabit.io.cosmostaion.data.model.res.MoonPay
import wannabit.io.cosmostaion.data.model.res.NoticeResponse
import wannabit.io.cosmostaion.data.model.res.Price
import wannabit.io.cosmostaion.data.model.res.ResDaoVoteStatus
import wannabit.io.cosmostaion.data.model.res.VoteStatus

interface MintscanApi {

    @GET("v10/app/version/android")
    suspend fun version(): Response<AppVersion>

    @POST("v10/app/keys/moonpay")
    suspend fun moonPay(@Body data: MoonPayReq): Response<MoonPay>

    @POST("v10/notification")
    fun syncPush(@Body status: PushSyncReq?): Call<Void>

    @GET("v10/utils/market/prices")
    suspend fun price(@Query("currency") currency: String): List<Price>

    @GET("v11/assets")
    suspend fun asset(): AssetResponse

    @GET("v11/utils/params")
    suspend fun param(): JsonObject

    @GET("v11/assets/cw20")
    suspend fun cw20token(): Cw20TokenResponse

    @GET("v11/assets/erc20")
    suspend fun erc20token(): Erc20TokenResponse

    @GET("v11/assets/grc20")
    suspend fun grc20token(): Grc20TokenResponse

    @GET("v11/assets/cw721")
    suspend fun cw721(): Cw721Response

    @GET("v10/{chain}/contracts/{contractAddress}/nft-url/{tokenId}")
    suspend fun cw721Detail(
        @Path("chain") chain: String,
        @Path("contractAddress") contractAddress: String,
        @Path("tokenId") tokenId: String
    ): JsonObject

    @GET("v10/{chain}/account/{address}/txs")
    suspend fun cosmosHistory(
        @Path("chain") chain: String,
        @Path("address") address: String?,
        @Query("limit") limit: String,
        @Query("search_after") searchAfter: String
    ): Response<List<CosmosHistory>>

    @GET("v11/{chain}/proposals")
    suspend fun cosmosProposal(@Path("chain") chain: String): Response<MutableList<CosmosProposal>>

    @GET("v10/{chain}/account/{account}/votes")
    suspend fun voteStatus(
        @Path("chain") chain: String?, @Path("account") account: String?
    ): Response<VoteStatus>

    @GET("v10/{chain}/dao/address/{address}/votes")
    suspend fun daoVoteStatus(
        @Path("chain") chain: String?, @Path("address") address: String?
    ): Response<MutableList<ResDaoVoteStatus>>

    @GET("{chain}/eco_list.json")
    suspend fun ecoSystemInfo(@Path("chain") chain: String): MutableList<JsonObject>

    @GET("eco_list.json")
    suspend fun ecoSystemTestInfo(): MutableList<JsonObject>

    @GET("v10/notice?include_content=true&flatform=MOBILE")
    suspend fun notice(): NoticeResponse

    @GET("v10/{chain}/proxy/okx/account/{address}/txs")
    suspend fun evmHistory(
        @Path("chain") chain: String?,
        @Path("address") address: String?,
        @Query("limit") limit: String?,
        @Query("search_after") searchAfter: String
    ): Response<JsonObject?>

    @GET("v11/{chain}/btc/stakers")
    suspend fun bitStakedStatus(
        @Path("chain") chain: String?,
        @Query("staker_addr") address: String,
        @Query("limit") limit: String?,
        @Query("search_after") searchAfter: String? = ""
    ): JsonObject?
}