package wannabit.io.cosmostaion.data.api

import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import wannabit.io.cosmostaion.data.model.req.BroadcastReq
import wannabit.io.cosmostaion.data.model.res.LegacyRes
import wannabit.io.cosmostaion.data.model.res.OktAccountResponse
import wannabit.io.cosmostaion.data.model.res.OktDepositedResponse
import wannabit.io.cosmostaion.data.model.res.OktTokenResponse
import wannabit.io.cosmostaion.data.model.res.OktValidatorResponse
import wannabit.io.cosmostaion.data.model.res.OktWithdrawResponse

interface LcdApi {
    @GET("auth/accounts/{address}")
    suspend fun oktAccountInfo(@Path("address") address: String?): JsonObject

    @GET("staking/delegators/{address}")
    suspend fun oktDepositInfo(@Path("address") address: String?): JsonObject

    @GET("staking/delegators/{address}/unbonding_delegations")
    suspend fun oktWithdrawInfo(@Path("address") address: String?): JsonObject

    @GET("tokens")
    suspend fun oktTokens(): JsonObject

    @GET("staking/validators?status=all")
    suspend fun oktValidators(): MutableList<JsonObject>

    @POST("txs")
    suspend fun broadTx(@Body data: BroadcastReq?): LegacyRes
}