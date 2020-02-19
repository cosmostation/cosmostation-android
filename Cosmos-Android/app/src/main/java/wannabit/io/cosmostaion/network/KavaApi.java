package wannabit.io.cosmostaion.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import wannabit.io.cosmostaion.network.res.ResApiTxList;

public interface KavaApi {

    @GET("/v1/account/txs/{address}")
    Call<ResApiTxList> getAccountTxs(@Path("address") String address);
}
