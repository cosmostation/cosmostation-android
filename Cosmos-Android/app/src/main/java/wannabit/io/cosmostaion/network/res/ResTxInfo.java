package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.StdTx;

public class ResTxInfo {
    @SerializedName("height")
    public String height;

    @SerializedName("txhash")
    public String txhash;

    @SerializedName("code")
    public int code;

    @SerializedName("logs")
    public ArrayList<Log> logs;

    @SerializedName("gasWanted")
    public String gasWanted;

    @SerializedName("gasUsed")
    public String gasUsed;

    @SerializedName("tags")
    public ArrayList<Tag> tags;

    @SerializedName("tx")
    public StdTx tx;



    public class Log {
        @SerializedName("msg_index")
        public String msg_index;

        @SerializedName("success")
        public boolean success;

        @SerializedName("log")
        public String log;
    }

    public class Tag {
        @SerializedName("key")
        public String key;

        @SerializedName("value")
        public String value;
    }
}
