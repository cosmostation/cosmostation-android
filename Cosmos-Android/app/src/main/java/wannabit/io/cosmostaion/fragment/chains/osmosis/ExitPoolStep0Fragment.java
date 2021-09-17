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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.math.RoundingMode;

import osmosis.gamm.v1beta1.PoolOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.osmosis.ExitPoolActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.OsmosisPoolInfoGrpcTask;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_OSMOSIS_POOL_INFO;

public class ExitPoolStep0Fragment extends BaseFragment implements View.OnClickListener, TaskListener {

    private RelativeLayout  mProgress;
    private Button          mCancelBtn, mNextBtn;

    private ImageView       mLpCoinImg;
    private TextView        mLpCoinSymbol;
    private EditText        mLpCoinInput;
    private ImageView       mLpCoinClearBtn;
    private TextView        mLpCoinAmount, mLpCoinDenom;
    private Button          mLpCoin1_4Btn, mLpCoinHalfBtn, mLpCoin3_4Btn, mLpCoinMaxBtn;

    private BigDecimal      mAvailableMaxAmount;
    private int             mCoinDecimal = 18;
    private String          mDecimalChecker, mDecimalSetter;

    public static ExitPoolStep0Fragment newInstance(Bundle bundle) {
        ExitPoolStep0Fragment fragment = new ExitPoolStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exit_pool_step0, container, false);
        mCancelBtn          = rootView.findViewById(R.id.btn_cancel);
        mNextBtn            = rootView.findViewById(R.id.btn_next);
        mProgress           = rootView.findViewById(R.id.progress);

        mLpCoinImg          = rootView.findViewById(R.id.exit_pool_input_icon);
        mLpCoinSymbol       = rootView.findViewById(R.id.exit_pool_input_symbol);
        mLpCoinInput        = rootView.findViewById(R.id.exit_pool_input);
        mLpCoinClearBtn     = rootView.findViewById(R.id.exit_pool_input_clear);
        mLpCoinAmount       = rootView.findViewById(R.id.exit_pool_input_amount);
        mLpCoinDenom        = rootView.findViewById(R.id.exit_pool_input_amount_denom);
        mLpCoin1_4Btn       = rootView.findViewById(R.id.exit_pool_input_1_4);
        mLpCoinHalfBtn      = rootView.findViewById(R.id.exit_pool_input_half);
        mLpCoin3_4Btn       = rootView.findViewById(R.id.exit_pool_input_3_4);
        mLpCoinMaxBtn       = rootView.findViewById(R.id.exit_pool_input_max);

        mLpCoinClearBtn.setOnClickListener(this);
        mLpCoin1_4Btn.setOnClickListener(this);
        mLpCoinHalfBtn.setOnClickListener(this);
        mLpCoin3_4Btn.setOnClickListener(this);
        mLpCoinMaxBtn.setOnClickListener(this);
        mCancelBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

