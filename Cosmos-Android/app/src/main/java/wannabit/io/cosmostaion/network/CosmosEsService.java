package wannabit.io.cosmostaion.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import wannabit.io.cosmostaion.network.req.ReqTx;
import wannabit.io.cosmostaion.network.req.ReqTxVal;
import wannabit.io.cosmostaion.network.res.ResHistory;

public interface CosmosEsService {

    @POST("cosmos/v1/getTxsByAddr")
    Call<ResHistory> getTx(@Body ReqTx data);


    @POST("cosmos/v1/getTxsByAddr")
    Call<ResHistory> getValTx(@Body ReqTxVal data);


    @POST("iris/v1/getTxsByAddr")
    Call<ResHistory> getIrisTx(@Body ReqTx data);
}
