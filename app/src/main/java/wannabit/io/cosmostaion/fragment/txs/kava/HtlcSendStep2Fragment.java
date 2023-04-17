package wannabit.io.cosmostaion.fragment.txs.kava;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BNB;

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
import wannabit.io.cosmostaion.activities.txs.kava.HtlcSendActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.CommonAlertDialog;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;

public class HtlcSendStep2Fragment extends BaseFragment implements View.OnClickListener {

    private Button mBefore, mNextBtn;
    private EditText mAmountInput;
    private TextView mMinAmount, mMaxAmount;
    private TextView mDenomTitle;
    private ImageView mClearAll;
    private Button mAdd01, mAdd1, mAdd10, mAdd100, mAddHalf, mAddMax;
    private BigDecimal mMinAvailable = BigDecimal.ZERO;
    private BigDecimal mMaxAvailable = BigDecimal.ZERO;

    private ArrayList<Coin> mToSendCoins = new ArrayList<>();
    private int mDecimal = 8;
    private String mDecimalChecker, mDecimalSetter;
    public String mToSwapDenom;

    public static HtlcSendStep2Fragment newInstance() {
        return new HtlcSendStep2Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_htlc_send_step2, container, false);
        mBefore = rootView.findViewById(R.id.btn_before);
        mNextBtn = rootView.findViewById(R.id.btn_next);
        mAmountInput = rootView.findViewById(R.id.et_amount_coin);
        mMinAmount = rootView.findViewById(R.id.tv_min_coin);
        mMaxAmount = rootView.findViewById(R.id.tv_max_coin);
        mDenomTitle = rootView.findViewById(R.id.tv_symbol_coin);
        mClearAll = rootView.findViewById(R.id.clearAll);
        mAdd01 = rootView.findViewById(R.id.btn_add_01);
        mAdd1 = rootView.findViewById(R.id.btn_add_1);
        mAdd10 = rootView.findViewById(R.id.btn_add_10);
        mAdd100 = rootView.findViewById(R.id.btn_add_100);
        mAddHalf = rootView.findViewById(R.id.btn_add_half);
        mAddMax = rootView.findViewById(R.id.btn_add_all);
        mBefore.setOnClickListener(this);
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
    public void onRefreshTab() {
        super.onRefreshTab();
        mToSwapDenom = getSActivity().mToSwapDenom;
        mAmountInput.setText("");
        onUpdateInitInfo();

    }

    private void onUpdateInitInfo() {
        if (getSActivity().mBaseChain.equals(BaseChain.BNB_MAIN)) {
            mDecimal = 8;
            setDpDecimals(mDecimal);
            WDp.setDpSymbol(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mToSwapDenom, mDenomTitle);
            mMaxAvailable = getSActivity().getAvailable();

            // check relayer capacity
            BigDecimal remainCap = getSActivity().mRemainCap.movePointLeft(mDecimal);
            BigDecimal maxOnce = getSActivity().mMaxOnce.movePointLeft(mDecimal);
            if (mMaxAvailable.compareTo(remainCap) > 0) {
                mMaxAvailable = remainCap;
            }
            if (mMaxAvailable.compareTo(maxOnce) > 0) {
                mMaxAvailable = maxOnce;
            }
            mMaxAmount.setText(WDp.getDpAmount2(getContext(), mMaxAvailable, 0, mDecimal));

            mMinAvailable = getSActivity().mKavaBep3Param2.getSupportedSwapAssetMin(mToSwapDenom).movePointLeft(8);
            mMinAmount.setText(WDp.getDpAmount2(getContext(), mMinAvailable, 0, mDecimal));

        } else if (getSActivity().mBaseChain.equals(BaseChain.KAVA_MAIN)) {
            mDecimal = WDp.getDenomDecimal(getBaseDao(), getSActivity().mChainConfig, getSActivity().mToSwapDenom);
            setDpDecimals(mDecimal);
            if (mToSwapDenom.equals(TOKEN_HTLC_KAVA_BNB)) {
                mDenomTitle.setText("BNB");
                mDenomTitle.setTextColor(ContextCompat.getColor(getSActivity(), R.color.color_binance));
            } else {
                WDp.setDpSymbol(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mToSwapDenom, mDenomTitle);
            }

            mMaxAvailable = getSActivity().getAvailable();
            // check relayer capacity
            BigDecimal maxOnce = getSActivity().mMaxOnce;
            if (mMaxAvailable.compareTo(maxOnce) > 0) {
                mMaxAvailable = maxOnce;
            }
            mMaxAmount.setText(WDp.getDpAmount2(getContext(), mMaxAvailable, mDecimal, mDecimal));

            mMinAvailable = getSActivity().mKavaBep3Param2.getSupportedSwapAssetMin(mToSwapDenom);
            mMinAmount.setText(WDp.getDpAmount2(getContext(), mMinAvailable, mDecimal, mDecimal));
        }

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
                    return;
                } else if (es.length() > 1 && es.startsWith("0") && !es.startsWith("0.")) {
                    mAmountInput.setText("0");
                    mAmountInput.setSelection(1);
                }

