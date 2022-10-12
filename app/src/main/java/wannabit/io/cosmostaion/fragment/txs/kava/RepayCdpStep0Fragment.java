package wannabit.io.cosmostaion.fragment.txs.kava;

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
import androidx.core.content.ContextCompat;

import java.math.BigDecimal;
import java.math.RoundingMode;

import kava.cdp.v1beta1.Genesis;
import kava.cdp.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.kava.RepayCdpActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.SafeScoreConfirmDialog;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class RepayCdpStep0Fragment extends BaseFragment implements View.OnClickListener {
    public final static int CDP_REPAY_CONFIRM_DIALOG = 6018;

    private Button mBtnCancel, mBtnNext;

    private ImageView mPrincipalImg;
    private TextView mPrincipalSymbol;
    private EditText mPrincipalInput;
    private ImageView mPrincipalClear;


    private LinearLayout mParticalLayer;
    private TextView mParticalMaxAmountTx, mParticalMinAmountTx, mParticalDenom;
    private TextView mDisableParticalTx;

    private LinearLayout mAllLayer;
    private TextView mAllAmountTx, mAllDenom;
    private TextView mDisableAllTx;
    private Button mBtn1_3, mBtn2_3, mBtnMax, mBtnAll;

    private LinearLayout mAfterRiskLayer;
    private TextView mBeforeRisk, mAfterRisk, mBeforeRiskScore, mAfterRiskScore;
    private TextView mBeforePrincipalAmount;

    private BigDecimal mCurrentPrice;
    private String cDenom, pDenom;
    private BigDecimal mCurrentTotalDebetAmount, mCurrentCollateralAmount;
    private BigDecimal pMinAmount, pMaxAmount, pAllAmount, pAvailableAmount;
    private BigDecimal mBeforeLiquidationPrice, mBeforeRiskRate, mAfterLiquidationPrice, mAfterRiskRate, mRemainLoanAmount, mToPaymentAmount;

    private String mPrincipalChecker, mPrincipalSetter;
    private int mCDecimal, mPDecimal;

    public static RepayCdpStep0Fragment newInstance() {
        return new RepayCdpStep0Fragment();
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
        mBeforeLiquidationPrice = mBeforeRiskRate = mAfterLiquidationPrice = mAfterRiskRate = mRemainLoanAmount = BigDecimal.ZERO;

        cDenom = getCParam().getDenom();
        pDenom = getCParam().getDebtLimit().getDenom();
        mCDecimal = WDp.getDenomDecimal(getBaseDao(), getSActivity().mChainConfig, cDenom);
        mPDecimal = WDp.getDenomDecimal(getBaseDao(), getSActivity().mChainConfig, pDenom);
        setDpDecimals(mPDecimal);
        mCurrentPrice = getSActivity().getKavaOraclePrice();

        pAvailableAmount = getBaseDao().getAvailable(pDenom);
        pAllAmount = WUtil.getEstimatedTotalDebt(getContext(), getOwenCdp(), getCParam());

        BigDecimal debtFloor = new BigDecimal(getCdpParam().getDebtParam().getDebtFloor());
        BigDecimal rawDebtAmount = new BigDecimal(getOwenCdp().getPrincipal().getAmount());

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
            WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, pDenom, pAllAmount.toPlainString(), mAllDenom, mAllAmountTx);
            mAllLayer.setVisibility(View.VISIBLE);
            mDisableAllTx.setVisibility(View.GONE);
        } else {
            mAllLayer.setVisibility(View.GONE);
            mDisableAllTx.setVisibility(View.VISIBLE);
        }

        if (pMaxAmount.compareTo(BigDecimal.ZERO) > 0 && pMinAmount.compareTo(BigDecimal.ZERO) > 0) {
            WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, pDenom, pMinAmount.toPlainString(), mParticalDenom, mParticalMinAmountTx);
            WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, pDenom, pMaxAmount.toPlainString(), mParticalDenom, mParticalMaxAmountTx);
            mParticalLayer.setVisibility(View.VISIBLE);
            mDisableParticalTx.setVisibility(View.GONE);
        } else {
            mParticalLayer.setVisibility(View.GONE);
            mDisableParticalTx.setVisibility(View.VISIBLE);

        }

        WDp.setDpSymbol(getSActivity(), getBaseDao(), getSActivity().mChainConfig, pDenom, mPrincipalSymbol);
        WDp.setDpSymbolImg(getBaseDao(), getSActivity().mChainConfig, pDenom, mPrincipalImg);

        //before(current) state views!!
        mCurrentCollateralAmount = new BigDecimal(getOwenCdp().getCollateral().getAmount());
        mCurrentTotalDebetAmount = new BigDecimal(getOwenCdp().getPrincipal().getAmount()).add(new BigDecimal(getOwenCdp().getAccumulatedFees().getAmount()));
        BigDecimal hiddenFeeValue = WUtil.getCdpGrpcHiddenFee(getContext(), mCurrentTotalDebetAmount, getCParam(), getOwenCdp());
        mCurrentTotalDebetAmount = mCurrentTotalDebetAmount.add(hiddenFeeValue);

        mBeforeLiquidationPrice = mCurrentTotalDebetAmount.movePointLeft(mPDecimal - mCDecimal).multiply(new BigDecimal(getCParam().getLiquidationRatio()).movePointLeft(18)).divide(mCurrentCollateralAmount, mCDecimal, RoundingMode.DOWN);
        mBeforeRiskRate = new BigDecimal(100).subtract((mCurrentPrice.subtract(mBeforeLiquidationPrice)).movePointRight(2).divide(mCurrentPrice, 2, RoundingMode.DOWN));
        WUtil.DpRiskRate3(getContext(), mBeforeRiskRate, mBeforeRiskScore, mBeforeRisk);
        mBeforePrincipalAmount.setText(WDp.getDpAmount2(getContext(), mCurrentTotalDebetAmount, mPDecimal, mPDecimal));

        mPrincipalInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable et) {
                String es = et.toString().trim();
                if (TextUtils.isEmpty(es)) {
                    mPrincipalInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                } else if (es.startsWith(".")) {
                    mPrincipalInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                    mPrincipalInput.setText("");
                } else if (es.endsWith(".")) {
                    mPrincipalInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                } else if (mPrincipalInput.length() > 1 && es.startsWith("0") && !es.startsWith("0.")) {
                    mPrincipalInput.setText("0");
                    mPrincipalInput.setSelection(1);
                }

                if (es.equals(mPrincipalChecker)) {
                    mPrincipalInput.setText(mPrincipalSetter);
                    mPrincipalInput.setSelection(mPDecimal + 1);
                } else {
                    try {
                        final BigDecimal inputAmount = new BigDecimal(es);
                        if (BigDecimal.ZERO.compareTo(inputAmount) >= 0) {
                            mPrincipalInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                            return;
                        }

                        BigDecimal checkPosition = inputAmount.movePointRight(mPDecimal);
                        BigDecimal checkMax = checkPosition.setScale(0, RoundingMode.DOWN);
                        if (checkPosition.compareTo(checkMax) != 0 || !checkPosition.equals(checkMax)) {
                            String recover = es.substring(0, es.length() - 1);
                            mPrincipalInput.setText(recover);
                            mPrincipalInput.setSelection(recover.length());
                            return;
                        }
                        if ((checkPosition.compareTo(pMinAmount) < 0 || checkPosition.compareTo(pMaxAmount) > 0) &&
                                !checkPosition.equals(pAllAmount)) {
                            mPrincipalInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                        }
                        mPrincipalInput.setSelection(mPrincipalInput.getText().length());

                    } catch (Exception e) {
                    }
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
                mPrincipalInput.setText(cal.movePointLeft(mPDecimal).toPlainString());
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
                mPrincipalInput.setText(cal.movePointLeft(mPDecimal).toPlainString());
            } else {
                Toast.makeText(getContext(), R.string.str_cannot_particaly, Toast.LENGTH_SHORT).show();
            }

        } else if (v.equals(mBtnMax)) {
            if (pMaxAmount.compareTo(BigDecimal.ZERO) > 0) {
                mPrincipalInput.setText(pMaxAmount.movePointLeft(mPDecimal).toPlainString());
            } else {
                Toast.makeText(getContext(), R.string.str_cannot_particaly, Toast.LENGTH_SHORT).show();
            }

        } else if (v.equals(mBtnAll)) {
            if (pAllAmount.compareTo(BigDecimal.ZERO) > 0) {
                mPrincipalInput.setText(pAllAmount.movePointLeft(mPDecimal).toPlainString());
            } else {
                Toast.makeText(getContext(), R.string.str_cannot_repay_all, Toast.LENGTH_SHORT).show();
            }

        } else if (v.equals(mBtnCancel)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mBtnNext)) {
            if (onValidateAmount() && !getSActivity().isFinishing()) {
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
                bundle.putString("currentPrice", mCurrentPrice.toPlainString());
                bundle.putString("denom", cDenom);
                SafeScoreConfirmDialog dialog = SafeScoreConfirmDialog.newInstance(bundle);
                dialog.setTargetFragment(this, CDP_REPAY_CONFIRM_DIALOG);
                dialog.show(getSActivity().getSupportFragmentManager(), "dialog");

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
                mToPaymentAmount = new BigDecimal(userInput).movePointRight(mPDecimal);
            } catch (Exception e) {
                return false;
            }
            if (mToPaymentAmount.equals(BigDecimal.ZERO)) return false;
            if ((mToPaymentAmount.compareTo(pMinAmount) < 0 || mToPaymentAmount.compareTo(pMaxAmount) > 0) &&
                    !mToPaymentAmount.equals(pAllAmount)) {
                return false;
            }

//            //after payment state views!!
            mRemainLoanAmount = mCurrentTotalDebetAmount.subtract(mToPaymentAmount);

            mAfterLiquidationPrice = mRemainLoanAmount.movePointLeft(mPDecimal - mCDecimal).multiply(new BigDecimal(getCParam().getLiquidationRatio()).movePointLeft(18)).divide(mCurrentCollateralAmount, mCDecimal, RoundingMode.DOWN);
            mAfterRiskRate = new BigDecimal(100).subtract((mCurrentPrice.subtract(mAfterLiquidationPrice)).movePointRight(2).divide(mCurrentPrice, 2, RoundingMode.DOWN));
            return true;

        } catch (Exception e) {
            mBtnNext.setText(R.string.str_next);
            mBtnNext.setTextColor(ContextCompat.getColor(getSActivity(), R.color.colorPhoton));
            mBtnNext.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.btn_trans_with_border));
            mBtnNext.setTypeface(null, Typeface.NORMAL);
            return false;
        }
    }

    private void onUpdateNextBtn() {
        if (!onValidateAmount()) {
            mBtnNext.setText(R.string.str_next);
            mBtnNext.setTextColor(ContextCompat.getColor(getSActivity(), R.color.colorPhoton));
            mBtnNext.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.btn_trans_with_border));
            mBtnNext.setTypeface(null, Typeface.NORMAL);
            mAfterRiskLayer.setVisibility(View.INVISIBLE);

        } else {
            WUtil.DpRiskRate3(getContext(), mAfterRiskRate, mAfterRiskScore, mAfterRisk);
            WUtil.DpRiskButton2(getContext(), mAfterRiskRate, mBtnNext);
            mAfterRiskLayer.setVisibility(View.VISIBLE);
            if (mRemainLoanAmount.equals(BigDecimal.ZERO)) {
                mBtnNext.setText("Repay All");
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CDP_REPAY_CONFIRM_DIALOG && resultCode == Activity.RESULT_OK) {
            getSActivity().onNextStep();
        }
    }

    private RepayCdpActivity getSActivity() {
        return (RepayCdpActivity) getBaseActivity();
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

    private void setDpDecimals(int pDeciaml) {
        mPrincipalChecker = "0.";
        mPrincipalSetter = "0.";
        for (int i = 0; i < pDeciaml; i++) {
            mPrincipalChecker = mPrincipalChecker + "0";
        }
        for (int i = 0; i < pDeciaml - 1; i++) {
            mPrincipalSetter = mPrincipalSetter + "0";
        }
    }


}
