package wannabit.io.cosmostaion.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import wannabit.io.cosmostaion.data.model.req.MoonPayReq
import wannabit.io.cosmostaion.data.model.res.AppVersion
import wannabit.io.cosmostaion.data.model.res.MoonPay
import wannabit.io.cosmostaion.network.model.PushStatusRequest
import wannabit.io.cosmostaion.network.model.PushStatusResponse
import wannabit.io.cosmostaion.network.model.PushSyncRequest

interface WalletApi {

    @GET("v1/app/version/android")
    suspend fun version(): Response<AppVersion>

    @POST("v1/sign/moonpay")
    suspend fun moonPay(@Body data: MoonPayReq): Response<MoonPay>

    @PUT("v1/push/alarm/status")
    fun putAlarmStatus(@Body status: PushStatusRequest): Void

    @POST("v1/push/token/address")
    fun syncPushAddress(@Body status: PushSyncRequest): Void

    @GET("v1/push/alarm/status/{fcmToken}")
    fun getPushStatus(@Path("fcmToken") fcmToken: String): PushStatusResponse
}