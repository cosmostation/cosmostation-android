package wannabit.io.cosmostaion.model.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;

public class StakeMsg {

    @SerializedName("type")
    public String type;

    @SerializedName("value")
    public StakeMsg.Value value;

    public static class Value {

        //TODO changed
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("amount")
        public Coin amount;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("delegator_address")
        public String delegator_address;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("validator_address")
        public String validator_address;

    }
}
