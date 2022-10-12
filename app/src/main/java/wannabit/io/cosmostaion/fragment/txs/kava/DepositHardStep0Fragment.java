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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.kava.DepositHardActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;

public class DepositHardStep0Fragment extends BaseFragment implements View.OnClickListener {

    private Button mBtnCancel, mBtnNext;
    private ImageView mDepositImg;
    private TextView mDepositSymbol;
    private EditText mDepositInput;
    private ImageView mDepositClear;
    private TextView mDepositMaxTx, mDepositDenomTx;
    private Button mBtnAdd1, mBtnAdd1_4, mBtnAddHalf, mBtnAdd3_4, mBtnAddMax;

    private int mDpDecimal = 6;
    private String mHardMoneyMarketDenom;
    private String mDecimalChecker, mDecimalSetter, mDecimalDivider2, mDecimalDivider1;
    private BigDecimal mMaxAvailable = BigDecimal.ZERO;

    public static DepositHardStep0Fragment newInstance() {
        return new DepositHardStep0Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_deposit_hard_step0, container, false);
        mBtnCancel = rootView.findViewById(R.id.btn_cancel);
        mBtnNext = rootView.findViewById(R.id.btn_next);
        mDepositImg = rootView.findViewById(R.id.deposit_icon);
        mDepositSymbol = rootView.findViewById(R.id.deposit_symbol);
        mDepositInput = rootView.findViewById(R.id.deposit_input);
        mDepositClear = rootView.findViewById(R.id.deposit_clear);
        mDepositMaxTx = rootView.findViewById(R.id.deposit_max_amount);
        mDepositDenomTx = rootView.findViewById(R.id.deposit_denom);
        mBtnAdd1 = rootView.findViewById(R.id.btn_add_1);
        mBtnAdd1_4 = rootView.findViewById(R.id.btn_add_1_4);
        mBtnAddHalf = rootView.findViewById(R.id.btn_add_half);
        mBtnAdd3_4 = rootView.findViewById(R.id.btn_add_3_4);
        mBtnAddMax = rootView.findViewById(R.id.btn_add_max);

