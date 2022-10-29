package wannabit.io.cosmostaion.dao;

import static wannabit.io.cosmostaion.base.BaseChain.AXELAR_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.STARGAZE_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;
import static wannabit.io.cosmostaion.base.BaseConstant.YEAR_SEC;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.model.type.Coin;

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
        public Object supply;

        @SerializedName("gov_tally_params")
        public GovTallyParams mGovTallyParams;
    }

    public BigDecimal getMintInflation(ChainConfig chainConfig) {
        if (chainConfig.baseChain().equals(BaseChain.IRIS_MAIN)) {
            if (mParams.mIrisMintingParams != null && mParams.mIrisMintingParams.mResult != null) {
                return new BigDecimal(mParams.mIrisMintingParams.mResult.inflation);
            }

        } else {
            if (mParams != null && mParams.mMintingInflation != null) {
                return new BigDecimal(mParams.mMintingInflation.inflation);
            }
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
                return new BigDecimal(mParams.mDistributionParams.mDistributionParam.community_tax);
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

    public ArrayList<Coin> getSupplys() {
        ArrayList<Coin> result = new ArrayList<>();
        try {
            Supply temp = new Gson().fromJson(new Gson().toJson(mParams.supply), Supply.class);
            result.addAll(temp.supply);
        } catch (Exception e) { }
        try {
            result = new Gson().fromJson(new Gson().toJson(mParams.supply), new TypeToken<List<Coin>>(){}.getType());
        } catch (Exception e) { }
        return result;
    }

    public BigDecimal getMainSupply() {
        String denom = getMainDenom();
        for (Coin coin : getSupplys()) {
            if (coin.denom.equals(denom)) {
                return new BigDecimal(coin.amount);
            }
        }
        return BigDecimal.ZERO;
    }

    public String getMainDenom() {
        if (mParams != null && mParams.mStakingParams != null) {
            return mParams.mStakingParams.mStakingParam.bond_denom;
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

            BigDecimal ap;
            if (chainConfig.baseChain().equals(AXELAR_MAIN)) ap = getMainSupply().multiply(getMintInflation(chainConfig));
            else ap = getAnnualProvision();
            if (ap.compareTo(BigDecimal.ZERO) > 0) {
                return ap.multiply(calTax).divide(getBondedAmount(), 6, RoundingMode.DOWN);
            } else {
                return inflation.multiply(calTax).divide(bondingRate, 6, RoundingMode.DOWN);
            }
        }
    }

    public BigDecimal getDpApr(ChainConfig chainConfig) {
        return getApr(chainConfig).movePointRight(2);
    }

    public BigDecimal getBlockPerYear(ChainConfig chainConfig) {
        if (mParams != null && mParams.mMintingParams != null) {
            if (chainConfig.baseChain().equals(STARGAZE_MAIN)) {

            } else {
                return new BigDecimal(mParams.mMintingParams.mMintingParam.blocks_per_year);
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
        public MintingParam mMintingParam;

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
        public StakingParam mStakingParam;

        public class StakingParam {
            @SerializedName("unbonding_time")
            public String unbonding_time;

            @SerializedName("bond_denom")
            public String bond_denom;
        }

    }

    public class DistributionParams {
        @SerializedName("params")
        public DistributionParam mDistributionParam;

        @SerializedName("community_tax")
        public String community_tax;

        public class DistributionParam {
            @SerializedName("community_tax")
            public String community_tax;

            @SerializedName("withdraw_addr_enabled")
            public boolean withdraw_addr_enabled;
        }
    }

    public class Supply {
        @SerializedName("supply")
        public ArrayList<Coin> supply;
    }

    public class GovTallyParams {
        @SerializedName("tally_params")
        public TallyParams mTallyParams;

        public class TallyParams {
            @SerializedName("quorum")
            public String quorum;
        }
    }
}
