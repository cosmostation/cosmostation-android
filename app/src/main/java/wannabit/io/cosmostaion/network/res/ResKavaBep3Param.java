package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ResKavaBep3Param {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public KavaBep3AssetParams result;


    public class KavaBep3AssetParams {
        @SerializedName("asset_params")
        public ArrayList<KavaBep3AssetParam> asset_params;
    }

    public class KavaBep3AssetParam {
        @SerializedName("denom")
        public String denom;

        @SerializedName("coin_id")
        public String coin_id;

        @SerializedName("active")
        public boolean active;

        @SerializedName("deputy_address")
        public String deputy_address;

        @SerializedName("fixed_fee")
        public String fixed_fee;

        @SerializedName("min_swap_amount")
        public String min_swap_amount;

        @SerializedName("max_swap_amount")
        public String max_swap_amount;

        @SerializedName("min_block_lock")
        public String min_block_lock;

        @SerializedName("max_block_lock")
        public String max_block_lock;

        @SerializedName("supply_limit")
        public KavaBep3SupplyLimit supply_limit;

    }

    public class KavaBep3SupplyLimit {
        @SerializedName("limit")
        public String limit;

        @SerializedName("time_limited")
        public boolean time_limited;

        @SerializedName("time_period")
        public String time_period;

        @SerializedName("time_based_limit")
        public String time_based_limit;

    }


    public KavaBep3AssetParam getSupportedSwapAsset(String denom) {
        for (KavaBep3AssetParam asset : result.asset_params) {
            if (denom.toLowerCase().startsWith(asset.denom.toLowerCase())) {
                return asset;
            }
            if (denom.toLowerCase().startsWith("xrp") && asset.denom.toLowerCase().startsWith("xrp")) {
                return asset;
            }
        }
        return null;
    }

    public BigDecimal getSupportedSwapAssetLimit(String denom) {
        BigDecimal limit = BigDecimal.ZERO;
        try {
            limit = new BigDecimal(getSupportedSwapAsset(denom).supply_limit.limit);
        } catch (Exception e) {
        }
        return limit;
    }

    public BigDecimal getSupportedSwapAssetMaxOnce(String denom) {
        BigDecimal once = BigDecimal.ZERO;
        try {
            once = new BigDecimal(getSupportedSwapAsset(denom).max_swap_amount);
        } catch (Exception e) {
        }
        return once;
    }


    public BigDecimal getSupportedSwapAssetMin(String denom) {
        BigDecimal once = BigDecimal.ZERO;
        try {
            once = new BigDecimal(getSupportedSwapAsset(denom).min_swap_amount).add(getSupportedSwapAssetFee(denom));
        } catch (Exception e) {
        }
        return once;
    }

    public BigDecimal getSupportedSwapAssetFee(String denom) {
        BigDecimal once = BigDecimal.ZERO;
        try {
            once = new BigDecimal(getSupportedSwapAsset(denom).fixed_fee);
        } catch (Exception e) {
        }
        return once;
    }

}
