package wannabit.io.cosmostaion.fragment.chains.osmosis;

import android.os.AsyncTask;
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

import osmosis.gamm.v1beta1.PoolOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.osmosis.JoinPoolActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.OsmosisGrpcPoolInfoTask;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_OSMOSIS_POOL_INFO;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OSMOSIS;

public class JoinPoolStep0Fragment extends BaseFragment implements TaskListener, View.OnClickListener {

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

    private PoolOuterClass.Pool mSelcetedPool;
    private int             mTxType;
    private String          mPoolId;
    private BigDecimal      available0MaxAmount , available1MaxAmount;
    private int             coin0Decimal = 6, coin1Decimal = 6;
    private BigDecimal      depositRate = BigDecimal.ONE;

    private String          mInDecimalChecker, mInDecimalSetter;
    private String          mOutDecimalChecker,mOutDecimalSetter;


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
        View rootView = inflater.inflate(R.layout.fragment_join_pool_step0, container, false);
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

        mPoolId = getSActivity().getIntent().getStringExtra("mPoolId");
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
        setDpDecimals(coin0Decimal, coin1Decimal);

        WUtil.DpOsmosisTokenImg(mJoinPoolInputImg, coin0Denom);
        WUtil.dpOsmosisTokenName(getSActivity(), mJoinPoolInputSymbol, coin0Denom);
        WUtil.DpOsmosisTokenImg(mJoinPoolOutputImg, coin1Denom);
        WUtil.dpOsmosisTokenName(getSActivity(), mJoinPoolOutputSymbol, coin1Denom);
        WDp.showCoinDp(getSActivity(), WUtil.dpOsmosisTokenName(getSActivity(), mJoinPoolIntputDenom, coin0Denom), available0MaxAmount.toString(), mJoinPoolIntputDenom, mJoinPoolInputAmount, BaseChain.OSMOSIS_MAIN);
        WDp.showCoinDp(getSActivity(), WUtil.dpOsmosisTokenName(getSActivity(), mJoinPoolOutputDenom, coin1Denom), available1MaxAmount.toString(), mJoinPoolOutputDenom, mJoinPoolOutputAmount, BaseChain.OSMOSIS_MAIN);

        BigDecimal coin0Amount = new BigDecimal(getSActivity().getIntent().getStringExtra("coin0Amount"));
        BigDecimal coin1Amount = new BigDecimal(getSActivity().getIntent().getStringExtra("coin1Amount"));
        depositRate = coin1Amount.divide(coin0Amount, 18, RoundingMode.DOWN);

