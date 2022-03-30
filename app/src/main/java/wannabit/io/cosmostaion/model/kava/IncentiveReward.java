package wannabit.io.cosmostaion.model.kava;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Coin;

public class IncentiveReward {
    @SerializedName("hard_claims")
    public ArrayList<HardClaim> hard_claims;

    @SerializedName("usdx_minting_claims")
    public ArrayList<UsdxMintingClaim> usdx_minting_claims;

    @SerializedName("delegator_claims")
    public ArrayList<DelegatorClaim> delegator_claims;

    @SerializedName("swap_claims")
    public ArrayList<SwapClaim> swap_claims;

    public BigDecimal getSupplyRewardFactor(String denom) {
        BigDecimal result = BigDecimal.ZERO;
        if (hard_claims != null && hard_claims.size() > 0 && hard_claims.get(0).supply_reward_indexes != null) {
            for (SupplyRewardIndex index : hard_claims.get(0).supply_reward_indexes) {
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
            for (BorrowRewardIndex index : hard_claims.get(0).borrow_reward_indexes) {
                if (index.collateral_type.equals(denom)) {
                    if (index.reward_indexes != null && index.reward_indexes.size() > 0) {
                        result = new BigDecimal(index.reward_indexes.get(0).reward_factor);
                    }
                }
            }
        }
        return result;
    }


    public BigDecimal getHardPoolRewardCnt() {
        BigDecimal result = BigDecimal.ZERO;
        if (hard_claims != null) {
            result = new BigDecimal(hard_claims.size());
        }
        return result;
    }

    public BigDecimal getMintingRewardCnt() {
        BigDecimal result = BigDecimal.ZERO;
        if (usdx_minting_claims != null) {
            result = new BigDecimal(usdx_minting_claims.size());
        }
        return result;
    }

    public BigDecimal getHardPoolHardRewardAmount() {
        BigDecimal result = BigDecimal.ZERO;
        for (HardClaim reward : hard_claims) {
            if (reward.base_claim.reward != null) {
                for (Coin coin : reward.base_claim.reward) {
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
                for (Coin coin : reward.base_claim.reward) {
                    if (coin.denom.equals(TOKEN_KAVA)) {
                        result = result.add(new BigDecimal(coin.amount));
                    }
                }
            }
        }
        return result;
    }

    public BigDecimal getHardPoolRewardAmount(String denom) {
        BigDecimal result = BigDecimal.ZERO;
        if (hard_claims != null) {
            for (HardClaim reward : hard_claims) {
                if (reward.base_claim.reward != null) {
                    for (Coin coin : reward.base_claim.reward) {
                        if (coin.denom.equalsIgnoreCase(denom)) {
                            result = result.add(new BigDecimal(coin.amount));
                        }
                    }
                }
            }
        }
        return result;
    }

    public BigDecimal getMintingRewardAmount() {
        BigDecimal result = BigDecimal.ZERO;
        if (usdx_minting_claims != null) {
            for (UsdxMintingClaim reward : usdx_minting_claims) {
                if (reward.base_claim.reward != null) {
                    if (reward.base_claim.reward.denom.equals(TOKEN_KAVA)) {
                        result = result.add(new BigDecimal(reward.base_claim.reward.amount));
                    }
                }
            }
        }
        return result;
    }

    public ArrayList<String> getHardRewardDenoms() {
        ArrayList<String> result = new ArrayList<>();
        if (hard_claims == null) {
            return result;
        }
        for (HardClaim hardClaim : hard_claims) {
            for (Coin coin : hardClaim.base_claim.reward) {
                if (!result.contains(coin.denom)) {
                    result.add(coin.denom);
                }
            }
        }
        return result;
    }

    public BigDecimal getDelegatorKavaRewardAmount(String denom) {
        BigDecimal result = BigDecimal.ZERO;
        if (delegator_claims != null) {
            for (DelegatorClaim reward : delegator_claims) {
                if (reward.base_claim.reward != null) {
                    for (Coin coin : reward.base_claim.reward) {
                        if (coin.denom.equalsIgnoreCase(denom)) {
                            result = result.add(new BigDecimal(coin.amount));
                        }
                    }
                }
            }
        }
        return result;
    }

    public ArrayList<String> getDelegatorRewardDenoms() {
        ArrayList<String> result = new ArrayList<>();
        if (delegator_claims == null) {
            return result;
        }
        for (DelegatorClaim delegatorClaim : delegator_claims) {
            for (Coin coin : delegatorClaim.base_claim.reward) {
                if (!result.contains(coin.denom)) {
                    result.add(coin.denom);
                }
            }
        }
        return result;
    }

    public BigDecimal getSwapKavaRewardAmount(String denom) {
        BigDecimal result = BigDecimal.ZERO;
        if (swap_claims != null) {
            for (SwapClaim reward : swap_claims) {
                if (reward.base_claim.reward != null) {
                    for (Coin coin : reward.base_claim.reward) {
                        if (coin.denom.equalsIgnoreCase(denom)) {
                            result = result.add(new BigDecimal(coin.amount));
                        }
                    }
                }
            }
        }
        return result;
    }

    public ArrayList<String> getSwapRewardDenoms() {
        ArrayList<String> result = new ArrayList<>();
        if (swap_claims == null) {
            return result;
        }
        for (SwapClaim swapClaim : swap_claims) {
            for (Coin coin : swapClaim.base_claim.reward) {
                if (!result.contains(coin.denom)) {
                    result.add(coin.denom);
                }
            }
        }
        return result;
    }

    public BigDecimal getRewardSum(String denom) {
        if (denom.equals(TOKEN_KAVA)) {
            return getHardPoolRewardAmount(denom).add(getMintingRewardAmount()).add(getDelegatorKavaRewardAmount(denom)).add(getSwapKavaRewardAmount(denom));
        } else {
            return getHardPoolRewardAmount(denom).add(getDelegatorKavaRewardAmount(denom)).add(getSwapKavaRewardAmount(denom));
        }
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

    public class DelegatorClaim {
        @SerializedName("base_claim")
        public DelegatorBaseClaim base_claim;
    }

    public class DelegatorBaseClaim {
        @SerializedName("owner")
        public String owner;

        @SerializedName("reward")
        public ArrayList<Coin> reward;

    }

    public class SwapClaim {
        @SerializedName("base_claim")
        public SwapBaseClaim base_claim;
    }

    public class SwapBaseClaim {
        @SerializedName("owner")
        public String owner;

        @SerializedName("reward")
        public ArrayList<Coin> reward;
    }
}
