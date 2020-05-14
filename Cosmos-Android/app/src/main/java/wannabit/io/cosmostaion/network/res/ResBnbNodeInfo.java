package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

public class ResBnbNodeInfo {

    @SerializedName("sync_info")
    public BnbSyncInfo sync_info;

    public class BnbSyncInfo {
        @SerializedName("latest_block_height")
        public long latest_block_height;

        @SerializedName("latest_block_time")
        public String latest_block_time;

        @SerializedName("catching_up")
        public boolean catching_up;

    }

    public long getCHeight() {
        return  sync_info.latest_block_height;
    }
}
