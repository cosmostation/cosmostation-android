package wannabit.io.cosmostaion.network.res;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.model.type.Msg;

public class ResApiTxList {

    @SerializedName("data")
    public ArrayList<Data> data;

    public class Data {
        @SerializedName("id")
        public int id;

        @SerializedName("height")
        public int height;

        @SerializedName("tx_hash")
        public String tx_hash;

        @SerializedName("messages")
        public ArrayList<Msg> messages;

        @SerializedName("fee")
        public Fee fee;

        @SerializedName("memo")
        public String memo;

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

            } catch (Exception e) { }

            try {
                ArrayList<Log> temp = new Gson().fromJson(new Gson().toJson(logs), new TypeToken<List<Log>>(){}.getType());
                for (Log log:temp) {
                    if(!log.success) {
                        result = false;
                        break;
                    }
                }

            } catch (Exception e) { }
            return result;
        }

        public String failMsg() {
            String result = "";
            try {
                Log temp = new Gson().fromJson(new Gson().toJson(logs), Log.class);
                result = temp.log;

            } catch (Exception e) { }

            try {
                ArrayList<Log> temp = new Gson().fromJson(new Gson().toJson(logs), new TypeToken<List<Log>>(){}.getType());
                for (Log log:temp) {
                    if(!log.success) {
                        result = log.log;
                        break;
                    }
                }

            } catch (Exception e) { }
            return result.replace(" ", "\u00A0");
        }

    }


    public class Log {
        @SerializedName("msg_index")
        public String msg_index;

        @SerializedName("success")
        public boolean success;

        @SerializedName("log")
        public String log;
    }
}
