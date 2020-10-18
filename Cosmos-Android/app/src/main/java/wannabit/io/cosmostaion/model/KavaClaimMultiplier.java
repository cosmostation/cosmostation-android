package wannabit.io.cosmostaion.model;

import com.google.gson.annotations.SerializedName;

public class KavaClaimMultiplier {

    @SerializedName("name")
    public String name;

    @SerializedName("months_lockup")
    public String months_lockup;

    @SerializedName("factor")
    public String factor;
}
