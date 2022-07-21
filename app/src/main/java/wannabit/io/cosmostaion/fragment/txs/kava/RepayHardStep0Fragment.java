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
import wannabit.io.cosmostaion.activities.txs.kava.RepayHardActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class RepayHardStep0Fragment extends BaseFragment implements View.OnClickListener {

    private Button mBtnCancel, mBtnNext;
    private ImageView mRepayImg;
    private TextView mRepaySymbol;
    private EditText mRepayInput;
    private ImageView mRepayClear;
    private TextView mRepayMaxTx, mRepayDenomTx;
    private Button mBtnAdd1, mBtnAdd1_4, mBtnAddHalf, mBtnAdd3_4, mBtnAddMax;

    private int mDpDecimal = 6;
    public String mHardMoneyMarketDenom;
    private String mDecimalChecker, mDecimalSetter, mDecimalDivider2, mDecimalDivider1;
    private BigDecimal mMaxAvailable = BigDecimal.ZERO;
    private BigDecimal mBorrowedAmount = BigDecimal.ZERO;

    public static RepayHardStep0Fragment newInstance(Bundle bundle) {
        RepayHardStep0Fragment fragment = new RepayHardStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_repay_hard_step0, container, false);
        mBtnCancel = rootView.findViewById(R.id.btn_cancel);
        mBtnNext = rootView.findViewById(R.id.btn_next);
        mRepayImg = rootView.findViewById(R.id.repay_icon);
        mRepaySymbol = rootView.findViewById(R.id.repay_symbol);
        mRepayInput = rootView.findViewById(R.id.repay_input);
        mRepayClear = rootView.findViewById(R.id.repay_clear);
        mRepayMaxTx = rootView.findViewById(R.id.repay_max_amount);
        mRepayDenomTx = rootView.findViewById(R.id.repay_denom);
        mBtnAdd1 = rootView.findViewById(R.id.btn_add_1);
        mBtnAdd1_4 = rootView.findViewById(R.id.btn_add_1_4);
        mBtnAddHalf = rootView.findViewById(R.id.btn_add_half);
        mBtnAdd3_4 = rootView.findViewById(R.id.btn_add_3_4);
        mBtnAddMax = rootView.findViewById(R.id.btn_add_max);

        mRepayClear.setOnClickListener(this);
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

        mBorrowedAmount = WUtil.getHardBorrowedAmountByDenom(getContext(), getBaseDao(), mHardMoneyMarketDenom, getBaseDao().mMyHardBorrows).multiply(new BigDecimal("1.05")).setScale(0, RoundingMode.DOWN);
        BigDecimal availableAmount = getBaseDao().getAvailable(mHardMoneyMarketDenom);
        mMaxAvailable = mBorrowedAmount.min(availableAmount);
        WDp.setDpCoin(getContext(), getBaseDao(), getSActivity().mChainConfig, mHardMoneyMarketDenom, mMaxAvailable.toPlainString(), mRepayDenomTx, mRepayMaxTx);

        WDp.setDpSymbolImg(getBaseDao(), getSActivity().mChainConfig, mHardMoneyMarketDenom, mRepayImg);
        WDp.setDpSymbol(getSActivity(), getBaseDao(), getSActivity().mChainConfig, mHardMoneyMarketDenom, mRepaySymbol);

        mRepayInput.addTextChangedListener(new TextWatcher() {
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
                    mRepayInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                } else if (es.startsWith(".")) {
                    mRepayInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                    mRepayInput.setText("");
                } else if (es.endsWith(".")) {
                    mRepayInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                    mRepayInput.setVisibility(View.VISIBLE);
                } else if (es.length() > 1 && es.startsWith("0") && !es.startsWith("0.")) {
                    mRepayInput.setText("0");
                    mRepayInput.setSelection(1);
                }

                if (es.equals(mDecimalChecker)) {
                    mRepayInput.setText(mDecimalSetter);
                    mRepayInput.setSelection(mDpDecimal + 1);
                } else {
                    try {
                        final BigDecimal inputAmount = new BigDecimal(es);
                        if (BigDecimal.ZERO.compareTo(inputAmount) >= 0) {
                            mRepayInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                            return;
                        }

                        BigDecimal checkPosition = inputAmount.movePointRight(mDpDecimal);
                        BigDecimal checkMax = checkPosition.setScale(0, RoundingMode.DOWN);
                        if (checkPosition.compareTo(checkMax) != 0 || !checkPosition.equals(checkMax)) {
                            String recover = es.substring(0, es.length() - 1);
                            mRepayInput.setText(recover);
                            mRepayInput.setSelection(recover.length());
                            return;
                        }
                        if (inputAmount.compareTo(mMaxAvailable.movePointLeft(mDpDecimal).setScale(mDpDecimal, RoundingMode.CEILING)) > 0) {
                            mRepayInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                        } else {
                            mRepayInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                        }
                        mRepayInput.setSelection(mRepayInput.getText().length());
                    } catch (Exception e) {
                    }
                }
            }
        });
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mRepayClear)) {
            mRepayInput.setText("");

        } else if (v.equals(mBtnAdd1)) {
            BigDecimal inputedAmount = BigDecimal.ZERO;
            try {
                inputedAmount = new BigDecimal(mRepayInput.getText().toString().trim());
            } catch (Exception e) {
            }
            inputedAmount = inputedAmount.add(new BigDecimal("1"));
            mRepayInput.setText(inputedAmount.toPlainString());

        } else if (v.equals(mBtnAdd1_4)) {
            try {
                BigDecimal cal = mMaxAvailable.divide(new BigDecimal(4), 0, RoundingMode.DOWN);
                mRepayInput.setText(cal.movePointLeft(mDpDecimal).toPlainString());
            } catch (Exception e) {
                mRepayInput.setText("");
            }

        } else if (v.equals(mBtnAddHalf)) {
            try {
                BigDecimal cal = mMaxAvailable.divide(new BigDecimal(2), 0, RoundingMode.DOWN);
                mRepayInput.setText(cal.movePointLeft(mDpDecimal).toPlainString());
            } catch (Exception e) {
                mRepayInput.setText("");
            }

        } else if (v.equals(mBtnAdd3_4)) {
            try {
                BigDecimal cal = mMaxAvailable.multiply(new BigDecimal(0.75)).setScale(0, RoundingMode.DOWN);
                mRepayInput.setText(cal.movePointLeft(mDpDecimal).toPlainString());
            } catch (Exception e) {
                mRepayInput.setText("");
            }

        } else if (v.equals(mBtnAddMax)) {
            mRepayInput.setText(mMaxAvailable.movePointLeft(mDpDecimal).toPlainString());

        } else if (v.equals(mBtnCancel)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mBtnNext)) {
            if (isValidateDepositAmount()) {
                getSActivity().onNextStep();
            }
        }
    }

    private boolean isValidateDepositAmount() {
        try {
            BigDecimal amountTemp = new BigDecimal(mRepayInput.getText().toString().trim());
            if (amountTemp.compareTo(BigDecimal.ZERO) <= 0) return false;
            if (amountTemp.compareTo(mMaxAvailable.movePointLeft(mDpDecimal).setScale(mDpDecimal, RoundingMode.CEILING)) > 0)
                return false;

            //check dusty borrow amount!
            final BigDecimal denomPrice = WUtil.getKavaPrice(getBaseDao(), mHardMoneyMarketDenom);
            BigDecimal remainAmount = mBorrowedAmount.subtract(amountTemp.movePointRight(mDpDecimal).setScale(0));
            BigDecimal remainValue = remainAmount.movePointLeft(mDpDecimal).multiply(denomPrice);
            if (remainValue.compareTo(BigDecimal.ZERO) > 0 && remainValue.compareTo(BigDecimal.TEN) < 0) {
                Toast.makeText(getContext(), R.string.error_remain_borrow_small, Toast.LENGTH_SHORT).show();
                return false;
            }

            Coin coin = new Coin(mHardMoneyMarketDenom, amountTemp.movePointRight(mDpDecimal).setScale(0).toPlainString());
            ArrayList<Coin> temp = new ArrayList<>();
            temp.add(coin);
            getSActivity().mHardPoolCoins = temp;
            return true;

        } catch (Exception e) {
            Toast.makeText(getContext(), R.string.error_invalid_amounts, Toast.LENGTH_SHORT).show();
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

    private RepayHardActivity getSActivity() {
        return (RepayHardActivity) getBaseActivity();
    }
}
