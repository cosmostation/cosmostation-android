package wannabit.io.cosmostaion.model.type;

import com.google.gson.annotations.SerializedName;

public class IrisVote {

    @SerializedName("voter")
    public String voter;

    @SerializedName("proposal_id")
    public String proposal_id;

    @SerializedName("option")
    public String option;
}
