package wannabit.io.cosmostaion.network.req;

import com.google.gson.annotations.SerializedName;

import wannabit.io.cosmostaion.model.StdTx;

public class ReqBroadCast {

    @SerializedName("return")
    public String returns;

    @SerializedName("tx")
    public StdTx.Value tx;

}
