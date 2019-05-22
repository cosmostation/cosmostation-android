package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.ClaimRewardActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Reward;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class RewardStep3Fragment extends BaseFragment implements View.OnClickListener {

    private TextView        mTvAtomReward;
    private TextView        mFeeAmount, mFeeType;
    private TextView        mTvFromValidators;
    private LinearLayout    mTvGoalLayer;
    private TextView        mTvGoalAddress, mMemo;
    private TextView        mExpectedAmount, mExpectedPrice;
    private Button          mBeforeBtn, mConfirmBtn;

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
        mTvAtomReward       = rootView.findViewById(R.id.reward_atom);
        mFeeAmount          = rootView.findViewById(R.id.reward_fees);
        mFeeType            = rootView.findViewById(R.id.reward_fees_type);
        mTvFromValidators   = rootView.findViewById(R.id.reward_moniker);
        mTvGoalLayer        = rootView.findViewById(R.id.reward_receive_layer);
        mTvGoalAddress      = rootView.findViewById(R.id.reward_receive_address);
        mMemo               = rootView.findViewById(R.id.memo);
        mExpectedAmount     = rootView.findViewById(R.id.expected_atom);
        mExpectedPrice      = rootView.findViewById(R.id.expected_price);
        mBeforeBtn          = rootView.findViewById(R.id.btn_before);
        mConfirmBtn         = rootView.findViewById(R.id.btn_confirm);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onRefreshTab() {
        BigDecimal rewardSum    = BigDecimal.ZERO;
        BigDecimal feeAtom      = new BigDecimal(getSActivity().mRewardFee.amount.get(0).amount);

        for (Reward reward:getSActivity().mRewards) {
            rewardSum = rewardSum.add(new BigDecimal(reward.amount.get(0).amount).setScale(0, BigDecimal.ROUND_DOWN));
        }
        mTvAtomReward.setText(WDp.getDpAmount(getContext(), rewardSum, 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));
        mFeeAmount.setText(WDp.getDpAmount(getContext(), feeAtom, 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));

        String monikers = "";
        for (Validator validator:getSActivity().mValidators) {
            if(TextUtils.isEmpty(monikers)) {
                monikers = validator.description.moniker;
            } else {
                monikers = monikers + ",    " + validator.description.moniker;
            }
        }
        mTvFromValidators.setText(monikers);

        mTvGoalAddress.setText(getSActivity().mWithdrawAddress);
        if(getSActivity().mWithdrawAddress.equals(getSActivity().mAccount.address)) {
            mTvGoalLayer.setVisibility(View.GONE);
        } else {
            mTvGoalLayer.setVisibility(View.VISIBLE);
        }

        mMemo.setText(getSActivity().mRewardMemo);

        BigDecimal currentAtom      = getSActivity().mAccount.getAtomBalance();
        BigDecimal expectedAtom     = currentAtom.add(rewardSum).subtract(feeAtom);
        mExpectedAmount.setText(WDp.getDpAmount(getContext(), expectedAtom, 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));
//        BigDecimal expectedPrice = new BigDecimal(""+mExpectedAmount.getText().toString().trim().replace(",","")).multiply(new BigDecimal(""+getBaseDao().getLastAtomTic())).setScale(2, RoundingMode.DOWN);
        BigDecimal expectedPrice = expectedAtom.multiply(new BigDecimal(""+getBaseDao().getLastAtomTic())).divide(new BigDecimal("1000000"), 2, RoundingMode.DOWN);
        mExpectedPrice.setText(getString(R.string.str_approximately)+ " $" +  WDp.getDolor(getContext(), expectedPrice));

    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            WLog.w("mConfirmBtn");
            getSActivity().onStartReward();

        }

    }


    private ClaimRewardActivity getSActivity() {
        return (ClaimRewardActivity)getBaseActivity();
    }
}