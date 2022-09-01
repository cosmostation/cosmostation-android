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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.math.BigDecimal;
import java.math.RoundingMode;

import kava.cdp.v1beta1.Genesis;
import kava.pricefeed.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.kava.CreateCdpActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.SafeScoreCreateDialog;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class CreateCdpStep0Fragment extends BaseFragment implements View.OnClickListener {
    public final static int CDP_CREATE_CONFIRM_DIALOG = 6017;
    private final static int STEP_COLLATERAL = 0;
    private final static int STEP_PRINCIPAL = 1;
    private int mStep = STEP_COLLATERAL;

    private Button mBtnCancel, mBtnNext;

    private RelativeLayout mCollateralInputLayer;
    private ImageView mCollateralImg;
    private TextView mCollateralSymbol;
    private EditText mCollateralInput;
    private ImageView mCollateralClear;
    private LinearLayout mCollateralActionLayer;
    private TextView mCollateralMinTx, mCollateralMaxTx, mCollateralDenomTx;
    private Button mBtnCollateralMin, mBtnCollateral1_4, mBtnCollateralHalf, mBtnCollateral3_4, mBtnCollateralMax;

    private RelativeLayout mCollateralValueLayer;
    private TextView mCollateralValue;

    private LinearLayout mPrincipalLayer;
    private ImageView mPrincipalImg;
    private TextView mPrincipalSymbol;
    private EditText mPrincipalInput;
    private ImageView mPrincipalClear;
    private TextView mPrincipalMinTx, mPrincipalMaxTx, mPrincipalDenomTx;
    private Button mBtnPrincipalMin, mBtnPrincipal20, mBtnPrincipal50, mBtnPrincipal70, mBtnPrincipalMax;

    private String mCollateralDenom, mPrincipalDenom;
    private BigDecimal mCollateralMinAmount, mCollateralMaxAmount;
    private BigDecimal mPrincipalMinAmount, mPrincipalMaxAmount;

    private String mCollateralDecimalChecker, mCollateralDecimalSetter;
    private String mPrincipalChecker, mPrincipalSetter;

    public BigDecimal mToCollateralAmount = BigDecimal.ZERO;
    private int mCDecimal;
    private int mPDecimal;

    public static CreateCdpStep0Fragment newInstance(Bundle bundle) {
        CreateCdpStep0Fragment fragment = new CreateCdpStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_cdp_step0, container, false);
        mBtnCancel = rootView.findViewById(R.id.btn_cancel);
        mBtnNext = rootView.findViewById(R.id.btn_next);

        mCollateralInputLayer = rootView.findViewById(R.id.collateral_input_layer);
        mCollateralImg = rootView.findViewById(R.id.collateral_icon);
        mCollateralSymbol = rootView.findViewById(R.id.collateral_symbol);
        mCollateralInput = rootView.findViewById(R.id.collateral_input);
        mCollateralClear = rootView.findViewById(R.id.collateral_clear);
        mCollateralActionLayer = rootView.findViewById(R.id.collateral_action_layer);
        mCollateralMinTx = rootView.findViewById(R.id.collateral_min_amount);
        mCollateralMaxTx = rootView.findViewById(R.id.collateral_max_amount);
        mCollateralDenomTx = rootView.findViewById(R.id.collateral_denom);
        mBtnCollateralMin = rootView.findViewById(R.id.collateral_min);
        mBtnCollateral1_4 = rootView.findViewById(R.id.collateral_1_4);
        mBtnCollateralHalf = rootView.findViewById(R.id.collateral_half);
        mBtnCollateral3_4 = rootView.findViewById(R.id.collateral_3_4);
        mBtnCollateralMax = rootView.findViewById(R.id.collateral_max);
        mCollateralValueLayer = rootView.findViewById(R.id.collateral_value_layer);
        mCollateralValue = rootView.findViewById(R.id.collateral_value);

        mPrincipalLayer = rootView.findViewById(R.id.principal_layer);
        mPrincipalImg = rootView.findViewById(R.id.principal_icon);
        mPrincipalSymbol = rootView.findViewById(R.id.principal_symbol);
        mPrincipalInput = rootView.findViewById(R.id.principal_input);
        mPrincipalClear = rootView.findViewById(R.id.principal_clear);
        mPrincipalMinTx = rootView.findViewById(R.id.principal_min_amount);
        mPrincipalMaxTx = rootView.findViewById(R.id.principal_max_amount);
        mPrincipalDenomTx = rootView.findViewById(R.id.principal_denom);
        mBtnPrincipalMin = rootView.findViewById(R.id.principal_min);
        mBtnPrincipal20 = rootView.findViewById(R.id.principal_20);
        mBtnPrincipal50 = rootView.findViewById(R.id.principal_50);
        mBtnPrincipal70 = rootView.findViewById(R.id.principal_70);
        mBtnPrincipalMax = rootView.findViewById(R.id.principal_max);


        mCollateralInputLayer.setOnClickListener(this);
        mCollateralClear.setOnClickListener(this);
        mBtnCollateralMin.setOnClickListener(this);
        mBtnCollateral1_4.setOnClickListener(this);
        mBtnCollateralHalf.setOnClickListener(this);
        mBtnCollateral3_4.setOnClickListener(this);
        mBtnCollateralMax.setOnClickListener(this);
        mPrincipalClear.setOnClickListener(this);
        mBtnPrincipalMin.setOnClickListener(this);
        mBtnPrincipal20.setOnClickListener(this);
        mBtnPrincipal50.setOnClickListener(this);
        mBtnPrincipal70.setOnClickListener(this);
        mBtnPrincipalMax.setOnClickListener(this);

        mBtnCancel.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);

        mCollateralInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mStep = STEP_COLLATERAL;
                    onUpdateStep();
                }
            }
        });

        return rootView;
    }

    @Override
    public void onRefreshTab() {
        super.onRefreshTab();
        onUpdateInitInfo();
        onUpdateStep();
    }

    private void onUpdateInitInfo() {
        mCollateralDenom = getCParam().getDenom();
        mPrincipalDenom = getCParam().getDebtLimit().getDenom();
        mCDecimal = WDp.getDenomDecimal(getBaseDao(), getSActivity().mChainConfig, mCollateralDenom);
        mPDecimal = WDp.getDenomDecimal(getBaseDao(), getSActivity().mChainConfig, mPrincipalDenom);
        mPrincipalMinAmount = new BigDecimal(getCdpParam().getDebtParam().getDebtFloor());
        mCollateralMinAmount = mPrincipalMinAmount.movePointLeft(mPDecimal - mCDecimal).multiply(new BigDecimal("1.05263157895")).multiply(new BigDecimal(getCParam().getLiquidationRatio()).movePointLeft(18)).divide(new BigDecimal(getPrice().getPrice()), 0, RoundingMode.UP);
        mCollateralMaxAmount = getBaseDao().getAvailable(mCollateralDenom);

        WDp.setDpSymbol(getSActivity(), getBaseDao(), getSActivity().mChainConfig, mCollateralDenom, mCollateralSymbol);
        WDp.setDpSymbol(getSActivity(), getBaseDao(), getSActivity().mChainConfig, mCollateralDenom, mCollateralDenomTx);
        WDp.setDpSymbol(getSActivity(), getBaseDao(), getSActivity().mChainConfig, mPrincipalDenom, mPrincipalSymbol);
        WDp.setDpSymbol(getSActivity(), getBaseDao(), getSActivity().mChainConfig, mPrincipalDenom, mPrincipalDenomTx);
        mCollateralMinTx.setText(WDp.getDpAmount2(getContext(), mCollateralMinAmount, mCDecimal, mCDecimal));
        mCollateralMaxTx.setText(WDp.getDpAmount2(getContext(), mCollateralMaxAmount, mCDecimal, mCDecimal));
        setDpDecimals(mCDecimal, mPDecimal);

        WDp.setDpSymbolImg(getBaseDao(), getSActivity().mChainConfig, mCollateralDenom, mCollateralImg);
        WDp.setDpSymbolImg(getBaseDao(), getSActivity().mChainConfig, mPrincipalDenom, mPrincipalImg);
    }


    private void onUpdateStep() {
        if (mStep == STEP_COLLATERAL) {
            mCollateralActionLayer.setVisibility(View.VISIBLE);
            mCollateralValueLayer.setVisibility(View.GONE);
            mPrincipalLayer.setVisibility(View.GONE);
            mPrincipalInput.setText("");
            onUpdateNextBtn();

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
                            if (mCollateralMinAmount.compareTo(checkPosition) > 0) {
                                mCollateralInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));

                            } else if (mCollateralMaxAmount.compareTo(checkPosition) < 0) {
                                mCollateralInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));

                            } else {
                                mCollateralInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                            }
                            mCollateralInput.setSelection(mCollateralInput.getText().length());

                        } catch (Exception e) {
                        }
                    }
                }
            });


        } else if (mStep == STEP_PRINCIPAL) {
            mCollateralActionLayer.setVisibility(View.GONE);
            mCollateralValueLayer.setVisibility(View.VISIBLE);
            mPrincipalLayer.setVisibility(View.VISIBLE);
            mPrincipalInput.requestFocus();


            BigDecimal collateralValue = mToCollateralAmount.movePointLeft(mCDecimal).multiply(new BigDecimal(getPrice().getPrice()).movePointLeft(18)).setScale(2, RoundingMode.DOWN);
            mCollateralValue.setText(WDp.getDpRawDollor(getContext(), collateralValue, 2));

            mPrincipalMaxAmount = mToCollateralAmount.movePointLeft(mCDecimal - mPDecimal).multiply(new BigDecimal("0.95")).multiply(new BigDecimal(getPrice().getPrice()).movePointLeft(18)).divide(new BigDecimal(getCParam().getLiquidationRatio()).movePointLeft(18), 0, RoundingMode.DOWN);
            mPrincipalMinTx.setText(WDp.getDpAmount2(getContext(), mPrincipalMinAmount, mPDecimal, mPDecimal));
            mPrincipalMaxTx.setText(WDp.getDpAmount2(getContext(), mPrincipalMaxAmount, mPDecimal, mPDecimal));

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
                            if (checkPosition.compareTo(checkMax) != 0) {
                                String recover = es.substring(0, es.length() - 1);
                                mPrincipalInput.setText(recover);
                                mPrincipalInput.setSelection(recover.length());
                                return;
                            }
                            if (mPrincipalMinAmount.compareTo(checkPosition) > 0) {
                                mPrincipalInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));

                            } else if (mPrincipalMaxAmount.compareTo(checkPosition) < 0) {
                                mPrincipalInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));

                            } else {
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
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mCollateralInputLayer)) {
            if (mStep == STEP_PRINCIPAL) {
                mStep = STEP_COLLATERAL;
                onUpdateStep();
            }

        } else if (v.equals(mCollateralClear)) {
            mCollateralInput.setText("");
            if (mStep == STEP_PRINCIPAL) {
                mStep = STEP_COLLATERAL;
                onUpdateStep();
            }

        } else if (v.equals(mBtnCollateralMin)) {
            mCollateralInput.setText(mCollateralMinAmount.movePointLeft(mCDecimal).toPlainString());

        } else if (v.equals(mBtnCollateral1_4)) {
            BigDecimal cal = mCollateralMaxAmount.divide(new BigDecimal(4), 0, RoundingMode.DOWN);
            if (mCollateralMinAmount.compareTo(cal) > 0) {
                mCollateralInput.setText(mCollateralMinAmount.movePointLeft(mCDecimal).toPlainString());
                Toast.makeText(getContext(), R.string.error_less_than_min_deposit, Toast.LENGTH_SHORT).show();
            } else {
                mCollateralInput.setText(cal.movePointLeft(mCDecimal).toPlainString());
            }

        } else if (v.equals(mBtnCollateralHalf)) {
            BigDecimal cal = mCollateralMaxAmount.divide(new BigDecimal(2), 0, RoundingMode.DOWN);
            if (mCollateralMinAmount.compareTo(cal) > 0) {
                mCollateralInput.setText(mCollateralMinAmount.movePointLeft(mCDecimal).toPlainString());
                Toast.makeText(getContext(), R.string.error_less_than_min_deposit, Toast.LENGTH_SHORT).show();
            } else {
                mCollateralInput.setText(cal.movePointLeft(mCDecimal).toPlainString());
            }

        } else if (v.equals(mBtnCollateral3_4)) {
            BigDecimal cal = mCollateralMaxAmount.multiply(new BigDecimal(0.75)).setScale(0, RoundingMode.DOWN);
            if (mCollateralMinAmount.compareTo(cal) > 0) {
                mCollateralInput.setText(mCollateralMinAmount.movePointLeft(mCDecimal).toPlainString());
                Toast.makeText(getContext(), R.string.error_less_than_min_deposit, Toast.LENGTH_SHORT).show();
            } else {
                mCollateralInput.setText(cal.movePointLeft(mCDecimal).toPlainString());
            }

        } else if (v.equals(mBtnCollateralMax)) {
            mCollateralInput.setText(mCollateralMaxAmount.movePointLeft(mCDecimal).toPlainString());

        } else if (v.equals(mPrincipalClear)) {
            mPrincipalInput.setText("");

        } else if (v.equals(mBtnPrincipalMin)) {
            mPrincipalInput.setText(mPrincipalMinAmount.movePointLeft(mPDecimal).toPlainString());

        } else if (v.equals(mBtnPrincipal20)) {
            BigDecimal cal = mToCollateralAmount.movePointLeft(mCDecimal - mPDecimal).multiply(new BigDecimal("0.2")).multiply(new BigDecimal(getPrice().getPrice()).movePointLeft(18)).divide(new BigDecimal(getCParam().getLiquidationRatio()).movePointLeft(18), 0, RoundingMode.DOWN);
            if (mPrincipalMinAmount.compareTo(cal) > 0) {
                mPrincipalInput.setText(mPrincipalMinAmount.movePointLeft(mPDecimal).toPlainString());
                Toast.makeText(getContext(), R.string.error_less_than_min_principal, Toast.LENGTH_SHORT).show();
            } else {
                mPrincipalInput.setText(cal.movePointLeft(mPDecimal).toPlainString());
            }

        } else if (v.equals(mBtnPrincipal50)) {
            BigDecimal cal = mToCollateralAmount.movePointLeft(mCDecimal - mPDecimal).multiply(new BigDecimal("0.5")).multiply(new BigDecimal(getPrice().getPrice()).movePointLeft(18)).divide(new BigDecimal(getCParam().getLiquidationRatio()).movePointLeft(18), 0, RoundingMode.DOWN);
            if (mPrincipalMinAmount.compareTo(cal) > 0) {
                mPrincipalInput.setText(mPrincipalMinAmount.movePointLeft(mPDecimal).toPlainString());
                Toast.makeText(getContext(), R.string.error_less_than_min_principal, Toast.LENGTH_SHORT).show();
            } else {
                mPrincipalInput.setText(cal.movePointLeft(mPDecimal).toPlainString());
            }

        } else if (v.equals(mBtnPrincipal70)) {
            BigDecimal cal = mToCollateralAmount.movePointLeft(mCDecimal - mPDecimal).multiply(new BigDecimal("0.7")).multiply(new BigDecimal(getPrice().getPrice()).movePointLeft(18)).divide(new BigDecimal(getCParam().getLiquidationRatio()).movePointLeft(18), 0, RoundingMode.DOWN);
            if (mPrincipalMinAmount.compareTo(cal) > 0) {
                mPrincipalInput.setText(mPrincipalMinAmount.movePointLeft(mPDecimal).toPlainString());
                Toast.makeText(getContext(), R.string.error_less_than_min_principal, Toast.LENGTH_SHORT).show();
            } else {
                mPrincipalInput.setText(cal.movePointLeft(mPDecimal).toPlainString());
            }

        } else if (v.equals(mBtnPrincipalMax)) {
            mPrincipalInput.setText(mPrincipalMaxAmount.movePointLeft(mPDecimal).toPlainString());

        } else if (v.equals(mBtnCancel)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mBtnNext)) {
            if (mStep == STEP_COLLATERAL) {
                if (onValidateCollateral()) {
                    mStep = STEP_PRINCIPAL;
                    onUpdateStep();
                } else {
                    Toast.makeText(getContext(), R.string.error_invalid_amount, Toast.LENGTH_SHORT).show();
                }

            } else if (mStep == STEP_PRINCIPAL) {
                if (onValidateNext()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("riskRate", getSActivity().mRiskRate.toPlainString());
                    bundle.putString("liquidationPrice", getSActivity().mLiquidationPrice.toPlainString());
                    bundle.putString("currentPrice", getPrice().getPrice());
                    bundle.putString("denom", mCollateralDenom);
                    SafeScoreCreateDialog dialog = SafeScoreCreateDialog.newInstance(bundle);
                    dialog.setTargetFragment(this, CDP_CREATE_CONFIRM_DIALOG);
                    getSActivity().getSupportFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();

                } else {
                    Toast.makeText(getContext(), R.string.error_invalid_amount, Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    private boolean onValidateCollateral() {
        try {
            BigDecimal toCollateralAmount = new BigDecimal(mCollateralInput.getText().toString().trim()).movePointRight(mCDecimal);
            if (toCollateralAmount.compareTo(BigDecimal.ZERO) <= 0) return false;
            if (mCollateralMinAmount.compareTo(toCollateralAmount) > 0) return false;
            if (mCollateralMaxAmount.compareTo(toCollateralAmount) < 0) return false;
            mToCollateralAmount = toCollateralAmount;
            return true;
        } catch (Exception e) {
            mToCollateralAmount = BigDecimal.ZERO;
            return false;
        }
    }

    private boolean onValidateNext() {
        try {
            getSActivity().toCollateralAmount = BigDecimal.ZERO;
            getSActivity().toPrincipalAmount = BigDecimal.ZERO;
            getSActivity().mRiskRate = BigDecimal.ZERO;
            getSActivity().mLiquidationPrice = BigDecimal.ZERO;

            BigDecimal toCollateralAmount = new BigDecimal(mCollateralInput.getText().toString().trim()).movePointRight(mCDecimal);
            if (toCollateralAmount.compareTo(BigDecimal.ZERO) <= 0) return false;
            if (mCollateralMinAmount.compareTo(toCollateralAmount) > 0) return false;
            if (mCollateralMaxAmount.compareTo(toCollateralAmount) < 0) return false;

            BigDecimal toPrincipalAmount = new BigDecimal(mPrincipalInput.getText().toString().trim()).movePointRight(mPDecimal);
            if (toPrincipalAmount.compareTo(BigDecimal.ZERO) <= 0) return false;
            if (mPrincipalMinAmount.compareTo(toPrincipalAmount) > 0 || mPrincipalMaxAmount.compareTo(toPrincipalAmount) < 0)
                return false;

            final BigDecimal currentPrice = new BigDecimal(getPrice().getPrice()).movePointLeft(18);
            final BigDecimal liquidationPrice = toPrincipalAmount.movePointLeft(mPDecimal - mCDecimal).multiply(new BigDecimal(getCParam().getLiquidationRatio()).movePointLeft(18)).divide(mToCollateralAmount, mCDecimal, RoundingMode.DOWN);
            final BigDecimal riskRate = new BigDecimal(100).subtract((currentPrice.subtract(liquidationPrice)).movePointRight(2).divide(currentPrice, 2, RoundingMode.DOWN));

            getSActivity().mCollateral = new Coin(mCollateralDenom, toCollateralAmount.toPlainString());
            getSActivity().mPrincipal = new Coin(mPrincipalDenom, toPrincipalAmount.toPlainString());

            getSActivity().toCollateralAmount = toCollateralAmount;
            getSActivity().toPrincipalAmount = toPrincipalAmount;
            getSActivity().mRiskRate = riskRate;
            getSActivity().mLiquidationPrice = liquidationPrice;
            return true;

        } catch (Exception e) {
            getSActivity().toCollateralAmount = BigDecimal.ZERO;
            getSActivity().toPrincipalAmount = BigDecimal.ZERO;
            getSActivity().mRiskRate = BigDecimal.ZERO;
            getSActivity().mLiquidationPrice = BigDecimal.ZERO;
            return false;
        }

    }


    private void onUpdateNextBtn() {
        if (mStep == STEP_PRINCIPAL) {
            try {
                BigDecimal toPrincipalAmount = new BigDecimal(mPrincipalInput.getText().toString().trim()).movePointRight(mPDecimal);
                if (toPrincipalAmount.compareTo(BigDecimal.ZERO) <= 0) {
                    mBtnNext.setText(R.string.str_next);
                    mBtnNext.setTextColor(ContextCompat.getColor(getSActivity(), R.color.colorPhoton));
                    mBtnNext.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.btn_trans_with_photon_border));
                    mBtnNext.setTypeface(null, Typeface.NORMAL);
                    return;
                }
                if (mPrincipalMinAmount.compareTo(toPrincipalAmount) > 0 || mPrincipalMaxAmount.compareTo(toPrincipalAmount) < 0) {
                    mBtnNext.setText(R.string.str_next);
                    mBtnNext.setTextColor(ContextCompat.getColor(getSActivity(), R.color.colorPhoton));
                    mBtnNext.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.btn_trans_with_photon_border));
                    mBtnNext.setTypeface(null, Typeface.NORMAL);
                    return;

                }
                final BigDecimal currentPrice = new BigDecimal(getPrice().getPrice()).movePointLeft(18);
                final BigDecimal liquidationPrice = toPrincipalAmount.movePointLeft(mPDecimal - mCDecimal).multiply(new BigDecimal(getCParam().getLiquidationRatio()).movePointLeft(18)).divide(mToCollateralAmount, mCDecimal, RoundingMode.DOWN);
                final BigDecimal riskRate = new BigDecimal(100).subtract((currentPrice.subtract(liquidationPrice)).movePointRight(2).divide(currentPrice, 2, RoundingMode.DOWN));

                WUtil.DpRiskButton(getContext(), riskRate, mBtnNext);

            } catch (Exception e) {
                mBtnNext.setText(R.string.str_next);
                mBtnNext.setTextColor(ContextCompat.getColor(getSActivity(), R.color.colorPhoton));
                mBtnNext.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.btn_trans_with_photon_border));
                mBtnNext.setTypeface(null, Typeface.NORMAL);
            }

        } else {
            mBtnNext.setText(R.string.str_next);
            mBtnNext.setTextColor(ContextCompat.getColor(getSActivity(), R.color.colorPhoton));
            mBtnNext.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.btn_trans_with_photon_border));
            mBtnNext.setTypeface(null, Typeface.NORMAL);

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CDP_CREATE_CONFIRM_DIALOG && resultCode == Activity.RESULT_OK) {
            getSActivity().onNextStep();
        }
    }


    private CreateCdpActivity getSActivity() {
        return (CreateCdpActivity) getBaseActivity();
    }

    private Genesis.Params getCdpParam() {
        return getSActivity().mCdpParams;
    }

    private Genesis.CollateralParam getCParam() {
        return getSActivity().mCollateralParam;
    }

    private QueryOuterClass.CurrentPriceResponse getPrice() {
        return getSActivity().mKavaTokenPrice;
    }


    private void setDpDecimals(int cDecimal, int pDeciaml) {
        mCollateralDecimalChecker = "0.";
        mPrincipalChecker = "0.";
        mCollateralDecimalSetter = "0.";
        mPrincipalSetter = "0.";
        for (int i = 0; i < cDecimal; i++) {
            mCollateralDecimalChecker = mCollateralDecimalChecker + "0";
        }
        for (int i = 0; i < cDecimal - 1; i++) {
            mCollateralDecimalSetter = mCollateralDecimalSetter + "0";
        }

        for (int i = 0; i < pDeciaml; i++) {
            mPrincipalChecker = mPrincipalChecker + "0";
        }
        for (int i = 0; i < pDeciaml - 1; i++) {
            mPrincipalSetter = mPrincipalSetter + "0";
        }
    }

}
