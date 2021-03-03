package wannabit.io.cosmostaion.fragment.chains.kava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.math.RoundingMode;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.ClaimHardIncentiveActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;

public class ClaimHardIncentiveStep0Fragment extends BaseFragment implements View.OnClickListener {

    private Button mCancelBtn, mNextBtn;
    private TextView mKRewardAmount, mKRewardAmountDenom;
    private TextView mHRewardAmount, mHRewardAmountDenom;
    private TextView mLockTime;
    private TextView mKReceivableAmount, mKReceivableAmountDenom;
    private TextView mHReceivableAmount, mHReceivableAmountDenom;

    private RelativeLayout BtnOption1, BtnOption2, BtnOption3;
    private TextView OptionTitle1, OptionTitle2, OptionTitle3;

    public static ClaimHardIncentiveStep0Fragment newInstance(Bundle bundle) {
        ClaimHardIncentiveStep0Fragment fragment = new ClaimHardIncentiveStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_claim_harvest0, container, false);
        mCancelBtn              = rootView.findViewById(R.id.btn_cancel);
        mNextBtn                = rootView.findViewById(R.id.btn_next);
        mKRewardAmount          = rootView.findViewById(R.id.kava_reward_amount);
        mKRewardAmountDenom     = rootView.findViewById(R.id.kava_reward_denom);
        mHRewardAmount          = rootView.findViewById(R.id.harvest_reward_amount);
        mHRewardAmountDenom     = rootView.findViewById(R.id.harvest_reward_denom);
        mLockTime               = rootView.findViewById(R.id.lockup_time);
        mKReceivableAmount      = rootView.findViewById(R.id.kava_receivable_amount);
        mKReceivableAmountDenom = rootView.findViewById(R.id.kava_receivable_denom);
        mHReceivableAmount      = rootView.findViewById(R.id.hard_receivable_amount);
        mHReceivableAmountDenom = rootView.findViewById(R.id.hard_receivable_denom);
        BtnOption1              = rootView.findViewById(R.id.btn_option1);
        BtnOption2              = rootView.findViewById(R.id.btn_option2);
        BtnOption3              = rootView.findViewById(R.id.btn_option3);
        OptionTitle1            = rootView.findViewById(R.id.btn_option1_text);
        OptionTitle2            = rootView.findViewById(R.id.btn_option2_text);
        OptionTitle3            = rootView.findViewById(R.id.btn_option3_text);
        mCancelBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        BtnOption1.setOnClickListener(this);
        BtnOption2.setOnClickListener(this);
        BtnOption3.setOnClickListener(this);

        if (getSActivity().mClaimMultipliers.size() > 0) {
            BtnOption1.setVisibility(View.VISIBLE);
            OptionTitle1.setText(getSActivity().mClaimMultipliers.get(0).name.toUpperCase());
        }
        if (getSActivity().mClaimMultipliers.size() > 1) {
            BtnOption2.setVisibility(View.VISIBLE);
            OptionTitle2.setText(getSActivity().mClaimMultipliers.get(1).name.toUpperCase());
        }
        if (getSActivity().mClaimMultipliers.size() > 2) {
            BtnOption3.setVisibility(View.VISIBLE);
            OptionTitle3.setText(getSActivity().mClaimMultipliers.get(2).name.toUpperCase());
        }

        WDp.showCoinDp(getContext(), TOKEN_KAVA, getSActivity().mIncentiveReward5.getHardPoolKavaRewardAmount().toPlainString(), mKRewardAmountDenom, mKRewardAmount, getSActivity().mBaseChain);
        WDp.showCoinDp(getContext(), TOKEN_HARD, getSActivity().mIncentiveReward5.getHardPoolHardRewardAmount().toPlainString(), mHRewardAmountDenom, mHRewardAmount, getSActivity().mBaseChain);

        return rootView;
    }

    private void onUpdateView() {
        BigDecimal kreceivable = getSActivity().mIncentiveReward5.getHardPoolKavaRewardAmount().multiply(new BigDecimal(getSActivity().mSelectedMultiplier.factor)).setScale(0, RoundingMode.DOWN);
        WDp.showCoinDp(getContext(), TOKEN_KAVA, kreceivable.toPlainString(), mKReceivableAmountDenom, mKReceivableAmount, getSActivity().mBaseChain);
        getSActivity().mKReceivableAmount = kreceivable;

        BigDecimal hreceivable = getSActivity().mIncentiveReward5.getHardPoolHardRewardAmount().multiply(new BigDecimal(getSActivity().mSelectedMultiplier.factor)).setScale(0, RoundingMode.DOWN);
        WDp.showCoinDp(getContext(), TOKEN_HARD, hreceivable.toPlainString(), mHReceivableAmountDenom, mHReceivableAmount, getSActivity().mBaseChain);
        getSActivity().mHReceivableAmount = hreceivable;
        mLockTime.setText(getSActivity().mSelectedMultiplier.months_lockup + " Month");
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mCancelBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            if (getSActivity().mSelectedMultiplier != null) {
                getSActivity().onNextStep();
            } else {
                Toast.makeText(getContext(), R.string.error_no_incentive_claim_option, Toast.LENGTH_SHORT).show();
            }

        } else if (v.equals(BtnOption1)) {
            onInitBtnBg();
            BtnOption1.setBackground(getResources().getDrawable(R.drawable.box_round_selected));
            getSActivity().mSelectedMultiplier = getSActivity().mClaimMultipliers.get(0);
            onUpdateView();

        } else if (v.equals(BtnOption2)) {
            onInitBtnBg();
            BtnOption2.setBackground(getResources().getDrawable(R.drawable.box_round_selected));
            getSActivity().mSelectedMultiplier = getSActivity().mClaimMultipliers.get(1);
            onUpdateView();

        } else if (v.equals(BtnOption3)) {
            onInitBtnBg();
            BtnOption3.setBackground(getResources().getDrawable(R.drawable.box_round_selected));
            getSActivity().mSelectedMultiplier = getSActivity().mClaimMultipliers.get(2);
            onUpdateView();

        }

    }

    private void onInitBtnBg() {
        BtnOption1.setBackground(getResources().getDrawable(R.drawable.box_round_unselected));
        BtnOption2.setBackground(getResources().getDrawable(R.drawable.box_round_unselected));
        BtnOption3.setBackground(getResources().getDrawable(R.drawable.box_round_unselected));
    }

    private ClaimHardIncentiveActivity getSActivity() {
        return (ClaimHardIncentiveActivity)getBaseActivity();
    }
}
