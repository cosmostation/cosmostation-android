package wannabit.io.cosmostaion.fragment.txs.common;

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

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.common.DelegateActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.CommonAlertDialog;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class DelegateStep0Fragment extends BaseFragment implements View.OnClickListener {

    private Button mCancel, mNextBtn;
    private EditText mAmountInput;
    private TextView mAvailableAmount;
    private TextView mDenomTitle;
    private ImageView mClearAll;
    private Button mAdd01, mAdd1, mAdd10, mAdd100, mAddHalf, mAddMax;
    private BigDecimal mMaxAvailable = BigDecimal.ZERO;
    private int mDpDecimal = 6;
    private String mDecimalChecker, mDecimalSetter;

    public static DelegateStep0Fragment newInstance() {
        return new DelegateStep0Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_delegate_step0, container, false);
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

        mMaxAvailable = getSActivity().getBaseDao().getDelegatable(getSActivity().mBaseChain, getSActivity().mChainConfig.mainDenom()).subtract(WDp.getMainDenomFee(getActivity(), getBaseDao(), getSActivity().mChainConfig));
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
                if (TextUtils.isEmpty(es)) {
                    mAmountInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
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

                        if (inputAmount.compareTo(mMaxAvailable.movePointLeft(mDpDecimal).setScale(mDpDecimal, RoundingMode.CEILING)) > 0) {
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
            if (isValidateDelegateAmount()) {
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
            WLog.w("es " + es);
            if (es.length() > 0) {
                existed = new BigDecimal(es);
            }
            WLog.w("existed " + existed);
            WLog.w("add " + existed.add(new BigDecimal("10")).toPlainString());
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
            onShowEmptyBalanceWarnDialog();

        } else if (v.equals(mClearAll)) {
            mAmountInput.setText("");

        }

    }

    private boolean isValidateDelegateAmount() {
        try {
            BigDecimal amountTemp = new BigDecimal(mAmountInput.getText().toString().trim());
            if (amountTemp.compareTo(BigDecimal.ZERO) <= 0) return false;
            if (amountTemp.compareTo(mMaxAvailable.movePointLeft(mDpDecimal).setScale(mDpDecimal, RoundingMode.CEILING)) > 0)
                return false;
            Coin coin = new Coin(getSActivity().mChainConfig.mainDenom(), amountTemp.movePointRight(mDpDecimal).setScale(0).toPlainString());
            getSActivity().mAmount = coin;
            return true;

        } catch (Exception e) {
            getSActivity().mAmount = null;
            return false;
        }
    }

    private void onShowEmptyBalanceWarnDialog() {
        CommonAlertDialog.showSingleButton(getSActivity(), getString(R.string.str_empty_warnning_title), getString(R.string.str_empty_warnning_msg), getString(R.string.str_close), null);
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

    private DelegateActivity getSActivity() {
        return (DelegateActivity) getBaseActivity();
    }
}
