package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ResKavaBep3Param {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public KavaBep3Param result;


    public class KavaBep3Param {

        @SerializedName("bnb_deputy_address")
        public String bnb_deputy_address;

        @SerializedName("bnb_deputy_fixed_fee")
        public String bnb_deputy_fixed_fee;

        @SerializedName("min_amount")
        public String min_amount;

        @SerializedName("max_amount")
        public String max_amount;

        @SerializedName("min_block_lock")
        public String min_block_lock;

        @SerializedName("max_block_lock")
        public String max_block_lock;

        @SerializedName("supported_assets")
        public ArrayList<KavaBep3SupportAsset> supported_assets;

    }


    public class KavaBep3SupportAsset {
        @SerializedName("denom")
        public String denom;

        @SerializedName("coin_id")
        public String coin_id;

        @SerializedName("limit")
        public String limit;

        @SerializedName("active")
        public boolean active;
    }

    public KavaBep3SupportAsset getSupportedSwapAsset(String denom) {
        for (KavaBep3SupportAsset asset:result.supported_assets) {
            if (denom.equalsIgnoreCase(asset.denom)) {
                return asset;
            }
        }
        return null;
    }

    public BigDecimal getSupportedSwapAssetLimit(String denom) {
        BigDecimal limit = BigDecimal.ZERO;
        try {
            limit = new BigDecimal(getSupportedSwapAsset(denom).limit);
        } catch (Exception e) {}
        return limit;

    }

    public BigDecimal getSupportedSwapAssetMaxOnce(String denom) {
        BigDecimal once = BigDecimal.ZERO;
        try {
            once = new BigDecimal(result.max_amount);
        } catch (Exception e) {}
        return once;
    }
}
