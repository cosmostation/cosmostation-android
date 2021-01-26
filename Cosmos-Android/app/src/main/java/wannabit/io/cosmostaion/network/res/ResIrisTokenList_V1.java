package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.IrisToken_V1;

public class ResIrisTokenList_V1 {
    @SerializedName("Tokens")
    public ArrayList<IrisToken_V1> tokens;
}
