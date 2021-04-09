package wannabit.io.cosmostaion.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UnbondingInfo {
    @SerializedName("delegator_address")
    public String delegator_address;

    @SerializedName("validator_address")
    public String validator_address;

    @SerializedName("entries")
    public ArrayList<Entry> entries;


    public class Entry {
        @SerializedName("creation_height")
        public String creation_height;

        @SerializedName("completion_time")
        public String completion_time;

        @SerializedName("initial_balance")
        public String initial_balance;

        @SerializedName("balance")
        public String balance;

    }

    public static class DpEntry {
        public String validator_address;
        public String completion_time;
        public String balance;

        public DpEntry(String validator_address, String completion_time, String balance) {
            this.validator_address = validator_address;
            this.completion_time = completion_time;
            this.balance = balance;
        }
    }
}
