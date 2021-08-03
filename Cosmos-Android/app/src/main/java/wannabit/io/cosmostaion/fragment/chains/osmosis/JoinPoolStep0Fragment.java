package wannabit.io.cosmostaion.fragment.chains.osmosis;

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

import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.math.RoundingMode;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.osmosis.JoinPoolActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OSMOSIS;

public class JoinPoolStep0Fragment extends BaseFragment implements View.OnClickListener {

    private Button          mCancelBtn, mNextBtn;

    private ImageView       mJoinPoolInputImg;
    private TextView        mJoinPoolInputSymbol;
    private EditText        mJoinPoolInput;
    private ImageView       mBtnJoinPoolInputClear;
    private TextView        mJoinPoolInputAmount, mJoinPoolIntputDenom;
    private Button          mBtnJoinPoolInput1_4, mBtnJoinPoolInputHalf, mBtnJoinPoolInput3_4, mBtnJoinPoolInputMax;

    private ImageView       mJoinPoolOutputImg;
    private TextView        mJoinPoolOutputSymbol;
    private EditText        mJoinPoolOutput;
    private ImageView       mBtnJoinPoolOutputClear;
    private TextView        mJoinPoolOutputAmount, mJoinPoolOutputDenom;
    private Button          mBtnJoinPoolOutput1_4, mBtnJoinPoolOutputHalf, mBtnJoinPoolOutput3_4, mBtnJoinPoolOutputMax;

    private int             mTxType;
    private BigDecimal      available0MaxAmount , available1MaxAmount;
    private int             coin0Decimal = 6, coin1Decimal = 6;

    private String          mDecimalChecker, mDecimalSetter;


    public static JoinPoolStep0Fragment newInstance(Bundle bundle) {
        JoinPoolStep0Fragment fragment = new JoinPoolStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_join_pool0, container, false);
        mCancelBtn                  = rootView.findViewById(R.id.btn_cancel);
        mNextBtn                    = rootView.findViewById(R.id.btn_next);

        mJoinPoolInputImg           = rootView.findViewById(R.id.join_pool_input_icon);
        mJoinPoolInputSymbol        = rootView.findViewById(R.id.join_pool_input_symbol);
        mJoinPoolInput              = rootView.findViewById(R.id.join_pool_input);
        mBtnJoinPoolInputClear      = rootView.findViewById(R.id.join_pool_input_clear);
        mJoinPoolInputAmount        = rootView.findViewById(R.id.join_pool_input_amount);
        mJoinPoolIntputDenom        = rootView.findViewById(R.id.join_pool_input_amount_denom);
        mBtnJoinPoolInput1_4        = rootView.findViewById(R.id.join_pool_input_1_4);
        mBtnJoinPoolInputHalf       = rootView.findViewById(R.id.join_pool_input_half);
        mBtnJoinPoolInput3_4        = rootView.findViewById(R.id.join_pool_input_3_4);
        mBtnJoinPoolInputMax        = rootView.findViewById(R.id.join_pool_input_max);

        mJoinPoolOutputImg          = rootView.findViewById(R.id.join_pool_output_icon);
        mJoinPoolOutputSymbol       = rootView.findViewById(R.id.join_pool_output_symbol);
        mJoinPoolOutput             = rootView.findViewById(R.id.join_pool_output);
        mBtnJoinPoolOutputClear     = rootView.findViewById(R.id.join_pool_output_clear);
        mJoinPoolOutputAmount       = rootView.findViewById(R.id.join_pool_output_amount);
        mJoinPoolOutputDenom        = rootView.findViewById(R.id.join_pool_output_amount_denom);
        mBtnJoinPoolOutput1_4       = rootView.findViewById(R.id.join_pool_output_1_4);
        mBtnJoinPoolOutputHalf      = rootView.findViewById(R.id.join_pool_output_half);
        mBtnJoinPoolOutput3_4       = rootView.findViewById(R.id.join_pool_output_3_4);
        mBtnJoinPoolOutputMax       = rootView.findViewById(R.id.join_pool_output_max);

        mBtnJoinPoolInputClear.setOnClickListener(this);
        mBtnJoinPoolInput1_4.setOnClickListener(this);
        mBtnJoinPoolInputHalf.setOnClickListener(this);
        mBtnJoinPoolInput3_4.setOnClickListener(this);
        mBtnJoinPoolInputMax.setOnClickListener(this);

