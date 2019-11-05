package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Coin;

public class ResLcdKavaAccountInfo {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public Result result;

    public class Result {
        @SerializedName("type")
        public String type;

        @SerializedName("value")
        public Value value;
    }

    public class Value {
        @SerializedName("address")
        public String address;

        @SerializedName("coins")
        public ArrayList<Coin> coins;

        @SerializedName("account_number")
        public String account_number;

        @SerializedName("sequence")
        public String sequence;

        @SerializedName("PeriodicVestingAccount")
        public PeriodicVestingAccount PeriodicVestingAccount;
    }

    public class PeriodicVestingAccount {
//        @SerializedName("start_time")
//        public String start_time;

        @SerializedName("BaseVestingAccount")
        public BaseVestingAccount BaseVestingAccount;
    }

    public class BaseVestingAccount {
        @SerializedName("BaseAccount")
        public BaseAccount BaseAccount;

        @SerializedName("original_vesting")
        public ArrayList<Coin> original_vesting;

//        @SerializedName("delegated_free")
//        public ArrayList<Coin> DelegatedFree;

        @SerializedName("delegated_vesting")
        public ArrayList<Coin> delegated_vesting;

        @SerializedName("end_time")
        public String end_time;
    }

    public class BaseAccount {
        @SerializedName("address")
        public String address;

        @SerializedName("coins")
        public ArrayList<Coin> coins;

        @SerializedName("account_number")
        public String account_number;

        @SerializedName("sequence")
        public String sequence;
    }
}
