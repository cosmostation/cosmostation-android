package wannabit.io.cosmostaion.network;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import wannabit.io.cosmostaion.network.res.ResApiNewTxListCustom;
import wannabit.io.cosmostaion.network.res.ResApiTxList;

public interface HistoryApi {

    @GET("v1/account/txs/{address}")
    Call<ArrayList<ResApiTxList.Data>> getAccountTxs(@Path("address") String address, @Query("limit") String limit);

    @GET("v1/account/txs/{address}/{valAddress}")
    Call<ArrayList<ResApiTxList.Data>> getStakeTxs(@Path("address") String address, @Path("valAddress") String valAddress);

    // new cosmos api
    @GET("v1/account/new_txs/{address}")
    Call<ArrayList<ResApiNewTxListCustom>> getNewAccountTxCustom(@Path("address") String address, @Query("limit") String limit);

    @GET("v1/account/new_txs/{address}/{valAddress}")
    Call<ArrayList<ResApiNewTxListCustom>> getNewStakeTxsCustom(@Path("address") String address, @Path("valAddress") String valAddress, @Query("limit") String limit);
}
