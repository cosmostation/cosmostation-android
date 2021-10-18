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
import wannabit.io.cosmostaion.activities.chains.kava.WithdrawCdpActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.kava.CollateralParam;
import wannabit.io.cosmostaion.model.kava.MarketPrice;
import wannabit.io.cosmostaion.network.res.ResKavaMarketPrice;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;

public class WithdrawCdpStep3Fragment extends BaseFragment implements View.OnClickListener {

    private TextView mWithdrawTitle, mWithdrawAmount, mWithdrawDenom, mWithdrawValue;
    private TextView mFeesAmount, mFeesDenom, mFeeValue;
    private TextView mBeforeRiskTv, mAfterRiskRateTv;
    private TextView mBeforeLiquidationPriceTitle, mAfterLiquidationPriceTitle, mBeforeLiquidationPrice, mAfterLiquidationPrice;
    private TextView mTotalDepositAmount, mTotalDepositDenom, mTotalDepositValue;
    private TextView mMemo;
    private Button mBeforeBtn, mConfirmBtn;

    public static WithdrawCdpStep3Fragment newInstance(Bundle bundle) {
        WithdrawCdpStep3Fragment fragment = new WithdrawCdpStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_withdraw_cdp_step3, container, false);
        mWithdrawTitle = rootView.findViewById(R.id.withdraw_title);
        mWithdrawAmount = rootView.findViewById(R.id.withdraw_amount);
        mWithdrawDenom = rootView.findViewById(R.id.withdraw_amount_denom);
        mWithdrawValue = rootView.findViewById(R.id.withdraw_value);
        mFeesAmount = rootView.findViewById(R.id.fees_amount);
        mFeesDenom = rootView.findViewById(R.id.fees_denom);
        mFeeValue = rootView.findViewById(R.id.fee_value);
        mBeforeRiskTv = rootView.findViewById(R.id.risk_rate_before);
        mAfterRiskRateTv = rootView.findViewById(R.id.risk_rate_after);
        mBeforeLiquidationPriceTitle = rootView.findViewById(R.id.liquidation_price_before_title);
        mBeforeLiquidationPrice = rootView.findViewById(R.id.liquidation_price_before);
        mAfterLiquidationPriceTitle = rootView.findViewById(R.id.liquidation_price_after_title);
        mAfterLiquidationPrice = rootView.findViewById(R.id.liquidation_price_after);
        mTotalDepositAmount = rootView.findViewById(R.id.after_total_deposit_amount);
        mTotalDepositDenom = rootView.findViewById(R.id.after_total_deposit_denom);
        mTotalDepositValue = rootView.findViewById(R.id.after_total_deposit_value);
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
        final String pDenom = getCParam().debt_limit.denom;
        BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);

        WDp.showCoinDp(getContext(), cDenom, getSActivity().mCollateral.amount, mWithdrawDenom, mWithdrawAmount, getSActivity().mBaseChain);
        BigDecimal collateralValue = new BigDecimal(getSActivity().mCollateral.amount).movePointLeft(WUtil.getKavaCoinDecimal(cDenom)).multiply(new BigDecimal(getPrice().price)).setScale(2, RoundingMode.DOWN);
        mWithdrawValue.setText(WDp.getDpRawDollor(getContext(), collateralValue, 2));

        WDp.showCoinDp(getContext(), TOKEN_KAVA, feeAmount.toPlainString(), mFeesDenom, mFeesAmount, getSActivity().mBaseChain);
        BigDecimal kavaValue = WDp.usdValue(getBaseDao(), TOKEN_KAVA, feeAmount, 6);
        mFeeValue.setText(WDp.getDpRawDollor(getContext(), kavaValue, 2));

        WDp.DpRiskRate(getContext(), getSActivity().mBeforeRiskRate , mBeforeRiskTv, null);
        WDp.DpRiskRate(getContext(), getSActivity().mAfterRiskRate , mAfterRiskRateTv, null);

        mBeforeLiquidationPriceTitle.setText(String.format(getString(R.string.str_before_liquidation_title2), cDenom.toUpperCase()));
        mBeforeLiquidationPrice.setText(WDp.getDpRawDollor(getContext(), getSActivity().mBeforeLiquidationPrice.toPlainString(),  4));

        mAfterLiquidationPriceTitle.setText(String.format(getString(R.string.str_after_liquidation_title2), cDenom.toUpperCase()));
        mAfterLiquidationPrice.setText(WDp.getDpRawDollor(getContext(), getSActivity().mAfterLiquidationPrice.toPlainString(),  4));

        WDp.showCoinDp(getContext(), cDenom, getSActivity().mTotalDepositAmount.toPlainString(), mTotalDepositDenom, mTotalDepositAmount, getSActivity().mBaseChain);
        BigDecimal totalCollateralValue = getSActivity().mTotalDepositAmount.movePointLeft(WUtil.getKavaCoinDecimal(cDenom)).multiply(new BigDecimal(getPrice().price)).setScale(2, RoundingMode.DOWN);
        mTotalDepositValue.setText(WDp.getDpRawDollor(getContext(), totalCollateralValue, 2));

        mMemo.setText(getSActivity().mTxMemo);
    }


    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartWithdrawCdp();

        }
    }

    private WithdrawCdpActivity getSActivity() {
        return (WithdrawCdpActivity)getBaseActivity();
    }

    private CollateralParam getCParam() {
        return getSActivity().mCollateralParam;
    }

    private MarketPrice getPrice() {
        return getSActivity().mKavaTokenPrice;
    }
}