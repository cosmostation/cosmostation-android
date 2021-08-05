package wannabit.io.cosmostaion.fragment.chains.osmosis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.osmosis.JoinPoolActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class JoinPoolStep3Fragment extends BaseFragment implements View.OnClickListener{

    private TextView        mDelegateAmount;
    private TextView        mFeeAmount;
    private TextView        mValidatorName, mMemo;
    private Button          mBeforeBtn, mConfirmBtn;
    private TextView        mDenomDelegateAmount, mDenomFeeType;
    private int             mDpDecimal = 6;

    public static JoinPoolStep3Fragment newInstance(Bundle bundle) {
        JoinPoolStep3Fragment fragment = new JoinPoolStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_join_pool_step3, container, false);
        mDelegateAmount         = rootView.findViewById(R.id.delegate_atom);
        mDenomDelegateAmount    = rootView.findViewById(R.id.delegate_amount_title);
        mFeeAmount              = rootView.findViewById(R.id.delegate_fees);
        mDenomFeeType           = rootView.findViewById(R.id.delegate_fees_type);
        mValidatorName          = rootView.findViewById(R.id.to_delegate_moniker);
        mMemo                   = rootView.findViewById(R.id.memo);
        mBeforeBtn              = rootView.findViewById(R.id.btn_before);
        mConfirmBtn             = rootView.findViewById(R.id.btn_confirm);

        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mDenomDelegateAmount);
        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mDenomFeeType);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    private JoinPoolActivity getSActivity() {
        return (JoinPoolActivity)getBaseActivity();
    }

    @Override
    public void onClick(View v) {

    }
}
