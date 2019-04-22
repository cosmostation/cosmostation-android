package wannabit.io.cosmostaion.network.res;

import android.text.TextUtils;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import wannabit.io.cosmostaion.utils.WLog;

public class ResBroadTx {

    @SerializedName("txhash")
    public String txhash;

    @SerializedName("code")
    public Integer code;

    @SerializedName("raw_log")
    public String raw_log;


    /*

    @SerializedName("hash")
    public String hash;

    @SerializedName("height")
    public String height;

    @SerializedName("check_tx")
    public CheckTx check_tx;

    @SerializedName("deliver_tx")
    public DeliverTx deliver_tx;

    public boolean timeout;

    public boolean isAllSuccess() {
        boolean result = true;
        if(deliver_tx == null || TextUtils.isEmpty(deliver_tx.log)) {
            return false;
        }

        ArrayList<ResultLog> logs = new Gson().fromJson(deliver_tx.log, new TypeToken<List<ResultLog>>(){}.getType());
//        WLog.w("logs : " + logs.size());
        if(logs == null || logs.size() <= 0 ){
            result  = false;
        }
        for(ResultLog log : logs) {
            if(!log.success) {
                result  = false; break;
            }
        }
        return result;
    }

    public String getErrorReason() {
        String result = "";
        if(deliver_tx != null && !TextUtils.isEmpty(deliver_tx.log)) {
            ArrayList<ResultLog> logs = new Gson().fromJson(deliver_tx.log, new TypeToken<List<ResultLog>>(){}.getType());
            for(ResultLog log : logs) {
                LogDetail logDetail = new Gson().fromJson(log.log, LogDetail.class);
                if(logDetail != null && !TextUtils.isEmpty(logDetail.message)) {
                    result = logDetail.message;
                }
            }
        }
        if(TextUtils.isEmpty(result)) {
            LogDetail log = new Gson().fromJson(check_tx.log, LogDetail.class);
            if(!TextUtils.isEmpty(log.message))
                result = log.message;
        }

        return result;
    }


    public class CheckTx {
        @SerializedName("gasWanted")
        public String gasWanted;

        @SerializedName("gasUsed")
        public String gasUsed;

        @SerializedName("log")
        public String log;
    }


    public class DeliverTx {
        @SerializedName("code")
        public int code;

        @SerializedName("log")
        public String log;

        @SerializedName("codespace")
        public String codespace;
    }




    public class ResultLog {
        @SerializedName("msg_index")
        public String msg_index;

        @SerializedName("success")
        public boolean success;

        @SerializedName("log")
        public String log;
    }

    public class LogDetail {
        @SerializedName("codespace")
        public String codespace;

        @SerializedName("message")
        public String message;
    }
    */







//    @SerializedName("code")
//    public int code;
//
//    @SerializedName("data")
//    public String data;
//
//    @SerializedName("log")
//    public String log;
//
//
//    @SerializedName("errorMsg")
//    public String errorMsg;
//
//    @SerializedName("errorCode")
//    public int errorCode;
}
