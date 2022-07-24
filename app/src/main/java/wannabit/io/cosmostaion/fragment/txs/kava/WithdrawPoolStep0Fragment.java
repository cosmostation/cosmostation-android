package wannabit.io.cosmostaion.fragment.txs.kava;

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

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.kava.WithDrawPoolActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;

public class WithdrawPoolStep0Fragment extends BaseFragment implements View.OnClickListener {

    private RelativeLayout mProgress;
    private Button mCancelBtn, mNextBtn;

    private LinearLayout mLpCoinLayout;
    private EditText mLpCoinInput;
    private ImageView mLpCoinClearBtn;
    private TextView mLpCoinAmount;
    private Button mLpCoin1_4Btn, mLpCoinHalfBtn, mLpCoin3_4Btn, mLpCoinMaxBtn;

    private BigDecimal mAvailableMaxAmount;

    private int mCoinDecimal = 6;
    private String mDecimalChecker, mDecimalSetter;

    public static WithdrawPoolStep0Fragment newInstance(Bundle bundle) {
        WithdrawPoolStep0Fragment fragment = new WithdrawPoolStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exit_pool_step0, container, false);
        mCancelBtn = rootView.findViewById(R.id.btn_cancel);
        mNextBtn = rootView.findViewById(R.id.btn_next);
        mProgress = rootView.findViewById(R.id.progress);

        mLpCoinLayout = rootView.findViewById(R.id.exit_pool_input_symbol_layer);
        mLpCoinInput = rootView.findViewById(R.id.exit_pool_input);
        mLpCoinClearBtn = rootView.findViewById(R.id.exit_pool_input_clear);
        mLpCoinAmount = rootView.findViewById(R.id.exit_pool_input_amount);
        mLpCoin1_4Btn = rootView.findViewById(R.id.exit_pool_input_1_4);
        mLpCoinHalfBtn = rootView.findViewById(R.id.exit_pool_input_half);
        mLpCoin3_4Btn = rootView.findViewById(R.id.exit_pool_input_3_4);
        mLpCoinMaxBtn = rootView.findViewById(R.id.exit_pool_input_max);

        mLpCoinClearBtn.setOnClickListener(this);
        mLpCoin1_4Btn.setOnClickListener(this);
        mLpCoinHalfBtn.setOnClickListener(this);
        mLpCoin3_4Btn.setOnClickListener(this);
        mLpCoinMaxBtn.setOnClickListener(this);
        mCancelBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

