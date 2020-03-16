package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResIovBalance {

    @SerializedName("coins")
    public ArrayList<IovCoin> coins;

    public static class IovCoin {
        public int whole = 0;
        public int fractional = 0;
        public String ticker;

        public String getDpAmount(String denom) {
            if (denom.equals(ticker)) {
                return "" + whole + "." + fractional;
            }
            return "0";
        }

    }
}
