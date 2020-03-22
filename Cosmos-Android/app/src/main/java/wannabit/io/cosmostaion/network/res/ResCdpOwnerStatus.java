package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.model.KavaCDP;
import wannabit.io.cosmostaion.model.type.Coin;

public class ResCdpOwnerStatus {

    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public Result result;


    public class Result {

        @SerializedName("cdp")
        public KavaCDP cdp;

        @SerializedName("collateral_value")
        public Coin collateral_value;

        @SerializedName("collateralization_ratio")
        public String collateralization_ratio;

        public String getDenom() {
            return cdp.collateral.get(0).denom;
        }

        public String getPDenom() {
            return cdp.principal.get(0).denom;
        }

        public String getMarketId() {
            return cdp.collateral.get(0).denom + ":usd";
        }

        public String getDpMarketId() {
            return cdp.collateral.get(0).denom.toUpperCase() + " : usdx".toUpperCase() ;
        }

        public String getImagePath() {
            return cdp.collateral.get(0).denom + "usd.png";
        }

        public BigDecimal getCollateralAmount() {
            try {
                return new BigDecimal(cdp.collateral.get(0).amount);
            } catch (Exception e) {}
            return BigDecimal.ZERO;
        }

        public BigDecimal getPrincipalAmount() {
            try {
                return new BigDecimal(cdp.principal.get(0).amount);
            } catch (Exception e) { }
            return BigDecimal.ZERO;
        }

        public BigDecimal getAccumulatedFees() {
            try {
                return new BigDecimal(cdp.accumulated_fees.get(0).amount);
            } catch (Exception e) { }
            return BigDecimal.ZERO;
        }
    }


}
