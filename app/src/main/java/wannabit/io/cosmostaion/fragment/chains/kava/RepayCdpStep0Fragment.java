package wannabit.io.cosmostaion.fragment.chains.kava;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
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

import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.RoundingMode;

import kava.cdp.v1beta1.Genesis;
import kava.cdp.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.RepayCdpActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_Safe_Score_Confirm;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_COIN_IMG_URL;

public class RepayCdpStep0Fragment extends BaseFragment implements View.OnClickListener {
    public final static int CDP_REPAY_CONFIRM_DIALOG = 6018;

    private Button          mBtnCancel, mBtnNext;

    private ImageView       mPrincipalImg;
    private TextView        mPrincipalSymbol;
    private EditText        mPrincipalInput;
    private ImageView       mPrincipalClear;


    private LinearLayout    mParticalLayer;
    private TextView        mParticalMaxAmountTx, mParticalMinAmountTx, mParticalDenom;
    private TextView        mDisableParticalTx;

    private LinearLayout    mAllLayer;
    private TextView        mAllAmountTx, mAllDenom;
    private TextView        mDisableAllTx;
    private Button          mBtn1_3, mBtn2_3, mBtnMax, mBtnAll;

    private LinearLayout    mAfterRiskLayer;
    private TextView        mBeforeRisk, mAfterRisk, mBeforeRiskScore, mAfterRiskScore;
    private TextView        mBeforePrincipalAmount;

    private BigDecimal      mCurrentPrice;
    private String          cDenom, pDenom;
    private BigDecimal      mCurrentTotalDebetAmount, mCurrentCollateralAmount;
    private BigDecimal      pMinAmount, pMaxAmount, pAllAmount, pAvailableAmount;
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

        mPrincipalImg = rootView.findViewById(R.id.principal_icon);
        mPrincipalSymbol = rootView.findViewById(R.id.principal_symbol);
        mPrincipalInput = rootView.findViewById(R.id.principal_input);
        mPrincipalClear = rootView.findViewById(R.id.principal_clear);
        mParticalLayer = rootView.findViewById(R.id.paritical_layer);
        mParticalMaxAmountTx = rootView.findViewById(R.id.paritical_max_amount);
        mParticalMinAmountTx = rootView.findViewById(R.id.paritical_min_amount);
        mParticalDenom = rootView.findViewById(R.id.principal_denom);
        mDisableParticalTx = rootView.findViewById(R.id.paritical_disable);
        mAllLayer = rootView.findViewById(R.id.all_layer);
        mAllAmountTx = rootView.findViewById(R.id.all_amount);
        mAllDenom = rootView.findViewById(R.id.all_denom);
        mDisableAllTx = rootView.findViewById(R.id.all_disable);
        mBtn1_3 = rootView.findViewById(R.id.btn_1_3);
        mBtn2_3 = rootView.findViewById(R.id.btn_2_3);
        mBtnMax = rootView.findViewById(R.id.btn_p_max);
        mBtnAll = rootView.findViewById(R.id.btn_add_all);

        mBeforeRisk = rootView.findViewById(R.id.risk_rate_before);
        mBeforeRiskScore = rootView.findViewById(R.id.risk_score_before);
        mAfterRiskLayer = rootView.findViewById(R.id.risk_rate_after_layer);
        mAfterRisk = rootView.findViewById(R.id.risk_rate_after);
        mAfterRiskScore = rootView.findViewById(R.id.risk_score_after);
        mBeforePrincipalAmount = rootView.findViewById(R.id.loaned_amount_before);

        mPrincipalClear.setOnClickListener(this);
        mBtn1_3.setOnClickListener(this);
        mBtn2_3.setOnClickListener(this);
        mBtnMax.setOnClickListener(this);
        mBtnAll.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onRefreshTab() {
        super.onRefreshTab();
        onUpdateInitInfo();
        onUpdateNextBtn();
    }

