package wannabit.io.cosmostaion.fragment.txs.kava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.kava.ClaimIncentiveActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.Kava;
import wannabit.io.cosmostaion.model.kava.IncentiveReward;
import wannabit.io.cosmostaion.utils.WDp;

public class ClaimIncentiveStep0Fragment extends BaseFragment implements View.OnClickListener {

    private Button mCancelBtn, mNextBtn;
    private TextView mIncentiveKavaAmount, mIncentiveHardAmount, mIncentiveSwpAmount;

    private BigDecimal mKavaIncetiveAmount = BigDecimal.ZERO;
    private BigDecimal mHardIncetiveAmount = BigDecimal.ZERO;
    private BigDecimal mSwpIncetiveAmount = BigDecimal.ZERO;

    private IncentiveReward mIncentiveReward;

    public static ClaimIncentiveStep0Fragment newInstance() {
        return new ClaimIncentiveStep0Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_claim_incentive_0, container, false);
        mCancelBtn = rootView.findViewById(R.id.btn_cancel);
        mNextBtn = rootView.findViewById(R.id.btn_next);

        mIncentiveKavaAmount = rootView.findViewById(R.id.tx_incentive_kava_amount);
        mIncentiveHardAmount = rootView.findViewById(R.id.tx_incentive_hard_amount);
        mIncentiveSwpAmount = rootView.findViewById(R.id.tx_incentive_swp_amount);

        mCancelBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

        onUpdateView();
        return rootView;
    }

    private void onUpdateView() {
        mIncentiveReward = getBaseDao().mIncentiveRewards;

        if (mIncentiveReward != null) {
            mKavaIncetiveAmount = mIncentiveReward.getRewardSum(getSActivity().mChainConfig.mainDenom());
            mHardIncetiveAmount = mIncentiveReward.getRewardSum(Kava.KAVA_HARD_DENOM);
            mSwpIncetiveAmount = mIncentiveReward.getRewardSum(Kava.KAVA_SWP_DENOM);
        }

        mIncentiveKavaAmount.setText(WDp.getDpAmount2(getSActivity(), mKavaIncetiveAmount, 6, 6));
        mIncentiveHardAmount.setText(WDp.getDpAmount2(getSActivity(), mHardIncetiveAmount, 6, 6));
        mIncentiveSwpAmount.setText(WDp.getDpAmount2(getSActivity(), mSwpIncetiveAmount, 6, 6));

        getSActivity().mIncentiveMultiplier = "large";
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mCancelBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            getSActivity().onNextStep();
        }
    }

    private ClaimIncentiveActivity getSActivity() {
        return (ClaimIncentiveActivity) getBaseActivity();
    }
}