        mDepositClear.setOnClickListener(this);
        mBtnAdd1.setOnClickListener(this);
        mBtnAdd1_4.setOnClickListener(this);
        mBtnAddHalf.setOnClickListener(this);
        mBtnAdd3_4.setOnClickListener(this);
        mBtnAddMax.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);

        mHardMoneyMarketDenom = getSActivity().mHardMoneyMarketDenom;
        mDpDecimal = WDp.getDenomDecimal(getBaseDao(), getSActivity().mChainConfig, mHardMoneyMarketDenom);
        setDpDecimals(mDpDecimal);
        mMaxAvailable = getBaseDao().getAvailable(mHardMoneyMarketDenom);
        WDp.setDpCoin(getContext(), getBaseDao(), getSActivity().mChainConfig, mHardMoneyMarketDenom, mMaxAvailable.toPlainString(), mDepositDenomTx, mDepositMaxTx);

        WDp.setDpSymbolImg(getBaseDao(), getSActivity().mChainConfig, mHardMoneyMarketDenom, mDepositImg);
        WDp.setDpSymbol(getSActivity(), getBaseDao(), getSActivity().mChainConfig, mHardMoneyMarketDenom, mDepositSymbol);

        mDepositInput.addTextChangedListener(new TextWatcher() {
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
                    mDepositInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                } else if (es.startsWith(".")) {
                    mDepositInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                    mDepositInput.setText("");
                } else if (es.endsWith(".")) {
                    mDepositInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                    mDepositInput.setVisibility(View.VISIBLE);
                } else if (es.length() > 1 && es.startsWith("0") && !es.startsWith("0.")) {
                    mDepositInput.setText("0");
                    mDepositInput.setSelection(1);
                }

                if (es.equals(mDecimalChecker)) {
                    mDepositInput.setText(mDecimalSetter);
                    mDepositInput.setSelection(mDpDecimal + 1);
                } else {
                    try {
                        final BigDecimal inputAmount = new BigDecimal(es);
                        if (BigDecimal.ZERO.compareTo(inputAmount) >= 0) {
                            mDepositInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                            return;
                        }

                        BigDecimal checkPosition = inputAmount.movePointRight(mDpDecimal);
                        BigDecimal checkMax = checkPosition.setScale(0, RoundingMode.DOWN);
                        if (checkPosition.compareTo(checkMax) != 0 || !checkPosition.equals(checkMax)) {
                            String recover = es.substring(0, es.length() - 1);
                            mDepositInput.setText(recover);
                            mDepositInput.setSelection(recover.length());
                            return;
                        }
                        if (inputAmount.compareTo(mMaxAvailable.movePointLeft(mDpDecimal).setScale(mDpDecimal, RoundingMode.CEILING)) > 0) {
                            mDepositInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                        } else {
                            mDepositInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                        }
                        mDepositInput.setSelection(mDepositInput.getText().length());
                    } catch (Exception e) {
                    }
                }
            }
        });
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mDepositClear)) {
            mDepositInput.setText("");

        } else if (v.equals(mBtnAdd1)) {
            BigDecimal inputedAmount = BigDecimal.ZERO;
            try {
                inputedAmount = new BigDecimal(mDepositInput.getText().toString().trim());
            } catch (Exception e) {
            }
            inputedAmount = inputedAmount.add(new BigDecimal("1"));
            mDepositInput.setText(inputedAmount.toPlainString());

        } else if (v.equals(mBtnAdd1_4)) {
            try {
                BigDecimal cal = mMaxAvailable.divide(new BigDecimal(4), 0, RoundingMode.DOWN);
                mDepositInput.setText(cal.movePointLeft(mDpDecimal).toPlainString());
            } catch (Exception e) {
                mDepositInput.setText("");
            }

        } else if (v.equals(mBtnAddHalf)) {
            try {
                BigDecimal cal = mMaxAvailable.divide(new BigDecimal(2), 0, RoundingMode.DOWN);
                mDepositInput.setText(cal.movePointLeft(mDpDecimal).toPlainString());
            } catch (Exception e) {
                mDepositInput.setText("");
            }

        } else if (v.equals(mBtnAdd3_4)) {
            try {
                BigDecimal cal = mMaxAvailable.multiply(new BigDecimal(0.75)).setScale(0, RoundingMode.DOWN);
                mDepositInput.setText(cal.movePointLeft(mDpDecimal).toPlainString());
            } catch (Exception e) {
                mDepositInput.setText("");
            }

        } else if (v.equals(mBtnAddMax)) {
            mDepositInput.setText(mMaxAvailable.movePointLeft(mDpDecimal).toPlainString());

        } else if (v.equals(mBtnCancel)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mBtnNext)) {
            if (isValidateDepositAmount()) {
                getSActivity().onNextStep();
            } else {
                Toast.makeText(getContext(), R.string.error_invalid_amounts, Toast.LENGTH_SHORT).show();
            }
        }

    }

    private boolean isValidateDepositAmount() {
        try {
            BigDecimal amountTemp = new BigDecimal(mDepositInput.getText().toString().trim());
            if (amountTemp.compareTo(BigDecimal.ZERO) <= 0) return false;
            if (amountTemp.compareTo(mMaxAvailable.movePointLeft(mDpDecimal).setScale(mDpDecimal, RoundingMode.CEILING)) > 0)
                return false;
            Coin coin = new Coin(mHardMoneyMarketDenom, amountTemp.movePointRight(mDpDecimal).setScale(0).toPlainString());
            ArrayList<Coin> temp = new ArrayList<>();
            temp.add(coin);
            getSActivity().mHardPoolCoins = temp;
            return true;

        } catch (Exception e) {
            getSActivity().mHardPoolCoins = null;
            return false;
        }

    }

    private void setDpDecimals(int decimals) {
        mDecimalChecker = "0.";
        mDecimalSetter = "0.";
        mDecimalDivider2 = "2";
        mDecimalDivider1 = "1";
        for (int i = 0; i < decimals; i++) {
            mDecimalChecker = mDecimalChecker + "0";
            mDecimalDivider2 = mDecimalDivider2 + "0";
            mDecimalDivider1 = mDecimalDivider1 + "0";
        }
        for (int i = 0; i < decimals - 1; i++) {
            mDecimalSetter = mDecimalSetter + "0";
        }
    }

    private DepositHardActivity getSActivity() {
        return (DepositHardActivity) getBaseActivity();
    }
}
