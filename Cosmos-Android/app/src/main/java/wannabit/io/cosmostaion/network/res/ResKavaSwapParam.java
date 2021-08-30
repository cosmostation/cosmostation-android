package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import wannabit.io.cosmostaion.model.kava.SwapParam;

public class ResKavaSwapParam {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public SwapParam result;

}
