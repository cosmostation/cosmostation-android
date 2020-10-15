package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Coin;

public class ResKavaHarvestReward {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public ArrayList<HarvestReward> result;

    public class HarvestReward {
        @SerializedName("owner")
        public String owner;

        @SerializedName("deposit_denom")
        public String deposit_denom;

        @SerializedName("amount")
        public Coin amount;

        @SerializedName("type")
        public String type;

    }
}
