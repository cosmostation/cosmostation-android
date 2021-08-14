package wannabit.io.cosmostaion.model.hdac;

import com.google.gson.annotations.SerializedName;

public class HdacTxInfo {
    @SerializedName("txid")
    public String txid;

    @SerializedName("blockhash")
    public String blockhash;

    @SerializedName("blockheight")
    public String blockheight;

    @SerializedName("confirmations")
    public long confirmations;

    @SerializedName("time")
    public long time;

    @SerializedName("valueOut")
    public String valueOut;
}
