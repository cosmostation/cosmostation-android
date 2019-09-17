package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import wannabit.io.cosmostaion.model.StdTx;

public class ResBnbTxInfo {

    @SerializedName("code")
    public Integer code;

    @SerializedName("height")
    public String height;

    @SerializedName("hash")
    public String hash;

    @SerializedName("ok")
    public boolean ok;

    @SerializedName("tx")
    public StdTx tx;
}
