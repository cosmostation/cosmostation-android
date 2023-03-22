package wannabit.io.cosmostaion.dao;

import static wannabit.io.cosmostaion.base.BaseChain.CANTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CUDOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.QUICKSILVER_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SOMMELIER_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;
import static wannabit.io.cosmostaion.base.BaseConstant.YEAR_SEC;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;

import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;

public class Param {
    @SerializedName("chain_id")
    public String chain_id;

    @SerializedName("block_time")
    public Double block_time;

    @SerializedName("gas_price")
    public GasPrice mGasPrice;

    @SerializedName("params")
    public Params mParams;

    public class GasPrice {
        @SerializedName("chain")
        public String chain;

        @SerializedName("base")
        public String base;

        @SerializedName("rate")
        public ArrayList<String> rate;
    }

    public class Params {
        @SerializedName("iris_minting_params")
        public IrisMintingParams mIrisMintingParams;

        @SerializedName("minting_params")
        public MintingParams mMintingParams;

        @SerializedName("minting_inflation")
        public MintingInflation mMintingInflation;

        @SerializedName("minting_annual_provisions")
        public MintingAnnualProvisions mMintingAnnualProvisions;

        @SerializedName("staking_pool")
        public StakingPool mStakingPool;

        @SerializedName("staking_params")
        public StakingParams mStakingParams;

        @SerializedName("distribution_params")
        public DistributionParams mDistributionParams;

        @SerializedName("bank_supply")
        public BankSupply mBankSupply;

        @SerializedName("gov_tally_params")
        public GovTallyParams mGovTallyParams;

        //custom
        @SerializedName("shentu_gov_tally_params")
        public ShentuGovTallyParams mShentuGovTallyParams;

        @SerializedName("osmosis_minting_params")
        public OsmosisMintingParams mOsmosisMintingParams;

        @SerializedName("osmosis_minting_epoch_provisions")
        public OsmosisMintingEpochProvisions mOsmosisMintingEpochProvisions;

        @SerializedName("emoney_minting_inflation")
        public EmoneyMintingInflation mEmoneyMintingInflation;

        @SerializedName("stargaze_minting_params")
        public StargazeMintingParams mStargazeMintingParams;

        @SerializedName("stargaze_alloc_params")
        public StargazeAllocParams mStargazeAllocParams;

        @SerializedName("evmos_inflation_params")
        public EvmosInflationParams mEvmosInflationParams;

        @SerializedName("evmos_epoch_mint_provision")
        public EvmosEpochMintProvision mEvmosEpochMintProvision;

        @SerializedName("crescent_minting_params")
        public CrescentMintingParams mCrescentMintingParams;

        @SerializedName("crescent_budgets")
        public CrescentBudgets mCrescentBudgets;

        @SerializedName("teritori_minting_params")
        public TeritoriMintingParams mTeritoriMintingParams;

        @SerializedName("band_oracle_active_validators")
        public OracleActiveValidators mOracleActiveValidators;

        @SerializedName("starname_domains")
        public ArrayList<String> mStarnameDomains;

        @SerializedName("stride_minting_params")
        public StrideMintingParams mStrideMintingParams;

        @SerializedName("stride_minting_epoch_provisions")
        public StrideMintingEpochProvisions mStrideMintingEpochProvisions;

        @SerializedName("mars_vesting_balance")
        public MarsVestingBalance mMarsVestingBalance;

        @SerializedName("cudos_minting_params")
        public CudosMintingParams mCudosMintingParams;

        @SerializedName("axelar_key_mgmt_relative_inflation_rate")
        public String axelarKeyInflationRate;

        @SerializedName("axelar_external_chain_voting_inflation_rate")
        public String axelarExternalInflationRate;

        @SerializedName("axelar_evm_chains")
        public ArrayList<String> axelarEvmChainList;

        @SerializedName("canto_inflation_params")
        public CantoInflationParams mCantoInflationParams;

        @SerializedName("canto_epoch_mint_provision")
        public CantoEpochMintProvision mCantoEpochMintProvision;

        @SerializedName("sommelier_apy")
        public SommlierApy mSommlierApy;

