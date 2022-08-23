package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.dao.AssetsV2;

public class ResAssetsV2 {
    @SerializedName("assets")
    public ArrayList<AssetsV2> assets;
}
