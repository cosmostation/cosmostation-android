package wannabit.io.cosmostaion.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.model.type.Msg;

public class HistoryStdTx {

    @SerializedName("type")
    public String type;

    @SerializedName("value")
    public HistoryStdTx.Value value;

    public static class Value {

        @SerializedName("msg")
        public ArrayList<Msg> msg;

        @SerializedName("fee")
        public Fee fee;

        @SerializedName("memo")
        public String memo;
    }
}
