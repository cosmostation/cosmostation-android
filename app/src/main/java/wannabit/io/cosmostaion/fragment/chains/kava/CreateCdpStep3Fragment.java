package wannabit.io.cosmostaion.fragment.chains.kava;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;

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

import kava.cdp.v1beta1.Genesis;
import kava.pricefeed.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.CreateCdpActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.AlertDialogUtils;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class CreateCdpStep3Fragment extends BaseFragment implements View.OnClickListener{
    public final static int SELECT_CDP_CONFIRM = 9105;

    private TextView mCollateralAmount, mCollateralDenom, mCollateralValue;
    private TextView mPrincipalAmount, mPrincipalDenom, mPrincipalValue;
    private TextView mFeesAmount, mFeesDenom, mFeeValue;
    private TextView mRiskRate;
    private TextView mCurrentPriceTitle, mCurrentPrice;
    private TextView mLiquidationPriceTitle, mLiquidationPrice;
    private TextView mMemo;
    private Button   mBeforeBtn, mConfirmBtn;

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
        mCollateralAmount = rootView.findViewById(R.id.collateral_amount);
        mCollateralDenom = rootView.findViewById(R.id.collateral_amount_denom);
        mCollateralValue = rootView.findViewById(R.id.collateral_value);
        mPrincipalAmount = rootView.findViewById(R.id.principal_amount);
        mPrincipalDenom = rootView.findViewById(R.id.principal_amount_denom);
        mPrincipalValue = rootView.findViewById(R.id.principal_value);
        mFeesAmount = rootView.findViewById(R.id.fees_amount);
        mFeesDenom = rootView.findViewById(R.id.fees_denom);
        mFeeValue = rootView.findViewById(R.id.fee_value);
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
        final String cDenom = getCParam().getDenom();
        final String pDenom = getCParam().getDebtLimit().getDenom();
        BigDecimal feeAmount = new BigDecimal(getSActivity().mTxFee.amount.get(0).amount);

        WDp.showCoinDp(getContext(), getBaseDao(), cDenom, getSActivity().toCollateralAmount.toPlainString(), mCollateralDenom, mCollateralAmount, getSActivity().mBaseChain);
        BigDecimal collateralValue = getSActivity().toCollateralAmount.movePointLeft(WUtil.getKavaCoinDecimal(getBaseDao(), cDenom)).multiply(new BigDecimal(getPrice().getPrice()).movePointLeft(18)).setScale(2, RoundingMode.DOWN);
        mCollateralValue.setText(WDp.getDpRawDollor(getContext(), collateralValue, 2));

        WDp.showCoinDp(getContext(), getBaseDao(), pDenom, getSActivity().toPrincipalAmount.toPlainString(), mPrincipalDenom, mPrincipalAmount, getSActivity().mBaseChain);
        BigDecimal principalValue = getSActivity().toPrincipalAmount.movePointLeft(WUtil.getKavaCoinDecimal(getBaseDao(), pDenom)).setScale(2, RoundingMode.DOWN);
        mPrincipalValue.setText(WDp.getDpRawDollor(getContext(), principalValue, 2));

        WDp.showCoinDp(getContext(), getBaseDao(), TOKEN_KAVA, feeAmount.toPlainString(), mFeesDenom, mFeesAmount, getSActivity().mBaseChain);
        BigDecimal kavaValue = WDp.usdValue(getBaseDao(), TOKEN_KAVA, feeAmount, 6);
        mFeeValue.setText(WDp.getDpRawDollor(getContext(), kavaValue, 2));



        WDp.DpRiskRate(getContext(), getSActivity().mRiskRate, mRiskRate, null);

        mCurrentPriceTitle.setText(String.format(getString(R.string.str_current_title3), cDenom.toUpperCase()));
        mCurrentPrice.setText(WDp.getDpRawDollor(getContext(), new BigDecimal(getPrice().getPrice()).movePointLeft(18).toPlainString(),  4));

        mLiquidationPriceTitle.setText(String.format(getString(R.string.str_liquidation_title3), cDenom.toUpperCase()));
        mLiquidationPrice.setText(WDp.getDpRawDollor(getContext(), getSActivity().mLiquidationPrice.toPlainString(),  4));

        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();
        } else if (v.equals(mConfirmBtn)) {
            AlertDialogUtils.showHeaderImageDoubleButtonDialog(getSActivity(), getString(R.string.str_cdp_warn_title), getString(R.string.str_cdp_warn_msg),
                    getString(R.string.str_cancel),null,
                    getString(R.string.str_confirm), View -> {
                        Intent resultIntent = new Intent();
                        onActivityResult(SELECT_CDP_CONFIRM, Activity.RESULT_OK, resultIntent);
                        },R.drawable.img_cdp_warning);
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

    private Genesis.CollateralParam getCParam() {
        return getSActivity().mCollateralParam;
    }

    private QueryOuterClass.CurrentPriceResponse getPrice() {
        return getSActivity().mKavaTokenPrice;
    }
}
