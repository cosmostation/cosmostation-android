package wannabit.io.cosmostaion.model.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import wannabit.io.cosmostaion.model.kava.DenomsToClaim;

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
        @SerializedName("address")
        public String address;

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
        @SerializedName("validator_src_addr")
        public String validator_src_addr;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("validator_dst_addr")
        public String validator_dst_addr;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("shares")
        public String shares;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("delegation")
        public Coin delegation;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("proposal_id")
        public String proposal_id;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("voter")
        public String voter;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("option")
        public Object option;

        public String getOption() {
            try {
                int temp = new Gson().fromJson(new Gson().toJson(option), Integer.class);
                return String.valueOf(temp);

            } catch (Exception e) {}

            try {
                String temp = new Gson().fromJson(new Gson().toJson(option), String.class);
                return temp;
            } catch (Exception e) { }
            return "";
        }



        //FOR KAVA
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("from")
        public String from;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("market_id")
        public String market_id;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("price")
        public String price;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("expiry")
        public String expiry;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("sender")
        public String sender;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("depositor")
        public String depositor;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("validator")
        public String validator;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("owner")
        public String owner;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("keeper")
        public String keeper;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("collateral")
        public Coin collateral;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("principal")
        public Coin principal;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("payment")
        public Coin payment;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("cdp_denom")
        public String cdp_denom;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("denom")
        public String denom;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("collateral_type")
        public String collateral_type;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("deposit_type")
        public String deposit_type;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("receiver")
        public String receiver;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("deposit_denom")
        public String deposit_denom;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("multiplier_name")
        public String multiplier_name;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("denoms_to_claim")
        public ArrayList<DenomsToClaim> denoms_to_claim;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("borrower")
        public String borrower;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("requester")
        public String requester;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("exact_token_a")
        public Coin exact_token_a;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("token_a")
        public Coin token_a;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("token_b")
        public Coin token_b;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("slippage")
        public String slippage;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("deadline")
        public String deadline;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("min_token_a")
        public Coin min_token_a;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("min_token_b")
        public Coin min_token_b;



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
        @SerializedName("timestamp")
        public String timestamp;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("expected_income")
        public String expected_income;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("height_span")
        public String height_span;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("cross_chain")
        public Boolean cross_chain;



        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("transfers")
        public ArrayList<OkTransfer> transfers;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("quantity")
        public Coin quantity;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @SerializedName("validator_addresses")
        public ArrayList<String> validator_addresses;

    }

    public class OkTransfer {
        @SerializedName("to")
        public String to;

        @SerializedName("coins")
        public ArrayList<Coin> coins;
    }

}
