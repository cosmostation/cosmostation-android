package wannabit.io.cosmostaion.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.gson.annotations.SerializedName;

import wannabit.io.cosmostaion.R;

public class Proposal_V1 {
    @SerializedName("proposal_id")
    public String proposal_id;

    @SerializedName("status")
    public String status;

    @SerializedName("content")
    public Proposal_Content_V1 content;

    public class Proposal_Content_V1 {
        @SerializedName("type")
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
}
