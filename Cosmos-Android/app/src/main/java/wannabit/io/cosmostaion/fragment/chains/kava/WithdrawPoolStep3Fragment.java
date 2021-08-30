package wannabit.io.cosmostaion.fragment.chains.kava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.math.RoundingMode;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.WithDrawPoolActivity;
import wannabit.io.cosmostaion.activities.chains.osmosis.ExitPoolActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.fragment.chains.osmosis.ExitPoolStep3Fragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class WithdrawPoolStep3Fragment extends BaseFragment implements View.OnClickListener{

    private TextView mFeeAmount;
    private TextView        mFeeAmountSymbol;
    private TextView        mExitMyShare;
    private TextView        mExitOutput0Amount, mExitOutput0AmountSymbol;
    private TextView        mExitOutput1Amount, mExitOutput1AmountSymbol;
    private TextView        mMemo;
    private int             mDpDecimal = 6;
    private Coin            mCoin0, mCoin1;

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
        mExitMyShare               = rootView.findViewById(R.id.tx_kava_my_share);
        mExitOutput0Amount          = rootView.findViewById(R.id.tx_kava_withdraw_amount0);
        mExitOutput0AmountSymbol    = rootView.findViewById(R.id.tx_kava_withdraw_symbol0);
        mExitOutput1Amount          = rootView.findViewById(R.id.tx_kava_withdraw_amount1);
        mExitOutput1AmountSymbol    = rootView.findViewById(R.id.tx_kava_withdraw_symbol1);
        mMemo                       = rootView.findViewById(R.id.memo);
        mBeforeBtn                  = rootView.findViewById(R.id.btn_before);
        mConfirmBtn                 = rootView.findViewById(R.id.btn_confirm);

        WDp.DpMainDenom(getContext(), getSActivity().mAccount.baseChain, mFeeAmountSymbol);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mDpDecimal = WDp.mainDivideDecimal(getSActivity().mBaseChain);
        mExitMyShare.setText(WDp.getDpAmount2(getSActivity(), getSActivity().mKavaShareAmount, 6, 6));
        BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);
        WLog.w("mKavaSwapDeposit : " + getSActivity().mKavaSwapDeposit.shares_owned);
        BigDecimal depositRate = getSActivity().mKavaShareAmount.divide(new BigDecimal(getSActivity().mKavaSwapDeposit.shares_owned).setScale(18, RoundingMode.DOWN));
        BigDecimal padding = new BigDecimal("0.97");
        BigDecimal coin0Amount = new BigDecimal(getSActivity().mKavaSwapDeposit.shares_value.get(0).amount).multiply(padding).multiply(depositRate).setScale(0, RoundingMode.DOWN);
        BigDecimal coin1Amount = new BigDecimal(getSActivity().mKavaSwapDeposit.shares_value.get(1).amount).multiply(padding).multiply(depositRate).setScale(0, RoundingMode.DOWN);

        Coin coin0 = new Coin(getSActivity().mKavaSwapDeposit.shares_value.get(0).denom, coin0Amount.toPlainString());
        Coin coin1 = new Coin(getSActivity().mKavaSwapDeposit.shares_value.get(1).denom, coin1Amount.toPlainString());

        WDp.showCoinDp(getSActivity(), coin0, mExitOutput0AmountSymbol, mExitOutput0Amount, BaseChain.KAVA_MAIN);
        WDp.showCoinDp(getSActivity(), coin1, mExitOutput1AmountSymbol, mExitOutput1Amount, BaseChain.KAVA_MAIN);

        mFeeAmount.setText(WDp.getDpAmount2(getContext(), feeAmount, mDpDecimal, mDpDecimal));
        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartExitPool(mCoin0, mCoin1);
        }
    }

    private WithDrawPoolActivity getSActivity() {
        return (WithDrawPoolActivity)getBaseActivity();
    }
}
