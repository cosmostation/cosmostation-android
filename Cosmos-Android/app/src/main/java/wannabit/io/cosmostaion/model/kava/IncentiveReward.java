package wannabit.io.cosmostaion.model.kava;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Coin;

public class IncentiveReward {
    @SerializedName("hard_claims")
    public ArrayList<HardClaim> hard_claims;

    @SerializedName("usdx_minting_claims")
    public ArrayList<UsdxMintingClaim> usdx_minting_claims;

    public BigDecimal getMintingRewardAmount() {
        BigDecimal result = BigDecimal.ZERO;
        for (UsdxMintingClaim reward : usdx_minting_claims) {
            if (reward.base_claim.reward != null) {
                result = result.add(new BigDecimal(reward.base_claim.reward.amount));
            }
        }
        return result;
    }


    public class HardClaim {
        @SerializedName("base_claim")
        public HardBaseClaim base_claim;

    }

    public class UsdxMintingClaim {
        @SerializedName("base_claim")
        public UsdxBaseClaim base_claim;

    }

    public class HardBaseClaim {
        @SerializedName("owner")
        public String owner;

        @SerializedName("reward")
        public ArrayList<Coin> reward;

    }

    public class UsdxBaseClaim {
        @SerializedName("owner")
        public String owner;

        @SerializedName("reward")
        public Coin reward;

    }
}
