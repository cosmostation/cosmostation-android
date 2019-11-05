package wannabit.io.cosmostaion.network.res;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

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

        @SerializedName("timestamp")
        public String timestamp;


        @SerializedName("logs")
        @Expose
        public Object logs;

        public boolean isSuccess() {
            boolean result = true;
            try {
                Log temp = new Gson().fromJson(new Gson().toJson(logs), Log.class);
                result = temp.success;

            } catch (Exception e) {

            }
            try {
                ArrayList<Log> temp = new Gson().fromJson(new Gson().toJson(logs), new TypeToken<List<Log>>(){}.getType());
                for (Log log:temp) {
                    if(!log.success) {
                        result = false;
                        break;
                    }
                }

            } catch (Exception e) {

            }
            return result;
        }

    }


    public class Log {
        @SerializedName("msg_index")
        @Expose
        public String msg_index;


        @SerializedName("success")
        @Expose
        public boolean success;


        @SerializedName("log")
        @Expose
        public String log;

    }

}
