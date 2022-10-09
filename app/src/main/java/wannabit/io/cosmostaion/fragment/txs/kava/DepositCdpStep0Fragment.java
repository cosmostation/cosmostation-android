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
import wannabit.io.cosmostaion.activities.txs.kava.DepositCdpActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.SafeScoreConfirmDialog;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class DepositCdpStep0Fragment extends BaseFragment implements View.OnClickListener {
    public final static int CDP_DEPOSIT_CONFIRM_DIALOG = 6020;

    private Button mBtnCancel, mBtnNext;
    private ImageView mCollateralImg;
    private TextView mCollateralSymbol;
    private EditText mCollateralInput;
    private ImageView mCollateralClear;
    private TextView mCollateralMaxTx, mCollateralDenomTx;
    private Button mBtnAdd1, mBtnAdd1_4, mBtnAddHalf, mBtnAdd3_4, mBtnAddMax;

    private LinearLayout mAfterRiskLayer;
    private TextView mBeforeRisk, mAfterRisk, mBeforeRiskScore, mAfterRiskScore;
    private TextView mBeforeDepositAmount, mAfterDepositAmount;

    private BigDecimal mCurrentPrice;
    private String mCollateralDenom, mPrincipalDenom;
    private BigDecimal mCurrentTotalDebetAmount, mCurrentCollateralAmount, mCanDepositMaxMaxAmount, mToDepositAmount, mTotalDepositAmount;
    private BigDecimal mBeforeLiquidationPrice, mBeforeRiskRate, mAfterLiquidationPrice, mAfterRiskRate;

    private String mCollateralDecimalChecker, mCollateralDecimalSetter;
    private int mCDecimal, mPDecimal;

    public static DepositCdpStep0Fragment newInstance() {
        return new DepositCdpStep0Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_deposit_cdp_step0, container, false);
        mBtnCancel = rootView.findViewById(R.id.btn_cancel);
        mBtnNext = rootView.findViewById(R.id.btn_next);

        mCollateralImg = rootView.findViewById(R.id.collateral_icon);
        mCollateralSymbol = rootView.findViewById(R.id.collateral_symbol);
        mCollateralInput = rootView.findViewById(R.id.collateral_input);
        mCollateralClear = rootView.findViewById(R.id.collateral_clear);
        mCollateralMaxTx = rootView.findViewById(R.id.collateral_max_amount);
        mCollateralDenomTx = rootView.findViewById(R.id.collateral_denom);
        mBtnAdd1 = rootView.findViewById(R.id.btn_add_1);
        mBtnAdd1_4 = rootView.findViewById(R.id.btn_add_1_4);
        mBtnAddHalf = rootView.findViewById(R.id.btn_add_half);
        mBtnAdd3_4 = rootView.findViewById(R.id.btn_add_3_4);
        mBtnAddMax = rootView.findViewById(R.id.btn_add_max);

        mBeforeRisk = rootView.findViewById(R.id.risk_rate_before);
        mBeforeRiskScore = rootView.findViewById(R.id.risk_score_before);
        mAfterRiskLayer = rootView.findViewById(R.id.risk_rate_after_layer);
        mAfterRisk = rootView.findViewById(R.id.risk_rate_after);
        mAfterRiskScore = rootView.findViewById(R.id.risk_score_after);
        mBeforeDepositAmount = rootView.findViewById(R.id.deposit_before);
        mAfterDepositAmount = rootView.findViewById(R.id.deposit_after);

        mCollateralClear.setOnClickListener(this);
        mBtnAdd1.setOnClickListener(this);
        mBtnAdd1_4.setOnClickListener(this);
        mBtnAddHalf.setOnClickListener(this);
        mBtnAdd3_4.setOnClickListener(this);
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
        mCollateralDenom = getCParam().getDenom();
        mPrincipalDenom = getCParam().getDebtLimit().getDenom();
        mCDecimal = WDp.getDenomDecimal(getBaseDao(), getSActivity().mChainConfig, mCollateralDenom);
        mPDecimal = WDp.getDenomDecimal(getBaseDao(), getSActivity().mChainConfig, mPrincipalDenom);
        setDpDecimals(mCDecimal);
        mCurrentPrice = getSActivity().getKavaOraclePrice();

        mCanDepositMaxMaxAmount = getBaseDao().getAvailable(mCollateralDenom);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, mCollateralDenom, mCanDepositMaxMaxAmount.toPlainString(), mCollateralDenomTx, mCollateralMaxTx);

        mCurrentTotalDebetAmount = new BigDecimal(getOwenCdp().getPrincipal().getAmount()).add(new BigDecimal(getOwenCdp().getAccumulatedFees().getAmount()));
        BigDecimal hiddenFeeValue = WUtil.getCdpGrpcHiddenFee(getContext(), mCurrentTotalDebetAmount, getCParam(), getOwenCdp());
        mCurrentTotalDebetAmount = mCurrentTotalDebetAmount.add(hiddenFeeValue);

        mCurrentCollateralAmount = new BigDecimal(getOwenCdp().getCollateral().getAmount());
        WDp.setDpSymbol(getSActivity(), getBaseDao(), getSActivity().mChainConfig, mCollateralDenom, mCollateralSymbol);
        WDp.setDpSymbolImg(getBaseDao(), getSActivity().mChainConfig, mCollateralDenom, mCollateralImg);

        //before(current) state views!!
        mBeforeLiquidationPrice = mCurrentTotalDebetAmount.movePointLeft(mPDecimal - mCDecimal).multiply(new BigDecimal(getCParam().getLiquidationRatio()).movePointLeft(18)).divide(mCurrentCollateralAmount, mCDecimal, RoundingMode.DOWN);
        mBeforeRiskRate = new BigDecimal(100).subtract((mCurrentPrice.subtract(mBeforeLiquidationPrice)).movePointRight(2).divide(mCurrentPrice, 2, RoundingMode.DOWN));
        WUtil.DpRiskRate3(getContext(), mBeforeRiskRate, mBeforeRiskScore, mBeforeRisk);
        mBeforeDepositAmount.setText(WDp.getDpAmount2(getContext(), mCurrentCollateralAmount, mCDecimal, mCDecimal));

        mCollateralInput.addTextChangedListener(new TextWatcher() {
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
                    mCollateralInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                } else if (es.startsWith(".")) {
                    mCollateralInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                    mCollateralInput.setText("");
                } else if (es.endsWith(".")) {
                    mCollateralInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                    return;
                } else if (es.length() > 1 && es.startsWith("0") && !es.startsWith("0.")) {
                    mCollateralInput.setText("0");
                    mCollateralInput.setSelection(1);
                }

                if (es.equals(mCollateralDecimalChecker)) {
                    mCollateralInput.setText(mCollateralDecimalSetter);
                    mCollateralInput.setSelection(mCDecimal + 1);
                } else {
                    try {
                        final BigDecimal inputAmount = new BigDecimal(es);
                        if (BigDecimal.ZERO.compareTo(inputAmount) >= 0) {
                            mCollateralInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                            return;
                        }

                        BigDecimal checkPosition = inputAmount.movePointRight(mCDecimal);
                        BigDecimal checkMax = checkPosition.setScale(0, RoundingMode.DOWN);
                        if (checkPosition.compareTo(checkMax) != 0 || !checkPosition.equals(checkMax)) {
                            String recover = es.substring(0, es.length() - 1);
                            mCollateralInput.setText(recover);
                            mCollateralInput.setSelection(recover.length());
                            return;
                        }

                        if (mCanDepositMaxMaxAmount.compareTo(checkPosition) < 0) {
                            mCollateralInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));

                        } else {
                            mCollateralInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                        }
                        mCollateralInput.setSelection(mCollateralInput.getText().length());

                    } catch (Exception e) {
                    }
                }
                onDisplayViewUpdate();
            }
        });
    }

    private boolean onDisplayViewUpdate() {
        mAfterLiquidationPrice = mAfterRiskRate = null;

        try {
            mToDepositAmount = BigDecimal.ZERO;
            try {
                mToDepositAmount = new BigDecimal(mCollateralInput.getText().toString().trim()).movePointRight(mCDecimal);
            } catch (Exception e) {
                mAfterRiskLayer.setVisibility(View.INVISIBLE);
                mAfterDepositAmount.setVisibility(View.GONE);
                return false;
            }

            if (mToDepositAmount.compareTo(BigDecimal.ZERO) <= 0 || mCanDepositMaxMaxAmount.compareTo(mToDepositAmount) < 0) {
                mBtnNext.setText(R.string.str_next);
                mBtnNext.setTextColor(ContextCompat.getColor(getSActivity(), R.color.colorPhoton));
                mBtnNext.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.btn_trans_with_border));
                mBtnNext.setTypeface(null, Typeface.NORMAL);
                mAfterRiskLayer.setVisibility(View.INVISIBLE);
                mAfterDepositAmount.setVisibility(View.GONE);
                return false;

            }
            mAfterRiskLayer.setVisibility(View.VISIBLE);

            mTotalDepositAmount = mCurrentCollateralAmount.add(mToDepositAmount);

            mAfterLiquidationPrice = mCurrentTotalDebetAmount.movePointLeft(mPDecimal - mCDecimal).multiply(new BigDecimal(getCParam().getLiquidationRatio()).movePointLeft(18)).divide(mTotalDepositAmount, mCDecimal, RoundingMode.DOWN);

            mAfterRiskRate = new BigDecimal(100).subtract((mCurrentPrice.subtract(mAfterLiquidationPrice)).movePointRight(2).divide(mCurrentPrice, 2, RoundingMode.DOWN));

            WUtil.DpRiskRate3(getContext(), mAfterRiskRate, mAfterRiskScore, mAfterRisk);
            WUtil.DpRiskButton2(getContext(), mAfterRiskRate, mBtnNext);
            return true;

        } catch (Exception e) {
            mBtnNext.setText(R.string.str_next);
            mBtnNext.setTextColor(ContextCompat.getColor(getSActivity(), R.color.colorPhoton));
            mBtnNext.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.btn_trans_with_border));
            mBtnNext.setTypeface(null, Typeface.NORMAL);
            return false;
        }
    }


    @Override
    public void onClick(View v) {
        if (v.equals(mCollateralClear)) {
            mCollateralInput.setText("");

        } else if (v.equals(mBtnAdd1)) {
            BigDecimal inputedAmount = BigDecimal.ZERO;
            try {
                inputedAmount = new BigDecimal(mCollateralInput.getText().toString().trim());
            } catch (Exception e) {
            }
            inputedAmount = inputedAmount.add(new BigDecimal("1"));
            mCollateralInput.setText(inputedAmount.toPlainString());

        } else if (v.equals(mBtnAdd1_4)) {
            try {
                BigDecimal cal = mCanDepositMaxMaxAmount.divide(new BigDecimal(4), 0, RoundingMode.DOWN);
                mCollateralInput.setText(cal.movePointLeft(mCDecimal).toPlainString());

            } catch (Exception e) {
                mCollateralInput.setText("");
            }

        } else if (v.equals(mBtnAddHalf)) {
            try {
                BigDecimal cal = mCanDepositMaxMaxAmount.divide(new BigDecimal(2), 0, RoundingMode.DOWN);
                mCollateralInput.setText(cal.movePointLeft(mCDecimal).toPlainString());

            } catch (Exception e) {
                mCollateralInput.setText("");
            }

        } else if (v.equals(mBtnAdd3_4)) {
            try {
                BigDecimal cal = mCanDepositMaxMaxAmount.multiply(new BigDecimal(0.75)).setScale(0, RoundingMode.DOWN);
                mCollateralInput.setText(cal.movePointLeft(mCDecimal).toPlainString());

            } catch (Exception e) {
                mCollateralInput.setText("");
            }
        } else if (v.equals(mBtnAddMax)) {
            mCollateralInput.setText(mCanDepositMaxMaxAmount.movePointLeft(mCDecimal).toPlainString());

        } else if (v.equals(mBtnCancel)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mBtnNext)) {
            if (onDisplayViewUpdate() && !getSActivity().isFinishing()) {
                if (mBeforeLiquidationPrice.compareTo(BigDecimal.ZERO) <= 0 || mBeforeRiskRate.compareTo(BigDecimal.ZERO) < 0 || mToDepositAmount.compareTo(BigDecimal.ZERO) <= 0 ||
                        mAfterLiquidationPrice == null || mAfterRiskRate == null) {
                    Toast.makeText(getContext(), R.string.error_invalid_amount, Toast.LENGTH_SHORT).show();
                } else {
                    Coin collateral = new Coin(mCollateralDenom, mToDepositAmount.toPlainString());
                    getSActivity().mCollateral = collateral;
                    getSActivity().mBeforeLiquidationPrice = mBeforeLiquidationPrice;
                    getSActivity().mBeforeRiskRate = mBeforeRiskRate;
                    getSActivity().mAfterLiquidationPrice = mAfterLiquidationPrice;
                    getSActivity().mAfterRiskRate = mAfterRiskRate;
                    getSActivity().mTotalDepositAmount = mTotalDepositAmount;

                    Bundle bundle = new Bundle();
                    bundle.putString("beforeRiskRate", mBeforeRiskRate.toPlainString());
                    bundle.putString("afterRiskRate", mAfterRiskRate.toPlainString());
                    bundle.putString("beforeLiquidationPrice", mBeforeLiquidationPrice.toPlainString());
                    bundle.putString("afterLiquidationPrice", mAfterLiquidationPrice.toPlainString());
                    bundle.putString("currentPrice", mCurrentPrice.toPlainString());
                    bundle.putString("denom", mCollateralDenom);
                    SafeScoreConfirmDialog dialog = SafeScoreConfirmDialog.newInstance(bundle);
                    dialog.setTargetFragment(this, CDP_DEPOSIT_CONFIRM_DIALOG);
                    dialog.show(getSActivity().getSupportFragmentManager(), "dialog");

                }
            } else {
                Toast.makeText(getContext(), R.string.error_invalid_amount, Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CDP_DEPOSIT_CONFIRM_DIALOG && resultCode == Activity.RESULT_OK) {
            getSActivity().onNextStep();
        }
    }

    private DepositCdpActivity getSActivity() {
        return (DepositCdpActivity) getBaseActivity();
    }

    private Genesis.CollateralParam getCParam() {
        return getSActivity().mCollateralParam;
    }

    private QueryOuterClass.CDPResponse getOwenCdp() {
        return getSActivity().mMyCdp;
    }

    private void setDpDecimals(int deciaml) {
        mCollateralDecimalChecker = "0.";
        mCollateralDecimalSetter = "0.";
        for (int i = 0; i < deciaml; i++) {
            mCollateralDecimalChecker = mCollateralDecimalChecker + "0";
        }
        for (int i = 0; i < deciaml - 1; i++) {
            mCollateralDecimalSetter = mCollateralDecimalSetter + "0";
        }
    }
}
