package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.KavaCDP;
import wannabit.io.cosmostaion.model.type.Coin;

public class ResCdpList {

    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public ArrayList<Result> result;

    public class Result {

        @SerializedName("cdp")
        public KavaCDP cdp;

        @SerializedName("collateral_value")
        public Coin collateral_value;

        @SerializedName("collateralization_ratio")
        public String collateralization_ratio;
    }
}
