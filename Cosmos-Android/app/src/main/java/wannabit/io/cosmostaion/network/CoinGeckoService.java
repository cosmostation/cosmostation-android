package wannabit.io.cosmostaion.network;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import wannabit.io.cosmostaion.network.res.ResCgcTic;

public interface CoinGeckoService {

    @GET("api/v3/coins/{chain}")
    Call<ResCgcTic> getPriceTic(@Path("chain") String chain);
}
