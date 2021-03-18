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
import wannabit.io.cosmostaion.activities.chains.kava.WithdrawCdpActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_Safe_Score_Confirm;
import wannabit.io.cosmostaion.model.kava.CollateralParam;
import wannabit.io.cosmostaion.model.kava.MarketPrice;
import wannabit.io.cosmostaion.model.kava.MyCdp;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.network.res.ResCdpOwnerStatus;
import wannabit.io.cosmostaion.network.res.ResKavaMarketPrice;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_COIN_IMG_URL;

public class WithdrawCdpStep0Fragment extends BaseFragment implements View.OnClickListener {
    public final static int CDP_WITHDRAW_CONFIRM_DIALOG = 6021;

    private Button          mBtnCancel, mBtnNext;
    private ImageView       mCollateralImg;
    private TextView        mCollateralSymbol;
    private EditText        mCollateralInput;
    private ImageView       mCollateralClear;
    private TextView        mCollateralMaxTx, mCollateralDenomTx;
    private Button          mBtnAdd1, mBtnAdd1_4, mBtnAddHalf, mBtnAdd3_4, mBtnAddMax;

    private LinearLayout    mAfterRiskLayer;
    private TextView        mBeforeRisk, mAfterRisk, mBeforeRiskScore, mAfterRiskScore;
    private TextView        mBeforeDepositAmount, mAfterDepositAmount;

    private BigDecimal      mCurrentPrice;
    private String          mCollateralDenom, mPrincipalDenom;
    private BigDecimal      mCurrentTotalDebetAmount, mCurrentCollateralAmount, mCanWithdrawMaxMaxAmount, mToWithdrawAmount, mTotalDepositAmount;
    private BigDecimal      mBeforeLiquidationPrice, mBeforeRiskRate, mAfterLiquidationPrice, mAfterRiskRate;

    private String          mCollateralDecimalChecker, mCollateralDecimalSetter;


    public static WithdrawCdpStep0Fragment newInstance(Bundle bundle) {
        WithdrawCdpStep0Fragment fragment = new WithdrawCdpStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_withdraw_cdp_step0, container, false);
        mBtnCancel = rootView.findViewById(R.id.btn_cancel);
        mBtnNext = rootView.findViewById(R.id.btn_next);

        mCollateralImg = rootView.findViewById(R.id.collateral_icon);
        mCollateralSymbol = rootView.findViewById(R.id.collateral_symbol);
        mCollateralInput = rootView.findViewById(R.id.collateral_input);
        mCollateralClear = rootView.findViewById(R.id.collateral_clear);
        mCollateralMaxTx = rootView.findViewById(R.id.withdrawable_max_amount);
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
        mCollateralDenom = getCParam().denom;
        mPrincipalDenom = getCParam().debt_limit.denom;
        setDpDecimals(WUtil.getKavaCoinDecimal(mCollateralDenom));
        mCurrentPrice = new BigDecimal(getPrice().price);

        mCurrentTotalDebetAmount = getOwenCdp().getEstimatedTotalDebt(getContext(), getCParam());
        mCanWithdrawMaxMaxAmount = getOwenCdp().getWithdrawableAmount(getContext(), getCParam(), mCurrentPrice, getSActivity().mSelfDepositAmount);
        WDp.showCoinDp(getContext(), mCollateralDenom, mCanWithdrawMaxMaxAmount.toPlainString(), mCollateralDenomTx, mCollateralMaxTx, getSActivity().mBaseChain);

        mCollateralSymbol.setText(mCollateralDenom.toUpperCase());
        try {
            Picasso.get().load(KAVA_COIN_IMG_URL + mCollateralDenom + ".png").fit().into(mCollateralImg);
        } catch (Exception e) { }


        mCurrentCollateralAmount = getOwenCdp().getCollateralAmount();

        //before(current) state views!!
        mBeforeLiquidationPrice = mCurrentTotalDebetAmount.movePointLeft(WUtil.getKavaCoinDecimal(mPrincipalDenom) - WUtil.getKavaCoinDecimal(mCollateralDenom)).multiply(new BigDecimal(getCParam().liquidation_ratio)).divide(mCurrentCollateralAmount, WUtil.getKavaCoinDecimal(mCollateralDenom), RoundingMode.DOWN);
        WLog.w("mBeforeLiquidationPrice " + mBeforeLiquidationPrice);
        mBeforeRiskRate = new BigDecimal(100).subtract((mCurrentPrice.subtract(mBeforeLiquidationPrice)).movePointRight(2).divide(mCurrentPrice, 2, RoundingMode.DOWN));
        WLog.w("mBeforeRiskRate " + mBeforeRiskRate);
        WDp.DpRiskRate3(getContext(), mBeforeRiskRate, mBeforeRiskScore, mBeforeRisk);
        mBeforeDepositAmount.setText(WDp.getDpAmount2(getContext(), mCurrentCollateralAmount, WUtil.getKavaCoinDecimal(mCollateralDenom), WUtil.getKavaCoinDecimal(mCollateralDenom)));

        mCollateralInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable et) {
                String es = et.toString().trim();
                if(TextUtils.isEmpty(es)) {
                    mCollateralInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                } else if (es.startsWith(".")) {
                    mCollateralInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                    mCollateralInput.setText("");
                } else if (es.endsWith(".")) {
                    mCollateralInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                    return;
                } else if(es.length() > 1 && es.startsWith("0") && !es.startsWith("0.")) {
                    mCollateralInput.setText("0");
                    mCollateralInput.setSelection(1);
                }

                if (es.equals(mCollateralDecimalChecker)) {
                    mCollateralInput.setText(mCollateralDecimalSetter);
                    mCollateralInput.setSelection(WUtil.getKavaCoinDecimal(mCollateralDenom) + 1);
                } else {
                    try {
                        final BigDecimal inputAmount = new BigDecimal(es);
                        if (BigDecimal.ZERO.compareTo(inputAmount) >= 0 ){
                            mCollateralInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                            return;
                        }

                        BigDecimal checkPosition = inputAmount.movePointRight(WUtil.getKavaCoinDecimal(mCollateralDenom));
                        BigDecimal checkMax = checkPosition.setScale(0, RoundingMode.DOWN);
                        if (checkPosition.compareTo(checkMax) != 0) {
                            String recover = es.substring(0, es.length() - 1);
                            mCollateralInput.setText(recover);
                            mCollateralInput.setSelection(recover.length());
                            return;
                        }

                        if (mCanWithdrawMaxMaxAmount.compareTo(checkPosition) < 0) {
                            mCollateralInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));

                        } else {
                            mCollateralInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                        }
                        mCollateralInput.setSelection(mCollateralInput.getText().length());

                    } catch (Exception e) { }
                }
                onDisplayViewUpdate();
            }
        });
    }

    private boolean onDisplayViewUpdate() {
        mAfterLiquidationPrice = mAfterRiskRate =  null;
        try {
            mToWithdrawAmount = BigDecimal.ZERO;
            try {
                mToWithdrawAmount = new BigDecimal(mCollateralInput.getText().toString().trim()).movePointRight(WUtil.getKavaCoinDecimal(mCollateralDenom));
            } catch (Exception e) {
                mAfterRiskLayer.setVisibility(View.INVISIBLE);
                mAfterDepositAmount.setVisibility(View.GONE);
                return false;
            }
            if (mToWithdrawAmount.compareTo(BigDecimal.ZERO) <= 0 || mCanWithdrawMaxMaxAmount.compareTo(mToWithdrawAmount) < 0) {
                mBtnNext.setText(R.string.str_next);
                mBtnNext.setTextColor(getResources().getColor(R.color.color_btn_photon));
                mBtnNext.setBackground(getResources().getDrawable(R.drawable.btn_trans_with_border));
                mBtnNext.setTypeface(null, Typeface.NORMAL);
                mAfterRiskLayer.setVisibility(View.INVISIBLE);
                mAfterDepositAmount.setVisibility(View.GONE);
                return false;
            }
            WLog.w("mToWithdrawAmount " + mToWithdrawAmount);
            mAfterRiskLayer.setVisibility(View.VISIBLE);
//            mAfterDepositAmount.setVisibility(View.VISIBLE);

            mTotalDepositAmount = mCurrentCollateralAmount.subtract(mToWithdrawAmount);
            WLog.w("mTotalDepositAmount " + mTotalDepositAmount);

            mAfterLiquidationPrice = mCurrentTotalDebetAmount.movePointLeft(WUtil.getKavaCoinDecimal(mPrincipalDenom) - WUtil.getKavaCoinDecimal(mCollateralDenom)).multiply(new BigDecimal(getCParam().liquidation_ratio)).divide(mTotalDepositAmount, WUtil.getKavaCoinDecimal(mCollateralDenom), RoundingMode.DOWN);
            WLog.w("mAfterLiquidationPrice " + mAfterLiquidationPrice);

            mAfterRiskRate = new BigDecimal(100).subtract((mCurrentPrice.subtract(mAfterLiquidationPrice)).movePointRight(2).divide(mCurrentPrice, 2, RoundingMode.DOWN));
            WLog.w("mAfterRiskRate " + mAfterRiskRate);

            WDp.DpRiskRate3(getContext(), mAfterRiskRate, mAfterRiskScore, mAfterRisk);
            WDp.DpRiskButton2(getContext(), mAfterRiskRate, mBtnNext);

//            mAfterDepositAmount.setText(WDp.getDpAmount2(getContext(), mTotalDepositAmount, WUtil.getKavaCoinDecimal(mCollateralDenom), WUtil.getKavaCoinDecimal(mCollateralDenom)));
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
        if (v.equals(mCollateralClear)) {
            mCollateralInput.setText("");

        } else if (v.equals(mBtnAdd1)) {
            BigDecimal inputedAmount = BigDecimal.ZERO;
            try {
                inputedAmount = new BigDecimal(mCollateralInput.getText().toString().trim());
            } catch (Exception e) { }
            inputedAmount = inputedAmount.add(new BigDecimal("1"));
            mCollateralInput.setText(inputedAmount.toPlainString());

        } else if (v.equals(mBtnAdd1_4)) {
            try {
                BigDecimal cal = mCanWithdrawMaxMaxAmount.divide(new BigDecimal(4), 0, RoundingMode.DOWN);
                mCollateralInput.setText(cal.movePointLeft(WUtil.getKavaCoinDecimal(mCollateralDenom)).toPlainString());

            } catch (Exception e) {
                mCollateralInput.setText("");
            }

        } else if (v.equals(mBtnAddHalf)) {
            try {
                BigDecimal cal = mCanWithdrawMaxMaxAmount.divide(new BigDecimal(2), 0, RoundingMode.DOWN);
                mCollateralInput.setText(cal.movePointLeft(WUtil.getKavaCoinDecimal(mCollateralDenom)).toPlainString());

            } catch (Exception e) {
                mCollateralInput.setText("");
            }

        } else if (v.equals(mBtnAdd3_4)) {
            try {
                BigDecimal cal = mCanWithdrawMaxMaxAmount.multiply(new BigDecimal(0.75)).setScale(0, RoundingMode.DOWN);
                mCollateralInput.setText(cal.movePointLeft(WUtil.getKavaCoinDecimal(mCollateralDenom)).toPlainString());

            } catch (Exception e) {
                mCollateralInput.setText("");
            }
        } else if (v.equals(mBtnAddMax)) {
            mCollateralInput.setText(mCanWithdrawMaxMaxAmount.movePointLeft(WUtil.getKavaCoinDecimal(mCollateralDenom)).toPlainString());

        } else if (v.equals(mBtnCancel)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mBtnNext)) {
            if (onDisplayViewUpdate()) {
                if (mBeforeLiquidationPrice.compareTo(BigDecimal.ZERO) <= 0 || mBeforeRiskRate.compareTo(BigDecimal.ZERO) < 0 || mToWithdrawAmount.compareTo(BigDecimal.ZERO) <= 0 ||
                        mAfterLiquidationPrice == null || mAfterRiskRate == null) {
                    Toast.makeText(getContext(), R.string.error_invalid_amount, Toast.LENGTH_SHORT).show();
                } else {
                    Coin collateral = new Coin(mCollateralDenom, mToWithdrawAmount.toPlainString());
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
                    bundle.putString("currentPrice", getPrice().price);
                    bundle.putString("denom", mCollateralDenom);
                    Dialog_Safe_Score_Confirm dialog = Dialog_Safe_Score_Confirm.newInstance(bundle);
                    dialog.setCancelable(true);
                    dialog.setTargetFragment(this, CDP_WITHDRAW_CONFIRM_DIALOG);
                    dialog.show(getFragmentManager().beginTransaction(), "dialog");

                }
            } else {
                Toast.makeText(getContext(), R.string.error_invalid_amount, Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CDP_WITHDRAW_CONFIRM_DIALOG && resultCode == Activity.RESULT_OK) {
            getSActivity().onNextStep();
        }
    }

    private WithdrawCdpActivity getSActivity() {
        return (WithdrawCdpActivity)getBaseActivity();
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

    private void setDpDecimals(int deciaml) {
        mCollateralDecimalChecker = "0.";
        mCollateralDecimalSetter = "0.";
        for (int i = 0; i < deciaml; i ++) {
            mCollateralDecimalChecker = mCollateralDecimalChecker+"0";
        }
        for (int i = 0; i < deciaml-1; i ++) {
            mCollateralDecimalSetter = mCollateralDecimalSetter+"0";
        }
    }
}
