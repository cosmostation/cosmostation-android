package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.RewardInfo;

public class ResLcdAllRewards {

    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public AllRewardResult result;

    public class AllRewardResult {
        @SerializedName("rewards")
        public ArrayList<RewardInfo> rewards;
    }
}
