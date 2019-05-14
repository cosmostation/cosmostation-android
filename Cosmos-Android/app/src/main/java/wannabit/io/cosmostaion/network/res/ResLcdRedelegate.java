package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

public class ResLcdRedelegate {
    @SerializedName("delegator_address")
    public String delegator_address;

    @SerializedName("validator_src_address")
    public String validator_src_address;

    @SerializedName("validator_dst_address")
    public String validator_dst_address;
}
