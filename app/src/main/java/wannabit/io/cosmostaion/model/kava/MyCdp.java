package wannabit.io.cosmostaion.model.kava;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.math.RoundingMode;

import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class MyCdp {
    @SerializedName("cdp")
    public Cdp cdp;

    @SerializedName("collateral_value")
    public Coin collateral_value;

    @SerializedName("collateralization_ratio")
    public String collateralization_ratio;

    public String getDenom() {
        return cdp.collateral.denom;
    }

    public String getPDenom() {
        return cdp.principal.denom;
    }

    public String getMarketId() {
        return cdp.collateral.denom + ":usd:30";
    }

    public String getDpMarketId() {
        return cdp.collateral.denom.toUpperCase() + " : usdx".toUpperCase() ;
    }

    public String getImagePath() {
        return cdp.collateral.denom + "usd.png";
    }

    public BigDecimal getCollateralAmount() {
        try {
            return new BigDecimal(cdp.collateral.amount);
        } catch (Exception e) {}
        return BigDecimal.ZERO;
    }

    public BigDecimal getPrincipalAmount() {
        try {
            return new BigDecimal(cdp.principal.amount);
        } catch (Exception e) { }
        return BigDecimal.ZERO;
    }

    public BigDecimal getAccumulatedFees() {
        try {
            return new BigDecimal(cdp.accumulated_fees.amount);
        } catch (Exception e) { }
        return BigDecimal.ZERO;
    }

    public BigDecimal getRawDebtAmount() {
        return getPrincipalAmount().add(getAccumulatedFees());
    }

    public BigDecimal getEstimatedTotalFee(Context c, CollateralParam cParam) {
        BigDecimal hiddenFeeValue = WDp.getCdpHiddenFee(c, getRawDebtAmount(), cParam, cdp);
        return  getAccumulatedFees().add(hiddenFeeValue);
    }

    public BigDecimal getEstimatedTotalDebt(Context c, CollateralParam cParam) {
        BigDecimal hiddenFeeValue = WDp.getCdpHiddenFee(c, getRawDebtAmount(), cParam, cdp);
        return  getRawDebtAmount().add(hiddenFeeValue);
    }

    public BigDecimal getLiquidationPrice(Context c, BaseData baseData, CollateralParam cParam) {
        int denomCDecimal = WUtil.getKavaCoinDecimal(baseData, getDenom());
        int denomPDecimal = WUtil.getKavaCoinDecimal(baseData, getPDenom());
        BigDecimal collateralAmount = getCollateralAmount().movePointLeft(denomCDecimal);
        BigDecimal estimatedDebtAmount = getEstimatedTotalDebt(c, cParam).multiply(new BigDecimal(cParam.liquidation_ratio)).movePointLeft(denomPDecimal);
        return estimatedDebtAmount.divide(collateralAmount, denomPDecimal, BigDecimal.ROUND_DOWN);
    }

    public BigDecimal getWithdrawableAmount(Context c, BaseData baseData, CollateralParam cParam, BigDecimal price, BigDecimal selfDeposit) {
        int denomCDecimal = WUtil.getKavaCoinDecimal(baseData, getDenom());
        int denomPDecimal = WUtil.getKavaCoinDecimal(baseData, getPDenom());
        BigDecimal cValue = new BigDecimal(collateral_value.amount);
        BigDecimal minCValue = getEstimatedTotalDebt(c, cParam).multiply(new BigDecimal(cParam.liquidation_ratio)).divide(new BigDecimal("0.95"), 0, BigDecimal.ROUND_DOWN);
        BigDecimal maxWithdrawableValue = cValue.subtract(minCValue);
//            WLog.w("maxWithdrawableValue " + maxWithdrawableValue);
        BigDecimal maxWithdrawableAmount = maxWithdrawableValue.movePointLeft(denomPDecimal - denomCDecimal).divide(price, 0, RoundingMode.DOWN);
//            WLog.w("maxWithdrawableAmount " + maxWithdrawableAmount);

        if (maxWithdrawableAmount.compareTo(selfDeposit) > 0 ) {
            maxWithdrawableAmount =  selfDeposit;
        }
        if (maxWithdrawableAmount.compareTo(BigDecimal.ZERO) <= 0) {
            maxWithdrawableAmount = BigDecimal.ZERO;
        }
        return maxWithdrawableAmount;

    }

    public BigDecimal getMoreLoanableAmount(Context c, CollateralParam cParam) {
        BigDecimal maxDebtValue = new BigDecimal(collateral_value.amount).divide(new BigDecimal(cParam.liquidation_ratio), 0, RoundingMode.DOWN);
//            WLog.w("maxDebtValue " + maxDebtValue);
        maxDebtValue = maxDebtValue.multiply(new BigDecimal("0.95")).setScale(0, RoundingMode.DOWN);
//            WLog.w("maxDebtValue padding " + maxDebtValue);

        maxDebtValue = maxDebtValue.subtract(getEstimatedTotalDebt(c, cParam));
        if (maxDebtValue.compareTo(BigDecimal.ZERO) <= 0) {
            maxDebtValue = BigDecimal.ZERO;
        }
        return maxDebtValue;

    }
}
