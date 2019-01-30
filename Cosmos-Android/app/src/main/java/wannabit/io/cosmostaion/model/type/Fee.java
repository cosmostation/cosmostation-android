package wannabit.io.cosmostaion.model.type;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Fee {

    @SerializedName("amount")
    public ArrayList<Coin> amount;

    @SerializedName("gas")
    public String gas;

}
