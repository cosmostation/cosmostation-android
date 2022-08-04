package wannabit.io.cosmostaion.network.req;

import com.google.gson.annotations.SerializedName;

public class PushStatusRequest {

    @SerializedName("sub_tx")
    public Boolean sub_tx;

    @SerializedName("sub_notice")
    public Boolean sub_notice;

    @SerializedName("fcm_token")
    public String fcm_token;

    public PushStatusRequest(Boolean sub_tx, Boolean sub_notice, String fcm_token) {
        this.sub_tx = sub_tx;
        this.sub_notice = sub_notice;
        this.fcm_token = fcm_token;
    }
}
