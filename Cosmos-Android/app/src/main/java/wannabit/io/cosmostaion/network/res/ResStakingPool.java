package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

public class ResStakingPool {

    @SerializedName("not_bonded_tokens")
    public String not_bonded_tokens;

    @SerializedName("bonded_tokens")
    public String bonded_tokens;
}
