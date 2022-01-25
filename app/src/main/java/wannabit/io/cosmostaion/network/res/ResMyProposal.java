package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResMyProposal {
    @SerializedName("vote")
    public Vote vote;

    public class Vote {
        @SerializedName("proposal_id")
        public String proposal_id;

        @SerializedName("voter")
        public String voter;

        @SerializedName("option")
        public String option;

        @SerializedName("options")
        public ArrayList<Options> options;

        public class Options {
            @SerializedName("option")
            public String option;

            @SerializedName("weight")
            public String weight;
        }
    }
}
