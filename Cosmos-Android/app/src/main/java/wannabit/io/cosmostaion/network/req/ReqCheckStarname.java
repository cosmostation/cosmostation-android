package wannabit.io.cosmostaion.network.req;

import com.google.gson.annotations.SerializedName;

public class ReqCheckStarname {

    @SerializedName("starname")
    public String starname;

    public ReqCheckStarname(String starname) {
        this.starname = starname;
    }
}
