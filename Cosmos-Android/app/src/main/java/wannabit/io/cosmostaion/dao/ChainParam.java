package wannabit.io.cosmostaion.dao;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;
import static wannabit.io.cosmostaion.base.BaseConstant.YEAR_SEC;


public class ChainParam {
    @SerializedName("chain_id")
    public String chain_id;
    
    @SerializedName("Params")
    public Params params;

    public class Params {
        @SerializedName("iris_tokens")
        public ArrayList<IrisToken> mIrisTokens;

        @SerializedName("ibc_params")
        public IbcParams mIbcParams;

        @SerializedName("minting_params")
        public MintingParams mMintParams;

        @SerializedName("minting_inflation")
        public Object mMintInflations;

        @SerializedName("minting_annual_provisions")
        public Object mMintProvisions;

        @SerializedName("staking_pool")
        public StakingPools mStakingpools;

        @SerializedName("staking_params")
        public StakingParams mStakingParams;

        @SerializedName("distribution_params")
        public DistributionParams mDistributionParams;

        @SerializedName("supply")
        public Object supply;

        @SerializedName("gov_tallying")
        public GovTallyings govTallyings;


        public BigDecimal getMintInflation(BaseChain baseChain) {
            if (baseChain.equals(BaseChain.IRIS_MAIN) || baseChain.equals(BaseChain.IRIS_TEST)) {
                return new BigDecimal(mMintParams.inflation);

            } else {
                try {
                    MintInflation temp = new Gson().fromJson(new Gson().toJson(mMintInflations), MintInflation.class);
                    return new BigDecimal(temp.mInflation);
                } catch (Exception e) { }

                try {
                    String temp = new Gson().fromJson(new Gson().toJson(mMintInflations), String.class);
                    return new BigDecimal(temp);
                } catch (Exception e) { }
            }
            return BigDecimal.ZERO;
        }

        public BigDecimal getDpInflation(BaseChain baseChain) {
            return getMintInflation(baseChain).movePointRight(2);
        }

        public BigDecimal getBondedAmount(BaseChain baseChain) {
            if (isGRPC(baseChain)) {
                return new BigDecimal(mStakingpools.pool.bonded_tokens);
            } else {
                return new BigDecimal(mStakingpools.bonded_tokens);
            }
        }

        public BigDecimal getTax (BaseChain baseChain) {
            if (isGRPC(baseChain)) {
                return new BigDecimal(mDistributionParams.params.community_tax);
            } else {
                return new BigDecimal(mDistributionParams.community_tax);
            }
        }

        public BigDecimal getMainSupply(BaseChain baseChain) {
            String denom = getMainDenom(baseChain);
            for (Coin coin:getSupplys()) {
                if (coin.denom.equals(denom)){
                    return new BigDecimal(coin.amount);
                }
            }
            return BigDecimal.ZERO;
        }

        public BigDecimal getApr(BaseChain baseChain) {
            BigDecimal inflation = getMintInflation(baseChain);
            BigDecimal calTax = BigDecimal.ONE.subtract(getTax(baseChain));
            if (getMainSupply(baseChain).equals(BigDecimal.ZERO)) return BigDecimal.ZERO;
            BigDecimal bondingRate = getBondedAmount(baseChain).divide(getMainSupply(baseChain), 6, RoundingMode.DOWN);
            if (bondingRate.equals(BigDecimal.ZERO)) return BigDecimal.ZERO;
            return inflation.multiply(calTax).divide(bondingRate, 6, RoundingMode.DOWN);
        }

        public BigDecimal getDpApr(BaseChain baseChain) {
            return getApr(baseChain).movePointRight(2);
        }

        public BigDecimal getRealApr(BaseChain baseChain) {
            if (WUtil.getRealBlockPerYear(baseChain) == BigDecimal.ZERO || getBlockPerYear(baseChain) == BigDecimal.ZERO) {
                return BigDecimal.ZERO;
            }
            return getApr(baseChain).multiply(WUtil.getRealBlockPerYear(baseChain)).divide(getBlockPerYear(baseChain), 6, RoundingMode.DOWN);
        }

        public BigDecimal getDpRealApr(BaseChain baseChain) {
            if (getRealApr(baseChain) == BigDecimal.ZERO) { return BigDecimal.ZERO; }
            return getRealApr(baseChain).movePointRight(2);
        }

        public BigDecimal getBlockPerYear(BaseChain baseChain) {
            if (isGRPC(baseChain)) {
                if (baseChain.equals(BaseChain.IRIS_MAIN) || baseChain.equals(BaseChain.IRIS_TEST)) {
                    return BigDecimal.ZERO;
                }
                return new BigDecimal(mMintParams.params.blocks_per_year);

            } else if (baseChain.equals(SIF_MAIN)) {
                return BigDecimal.ZERO;

            }
            return new BigDecimal(mMintParams.blocks_per_year);
        }


        public ArrayList<Coin> getSupplys() {
            ArrayList<Coin> result = new ArrayList<>();
            try {
                Supply temp = new Gson().fromJson(new Gson().toJson(supply), Supply.class);
                result.addAll(temp.supply);
            } catch (Exception e) { }
            try {
                result = new Gson().fromJson(new Gson().toJson(supply), new TypeToken<List<Coin>>(){}.getType());
            } catch (Exception e) { }
            return result;
        }

