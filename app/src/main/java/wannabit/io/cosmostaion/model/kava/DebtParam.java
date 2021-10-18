package wannabit.io.cosmostaion.model.kava;

import com.google.gson.annotations.SerializedName;

public class DebtParam {
    @SerializedName("denom")
    public String denom;

    @SerializedName("reference_asset")
    public String reference_asset;

    @SerializedName("conversion_factor")
    public String conversion_factor;

    @SerializedName("debt_floor")
    public String debt_floor;

    @SerializedName("savings_rate")
    public String savings_rate;
}
