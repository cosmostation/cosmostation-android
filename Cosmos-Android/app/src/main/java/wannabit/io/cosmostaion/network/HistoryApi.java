package wannabit.io.cosmostaion.network;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import wannabit.io.cosmostaion.network.res.ResApiTxList;

public interface HistoryApi {

    @GET("/v1/account/txs/{address}")
    Call<ArrayList<ResApiTxList.Data>> getAccountTxs(@Path("address") String address, @Query("limit") String limit);

    @GET("/v1/account/transfer_txs/{address}")
    Call<ArrayList<ResApiTxList.Data>> getTokenTxs(@Path("address") String address, @Query("denom") String denom);

    @GET("/v1/account/txs/{address}/{valAddress}")
    Call<ArrayList<ResApiTxList.Data>> getStakeTxs(@Path("address") String address, @Path("valAddress") String valAddress);
}