                if (es.equals(mDecimalChecker)) {
                    mAmountInput.setText(mDecimalSetter);
                    mAmountInput.setSelection(mDecimal + 1);
                } else {
                    try {
                        final BigDecimal inputAmount = new BigDecimal(es);
                        if (BigDecimal.ZERO.compareTo(inputAmount) >= 0) {
                            mAmountInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                            return;
                        }

                        BigDecimal checkPosition = inputAmount.movePointRight(mDecimal);
                        BigDecimal checkMax = checkPosition.setScale(0, RoundingMode.DOWN);
                        if (checkPosition.compareTo(checkMax) != 0 || !checkPosition.equals(checkMax)) {
                            String recover = es.substring(0, es.length() - 1);
                            mAmountInput.setText(recover);
                            mAmountInput.setSelection(recover.length());
                            return;
                        }

                        if (getSActivity().mBaseChain.equals(BaseChain.BNB_MAIN)) {
                            if (mMaxAvailable.compareTo(inputAmount) < 0) {
                                mAmountInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));

                            } else if (mMinAvailable.compareTo(inputAmount) >= 0) {
                                mAmountInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));

                            } else {
                                mAmountInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                            }

                        } else if (getSActivity().mBaseChain.equals(BaseChain.KAVA_MAIN)) {
                            if (mMaxAvailable.compareTo(checkPosition) < 0) {
                                mAmountInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));

                            } else if (mMinAvailable.compareTo(checkPosition) >= 0) {
                                mAmountInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));

                            } else {
                                mAmountInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                            }
                        }
                    } catch (Exception e) { }
                }
            }
        });
    }

    private boolean isValidateAmount() {
        mToSendCoins.clear();
        try {
            if (getSActivity().mBaseChain.equals(BaseChain.BNB_MAIN)) {
                BigDecimal sendTemp = new BigDecimal(mAmountInput.getText().toString().trim());
                if (sendTemp.compareTo(BigDecimal.ZERO) <= 0) return false;
                if (sendTemp.compareTo(mMinAvailable) <= 0) return false;
                if (sendTemp.compareTo(mMaxAvailable) > 0) return false;
                Coin token = new Coin(getSActivity().mToSwapDenom, sendTemp.toPlainString());
                mToSendCoins.add(token);
                return true;

            } else if (getSActivity().mBaseChain.equals(BaseChain.KAVA_MAIN)) {
                BigDecimal sendTemp = new BigDecimal(mAmountInput.getText().toString().trim()).movePointRight(mDecimal);
                if (sendTemp.compareTo(BigDecimal.ZERO) <= 0) return false;
                if (sendTemp.compareTo(mMinAvailable) <= 0) return false;
                if (sendTemp.compareTo(mMaxAvailable) > 0) return false;
                Coin token = new Coin(getSActivity().mToSwapDenom.toLowerCase(), sendTemp.toPlainString());
                mToSendCoins.add(token);
            }

            if (mToSendCoins.size() <= 0) {
                return false;
            }

        } catch (Exception e) {
            mToSendCoins.clear();
            return false;
        }
        return true;
    }


    @Override
    public void onClick(View v) {
        if (v.equals(mBefore)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            if (isValidateAmount()) {
                getSActivity().mToSendCoins = mToSendCoins;
                getSActivity().onNextStep();
            } else {
                Toast.makeText(getContext(), R.string.error_invalid_amount, Toast.LENGTH_SHORT).show();
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
            if (getSActivity().mBaseChain.equals(BaseChain.BNB_MAIN)) {
                mAmountInput.setText(mMaxAvailable.divide(new BigDecimal("2"), mDecimal, RoundingMode.DOWN).toPlainString());
            } else if (getSActivity().mBaseChain.equals(BaseChain.KAVA_MAIN)) {
                mAmountInput.setText(mMaxAvailable.movePointLeft(mDecimal).divide(new BigDecimal("2"), mDecimal, RoundingMode.DOWN).toPlainString());
            }

        } else if (v.equals(mAddMax)) {
            if (getSActivity().mBaseChain.equals(BaseChain.BNB_MAIN)) {
                mAmountInput.setText(mMaxAvailable.toPlainString());
                if (mToSwapDenom.equals(TOKEN_HTLC_BINANCE_BNB)) {
                    onShowEmptyBalanceWarnDialog();
                }
            } else if (getSActivity().mBaseChain.equals(BaseChain.KAVA_MAIN)) {
                mAmountInput.setText(mMaxAvailable.movePointLeft(mDecimal).toPlainString());
            }

        } else if (v.equals(mClearAll)) {
            mAmountInput.setText("");
        }

    }


    private HtlcSendActivity getSActivity() {
        return (HtlcSendActivity) getBaseActivity();
    }

    private void setDpDecimals(int deciaml) {
        mDecimalChecker = "0.";
        mDecimalSetter = "0.";
        for (int i = 0; i < deciaml; i++) {
            mDecimalChecker = mDecimalChecker + "0";
        }
        for (int i = 0; i < deciaml - 1; i++) {
            mDecimalSetter = mDecimalSetter + "0";
        }
    }

    private void onShowEmptyBalanceWarnDialog() {
        CommonAlertDialog.showSingleButton(getSActivity(), getString(R.string.str_empty_warnning_title), getString(R.string.str_empty_warnning_msg), getString(R.string.str_ok), null);
    }
}
