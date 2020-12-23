package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.VoteActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;

public class VoteStep3Fragment extends BaseFragment implements View.OnClickListener {

    private TextView mProposalTitle;
    private TextView mProposer;
    private TextView mOpinion;
    private TextView mFeeAmount, mDenomFeeType;
    private TextView mMemo;
    private Button mBeforeBtn, mConfirmBtn;


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
        BigDecimal feeAmount = new BigDecimal(getSActivity().mFee.amount.get(0).amount);
        if (getSActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN) || getSActivity().mBaseChain.equals(BaseChain.KAVA_MAIN) || getSActivity().mBaseChain.equals(BaseChain.BAND_MAIN) ||
                getSActivity().mBaseChain.equals(BaseChain.CERTIK_MAIN) || getSActivity().mBaseChain.equals(BaseChain.CERTIK_TEST) || getSActivity().mBaseChain.equals(BaseChain.IOV_MAIN) ||
                getSActivity().mBaseChain.equals(BaseChain.AKASH_MAIN) || getSActivity().mBaseChain.equals(SECRET_MAIN)) {
            mFeeAmount.setText(WDp.getDpAmount(getContext(), feeAmount, 6, getSActivity().mBaseChain));

        } else if (getSActivity().mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            mFeeAmount.setText(WDp.getDpAmount(getContext(), feeAmount, 18, getSActivity().mBaseChain));

        }
        mOpinion.setText(getSActivity().mOpinion);
        mMemo.setText(getSActivity().mMemo);
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
        return (VoteActivity)getBaseActivity();
    }
}
