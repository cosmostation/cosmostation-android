package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

public class ResBroadTx {

    @SerializedName("code")
    public int code;

    @SerializedName("data")
    public String data;

    @SerializedName("log")
    public String log;

    @SerializedName("hash")
    public String hash;

    @SerializedName("errorMsg")
    public String errorMsg;

    @SerializedName("errorCode")
    public int errorCode;
}
