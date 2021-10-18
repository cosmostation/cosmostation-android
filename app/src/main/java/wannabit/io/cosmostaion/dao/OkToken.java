package wannabit.io.cosmostaion.dao;

import com.google.gson.annotations.SerializedName;

public class OkToken {
    @SerializedName("description")
    public String description;

    @SerializedName("symbol")
    public String symbol;

    @SerializedName("original_symbol")
    public String original_symbol;

    @SerializedName("whole_name")
    public String whole_name;

    @SerializedName("original_total_supply")
    public String original_total_supply;

    @SerializedName("total_supply")
    public String total_supply;

    @SerializedName("owner")
    public String owner;

    @SerializedName("mintable")
    public Boolean mintable;

}
