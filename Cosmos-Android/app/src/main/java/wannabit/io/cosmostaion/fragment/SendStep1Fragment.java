package wannabit.io.cosmostaion.fragment;

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
import wannabit.io.cosmostaion.activities.SendActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.dialog.Dialog_Empty_Warnning;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.FEE_BNB_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_AKASH;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BAND;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_CERTIK;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OK;

public class SendStep1Fragment extends BaseFragment implements View.OnClickListener {

    private Button              mBefore, mNextBtn;
    private EditText            mAmountInput;
    private TextView            mAvailableAmount;
    private TextView            mDenomTitle;
    private ImageView           mClearAll;
    private Button              mAdd01, mAdd1, mAdd10, mAdd100, mAddHalf, mAddMax;
    private BigDecimal          mMaxAvailable = BigDecimal.ZERO;

    private ArrayList<Coin>     mToSendCoins = new ArrayList<>();

    private int                 mDpDecimal = 6;
    private String              mDecimalChecker, mDecimalSetter,
                                mDecimalDivider2, mDecimalDivider1;


    public static SendStep1Fragment newInstance(Bundle bundle) {
        SendStep1Fragment fragment = new SendStep1Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_send_step1, container, false);
        mBefore = rootView.findViewById(R.id.btn_before);
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
    public void onResume() {
        super.onResume();
        if (!isAdded() || getSActivity() == null || getSActivity().mAccount == null) {
            getSActivity().onBackPressed();
        }

        if (getSActivity().mBaseChain.equals(BNB_MAIN) || getSActivity().mBaseChain.equals(BNB_TEST)) {
            mDpDecimal = 8;
            setDpDecimals(mDpDecimal);
            mDenomTitle.setText(getSActivity().mBnbToken.original_symbol.toUpperCase());
            if (getSActivity().mBnbToken.symbol.equals(TOKEN_BNB)) {
                mMaxAvailable = getSActivity().mAccount.getBnbBalance().subtract(new BigDecimal(FEE_BNB_SEND));
                mAvailableAmount.setText(WDp.getDpAmount(getContext(), mMaxAvailable, 8, getSActivity().mBaseChain));
                mDenomTitle.setTextColor(getResources().getColor(R.color.colorBnb));
            } else {
                mMaxAvailable = getSActivity().mAccount.getBnbTokenBalance(getSActivity().mBnbToken.symbol);
                mAvailableAmount.setText(WDp.getDpAmount(getContext(), mMaxAvailable, 8, getSActivity().mBaseChain));
            }

        } else if (getSActivity().mBaseChain.equals(KAVA_MAIN) || getSActivity().mBaseChain.equals(KAVA_TEST)) {
            mDpDecimal = WUtil.getKavaCoinDecimal(getSActivity().mDenom);
            setDpDecimals(mDpDecimal);
            mMaxAvailable = getSActivity().mAccount.getTokenBalance(getSActivity().mDenom);
            WDp.showCoinDp(getContext(), getSActivity().mDenom, mMaxAvailable.toPlainString(), mDenomTitle, mAvailableAmount, getSActivity().mBaseChain);

        } else if (getSActivity().mBaseChain.equals(IOV_MAIN) || getSActivity().mBaseChain.equals(IOV_TEST)) {
            mDpDecimal = 6;
            setDpDecimals(mDpDecimal);
            mMaxAvailable = getSActivity().mAccount.getTokenBalance(getSActivity().mIovDenom);
            if (getSActivity().mIovDenom.equals(TOKEN_IOV) || getSActivity().mIovDenom.equals(TOKEN_IOV_TEST)) {
                mMaxAvailable = mMaxAvailable.subtract(new BigDecimal("100000"));
            }
            WDp.showCoinDp(getContext(), getSActivity().mIovDenom, mMaxAvailable.toPlainString(), mDenomTitle, mAvailableAmount, getSActivity().mBaseChain);

        } else if (getSActivity().mBaseChain.equals(BAND_MAIN)) {
            mDpDecimal = 6;
            setDpDecimals(mDpDecimal);
            WDp.DpMainDenom(getContext(), getSActivity().mBaseChain.getChain(), mDenomTitle);
            mMaxAvailable = getSActivity().mAccount.getBandBalance();
            mAvailableAmount.setText(WDp.getDpAmount2(getContext(), mMaxAvailable, 6, 6));

        } else if (getSActivity().mBaseChain.equals(OKEX_MAIN) || getSActivity().mBaseChain.equals(OK_TEST)) {
            mDpDecimal = 18;
            setDpDecimals(mDpDecimal);
            mDenomTitle.setText(getSActivity().mOkDenom.toUpperCase());
            if (getSActivity().mOkDenom.equals(TOKEN_OK)) {
                mMaxAvailable = getSActivity().mAccount.getTokenBalance(TOKEN_OK).subtract(new BigDecimal("0.002"));
                mAvailableAmount.setText(WDp.getDpAmount2(getContext(), mMaxAvailable, 0, mDpDecimal));
                mDenomTitle.setTextColor(getResources().getColor(R.color.colorOK));
            } else {
                mMaxAvailable = getSActivity().mAccount.getTokenBalance(getSActivity().mOkDenom);
                mAvailableAmount.setText(WDp.getDpAmount2(getContext(), mMaxAvailable, 0, mDpDecimal));

            }

        } else if (getSActivity().mBaseChain.equals(CERTIK_MAIN) || getSActivity().mBaseChain.equals(CERTIK_TEST)) {
            mDpDecimal = 6;
            setDpDecimals(mDpDecimal);
            WDp.DpMainDenom(getContext(), getSActivity().mBaseChain.getChain(), mDenomTitle);
            mMaxAvailable = getSActivity().mAccount.getTokenBalance(getSActivity().mCertikDenom).subtract(new BigDecimal("5000"));
            mAvailableAmount.setText(WDp.getDpAmount2(getContext(), mMaxAvailable, 6, 6));

        } else if (getSActivity().mBaseChain.equals(SECRET_MAIN)) {
            mDpDecimal = 6;
            setDpDecimals(mDpDecimal);
            WDp.DpMainDenom(getContext(), getSActivity().mBaseChain.getChain(), mDenomTitle);
            mMaxAvailable = getSActivity().mAccount.getTokenBalance(getSActivity().mSecretDenom).subtract(new BigDecimal("20000"));
            mAvailableAmount.setText(WDp.getDpAmount2(getContext(), mMaxAvailable, 6, 6));

        } else if (getSActivity().mBaseChain.equals(AKASH_MAIN)) {
            mDpDecimal = 6;
            setDpDecimals(mDpDecimal);
            WDp.DpMainDenom(getContext(), getSActivity().mBaseChain.getChain(), mDenomTitle);
            mMaxAvailable = getSActivity().mAccount.getTokenBalance(getSActivity().mAkashDenom).subtract(new BigDecimal("2500"));
            mAvailableAmount.setText(WDp.getDpAmount2(getContext(), mMaxAvailable, 6, 6));

        }

        else if (getSActivity().mBaseChain.equals(COSMOS_MAIN)) {
            if (getSActivity().mDenom.equals(WDp.mainDenom(getSActivity().mBaseChain))) {
                mDpDecimal = 6;
                setDpDecimals(mDpDecimal);
                WDp.DpMainDenom(getContext(), getSActivity().mBaseChain.getChain(), mDenomTitle);

                BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getContext(), getSActivity().mBaseChain, CONST_PW_TX_SIMPLE_SEND, 0);
                mMaxAvailable = WDp.getAvailable(getBaseDao(), getSActivity().mDenom).subtract(feeAmount);
                mAvailableAmount.setText(WDp.getDpAmount2(getContext(), mMaxAvailable, 6, 6));
            }

        } else if (getSActivity().mBaseChain.equals(COSMOS_TEST)) {
            if (getSActivity().mDenom.equals(WDp.mainDenom(getSActivity().mBaseChain))) {
                mDpDecimal = 6;
                setDpDecimals(mDpDecimal);
                WDp.DpMainDenom(getContext(), getSActivity().mBaseChain.getChain(), mDenomTitle);

                BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getContext(), getSActivity().mBaseChain, CONST_PW_TX_SIMPLE_SEND, 0);
                mMaxAvailable = getBaseDao().getAvailable(getSActivity().mDenom).subtract(feeAmount);
                mAvailableAmount.setText(WDp.getDpAmount2(getContext(), mMaxAvailable, 6, 6));

            }

        } else if (getSActivity().mBaseChain.equals(IRIS_MAIN)) {
            mDpDecimal = getSActivity().mIrisToken_V1.scale;
            if (getSActivity().mDenom.equals(WDp.mainDenom(getSActivity().mBaseChain))) {
                setDpDecimals(mDpDecimal);
                WDp.DpMainDenom(getContext(), getSActivity().mBaseChain.getChain(), mDenomTitle);

                BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getContext(), getSActivity().mBaseChain, CONST_PW_TX_SIMPLE_SEND, 0);
                mMaxAvailable = WDp.getAvailable(getBaseDao(), getSActivity().mDenom).subtract(feeAmount);
                mAvailableAmount.setText(WDp.getDpAmount2(getContext(), mMaxAvailable, 6, mDpDecimal));

            } else {

            }

        } else if (getSActivity().mBaseChain.equals(IRIS_TEST)) {
            if (getSActivity().mDenom.equals(WDp.mainDenom(getSActivity().mBaseChain))) {
                mDpDecimal = 6;
                setDpDecimals(mDpDecimal);
                WDp.DpMainDenom(getContext(), getSActivity().mBaseChain.getChain(), mDenomTitle);

                BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getContext(), getSActivity().mBaseChain, CONST_PW_TX_SIMPLE_SEND, 0);
                mMaxAvailable = getBaseDao().getAvailable(getSActivity().mDenom).subtract(feeAmount);
                mAvailableAmount.setText(WDp.getDpAmount2(getContext(), mMaxAvailable, 6, 6));

            }
        }
        onAddAmountWatcher();
    }


    private void onAddAmountWatcher() {
        mAmountInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable et) {
                String es = et.toString().trim();
                if(TextUtils.isEmpty(es)) {
                    mAmountInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                } else if (es.startsWith(".")) {
                    mAmountInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                    mAmountInput.setText("");
                } else if (es.endsWith(".")) {
                    mAmountInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                    mAmountInput.setVisibility(View.VISIBLE);
                } else if(es.length() > 1 && es.startsWith("0") && !es.startsWith("0.")) {
                    mAmountInput.setText("0");
                    mAmountInput.setSelection(1);
                }

                if (es.equals(mDecimalChecker)) {
                    mAmountInput.setText(mDecimalSetter);
                    mAmountInput.setSelection(mDpDecimal + 1);
                } else {
                    try {
                        final BigDecimal inputAmount = new BigDecimal(es);
                        if (BigDecimal.ZERO.compareTo(inputAmount) >= 0 ){
                            mAmountInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                            return;
                        }

                        BigDecimal checkPosition = inputAmount.movePointRight(mDpDecimal);
                        BigDecimal checkMax = checkPosition.setScale(0, RoundingMode.DOWN);
                        if (checkPosition.compareTo(checkMax) != 0) {
                            String recover = es.substring(0, es.length() - 1);
                            mAmountInput.setText(recover);
                            mAmountInput.setSelection(recover.length());
                            return;
                        }

                        if (getSActivity().mBaseChain.equals(COSMOS_MAIN) || getSActivity().mBaseChain.equals(IRIS_MAIN) || getSActivity().mBaseChain.equals(KAVA_MAIN) ||
                                getSActivity().mBaseChain.equals(KAVA_TEST) || getSActivity().mBaseChain.equals(IOV_MAIN) || getSActivity().mBaseChain.equals(BaseChain.IOV_TEST) ||
                                getSActivity().mBaseChain.equals(BAND_MAIN) || getSActivity().mBaseChain.equals(CERTIK_MAIN) || getSActivity().mBaseChain.equals(CERTIK_TEST) ||
                                getSActivity().mBaseChain.equals(AKASH_MAIN) || getSActivity().mBaseChain.equals(SECRET_MAIN) || getSActivity().mBaseChain.equals(COSMOS_TEST) ||
                                getSActivity().mBaseChain.equals(IRIS_TEST)) {
                            if(inputAmount.compareTo(mMaxAvailable.movePointLeft(mDpDecimal).setScale(mDpDecimal, RoundingMode.CEILING)) > 0) {
                                mAmountInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                            } else {
                                mAmountInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                            }

                        } else if (getSActivity().mBaseChain.equals(BNB_MAIN) || getSActivity().mBaseChain.equals(BNB_TEST) || getSActivity().mBaseChain.equals(OKEX_MAIN) || getSActivity().mBaseChain.equals(OK_TEST)) {
                            if (inputAmount.compareTo(mMaxAvailable) > 0) {
                                mAmountInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                            } else {
                                mAmountInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                            }
                        }
                        mAmountInput.setSelection(mAmountInput.getText().length());
                    } catch (Exception e) { }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBefore)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            if(isValidateSendAmount()) {
                getSActivity().mTargetCoins = mToSendCoins;
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
            if(es.length() > 0) {
                existed = new BigDecimal(es);
            }
            mAmountInput.setText(existed.add(new BigDecimal("1")).toPlainString());

        } else if (v.equals(mAdd10)) {
            BigDecimal existed = BigDecimal.ZERO;
            String es = mAmountInput.getText().toString().trim();
            if(es.length() > 0) {
                existed = new BigDecimal(es);
            }
            mAmountInput.setText(existed.add(new BigDecimal("10")).toPlainString());

        } else if (v.equals(mAdd100)) {
            BigDecimal existed = BigDecimal.ZERO;
            String es = mAmountInput.getText().toString().trim();
            if(es.length() > 0) {
                existed = new BigDecimal(es);
            }
            mAmountInput.setText(existed.add(new BigDecimal("100")).toPlainString());

        } else if (v.equals(mAddHalf)) {
            if (getSActivity().mBaseChain.equals(BNB_MAIN) || getSActivity().mBaseChain.equals(BNB_TEST)) {
                mAmountInput.setText(mMaxAvailable.divide(new BigDecimal("2"), 8, RoundingMode.DOWN).toPlainString());

            } else if (getSActivity().mBaseChain.equals(KAVA_MAIN) || getSActivity().mBaseChain.equals(KAVA_TEST)) {
                mAmountInput.setText(mMaxAvailable.divide(new BigDecimal(mDecimalDivider2), mDpDecimal, RoundingMode.DOWN).toPlainString());

            } else if (getSActivity().mBaseChain.equals(IOV_MAIN) || getSActivity().mBaseChain.equals(IOV_TEST)) {
                mAmountInput.setText(mMaxAvailable.divide(new BigDecimal("2000000"), 6, RoundingMode.DOWN).toPlainString());

            } else if (getSActivity().mBaseChain.equals(BAND_MAIN)) {
                mAmountInput.setText(mMaxAvailable.divide(new BigDecimal("2000000"), 6, RoundingMode.DOWN).toPlainString());

            } else if (getSActivity().mBaseChain.equals(OKEX_MAIN) || getSActivity().mBaseChain.equals(OK_TEST)) {
                mAmountInput.setText(mMaxAvailable.divide(new BigDecimal("2"), mDpDecimal, RoundingMode.DOWN).toPlainString());

            } else if (getSActivity().mBaseChain.equals(CERTIK_MAIN) || getSActivity().mBaseChain.equals(CERTIK_TEST)) {
                mAmountInput.setText(mMaxAvailable.divide(new BigDecimal("2000000"), 6, RoundingMode.DOWN).toPlainString());

            } else if (getSActivity().mBaseChain.equals(SECRET_MAIN)) {
                mAmountInput.setText(mMaxAvailable.divide(new BigDecimal("2000000"), 6, RoundingMode.DOWN).toPlainString());

            } else if (getSActivity().mBaseChain.equals(AKASH_MAIN)) {
                mAmountInput.setText(mMaxAvailable.divide(new BigDecimal("2000000"), 6, RoundingMode.DOWN).toPlainString());

            }

            else if (getSActivity().mBaseChain.equals(COSMOS_MAIN) || getSActivity().mBaseChain.equals(COSMOS_TEST) || getSActivity().mBaseChain.equals(IRIS_MAIN) || getSActivity().mBaseChain.equals(IRIS_TEST)) {
                mAmountInput.setText(mMaxAvailable.divide(new BigDecimal(mDecimalDivider2), mDpDecimal, RoundingMode.DOWN).toPlainString());
            }

        } else if (v.equals(mAddMax)) {
            if (getSActivity().mBaseChain.equals(BNB_MAIN) || getSActivity().mBaseChain.equals(BNB_TEST)) {
                mAmountInput.setText(mMaxAvailable.toPlainString());
                if (getSActivity().mBnbToken.symbol.equals(TOKEN_BNB)) {
                    onShowEmptyBlanaceWarnDialog();
                }

            } else if (getSActivity().mBaseChain.equals(KAVA_MAIN) || getSActivity().mBaseChain.equals(KAVA_TEST)) {
                mAmountInput.setText(mMaxAvailable.divide(new BigDecimal(mDecimalDivider1), mDpDecimal, RoundingMode.DOWN).toPlainString());

            } else if (getSActivity().mBaseChain.equals(IOV_MAIN) || getSActivity().mBaseChain.equals(IOV_TEST)) {
                mAmountInput.setText(mMaxAvailable.toPlainString());
                mAmountInput.setText(mMaxAvailable.divide(new BigDecimal("1000000"), 6, RoundingMode.DOWN).toPlainString());
                if (getSActivity().mIovDenom.equals(TOKEN_IOV) || getSActivity().mIovDenom.equals(TOKEN_IOV_TEST)) {
                    onShowEmptyBlanaceWarnDialog();
                }

            } else if (getSActivity().mBaseChain.equals(BAND_MAIN)) {
                mAmountInput.setText(mMaxAvailable.divide(new BigDecimal("1000000"), 6, RoundingMode.DOWN).toPlainString());

            } else if (getSActivity().mBaseChain.equals(OKEX_MAIN) || getSActivity().mBaseChain.equals(OK_TEST)) {
                mAmountInput.setText(mMaxAvailable.toPlainString());
                if (getSActivity().mOkDenom.equals(TOKEN_OK)) {
                    onShowEmptyBlanaceWarnDialog();
                }

            } else if (getSActivity().mBaseChain.equals(CERTIK_MAIN) || getSActivity().mBaseChain.equals(CERTIK_TEST)) {
                mAmountInput.setText(mMaxAvailable.divide(new BigDecimal("1000000"), 6, RoundingMode.DOWN).toPlainString());
                if (getSActivity().mCertikDenom.equals(TOKEN_CERTIK)) {
                    onShowEmptyBlanaceWarnDialog();
                }

            } else if (getSActivity().mBaseChain.equals(SECRET_MAIN)) {
                mAmountInput.setText(mMaxAvailable.divide(new BigDecimal("1000000"), 6, RoundingMode.DOWN).toPlainString());
                if (getSActivity().mAkashDenom.equals(TOKEN_AKASH)) {
                    onShowEmptyBlanaceWarnDialog();
                }

            } else if (getSActivity().mBaseChain.equals(AKASH_MAIN)) {
                mAmountInput.setText(mMaxAvailable.divide(new BigDecimal("1000000"), 6, RoundingMode.DOWN).toPlainString());
                if (getSActivity().mAkashDenom.equals(TOKEN_AKASH)) {
                    onShowEmptyBlanaceWarnDialog();
                }

            }

            else if (getSActivity().mBaseChain.equals(COSMOS_MAIN) || getSActivity().mBaseChain.equals(COSMOS_TEST) || getSActivity().mBaseChain.equals(IRIS_MAIN) || getSActivity().mBaseChain.equals(IRIS_TEST)) {
                mAmountInput.setText(mMaxAvailable.divide(new BigDecimal(mDecimalDivider1), mDpDecimal, RoundingMode.DOWN).toPlainString());
                if (getSActivity().mDenom.equals(WDp.mainDenom(getSActivity().mBaseChain))) {
                    onShowEmptyBlanaceWarnDialog();
                }
            }

        } else if (v.equals(mClearAll)) {
            mAmountInput.setText("");

        }

    }

    private boolean isValidateSendAmount() {
        mToSendCoins.clear();
        try {
            if (getSActivity().mBaseChain.equals(BNB_MAIN) || getSActivity().mBaseChain.equals(BNB_TEST)) {
                BigDecimal sendTemp = new BigDecimal(mAmountInput.getText().toString().trim());
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

            } else if (getSActivity().mBaseChain.equals(KAVA_MAIN) || getSActivity().mBaseChain.equals(KAVA_TEST)) {
                BigDecimal sendTemp = new BigDecimal(mAmountInput.getText().toString().trim());
                if (sendTemp.compareTo(BigDecimal.ZERO) <= 0) return false;
                if (sendTemp.compareTo(mMaxAvailable.movePointLeft(mDpDecimal).setScale(mDpDecimal, RoundingMode.CEILING)) > 0) return false;
                if (getSActivity().mDenom.equals(TOKEN_KAVA)) {
                    Coin kava = new Coin(TOKEN_KAVA, sendTemp.multiply(new BigDecimal(mDecimalDivider1)).setScale(0).toPlainString());
                    mToSendCoins.add(kava);
                } else {
                    Coin kavaToken = new Coin(getSActivity().mDenom, sendTemp.multiply(new BigDecimal(mDecimalDivider1)).setScale(0).toPlainString());
                    mToSendCoins.add(kavaToken);
                }
                return true;

            } else if (getSActivity().mBaseChain.equals(IOV_MAIN)) {
                BigDecimal sendTemp = new BigDecimal(mAmountInput.getText().toString().trim());
                if (sendTemp.compareTo(BigDecimal.ZERO) <= 0) return false;
                if (sendTemp.compareTo(mMaxAvailable.movePointLeft(mDpDecimal).setScale(mDpDecimal, RoundingMode.CEILING)) > 0) return false;
                Coin iov = new Coin(TOKEN_IOV, sendTemp.multiply(new BigDecimal(mDecimalDivider1)).setScale(0).toPlainString());
                mToSendCoins.add(iov);
                return true;

            } else if (getSActivity().mBaseChain.equals(BAND_MAIN)) {
                BigDecimal sendTemp = new BigDecimal(mAmountInput.getText().toString().trim());
                if (sendTemp.compareTo(BigDecimal.ZERO) <= 0) return false;
                if (sendTemp.compareTo(mMaxAvailable.movePointLeft(6).setScale(6, RoundingMode.CEILING)) > 0) return false;
                Coin band = new Coin(TOKEN_BAND, sendTemp.multiply(new BigDecimal("1000000")).setScale(0).toPlainString());
                mToSendCoins.add(band);
                return true;

            } else if (getSActivity().mBaseChain.equals(IOV_TEST)) {
                BigDecimal sendTemp = new BigDecimal(mAmountInput.getText().toString().trim());
                if (sendTemp.compareTo(BigDecimal.ZERO) <= 0) return false;
                if (sendTemp.compareTo(mMaxAvailable.movePointLeft(mDpDecimal).setScale(mDpDecimal, RoundingMode.CEILING)) > 0) return false;
                Coin iov = new Coin(TOKEN_IOV_TEST, sendTemp.multiply(new BigDecimal(mDecimalDivider1)).setScale(0).toPlainString());
                mToSendCoins.add(iov);
                return true;

            } else if (getSActivity().mBaseChain.equals(OKEX_MAIN) || getSActivity().mBaseChain.equals(OK_TEST)) {
                BigDecimal sendTemp = new BigDecimal(mAmountInput.getText().toString().trim());
                if (sendTemp.compareTo(BigDecimal.ZERO) <= 0) return false;
                if (sendTemp.compareTo(mMaxAvailable) > 0) return false;
                Coin token = new Coin(getSActivity().mOkDenom, sendTemp.setScale(mDpDecimal).toPlainString());
                mToSendCoins.add(token);
                return true;

            } else if (getSActivity().mBaseChain.equals(CERTIK_MAIN) || getSActivity().mBaseChain.equals(CERTIK_TEST)) {
                BigDecimal sendTemp = new BigDecimal(mAmountInput.getText().toString().trim());
                if (sendTemp.compareTo(BigDecimal.ZERO) <= 0) return false;
                if (sendTemp.compareTo(mMaxAvailable.movePointLeft(mDpDecimal).setScale(mDpDecimal, RoundingMode.CEILING)) > 0) return false;
                Coin certik = new Coin(getSActivity().mCertikDenom, sendTemp.multiply(new BigDecimal(mDecimalDivider1)).setScale(0).toPlainString());
                mToSendCoins.add(certik);
                return true;

            } else if (getSActivity().mBaseChain.equals(SECRET_MAIN)) {
                BigDecimal sendTemp = new BigDecimal(mAmountInput.getText().toString().trim());
                if (sendTemp.compareTo(BigDecimal.ZERO) <= 0) return false;
                if (sendTemp.compareTo(mMaxAvailable.movePointLeft(mDpDecimal).setScale(mDpDecimal, RoundingMode.CEILING)) > 0) return false;
                Coin certik = new Coin(getSActivity().mSecretDenom, sendTemp.multiply(new BigDecimal(mDecimalDivider1)).setScale(0).toPlainString());
                mToSendCoins.add(certik);
                return true;

            } else if (getSActivity().mBaseChain.equals(AKASH_MAIN)) {
                BigDecimal sendTemp = new BigDecimal(mAmountInput.getText().toString().trim());
                if (sendTemp.compareTo(BigDecimal.ZERO) <= 0) return false;
                if (sendTemp.compareTo(mMaxAvailable.movePointLeft(mDpDecimal).setScale(mDpDecimal, RoundingMode.CEILING)) > 0) return false;
                Coin certik = new Coin(getSActivity().mAkashDenom, sendTemp.multiply(new BigDecimal(mDecimalDivider1)).setScale(0).toPlainString());
                mToSendCoins.add(certik);
                return true;

            }

            else if (getSActivity().mBaseChain.equals(COSMOS_MAIN) || getSActivity().mBaseChain.equals(COSMOS_TEST) || getSActivity().mBaseChain.equals(IRIS_MAIN) || getSActivity().mBaseChain.equals(IRIS_TEST)) {
                BigDecimal sendTemp = new BigDecimal(mAmountInput.getText().toString().trim());
                if (sendTemp.compareTo(BigDecimal.ZERO) <= 0) return false;
                if (sendTemp.compareTo(mMaxAvailable.movePointLeft(mDpDecimal).setScale(mDpDecimal, RoundingMode.CEILING)) > 0) return false;
                Coin coin = new Coin(getSActivity().mDenom, sendTemp.movePointRight(mDpDecimal).setScale(0).toPlainString());
                mToSendCoins.add(coin);
                return true;

            }
            return false;

        } catch (Exception e) {
            mToSendCoins.clear();
            return false;
        }
    }

    private void onShowEmptyBlanaceWarnDialog() {
        Dialog_Empty_Warnning dialog = Dialog_Empty_Warnning.newInstance();
        dialog.setCancelable(true);
        dialog.show(getFragmentManager().beginTransaction(), "dialog");
    }

    private void setDpDecimals(int decimals) {
        mDecimalChecker = "0.";
        mDecimalSetter = "0.";
        mDecimalDivider2 = "2";
        mDecimalDivider1 = "1";
        for (int i = 0; i < decimals; i ++) {
            mDecimalChecker = mDecimalChecker+"0";
            mDecimalDivider2 = mDecimalDivider2 + "0";
            mDecimalDivider1 = mDecimalDivider1 + "0";
        }
        for (int i = 0; i < decimals-1; i ++) {
            mDecimalSetter = mDecimalSetter+"0";
        }
    }

    private SendActivity getSActivity() {
        return (SendActivity)getBaseActivity();
    }


}
