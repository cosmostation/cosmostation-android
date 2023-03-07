package wannabit.io.cosmostaion.activities.txs.osmosis;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_OSMOSIS_POOL_LIST;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.protobuf.InvalidProtocolBufferException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import osmosis.gamm.poolmodels.stableswap.v1beta1.StableswapPool;
import osmosis.gamm.v1beta1.BalancerPool;
import osmosis.gamm.v1beta1.QueryGrpc;
import osmosis.gamm.v1beta1.QueryOuterClass;
import osmosis.gamm.v1beta1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dao.Asset;
import wannabit.io.cosmostaion.dao.SupportPool;
import wannabit.io.cosmostaion.dialog.SelectChainListDialog;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.FetchTask.MintscanOsmoPoolListTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.OsmosisPoolInfoGrpcTask;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class SwapViewActivity extends BaseActivity implements View.OnClickListener, TaskListener {

    private Toolbar mToolbar;
    private RelativeLayout mBtnInputCoinList, mBtnOutputCoinList;
    private ImageView mInputImg;
    private TextView mInputCoin, mInputAmount;
    private TextView mSwapFee, mSwapSlippage;

    private ImageView mOutputImg;
    private TextView mOutputCoin;
    private TextView mSwapInputCoinRate, mSwapInputCoinSymbol, mSwapOutputCoinRate, mSwapOutputCoinSymbol;
    private TextView mSwapInputCoinExRate, mSwapInputCoinExSymbol, mSwapOutputCoinExRate, mSwapOutputCoinExSymbol;
    private ImageButton mBtnToggle;
    private Button mBtnSwapStart;

    private ArrayList<SupportPool> mSupportPoolList = new ArrayList<>();
    private ArrayList<String> mAllDenoms = new ArrayList<>();
    private ArrayList<String> mSwapableDenoms = new ArrayList<>();
    private long mSelectedPoolId = 1;
    private BalancerPool.Pool mSelectedPool;
    private StableswapPool.Pool mSelectedStablePool;
    public String mInputCoinDenom;
    public String mOutputCoinDenom;

    private Asset mInputAsset;
    private Asset mOutputAsset;
    public int mInputDecimal;
    public int mOutputDecimal;
    public BigDecimal mStableSwapRateAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swap_view);
        mToolbar = findViewById(R.id.tool_bar);
        mBtnInputCoinList = findViewById(R.id.btn_to_input_coin);
        mBtnOutputCoinList = findViewById(R.id.btn_to_output_coin);

        mInputImg = findViewById(R.id.img_input_coin);
        mInputCoin = findViewById(R.id.txt_input_coin);
        mInputAmount = findViewById(R.id.inpus_amount);
        mOutputImg = findViewById(R.id.img_output_coin);
        mOutputCoin = findViewById(R.id.txt_output_coin);

        mSwapInputCoinRate = findViewById(R.id.inputs_rate);
        mSwapInputCoinSymbol = findViewById(R.id.inputs_rate_symbol);
        mSwapOutputCoinRate = findViewById(R.id.outputs_rate);
        mSwapOutputCoinSymbol = findViewById(R.id.outputs_rate_symbol);

        mSwapInputCoinExRate = findViewById(R.id.global_inputs_rate);
        mSwapInputCoinExSymbol = findViewById(R.id.global_inputs_rate_symbol);
        mSwapOutputCoinExRate = findViewById(R.id.global_outputs_rate);
        mSwapOutputCoinExSymbol = findViewById(R.id.global_outputs_rate_symbol);

        mSwapFee = findViewById(R.id.token_swap_fee);
        mSwapSlippage = findViewById(R.id.swap_slippage);
        mBtnToggle = findViewById(R.id.btn_toggle);
        mBtnSwapStart = findViewById(R.id.btn_start_swap);

        mBtnInputCoinList.setOnClickListener(this);
        mBtnOutputCoinList.setOnClickListener(this);
        mBtnToggle.setOnClickListener(this);
        mBtnSwapStart.setOnClickListener(this);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);
        mBtnToggle.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.color_osmosis));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        onFetchPoolList();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onUpdateView() {
        if (mInputCoinDenom != null && mOutputCoinDenom != null) {
            final BigDecimal availableMaxAmount = getBaseDao().getAvailable(mInputCoinDenom);
            mInputAmount.setText(WDp.getDpAmount2(this, availableMaxAmount, mInputDecimal, mInputDecimal));
            if (mSelectedPool != null) {
                BigDecimal swapFee = new BigDecimal(mSelectedPool.getPoolParams().getSwapFee());
                mSwapFee.setText(WDp.getPercentDp(swapFee.movePointLeft(16)));
            } else if (mSelectedStablePool != null) {
                BigDecimal swapFee = new BigDecimal(mSelectedStablePool.getPoolParams().getSwapFee());
                mSwapFee.setText(WDp.getPercentDp(swapFee.movePointLeft(16)));
            }
            mSwapSlippage.setText(WDp.getPercentDp(new BigDecimal("3")));

            WDp.setDpSymbolImg(getBaseDao(), mChainConfig, mInputCoinDenom, mInputImg);
            WDp.setDpSymbolImg(getBaseDao(), mChainConfig, mOutputCoinDenom, mOutputImg);
            WDp.setDpSymbol(this, getBaseDao(), mChainConfig, mInputCoinDenom, mInputCoin);
            WDp.setDpSymbol(this, getBaseDao(), mChainConfig, mOutputCoinDenom, mOutputCoin);
            WDp.setDpSymbol(this, getBaseDao(), mChainConfig, mInputCoinDenom, mSwapInputCoinSymbol);
            WDp.setDpSymbol(this, getBaseDao(), mChainConfig, mOutputCoinDenom, mSwapOutputCoinSymbol);
            WDp.setDpSymbol(this, getBaseDao(), mChainConfig, mInputCoinDenom, mSwapInputCoinExSymbol);
            WDp.setDpSymbol(this, getBaseDao(), mChainConfig, mOutputCoinDenom, mSwapOutputCoinExSymbol);

            mSwapInputCoinRate.setText(WDp.getDpAmount2(this, BigDecimal.ONE, 0, 6));
            mSwapInputCoinExRate.setText(WDp.getDpAmount2(this, BigDecimal.ONE, 0, 6));
            mSwapOutputCoinRate.setText(WDp.getDpAmount2(this, mStableSwapRateAmount, mOutputDecimal, 6));

            BigDecimal priceInput = WDp.price(getBaseDao(), mInputAsset.coinGeckoId);
            BigDecimal priceOutput = WDp.price(getBaseDao(), mOutputAsset.coinGeckoId);
            BigDecimal priceRate = BigDecimal.ZERO;
            if (priceInput.compareTo(BigDecimal.ZERO) == 0 || priceOutput.compareTo(BigDecimal.ZERO) == 0) {
                mSwapOutputCoinExRate.setText("??????");
            } else {
                priceRate = priceInput.divide(priceOutput, 6, RoundingMode.DOWN);
                mSwapOutputCoinExRate.setText(WDp.getDpAmount2(this, priceRate, 0, 6));
            }
            mSwapInputCoinExRate.setText(WDp.getDpAmount2(this, BigDecimal.ONE, 0, 6));
        }
    }

    private void onFetchPoolList() {
        onShowWaitDialog();
        mSupportPoolList.clear();
        new MintscanOsmoPoolListTask(getBaseApplication(), this, mChainConfig).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private int mTaskCount = 0;
    private void onFetchPoolData() {
        if (mTaskCount > 0) return;
        mTaskCount = 2;
        mSelectedPool = null;
        mSelectedStablePool = null;

        onFetchPoolInfo();
        onFetchEstimateOut();
    }

    private void onFetchFinished() {
        mTaskCount--;
        if (mTaskCount > 0) return;

        onHideWaitDialog();
        onUpdateView();
    }

    private void onFetchPoolInfo() {
        mSelectedPool = null;
        mSelectedStablePool = null;
        new OsmosisPoolInfoGrpcTask(getBaseApplication(), result -> {
            if (result.isSuccess && result.resultData != null) {
                QueryOuterClass.QueryPoolResponse response = (QueryOuterClass.QueryPoolResponse) result.resultData;
                try {
                    if (response.getPool().getTypeUrl().contains(BalancerPool.Pool.getDescriptor().getFullName())) {
                        mSelectedPool = BalancerPool.Pool.parseFrom(response.getPool().getValue());
                    } else if (response.getPool().getTypeUrl().contains(StableswapPool.Pool.getDescriptor().getFullName())) {
                        mSelectedStablePool = StableswapPool.Pool.parseFrom(response.getPool().getValue());
                    }
                    onFetchFinished();
                } catch (InvalidProtocolBufferException e) { e.printStackTrace(); }
            }
        }, mBaseChain, mSelectedPoolId).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @SuppressLint("CheckResult")
    private void onFetchEstimateOut() {
        mInputAsset = getBaseDao().getAsset(mChainConfig, mInputCoinDenom);
        mOutputAsset = getBaseDao().getAsset(mChainConfig, mOutputCoinDenom);
        mInputDecimal = mInputAsset.decimals;
        mOutputDecimal = mOutputAsset.decimals;

        QueryGrpc.QueryBlockingStub stub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
        Tx.SwapAmountInRoute swapAmountInRoute = Tx.SwapAmountInRoute.newBuilder().setPoolId(mSelectedPoolId).setTokenOutDenom(mOutputCoinDenom).build();
        Callable<QueryOuterClass.QuerySwapExactAmountInResponse> callable = () -> {
            QueryOuterClass.QuerySwapExactAmountInRequest request = QueryOuterClass.QuerySwapExactAmountInRequest.newBuilder().setSender(mAccount.address).setPoolId(mSelectedPoolId).setTokenIn(new BigDecimal("1").movePointRight(mInputDecimal) + mInputCoinDenom).addRoutes(swapAmountInRoute).build();
            return stub.estimateSwapExactAmountIn(request);
        };
        Observable<QueryOuterClass.QuerySwapExactAmountInResponse> observable = Observable.fromCallable(callable);
        try {
            observable.subscribe(response -> {
                mStableSwapRateAmount = new BigDecimal(response.getTokenOutAmount());
                onFetchFinished();
            });
        } catch (Exception e) {
            WLog.w("error : " + e.getMessage());
        }
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (isFinishing()) return;
        if (result.taskType == TASK_FETCH_OSMOSIS_POOL_LIST) {
            if (result.isSuccess && result.resultData != null) {
                mSupportPoolList = (ArrayList<SupportPool>) result.resultData;

                if (mSupportPoolList != null && mSupportPoolList.size() > 0) {
                    for (SupportPool pool : mSupportPoolList) {
                        if (!mAllDenoms.contains(pool.adenom)) {
                            mAllDenoms.add(pool.adenom);
                        }
                        if (!mAllDenoms.contains(pool.bdenom)) {
                            mAllDenoms.add(pool.bdenom);
                        }
                    }

                    mSelectedPoolId = 1;
                    mInputCoinDenom = "ibc/27394FB092D2ECCD56123C74F36E4C1F926001CEADA9CA97EA622B25F41E5EB2";
                    mOutputCoinDenom = "uosmo";
                    onFetchPoolData();
                }
            }
        }
    }

    private void onSetSwapableDenoms(String include) {
        mSwapableDenoms.clear();
        if (mSupportPoolList != null && mSupportPoolList.size() > 0) {
            for (SupportPool pool : mSupportPoolList) {
                if (pool.adenom.equalsIgnoreCase(include)) {
                    if (!mSwapableDenoms.contains(pool.bdenom)) {
                        mSwapableDenoms.add(pool.bdenom);
                    }
                }
                if (pool.bdenom.equalsIgnoreCase(include)) {
                    if (!mSwapableDenoms.contains(pool.adenom)) {
                        mSwapableDenoms.add(pool.adenom);
                    }
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnInputCoinList)) {
            Bundle bundleData = new Bundle();
            bundleData.putStringArrayList(SelectChainListDialog.SWAP_COIN_LIST_BUNDLE_KEY, mAllDenoms);
            bundleData.putInt(SelectChainListDialog.SELECT_CHAIN_LIST_BUNDLE_KEY, SelectChainListDialog.SELECT_INPUT_CHAIN_VALUE);
            SelectChainListDialog dialog = SelectChainListDialog.newInstance(bundleData);
            dialog.show(getSupportFragmentManager(), SelectChainListDialog.class.getName());
            getSupportFragmentManager().setFragmentResultListener(SelectChainListDialog.SELECT_CHAIN_LIST_BUNDLE_KEY, this, (requestKey, bundle) -> {
                int result = bundle.getInt(BaseConstant.POSITION);
                mInputCoinDenom = mAllDenoms.get(result);
                mSelectedPoolId = 0;
                for (SupportPool pool : mSupportPoolList) {
                    if (pool.adenom.equalsIgnoreCase(mInputCoinDenom)) {
                        mOutputCoinDenom = pool.bdenom;
                        mSelectedPoolId = Long.parseLong(pool.id);
                        onFetchPoolData();
                        return;
                    }
                    if (pool.bdenom.equalsIgnoreCase(mInputCoinDenom)) {
                        mOutputCoinDenom = pool.adenom;
                        mSelectedPoolId = Long.parseLong(pool.id);
                        onFetchPoolData();
                        return;
                    }
                }
            });

        } else if (v.equals(mBtnOutputCoinList)) {
            onSetSwapableDenoms(mInputCoinDenom);
            Bundle bundleData = new Bundle();
            bundleData.putStringArrayList(SelectChainListDialog.SWAP_COIN_LIST_BUNDLE_KEY, mSwapableDenoms);
            bundleData.putInt(SelectChainListDialog.SELECT_CHAIN_LIST_BUNDLE_KEY, SelectChainListDialog.SELECT_OUTPUT_CHAIN_VALUE);
            SelectChainListDialog dialog = SelectChainListDialog.newInstance(bundleData);
            dialog.show(getSupportFragmentManager(), SelectChainListDialog.class.getName());
            getSupportFragmentManager().setFragmentResultListener(SelectChainListDialog.SELECT_CHAIN_LIST_BUNDLE_KEY, this, (requestKey, bundle) -> {
                int result = bundle.getInt(BaseConstant.POSITION);
                mOutputCoinDenom = mSwapableDenoms.get(result);
                mSelectedPoolId = 0;
                for (SupportPool pool : mSupportPoolList) {
                    if (pool.adenom.equalsIgnoreCase(mOutputCoinDenom) && pool.bdenom.equalsIgnoreCase(mInputCoinDenom)) {
                        mInputCoinDenom = pool.bdenom;
                        mSelectedPoolId = Integer.parseInt(pool.id);
                        onFetchPoolData();
                        return;
                    }
                    if (pool.bdenom.equalsIgnoreCase(mOutputCoinDenom) && pool.adenom.equalsIgnoreCase(mInputCoinDenom)) {
                        mInputCoinDenom = pool.adenom;
                        mSelectedPoolId = Integer.parseInt(pool.id);
                        onFetchPoolData();
                        return;
                    }
                }
            });

        } else if (v.equals(mBtnToggle)) {
            String temp = mInputCoinDenom;
            mInputCoinDenom = mOutputCoinDenom;
            mOutputCoinDenom = temp;
            onFetchPoolData();

        } else if (v.equals(mBtnSwapStart)) {
            if (!mAccount.hasPrivateKey && !mAccount.isLedger()) {
                onInsertKeyDialog();
                return;
            }
            if (!WDp.isTxFeePayable(this, getBaseDao(), mChainConfig)) {
                Toast.makeText(this, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }

            BigDecimal inputBalance = getBaseDao().getAvailable(mInputCoinDenom);
            if (BigDecimal.ZERO.compareTo(inputBalance) >= 0) {
                Toast.makeText(this, R.string.error_not_enough_balance_to_vote, Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, SwapActivity.class);
            intent.putExtra("inputDenom", mInputCoinDenom);
            intent.putExtra("outputDenom", mOutputCoinDenom);
            intent.putExtra("mPoolId", mSelectedPoolId);
            startActivity(intent);
        }
    }
}
