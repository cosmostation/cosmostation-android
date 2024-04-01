package wannabit.io.cosmostaion.data.api

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import wannabit.io.cosmostaion.data.model.res.AssetResponse
import wannabit.io.cosmostaion.data.model.res.CosmosHistory
import wannabit.io.cosmostaion.data.model.res.CosmosProposal
import wannabit.io.cosmostaion.data.model.res.OktHistoryResponse
import wannabit.io.cosmostaion.data.model.res.Price
import wannabit.io.cosmostaion.data.model.res.ResDaoVoteStatus
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.data.model.res.VoteStatus

interface MintscanApi {
    @GET("v10/utils/market/prices")
    suspend fun price(@Query("currency") currency: String): List<Price>

    @GET("v10/assets")
    suspend fun asset(): AssetResponse

    @GET("v10/utils/params")
    suspend fun param(): JsonObject

    @GET("v10/assets/{chain}/cw20/info")
    suspend fun cw20token(@Path("chain") chain: String): MutableList<Token>

    @GET("v10/assets/{chain}/erc20/info")
    suspend fun erc20token(@Path("chain") chain: String): MutableList<Token>

    @GET("v10/{chain}/account/{address}/txs")
    suspend fun cosmosHistory(
        @Path("chain") chain: String, @Path("address") address: String?, @Query("limit") limit: String, @Query("search_after") searchAfter: String
    ): Response<List<CosmosHistory>>

    @GET("v10/{chain}/proposals")
    suspend fun cosmosProposal(@Path("chain") chain: String): Response<MutableList<CosmosProposal>>

    @GET("v10/{chain}/account/{account}/votes")
    suspend fun voteStatus(
        @Path("chain") chain: String?, @Path("account") account: String?
    ): Response<VoteStatus>

    @GET("v10/{chain}/dao/address/{address}/votes")
    suspend fun daoVoteStatus(
        @Path("chain") chain: String?, @Path("address") address: String?
    ): Response<MutableList<ResDaoVoteStatus>>

    @GET("v1/utils/proxy/okc-transaction-list")
    suspend fun oktHistory(
        @Query("device") device: String?, @Query("address") address: String?, @Query("limit") limit: String?
    ): Response<OktHistoryResponse>
}