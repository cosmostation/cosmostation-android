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
import wannabit.io.cosmostaion.activities.ReInvestActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.BondingState;
import wannabit.io.cosmostaion.dialog.Dialog_Reward_Small;
import wannabit.io.cosmostaion.utils.WDp;

public class ReInvestStep3Fragment extends BaseFragment implements View.OnClickListener {

    private TextView    mRewardAmount, mFeeAmount;
    private TextView    mRewardDenom, mFeeDenom;
    private TextView    mValidator, mMemo, mCurrentAmount, mExpectedAmount;
    private TextView    mCurrentDenom, mExpectedDenom;
    private Button      mBeforeBtn, mConfirmBtn;


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
        mRewardAmount       = rootView.findViewById(R.id.reward_amount);
        mRewardDenom        = rootView.findViewById(R.id.reward_amount_title);
        mFeeAmount          = rootView.findViewById(R.id.reward_fees);
        mFeeDenom           = rootView.findViewById(R.id.reward_fees_type);
        mValidator          = rootView.findViewById(R.id.reward_moniker);
        mMemo               = rootView.findViewById(R.id.memo);
        mCurrentAmount      = rootView.findViewById(R.id.current_delegation);
        mCurrentDenom       = rootView.findViewById(R.id.current_delegation_title);
        mExpectedAmount     = rootView.findViewById(R.id.expected_delegation);
        mExpectedDenom      = rootView.findViewById(R.id.expected_delegation_title);

        mBeforeBtn          = rootView.findViewById(R.id.btn_before);
        mConfirmBtn         = rootView.findViewById(R.id.btn_confirm);

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
        BondingState bonding = getBaseDao().onSelectBondingState(getSActivity().mAccount.id, getSActivity().mValidator.operator_address);
        if (getSActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN)
                || getSActivity().mBaseChain.equals(BaseChain.KAVA_MAIN)
                || getSActivity().mBaseChain.equals(BaseChain.KAVA_TEST)) {
            mRewardAmount.setText(WDp.getDpAmount(getContext(), new BigDecimal(getSActivity().mReinvestCoin.amount).setScale(0, BigDecimal.ROUND_DOWN), 6, getSActivity().mBaseChain));
            mFeeAmount.setText(WDp.getDpAmount(getContext(), new BigDecimal(getSActivity().mReinvestFee.amount.get(0).amount), 6, getSActivity().mBaseChain));
            if(bonding != null && bonding.getBondingAmount(getSActivity().mValidator) != null) {
                mCurrentAmount.setText(WDp.getDpAmount(getContext(), bonding.getBondingAmount(getSActivity().mValidator), 6, getSActivity().mBaseChain));
                BigDecimal expected = bonding.getBondingAmount(getSActivity().mValidator).add(new BigDecimal(getSActivity().mReinvestCoin.amount).setScale(0, BigDecimal.ROUND_DOWN));
                mExpectedAmount.setText(WDp.getDpAmount(getContext(), expected, 6, getSActivity().mBaseChain));
            }

        } else if (getSActivity().mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            mRewardAmount.setText(WDp.getDpAmount(getContext(), new BigDecimal(getSActivity().mReinvestCoin.amount).setScale(0, BigDecimal.ROUND_DOWN), 18, getSActivity().mBaseChain));
            mFeeAmount.setText(WDp.getDpAmount(getContext(), new BigDecimal(getSActivity().mReinvestFee.amount.get(0).amount), 18, getSActivity().mBaseChain));
            if(bonding != null && bonding.getBondingAmount(getSActivity().mValidator) != null) {
                mCurrentAmount.setText(WDp.getDpAmount(getContext(), bonding.getBondingAmount(getSActivity().mValidator), 18, getSActivity().mBaseChain));
                BigDecimal expected = bonding.getBondingAmount(getSActivity().mValidator).add(new BigDecimal(getSActivity().mReinvestCoin.amount).setScale(0, BigDecimal.ROUND_DOWN));
                mExpectedAmount.setText(WDp.getDpAmount(getContext(), expected, 18, getSActivity().mBaseChain));
            }
        }
        mValidator.setText(getSActivity().mValidator.description.moniker);
        mMemo.setText(getSActivity().mReinvestMemo);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            if(onCheckValidateRewardAndFee()) {
                getSActivity().onStartReInvest();

            } else {
                Dialog_Reward_Small dialog = Dialog_Reward_Small.newInstance();
                dialog.setCancelable(true);
                dialog.show(getFragmentManager().beginTransaction(), "dialog");
            }

        }

    }

    private boolean onCheckValidateRewardAndFee() {
        BigDecimal reward       = new BigDecimal(getSActivity().mReinvestCoin.amount).setScale(0, BigDecimal.ROUND_DOWN);
        BigDecimal feeAtom      = new BigDecimal(getSActivity().mReinvestFee.amount.get(0).amount);
        return feeAtom.compareTo(reward) < 0;
    }

    private ReInvestActivity getSActivity() {
        return (ReInvestActivity)getBaseActivity();
    }
}