        onFetchPoolInfo();
        return rootView;
    }

    private void onInitView() {
        mProgress.setVisibility(View.GONE);

        String lpDenom = getSActivity().mOsmosisPool.getTotalShares().getDenom();
        mAvailableMaxAmount = getBaseDao().getAvailable(lpDenom);
        setDpDecimals(mCoinDecimal);

        WUtil.DpOsmosisTokenImg(mLpCoinImg, lpDenom);
        WUtil.dpOsmosisTokenName(getSActivity(), mLpCoinSymbol, lpDenom);
        WDp.showCoinDp(getSActivity(), lpDenom, mAvailableMaxAmount.toString(), mLpCoinDenom, mLpCoinAmount, BaseChain.OSMOSIS_MAIN);
        onAddAmountWatcher();
    }

    private void onAddAmountWatcher() {
        mLpCoinInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable et) {
                String es = et.toString().trim();
                if(TextUtils.isEmpty(es)) {
                    mLpCoinInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                } else if (es.startsWith(".")) {
                    mLpCoinInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                    mLpCoinInput.setText("");
                } else if (es.endsWith(".")) {
                    mLpCoinInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                    mLpCoinInput.setVisibility(View.VISIBLE);
                } else if(es.length() > 1 && es.startsWith("0") && !es.startsWith("0.")) {
                    mLpCoinInput.setText("0");
                    mLpCoinInput.setSelection(1);
                }

                if (es.equals(mDecimalChecker)) {
                    mLpCoinInput.setText(mDecimalSetter);
                    mLpCoinInput.setSelection(mCoinDecimal + 1);
                } else {
                    try {
                        final BigDecimal inputAmount = new BigDecimal(es);
                        if (BigDecimal.ZERO.compareTo(inputAmount) >= 0 ){
                            mLpCoinInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                            return;
                        }

                        BigDecimal checkPosition = inputAmount.movePointRight(mCoinDecimal);
                        BigDecimal checkMax = checkPosition.setScale(0, RoundingMode.DOWN);
                        if (checkPosition.compareTo(checkMax) != 0 || !checkPosition.equals(checkMax)) {
                            String recover = es.substring(0, es.length() - 1);
                            mLpCoinInput.setText(recover);
                            mLpCoinInput.setSelection(recover.length());
                            return;
                        }

                        if (inputAmount.compareTo(mAvailableMaxAmount.movePointLeft(mCoinDecimal).setScale(mCoinDecimal, RoundingMode.CEILING)) > 0) {
                            mLpCoinInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                        } else {
                            mLpCoinInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                        }
                        mLpCoinInput.setSelection(mLpCoinInput.getText().length());
                    } catch (Exception e) { }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mLpCoinClearBtn)) {
            mLpCoinInput.setText("");

        } else if (v.equals(mLpCoin1_4Btn)) {
            BigDecimal cal = mAvailableMaxAmount.multiply(new BigDecimal(0.25)).setScale(0, RoundingMode.DOWN);
            mLpCoinInput.setText(cal.movePointLeft(mCoinDecimal).toPlainString());

        } else if (v.equals(mLpCoinHalfBtn)) {
            BigDecimal cal = mAvailableMaxAmount.multiply(new BigDecimal(0.5)).setScale(0, RoundingMode.DOWN);
            mLpCoinInput.setText(cal.movePointLeft(mCoinDecimal).toPlainString());

        } else if (v.equals(mLpCoin3_4Btn)) {
            BigDecimal cal = mAvailableMaxAmount.multiply(new BigDecimal(0.75)).setScale(0, RoundingMode.DOWN);
            mLpCoinInput.setText(cal.movePointLeft(mCoinDecimal).toPlainString());

        } else if (v.equals(mLpCoinMaxBtn)) {
            mLpCoinInput.setText(mAvailableMaxAmount.movePointLeft(mCoinDecimal).toPlainString());
        }

        else if (v.equals(mCancelBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            if (isValidateAmount()) {
                getSActivity().onNextStep();
            } else {
                Toast.makeText(getContext(), R.string.error_invalid_amounts, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isValidateAmount() {
        try {
            BigDecimal amountTemp = new BigDecimal(mLpCoinInput.getText().toString().trim());
            if (amountTemp.compareTo(BigDecimal.ZERO) <= 0) return false;
            if (amountTemp.compareTo(mAvailableMaxAmount.movePointLeft(mCoinDecimal).setScale(mCoinDecimal, RoundingMode.CEILING)) > 0) return false;

            getSActivity().mLpToken = new Coin(getSActivity().mOsmosisPool.getTotalShares().getDenom(), amountTemp.movePointRight(mCoinDecimal).toPlainString());

            //Expected receiveing pool tokens!!
            String coin0Denom = getSActivity().mOsmosisPool.getPoolAssets(0).getToken().getDenom();
            String coin1Denom = getSActivity().mOsmosisPool.getPoolAssets(1).getToken().getDenom();
            BigDecimal coin0Amount = new BigDecimal(getSActivity().mOsmosisPool.getPoolAssets(0).getToken().getAmount());
            BigDecimal coin1Amount = new BigDecimal(getSActivity().mOsmosisPool.getPoolAssets(1).getToken().getAmount());
            BigDecimal poolTotalShare = new BigDecimal(getSActivity().mOsmosisPool.getTotalShares().getAmount());

            BigDecimal coin0PaybackAmount = coin0Amount.multiply(amountTemp.movePointRight(mCoinDecimal)).divide(poolTotalShare, 0, RoundingMode.DOWN).multiply(new BigDecimal("0.975")).setScale(0, RoundingMode.DOWN);
            BigDecimal coin1PaybackAmount = coin1Amount.multiply(amountTemp.movePointRight(mCoinDecimal)).divide(poolTotalShare, 0, RoundingMode.DOWN).multiply(new BigDecimal("0.975")).setScale(0, RoundingMode.DOWN);

            getSActivity().mPoolCoin0 = new Coin(coin0Denom, coin0PaybackAmount.toPlainString());
            getSActivity().mPoolCoin1 = new Coin(coin1Denom, coin1PaybackAmount.toPlainString());
            return true;

        } catch (Exception e) {
            return false;
        }
    }



    private int mTaskCount;
    public void onFetchPoolInfo() {
        mTaskCount = 1;
        new OsmosisPoolInfoGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mOsmosisPoolId).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        mTaskCount--;
        if (result.taskType == TASK_GRPC_FETCH_OSMOSIS_POOL_INFO) {
            if (result.isSuccess && result.resultData != null) {
                getSActivity().mOsmosisPool = (PoolOuterClass.Pool)result.resultData;
            }
        }
        if (mTaskCount == 0) {
            onInitView();
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

    private ExitPoolActivity getSActivity() { return (ExitPoolActivity)getBaseActivity(); }
}
