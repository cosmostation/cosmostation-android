package wannabit.io.cosmostaion.dao;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class Cw20Assets {
    @SerializedName("id")
    public String id;

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

    public String amount = "0";

    public BigDecimal getAmount() {
        return new BigDecimal(amount);
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
