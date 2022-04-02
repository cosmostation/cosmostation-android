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
import wannabit.io.cosmostaion.activities.ReInvestActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_Reward_Small;
import wannabit.io.cosmostaion.utils.WDp;

public class ReInvestStep3Fragment extends BaseFragment implements View.OnClickListener {

    private TextView mRewardAmount, mFeeAmount;
    private TextView mRewardDenom, mFeeDenom;
    private TextView mValidator, mMemo, mCurrentAmount, mExpectedAmount;
    private TextView mCurrentDenom, mExpectedDenom;
    private Button mBeforeBtn, mConfirmBtn;
    private int mDpDecimal = 6;


    public static ReInvestStep3Fragment newInstance(Bundle bundle) {
        ReInvestStep3Fragment fragment = new ReInvestStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reinvest_step3, container, false);
        mRewardAmount = rootView.findViewById(R.id.reward_amount);
        mRewardDenom = rootView.findViewById(R.id.reward_amount_title);
        mFeeAmount = rootView.findViewById(R.id.reward_fees);
        mFeeDenom = rootView.findViewById(R.id.reward_fees_type);
        mValidator = rootView.findViewById(R.id.reward_moniker);
        mMemo = rootView.findViewById(R.id.memo);
        mCurrentAmount = rootView.findViewById(R.id.current_delegation);
        mCurrentDenom = rootView.findViewById(R.id.current_delegation_title);
        mExpectedAmount = rootView.findViewById(R.id.expected_delegation);
        mExpectedDenom = rootView.findViewById(R.id.expected_delegation_title);

        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mConfirmBtn = rootView.findViewById(R.id.btn_confirm);

        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mRewardDenom);
        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mFeeDenom);
        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mCurrentDenom);
        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mExpectedDenom);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onRefreshTab() {
        mDpDecimal = WDp.mainDivideDecimal(getSActivity().mBaseChain);
        mRewardAmount.setText(WDp.getDpAmount2(getContext(), new BigDecimal(getSActivity().mAmount.amount).setScale(0, BigDecimal.ROUND_DOWN), mDpDecimal, mDpDecimal));
        mFeeAmount.setText(WDp.getDpAmount2(getContext(), new BigDecimal(getSActivity().mTxFee.amount.get(0).amount), mDpDecimal, mDpDecimal));
        BigDecimal current = getSActivity().getBaseDao().getDelegation(getSActivity().mValAddress);
        BigDecimal expected = current.add(new BigDecimal(getSActivity().mAmount.amount).setScale(0, BigDecimal.ROUND_DOWN));
        mCurrentAmount.setText(WDp.getDpAmount2(getContext(), current, mDpDecimal, mDpDecimal));
        mExpectedAmount.setText(WDp.getDpAmount2(getContext(), expected, mDpDecimal, mDpDecimal));
        mValidator.setText(getSActivity().getBaseDao().getValidatorInfo(getSActivity().mValAddress).getDescription().getMoniker());
        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            if (onCheckValidateRewardAndFee()) {
                getSActivity().onStartReInvest();

            } else {
                Dialog_Reward_Small dialog = Dialog_Reward_Small.newInstance();
                showDialog(dialog);
            }

        }

    }

    private boolean onCheckValidateRewardAndFee() {
        BigDecimal reward = new BigDecimal(getSActivity().mAmount.amount).setScale(0, BigDecimal.ROUND_DOWN);
        BigDecimal feeAtom = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);
        return feeAtom.compareTo(reward) < 0;
    }

    private ReInvestActivity getSActivity() {
        return (ReInvestActivity) getBaseActivity();
    }
}
