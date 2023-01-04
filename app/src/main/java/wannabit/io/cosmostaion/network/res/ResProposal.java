package wannabit.io.cosmostaion.network.res;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import wannabit.io.cosmostaion.model.type.Coin;

public class ResProposal {
    @SerializedName("id")
    public int id;

    @SerializedName("tx_hash")
    public String tx_hash;

    @SerializedName("proposer")
    public String proposer;

    @SerializedName("title")
    public String title;

    @SerializedName("description")
    public String description;

    @SerializedName("proposal_type")
    public String proposal_type;

    @SerializedName("proposal_status")
    public String proposal_status;

    @SerializedName("submit_time")
    public String submit_time;

    @SerializedName("voting_start_time")
    public String voting_start_time;

    @SerializedName("voting_end_time")
    public String voting_end_time;

    @SerializedName("deposit_end_time")
    public String deposit_end_time;

    @SerializedName("notification_status")
    public String notification_status;

    @SerializedName("metadata")
    public String metadata;

    @SerializedName("gov_rest_path")
    public String gov_rest_path;

    @SerializedName("messages")
    public ArrayList<Messages> messages;

    @SerializedName("moniker")
    public String moniker;

    @SerializedName("initial_deposit")
    public Coin initialDeposit;

    @SerializedName("total_deposit")
    public Object totalDeposit;

    @SerializedName("voteMeta")
    public ResProposal.VoteMeta voteMeta;

    public class Messages {
        @SerializedName("@type")
        public String type;

        @SerializedName("title")
        public String title;

        @SerializedName("description")
        public String description;

        @SerializedName("authority")
        public String authority;

        @SerializedName("add_tokens")
        public ArrayList<ResProposal.Tokens> add_tokens;

        @SerializedName("recipient")
        public String recipient;

        @SerializedName("amount")
        public ArrayList<Coin> amount;

        @SerializedName("content")
        public Content content;

    }

    public class VoteMeta {
        @SerializedName("yes")
        public String yes;

        @SerializedName("no")
        public String no;

        @SerializedName("no_with_veto")
        public String no_with_veto;

        @SerializedName("abstain")
        public String abstain;

        @SerializedName("yes_amount")
        public String yes_amount;

        @SerializedName("no_amount")
        public String no_amount;

        @SerializedName("no_with_veto_amount")
        public String no_with_veto_amount;

        @SerializedName("abstain_amount")
        public String abstain_amount;
    }

    public class Content {
        @SerializedName("@type")
        public String type;

        @SerializedName("title")
        public String title;

        @SerializedName("description")
        public String description;

        @SerializedName("recipient")
        public String recipient;

        @SerializedName("amount")
        public ArrayList<Coin> amount;

    }

    public class Tokens {
        @SerializedName("base_denom")
        public String base_denom;

        @SerializedName("reserve_factor")
        public String reserve_factor;

        @SerializedName("collateral_weight")
        public String collateral_weight;

        @SerializedName("liquidation_threshold")
        public String liquidation_threshold;

        @SerializedName("base_borrow_rate")
        public String base_borrow_rate;

        @SerializedName("kink_utilization")
        public String kink_utilization;

        @SerializedName("liquidation_incentive")
        public String liquidation_incentive;

        @SerializedName("symbol_denom")
        public String symbol_denom;

        @SerializedName("exponent")
        public int exponent;

        @SerializedName("enable_msg_supply")
        public boolean enable_msg_supply;

        @SerializedName("enable_msg_borrow")
        public boolean enable_msg_borrow;

        @SerializedName("max_collateral_share")
        public String max_collateral_share;

        @SerializedName("max_supply_utilization")
        public String max_supply_utilization;

        @SerializedName("min_collateral_liquidity")
        public String min_collateral_liquidity;

        @SerializedName("max_supply")
        public String max_supply;
    }


    public Coin getAmounts() {
        try {
            ArrayList<Coin> temp = new Gson().fromJson(new Gson().toJson(messages.get(0).amount), new TypeToken<List<Coin>>() {
            }.getType());
            if (temp != null && temp.size() > 0) {
                return temp.get(0);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Coin getEvmosAmounts() {
        try {
            ArrayList<Coin> temp = new Gson().fromJson(new Gson().toJson(messages.get(0).content.amount), new TypeToken<List<Coin>>() {
            }.getType());
            if (temp != null && temp.size() > 0) {
                return temp.get(0);
            }
        } catch (Exception e) {
        }
        return null;
    }
}
