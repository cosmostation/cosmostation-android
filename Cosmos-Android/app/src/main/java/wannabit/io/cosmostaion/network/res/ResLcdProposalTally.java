package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

public class ResLcdProposalTally {

    //TODO remake for cosmoshub-3
    @SerializedName("yes")
    public String yes;

    @SerializedName("abstain")
    public String abstain;

    @SerializedName("no")
    public String no;

    @SerializedName("no_with_veto")
    public String no_with_veto;

}
