package wannabit.io.cosmostaion.model.kava;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Coin;

public class HardMyDeposit {
    @SerializedName("depositor")
    public String depositor;

    @SerializedName("amount")
    public ArrayList<Coin> amount;

    @SerializedName("index")
    public ArrayList<HardIndex> index;
}
