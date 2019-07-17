package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class ResLcdIrisPool {

    @SerializedName("loose_tokens")
    public String loose_tokens;

    @SerializedName("bonded_tokens")
    public String bonded_tokens;

    @SerializedName("total_supply")
    public String total_supply;

    @SerializedName("bonded_ratio")
    public String bonded_ratio;


    public BigDecimal geTotal() {
        return new BigDecimal(total_supply);
    }

    public BigDecimal getBonded() {
        return new BigDecimal(bonded_tokens);
    }
}
