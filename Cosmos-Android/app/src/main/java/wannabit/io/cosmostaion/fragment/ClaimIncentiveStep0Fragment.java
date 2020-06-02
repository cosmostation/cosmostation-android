package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.ClaimIncentiveActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.network.res.ResKavaIncentiveParam;
import wannabit.io.cosmostaion.network.res.ResKavaIncentiveReward;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class ClaimIncentiveStep0Fragment extends BaseFragment implements View.OnClickListener {

    private Button mCancelBtn, mNextBtn;
    private TextView mMyReward, mMyRewardDenom;
    private TextView mSenderAddress, mCoinType, mLockTime;

    public static ClaimIncentiveStep0Fragment newInstance(Bundle bundle) {
        ClaimIncentiveStep0Fragment fragment = new ClaimIncentiveStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_claim_incentive_0, container, false);
        mCancelBtn          = rootView.findViewById(R.id.btn_cancel);
        mNextBtn            = rootView.findViewById(R.id.btn_next);
        mMyReward           = rootView.findViewById(R.id.my_reward_amount);
        mMyRewardDenom      = rootView.findViewById(R.id.my_reward_denom);
        mSenderAddress      = rootView.findViewById(R.id.sender_address);
        mCoinType           = rootView.findViewById(R.id.coin_type);
        mLockTime           = rootView.findViewById(R.id.lock_time);
        mCancelBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mMyRewardDenom);

        BigDecimal myReward = BigDecimal.ZERO;
        if (getSActivity().mKavaUnClaimedIncentiveRewards.size() > 0) {
            for (ResKavaIncentiveReward.KavaUnclaimedIncentiveReward reward:getSActivity().mKavaUnClaimedIncentiveRewards) {
                myReward = myReward.add(new BigDecimal(reward.reward.amount));
            }
        }
        mMyReward.setText(WDp.getDpAmount(getContext(), myReward, 6, getSActivity().mBaseChain));
        mSenderAddress.setText(getSActivity().mAccount.address);
        mCoinType.setText(getSActivity().mIncentiveReward.denom.toUpperCase());
        mLockTime.setText("365 Days");
        return rootView;
    }

    private ClaimIncentiveActivity getSActivity() {
        return (ClaimIncentiveActivity)getBaseActivity();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mCancelBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            getSActivity().onNextStep();
        }
    }
}
