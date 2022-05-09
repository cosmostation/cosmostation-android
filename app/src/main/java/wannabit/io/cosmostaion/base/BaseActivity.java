package wannabit.io.cosmostaion.base;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OSMOSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;
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

import android.Manifest;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.protobuf2.Any;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.shasin.notificationbanner.Banner;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cosmos.auth.v1beta1.Auth;
import cosmos.base.v1beta1.CoinOuterClass;
import cosmos.distribution.v1beta1.Distribution;
import cosmos.staking.v1beta1.Staking;
import kava.pricefeed.v1beta1.QueryOuterClass;
import osmosis.gamm.poolmodels.balancer.BalancerPool;
import tendermint.liquidity.v1beta1.Liquidity;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.AppLockActivity;
import wannabit.io.cosmostaion.activities.HtlcSendActivity;
import wannabit.io.cosmostaion.activities.IntroActivity;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.activities.PasswordSetActivity;
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
import wannabit.io.cosmostaion.dialog.AlertDialogUtils;
import wannabit.io.cosmostaion.dialog.Dialog_AddAccount;
import wannabit.io.cosmostaion.dialog.Dialog_Buy_Select_Fiat;
import wannabit.io.cosmostaion.dialog.Dialog_ShareType;
import wannabit.io.cosmostaion.dialog.Dialog_Wait;
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

public class BaseActivity extends AppCompatActivity implements TaskListener {

    protected BaseApplication               mApplication;
    protected BaseData                      mData;
    protected Dialog_Wait                   mDialogWait;
    protected boolean                       mNeedLeaveTime = true;

    public View                             mRootview;
    public Account                          mAccount;
    public BaseChain                        mBaseChain;

