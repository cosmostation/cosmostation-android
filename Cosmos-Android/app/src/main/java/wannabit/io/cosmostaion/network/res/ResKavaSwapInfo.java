package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

public class ResKavaSwapInfo {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public Result result;

    public class Result {

        @SerializedName("status")
        public String status;

        @SerializedName("direction")
        public String direction;

    }
}
