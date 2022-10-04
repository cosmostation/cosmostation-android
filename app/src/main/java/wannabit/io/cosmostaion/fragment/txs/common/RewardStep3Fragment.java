package wannabit.io.cosmostaion.fragment.txs.common;

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
import wannabit.io.cosmostaion.activities.txs.common.ClaimRewardActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
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
    private TextView mRewardDenom, mFeeDenom, mResultDenom;

    public static RewardStep3Fragment newInstance() {
        return new RewardStep3Fragment();
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
        mRewardDenom = rootView.findViewById(R.id.reward_denom);
        mFeeDenom = rootView.findViewById(R.id.reward_fees_type);
        mResultDenom = rootView.findViewById(R.id.expected_denom);
        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mConfirmBtn = rootView.findViewById(R.id.btn_confirm);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onRefreshTab() {
        BigDecimal rewardSum = BigDecimal.ZERO;
        BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);
        for (String opAddress : getSActivity().mValAddresses) {
            rewardSum = rewardSum.add(getSActivity().getBaseDao().getReward(getSActivity().mChainConfig.mainDenom(), opAddress));
        }
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mChainConfig.mainDenom(), rewardSum.toPlainString(), mRewardDenom, mTvRewardAmount);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), mFeeDenom, mFeeAmount);

        if (getSActivity().mWithdrawAddress.equals(getSActivity().mAccount.address)) {
            mTvGoalLayer.setVisibility(View.GONE);
            mExpectedLayer.setVisibility(View.VISIBLE);
            BigDecimal availableAmount = getBaseDao().getAvailable(getSActivity().mChainConfig.mainDenom());
            BigDecimal expectedAmount = BigDecimal.ZERO;
            if (getSActivity().mChainConfig.mainDenom().equalsIgnoreCase(getSActivity().mTxFee.amount.get(0).denom)) {
                expectedAmount = availableAmount.add(rewardSum).subtract(feeAmount);
            } else {
                expectedAmount = availableAmount.add(rewardSum);
            }
            WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mChainConfig.mainDenom(), expectedAmount.toPlainString(), mResultDenom, mExpectedAmount);
            mExpectedPrice.setText(WDp.dpAssetValue(getBaseDao(), getSActivity().mChainConfig.mainDenom(), expectedAmount, WDp.getDenomDecimal(getBaseDao(), getSActivity().mChainConfig, getSActivity().mChainConfig.mainDenom())));

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
            getSActivity().onStartReward();
        }
    }

    private ClaimRewardActivity getSActivity() {
        return (ClaimRewardActivity) getBaseActivity();
    }
}