    protected int                           mTaskCount;
    private FetchCallBack                   mFetchCallback;

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            onDisplayNotification(intent);
        }
    };

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootview  = findViewById(android.R.id.content);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!(this instanceof PasswordSetActivity) && !(this instanceof PasswordCheckActivity) && !(this instanceof IntroActivity)) {
            if(getBaseApplication().needShowLockScreen()) {
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
    protected void onStop() {
        super.onStop();
        if (mNeedLeaveTime) {
            getBaseDao().setAppLockLeaveTime();
        }

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

    public void onShowWaitDialog() {
        if (mDialogWait == null) {
            mDialogWait = new Dialog_Wait();
        }
        if (getSupportFragmentManager().findFragmentByTag("wait") != null && getSupportFragmentManager().findFragmentByTag("wait").isAdded()) {
            return;
        }

        mDialogWait.setCancelable(false);
        getSupportFragmentManager().beginTransaction().add(mDialogWait, "wait").commitNowAllowingStateLoss();
    }

    public void onHideWaitDialog() {
        if (mDialogWait != null) {
            mDialogWait.dismissAllowingStateLoss();
        }
    }

    public void onHideKeyboard() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        View v = getCurrentFocus();
        if (v == null) {
            v = new View(getBaseContext());
        }
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        v.clearFocus();
    }

    public void onStartMainActivity(int page) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("page", page);
        startActivity(intent);
    }


    public void onStartSendMainDenom() {
        if (mAccount == null) return;
        if (!mAccount.hasPrivateKey) {
            AlertDialogUtils.showDoubleButtonDialog(this, getString(R.string.str_only_observe_title), getString(R.string.str_only_observe_msg),
                    getString(R.string.str_add_mnemonics), view -> onAddMnemonicForAccount(),
                    getString(R.string.str_close), null);
            return;
        }

        if (isGRPC(mBaseChain)) {
            Intent intent = new Intent(getBaseContext(), SendActivity.class);
            BigDecimal mainAvailable = getBaseDao().getAvailable(WDp.mainDenom(mBaseChain));
            BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), mBaseChain, CONST_PW_TX_SIMPLE_SEND, 0);
            if (mainAvailable.compareTo(feeAmount) <= 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
                return;
            }
            intent.putExtra("sendTokenDenom", WDp.mainDenom(mBaseChain));
            startActivity(intent);

        } else {
            Intent intent = new Intent(getBaseContext(), SendActivity.class);
            BigDecimal mainAvailable = getBaseDao().availableAmount(WDp.mainDenom(mBaseChain));
            BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), mBaseChain, CONST_PW_TX_SIMPLE_SEND, 0);
            if (mainAvailable.compareTo(feeAmount) <= 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
                return;
            }
            intent.putExtra("sendTokenDenom", WDp.mainDenom(mBaseChain));
            startActivity(intent);
        }

    }

    public void onStartHTLCSendActivity(String sendDenom) {
//        WLog.w("onStartHTLCSendActivity " + mBaseChain.getChain() + " " + sendDenom);
        if (mAccount == null) return;
        if (!SUPPORT_BEP3_SWAP) {
            Toast.makeText(getBaseContext(), R.string.error_bep3_swap_temporary_disable, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!mAccount.hasPrivateKey) {
            AlertDialogUtils.showDoubleButtonDialog(this, getString(R.string.str_only_observe_title), getString(R.string.str_only_observe_msg),
                    getString(R.string.str_add_mnemonics), view -> onAddMnemonicForAccount(),
                    getString(R.string.str_close), null);
            return;
        }

        boolean hasbalance = true;
        BigDecimal mainDenomAvailable = getBaseDao().availableAmount(WDp.mainDenom(mBaseChain));
        if (mBaseChain.equals(BNB_MAIN)) {
            if (mainDenomAvailable.compareTo(new BigDecimal(FEE_BNB_SEND)) <= 0) {
                hasbalance = false;
            }
        } else if (mBaseChain.equals(KAVA_MAIN)) {
            BigDecimal mainAvailable = getBaseDao().getAvailable(WDp.mainDenom(mBaseChain));
            BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), mBaseChain, CONST_PW_TX_HTLS_REFUND, 0);
            if (mainAvailable.subtract(feeAmount).compareTo(BigDecimal.ZERO) <= 0) {
                hasbalance = false;
            }
        }
        if (!hasbalance) {
            Toast.makeText(getBaseContext(), R.string.error_not_enough_budget_bep3, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(getBaseContext(), HtlcSendActivity.class);
        intent.putExtra("toSwapDenom", sendDenom);
        startActivity(intent);
    }

    public void onChoiceNet(BaseChain chain) { }

    public void onChainSelected(BaseChain baseChain) {
        if (getBaseDao().onSelectAccountsByChain(baseChain).size() >= 5) {
            Toast.makeText(this, R.string.error_max_account_number, Toast.LENGTH_SHORT).show();
            return;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Bundle bundle = new Bundle();
                bundle.putString("chain", baseChain.getChain());
                Dialog_AddAccount add = Dialog_AddAccount.newInstance(bundle);
                add.setCancelable(true);
                getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            }
        }, 300);
    }

    public void onChoiceStarnameResourceAddress(String address) { }

    public void onShare(boolean isText, String address) {
        if(isText) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, address);
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent, "send"));

        } else {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            try {
                final Bitmap mBitmap = WUtil.toBitmap(qrCodeWriter.encode(address, BarcodeFormat.QR_CODE, 480, 480));
                new TedPermission(this)
                        .setPermissionListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted() {
                                try {
                                    ContentValues values = new ContentValues();
                                    values.put(MediaStore.Images.Media.TITLE, address);
                                    values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                                    Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                                    OutputStream outstream = getContentResolver().openOutputStream(uri);
                                    mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outstream);
                                    outstream.close();

                                    Intent shareIntent = new Intent();
                                    shareIntent.setAction(Intent.ACTION_SEND);
                                    shareIntent.putExtra(Intent.EXTRA_TEXT, address);
                                    shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                                    shareIntent.setType("image/jpeg");
                                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                    startActivity(Intent.createChooser(shareIntent, "send"));

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                                Toast.makeText(getBaseContext(), R.string.error_permission, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .setRationaleMessage(getString(R.string.str_permission_qr))
                        .check();

            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
    }

    public void onShareType(String address) {
        Bundle bundle = new Bundle();
        bundle.putString("address", address);
        Dialog_ShareType add = Dialog_ShareType.newInstance(bundle);
        add.setCancelable(true);
        getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
    }

    public void onDeleteAccount(long id) {
        new PushUpdateTask(getBaseApplication(), null, mAccount, getBaseDao().getFCMToken(), false).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        try {
            CryptoHelper.deleteKey(getString(R.string.key_mnemonic) + getBaseDao().onSelectAccount(""+id).uuid);
        } catch (Exception e) { }
        try {
            CryptoHelper.deleteKey(getString(R.string.key_private) + getBaseDao().onSelectAccount(""+id).uuid);
        } catch (Exception e) { }
        getBaseDao().onDeleteAccount(""+id);
        getBaseDao().onSelectBalance(id);

        if (getBaseDao().onSelectAccounts().size() > 0) {
            if (mAccount.id.equals(id)) {
                getBaseDao().setLastUser(getBaseDao().onSelectAccounts().get(0).id);
                onStartMainActivity(0);
            } else {
                getBaseDao().setLastUser(mAccount.id);
                onStartMainActivity(0);
                return;
            }

        } else {
            getBaseDao().setLastUser(-1);
            Intent intent = new Intent(this, IntroActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        for (BaseChain baseChain: getBaseDao().dpSortedChains()) {
            int accountNum = getBaseDao().onSelectAccountsByChain(baseChain).size();
            if (accountNum > 0) {
                getBaseDao().setLastUser(getBaseDao().onSelectAccountsByChain(baseChain).get(0).id);
                break;
            }
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








    private CardView mPushBody;
    private ImageView mPushType, mPushClose;
    private TextView mPushTitle, mPushMsg;

    public void onDisplayNotification(Intent intent) {
        if (!(this instanceof PasswordSetActivity) && !(this instanceof PasswordCheckActivity) && !(this instanceof IntroActivity) && !(this instanceof AppLockActivity)) {
            Banner.make(mRootview, this, Banner.TOP, R.layout.foreground_push);
            mPushBody = Banner.getInstance().getBannerView().findViewById(R.id.push_body);
            mPushType = Banner.getInstance().getBannerView().findViewById(R.id.push_type);
            mPushClose = Banner.getInstance().getBannerView().findViewById(R.id.push_close);
            mPushTitle = Banner.getInstance().getBannerView().findViewById(R.id.push_title);
            mPushMsg = Banner.getInstance().getBannerView().findViewById(R.id.push_msg);

            if (intent.getStringExtra("type").equals("sent")) {
                mPushType.setImageDrawable(getResources().getDrawable(R.drawable.ic_notifications_send));
                mPushTitle.setTextColor(getColor(R.color.colorNotiSend));
            } else if (intent.getStringExtra("type").equals("received")) {
                mPushType.setImageDrawable(getResources().getDrawable(R.drawable.ic_notifications_receive));
                mPushTitle.setTextColor(getColor(R.color.colorNotiReceive));
            } else {
                return;
            }
            mPushTitle.setText(intent.getStringExtra("title"));
            mPushMsg.setText(intent.getStringExtra("Body"));

            bannerClickListener(intent.getStringExtra("pushNotifyto"));
            Banner.getInstance().setCustomAnimationStyle(R.style.topAnimation);
            Banner.getInstance().setDuration(4000);
            Banner.getInstance().show();
        }
    }

    private void bannerClickListener(String address){
        mPushBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account account = getBaseDao().onSelectExistAccount2(address);
                if (account != null) {
                    getBaseDao().setLastUser(account.id);
                    onStartMainActivity(2);
                }
            }
        });

        mPushClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Banner.getInstance().dismissBanner();
            }
        });
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


        if (mBaseChain.equals(BNB_MAIN)) {
            mTaskCount = 6;
            new NodeInfoTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AccountInfoTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BnbTokenListTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BnbMiniTokenListTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BnbTickerTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BnbMiniTickerTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//            new BnbFeesTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (mBaseChain.equals(BaseChain.OKEX_MAIN)) {
            mTaskCount = 8;
            getBaseDao().mOkStaking = null;
            getBaseDao().mOkUnbonding = null;
            getBaseDao().mOkTokenList = null;
            getBaseDao().mOkTickersList = null;
            new NodeInfoTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoAllTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new AccountInfoTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new OkAccountBalanceTask(getBaseApplication(), this, mAccount, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new OkTokenListTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new OkDexTickerTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new OkStakingInfoTask(getBaseApplication(), this, mAccount, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new OkUnbondingInfoTask(getBaseApplication(), this, mAccount, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        }

        // grpc
        else if (mBaseChain.equals(COSMOS_MAIN)) {
            mTaskCount = 10;
            new NodeInfoGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AuthGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BondedValidatorsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondedValidatorsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondingValidatorsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new BalanceGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new DelegationsGrpcTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnDelegationsGrpcTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AllRewardGrpcTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new GravityDexPoolGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (mBaseChain.equals(IOV_MAIN)) {
            mTaskCount = 11;
            new NodeInfoGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AuthGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BondedValidatorsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondedValidatorsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondingValidatorsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new BalanceGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new DelegationsGrpcTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnDelegationsGrpcTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AllRewardGrpcTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new StarNameGrpcFeeTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new StarNameGrpcConfigTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (mBaseChain.equals(OSMOSIS_MAIN)) {
            mTaskCount = 10;
            new NodeInfoGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AuthGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BondedValidatorsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondedValidatorsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondingValidatorsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new BalanceGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new DelegationsGrpcTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnDelegationsGrpcTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AllRewardGrpcTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new OsmosisPoolListGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (mBaseChain.equals(KAVA_MAIN)) {
            mTaskCount = 12;
            new NodeInfoGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AuthGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BondedValidatorsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondedValidatorsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondingValidatorsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new BalanceGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new DelegationsGrpcTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnDelegationsGrpcTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AllRewardGrpcTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new KavaMarketPriceGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new KavaIncentiveParamTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new KavaIncentiveRewardTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (isGRPC(mBaseChain)) {
            mTaskCount = 9;
            new NodeInfoGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AuthGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BondedValidatorsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondedValidatorsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondingValidatorsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new BalanceGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new DelegationsGrpcTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnDelegationsGrpcTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AllRewardGrpcTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }

    }

    @Override
    public void onTaskResponse(TaskResult result) {
//        WLog.w("onTaskResponse " + result.taskType + "   " + mTaskCount);
        if(isFinishing()) return;
        if (result.taskType == BaseConstant.TASK_PUSH_STATUS_UPDATE) {
            if (result.isSuccess) {
                mAccount = getBaseDao().onUpdatePushEnabled(mAccount, (boolean)result.resultData);
            }
            invalidateOptionsMenu();
            return;

        } else if (result.taskType == BaseConstant.TASK_FETCH_PRICE_INFO) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mPrices.clear();
                ArrayList<Price> tempPrice = new ArrayList<>();
                tempPrice = (ArrayList<Price>) result.resultData;
                for (Price price: tempPrice) {
                    getBaseDao().mPrices.add(price);
                }
            }
        }

        mTaskCount--;
        if (result.taskType == BaseConstant.TASK_FETCH_ACCOUNT) {
            mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
            getBaseDao().mBalances = getBaseDao().onSelectBalance(mAccount.id);
//            WLog.w("getBaseDao().mBalances " + getBaseDao().mBalances.size());


        } else if (result.taskType == TASK_FETCH_NODE_INFO) {
            getBaseDao().mNodeInfo = (NodeInfo)result.resultData;
            mTaskCount = mTaskCount + 1;
            new StationParamInfoTask(getBaseApplication(), this, mBaseChain, getBaseDao().getChainId()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (result.taskType == TASK_FETCH_OKEX_ALL_VALIDATORS) {
            ArrayList<Validator> allValis = (ArrayList<Validator>)result.resultData;
            if (allValis != null) {
                getBaseDao().mAllValidators = allValis;
            }

        } else if (result.taskType == BaseConstant.TASK_FETCH_BNB_TOKENS) {
            ArrayList<BnbToken> tempTokens = (ArrayList<BnbToken>)result.resultData;
            if (tempTokens!= null) {
                for (BnbToken token:tempTokens) {
                    token.type = BnbToken.BNB_TOKEN_TYPE_BEP2;
                    getBaseDao().mBnbTokens.add(token);
                }
            }

        } else if (result.taskType == BaseConstant.TASK_FETCH_BNB_MINI_TOKENS) {
            ArrayList<BnbToken> tempTokens = (ArrayList<BnbToken>)result.resultData;
            if (tempTokens!= null) {
                for (BnbToken token:tempTokens) {
                    token.type = BnbToken.BNB_TOKEN_TYPE_MINI;
                    getBaseDao().mBnbTokens.add(token);
                }
            }

        } else if (result.taskType == TASK_FETCH_BNB_TICKER) {
            ArrayList<BnbTicker> tempTickers = (ArrayList<BnbTicker>)result.resultData;
            if (tempTickers!= null) {
                getBaseDao().mBnbTickers.addAll(tempTickers);
            }

        } else if (result.taskType == TASK_FETCH_BNB_MINI_TICKER) {
            ArrayList<BnbTicker> tempTickers = (ArrayList<BnbTicker>)result.resultData;
            if (tempTickers!= null) {
                getBaseDao().mBnbTickers.addAll(tempTickers);
            }

        } else if (result.taskType == TASK_FETCH_BNB_FEES) {
            getBaseDao().mBnbFees.clear();
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mBnbFees = (ArrayList<ResBnbFee>)result.resultData;
            }

        } else if (result.taskType == TASK_FETCH_OK_ACCOUNT_BALANCE) {
            if (result.isSuccess) {
                getBaseDao().mBalances = getBaseDao().onSelectBalance(mAccount.id);
            }
//            WLog.w("getBaseDao().mBalances " + getBaseDao().mBalances.size());

        } else if (result.taskType == TASK_FETCH_OK_STAKING_INFO) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mOkStaking = (ResOkStaking)result.resultData;
            }

        } else if (result.taskType == TASK_FETCH_OK_UNBONDING_INFO) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mOkUnbonding = ((ResOkUnbonding)result.resultData);
            }

        } else if (result.taskType == TASK_FETCH_OK_TOKEN_LIST) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mOkTokenList = ((ResOkTokenList)result.resultData);
            }

        } else if (result.taskType == TASK_FETCH_OK_DEX_TICKERS) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mOkTickersList = ((ResOkTickersList)result.resultData);
            }

        }


        //gRPC callback
        else if (result.taskType == TASK_GRPC_FETCH_NODE_INFO) {
            tendermint.p2p.Types.NodeInfo tempNodeInfo = (tendermint.p2p.Types.NodeInfo) result.resultData;
            if (tempNodeInfo != null) { getBaseDao().mGRpcNodeInfo = tempNodeInfo;
                mTaskCount = mTaskCount + 5;
                new StationParamInfoTask(getBaseApplication(), this, mBaseChain, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                new StationIbcPathsTask(getBaseApplication(), this, mBaseChain, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                new StationIbcTokensTask(getBaseApplication(), this, mBaseChain, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                new MintScanAssetsTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                new MintScanCw20AssetsTask(getBaseApplication(), this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }

        } else if (result.taskType == TASK_GRPC_FETCH_AUTH) {
            Any tempAccount = (Any)result.resultData;
            if (tempAccount != null) { getBaseDao().mGRpcAccount = tempAccount; }

        } else if (result.taskType == TASK_GRPC_FETCH_BONDED_VALIDATORS) {
            ArrayList<Staking.Validator> bonded = (ArrayList<Staking.Validator>)result.resultData;
            if (bonded != null) { getBaseDao().mGRpcTopValidators.addAll(bonded); }

        } else if (result.taskType == TASK_GRPC_FETCH_UNBONDED_VALIDATORS) {
            ArrayList<Staking.Validator> unbonded = (ArrayList<Staking.Validator>) result.resultData;
            if (unbonded != null) { getBaseDao().mGRpcOtherValidators.addAll(unbonded); }

        } else if (result.taskType == TASK_GRPC_FETCH_UNBONDING_VALIDATORS) {
            ArrayList<Staking.Validator> unbonding = (ArrayList<Staking.Validator>) result.resultData;
            if (unbonding != null) { getBaseDao().mGRpcOtherValidators.addAll(unbonding); }

        } else if (result.taskType == TASK_GRPC_FETCH_BALANCE) {
            ArrayList<CoinOuterClass.Coin> balance = (ArrayList<CoinOuterClass.Coin>) result.resultData;
            if (balance != null && balance.size() > 0) {
                for (CoinOuterClass.Coin coin: balance) {
                    if (!coin.getAmount().equals("0")) {
                        getBaseDao().mGrpcBalance.add(new Coin(coin.getDenom(), coin.getAmount()));
                    } else {
                        if (coin.getDenom().equalsIgnoreCase(WDp.mainDenom(mBaseChain))) {
                            getBaseDao().mGrpcBalance.add(new Coin(coin.getDenom(), coin.getAmount()));
                        }
                    }
                }
            }
            if (getBaseDao().mGrpcBalance.size() <= 0 || getBaseDao().getAvailable(WDp.mainDenom(mBaseChain)).compareTo(BigDecimal.ZERO) <= 0) {
                getBaseDao().mGrpcBalance.add(new Coin(WDp.mainDenom(mBaseChain), "0"));
            }

        } else if (result.taskType == TASK_GRPC_FETCH_DELEGATIONS) {
            ArrayList<Staking.DelegationResponse> delegations = (ArrayList<Staking.DelegationResponse>) result.resultData;
            if (delegations != null) { getBaseDao().mGrpcDelegations = delegations; }

        } else if (result.taskType == TASK_GRPC_FETCH_UNDELEGATIONS) {
            ArrayList<Staking.UnbondingDelegation> undelegations = (ArrayList<Staking.UnbondingDelegation>) result.resultData;
            if (undelegations != null) { getBaseDao().mGrpcUndelegations = undelegations; }

        } else if (result.taskType == TASK_GRPC_FETCH_ALL_REWARDS) {
            ArrayList<Distribution.DelegationDelegatorReward> rewards = (ArrayList<Distribution.DelegationDelegatorReward>) result.resultData;
            if (rewards != null) { getBaseDao().mGrpcRewards = rewards; }

        }

         else if (result.taskType == TASK_GRPC_FETCH_STARNAME_FEE) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mGrpcStarNameFee = ((starnamed.x.configuration.v1beta1.Types.Fees)result.resultData);
            }

        } else if (result.taskType == TASK_GRPC_FETCH_STARNAME_CONFIG) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mGrpcStarNameConfig = ((starnamed.x.configuration.v1beta1.Types.Config )result.resultData);
            }

        }

        else if (result.taskType == TASK_GRPC_FETCH_GRAVITY_POOL_LIST) {
            if (result.isSuccess && result.resultData != null) {
                List<Liquidity.Pool> pools = (List<Liquidity.Pool>)result.resultData;
                getBaseDao().mGrpcGravityPools = new ArrayList<Liquidity.Pool>(pools);
            }
        }

        else if (result.taskType == TASK_GRPC_FETCH_OSMOSIS_POOL_LIST) {
            if (result.isSuccess && result.resultData != null) {
                List<BalancerPool.Pool> pools = (List<BalancerPool.Pool>)result.resultData;
                getBaseDao().mGrpcOsmosisPool = new ArrayList<BalancerPool.Pool>(pools);
            }
        }

        //kava
        else if (result.taskType == TASK_FETCH_KAVA_INCENTIVE_PARAM) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mIncentiveParam5 = (IncentiveParam)result.resultData;
            }

        } else if (result.taskType == TASK_FETCH_KAVA_INCENTIVE_REWARD) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mIncentiveRewards = (IncentiveReward)result.resultData;
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
                    for (Cw20Assets assets: getBaseDao().mCw20Assets) {
                        if (assets.chain.equalsIgnoreCase(WDp.getChainNameByBaseChain(mBaseChain))) {
                            mTaskCount = mTaskCount + 1;
                            new Cw20BalanceGrpcTask(getBaseApplication(), this, mBaseChain, mAccount, assets.contract_address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                        }
                    }
                }
            }
        }

        if (mTaskCount == 0) {
            if (isGRPC(mBaseChain)) {
                getBaseDao().mGRpcAllValidators.addAll(getBaseDao().mGRpcTopValidators);
                getBaseDao().mGRpcAllValidators.addAll(getBaseDao().mGRpcOtherValidators);
                for (Staking.Validator validator: getBaseDao().mGRpcAllValidators) {
                    boolean already = false;
                    for (Staking.DelegationResponse delegation: getBaseDao().mGrpcDelegations) {
                        if (delegation.getDelegation().getValidatorAddress().equals(validator.getOperatorAddress())) {
                            already = true; break;
                        }
                    }
                    for (Staking.UnbondingDelegation undelegation: getBaseDao().mGrpcUndelegations) {
                        if (undelegation.getValidatorAddress().equals(validator.getOperatorAddress())) {
                            already = true; break;
                        }
                    }
                    if (already) getBaseDao().mGRpcMyValidators.add(validator);
                }

//                WLog.w("mGRpcNodeInfo " + getBaseDao().mGRpcNodeInfo);
//                WLog.w("mGRpcTopValidators " + getBaseDao().mGRpcTopValidators.size());
//                WLog.w("mGRpcOtherValidators " + getBaseDao().mGRpcOtherValidators.size());
//                WLog.w("mGRpcAllValidators " + getBaseDao().mGRpcAllValidators.size());
//                WLog.w("mGRpcMyValidators " + getBaseDao().mGRpcMyValidators.size());
//                WLog.w("mIbcPaths " + getBaseDao().mIbcPaths.size());
//                WLog.w("mIbcTokens " + getBaseDao().mIbcTokens.size());
                if (getBaseDao().mGRpcNodeInfo == null) {
                    Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();
                } else {
//                    WLog.w("mGRpcAccount " + getBaseDao().mGRpcAccount.getTypeUrl());
                    if (getBaseDao().mGRpcAccount != null && !getBaseDao().mGRpcAccount.getTypeUrl().contains(Auth.BaseAccount.getDescriptor().getFullName())) {
                        WUtil.onParseVestingAccount(getBaseDao(), mBaseChain);
                    }
                    ArrayList<Balance> snapBalance = new ArrayList<>();
                    for (Coin coin: getBaseDao().mGrpcBalance) {
                        snapBalance.add(new Balance(mAccount.id, coin.denom, coin.amount, Calendar.getInstance().getTime().getTime(), "0", "0"));
                    }
                    getBaseDao().onUpdateBalances(mAccount.id, snapBalance);
//                    WLog.w("getBaseDao().mGRpcNodeInfo " + getBaseDao().mGRpcNodeInfo.getNetwork());
                }

            } else if (mBaseChain.equals(BNB_MAIN)) {
                if (getBaseDao().mNodeInfo == null) {
                    Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();
                }
//                WLog.w("mBnbTokens " + getBaseDao().mBnbTokens.size());
//                WLog.w("mBnbTickers " + getBaseDao().mBnbTickers.size());

            } else if (mBaseChain.equals(OKEX_MAIN)) {
                for (Validator all:getBaseDao().mAllValidators) {
                    if (all.status == Validator.BONDED) {
                        getBaseDao().mTopValidators.add(all);
                    } else {
                        getBaseDao().mOtherValidators.add(all);
                    }
                }
//                WLog.w("mAllValidators " + getBaseDao().mAllValidators.size());
//                WLog.w("mMyValidators " + getBaseDao().mMyValidators.size());
//                WLog.w("mTopValidators " + getBaseDao().mTopValidators.size());
//                WLog.w("mOtherValidators " + getBaseDao().mOtherValidators.size());

                if (getBaseDao().mOkStaking != null && getBaseDao().mOkStaking.validator_address != null) {
                    for (String valAddr : getBaseDao().mOkStaking.validator_address) {
                        for (Validator val:getBaseDao().mAllValidators) {
                            if (val.operator_address.equals(valAddr)) {
                                getBaseDao().mMyValidators.add(val);
                            }
                        }
                    }
                }

                if (getBaseDao().mNodeInfo == null) {
                    Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();
                }

            } else {
                getBaseDao().mAllValidators.addAll(getBaseDao().mTopValidators);
                getBaseDao().mAllValidators.addAll(getBaseDao().mOtherValidators);

                for (Validator top:getBaseDao().mAllValidators) {
                    boolean already = false;
                    for (BondingInfo bond: getBaseDao().mMyDelegations) {
                        if (bond.validator_address.equals(top.operator_address)) {
                            already = true;
                            break;
                        }
                    }
                    for (UnbondingInfo unbond: getBaseDao().mMyUnbondings) {
                        if (unbond.validator_address.equals(top.operator_address) && !already) {
                            already = true;
                            break;
                        }
                    }
                    if (already) getBaseDao().mMyValidators.add(top);
                }
//                WLog.w("mAllValidators " + getBaseDao().mAllValidators.size());
//                WLog.w("mMyValidators " + getBaseDao().mMyValidators.size());
//                WLog.w("mTopValidators " + getBaseDao().mTopValidators.size());
//                WLog.w("mOtherValidators " + getBaseDao().mOtherValidators.size());
//                WLog.w("mBalances " + getBaseDao().mBalances.size());
//                WLog.w("mMyDelegations " + getBaseDao().mMyDelegations.size());
//                WLog.w("mMyUnbondings " + getBaseDao().mMyUnbondings.size());
//                WLog.w("mMyRewards " + getBaseDao().mMyRewards.size());

                if (getBaseDao().mNodeInfo == null) {
                    Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();
                }

            }
            new StationPriceInfoTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            //callback with delay fix gRPC  timming issue
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mFetchCallback != null) {
                        mFetchCallback.fetchFinished();
                    }
                }
            },300);
        }
    }

    private Handler mHandler = new Handler(Looper.getMainLooper());

    public boolean isNotificationsEnabled() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager manager = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
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
        AlertDialogUtils.showDoubleButtonDialog(this, getString(R.string.str_push_permission_title), getString(R.string.str_push_permission_msg),
                getString(R.string.str_cancel), view -> onRedirectPushSet(),
                getString(R.string.str_continue), view -> { }, false);
    }

    public void onRedirectPushSet() {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, getBaseContext().getPackageName());
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
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

    public void onShowBuyWarnNoKey() {
        AlertDialogUtils.showDoubleButtonDialog(this, getString(R.string.str_only_observe_title), getString(R.string.str_buy_without_key_msg),
                getString(R.string.str_cancel), null,
                getString(R.string.str_continue), view -> onShowBuySelectFiat());
    }

    public void onShowBuySelectFiat() {
        Dialog_Buy_Select_Fiat dialog = Dialog_Buy_Select_Fiat.newInstance();
        dialog.setCancelable(true);
        getSupportFragmentManager().beginTransaction().add(dialog, "wait").commitNowAllowingStateLoss();
    }

    public void onStartMoonpaySignature(String fiat) {
        String query = "?apiKey=" + getString(R.string.moon_pay_public_key);
        if (mBaseChain.equals(COSMOS_MAIN)) {
            query = query + "&currencyCode=atom";
        } else if (mBaseChain.equals(BNB_MAIN)) {
            query = query + "&currencyCode=bnb";
        } else if (mBaseChain.equals(KAVA_MAIN)) {
            query = query + "&currencyCode=kava";
        } else if (mBaseChain.equals(BAND_MAIN)) {
            query = query + "&currencyCode=band";
        }
        query = query + "&walletAddress=" + mAccount.address + "&baseCurrencyCode=" + fiat;
        final String data = query;

        new MoonPayTask(getBaseApplication(), new TaskListener() {
            @Override
            public void onTaskResponse(TaskResult result) {
                if (result.isSuccess) {
                    try {
                        String en = URLEncoder.encode((String)result.resultData, "UTF-8");
                        Intent guideIntent = new Intent(Intent.ACTION_VIEW , Uri.parse(getString(R.string.url_moon_pay) + data + "&signature=" + en));
                        startActivity(guideIntent);
                    }catch (Exception e) {
                        Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();
                }
            }
        }, query).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
    }
}
