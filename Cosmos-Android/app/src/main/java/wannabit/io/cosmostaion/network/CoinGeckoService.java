package wannabit.io.cosmostaion.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import wannabit.io.cosmostaion.network.res.ResCgcTic;

public interface CoinGeckoService {

    @GET("api/v3/coins/{chain}")
    Call<ResCgcTic> getPriceTic(@Path("chain") String chain);


    @GET("api/v3/coins/{chain}")
    Call<ResCgcTic> getPriceTicLite(@Path("chain") String chain, @Query("localization") String localization, @Query("tickers") String tickers, @Query("community_data") String community_data, @Query("developer_data") String developer_data, @Query("sparkline") String sparkline);
}
