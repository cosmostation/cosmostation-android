package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Proposal;
import wannabit.io.cosmostaion.model.type.Redelegate;

public class ResLcdRedelegate {

    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public ArrayList<Redelegate> result;

}
