package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import wannabit.io.cosmostaion.model.kava.AuctionParam;

public class ResKavaAuctionParam {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public AuctionParam result;
}
