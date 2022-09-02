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
import wannabit.io.cosmostaion.activities.txs.kava.BorrowCdpActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.SafeScoreConfirmDialog;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class DrawDebtCdpStep0Fragment extends BaseFragment implements View.OnClickListener {
    public final static int CDP_DRAW_DEBT_CONFIRM_DIALOG = 6019;

    private Button mBtnCancel, mBtnNext;

    private ImageView mPrincipalImg;
    private TextView mPrincipalSymbol;
    private EditText mPrincipalInput;
    private ImageView mPrincipalClear;
    private TextView mLoanableMinTx, mLoanableMaxTx, mLoanableDenomTx;
    private Button mBtnMin, mBtn1_4, mBtnHalf, mBtn3_4, mBtnMax;

    private LinearLayout mAfterRiskLayer;
    private TextView mBeforeRisk, mAfterRisk, mBeforeRiskScore, mAfterRiskScore;
    private TextView mBeforePrincipalAmount, mAfterPrincipalAmount;

    private BigDecimal mCurrentPrice;
    private String mCollateralDenom, mPrincipalDenom;
    private BigDecimal mCurrentTotalDebetAmount, mCurrentCollateralAmount, mToLoanAmount, mMoreAddedLoanAmount;
    private BigDecimal mMinLoanableAmount, mMaxLoanableAmount;
    private BigDecimal mBeforeLiquidationPrice, mBeforeRiskRate, mAfterLiquidationPrice, mAfterRiskRate;

    private String mPrincipalChecker, mPrincipalSetter;
    private int mCDecimal, mPDecimal;


    public static DrawDebtCdpStep0Fragment newInstance(Bundle bundle) {
        DrawDebtCdpStep0Fragment fragment = new DrawDebtCdpStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_drawdebt_cdp_step0, container, false);
        mBtnCancel = rootView.findViewById(R.id.btn_cancel);
        mBtnNext = rootView.findViewById(R.id.btn_next);

        mPrincipalImg = rootView.findViewById(R.id.principal_icon);
        mPrincipalSymbol = rootView.findViewById(R.id.principal_symbol);
        mPrincipalInput = rootView.findViewById(R.id.principal_input);
        mPrincipalClear = rootView.findViewById(R.id.principal_clear);
        mLoanableMinTx = rootView.findViewById(R.id.loanable_min_amount);
        mLoanableMaxTx = rootView.findViewById(R.id.loanable_max_amount);
        mLoanableDenomTx = rootView.findViewById(R.id.loanable_denom);
        mBtnMin = rootView.findViewById(R.id.btn_add_01);
        mBtn1_4 = rootView.findViewById(R.id.btn_add_1);
        mBtnHalf = rootView.findViewById(R.id.btn_add_10);
        mBtn3_4 = rootView.findViewById(R.id.btn_add_half);
        mBtnMax = rootView.findViewById(R.id.btn_add_all);

        mBeforeRisk = rootView.findViewById(R.id.risk_rate_before);
        mBeforeRiskScore = rootView.findViewById(R.id.risk_score_before);
        mAfterRiskLayer = rootView.findViewById(R.id.risk_rate_after_layer);
        mAfterRisk = rootView.findViewById(R.id.risk_rate_after);
        mAfterRiskScore = rootView.findViewById(R.id.risk_score_after);
        mBeforePrincipalAmount = rootView.findViewById(R.id.loaned_amount_before);
        mAfterPrincipalAmount = rootView.findViewById(R.id.loaned_amount_after);

        mPrincipalClear.setOnClickListener(this);
        mBtnMin.setOnClickListener(this);
        mBtn1_4.setOnClickListener(this);
        mBtnHalf.setOnClickListener(this);
        mBtn3_4.setOnClickListener(this);
        mBtnMax.setOnClickListener(this);
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
        setDpDecimals(mPDecimal);
        mCurrentPrice = getSActivity().getKavaOraclePrice();

        BigDecimal currentPAmount = new BigDecimal(getOwenCdp().getPrincipal().getAmount());
        BigDecimal debtFloor = new BigDecimal(getCDPParam().getDebtParam().getDebtFloor());

        if (currentPAmount.compareTo(debtFloor) > 0) {
            mMinLoanableAmount = BigDecimal.ONE;
        } else {
            mMinLoanableAmount = debtFloor.subtract(currentPAmount);
        }

        mMaxLoanableAmount = WUtil.getMoreLoanableAmount(getContext(), getOwenCdp(), getCParam());
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, mPrincipalDenom, mMinLoanableAmount.toPlainString(), mLoanableDenomTx, mLoanableMinTx);
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, mPrincipalDenom, mMaxLoanableAmount.toPlainString(), mLoanableDenomTx, mLoanableMaxTx);

        WDp.setDpSymbol(getSActivity(), getBaseDao(), getSActivity().mChainConfig, mPrincipalDenom, mPrincipalSymbol);
        WDp.setDpSymbolImg(getBaseDao(), getSActivity().mChainConfig, mPrincipalDenom, mPrincipalImg);

        mCurrentTotalDebetAmount = WUtil.getEstimatedTotalDebt(getContext(), getOwenCdp(), getCParam());
        mCurrentCollateralAmount = new BigDecimal(getOwenCdp().getCollateral().getAmount());
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
                        if (mMinLoanableAmount.compareTo(checkPosition) > 0) {
                            mPrincipalInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));

                        } else if (mMaxLoanableAmount.compareTo(checkPosition) < 0) {
                            mPrincipalInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));

                        } else {
                            mPrincipalInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                        }
                        mPrincipalInput.setSelection(mPrincipalInput.getText().length());

                    } catch (Exception e) {
                    }
                }
                onDisplayViewUpdate();
            }
        });

    }

    private boolean onDisplayViewUpdate() {
        mAfterLiquidationPrice = mAfterRiskRate = mToLoanAmount = null;
        try {
            mToLoanAmount = BigDecimal.ZERO;
            try {
                mToLoanAmount = new BigDecimal(mPrincipalInput.getText().toString().trim()).movePointRight(mPDecimal);
            } catch (Exception e) {
                mAfterRiskLayer.setVisibility(View.INVISIBLE);
                mAfterPrincipalAmount.setVisibility(View.GONE);
                return false;
            }
            if (mToLoanAmount.compareTo(mMinLoanableAmount) < 0 || mToLoanAmount.compareTo(mMaxLoanableAmount) > 0) {
                mBtnNext.setText(R.string.str_next);
                mBtnNext.setTextColor(ContextCompat.getColor(getSActivity(), R.color.colorPhoton));
                mBtnNext.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.btn_trans_with_border));
                mBtnNext.setTypeface(null, Typeface.NORMAL);
                mAfterRiskLayer.setVisibility(View.INVISIBLE);
                mAfterPrincipalAmount.setVisibility(View.GONE);
                return false;
            }
            mAfterRiskLayer.setVisibility(View.VISIBLE);

            mMoreAddedLoanAmount = mCurrentTotalDebetAmount.add(mToLoanAmount);

            mAfterLiquidationPrice = mMoreAddedLoanAmount.movePointLeft(mPDecimal - mCDecimal).multiply(new BigDecimal(getCParam().getLiquidationRatio()).movePointLeft(18)).divide(mCurrentCollateralAmount, mCDecimal, RoundingMode.DOWN);

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
        if (v.equals(mPrincipalClear)) {
            mPrincipalInput.setText("");

        } else if (v.equals(mBtnMin)) {
            mPrincipalInput.setText(mMinLoanableAmount.movePointLeft(mPDecimal).toPlainString());

        } else if (v.equals(mBtn1_4)) {
            BigDecimal cal = mMaxLoanableAmount.multiply(new BigDecimal("0.25"));
            if (mMinLoanableAmount.compareTo(cal) > 0) {
                cal = mMinLoanableAmount;
                Toast.makeText(getContext(), R.string.error_less_than_min_principal, Toast.LENGTH_SHORT).show();
            }
            cal = cal.movePointLeft(mPDecimal).setScale(mPDecimal, RoundingMode.DOWN);
            mPrincipalInput.setText(cal.toPlainString());

        } else if (v.equals(mBtnHalf)) {
            BigDecimal cal = mMaxLoanableAmount.multiply(new BigDecimal("0.5"));
            if (mMinLoanableAmount.compareTo(cal) > 0) {
                cal = mMinLoanableAmount;
                Toast.makeText(getContext(), R.string.error_less_than_min_principal, Toast.LENGTH_SHORT).show();
            }
            cal = cal.movePointLeft(mPDecimal).setScale(mPDecimal, RoundingMode.DOWN);
            mPrincipalInput.setText(cal.toPlainString());

        } else if (v.equals(mBtn3_4)) {
            BigDecimal cal = mMaxLoanableAmount.multiply(new BigDecimal("0.75"));
            if (mMinLoanableAmount.compareTo(cal) > 0) {
                cal = mMinLoanableAmount;
                Toast.makeText(getContext(), R.string.error_less_than_min_principal, Toast.LENGTH_SHORT).show();
            }
            cal = cal.movePointLeft(mPDecimal).setScale(mPDecimal, RoundingMode.DOWN);
            mPrincipalInput.setText(cal.toPlainString());

        } else if (v.equals(mBtnMax)) {
            BigDecimal cal = mMaxLoanableAmount.movePointLeft(mPDecimal).setScale(mPDecimal, RoundingMode.DOWN);
            mPrincipalInput.setText(cal.toPlainString());

        } else if (v.equals(mBtnCancel)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mBtnNext)) {
            if (onDisplayViewUpdate()) {
                if (mBeforeLiquidationPrice.compareTo(BigDecimal.ZERO) <= 0 || mBeforeRiskRate.compareTo(BigDecimal.ZERO) < 0 ||
                        mAfterLiquidationPrice == null || mAfterRiskRate == null || mToLoanAmount == null) {
                    Toast.makeText(getContext(), R.string.error_invalid_amount, Toast.LENGTH_SHORT).show();
                } else {
                    Coin principal = new Coin(mPrincipalDenom, mToLoanAmount.toPlainString());
                    getSActivity().mPrincipal = principal;
                    getSActivity().mBeforeLiquidationPrice = mBeforeLiquidationPrice;
                    getSActivity().mBeforeRiskRate = mBeforeRiskRate;
                    getSActivity().mAfterLiquidationPrice = mAfterLiquidationPrice;
                    getSActivity().mAfterRiskRate = mAfterRiskRate;
                    getSActivity().mMoreAddedLoanAmount = mMoreAddedLoanAmount;

                    Bundle bundle = new Bundle();
                    bundle.putString("beforeRiskRate", mBeforeRiskRate.toPlainString());
                    bundle.putString("afterRiskRate", mAfterRiskRate.toPlainString());
                    bundle.putString("beforeLiquidationPrice", mBeforeLiquidationPrice.toPlainString());
                    bundle.putString("afterLiquidationPrice", mAfterLiquidationPrice.toPlainString());
                    bundle.putString("currentPrice", mCurrentPrice.toPlainString());
                    bundle.putString("denom", mCollateralDenom);
                    SafeScoreConfirmDialog dialog = SafeScoreConfirmDialog.newInstance(bundle);
                    dialog.setTargetFragment(this, CDP_DRAW_DEBT_CONFIRM_DIALOG);
                    dialog.show(getSActivity().getSupportFragmentManager(), "dialog");
                }
            } else {
                Toast.makeText(getContext(), R.string.error_invalid_amount, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CDP_DRAW_DEBT_CONFIRM_DIALOG && resultCode == Activity.RESULT_OK) {
            getSActivity().onNextStep();
        }
    }


    private BorrowCdpActivity getSActivity() {
        return (BorrowCdpActivity) getBaseActivity();
    }

    public Genesis.Params getCDPParam() {
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

