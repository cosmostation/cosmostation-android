package wannabit.io.cosmostaion.model.kava;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class IncentiveParam {
    @SerializedName("claim_multipliers")
    public ArrayList<ClaimMultiplier> claim_multipliers;
}
