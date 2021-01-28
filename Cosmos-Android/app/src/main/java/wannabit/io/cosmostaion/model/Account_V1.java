package wannabit.io.cosmostaion.model;

import com.google.gson.annotations.SerializedName;

public class Account_V1 {
    @SerializedName("@type")
    public String type;

    @SerializedName("address")
    public String address;

    @SerializedName("pub_key")
    public PublicKey pub_key;

    @SerializedName("account_number")
    public String account_number;

    @SerializedName("sequence")
    public String sequence;

    public class PublicKey {
        @SerializedName("@type")
        public String type;

        @SerializedName("key")
        public String key;

    }
}
