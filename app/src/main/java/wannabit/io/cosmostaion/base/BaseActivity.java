package wannabit.io.cosmostaion.base;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OSMOSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_HTLS_REFUND;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.FEE_BNB_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.SUPPORT_BEP3_SWAP;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_BNB_FEES;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_BNB_MINI_TICKER;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_BNB_TICKER;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_INCENTIVE_PARAM;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_INCENTIVE_REWARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_MINTSCAN_CW20_ASSETS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_NODE_INFO;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_OKEX_ALL_VALIDATORS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_OK_ACCOUNT_BALANCE;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_OK_DEX_TICKERS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_OK_STAKING_INFO;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_OK_TOKEN_LIST;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_OK_UNBONDING_INFO;
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

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.fulldive.wallet.di.IEnrichableActivity;
import com.fulldive.wallet.presentation.accounts.AddAccountDialogFragment;
import com.fulldive.wallet.presentation.main.intro.IntroActivity;
import com.fulldive.wallet.presentation.system.WaitDialogFragment;
import com.google.protobuf2.Any;
import com.joom.lightsaber.Injector;
import com.shasin.notificationbanner.Banner;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cosmos.auth.v1beta1.Auth;
import cosmos.base.v1beta1.CoinOuterClass;
import cosmos.distribution.v1beta1.Distribution;
import cosmos.staking.v1beta1.Staking;
import io.reactivex.disposables.CompositeDisposable;
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
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BnbTicker;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.dao.Cw20Assets;
import wannabit.io.cosmostaion.dao.Price;
import wannabit.io.cosmostaion.dialog.Dialog_Buy_Select_Fiat;
import wannabit.io.cosmostaion.dialog.Dialog_Buy_Without_Key;
import wannabit.io.cosmostaion.dialog.Dialog_Push_Enable;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.model.BondingInfo;
import wannabit.io.cosmostaion.model.NodeInfo;
import wannabit.io.cosmostaion.model.UnbondingInfo;
import wannabit.io.cosmostaion.model.kava.IncentiveParam;
import wannabit.io.cosmostaion.model.kava.IncentiveReward;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.res.ResBnbFee;
import wannabit.io.cosmostaion.network.res.ResOkStaking;
import wannabit.io.cosmostaion.network.res.ResOkTickersList;
import wannabit.io.cosmostaion.network.res.ResOkTokenList;
import wannabit.io.cosmostaion.network.res.ResOkUnbonding;
import wannabit.io.cosmostaion.task.FetchTask.AccountInfoTask;
import wannabit.io.cosmostaion.task.FetchTask.BnbMiniTickerTask;
import wannabit.io.cosmostaion.task.FetchTask.BnbMiniTokenListTask;
import wannabit.io.cosmostaion.task.FetchTask.BnbTickerTask;
import wannabit.io.cosmostaion.task.FetchTask.BnbTokenListTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaIncentiveParamTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaIncentiveRewardTask;
import wannabit.io.cosmostaion.task.FetchTask.MintScanAssetsTask;
import wannabit.io.cosmostaion.task.FetchTask.MintScanCw20AssetsTask;
import wannabit.io.cosmostaion.task.FetchTask.MoonPayTask;
import wannabit.io.cosmostaion.task.FetchTask.NodeInfoTask;
import wannabit.io.cosmostaion.task.FetchTask.OkAccountBalanceTask;
import wannabit.io.cosmostaion.task.FetchTask.OkDexTickerTask;
import wannabit.io.cosmostaion.task.FetchTask.OkStakingInfoTask;
import wannabit.io.cosmostaion.task.FetchTask.OkTokenListTask;
import wannabit.io.cosmostaion.task.FetchTask.OkUnbondingInfoTask;
import wannabit.io.cosmostaion.task.FetchTask.PushUpdateTask;
import wannabit.io.cosmostaion.task.FetchTask.StationIbcPathsTask;
import wannabit.io.cosmostaion.task.FetchTask.StationIbcTokensTask;
import wannabit.io.cosmostaion.task.FetchTask.StationParamInfoTask;
import wannabit.io.cosmostaion.task.FetchTask.StationPriceInfoTask;
import wannabit.io.cosmostaion.task.FetchTask.ValidatorInfoAllTask;
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

    private CardView mPushBody;
    private ImageView mPushClose;

    protected final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            onDisplayNotification(intent);
        }
    };

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
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("pushAlarm"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
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

    public void onDeleteAccount(long id) {
        new PushUpdateTask(getBaseApplication(), null, account, getBaseDao().getFCMToken(), false).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        String accountId = "" + id;
        String uuid = getBaseDao().onSelectAccount(accountId).uuid;

        try {
            CryptoHelper.deleteKey(getString(R.string.key_mnemonic) + uuid);
        } catch (Exception e) {
        }
        try {
            CryptoHelper.deleteKey(getString(R.string.key_private) + uuid);
        } catch (Exception e) {
        }
        getBaseDao().onDeleteAccount(accountId);
        getBaseDao().onSelectBalance(id);

        if (getBaseDao().onSelectAccounts().size() > 0) {
            if (account.id.equals(id)) {
                getBaseDao().setLastUser(getBaseDao().onSelectAccounts().get(0).id);
                for (BaseChain baseChain : getBaseDao().dpSortedChains()) {
                    int accountNum = getBaseDao().getAccountsByChain(baseChain).size();
                    if (accountNum > 0) {
                        getBaseDao().setLastUser(getBaseDao().getAccountsByChain(baseChain).get(0).id);
                        break;
                    }
                }
            } else {
                getBaseDao().setLastUser(account.id);
            }
            startMainActivity(0);
        } else {
            getBaseDao().setLastUser(-1);
            Intent intent = new Intent(this, IntroActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    public void onAddMnemonicForAccount() {
        startActivity(new Intent(BaseActivity.this, RestoreActivity.class));
    }

    public void onUpdateUserAlarm(Account account, boolean useAlarm) {
        new PushUpdateTask(getBaseApplication(), this, account, getBaseDao().getFCMToken(), useAlarm).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void onCheckIbcTransfer(String denom) {
        Intent intent = new Intent(BaseActivity.this, IBCSendActivity.class);
        intent.putExtra("sendTokenDenom", denom);
        startActivity(intent);
    }


    public void onDisplayNotification(Intent intent) {
        if (!(this instanceof PasswordCheckActivity) && !(this instanceof AppLockActivity)) {
            Banner.make(rootView, this, Banner.TOP, R.layout.foreground_push);
            mPushBody = Banner.getInstance().getBannerView().findViewById(R.id.push_body);
            ImageView pushType = Banner.getInstance().getBannerView().findViewById(R.id.push_type);
            mPushClose = Banner.getInstance().getBannerView().findViewById(R.id.push_close);
            TextView pushTitle = Banner.getInstance().getBannerView().findViewById(R.id.push_title);
            TextView pushMsg = Banner.getInstance().getBannerView().findViewById(R.id.push_msg);

            if (intent.getStringExtra("type").equals("sent")) {
                pushType.setImageResource(R.drawable.ic_notifications_send);
                pushTitle.setTextColor(getColor(R.color.colorNotiSend));
            } else if (intent.getStringExtra("type").equals("received")) {
                pushType.setImageResource(R.drawable.ic_notifications_receive);
                pushTitle.setTextColor(getColor(R.color.colorNotiReceive));
            } else {
                return;
            }
            pushTitle.setText(intent.getStringExtra("title"));
            pushMsg.setText(intent.getStringExtra("Body"));

            bannerClickListener(intent.getStringExtra("pushNotifyto"));
            Banner.getInstance().setCustomAnimationStyle(R.style.topAnimation);
            Banner.getInstance().setDuration(4000);
            Banner.getInstance().show();
        }
    }

    private void bannerClickListener(String address) {
        mPushBody.setOnClickListener(view -> {
            List<Account> accounts = getBaseDao().getAccountsByAddress(address);
            if (!accounts.isEmpty()) {
                getBaseDao().setLastUser(accounts.get(0).id);
                startMainActivity(2);
            }
        });

        mPushClose.setOnClickListener(view -> Banner.getInstance().dismissBanner());
    }


    public void onFetchAccountInfo(FetchCallBack callback) {
        if (mTaskCount > 0) {
            callback.fetchBusy();
        }
        mFetchCallback = callback;

        getBaseDao().mIbcPaths.clear();
        getBaseDao().mIbcTokens.clear();
        getBaseDao().mChainParam = null;
        getBaseDao().mAssets.clear();
        getBaseDao().mCw20Assets.clear();

        getBaseDao().mSifLmIncentive = null;

        getBaseDao().mNodeInfo = null;
        getBaseDao().mAllValidators.clear();
        getBaseDao().mMyValidators.clear();
        getBaseDao().mTopValidators.clear();
        getBaseDao().mOtherValidators.clear();

        getBaseDao().mBalances.clear();
        getBaseDao().mMyDelegations.clear();
        getBaseDao().mMyUnbondings.clear();
        getBaseDao().mMyRewards.clear();

        //kava GRPC
        getBaseDao().mIncentiveParam5 = null;
        getBaseDao().mIncentiveRewards = null;
        getBaseDao().mMyHardDeposits.clear();
        getBaseDao().mMyHardBorrows.clear();
        getBaseDao().mModuleCoins.clear();
        getBaseDao().mReserveCoins.clear();


        //binance
        getBaseDao().mBnbTokens.clear();
        getBaseDao().mBnbTickers.clear();


        //gRPC
        getBaseDao().mGRpcNodeInfo = null;
        getBaseDao().mGRpcAccount = null;
        getBaseDao().mGRpcTopValidators.clear();
        getBaseDao().mGRpcOtherValidators.clear();
        getBaseDao().mGRpcAllValidators.clear();
        getBaseDao().mGRpcMyValidators.clear();

        getBaseDao().mGrpcBalance.clear();
        getBaseDao().mGrpcVesting.clear();
        getBaseDao().mGrpcDelegations.clear();
        getBaseDao().mGrpcUndelegations.clear();
        getBaseDao().mGrpcRewards.clear();

        getBaseDao().mGrpcStarNameFee = null;
        getBaseDao().mGrpcStarNameConfig = null;

        getBaseDao().mGrpcGravityPools.clear();

        if (baseChain.equals(BNB_MAIN)) {
            mTaskCount = 6;
            new NodeInfoTask(getBaseApplication(), this, BaseChain.getChain(account.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AccountInfoTask(getBaseApplication(), this, account).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BnbTokenListTask(getBaseApplication(), this, account).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BnbMiniTokenListTask(getBaseApplication(), this, account).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BnbTickerTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BnbMiniTickerTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//            new BnbFeesTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (baseChain.equals(BaseChain.OKEX_MAIN)) {
            mTaskCount = 8;
            getBaseDao().mOkStaking = null;
            getBaseDao().mOkUnbonding = null;
            getBaseDao().mOkTokenList = null;
            getBaseDao().mOkTickersList = null;
            new NodeInfoTask(getBaseApplication(), this, BaseChain.getChain(account.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoAllTask(getBaseApplication(), this, BaseChain.getChain(account.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new AccountInfoTask(getBaseApplication(), this, account).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new OkAccountBalanceTask(getBaseApplication(), this, account, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new OkTokenListTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new OkDexTickerTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new OkStakingInfoTask(getBaseApplication(), this, account, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new OkUnbondingInfoTask(getBaseApplication(), this, account, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        }

        // grpc
        else if (baseChain.equals(COSMOS_MAIN)) {
            mTaskCount = 10;
            new NodeInfoGrpcTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AuthGrpcTask(getBaseApplication(), this, baseChain, account.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BondedValidatorsGrpcTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondedValidatorsGrpcTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondingValidatorsGrpcTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new BalanceGrpcTask(getBaseApplication(), this, baseChain, account.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new DelegationsGrpcTask(getBaseApplication(), this, baseChain, account).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnDelegationsGrpcTask(getBaseApplication(), this, baseChain, account).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AllRewardGrpcTask(getBaseApplication(), this, baseChain, account).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new GravityDexPoolGrpcTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (baseChain.equals(IOV_MAIN)) {
            mTaskCount = 11;
            new NodeInfoGrpcTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AuthGrpcTask(getBaseApplication(), this, baseChain, account.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BondedValidatorsGrpcTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondedValidatorsGrpcTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondingValidatorsGrpcTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new BalanceGrpcTask(getBaseApplication(), this, baseChain, account.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new DelegationsGrpcTask(getBaseApplication(), this, baseChain, account).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnDelegationsGrpcTask(getBaseApplication(), this, baseChain, account).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AllRewardGrpcTask(getBaseApplication(), this, baseChain, account).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new StarNameGrpcFeeTask(getBaseApplication(), this, BaseChain.getChain(account.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new StarNameGrpcConfigTask(getBaseApplication(), this, BaseChain.getChain(account.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (baseChain.equals(OSMOSIS_MAIN)) {
            mTaskCount = 10;
            new NodeInfoGrpcTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AuthGrpcTask(getBaseApplication(), this, baseChain, account.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BondedValidatorsGrpcTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondedValidatorsGrpcTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondingValidatorsGrpcTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new BalanceGrpcTask(getBaseApplication(), this, baseChain, account.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new DelegationsGrpcTask(getBaseApplication(), this, baseChain, account).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnDelegationsGrpcTask(getBaseApplication(), this, baseChain, account).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AllRewardGrpcTask(getBaseApplication(), this, baseChain, account).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new OsmosisPoolListGrpcTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (baseChain.equals(KAVA_MAIN)) {
            mTaskCount = 12;
            new NodeInfoGrpcTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AuthGrpcTask(getBaseApplication(), this, baseChain, account.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BondedValidatorsGrpcTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondedValidatorsGrpcTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondingValidatorsGrpcTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new BalanceGrpcTask(getBaseApplication(), this, baseChain, account.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new DelegationsGrpcTask(getBaseApplication(), this, baseChain, account).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnDelegationsGrpcTask(getBaseApplication(), this, baseChain, account).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AllRewardGrpcTask(getBaseApplication(), this, baseChain, account).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new KavaMarketPriceGrpcTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new KavaIncentiveParamTask(getBaseApplication(), this, baseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new KavaIncentiveRewardTask(getBaseApplication(), this, baseChain, account).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (baseChain.isGRPC()) {
            mTaskCount = 9;
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
//        WLog.w("onTaskResponse " + result.taskType + "   " + mTaskCount);
        if (isFinishing()) return;
        if (result.taskType == BaseConstant.TASK_PUSH_STATUS_UPDATE) {
            if (result.isSuccess) {
                account = getBaseDao().onUpdatePushEnabled(account, (boolean) result.resultData);
            }
            invalidateOptionsMenu();
            return;

        } else if (result.taskType == BaseConstant.TASK_FETCH_PRICE_INFO) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mPrices.clear();
                ArrayList<Price> tempPrice = new ArrayList<>();
                tempPrice = (ArrayList<Price>) result.resultData;
                for (Price price : tempPrice) {
                    getBaseDao().mPrices.add(price);
                }
            }
        }

        mTaskCount--;
        if (result.taskType == BaseConstant.TASK_FETCH_ACCOUNT) {
            account = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
            getBaseDao().mBalances = getBaseDao().onSelectBalance(account.id);
//            WLog.w("getBaseDao().mBalances " + getBaseDao().mBalances.size());


        } else if (result.taskType == TASK_FETCH_NODE_INFO) {
            getBaseDao().mNodeInfo = (NodeInfo) result.resultData;
            mTaskCount = mTaskCount + 1;
            new StationParamInfoTask(getBaseApplication(), this, baseChain, getBaseDao().getChainId()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (result.taskType == TASK_FETCH_OKEX_ALL_VALIDATORS) {
            ArrayList<Validator> allValis = (ArrayList<Validator>) result.resultData;
            if (allValis != null) {
                getBaseDao().mAllValidators = allValis;
            }

        } else if (result.taskType == BaseConstant.TASK_FETCH_BNB_TOKENS) {
            ArrayList<BnbToken> tempTokens = (ArrayList<BnbToken>) result.resultData;
            if (tempTokens != null) {
                for (BnbToken token : tempTokens) {
                    token.type = BnbToken.BNB_TOKEN_TYPE_BEP2;
                    getBaseDao().mBnbTokens.add(token);
                }
            }

        } else if (result.taskType == BaseConstant.TASK_FETCH_BNB_MINI_TOKENS) {
            ArrayList<BnbToken> tempTokens = (ArrayList<BnbToken>) result.resultData;
            if (tempTokens != null) {
                for (BnbToken token : tempTokens) {
                    token.type = BnbToken.BNB_TOKEN_TYPE_MINI;
                    getBaseDao().mBnbTokens.add(token);
                }
            }

        } else if (result.taskType == TASK_FETCH_BNB_TICKER) {
            ArrayList<BnbTicker> tempTickers = (ArrayList<BnbTicker>) result.resultData;
            if (tempTickers != null) {
                getBaseDao().mBnbTickers.addAll(tempTickers);
            }

        } else if (result.taskType == TASK_FETCH_BNB_MINI_TICKER) {
            ArrayList<BnbTicker> tempTickers = (ArrayList<BnbTicker>) result.resultData;
            if (tempTickers != null) {
                getBaseDao().mBnbTickers.addAll(tempTickers);
            }

        } else if (result.taskType == TASK_FETCH_BNB_FEES) {
            getBaseDao().mBnbFees.clear();
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mBnbFees = (ArrayList<ResBnbFee>) result.resultData;
            }

        } else if (result.taskType == TASK_FETCH_OK_ACCOUNT_BALANCE) {
            if (result.isSuccess) {
                getBaseDao().mBalances = getBaseDao().onSelectBalance(account.id);
            }
//            WLog.w("getBaseDao().mBalances " + getBaseDao().mBalances.size());

        } else if (result.taskType == TASK_FETCH_OK_STAKING_INFO) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mOkStaking = (ResOkStaking) result.resultData;
            }

        } else if (result.taskType == TASK_FETCH_OK_UNBONDING_INFO) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mOkUnbonding = ((ResOkUnbonding) result.resultData);
            }

        } else if (result.taskType == TASK_FETCH_OK_TOKEN_LIST) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mOkTokenList = ((ResOkTokenList) result.resultData);
            }

        } else if (result.taskType == TASK_FETCH_OK_DEX_TICKERS) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mOkTickersList = ((ResOkTickersList) result.resultData);
            }

        }


        //gRPC callback
        else if (result.taskType == TASK_GRPC_FETCH_NODE_INFO) {
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
                if (currentPrices != null) {
                    for (QueryOuterClass.CurrentPriceResponse response : currentPrices) {
                        getBaseDao().mKavaTokenPrice.put(response.getMarketId(), response);
                    }
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
                    new Exception().printStackTrace();
                    Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();

                } else {
                    if (getBaseDao().mGRpcAccount != null && !getBaseDao().mGRpcAccount.getTypeUrl().contains(Auth.BaseAccount.getDescriptor().getFullName())) {
                        WUtil.onParseVestingAccount(getBaseDao(), baseChain);
                    }
                    ArrayList<Balance> snapBalance = new ArrayList<>();
                    for (Coin coin : getBaseDao().mGrpcBalance) {
                        snapBalance.add(new Balance(account.id, coin.denom, coin.amount, Calendar.getInstance().getTime().getTime(), "0", "0"));
                    }
                    getBaseDao().onUpdateBalances(account.id, snapBalance);
                }

            } else if (baseChain.equals(BNB_MAIN)) {
                if (getBaseDao().mNodeInfo == null) {
                    new Exception().printStackTrace();
                    Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();

                }
            } else if (baseChain.equals(OKEX_MAIN)) {
                for (Validator all : getBaseDao().mAllValidators) {
                    if (all.status == Validator.BONDED) {
                        getBaseDao().mTopValidators.add(all);
                    } else {
                        getBaseDao().mOtherValidators.add(all);
                    }
                }

                if (getBaseDao().mOkStaking != null && getBaseDao().mOkStaking.validator_address != null) {
                    for (String valAddr : getBaseDao().mOkStaking.validator_address) {
                        for (Validator val : getBaseDao().mAllValidators) {
                            if (val.operator_address.equals(valAddr)) {
                                getBaseDao().mMyValidators.add(val);
                            }
                        }
                    }
                }

                if (getBaseDao().mNodeInfo == null) {
                    new Exception().printStackTrace();
                    Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();

                }

            } else {
                getBaseDao().mAllValidators.addAll(getBaseDao().mTopValidators);
                getBaseDao().mAllValidators.addAll(getBaseDao().mOtherValidators);

                for (Validator top : getBaseDao().mAllValidators) {
                    boolean already = false;
                    for (BondingInfo bond : getBaseDao().mMyDelegations) {
                        if (bond.validator_address.equals(top.operator_address)) {
                            already = true;
                            break;
                        }
                    }
                    for (UnbondingInfo unbond : getBaseDao().mMyUnbondings) {
                        if (unbond.validator_address.equals(top.operator_address) && !already) {
                            already = true;
                            break;
                        }
                    }
                    if (already) getBaseDao().mMyValidators.add(top);
                }
                if (getBaseDao().mNodeInfo == null) {
                    new Exception().printStackTrace();
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

    private final Handler mHandler = new Handler(Looper.getMainLooper());

    public boolean isNotificationsEnabled() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
            if (!manager.areNotificationsEnabled()) {
                return false;
            }
            List<NotificationChannel> channels = manager.getNotificationChannels();
            for (NotificationChannel channel : channels) {
                if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
                    return false;
                }
            }
            return true;
        } else {
            return NotificationManagerCompat.from(this).areNotificationsEnabled();
        }
    }

    public void onShowPushEnableDialog() {
        Dialog_Push_Enable dialog = new Dialog_Push_Enable();
        showDialog(dialog, "wait", false);
    }

    public void onRedirectPushSet() {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, getBaseContext().getPackageName());
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", getBaseContext().getPackageName());
            intent.putExtra("app_uid", getBaseContext().getApplicationInfo().uid);
        } else {
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setData(Uri.parse("package:" + getBaseContext().getPackageName()));
        }
        getBaseContext().startActivity(intent);
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

        new MoonPayTask(getBaseApplication(), new TaskListener() {
            @Override
            public void onTaskResponse(TaskResult result) {
                if (result.isSuccess) {
                    try {
                        String en = URLEncoder.encode((String) result.resultData, "UTF-8");
                        Intent guideIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url_moon_pay) + data + "&signature=" + en));
                        startActivity(guideIntent);
                    } catch (Exception e) {
                        new Exception().printStackTrace();
                        Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();

                    }

                } else {
                    new Exception().printStackTrace();
                    Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();

                }
            }
        }, query).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
    }
}
