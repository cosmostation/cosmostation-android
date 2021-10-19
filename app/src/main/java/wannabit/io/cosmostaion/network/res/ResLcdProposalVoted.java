package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Vote;

public class ResLcdProposalVoted {

    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public ArrayList<Vote> result;
}
