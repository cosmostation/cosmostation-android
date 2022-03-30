package wannabit.io.cosmostaion.fragment.chains.osmosis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

import osmosis.lockup.Lock;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.osmosis.StartUnlockActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class StartUnLockStep3Fragment extends BaseFragment implements View.OnClickListener {
    private TextView mFeeAmount;
    private TextView mFeeAmountSymbol;
    private TextView mUnLockIds;
    private TextView mUnLockAmount, mUnLockSymbol;
    private TextView mMemo;
    private int mDpDecimal = 18;

    private Button mBeforeBtn, mConfirmBtn;

    public static StartUnLockStep3Fragment newInstance(Bundle bundle) {
        StartUnLockStep3Fragment fragment = new StartUnLockStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_start_unlock_step3, container, false);
        mFeeAmount = rootView.findViewById(R.id.unlock_fee_amount);
        mFeeAmountSymbol = rootView.findViewById(R.id.unlock_fee_symbol);
        mUnLockIds = rootView.findViewById(R.id.unlock_ids);
        mUnLockAmount = rootView.findViewById(R.id.unlock_amount);
        mUnLockSymbol = rootView.findViewById(R.id.unlock_amount_symbol);
        mMemo = rootView.findViewById(R.id.memo);

        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mConfirmBtn = rootView.findViewById(R.id.btn_confirm);

        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mFeeAmountSymbol);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mDpDecimal = WDp.mainDivideDecimal(getSActivity().mBaseChain);
        BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);
        String toUnbondingDenom = getSActivity().mOsmosisLockups.get(0).getCoins(0).getDenom();
        String ids = "";
        BigDecimal toUnbondingAmount = BigDecimal.ZERO;
        for (Lock.PeriodLock lockup : getSActivity().mOsmosisLockups) {
            ids = ids + "# " + lockup.getID() + "  ";
            toUnbondingAmount = toUnbondingAmount.add(new BigDecimal(lockup.getCoins(0).getAmount()));
        }

        mFeeAmount.setText(WDp.getDpAmount2(getContext(), feeAmount, mDpDecimal, mDpDecimal));
        mUnLockIds.setText(ids);
        WDp.showCoinDp(getSActivity(), getBaseDao(), toUnbondingDenom, toUnbondingAmount.toPlainString(), mUnLockSymbol, mUnLockAmount, BaseChain.OSMOSIS_MAIN);
        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartUnLock();
        }
    }

    private StartUnlockActivity getSActivity() {
        return (StartUnlockActivity) getBaseActivity();
    }
}
