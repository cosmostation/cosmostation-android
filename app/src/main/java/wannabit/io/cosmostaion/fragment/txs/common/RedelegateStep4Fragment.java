package wannabit.io.cosmostaion.fragment.txs.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.common.RedelegateActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.databinding.FragmentRedelegateStep4Binding;
import wannabit.io.cosmostaion.dialog.CommonAlertDialog;
import wannabit.io.cosmostaion.utils.WDp;

public class RedelegateStep4Fragment extends BaseFragment implements View.OnClickListener {

    private FragmentRedelegateStep4Binding fragmentRedelegateStep4Binding;

    public static RedelegateStep4Fragment newInstance() {
        return new RedelegateStep4Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentRedelegateStep4Binding = FragmentRedelegateStep4Binding.inflate(getLayoutInflater());
        fragmentRedelegateStep4Binding.btnBefore.setOnClickListener(this);
        fragmentRedelegateStep4Binding.btnConfirm.setOnClickListener(this);
        return fragmentRedelegateStep4Binding.getRoot();
    }

    @Override
    public void onRefreshTab() {
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mChainConfig.mainDenom(), getSActivity().mAmount.amount, fragmentRedelegateStep4Binding.redelegateDenom, fragmentRedelegateStep4Binding.redelegateAmount);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), fragmentRedelegateStep4Binding.redelegateFeesType, fragmentRedelegateStep4Binding.redelegateFees);

        fragmentRedelegateStep4Binding.redelegateFromMoniker.setText(getSActivity().getBaseDao().getValidatorInfo(getSActivity().mValAddress).getDescription().getMoniker());
        fragmentRedelegateStep4Binding.redelegateToMoniker.setText(getSActivity().getBaseDao().getValidatorInfo(getSActivity().mToValAddress).getDescription().getMoniker());
        fragmentRedelegateStep4Binding.memo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentRedelegateStep4Binding = null;
    }

    private RedelegateActivity getSActivity() {
        return (RedelegateActivity) getBaseActivity();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(fragmentRedelegateStep4Binding.btnBefore)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(fragmentRedelegateStep4Binding.btnConfirm)) {
            CommonAlertDialog.showDoubleButton(getSActivity(), getString(R.string.str_redelegation_warnning_title), getString(R.string.str_redelegation_warnning_msg),
                    getString(R.string.str_cancel), null,
                    getString(R.string.str_confirm), view -> getSActivity().onStartRedelegate());
        }
    }
}
