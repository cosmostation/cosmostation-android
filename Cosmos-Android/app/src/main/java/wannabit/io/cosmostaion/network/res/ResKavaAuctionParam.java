package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

public class ResKavaAuctionParam {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public AuctionParam result;

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
}
