package wannabit.io.cosmostaion.fragment.txs.authz;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import cosmos.distribution.v1beta1.Distribution;
import cosmos.staking.v1beta1.Staking;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.authz.AuthzClaimRewardActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;

public class AuthzClaimRewardStep3Fragment extends BaseFragment implements View.OnClickListener {

    private TextView mTvRewardAmount;
    private TextView mFeeAmount;
    private TextView mTvFromValidators;
    private LinearLayout mTvGoalLayer;
    private TextView mTvGoalAddress, mMemo;
    private Button mBeforeBtn, mConfirmBtn;
    private TextView mRewardDenom, mFeeDenom;

    public static AuthzClaimRewardStep3Fragment newInstance() {
        return new AuthzClaimRewardStep3Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_authz_reward_step3, container, false);
        mTvRewardAmount = rootView.findViewById(R.id.reward_amount);
        mFeeAmount = rootView.findViewById(R.id.reward_fees);
        mTvFromValidators = rootView.findViewById(R.id.reward_moniker);
        mTvGoalLayer = rootView.findViewById(R.id.reward_receive_layer);
        mTvGoalAddress = rootView.findViewById(R.id.reward_receive_address);
        mMemo = rootView.findViewById(R.id.memo);
        mRewardDenom = rootView.findViewById(R.id.reward_denom);
        mFeeDenom = rootView.findViewById(R.id.reward_fees_type);
        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mConfirmBtn = rootView.findViewById(R.id.btn_confirm);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        Coin mainReward = getSActivity().mGranterRewardSum;
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, mainReward, mRewardDenom, mTvRewardAmount);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), mFeeDenom, mFeeAmount);

        String monikers = "";
        for (Staking.Validator validator: getBaseDao().mGRpcAllValidators) {
            for (Distribution.DelegationDelegatorReward myValidator : getSActivity().mGranterRewards) {
                if (validator.getOperatorAddress().equalsIgnoreCase(myValidator.getValidatorAddress())) {
                    if (!TextUtils.isEmpty(monikers)) {
                        monikers = monikers + ",    " + validator.getDescription().getMoniker();
                    } else {
                        monikers = validator.getDescription().getMoniker();
                    }
                }
            }
        }
        mTvFromValidators.setText(monikers);

        mTvGoalAddress.setText(getSActivity().mWithdrawAddress);
        if (getSActivity().mGranter.equalsIgnoreCase(getSActivity().mWithdrawAddress)) {
            mTvGoalLayer.setVisibility(View.GONE);
        } else {
            mTvGoalLayer.setVisibility(View.VISIBLE);
        }

        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onAuthzClaimReward();
        }
    }

    private AuthzClaimRewardActivity getSActivity() {
        return (AuthzClaimRewardActivity) getBaseActivity();
    }
}
