package wannabit.io.cosmostaion.fragment.chains.kava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.DepositPoolActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class DepositPoolStep3Fragment extends BaseFragment implements View.OnClickListener {

    private TextView mFeeAmount;
    private TextView mFeeAmountSymbol;
    private TextView mJoinInput0Amount, mJoinInput0AmountSymbol;
    private TextView mJoinInput1Amount, mJoinInput1AmountSymbol;
    private TextView mJoinSlippage;
    private TextView mMemo;
    private int mDpDecimal = 6;

    private Button mBeforeBtn, mConfirmBtn;

    public static DepositPoolStep3Fragment newInstance(Bundle bundle) {
        DepositPoolStep3Fragment fragment = new DepositPoolStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_deposit_pool_step3, container, false);
        mFeeAmount = rootView.findViewById(R.id.join_fee_amount);
        mFeeAmountSymbol = rootView.findViewById(R.id.join_fee_amount_symbol);
        mJoinInput0Amount = rootView.findViewById(R.id.tx_kava_deposit_amount0);
        mJoinInput0AmountSymbol = rootView.findViewById(R.id.tx_kava_deposit_symbol0);
        mJoinInput1Amount = rootView.findViewById(R.id.tx_kava_deposit_amount1);
        mJoinInput1AmountSymbol = rootView.findViewById(R.id.tx_kava_deposit_symbol1);
        mJoinSlippage = rootView.findViewById(R.id.tx_kava_slippage);
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

        mFeeAmount.setText(WDp.getDpAmount2(feeAmount, mDpDecimal, mDpDecimal));
        WDp.showCoinDp(getSActivity(), getBaseDao(), getSActivity().mKavaPoolTokenA, mJoinInput0AmountSymbol, mJoinInput0Amount, BaseChain.KAVA_MAIN);
        WDp.showCoinDp(getSActivity(), getBaseDao(), getSActivity().mKavaPoolTokenB, mJoinInput1AmountSymbol, mJoinInput1Amount, BaseChain.KAVA_MAIN);
        mJoinSlippage.setText(WDp.getPercentDp(new BigDecimal(3)));
        mMemo.setText(getSActivity().mTxMemo);
    }


    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartJoinPool();
        }
    }

    private DepositPoolActivity getSActivity() {
        return (DepositPoolActivity) getBaseActivity();
    }
}
