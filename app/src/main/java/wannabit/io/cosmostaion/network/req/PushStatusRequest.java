package wannabit.io.cosmostaion.network.req;

import com.google.gson.annotations.SerializedName;

public class PushStatusRequest {

    @SerializedName("subscribe")
    public Boolean subscribe;

    @SerializedName("fcm_token")
    public String fcm_token;

    public PushStatusRequest(Boolean subscribe, String fcm_token) {
        this.subscribe = subscribe;
        this.fcm_token = fcm_token;
    }
}
