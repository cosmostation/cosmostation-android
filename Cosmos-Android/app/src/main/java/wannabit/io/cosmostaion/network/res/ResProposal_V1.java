package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.Proposal_V1;

public class ResProposal_V1 {
    @SerializedName("proposals")
    public ArrayList<Proposal_V1> proposals;
}
