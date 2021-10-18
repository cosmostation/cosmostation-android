package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

public class ResBlockInfo {

    @SerializedName("block_meta")
    public BlockMeta block_meta;



    public class BlockMeta {
        @SerializedName("header")
        public BlockMetaHeader header;
    }

    public class BlockMetaHeader {
        @SerializedName("height")
        public String height;

        @SerializedName("time")
        public String time;
    }
}
