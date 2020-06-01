package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Coin;

public class ResKavaIncentiveReward {

    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public ArrayList<KavaUnclaimedIncentiveReward> result;

    public class KavaUnclaimedIncentiveReward {
        @SerializedName("owner")
        public String owner;

        @SerializedName("reward")
        public Coin reward;

        @SerializedName("denom")
        public String denom;

        @SerializedName("claim_period_id")
        public String claim_period_id;

    }
}
