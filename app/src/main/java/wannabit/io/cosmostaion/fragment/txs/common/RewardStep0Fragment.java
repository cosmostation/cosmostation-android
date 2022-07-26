package wannabit.io.cosmostaion.fragment.txs.common;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

import cosmos.staking.v1beta1.Staking;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.common.ClaimRewardActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class RewardStep0Fragment extends BaseFragment implements View.OnClickListener {

    private TextView        mTvRewardAmount, mTvDenomTitle;
    private TextView        mTvFromValidators;
    private LinearLayout    mReceiveLayer;
    private TextView        mTvReceiveAddress;
    private RelativeLayout  mProgressBar;
    private Button          mCancelBtn, mNextBtn;

    public static RewardStep0Fragment newInstance() {
        return new RewardStep0Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reward_step0, container, false);
        mTvRewardAmount         = rootView.findViewById(R.id.reward_amount);
        mTvDenomTitle           = rootView.findViewById(R.id.reward_denom);
        mTvFromValidators       = rootView.findViewById(R.id.reward_moniker);
        mReceiveLayer           = rootView.findViewById(R.id.reward_receive_address_layer);
        mTvReceiveAddress       = rootView.findViewById(R.id.reward_receive_address);
        mProgressBar            = rootView.findViewById(R.id.reward_progress);
        mCancelBtn              = rootView.findViewById(R.id.btn_cancel);
        mNextBtn                = rootView.findViewById(R.id.btn_next);

        mCancelBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        mNextBtn.setClickable(false);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        BigDecimal rewardSum = BigDecimal.ZERO;
        for (String opAddress: getSActivity().mValAddresses) {
            rewardSum = rewardSum.add(getSActivity().getBaseDao().getReward(getSActivity().mChainConfig.mainDenom(), opAddress));
        }
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mChainConfig.mainDenom(), rewardSum.toPlainString(), mTvDenomTitle, mTvRewardAmount);
        String monikers = "";
        for (Staking.Validator validator: getBaseDao().mGRpcAllValidators) {
            boolean isMatch = false;
            for (String myVal: getSActivity().mValAddresses) {
                if (myVal.equals(validator.getOperatorAddress())) { isMatch = true; break; }
            }
            if (isMatch) {
                if (TextUtils.isEmpty(monikers)) {  monikers = validator.getDescription().getMoniker(); }
                else { monikers = monikers + ",    " + validator.getDescription().getMoniker(); }
            }
        }
        mTvFromValidators.setText(monikers);

        mTvReceiveAddress.setText(getSActivity().mWithdrawAddress);
        if (getSActivity().mWithdrawAddress.equals(getSActivity().mAccount.address)) {
            mReceiveLayer.setVisibility(View.GONE);
        } else {
            mReceiveLayer.setVisibility(View.VISIBLE);
        }
        mProgressBar.setVisibility(View.GONE);
        mNextBtn.setClickable(true);
    }


    @Override
    public void onClick(View v) {
        if (v.equals(mCancelBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            getSActivity().onNextStep();
        }
    }

    private ClaimRewardActivity getSActivity() {
        return (ClaimRewardActivity)getBaseActivity();
    }
}
