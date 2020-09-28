package wannabit.io.cosmostaion.network.req;

import com.google.gson.annotations.SerializedName;

public class ReqAccountInDomain {

    @SerializedName("domain")
    public String domain;

    @SerializedName("results_per_page")
    public int results_per_page;

    @SerializedName("offset")
    public int offset;
}
