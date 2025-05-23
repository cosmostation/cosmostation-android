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
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.chain.majorClass.MOVE_API
import wannabit.io.cosmostaion.common.CosmostationConstants
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    private val okHttpClient: OkHttpClient by lazy {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpExceptionInterceptor = HttpExceptionInterceptor()

        OkHttpClient.Builder().addInterceptor(httpExceptionInterceptor)
            .addInterceptor(httpLoggingInterceptor).connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).build()
    }

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    val gson: Gson = GsonBuilder().setLenient().create()

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

    private fun lcdRetrofit(chain: BaseChain): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).client(okHttpClient)
            .baseUrl(chain.cosmosFetcher()?.getLcd() ?: chain.lcdUrl).build()
    }

    private fun cosmosLcdRetrofit(chain: BaseChain): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).client(okHttpClient)
            .baseUrl(chain.lcdUrl).build()
    }

    private val moveRetrofit: Retrofit by lazy {
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).client(okHttpClient)
            .baseUrl(MOVE_API).build()
    }

    private fun bitRetrofit(chain: ChainBitCoin86): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).client(okHttpClient)
            .baseUrl(chain.btcFetcher()?.mempoolUrl() ?: chain.mainUrl).build()
    }

    private fun bitApiRetrofit(chain: BaseChain): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).client(okHttpClient)
            .baseUrl(chain.apiUrl).build()
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

    fun cosmosLcdApi(chain: BaseChain): LcdApi {
        return cosmosLcdRetrofit(chain).create(LcdApi::class.java)
    }

    fun bitApi(chain: ChainBitCoin86): LcdApi {
        return bitRetrofit(chain).create(LcdApi::class.java)
    }

    fun bitExternalApi(chain: BaseChain): LcdApi {
        return bitApiRetrofit(chain).create(LcdApi::class.java)
    }

    val skipApi: SkipApi by lazy {
        skipRetrofit.create(SkipApi::class.java)
    }

    val moveApi: LcdApi by lazy {
        moveRetrofit.create(LcdApi::class.java)
    }
}