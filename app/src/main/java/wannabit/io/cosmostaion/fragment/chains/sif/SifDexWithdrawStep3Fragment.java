package wannabit.io.cosmostaion.fragment.chains.sif;

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
import wannabit.io.cosmostaion.activities.chains.sif.SifWithdrawPoolActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.IRefreshTabListener;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class SifDexWithdrawStep3Fragment extends BaseFragment implements View.OnClickListener, IRefreshTabListener {

    private TextView mFeeAmount;
    private TextView mFeeAmountSymbol;
    private TextView mExitInAmount;
    private TextView mExitOutput0Amount, mExitOutput0AmountSymbol;
    private TextView mExitOutput1Amount, mExitOutput1AmountSymbol;
    private TextView mMemo;
    private int mDpDecimal = 18;

    private Button mBeforeBtn, mConfirmBtn;

    public static SifDexWithdrawStep3Fragment newInstance(Bundle bundle) {
        SifDexWithdrawStep3Fragment fragment = new SifDexWithdrawStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sif_withdraw_pool_step3, container, false);
        mFeeAmount = rootView.findViewById(R.id.exit_fee_amount);
        mFeeAmountSymbol = rootView.findViewById(R.id.exit_fee_amount_symbol);
        mExitInAmount = rootView.findViewById(R.id.exit_in_amount);
        mExitOutput0Amount = rootView.findViewById(R.id.exit_output0_amount);
        mExitOutput0AmountSymbol = rootView.findViewById(R.id.exit_output0_amount_symbol);
        mExitOutput1Amount = rootView.findViewById(R.id.exit_output1_amount);
        mExitOutput1AmountSymbol = rootView.findViewById(R.id.exit_output1_amount_symbol);
        mMemo = rootView.findViewById(R.id.memo);
        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mConfirmBtn = rootView.findViewById(R.id.confirmButton);

        WDp.DpMainDenom(getSActivity().account.baseChain, mFeeAmountSymbol);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mDpDecimal = WDp.mainDivideDecimal(getSActivity().baseChain);
        BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);

        BigDecimal lpRoWanAmount = WUtil.getNativeAmount(getSActivity().mSifPool);
        BigDecimal lpExternalAmount = WUtil.getExternalAmount(getSActivity().mSifPool);
        BigDecimal lpUnitAmount = WUtil.getUnitAmount(getSActivity().mSifPool);
        BigDecimal myShareAmount = new BigDecimal(getSActivity().mSifWithdrawCoin.amount);
        BigDecimal rowanWithAmount = lpRoWanAmount.multiply(myShareAmount).divide(lpUnitAmount, 0, RoundingMode.DOWN);
        BigDecimal externalWithDrawAmount = lpExternalAmount.multiply(myShareAmount).divide(lpUnitAmount, 0, RoundingMode.DOWN);

        mExitInAmount.setText(WDp.getDpAmount2(lpUnitAmount, mDpDecimal, mDpDecimal));
        mFeeAmount.setText(WDp.getDpAmount2(feeAmount, mDpDecimal, mDpDecimal));
        WDp.showCoinDp(getSActivity(), getBaseDao(), BaseConstant.TOKEN_SIF, rowanWithAmount.toPlainString(), mExitOutput0AmountSymbol, mExitOutput0Amount, BaseChain.SIF_MAIN);
        WDp.showCoinDp(getSActivity(), getBaseDao(), getSActivity().mSifPool.getExternalAsset().getSymbol(), externalWithDrawAmount.toPlainString(), mExitOutput1AmountSymbol, mExitOutput1Amount, BaseChain.SIF_MAIN);
        mMemo.setText(getSActivity().mTxMemo);

    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartExitPool();
        }
    }

    private SifWithdrawPoolActivity getSActivity() {
        return (SifWithdrawPoolActivity) getBaseActivity();
    }
}