        public String getMainDenom(BaseChain baseChain) {
            if (isGRPC(baseChain)) {
                return mStakingParams.params.bond_denom;
            } else {
                return mStakingParams.bond_denom;
            }
        }
    }



    public class IbcParams {
        @SerializedName("params")
        public IbcParam params;

        public class IbcParam {
            @SerializedName("send_enabled")
            public boolean send_enabled;

            @SerializedName("receive_enabled")
            public boolean receive_enabled;
        }
    }

    public class MintingParams {
        @SerializedName("params")
        public MintingParam params;

        @SerializedName("inflation")
        public String inflation;

        @SerializedName("mint_denom")
        public String mint_denom;

        @SerializedName("goal_bonded")
        public String goal_bonded;

        @SerializedName("blocks_per_year")
        public String blocks_per_year;

        @SerializedName("inflation_min")
        public String inflation_min;

        @SerializedName("inflation_max")
        public String inflation_max;

        @SerializedName("inflation_rate_change")
        public String inflation_rate_change;

        public class MintingParam {
            @SerializedName("mint_denom")
            public String mint_denom;

            @SerializedName("goal_bonded")
            public String goal_bonded;

            @SerializedName("blocks_per_year")
            public String blocks_per_year;

            @SerializedName("inflation_min")
            public String inflation_min;

            @SerializedName("inflation_max")
            public String inflation_max;

            @SerializedName("inflation_rate_change")
            public String inflation_rate_change;
        }
    }

    public class MintInflation {
        @SerializedName("inflation")
        public String mInflation;
    }

    public class MintProvision {
        @SerializedName("annual_provisions")
        public String mProvision;
    }

    public class StakingPools {
        @SerializedName("pool")
        public StakingPool pool;

        @SerializedName("bonded_tokens")
        public String bonded_tokens;

        @SerializedName("not_bonded_tokens")
        public String not_bonded_tokens;

        public class StakingPool {
            @SerializedName("bonded_tokens")
            public String bonded_tokens;

            @SerializedName("not_bonded_tokens")
            public String not_bonded_tokens;
        }
    }

    public class StakingParams {
        @SerializedName("params")
        public StakingParam params;

        @SerializedName("bond_denom")
        public String bond_denom;

        @SerializedName("max_entries")
        public BigDecimal max_entries;

        @SerializedName("max_validators")
        public BigDecimal max_validators;

        @SerializedName("unbonding_time")
        public String unbonding_time;

        @SerializedName("historical_entries")
        public BigDecimal historical_entries;

        public class StakingParam {
            @SerializedName("bond_denom")
            public String bond_denom;

            @SerializedName("max_entries")
            public BigDecimal max_entries;

            @SerializedName("max_validators")
            public BigDecimal max_validators;

            @SerializedName("unbonding_time")
            public String unbonding_time;

            @SerializedName("historical_entries")
            public BigDecimal historical_entries;
        }
    }

    public class DistributionParams {
        @SerializedName("params")
        public DistributionParam params;

        @SerializedName("community_tax")
        public String community_tax;

        @SerializedName("base_proposer_reward")
        public String base_proposer_reward;

        @SerializedName("bonus_proposer_reward")
        public String bonus_proposer_reward;

        @SerializedName("withdraw_addr_enabled")
        public Boolean withdraw_addr_enabled;

        public class DistributionParam {
            @SerializedName("community_tax")
            public String community_tax;

            @SerializedName("base_proposer_reward")
            public String base_proposer_reward;

            @SerializedName("bonus_proposer_reward")
            public String bonus_proposer_reward;

            @SerializedName("withdraw_addr_enabled")
            public Boolean withdraw_addr_enabled;
        }
    }

    public class Supply {
        @SerializedName("supply")
        public ArrayList<Coin> supply;
    }

    public class GovTallyings {
        @SerializedName("DefaultTally")
        public DefaultTally defaultTally;

        @SerializedName("tally_params")
        public GovTallying tallyparams;

        @SerializedName("veto")
        public String veto;

        @SerializedName("quorum")
        public String quorum;

        @SerializedName("threshold")
        public String threshold;

        public class GovTallying {
            @SerializedName("quorum")
            public String quorum;

            @SerializedName("threshold")
            public String threshold;

            @SerializedName("veto_threshold")
            public String veto_threshold;
        }

        public class DefaultTally {
            @SerializedName("veto")
            public String veto;

            @SerializedName("quorum")
            public String quorum;

            @SerializedName("threshold")
            public String threshold;
        }

    }

    public class IrisToken {
        @SerializedName("type")
        public String type;

        @SerializedName("value")
        public IrisTokenValue value;

        public class IrisTokenValue {
            @SerializedName("name")
            public String name;

            @SerializedName("owner")
            public String owner;

            @SerializedName("scale")
            public String scale;

            @SerializedName("symbol")
            public String symbol;

            @SerializedName("min_unit")
            public String min_unit;

            @SerializedName("mintable")
            public boolean mintable;

            @SerializedName("max_supply")
            public String max_supply;

            @SerializedName("initial_supply")
            public String inital_supply;
        }

    }
}


