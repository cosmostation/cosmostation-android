package wannabit.io.cosmostaion.fragment.chains.kava;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;

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
import wannabit.io.cosmostaion.activities.chains.kava.BorrowHardActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.IRefreshTabListener;
import wannabit.io.cosmostaion.dialog.Dialog_Hard_Liquidation_Warning;
import wannabit.io.cosmostaion.utils.WDp;

public class BorrowHardStep3Fragment extends BaseFragment implements View.OnClickListener, IRefreshTabListener {
    public final static int SELECT_HARD_BORROW_CHECK = 9108;

    private Button mBeforeBtn, mConfirmBtn;
    private TextView mBorrowAmount, mBorrowDenom;
    private TextView mFeesAmount, mFeesDenom;
    private TextView mMemo;

    public static BorrowHardStep3Fragment newInstance(Bundle bundle) {
        BorrowHardStep3Fragment fragment = new BorrowHardStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_borrow_hard_step3, container, false);
        mBorrowAmount = rootView.findViewById(R.id.borrow_amount);
        mBorrowDenom = rootView.findViewById(R.id.borrow_amount_denom);
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
        WDp.showCoinDp(getContext(), getBaseDao(), getSActivity().mHardPoolCoins.get(0), mBorrowDenom, mBorrowAmount, getSActivity().mBaseChain);
        WDp.showCoinDp(getContext(), getBaseDao(), TOKEN_KAVA, feeAmount.toPlainString(), mFeesDenom, mFeesAmount, getSActivity().mBaseChain);
        mMemo.setText(getSActivity().mTxMemo);

    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            Dialog_Hard_Liquidation_Warning dialog = Dialog_Hard_Liquidation_Warning.newInstance(null);
            dialog.setTargetFragment(BorrowHardStep3Fragment.this, SELECT_HARD_BORROW_CHECK);
            showDialog(dialog);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_HARD_BORROW_CHECK && resultCode == Activity.RESULT_OK) {
            getSActivity().onStartBorrowHard();
        }
    }

    private BorrowHardActivity getSActivity() {
        return (BorrowHardActivity) getBaseActivity();
    }
}