package wannabit.io.cosmostaion.fragment.chains.kava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.ClaimHardIncentiveActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;

public class ClaimHardIncentiveStep3Fragment extends BaseFragment implements View.OnClickListener {

    private Button mBackBtn, mConfirmBtn;
    private TextView mFee, mFeeDenom;
    private TextView mKReceivableAmount, mKReceivableAmountDenom;
    private TextView mHReceivableAmount, mHReceivableAmountDenom;
    private TextView mLockTime, mClaimType, mMemo;

    public static ClaimHardIncentiveStep3Fragment newInstance(Bundle bundle) {
        ClaimHardIncentiveStep3Fragment fragment = new ClaimHardIncentiveStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_claim_harvest3, container, false);
        mBackBtn                = rootView.findViewById(R.id.btn_before);
        mConfirmBtn             = rootView.findViewById(R.id.btn_confirm);
        mFee                    = rootView.findViewById(R.id.fee_amount);
        mFeeDenom               = rootView.findViewById(R.id.fee_denom);
        mKReceivableAmount      = rootView.findViewById(R.id.kava_receivable_amount);
        mKReceivableAmountDenom = rootView.findViewById(R.id.kava_receivable_denom);
        mHReceivableAmount      = rootView.findViewById(R.id.hard_receivable_amount);
        mHReceivableAmountDenom = rootView.findViewById(R.id.hard_receivable_denom);
        mLockTime               = rootView.findViewById(R.id.lockup_time);
        mClaimType              = rootView.findViewById(R.id.claim_type);
        mMemo                   = rootView.findViewById(R.id.memo);

        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mFeeDenom);

        mBackBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        WDp.showCoinDp(getContext(), TOKEN_KAVA, getSActivity().mKReceivableAmount.toPlainString(), mKReceivableAmountDenom, mKReceivableAmount, getSActivity().mBaseChain);
        WDp.showCoinDp(getContext(), TOKEN_HARD, getSActivity().mHReceivableAmount.toPlainString(), mHReceivableAmountDenom, mHReceivableAmount, getSActivity().mBaseChain);
        BigDecimal feeAmount = new BigDecimal(getSActivity().mFee.amount.get(0).amount);
        mFee.setText(WDp.getDpAmount2(getContext(), feeAmount, 6, 6));
        mLockTime.setText(getSActivity().mSelectedMultiplier.months_lockup + " Month");
        mClaimType.setText(getSActivity().mSelectedMultiplier.name.toUpperCase());
        mMemo.setText(getSActivity().mMemo);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBackBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartHarvestRewardClaim();

        }
    }

    private ClaimHardIncentiveActivity getSActivity() {
        return (ClaimHardIncentiveActivity)getBaseActivity();
    }
}

