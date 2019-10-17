package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResIovBalance {

    public String address;

    @SerializedName("balance")
    public ArrayList<IovBalance> balance;

    public class IovBalance {
        public String quantity;
        public int fractionalDigits;
        public String tokenTicker;

    }
}
