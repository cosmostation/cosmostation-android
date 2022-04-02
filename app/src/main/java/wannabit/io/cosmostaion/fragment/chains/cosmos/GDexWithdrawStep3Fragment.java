package wannabit.io.cosmostaion.fragment.chains.cosmos;

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
import wannabit.io.cosmostaion.activities.chains.cosmos.GravityWithdrawPoolActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class GDexWithdrawStep3Fragment extends BaseFragment implements View.OnClickListener {

    private TextView mFeeAmount;
    private TextView mFeeAmountSymbol;
    private TextView mWithdrawAmount, mWithdrawSymbol;
    private TextView mExitOutput0Amount, mExitOutput0AmountSymbol;
    private TextView mExitOutput1Amount, mExitOutput1AmountSymbol;
    private TextView mMemo;
    private int mDpDecimal = 6;

    private Button mBeforeBtn, mConfirmBtn;

    public static GDexWithdrawStep3Fragment newInstance(Bundle bundle) {
        GDexWithdrawStep3Fragment fragment = new GDexWithdrawStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gdex_withdraw_pool_step3, container, false);
        mFeeAmount = rootView.findViewById(R.id.withdraw_fee_amount);
        mFeeAmountSymbol = rootView.findViewById(R.id.withdraw_fee_amount_symbol);
        mWithdrawAmount = rootView.findViewById(R.id.withdraw_lp_amount);
        mWithdrawSymbol = rootView.findViewById(R.id.withdraw_lp_symbol);
        mExitOutput0Amount = rootView.findViewById(R.id.exit_output0_amount);
        mExitOutput0AmountSymbol = rootView.findViewById(R.id.exit_output0_amount_symbol);
        mExitOutput1Amount = rootView.findViewById(R.id.exit_output1_amount);
        mExitOutput1AmountSymbol = rootView.findViewById(R.id.exit_output1_amount_symbol);

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

        String lpDenom = getSActivity().mLpToken.denom;
        String lpAmount = getSActivity().mLpToken.amount;

        WUtil.dpCosmosTokenName(getSActivity(), getBaseDao(), mWithdrawSymbol, lpDenom);
        mWithdrawAmount.setText(WDp.getDpAmount2(new BigDecimal(lpAmount), mDpDecimal, mDpDecimal));

        String coin0Denom = getSActivity().mGDexPool.getReserveCoinDenoms(0);
        String coin1Denom = getSActivity().mGDexPool.getReserveCoinDenoms(1);
        BigDecimal totalShare = new BigDecimal(getSActivity().mGDexPoolCoinSupply.amount);
        BigDecimal myShare = new BigDecimal(getSActivity().mLpToken.amount);
        BigDecimal depositRate = myShare.divide(totalShare, 18, RoundingMode.DOWN);
        BigDecimal coin0Amount = WUtil.getLpAmount(getBaseDao(), getSActivity().mGDexPool.getReserveAccountAddress(), coin0Denom);
        BigDecimal coin1Amount = WUtil.getLpAmount(getBaseDao(), getSActivity().mGDexPool.getReserveAccountAddress(), coin1Denom);
        BigDecimal expectCoin0Amount = coin0Amount.multiply(depositRate).setScale(0, RoundingMode.DOWN);
        BigDecimal expectCoin1Amount = coin1Amount.multiply(depositRate).setScale(0, RoundingMode.DOWN);

        mFeeAmount.setText(WDp.getDpAmount2(feeAmount, mDpDecimal, mDpDecimal));
        mMemo.setText(getSActivity().mTxMemo);
        WDp.showCoinDp(getSActivity(), getBaseDao(), coin0Denom, expectCoin0Amount.toPlainString(), mExitOutput0AmountSymbol, mExitOutput0Amount, BaseChain.COSMOS_MAIN);
        WDp.showCoinDp(getSActivity(), getBaseDao(), coin1Denom, expectCoin1Amount.toPlainString(), mExitOutput1AmountSymbol, mExitOutput1Amount, BaseChain.COSMOS_MAIN);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartExitPool();
        }
    }

    private GravityWithdrawPoolActivity getSActivity() {
        return (GravityWithdrawPoolActivity) getBaseActivity();
    }
}
