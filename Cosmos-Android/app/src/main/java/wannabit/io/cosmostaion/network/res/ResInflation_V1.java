package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class ResInflation_V1 {
    @SerializedName("inflation")
    public String inflation;

    public BigDecimal getInflation() {
        if (inflation == null) return BigDecimal.ZERO;
        return new BigDecimal(inflation);
    }
}
