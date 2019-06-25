package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import wannabit.io.cosmostaion.model.StdTx;

public class ResStakeTxInfo {
    @SerializedName("height")
    public String height;

    @SerializedName("txhash")
    public String txhash;

    @SerializedName("tx")
    public StdTx tx;

    @SerializedName("timestamp")
    public String timestamp;
}
