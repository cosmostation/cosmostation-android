package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResKavaHardParam {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public HardParam result;


    public class HardParam {
        @SerializedName("money_markets")
        public ArrayList<HardMoneyMarket> money_markets;

        @SerializedName("minimum_borrow_usd_value")
        public String minimum_borrow_usd_value;

    }

    public class HardMoneyMarket {
        @SerializedName("denom")
        public String denom;

        @SerializedName("borrow_limit")
        public BorrowLimit borrow_limit;

        @SerializedName("spot_market_id")
        public String spot_market_id;

        @SerializedName("conversion_factor")
        public String conversion_factor;

        @SerializedName("interest_rate_model")
        public InterestRateModel interest_rate_model;

        @SerializedName("reserve_factor")
        public String reserve_factor;

        @SerializedName("keeper_reward_percentage")
        public String keeper_reward_percentage;


    }


    public class BorrowLimit {
        @SerializedName("has_max_limit")
        public boolean has_max_limit;

        @SerializedName("maximum_limit")
        public String maximum_limit;

        @SerializedName("loan_to_value")
        public String loan_to_value;
    }

    public class InterestRateModel {
        @SerializedName("base_rate_apy")
        public String base_rate_apy;

        @SerializedName("base_multiplier")
        public String base_multiplier;

        @SerializedName("kink")
        public String kink;

        @SerializedName("jump_multiplier")
        public String jump_multiplier;

    }
}
