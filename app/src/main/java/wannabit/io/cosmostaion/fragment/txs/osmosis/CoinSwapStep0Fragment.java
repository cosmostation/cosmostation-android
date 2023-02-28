package wannabit.io.cosmostaion.fragment.txs.osmosis;

import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import android.annotation.SuppressLint;
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
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import osmosis.gamm.v1beta1.QueryGrpc;
import osmosis.gamm.v1beta1.QueryOuterClass;
import osmosis.gamm.v1beta1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.osmosis.SwapActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Asset;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class CoinSwapStep0Fragment extends BaseFragment implements View.OnClickListener {

    private RelativeLayout mProgress;
    private Button mCancelBtn, mNextBtn;

    private TextView mSwapAvailAmount, mSwapAvailAmountSymbol;

    private ImageView mSwapInputImg;
    private TextView mSwapInputSymbol;
    private EditText mSwapInputAmount;
    private ImageView mBtnSwapInputClear;
    private Button mBtnSwapInput1_4, mBtnSwapInputHalf, mBtnSwapInput3_4, mBtnSwapInputMax;

    private ImageView mSwapOutputImg;
    private TextView mSwapOutputSymbol;
    private TextView mSwapOutputAmount;
    private int mInputCoinDecimal;
    private int mOutputCoinDecimal;
    private BigDecimal mStableSwapRateAmount;
    private BigDecimal mAvailableMaxAmount;
    private String mInDecimalChecker, mInDecimalSetter;

    public static CoinSwapStep0Fragment newInstance() {
        return new CoinSwapStep0Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_swap_step0, container, false);
        mCancelBtn = rootView.findViewById(R.id.btn_cancel);
        mNextBtn = rootView.findViewById(R.id.btn_next);
        mProgress = rootView.findViewById(R.id.progress);

        mSwapAvailAmount = rootView.findViewById(R.id.swap_available_amount);
        mSwapAvailAmountSymbol = rootView.findViewById(R.id.swap_available_amount_symbol);
        mSwapInputImg = rootView.findViewById(R.id.swap_input_icon);
        mSwapInputSymbol = rootView.findViewById(R.id.swap_input_symbol);
        mBtnSwapInputClear = rootView.findViewById(R.id.swap_input_clear);
        mSwapInputAmount = rootView.findViewById(R.id.swap_input_amount);
        mBtnSwapInput1_4 = rootView.findViewById(R.id.swap_input_1_4);
        mBtnSwapInputHalf = rootView.findViewById(R.id.swap_input_half);
        mBtnSwapInput3_4 = rootView.findViewById(R.id.swap_input_3_4);
        mBtnSwapInputMax = rootView.findViewById(R.id.swap_input_max);

        mSwapOutputImg = rootView.findViewById(R.id.swap_output_icon);
        mSwapOutputSymbol = rootView.findViewById(R.id.swap_output_symbol);
        mSwapOutputAmount = rootView.findViewById(R.id.swap_pool_output);

        mBtnSwapInputClear.setOnClickListener(this);
        mBtnSwapInput1_4.setOnClickListener(this);
        mBtnSwapInputHalf.setOnClickListener(this);
        mBtnSwapInput3_4.setOnClickListener(this);
        mBtnSwapInputMax.setOnClickListener(this);

        mCancelBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

        onFetchEstimateOut();
        onAddAmountWatcher();
        return rootView;
    }

    private void onInitView() {
        mProgress.setVisibility(View.GONE);
        setDpDecimals(mInputCoinDecimal);

        mAvailableMaxAmount = getBaseDao().getAvailable(getSActivity().mInputDenom);
        if (getSActivity().mInputDenom.equals(getSActivity().mChainConfig.mainDenom())) {
            mAvailableMaxAmount = mAvailableMaxAmount.subtract(WDp.getMainDenomFee(getActivity(), getBaseDao(), getSActivity().mChainConfig));
        }
        WDp.setDpCoin(getActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mInputDenom, mAvailableMaxAmount.toPlainString(), mSwapAvailAmountSymbol, mSwapAvailAmount);
        WDp.setDpSymbol(getContext(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mInputDenom, mSwapInputSymbol);
        WDp.setDpSymbolImg(getBaseDao(), getSActivity().mChainConfig, getSActivity().mInputDenom, mSwapInputImg);
        WDp.setDpSymbol(getContext(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mOutputDenom, mSwapOutputSymbol);
        WDp.setDpSymbolImg(getBaseDao(), getSActivity().mChainConfig, getSActivity().mOutputDenom, mSwapOutputImg);
    }

    private void onAddAmountWatcher() {
        mSwapInputAmount.addTextChangedListener(new TextWatcher() {
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
                    mSwapInputAmount.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                } else if (es.startsWith(".")) {
                    mSwapInputAmount.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                    mSwapInputAmount.setText("");
                } else if (es.endsWith(".")) {
                    mSwapInputAmount.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                    mSwapInputAmount.setVisibility(View.VISIBLE);
                } else if (mSwapInputAmount.length() > 1 && es.startsWith("0") && !es.startsWith("0.")) {
                    mSwapInputAmount.setText("0");
                    mSwapInputAmount.setSelection(1);
                }

                if (es.equals(mInDecimalChecker)) {
                    mSwapInputAmount.setText(mInDecimalSetter);
                    mSwapInputAmount.setSelection(mInputCoinDecimal + 1);
                } else {
                    try {
                        final BigDecimal inputAmount = new BigDecimal(es);
                        if (BigDecimal.ZERO.compareTo(inputAmount) >= 0) {
                            mSwapInputAmount.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                            return;
                        }

                        BigDecimal checkPosition = inputAmount.movePointRight(mInputCoinDecimal);
                        BigDecimal checkMax = checkPosition.setScale(0, RoundingMode.DOWN);
                        if (checkPosition.compareTo(checkMax) != 0 || !checkPosition.equals(checkMax)) {
                            String recover = es.substring(0, es.length() - 1);
                            mSwapInputAmount.setText(recover);
                            mSwapInputAmount.setSelection(recover.length());
                            return;
                        }

                        if (inputAmount.compareTo(mAvailableMaxAmount.movePointLeft(mInputCoinDecimal).setScale(mInputCoinDecimal, RoundingMode.CEILING)) > 0) {
                            mSwapInputAmount.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                        } else {
                            mSwapInputAmount.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                        }
                        mSwapInputAmount.setSelection(mSwapInputAmount.getText().length());

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
            if (isValidateSwapInputAmount()) {
                getSActivity().onNextStep();
            } else {
                Toast.makeText(getContext(), R.string.error_invalid_amounts, Toast.LENGTH_SHORT).show();
            }

        } else if (v.equals(mBtnSwapInputClear)) {
            mSwapInputAmount.setText("");
            mSwapOutputAmount.setText("");

        } else if (v.equals(mBtnSwapInput1_4)) {
            BigDecimal cal = mAvailableMaxAmount.movePointLeft(mInputCoinDecimal).multiply(new BigDecimal("0.25")).setScale(mInputCoinDecimal, RoundingMode.DOWN);
            mSwapInputAmount.setText(cal.toPlainString());
            onUpdateOutputTextView();

        } else if (v.equals(mBtnSwapInputHalf)) {
            BigDecimal cal = mAvailableMaxAmount.movePointLeft(mInputCoinDecimal).multiply(new BigDecimal("0.5")).setScale(mInputCoinDecimal, RoundingMode.DOWN);
            mSwapInputAmount.setText(cal.toPlainString());
            onUpdateOutputTextView();

        } else if (v.equals(mBtnSwapInput3_4)) {
            BigDecimal cal = mAvailableMaxAmount.movePointLeft(mInputCoinDecimal).multiply(new BigDecimal("0.75")).setScale(mInputCoinDecimal, RoundingMode.DOWN);
            mSwapInputAmount.setText(cal.toPlainString());
            onUpdateOutputTextView();

        } else if (v.equals(mBtnSwapInputMax)) {
            BigDecimal max = mAvailableMaxAmount.movePointLeft(mInputCoinDecimal).setScale(mInputCoinDecimal, RoundingMode.DOWN);
            mSwapInputAmount.setText(max.toPlainString());
            onUpdateOutputTextView();
        }
    }

    private void onUpdateOutputTextView() {
        try {
            BigDecimal InputAmountTemp = new BigDecimal(mSwapInputAmount.getText().toString().trim());

            BigDecimal padding = new BigDecimal("0.97");
            if (InputAmountTemp.compareTo(BigDecimal.ZERO) == 0) {
                mSwapOutputAmount.setText("");
                return;
            }
            BigDecimal OutputAmount = InputAmountTemp.multiply(padding).multiply(mStableSwapRateAmount).setScale(0, RoundingMode.DOWN);
            mSwapOutputAmount.setText(OutputAmount.movePointLeft(mOutputCoinDecimal).toPlainString());
        } catch (Exception e) { }
    }

    private boolean isValidateSwapInputAmount() {
        try {
            BigDecimal InputAmountTemp = new BigDecimal(mSwapInputAmount.getText().toString().trim());
            BigDecimal OutAmountTemp = new BigDecimal(mSwapOutputAmount.getText().toString().trim());
            if (InputAmountTemp.compareTo(BigDecimal.ZERO) <= 0) return false;
            if (InputAmountTemp.compareTo(mAvailableMaxAmount.movePointLeft(mInputCoinDecimal).setScale(mInputCoinDecimal, RoundingMode.CEILING)) > 0)
                return false;

            getSActivity().mSwapInCoin = new Coin(getSActivity().mInputDenom, InputAmountTemp.movePointRight(mInputCoinDecimal).setScale(0).toPlainString());
            getSActivity().mSwapOutCoin = new Coin(getSActivity().mOutputDenom, OutAmountTemp.movePointRight(mOutputCoinDecimal).setScale(0).toPlainString());
            getSActivity().mOsmosisSwapAmountInRoute = Tx.SwapAmountInRoute.newBuilder().setPoolId(getSActivity().mOsmosisPoolId).setTokenOutDenom(getSActivity().mOutputDenom).build();
            return true;

        } catch (Exception e) {
            getSActivity().mSwapInCoin = null;
            getSActivity().mSwapOutCoin = null;
            getSActivity().mOsmosisSwapAmountInRoute = null;
            return false;
        }
    }

    @SuppressLint("CheckResult")
    private void onFetchEstimateOut() {
        Asset inputAsset = getBaseDao().getAsset(getSActivity().mChainConfig, getSActivity().mInputDenom);
        Asset outputAsset = getBaseDao().getAsset(getSActivity().mChainConfig, getSActivity().mOutputDenom);
        mInputCoinDecimal = inputAsset.decimals;
        mOutputCoinDecimal = outputAsset.decimals;

        QueryGrpc.QueryBlockingStub stub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(getSActivity().mBaseChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
        Tx.SwapAmountInRoute swapAmountInRoute = Tx.SwapAmountInRoute.newBuilder().setPoolId(getSActivity().mOsmosisPoolId).setTokenOutDenom(getSActivity().mOutputDenom).build();
        Callable<QueryOuterClass.QuerySwapExactAmountInResponse> callable = () -> {
            QueryOuterClass.QuerySwapExactAmountInRequest request = QueryOuterClass.QuerySwapExactAmountInRequest.newBuilder()
                    .setSender(getSActivity().mAccount.address)
                    .setPoolId(getSActivity().mOsmosisPoolId)
                    .setTokenIn(new BigDecimal("1").movePointRight(mInputCoinDecimal) + getSActivity().mInputDenom)
                    .addRoutes(swapAmountInRoute).build();
            return stub.estimateSwapExactAmountIn(request);
        };
        Observable<QueryOuterClass.QuerySwapExactAmountInResponse> observable = Observable.fromCallable(callable);
        try {
            observable.subscribe(response -> {
                mStableSwapRateAmount = new BigDecimal(response.getTokenOutAmount());
                onInitView();
            });
        } catch (Exception e) {
            WLog.w("error : " + e.getMessage());
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

    private SwapActivity getSActivity() {
        return (SwapActivity) getBaseActivity();
    }
}
