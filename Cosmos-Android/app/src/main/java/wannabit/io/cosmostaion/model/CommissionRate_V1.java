package wannabit.io.cosmostaion.model;

import com.google.gson.annotations.SerializedName;

public class CommissionRate_V1 {
    @SerializedName("rate")
    public String rate;

    @SerializedName("max_rate")
    public String max_rate;

    @SerializedName("max_change_rate")
    public String max_change_rate;
}
