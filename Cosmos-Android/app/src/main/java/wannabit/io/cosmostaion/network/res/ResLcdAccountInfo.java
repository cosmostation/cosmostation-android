package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Pub_key;

public class ResLcdAccountInfo {
    @SerializedName("type")
    public String type;

    @SerializedName("value")
    public Value value;

    public class Value {
        @SerializedName("address")
        public String address;

        @SerializedName("coins")
        public ArrayList<Coin> coins;

        @SerializedName("public_key")
        public Pub_key public_key;

        @SerializedName("account_number")
        public String account_number;

        @SerializedName("sequence")
        public String sequence;
    }
}
