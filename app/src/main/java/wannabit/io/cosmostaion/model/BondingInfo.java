package wannabit.io.cosmostaion.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.model.type.Coin;

public class BondingInfo {
    @SerializedName("delegator_address")
    public String delegator_address;

    @SerializedName("validator_address")
    public String validator_address;

    @SerializedName("shares")
    public String shares;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @SerializedName("balance")
    public Object balance;

    public BigDecimal getBalance() {
        try {
            Coin temp = new Gson().fromJson(new Gson().toJson(balance), Coin.class);
            return new BigDecimal(temp.amount);

        } catch (Exception e) {
        }

        try {
            String temp = new Gson().fromJson(new Gson().toJson(balance), String.class);
            return new BigDecimal(temp);
        } catch (Exception e) {
        }

        return BigDecimal.ZERO;
    }
}
