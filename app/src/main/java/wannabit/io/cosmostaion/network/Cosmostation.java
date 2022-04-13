package wannabit.io.cosmostaion.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import wannabit.io.cosmostaion.network.req.ReqMoonPayKey;
import wannabit.io.cosmostaion.network.res.ResMoonPaySignature;

public interface Cosmostation {

    @POST("v1/sign/moonpay")
    Call<ResMoonPaySignature> getMoonPay(@Body ReqMoonPayKey data);
}
