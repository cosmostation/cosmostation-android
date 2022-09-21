package wannabit.io.cosmostaion.dao;

import com.google.gson.annotations.SerializedName;

public class Price {
    @SerializedName("denom")
    public String denom;

    @SerializedName("current_price")
    public Double current_price;

    @SerializedName("daily_price_change_in_percent")
    public Double daily_price_change_in_percentage;

}
