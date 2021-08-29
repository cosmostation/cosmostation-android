package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import wannabit.io.cosmostaion.model.kava.SwapPool;

public class ResKavaPoolInfo {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public SwapPool result;
}
