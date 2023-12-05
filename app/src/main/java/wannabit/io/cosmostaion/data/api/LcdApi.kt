package wannabit.io.cosmostaion.data.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import wannabit.io.cosmostaion.data.model.req.BroadcastReq
import wannabit.io.cosmostaion.data.model.res.AccountResponse
import wannabit.io.cosmostaion.data.model.res.BnbHistoryResponse
import wannabit.io.cosmostaion.data.model.res.BnbToken
import wannabit.io.cosmostaion.data.model.res.LegacyRes
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.NodeInfoResponse
import wannabit.io.cosmostaion.data.model.res.OktAccountResponse
import wannabit.io.cosmostaion.data.model.res.OktDepositedResponse
import wannabit.io.cosmostaion.data.model.res.OktTokenResponse
import wannabit.io.cosmostaion.data.model.res.OktWithdrawResponse
import wannabit.io.cosmostaion.data.model.res.SwapIdResponse

interface LcdApi {
    @GET("api/v1/account/{address}")
    suspend fun accountInfo(@Path("address") address: String?): AccountResponse

    @GET("api/v1/tokens")
    suspend fun beaconTokens(@Query("limit") limit: String): MutableList<BnbToken>

    @GET("api/v1/transactions")
    suspend fun bnbHistory(
        @Query("address") address: String?,
        @Query("startTime") startTime: String,
        @Query("endTime") endTime: String
    ): Response<BnbHistoryResponse>

    @GET("api/v1/atomic-swaps/{swapId}")
    suspend fun swapById(@Path("swapId") swapId: String?): SwapIdResponse

    @GET("auth/accounts/{address}")
    suspend fun oktAccountInfo(@Path("address") address: String?): OktAccountResponse

    @GET("staking/delegators/{address}")
    suspend fun oktDepositInfo(@Path("address") address: String?): OktDepositedResponse

    @GET("staking/delegators/{address}/unbonding_delegations")
    suspend fun oktWithdrawInfo(@Path("address") address: String?): OktWithdrawResponse

    @GET("tokens")
    suspend fun oktTokens(): OktTokenResponse

    @POST("txs")
    suspend fun broadTx(@Body data: BroadcastReq?): LegacyRes
}