package wannabit.io.cosmostaion.network;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import wannabit.io.cosmostaion.network.res.ResApiNewTxListCustom;

public interface HistoryApi {
    @GET("v1/account/new_txs/{address}")
    Single<List<ResApiNewTxListCustom>> getNewAccountTxCustomRx(@Path("address") String address, @Query("limit") int limit);

    @GET("v1/account/new_txs/{address}/{valAddress}")
    Call<ArrayList<ResApiNewTxListCustom>> getNewStakeTxsCustom(@Path("address") String address, @Path("valAddress") String valAddress, @Query("limit") String limit);

    @GET("v1/account/new_txs/{address}/{valAddress}")
    Single<List<ResApiNewTxListCustom>> getNewStakeTxsCustomRx(@Path("address") String address, @Path("valAddress") String valAddress, @Query("limit") int limit);
}
