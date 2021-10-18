package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.dao.IbcPath;

public class ResIbcPaths {
    @SerializedName("sendable")
    public ArrayList<IbcPath> sendable;
}
