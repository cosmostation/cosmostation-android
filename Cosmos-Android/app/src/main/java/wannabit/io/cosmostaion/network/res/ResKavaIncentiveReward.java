package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Coin;

public class ResKavaIncentiveReward {

    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public ArrayList<IncentiveRewardClaimable> result;




    public class IncentiveRewardClaimable {
        @SerializedName("claimable")
        public boolean claimable;

        @SerializedName("claim")
        public IncentiveRewardClaim claim;
    }




    public class IncentiveRewardClaim {
        @SerializedName("owner")
        public String owner;

        @SerializedName("reward")
        public Coin reward;

        @SerializedName("collateral_type")
        public String collateral_type;

        @SerializedName("claim_period_id")
        public String claim_period_id;

    }

//    public class KavaUnclaimedIncentiveReward {
//        @SerializedName("owner")
//        public String owner;
//
        @SerializedName("reward")
        public Coin reward;
//
//        @SerializedName("denom")
//        public String denom;
//
//        @SerializedName("claim_period_id")
//        public String claim_period_id;
//
//    }
}
