package wannabit.io.cosmostaion.model.type;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.network.res.ResLcdRedelegate;

public class Redelegate {
    @SerializedName("delegator_address")
    public String delegator_address;

    @SerializedName("validator_src_address")
    public String validator_src_address;

    @SerializedName("validator_dst_address")
    public String validator_dst_address;

    @SerializedName("error")
    public String error;

    @SerializedName("entries")
    public ArrayList<Entry> entries;

    public static class Entry {
        @SerializedName("creation_height")
        public String creation_height;

        @SerializedName("completion_time")
        public String completion_time;

        @SerializedName("initial_balance")
        public String initial_balance;

        @SerializedName("shares_dst")
        public String shares_dst;
    }
}
