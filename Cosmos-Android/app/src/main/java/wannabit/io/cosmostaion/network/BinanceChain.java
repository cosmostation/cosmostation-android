package wannabit.io.cosmostaion.network;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.res.ResBnbAccountInfo;
import wannabit.io.cosmostaion.network.res.ResBnbHistories;
import wannabit.io.cosmostaion.network.res.ResBnbTic;

public interface BinanceChain {

    @GET("/api/v1/account/{address}")
    Call<ResBnbAccountInfo> getAccountInfo(@Path("address") String address);

    @GET("/api/v1/transactions")
    Call<ResBnbHistories> getHistory(@Query("address") String address, @Query("startTime") String startTime, @Query("endTime") String endTime);

    @GET("/api/v1/tokens")
    Call<ArrayList<BnbToken>> getTokens(@Query("limit") String limit);

    @GET("/api/v1/ticker/24hr")
    Call<ArrayList<ResBnbTic>> getTic(@Query("symbol") String symbol);
}
