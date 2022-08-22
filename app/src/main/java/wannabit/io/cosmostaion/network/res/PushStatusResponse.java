package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

public class PushStatusResponse {
    @SerializedName("subscribe")
    public Boolean subscribe;

    @SerializedName("timestamp")
    public String timestamp;
}
