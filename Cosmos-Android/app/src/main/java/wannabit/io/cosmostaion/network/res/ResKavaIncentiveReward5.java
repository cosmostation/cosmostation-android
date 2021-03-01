package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Coin;

public class ResKavaIncentiveReward5 {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public IncentiveReward5 result;


    public class IncentiveReward5 {
        @SerializedName("hard_claims")
        public ArrayList<HardClaim5> hard_claims;

        @SerializedName("usdx_minting_claims")
        public ArrayList<UsdxMintingClaim5> usdx_minting_claims;

        public BigDecimal getMintingRewardAmount() {
            BigDecimal result = BigDecimal.ZERO;
            for (UsdxMintingClaim5 reward : usdx_minting_claims) {
                if (reward.base_claim.reward != null) {
                    result = result.add(new BigDecimal(reward.base_claim.reward.amount));
                }
            }
            return result;
        }

    }

    public class HardClaim5 {
        @SerializedName("base_claim")
        public HardBaseClaim5 base_claim;

    }

    public class UsdxMintingClaim5 {
        @SerializedName("base_claim")
        public UsdxBaseClaim5 base_claim;

    }

    public class HardBaseClaim5 {
        @SerializedName("owner")
        public String owner;

        @SerializedName("reward")
        public ArrayList<Coin> reward;

    }

    public class UsdxBaseClaim5 {
        @SerializedName("owner")
        public String owner;

        @SerializedName("reward")
        public Coin reward;

    }
}
