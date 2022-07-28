package wannabit.io.cosmostaion.model.type;

import com.google.gson.annotations.SerializedName;

public class Vote {

    public final static String OPTION_YES           = "Yes";
    public final static String OPTION_NO            = "No";
    public final static String OPTION_VETO          = "NoWithVeto";
    public final static String OPTION_ABSTAIN       = "Abstain";

    @SerializedName("voter")
    public String voter;

    @SerializedName("proposal_id")
    public String proposal_id;

    @SerializedName("option")
    public String option;

    @SerializedName("tx_hash")
    public String tx_hash;

    @SerializedName("timestamp")
    public String timestamp;

    @SerializedName("answer")
    public String answer;

}
