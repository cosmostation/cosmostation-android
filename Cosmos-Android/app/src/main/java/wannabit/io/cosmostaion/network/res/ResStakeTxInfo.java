package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import wannabit.io.cosmostaion.model.StakeStdTx;

public class ResStakeTxInfo {
    @SerializedName("height")
    public String height;

    @SerializedName("txhash")
    public String txhash;

    @SerializedName("tx")
    public StakeStdTx tx;
}
