package wannabit.io.cosmostaion.model;

import com.google.gson.annotations.SerializedName;

public class SifIncentive {
    @SerializedName("user")
    public User user;

    public class User {
        @SerializedName("totalClaimableCommissionsAndClaimableRewards")
        public double totalClaimableCommissionsAndClaimableRewards;

        @SerializedName("totalRewardsOnDepositedAssetsAtMaturity")
        public double totalRewardsOnDepositedAssetsAtMaturity;

        @SerializedName("maturityApy")
        public double maturityApy;

        @SerializedName("maturityDateISO")
        public String maturityDateISO;
    }

}
