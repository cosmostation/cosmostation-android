package wannabit.io.cosmostaion.dao;

import com.google.gson.annotations.SerializedName;

public class Assets {
    @SerializedName("denom")
    public String denom;

    @SerializedName("origin_chain")
    public String origin_chain;

    @SerializedName("origin_symbol")
    public String origin_symbol;

    @SerializedName("display_symbol")
    public String display_symbol;

    @SerializedName("decimal")
    public int decimal;

    @SerializedName("logo")
    public String logo;
}
