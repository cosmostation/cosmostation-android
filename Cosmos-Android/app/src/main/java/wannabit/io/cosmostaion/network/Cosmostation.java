package wannabit.io.cosmostaion.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import wannabit.io.cosmostaion.network.req.ReqMoonPayKey;
import wannabit.io.cosmostaion.network.req.ReqPushAlarm;
import wannabit.io.cosmostaion.network.res.ResMoonPaySignature;
import wannabit.io.cosmostaion.network.res.ResPushAlarm;
import wannabit.io.cosmostaion.network.res.ResVersionCheck;

public interface Cosmostation {

    @GET("v1/app/version/android")
    Call<ResVersionCheck> getVersion();

    @POST("v1/account/update")
    Call<ResPushAlarm> updateAlarm(@Body ReqPushAlarm data);

    @POST("v1/sign/moonpay")
    Call<ResMoonPaySignature> getMoonPay(@Body ReqMoonPayKey data);
}
