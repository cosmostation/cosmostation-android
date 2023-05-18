package wannabit.io.cosmostaion.model.kava;

import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import wannabit.io.cosmostaion.model.type.Coin;

public class IncentiveReward {
    @SerializedName("hard_liquidity_provider_claims")
    public ArrayList<HardClaim> hard_claims;

    @SerializedName("usdx_minting_claims")
    public ArrayList<UsdxMintingClaim> usdx_minting_claims;

    @SerializedName("delegator_claims")
    public ArrayList<DelegatorClaim> delegator_claims;

    @SerializedName("swap_claims")
    public ArrayList<SwapClaim> swap_claims;

    @SerializedName("earn_claims")
    public ArrayList<EarnClaim> earn_claims;

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

    public ArrayList<Coin> getAllIncentives() {
        ArrayList<Coin> total = Lists.newArrayList();
        if (hard_claims != null && hard_claims.size() > 0) {
            addRewards(total, getRewardListFromArray(hard_claims.stream().map(item -> item.base_claim.reward)));
        }
        if (swap_claims != null && swap_claims.size() > 0) {
            addRewards(total, getRewardListFromArray(swap_claims.stream().map(item -> item.base_claim.reward)));
        }
        if (earn_claims != null && earn_claims.size() > 0) {
            addRewards(total, getRewardListFromArray(earn_claims.stream().map(item -> item.base_claim.reward)));
        }
        if (usdx_minting_claims != null && usdx_minting_claims.size() > 0) {
            addRewards(total, getRewardList(usdx_minting_claims.stream().map(item -> item.base_claim.reward)));
        }
        if (delegator_claims != null && delegator_claims.size() > 0) {
            addRewards(total, getRewardListFromArray(delegator_claims.stream().map(item -> item.base_claim.reward)));
        }
        return total;
    }

    private List<Coin> getRewardListFromArray(Stream<ArrayList<Coin>> arrayStream) {
        return getRewardList(arrayStream.flatMap(Collection::stream));
    }

    private List<Coin> getRewardList(Stream<Coin> stream) {
        return stream.collect(Collectors.toList());
    }

    private void addRewards(List<Coin> result, List<Coin> coins) {
        for (Coin coin : coins) {
            BigDecimal amount = new BigDecimal(coin.amount);
            if (amount.compareTo(BigDecimal.ZERO) > 0) {
                Optional<Coin> matchedCoin = result.stream().filter(item -> item.denom.equalsIgnoreCase(coin.denom)).findFirst();
                if (matchedCoin.isPresent()) {
                    matchedCoin.get().amount = new BigDecimal(matchedCoin.get().amount).add(amount).toString();
                } else {
                    result.add(new Coin(coin.denom, coin.amount));
                }
            }
        }
    }

    public BigDecimal getMintingRewardAmount() {
        BigDecimal result = BigDecimal.ZERO;
        if (usdx_minting_claims != null) {
            for (UsdxMintingClaim reward : usdx_minting_claims) {
                if (reward.base_claim.reward != null) {
                    result = result.add(new BigDecimal(reward.base_claim.reward.amount));
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

    public ArrayList<String> getEarnRewardDenoms() {
        ArrayList<String> result = new ArrayList<>();
        if (earn_claims == null) {
            return result;
        }
        for (EarnClaim earnClaim : earn_claims) {
            for (Coin coin : earnClaim.base_claim.reward) {
                if (!result.contains(coin.denom)) {
                    result.add(coin.denom);
                }
            }
        }
        return result;
    }

    public BigDecimal getIncentiveAmount(String denom) {
        Optional<Coin> coin = getAllIncentives().stream().filter(item -> item.denom.equalsIgnoreCase(denom)).findFirst();
        if (coin.isPresent()){
            return new BigDecimal(coin.get().amount);
        } else {
            return BigDecimal.ZERO;
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

    public class EarnClaim {
        @SerializedName("base_claim")
        public EarnBaseClaim base_claim;
    }

    public class EarnBaseClaim {
        @SerializedName("owner")
        public String owner;

        @SerializedName("reward")
        public ArrayList<Coin> reward;
    }
}
