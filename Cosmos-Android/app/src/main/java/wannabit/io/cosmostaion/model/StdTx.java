package wannabit.io.cosmostaion.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.model.type.Signature;

public class StdTx {

    @SerializedName("type")
    public String type;

    @SerializedName("value")
    public Value value;

    public static class Value {

        @SerializedName("msg")
        public ArrayList<Msg> msg;

        @SerializedName("fee")
        public Fee fee;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("signatures")
        public ArrayList<Signature> signatures;

        @SerializedName("memo")
        public String memo;
    }
}
