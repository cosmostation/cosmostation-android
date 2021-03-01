package wannabit.io.cosmostaion.model.kava;

import com.google.gson.annotations.SerializedName;

public class HardInterestRate {
    @SerializedName("denom")
    public String denom;

    @SerializedName("supply_interest_rate")
    public String supply_interest_rate;

    @SerializedName("borrow_interest_rate")
    public String borrow_interest_rate;
}
