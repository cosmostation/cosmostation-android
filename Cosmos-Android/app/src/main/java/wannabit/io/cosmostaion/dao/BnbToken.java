package wannabit.io.cosmostaion.dao;

import com.google.gson.annotations.SerializedName;

public class BnbToken {

    @SerializedName("symbol")
    public String symbol;

    @SerializedName("original_symbol")
    public String original_symbol;

    @SerializedName("name")
    public String name;

    @SerializedName("owner")
    public String owner;

    @SerializedName("total_supply")
    public String total_supply;

    @SerializedName("mintable")
    public boolean mintable;
}
