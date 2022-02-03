package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.dao.Cw20Assets;


public class ResCw20Assets {
    @SerializedName("assets")
    public ArrayList<Cw20Assets> assets;
}
