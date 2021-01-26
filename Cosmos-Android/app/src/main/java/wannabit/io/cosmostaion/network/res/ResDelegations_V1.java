package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.Delegation_V1;

public class ResDelegations_V1 {
    @SerializedName("delegation_responses")
    public ArrayList<Delegation_V1> delegation_responses;

}
