package wannabit.io.cosmostaion.dao;

import com.google.gson.annotations.SerializedName;

public class IrisToken {

    @SerializedName("base_token")
    public BaseToken base_token;

    public class BaseToken {
        @SerializedName("id")
        public String id;

        @SerializedName("family")
        public String family;

        @SerializedName("source")
        public String source;

        @SerializedName("gateway")
        public String gateway;

        @SerializedName("symbol")
        public String symbol;

        @SerializedName("name")
        public String name;

        @SerializedName("decimal")
        public int decimal;

        @SerializedName("canonical_symbol")
        public String canonical_symbol;

        @SerializedName("min_unit_alias")
        public String min_unit_alias;
    }


}
