package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Proposal;
import wannabit.io.cosmostaion.model.type.Tally;

public class ResLcdProposalTally {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public Tally result;

}
