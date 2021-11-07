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

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.BorrowCdpActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_Safe_Score_Confirm;
import wannabit.io.cosmostaion.model.kava.CdpParam;
import wannabit.io.cosmostaion.model.kava.CollateralParam;
import wannabit.io.cosmostaion.model.kava.MarketPrice;
import wannabit.io.cosmostaion.model.kava.MyCdp;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_COIN_IMG_URL;

public class DrawDebtCdpStep0Fragment extends BaseFragment implements View.OnClickListener {
    public final static int CDP_DRAW_DEBT_CONFIRM_DIALOG = 6019;

    private Button          mBtnCancel, mBtnNext;

    private ImageView       mPrincipalImg;
    private TextView        mPrincipalSymbol;
    private EditText        mPrincipalInput;
    private ImageView       mPrincipalClear;
    private TextView        mLoanableMinTx, mLoanableMaxTx, mLoanableDenomTx;
    private Button          mBtnMin, mBtn1_4, mBtnHalf, mBtn3_4, mBtnMax;

    private LinearLayout    mAfterRiskLayer;
    private TextView        mBeforeRisk, mAfterRisk, mBeforeRiskScore, mAfterRiskScore;
    private TextView        mBeforePrincipalAmount, mAfterPrincipalAmount;

    private BigDecimal      mCurrentPrice;
    private String          mCollateralDenom, mPrincipalDenom;
    private BigDecimal      mCurrentTotalDebetAmount, mCurrentCollateralAmount, mToLoanAmount, mMoreAddedLoanAmount;
    private BigDecimal      mMinLoanableAmount, mMaxLoanableAmount;
    private BigDecimal      mBeforeLiquidationPrice, mBeforeRiskRate, mAfterLiquidationPrice, mAfterRiskRate;

    private String          mPrincipalChecker, mPrincipalSetter;


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

        mCollateralDenom = getCParam().denom;
        mPrincipalDenom = getCParam().debt_limit.denom;
        setDpDecimals(WUtil.getKavaCoinDecimal(mPrincipalDenom));
        mCurrentPrice = new BigDecimal(getPrice().price);

        BigDecimal currentPAmount = getOwenCdp().getPrincipalAmount();
        BigDecimal debtFloor = new BigDecimal(getCDPParam().debt_param.debt_floor);

        if (currentPAmount.compareTo(debtFloor) > 0) {
            mMinLoanableAmount = BigDecimal.ONE;
        } else {
            mMinLoanableAmount = debtFloor.subtract(currentPAmount);
        }

        mMaxLoanableAmount = getOwenCdp().getMoreLoanableAmount(getContext(), getCParam());
        WDp.showCoinDp(getContext(), getBaseDao(), mPrincipalDenom, mMinLoanableAmount.toPlainString(), mLoanableDenomTx, mLoanableMinTx, getSActivity().mBaseChain);
        WDp.showCoinDp(getContext(), getBaseDao(), mPrincipalDenom, mMaxLoanableAmount.toPlainString(), mLoanableDenomTx, mLoanableMaxTx, getSActivity().mBaseChain);

        mPrincipalSymbol.setText(mPrincipalDenom.toUpperCase());
        try {
            Picasso.get().load(KAVA_COIN_IMG_URL + mPrincipalDenom + ".png").fit().into(mPrincipalImg);
        } catch (Exception e) { }

