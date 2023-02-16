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
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.common.SendActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.dao.MintscanToken;
import wannabit.io.cosmostaion.databinding.FragmentSendStep1Binding;
import wannabit.io.cosmostaion.dialog.CommonAlertDialog;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;

public class SendStep1Fragment extends BaseFragment implements View.OnClickListener {

    private FragmentSendStep1Binding fragmentSendStep1Binding;
    private BigDecimal mMaxAvailable = BigDecimal.ZERO;

    private String mainDenom;
    private String toSendDenom;
    private ArrayList<Coin> mToSendCoins = new ArrayList<>();
    private int mDpDecimal = 6;
    private String mDecimalChecker, mDecimalSetter;

    private MintscanToken mMintscanToken;

    public static SendStep1Fragment newInstance() {
        return new SendStep1Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentSendStep1Binding = FragmentSendStep1Binding.inflate(getLayoutInflater());
        fragmentSendStep1Binding.btnBefore.setOnClickListener(this);
        fragmentSendStep1Binding.btnNext.setOnClickListener(this);
        fragmentSendStep1Binding.clearAll.setOnClickListener(this);
        fragmentSendStep1Binding.btnAdd01.setOnClickListener(this);
        fragmentSendStep1Binding.btnAdd1.setOnClickListener(this);
        fragmentSendStep1Binding.btnAdd10.setOnClickListener(this);
        fragmentSendStep1Binding.btnAdd100.setOnClickListener(this);
        fragmentSendStep1Binding.btnAddHalf.setOnClickListener(this);
        fragmentSendStep1Binding.btnAddAll.setOnClickListener(this);
        return fragmentSendStep1Binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isAdded() || getSActivity() == null || getSActivity().mAccount == null) {
            getSActivity().onBackPressed();
        }
        mainDenom = getSActivity().mChainConfig.mainDenom();
        toSendDenom = getSActivity().mDenom;
        mMintscanToken = getBaseDao().getCw20Asset(getSActivity().mChainConfig, toSendDenom);
        mDpDecimal = WDp.getDenomDecimal(getBaseDao(), getSActivity().mChainConfig, toSendDenom);

