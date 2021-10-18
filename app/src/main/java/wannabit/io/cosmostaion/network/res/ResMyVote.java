package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import wannabit.io.cosmostaion.model.type.Vote;

public class ResMyVote {

    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public Vote result;
}
