package wannabit.io.cosmostaion.model.type;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class Result {

    @SerializedName("Code")
    public int Code;

    @SerializedName("gas_wanted")
    public String gas_wanted;

    @SerializedName("gas_used")
    public String gas_used;


    @SerializedName("log")
    @Expose
    public Object log;

    public boolean isSuccess() {
        boolean result = true;
        try {
            Log temp = new Gson().fromJson(new Gson().toJson(log), Log.class);
            result = temp.success;

        } catch (Exception e) {

        }
        try {
            ArrayList<Log> temp = new Gson().fromJson(new Gson().toJson(log), new TypeToken<List<Log>>() {
            }.getType());
            for (Log log : temp) {
                if (!log.success) {
                    result = false;
                    break;
                }
            }

        } catch (Exception e) {

        }
        return result;
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
