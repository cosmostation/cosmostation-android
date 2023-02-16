package wannabit.io.cosmostaion.fragment.txs.common;

import android.os.Bundle;
import android.text.Editable;
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
import wannabit.io.cosmostaion.activities.txs.common.RedelegateActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.databinding.FragmentRedelegateStep0Binding;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;

public class RedelegateStep0Fragment extends BaseFragment implements View.OnClickListener {

    private FragmentRedelegateStep0Binding fragmentRedelegateStep0Binding;

    private BigDecimal mMaxAvailable = BigDecimal.ZERO;
    private int mDpDecimal = 6;
    private String mDecimalChecker, mDecimalSetter;

    public static RedelegateStep0Fragment newInstance() {
        return new RedelegateStep0Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentRedelegateStep0Binding = FragmentRedelegateStep0Binding.inflate(getLayoutInflater());
        fragmentRedelegateStep0Binding.btnCancel.setOnClickListener(this);
        fragmentRedelegateStep0Binding.btnNext.setOnClickListener(this);
        fragmentRedelegateStep0Binding.clearAll.setOnClickListener(this);
        fragmentRedelegateStep0Binding.btnAdd01.setOnClickListener(this);
        fragmentRedelegateStep0Binding.btnAdd1.setOnClickListener(this);
        fragmentRedelegateStep0Binding.btnAdd10.setOnClickListener(this);
        fragmentRedelegateStep0Binding.btnAdd100.setOnClickListener(this);
        fragmentRedelegateStep0Binding.btnAddHalf.setOnClickListener(this);
        fragmentRedelegateStep0Binding.btnAddAll.setOnClickListener(this);
        return fragmentRedelegateStep0Binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        mDpDecimal = WDp.getDenomDecimal(getBaseDao(), getSActivity().mChainConfig, getSActivity().mChainConfig.mainDenom());
        setDpDecimals(mDpDecimal);
        WDp.setDpSymbol(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mChainConfig.mainDenom(), fragmentRedelegateStep0Binding.tvSymbolCoin);
        mMaxAvailable = getSActivity().getBaseDao().getDelegation(getSActivity().mValAddress);
        fragmentRedelegateStep0Binding.tvMaxCoin.setText(WDp.getDpAmount2(getContext(), mMaxAvailable, mDpDecimal, mDpDecimal));
        fragmentRedelegateStep0Binding.rewardProgress.setVisibility(View.GONE);
        onAddAmountWatcher();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentRedelegateStep0Binding = null;
    }

