package wannabit.io.cosmostaion.dao;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.base.BaseConstant;

public class Cw20Asset {
    @SerializedName("id")
    public String id;

    @SerializedName("chain")
    public String chain;

    @SerializedName("contract_address")
    public String contract_address;

    @SerializedName("denom")
    public String denom;

    @SerializedName("decimal")
    public int decimal;

    @SerializedName("display")
    public int display;

    @SerializedName("logo")
    public String logo;

    @SerializedName("default")
    public boolean default_show;

    @SerializedName("total_supply")
    public long total_supply;

    @SerializedName("coingecko_id")
    public String coingecko_id;

    public String amount = "0";

    public BigDecimal getAmount() {
        if (amount != null) {
            return new BigDecimal(amount);
        } else {
            return BigDecimal.ZERO;
        }
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String assetImg() {
        if (logo.startsWith("https://"))  {
            return logo;
        } else {
            return BaseConstant.ASSET_IMG_URL + logo;
        }
    }
}
