package wannabit.io.cosmostaion.network.req;

import com.google.gson.annotations.SerializedName;

public class ReqStarNameByOwner {

    @SerializedName("owner")
    public String owner;

    @SerializedName("results_per_page")
    public int results_per_page;

    @SerializedName("offset")
    public int offset;

    public ReqStarNameByOwner(String owner, int results_per_page, int offset) {
        this.owner = owner;
        this.results_per_page = results_per_page;
        this.offset = offset;
    }
}
