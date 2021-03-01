package wannabit.io.cosmostaion.model.kava;

import com.google.gson.annotations.SerializedName;

public class ClaimMultiplier {

    @SerializedName("name")
    public String name;

    @SerializedName("months_lockup")
    public String months_lockup;

    @SerializedName("factor")
    public String factor;
}
