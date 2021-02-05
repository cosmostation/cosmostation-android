package wannabit.io.cosmostaion.dao;

import com.google.gson.annotations.SerializedName;

public class OkTicker {
    @SerializedName("close")
    public String close;

    @SerializedName("high")
    public String high;

    @SerializedName("low")
    public String low;

    @SerializedName("open")
    public String open;

    @SerializedName("price")
    public String price;

    @SerializedName("product")
    public String product;

    @SerializedName("symbol")
    public String symbol;

    @SerializedName("timestamp")
    public String timestamp;

    @SerializedName("volume")
    public String volume;
}
