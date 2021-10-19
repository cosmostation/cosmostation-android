package wannabit.io.cosmostaion.network.req;

import com.google.gson.annotations.SerializedName;

public class ReqStarNameResolve {

    @SerializedName("starname")
    public String starname;

    public ReqStarNameResolve(String starname) {
        this.starname = starname;
    }
}
