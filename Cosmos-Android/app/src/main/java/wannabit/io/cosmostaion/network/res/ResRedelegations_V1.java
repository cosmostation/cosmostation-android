package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResRedelegations_V1 {
    @SerializedName("redelegation_responses")
    public ArrayList<RedelegationResponses_V1> redelegation_responses;


    public class RedelegationResponses_V1 {
        @SerializedName("redelegation")
        public Redelegation_V1 redelegation;

        @SerializedName("entries")
        public ArrayList<Entry_V1> entries;

    }

    public class Redelegation_V1 {
        @SerializedName("delegator_address")
        public String delegator_address;

        @SerializedName("validator_src_address")
        public String validator_src_address;

        @SerializedName("validator_dst_address")
        public String validator_dst_address;

    }

    public class Entry_V1 {
        @SerializedName("redelegation_entry")
        public Redelegation_Entry_V1 redelegation_entry;

        @SerializedName("balance")
        public String balance;

    }

    public class Redelegation_Entry_V1 {
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
