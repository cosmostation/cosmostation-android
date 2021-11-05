package wannabit.io.cosmostaion.fragment.chains.kava;

import android.app.Activity;
import android.content.Intent;
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
import wannabit.io.cosmostaion.activities.chains.kava.CreateCdpActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_Cdp_Warning;
import wannabit.io.cosmostaion.model.kava.CdpParam;
import wannabit.io.cosmostaion.model.kava.CollateralParam;
import wannabit.io.cosmostaion.model.kava.MarketPrice;
import wannabit.io.cosmostaion.network.res.ResKavaMarketPrice;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;

public class CreateCdpStep3Fragment extends BaseFragment implements View.OnClickListener {
    public final static int SELECT_CDP_CONFIRM = 9105;

    private TextView mCollateralAmountTitle, mCollateralAmount, mCollateralDenom, mCollateralValue;
    private TextView mPrincipalAmountTitle, mPrincipalAmount, mPrincipalDenom, mPrincipalValue;
    private TextView mFeesAmount, mFeesDenom, mFeeValue;
    private TextView mRiskTxt, mRiskRate;
    private TextView mCurrentPriceTitle, mCurrentPrice;
    private TextView mLiquidationPriceTitle, mLiquidationPrice;
    private TextView mMemo;
    private Button mBeforeBtn, mConfirmBtn;

    public static CreateCdpStep3Fragment newInstance(Bundle bundle) {
        CreateCdpStep3Fragment fragment = new CreateCdpStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_cdp_step3, container, false);
        mCollateralAmountTitle = rootView.findViewById(R.id.collateral_amount_title);
        mCollateralAmount = rootView.findViewById(R.id.collateral_amount);
        mCollateralDenom = rootView.findViewById(R.id.collateral_amount_denom);
        mCollateralValue = rootView.findViewById(R.id.collateral_value);
        mPrincipalAmountTitle = rootView.findViewById(R.id.principal_amount_title);
        mPrincipalAmount = rootView.findViewById(R.id.principal_amount);
        mPrincipalDenom = rootView.findViewById(R.id.principal_amount_denom);
        mPrincipalValue = rootView.findViewById(R.id.principal_value);
        mFeesAmount = rootView.findViewById(R.id.fees_amount);
        mFeesDenom = rootView.findViewById(R.id.fees_denom);
        mFeeValue = rootView.findViewById(R.id.fee_value);
        mRiskTxt = rootView.findViewById(R.id.risk_rate_txt);
        mRiskRate = rootView.findViewById(R.id.risk_rate);
        mCurrentPriceTitle = rootView.findViewById(R.id.current_price_title);
        mCurrentPrice = rootView.findViewById(R.id.current_price);
        mLiquidationPriceTitle = rootView.findViewById(R.id.liquidation_price_title);
        mLiquidationPrice = rootView.findViewById(R.id.liquidation_price);
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

//        mCollateralAmountTitle.setText(WDp.DpCollateralTitle(getContext(), cDenom.toUpperCase()));
        WDp.showCoinDp(getContext(), getBaseDao(), cDenom, getSActivity().toCollateralAmount.toPlainString(), mCollateralDenom, mCollateralAmount, getSActivity().mBaseChain);
        BigDecimal collateralValue = getSActivity().toCollateralAmount.movePointLeft(WUtil.getKavaCoinDecimal(cDenom)).multiply(new BigDecimal(getPrice().price)).setScale(2, RoundingMode.DOWN);
        mCollateralValue.setText(WDp.getDpRawDollor(getContext(), collateralValue, 2));

//        mPrincipalAmountTitle.setText(WDp.DpLoanedTitle(getContext(), pDenom.toUpperCase()));
        WDp.showCoinDp(getContext(), getBaseDao(), pDenom, getSActivity().toPrincipalAmount.toPlainString(), mPrincipalDenom, mPrincipalAmount, getSActivity().mBaseChain);
        BigDecimal principalValue = getSActivity().toPrincipalAmount.movePointLeft(WUtil.getKavaCoinDecimal(pDenom)).setScale(2, RoundingMode.DOWN);
        mPrincipalValue.setText(WDp.getDpRawDollor(getContext(), principalValue, 2));

        WDp.showCoinDp(getContext(), getBaseDao(), TOKEN_KAVA, feeAmount.toPlainString(), mFeesDenom, mFeesAmount, getSActivity().mBaseChain);
        BigDecimal kavaValue = WDp.usdValue(getBaseDao(), TOKEN_KAVA, feeAmount, 6);
        mFeeValue.setText(WDp.getDpRawDollor(getContext(), kavaValue, 2));



        WDp.DpRiskRate(getContext(), getSActivity().mRiskRate, mRiskRate, null);

        mCurrentPriceTitle.setText(String.format(getString(R.string.str_current_title3), cDenom.toUpperCase()));
        mCurrentPrice.setText(WDp.getDpRawDollor(getContext(), getPrice().price,  4));

        mLiquidationPriceTitle.setText(String.format(getString(R.string.str_liquidation_title3), cDenom.toUpperCase()));
        mLiquidationPrice.setText(WDp.getDpRawDollor(getContext(), getSActivity().mLiquidationPrice.toPlainString(),  4));

        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            Dialog_Cdp_Warning dialog = Dialog_Cdp_Warning.newInstance();
            dialog.setTargetFragment(CreateCdpStep3Fragment.this, SELECT_CDP_CONFIRM);
            getFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_CDP_CONFIRM && resultCode == Activity.RESULT_OK) {
            getSActivity().onStartCreateCdp();

        }
    }


    private CreateCdpActivity getSActivity() {
        return (CreateCdpActivity)getBaseActivity();
    }

    private CdpParam getCdpParam() {
        return getSActivity().mCdpParam;
    }

    private CollateralParam getCParam() {
        return getSActivity().mCollateralParam;
    }

    private MarketPrice getPrice() {
        return getSActivity().mKavaTokenPrice;
    }
}
