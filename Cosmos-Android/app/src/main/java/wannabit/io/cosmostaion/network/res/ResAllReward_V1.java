package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.Reward_V1;

public class ResAllReward_V1 {
    @SerializedName("rewards")
    public ArrayList<Reward_V1> rewards;
}