        @SerializedName("quicksilver_minting_params")
        public QuicksilverMintingParams mQuicksilverMintingParams;

        @SerializedName("quicksilver_minting_epoch_provisions")
        public QuicksilverMintProvision mQuicksilverMintProvision;
    }

    public BigDecimal getMintInflation(ChainConfig chainConfig) {
        try {
            if (chainConfig.baseChain().equals(BaseChain.IRIS_MAIN)) {
                if (mParams.mIrisMintingParams != null && mParams.mIrisMintingParams.mResult != null) {
                    return new BigDecimal(mParams.mIrisMintingParams.mResult.inflation);
                }

            } else if (chainConfig.baseChain().equals(BaseChain.OSMOSIS_MAIN)) {
                if (mParams.mOsmosisMintingEpochProvisions != null && mParams.mOsmosisMintingParams != null && mParams.mOsmosisMintingParams.params != null) {
                    BigDecimal epochProvisions = new BigDecimal(mParams.mOsmosisMintingEpochProvisions.epoch_provisions);
                    BigDecimal epochPeriods = new BigDecimal(mParams.mOsmosisMintingParams.params.reduction_period_in_epochs);
                    return epochProvisions.multiply(epochPeriods).divide(getMainSupply(), 18, RoundingMode.DOWN);
                }

            } else if (chainConfig.baseChain().equals(BaseChain.EMONEY_MAIN)) {
                if (mParams.mEmoneyMintingInflation != null && mParams.mEmoneyMintingInflation.assets != null) {
                    for (EmoneyMintingInflation.Asset asset : mParams.mEmoneyMintingInflation.assets) {
                        if (asset.denom.equalsIgnoreCase(chainConfig.mainDenom())) {
                            return new BigDecimal(asset.inflation);
                        }
                    }
                }

            } else if (chainConfig.baseChain().equals(BaseChain.STARGAZE_MAIN)) {
                if (mParams.mStargazeMintingParams != null && mParams.mStargazeMintingParams.params != null) {
                    BigDecimal initialProvision = new BigDecimal(mParams.mStargazeMintingParams.params.initial_annual_provisions);
                    return initialProvision.divide(getMainSupply(), 18, RoundingMode.DOWN);
                }

            } else if (chainConfig.baseChain().equals(BaseChain.EVMOS_MAIN)) {
                if (mParams.mEvmosInflationParams != null && mParams.mEvmosInflationParams.params != null && mParams.mEvmosEpochMintProvision.mEpochMintProvision != null) {
                    if (!mParams.mEvmosInflationParams.params.enable_inflation)
                        return BigDecimal.ZERO;
                    BigDecimal annualProvisions = new BigDecimal(mParams.mEvmosEpochMintProvision.mEpochMintProvision.amount).multiply(new BigDecimal("365"));
                    BigDecimal evmosSupply = getMainSupply().subtract(new BigDecimal("200000000000000000000000000"));
                    return annualProvisions.divide(evmosSupply, 18, RoundingMode.DOWN);
                }

            } else if (chainConfig.baseChain().equals(BaseChain.CRESCENT_MAIN)) {
                long now = Calendar.getInstance().getTime().getTime();
                if (mParams.mCrescentMintingParams != null && mParams.mCrescentMintingParams.params != null && mParams.mCrescentMintingParams.params.inflation_schedules != null) {
                    BigDecimal genesisSupply = new BigDecimal("200000000000000");
                    BigDecimal thisInflation = getCurrentInflationAmount();
                    for (CrescentMintingParams.CrescentMintingParam.InflationSchedule schedules : mParams.mCrescentMintingParams.params.inflation_schedules) {
                        if (schedules.getStart_time() < now && schedules.getEnd_time() < now) {
                            genesisSupply = genesisSupply.add(schedules.getAmount());
                        }
                    }
                    return thisInflation.divide(genesisSupply, 18, RoundingMode.UP);
                }

            } else if (chainConfig.baseChain().equals(BaseChain.AXELAR_MAIN)) {
                BigDecimal baseInflation = new BigDecimal(mParams.mMintingInflation.inflation);
                BigDecimal keyManageRate = new BigDecimal(mParams.axelarKeyInflationRate);
                BigDecimal externalRate = new BigDecimal(mParams.axelarExternalInflationRate);
                BigDecimal evmChainCnt = new BigDecimal(mParams.axelarEvmChainList.size());

                BigDecimal keyManageInflation = baseInflation.multiply(keyManageRate);
                BigDecimal externalEvmInflation = externalRate.multiply(evmChainCnt);
                return baseInflation.add(keyManageInflation).add(externalEvmInflation);


            } else if (chainConfig.baseChain().equals(BaseChain.TERITORI_MAIN)) {
                if (mParams.mTeritoriMintingParams != null && mParams.mTeritoriMintingParams.params != null) {
                    BigDecimal inflationNum = new BigDecimal(mParams.mTeritoriMintingParams.params.reduction_period_in_blocks).subtract(new BigDecimal(mParams.mTeritoriMintingParams.params.minting_rewards_distribution_start_block));
                    return inflationNum.multiply(new BigDecimal(mParams.mTeritoriMintingParams.params.genesis_block_provisions)).divide(getMainSupply(), 18, RoundingMode.DOWN);
                }

            } else if (chainConfig.baseChain().equals(BaseChain.STRIDE_MAIN)) {
                if (mParams.mStrideMintingParams != null && mParams.mStrideMintingParams.params != null) {
                    BigDecimal epochProvisions = new BigDecimal(mParams.mStrideMintingEpochProvisions.epoch_provisions);
                    BigDecimal epochPeriods = new BigDecimal(mParams.mStrideMintingParams.params.reduction_period_in_epochs);
                    return epochProvisions.multiply(epochPeriods).divide(getMainSupply(), 18, RoundingMode.DOWN);
                }

            } else if (chainConfig.baseChain().equals(CUDOS_MAIN)) {
                if (mParams.mCudosMintingParams != null && !mParams.mCudosMintingParams.inflation.isEmpty()) {
                    return new BigDecimal(mParams.mCudosMintingParams.inflation);
                } else {
                    return BigDecimal.ZERO;
                }

            } else if (chainConfig.baseChain().equals(CANTO_MAIN)) {
                if (mParams.mCantoInflationParams != null && mParams.mCantoInflationParams.params != null && mParams.mCantoEpochMintProvision.mEpochMintProvision != null) {
                    if (!mParams.mCantoInflationParams.params.enable_inflation)
                        return BigDecimal.ZERO;
                    BigDecimal annualProvisions = new BigDecimal(mParams.mCantoEpochMintProvision.mEpochMintProvision.amount).multiply(new BigDecimal("365"));
                    return annualProvisions.divide(getMainSupply(), 18, RoundingMode.DOWN);
                }

            } else if (chainConfig.baseChain().equals(QUICKSILVER_MAIN)) {
                if (mParams.mQuicksilverMintingParams != null && mParams.mQuicksilverMintingParams.params != null && mParams.mQuicksilverMintProvision != null) {
                    BigDecimal epochProvisions = new BigDecimal(mParams.mQuicksilverMintProvision.epochProvisions);
                    BigDecimal epochPeriods = new BigDecimal(mParams.mQuicksilverMintingParams.params.reduction_period_in_epochs);
                    return epochProvisions.multiply(epochPeriods).divide(getMainSupply(), 18, RoundingMode.DOWN);
                }

            } else {
                if (mParams != null && mParams.mMintingInflation != null && mParams.mMintingInflation.inflation != null) {
                    return new BigDecimal(mParams.mMintingInflation.inflation);
                }
            }
        } catch (ArithmeticException e) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal getDpInflation(ChainConfig chainConfig) {
        return getMintInflation(chainConfig).movePointRight(2);
    }

    public BigDecimal getAnnualProvision() {
        if (mParams != null && mParams.mMintingAnnualProvisions != null) {
            return new BigDecimal(mParams.mMintingAnnualProvisions.annual_provisions);
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal getTax(ChainConfig chainConfig) {
        if (mParams != null && mParams.mDistributionParams != null) {
            if (isGRPC(chainConfig.baseChain())) {
                return new BigDecimal(mParams.mDistributionParams.params.community_tax);
            } else {
                return new BigDecimal(mParams.mDistributionParams.community_tax);
            }
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal getBondedAmount() {
        if (mParams != null && mParams.mStakingPool != null) {
            return new BigDecimal(mParams.mStakingPool.pool.bonded_tokens);
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal getMainSupply() {
        String denom = getMainDenom();
        if (mParams != null && mParams.mBankSupply != null) {
            for (Coin coin : mParams.mBankSupply.supply) {
                if (coin.denom.equals(denom)) {
                    return new BigDecimal(coin.amount);
                }
            }
        }
        return BigDecimal.ZERO;
    }

    public String getMainDenom() {
        if (mParams != null && mParams.mStakingParams != null) {
            return mParams.mStakingParams.params.bond_denom;
        }
        return "";
    }

    public BigDecimal getApr(ChainConfig chainConfig) {
        BigDecimal inflation = getMintInflation(chainConfig);
        BigDecimal calTax = BigDecimal.ONE.subtract(getTax(chainConfig));
        if (getMainSupply() == null || getMainSupply().equals(BigDecimal.ZERO)) {
            return BigDecimal.ZERO;
        } else {
            BigDecimal bondingRate = getBondedAmount().divide(getMainSupply(), 6, RoundingMode.DOWN);
            if (bondingRate.equals(BigDecimal.ZERO)) return BigDecimal.ZERO;

            if (BigDecimal.ZERO.compareTo(bondingRate) != 0) {
                if (chainConfig.baseChain().equals(BaseChain.OSMOSIS_MAIN)) {
                    if (mParams.mOsmosisMintingParams != null && mParams.mOsmosisMintingParams.params != null && mParams.mOsmosisMintingParams.params.mDistributionProportion != null) {
                        BigDecimal stakingDistribution = new BigDecimal(mParams.mOsmosisMintingParams.params.mDistributionProportion.staking);
                        return inflation.multiply(calTax).multiply(stakingDistribution).divide(bondingRate, 6, RoundingMode.DOWN);
                    }

                } else if (chainConfig.baseChain().equals(BaseChain.STARGAZE_MAIN)) {
                    if (mParams.mStargazeAllocParams != null && mParams.mStargazeAllocParams.params != null && mParams.mStargazeAllocParams.params.mDistributionProportions != null) {
                        BigDecimal reductionFactor = new BigDecimal(mParams.mStargazeAllocParams.params.mDistributionProportions.nft_incentives).add(new BigDecimal(mParams.mStargazeAllocParams.params.mDistributionProportions.developer_rewards));
                        return inflation.multiply(calTax).multiply(BigDecimal.ONE.subtract(reductionFactor)).divide(bondingRate, 6, RoundingMode.DOWN);
                    }

                } else if (chainConfig.baseChain().equals(BaseChain.EVMOS_MAIN)) {
                    if (mParams.mEvmosInflationParams != null && mParams.mEvmosInflationParams.params != null && mParams.mEvmosEpochMintProvision != null) {
                        if (!mParams.mEvmosInflationParams.params.enable_inflation)
                            return BigDecimal.ZERO;
                        BigDecimal stakingRewardsFactor = BigDecimal.ZERO;
                        BigDecimal ap = new BigDecimal(mParams.mEvmosEpochMintProvision.mEpochMintProvision.amount).multiply(new BigDecimal("365"));
                        if (mParams.mEvmosInflationParams.params.mInflationDistribution != null) {
                            stakingRewardsFactor = new BigDecimal(mParams.mEvmosInflationParams.params.mInflationDistribution.staking_rewards);
                        }
                        return ap.multiply(stakingRewardsFactor).divide(getBondedAmount(), 6, RoundingMode.DOWN);
                    }

                } else if (chainConfig.baseChain().equals(BaseChain.CRESCENT_MAIN)) {
                    BigDecimal inflationAmount = getCurrentInflationAmount();
                    BigDecimal budgetRate = BigDecimal.ONE.subtract(getBudgetRate());
                    return budgetRate.multiply(calTax).multiply(inflationAmount).divide(getBondedAmount(), 6, RoundingMode.DOWN);

                } else if (chainConfig.baseChain().equals(BaseChain.TERITORI_MAIN)) {
                    if (mParams.mTeritoriMintingParams != null && mParams.mTeritoriMintingParams.params != null && mParams.mTeritoriMintingParams.params.mTeritoriDistributionProportions != null) {
                        BigDecimal stakingDistribution = new BigDecimal(mParams.mTeritoriMintingParams.params.mTeritoriDistributionProportions.staking);
                        return inflation.multiply(calTax).multiply(stakingDistribution).divide(bondingRate, 6, RoundingMode.DOWN);
                    }

                } else if (chainConfig.baseChain().equals(BaseChain.STRIDE_MAIN)) {
                    if (mParams.mStrideMintingParams != null && mParams.mStrideMintingParams.params != null && mParams.mStrideMintingParams.params.mStrideDistributionProportions != null) {
                        BigDecimal stakingDistribution = new BigDecimal(mParams.mStrideMintingParams.params.mStrideDistributionProportions.staking);
                        return inflation.multiply(calTax).multiply(stakingDistribution).divide(bondingRate, 6, RoundingMode.DOWN);
                    }

                } else if (chainConfig.baseChain().equals(CUDOS_MAIN)) {
                    if (mParams.mCudosMintingParams != null && !mParams.mCudosMintingParams.apr.isEmpty()) {
                        return new BigDecimal(mParams.mCudosMintingParams.apr);
                    } else {
                        return BigDecimal.ZERO;
                    }

                } else if (chainConfig.baseChain().equals(CANTO_MAIN)) {
                    if (mParams.mCantoInflationParams != null && mParams.mCantoInflationParams.params != null && mParams.mCantoEpochMintProvision != null) {
                        if (!mParams.mCantoInflationParams.params.enable_inflation)
                            return BigDecimal.ZERO;
                        BigDecimal stakingRewardsFactor = BigDecimal.ZERO;
                        BigDecimal ap = new BigDecimal(mParams.mCantoEpochMintProvision.mEpochMintProvision.amount).multiply(new BigDecimal("365"));
                        if (mParams.mCantoInflationParams.params.mInflationDistribution != null) {
                            stakingRewardsFactor = new BigDecimal(mParams.mCantoInflationParams.params.mInflationDistribution.staking_rewards);
                        }
                        return ap.multiply(stakingRewardsFactor).divide(getBondedAmount(), 6, RoundingMode.DOWN);
                    }

                } else if (chainConfig.baseChain().equals(SOMMELIER_MAIN)) {
                    if (mParams.mSommlierApy != null && !mParams.mSommlierApy.apy.isEmpty()) {
                        return new BigDecimal(mParams.mSommlierApy.apy);
                    } else {
                        return BigDecimal.ZERO;
                    }

                } else if (chainConfig.baseChain().equals(QUICKSILVER_MAIN)) {
                    if (mParams.mQuicksilverMintingParams != null && mParams.mQuicksilverMintingParams.params != null && mParams.mQuicksilverMintingParams.params.mDistributionProportion != null) {
                        BigDecimal stakingDistribution = new BigDecimal(mParams.mQuicksilverMintingParams.params.mDistributionProportion.staking);
                        return inflation.multiply(stakingDistribution).divide(bondingRate, 6, RoundingMode.DOWN);
                    }
                } else {
                    BigDecimal ap;
                    if (chainConfig.baseChain().equals(BaseChain.AXELAR_MAIN))
                        ap = getMainSupply().multiply(getMintInflation(chainConfig));
                    else ap = getAnnualProvision();
                    if (ap.compareTo(BigDecimal.ZERO) > 0) {
                        return ap.multiply(calTax).divide(getBondedAmount(), 6, RoundingMode.DOWN);
                    } else {
                        return inflation.multiply(calTax).divide(bondingRate, 6, RoundingMode.DOWN);
                    }
                }
            }
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal getDpApr(ChainConfig chainConfig) {
        return getApr(chainConfig).movePointRight(2);
    }

    public BigDecimal getBlockPerYear(ChainConfig chainConfig) {
        if (mParams != null) {
            if (mParams.mStargazeMintingParams != null && chainConfig.baseChain().equals(BaseChain.STARGAZE_MAIN)) {
                return new BigDecimal(mParams.mStargazeMintingParams.params.blocks_per_year);
            } else if (mParams.mMintingParams != null && mParams.mMintingParams.params != null && mParams.mMintingParams.params.blocks_per_year != null) {
                return new BigDecimal(mParams.mMintingParams.params.blocks_per_year);
            } else {
                return BigDecimal.ZERO;
            }
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal getRealBlockPerYear() {
        if (block_time == null || BigDecimal.ZERO.equals(new BigDecimal(block_time))) {
            return BigDecimal.ZERO;
        }
        return YEAR_SEC.divide(new BigDecimal(block_time), 2, RoundingMode.DOWN);
    }

    public BigDecimal getRealApr(ChainConfig chainConfig) {
        if (BigDecimal.ZERO.equals(getRealBlockPerYear()) || BigDecimal.ZERO.equals(getBlockPerYear(chainConfig))) {
            return BigDecimal.ZERO;
        }
        return getApr(chainConfig).multiply(getRealBlockPerYear()).divide(getBlockPerYear(chainConfig), 6, RoundingMode.DOWN);
    }

    public BigDecimal getDpRealApr(ChainConfig chainConfig) {
        if (BigDecimal.ZERO.equals(getRealApr(chainConfig))) {
            return BigDecimal.ZERO;
        }
        return getRealApr(chainConfig).movePointRight(2);
    }

    public BigDecimal getCurrentInflationAmount() {
        BigDecimal inflationAmount = BigDecimal.ZERO;
        long now = Calendar.getInstance().getTime().getTime();
        if (mParams.mCrescentMintingParams != null && mParams.mCrescentMintingParams.params != null) {
            for (CrescentMintingParams.CrescentMintingParam.InflationSchedule schedules : mParams.mCrescentMintingParams.params.inflation_schedules) {
                if (schedules.getStart_time() < now && schedules.getEnd_time() > now) {
                    inflationAmount = schedules.getAmount();
                }
            }
        }
        return inflationAmount;
    }

    public BigDecimal getBudgetRate() {
        BigDecimal budgetRate = BigDecimal.ZERO;
        if (mParams.mCrescentBudgets != null) {
            for (CrescentBudgets.Budgets budgets : mParams.mCrescentBudgets.mBudgets) {
                if (budgets.mBudget.name.equalsIgnoreCase("budget-ecosystem-incentive") || budgets.mBudget.name.equalsIgnoreCase("budget-dev-team")) {
                    budgetRate = budgetRate.add(new BigDecimal(budgets.mBudget.rate));
                }
            }
        }
        return budgetRate;
    }

    public BigDecimal getQuorum(ChainConfig chainConfig) {
        if (chainConfig.baseChain().equals(CERTIK_MAIN) && mParams.mShentuGovTallyParams != null) {
            return new BigDecimal(mParams.mShentuGovTallyParams.mTallyParams.mDefaultTally.quorum).movePointRight(2);
        } else if (isGRPC(chainConfig.baseChain()) && mParams.mGovTallyParams.mTallyParams != null) {
            return new BigDecimal(mParams.mGovTallyParams.mTallyParams.quorum).movePointRight(2);
        } else {
            return new BigDecimal(mParams.mGovTallyParams.quorum).movePointRight(2);
        }
    }

    public boolean isOracleEnable(String valOpAddress) {
        if (mParams.mOracleActiveValidators == null) return true;
        if (mParams.mOracleActiveValidators.validators != null) {
            for (Param.OracleActiveValidators.validators oracle : mParams.mOracleActiveValidators.validators) {
                if (oracle.address.equalsIgnoreCase(valOpAddress)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getUnbonding(BaseChain baseChain) {
        int result = 0;
        String unbondingTime = null;
        if (mParams.mStakingParams != null && mParams.mStakingParams.params != null) {
            if (isGRPC(baseChain)) {
                unbondingTime = mParams.mStakingParams.params.unbonding_time;
                result = Integer.parseInt(unbondingTime.split("s")[0]) / 60 / 60 / 24;
            } else {
                unbondingTime = mParams.mStakingParams.unbonding_time;
                result = Integer.parseInt(String.valueOf(Long.parseLong(unbondingTime) / 1000000000 / 60 / 60 / 24));
            }
        }
        return result;
    }

    public ArrayList<String> getGasRate() {
        if (mGasPrice != null && mGasPrice.rate.size() > 0) {
            return mGasPrice.rate;
        }
        return null;
    }

    public BigDecimal getTurnoutBondedAmount() {
        if (mParams.mMarsVestingBalance != null && mParams.mMarsVestingBalance.balances.size() > 0) {
            BigDecimal marsVestingAmount = new BigDecimal(mParams.mMarsVestingBalance.balances.get(0).amount);
            return getBondedAmount().add(marsVestingAmount);
        } else {
            return getBondedAmount();
        }
    }

    public class IrisMintingParams {
        @SerializedName("result")
        public Result mResult;

        public class Result {
            @SerializedName("inflation")
            public String inflation;
        }
    }

    public class MintingParams {
        @SerializedName("params")
        public MintingParam params;

        public class MintingParam {
            @SerializedName("blocks_per_year")
            public String blocks_per_year;
        }
    }

    public class MintingInflation {
        @SerializedName("inflation")
        public String inflation;
    }

    public class MintingAnnualProvisions {
        @SerializedName("annual_provisions")
        public String annual_provisions;
    }

    public class StakingPool {
        @SerializedName("pool")
        public Pool pool;

        public class Pool {
            @SerializedName("bonded_tokens")
            public String bonded_tokens;
        }
    }

    public class StakingParams {
        @SerializedName("params")
        public StakingParam params;

        @SerializedName("unbonding_time")
        public String unbonding_time;

        public class StakingParam {
            @SerializedName("unbonding_time")
            public String unbonding_time;

            @SerializedName("bond_denom")
            public String bond_denom;
        }

    }

    public class DistributionParams {
        @SerializedName("params")
        public DistributionParam params;

        @SerializedName("community_tax")
        public String community_tax;

        public class DistributionParam {
            @SerializedName("community_tax")
            public String community_tax;

            @SerializedName("withdraw_addr_enabled")
            public boolean withdraw_addr_enabled;
        }
    }

    public class BankSupply {
        @SerializedName("supply")
        public ArrayList<Coin> supply;
    }

    public class GovTallyParams {
        @SerializedName("tally_params")
        public TallyParams mTallyParams;

        @SerializedName("quorum")
        public String quorum;

        public class TallyParams {
            @SerializedName("quorum")
            public String quorum;
        }
    }

    public class ShentuGovTallyParams {
        @SerializedName("tally_params")
        public TallyParams mTallyParams;

        public class TallyParams {
            @SerializedName("default_tally")
            public DefaultTally mDefaultTally;

            public class DefaultTally {
                @SerializedName("quorum")
                public String quorum;
            }
        }
    }

    public class OsmosisMintingParams {
        @SerializedName("params")
        public OsmosisMintingParam params;

        public class OsmosisMintingParam {
            @SerializedName("reduction_period_in_epochs")
            public String reduction_period_in_epochs;

            @SerializedName("distribution_proportions")
            public DistributionProportion mDistributionProportion;

            public class DistributionProportion {
                @SerializedName("staking")
                public String staking;
            }
        }
    }

    public class OsmosisMintingEpochProvisions {
        @SerializedName("epoch_provisions")
        public String epoch_provisions;
    }

    public class EmoneyMintingInflation {
        @SerializedName("assets")
        public ArrayList<Asset> assets;

        public class Asset {
            @SerializedName("denom")
            public String denom;

            @SerializedName("inflation")
            public String inflation;
        }
    }

    public class StargazeMintingParams {
        @SerializedName("params")
        public StargazeMintingParam params;

        public class StargazeMintingParam {
            @SerializedName("initial_annual_provisions")
            public String initial_annual_provisions;

            @SerializedName("blocks_per_year")
            public String blocks_per_year;
        }
    }

    public class StargazeAllocParams {
        @SerializedName("params")
        public StargazeAllocParam params;

        public class StargazeAllocParam {
            @SerializedName("distribution_proportions")
            public DistributionProportions mDistributionProportions;

            public class DistributionProportions {
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
            @SerializedName("inflation_distribution")
            public InflationDistribution mInflationDistribution;

            @SerializedName("enable_inflation")
            public boolean enable_inflation;

            @SerializedName("mint_denom")
            public String mint_denom;

            public class InflationDistribution {
                @SerializedName("staking_rewards")
                public String staking_rewards;
            }
        }
    }

    public class EvmosEpochMintProvision {
        @SerializedName("epoch_mint_provision")
        public Coin mEpochMintProvision;
    }

    public class CrescentMintingParams {
        @SerializedName("params")
        public CrescentMintingParam params;

        public class CrescentMintingParam {
            @SerializedName("inflation_schedules")
            public ArrayList<InflationSchedule> inflation_schedules;

            public class InflationSchedule {
                @SerializedName("start_time")
                public String start_time;

                @SerializedName("end_time")
                public String end_time;

                @SerializedName("amount")
                public String amount;

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
        }
    }

    public class CrescentBudgets {
        @SerializedName("budgets")
        public ArrayList<Budgets> mBudgets;

        public class Budgets {
            @SerializedName("budget")
            public Budget mBudget;

            public class Budget {
                @SerializedName("name")
                public String name;

                @SerializedName("rate")
                public String rate;
            }
        }
    }

    public class TeritoriMintingParams {
        @SerializedName("params")
        public TeritoriMintingParam params;

        public class TeritoriMintingParam {
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
            }
        }
    }

    public class OracleActiveValidators {
        @SerializedName("validators")
        public ArrayList<validators> validators;

        public class validators {
            @SerializedName("address")
            public String address;

            @SerializedName("power")
            public String power;

        }
    }

    public class StrideMintingParams {
        @SerializedName("params")
        public StrideMintingParam params;

        public class StrideMintingParam {
            @SerializedName("reduction_period_in_epochs")
            public String reduction_period_in_epochs;

            @SerializedName("distribution_proportions")
            public StrideDistributionProportions mStrideDistributionProportions;

            public class StrideDistributionProportions {
                @SerializedName("staking")
                public String staking;
            }
        }
    }

    public class StrideMintingEpochProvisions {
        @SerializedName("epoch_provisions")
        public String epoch_provisions;
    }

    public class MarsVestingBalance {
        @SerializedName("balances")
        public ArrayList<Coin> balances;
    }

    public class CudosMintingParams {
        @SerializedName("inflation")
        public String inflation;

        @SerializedName("apr")
        public String apr;

        @SerializedName("supply")
        public String supply;
    }

    public class CantoInflationParams {
        @SerializedName("params")
        public CantoInflationParam params;

        public class CantoInflationParam {
            @SerializedName("inflation_distribution")
            public InflationDistribution mInflationDistribution;

            @SerializedName("enable_inflation")
            public boolean enable_inflation;

            @SerializedName("mint_denom")
            public String mint_denom;

            public class InflationDistribution {
                @SerializedName("staking_rewards")
                public String staking_rewards;
            }
        }
    }

    public class CantoEpochMintProvision {
        @SerializedName("epoch_mint_provision")
        public Coin mEpochMintProvision;
    }

    public class SommlierApy {
        @SerializedName("apy")
        public String apy;
    }

    public class QuicksilverMintingParams {
        @SerializedName("params")
        public QuicksilverMintingParam params;

        public class QuicksilverMintingParam {
            @SerializedName("reduction_period_in_epochs")
            public String reduction_period_in_epochs;

            @SerializedName("distribution_proportions")
            public DistributionProportion mDistributionProportion;

            public class DistributionProportion {
                @SerializedName("staking")
                public String staking;
            }
        }
    }

    public class QuicksilverMintProvision {
        @SerializedName("epoch_provisions")
        public String epochProvisions;
    }
}
