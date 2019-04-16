package wannabit.io.cosmostaion.network;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.req.ReqTx;
import wannabit.io.cosmostaion.network.req.ReqTxVal;
import wannabit.io.cosmostaion.network.res.ResHistory;

public interface EsService {

    @POST("/tx_index/_search")
    Call<ResHistory> getTx(@Body ReqTx data);


    @POST("/tx_index/_search")
    Call<ResHistory> getValTx(@Body ReqTxVal data);

}
