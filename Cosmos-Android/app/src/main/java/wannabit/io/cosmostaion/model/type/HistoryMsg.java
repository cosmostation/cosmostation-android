package wannabit.io.cosmostaion.model.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;

public class HistoryMsg {

    @SerializedName("type")
    public String type;

    @SerializedName("value")
    public HistoryMsg.Value value;

    public static class Value {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("from_address")
        public String from_address;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("to_address")
        public String to_address;
    }
}
