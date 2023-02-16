package wannabit.io.cosmostaion.fragment.txs.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.common.DelegateActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.databinding.FragmentDelegateStep3Binding;
import wannabit.io.cosmostaion.dialog.CommonAlertDialog;
import wannabit.io.cosmostaion.utils.WDp;

public class DelegateStep3Fragment extends BaseFragment implements View.OnClickListener {

    private FragmentDelegateStep3Binding fragmentDelegateStep3Binding;

    public static DelegateStep3Fragment newInstance() {
        return new DelegateStep3Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentDelegateStep3Binding = FragmentDelegateStep3Binding.inflate(getLayoutInflater());
        fragmentDelegateStep3Binding.btnBefore.setOnClickListener(this);
        fragmentDelegateStep3Binding.btnConfirm.setOnClickListener(this);
        return fragmentDelegateStep3Binding.getRoot();
    }

    @Override
    public void onRefreshTab() {
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mChainConfig.mainDenom(), getSActivity().mAmount.amount, fragmentDelegateStep3Binding.delegateDenom, fragmentDelegateStep3Binding.delegateAmount);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), fragmentDelegateStep3Binding.delegateFeesType, fragmentDelegateStep3Binding.delegateFees);

        fragmentDelegateStep3Binding.toDelegateMoniker.setText(getSActivity().getBaseDao().getValidatorInfo(getSActivity().mValAddress).getDescription().getMoniker());
        fragmentDelegateStep3Binding.memo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentDelegateStep3Binding = null;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(fragmentDelegateStep3Binding.btnBefore)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(fragmentDelegateStep3Binding.btnConfirm)) {
            int unBondingTimeImage = 0;
            if (getBaseDao().mParam != null) {
                int dpDay = getBaseDao().mParam.getUnbonding(getSActivity().mBaseChain);
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
                    getString(R.string.str_cancel), null,
                    getString(R.string.str_confirm), View -> getSActivity().onStartDelegate(), unBondingTimeImage);
        }
    }

    private DelegateActivity getSActivity() {
        return (DelegateActivity) getBaseActivity();
    }

}
