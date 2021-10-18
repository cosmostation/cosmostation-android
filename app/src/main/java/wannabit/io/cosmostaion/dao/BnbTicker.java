package wannabit.io.cosmostaion.dao;

import com.google.gson.annotations.SerializedName;

public class BnbTicker {
    @SerializedName("symbol")
    public String symbol;

    @SerializedName("baseAssetName")
    public String baseAssetName;

    @SerializedName("quoteAssetName")
    public String quoteAssetName;

    @SerializedName("lastPrice")
    public String lastPrice;
}
