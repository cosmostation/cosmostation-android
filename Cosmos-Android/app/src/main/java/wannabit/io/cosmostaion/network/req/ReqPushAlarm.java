package wannabit.io.cosmostaion.network.req;

import com.google.gson.annotations.SerializedName;

public class ReqPushAlarm {

    @SerializedName("chain_id")
    public int chain_id;

    @SerializedName("device_type")
    public String device_type;

    @SerializedName("address")
    public String address;

    @SerializedName("alarm_token")
    public String alarm_token;

    @SerializedName("alarm_status")
    public boolean alarm_status;
}
