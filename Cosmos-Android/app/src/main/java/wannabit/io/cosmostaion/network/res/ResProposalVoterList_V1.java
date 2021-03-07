package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.Vote_V1;

public class ResProposalVoterList_V1 {
    @SerializedName("votes")
    public ArrayList<Vote_V1> votes;
}
