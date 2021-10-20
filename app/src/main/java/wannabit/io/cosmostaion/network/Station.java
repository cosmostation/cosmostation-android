package wannabit.io.cosmostaion.network;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import wannabit.io.cosmostaion.dao.ChainParam;
import wannabit.io.cosmostaion.dao.Price;
import wannabit.io.cosmostaion.network.res.ResIbcPaths;
import wannabit.io.cosmostaion.network.res.ResIbcTokens;

public interface Station {

    @GET("v1/market/prices")
    Call<ArrayList<Price>> getPrice();

    @GET("v1/params/{chain_id}")
    Call<ChainParam> getParam(@Path("chain_id") String chain_id);

    @GET("v1/ibc/paths/{chain_id}")
    Call<ResIbcPaths> getIbcPaths(@Path("chain_id") String chain_id);

    @GET("v1/ibc/tokens/{chain_id}")
    Call<ResIbcTokens> getIbcTokens(@Path("chain_id") String chain_id);

}
