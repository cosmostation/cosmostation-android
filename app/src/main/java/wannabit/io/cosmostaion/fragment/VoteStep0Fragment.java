package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.VoteActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.Vote;

public class VoteStep0Fragment extends BaseFragment implements View.OnClickListener {

    private TextView mProposalTitle;
    private TextView mProposer;

    private RelativeLayout mBtnYes, mBtnNo, mBtnVeto, mBtnAbstain;
    private ImageView mImgYes, mImgNo, mImgVeto, mImgAbstain;
    private Button mCancel, mNextBtn;
    private String mMyVote = "";

    public static VoteStep0Fragment newInstance(Bundle bundle) {
        VoteStep0Fragment fragment = new VoteStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_vote_step0, container, false);
        mProposalTitle = rootView.findViewById(R.id.vote_title);
        mProposer = rootView.findViewById(R.id.vote_proposer);
        mBtnYes = rootView.findViewById(R.id.checkBtn_yes);
        mBtnNo = rootView.findViewById(R.id.checkBtn_no);
        mBtnVeto = rootView.findViewById(R.id.checkBtn_veto);
        mBtnAbstain = rootView.findViewById(R.id.checkBtn_abstain);
        mImgYes = rootView.findViewById(R.id.checkImg_yes);
        mImgNo = rootView.findViewById(R.id.checkImg_no);
        mImgVeto = rootView.findViewById(R.id.checkImg_veto);
        mImgAbstain = rootView.findViewById(R.id.checkImg_abstain);
        mNextBtn = rootView.findViewById(R.id.btn_next);
        mCancel = rootView.findViewById(R.id.btn_cancel);

        mCancel.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        mBtnYes.setOnClickListener(this);
        mBtnNo.setOnClickListener(this);
        mBtnVeto.setOnClickListener(this);
        mBtnAbstain.setOnClickListener(this);

        mProposalTitle.setText(getSActivity().mProposeTitle);
        mProposer.setText(getSActivity().mProposer);

        onUpdateView();
        return rootView;
    }

    private void onUpdateView() {
        mBtnYes.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.box_round_unselected));
        mBtnNo.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.box_round_unselected));
        mBtnVeto.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.box_round_unselected));
        mBtnAbstain.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.box_round_unselected));
        mImgYes.setColorFilter(ContextCompat.getColor(getSActivity(), R.color.colorGray1), android.graphics.PorterDuff.Mode.SRC_IN);
        mImgNo.setColorFilter(ContextCompat.getColor(getSActivity(), R.color.colorGray1), android.graphics.PorterDuff.Mode.SRC_IN);
        mImgVeto.setColorFilter(ContextCompat.getColor(getSActivity(), R.color.colorGray1), android.graphics.PorterDuff.Mode.SRC_IN);
        mImgAbstain.setColorFilter(ContextCompat.getColor(getSActivity(), R.color.colorGray1), android.graphics.PorterDuff.Mode.SRC_IN);

        if (mMyVote.equals(Vote.OPTION_YES)) {
            mBtnYes.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.box_round_selected));
            mImgYes.setColorFilter(ContextCompat.getColor(getSActivity(), R.color.colorBlackDayNight), android.graphics.PorterDuff.Mode.SRC_IN);

        } else if (mMyVote.equals(Vote.OPTION_NO)) {
            mBtnNo.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.box_round_selected));
            mImgNo.setColorFilter(ContextCompat.getColor(getSActivity(), R.color.colorBlackDayNight), android.graphics.PorterDuff.Mode.SRC_IN);

        } else if (mMyVote.equals(Vote.OPTION_VETO)) {
            mBtnVeto.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.box_round_selected));
            mImgVeto.setColorFilter(ContextCompat.getColor(getSActivity(), R.color.colorBlackDayNight), android.graphics.PorterDuff.Mode.SRC_IN);

        } else if (mMyVote.equals(Vote.OPTION_ABSTAIN)) {
            mBtnAbstain.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.box_round_selected));
            mImgAbstain.setColorFilter(ContextCompat.getColor(getSActivity(), R.color.colorBlackDayNight), android.graphics.PorterDuff.Mode.SRC_IN);

        }

    }

    private VoteActivity getSActivity() {
        return (VoteActivity) getBaseActivity();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnYes)) {
            mMyVote = Vote.OPTION_YES;
            onUpdateView();

        } else if (v.equals(mBtnNo)) {
            mMyVote = Vote.OPTION_NO;
            onUpdateView();

        } else if (v.equals(mBtnVeto)) {
            mMyVote = Vote.OPTION_VETO;
            onUpdateView();

        } else if (v.equals(mBtnAbstain)) {
            mMyVote = Vote.OPTION_ABSTAIN;
            onUpdateView();

        } else if (v.equals(mCancel)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            if (mMyVote.equals(Vote.OPTION_YES) || mMyVote.equals(Vote.OPTION_NO) || mMyVote.equals(Vote.OPTION_VETO) || mMyVote.equals(Vote.OPTION_ABSTAIN)) {
                getSActivity().mOpinion = mMyVote;
                getSActivity().onNextStep();

            } else {
                Toast.makeText(getSActivity(), R.string.error_no_option, Toast.LENGTH_SHORT).show();
                return;
            }
        }

    }
}
