package wannabit.io.cosmostaion.model;

import com.google.gson.annotations.SerializedName;

public class SyncInfo {
    @SerializedName("latest_block_height")
    public long latest_block_height;

    @SerializedName("latest_block_time")
    public String latest_block_time;

    @SerializedName("catching_up")
    public boolean catching_up;

}
