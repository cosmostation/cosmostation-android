package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class ResProvision_V1 {
    @SerializedName("annual_provisions")
    public String annual_provisions;

    public BigDecimal getProvision() {
        if (annual_provisions == null) return BigDecimal.ZERO;
        return new BigDecimal(annual_provisions);
    }
}
