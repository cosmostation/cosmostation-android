package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import wannabit.io.cosmostaion.model.UnbondingInfo;

public class ResLcdSingleUnBonding {

    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public UnbondingInfo result;
}
