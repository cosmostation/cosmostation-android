package wannabit.io.cosmostaion.fragment.broadcast.kava;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.RoundingMode;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.broadcast.kava.RepayCdpActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_Safe_Score_Confirm;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.network.res.ResCdpOwnerStatus;
import wannabit.io.cosmostaion.network.res.ResCdpParam;
import wannabit.io.cosmostaion.network.res.ResKavaMarketPrice;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_COIN_IMG_URL;

public class RepayCdpStep0Fragment extends BaseFragment implements View.OnClickListener {
    public final static int CDP_REPAY_CONFIRM_DIALOG = 6018;

    private Button          mBtnCancel, mBtnNext;

    private LinearLayout    mPrincipalLayer;
    private ImageView       mPrincipalImg;
    private TextView        mPrincipalSymbol;
    private EditText        mPrincipalInput;
    private ImageView       mPrincipalClear;
    private TextView        mPrincipalMaxTx, mPrincipalDenomTx;
    private Button          mBtnAdd01, mBtnAdd1, mBtnAdd10, mBtnAddHalf, mBtnAddMax;

    private TextView        mBeforeRisk, mAfterRisk;
    private TextView        mBeforePrincipalAmount, mAfterPrincipalAmount;

    private BigDecimal      mCurrentPrice;
    private String          mCollateralDenom, mPrincipalDenom;
    private BigDecimal      mCurrentTotalDebetAmount, mCurrentCollateralAmount, mMaxPaymentableAmount;
    private BigDecimal      mBeforeLiquidationPrice, mBeforeRiskRate, mAfterLiquidationPrice, mAfterRiskRate, mRemainLoanAmount, mToPaymentAmount;
    private String          mPrincipalChecker, mPrincipalSetter;


    public static RepayCdpStep0Fragment newInstance(Bundle bundle) {
        RepayCdpStep0Fragment fragment = new RepayCdpStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_repay_cdp_0, container, false);
        mBtnCancel = rootView.findViewById(R.id.btn_cancel);
        mBtnNext = rootView.findViewById(R.id.btn_next);

        mPrincipalLayer = rootView.findViewById(R.id.principal_layer);
        mPrincipalImg = rootView.findViewById(R.id.principal_icon);
        mPrincipalSymbol = rootView.findViewById(R.id.principal_symbol);
        mPrincipalInput = rootView.findViewById(R.id.principal_input);
        mPrincipalClear = rootView.findViewById(R.id.principal_clear);
        mPrincipalMaxTx = rootView.findViewById(R.id.principal_max_amount);
        mPrincipalDenomTx = rootView.findViewById(R.id.principal_denom);
        mBtnAdd01 = rootView.findViewById(R.id.btn_add_01);
        mBtnAdd1 = rootView.findViewById(R.id.btn_add_1);
        mBtnAdd10 = rootView.findViewById(R.id.btn_add_10);
        mBtnAddHalf = rootView.findViewById(R.id.btn_add_half);
        mBtnAddMax = rootView.findViewById(R.id.btn_add_all);

        mBeforeRisk = rootView.findViewById(R.id.risk_rate_before);
        mAfterRisk = rootView.findViewById(R.id.risk_rate_after);
        mBeforePrincipalAmount = rootView.findViewById(R.id.loaned_amount_before);
        mAfterPrincipalAmount = rootView.findViewById(R.id.loaned_amount_after);

        mPrincipalClear.setOnClickListener(this);
        mBtnAdd01.setOnClickListener(this);
        mBtnAdd1.setOnClickListener(this);
        mBtnAdd10.setOnClickListener(this);
        mBtnAddHalf.setOnClickListener(this);
        mBtnAddMax.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onRefreshTab() {
        super.onRefreshTab();
        onUpdateInitInfo();
        onDisplayViewUpdate();
    }

