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

public class ClaimIncentiveStep3Fragment extends BaseFragment implements View.OnClickListener {

    private Button mBackBtn, mConfirmBtn;
    private TextView mFee, mFeeDenom;
    private TextView mMyReward, mMyrewardDenom;
    private TextView mSenderAddress, mCoinType, mLockTime, mMemo;


    public static ClaimIncentiveStep3Fragment newInstance(Bundle bundle) {
        ClaimIncentiveStep3Fragment fragment = new ClaimIncentiveStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_claim_incentive_3, container, false);
        mBackBtn        = rootView.findViewById(R.id.btn_before);
        mConfirmBtn     = rootView.findViewById(R.id.btn_confirm);
        mFee            = rootView.findViewById(R.id.fee_amount);
        mFeeDenom       = rootView.findViewById(R.id.fee_denom);
        mMyReward       = rootView.findViewById(R.id.my_reward_amount);
        mMyrewardDenom  = rootView.findViewById(R.id.my_reward_denom);
        mSenderAddress  = rootView.findViewById(R.id.sender_address);
        mCoinType       = rootView.findViewById(R.id.coin_type);
        mLockTime       = rootView.findViewById(R.id.lock_time);
        mMemo           = rootView.findViewById(R.id.memo);

        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mFeeDenom);
        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mMyrewardDenom);

        mBackBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        BigDecimal feeAmount = new BigDecimal(getSActivity().mFee.amount.get(0).amount);
        BigDecimal myReward  = BigDecimal.ZERO;
        if (getSActivity().mKavaUnClaimedIncentiveRewards.size() > 0) {
            for (ResKavaIncentiveReward.KavaUnclaimedIncentiveReward reward:getSActivity().mKavaUnClaimedIncentiveRewards) {
                myReward = myReward.add(new BigDecimal(reward.reward.amount));
            }
        }
        mFee.setText(WDp.getDpAmount(getContext(), feeAmount, 6, getSActivity().mBaseChain));
        mMyReward.setText(WDp.getDpAmount(getContext(), myReward, 6, getSActivity().mBaseChain));
        mSenderAddress.setText(getSActivity().mAccount.address);
        mCoinType.setText(getSActivity().mIncentiveReward.denom.toUpperCase());
        mLockTime.setText("365 Days");
        mMemo.setText(getSActivity().mMemo);

    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBackBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartIncentiveClaim();

        }

    }

    private ClaimIncentiveActivity getSActivity() {
        return (ClaimIncentiveActivity)getBaseActivity();
    }
}