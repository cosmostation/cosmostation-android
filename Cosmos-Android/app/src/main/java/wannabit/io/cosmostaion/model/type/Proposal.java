package wannabit.io.cosmostaion.model.type;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.utils.WDp;


public class Proposal {

    public final static String PROPOSAL_DEPOSIT         = "DepositPeriod";
    public final static String PROPOSAL_VOTING          = "VotingPeriod";
    public final static String PROPOSAL_REJECTED        = "Rejected";
    public final static String PROPOSAL_PASSED          = "Passed";

    @SerializedName("id")
    public String id;

    @SerializedName("proposal_status")
    public String proposal_status;

    @SerializedName("content")
    public ProposalContent content;

    @SerializedName("voting_start_time")
    public String voting_start_time;

    @SerializedName("voting_end_time")
    public String voting_end_time;


    public class ProposalContent {
        @SerializedName("type")
        public String type;

        @SerializedName("value")
        public Value value;

    }

    public class Value {
        @SerializedName("title")
        public String title;

        @SerializedName("description")
        public String description;
    }


    public Drawable getStatusImg(Context c) {
        if (proposal_status.equals(PROPOSAL_DEPOSIT)) {
            return c.getResources().getDrawable(R.drawable.ic_deposit_img);

        } else if (proposal_status.equals(PROPOSAL_VOTING)) {
            return c.getResources().getDrawable(R.drawable.ic_voting_img);

        } else if (proposal_status.equals(PROPOSAL_REJECTED)) {
            return c.getResources().getDrawable(R.drawable.ic_rejected_img);

        } else if (proposal_status.equals(PROPOSAL_PASSED)) {
            return c.getResources().getDrawable(R.drawable.ic_passed_img);

        }
        return null;
    }

    public String getTitle() {
        return "# " + id + ". "  + content.value.title;
    }

    public String getType() {
        String[] split =  content.type.split("/");
        return split[split.length - 1];

    }

    public String getStartTime(Context c) {
        if (proposal_status.equals(PROPOSAL_DEPOSIT)) {
            return c.getString(R.string.str_vote_wait_deposit);

        } else {
            return WDp.getTimeformat(c, voting_start_time);

        }
    }

    public String getEndTime(Context c) {
        if (proposal_status.equals(PROPOSAL_DEPOSIT)) {
            return c.getString(R.string.str_vote_wait_deposit);

        } else {
            return WDp.getTimeformat(c, voting_end_time);

        }
    }
}
