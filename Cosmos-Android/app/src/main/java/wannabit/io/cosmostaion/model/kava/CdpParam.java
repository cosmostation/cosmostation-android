package wannabit.io.cosmostaion.model.kava;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Coin;

public class CdpParam {
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
    public DebtParam debt_param;

    @SerializedName("collateral_params")
    public ArrayList<CollateralParam> collateral_params;

    public BigDecimal getGlobalDebtAmount() {
        BigDecimal result = BigDecimal.ZERO;
        if (global_debt_limit != null) {
            result = new BigDecimal(global_debt_limit.amount);
        }
        return result;
    }

    public BigDecimal getRawLiquidationRatio(String denom) {
        for (CollateralParam param : collateral_params) {
            if (param.denom.equals(denom)) {
                return new BigDecimal(param.liquidation_ratio);
            }
        }
        return BigDecimal.ZERO;
    }

    //only using for price
    public CollateralParam getCollateralParamByDenom(String denom) {
        CollateralParam result = null;
        for (CollateralParam collateralParam:collateral_params) {
            if (collateralParam.denom.equals(denom)) {
                return collateralParam;
            }
        }
        return  result;
    }


    public CollateralParam getCollateralParamByType(String type) {
        CollateralParam result = null;
        for (CollateralParam collateralParam:collateral_params) {
            if (collateralParam.type.equals(type)) {
                return collateralParam;
            }
        }
        return result;
    }

}