        onAddAmountWatcher();
    }

    @Override
    public void onRefreshTab() {
        mSelcetedPool = getSActivity().getBaseDao().mSelectedPool;
    }

    private int mTaskCount;
    public void onFetchPoolInfo() {
        getSActivity().onShowWaitDialog();
        mTaskCount = 1;
        new OsmosisGrpcPoolInfoTask(getBaseApplication(), this, BaseChain.OSMOSIS_MAIN, mPoolId).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (getSActivity().isFinishing()) return;
        mTaskCount--;
        if (result.taskType == TASK_GRPC_FETCH_OSMOSIS_POOL_INFO) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mSelectedPool = (PoolOuterClass.Pool) result.resultData;
            }
        }

        if (mTaskCount == 0) {
            getSActivity().onHideWaitDialog();
        }
    }

    private void onAddAmountWatcher() {
        mJoinPoolInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable et) {
                onUIUpdate0();
                String es = et.toString().trim();
                if (TextUtils.isEmpty(es)) {
                    mJoinPoolInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                } else if (es.startsWith(".")) {
                    mJoinPoolInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                    mJoinPoolInput.setText("");
                } else if (es.endsWith(".")) {
                    mJoinPoolInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                    mJoinPoolInput.setVisibility(View.VISIBLE);
                } else if(mJoinPoolInput.length() > 1 && es.startsWith("0") && !es.startsWith("0.")) {
                    mJoinPoolInput.setText("0");
                    mJoinPoolInput.setSelection(1);
                }

                if (es.equals(mInDecimalChecker)) {
                    mJoinPoolInput.setText(mInDecimalSetter);
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
                } else if(mJoinPoolOutput.length() > 1 && es.startsWith("0") && !es.startsWith("0.")) {
                    mJoinPoolOutput.setText("0");
                    mJoinPoolOutput.setSelection(1);
                }

                if (es.equals(mOutDecimalChecker)) {
                    mJoinPoolOutput.setText(mOutDecimalSetter);
                    mJoinPoolOutput.setSelection(coin1Decimal + 1);
                } else {
                    try {
                        final BigDecimal inputAmount = new BigDecimal(es);
                        if (BigDecimal.ZERO.compareTo(inputAmount) >= 0 ){
                            mJoinPoolOutput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                            return;
                        }

                        BigDecimal checkPosition = inputAmount.movePointRight(coin1Decimal);
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

    private void onUIUpdate0() {
        try {
            BigDecimal InputAmountTemp = new BigDecimal(mJoinPoolInput.getText().toString().trim());
            if (InputAmountTemp.compareTo(BigDecimal.ZERO) <= 0) {
                mJoinPoolInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                mJoinPoolOutput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                return;
            }
            if (InputAmountTemp.movePointLeft(coin0Decimal).compareTo(available0MaxAmount) > 0) {
                mJoinPoolInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                mJoinPoolOutput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                return;
            }
            mJoinPoolInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));

            BigDecimal OutputAmount = InputAmountTemp.multiply(depositRate).movePointLeft(coin0Decimal);
            mJoinPoolOutput.setText(OutputAmount.movePointLeft(-coin1Decimal).toPlainString());
            if (OutputAmount.compareTo(available1MaxAmount) > 0 ) {
                mJoinPoolOutput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
            } else {
                mJoinPoolOutput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
            }
        } catch (Exception e) { }
    }

    private void onUIUpdate1() {
        BigDecimal OutputAmountTemp = new BigDecimal(mJoinPoolOutput.getText().toString().trim());
        if (OutputAmountTemp.compareTo(BigDecimal.ZERO) <= 0) {
            mJoinPoolOutput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
            mJoinPoolInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
            return;
        }
        if (OutputAmountTemp.movePointLeft(coin1Decimal).compareTo(available1MaxAmount) > 0) {
            mJoinPoolOutput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
            mJoinPoolInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
            return;
        }
        mJoinPoolOutput.setBackground(getResources().getDrawable(R.drawable.edittext_box));

        BigDecimal InputAmount = OutputAmountTemp.divide(depositRate, 0, RoundingMode.DOWN).movePointLeft(coin1Decimal);
        mJoinPoolInput.setText(InputAmount.movePointLeft(-coin0Decimal).toPlainString());
        if (InputAmount.compareTo(available0MaxAmount) > 0 ) {
            mJoinPoolInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
        } else {
            mJoinPoolInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
        }
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
            if (isValidateJoinPoolInputAmount() && isValidateJoinPoolOutputAmount()) {
                getSActivity().onNextStep();
            } else {
                Toast.makeText(getContext(), R.string.error_invalid_amounts, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isValidateJoinPoolInputAmount() {
        try {
            BigDecimal InputAmountTemp = new BigDecimal(mJoinPoolInput.getText().toString().trim());
            if (InputAmountTemp.compareTo(BigDecimal.ZERO) <= 0) return false;
            if (InputAmountTemp.compareTo(available0MaxAmount.movePointLeft(coin0Decimal).setScale(coin0Decimal, RoundingMode.CEILING)) > 0) return false;
            Coin coin = new Coin(WDp.mainDenom(getSActivity().mBaseChain), InputAmountTemp.movePointRight(coin0Decimal).setScale(0).toPlainString());
            getSActivity().mAmount = coin;
            return true;

        } catch (Exception e) {
            getSActivity().mAmount = null;
            return false;
        }
    }

    private boolean isValidateJoinPoolOutputAmount() {
        try {
            BigDecimal OutputAmountTemp = new BigDecimal(mJoinPoolOutput.getText().toString().trim());
            if (OutputAmountTemp.compareTo(BigDecimal.ZERO) <= 0) return false;
            if (OutputAmountTemp.compareTo(available1MaxAmount.movePointLeft(coin1Decimal).setScale(coin1Decimal, RoundingMode.CEILING)) > 0) return false;
            Coin coin = new Coin(WDp.mainDenom(getSActivity().mBaseChain), OutputAmountTemp.movePointRight(coin1Decimal).setScale(0).toPlainString());
            getSActivity().mAmount = coin;
            return true;

        } catch (Exception e) {
            getSActivity().mAmount = null;
            return false;
        }
    }

    private void setDpDecimals(int indecimals, int outdecimals) {
        mInDecimalChecker = "0.";
        mInDecimalSetter = "0.";
        mOutDecimalChecker = "0.";
        mOutDecimalSetter = "0.";
        for (int i = 0; i < indecimals; i ++) {
            mInDecimalChecker = mInDecimalChecker + "0";
        }
        for (int i = 0; i < indecimals-1; i ++) {
            mInDecimalSetter = mInDecimalSetter + "0";
        }
        for (int i = 0; i < outdecimals; i ++) {
            mOutDecimalChecker = mOutDecimalChecker + "0";
        }
        for (int i = 0; i < outdecimals-1; i ++) {
            mOutDecimalSetter = mOutDecimalSetter + "0";
        }
    }

    private JoinPoolActivity getSActivity() { return (JoinPoolActivity)getBaseActivity(); }

}
