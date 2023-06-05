package wannabit.io.cosmostaion.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import wannabit.io.cosmostaion.BuildConfig
import wannabit.io.cosmostaion.common.CosmostationConstants
import java.util.concurrent.TimeUnit

interface CosmostationService {
    companion object {
        fun create(): CosmostationService {
            val builder = Retrofit.Builder().baseUrl(CosmostationConstants.WALLET_API_URL).addConverterFactory(GsonConverterFactory.create())

            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client = OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(10, TimeUnit.SECONDS).build()
                builder.client(client)
            }

            return builder.build().create(CosmostationService::class.java)
        }
    }

    @GET("v1/app/version/android")
    fun getVersion(): Call<AppVersion>

    @PUT("v1/push/alarm/status")
    fun putAlarmStatus(@Body status: PushStatusRequest): Call<Void>

    @POST("v1/push/token/address")
    fun syncPushAddress(@Body status: PushSyncRequest): Call<Void>

    @GET("v1/push/alarm/status/{fcmToken}")
    fun getPushStatus(@Path("fcmToken") fcmToken: String): Call<PushStatusResponse>
}

data class AppVersion(val app_name: String, val device_type: String, val enable: Boolean, val version: Int, val timestamp: String)
data class PushSyncRequest(val fcm_token: String, val accounts: List<PushAccount>)
data class PushAccount(val address: String, val chain: String)
data class PushStatusResponse(val subscribe: Boolean, val timestamp: String)
data class PushStatusRequest(val subscribe: Boolean, val fcm_token: String)
