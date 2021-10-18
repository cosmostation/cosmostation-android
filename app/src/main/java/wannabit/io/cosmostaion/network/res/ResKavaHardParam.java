package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import wannabit.io.cosmostaion.model.kava.HardParam;

public class ResKavaHardParam {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public HardParam result;

}
