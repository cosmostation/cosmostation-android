package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.dao.MintscanToken;

public class ResMintscanAssets {
    @SerializedName("assets")
    public ArrayList<MintscanToken> assets;
}