        if (mMintscanToken != null) {
            mMaxAvailable = mMintscanToken.getAmount();

        } else {
            if (BaseChain.isGRPC(getSActivity().mBaseChain)) {
                if (toSendDenom.equals(mainDenom)) {
                    mMaxAvailable = getBaseDao().getAvailable(toSendDenom).subtract(WDp.getMainDenomFee(getActivity(), getBaseDao(), getSActivity().mChainConfig));
                } else {
                    mMaxAvailable = getBaseDao().getAvailable(toSendDenom);
                }

            } else {
                mDpDecimal = WDp.mainDisplayDecimal(getSActivity().mBaseChain);
                if (toSendDenom.equals(mainDenom)) {
                    mMaxAvailable = getBaseDao().availableAmount(toSendDenom).subtract(WDp.getMainDenomFee(getActivity(), getBaseDao(), getSActivity().mChainConfig));
                } else {
                    mMaxAvailable = getBaseDao().availableAmount(toSendDenom);
                }
            }
        }
        WDp.setDpCoin(getContext(), getBaseDao(), getSActivity().mChainConfig, toSendDenom, mMaxAvailable.toPlainString(), fragmentSendStep1Binding.tvSymbolCoin, fragmentSendStep1Binding.tvMaxCoin);
        setDisplayDecimals(mDpDecimal);
        onAddAmountWatcher();
    }

    private void onAddAmountWatcher() {
        fragmentSendStep1Binding.etAmountCoin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable et) {
                String es = String.valueOf(et).trim();
                if (TextUtils.isEmpty(es)) {
                    fragmentSendStep1Binding.etAmountCoin.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                } else if (es.startsWith(".")) {
                    fragmentSendStep1Binding.etAmountCoin.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                    fragmentSendStep1Binding.etAmountCoin.setText("");
                } else if (es.endsWith(".")) {
                    fragmentSendStep1Binding.etAmountCoin.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                    fragmentSendStep1Binding.etAmountCoin.setVisibility(View.VISIBLE);
                } else if (es.length() > 1 && es.startsWith("0") && !es.startsWith("0.")) {
                    fragmentSendStep1Binding.etAmountCoin.setText("0");
                    fragmentSendStep1Binding.etAmountCoin.setSelection(1);
                }

                if (es.equals(mDecimalChecker)) {
                    fragmentSendStep1Binding.etAmountCoin.setText(mDecimalSetter);
                    fragmentSendStep1Binding.etAmountCoin.setSelection(mDpDecimal + 1);
                } else {
                    try {
                        final BigDecimal inputAmount = new BigDecimal(es);
                        if (BigDecimal.ZERO.compareTo(inputAmount) >= 0) {
                            fragmentSendStep1Binding.etAmountCoin.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                            return;
                        }

                        BigDecimal checkPosition = inputAmount.movePointRight(mDpDecimal);
                        BigDecimal checkMax = checkPosition.setScale(0, RoundingMode.DOWN);
                        if (checkPosition.compareTo(checkMax) != 0 || !checkPosition.equals(checkMax)) {
                            String recover = es.substring(0, es.length() - 1);
                            fragmentSendStep1Binding.etAmountCoin.setText(recover);
                            fragmentSendStep1Binding.etAmountCoin.setSelection(recover.length());
                            return;
                        }

                        if (mMintscanToken != null) {
                            if (inputAmount.compareTo(mMaxAvailable.movePointLeft(mDpDecimal).setScale(mDpDecimal, RoundingMode.CEILING)) > 0) {
                                fragmentSendStep1Binding.etAmountCoin.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                            } else {
                                fragmentSendStep1Binding.etAmountCoin.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                            }

                        } else {
                            if (inputAmount.compareTo(mMaxAvailable) > 0) {
                                fragmentSendStep1Binding.etAmountCoin.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                            } else {
                                fragmentSendStep1Binding.etAmountCoin.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                            }
                        }
                        fragmentSendStep1Binding.etAmountCoin.setSelection(fragmentSendStep1Binding.etAmountCoin.getText().length());
                    } catch (Exception e) {
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.equals(fragmentSendStep1Binding.btnBefore)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(fragmentSendStep1Binding.btnNext)) {
            if (isValidateSendAmount()) {
                getSActivity().mAmounts = mToSendCoins;
                getSActivity().onNextStep();
            } else {
                Toast.makeText(getContext(), R.string.error_invalid_amount, Toast.LENGTH_SHORT).show();
            }

        } else if (v.equals(fragmentSendStep1Binding.btnAdd01)) {
            BigDecimal existed = BigDecimal.ZERO;
            String es = String.valueOf(fragmentSendStep1Binding.etAmountCoin.getText()).trim();
            if (es.length() > 0) {
                existed = new BigDecimal(es);
            }
            fragmentSendStep1Binding.etAmountCoin.setText(existed.add(new BigDecimal("0.1")).toPlainString());

        } else if (v.equals(fragmentSendStep1Binding.btnAdd1)) {
            BigDecimal existed = BigDecimal.ZERO;
            String es = String.valueOf(fragmentSendStep1Binding.etAmountCoin.getText()).trim();
            if (es.length() > 0) {
                existed = new BigDecimal(es);
            }
            fragmentSendStep1Binding.etAmountCoin.setText(existed.add(new BigDecimal("1")).toPlainString());

        } else if (v.equals(fragmentSendStep1Binding.btnAdd10)) {
            BigDecimal existed = BigDecimal.ZERO;
            String es = String.valueOf(fragmentSendStep1Binding.etAmountCoin.getText()).trim();
            if (es.length() > 0) {
                existed = new BigDecimal(es);
            }
            fragmentSendStep1Binding.etAmountCoin.setText(existed.add(new BigDecimal("10")).toPlainString());

        } else if (v.equals(fragmentSendStep1Binding.btnAdd100)) {
            BigDecimal existed = BigDecimal.ZERO;
            String es = String.valueOf(fragmentSendStep1Binding.etAmountCoin.getText()).trim();
            if (es.length() > 0) {
                existed = new BigDecimal(es);
            }
            fragmentSendStep1Binding.etAmountCoin.setText(existed.add(new BigDecimal("100")).toPlainString());

        } else if (v.equals(fragmentSendStep1Binding.btnAddHalf)) {
            BigDecimal half;
            if (mMintscanToken != null) {
                half = mMaxAvailable.movePointLeft(mDpDecimal).divide(new BigDecimal("2"), mDpDecimal, RoundingMode.DOWN);
            } else {
                if (BaseChain.isGRPC(getSActivity().mChainConfig.baseChain())) {
                    half = mMaxAvailable.movePointLeft(mDpDecimal).divide(new BigDecimal("2"), mDpDecimal, RoundingMode.DOWN);
                } else {
                    half = mMaxAvailable.divide(new BigDecimal("2"), mDpDecimal, RoundingMode.DOWN);
                }
            }
            fragmentSendStep1Binding.etAmountCoin.setText(half.toPlainString());

        } else if (v.equals(fragmentSendStep1Binding.btnAddAll)) {
            if (mMintscanToken != null) {
                fragmentSendStep1Binding.etAmountCoin.setText(mMaxAvailable.movePointLeft(mDpDecimal).setScale(mDpDecimal, RoundingMode.DOWN).toPlainString());
            } else {
                if (BaseChain.isGRPC(getSActivity().mChainConfig.baseChain())) {
                    fragmentSendStep1Binding.etAmountCoin.setText(mMaxAvailable.movePointLeft(mDpDecimal).setScale(mDpDecimal, RoundingMode.DOWN).toPlainString());
                } else {
                    fragmentSendStep1Binding.etAmountCoin.setText(mMaxAvailable.toPlainString());
                }
            }
            onShowEmptyBalanceWarnDialog();

        } else if (v.equals(fragmentSendStep1Binding.clearAll)) {
            fragmentSendStep1Binding.etAmountCoin.setText("");
        }
    }

    private boolean isValidateSendAmount() {
        mToSendCoins.clear();
        try {
            if (getSActivity().mBaseChain.equals(BaseChain.BNB_MAIN)) {
                BigDecimal sendTemp = new BigDecimal(String.valueOf(fragmentSendStep1Binding.etAmountCoin.getText()).trim());
                if (sendTemp.compareTo(BigDecimal.ZERO) <= 0) return false;
                if (sendTemp.compareTo(mMaxAvailable) > 0) return false;
                if (getSActivity().mBnbToken.type == BnbToken.BNB_TOKEN_TYPE_MINI) {
                    if ((sendTemp.compareTo(BigDecimal.ONE) < 0) && (sendTemp.compareTo(mMaxAvailable) != 0)) {
                        Toast.makeText(getContext(), R.string.error_bnb_mini_amount, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
                Coin token = new Coin(getSActivity().mBnbToken.symbol, sendTemp.toPlainString());
                mToSendCoins.add(token);
                return true;

            } else {
                BigDecimal sendTemp = new BigDecimal(String.valueOf(fragmentSendStep1Binding.etAmountCoin.getText()).trim());
                if (sendTemp.compareTo(BigDecimal.ZERO) <= 0) return false;
                if (getSActivity().mBaseChain.equals(BaseChain.OKEX_MAIN) && mMintscanToken == null) {
                    if (sendTemp.compareTo(mMaxAvailable) > 0) return false;
                    Coin coin = new Coin(getSActivity().mDenom, sendTemp.setScale(mDpDecimal).toPlainString());
                    mToSendCoins.add(coin);
                } else {
                    if (sendTemp.compareTo(mMaxAvailable.movePointLeft(mDpDecimal).setScale(mDpDecimal, RoundingMode.CEILING)) > 0)
                        return false;
                    Coin coin = new Coin(getSActivity().mDenom, sendTemp.movePointRight(mDpDecimal).setScale(0).toPlainString());
                    mToSendCoins.add(coin);
                }
                return true;
            }

        } catch (Exception e) {
            mToSendCoins.clear();
            return false;
        }
    }

    private void onShowEmptyBalanceWarnDialog() {
        if (mainDenom.equalsIgnoreCase(toSendDenom)) {
            CommonAlertDialog.showSingleButton(getSActivity(), getString(R.string.str_empty_warnning_title), getString(R.string.str_empty_warnning_msg), getString(R.string.str_ok), null);
        }
    }

    private void setDisplayDecimals(int decimals) {
        mDecimalChecker = "0.";
        mDecimalSetter = "0.";
        for (int i = 0; i < decimals; i++) {
            mDecimalChecker = mDecimalChecker + "0";
        }
        for (int i = 0; i < decimals - 1; i++) {
            mDecimalSetter = mDecimalSetter + "0";
        }
    }

    private SendActivity getSActivity() {
        return (SendActivity) getBaseActivity();
    }
}
