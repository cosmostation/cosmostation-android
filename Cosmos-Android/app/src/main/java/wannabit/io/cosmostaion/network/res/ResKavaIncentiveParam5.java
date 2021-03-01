package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResKavaIncentiveParam5 {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public IncentiveParam5 result;

    public class IncentiveParam5 {
        @SerializedName("claim_multipliers")
        public ArrayList<KavaClaimMultiplier5> claim_multipliers;

    }

    public class KavaClaimMultiplier5 {
        @SerializedName("name")
        public String name;

        @SerializedName("months_lockup")
        public String months_lockup;

        @SerializedName("factor")
        public String factor;

    }
}
