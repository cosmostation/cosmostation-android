package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResKavaPriceFeedParam {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public KavaPriceParam result;

    public class KavaPriceParam {
        @SerializedName("markets")
        public ArrayList<KavaPriceMarket> markets;

    }

    public class KavaPriceMarket {
        @SerializedName("market_id")
        public String market_id;

        @SerializedName("base_asset")
        public String base_asset;

        @SerializedName("quote_asset")
        public String quote_asset;

        @SerializedName("active")
        public boolean active;

    }
}

