package wannabit.io.cosmostaion.fragment.txs.kava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.math.RoundingMode;

import kava.cdp.v1beta1.Genesis;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.kava.DepositCdpActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class DepositCdpStep3Fragment extends BaseFragment implements View.OnClickListener {

    private TextView mDepositAmount, mDepositDenom, mDepositValue;
    private TextView mFeesAmount, mFeesDenom, mFeeValue;
    private TextView mBeforeRiskTv, mAfterRiskRateTv;
    private TextView mBeforeLiquidationPriceTitle, mAfterLiquidationPriceTitle, mBeforeLiquidationPrice, mAfterLiquidationPrice;
    private TextView mTotalDepositAmount, mTotalDepositDenom, mTotalDepositValue;
    private TextView mMemo;
    private Button   mBeforeBtn, mConfirmBtn;


    public static DepositCdpStep3Fragment newInstance(Bundle bundle) {
        DepositCdpStep3Fragment fragment = new DepositCdpStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_deposit_cdp_step3, container, false);
        mDepositAmount = rootView.findViewById(R.id.deposit_amount);
        mDepositDenom = rootView.findViewById(R.id.deposit_amount_denom);
        mDepositValue = rootView.findViewById(R.id.deposit_value);
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
        final String cDenom = getCParam().getDenom();
        final int cDpDecimal = WDp.getDenomDecimal(getBaseDao(), getSActivity().mChainConfig, cDenom);

        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, cDenom, getSActivity().mCollateral.amount, mDepositDenom, mDepositAmount);
        BigDecimal collateralValue = new BigDecimal(getSActivity().mCollateral.amount).movePointLeft(cDpDecimal).multiply(getSActivity().getKavaOraclePrice()).setScale(2, RoundingMode.DOWN);
        mDepositValue.setText(WDp.getDpRawDollor(getContext(), collateralValue, 2));

        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), mFeesDenom, mFeesAmount);
        BigDecimal kavaValue = WDp.usdValue(getBaseDao(), getSActivity().mChainConfig.mainDenom(), new BigDecimal(getSActivity().mTxFee.amount.get(0).amount), 6);
        mFeeValue.setText(WDp.getDpRawDollor(getContext(), kavaValue, 2));

        WUtil.DpRiskRate(getContext(), getSActivity().mBeforeRiskRate , mBeforeRiskTv, null);
        WUtil.DpRiskRate(getContext(), getSActivity().mAfterRiskRate , mAfterRiskRateTv, null);

        mBeforeLiquidationPriceTitle.setText(String.format(getString(R.string.str_before_liquidation_title2), cDenom.toUpperCase()));
        mBeforeLiquidationPrice.setText(WDp.getDpRawDollor(getContext(), getSActivity().mBeforeLiquidationPrice.toPlainString(),  4));

        mAfterLiquidationPriceTitle.setText(String.format(getString(R.string.str_after_liquidation_title2), cDenom.toUpperCase()));
        mAfterLiquidationPrice.setText(WDp.getDpRawDollor(getContext(), getSActivity().mAfterLiquidationPrice.toPlainString(),  4));

        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, cDenom, getSActivity().mTotalDepositAmount.toPlainString(), mTotalDepositDenom, mTotalDepositAmount);
        BigDecimal totalCollateralValue = getSActivity().mTotalDepositAmount.movePointLeft(cDpDecimal).multiply(getSActivity().getKavaOraclePrice()).setScale(2, RoundingMode.DOWN);
        mTotalDepositValue.setText(WDp.getDpRawDollor(getContext(), totalCollateralValue, 2));

        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartDepositCdp();
        }
    }


    private DepositCdpActivity getSActivity() {
        return (DepositCdpActivity)getBaseActivity();
    }

    private Genesis.CollateralParam getCParam() {
        return getSActivity().mCollateralParam;
    }
}
