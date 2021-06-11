package wannabit.io.cosmostaion.network;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import wannabit.io.cosmostaion.dao.ChainParam;
import wannabit.io.cosmostaion.dao.Price;

public interface Station {

    @GET("v1/market/price")
    Call<ArrayList<Price>> getPrice(@Query("id") String denoms);

    @GET("v1/params/{chain_id}")
    Call<ChainParam> getParam(@Path("chain_id") String chain_id);

}
