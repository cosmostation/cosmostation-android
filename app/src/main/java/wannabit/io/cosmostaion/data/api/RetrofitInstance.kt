package wannabit.io.cosmostaion.data.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import wannabit.io.cosmostaion.common.CosmostationConstants
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private const val CONNECT_TIMEOUT_SEC = 10L

    private val okHttpClient: OkHttpClient by lazy {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpExceptionInterceptor = HttpExceptionInterceptor()

        OkHttpClient.Builder()
            .addInterceptor(httpExceptionInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(CONNECT_TIMEOUT_SEC, TimeUnit.SECONDS)
            .build()
    }

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val walletRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .baseUrl(CosmostationConstants.WALLET_API_URL)
            .build()
    }

    private val mintScanRetrofit: Retrofit by lazy {
        Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .baseUrl(CosmostationConstants.MINTSCAN_API_URL)
        .build()
    }

    val walletApi: WalletApi by lazy {
        walletRetrofit.create(WalletApi::class.java)
    }

    val mintscanApi: MintscanApi by lazy {
        mintScanRetrofit.create(MintscanApi::class.java)
    }
}