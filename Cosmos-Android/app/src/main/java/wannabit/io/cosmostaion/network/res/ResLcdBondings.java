package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.BondingInfo;

public class ResLcdBondings {

    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public ArrayList<BondingInfo> result;

}
