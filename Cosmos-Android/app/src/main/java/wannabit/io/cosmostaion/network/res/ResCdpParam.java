package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WLog;

public class ResCdpParam {

    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public Result result;



    public class Result {
        @SerializedName("surplus_auction_threshold")
        public String surplus_auction_threshold;

        @SerializedName("debt_auction_threshold")
        public String debt_auction_threshold;

        @SerializedName("circuit_breaker")
        public Boolean circuit_breaker;

        @SerializedName("global_debt_limit")
        public ArrayList<Coin> global_debt_limit;

        @SerializedName("debt_params")
        public ArrayList<KavaCdpDebtParam> debt_params;

        @SerializedName("collateral_params")
        public ArrayList<KavaCollateralParam> collateral_params;

        public BigDecimal getRawLiquidationRatio(String denom) {
            for (KavaCollateralParam param : collateral_params) {
                if (param.denom.equals(denom)) {
                    return new BigDecimal(param.liquidation_ratio);
                }
            }
            return BigDecimal.ZERO;
        }

        public KavaCollateralParam getCollateralParamByDenom(String denom) {
            KavaCollateralParam result = null;
            for (KavaCollateralParam collateralParam:collateral_params) {
                if (collateralParam.denom.equals(denom)) {
                    return  collateralParam;
                }
            }
            return  result;
        }
    }

    public class KavaCdpDebtParam {
        @SerializedName("denom")
        public String denom;

        @SerializedName("reference_asset")
        public String reference_asset;

        @SerializedName("conversion_factor")
        public String conversion_factor;

        @SerializedName("debt_floor")
        public String debt_floor;
    }

    public class KavaCollateralParam {
        @SerializedName("denom")
        public String denom;

        @SerializedName("liquidation_ratio")
        public String liquidation_ratio;

        @SerializedName("debt_limit")
        public ArrayList<Coin> debt_limit;

        @SerializedName("stability_fee")
        public String stability_fee;

        @SerializedName("auction_size")
        public String auction_size;

        @SerializedName("liquidation_penalty")
        public String liquidation_penalty;

        @SerializedName("prefix")
        public int prefix;

        @SerializedName("market_id")
        public String market_id;

        @SerializedName("conversion_factor")
        public String conversion_factor;

        public String getDpMarketId() {
            return market_id.split(":")[0].toUpperCase() + " : " + market_id.split(":")[1].toUpperCase() + "X";
        }

        public BigDecimal getDpLiquidationRatio() {
            return new BigDecimal(liquidation_ratio).movePointRight(2);
        }

        public BigDecimal getDpLiquidationPenalty() {
            return new BigDecimal(liquidation_penalty).movePointRight(2);
        }

        public BigDecimal getDpStabilityFee() {
            return (new BigDecimal(stability_fee).subtract(BigDecimal.ONE)).multiply(new BigDecimal("31536000")).movePointRight(2);
        }

        public String getImagePath() {
            return market_id.replace(":","")   +".png";
        }
    }

}
