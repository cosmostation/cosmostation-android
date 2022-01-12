package wannabit.io.cosmostaion.network.req;

import com.google.gson.annotations.SerializedName;

public class ReqDesmosAirDrop {
    @SerializedName("desmos_address")
    public String desmos_address;

    public ReqDesmosAirDrop(String desmos_address) {
        this.desmos_address = desmos_address;
    }
}
