package wannabit.io.cosmostaion.model.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.nio.charset.Charset;
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
        @SerializedName("delegator_addr")
        public String delegator_addr;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("validator_addr")
        public String validator_addr;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("from_address")
        public String from_address;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("to_address")
        public String to_address;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("withdraw_addr")
        public String withdraw_addr;

        //TODO changed
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("value")
        public Coin value;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("shares_amount")
        public String shares_amount;

        //From 0.33.0 version changed
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("amount")
        public Object amount;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("delegator_address")
        public String delegator_address;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("validator_address")
        public String validator_address;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("withdraw_address")
        public String withdraw_address;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("validator_src_address")
        public String validator_src_address;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("validator_dst_address")
        public String validator_dst_address;


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
        @SerializedName("delegation")
        public Coin delegation;

    }

//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    public byte[] getToSignByte() {
//        Gson Presenter = new GsonBuilder().create();
//        return Presenter.toJson(this).getBytes(Charset.forName("UTF-8"));
//    }

}
