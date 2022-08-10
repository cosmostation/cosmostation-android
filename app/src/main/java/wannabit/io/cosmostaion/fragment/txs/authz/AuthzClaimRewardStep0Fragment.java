package wannabit.io.cosmostaion.fragment.txs.authz;

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

import java.util.ArrayList;

import cosmos.distribution.v1beta1.Distribution;
import cosmos.staking.v1beta1.Staking;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.authz.AuthzClaimRewardActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;

public class AuthzClaimRewardStep0Fragment extends BaseFragment implements View.OnClickListener {

    private TextView mTvRewardAmount, mTvDenomTitle;
    private TextView mTvFromValidators;
    private LinearLayout mReceiveLayer;
    private TextView mTvReceiveAddress;
    private RelativeLayout mProgressBar;
    private Button mCancelBtn, mNextBtn;

    public static AuthzClaimRewardStep0Fragment newInstance() {
        return new AuthzClaimRewardStep0Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_authz_reward_step0, container, false);
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
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        Coin mainReward = getSActivity().mGranterRewardSum;
        WDp.setDpCoin(getActivity(), getBaseDao(), getSActivity().mChainConfig, mainReward, mTvDenomTitle, mTvRewardAmount);

        String monikers = "";
        ArrayList<String> myValiatorList = new ArrayList<>();
        for (Staking.Validator validator: getBaseDao().mGRpcAllValidators) {
            for (Distribution.DelegationDelegatorReward myValidator : getSActivity().mGranterRewards) {
                if (validator.getOperatorAddress().equalsIgnoreCase(myValidator.getValidatorAddress())) {
                    if (!TextUtils.isEmpty(monikers)) {
                        monikers = monikers + ",    " + validator.getDescription().getMoniker();
                    } else {
                        monikers = validator.getDescription().getMoniker();
                    }
                    myValiatorList.add(myValidator.getValidatorAddress());
                }
                getSActivity().mValAddresses = myValiatorList;
            }
        }
        mTvFromValidators.setText(monikers);

        mTvReceiveAddress.setText(getSActivity().mWithdrawAddress);
        if (getSActivity().mGranter.equalsIgnoreCase(getSActivity().mWithdrawAddress)) {
            mReceiveLayer.setVisibility(View.GONE);
        } else {
            mReceiveLayer.setVisibility(View.VISIBLE);
        }
        mProgressBar.setVisibility(View.GONE);
    }


    @Override
    public void onClick(View v) {
        if (v.equals(mCancelBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            getSActivity().onNextStep();
        }
    }

    private AuthzClaimRewardActivity getSActivity() {
        return (AuthzClaimRewardActivity) getBaseActivity();
    }
}
