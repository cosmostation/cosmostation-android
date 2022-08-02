package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResVoteStatus {
    @SerializedName("votes")
    public ArrayList<VotesData> votes;

    public class VotesData {
        @SerializedName("proposal_id")
        public int id;

        @SerializedName("votes")
        public ArrayList<VoteDetail> voteDetails;

    }

    public class VoteDetail {
        @SerializedName("voter")
        public String voter;

        @SerializedName("option")
        public String option;

        @SerializedName("tx_hash")
        public String tx_hash;

        @SerializedName("timestamp")
        public String timestamp;
    }
}
