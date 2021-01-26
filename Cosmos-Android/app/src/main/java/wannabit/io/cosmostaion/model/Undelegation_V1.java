package wannabit.io.cosmostaion.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Undelegation_V1 {
    @SerializedName("delegator_address")
    public String delegator_address;

    @SerializedName("validator_address")
    public String validator_address;

    @SerializedName("entries")
    public ArrayList<Entry_V1> entries;

    public class Entry_V1 {
        @SerializedName("creation_height")
        public String creation_height;

        @SerializedName("completion_time")
        public String completion_time;

        @SerializedName("initial_balance")
        public String initial_balance;

        @SerializedName("balance")
        public String balance;

    }

    public BigDecimal getAllUnbondingBalance() {
        BigDecimal result = BigDecimal.ZERO;
        if (entries != null) {
            for (Entry_V1 entry: entries) {
                result = result.add(new BigDecimal(entry.balance));
            }
        }
        return result;
    }
}
