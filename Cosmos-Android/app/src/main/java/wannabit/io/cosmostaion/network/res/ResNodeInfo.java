package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import wannabit.io.cosmostaion.model.NodeInfo;
import wannabit.io.cosmostaion.model.SyncInfo;

public class ResNodeInfo {

    @SerializedName("node_info")
    public NodeInfo node_info;

    @SerializedName("sync_info")
    public SyncInfo sync_info;

    public long getCHeight() {
        return  sync_info.latest_block_height;
    }

}
