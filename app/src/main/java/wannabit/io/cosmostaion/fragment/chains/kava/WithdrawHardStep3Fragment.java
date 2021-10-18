package wannabit.io.cosmostaion.fragment.chains.kava;

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
import wannabit.io.cosmostaion.activities.chains.kava.WithdrawHardActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_Hard_Liquidation_Warning;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;

public class WithdrawHardStep3Fragment extends BaseFragment implements View.OnClickListener {
    public final static int SELECT_HARD_WITHDRAW_CHECK = 9107;

    private Button mBeforeBtn, mConfirmBtn;
    private TextView mWithdrawAmount, mWithdrawDenom;
    private TextView mFeesAmount, mFeesDenom;
    private TextView mMemo;

    public static WithdrawHardStep3Fragment newInstance(Bundle bundle) {
        WithdrawHardStep3Fragment fragment = new WithdrawHardStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_withdraw_hard_step3, container, false);
        mWithdrawAmount = rootView.findViewById(R.id.withdraw_amount);
        mWithdrawDenom = rootView.findViewById(R.id.withdraw_amount_denom);
        mFeesAmount = rootView.findViewById(R.id.fees_amount);
        mFeesDenom = rootView.findViewById(R.id.fees_denom);
        mMemo = rootView.findViewById(R.id.memo);
        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mConfirmBtn = rootView.findViewById(R.id.btn_confirm);
        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);
        WDp.showCoinDp(getContext(), getSActivity().mHardPoolCoins.get(0), mWithdrawDenom, mWithdrawAmount, getSActivity().mBaseChain);
        WDp.showCoinDp(getContext(), TOKEN_KAVA, feeAmount.toPlainString(), mFeesDenom, mFeesAmount, getSActivity().mBaseChain);
        mMemo.setText(getSActivity().mTxMemo);

    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            Dialog_Hard_Liquidation_Warning dialog = Dialog_Hard_Liquidation_Warning.newInstance(null);
            dialog.setTargetFragment(WithdrawHardStep3Fragment.this, SELECT_HARD_WITHDRAW_CHECK);
            getFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_HARD_WITHDRAW_CHECK && resultCode == Activity.RESULT_OK) {
            getSActivity().onStartWithdrawHarvest();
        }
    }

    private WithdrawHardActivity getSActivity() {
        return (WithdrawHardActivity)getBaseActivity();
    }
}