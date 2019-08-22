package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

public class ResStakingPool {

    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public Result result;

    public class Result {
        @SerializedName("not_bonded_tokens")
        public String not_bonded_tokens;

        @SerializedName("bonded_tokens")
        public String bonded_tokens;
    }

}
