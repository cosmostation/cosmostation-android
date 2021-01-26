package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Coin;

public class ResBalance_V1 {
    @SerializedName("balances")
    public ArrayList<Coin> balances;
}
