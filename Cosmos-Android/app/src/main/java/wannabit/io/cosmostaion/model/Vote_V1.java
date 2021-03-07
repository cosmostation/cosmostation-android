package wannabit.io.cosmostaion.model;

import com.google.gson.annotations.SerializedName;

public class Vote_V1 {
    public final static String OPTION_YES           = "VOTE_OPTION_YES";
    public final static String OPTION_NO            = "VOTE_OPTION_NO";
    public final static String OPTION_VETO          = "VOTE_OPTION_NO_WITH_VETO";
    public final static String OPTION_ABSTAIN       = "VOTE_OPTION_ABSTAIN";

    @SerializedName("proposal_id")
    public String proposal_id;

    @SerializedName("voter")
    public String voter;

    @SerializedName("option")
    public String option;

//    public enum VoteOption_V1 {
//        Unsepcified(0), Yes(1), Abstain(2), No(3), NoWithVeto(4);
//
//        VoteOption_V1(int value) {
//            value = value;
//        }
//    }

}