    private void onUpdateInitInfo() {
        mBeforeLiquidationPrice = mBeforeRiskRate = mAfterLiquidationPrice = mAfterRiskRate = mRemainLoanAmount= BigDecimal.ZERO;

        cDenom = getCParam().getDenom();
        pDenom = getCParam().getDebtLimit().getDenom();
        setDpDecimals(WUtil.getKavaCoinDecimal(pDenom));
        mCurrentPrice = new BigDecimal(getPrice().getPrice()).movePointLeft(18);

        pAvailableAmount = getBaseDao().getAvailable(pDenom);
        pAllAmount = WUtil.getEstimatedTotalDebt(getContext(), getOwenCdp(), getCParam());

        BigDecimal debtFloor = new BigDecimal(getCdpParam().getDebtParam().getDebtFloor());
        BigDecimal rawDebtAmount =  new BigDecimal(getOwenCdp().getPrincipal().getAmount());

        pMaxAmount = rawDebtAmount.subtract(debtFloor);
        pMinAmount = BigDecimal.ONE;

        if (pAllAmount.compareTo(pAvailableAmount) > 0) {
            // now disable to repay all
            pAllAmount = BigDecimal.ZERO;
        }
        if (rawDebtAmount.compareTo(debtFloor) < 0) {
            // now disbale to partically repay
            pMaxAmount = BigDecimal.ZERO;
            pMinAmount = BigDecimal.ZERO;
        } else {
            if (pMaxAmount.compareTo(pAvailableAmount) > 0) {
                pMaxAmount = pAvailableAmount;
            }
        }

        if (pAllAmount.compareTo(BigDecimal.ZERO) > 0) {
            WDp.showCoinDp(getContext(), getBaseDao(), pDenom, pAllAmount.toPlainString(), mAllDenom, mAllAmountTx, getSActivity().mBaseChain);
            mAllLayer.setVisibility(View.VISIBLE);
            mDisableAllTx.setVisibility(View.GONE);
        } else {
            mAllLayer.setVisibility(View.GONE);
            mDisableAllTx.setVisibility(View.VISIBLE);
        }

        if (pMaxAmount.compareTo(BigDecimal.ZERO) > 0 && pMinAmount.compareTo(BigDecimal.ZERO) > 0) {
            WDp.showCoinDp(getContext(), getBaseDao(), pDenom, pMinAmount.toPlainString(), mParticalDenom, mParticalMinAmountTx, getSActivity().mBaseChain);
            WDp.showCoinDp(getContext(), getBaseDao(), pDenom, pMaxAmount.toPlainString(), mParticalDenom, mParticalMaxAmountTx, getSActivity().mBaseChain);
            mParticalLayer.setVisibility(View.VISIBLE);
            mDisableParticalTx.setVisibility(View.GONE);
        } else {
            mParticalLayer.setVisibility(View.GONE);
            mDisableParticalTx.setVisibility(View.VISIBLE);

        }

        mPrincipalSymbol.setText(WUtil.getKavaTokenName(pDenom));
        try {
            Picasso.get().load(KAVA_COIN_IMG_URL + pDenom + ".png").fit().into(mPrincipalImg);
        } catch (Exception e) { }


        //before(current) state views!!
        mCurrentCollateralAmount = new BigDecimal(getOwenCdp().getCollateral().getAmount());
        mCurrentTotalDebetAmount = new BigDecimal(getOwenCdp().getPrincipal().getAmount()).add(new BigDecimal(getOwenCdp().getAccumulatedFees().getAmount()));
        BigDecimal hiddenFeeValue = WDp.getCdpGrpcHiddenFee(getContext(), mCurrentTotalDebetAmount, getCParam(), getOwenCdp());
        mCurrentTotalDebetAmount = mCurrentTotalDebetAmount.add(hiddenFeeValue);

        mBeforeLiquidationPrice = mCurrentTotalDebetAmount.movePointLeft(WUtil.getKavaCoinDecimal(pDenom) - WUtil.getKavaCoinDecimal(cDenom)).multiply(new BigDecimal(getCParam().getLiquidationRatio()).movePointLeft(18)).divide(mCurrentCollateralAmount, WUtil.getKavaCoinDecimal(cDenom), RoundingMode.DOWN);
        mBeforeRiskRate = new BigDecimal(100).subtract((mCurrentPrice.subtract(mBeforeLiquidationPrice)).movePointRight(2).divide(mCurrentPrice, 2, RoundingMode.DOWN));
        WDp.DpRiskRate3(getContext(), mBeforeRiskRate, mBeforeRiskScore, mBeforeRisk);
        mBeforePrincipalAmount.setText(WDp.getDpAmount2(getContext(), mCurrentTotalDebetAmount, WUtil.getKavaCoinDecimal(pDenom), WUtil.getKavaCoinDecimal(pDenom)));

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
                    mPrincipalInput.setSelection(WUtil.getKavaCoinDecimal(pDenom) + 1);
                } else {
                    try {
                        final BigDecimal inputAmount = new BigDecimal(es);
                        if (BigDecimal.ZERO.compareTo(inputAmount) >= 0 ){
                            mPrincipalInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                            return;
                        }

                        BigDecimal checkPosition = inputAmount.movePointRight(WUtil.getKavaCoinDecimal(pDenom));
                        BigDecimal checkMax = checkPosition.setScale(0, RoundingMode.DOWN);
                        if (checkPosition.compareTo(checkMax) != 0 || !checkPosition.equals(checkMax)) {
                            String recover = es.substring(0, es.length() - 1);
                            mPrincipalInput.setText(recover);
                            mPrincipalInput.setSelection(recover.length());
                            return;
                        }
                        if ((checkPosition.compareTo(pMinAmount)< 0 || checkPosition.compareTo(pMaxAmount) > 0) &&
                                !checkPosition.equals(pAllAmount)) {
                            mPrincipalInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                        }
                        mPrincipalInput.setSelection(mPrincipalInput.getText().length());

                    } catch (Exception e) { }
                }
                onUpdateNextBtn();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mPrincipalClear)) {
            mPrincipalInput.setText("");

        } else if (v.equals(mBtn1_3)) {
            if (pMaxAmount.compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal cal = pMaxAmount.divide(new BigDecimal(3), 0, RoundingMode.DOWN);
                if (cal.compareTo(pMinAmount) < 0) {
                    cal = pMinAmount;
                    Toast.makeText(getContext(), R.string.error_less_than_min_principal, Toast.LENGTH_SHORT).show();
                }
                mPrincipalInput.setText(cal.movePointLeft(WUtil.getKavaCoinDecimal(pDenom)).toPlainString());
            } else {
                Toast.makeText(getContext(), R.string.str_cannot_particaly, Toast.LENGTH_SHORT).show();
            }

        } else if (v.equals(mBtn2_3)) {
            if (pMaxAmount.compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal cal = pMaxAmount.multiply(new BigDecimal("2")).divide(new BigDecimal(3), 0, RoundingMode.DOWN);
                if (cal.compareTo(pMinAmount) < 0) {
                    cal = pMinAmount;
                    Toast.makeText(getContext(), R.string.error_less_than_min_principal, Toast.LENGTH_SHORT).show();
                }
                mPrincipalInput.setText(cal.movePointLeft(WUtil.getKavaCoinDecimal(pDenom)).toPlainString());
            } else {
                Toast.makeText(getContext(), R.string.str_cannot_particaly, Toast.LENGTH_SHORT).show();
            }

        } else if (v.equals(mBtnMax)) {
            if (pMaxAmount.compareTo(BigDecimal.ZERO) > 0) {
                mPrincipalInput.setText(pMaxAmount.movePointLeft(WUtil.getKavaCoinDecimal(pDenom)).toPlainString());
            } else {
                Toast.makeText(getContext(), R.string.str_cannot_particaly, Toast.LENGTH_SHORT).show();
            }

        } else if (v.equals(mBtnAll)) {
            if (pAllAmount.compareTo(BigDecimal.ZERO) > 0) {
                mPrincipalInput.setText(pAllAmount.movePointLeft(WUtil.getKavaCoinDecimal(pDenom)).toPlainString());
            } else {
                Toast.makeText(getContext(), R.string.str_cannot_repay_all, Toast.LENGTH_SHORT).show();
            }

        } else if (v.equals(mBtnCancel)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mBtnNext)) {
            if (onValidateAmount()) {
                Coin payment = new Coin(pDenom, mToPaymentAmount.toPlainString());
                getSActivity().mPayment = payment;
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
                bundle.putString("currentPrice", getPrice().getPrice());
                bundle.putString("denom", cDenom);
                Dialog_Safe_Score_Confirm dialog = Dialog_Safe_Score_Confirm.newInstance(bundle);
                dialog.setCancelable(true);
                dialog.setTargetFragment(this, CDP_REPAY_CONFIRM_DIALOG);
                dialog.show(getFragmentManager().beginTransaction(), "dialog");
            } else {
                Toast.makeText(getContext(), R.string.error_invalid_amount, Toast.LENGTH_SHORT).show();
            }
        }

    }

    private boolean onValidateAmount() {
        mAfterLiquidationPrice = mAfterRiskRate = mRemainLoanAmount = null;
        try {
            mToPaymentAmount = BigDecimal.ZERO;
            String userInput = mPrincipalInput.getText().toString().trim();
            if (TextUtils.isEmpty(userInput)) return false;
            try {
                mToPaymentAmount = new BigDecimal(userInput).movePointRight(WUtil.getKavaCoinDecimal(pDenom));
            } catch (Exception e) {
                return false;
            }
            if (mToPaymentAmount.equals(BigDecimal.ZERO)) return false;
            if ((mToPaymentAmount.compareTo(pMinAmount)< 0 || mToPaymentAmount.compareTo(pMaxAmount) > 0) &&
                    !mToPaymentAmount.equals(pAllAmount)) {
                return false;
            }

//            //after payment state views!!
            mRemainLoanAmount = mCurrentTotalDebetAmount.subtract(mToPaymentAmount);

            mAfterLiquidationPrice = mRemainLoanAmount.movePointLeft(WUtil.getKavaCoinDecimal(pDenom) - WUtil.getKavaCoinDecimal(cDenom)).multiply(new BigDecimal(getCParam().getLiquidationRatio()).movePointLeft(18)).divide(mCurrentCollateralAmount, WUtil.getKavaCoinDecimal(cDenom), RoundingMode.DOWN);
            mAfterRiskRate = new BigDecimal(100).subtract((mCurrentPrice.subtract(mAfterLiquidationPrice)).movePointRight(2).divide(mCurrentPrice, 2, RoundingMode.DOWN));
            return true;

        } catch (Exception e) {
            mBtnNext.setText(R.string.str_next);
            mBtnNext.setTextColor(getResources().getColor(R.color.color_btn_photon));
            mBtnNext.setBackground(getResources().getDrawable(R.drawable.btn_trans_with_border));
            mBtnNext.setTypeface(null, Typeface.NORMAL);
            return false;
        }
    }

    private void onUpdateNextBtn() {
        if (!onValidateAmount()) {
            mBtnNext.setText(R.string.str_next);
            mBtnNext.setTextColor(getResources().getColor(R.color.color_btn_photon));
            mBtnNext.setBackground(getResources().getDrawable(R.drawable.btn_trans_with_border));
            mBtnNext.setTypeface(null, Typeface.NORMAL);
            mAfterRiskLayer.setVisibility(View.INVISIBLE);

        } else {
            WDp.DpRiskRate3(getContext(), mAfterRiskRate, mAfterRiskScore, mAfterRisk);
            WDp.DpRiskButton2(getContext(), mAfterRiskRate, mBtnNext);
            mAfterRiskLayer.setVisibility(View.VISIBLE);
            if (mRemainLoanAmount.equals(BigDecimal.ZERO)) {
                mBtnNext.setText("Repay All");
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

    private Genesis.Params getCdpParam() {
        return getSActivity().mCdpParams;
    }

    private Genesis.CollateralParam getCParam() {
        return getSActivity().mCollateralParam;
    }

    private QueryOuterClass.CDPResponse getOwenCdp() {
        return getSActivity().mMyCdp;
    }

    private kava.pricefeed.v1beta1.QueryOuterClass.CurrentPriceResponse getPrice() {
        return getSActivity().mKavaTokenPrice;
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
