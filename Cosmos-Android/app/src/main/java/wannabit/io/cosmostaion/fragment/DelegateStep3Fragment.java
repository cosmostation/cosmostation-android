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
import wannabit.io.cosmostaion.dialog.Dialog_Delegate_Warning;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OSMOSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;

public class DelegateStep3Fragment extends BaseFragment implements View.OnClickListener {
    public final static int SELECT_DELEGATE_CHECK = 9106;

    private TextView        mDelegateAmount;
    private TextView        mFeeAmount;
    private TextView        mValidatorName, mMemo;
    private Button          mBeforeBtn, mConfirmBtn;
    private TextView        mDenomDelegateAmount, mDenomFeeType;
    private int             mDpDecimal = 6;


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

    @Override
    public void onRefreshTab() {
        mDpDecimal = WDp.mainDivideDecimal(getSActivity().mBaseChain);
        BigDecimal toDeleagteAmount = new BigDecimal(getSActivity().mAmount.amount);
        BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);
        if (isGRPC(getSActivity().mBaseChain)) {
            mDelegateAmount.setText(WDp.getDpAmount2(getContext(), toDeleagteAmount, mDpDecimal, mDpDecimal));
            mFeeAmount.setText(WDp.getDpAmount2(getContext(), feeAmount, mDpDecimal, mDpDecimal));
            mValidatorName.setText(getSActivity().getBaseDao().getValidatorInfo(getSActivity().mValAddress).getDescription().getMoniker());
            mMemo.setText(getSActivity().mTxMemo);

        } else {
            mDelegateAmount.setText(WDp.getDpAmount2(getContext(), toDeleagteAmount, mDpDecimal, mDpDecimal));
            mFeeAmount.setText(WDp.getDpAmount2(getContext(), feeAmount, mDpDecimal, mDpDecimal));
            mValidatorName.setText(getSActivity().mValidator.description.moniker);
            mMemo.setText(getSActivity().mTxMemo);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            Bundle bundle = new Bundle();
            if (getSActivity().mBaseChain.equals(SENTINEL_MAIN) || getSActivity().mBaseChain.equals(CRYPTO_MAIN)) {
                bundle.putInt("day", 28);
            } else if (getSActivity().mBaseChain.equals(OSMOSIS_MAIN)) {
                bundle.putInt("day", 14);
            } else {
                bundle.putInt("day", 21);
            }
            Dialog_Delegate_Warning dialog = Dialog_Delegate_Warning.newInstance(bundle);
            dialog.setTargetFragment(DelegateStep3Fragment.this, SELECT_DELEGATE_CHECK);
            getFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_DELEGATE_CHECK && resultCode == Activity.RESULT_OK) {
            getSActivity().onStartDelegate();
        }
    }

    private DelegateActivity getSActivity() {
        return (DelegateActivity)getBaseActivity();
    }


}
