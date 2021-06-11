package wannabit.io.cosmostaion.dao;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WLog;


public class ChainParam {
    @SerializedName("chain_id")
    public String chain_id;
    
    @SerializedName("Params")
    public Params params;

    public class Params {
        @SerializedName("iris_tokens")
        public ArrayList<Token> mIrisTokens;

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
        public Object supplys;

        @SerializedName("gov_tallying")
        public GovTallyings govTallyings;




        public class MintInflation {
            @SerializedName("inflation")
            public String mInflation;
        }

        public BigDecimal getMintInflation() {
            try {
                MintInflation temp = new Gson().fromJson(new Gson().toJson(mMintInflations), MintInflation.class);
                return new BigDecimal(temp.mInflation);
            } catch (Exception e) { }

            try {
                String temp = new Gson().fromJson(new Gson().toJson(mMintInflations), String.class);
                return new BigDecimal(temp);
            } catch (Exception e) { }

            return BigDecimal.ZERO;
        }

        public class MintProvision {
            @SerializedName("annual_provisions")
            public String mProvision;
        }

        public BigDecimal getMintProvision() {
            try {
                MintProvision temp = new Gson().fromJson(new Gson().toJson(mMintProvisions), MintProvision.class);
                return new BigDecimal(temp.mProvision);
            } catch (Exception e) { }

            try {
                String temp = new Gson().fromJson(new Gson().toJson(mMintProvisions), String.class);
                return new BigDecimal(temp);
            } catch (Exception e) { }

            return BigDecimal.ZERO;
        }

        public class Supply {
            @SerializedName("supply")
            public ArrayList<Coin> supply;
        }

        public ArrayList<Coin> getSupplys() {
            ArrayList<Coin> result = new ArrayList<>();
            try {
                Coin temp = new Gson().fromJson(new Gson().toJson(supplys), Coin.class);
                result.add(temp);
            } catch (Exception e) { }

            try {
                result = new Gson().fromJson(new Gson().toJson(supplys), new TypeToken<List<Coin>>(){}.getType());
            } catch (Exception e) { }
            return result;
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
                public String mInflation;

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

            public class StakingPools {
                @SerializedName("pool")
                public StakingPool pool;

                @SerializedName("bonded_tokens")
                public String bonded_tokens;

                @SerializedName("not_bonded_tokens")
                public String not_bonded_tokens;
            }

            public class StakingPool {
                @SerializedName("bonded_tokens")
                public String bonded_tokens;

                @SerializedName("not_bonded_tokens")
                public String not_bonded_tokens;
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
             }

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
            }

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

            public class GovTallyings {
                @SerializedName("DefaultTally")
                public DefaultTally defaultTally;
                    public class DefaultTally {
                        @SerializedName("veto")
                        public String veto;

                        @SerializedName("quorum")
                        public String quorum;

                        @SerializedName("threshold")
                        public String threshold;
                    }

                @SerializedName("tally_params")
                public GovTallying tallyparams;

                @SerializedName("veto")
                public String veto;

                @SerializedName("quorum")
                public String quorum;

                @SerializedName("threshold")
                public String threshold;

            }

            public class GovTallying {
                @SerializedName("quorum")
                public String quorum;

                @SerializedName("threshold")
                public String threshold;

                @SerializedName("veto_threshold")
                public String veto_threshold;
            }

            public class Token {
                @SerializedName("type")
                public String type;

                @SerializedName("value")
                public iris_value value;

                public class iris_value {
                    @SerializedName("name")
                    public String name;

                    @SerializedName("owner")
                    public String owner;

                    @SerializedName("scale")
                    public BigDecimal scale;

                    @SerializedName("symbol")
                    public String symbol;

                    @SerializedName("min_unit")
                    public String min_unit;

                    @SerializedName("mintable")
                    public boolean mintable;

                    @SerializedName("max_supply")
                    public BigDecimal max_supply;

                    @SerializedName("initial_supply")
                    public BigDecimal inital_supply;
            }

        }

        public String getMainDenom() {
            try {
                StakingParam temp = new Gson().fromJson(new Gson().toJson(mStakingParams.params), StakingParam.class);
                return temp.bond_denom;
            } catch (Exception e) { }

            try {
                StakingParams temp = new Gson().fromJson(new Gson().toJson(mStakingParams), StakingParams.class);
                return temp.bond_denom;
            } catch (Exception e) { }

            return "";
        }

        public BigDecimal getInflation(BaseChain baseChain) {
            if (baseChain.equals(BaseChain.IRIS_MAIN) || baseChain.equals(BaseChain.IRIS_TEST)) {
                if (params == null || params.mMintParams == null || params.mMintParams.mInflation == null){
                    return BigDecimal.ZERO;
                }
                return new BigDecimal(params.mMintParams.mInflation);
            }
            return getMintInflation();
        }

        public BigDecimal Inflation(BaseChain baseChain) {
            return getInflation(baseChain).movePointRight(2);
        }

        public BigDecimal getBondedAmount() {
            try {
                StakingPool temp = new Gson().fromJson(new Gson().toJson(mStakingpools.pool), StakingPool.class);
                return new BigDecimal(temp.bonded_tokens);

            } catch (Exception e) {}

            try {
                StakingPools temp = new Gson().fromJson(new Gson().toJson(mStakingpools), StakingPools.class);
                return new BigDecimal(temp.bonded_tokens);
            } catch (Exception e) {}

            return BigDecimal.ZERO;
        }

        public BigDecimal getTax () {
            try {
                DistributionParam temp = new Gson().fromJson(new Gson().toJson(mDistributionParams.params), DistributionParam.class);
                return new BigDecimal(temp.community_tax);

            } catch (Exception e) {}

            try {
                DistributionParams temp = new Gson().fromJson(new Gson().toJson(mDistributionParams), DistributionParams.class);
                return new BigDecimal(temp.community_tax);
            } catch (Exception e) {}

            return BigDecimal.ZERO;
        }

        public BigDecimal getApr(BaseChain baseChain) {
            BigDecimal inflation = Inflation(baseChain);
            return BigDecimal.ZERO;
        }

    }
}
