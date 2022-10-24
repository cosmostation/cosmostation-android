package wannabit.io.cosmostaion.dao;

import static wannabit.io.cosmostaion.base.BaseChain.AXELAR_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CRESCENT_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.EMONEY_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.EVMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.STARGAZE_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.TERITORI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;
import static wannabit.io.cosmostaion.base.BaseConstant.YEAR_SEC;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;


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

        @SerializedName("stargaze_minting_params")
        public StargazeMintingParams mStargazeMintingParams;

        @SerializedName("stargaze_alloc_params")
        public StargazeAllocParams mStargazeAllocParams;

        @SerializedName("inflation_params")
        public EvmosInflationParams mEvmosInflationParams;

        @SerializedName("epoch_mint_provision")
        public EvmosEpochMintProvision mEvmosEpochMintProvision;

        @SerializedName("swap_enabled")
        public boolean swap_enabled;

        @SerializedName("crescent_minting_params")
        public CrescentMintingParams mCrescentMintingParams;

        @SerializedName("crescent_budgets")
        public CrescentBudgets mCrescentBudgets;

        @SerializedName("teritori_minting_params")
        public TeritoriMintingParams mTeritoriMintingParams;


        public BigDecimal getMintInflation(BaseChain baseChain) {
            if (baseChain.equals(BaseChain.IRIS_MAIN)) {
                if (mMintParams != null && mMintParams.inflation != null) {
                    return new BigDecimal(mMintParams.inflation);
                }

            } else if (baseChain.equals(BaseChain.OSMOSIS_MAIN)) {
                BigDecimal epochProvisions = new BigDecimal(mintingEpochProvision.epoch_provisions);
                BigDecimal epochPeriods = new BigDecimal(osmosisMingtingParams.params.reduction_period_in_epochs);
                BigDecimal osmoSupply = getMainSupply(baseChain);
                return epochProvisions.multiply(epochPeriods).divide(osmoSupply, 18, RoundingMode.DOWN);
            } else if (baseChain.equals(EMONEY_MAIN)) {
                for (Asset asset: mEmoneyInflations.mEmoneyInflation.assets) {
                    if (asset.denom.equalsIgnoreCase(ChainFactory.getChain(EMONEY_MAIN).mainDenom())) {
                        return new BigDecimal(asset.inflation);
                    }
                }
                return new BigDecimal(mEmoneyInflations.mEmoneyInflation.assets.get(0).inflation);
            } else if (baseChain.equals(STARGAZE_MAIN)) {
                BigDecimal initialProvision = new BigDecimal(mStargazeMintingParams.params.initial_annual_provisions);
                return initialProvision.divide(getMainSupply(baseChain), 18, RoundingMode.DOWN);
            } else if (baseChain.equals(EVMOS_MAIN)) {
                if (!mEvmosInflationParams.params.enable_inflation) {
                    return BigDecimal.ZERO;
                }
                BigDecimal annualProvisions = new BigDecimal(mEvmosEpochMintProvision.mEpochMintProvision.amount).multiply(new BigDecimal("365"));
                BigDecimal evmosSupply = getMainSupply(baseChain).subtract(new BigDecimal("200000000000000000000000000"));
                return annualProvisions.divide(evmosSupply, 18, RoundingMode.DOWN);
            } else if (baseChain.equals(CRESCENT_MAIN)) {
                long now = Calendar.getInstance().getTime().getTime();
                BigDecimal genesisSupply = new BigDecimal("200000000000000");
                BigDecimal thisInflation = getCurrentInflationAmount(baseChain);
                for (Schedules schedules: mCrescentMintingParams.mParams.mSchedules) {
                    if (schedules.getStart_time() < now && schedules.getEnd_time() < now) {
                        genesisSupply = genesisSupply.add(schedules.getAmount());
                    }
                }
                return thisInflation.divide(genesisSupply, 18, RoundingMode.UP);
            } else if (baseChain.equals(AXELAR_MAIN)) {
                return new BigDecimal("0.150000000000000000");
            } else if (baseChain.equals(TERITORI_MAIN)) {
                BigDecimal inflationNum = new BigDecimal(mTeritoriMintingParams.params.reduction_period_in_blocks).subtract(new BigDecimal(mTeritoriMintingParams.params.minting_rewards_distribution_start_block));
                return inflationNum.multiply(new BigDecimal(mTeritoriMintingParams.params.genesis_block_provisions)).divide(getMainSupply(baseChain), 18, RoundingMode.DOWN);
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

        public BigDecimal getAnnualProvision() {
            try {
                MintProvision temp = new Gson().fromJson(new Gson().toJson(mMintProvisions), MintProvision.class);
                return new BigDecimal(temp.mProvision);
            } catch (Exception e) { }
            return BigDecimal.ZERO;
        }

        public BigDecimal getBondedAmount(BaseChain baseChain) {
            if (isGRPC(baseChain)) {
                return new BigDecimal(mStakingpools.pool.bonded_tokens);
            } else {
                return new BigDecimal(mStakingpools.bonded_tokens);
            }
        }

        public BigDecimal getTax(BaseChain baseChain) {
            if (mDistributionParams != null) {
                if (isGRPC(baseChain)) {
                    return new BigDecimal(mDistributionParams.params.community_tax);
                } else {
                    return new BigDecimal(mDistributionParams.community_tax);
                }
            } else {
                return BigDecimal.ZERO;
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

        // Crescent
        public BigDecimal getCurrentInflationAmount(BaseChain baseChain) {
            BigDecimal inflationAmount = BigDecimal.ZERO;
            if (baseChain.equals(CRESCENT_MAIN)) {
                long now = Calendar.getInstance().getTime().getTime();
                for (Schedules schedules: mCrescentMintingParams.mParams.mSchedules) {
                    if (schedules.getStart_time() < now && schedules.getEnd_time() > now) {
                        inflationAmount = schedules.getAmount();
                    }
                }
            }
            return inflationAmount;
        }

        public BigDecimal getBudgetRate(BaseChain baseChain) {
            BigDecimal budgetRate = BigDecimal.ZERO;
            if (baseChain.equals(CRESCENT_MAIN)) {
                for (Budgets budgets: mCrescentBudgets.mBudgets) {
                    if (budgets.mBudget.name.equalsIgnoreCase("budget-ecosystem-incentive") || budgets.mBudget.name.equalsIgnoreCase("budget-dev-team")) {
                        budgetRate = budgetRate.add(new BigDecimal(budgets.mBudget.rate));
                    }
                }
            }
            return budgetRate;
        }

        public BigDecimal getApr(BaseChain baseChain) {
            BigDecimal inflation = getMintInflation(baseChain);
            BigDecimal calTax = BigDecimal.ONE.subtract(getTax(baseChain));
            if (getMainSupply(baseChain) == null || getMainSupply(baseChain).equals(BigDecimal.ZERO)) {
                return BigDecimal.ZERO;
            } else {
                BigDecimal bondingRate = getBondedAmount(baseChain).divide(getMainSupply(baseChain), 6, RoundingMode.DOWN);
                if (bondingRate.equals(BigDecimal.ZERO)) return BigDecimal.ZERO;
                if (BigDecimal.ZERO.compareTo(bondingRate) != 0) {
                    if (baseChain.equals(BaseChain.OSMOSIS_MAIN)) {
                        BigDecimal stakingDistribution = new BigDecimal(osmosisMingtingParams.params.distributionProportions.staking);
                        return inflation.multiply(calTax).multiply(stakingDistribution).divide(bondingRate, 6, RoundingMode.DOWN);
                    } else if (baseChain.equals(STARGAZE_MAIN)) {
                        BigDecimal reductionFactor = BigDecimal.ONE.subtract(new BigDecimal(mStargazeAllocParams.params.mDistributionProportion.nft_incentives).add(new BigDecimal(mStargazeAllocParams.params.mDistributionProportion.developer_rewards)));
                        return inflation.multiply(calTax).multiply(reductionFactor).divide(bondingRate, 6, RoundingMode.DOWN);
                    } else if (baseChain.equals(EVMOS_MAIN)) {
                        if (!mEvmosInflationParams.params.enable_inflation) {
                            return BigDecimal.ZERO;
                        }
                        BigDecimal ap = new BigDecimal(mEvmosEpochMintProvision.mEpochMintProvision.amount).multiply(new BigDecimal("365"));
                        BigDecimal stakingRewardsFactor = BigDecimal.ZERO;
                        if (mEvmosInflationParams.params.mInflationDistributions.staking_rewards != null) {
                            stakingRewardsFactor = new BigDecimal(mEvmosInflationParams.params.mInflationDistributions.staking_rewards);
                        }
                        return ap.multiply(stakingRewardsFactor).divide(getBondedAmount(baseChain), 6, RoundingMode.DOWN);
                    } else if (baseChain.equals(CRESCENT_MAIN)) {
                        BigDecimal inflationAmount = getCurrentInflationAmount(baseChain);
                        BigDecimal budgetRate = BigDecimal.ONE.subtract(getBudgetRate(baseChain));
                        return budgetRate.multiply(calTax).multiply(inflationAmount).divide(getBondedAmount(baseChain), 6, RoundingMode.DOWN);
                    } else if (baseChain.equals(TERITORI_MAIN)) {
                        BigDecimal stakingDistribution = new BigDecimal(mTeritoriMintingParams.params.mTeritoriDistributionProportions.staking);
                        return inflation.multiply(calTax).multiply(stakingDistribution).divide(bondingRate, 6, RoundingMode.DOWN);
                    } else {
                        BigDecimal ap;
                        if (baseChain.equals(AXELAR_MAIN)) ap = getMainSupply(baseChain).multiply(getMintInflation(baseChain));
                        else ap = getAnnualProvision();
                        if (ap.compareTo(BigDecimal.ZERO) > 0) {
                            return ap.multiply(calTax).divide(getBondedAmount(baseChain), 6, RoundingMode.DOWN);
                        } else {
                            return inflation.multiply(calTax).divide(bondingRate, 6, RoundingMode.DOWN);
                        }
                    }
                } else {
                    return BigDecimal.ZERO;
                }
            }
        }

        public BigDecimal getDpApr(BaseChain baseChain) {
            return getApr(baseChain).movePointRight(2);
        }

        public BigDecimal getRealBlockPerYear(BaseChain chain) {
            if (chain != null) {
                ChainConfig chainConfig = ChainFactory.getChain(chain);
                if (chainConfig.blockTime() == BigDecimal.ZERO) {
                    return BigDecimal.ZERO;
                } else {
                    return YEAR_SEC.divide(chainConfig.blockTime(), 2, RoundingMode.DOWN);
                }
            }
            return BigDecimal.ZERO;
        }

        public BigDecimal getRealApr(BaseChain baseChain) {
            if (getRealBlockPerYear(baseChain) == BigDecimal.ZERO || getBlockPerYear(baseChain) == BigDecimal.ZERO) {
                return BigDecimal.ZERO;
            }
            return getApr(baseChain).multiply(getRealBlockPerYear(baseChain)).divide(getBlockPerYear(baseChain), 6, RoundingMode.DOWN);
        }

        public BigDecimal getDpRealApr(BaseChain baseChain) {
            if (getRealApr(baseChain) == BigDecimal.ZERO) { return BigDecimal.ZERO; }
            return getRealApr(baseChain).movePointRight(2);
        }

        public BigDecimal getBlockPerYear(BaseChain baseChain) {
            if (isGRPC(baseChain)) {
                if (baseChain.equals(STARGAZE_MAIN)) {
                    return new BigDecimal(mStargazeMintingParams.params.blocks_per_year);
                } else if (mMintParams != null && mMintParams.params!= null && mMintParams.params.blocks_per_year!= null) {
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
            if (mStakingParams != null) {
                if (isGRPC(baseChain)) {
                    return mStakingParams.params.bond_denom;
                } else {
                    return mStakingParams.bond_denom;
                }
            } else {
                return "";
            }
        }

        public BigDecimal getQuorum(BaseChain baseChain) {
            if (baseChain.equals(CERTIK_MAIN)) {
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

    public class StargazeAllocParams {
        @SerializedName("params")
        public StargazeAllocParam params;

        public class StargazeAllocParam {
            @SerializedName("distribution_proportions")
            public DistributionProportion mDistributionProportion;

            public class DistributionProportion {
                @SerializedName("nft_incentives")
                public String nft_incentives;

                @SerializedName("developer_rewards")
                public String developer_rewards;
            }
        }
    }

    public class EvmosInflationParams {
        @SerializedName("params")
        public EvmosInflationParam params;

        public class EvmosInflationParam {
            @SerializedName("mint_denom")
            public String mint_denom;

            @SerializedName("enable_inflation")
            public Boolean enable_inflation;

            @SerializedName("inflation_distribution")
            public InflationDistributions mInflationDistributions;

            public class InflationDistributions {
                @SerializedName("community_pool")
                public String community_pool;

                @SerializedName("staking_rewards")
                public String staking_rewards;

                @SerializedName("usage_incentives")
                public String usage_incentives;
            }
        }
    }

    public class EvmosEpochMintProvision {
        @SerializedName("epoch_mint_provision")
        public Coin mEpochMintProvision ;
    }

    public class CrescentMintingParams {
        @SerializedName("params")
        public CrescentParams mParams;
    }

    public class CrescentParams {
        @SerializedName("mint_denom")
        public String mint_denom;

        @SerializedName("inflation_schedules")
        public ArrayList<Schedules> mSchedules;
    }

    public class Schedules {
        @SerializedName("amount")
        public String amount;

        @SerializedName("end_time")
        public String end_time;

        @SerializedName("start_time")
        public String start_time;

        public BigDecimal getAmount() {
            return new BigDecimal(amount);
        }

        public long getEnd_time() {
            return WDp.dateToLong2(end_time);
        }

        public long getStart_time() {
            return WDp.dateToLong2(start_time);
        }
    }

    public class CrescentBudgets {
        @SerializedName("budgets")
        public ArrayList<Budgets> mBudgets;
    }

    public class Budgets {
        @SerializedName("budget")
        public Budget mBudget;
    }

    public class Budget {
        @SerializedName("name")
        public String name;

        @SerializedName("rate")
        public String rate;

        @SerializedName("end_time")
        public String end_time;

        @SerializedName("start_time")
        public String start_time;

        @SerializedName("source_address")
        public String source_address;

        @SerializedName("destination_address")
        public String destination_address;
    }

    public class TeritoriMintingParams {
        @SerializedName("params")
        public TeritoriMintingParam params;

        public class TeritoriMintingParam {
            @SerializedName("mint_denom")
            public String mint_denom;

            @SerializedName("reduction_factor")
            public String reduction_factor;

            @SerializedName("distribution_proportions")
            public TeritoriDistributionProportions mTeritoriDistributionProportions;

            @SerializedName("genesis_block_provisions")
            public String genesis_block_provisions;

            @SerializedName("reduction_period_in_blocks")
            public String reduction_period_in_blocks;

            @SerializedName("minting_rewards_distribution_start_block")
            public String minting_rewards_distribution_start_block;

            public class TeritoriDistributionProportions {
                @SerializedName("staking")
                public String staking;

                @SerializedName("community_pool")
                public String community_pool;

                @SerializedName("grants_program")
                public String grants_program;

                @SerializedName("usage_incentive")
                public String usage_incentive;

                @SerializedName("developer_rewards")
                public String developer_rewards;
            }
        }
    }
}



