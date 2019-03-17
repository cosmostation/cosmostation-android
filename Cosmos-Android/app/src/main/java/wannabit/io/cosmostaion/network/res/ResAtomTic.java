package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class ResAtomTic {
    @SerializedName("data")
    AtomData data;

    @SerializedName("metadata")
    AtomMetadata metadata;

    public AtomData getData() {
        return data;
    }

    public void setData(AtomData data) {
        this.data = data;
    }

    public AtomMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(AtomMetadata metadata) {
        this.metadata = metadata;
    }

    public class AtomData {
        @SerializedName("id")
        int id;

        @SerializedName("name")
        String name;

        @SerializedName("symbol")
        String symbol;

        @SerializedName("website_slug")
        String website_slug;

        @SerializedName("rank")
        int rank;

        @SerializedName("circulating_supply")
        Double circulating_supply;

        @SerializedName("total_supply")
        Double total_supply;

        @SerializedName("max_supply")
        Double max_supply;

        @SerializedName("quotes")
        Map<String, AtomQuotesEntry> quotesMap = new HashMap<String, AtomQuotesEntry>();

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getWebsite_slug() {
            return website_slug;
        }

        public void setWebsite_slug(String website_slug) {
            this.website_slug = website_slug;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public Double getCirculating_supply() {
            return circulating_supply;
        }

        public void setCirculating_supply(Double circulating_supply) {
            this.circulating_supply = circulating_supply;
        }

        public Double getTotal_supply() {
            return total_supply;
        }

        public void setTotal_supply(Double total_supply) {
            this.total_supply = total_supply;
        }

        public Double getMax_supply() {
            return max_supply;
        }

        public void setMax_supply(Double max_supply) {
            this.max_supply = max_supply;
        }

        public Map<String, AtomQuotesEntry> getQuotesMap() {
            return quotesMap;
        }

        public void setQuotesMap(Map<String, AtomQuotesEntry> quotesMap) {
            this.quotesMap = quotesMap;
        }
    }

    public class AtomMetadata {

        @SerializedName("timestamp")
        public Long timestamp;

        @SerializedName("error")
        public String error;
    }


    public class AtomQuotesEntry {
        @SerializedName("price")
        Double price;

        @SerializedName("volume_24h")
        Double volume_24h;

        @SerializedName("market_cap")
        Double market_cap;

        @SerializedName("percent_change_1h")
        Double percent_change_1h;

        @SerializedName("percent_change_24h")
        Double percent_change_24h;

        @SerializedName("percent_change_7d")
        Double percent_change_7d;

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Double getVolume_24h() {
            return volume_24h;
        }

        public void setVolume_24h(Double volume_24h) {
            this.volume_24h = volume_24h;
        }

        public Double getMarket_cap() {
            return market_cap;
        }

        public void setMarket_cap(Double market_cap) {
            this.market_cap = market_cap;
        }

        public Double getPercent_change_1h() {
            return percent_change_1h;
        }

        public void setPercent_change_1h(Double percent_change_1h) {
            this.percent_change_1h = percent_change_1h;
        }

        public Double getPercent_change_24h() {
            return percent_change_24h;
        }

        public void setPercent_change_24h(Double percent_change_24h) {
            this.percent_change_24h = percent_change_24h;
        }

        public Double getPercent_change_7d() {
            return percent_change_7d;
        }

        public void setPercent_change_7d(Double percent_change_7d) {
            this.percent_change_7d = percent_change_7d;
        }
    }
}
