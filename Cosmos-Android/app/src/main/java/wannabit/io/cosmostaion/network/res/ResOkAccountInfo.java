package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

public class ResOkAccountInfo {
    @SerializedName("type")
    public String type;

    @SerializedName("value")
    public Value value;

    public class Value {
        @SerializedName("address")
        public String address;

        @SerializedName("eth_address")
        public String eth_address;

        @SerializedName("account_number")
        public String account_number;

        @SerializedName("sequence")
        public String sequence;

        @SerializedName("code_hash")
        public String code_hash;

    }

}
