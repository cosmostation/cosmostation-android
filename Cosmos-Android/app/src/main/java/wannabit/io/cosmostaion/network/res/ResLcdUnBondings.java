package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Coin;

public class ResLcdUnBondings {

    @SerializedName("delegator_addr")
    public String delegator_addr;

    @SerializedName("validator_addr")
    public String validator_addr;

    @SerializedName("entries")
    public ArrayList<Entry> entries;

    public class Entry {
        @SerializedName("creation_height")
        public String creation_height;

        @SerializedName("completion_time")
        public String completion_time;

        @SerializedName("initial_balance")
        public Coin initial_balance;

        @SerializedName("balance")
        public Coin balance;
    }
}
