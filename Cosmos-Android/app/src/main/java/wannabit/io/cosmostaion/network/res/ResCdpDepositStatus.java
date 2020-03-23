package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Coin;

public class ResCdpDepositStatus {

    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public ArrayList<Result> result;

    public class Result {

        @SerializedName("cdp_id")
        public String cdp_id;

        @SerializedName("depositor")
        public String depositor;

        @SerializedName("amount")
        public ArrayList<Coin> amount;

    }

}
