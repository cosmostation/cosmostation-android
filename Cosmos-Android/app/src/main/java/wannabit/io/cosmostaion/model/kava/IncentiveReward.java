package wannabit.io.cosmostaion.model.kava;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Coin;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;

public class IncentiveReward {
    @SerializedName("hard_claims")
    public ArrayList<HardClaim> hard_claims;

    @SerializedName("usdx_minting_claims")
    public ArrayList<UsdxMintingClaim> usdx_minting_claims;

    public BigDecimal getSupplyRewardFactor(String denom) {
        BigDecimal result = BigDecimal.ZERO;
        if (hard_claims != null && hard_claims.size() > 0 && hard_claims.get(0).supply_reward_indexes != null) {
            for (SupplyRewardIndex index: hard_claims.get(0).supply_reward_indexes) {
                if (index.collateral_type.equals(denom)) {
                    if (index.reward_indexes != null && index.reward_indexes.size() > 0) {
                        result = new BigDecimal(index.reward_indexes.get(0).reward_factor);
                    }
                }
            }
        }
        return result;
    }

    public BigDecimal getBorrowRewardFactor(String denom) {
        BigDecimal result = BigDecimal.ZERO;
        if (hard_claims != null && hard_claims.size() > 0 && hard_claims.get(0).borrow_reward_indexes != null) {
            for (BorrowRewardIndex index: hard_claims.get(0).borrow_reward_indexes) {
                if (index.collateral_type.equals(denom)) {
                    if (index.reward_indexes != null && index.reward_indexes.size() > 0) {
                        result = new BigDecimal(index.reward_indexes.get(0).reward_factor);
                    }
                }
            }
        }
        return result;
    }


    public int getHardPoolRewardCnt() {
        int result = 0;
        if (hard_claims != null) {
            result = hard_claims.size();
        }
        return result;
    }

    public BigDecimal getHardPoolHardRewardAmount() {
        BigDecimal result = BigDecimal.ZERO;
        for (HardClaim reward : hard_claims) {
            if (reward.base_claim.reward != null) {
                for (Coin coin: reward.base_claim.reward) {
                    if (coin.denom.equals(TOKEN_HARD)) {
                        result = result.add(new BigDecimal(coin.amount));
                    }
                }
            }
        }
        return result;
    }

    public BigDecimal getHardPoolKavaRewardAmount() {
        BigDecimal result = BigDecimal.ZERO;
        for (HardClaim reward : hard_claims) {
            if (reward.base_claim.reward != null) {
                for (Coin coin: reward.base_claim.reward) {
                    if (coin.denom.equals(TOKEN_KAVA)) {
                        result = result.add(new BigDecimal(coin.amount));
                    }
                }
            }
        }
        return result;
    }


    public int getMintingRewardCnt() {
        int result = 0;
        if (usdx_minting_claims != null) {
            result = usdx_minting_claims.size();
        }
        return result;
    }

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

        @SerializedName("supply_reward_indexes")
        public ArrayList<SupplyRewardIndex> supply_reward_indexes;

        @SerializedName("borrow_reward_indexes")
        public ArrayList<BorrowRewardIndex> borrow_reward_indexes;

    }

    public class HardBaseClaim {
        @SerializedName("owner")
        public String owner;

        @SerializedName("reward")
        public ArrayList<Coin> reward;

    }

    public class SupplyRewardIndex {
        @SerializedName("collateral_type")
        public String collateral_type;

        @SerializedName("reward_indexes")
        public ArrayList<RewardIndex> reward_indexes;

    }

    public class BorrowRewardIndex {
        @SerializedName("collateral_type")
        public String collateral_type;

        @SerializedName("reward_indexes")
        public ArrayList<RewardIndex> reward_indexes;

    }

    public class RewardIndex {
        @SerializedName("collateral_type")
        public String collateral_type;

        @SerializedName("reward_factor")
        public String reward_factor;

    }






    public class UsdxMintingClaim {
        @SerializedName("base_claim")
        public UsdxBaseClaim base_claim;

    }

    public class UsdxBaseClaim {
        @SerializedName("owner")
        public String owner;

        @SerializedName("reward")
        public Coin reward;

    }
}
