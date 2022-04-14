package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResOkAccountToken {
    @SerializedName("code")
    public int code;

    @SerializedName("msg")
    public String msg;

    @SerializedName("detail_msg")
    public String detail_msg;

    @SerializedName("data")
    public OkAccountTokenData data;

    public static class OkAccountTokenData {
        @SerializedName("address")
        public String address;

        @SerializedName("currencies")
        public ArrayList<OkCurrency> currencies;
    }

    public static class OkCurrency {
        @SerializedName("symbol")
        public String symbol;

        @SerializedName("available")
        public String available;

        @SerializedName("locked")
        public String locked;

    }
}