        //before(current) state views!!
        mCurrentTotalDebetAmount = getOwenCdp().getEstimatedTotalDebt(getContext(), getCParam());
        mCurrentCollateralAmount = getOwenCdp().getCollateralAmount();
        mBeforeLiquidationPrice = mCurrentTotalDebetAmount.movePointLeft(WUtil.getKavaCoinDecimal(mPrincipalDenom) - WUtil.getKavaCoinDecimal(mCollateralDenom)).multiply(new BigDecimal(getCParam().liquidation_ratio)).divide(mCurrentCollateralAmount, WUtil.getKavaCoinDecimal(mCollateralDenom), RoundingMode.DOWN);
        WLog.w("mBeforeLiquidationPrice " + mBeforeLiquidationPrice);
        mBeforeRiskRate = new BigDecimal(100).subtract((mCurrentPrice.subtract(mBeforeLiquidationPrice)).movePointRight(2).divide(mCurrentPrice, 2, RoundingMode.DOWN));
        WLog.w("mBeforeRiskRate " + mBeforeRiskRate);
        WDp.DpRiskRate3(getContext(), mBeforeRiskRate, mBeforeRiskScore, mBeforeRisk);
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
                        if (checkPosition.compareTo(checkMax) != 0 || !checkPosition.equals(checkMax)) {
                            String recover = es.substring(0, es.length() - 1);
                            mPrincipalInput.setText(recover);
                            mPrincipalInput.setSelection(recover.length());
                            return;
                        }
                        if (mMinLoanableAmount.compareTo(checkPosition) > 0) {
                            mPrincipalInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));

                        } else if (mMaxLoanableAmount.compareTo(checkPosition) < 0) {
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
        mAfterLiquidationPrice = mAfterRiskRate = mToLoanAmount = null;
        try {
            mToLoanAmount = BigDecimal.ZERO;
            try {
                mToLoanAmount = new BigDecimal(mPrincipalInput.getText().toString().trim()).movePointRight(WUtil.getKavaCoinDecimal(mPrincipalDenom));
            } catch (Exception e) {
                mAfterRiskLayer.setVisibility(View.INVISIBLE);
                mAfterPrincipalAmount.setVisibility(View.GONE);
                return false;
            }
            WLog.w("mToLoanAmount " + mToLoanAmount);
            if (mToLoanAmount.compareTo(mMinLoanableAmount) < 0 || mToLoanAmount.compareTo(mMaxLoanableAmount) > 0) {
                mBtnNext.setText(R.string.str_next);
                mBtnNext.setTextColor(getResources().getColor(R.color.color_btn_photon));
                mBtnNext.setBackground(getResources().getDrawable(R.drawable.btn_trans_with_border));
                mBtnNext.setTypeface(null, Typeface.NORMAL);
                mAfterRiskLayer.setVisibility(View.INVISIBLE);
                mAfterPrincipalAmount.setVisibility(View.GONE);
                return false;
            }
            mAfterRiskLayer.setVisibility(View.VISIBLE);
//            mAfterPrincipalAmount.setVisibility(View.VISIBLE);

            mMoreAddedLoanAmount = mCurrentTotalDebetAmount.add(mToLoanAmount);
            WLog.w("mMoreAddedLoanAmount " + mMoreAddedLoanAmount);

            mAfterLiquidationPrice = mMoreAddedLoanAmount.movePointLeft(WUtil.getKavaCoinDecimal(mPrincipalDenom) - WUtil.getKavaCoinDecimal(mCollateralDenom)).multiply(new BigDecimal(getCParam().liquidation_ratio)).divide(mCurrentCollateralAmount, WUtil.getKavaCoinDecimal(mCollateralDenom), RoundingMode.DOWN);
            WLog.w("mAfterLiquidationPrice " + mAfterLiquidationPrice);

            mAfterRiskRate = new BigDecimal(100).subtract((mCurrentPrice.subtract(mAfterLiquidationPrice)).movePointRight(2).divide(mCurrentPrice, 2, RoundingMode.DOWN));
            WLog.w("mAfterRiskRate " + mAfterRiskRate);

            WDp.DpRiskRate3(getContext(), mAfterRiskRate, mAfterRiskScore, mAfterRisk);
            WDp.DpRiskButton2(getContext(), mAfterRiskRate, mBtnNext);

//            mAfterPrincipalAmount.setText(WDp.getDpAmount2(getContext(), mMoreAddedLoanAmount, WUtil.getKavaCoinDecimal(mPrincipalDenom), WUtil.getKavaCoinDecimal(mPrincipalDenom)));
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

        } else if (v.equals(mBtnMin)) {
            mPrincipalInput.setText( mMinLoanableAmount.movePointLeft(WUtil.getKavaCoinDecimal(mPrincipalDenom)).toPlainString());

        } else if (v.equals(mBtn1_4)) {
            BigDecimal cal = mMaxLoanableAmount.multiply(new BigDecimal("0.25"));
            if (mMinLoanableAmount.compareTo(cal) > 0) {
                cal = mMinLoanableAmount;
                Toast.makeText(getContext(), R.string.error_less_than_min_principal, Toast.LENGTH_SHORT).show();
            }
            cal = cal.movePointLeft(WUtil.getKavaCoinDecimal(mPrincipalDenom)).setScale(WUtil.getKavaCoinDecimal(mPrincipalDenom), RoundingMode.DOWN);
            mPrincipalInput.setText(cal.toPlainString());

        } else if (v.equals(mBtnHalf)) {
            BigDecimal cal = mMaxLoanableAmount.multiply(new BigDecimal("0.5"));
            if (mMinLoanableAmount.compareTo(cal) > 0) {
                cal = mMinLoanableAmount;
                Toast.makeText(getContext(), R.string.error_less_than_min_principal, Toast.LENGTH_SHORT).show();
            }
            cal = cal.movePointLeft(WUtil.getKavaCoinDecimal(mPrincipalDenom)).setScale(WUtil.getKavaCoinDecimal(mPrincipalDenom), RoundingMode.DOWN);
            mPrincipalInput.setText(cal.toPlainString());

        } else if (v.equals(mBtn3_4)) {
            BigDecimal cal = mMaxLoanableAmount.multiply(new BigDecimal("0.75"));
            if (mMinLoanableAmount.compareTo(cal) > 0) {
                cal = mMinLoanableAmount;
                Toast.makeText(getContext(), R.string.error_less_than_min_principal, Toast.LENGTH_SHORT).show();
            }
            cal = cal.movePointLeft(WUtil.getKavaCoinDecimal(mPrincipalDenom)).setScale(WUtil.getKavaCoinDecimal(mPrincipalDenom), RoundingMode.DOWN);
            mPrincipalInput.setText(cal.toPlainString());

        } else if (v.equals(mBtnMax)) {
            BigDecimal cal = mMaxLoanableAmount.movePointLeft(WUtil.getKavaCoinDecimal(mPrincipalDenom)).setScale(WUtil.getKavaCoinDecimal(mPrincipalDenom), RoundingMode.DOWN);
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
                    bundle.putString("currentPrice", getPrice().price);
                    bundle.putString("denom", mCollateralDenom);
                    Dialog_Safe_Score_Confirm dialog = Dialog_Safe_Score_Confirm.newInstance(bundle);
                    dialog.setCancelable(true);
                    dialog.setTargetFragment(this, CDP_DRAW_DEBT_CONFIRM_DIALOG);
                    dialog.show(getFragmentManager().beginTransaction(), "dialog");

                }
            } else {
                Toast.makeText(getContext(), R.string.error_invalid_amount, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CDP_DRAW_DEBT_CONFIRM_DIALOG && resultCode == Activity.RESULT_OK) {
            getSActivity().onNextStep();
        }
    }


    private BorrowCdpActivity getSActivity() {
        return (BorrowCdpActivity)getBaseActivity();
    }

    public CdpParam getCDPParam() {
        return getSActivity().mCdpParam;
    }

    private CollateralParam getCParam() {
        return getSActivity().mCollateralParam;
    }

    private MyCdp getOwenCdp() {
        return getSActivity().mMyCdp;
    }

    private MarketPrice getPrice() {
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

