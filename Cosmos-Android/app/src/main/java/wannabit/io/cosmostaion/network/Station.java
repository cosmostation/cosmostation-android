package wannabit.io.cosmostaion.network;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import wannabit.io.cosmostaion.dao.Price;

public interface Station {

    @GET("v1/market/price")
    Call<ArrayList<Price>> getPrice(@Query("id") String denoms);
}
