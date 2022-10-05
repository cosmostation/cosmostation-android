package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResGasRateParam {
    @SerializedName("chain")
    public String chain;

    @SerializedName("base")
    public int base;

    @SerializedName("rate")
    public ArrayList<String> rates;
}
