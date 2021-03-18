package wannabit.io.cosmostaion.model.kava;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.model.type.Coin;

public class CollateralParam {
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
