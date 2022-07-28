package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

public class ResVoteStatus {

    @SerializedName("voter")
    public String voter;

    @SerializedName("option")
    public String option;

    @SerializedName("tx_hash")
    public String tx_hash;

    @SerializedName("timestamp")
    public String timestamp;

    @SerializedName("answer")
    public String answer;

}
