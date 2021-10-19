package wannabit.io.cosmostaion.model.kava;

import com.google.gson.annotations.SerializedName;

public class AuctionParam {
    @SerializedName("max_auction_duration")
    public String max_auction_duration;

    @SerializedName("bid_duration")
    public String bid_duration;

    @SerializedName("increment_surplus")
    public String increment_surplus;

    @SerializedName("increment_debt")
    public String increment_debt;

    @SerializedName("increment_collateral")
    public String increment_collateral;
}
