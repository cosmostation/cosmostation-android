package wannabit.io.cosmostaion.fragment.txs.kava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

import kava.cdp.v1beta1.Genesis;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.kava.BorrowCdpActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class DrawDebtCdpStep3Fragment extends BaseFragment implements View.OnClickListener {

    private TextView mLoanAmount, mLoanDenom, mLoanValue;
    private TextView mFeesAmount, mFeesDenom, mFeeValue;
    private TextView mBeforeRiskTv, mAfterRiskRateTv;
    private TextView mBeforeLiquidationPriceTitle, mAfterLiquidationPriceTitle, mBeforeLiquidationPrice, mAfterLiquidationPrice;
    private TextView mTotalDebtAmount, mTotalDebtDenom, mTotalDebtValue;
    private TextView mMemo;
    private Button   mBeforeBtn, mConfirmBtn;

    public static DrawDebtCdpStep3Fragment newInstance() {
        return new DrawDebtCdpStep3Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_drawdebt_cdp_step3, container, false);
        mLoanAmount = rootView.findViewById(R.id.more_loan_amount);
        mLoanDenom = rootView.findViewById(R.id.more_loan_amount_denom);
        mLoanValue = rootView.findViewById(R.id.more_loan_value);
        mFeesAmount = rootView.findViewById(R.id.fees_amount);
        mFeesDenom = rootView.findViewById(R.id.fees_denom);
        mFeeValue = rootView.findViewById(R.id.fee_value);
        mBeforeRiskTv = rootView.findViewById(R.id.risk_rate_before);
        mAfterRiskRateTv = rootView.findViewById(R.id.risk_rate_after);
        mBeforeLiquidationPriceTitle = rootView.findViewById(R.id.liquidation_price_before_title);
        mBeforeLiquidationPrice = rootView.findViewById(R.id.liquidation_price_before);
        mAfterLiquidationPriceTitle = rootView.findViewById(R.id.liquidation_price_after_title);
        mAfterLiquidationPrice = rootView.findViewById(R.id.liquidation_price_after);
        mTotalDebtAmount = rootView.findViewById(R.id.after_total_debt_amount);
        mTotalDebtDenom = rootView.findViewById(R.id.after_total_debt_denom);
        mTotalDebtValue = rootView.findViewById(R.id.after_total_debt_value);
        mMemo = rootView.findViewById(R.id.memo);
        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mConfirmBtn = rootView.findViewById(R.id.btn_confirm);
        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        final String cDenom = getCParam().getDenom();
        final String pDenom = getCParam().getDebtLimit().getDenom();
        final int pDecimal = WDp.getDenomDecimal(getBaseDao(), getSActivity().mChainConfig, pDenom);

        WDp.setDpCoin(getContext(), getBaseDao(), getSActivity().mChainConfig, pDenom, getSActivity().mPrincipal.amount, mLoanDenom, mLoanAmount);
        BigDecimal moreLoanValue = new BigDecimal(getSActivity().mPrincipal.amount).movePointLeft(pDecimal);
        mLoanValue.setText(WDp.getDpRawDollor(getContext(), moreLoanValue, 2));

        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), mFeesDenom, mFeesAmount);
        BigDecimal kavaValue = WDp.usdValue(getBaseDao(), getSActivity().mChainConfig.mainDenom(), new BigDecimal(getSActivity().mTxFee.amount.get(0).amount), 6);
        mFeeValue.setText(WDp.getDpRawDollor(getContext(), kavaValue, 2));

        WUtil.DpRiskRate(getContext(), getSActivity().mBeforeRiskRate , mBeforeRiskTv, null);
        WUtil.DpRiskRate(getContext(), getSActivity().mAfterRiskRate , mAfterRiskRateTv, null);

        mBeforeLiquidationPriceTitle.setText(String.format(getString(R.string.str_before_liquidation_title2), cDenom.toUpperCase()));
        mBeforeLiquidationPrice.setText(WDp.getDpRawDollor(getContext(), getSActivity().mBeforeLiquidationPrice.toPlainString(),  4));

        mAfterLiquidationPriceTitle.setText(String.format(getString(R.string.str_after_liquidation_title2), cDenom.toUpperCase()));
        mAfterLiquidationPrice.setText(WDp.getDpRawDollor(getContext(), getSActivity().mAfterLiquidationPrice.toPlainString(),  4));

        WDp.setDpCoin(getContext(), getBaseDao(), getSActivity().mChainConfig, pDenom, getSActivity().mMoreAddedLoanAmount.toPlainString(), mTotalDebtDenom, mTotalDebtAmount);
        BigDecimal totalLaonValue = getSActivity().mMoreAddedLoanAmount.movePointLeft(pDecimal);
        mTotalDebtValue.setText(WDp.getDpRawDollor(getContext(), totalLaonValue, 2));

        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartDrawDebtCdp();

        }
    }

    private BorrowCdpActivity getSActivity() {
        return (BorrowCdpActivity)getBaseActivity();
    }

    private Genesis.CollateralParam getCParam() {
        return getSActivity().mCollateralParam;
    }

}
