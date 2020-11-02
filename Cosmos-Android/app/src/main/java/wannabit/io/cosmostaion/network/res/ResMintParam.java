package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

public class ResMintParam {

    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public MintParam result;


    public class MintParam {

        @SerializedName("mint_denom")
        public String mint_denom;

        @SerializedName("goal_bonded")
        public String goal_bonded;

        @SerializedName("blocks_per_year")
        public String blocks_per_year;

        @SerializedName("inflation_min")
        public String inflation_min;

        @SerializedName("inflation_max")
        public String inflation_max;

        @SerializedName("inflation_rate_change")
        public String inflation_rate_change;

    }
}
