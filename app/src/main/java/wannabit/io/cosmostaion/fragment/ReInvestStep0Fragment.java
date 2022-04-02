package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.ReInvestActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class ReInvestStep0Fragment extends BaseFragment implements View.OnClickListener {

    private CardView mCardReward;
    private TextView mTvRewardAmount, mTvRewardDenom;
    private TextView mTvFromValidators;
    private RelativeLayout mProgressBar;
    private Button mCancelBtn, mNextBtn;
    private int mDpDecimal = 6;

    public static ReInvestStep0Fragment newInstance(Bundle bundle) {
        ReInvestStep0Fragment fragment = new ReInvestStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reinvest_step0, container, false);
        mCardReward = rootView.findViewById(R.id.reward_card);
        mTvRewardAmount = rootView.findViewById(R.id.reward_amount);
        mTvRewardDenom = rootView.findViewById(R.id.reward_amount_title);
        mTvFromValidators = rootView.findViewById(R.id.reward_moniker);
        mProgressBar = rootView.findViewById(R.id.reward_progress);
        mCancelBtn = rootView.findViewById(R.id.btn_cancel);
        mNextBtn = rootView.findViewById(R.id.btn_next);

        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mTvRewardDenom);

        mCancelBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        mNextBtn.setClickable(false);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mDpDecimal = WDp.mainDivideDecimal(getSActivity().mBaseChain);
        if (getSActivity().mAmount != null) {
            BigDecimal rewardSum = new BigDecimal(getSActivity().mAmount.amount).setScale(0, BigDecimal.ROUND_DOWN);
            mTvRewardAmount.setText(WDp.getDpAmount2(rewardSum, mDpDecimal, mDpDecimal));
        }
        mTvFromValidators.setText(getSActivity().getBaseDao().getValidatorInfo(getSActivity().mValAddress).getDescription().getMoniker());
        mProgressBar.setVisibility(View.GONE);
        mNextBtn.setClickable(true);

    }

    @Override
    public void onClick(View v) {
        if (v.equals(mCancelBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            getSActivity().onNextStep();
        }

    }

    private ReInvestActivity getSActivity() {
        return (ReInvestActivity) getBaseActivity();
    }
}
