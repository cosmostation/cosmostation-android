package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

public class ResLcdProposer {

    //TODO remake for cosmoshub-3
    @SerializedName("proposal_id")
    public String id;

    @SerializedName("proposer")
    public String proposer;
}
