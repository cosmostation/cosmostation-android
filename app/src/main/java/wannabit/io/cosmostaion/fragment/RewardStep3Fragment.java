package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

import cosmos.staking.v1beta1.Staking;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.ClaimRewardActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.AlertDialogUtils;
import wannabit.io.cosmostaion.utils.WDp;

public class RewardStep3Fragment extends BaseFragment implements View.OnClickListener {

    private TextView mTvRewardAmount;
    private TextView mFeeAmount;
    private TextView mTvFromValidators;
    private LinearLayout mTvGoalLayer;
    private TextView mTvGoalAddress, mMemo;
    private LinearLayout mExpectedLayer;
    private TextView mExpectedAmount, mExpectedPrice;
    private Button mBeforeBtn, mConfirmBtn;
    private TextView mDenomRewardAmount, mDenomFeeType, mDenomResultAmount;
    private int mDpDecimal = 6;

    public static RewardStep3Fragment newInstance(Bundle bundle) {
        RewardStep3Fragment fragment = new RewardStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reward_step3, container, false);
        mTvRewardAmount = rootView.findViewById(R.id.reward_amount);
        mFeeAmount = rootView.findViewById(R.id.reward_fees);
        mTvFromValidators = rootView.findViewById(R.id.reward_moniker);
        mTvGoalLayer = rootView.findViewById(R.id.reward_receive_layer);
        mTvGoalAddress = rootView.findViewById(R.id.reward_receive_address);
        mMemo = rootView.findViewById(R.id.memo);
        mExpectedLayer = rootView.findViewById(R.id.expected_Layer);
        mExpectedAmount = rootView.findViewById(R.id.expected_amount);
        mExpectedPrice = rootView.findViewById(R.id.expected_price);
        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mConfirmBtn = rootView.findViewById(R.id.btn_confirm);
        mDenomRewardAmount = rootView.findViewById(R.id.reward_amount_title);
        mDenomFeeType = rootView.findViewById(R.id.reward_fees_type);
        mDenomResultAmount = rootView.findViewById(R.id.expected_amount_title);

        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mDenomRewardAmount);
        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mDenomFeeType);
        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mDenomResultAmount);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onRefreshTab() {
        mDpDecimal = WDp.mainDivideDecimal(getSActivity().mBaseChain);
        BigDecimal rewardSum = BigDecimal.ZERO;
        BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);
        for (String opAddress : getSActivity().mValAddresses) {
            rewardSum = rewardSum.add(getSActivity().getBaseDao().getReward(WDp.mainDenom(getSActivity().mBaseChain), opAddress));
        }
        mTvRewardAmount.setText(WDp.getDpAmount2(getContext(), rewardSum, mDpDecimal, mDpDecimal));
        mFeeAmount.setText(WDp.getDpAmount2(getContext(), feeAmount, mDpDecimal, mDpDecimal));
        if (getSActivity().mWithdrawAddress.equals(getSActivity().mAccount.address)) {
            mTvGoalLayer.setVisibility(View.GONE);
            mExpectedLayer.setVisibility(View.VISIBLE);
            BigDecimal availableAmount = getBaseDao().getAvailable(WDp.mainDenom(getSActivity().mBaseChain));
            BigDecimal expectedAmount = availableAmount.add(rewardSum).subtract(feeAmount);
            mExpectedAmount.setText(WDp.getDpAmount2(getContext(), expectedAmount, mDpDecimal, mDpDecimal));
            mExpectedPrice.setText(WDp.dpUserCurrencyValue(getBaseDao(), WDp.mainDenom(getSActivity().mBaseChain), expectedAmount, mDpDecimal));

        } else {
            mTvGoalLayer.setVisibility(View.VISIBLE);
            mExpectedLayer.setVisibility(View.GONE);
        }
        String monikers = "";
        for (Staking.Validator validator : getBaseDao().mGRpcAllValidators) {
            boolean isMatch = false;
            for (String myVal : getSActivity().mValAddresses) {
                if (myVal.equals(validator.getOperatorAddress())) {
                    isMatch = true;
                    break;
                }
            }
            if (isMatch) {
                if (TextUtils.isEmpty(monikers)) {
                    monikers = validator.getDescription().getMoniker();
                } else {
                    monikers = monikers + ",    " + validator.getDescription().getMoniker();
                }
            }
        }
        mTvFromValidators.setText(monikers);
        mTvGoalAddress.setText(getSActivity().mWithdrawAddress);
        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            if (onCheckValidateRewardAndFee()) {
                getSActivity().onStartReward();

            } else {
                AlertDialogUtils.showSingleButtonDialog(getActivity(), getString(R.string.str_fee_over_title), getString(R.string.str_fee_over_msg), getString(R.string.str_ok), view -> {});
            }
        }
    }

    private boolean onCheckValidateRewardAndFee() {
        BigDecimal rewardSum = BigDecimal.ZERO;
        BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);
        for (String opAddress : getSActivity().mValAddresses) {
            rewardSum = rewardSum.add(getBaseDao().getReward(WDp.mainDenom(getSActivity().mBaseChain), opAddress));
        }
        return feeAmount.compareTo(rewardSum) < 0;
    }


    private ClaimRewardActivity getSActivity() {
        return (ClaimRewardActivity) getBaseActivity();
    }
}