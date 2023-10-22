package wannabit.io.cosmostaion.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import wannabit.io.cosmostaion.data.model.res.AccountResponse
import wannabit.io.cosmostaion.data.model.res.BnbHistoryResponse
import wannabit.io.cosmostaion.data.model.res.BnbToken
import wannabit.io.cosmostaion.data.model.res.NodeInfoResponse

interface LcdApi {
    @GET("api/v1/node-info")
    suspend fun nodeInfo(): NodeInfoResponse

    @GET("api/v1/account/{address}")
    suspend fun accountInfo(@Path("address") address: String?): AccountResponse

    @GET("api/v1/tokens")
    suspend fun beaconTokens(@Query("limit") limit: String): MutableList<BnbToken>

    @GET("api/v1/transactions")
    suspend fun bnbHistory(@Query("address") address: String?, @Query("startTime") startTime: String, @Query("endTime") endTime: String): Response<BnbHistoryResponse>
}