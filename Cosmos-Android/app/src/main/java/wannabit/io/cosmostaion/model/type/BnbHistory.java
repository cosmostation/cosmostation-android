package wannabit.io.cosmostaion.model.type;

import com.google.gson.annotations.SerializedName;

public class BnbHistory {

    @SerializedName("txHash")
    public String txHash;

    @SerializedName("blockHeight")
    public int blockHeight;

    @SerializedName("txType")
    public String txType;

    @SerializedName("timeStamp")
    public String timeStamp;

    @SerializedName("fromAddr")
    public String fromAddr;

    @SerializedName("toAddr")
    public String toAddr;
}
