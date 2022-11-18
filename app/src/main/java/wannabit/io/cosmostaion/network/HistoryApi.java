package wannabit.io.cosmostaion.network;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import wannabit.io.cosmostaion.network.res.ResApiNewTxListCustom;
import wannabit.io.cosmostaion.network.res.ResBnbHistories;
import wannabit.io.cosmostaion.network.res.ResOkHistory;

public interface HistoryApi {
    // new cosmos api
    @GET("v1/account/new_txs/{address}")
    Call<ArrayList<ResApiNewTxListCustom>> getNewAccountTxCustom(@Path("address") String address, @Query("limit") String limit, @Query("from") int id);

    @GET("v1/account/new_txs/{address}/{valAddress}")
    Call<ArrayList<ResApiNewTxListCustom>> getNewStakeTxsCustom(@Path("address") String address, @Path("valAddress") String valAddress, @Query("limit") String limit);

    //Bnb
    @GET("api/v1/transactions")
    Call<ResBnbHistories> getHistory(@Query("address") String address, @Query("startTime") String startTime, @Query("endTime") String endTime);

    @GET("api/v1/transactions")
    Call<ResBnbHistories> getHistoryAsset(@Query("address") String address, @Query("startTime") String startTime, @Query("endTime") String endTime, @Query("txAsset") String txAsset);

    //Okc
    @Headers("Ok-Access-key: e829eb97-3fdb-4679-8937-4f6ea901817a")
    @GET("address/transaction-list")
    Call<ResOkHistory> getNewOkecTxs(@Query("chainShortName") String chainShortName, @Query("address") String address, @Query("limit") String limit);
}
