package wannabit.io.cosmostaion.model;

import com.google.gson.annotations.SerializedName;

public class Commission_V1 {
    @SerializedName("commission_rates")
    public CommissionRate_V1 commission_rates;

    @SerializedName("update_time")
    public String update_time;

}
