package wannabit.io.cosmostaion.model.type;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Output {

    @SerializedName("address")
    public String address;

    @SerializedName("coins")
    public ArrayList<Coin> coins;

    public Output(String address, ArrayList<Coin> coins) {
        this.address = address;
        this.coins = coins;
    }
}
