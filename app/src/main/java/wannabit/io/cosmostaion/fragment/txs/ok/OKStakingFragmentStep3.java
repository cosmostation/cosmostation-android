package wannabit.io.cosmostaion.fragment.txs.ok;

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
import wannabit.io.cosmostaion.activities.txs.ok.OKStakingActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.CommonAlertDialog;
import wannabit.io.cosmostaion.utils.WDp;

public class OKStakingFragmentStep3 extends BaseFragment implements View.OnClickListener {
    public final static int SELECT_DEPOSIT_CHECK = 9106;

    private TextView mDepositAmount;
    private TextView mFeeAmount;
    private TextView mMemo;
    private Button mBeforeBtn, mConfirmBtn;
    private TextView mDepositDenom, mFeeDenom;

    public static OKStakingFragmentStep3 newInstance() {
        return new OKStakingFragmentStep3();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stake_deposit_3, container, false);
        mDepositAmount = rootView.findViewById(R.id.deposit_amount);
        mDepositDenom = rootView.findViewById(R.id.deposit_amount_denom);
        mFeeAmount = rootView.findViewById(R.id.fees_amount);
        mFeeDenom = rootView.findViewById(R.id.fees_denom);
        mMemo = rootView.findViewById(R.id.memo);
        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mConfirmBtn = rootView.findViewById(R.id.btn_confirm);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onRefreshTab() {
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), mFeeDenom, mFeeAmount);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mToDepositCoin, mDepositDenom, mDepositAmount);

        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            CommonAlertDialog.showHeaderImageDoubleButton(getSActivity(), getString(R.string.str_deposit_warn_title), getString(R.string.str_delegate_warn_msg),
                    getString(R.string.str_cancel), null,
                    getString(R.string.str_confirm), View -> {
                        Intent resultIntent = new Intent();
                        onActivityResult(SELECT_DEPOSIT_CHECK, Activity.RESULT_OK, resultIntent);
                    }, R.drawable.img_delegate_14_warning);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_DEPOSIT_CHECK && resultCode == Activity.RESULT_OK) {
            getSActivity().onStartDeposit();
        }
    }

    private OKStakingActivity getSActivity() {
        return (OKStakingActivity) getBaseActivity();
    }
}
