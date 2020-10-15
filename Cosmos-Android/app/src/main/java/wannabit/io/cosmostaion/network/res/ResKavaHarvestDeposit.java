package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Coin;

public class ResKavaHarvestDeposit {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public ArrayList<HarvestDeposit> result;


    public class HarvestDeposit {
        @SerializedName("depositor")
        public String depositor;

        @SerializedName("amount")
        public Coin amount;

        @SerializedName("type")
        public String type;

    }
}
