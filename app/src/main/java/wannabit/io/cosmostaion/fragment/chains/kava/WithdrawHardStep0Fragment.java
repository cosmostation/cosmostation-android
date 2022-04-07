package wannabit.io.cosmostaion.fragment.chains.kava;

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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.WithdrawHardActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class WithdrawHardStep0Fragment extends BaseFragment implements View.OnClickListener {

    private Button mBtnCancel, mBtnNext;
    private ImageView mWithdrawImg;
    private TextView mWithdrawSymbol;
    private EditText mWithdrawInput;
    private ImageView mWithdrawClear;
    private TextView mDWithdrawMaxTx, mWithdrawDenomTx;
    private Button mBtnAdd1, mBtnAdd1_4, mBtnAddHalf, mBtnAdd3_4, mBtnAddMax;

    private int mDpDecimal = 6;
    public String mHardMoneyMarketDenom;
    private String mDecimalChecker, mDecimalSetter, mDecimalDivider2, mDecimalDivider1;
    private BigDecimal mMaxAvailable = BigDecimal.ZERO;

    public static WithdrawHardStep0Fragment newInstance(Bundle bundle) {
        WithdrawHardStep0Fragment fragment = new WithdrawHardStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_withdraw_hard_step0, container, false);
        mBtnCancel = rootView.findViewById(R.id.cancelButton);
        mBtnNext = rootView.findViewById(R.id.nextButton);
        mWithdrawImg = rootView.findViewById(R.id.withdraw_icon);
        mWithdrawSymbol = rootView.findViewById(R.id.withdraw_symbol);
        mWithdrawInput = rootView.findViewById(R.id.withdraw_input);
        mWithdrawClear = rootView.findViewById(R.id.withdraw_clear);
        mDWithdrawMaxTx = rootView.findViewById(R.id.withdraw_max_amount);
        mWithdrawDenomTx = rootView.findViewById(R.id.withdraw_denom);
        mBtnAdd1 = rootView.findViewById(R.id.btn_add_1);
        mBtnAdd1_4 = rootView.findViewById(R.id.btn_add_1_4);
        mBtnAddHalf = rootView.findViewById(R.id.btn_add_half);
        mBtnAdd3_4 = rootView.findViewById(R.id.btn_add_3_4);
        mBtnAddMax = rootView.findViewById(R.id.btn_add_max);

        mWithdrawClear.setOnClickListener(this);
        mBtnAdd1.setOnClickListener(this);
        mBtnAdd1_4.setOnClickListener(this);
        mBtnAddHalf.setOnClickListener(this);
        mBtnAdd3_4.setOnClickListener(this);
        mBtnAddMax.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);

        mHardMoneyMarketDenom = getSActivity().mHardMoneyMarketDenom;
        mDpDecimal = WUtil.getKavaCoinDecimal(getBaseDao(), mHardMoneyMarketDenom);
        setDpDecimals(mDpDecimal);

        mMaxAvailable = WUtil.getHardSuppliedAmountByDenom(getContext(), getBaseDao(), mHardMoneyMarketDenom, getBaseDao().mMyHardDeposits);
        WDp.showCoinDp(getContext(), getBaseDao(), mHardMoneyMarketDenom, mMaxAvailable.toPlainString(), mWithdrawDenomTx, mDWithdrawMaxTx, getSActivity().baseChain);
        WUtil.DpKavaTokenImg(getBaseDao(), mWithdrawImg, mHardMoneyMarketDenom);
        WUtil.dpKavaTokenName(getContext(), getBaseDao(), mWithdrawSymbol, mHardMoneyMarketDenom);

        mWithdrawInput.addTextChangedListener(new TextWatcher() {
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
                    mWithdrawInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                } else if (es.startsWith(".")) {
                    mWithdrawInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                    mWithdrawInput.setText("");
                } else if (es.endsWith(".")) {
                    mWithdrawInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                    mWithdrawInput.setVisibility(View.VISIBLE);
                } else if (es.length() > 1 && es.startsWith("0") && !es.startsWith("0.")) {
                    mWithdrawInput.setText("0");
                    mWithdrawInput.setSelection(1);
                }

                if (es.equals(mDecimalChecker)) {
                    mWithdrawInput.setText(mDecimalSetter);
                    mWithdrawInput.setSelection(mDpDecimal + 1);
                } else {
                    try {
                        final BigDecimal inputAmount = new BigDecimal(es);
                        if (BigDecimal.ZERO.compareTo(inputAmount) >= 0) {
                            mWithdrawInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                            return;
                        }

                        BigDecimal checkPosition = inputAmount.movePointRight(mDpDecimal);
                        BigDecimal checkMax = checkPosition.setScale(0, RoundingMode.DOWN);
                        if (checkPosition.compareTo(checkMax) != 0 || !checkPosition.equals(checkMax)) {
                            String recover = es.substring(0, es.length() - 1);
                            mWithdrawInput.setText(recover);
                            mWithdrawInput.setSelection(recover.length());
                            return;
                        }
                        if (inputAmount.compareTo(mMaxAvailable.movePointLeft(mDpDecimal).setScale(mDpDecimal, RoundingMode.CEILING)) > 0) {
                            mWithdrawInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                        } else {
                            mWithdrawInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                        }
                        mWithdrawInput.setSelection(mWithdrawInput.getText().length());
                    } catch (Exception e) {
                    }
                }
            }
        });
        return rootView;
    }


    @Override
    public void onClick(View v) {
        if (v.equals(mWithdrawClear)) {
            mWithdrawInput.setText("");

        } else if (v.equals(mBtnAdd1)) {
            BigDecimal inputedAmount = BigDecimal.ZERO;
            try {
                inputedAmount = new BigDecimal(mWithdrawInput.getText().toString().trim());
            } catch (Exception e) {
            }
            inputedAmount = inputedAmount.add(new BigDecimal("1"));
            mWithdrawInput.setText(inputedAmount.toPlainString());

        } else if (v.equals(mBtnAdd1_4)) {
            try {
                BigDecimal cal = mMaxAvailable.divide(new BigDecimal(4), 0, RoundingMode.DOWN);
                mWithdrawInput.setText(cal.movePointLeft(mDpDecimal).toPlainString());
            } catch (Exception e) {
                mWithdrawInput.setText("");
            }

        } else if (v.equals(mBtnAddHalf)) {
            try {
                BigDecimal cal = mMaxAvailable.divide(new BigDecimal(2), 0, RoundingMode.DOWN);
                mWithdrawInput.setText(cal.movePointLeft(mDpDecimal).toPlainString());
            } catch (Exception e) {
                mWithdrawInput.setText("");
            }

        } else if (v.equals(mBtnAdd3_4)) {
            try {
                BigDecimal cal = mMaxAvailable.multiply(new BigDecimal(0.75)).setScale(0, RoundingMode.DOWN);
                mWithdrawInput.setText(cal.movePointLeft(mDpDecimal).toPlainString());
            } catch (Exception e) {
                mWithdrawInput.setText("");
            }

        } else if (v.equals(mBtnAddMax)) {
            mWithdrawInput.setText(mMaxAvailable.movePointLeft(mDpDecimal).toPlainString());

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
            BigDecimal amountTemp = new BigDecimal(mWithdrawInput.getText().toString().trim());
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


    private WithdrawHardActivity getSActivity() {
        return (WithdrawHardActivity) getBaseActivity();
    }
}