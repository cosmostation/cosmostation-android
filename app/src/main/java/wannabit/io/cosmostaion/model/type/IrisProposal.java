package wannabit.io.cosmostaion.model.type;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.utils.WDp;

public class IrisProposal {
    @SerializedName("type")
    public String type;

    @SerializedName("value")
    public Value value;

    public class Value {
        @SerializedName("BasicProposal")
        public BasicProposal basicProposal;

    }

    public class BasicProposal {
        @SerializedName("proposal_id")
        public String proposal_id;

        @SerializedName("title")
        public String title;

        @SerializedName("description")
        public String description;

        @SerializedName("proposal_status")
        public String proposal_status;

        @SerializedName("tally_result")
        public Tally tally_result;

        @SerializedName("submit_time")
        public String submit_time;

        @SerializedName("deposit_end_time")
        public String deposit_end_time;

        @SerializedName("total_deposit")
        public ArrayList<Coin> total_deposit;

        @SerializedName("voting_start_time")
        public String voting_start_time;

        @SerializedName("voting_end_time")
        public String voting_end_time;

        @SerializedName("proposer")
        public String proposer;
    }

    public boolean hasTally() {
        if (value == null ) return false;
        if (value.basicProposal == null) return false;
        if (value.basicProposal.tally_result == null ) return false;
        return true;
    }


    public Drawable getStatusImg(Context c) {
        if (value.basicProposal.proposal_status.equals(Proposal.PROPOSAL_DEPOSIT)) {
            return c.getResources().getDrawable(R.drawable.ic_deposit_img);

        } else if (value.basicProposal.proposal_status.equals(Proposal.PROPOSAL_VOTING)) {
            return c.getResources().getDrawable(R.drawable.ic_voting_img);

        } else if (value.basicProposal.proposal_status.equals(Proposal.PROPOSAL_REJECTED)) {
            return c.getResources().getDrawable(R.drawable.ic_rejected_img);

        } else if (value.basicProposal.proposal_status.equals(Proposal.PROPOSAL_PASSED)) {
            return c.getResources().getDrawable(R.drawable.ic_passed_img);

        }
        return null;
    }

    public String getTitle() {
        return "# " + value.basicProposal.proposal_id + ". "  + value.basicProposal.title;
    }

    public String getType() {
        String[] split =  type.split("/");
        return split[split.length - 1];

    }

    public String getStartTime(Context c) {
        if (value.basicProposal.proposal_status.equals(Proposal.PROPOSAL_DEPOSIT)) {
            return c.getString(R.string.str_vote_wait_deposit);

        } else {
            return WDp.getTimeformat(c, value.basicProposal.voting_start_time);

        }
    }

    public String getEndTime(Context c) {
        if (value.basicProposal.proposal_status.equals(Proposal.PROPOSAL_DEPOSIT)) {
            return c.getString(R.string.str_vote_wait_deposit);

        } else {
            return WDp.getTimeformat(c, value.basicProposal.voting_end_time);

        }
    }


}
