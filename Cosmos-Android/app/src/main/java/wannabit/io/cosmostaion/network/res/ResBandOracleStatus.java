package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResBandOracleStatus {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public ArrayList<BandOracle> result;


    public boolean isEnable(String valOpAddress) {
        if (result == null) { return true; }
        for (BandOracle oracle:result) {
            if (oracle.address.equals(valOpAddress)) {
                return true;
            }
        }
        return false;
    }


    public class BandOracle {
        @SerializedName("address")
        public String address;

        @SerializedName("power")
        public String power;
    }


}
