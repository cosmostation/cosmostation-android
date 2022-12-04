package wannabit.io.cosmostaion.dao;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.base.BaseConstant;

public class MintscanToken {
    @SerializedName("id")
    public int id;

    @SerializedName("chainName")
    public String chainName;

    @SerializedName("address")
    public String address;

    @SerializedName("symbol")
    public String symbol;

    @SerializedName("decimals")
    public int decimals;

    @SerializedName("description")
    public String description;

    @SerializedName("display")
    public int display;

    @SerializedName("image")
    public String image;

    @SerializedName("default")
    public boolean default_show;

    @SerializedName("totalSupply")
    public String totalSupply;

    @SerializedName("coinGeckoId")
    public String coinGeckoId;

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
        return BaseConstant.CHAIN_BASE_URL + image;
    }
}
