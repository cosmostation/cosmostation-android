package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.dao.IovToken;

public class ResIovToken {

    @SerializedName("allTokens")
    public ArrayList<IovToken> allTokens;

}
