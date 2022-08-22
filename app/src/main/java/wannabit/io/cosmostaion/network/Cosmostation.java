package wannabit.io.cosmostaion.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import wannabit.io.cosmostaion.network.req.PushStatusRequest;
import wannabit.io.cosmostaion.network.req.PushSyncRequest;
import wannabit.io.cosmostaion.network.req.ReqMoonPayKey;
import wannabit.io.cosmostaion.network.req.ReqPushAlarm;
import wannabit.io.cosmostaion.network.res.PushStatusResponse;
import wannabit.io.cosmostaion.network.res.ResMoonPaySignature;
import wannabit.io.cosmostaion.network.res.ResPushAlarm;
import wannabit.io.cosmostaion.network.res.ResVersionCheck;

public interface Cosmostation {

    @GET("v1/app/version/android")
    Call<ResVersionCheck> getVersion();

    @Deprecated
    @POST("v1/account/update")
    Call<ResPushAlarm> updateAlarm(@Body ReqPushAlarm data);

    @POST("v1/sign/moonpay")
    Call<ResMoonPaySignature> getMoonPay(@Body ReqMoonPayKey data);

    @PUT("v1/push/alarm/status")
    Call<Void> putAlarmStatus(@Body PushStatusRequest status);

    @POST("v1/push/token/address")
    Call<Void> syncPushAddress(@Body PushSyncRequest status);

    @GET("v1/push/alarm/status/{fcmToken}")
    Call<PushStatusResponse> getPushStatus(@Path("fcmToken") String fcmToken);
}
