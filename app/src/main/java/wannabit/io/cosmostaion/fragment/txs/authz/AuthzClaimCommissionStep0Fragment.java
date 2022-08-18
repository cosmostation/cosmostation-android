package wannabit.io.cosmostaion.fragment.txs.authz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import cosmos.staking.v1beta1.Staking;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.authz.AuthzClaimCommissionActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;

public class AuthzClaimCommissionStep0Fragment extends BaseFragment implements View.OnClickListener {

    private TextView mTvCommissionAmount, mTvDenomTitle;
    private TextView mTvFromValidators;
    private LinearLayout mReceiveLayer;
    private TextView mTvReceiveAddress;
    private RelativeLayout mProgressBar;
    private Button mCancelBtn, mNextBtn;

    public static AuthzClaimCommissionStep0Fragment newInstance() {
        return new AuthzClaimCommissionStep0Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_authz_commission_step0, container, false);
        mTvCommissionAmount     = rootView.findViewById(R.id.commission_amount);
        mTvDenomTitle           = rootView.findViewById(R.id.commission_denom);
        mTvFromValidators       = rootView.findViewById(R.id.commission_moniker);
        mReceiveLayer           = rootView.findViewById(R.id.commission_receive_address_layer);
        mTvReceiveAddress       = rootView.findViewById(R.id.commission_receive_address);
        mProgressBar            = rootView.findViewById(R.id.commission_progress);
        mCancelBtn              = rootView.findViewById(R.id.btn_cancel);
        mNextBtn                = rootView.findViewById(R.id.btn_next);

        mCancelBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        Coin mainCommission = getSActivity().mGranterCommission;
        WDp.setDpCoin(getActivity(), getBaseDao(), getSActivity().mChainConfig, mainCommission, mTvDenomTitle, mTvCommissionAmount);

        String opAddress = WKey.convertDpOpAddressToDpAddress(getSActivity().mGranter, getSActivity().mChainConfig);
        Staking.Validator validatorInfo = getBaseDao().mGRpcAllValidators.stream().filter(item -> item.getOperatorAddress().equalsIgnoreCase(opAddress)).findFirst().get();
        mTvFromValidators.setText(validatorInfo.getDescription().getMoniker());

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

    private AuthzClaimCommissionActivity getSActivity() {
        return (AuthzClaimCommissionActivity) getBaseActivity();
    }
}
