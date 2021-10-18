package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Coin;

public class ResKavaSwapInfo {

    public static final String STATUS_NULL      = "NULL";
    public static final String STATUS_OPEN      = "Open";
    public static final String STATUS_COMPLETED = "Completed";
    public static final String STATUS_EXPIRED   = "Expired";

    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public Result result;

    public class Result {

        @SerializedName("amount")
        public ArrayList<Coin> amount;

        @SerializedName("sender")
        public String sender;

        @SerializedName("status")
        public String status;

        @SerializedName("direction")
        public String direction;

    }
}
