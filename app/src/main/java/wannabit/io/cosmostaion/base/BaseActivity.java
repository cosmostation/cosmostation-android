package wannabit.io.cosmostaion.base;

import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_HTLS_REFUND;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.FEE_BNB_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.SUPPORT_BEP3_SWAP;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_INCENTIVE_PARAM;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_INCENTIVE_REWARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_MINTSCAN_CW20_ASSETS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_ALL_REWARDS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_AUTH;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_BALANCE;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_BONDED_VALIDATORS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_DELEGATIONS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_GRAVITY_POOL_LIST;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_KAVA_PRICES;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_NODE_INFO;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_OSMOSIS_POOL_LIST;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_STARNAME_CONFIG;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_STARNAME_FEE;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_UNBONDED_VALIDATORS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_UNBONDING_VALIDATORS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_UNDELEGATIONS;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.fulldive.wallet.di.IEnrichableActivity;
import com.fulldive.wallet.interactors.chains.binance.BinanceInteractor;
import com.fulldive.wallet.interactors.chains.okex.OkexInteractor;
import com.fulldive.wallet.presentation.accounts.AddAccountDialogFragment;
import com.fulldive.wallet.presentation.system.WaitDialogFragment;
import com.fulldive.wallet.rx.AppSchedulers;
import com.google.protobuf2.Any;
import com.joom.lightsaber.Injector;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cosmos.auth.v1beta1.Auth;
import cosmos.base.v1beta1.CoinOuterClass;
import cosmos.distribution.v1beta1.Distribution;
import cosmos.staking.v1beta1.Staking;
import io.reactivex.Completable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import kava.pricefeed.v1beta1.QueryOuterClass;
import osmosis.gamm.poolmodels.balancer.BalancerPool;
import tendermint.liquidity.v1beta1.Liquidity;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.AppLockActivity;
import wannabit.io.cosmostaion.activities.HtlcSendActivity;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.activities.RestoreActivity;
import wannabit.io.cosmostaion.activities.SendActivity;
import wannabit.io.cosmostaion.activities.chains.ibc.IBCSendActivity;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.Cw20Assets;
import wannabit.io.cosmostaion.dao.Price;
import wannabit.io.cosmostaion.dialog.Dialog_Buy_Select_Fiat;
import wannabit.io.cosmostaion.dialog.Dialog_Buy_Without_Key;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.model.kava.IncentiveParam;
import wannabit.io.cosmostaion.model.kava.IncentiveReward;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.task.FetchTask.KavaIncentiveParamTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaIncentiveRewardTask;
import wannabit.io.cosmostaion.task.FetchTask.MintScanAssetsTask;
import wannabit.io.cosmostaion.task.FetchTask.MintScanCw20AssetsTask;
import wannabit.io.cosmostaion.task.FetchTask.MoonPayTask;
import wannabit.io.cosmostaion.task.FetchTask.StationIbcPathsTask;
import wannabit.io.cosmostaion.task.FetchTask.StationIbcTokensTask;
import wannabit.io.cosmostaion.task.FetchTask.StationParamInfoTask;
import wannabit.io.cosmostaion.task.FetchTask.StationPriceInfoTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.AllRewardGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.AuthGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.BalanceGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.BondedValidatorsGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.Cw20BalanceGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.DelegationsGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.GravityDexPoolGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.KavaMarketPriceGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.NodeInfoGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.OsmosisPoolListGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.StarNameGrpcConfigTask;
import wannabit.io.cosmostaion.task.gRpcTask.StarNameGrpcFeeTask;
import wannabit.io.cosmostaion.task.gRpcTask.UnBondedValidatorsGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.UnBondingValidatorsGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.UnDelegationsGrpcTask;
import wannabit.io.cosmostaion.utils.FetchCallBack;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class BaseActivity extends AppCompatActivity implements IEnrichableActivity, TaskListener {
    private Injector injector;

    protected BaseApplication mApplication;
    protected BaseData mData;
    protected WaitDialogFragment waitDialogFragment;

    public View rootView;
    public Account account;
    public BaseChain baseChain;

    protected int mTaskCount;
    private FetchCallBack mFetchCallback;
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    protected final CompositeDisposable compositeDisposable = new CompositeDisposable();


    // tmp
    private BinanceInteractor binanceInteractor;
    private OkexInteractor okexInteractor;


    @Override
    public void setAppInjector(@NonNull Injector appInjector) {
        this.injector = appInjector;
    }

    @NonNull
    @Override
    public Injector getAppInjector() {
        return injector;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = findViewById(android.R.id.content);
        binanceInteractor = getAppInjector().getInstance(BinanceInteractor.class);
        okexInteractor = getAppInjector().getInstance(OkexInteractor.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!(this instanceof PasswordCheckActivity)) {
            if (getBaseApplication().needShowLockScreen()) {
                Intent intent = new Intent(BaseActivity.this, AppLockActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
            }
        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    public BaseApplication getBaseApplication() {
        if (mApplication == null)
            mApplication = (BaseApplication) getApplication();
        return mApplication;
    }

    public BaseData getBaseDao() {
        if (mData == null)
            mData = getBaseApplication().getBaseDao();
        return mData;
    }

    public void showWaitDialog() {
        if (waitDialogFragment == null) {
            waitDialogFragment = WaitDialogFragment.Companion.newInstance();
        }
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("wait");
        if (fragment == null || !fragment.isAdded()) {
            showDialog(waitDialogFragment, "wait", false);
        }
    }

    public void hideWaitDialog() {
        if (waitDialogFragment != null) {
            waitDialogFragment.dismissAllowingStateLoss();
        }
    }

    public void startMainActivity(int page) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("page", page);
        startActivity(intent);
    }

    public void startSendMainDenom() {
        if (account == null) return;
        if (!account.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            showDialog(add);
            return;
        }

        Intent intent = new Intent(getBaseContext(), SendActivity.class);
        BigDecimal mainAvailable;
        if (baseChain.isGRPC()) {
            mainAvailable = getBaseDao().getAvailable(baseChain.getMainDenom());
        } else {
            mainAvailable = getBaseDao().availableAmount(baseChain.getMainDenom());
        }
        BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), baseChain, CONST_PW_TX_SIMPLE_SEND, 0);
        if (mainAvailable.compareTo(feeAmount) <= 0) {
            Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
            return;
        }
        intent.putExtra("sendTokenDenom", baseChain.getMainDenom());
        startActivity(intent);

    }

    public void startHTLCSendActivity(String sendDenom) {
//        WLog.w("onStartHTLCSendActivity " + mBaseChain.getChain() + " " + sendDenom);
        if (account == null) return;
        if (!SUPPORT_BEP3_SWAP) {
            Toast.makeText(getBaseContext(), R.string.error_bep3_swap_temporary_disable, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!account.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            showDialog(add);
            return;
        }

        boolean hasBalance = true;
        BigDecimal mainDenomAvailable = getBaseDao().availableAmount(baseChain.getMainDenom());
        if (baseChain.equals(BNB_MAIN)) {
            if (mainDenomAvailable.compareTo(new BigDecimal(FEE_BNB_SEND)) <= 0) {
                hasBalance = false;
            }
        } else if (baseChain.equals(KAVA_MAIN)) {
            BigDecimal mainAvailable = getBaseDao().getAvailable(baseChain.getMainDenom());
            BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), baseChain, CONST_PW_TX_HTLS_REFUND, 0);
            if (mainAvailable.subtract(feeAmount).compareTo(BigDecimal.ZERO) <= 0) {
                hasBalance = false;
            }
        }
        if (!hasBalance) {
            Toast.makeText(getBaseContext(), R.string.error_not_enough_budget_bep3, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(getBaseContext(), HtlcSendActivity.class);
        intent.putExtra("toSwapDenom", sendDenom);
        startActivity(intent);
    }

    @Deprecated
    public void onChoiceNet(BaseChain chain) {
    }

    public void onChainSelected(BaseChain baseChain) {
        if (getBaseDao().getAccountsByChain(baseChain).size() >= 5) {
            Toast.makeText(this, R.string.error_max_account_number, Toast.LENGTH_SHORT).show();
            return;
        }
        new Handler().postDelayed(() -> {
            showDialog(AddAccountDialogFragment.Companion.newInstance(baseChain.getChain()));
        }, 300);
    }

    public void onChoiceStarnameResourceAddress(String address) {
    }

    public void onAddMnemonicForAccount() {
        startActivity(new Intent(BaseActivity.this, RestoreActivity.class));
    }

    public void onCheckIbcTransfer(String denom) {
        Intent intent = new Intent(BaseActivity.this, IBCSendActivity.class);
        intent.putExtra("sendTokenDenom", denom);
        startActivity(intent);
    }

    public void fetchAllData(FetchCallBack callback) {
        getBaseDao().clear();
        switch (baseChain) {
            case BNB_MAIN:
                updateBinanceAccount(callback);
                break;
            case OKEX_MAIN:
                updateOkexAccount(callback);
                break;
            default:
                onFetchAccountInfo(callback);
        }
    }

    private void updateBinanceAccount(FetchCallBack callback) {
        Disposable disposable = binanceInteractor
                .update(account, baseChain)
                .andThen(updateBalance())
                .subscribeOn(AppSchedulers.INSTANCE.io())
                .observeOn(AppSchedulers.INSTANCE.ui())
                .doOnError(error -> WLog.e(error.toString()))
                .subscribe(
                        callback::fetchFinished,
                        error -> {
                            Toast.makeText(getBaseContext(), R.string.str_network_error_msg, Toast.LENGTH_SHORT).show();
                            callback.fetchBusy();
                        }
                );
        compositeDisposable.add(disposable);
    }

    private void updateOkexAccount(FetchCallBack callback) {
        Disposable disposable = okexInteractor
                .update(account, baseChain)
                .andThen(updateBalance())
                .subscribeOn(AppSchedulers.INSTANCE.io())
                .observeOn(AppSchedulers.INSTANCE.ui())
                .doOnError(error -> WLog.e(error.toString()))
                .subscribe(
                        callback::fetchFinished,
                        error -> {
                            Toast.makeText(getBaseContext(), R.string.str_network_error_msg, Toast.LENGTH_SHORT).show();
                            callback.fetchBusy();
                        }
                );
        compositeDisposable.add(disposable);
    }

    private void onFetchAccountInfo(FetchCallBack callback) {
        if (mTaskCount > 0) {
            callback.fetchBusy();
        }
        mFetchCallback = callback;

        switch (baseChain) {
            // grpc
            case COSMOS_MAIN:
                mTaskCount = 1;
                new GravityDexPoolGrpcTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                break;
            case IOV_MAIN:
                mTaskCount = 2;

                new StarNameGrpcFeeTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                new StarNameGrpcConfigTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                break;
            case OSMOSIS_MAIN:
                mTaskCount = 1;
                new OsmosisPoolListGrpcTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                break;
            case KAVA_MAIN:
                mTaskCount = 3;

                new KavaMarketPriceGrpcTask(getBaseApplication(), this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                new KavaIncentiveParamTask(getBaseApplication(), this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                new KavaIncentiveRewardTask(getBaseApplication(), this, account).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                break;
            default:
                mTaskCount = 0;
                break;
        }

        if (baseChain.isGRPC()) {
            mTaskCount += 9;
            new NodeInfoGrpcTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AuthGrpcTask(getBaseApplication(), this, baseChain, account.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BondedValidatorsGrpcTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondedValidatorsGrpcTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondingValidatorsGrpcTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new BalanceGrpcTask(getBaseApplication(), this, baseChain, account.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new DelegationsGrpcTask(getBaseApplication(), this, baseChain, account).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnDelegationsGrpcTask(getBaseApplication(), this, baseChain, account).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AllRewardGrpcTask(getBaseApplication(), this, baseChain, account).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (isFinishing()) return;
        if (result.taskType == BaseConstant.TASK_FETCH_PRICE_INFO) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mPrices.clear();
                ArrayList<Price> tempPrice = (ArrayList<Price>) result.resultData;
                for (Price price : tempPrice) {
                    getBaseDao().mPrices.add(price);
                }
            }
        }

        mTaskCount--;
        //gRPC callback
        if (result.taskType == TASK_GRPC_FETCH_NODE_INFO) {
            tendermint.p2p.Types.NodeInfo tempNodeInfo = (tendermint.p2p.Types.NodeInfo) result.resultData;
            if (tempNodeInfo != null) {
                getBaseDao().mGRpcNodeInfo = tempNodeInfo;
                mTaskCount = mTaskCount + 5;
                new StationParamInfoTask(getBaseApplication(), this, baseChain, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                new StationIbcPathsTask(getBaseApplication(), this, baseChain, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                new StationIbcTokensTask(getBaseApplication(), this, baseChain, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                new MintScanAssetsTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                new MintScanCw20AssetsTask(getBaseApplication(), this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }

        } else if (result.taskType == TASK_GRPC_FETCH_AUTH) {
            Any tempAccount = (Any) result.resultData;
            if (tempAccount != null) {
                getBaseDao().mGRpcAccount = tempAccount;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_BONDED_VALIDATORS) {
            ArrayList<Staking.Validator> bonded = (ArrayList<Staking.Validator>) result.resultData;
            if (bonded != null) {
                getBaseDao().mGRpcTopValidators.addAll(bonded);
            }

        } else if (result.taskType == TASK_GRPC_FETCH_UNBONDED_VALIDATORS) {
            ArrayList<Staking.Validator> unbonded = (ArrayList<Staking.Validator>) result.resultData;
            if (unbonded != null) {
                getBaseDao().mGRpcOtherValidators.addAll(unbonded);
            }

        } else if (result.taskType == TASK_GRPC_FETCH_UNBONDING_VALIDATORS) {
            ArrayList<Staking.Validator> unbonding = (ArrayList<Staking.Validator>) result.resultData;
            if (unbonding != null) {
                getBaseDao().mGRpcOtherValidators.addAll(unbonding);
            }

        } else if (result.taskType == TASK_GRPC_FETCH_BALANCE) {
            ArrayList<CoinOuterClass.Coin> balance = (ArrayList<CoinOuterClass.Coin>) result.resultData;
            if (balance != null && balance.size() > 0) {
                for (CoinOuterClass.Coin coin : balance) {
                    if (!coin.getAmount().equals("0")) {
                        getBaseDao().mGrpcBalance.add(new Coin(coin.getDenom(), coin.getAmount()));
                    } else {
                        if (coin.getDenom().equalsIgnoreCase(baseChain.getMainDenom())) {
                            getBaseDao().mGrpcBalance.add(new Coin(coin.getDenom(), coin.getAmount()));
                        }
                    }
                }
            }
            if (getBaseDao().mGrpcBalance.size() <= 0 || getBaseDao().getAvailable(baseChain.getMainDenom()).compareTo(BigDecimal.ZERO) <= 0) {
                getBaseDao().mGrpcBalance.add(new Coin(baseChain.getMainDenom(), "0"));
            }

        } else if (result.taskType == TASK_GRPC_FETCH_DELEGATIONS) {
            ArrayList<Staking.DelegationResponse> delegations = (ArrayList<Staking.DelegationResponse>) result.resultData;
            if (delegations != null) {
                getBaseDao().mGrpcDelegations = delegations;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_UNDELEGATIONS) {
            ArrayList<Staking.UnbondingDelegation> undelegations = (ArrayList<Staking.UnbondingDelegation>) result.resultData;
            if (undelegations != null) {
                getBaseDao().mGrpcUndelegations = undelegations;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_ALL_REWARDS) {
            ArrayList<Distribution.DelegationDelegatorReward> rewards = (ArrayList<Distribution.DelegationDelegatorReward>) result.resultData;
            if (rewards != null) {
                getBaseDao().mGrpcRewards = rewards;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_STARNAME_FEE) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mGrpcStarNameFee = ((starnamed.x.configuration.v1beta1.Types.Fees) result.resultData);
            }

        } else if (result.taskType == TASK_GRPC_FETCH_STARNAME_CONFIG) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mGrpcStarNameConfig = ((starnamed.x.configuration.v1beta1.Types.Config) result.resultData);
            }

        } else if (result.taskType == TASK_GRPC_FETCH_GRAVITY_POOL_LIST) {
            if (result.isSuccess && result.resultData != null) {
                List<Liquidity.Pool> pools = (List<Liquidity.Pool>) result.resultData;
                getBaseDao().mGrpcGravityPools = new ArrayList<Liquidity.Pool>(pools);
            }
        } else if (result.taskType == TASK_GRPC_FETCH_OSMOSIS_POOL_LIST) {
            if (result.isSuccess && result.resultData != null) {
                List<BalancerPool.Pool> pools = (List<BalancerPool.Pool>) result.resultData;
                getBaseDao().mGrpcOsmosisPool = new ArrayList<BalancerPool.Pool>(pools);
            }
        }

        //kava
        else if (result.taskType == TASK_FETCH_KAVA_INCENTIVE_PARAM) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mIncentiveParam5 = (IncentiveParam) result.resultData;
            }

        } else if (result.taskType == TASK_FETCH_KAVA_INCENTIVE_REWARD) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mIncentiveRewards = (IncentiveReward) result.resultData;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_KAVA_PRICES) {
            if (result.isSuccess && result.resultData != null) {
                ArrayList<QueryOuterClass.CurrentPriceResponse> currentPrices = (ArrayList<QueryOuterClass.CurrentPriceResponse>) result.resultData;
                for (QueryOuterClass.CurrentPriceResponse response : currentPrices) {
                    getBaseDao().mKavaTokenPrice.put(response.getMarketId(), response);
                }
            }
        }

        // mintscan
        else if (result.taskType == TASK_FETCH_MINTSCAN_CW20_ASSETS) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mCw20Assets = (ArrayList<Cw20Assets>) result.resultData;
                if (getBaseDao().mCw20Assets != null && getBaseDao().mCw20Assets.size() > 0) {
                    for (Cw20Assets assets : getBaseDao().mCw20Assets) {
                        if (assets.chain.equalsIgnoreCase(WDp.getChainNameByBaseChain(baseChain))) {
                            mTaskCount = mTaskCount + 1;
                            new Cw20BalanceGrpcTask(getBaseApplication(), this, baseChain, account, assets.contract_address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                        }
                    }
                }
            }
        }

        if (mTaskCount == 0) {
            if (baseChain.isGRPC()) {
                getBaseDao().mGRpcAllValidators.addAll(getBaseDao().mGRpcTopValidators);
                getBaseDao().mGRpcAllValidators.addAll(getBaseDao().mGRpcOtherValidators);
                for (Staking.Validator validator : getBaseDao().mGRpcAllValidators) {
                    boolean already = false;
                    for (Staking.DelegationResponse delegation : getBaseDao().mGrpcDelegations) {
                        if (delegation.getDelegation().getValidatorAddress().equals(validator.getOperatorAddress())) {
                            already = true;
                            break;
                        }
                    }
                    for (Staking.UnbondingDelegation undelegation : getBaseDao().mGrpcUndelegations) {
                        if (undelegation.getValidatorAddress().equals(validator.getOperatorAddress())) {
                            already = true;
                            break;
                        }
                    }
                    if (already) getBaseDao().mGRpcMyValidators.add(validator);
                }

                if (getBaseDao().mGRpcNodeInfo == null) {
                    Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();

                } else {
                    if (getBaseDao().mGRpcAccount != null && !getBaseDao().mGRpcAccount.getTypeUrl().contains(Auth.BaseAccount.getDescriptor().getFullName())) {
                        WUtil.onParseVestingAccount(getBaseDao(), baseChain);
                    }
                    ArrayList<Balance> snapBalance = new ArrayList<>();
                    for (Coin coin : getBaseDao().mGrpcBalance) {
                        snapBalance.add(new Balance(account.id, coin.denom, coin.amount, Calendar.getInstance().getTime().getTime(), "0", "0"));
                    }
                    getBaseDao().updateBalances(account.id, snapBalance);
                }
            } else if (!(baseChain.equals(BNB_MAIN) || baseChain.equals(OKEX_MAIN))) {
                getBaseDao().mAllValidators.addAll(getBaseDao().mTopValidators);
                getBaseDao().mAllValidators.addAll(getBaseDao().mOtherValidators);
                getBaseDao().mMyValidators = new ArrayList<>();
                if (getBaseDao().mNodeInfo == null) {
                    Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();
                }

            }
            new StationPriceInfoTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            //callback with delay fix gRPC  timming issue
            mHandler.postDelayed(() -> {
                if (mFetchCallback != null) {
                    mFetchCallback.fetchFinished();
                }
            }, 300);
        }
    }

    private Completable updateBalance() {
        return Completable.fromCallable(() -> {
            // TODO: It will be refactored
            getBaseDao().mBalances = getBaseDao().onSelectBalance(account.id);
            return true;
        });
    }

    public void showDialog(DialogFragment dialogFragment) {
        showDialog(dialogFragment, "dialog", true);
    }

    public void showDialog(DialogFragment dialogFragment, String tag, boolean cancelable) {
        dialogFragment.setCancelable(cancelable);
        getSupportFragmentManager()
                .beginTransaction()
                .add(dialogFragment, tag)
                .commitNowAllowingStateLoss();
    }

    public void onShowBuyWarnNoKey() {
        Dialog_Buy_Without_Key dialog = Dialog_Buy_Without_Key.newInstance();
        showDialog(dialog, "wait", true);
    }

    public void onShowBuySelectFiat() {
        Dialog_Buy_Select_Fiat dialog = Dialog_Buy_Select_Fiat.newInstance();
        showDialog(dialog, "wait", true);
    }

    public void onStartMoonpaySignature(String fiat) {
        String query = "?apiKey=" + getString(R.string.moon_pay_public_key);
        if (baseChain.equals(COSMOS_MAIN)) {
            query = query + "&currencyCode=atom";
        } else if (baseChain.equals(BNB_MAIN)) {
            query = query + "&currencyCode=bnb";
        } else if (baseChain.equals(KAVA_MAIN)) {
            query = query + "&currencyCode=kava";
        } else if (baseChain.equals(BAND_MAIN)) {
            query = query + "&currencyCode=band";
        }
        query = query + "&walletAddress=" + account.address + "&baseCurrencyCode=" + fiat;
        final String data = query;

        new MoonPayTask(getBaseApplication(), result -> {
            if (result.isSuccess) {
                try {
                    String en = URLEncoder.encode((String) result.resultData, "UTF-8");
                    Intent guideIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url_moon_pay) + data + "&signature=" + en));
                    startActivity(guideIntent);
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();

                }

            } else {
                Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();

            }
        }, query).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
    }
}
