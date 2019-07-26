package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

public class ResCgcTic {

    @SerializedName("market_data")
    public MarketData market_data;



    public class MarketData {
        @SerializedName("current_price")
        public CurrentPrice current_price;


        @SerializedName("price_change_percentage_24h_in_currency")
        public Change24Price price_change_24h;
    }

    public class CurrentPrice {
        @SerializedName("usd")
        public Double usd;

        @SerializedName("eur")
        public Double eur;

        @SerializedName("krw")
        public Double krw;

        @SerializedName("jpy")
        public Double jpy;

        @SerializedName("cny")
        public Double cny;

        @SerializedName("btc")
        public Double btc;

    }

    public class Change24Price {
        @SerializedName("usd")
        public Double usd;

        @SerializedName("eur")
        public Double eur;

        @SerializedName("krw")
        public Double krw;

        @SerializedName("jpy")
        public Double jpy;

        @SerializedName("cny")
        public Double cny;

        @SerializedName("btc")
        public Double btc;

    }
}
