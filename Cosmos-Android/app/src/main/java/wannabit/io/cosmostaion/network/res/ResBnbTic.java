package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

public class ResBnbTic {

    @SerializedName("symbol")
    public String symbol;

    @SerializedName("baseAssetName")
    public String baseAssetName;

    @SerializedName("quoteAssetName")
    public String quoteAssetName;

    @SerializedName("lastPrice")
    public String lastPrice;
}
