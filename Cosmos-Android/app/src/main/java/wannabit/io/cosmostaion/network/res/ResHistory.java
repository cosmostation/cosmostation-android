package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.HistoryStdTx;
import wannabit.io.cosmostaion.model.type.Result;

public class ResHistory {

    @SerializedName("hits")
    public Hits hits;

    public class Hits{
        @SerializedName("total")
        public int total;

        @SerializedName("hits")
        public ArrayList<InnerHits> hits;
    }

    public class InnerHits{
        @SerializedName("_source")
        public Source _source;
    }

    public class Source {
        @SerializedName("hash")
        public String hash;

        @SerializedName("height")
        public int height;

        @SerializedName("time")
        public String time;

//        @SerializedName("tx")
//        public StdTx tx;

        @SerializedName("tx")
        public HistoryStdTx tx;

        @SerializedName("result")
        public Result result;

    }

}
