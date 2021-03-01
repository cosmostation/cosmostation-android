package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.kava.ClaimMultiplier;
import wannabit.io.cosmostaion.model.type.Coin;

public class ResKavaIncentiveParam {

    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public IncentiveParam result;


    public class IncentiveParam {

        @SerializedName("active")
        public Boolean active;

        @SerializedName("rewards")
        public ArrayList<IncentiveReward> rewards;

        public boolean isActive() {
            if (active && rewards != null && rewards.size() > 0 ) {
                return true;
            } else {
                return false;
            }
        }

    }

    public class IncentiveReward {

        @SerializedName("active")
        public Boolean active;

        @SerializedName("collateral_type")
        public String collateral_type;

        @SerializedName("available_rewards")
        public Coin available_rewards;

        @SerializedName("duration")
        public String duration;

        @SerializedName("claim_multipliers")
        public ArrayList<ClaimMultiplier> claim_multipliers;

        @SerializedName("claim_duration")
        public String claim_duration;
    }


}
