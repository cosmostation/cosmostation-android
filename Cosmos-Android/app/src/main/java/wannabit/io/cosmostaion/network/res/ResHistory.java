package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.StdTx;
import wannabit.io.cosmostaion.model.type.Msg;
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
        public String height;

        @SerializedName("time")
        public String time;

        @SerializedName("tx")
        public StdTx tx;

        @SerializedName("result")
        public Result result;

    }

}
