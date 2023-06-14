package wannabit.io.cosmostaion.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import wannabit.io.cosmostaion.BuildConfig
import wannabit.io.cosmostaion.common.CosmostationConstants
import wannabit.io.cosmostaion.network.model.Price
import java.util.concurrent.TimeUnit

interface MintscanService {
    companion object {
        fun create(): MintscanService {
            val builder = Retrofit.Builder().baseUrl(CosmostationConstants.MINTSCAN_API_URL).addConverterFactory(GsonConverterFactory.create())

            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client = OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(10, TimeUnit.SECONDS).build()
                builder.client(client)
            }

            return builder.build().create(MintscanService::class.java)
        }
    }

    @GET("v2/utils/market/prices")
    suspend fun price(): List<Price>
}