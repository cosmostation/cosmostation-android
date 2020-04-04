package wannabit.io.cosmostaion.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Coin;

public class KavaCDP {

    @SerializedName("id")
    public String id;

    @SerializedName("owner")
    public String owner;

    @SerializedName("collateral")
    public ArrayList<Coin> collateral;

    @SerializedName("principal")
    public ArrayList<Coin> principal;

    @SerializedName("accumulated_fees")
    public ArrayList<Coin> accumulated_fees;

    @SerializedName("fees_updated")
    public String fees_updated;

    public BigDecimal getAccumulatedFees() {
        BigDecimal result = BigDecimal.ZERO;
        if (accumulated_fees != null && accumulated_fees.size() > 0) {
            result = new BigDecimal(accumulated_fees.get(0).amount);
        }
        return result;
    }

    public int getCdpId() {
        return Integer.parseInt(id);
    }

}
