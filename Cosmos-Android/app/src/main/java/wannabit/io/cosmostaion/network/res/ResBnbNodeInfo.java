package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

public class ResBnbNodeInfo {

    @SerializedName("protocol_version")
    public BnbProtocal protocol_version;

    @SerializedName("sync_info")
    public BnbSyncInfo sync_info;

    public class BnbProtocal {
        @SerializedName("network")
        public String network;

    }

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
