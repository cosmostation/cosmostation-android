package wannabit.io.cosmostaion.model.kava;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class KavaPriceMarket {
    @SerializedName("markets")
    public ArrayList<Market> markets;

    public class Market {
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
