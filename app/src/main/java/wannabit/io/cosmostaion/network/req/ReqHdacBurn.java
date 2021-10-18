package wannabit.io.cosmostaion.network.req;

import com.google.gson.annotations.SerializedName;

public class ReqHdacBurn {
    @SerializedName("rawtx")
    public String rawtx;

    public ReqHdacBurn(String rawtx) {
        this.rawtx = rawtx;
    }
}
