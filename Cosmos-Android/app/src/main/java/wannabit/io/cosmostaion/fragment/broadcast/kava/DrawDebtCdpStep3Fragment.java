package wannabit.io.cosmostaion.fragment.broadcast.kava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.broadcast.kava.DrawDebtActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.network.res.ResCdpParam;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_KAVA;

public class DrawDebtCdpStep3Fragment extends BaseFragment implements View.OnClickListener {

    private TextView mLoanTitle, mLoanAmount, mLoanDenom, mLoanValue;
    private TextView mFeesAmount, mFeesDenom, mFeeValue;
    private TextView mBeforeRiskTv, mAfterRiskRateTv;
    private TextView mBeforeLiquidationPriceTitle, mAfterLiquidationPriceTitle, mBeforeLiquidationPrice, mAfterLiquidationPrice;
    private TextView mTotalDebtTitle, mTotalDebtAmount, mTotalDebtDenom, mTotalDebtValue;
    private TextView mMemo;
    private Button mBeforeBtn, mConfirmBtn;

    public static DrawDebtCdpStep3Fragment newInstance(Bundle bundle) {
        DrawDebtCdpStep3Fragment fragment = new DrawDebtCdpStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_drawdebt_cdp_step3, container, false);
        mLoanTitle = rootView.findViewById(R.id.more_loan_title);
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
        mTotalDebtTitle = rootView.findViewById(R.id.after_total_debt_title);
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
        final String cDenom = getCParam().denom;
        final String pDenom = getCParam().debt_limit.get(0).denom;
        BigDecimal feeAmount = new BigDecimal(getSActivity().mFee.amount.get(0).amount);

        WDp.showCoinDp(getContext(), pDenom, getSActivity().mPrincipals.get(0).amount, mLoanDenom, mLoanAmount, getSActivity().mBaseChain);
        BigDecimal moreLoanValue = new BigDecimal(getSActivity().mPrincipals.get(0).amount).movePointLeft(WUtil.getKavaCoinDecimal(pDenom));
        mLoanValue.setText(WDp.getDpRawDollor(getContext(), moreLoanValue, 2));

        WDp.showCoinDp(getContext(), COSMOS_KAVA, feeAmount.toPlainString(), mFeesDenom, mFeesAmount, getSActivity().mBaseChain);
        BigDecimal kavaValue = feeAmount.movePointLeft(WUtil.getKavaCoinDecimal(COSMOS_KAVA)).multiply(getBaseDao().getLastKavaDollorTic()).setScale(2, RoundingMode.DOWN);
        mFeeValue.setText(WDp.getDpRawDollor(getContext(), kavaValue, 2));


        WDp.DpRiskRate(getContext(), getSActivity().mBeforeRiskRate , mBeforeRiskTv, null);
        WDp.DpRiskRate(getContext(), getSActivity().mAfterRiskRate , mAfterRiskRateTv, null);

        mBeforeLiquidationPriceTitle.setText(WDp.DpBeforeLiquidationPriceTitle(getContext(), cDenom.toUpperCase()));
        mBeforeLiquidationPrice.setText(WDp.getDpRawDollor(getContext(), getSActivity().mBeforeLiquidationPrice.toPlainString(),  4));

        mAfterLiquidationPriceTitle.setText(WDp.DpAfterLiquidationPriceTitle(getContext(), cDenom.toUpperCase()));
        mAfterLiquidationPrice.setText(WDp.getDpRawDollor(getContext(), getSActivity().mAfterLiquidationPrice.toPlainString(),  4));

        WDp.showCoinDp(getContext(), pDenom, getSActivity().mMoreAddedLoanAmount.toPlainString(), mTotalDebtDenom, mTotalDebtAmount, getSActivity().mBaseChain);
        BigDecimal totalLaonValue = getSActivity().mMoreAddedLoanAmount.movePointLeft(WUtil.getKavaCoinDecimal(pDenom));
        mTotalDebtValue.setText(WDp.getDpRawDollor(getContext(), totalLaonValue, 2));

        mMemo.setText(getSActivity().mMemo);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartDrawDebtCdp();

        }
    }

    private DrawDebtActivity getSActivity() {
        return (DrawDebtActivity)getBaseActivity();
    }

    private ResCdpParam.KavaCollateralParam getCParam() {
        return getSActivity().getCParam();
    }

}
