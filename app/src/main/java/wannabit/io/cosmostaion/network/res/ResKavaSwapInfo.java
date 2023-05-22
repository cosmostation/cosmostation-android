package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Coin;

public class ResKavaSwapInfo {
    @SerializedName("atomic_swap")
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
