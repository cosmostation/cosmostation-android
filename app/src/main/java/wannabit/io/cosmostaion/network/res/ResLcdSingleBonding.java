package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import wannabit.io.cosmostaion.model.BondingInfo;

public class ResLcdSingleBonding {

    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public BondingInfo result;
}
