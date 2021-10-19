package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Coin;

public class ResKavaSupply {

    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public ArrayList<Coin> result;

    public BigDecimal getDebtAmount() {
        BigDecimal result = BigDecimal.ZERO;
        if (getDebt() != null) {
            result = new BigDecimal(getDebt().amount);
        }
        return result;
    }

    public Coin getDebt() {
        if (result != null) {
            for (Coin coin:result) {
                if (coin.denom.equalsIgnoreCase("debt")) {
                    return coin;
                }
            }
        }
        return null;
    }

}