        mLpCoinLayout.setVisibility(View.GONE);
        onInitView();
        return rootView;
    }

    private void onInitView() {
        mProgress.setVisibility(View.GONE);

        mAvailableMaxAmount = new BigDecimal(getSActivity().mKavaDepositPool.getSharesOwned());
        mLpCoinAmount.setText(WDp.getDpAmount2(getSActivity(), mAvailableMaxAmount, mCoinDecimal, mCoinDecimal));
        setDpDecimals(mCoinDecimal);

        onAddAmountWatcher();
    }

    private void onAddAmountWatcher() {
        mLpCoinInput.addTextChangedListener(new TextWatcher() {
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
                    mLpCoinInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                } else if (es.startsWith(".")) {
                    mLpCoinInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                    mLpCoinInput.setText("");
                } else if (es.endsWith(".")) {
                    mLpCoinInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                    mLpCoinInput.setVisibility(View.VISIBLE);
                } else if (es.length() > 1 && es.startsWith("0") && !es.startsWith("0.")) {
                    mLpCoinInput.setText("0");
                    mLpCoinInput.setSelection(1);
                }

                if (es.equals(mDecimalChecker)) {
                    mLpCoinInput.setText(mDecimalSetter);
                    mLpCoinInput.setSelection(mCoinDecimal + 1);
                } else {
                    try {
                        final BigDecimal inputAmount = new BigDecimal(es);
                        if (BigDecimal.ZERO.compareTo(inputAmount) >= 0) {
                            mLpCoinInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                            return;
                        }

                        BigDecimal checkPosition = inputAmount.movePointRight(mCoinDecimal);
                        BigDecimal checkMax = checkPosition.setScale(0, RoundingMode.DOWN);
                        if (checkPosition.compareTo(checkMax) != 0 || !checkPosition.equals(checkMax)) {
                            String recover = es.substring(0, es.length() - 1);
                            mLpCoinInput.setText(recover);
                            mLpCoinInput.setSelection(recover.length());
                            return;
                        }

                        if (inputAmount.compareTo(mAvailableMaxAmount.movePointLeft(mCoinDecimal).setScale(mCoinDecimal, RoundingMode.CEILING)) > 0) {
                            mLpCoinInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                        } else {
                            mLpCoinInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                        }
                        mLpCoinInput.setSelection(mLpCoinInput.getText().length());
                    } catch (Exception e) {
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mLpCoinClearBtn)) {
            mLpCoinInput.setText("");

        } else if (v.equals(mLpCoin1_4Btn)) {
            BigDecimal cal = mAvailableMaxAmount.multiply(new BigDecimal(0.25)).setScale(0, RoundingMode.DOWN);
            mLpCoinInput.setText(cal.movePointLeft(mCoinDecimal).toPlainString());

        } else if (v.equals(mLpCoinHalfBtn)) {
            BigDecimal cal = mAvailableMaxAmount.multiply(new BigDecimal(0.5)).setScale(0, RoundingMode.DOWN);
            mLpCoinInput.setText(cal.movePointLeft(mCoinDecimal).toPlainString());

        } else if (v.equals(mLpCoin3_4Btn)) {
            BigDecimal cal = mAvailableMaxAmount.multiply(new BigDecimal(0.75)).setScale(0, RoundingMode.DOWN);
            mLpCoinInput.setText(cal.movePointLeft(mCoinDecimal).toPlainString());

        } else if (v.equals(mLpCoinMaxBtn)) {
            mLpCoinInput.setText(mAvailableMaxAmount.movePointLeft(mCoinDecimal).toPlainString());
        } else if (v.equals(mCancelBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            if (isValidateAmount()) {
                getSActivity().onNextStep();
            } else {
                Toast.makeText(getContext(), R.string.error_invalid_amounts, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isValidateAmount() {
        try {
            BigDecimal amountTemp = new BigDecimal(mLpCoinInput.getText().toString().trim());
            if (amountTemp.compareTo(BigDecimal.ZERO) <= 0) return false;
            if (amountTemp.compareTo(mAvailableMaxAmount.movePointLeft(mCoinDecimal).setScale(mCoinDecimal, RoundingMode.CEILING)) > 0)
                return false;

            getSActivity().mKavaShareAmount = amountTemp.movePointRight(mCoinDecimal);
            BigDecimal depositRate = (getSActivity().mKavaShareAmount).divide(new BigDecimal(getSActivity().mKavaDepositPool.getSharesOwned()), 18, RoundingMode.DOWN);
            BigDecimal padding = new BigDecimal("0.97");
            BigDecimal coin0Amount = new BigDecimal(getSActivity().mKavaDepositPool.getSharesValue(0).getAmount()).multiply(padding).multiply(depositRate).setScale(0, RoundingMode.DOWN);
            BigDecimal coin1Amount = new BigDecimal(getSActivity().mKavaDepositPool.getSharesValue(1).getAmount()).multiply(padding).multiply(depositRate).setScale(0, RoundingMode.DOWN);

            getSActivity().mKavaPoolTokenA = new Coin(getSActivity().mKavaDepositPool.getSharesValue(0).getDenom(), coin0Amount.toPlainString());
            getSActivity().mKavaPoolTokenB = new Coin(getSActivity().mKavaDepositPool.getSharesValue(1).getDenom(), coin1Amount.toPlainString());

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    private void setDpDecimals(int decimals) {
        mDecimalChecker = "0.";
        mDecimalSetter = "0.";
        for (int i = 0; i < decimals; i++) {
            mDecimalChecker = mDecimalChecker + "0";
        }
        for (int i = 0; i < decimals - 1; i++) {
            mDecimalSetter = mDecimalSetter + "0";
        }
    }

    private WithDrawPoolActivity getSActivity() {
        return (WithDrawPoolActivity) getBaseActivity();
    }
}
