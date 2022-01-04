package wannabit.io.cosmostaion.dao;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.EMONEY_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.STARGAZE_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;


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

        @SerializedName("osmosis_minting_params")
        public OsmosisMingtingParams osmosisMingtingParams;

        @SerializedName("minting_epoch_provisions")
        public MintingEpochProvision mintingEpochProvision;

        @SerializedName("enabled_pools")
        public ArrayList<Integer> mEnabledPools;

        @SerializedName("emoney_minting_inflation")
        public EmoneyInflations mEmoneyInflations;

        @SerializedName("active_validators")
        public ActiveValidators activeValidators;

        @SerializedName("starname_domains")
        public ArrayList<String> mStarnameDomains;

        @SerializedName("sifchain_token_registry")
        public SifTokenRegistry mSifTokenRegistry;

        @SerializedName("gdex_status")
        public ArrayList<GdexStatus> mGdexStatus;

        @SerializedName("stargaze_minting_params")
        public StargazeMintingParams stargazeMintingParams;

        @SerializedName("swap_enabled")
        public boolean swap_enabled;


        public BigDecimal getMintInflation(BaseChain baseChain) {
            if (baseChain.equals(BaseChain.IRIS_MAIN) || baseChain.equals(BaseChain.IRIS_TEST)) {
                return new BigDecimal(mMintParams.inflation);

            } else if (baseChain.equals(BaseChain.OSMOSIS_MAIN)) {
                BigDecimal epochProvisions = new BigDecimal(mintingEpochProvision.epoch_provisions);
                BigDecimal epochPeriods = new BigDecimal(osmosisMingtingParams.params.reduction_period_in_epochs);
                BigDecimal osmoSupply = getMainSupply(baseChain);
                return epochProvisions.multiply(epochPeriods).divide(osmoSupply, 18, RoundingMode.DOWN);
            } else if (baseChain.equals(EMONEY_MAIN)) {
                for (Asset asset: mEmoneyInflations.mEmoneyInflation.assets) {
                    if (asset.denom.equalsIgnoreCase(BaseConstant.TOKEN_NGM)) {
                        return new BigDecimal(asset.inflation);
                    }
                }
                return new BigDecimal(mEmoneyInflations.mEmoneyInflation.assets.get(0).inflation);
            } else if (baseChain.equals(STARGAZE_MAIN)) {
                BigDecimal initialProvision = new BigDecimal(stargazeMintingParams.params.initial_annual_provisions);
                return initialProvision.divide(getMainSupply(baseChain), 18, RoundingMode.DOWN);
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

        public BigDecimal getTax(BaseChain baseChain) {
            if (isGRPC(baseChain)) {
                return new BigDecimal(mDistributionParams.params.community_tax);
            } else {
                return new BigDecimal(mDistributionParams.community_tax);
            }
        }

        public BigDecimal getMainSupply(BaseChain baseChain) {
            String denom = getMainDenom(baseChain);
            for (Coin coin : getSupplys()) {
                if (coin.denom.equals(denom)) {
                    return new BigDecimal(coin.amount);
                }
            }
            return BigDecimal.ZERO;
        }

        public BigDecimal getApr(BaseChain baseChain) {
            BigDecimal inflation = getMintInflation(baseChain);
            BigDecimal calTax = BigDecimal.ONE.subtract(getTax(baseChain));
            if (getMainSupply(baseChain) == null || getMainSupply(baseChain).equals(BigDecimal.ZERO)) {
                return BigDecimal.ZERO;
            } else {
                BigDecimal bondingRate = getBondedAmount(baseChain).divide(getMainSupply(baseChain), 6, RoundingMode.DOWN);
                if (bondingRate.equals(BigDecimal.ZERO)) return BigDecimal.ZERO;
                if (baseChain.equals(BaseChain.OSMOSIS_MAIN)) {
                    BigDecimal stakingDistribution = new BigDecimal(osmosisMingtingParams.params.distributionProportions.staking);
                    return inflation.multiply(calTax).multiply(stakingDistribution).divide(bondingRate, 6, RoundingMode.DOWN);
                } else if (baseChain.equals(STARGAZE_MAIN)) {
                    BigDecimal reductionFactor = BigDecimal.ONE.subtract(new BigDecimal(stargazeMintingParams.params.reduction_factor));
                    return inflation.multiply(calTax).multiply(reductionFactor).divide(bondingRate, 6, RoundingMode.DOWN);
                } else {
                    return inflation.multiply(calTax).divide(bondingRate, 6, RoundingMode.DOWN);
                }
            }
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
                if (mMintParams != null && mMintParams.params!= null && mMintParams.params.blocks_per_year!= null) {
                    return new BigDecimal(mMintParams.params.blocks_per_year);
                } else {
                    return BigDecimal.ZERO;
                }
            } else {
                if (mMintParams != null && mMintParams.blocks_per_year != null) {
                    return new BigDecimal(mMintParams.blocks_per_year);
                } else {
                    return BigDecimal.ZERO;
                }
            }
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

        public BigDecimal getQuorum(BaseChain baseChain) {
            if (baseChain.equals(CERTIK_MAIN) || baseChain.equals(CERTIK_TEST)) {
                return new BigDecimal(govTallyings.tallyparams.defaultTally.quorum).movePointRight(2);
            } else if (isGRPC(baseChain)) {
                return new BigDecimal(govTallyings.tallyparams.quorum).movePointRight(2);
            } else {
                return new BigDecimal(govTallyings.quorum).movePointRight(2);
            }
        }

        public boolean isPoolEnabled(long id) {
            return mEnabledPools.contains(Integer.parseInt("" + id));
        }

        public boolean isOracleEnable(String valOpAddress) {
            if (activeValidators == null) { return true; }
            for (ActiveValidators.ActiveValidator.Oracle oracle : activeValidators.activeValidator.oracles) {
                if (oracle.address.equalsIgnoreCase(valOpAddress)) {
                    return true;
                }
            }
            return false;
        }

        public ArrayList<SifTokenRegistry.Registry.Entry> getSifToken() {
            return mSifTokenRegistry.registry.entries;
        }

        public ArrayList<GdexStatus> getmGdexList() {
            return mGdexStatus;
        }

        public int getUnbonding(BaseChain baseChain) {
            int result = 0;
            String unbondingTime = null;
            if (isGRPC(baseChain)) {
                unbondingTime = mStakingParams.params.unbonding_time;
                result = Integer.parseInt(unbondingTime.split("s")[0]) / 60 / 60 / 24;
            } else {
                unbondingTime = mStakingParams.unbonding_time;
                result = Integer.parseInt(String.valueOf(Long.parseLong(unbondingTime) / 1000000000 / 60 / 60 / 24));
            }
            return result;
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
        public boolean withdraw_addr_enabled;

        public class DistributionParam {
            @SerializedName("community_tax")
            public String community_tax;

            @SerializedName("base_proposer_reward")
            public String base_proposer_reward;

            @SerializedName("bonus_proposer_reward")
            public String bonus_proposer_reward;

            @SerializedName("withdraw_addr_enabled")
            public boolean withdraw_addr_enabled;
        }
    }

    public class Supply {
        @SerializedName("supply")
        public ArrayList<Coin> supply;
    }

    public class GovTallyings {
        @SerializedName("tally_params")
        public GovTallying tallyparams;

        @SerializedName("quorum")
        public String quorum;

        @SerializedName("veto")
        public String veto;

        @SerializedName("threshold")
        public String threshold;


        public class GovTallying {
            @SerializedName("quorum")
            public String quorum;

            @SerializedName("threshold")
            public String threshold;

            @SerializedName("veto_threshold")
            public String veto_threshold;

            @SerializedName("default_tally")
            public DefaultTally defaultTally;
        }

        public class DefaultTally {
            @SerializedName("quorum")
            public String quorum;

            @SerializedName("threshold")
            public String threshold;

            @SerializedName("veto_threshold")
            public String veto;
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

    public class OsmosisMingtingParams {
        @SerializedName("params")
        public OsmosisMingtingParam params;

        public class OsmosisMingtingParam {
            @SerializedName("mint_denom")
            public String mint_denom;

            @SerializedName("epoch_identifier")
            public String epoch_identifier;

            @SerializedName("reduction_factor")
            public String reduction_factor;

            @SerializedName("genesis_epoch_provisions")
            public String genesis_epoch_provisions;

            @SerializedName("reduction_period_in_epochs")
            public String reduction_period_in_epochs;

            @SerializedName("distribution_proportions")
            public DistributionProportions distributionProportions;
        }
    }

    public class DistributionProportions {
        @SerializedName("staking")
        public String staking;

        @SerializedName("community_pool")
        public String community_pool;

        @SerializedName("pool_incentives")
        public String pool_incentives;

        @SerializedName("developer_rewards")
        public String developer_rewards;
    }

    public class MintingEpochProvision {
        @SerializedName("epoch_provisions")
        public String epoch_provisions;
    }

    public class EmoneyInflations {
        @SerializedName("result")
        public EmoneyInflation mEmoneyInflation;
    }

    public class EmoneyInflation {
        @SerializedName("assets")
        public ArrayList<Asset> assets;
    }

    public class Asset {
        @SerializedName("accum")
        public String accum;

        @SerializedName("denom")
        public String denom;

        @SerializedName("inflation")
        public String inflation;
    }

    public class ActiveValidators {
        @SerializedName("result")
        public ActiveValidator activeValidator;

        public class ActiveValidator {
            @SerializedName("result")
            public ArrayList<Oracle> oracles;

            public class Oracle {
                @SerializedName("power")
                public String power;

                @SerializedName("address")
                public String address;
            }
        }
    }

    public class SifTokenRegistry {
        @SerializedName("registry")
        public Registry registry;

        public class Registry {
            @SerializedName("entries")
            public ArrayList<Entry> entries;

            public class Entry {
                @SerializedName("denom")
                public String denom;

                @SerializedName("decimals")
                public int decimal;

                @SerializedName("base_denom")
                public String basedenom;
            }
        }
    }

    public class GdexStatus {
        @SerializedName("id")
        public String id;

        @SerializedName("reserve_address")
        public String reserve_address;

        @SerializedName("token_pair")
        public ArrayList<Coin> tokenPairs;

        @SerializedName("pool_token")
        public String pool_token;
    }

    public class StargazeMintingParams {
        @SerializedName("params")
        public StargazeMintingParam params;

        public class StargazeMintingParam {
            @SerializedName("mint_denom")
            public String mint_denom;

            @SerializedName("start_time")
            public String start_time;

            @SerializedName("blocks_per_year")
            public String blocks_per_year;

            @SerializedName("reduction_factor")
            public String reduction_factor;

            @SerializedName("initial_annual_provisions")
            public String initial_annual_provisions;
        }
    }
}



