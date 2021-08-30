package wannabit.io.cosmostaion.model.kava;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ClaimMultiplier {
    @SerializedName("denom")
    public String denom;

    @SerializedName("multipliers")
    public ArrayList<Multiplier> multipliers;

    public class Multiplier {
        @SerializedName("name")
        public String name;

        @SerializedName("months_lockup")
        public String months_lockup;

        @SerializedName("factor")
        public String factor;
    }
}
