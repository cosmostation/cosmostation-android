package wannabit.io.cosmostaion.fragment.txs.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.common.DelegateActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.CommonAlertDialog;
import wannabit.io.cosmostaion.utils.WDp;

public class DelegateStep3Fragment extends BaseFragment implements View.OnClickListener {
    public final static int SELECT_DELEGATE_CHECK = 9106;

    private TextView mDelegateAmount, mDelegateDenom;
    private TextView mFeeAmount, mFeeType;
    private TextView mValidatorName, mMemo;
    private Button mBeforeBtn, mConfirmBtn;

    public static DelegateStep3Fragment newInstance() {
        return new DelegateStep3Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_delegate_step3, container, false);
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
            int unBondingTimeImage = 0;
            if (getBaseDao().mChainParam != null) {
                int dpDay = getBaseDao().mChainParam.getUnbonding(getSActivity().mBaseChain);
                if (dpDay == 21) {
                    unBondingTimeImage = R.drawable.img_delegate_warning;
                } else if (dpDay == 3) {
                    unBondingTimeImage = R.drawable.img_delegate_3_warning;
                } else if (dpDay == 14) {
                    unBondingTimeImage = R.drawable.img_delegate_14_warning;
                } else if (dpDay == 28) {
                    unBondingTimeImage = R.drawable.img_delegate_28_warning;
                } else if (dpDay == 7) {
                    unBondingTimeImage = R.drawable.dialogicon_undelegate_7;
                }
            }
            CommonAlertDialog.showHeaderImageDoubleButton(getSActivity(), getString(R.string.str_delegate_warn_title), getString(R.string.str_delegate_warn_msg),
                    CommonAlertDialog.highlightingText(getString(R.string.str_cancel)), null,
                    getString(R.string.str_confirm), View -> {
                        Intent resultIntent = new Intent();
                        onActivityResult(SELECT_DELEGATE_CHECK, Activity.RESULT_OK, resultIntent);
                    }, unBondingTimeImage);
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
