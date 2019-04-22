package wannabit.io.cosmostaion.network.req;

import com.google.gson.annotations.SerializedName;

import wannabit.io.cosmostaion.model.StakeStdTx;

public class ReqStakeBroadCast {

    @SerializedName("mode")
    public String returns;

    @SerializedName("tx")
    public StakeStdTx.Value tx;
}
