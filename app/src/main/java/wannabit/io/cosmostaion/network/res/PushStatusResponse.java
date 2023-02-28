package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

public class PushStatusResponse {
    @SerializedName("subscribe")
    public boolean subscribe;

    @SerializedName("timestamp")
    public String timestamp;
}
