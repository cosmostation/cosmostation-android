package wannabit.io.cosmostaion.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class ParamMint_V1 {
    @SerializedName("mint_denom")
    public String mint_denom;

    @SerializedName("inflation_rate_change")
    public String inflation_rate_change;

    @SerializedName("inflation_max")
    public String inflation_max;

    @SerializedName("inflation_min")
    public String inflation_min;

    @SerializedName("goal_bonded")
    public String goal_bonded;

    @SerializedName("blocks_per_year")
    public String blocks_per_year;


    //for Iris
    @SerializedName("inflation")
    public String inflation;

    public BigDecimal getInflation() {
        if (inflation == null) return BigDecimal.ZERO;
        return new BigDecimal(inflation);
    }
}
