package wannabit.io.cosmostaion.model.type;

import com.google.gson.annotations.SerializedName;

public class Coin {

    @SerializedName("denom")
    public String denom;

    @SerializedName("amount")
    public String amount;


    public Coin() {
    }

    public Coin(String denom, String amount) {
        this.denom = denom;
        this.amount = amount;
    }
}
