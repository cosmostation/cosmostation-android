package wannabit.io.cosmostaion.network.req;

import com.google.gson.annotations.SerializedName;

public class ReqBroadAirDrop {
    @SerializedName("user_address")
    public String user_address;

    @SerializedName("desmos_address")
    public String desmos_address;

    public ReqBroadAirDrop(String desmos_address) {
        this.user_address = "cosmos1603mnk65ny0a6m3fs59s0qmpupm9g93vctsau4";
        this.desmos_address = desmos_address;
    }
}
