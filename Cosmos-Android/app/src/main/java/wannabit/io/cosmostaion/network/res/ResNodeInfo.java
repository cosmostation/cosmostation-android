package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import wannabit.io.cosmostaion.model.Node_Info;
import wannabit.io.cosmostaion.model.Sync_Info;

public class ResNodeInfo {

    @SerializedName("node_info")
    public Node_Info node_info;

    @SerializedName("sync_info")
    public Sync_Info sync_info;

    public long getCHeight() {
        return  sync_info.latest_block_height;
    }

}
