package wannabit.io.cosmostaion.fragment.chains.kava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.RepayCdpActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.network.res.ResCdpParam;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;

public class RepayCdpStep3Fragment extends BaseFragment implements View.OnClickListener {

    private TextView mPaymentTitle, mPaymentAmount, mPaymentDenom, mPaymentValue;
    private TextView mFeesAmount, mFeesDenom, mFeeValue;
    private TextView mBeforeRiskTv, mAfterRiskRateTv;
    private TextView mBeforeLiquidationPriceTitle, mAfterLiquidationPriceTitle, mBeforeLiquidationPrice, mAfterLiquidationPrice;
    private TextView mRemainDebtTitle, mRemainDebtAmount, mRemainDebtDenom, mRemainDebtValue;
    private TextView mMemo;
    private LinearLayout mWarnLayer;
    private Button mBeforeBtn, mConfirmBtn;

    public static RepayCdpStep3Fragment newInstance(Bundle bundle) {
        RepayCdpStep3Fragment fragment = new RepayCdpStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_repay_cdp_3, container, false);
        mPaymentTitle = rootView.findViewById(R.id.payment_title);
        mPaymentAmount = rootView.findViewById(R.id.payment_amount);
        mPaymentDenom = rootView.findViewById(R.id.payment_amount_denom);
        mPaymentValue = rootView.findViewById(R.id.payment_value);
        mFeesAmount = rootView.findViewById(R.id.fees_amount);
        mFeesDenom = rootView.findViewById(R.id.fees_denom);
        mFeeValue = rootView.findViewById(R.id.fee_value);
        mBeforeRiskTv = rootView.findViewById(R.id.risk_rate_before);
        mAfterRiskRateTv = rootView.findViewById(R.id.risk_rate_after);
        mBeforeLiquidationPriceTitle = rootView.findViewById(R.id.liquidation_price_before_title);
        mBeforeLiquidationPrice = rootView.findViewById(R.id.liquidation_price_before);
        mAfterLiquidationPriceTitle = rootView.findViewById(R.id.liquidation_price_after_title);
        mAfterLiquidationPrice = rootView.findViewById(R.id.liquidation_price_after);
        mRemainDebtTitle = rootView.findViewById(R.id.remaining_debt_title);
        mRemainDebtAmount = rootView.findViewById(R.id.remaining_debt_amount);
        mRemainDebtDenom = rootView.findViewById(R.id.remaining_debt_amount_denom);
        mRemainDebtValue = rootView.findViewById(R.id.remaining_debt_value);
        mMemo = rootView.findViewById(R.id.memo);
        mWarnLayer = rootView.findViewById(R.id.warnning_layer);
        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mConfirmBtn = rootView.findViewById(R.id.btn_confirm);
        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onRefreshTab() {
        final String cDenom = getCParam().denom;
        final String pDenom = getCParam().debt_limit.denom;
        BigDecimal feeAmount = new BigDecimal(getSActivity().mFee.amount.get(0).amount);

        WDp.showCoinDp(getContext(), pDenom, getSActivity().mPayment.amount, mPaymentDenom, mPaymentAmount, getSActivity().mBaseChain);
        BigDecimal paymentValue = new BigDecimal(getSActivity().mPayment.amount).movePointLeft(WUtil.getKavaCoinDecimal(pDenom));
        mPaymentValue.setText(WDp.getDpRawDollor(getContext(), paymentValue, 2));

        WDp.showCoinDp(getContext(), TOKEN_KAVA, feeAmount.toPlainString(), mFeesDenom, mFeesAmount, getSActivity().mBaseChain);
        BigDecimal kavaValue = feeAmount.movePointLeft(WUtil.getKavaCoinDecimal(TOKEN_KAVA)).multiply(getBaseDao().getLastKavaDollorTic()).setScale(2, RoundingMode.DOWN);
        mFeeValue.setText(WDp.getDpRawDollor(getContext(), kavaValue, 2));

        WDp.DpRiskRate(getContext(), getSActivity().mBeforeRiskRate , mBeforeRiskTv, null);
        WDp.DpRiskRate(getContext(), getSActivity().mAfterRiskRate , mAfterRiskRateTv, null);

        mBeforeLiquidationPriceTitle.setText(String.format(getString(R.string.str_before_liquidation_title2), cDenom.toUpperCase()));
        mBeforeLiquidationPrice.setText(WDp.getDpRawDollor(getContext(), getSActivity().mBeforeLiquidationPrice.toPlainString(),  4));

        mAfterLiquidationPriceTitle.setText(String.format(getString(R.string.str_after_liquidation_title2), cDenom.toUpperCase()));
        mAfterLiquidationPrice.setText(WDp.getDpRawDollor(getContext(), getSActivity().mAfterLiquidationPrice.toPlainString(),  4));

        WDp.showCoinDp(getContext(), pDenom, getSActivity().mRemainLoanAmount.toPlainString(), mRemainDebtDenom, mRemainDebtAmount, getSActivity().mBaseChain);
        BigDecimal remaindValue = getSActivity().mRemainLoanAmount.movePointLeft(WUtil.getKavaCoinDecimal(pDenom));
        mRemainDebtValue.setText(WDp.getDpRawDollor(getContext(), remaindValue, 2));
        if (getSActivity().mRemainLoanAmount.equals(BigDecimal.ZERO)) {
            mWarnLayer.setVisibility(View.VISIBLE);
        }
        mMemo.setText(getSActivity().mMemo);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartRepayCdp();

        }
    }

    private RepayCdpActivity getSActivity() {
        return (RepayCdpActivity)getBaseActivity();
    }

    private ResCdpParam.KavaCollateralParam getCParam() {
        return getSActivity().mCollateralParam;
    }
}
