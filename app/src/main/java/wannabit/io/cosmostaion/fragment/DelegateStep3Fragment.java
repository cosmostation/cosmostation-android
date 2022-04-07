package wannabit.io.cosmostaion.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.DelegateActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.IRefreshTabListener;
import wannabit.io.cosmostaion.dialog.Dialog_Delegate_Warning;
import wannabit.io.cosmostaion.utils.WDp;

public class DelegateStep3Fragment extends BaseFragment implements View.OnClickListener, IRefreshTabListener {
    public final static int SELECT_DELEGATE_CHECK = 9106;

    private TextView mDelegateAmount;
    private TextView mFeeAmount;
    private TextView mValidatorName, mMemo;
    private Button mBeforeBtn, mConfirmBtn;
    private TextView mDenomDelegateAmount, mDenomFeeType;
    private int mDpDecimal = 6;


    public static DelegateStep3Fragment newInstance(Bundle bundle) {
        DelegateStep3Fragment fragment = new DelegateStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_delegate_step3, container, false);
        mDelegateAmount = rootView.findViewById(R.id.delegate_atom);
        mDenomDelegateAmount = rootView.findViewById(R.id.delegate_amount_title);
        mFeeAmount = rootView.findViewById(R.id.delegate_fees);
        mDenomFeeType = rootView.findViewById(R.id.delegate_fees_type);
        mValidatorName = rootView.findViewById(R.id.to_delegate_moniker);
        mMemo = rootView.findViewById(R.id.memo);
        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mConfirmBtn = rootView.findViewById(R.id.confirmButton);

        WDp.DpMainDenom(getSActivity().account.baseChain, mDenomDelegateAmount);
        WDp.DpMainDenom(getSActivity().account.baseChain, mDenomFeeType);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mDpDecimal = WDp.mainDivideDecimal(getSActivity().baseChain);
        BigDecimal toDeleagteAmount = new BigDecimal(getSActivity().mAmount.amount);
        BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);
        mDelegateAmount.setText(WDp.getDpAmount2(toDeleagteAmount, mDpDecimal, mDpDecimal));
        mFeeAmount.setText(WDp.getDpAmount2(feeAmount, mDpDecimal, mDpDecimal));
        mValidatorName.setText(getSActivity().getBaseDao().getValidatorInfo(getSActivity().mValAddress).getDescription().getMoniker());
        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            Bundle bundle = new Bundle();
            bundle.putInt("day", getBaseDao().mChainParam.getUnbonding(getSActivity().baseChain));
            Dialog_Delegate_Warning dialog = Dialog_Delegate_Warning.newInstance(bundle);
            dialog.setTargetFragment(DelegateStep3Fragment.this, SELECT_DELEGATE_CHECK);
            showDialog(dialog);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_DELEGATE_CHECK && resultCode == Activity.RESULT_OK) {
            getSActivity().onStartDelegate();
        }
    }

    private DelegateActivity getSActivity() {
        return (DelegateActivity) getBaseActivity();
    }


}
