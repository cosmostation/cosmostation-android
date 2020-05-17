package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

public class ResLcdProposer {

    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public Proposer result;


    public class Proposer {
        @SerializedName("proposal_id")
        public String id;

        @SerializedName("proposer")
        public String proposer;
    }




}
