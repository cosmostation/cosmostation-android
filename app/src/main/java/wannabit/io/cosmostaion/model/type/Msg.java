package wannabit.io.cosmostaion.model.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

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
        @SerializedName("from_address")
        public String from_address;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("to_address")
        public String to_address;

        //From 0.33.0 version changed
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("amount")
        public Object amount;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        public ArrayList<Coin> getCoins() {
            ArrayList<Coin> result = new ArrayList<>();
            try {
                Coin temp = new Gson().fromJson(new Gson().toJson(amount), Coin.class);
                result.add(temp);

            } catch (Exception e) {}

            try {
                result = new Gson().fromJson(new Gson().toJson(amount), new TypeToken<List<Coin>>(){}.getType());
            } catch (Exception e) { }
            return result;
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("delegator_address")
        public String delegator_address;

        //FOR KAVA
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("from")
        public String from;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("swap_id")
        public String swap_id;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("random_number")
        public String random_number;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("random_number_hash")
        public String random_number_hash;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("to")
        public String to;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("recipient_other_chain")
        public String recipient_other_chain;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("sender_other_chain")
        public String sender_other_chain;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("quantity")
        public Coin quantity;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("validator_addresses")
        public ArrayList<String> validator_addresses;
    }
}
