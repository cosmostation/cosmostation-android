package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

public class ResLcdProposalVoted {

    //TODO remake for cosmoshub-3
    @SerializedName("voter")
    public String voter;

    @SerializedName("proposal_id")
    public String proposal_id;

    @SerializedName("option")
    public String option;
}
