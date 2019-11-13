package wannabit.io.cosmostaion.network;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import wannabit.io.cosmostaion.network.req.ReqTx;
import wannabit.io.cosmostaion.network.req.ReqTxToken;
import wannabit.io.cosmostaion.network.req.ReqTxVal;
import wannabit.io.cosmostaion.network.res.ResHistory;

public interface CosmosEsService {

    @POST("cosmos/v1/getTxsByAddr")
    Call<ResHistory> getTx(@Body ReqTx data);


    @POST("cosmos/v1/getTxsByAddr")
    Call<ResHistory> getValTx(@Body ReqTxVal data);

    @POST("cosmos/v1/getTxsByAddr")
    Call<ResHistory> getTokenTx(@Body ReqTxToken data);


    @POST("iris/v1/getTxsByAddr")
    Call<ResHistory> getIrisTx(@Body ReqTx data);

    @POST("iris/v1/getTxsByAddr")
    Call<ResHistory> getIrisValTx(@Body ReqTxVal data);

    @POST("iris/v1/getTxsByAddr")
    Call<ResHistory> getIrisTokenTx(@Body ReqTxToken data);



    @POST("kava/v1/getTxsByAddr")
    Call<ResHistory> getKavaTx(@Body ReqTx data);

    @POST("kava/v1/getTxsByAddr")
    Call<ResHistory> getKavaValTx(@Body ReqTxVal data);

    @POST("kava/v1/getTxsByAddr")
    Call<ResHistory> getKavaTokenTx(@Body ReqTxToken data);
}
