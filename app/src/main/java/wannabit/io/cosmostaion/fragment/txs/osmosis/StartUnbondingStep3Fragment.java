package wannabit.io.cosmostaion.fragment.txs.osmosis;

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
import wannabit.io.cosmostaion.activities.txs.osmosis.StartUnbondingActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class StartUnbondingStep3Fragment extends BaseFragment implements View.OnClickListener{
    private TextView        mFeeAmount;
    private TextView        mFeeAmountSymbol;
    private TextView        mUnbondingIds;
    private TextView        mUnbondingAmount, mUnbondingSymbol;
    private TextView        mUnbondingDuraion;
    private TextView        mMemo;

    private Button          mBeforeBtn, mConfirmBtn;

    public static StartUnbondingStep3Fragment newInstance() {
        return new StartUnbondingStep3Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_start_unbonding_step3, container, false);
        mFeeAmount                  = rootView.findViewById(R.id.unbonding_fee_amount);
        mFeeAmountSymbol            = rootView.findViewById(R.id.unbonding_fee_symbol);
        mUnbondingIds               = rootView.findViewById(R.id.unbonding_ids);
        mUnbondingAmount            = rootView.findViewById(R.id.unbonding_amount);
        mUnbondingSymbol            = rootView.findViewById(R.id.unbonding_amount_symbol);
        mUnbondingDuraion           = rootView.findViewById(R.id.unbonding_duration);
        mMemo                       = rootView.findViewById(R.id.memo);

        mBeforeBtn                  = rootView.findViewById(R.id.btn_before);
        mConfirmBtn                 = rootView.findViewById(R.id.btn_confirm);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onRefreshTab() {
        long toUnbondingDuration = getSActivity().mOsmosisLockups.get(0).getDuration().getSeconds();
        String toUnbondingDenom = getSActivity().mOsmosisLockups.get(0).getCoins(0).getDenom();
        String ids = "";
        BigDecimal toUnbondingAmount = BigDecimal.ZERO;
        for (Lock.PeriodLock lockup: getSActivity().mOsmosisLockups) {
            ids = ids + "# " + lockup.getID() + "  ";
            toUnbondingAmount = toUnbondingAmount.add(new BigDecimal(lockup.getCoins(0).getAmount()));
        }

        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), mFeeAmountSymbol, mFeeAmount);
        mUnbondingIds.setText(ids);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, toUnbondingDenom, toUnbondingAmount.toPlainString(), mUnbondingSymbol, mUnbondingAmount);
        if (toUnbondingDuration == 86400) {
            mUnbondingDuraion.setText("1 Day");
        } else if (toUnbondingDuration == 604800) {
            mUnbondingDuraion.setText("7 Days");
        } else if (toUnbondingDuration == 1209600) {
            mUnbondingDuraion.setText("14 Days");
        }
        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartUnBonding();
        }
    }

    private StartUnbondingActivity getSActivity() { return (StartUnbondingActivity)getBaseActivity(); }
}
