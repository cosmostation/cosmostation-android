package wannabit.io.cosmostaion.fragment.txs.kava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.kava.WithDrawPoolActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class WithdrawPoolStep3Fragment extends BaseFragment implements View.OnClickListener{

    private TextView        mFeeAmount;
    private TextView        mFeeAmountSymbol;
    private TextView        mExitMyShare;
    private TextView        mExitOutput0Amount, mExitOutput0AmountSymbol;
    private TextView        mExitOutput1Amount, mExitOutput1AmountSymbol;
    private TextView        mMemo;

    private Button          mBeforeBtn, mConfirmBtn;

    public static WithdrawPoolStep3Fragment newInstance(Bundle bundle) {
        WithdrawPoolStep3Fragment fragment = new WithdrawPoolStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_withdraw_pool_step3, container, false);
        mFeeAmount                  = rootView.findViewById(R.id.exit_fee_amount);
        mFeeAmountSymbol            = rootView.findViewById(R.id.exit_fee_amount_symbol);
        mExitMyShare                = rootView.findViewById(R.id.tx_kava_my_share);
        mExitOutput0Amount          = rootView.findViewById(R.id.tx_kava_withdraw_amount0);
        mExitOutput0AmountSymbol    = rootView.findViewById(R.id.tx_kava_withdraw_symbol0);
        mExitOutput1Amount          = rootView.findViewById(R.id.tx_kava_withdraw_amount1);
        mExitOutput1AmountSymbol    = rootView.findViewById(R.id.tx_kava_withdraw_symbol1);
        mMemo                       = rootView.findViewById(R.id.memo);
        mBeforeBtn                  = rootView.findViewById(R.id.btn_before);
        mConfirmBtn                 = rootView.findViewById(R.id.btn_confirm);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        WDp.dpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), mFeeAmountSymbol, mFeeAmount);
        mExitMyShare.setText(WDp.getDpAmount2(getSActivity(), getSActivity().mKavaShareAmount, 6, 6));

        WDp.dpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mKavaPoolTokenA, mExitOutput0AmountSymbol, mExitOutput0Amount);
        WDp.dpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mKavaPoolTokenB, mExitOutput1AmountSymbol, mExitOutput1Amount);

        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartExitPool();
        }
    }

    private WithDrawPoolActivity getSActivity() {
        return (WithDrawPoolActivity)getBaseActivity();
    }
}
