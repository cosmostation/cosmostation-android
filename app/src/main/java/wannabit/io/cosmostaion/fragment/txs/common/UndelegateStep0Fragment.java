package wannabit.io.cosmostaion.fragment.txs.common;

import android.os.Bundle;
import android.text.Editable;
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

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.common.UndelegateActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;

public class UndelegateStep0Fragment extends BaseFragment implements View.OnClickListener {

    private Button mCancel, mNextBtn;
    private EditText mAmountInput;
    private TextView mAvailableAmount;
    private TextView mDenomTitle;
    private ImageView mClearAll;
    private Button mAdd01, mAdd1, mAdd10, mAdd100, mAddHalf, mAddMax;
    private BigDecimal mMaxAvailable = BigDecimal.ZERO;
    private int mDpDecimal = 6;
    private String mDecimalChecker, mDecimalSetter;

    public static UndelegateStep0Fragment newInstance() {
        return new UndelegateStep0Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_undelegate_step0, container, false);
        mCancel = rootView.findViewById(R.id.btn_cancel);
        mNextBtn = rootView.findViewById(R.id.btn_next);
        mAmountInput = rootView.findViewById(R.id.et_amount_coin);
        mAvailableAmount = rootView.findViewById(R.id.tv_max_coin);
        mDenomTitle = rootView.findViewById(R.id.tv_symbol_coin);
        mClearAll = rootView.findViewById(R.id.clearAll);
        mAdd01 = rootView.findViewById(R.id.btn_add_01);
        mAdd1 = rootView.findViewById(R.id.btn_add_1);
        mAdd10 = rootView.findViewById(R.id.btn_add_10);
        mAdd100 = rootView.findViewById(R.id.btn_add_100);
        mAddHalf = rootView.findViewById(R.id.btn_add_half);
        mAddMax = rootView.findViewById(R.id.btn_add_all);
        mCancel.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        mClearAll.setOnClickListener(this);
        mAdd01.setOnClickListener(this);
        mAdd1.setOnClickListener(this);
        mAdd10.setOnClickListener(this);
        mAdd100.setOnClickListener(this);
        mAddHalf.setOnClickListener(this);
        mAddMax.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isAdded() || getSActivity() == null || getSActivity().mAccount == null)
            getSActivity().onBackPressed();
        mDpDecimal = WDp.getDenomDecimal(getBaseDao(), getSActivity().mChainConfig, getSActivity().mChainConfig.mainDenom());
        setDpDecimals(mDpDecimal);
        WDp.setDpSymbol(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mChainConfig.mainDenom(), mDenomTitle);

        mMaxAvailable = getBaseDao().getDelegation(getSActivity().mValAddress);
        mAvailableAmount.setText(WDp.getDpAmount2(getContext(), mMaxAvailable, mDpDecimal, mDpDecimal));
        onAddAmountWatcher();
    }

    private void onAddAmountWatcher() {
        mAmountInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable et) {
                String es = et.toString().trim();
                if (es == null || es.length() == 0) {
                    mAmountInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                } else if (es.startsWith(".")) {
                    mAmountInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                    mAmountInput.setText("");
                } else if (es.endsWith(".")) {
                    mAmountInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                    mAmountInput.setVisibility(View.VISIBLE);
                } else if (es.length() > 1 && es.startsWith("0") && !es.startsWith("0.")) {
                    mAmountInput.setText("0");
                    mAmountInput.setSelection(1);
                }

                if (es.equals(mDecimalChecker)) {
                    mAmountInput.setText(mDecimalSetter);
                    mAmountInput.setSelection(mDpDecimal + 1);
                } else {
                    try {
                        final BigDecimal inputAmount = new BigDecimal(es);
                        if (BigDecimal.ZERO.compareTo(inputAmount) >= 0) {
                            mAmountInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                            return;
                        }

                        BigDecimal checkPosition = inputAmount.movePointRight(mDpDecimal);
                        BigDecimal checkMax = checkPosition.setScale(0, RoundingMode.DOWN);
                        if (checkPosition.compareTo(checkMax) != 0 || !checkPosition.equals(checkMax)) {
                            String recover = es.substring(0, es.length() - 1);
                            mAmountInput.setText(recover);
                            mAmountInput.setSelection(recover.length());
                            return;
                        }

                        if (inputAmount.compareTo(mMaxAvailable.movePointLeft(mDpDecimal).setScale(mDpDecimal, RoundingMode.DOWN)) > 0) {
                            mAmountInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                        } else {
                            mAmountInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                        }
                    } catch (Exception e) { }
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v.equals(mCancel)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            if (isValidateUnDelegateAmount()) {
                getSActivity().onNextStep();
            } else {
                Toast.makeText(getContext(), R.string.error_invalid_amounts, Toast.LENGTH_SHORT).show();
            }

        } else if (v.equals(mAdd01)) {
            BigDecimal existed = BigDecimal.ZERO;
            String es = mAmountInput.getText().toString().trim();
            if (es.length() > 0) {
                existed = new BigDecimal(es);
            }
            mAmountInput.setText(existed.add(new BigDecimal("0.1")).toPlainString());

        } else if (v.equals(mAdd1)) {
            BigDecimal existed = BigDecimal.ZERO;
            String es = mAmountInput.getText().toString().trim();
            if (es.length() > 0) {
                existed = new BigDecimal(es);
            }
            mAmountInput.setText(existed.add(new BigDecimal("1")).toPlainString());

        } else if (v.equals(mAdd10)) {
            BigDecimal existed = BigDecimal.ZERO;
            String es = mAmountInput.getText().toString().trim();
            if (es.length() > 0) {
                existed = new BigDecimal(es);
            }
            mAmountInput.setText(existed.add(new BigDecimal("10")).toPlainString());

        } else if (v.equals(mAdd100)) {
            BigDecimal existed = BigDecimal.ZERO;
            String es = mAmountInput.getText().toString().trim();
            if (es.length() > 0) {
                existed = new BigDecimal(es);
            }
            mAmountInput.setText(existed.add(new BigDecimal("100")).toPlainString());

        } else if (v.equals(mAddHalf)) {
            BigDecimal half = mMaxAvailable.movePointLeft(mDpDecimal).divide(new BigDecimal("2"), mDpDecimal, RoundingMode.DOWN);
            mAmountInput.setText(half.toPlainString());

        } else if (v.equals(mAddMax)) {
            BigDecimal max = mMaxAvailable.movePointLeft(mDpDecimal).setScale(mDpDecimal, RoundingMode.DOWN);
            mAmountInput.setText(max.toPlainString());

        } else if (v.equals(mClearAll)) {
            mAmountInput.setText("");

        }
    }

    private boolean isValidateUnDelegateAmount() {
        try {
            BigDecimal userInput = new BigDecimal(mAmountInput.getText().toString().trim()).movePointRight(mDpDecimal).setScale(0);
            if (userInput.compareTo(BigDecimal.ZERO) <= 0) return false;
            if (userInput.compareTo(mMaxAvailable) > 0) return false;
            Coin coin = new Coin(getSActivity().mChainConfig.mainDenom(), userInput.toPlainString());
            getSActivity().mAmount = coin;
            return true;

        } catch (Exception e) {
            getSActivity().mAmount = null;
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

    private UndelegateActivity getSActivity() {
        return (UndelegateActivity) getBaseActivity();
    }
}
