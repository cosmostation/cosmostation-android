package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import wannabit.io.cosmostaion.model.Vote_V1;

public class ResProposalMyVoted_V1 {
    @SerializedName("vote")
    public Vote_V1 vote;
}
