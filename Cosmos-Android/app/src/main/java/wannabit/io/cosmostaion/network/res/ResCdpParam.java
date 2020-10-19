package wannabit.io.cosmostaion.network.res;

import android.text.TextUtils;

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

        @SerializedName("surplus_auction_lot")
        public String surplus_auction_lot;

        @SerializedName("debt_auction_threshold")
        public String debt_auction_threshold;

        @SerializedName("debt_auction_lot")
        public String debt_auction_lot;

        @SerializedName("savings_distribution_frequency")
        public String savings_distribution_frequency;

        @SerializedName("circuit_breaker")
        public Boolean circuit_breaker;

        @SerializedName("global_debt_limit")
        public Coin global_debt_limit;

        @SerializedName("debt_param")
        public KavaCdpDebtParam debt_param;

        @SerializedName("collateral_params")
        public ArrayList<KavaCollateralParam> collateral_params;

        public BigDecimal getGlobalDebtAmount() {
            BigDecimal result = BigDecimal.ZERO;
            if (global_debt_limit != null) {
                result = new BigDecimal(global_debt_limit.amount);
            }
            return result;
        }

        public BigDecimal getRawLiquidationRatio(String denom) {
            for (KavaCollateralParam param : collateral_params) {
                if (param.denom.equals(denom)) {
                    return new BigDecimal(param.liquidation_ratio);
                }
            }
            return BigDecimal.ZERO;
        }

        //only using for price
        public KavaCollateralParam getCollateralParamByDenom(String denom) {
            KavaCollateralParam result = null;
            for (KavaCollateralParam collateralParam:collateral_params) {
                if (collateralParam.denom.equals(denom)) {
                    return collateralParam;
                }
            }
            return  result;
        }


        public KavaCollateralParam getCollateralParamByType(String type) {
            KavaCollateralParam result = null;
            for (KavaCollateralParam collateralParam:collateral_params) {
                if (collateralParam.type.equals(type)) {
                    return collateralParam;
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

        @SerializedName("savings_rate")
        public String savings_rate;
    }

    public class KavaCollateralParam {
        @SerializedName("denom")
        public String denom;

        @SerializedName("type")
        public String type;

        @SerializedName("liquidation_ratio")
        public String liquidation_ratio;

        @SerializedName("debt_limit")
        public Coin debt_limit;

        @SerializedName("stability_fee")
        public String stability_fee;

        @SerializedName("auction_size")
        public String auction_size;

        @SerializedName("liquidation_penalty")
        public String liquidation_penalty;

        @SerializedName("prefix")
        public int prefix;

        @SerializedName("conversion_factor")
        public String conversion_factor;

        @SerializedName("spot_market_id")
        public String spot_market_id;

        @SerializedName("liquidation_market_id")
        public String liquidation_market_id;

        public String getDpMarketId() {
            if (!TextUtils.isEmpty(denom) && !TextUtils.isEmpty(debt_limit.denom)) {
                return denom.toUpperCase() + " : " + debt_limit.denom.toUpperCase();
            }
            return "";
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
            if (!TextUtils.isEmpty(spot_market_id))
                return spot_market_id.replace(":","")   +".png";
            return "";
        }
    }

}
