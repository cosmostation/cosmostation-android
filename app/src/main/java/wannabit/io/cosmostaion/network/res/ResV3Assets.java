package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.dao.V3Asset;

public class ResV3Assets {
    @SerializedName("assets")
    public ArrayList<V3Asset> assets;
}
