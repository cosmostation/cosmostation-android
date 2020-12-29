package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

public class ResOkUnbonding {
    @SerializedName("delegator_address")
    public String delegator_address;

    @SerializedName("quantity")
    public String quantity;

    @SerializedName("completion_time")
    public String completion_time;

}
