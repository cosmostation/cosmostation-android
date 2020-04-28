package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

public class ResIovSubmitTx {

    @SerializedName("code")
    public int code;

    @SerializedName("data")
    public String data;

    @SerializedName("log")
    public String log;

    @SerializedName("hash")
    public String hash;
}
