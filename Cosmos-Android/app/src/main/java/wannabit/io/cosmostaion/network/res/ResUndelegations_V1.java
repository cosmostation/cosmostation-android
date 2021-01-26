package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.Undelegation_V1;

public class ResUndelegations_V1 {
    @SerializedName("unbonding_responses")
    public ArrayList<Undelegation_V1> unbonding_responses;
}
