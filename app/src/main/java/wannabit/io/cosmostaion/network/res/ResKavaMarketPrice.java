package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import wannabit.io.cosmostaion.model.kava.MarketPrice;

public class ResKavaMarketPrice {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public MarketPrice result;
}
