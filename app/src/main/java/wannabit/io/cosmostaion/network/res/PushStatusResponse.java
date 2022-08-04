package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

public class PushStatusResponse {

    @SerializedName("sub_tx")
    public Boolean sub_tx;

    @SerializedName("sub_notice")
    public Boolean sub_notice;

    @SerializedName("timestamp")
    public String timestamp;
}
