package wannabit.io.cosmostaion.fragment.txs.authz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.authz.AuthzDelegateActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class AuthzDelegateStep4Fragment extends BaseFragment implements View.OnClickListener {
    private TextView mDelegateAmount, mDelegateDenom;
    private TextView mFeeAmount, mFeeType;
    private TextView mValidatorName, mMemo;

    private Button mBeforeBtn, mConfirmBtn;

    public static AuthzDelegateStep4Fragment newInstance() {
        return new AuthzDelegateStep4Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_authz_delegate_step4, container, false);
        mDelegateAmount = rootView.findViewById(R.id.delegate_amount);
        mDelegateDenom = rootView.findViewById(R.id.delegate_denom);
        mFeeAmount = rootView.findViewById(R.id.delegate_fees);
        mFeeType = rootView.findViewById(R.id.delegate_fees_type);
        mValidatorName = rootView.findViewById(R.id.to_delegate_moniker);
        mMemo = rootView.findViewById(R.id.memo);
        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mConfirmBtn = rootView.findViewById(R.id.btn_confirm);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mChainConfig.mainDenom(), getSActivity().mAmount.amount, mDelegateDenom, mDelegateAmount);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), mFeeType, mFeeAmount);

        mValidatorName.setText(getSActivity().getBaseDao().getValidatorInfo(getSActivity().mValAddress).getDescription().getMoniker());
        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onAuthzDelegate();
        }
    }

    private AuthzDelegateActivity getSActivity() {
        return (AuthzDelegateActivity) getBaseActivity();
    }
}
