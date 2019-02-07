package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

public class ResLcdBondings {

    @SerializedName("delegator_addr")
    public String delegator_addr;

    @SerializedName("validator_addr")
    public String validator_addr;

    @SerializedName("shares")
    public String shares;
}
