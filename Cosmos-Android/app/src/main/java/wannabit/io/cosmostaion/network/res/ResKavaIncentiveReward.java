package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import wannabit.io.cosmostaion.model.kava.IncentiveReward;

public class ResKavaIncentiveReward {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public IncentiveReward result;
}
