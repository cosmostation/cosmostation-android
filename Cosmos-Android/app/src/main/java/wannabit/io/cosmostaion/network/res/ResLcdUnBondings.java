package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResLcdUnBondings {

    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public ArrayList<ResLcdUnBonding> result;
}
