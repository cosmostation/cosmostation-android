package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.dao.Asset;

public class ResAssets {
    @SerializedName("assets")
    public ArrayList<Asset> assets;
}
