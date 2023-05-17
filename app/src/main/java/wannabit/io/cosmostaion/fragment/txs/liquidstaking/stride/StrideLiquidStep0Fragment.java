package wannabit.io.cosmostaion.fragment.txs.liquidstaking.stride;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_STRIDE_LIQUID_STAKING;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_HOST_ZONE_CHAINID;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.math.BigDecimal;
import java.math.RoundingMode;

import stride.stakeibc.HostZoneOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.liquidstaking.StrideLiquidActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.HostZoneGrpcTask;
import wannabit.io.cosmostaion.utils.WDp;

public class StrideLiquidStep0Fragment extends BaseFragment implements View.OnClickListener, TaskListener {

    private RelativeLayout mProgress;
    private Button mCancelBtn, mNextBtn;

    private TextView mLSAvailAmount, mLSAvailAmountSymbol;

    private ImageView mLSInputImg;
    private TextView mLSInputSymbol;
    private EditText mLSInputAmount;
    private ImageView mBtnLSInputClear;
    private Button mBtnLSInput1_4, mBtnLSInputHalf, mBtnLSInput3_4, mBtnLSInputMax;

    private ImageView mLSOutputImg;
    private TextView mLSOutputSymbol;
    private TextView mLSOutputAmount;

    private String mOutputDenom;
    private int mInputCoinDecimal;
    private int mOutputCoinDecimal;
    private BigDecimal mAvailableMaxAmount;

    private String mInDecimalChecker, mInDecimalSetter;

    public static StrideLiquidStep0Fragment newInstance() {
        return new StrideLiquidStep0Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_liquid_step0, container, false);
        mCancelBtn = rootView.findViewById(R.id.btn_cancel);
        mNextBtn = rootView.findViewById(R.id.btn_next);
        mProgress = rootView.findViewById(R.id.progress);

        mLSAvailAmount = rootView.findViewById(R.id.ls_available_amount);
        mLSAvailAmountSymbol = rootView.findViewById(R.id.ls_available_amount_symbol);
        mLSInputImg = rootView.findViewById(R.id.ls_input_icon);
        mLSInputSymbol = rootView.findViewById(R.id.ls_input_symbol);
        mBtnLSInputClear = rootView.findViewById(R.id.ls_input_clear);
        mLSInputAmount = rootView.findViewById(R.id.ls_input_amount);
        mBtnLSInput1_4 = rootView.findViewById(R.id.ls_input_1_4);
        mBtnLSInputHalf = rootView.findViewById(R.id.ls_input_half);
        mBtnLSInput3_4 = rootView.findViewById(R.id.ls_input_3_4);
        mBtnLSInputMax = rootView.findViewById(R.id.ls_input_max);

        mLSOutputImg = rootView.findViewById(R.id.ls_output_icon);
        mLSOutputSymbol = rootView.findViewById(R.id.ls_output_symbol);
        mLSOutputAmount = rootView.findViewById(R.id.ls_pool_output);

        mBtnLSInputClear.setOnClickListener(this);
        mBtnLSInput1_4.setOnClickListener(this);
        mBtnLSInputHalf.setOnClickListener(this);
        mBtnLSInput3_4.setOnClickListener(this);
        mBtnLSInputMax.setOnClickListener(this);

        mCancelBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