        mBtnJoinPoolOutputClear.setOnClickListener(this);
        mBtnJoinPoolOutput1_4.setOnClickListener(this);
        mBtnJoinPoolOutputHalf.setOnClickListener(this);
        mBtnJoinPoolOutput3_4.setOnClickListener(this);
        mBtnJoinPoolOutputMax.setOnClickListener(this);

        mCancelBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

        mTxType = getSActivity().getIntent().getIntExtra("mType", -1);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isAdded() || getSActivity() == null || getSActivity().mAccount == null) {
            getSActivity().onBackPressed();
        }
        BigDecimal txFeeAmount = WUtil.getEstimateGasFeeAmount(getSActivity(), getSActivity().mBaseChain, mTxType, 0);
        String coin0Denom = getSActivity().getIntent().getStringExtra("coin0Denom");
        String coin1Denom = getSActivity().getIntent().getStringExtra("coin1Denom");

        available0MaxAmount = getBaseDao().getAvailable(coin0Denom);
        if (coin0Denom.equalsIgnoreCase(TOKEN_OSMOSIS)) {
            available0MaxAmount = available0MaxAmount.subtract(txFeeAmount);
        }
        available1MaxAmount = getBaseDao().getAvailable(coin1Denom);
        if (coin1Denom.equalsIgnoreCase(TOKEN_OSMOSIS)) {
            available1MaxAmount = available1MaxAmount.subtract(txFeeAmount);
        }
        coin0Decimal = WUtil.getOsmosisCoinDecimal(coin0Denom);
        coin1Decimal = WUtil.getOsmosisCoinDecimal(coin1Denom);

        WUtil.DpOsmosisTokenImg(mJoinPoolInputImg, coin0Denom);
        WUtil.getOsmosisTokenName(getSActivity(), mJoinPoolInputSymbol, coin0Denom);
        WUtil.DpOsmosisTokenImg(mJoinPoolOutputImg, coin1Denom);
        WUtil.getOsmosisTokenName(getSActivity(), mJoinPoolOutputSymbol, coin1Denom);
        WDp.showCoinDp(getSActivity(), WUtil.getOsmosisTokenName(getSActivity(), mJoinPoolIntputDenom, coin0Denom), available0MaxAmount.toString(), mJoinPoolIntputDenom, mJoinPoolInputAmount, BaseChain.OSMOSIS_MAIN);
        WDp.showCoinDp(getSActivity(), WUtil.getOsmosisTokenName(getSActivity(), mJoinPoolOutputDenom, coin1Denom), available1MaxAmount.toString(), mJoinPoolOutputDenom, mJoinPoolOutputAmount, BaseChain.OSMOSIS_MAIN);

        onAddAmountWatcher();
    }

    private void onAddAmountWatcher() {
        mJoinPoolInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable et) {
                String es = et.toString().trim();
                if (TextUtils.isEmpty(es)) {
                    mJoinPoolInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                } else if (es.startsWith(".")) {
                    mJoinPoolInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                    mJoinPoolInput.setText("");
                } else if (es.endsWith(".")) {
                    mJoinPoolInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                    mJoinPoolInput.setVisibility(View.VISIBLE);
                }

                if (es.equals(mDecimalChecker)) {
                    mJoinPoolInput.setText(mDecimalSetter);
                    mJoinPoolInput.setSelection(coin0Decimal + 1);
                } else {
                    try {
                        final BigDecimal inputAmount = new BigDecimal(es);
                        if (BigDecimal.ZERO.compareTo(inputAmount) >= 0 ){
                            mJoinPoolInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                            return;
                        }

                        BigDecimal checkPosition = inputAmount.movePointRight(coin0Decimal);
                        BigDecimal checkMax = checkPosition.setScale(0, RoundingMode.DOWN);
                        if (checkPosition.compareTo(checkMax) != 0) {
                            String recover = es.substring(0, es.length() - 1);
                            mJoinPoolInput.setText(recover);
                            mJoinPoolInput.setSelection(recover.length());
                            return;
                        }

                        if (inputAmount.compareTo(available0MaxAmount.movePointLeft(coin0Decimal).setScale(coin0Decimal, RoundingMode.CEILING)) > 0) {
                            mJoinPoolInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                        } else {
                            mJoinPoolInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                        }
                        mJoinPoolInput.setSelection(mJoinPoolInput.getText().length());

                    } catch (Exception e) { }
                }
            }
        });

        mJoinPoolOutput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable et) {
                String es = et.toString().trim();
                if (TextUtils.isEmpty(es)) {
                    mJoinPoolOutput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                } else if (es.startsWith(".")) {
                    mJoinPoolOutput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                    mJoinPoolOutput.setText("");
                } else if (es.endsWith(".")) {
                    mJoinPoolOutput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                    mJoinPoolOutput.setVisibility(View.VISIBLE);
                }

                if (es.equals(mDecimalChecker)) {
                    mJoinPoolOutput.setText(mDecimalSetter);
                    mJoinPoolOutput.setSelection(coin1Decimal + 1);
                } else {
                    try {
                        final BigDecimal inputAmount = new BigDecimal(es);
                        if (BigDecimal.ZERO.compareTo(inputAmount) >= 0 ){
                            mJoinPoolOutput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                            return;
                        }

                        BigDecimal checkPosition = inputAmount.movePointRight(coin0Decimal);
                        BigDecimal checkMax = checkPosition.setScale(0, RoundingMode.DOWN);
                        if (checkPosition.compareTo(checkMax) != 0) {
                            String recover = es.substring(0, es.length() - 1);
                            mJoinPoolOutput.setText(recover);
                            mJoinPoolOutput.setSelection(recover.length());
                            return;
                        }

                        if (inputAmount.compareTo(available1MaxAmount.movePointLeft(coin1Decimal).setScale(coin1Decimal, RoundingMode.CEILING)) > 0) {
                            mJoinPoolOutput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                        } else {
                            mJoinPoolOutput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                        }
                        mJoinPoolOutput.setSelection(mJoinPoolOutput.getText().length());

                    } catch (Exception e) { }
                }
            }
        });
    }

    public void onInputUpdate() {

    }

    @Override
    public void onClick(View v) {
        if (v.equals(mCancelBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mBtnJoinPoolInput1_4)) {
            BigDecimal cal = available0MaxAmount.divide(new BigDecimal(4), 0, RoundingMode.DOWN);
            mJoinPoolInput.setText(cal.movePointLeft(coin0Decimal).toPlainString());

        } else if (v.equals(mBtnJoinPoolInputHalf)) {
            BigDecimal cal = available0MaxAmount.divide(new BigDecimal(2), 0, RoundingMode.DOWN);
            mJoinPoolInput.setText(cal.movePointLeft(coin0Decimal).toPlainString());

        } else if (v.equals(mBtnJoinPoolInput3_4)) {
            BigDecimal cal = available0MaxAmount.multiply(new BigDecimal(0.75)).setScale(0, RoundingMode.DOWN);
            mJoinPoolInput.setText(cal.movePointLeft(coin0Decimal).toPlainString());

        } else if (v.equals(mBtnJoinPoolInputMax)) {
            mJoinPoolInput.setText(available0MaxAmount.movePointLeft(coin0Decimal).toPlainString());

        } else if (v.equals(mBtnJoinPoolInputClear)) {
            mJoinPoolInput.setText("");

        }

        else if (v.equals(mBtnJoinPoolOutput1_4)) {
            BigDecimal cal = available1MaxAmount.divide(new BigDecimal(4), 0, RoundingMode.DOWN);
            mJoinPoolOutput.setText(cal.movePointLeft(coin1Decimal).toPlainString());

        } else if (v.equals(mBtnJoinPoolOutputHalf)) {
            BigDecimal cal = available1MaxAmount.divide(new BigDecimal(2), 0, RoundingMode.DOWN);
            mJoinPoolOutput.setText(cal.movePointLeft(coin1Decimal).toPlainString());

        } else if (v.equals(mBtnJoinPoolOutput3_4)) {
            BigDecimal cal = available1MaxAmount.multiply(new BigDecimal(0.75)).setScale(0, RoundingMode.DOWN);
            mJoinPoolOutput.setText(cal.movePointLeft(coin1Decimal).toPlainString());

        } else if (v.equals(mBtnJoinPoolOutputMax)) {
            mJoinPoolOutput.setText(available1MaxAmount.movePointLeft(coin1Decimal).toPlainString());

        } else if (v.equals(mBtnJoinPoolOutputClear)) {
            mJoinPoolOutput.setText("");
        }

        else if (v.equals(mNextBtn)) {
            getSActivity().onNextStep();
        }
    }

    private void setDpDecimals(int decimals) {
        mDecimalChecker = "0.";
        mDecimalSetter = "0.";
        for (int i = 0; i < decimals; i ++) {
            mDecimalChecker = mDecimalChecker+"0";
        }
        for (int i = 0; i < decimals-1; i ++) {
            mDecimalSetter = mDecimalSetter + "0";
        }
    }

    private JoinPoolActivity getSActivity() { return (JoinPoolActivity)getBaseActivity(); }
}
