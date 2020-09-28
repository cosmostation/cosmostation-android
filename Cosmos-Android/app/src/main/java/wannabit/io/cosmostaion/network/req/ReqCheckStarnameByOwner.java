package wannabit.io.cosmostaion.network.req;

import com.google.gson.annotations.SerializedName;

public class ReqCheckStarnameByOwner {

    @SerializedName("owner")
    public String owner;

    @SerializedName("results_per_page")
    public int results_per_page;

    @SerializedName("offset")
    public int offset;
}
