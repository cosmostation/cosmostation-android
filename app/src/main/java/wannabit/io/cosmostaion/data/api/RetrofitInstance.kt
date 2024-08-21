package wannabit.io.cosmostaion.data.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.SUI_API
import wannabit.io.cosmostaion.common.CosmostationConstants
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    private val okHttpClient: OkHttpClient by lazy {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpExceptionInterceptor = HttpExceptionInterceptor()

        OkHttpClient.Builder().addInterceptor(httpExceptionInterceptor)
            .addInterceptor(httpLoggingInterceptor).connectTimeout(30, TimeUnit.SECONDS).build()
    }

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    val gson : Gson = GsonBuilder().setLenient().create()

    private val mintScanRetrofit: Retrofit by lazy {
        Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).client(okHttpClient)
            .baseUrl(CosmostationConstants.MINTSCAN_API_URL).build()
    }

    private val mintScanJsonRetrofit: Retrofit by lazy {
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).client(okHttpClient)
            .baseUrl(CosmostationConstants.MINTSCAN_API_URL).build()
    }

    private val skipRetrofit: Retrofit by lazy {
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).client(okHttpClient)
            .baseUrl(CosmostationConstants.SKIP_API_URL).build()
    }

    private val baseRetrofit: Retrofit by lazy {
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).client(okHttpClient)
            .baseUrl(CosmostationConstants.CHAIN_BASE_URL).build()
    }

    private val ecoSystemRetrofit: Retrofit by lazy {
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).client(okHttpClient)
            .baseUrl(CosmostationConstants.ECO_SYSTEM_URL).build()
    }

    private fun lcdRetrofit(chain: BaseChain): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).client(okHttpClient)
            .baseUrl(chain.cosmosFetcher()?.getLcd() ?: chain.lcdUrl).build()
    }

    private val suiRetrofit: Retrofit by lazy {
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).client(okHttpClient)
            .baseUrl(SUI_API).build()
    }

    val mintscanApi: MintscanApi by lazy {
        mintScanRetrofit.create(MintscanApi::class.java)
    }

    val mintscanJsonApi: MintscanApi by lazy {
        mintScanJsonRetrofit.create(MintscanApi::class.java)
    }

    fun lcdApi(chain: BaseChain): LcdApi {
        return lcdRetrofit(chain).create(LcdApi::class.java)
    }

    val skipApi: SkipApi by lazy {
        skipRetrofit.create(SkipApi::class.java)
    }

    val baseApi: MintscanApi by lazy {
        baseRetrofit.create(MintscanApi::class.java)
    }

    val ecoApi: MintscanApi by lazy {
        ecoSystemRetrofit.create(MintscanApi::class.java)
    }

    val suiApi: LcdApi by lazy {
        suiRetrofit.create(LcdApi::class.java)
    }
}