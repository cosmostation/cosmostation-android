package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Coin;

public class ResKavaCdpParam {

    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public Result result;

    public class Result {
        @SerializedName("surplus_auction_threshold")
        public String surplus_auction_threshold;

        @SerializedName("debt_auction_threshold")
        public String debt_auction_threshold;

        @SerializedName("circuit_breaker")
        public Boolean circuit_breaker;

        @SerializedName("global_debt_limit")
        public ArrayList<Coin> global_debt_limit;

        @SerializedName("debt_params")
        public ArrayList<KavaCdpDebtParam> debt_params;

        @SerializedName("collateral_params")
        public ArrayList<KavaCollateralParam> collateral_params;

    }

    public class KavaCdpDebtParam {
        @SerializedName("denom")
        public String denom;

        @SerializedName("reference_asset")
        public String reference_asset;

        @SerializedName("conversion_factor")
        public String conversion_factor;

        @SerializedName("debt_floor")
        public String debt_floor;
    }

    public class KavaCollateralParam {
        @SerializedName("denom")
        public String denom;

        @SerializedName("liquidation_ratio")
        public String liquidation_ratio;

        @SerializedName("debt_limit")
        public ArrayList<Coin> debt_limit;

        @SerializedName("stability_fee")
        public String stability_fee;

        @SerializedName("auction_size")
        public String auction_size;

        @SerializedName("liquidation_penalty")
        public String liquidation_penalty;

        @SerializedName("prefix")
        public int prefix;

        @SerializedName("market_id")
        public String market_id;

        @SerializedName("conversion_factor")
        public String conversion_factor;

    }

}
