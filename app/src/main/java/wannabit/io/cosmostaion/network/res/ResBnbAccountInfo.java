package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResBnbAccountInfo {

    @SerializedName("address")
    public String address;

    @SerializedName("account_number")
    public String account_number;

    @SerializedName("sequence")
    public String sequence;

    @SerializedName("balances")
    public ArrayList<BnbBalance> balances;


    public class BnbBalance {
        @SerializedName("free")
        public String free;
        @SerializedName("frozen")
        public String frozen;
        @SerializedName("locked")
        public String locked;
        @SerializedName("symbol")
        public String symbol;

    }
}