        onFetchData();
        onAddAmountWatcher();
        return rootView;
    }

    private void onInitView() {
        mProgress.setVisibility(View.GONE);

        mInputCoinDecimal = WDp.getDenomDecimal(getBaseDao(), getSActivity().mChainConfig, getSActivity().mInputDenom);
        setDpDecimals(mInputCoinDecimal);
        mAvailableMaxAmount = getBaseDao().getAvailable(getSActivity().mInputDenom);
        WDp.setDpSymbolImg(getBaseDao(), getSActivity().mChainConfig, getSActivity().mInputDenom, mLSInputImg);
        WDp.setDpSymbol(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mInputDenom, mLSInputSymbol);
        WDp.setDpCoin(getActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mInputDenom, mAvailableMaxAmount.toPlainString(), mLSAvailAmountSymbol, mLSAvailAmount);

        if (getSActivity().mTxType == CONST_PW_TX_STRIDE_LIQUID_STAKING) {
            mOutputDenom = "st" + getSActivity().mHostZone.getHostDenom();
        } else {
            mOutputDenom = getSActivity().mHostZone.getIbcDenom();
        }

        mOutputCoinDecimal = WDp.getDenomDecimal(getBaseDao(), getSActivity().mChainConfig, mOutputDenom);
        WDp.setDpSymbolImg(getBaseDao(), getSActivity().mChainConfig, mOutputDenom, mLSOutputImg);
        WDp.setDpSymbol(getSActivity(), getBaseDao(), getSActivity().mChainConfig, mOutputDenom, mLSOutputSymbol);
    }

    private void onAddAmountWatcher() {
        mLSInputAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable et) {
                onUpdateOutputTextView();
                String es = et.toString().trim();
                if (TextUtils.isEmpty(es)) {
                    mLSInputAmount.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                } else if (es.startsWith(".")) {
                    mLSInputAmount.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                    mLSInputAmount.setText("");
                } else if (es.endsWith(".")) {
                    mLSInputAmount.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                    mLSInputAmount.setVisibility(View.VISIBLE);
                } else if (mLSInputAmount.length() > 1 && es.startsWith("0") && !es.startsWith("0.")) {
                    mLSInputAmount.setText("0");
                    mLSInputAmount.setSelection(1);
                }

                if (es.equals(mInDecimalChecker)) {
                    mLSInputAmount.setText(mInDecimalSetter);
                    mLSInputAmount.setSelection(mInputCoinDecimal + 1);
                } else {
                    try {
                        final BigDecimal inputAmount = new BigDecimal(es);
                        if (BigDecimal.ZERO.compareTo(inputAmount) >= 0) {
                            mLSInputAmount.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                            return;
                        }

                        BigDecimal checkPosition = inputAmount.movePointRight(mInputCoinDecimal);
                        BigDecimal checkMax = checkPosition.setScale(0, RoundingMode.DOWN);
                        if (checkPosition.compareTo(checkMax) != 0 || !checkPosition.equals(checkMax)) {
                            String recover = es.substring(0, es.length() - 1);
                            mLSInputAmount.setText(recover);
                            mLSInputAmount.setSelection(recover.length());
                            return;
                        }

                        if (inputAmount.compareTo(mAvailableMaxAmount.movePointLeft(mInputCoinDecimal).setScale(mInputCoinDecimal, RoundingMode.CEILING)) > 0) {
                            mLSInputAmount.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                        } else {
                            mLSInputAmount.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                        }
                        mLSInputAmount.setSelection(mLSInputAmount.getText().length());

                    } catch (Exception e) {
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mCancelBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            if (isValidateLSInputAmount()) {
                getSActivity().onNextStep();
            } else {
                Toast.makeText(getContext(), R.string.error_invalid_amounts, Toast.LENGTH_SHORT).show();
            }

        } else if (v.equals(mBtnLSInputClear)) {
            mLSInputAmount.setText("");
            mLSOutputAmount.setText("");

        } else if (v.equals(mBtnLSInput1_4)) {
            BigDecimal cal = mAvailableMaxAmount.movePointLeft(mInputCoinDecimal).multiply(new BigDecimal("0.25")).setScale(mInputCoinDecimal, RoundingMode.DOWN);
            mLSInputAmount.setText(cal.toPlainString());
            onUpdateOutputTextView();

        } else if (v.equals(mBtnLSInputHalf)) {
            BigDecimal cal = mAvailableMaxAmount.movePointLeft(mInputCoinDecimal).multiply(new BigDecimal("0.5")).setScale(mInputCoinDecimal, RoundingMode.DOWN);
            mLSInputAmount.setText(cal.toPlainString());
            onUpdateOutputTextView();

        } else if (v.equals(mBtnLSInput3_4)) {
            BigDecimal cal = mAvailableMaxAmount.movePointLeft(mInputCoinDecimal).multiply(new BigDecimal("0.75")).setScale(mInputCoinDecimal, RoundingMode.DOWN);
            mLSInputAmount.setText(cal.toPlainString());
            onUpdateOutputTextView();

        } else if (v.equals(mBtnLSInputMax)) {
            BigDecimal max = mAvailableMaxAmount.movePointLeft(mInputCoinDecimal).setScale(mInputCoinDecimal, RoundingMode.DOWN);
            mLSInputAmount.setText(max.toPlainString());
            onUpdateOutputTextView();

        }
    }

    private void onUpdateOutputTextView() {
        try {
            BigDecimal inputAmountTemp = new BigDecimal(mLSInputAmount.getText().toString().trim());
            BigDecimal rate = new BigDecimal(getSActivity().mHostZone.getRedemptionRate()).movePointLeft(18);
            if (inputAmountTemp.compareTo(BigDecimal.ZERO) == 0) {
                mLSOutputAmount.setText("");
                return;
            }
            BigDecimal outputAmount = BigDecimal.ZERO;
            if (getSActivity().mTxType == CONST_PW_TX_STRIDE_LIQUID_STAKING) {
                outputAmount = inputAmountTemp.movePointRight(mInputCoinDecimal).divide(rate, 0, RoundingMode.DOWN);
            } else {
                outputAmount = inputAmountTemp.movePointRight(mInputCoinDecimal).multiply(rate).setScale(0, RoundingMode.DOWN);
            }
            mLSOutputAmount.setText(outputAmount.movePointLeft(mOutputCoinDecimal).toPlainString());
        } catch (Exception e) { }
    }

    private boolean isValidateLSInputAmount() {
        try {
            BigDecimal inputAmountTemp = new BigDecimal(mLSInputAmount.getText().toString().trim());
            BigDecimal outAmountTemp = new BigDecimal(mLSOutputAmount.getText().toString().trim());
            if (inputAmountTemp.compareTo(BigDecimal.ZERO) <= 0) return false;
            if (inputAmountTemp.compareTo(mAvailableMaxAmount.movePointLeft(mInputCoinDecimal).setScale(mInputCoinDecimal, RoundingMode.CEILING)) > 0)
                return false;

            getSActivity().mSwapInCoin = new Coin(getSActivity().mInputDenom, inputAmountTemp.movePointRight(mInputCoinDecimal).setScale(0).toPlainString());
            getSActivity().mSwapOutCoin = new Coin(mOutputDenom, outAmountTemp.movePointRight(mOutputCoinDecimal).setScale(0).toPlainString());
            return true;

        } catch (Exception e) {
            getSActivity().mKavaSwapIn = null;
            getSActivity().mKavaSwapOut = null;
            return false;
        }
    }

    private int mTaskCount;
    public void onFetchData() {
        mTaskCount = 1;
        new HostZoneGrpcTask(getBaseApplication(), this, getSActivity().mChainConfig, getSActivity().mChainId).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        mTaskCount--;
        if (result.taskType == TASK_GRPC_FETCH_HOST_ZONE_CHAINID) {
            if (result.isSuccess && result.resultData != null) {
                getSActivity().mHostZone = (HostZoneOuterClass.HostZone) result.resultData;
            }
        }
        if (mTaskCount == 0) {
            onInitView();
        }
    }

    private void setDpDecimals(int indecimals) {
        mInDecimalChecker = "0.";
        mInDecimalSetter = "0.";
        for (int i = 0; i < indecimals; i++) {
            mInDecimalChecker = mInDecimalChecker + "0";
        }
        for (int i = 0; i < indecimals - 1; i++) {
            mInDecimalSetter = mInDecimalSetter + "0";
        }
    }

    private StrideLiquidActivity getSActivity() {
        return (StrideLiquidActivity) getBaseActivity();
    }
}
