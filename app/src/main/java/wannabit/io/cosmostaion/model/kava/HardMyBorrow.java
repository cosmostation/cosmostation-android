package wannabit.io.cosmostaion.model.kava;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Coin;

public class HardMyBorrow {
    @SerializedName("borrower")
    public String borrower;

    @SerializedName("amount")
    public ArrayList<Coin> amount;

    @SerializedName("index")
    public ArrayList<HardIndex> index;
}
