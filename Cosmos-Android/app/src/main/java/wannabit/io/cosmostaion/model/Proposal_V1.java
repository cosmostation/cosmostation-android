package wannabit.io.cosmostaion.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.gson.annotations.SerializedName;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class Proposal_V1 {
    @SerializedName("proposal_id")
    public String proposal_id;

    @SerializedName("status")
    public String status;

    @SerializedName("content")
    public Proposal_Content_V1 content;

    @SerializedName("voting_start_time")
    public String voting_start_time;

    @SerializedName("voting_end_time")
    public String voting_end_time;

    public class Proposal_Content_V1 {
        @SerializedName("@type")
        public String type;

        @SerializedName("title")
        public String title;

        @SerializedName("description")
        public String description;

    }

    public Drawable getStatusImg(Context c) {
        if (status.equals("PROPOSAL_STATUS_DEPOSIT_PERIOD")) {
            return c.getResources().getDrawable(R.drawable.ic_deposit_img);

        } else if (status.equals("PROPOSAL_STATUS_VOTING_PERIOD")) {
            return c.getResources().getDrawable(R.drawable.ic_voting_img);

        } else if (status.equals("PROPOSAL_STATUS_REJECTED")) {
            return c.getResources().getDrawable(R.drawable.ic_rejected_img);

        } else if (status.equals("PROPOSAL_STATUS_PASSED")) {
            return c.getResources().getDrawable(R.drawable.ic_passed_img);

        }
        return null;
    }

    public String getStatusText(Context c) {
        if (status.equals("PROPOSAL_STATUS_DEPOSIT_PERIOD")) {
            return "DepositPeriod";

        } else if (status.equals("PROPOSAL_STATUS_VOTING_PERIOD")) {
            return "VotingPeriod";

        } else if (status.equals("PROPOSAL_STATUS_REJECTED")) {
            return "Rejected";

        } else if (status.equals("PROPOSAL_STATUS_PASSED")) {
            return "Passed";
        }
        return "UnKnown";
    }


    public String getTitle() {
        return "# " + proposal_id + ". "  + content.title;
    }

    public String getType() {
        String[] split = content.type.split("\\.");
        return split[split.length - 1];
    }

    public String getStartTime(Context c) {
        if (status.equals("PROPOSAL_STATUS_DEPOSIT_PERIOD")) {
            return c.getString(R.string.str_vote_wait_deposit);

        } else {
            return WDp.getTimeformat(c, voting_start_time);

        }
    }

    public String getEndTime(Context c) {
        if (status.equals("PROPOSAL_STATUS_DEPOSIT_PERIOD")) {
            return c.getString(R.string.str_vote_wait_deposit);

        } else {
            return WDp.getTimeformat(c, voting_end_time);

        }
    }
}
