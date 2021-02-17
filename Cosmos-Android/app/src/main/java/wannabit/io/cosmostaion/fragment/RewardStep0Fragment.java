package wannabit.io.cosmostaion.fragment;

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
import androidx.cardview.widget.CardView;

import java.math.BigDecimal;

import cosmos.staking.v1beta1.Staking;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.ClaimRewardActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Reward;
import wannabit.io.cosmostaion.model.type.Validator;
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

public class RewardStep0Fragment extends BaseFragment implements View.OnClickListener {

    private CardView        mCardReward;
    private TextView        mTvRewardAmount, mTvDenomTitle;
    private TextView        mTvFromValidators;

    private LinearLayout    mReceiveLayer;
    private TextView        mTvReceiveAddress;
    private RelativeLayout  mProgressBar;

    private Button          mCancelBtn, mNextBtn;

    public static RewardStep0Fragment newInstance(Bundle bundle) {
        RewardStep0Fragment fragment = new RewardStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reward_step0, container, false);
        mCardReward             = rootView.findViewById(R.id.reward_card);
        mTvRewardAmount         = rootView.findViewById(R.id.reward_amount);
        mTvDenomTitle           = rootView.findViewById(R.id.reward_denom_title);
        mTvFromValidators       = rootView.findViewById(R.id.reward_moniker);
        mReceiveLayer           = rootView.findViewById(R.id.reward_receive_address_layer);
        mTvReceiveAddress       = rootView.findViewById(R.id.reward_receive_address);
        mProgressBar            = rootView.findViewById(R.id.reward_progress);
        mCancelBtn              = rootView.findViewById(R.id.btn_cancel);
        mNextBtn                = rootView.findViewById(R.id.btn_next);

        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mTvDenomTitle);

        mCancelBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        mNextBtn.setClickable(false);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        if (getSActivity().mBaseChain.equals(COSMOS_MAIN) || getSActivity().mBaseChain.equals(KAVA_MAIN) || getSActivity().mBaseChain.equals(KAVA_TEST) ||
                getSActivity().mBaseChain.equals(BAND_MAIN) || getSActivity().mBaseChain.equals(IOV_MAIN) || getSActivity().mBaseChain.equals(IOV_TEST) ||
                getSActivity().mBaseChain.equals(CERTIK_MAIN) || getSActivity().mBaseChain.equals(CERTIK_TEST) || getSActivity().mBaseChain.equals(AKASH_MAIN) || getSActivity().mBaseChain.equals(SECRET_MAIN)) {
            BigDecimal rewardSum = BigDecimal.ZERO;
            for (Reward reward:getSActivity().mRewards) {
                rewardSum = rewardSum.add(new BigDecimal(reward.amount.get(0).amount).setScale(0, BigDecimal.ROUND_DOWN));
            }
            mTvRewardAmount.setText(WDp.getDpAmount(getContext(), rewardSum, 6, getSActivity().mBaseChain));
            String monikers = "";
            for (Validator validator:getSActivity().mValidators) {
                if(TextUtils.isEmpty(monikers)) {
                    monikers = validator.description.moniker;
                } else {
                    monikers = monikers + ",    " + validator.description.moniker;
                }
            }
            mTvFromValidators.setText(monikers);

        } else if (getSActivity().mBaseChain.equals(IRIS_MAIN)) {
            mTvRewardAmount.setText(WDp.getDpAmount(getContext(), getSActivity().getIrisRewardSum(), 18, getSActivity().mBaseChain));
            String monikers = "";
            for (Validator validator:getSActivity().mValidators) {
                if(TextUtils.isEmpty(monikers)) {
                    monikers = validator.description.moniker;
                } else {
                    monikers = monikers + ",    " + validator.description.moniker;
                }
            }
            mTvFromValidators.setText(monikers);

        } else if (getSActivity().mBaseChain.equals(COSMOS_TEST) || getSActivity().mBaseChain.equals(IRIS_TEST)) {
            BigDecimal rewardSum = BigDecimal.ZERO;
            for (String opAddress: getSActivity().mValAddresses) {
                rewardSum = rewardSum.add(getSActivity().getBaseDao().getReward(WDp.mainDenom(getSActivity().mBaseChain), opAddress));
            }
            mTvRewardAmount.setText(WDp.getDpAmount2(getContext(), rewardSum, 6, 6));
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

        }

        mTvReceiveAddress.setText(getSActivity().mWithdrawAddress);
        if(getSActivity().mWithdrawAddress.equals(getSActivity().mAccount.address)) {
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
