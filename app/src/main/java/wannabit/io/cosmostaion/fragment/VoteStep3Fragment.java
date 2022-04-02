package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.VoteActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class VoteStep3Fragment extends BaseFragment implements View.OnClickListener {

    private TextView mProposalTitle;
    private TextView mProposer;
    private TextView mOpinion;
    private TextView mFeeAmount, mDenomFeeType;
    private TextView mMemo;
    private Button mBeforeBtn, mConfirmBtn;
    private int mDpDecimal = 6;


    public static VoteStep3Fragment newInstance(Bundle bundle) {
        VoteStep3Fragment fragment = new VoteStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_vote_step3, container, false);
        mProposalTitle = rootView.findViewById(R.id.vote_title);
        mProposer = rootView.findViewById(R.id.vote_proposer);
        mOpinion = rootView.findViewById(R.id.vote_to);
        mFeeAmount = rootView.findViewById(R.id.vote_fees);
        mDenomFeeType = rootView.findViewById(R.id.vote_fees_type);
        mMemo = rootView.findViewById(R.id.memo);
        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mConfirmBtn = rootView.findViewById(R.id.btn_confirm);

        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mDenomFeeType);
        mProposalTitle.setText(getSActivity().mProposeTitle);
        mProposer.setText(getSActivity().mProposer);
        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mDpDecimal = WDp.mainDivideDecimal(getSActivity().mBaseChain);
        BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);
        mFeeAmount.setText(WDp.getDpAmount2(feeAmount, mDpDecimal, mDpDecimal));
        mOpinion.setText(getSActivity().mOpinion);
        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartVote();

        }

    }

    private VoteActivity getSActivity() {
        return (VoteActivity) getBaseActivity();
    }
}
