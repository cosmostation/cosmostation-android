package wannabit.io.cosmostaion.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import wannabit.io.cosmostaion.data.model.req.MoonPayReq
import wannabit.io.cosmostaion.data.model.res.AppVersion
import wannabit.io.cosmostaion.data.model.res.MoonPay

interface WalletApi {

    @GET("v1/app/version/android")
    suspend fun version(): Response<AppVersion>

    @POST("v1/sign/moonpay")
    suspend fun moonPay(@Body data: MoonPayReq): Response<MoonPay>
}