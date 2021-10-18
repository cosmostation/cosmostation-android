package wannabit.io.cosmostaion.dao;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import wannabit.io.cosmostaion.utils.WLog;

public class Price {
    @SerializedName("denom")
    public String denom;

    @SerializedName("last_updated")
    public String last_updated;

    @SerializedName("prices")
    public ArrayList<Prices> prices;


    public class Prices {
        @SerializedName("currency")
        public String currency;

        @SerializedName("current_price")
        public Double current_price;

        @SerializedName("daily_price_change_in_percentage")
        public Double daily_price_change_in_percentage;
    }

    public BigDecimal currencyPrice(String currency) {
        for (Prices prices: prices) {
            if (prices.currency.equals(currency)) {
                return BigDecimal.valueOf(prices.current_price).setScale(8, RoundingMode.DOWN);
            }
        }
        return BigDecimal.ZERO.setScale(8, RoundingMode.DOWN);
    }

    public BigDecimal priceChange() {
        for (Prices prices: prices) {
            if (prices.currency.equals("usd")) {
                return BigDecimal.valueOf(prices.daily_price_change_in_percentage).setScale(2, RoundingMode.FLOOR);
            }
        }
        return BigDecimal.ZERO.setScale(2, RoundingMode.FLOOR);
    }
}
