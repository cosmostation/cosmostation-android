package wannabit.io.cosmostaion.widget;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cosmos.gov.v1beta1.Gov;
import cosmos.gov.v1beta1.Tx;
import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;

public class TxVoterHolder extends TxHolder {
    ImageView itemVoteImg;
    TextView itemVoteTitle;
    TextView itemDelegator, itemProposalId, itemOpinion;

    public TxVoterHolder(@NonNull View itemView) {
        super(itemView);
        itemVoteImg = itemView.findViewById(R.id.tx_vote_icon);
        itemVoteTitle = itemView.findViewById(R.id.tx_vote_text);
        itemDelegator = itemView.findViewById(R.id.tx_vote_voter);
        itemProposalId = itemView.findViewById(R.id.tx_vote_proposal_id);
        itemOpinion = itemView.findViewById(R.id.tx_vote_proposal_opinion);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemVoteImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgVote msg = Tx.MsgVote.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemDelegator.setText(msg.getVoter());
            itemProposalId.setText("" +  msg.getProposalId());
            if (msg.getOption().equals(Gov.VoteOption.VOTE_OPTION_YES)) {
                itemOpinion.setText("Yes");

            } else if (msg.getOption().equals(Gov.VoteOption.VOTE_OPTION_NO)) {
                itemOpinion.setText("No");

            } else if (msg.getOption().equals(Gov.VoteOption.VOTE_OPTION_NO_WITH_VETO)) {
                itemOpinion.setText("No with Veto");

            } else if (msg.getOption().equals(Gov.VoteOption.VOTE_OPTION_ABSTAIN)) {
                itemOpinion.setText("Abstain");

            }
        } catch (Exception e) {}
    }
}
