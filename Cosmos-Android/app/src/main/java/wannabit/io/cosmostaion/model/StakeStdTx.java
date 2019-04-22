package wannabit.io.cosmostaion.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.model.type.Signature;
import wannabit.io.cosmostaion.model.type.StakeMsg;

public class StakeStdTx {

    @SerializedName("type")
    public String type;

    @SerializedName("value")
    public StakeStdTx.Value value;

    public static class Value {

        @SerializedName("msg")
        public ArrayList<StakeMsg> msg;

        @SerializedName("fee")
        public Fee fee;

        @JsonInclude(JsonInclude.Include.ALWAYS)
        @SerializedName("signatures")
        public ArrayList<Signature> signatures;

        @SerializedName("memo")
        public String memo;
    }
}
