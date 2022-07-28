package wannabit.io.cosmostaion.fragment.txs.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.common.ReInvestActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class ReInvestStep3Fragment extends BaseFragment implements View.OnClickListener {

    private TextView    mRewardAmount, mFeeAmount;
    private TextView    mRewardDenom, mFeeDenom;
    private TextView    mValidator, mMemo, mCurrentAmount, mExpectedAmount;
    private TextView    mCurrentDenom, mExpectedDenom;
    private Button      mBeforeBtn, mConfirmBtn;

    public static ReInvestStep3Fragment newInstance() {
        return new ReInvestStep3Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reinvest_step3, container, false);
        mRewardAmount       = rootView.findViewById(R.id.reward_amount);
        mRewardDenom        = rootView.findViewById(R.id.reward_denom);
        mFeeAmount          = rootView.findViewById(R.id.reward_fees);
        mFeeDenom           = rootView.findViewById(R.id.reward_fees_type);
        mValidator          = rootView.findViewById(R.id.reward_moniker);
        mMemo               = rootView.findViewById(R.id.memo);
        mCurrentAmount      = rootView.findViewById(R.id.current_delegation);
        mCurrentDenom       = rootView.findViewById(R.id.current_delegation_denom);
        mExpectedAmount     = rootView.findViewById(R.id.expected_delegation);
        mExpectedDenom      = rootView.findViewById(R.id.expected_delegation_denom);
        mBeforeBtn          = rootView.findViewById(R.id.btn_before);
        mConfirmBtn         = rootView.findViewById(R.id.btn_confirm);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onRefreshTab() {
        BigDecimal currentAmount = new BigDecimal(getSActivity().mAmount.amount).setScale(0, BigDecimal.ROUND_DOWN);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mChainConfig.mainDenom(), currentAmount.toPlainString(), mRewardDenom, mRewardAmount);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), mFeeDenom, mFeeAmount);

        BigDecimal currentDeleAmount = getSActivity().getBaseDao().getDelegation(getSActivity().mValAddress);
        BigDecimal expectedAmount = currentDeleAmount.add(currentAmount);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mChainConfig.mainDenom(), currentDeleAmount.toPlainString(), mCurrentDenom, mCurrentAmount);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mChainConfig.mainDenom(), expectedAmount.toPlainString(), mExpectedDenom, mExpectedAmount);
        mValidator.setText(getSActivity().getBaseDao().getValidatorInfo(getSActivity().mValAddress).getDescription().getMoniker());
        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartReInvest();
        }
    }

    private ReInvestActivity getSActivity() {
        return (ReInvestActivity)getBaseActivity();
    }
}
