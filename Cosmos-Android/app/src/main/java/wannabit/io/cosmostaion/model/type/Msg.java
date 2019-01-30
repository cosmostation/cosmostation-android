package wannabit.io.cosmostaion.model.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Msg {

    @SerializedName("type")
    public String type;

    @SerializedName("value")
    public Value value;


    public static class Value {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("inputs")
        public ArrayList<Input> inputs;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("outputs")
        public ArrayList<Output> outputs;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("delegator_addr")
        public String delegator_addr;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("validator_addr")
        public String validator_addr;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("delegation")
        public Delegation delegation;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("shares_amount")
        public String shares_amount;

    }

}
