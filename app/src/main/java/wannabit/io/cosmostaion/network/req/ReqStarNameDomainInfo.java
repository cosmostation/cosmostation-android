package wannabit.io.cosmostaion.network.req;

import com.google.gson.annotations.SerializedName;

public class ReqStarNameDomainInfo {

    @SerializedName("name")
    public String name;

    public ReqStarNameDomainInfo(String name) {
        this.name = name;
    }
}
