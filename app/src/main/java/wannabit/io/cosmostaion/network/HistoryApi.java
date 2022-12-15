package wannabit.io.cosmostaion.network;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import wannabit.io.cosmostaion.network.res.ResApiNewTxListCustom;
import wannabit.io.cosmostaion.network.res.ResBnbHistories;
import wannabit.io.cosmostaion.network.res.ResOkHistory;

public interface HistoryApi {
    //Bnb
    @GET("api/v1/transactions")
    Call<ResBnbHistories> getHistory(@Query("address") String address, @Query("startTime") String startTime, @Query("endTime") String endTime);

    @GET("api/v1/transactions")
    Call<ResBnbHistories> getHistoryAsset(@Query("address") String address, @Query("startTime") String startTime, @Query("endTime") String endTime, @Query("txAsset") String txAsset);
}
