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
import wannabit.io.cosmostaion.dao.BondingState;
import wannabit.io.cosmostaion.dialog.Dialog_Reward_Small;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;

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
        if (getSActivity().mBaseChain.equals(COSMOS_MAIN) || getSActivity().mBaseChain.equals(COSMOS_TEST) || getSActivity().mBaseChain.equals(IRIS_TEST)) {
            mRewardAmount.setText(WDp.getDpAmount2(getContext(), new BigDecimal(getSActivity().mReinvestCoin.amount).setScale(0, BigDecimal.ROUND_DOWN), 6, 6));
            mFeeAmount.setText(WDp.getDpAmount2(getContext(), new BigDecimal(getSActivity().mReinvestFee.amount.get(0).amount), 6, 6));
            BigDecimal current = getSActivity().getBaseDao().getDelegation(getSActivity().mValOpAddress);
            BigDecimal expected = current.add(new BigDecimal(getSActivity().mReinvestCoin.amount).setScale(0, BigDecimal.ROUND_DOWN));
            mCurrentAmount.setText(WDp.getDpAmount2(getContext(), current, 6, 6));
            mExpectedAmount.setText(WDp.getDpAmount2(getContext(), expected, 6, 6));
            mValidator.setText(getSActivity().getBaseDao().getValidatorInfo(getSActivity().mValOpAddress).getDescription().getMoniker());
            mMemo.setText(getSActivity().mReinvestMemo);

        } else {
            BondingState bonding = getBaseDao().onSelectBondingState(getSActivity().mAccount.id, getSActivity().mValidator.operator_address);
            if (getSActivity().mBaseChain.equals(KAVA_MAIN) || getSActivity().mBaseChain.equals(KAVA_TEST) ||
                    getSActivity().mBaseChain.equals(BAND_MAIN) || getSActivity().mBaseChain.equals(IOV_MAIN) || getSActivity().mBaseChain.equals(IOV_TEST) ||
                    getSActivity().mBaseChain.equals(CERTIK_MAIN) || getSActivity().mBaseChain.equals(CERTIK_TEST) || getSActivity().mBaseChain.equals(AKASH_MAIN) || getSActivity().mBaseChain.equals(SECRET_MAIN)) {
                mRewardAmount.setText(WDp.getDpAmount2(getContext(), new BigDecimal(getSActivity().mReinvestCoin.amount).setScale(0, BigDecimal.ROUND_DOWN), 6, 6));
                mFeeAmount.setText(WDp.getDpAmount2(getContext(), new BigDecimal(getSActivity().mReinvestFee.amount.get(0).amount), 6, 6));
                if(bonding != null && bonding.getBondingAmount(getSActivity().mValidator) != null) {
                    mCurrentAmount.setText(WDp.getDpAmount2(getContext(), bonding.getBondingAmount(getSActivity().mValidator), 6, 6));
                    BigDecimal expected = bonding.getBondingAmount(getSActivity().mValidator).add(new BigDecimal(getSActivity().mReinvestCoin.amount).setScale(0, BigDecimal.ROUND_DOWN));
                    mExpectedAmount.setText(WDp.getDpAmount2(getContext(), expected, 6, 6));
                }

            } else if (getSActivity().mBaseChain.equals(IRIS_MAIN)) {
                mRewardAmount.setText(WDp.getDpAmount2(getContext(), new BigDecimal(getSActivity().mReinvestCoin.amount).setScale(0, BigDecimal.ROUND_DOWN), 18, 18));
                mFeeAmount.setText(WDp.getDpAmount2(getContext(), new BigDecimal(getSActivity().mReinvestFee.amount.get(0).amount), 18, 18));
                if(bonding != null && bonding.getBondingAmount(getSActivity().mValidator) != null) {
                    mCurrentAmount.setText(WDp.getDpAmount2(getContext(), bonding.getBondingAmount(getSActivity().mValidator), 18, 18));
                    BigDecimal expected = bonding.getBondingAmount(getSActivity().mValidator).add(new BigDecimal(getSActivity().mReinvestCoin.amount).setScale(0, BigDecimal.ROUND_DOWN));
                    mExpectedAmount.setText(WDp.getDpAmount2(getContext(), expected, 18, 18));
                }
            }
            mValidator.setText(getSActivity().mValidator.description.moniker);
            mMemo.setText(getSActivity().mReinvestMemo);
        }

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
