package wannabit.io.cosmostaion.fragment.txs.common;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.math.BigDecimal;
import java.math.RoundingMode;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.common.DelegateActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.databinding.FragmentDelegateStep0Binding;
import wannabit.io.cosmostaion.dialog.CommonAlertDialog;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class DelegateStep0Fragment extends BaseFragment implements View.OnClickListener {

    private FragmentDelegateStep0Binding fragmentDelegateStep0Binding;

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
        fragmentDelegateStep0Binding = FragmentDelegateStep0Binding.inflate(getLayoutInflater());
        fragmentDelegateStep0Binding.btnCancel.setOnClickListener(this);
        fragmentDelegateStep0Binding.btnNext.setOnClickListener(this);
        fragmentDelegateStep0Binding.clearAll.setOnClickListener(this);
        fragmentDelegateStep0Binding.btnAdd01.setOnClickListener(this);
        fragmentDelegateStep0Binding.btnAdd1.setOnClickListener(this);
        fragmentDelegateStep0Binding.btnAdd10.setOnClickListener(this);
        fragmentDelegateStep0Binding.btnAdd100.setOnClickListener(this);
        fragmentDelegateStep0Binding.btnAddHalf.setOnClickListener(this);
        fragmentDelegateStep0Binding.btnAddAll.setOnClickListener(this);
        return fragmentDelegateStep0Binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isAdded() || getSActivity() == null || getSActivity().mAccount == null)
            getSActivity().onBackPressed();
        mDpDecimal = WDp.getDenomDecimal(getBaseDao(), getSActivity().mChainConfig, getSActivity().mChainConfig.mainDenom());
        setDpDecimals(mDpDecimal);
        WDp.setDpSymbol(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mChainConfig.mainDenom(), fragmentDelegateStep0Binding.tvSymbolCoin);

        mMaxAvailable = getSActivity().getBaseDao().getDelegatable(getSActivity().mBaseChain, getSActivity().mChainConfig.mainDenom()).subtract(WDp.getMainDenomFee(getActivity(), getBaseDao(), getSActivity().mChainConfig));
        fragmentDelegateStep0Binding.tvMaxCoin.setText(WDp.getDpAmount2(getContext(), mMaxAvailable, mDpDecimal, mDpDecimal));
        onAddAmountWatcher();
    }

    private void onAddAmountWatcher() {
        fragmentDelegateStep0Binding.etAmountCoin.addTextChangedListener(new TextWatcher() {
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
                    fragmentDelegateStep0Binding.etAmountCoin.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                } else if (es.startsWith(".")) {
                    fragmentDelegateStep0Binding.etAmountCoin.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                    fragmentDelegateStep0Binding.etAmountCoin.setText("");
                } else if (es.endsWith(".")) {
                    fragmentDelegateStep0Binding.etAmountCoin.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                    fragmentDelegateStep0Binding.etAmountCoin.setVisibility(View.VISIBLE);
                } else if (es.length() > 1 && es.startsWith("0") && !es.startsWith("0.")) {
                    fragmentDelegateStep0Binding.etAmountCoin.setText("0");
                    fragmentDelegateStep0Binding.etAmountCoin.setSelection(1);
                }

                if (es.equals(mDecimalChecker)) {
                    fragmentDelegateStep0Binding.etAmountCoin.setText(mDecimalSetter);
                    fragmentDelegateStep0Binding.etAmountCoin.setSelection(mDpDecimal + 1);
                } else {
                    try {
                        final BigDecimal inputAmount = new BigDecimal(es);
                        if (BigDecimal.ZERO.compareTo(inputAmount) >= 0) {
                            fragmentDelegateStep0Binding.etAmountCoin.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                            return;
                        }

                        BigDecimal checkPosition = inputAmount.movePointRight(mDpDecimal);
                        BigDecimal checkMax = checkPosition.setScale(0, RoundingMode.DOWN);
                        if (checkPosition.compareTo(checkMax) != 0 || !checkPosition.equals(checkMax)) {
                            String recover = es.substring(0, es.length() - 1);
                            fragmentDelegateStep0Binding.etAmountCoin.setText(recover);
                            fragmentDelegateStep0Binding.etAmountCoin.setSelection(recover.length());
                            return;
                        }

                        if (inputAmount.compareTo(mMaxAvailable.movePointLeft(mDpDecimal).setScale(mDpDecimal, RoundingMode.CEILING)) > 0) {
                            fragmentDelegateStep0Binding.etAmountCoin.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                        } else {
                            fragmentDelegateStep0Binding.etAmountCoin.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                        }
                        fragmentDelegateStep0Binding.etAmountCoin.setSelection(fragmentDelegateStep0Binding.etAmountCoin.getText().length());
                    } catch (Exception e) {
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.equals(fragmentDelegateStep0Binding.btnCancel)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(fragmentDelegateStep0Binding.btnNext)) {
            if (isValidateDelegateAmount()) {
                getSActivity().onNextStep();
            } else {
                Toast.makeText(getContext(), R.string.error_invalid_amounts, Toast.LENGTH_SHORT).show();
            }

        } else if (v.equals(fragmentDelegateStep0Binding.btnAdd01)) {
            BigDecimal existed = BigDecimal.ZERO;
            String es = fragmentDelegateStep0Binding.etAmountCoin.getText().toString().trim();
            if (es.length() > 0) {
                existed = new BigDecimal(es);
            }
            fragmentDelegateStep0Binding.etAmountCoin.setText(existed.add(new BigDecimal("0.1")).toPlainString());

        } else if (v.equals(fragmentDelegateStep0Binding.btnAdd1)) {
            BigDecimal existed = BigDecimal.ZERO;
            String es = fragmentDelegateStep0Binding.etAmountCoin.getText().toString().trim();
            if (es.length() > 0) {
                existed = new BigDecimal(es);
            }
            fragmentDelegateStep0Binding.etAmountCoin.setText(existed.add(new BigDecimal("1")).toPlainString());

        } else if (v.equals(fragmentDelegateStep0Binding.btnAdd10)) {
            BigDecimal existed = BigDecimal.ZERO;
            String es = fragmentDelegateStep0Binding.etAmountCoin.getText().toString().trim();
            WLog.w("es " + es);
            if (es.length() > 0) {
                existed = new BigDecimal(es);
            }
            WLog.w("existed " + existed);
            WLog.w("add " + existed.add(new BigDecimal("10")).toPlainString());
            fragmentDelegateStep0Binding.etAmountCoin.setText(existed.add(new BigDecimal("10")).toPlainString());

        } else if (v.equals(fragmentDelegateStep0Binding.btnAdd100)) {
            BigDecimal existed = BigDecimal.ZERO;
            String es = fragmentDelegateStep0Binding.etAmountCoin.getText().toString().trim();
            if (es.length() > 0) {
                existed = new BigDecimal(es);
            }
            fragmentDelegateStep0Binding.etAmountCoin.setText(existed.add(new BigDecimal("100")).toPlainString());

        } else if (v.equals(fragmentDelegateStep0Binding.btnAddHalf)) {
            BigDecimal half = mMaxAvailable.movePointLeft(mDpDecimal).divide(new BigDecimal("2"), mDpDecimal, RoundingMode.DOWN);
            fragmentDelegateStep0Binding.etAmountCoin.setText(half.toPlainString());

        } else if (v.equals(fragmentDelegateStep0Binding.btnAddAll)) {
            BigDecimal max = mMaxAvailable.movePointLeft(mDpDecimal).setScale(mDpDecimal, RoundingMode.DOWN);
            fragmentDelegateStep0Binding.etAmountCoin.setText(max.toPlainString());
            onShowEmptyBalanceWarnDialog();

        } else if (v.equals(fragmentDelegateStep0Binding.clearAll)) {
            fragmentDelegateStep0Binding.etAmountCoin.setText("");

        }

    }

    private boolean isValidateDelegateAmount() {
        try {
            BigDecimal amountTemp = new BigDecimal(fragmentDelegateStep0Binding.etAmountCoin.getText().toString().trim());
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