    private void onUpdateInitInfo() {
        mBeforeLiquidationPrice = mBeforeRiskRate = mAfterLiquidationPrice = mAfterRiskRate = mRemainLoanAmount= BigDecimal.ZERO;

        mCollateralDenom = getCParam().denom;
        mPrincipalDenom = getCParam().debt_limit.get(0).denom;
        setDpDecimals(WUtil.getKavaCoinDecimal(mPrincipalDenom));
        mCurrentPrice = new BigDecimal(getPrice().price);

        mCurrentTotalDebetAmount = getOwenCdp().getPrincipalAmount().add(getOwenCdp().getAccumulatedFees());
        BigDecimal hiddenFeeValue = WDp.getCdpHiddenFee(getContext(), mCurrentTotalDebetAmount, getCParam(), getOwenCdp().cdp);
        WLog.w("hiddenFeeValue " + hiddenFeeValue);
        mCurrentTotalDebetAmount = mCurrentTotalDebetAmount.add(hiddenFeeValue);

        mCurrentCollateralAmount = getOwenCdp().getCollateralAmount();

        BigDecimal available = getSActivity().getTokenAvailable(mPrincipalDenom);
        mMaxPaymentableAmount = mCurrentTotalDebetAmount.compareTo(available) > 0 ? available : mCurrentTotalDebetAmount;
        WLog.w("mPrincipalDenom " + mPrincipalDenom);
        WLog.w("mCurrentTotalDebetAmount " + mCurrentTotalDebetAmount);
        WLog.w("mPaymnetMaxAmount " + mMaxPaymentableAmount);
        WDp.showCoinDp(getContext(), mPrincipalDenom, mMaxPaymentableAmount.toPlainString(), mPrincipalDenomTx, mPrincipalMaxTx, getSActivity().mBaseChain);

        mPrincipalSymbol.setText(mPrincipalDenom.toUpperCase());
//        mPrincipalDenomTx.setText(mPrincipalDenom.toUpperCase());
        try {
            Picasso.get().load(KAVA_COIN_IMG_URL + mPrincipalDenom + ".png").fit().into(mPrincipalImg);
        } catch (Exception e) { }


        //before(current) state views!!
        mBeforeLiquidationPrice = mCurrentTotalDebetAmount.movePointLeft(WUtil.getKavaCoinDecimal(mPrincipalDenom) - WUtil.getKavaCoinDecimal(mCollateralDenom)).multiply(new BigDecimal(getCParam().liquidation_ratio)).divide(mCurrentCollateralAmount, WUtil.getKavaCoinDecimal(mCollateralDenom), RoundingMode.DOWN);
        WLog.w("mBeforeLiquidationPrice " + mBeforeLiquidationPrice);
        mBeforeRiskRate = new BigDecimal(100).subtract((mCurrentPrice.subtract(mBeforeLiquidationPrice)).movePointRight(2).divide(mCurrentPrice, 2, RoundingMode.DOWN));
        WLog.w("mBeforeRiskRate " + mBeforeRiskRate);
        WDp.DpRiskRate(getContext(), mBeforeRiskRate, mBeforeRisk, null);
        mBeforePrincipalAmount.setText(WDp.getDpAmount2(getContext(), mCurrentTotalDebetAmount, WUtil.getKavaCoinDecimal(mPrincipalDenom), WUtil.getKavaCoinDecimal(mPrincipalDenom)));

        mPrincipalInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable et) {
                String es = et.toString().trim();
                if(TextUtils.isEmpty(es)) {
                    mPrincipalInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                } else if (es.startsWith(".")) {
                    mPrincipalInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                    mPrincipalInput.setText("");
                } else if (es.endsWith(".")) {
                    mPrincipalInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                } else if(mPrincipalInput.length() > 1 && es.startsWith("0") && !es.startsWith("0.")) {
                    mPrincipalInput.setText("0");
                    mPrincipalInput.setSelection(1);
                }

                if (es.equals(mPrincipalChecker)) {
                    mPrincipalInput.setText(mPrincipalSetter);
                    mPrincipalInput.setSelection(WUtil.getKavaCoinDecimal(mPrincipalDenom) + 1);
                } else {
                    try {
                        final BigDecimal inputAmount = new BigDecimal(es);
                        if (BigDecimal.ZERO.compareTo(inputAmount) >= 0 ){
                            mPrincipalInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                            return;
                        }

                        BigDecimal checkPosition = inputAmount.movePointRight(WUtil.getKavaCoinDecimal(mPrincipalDenom));
                        BigDecimal checkMax = checkPosition.setScale(0, RoundingMode.DOWN);
                        if (checkPosition.compareTo(checkMax) != 0) {
                            String recover = es.substring(0, es.length() - 1);
                            mPrincipalInput.setText(recover);
                            mPrincipalInput.setSelection(recover.length());
                            return;
                        }
                        if (BigDecimal.ZERO.compareTo(checkPosition) >= 0) {
                            mPrincipalInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));

                        } else if (mMaxPaymentableAmount.compareTo(checkPosition) < 0) {
                            mPrincipalInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));

                        } else {
                            mPrincipalInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                        }
                        mPrincipalInput.setSelection(mPrincipalInput.getText().length());

                    } catch (Exception e) { }
                }
                onDisplayViewUpdate();
            }
        });
    }


    private boolean onDisplayViewUpdate() {
        //TODO show before & next button update!
        mAfterLiquidationPrice = mAfterRiskRate = mRemainLoanAmount = null;
        try {
            mToPaymentAmount = BigDecimal.ZERO;
            try {
                mToPaymentAmount = new BigDecimal(mPrincipalInput.getText().toString().trim()).movePointRight(WUtil.getKavaCoinDecimal(mPrincipalDenom));
            } catch (Exception e) {
                mAfterRisk.setVisibility(View.INVISIBLE);
                mAfterPrincipalAmount.setVisibility(View.GONE);
                return false;
            }
            WLog.w("mToPaymentAmount " + mToPaymentAmount);
            if (mToPaymentAmount.compareTo(BigDecimal.ZERO) <= 0 || mToPaymentAmount.compareTo(mCurrentTotalDebetAmount) > 0) {
                mBtnNext.setText(R.string.str_next);
                mBtnNext.setTextColor(getResources().getColor(R.color.color_btn_photon));
                mBtnNext.setBackground(getResources().getDrawable(R.drawable.btn_trans_with_border));
                mBtnNext.setTypeface(null, Typeface.NORMAL);
                mAfterRisk.setVisibility(View.INVISIBLE);
                mAfterPrincipalAmount.setVisibility(View.GONE);
                return false;
            }
            if (mMaxPaymentableAmount.compareTo(mToPaymentAmount) < 0) {
                mBtnNext.setText(R.string.str_next);
                mBtnNext.setTextColor(getResources().getColor(R.color.color_btn_photon));
                mBtnNext.setBackground(getResources().getDrawable(R.drawable.btn_trans_with_border));
                mBtnNext.setTypeface(null, Typeface.NORMAL);
                mAfterRisk.setVisibility(View.INVISIBLE);
                mAfterPrincipalAmount.setVisibility(View.GONE);
                return false;

            }
            mAfterRisk.setVisibility(View.VISIBLE);
//            mAfterPrincipalAmount.setVisibility(View.VISIBLE);

            //after payment state views!!
            mRemainLoanAmount = mCurrentTotalDebetAmount.subtract(mToPaymentAmount);
            WLog.w("mRemainLoanAmount " + mRemainLoanAmount);

            mAfterLiquidationPrice = mRemainLoanAmount.movePointLeft(WUtil.getKavaCoinDecimal(mPrincipalDenom) - WUtil.getKavaCoinDecimal(mCollateralDenom)).multiply(new BigDecimal(getCParam().liquidation_ratio)).divide(mCurrentCollateralAmount, WUtil.getKavaCoinDecimal(mCollateralDenom), RoundingMode.DOWN);
            WLog.w("mAfterLiquidationPrice " + mAfterLiquidationPrice);

            mAfterRiskRate = new BigDecimal(100).subtract((mCurrentPrice.subtract(mAfterLiquidationPrice)).movePointRight(2).divide(mCurrentPrice, 2, RoundingMode.DOWN));
            WLog.w("mAfterRiskRate " + mAfterRiskRate);


            WDp.DpRiskRate(getContext(), mAfterRiskRate, mAfterRisk, null);
            WDp.DpRiskButton(getContext(), mAfterRiskRate, mBtnNext);
            if (mRemainLoanAmount.equals(BigDecimal.ZERO)) {
                mBtnNext.setText("REPAY ALL");
            }

//            mAfterPrincipalAmount.setText(WDp.getDpAmount2(getContext(), mRemainLoanAmount, WUtil.getKavaCoinDecimal(mPrincipalDenom), WUtil.getKavaCoinDecimal(mPrincipalDenom)));
            return true;

        } catch (Exception e) {
            mBtnNext.setText(R.string.str_next);
            mBtnNext.setTextColor(getResources().getColor(R.color.color_btn_photon));
            mBtnNext.setBackground(getResources().getDrawable(R.drawable.btn_trans_with_border));
            mBtnNext.setTypeface(null, Typeface.NORMAL);
            return false;
        }

    }


    @Override
    public void onClick(View v) {
        if (v.equals(mPrincipalClear)) {
            mPrincipalInput.setText("");

        } else if (v.equals(mBtnAdd01)) {
            BigDecimal inputedAmount = BigDecimal.ZERO;
            try {
                inputedAmount = new BigDecimal(mPrincipalInput.getText().toString().trim());
            } catch (Exception e) { }
            inputedAmount = inputedAmount.add(new BigDecimal("0.1"));
            mPrincipalInput.setText(inputedAmount.toPlainString());

        } else if (v.equals(mBtnAdd1)) {
            BigDecimal inputedAmount = BigDecimal.ZERO;
            try {
                inputedAmount = new BigDecimal(mPrincipalInput.getText().toString().trim());
            } catch (Exception e) { }
            inputedAmount = inputedAmount.add(BigDecimal.ONE);
            mPrincipalInput.setText(inputedAmount.toPlainString());

        } else if (v.equals(mBtnAdd10)) {
            BigDecimal inputedAmount = BigDecimal.ZERO;
            try {
                inputedAmount = new BigDecimal(mPrincipalInput.getText().toString().trim());
            } catch (Exception e) { }
            inputedAmount = inputedAmount.add(new BigDecimal("10"));
            mPrincipalInput.setText(inputedAmount.toPlainString());

        } else if (v.equals(mBtnAddHalf)) {
            mPrincipalInput.setText(mMaxPaymentableAmount.divide(new BigDecimal("2").movePointRight(WUtil.getKavaCoinDecimal(mPrincipalDenom)), WUtil.getKavaCoinDecimal(mPrincipalDenom), RoundingMode.DOWN).toPlainString());

        } else if (v.equals(mBtnAddMax)) {
            mPrincipalInput.setText(mMaxPaymentableAmount.divide(new BigDecimal("1").movePointRight(WUtil.getKavaCoinDecimal(mPrincipalDenom)), WUtil.getKavaCoinDecimal(mPrincipalDenom), RoundingMode.DOWN).toPlainString());

        } else if (v.equals(mBtnCancel)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mBtnNext)) {
            if (onDisplayViewUpdate()) {
                if (mBeforeRiskRate.compareTo(BigDecimal.ZERO) < 0 || mToPaymentAmount.compareTo(BigDecimal.ZERO) <= 0 ||
                        mAfterLiquidationPrice == null || mAfterRiskRate == null || mRemainLoanAmount == null) {
                    Toast.makeText(getContext(), R.string.error_invalid_amount, Toast.LENGTH_SHORT).show();
                    return;

                } else if ((mCurrentTotalDebetAmount.subtract(mToPaymentAmount).compareTo(new BigDecimal(getCdpParam().debt_params.get(0).debt_floor)) < 0) && (mCurrentTotalDebetAmount.subtract(mToPaymentAmount).compareTo(BigDecimal.ZERO) > 0)) {
                    Toast.makeText(getContext(), R.string.error_dust_principal_remain, Toast.LENGTH_SHORT).show();
                    return;

                } else {
                    getSActivity().mPayment.clear();
                    Coin payment = new Coin(mPrincipalDenom, mToPaymentAmount.toPlainString());
                    getSActivity().mPayment.add(payment);
                    getSActivity().mBeforeLiquidationPrice = mBeforeLiquidationPrice;
                    getSActivity().mBeforeRiskRate = mBeforeRiskRate;
                    getSActivity().mAfterLiquidationPrice = mAfterLiquidationPrice;
                    getSActivity().mAfterRiskRate = mAfterRiskRate;
                    getSActivity().mRemainLoanAmount = mRemainLoanAmount;

                    Bundle bundle = new Bundle();
                    bundle.putString("beforeRiskRate", mBeforeRiskRate.toPlainString());
                    bundle.putString("afterRiskRate", mAfterRiskRate.toPlainString());
                    bundle.putString("beforeLiquidationPrice", mBeforeLiquidationPrice.toPlainString());
                    bundle.putString("afterLiquidationPrice", mAfterLiquidationPrice.toPlainString());
                    bundle.putString("currentPrice", getPrice().price);
                    bundle.putString("denom", mCollateralDenom);
                    Dialog_Safe_Score_Confirm dialog = Dialog_Safe_Score_Confirm.newInstance(bundle);
                    dialog.setCancelable(true);
                    dialog.setTargetFragment(this, CDP_REPAY_CONFIRM_DIALOG);
                    dialog.show(getFragmentManager().beginTransaction(), "dialog");

                }
            } else {
                Toast.makeText(getContext(), R.string.error_invalid_amount, Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CDP_REPAY_CONFIRM_DIALOG && resultCode == Activity.RESULT_OK) {
            getSActivity().onNextStep();
        }
    }



    private RepayCdpActivity getSActivity() {
        return (RepayCdpActivity)getBaseActivity();
    }

    private ResCdpParam.Result getCdpParam() {
        return getSActivity().mCdpParam;
    }

    private ResCdpParam.KavaCollateralParam getCParam() {
        return getSActivity().getCParam();
    }

    private ResCdpOwnerStatus.Result getOwenCdp() {
        return getSActivity().mMyOwenCdp;
    }

    private ResKavaMarketPrice.Result getPrice() {
        return getSActivity().mTokenPrice;
    }

    private void setDpDecimals(int pDeciaml) {
        mPrincipalChecker = "0.";
        mPrincipalSetter = "0.";
        for (int i = 0; i < pDeciaml; i ++) {
            mPrincipalChecker = mPrincipalChecker+"0";
        }
        for (int i = 0; i < pDeciaml-1; i ++) {
            mPrincipalSetter = mPrincipalSetter+"0";
        }
    }



}