    private void onAddAmountWatcher() {
        fragmentRedelegateStep0Binding.etAmountCoin.addTextChangedListener(new TextWatcher() {
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
                    fragmentRedelegateStep0Binding.etAmountCoin.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                } else if (es.startsWith(".")) {
                    fragmentRedelegateStep0Binding.etAmountCoin.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                    fragmentRedelegateStep0Binding.etAmountCoin.setText("");
                } else if (es.endsWith(".")) {
                    fragmentRedelegateStep0Binding.etAmountCoin.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                    fragmentRedelegateStep0Binding.etAmountCoin.setVisibility(View.VISIBLE);
                } else if (es.length() > 1 && es.startsWith("0") && !es.startsWith("0.")) {
                    fragmentRedelegateStep0Binding.etAmountCoin.setText("0");
                    fragmentRedelegateStep0Binding.etAmountCoin.setSelection(1);
                }

                if (es.equals(mDecimalChecker)) {
                    fragmentRedelegateStep0Binding.etAmountCoin.setText(mDecimalSetter);
                    fragmentRedelegateStep0Binding.etAmountCoin.setSelection(mDpDecimal + 1);
                } else {
                    try {
                        final BigDecimal inputAmount = new BigDecimal(es);
                        if (BigDecimal.ZERO.compareTo(inputAmount) >= 0) {
                            fragmentRedelegateStep0Binding.etAmountCoin.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                            return;
                        }

                        BigDecimal checkPosition = inputAmount.movePointRight(mDpDecimal);
                        BigDecimal checkMax = checkPosition.setScale(0, RoundingMode.DOWN);
                        if (checkPosition.compareTo(checkMax) != 0 || !checkPosition.equals(checkMax)) {
                            String recover = es.substring(0, es.length() - 1);
                            fragmentRedelegateStep0Binding.etAmountCoin.setText(recover);
                            fragmentRedelegateStep0Binding.etAmountCoin.setSelection(recover.length());
                            return;
                        }

                        if (inputAmount.compareTo(mMaxAvailable.movePointLeft(mDpDecimal).setScale(mDpDecimal, RoundingMode.DOWN)) > 0) {
                            fragmentRedelegateStep0Binding.etAmountCoin.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                        } else {
                            fragmentRedelegateStep0Binding.etAmountCoin.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                        }
                        fragmentRedelegateStep0Binding.etAmountCoin.setSelection(fragmentRedelegateStep0Binding.etAmountCoin.getText().length());
                    } catch (Exception e) {
                    }
                }
            }
        });
    }

    @Override
    public void onRefreshTab() {
        fragmentRedelegateStep0Binding.rewardProgress.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(fragmentRedelegateStep0Binding.btnCancel)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(fragmentRedelegateStep0Binding.btnNext)) {
            if (isValidateReDelegateAmount()) {
                getSActivity().onNextStep();
            } else {
                Toast.makeText(getContext(), R.string.error_invalid_amounts, Toast.LENGTH_SHORT).show();
            }
        } else if (v.equals(fragmentRedelegateStep0Binding.btnAdd01)) {
            BigDecimal existed = BigDecimal.ZERO;
            String es = fragmentRedelegateStep0Binding.etAmountCoin.getText().toString().trim();
            if (es.length() > 0) {
                existed = new BigDecimal(es);
            }
            fragmentRedelegateStep0Binding.etAmountCoin.setText(existed.add(new BigDecimal("0.1")).toPlainString());

        } else if (v.equals(fragmentRedelegateStep0Binding.btnAdd1)) {
            BigDecimal existed = BigDecimal.ZERO;
            String es = fragmentRedelegateStep0Binding.etAmountCoin.getText().toString().trim();
            if (es.length() > 0) {
                existed = new BigDecimal(es);
            }
            fragmentRedelegateStep0Binding.etAmountCoin.setText(existed.add(new BigDecimal("1")).toPlainString());

        } else if (v.equals(fragmentRedelegateStep0Binding.btnAdd10)) {
            BigDecimal existed = BigDecimal.ZERO;
            String es = fragmentRedelegateStep0Binding.etAmountCoin.getText().toString().trim();
            if (es.length() > 0) {
                existed = new BigDecimal(es);
            }
            fragmentRedelegateStep0Binding.etAmountCoin.setText(existed.add(new BigDecimal("10")).toPlainString());

        } else if (v.equals(fragmentRedelegateStep0Binding.btnAdd100)) {
            BigDecimal existed = BigDecimal.ZERO;
            String es = fragmentRedelegateStep0Binding.etAmountCoin.getText().toString().trim();
            if (es.length() > 0) {
                existed = new BigDecimal(es);
            }
            fragmentRedelegateStep0Binding.etAmountCoin.setText(existed.add(new BigDecimal("100")).toPlainString());

        } else if (v.equals(fragmentRedelegateStep0Binding.btnAddHalf)) {
            BigDecimal half = mMaxAvailable.movePointLeft(mDpDecimal).divide(new BigDecimal("2"), mDpDecimal, RoundingMode.DOWN);
            fragmentRedelegateStep0Binding.etAmountCoin.setText(half.toPlainString());

        } else if (v.equals(fragmentRedelegateStep0Binding.btnAddAll)) {
            BigDecimal max = mMaxAvailable.movePointLeft(mDpDecimal).setScale(mDpDecimal, RoundingMode.DOWN);
            fragmentRedelegateStep0Binding.etAmountCoin.setText(max.toPlainString());

        } else if (v.equals(fragmentRedelegateStep0Binding.clearAll)) {
            fragmentRedelegateStep0Binding.etAmountCoin.setText("");

        }
    }

    private boolean isValidateReDelegateAmount() {
        try {
            BigDecimal userInput = new BigDecimal(fragmentRedelegateStep0Binding.etAmountCoin.getText().toString().trim()).movePointRight(mDpDecimal).setScale(0);
            if (userInput.compareTo(BigDecimal.ZERO) <= 0) return false;
            if (userInput.compareTo(mMaxAvailable) > 0) return false;
            Coin coin = new Coin(getSActivity().mChainConfig.mainDenom(), userInput.toPlainString());
            getSActivity().mAmount = coin;
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

    private RedelegateActivity getSActivity() {
        return (RedelegateActivity) getBaseActivity();
    }

}
