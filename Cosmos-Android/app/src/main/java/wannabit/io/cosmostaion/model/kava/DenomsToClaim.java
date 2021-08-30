package wannabit.io.cosmostaion.model.kava;

import com.google.gson.annotations.SerializedName;

public class DenomsToClaim {
    @SerializedName("denom")
    public String denom;

    @SerializedName("multiplier_name")
    public String multiplier_name;

    public DenomsToClaim(String denom, String multiplier_name) {
        this.denom = denom;
        this.multiplier_name = multiplier_name;
    }
}
