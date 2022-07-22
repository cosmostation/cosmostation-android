package wannabit.io.cosmostaion.fragment.txs.osmosis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.osmosis.StartEarningActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;

public class StartLockStep3Fragment extends BaseFragment implements View.OnClickListener{

    private TextView        mFeeAmount;
    private TextView        mFeeAmountSymbol;
    private TextView        mLockCoinAmount, mLockCoinSymbol;
    private TextView        mLockUnbondingDuraion;
    private TextView        mMemo;

    private Button          mBeforeBtn, mConfirmBtn;

    public static StartLockStep3Fragment newInstance(Bundle bundle) {
        StartLockStep3Fragment fragment = new StartLockStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_start_lock_step3, container, false);
        mFeeAmount                  = rootView.findViewById(R.id.lock_fee_amount);
        mFeeAmountSymbol            = rootView.findViewById(R.id.lock_fee_amount_symbol);
        mLockCoinAmount             = rootView.findViewById(R.id.lock_amount);
        mLockCoinSymbol             = rootView.findViewById(R.id.lock_amount_symbol);
        mLockUnbondingDuraion       = rootView.findViewById(R.id.lock_unbonding_duration);
        mMemo                       = rootView.findViewById(R.id.memo);

        mBeforeBtn                  = rootView.findViewById(R.id.btn_before);
        mConfirmBtn                 = rootView.findViewById(R.id.btn_confirm);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onRefreshTab() {
        final Coin LpCoin = new Coin(getSActivity().mLpToken.denom, getSActivity().mLpToken.amount);
        long UnbondingDuraion = getSActivity().mOsmosisLockupDuration;

        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), mFeeAmountSymbol, mFeeAmount);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, LpCoin, mLockCoinSymbol, mLockCoinAmount);
        if (UnbondingDuraion == 86400) {
            mLockUnbondingDuraion.setText("1 Day");
        } else if (UnbondingDuraion == 604800) {
            mLockUnbondingDuraion.setText("7 Days");
        } else if (UnbondingDuraion == 1209600) {
            mLockUnbondingDuraion.setText("14 Days");
        }
        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartEarning();
        }
    }

    private StartEarningActivity getSActivity() {
        return (StartEarningActivity)getBaseActivity();
    }
}
