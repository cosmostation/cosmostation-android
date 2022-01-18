package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Coin;

public class ResKavaHardModuleAccount {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public ArrayList<HarvestAccount> result;


    public class HarvestAccount {
        @SerializedName("account")
        public AccountValue account;

        @SerializedName("coins")
        public ArrayList<Coin> coins;

        public class AccountValue {
            @SerializedName("type")
            public String type;

            @SerializedName("value")
            public HarvestAccountValue value;

            public class HarvestAccountValue {
                @SerializedName("address")
                public String address;

                @SerializedName("account_number")
                public String account_number;

                @SerializedName("sequence")
                public String sequence;

                @SerializedName("name")
                public String name;
            }
        }
    }
}
