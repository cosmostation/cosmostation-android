package wannabit.io.cosmostaion.model.type;

import com.google.gson.annotations.SerializedName;

public class Delegation {

    @SerializedName("denom")
    public String denom;

    @SerializedName("amount")
    public String amount;
}
