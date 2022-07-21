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
import wannabit.io.cosmostaion.activities.txs.kava.BorrowHardActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class BorrowHardStep0Fragment extends BaseFragment implements View.OnClickListener {

    private Button mBtnCancel, mBtnNext;
    private ImageView mBorrowImg;
    private TextView mBorrowSymbol;
    private EditText mBorrowInput;
    private ImageView mBorrowClear;
    private TextView mBorrowMaxTx, mBorrowDenomTx;
    private Button mBtnAdd1, mBtnAdd1_4, mBtnAddHalf, mBtnAdd3_4, mBtnAddMax;

    private int mDpDecimal = 6;
    public String mHardMoneyMarketDenom;
    private String mDecimalChecker, mDecimalSetter, mDecimalDivider2, mDecimalDivider1;
    private BigDecimal mMaxAvailable = BigDecimal.ZERO;

    public static BorrowHardStep0Fragment newInstance(Bundle bundle) {
        BorrowHardStep0Fragment fragment = new BorrowHardStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_borrow_hard_step0, container, false);
        mBtnCancel = rootView.findViewById(R.id.btn_cancel);
        mBtnNext = rootView.findViewById(R.id.btn_next);
        mBorrowImg = rootView.findViewById(R.id.borrow_icon);
        mBorrowSymbol = rootView.findViewById(R.id.borrow_symbol);
        mBorrowInput = rootView.findViewById(R.id.borrow_input);
        mBorrowClear = rootView.findViewById(R.id.borrow_clear);
        mBorrowMaxTx = rootView.findViewById(R.id.borrow_max_amount);
        mBorrowDenomTx = rootView.findViewById(R.id.borrow_denom);
        mBtnAdd1 = rootView.findViewById(R.id.btn_add_1);
        mBtnAdd1_4 = rootView.findViewById(R.id.btn_add_1_4);
        mBtnAddHalf = rootView.findViewById(R.id.btn_add_half);
        mBtnAdd3_4 = rootView.findViewById(R.id.btn_add_3_4);
        mBtnAddMax = rootView.findViewById(R.id.btn_add_max);

        mBorrowClear.setOnClickListener(this);
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

        // display borrowable amount with padding 5%
        mMaxAvailable = WUtil.getHardBorrowableAmountByDenom(getContext(), getBaseDao(), mHardMoneyMarketDenom,
                getBaseDao().mMyHardDeposits, getBaseDao().mMyHardBorrows, getBaseDao().mModuleCoins, getBaseDao().mReserveCoins);

        WDp.setDpCoin(getContext(), getBaseDao(), getSActivity().mChainConfig, mHardMoneyMarketDenom, mMaxAvailable.toPlainString(), mBorrowDenomTx, mBorrowMaxTx);
        WDp.setDpSymbolImg(getBaseDao(), getSActivity().mChainConfig, mHardMoneyMarketDenom, mBorrowImg);
        WDp.setDpSymbol(getSActivity(), getBaseDao(), getSActivity().mChainConfig, mHardMoneyMarketDenom, mBorrowSymbol);

        mBorrowInput.addTextChangedListener(new TextWatcher() {
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
                    mBorrowInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                } else if (es.startsWith(".")) {
                    mBorrowInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                    mBorrowInput.setText("");
                } else if (es.endsWith(".")) {
                    mBorrowInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                    mBorrowInput.setVisibility(View.VISIBLE);
                } else if (es.length() > 1 && es.startsWith("0") && !es.startsWith("0.")) {
                    mBorrowInput.setText("0");
                    mBorrowInput.setSelection(1);
                }

                if (es.equals(mDecimalChecker)) {
                    mBorrowInput.setText(mDecimalSetter);
                    mBorrowInput.setSelection(mDpDecimal + 1);
                } else {
                    try {
                        final BigDecimal inputAmount = new BigDecimal(es);
                        if (BigDecimal.ZERO.compareTo(inputAmount) >= 0) {
                            mBorrowInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                            return;
                        }

                        BigDecimal checkPosition = inputAmount.movePointRight(mDpDecimal);
                        BigDecimal checkMax = checkPosition.setScale(0, RoundingMode.DOWN);
                        if (checkPosition.compareTo(checkMax) != 0 || !checkPosition.equals(checkMax)) {
                            String recover = es.substring(0, es.length() - 1);
                            mBorrowInput.setText(recover);
                            mBorrowInput.setSelection(recover.length());
                            return;
                        }
                        if (inputAmount.compareTo(mMaxAvailable.movePointLeft(mDpDecimal).setScale(mDpDecimal, RoundingMode.CEILING)) > 0) {
                            mBorrowInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                        } else {
                            mBorrowInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                        }
                        mBorrowInput.setSelection(mBorrowInput.getText().length());
                    } catch (Exception e) {
                    }
                }
            }
        });
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBorrowClear)) {
            mBorrowInput.setText("");

        } else if (v.equals(mBtnAdd1)) {
            BigDecimal inputedAmount = BigDecimal.ZERO;
            try {
                inputedAmount = new BigDecimal(mBorrowInput.getText().toString().trim());
            } catch (Exception e) {
            }
            inputedAmount = inputedAmount.add(new BigDecimal("1"));
            mBorrowInput.setText(inputedAmount.toPlainString());

        } else if (v.equals(mBtnAdd1_4)) {
            try {
                BigDecimal cal = mMaxAvailable.divide(new BigDecimal(4), 0, RoundingMode.DOWN);
                mBorrowInput.setText(cal.movePointLeft(mDpDecimal).toPlainString());
            } catch (Exception e) {
                mBorrowInput.setText("");
            }

        } else if (v.equals(mBtnAddHalf)) {
            try {
                BigDecimal cal = mMaxAvailable.divide(new BigDecimal(2), 0, RoundingMode.DOWN);
                mBorrowInput.setText(cal.movePointLeft(mDpDecimal).toPlainString());
            } catch (Exception e) {
                mBorrowInput.setText("");
            }

        } else if (v.equals(mBtnAdd3_4)) {
            try {
                BigDecimal cal = mMaxAvailable.multiply(new BigDecimal(0.75)).setScale(0, RoundingMode.DOWN);
                mBorrowInput.setText(cal.movePointLeft(mDpDecimal).toPlainString());
            } catch (Exception e) {
                mBorrowInput.setText("");
            }

        } else if (v.equals(mBtnAddMax)) {
            mBorrowInput.setText(mMaxAvailable.movePointLeft(mDpDecimal).toPlainString());

        } else if (v.equals(mBtnCancel)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mBtnNext)) {
            if (isValidateBorrowAmount()) {
                getSActivity().onNextStep();
            } else {
                Toast.makeText(getContext(), R.string.error_invalid_amounts, Toast.LENGTH_SHORT).show();
            }
        }
    }


    private boolean isValidateBorrowAmount() {
        try {
            BigDecimal amountTemp = new BigDecimal(mBorrowInput.getText().toString().trim());
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

    private BorrowHardActivity getSActivity() {
        return (BorrowHardActivity) getBaseActivity();
    }


}
