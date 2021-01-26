package wannabit.io.cosmostaion.model;

import com.google.gson.annotations.SerializedName;

public class IrisToken_V1 {
    @SerializedName("@type")
    public String type;

    @SerializedName("symbol")
    public String symbol;

    @SerializedName("name")
    public String name;

    @SerializedName("scale")
    public int scale;

    @SerializedName("min_unit")
    public String min_unit;

    @SerializedName("initial_supply")
    public String initial_supply;

    @SerializedName("max_supply")
    public String max_supply;

    @SerializedName("mintable")
    public boolean mintable;

    @SerializedName("owner")
    public String owner;
}
