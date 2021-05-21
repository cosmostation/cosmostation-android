package wannabit.io.cosmostaion.base;

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
import java.util.List;

import cosmos.auth.v1beta1.Auth;
import cosmos.base.v1beta1.CoinOuterClass;
import cosmos.distribution.v1beta1.Distribution;
import cosmos.staking.v1beta1.Staking;
import irismod.token.TokenOuterClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tendermint.p2p.Types;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.AppLockActivity;
import wannabit.io.cosmostaion.activities.HtlcSendActivity;
import wannabit.io.cosmostaion.activities.IntroActivity;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.activities.PasswordSetActivity;
import wannabit.io.cosmostaion.activities.RestoreActivity;
import wannabit.io.cosmostaion.activities.SendActivity;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BnbTicker;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.dialog.Dialog_Buy_Select_Fiat;
import wannabit.io.cosmostaion.dialog.Dialog_Buy_Without_Key;
import wannabit.io.cosmostaion.dialog.Dialog_Push_Enable;
import wannabit.io.cosmostaion.dialog.Dialog_ShareType;
import wannabit.io.cosmostaion.dialog.Dialog_Wait;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.model.BondingInfo;
import wannabit.io.cosmostaion.model.NodeInfo;
import wannabit.io.cosmostaion.model.RewardInfo;
import wannabit.io.cosmostaion.model.UnbondingInfo;
import wannabit.io.cosmostaion.model.kava.CdpParam;
import wannabit.io.cosmostaion.model.kava.MarketPrice;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResBandOracleStatus;
import wannabit.io.cosmostaion.network.res.ResBnbFee;
import wannabit.io.cosmostaion.network.res.ResCgcTic;
import wannabit.io.cosmostaion.network.res.ResIovConfig;
import wannabit.io.cosmostaion.network.res.ResIovFee;
import wannabit.io.cosmostaion.network.res.ResKavaPriceFeedParam;
import wannabit.io.cosmostaion.network.res.ResMintParam;
import wannabit.io.cosmostaion.network.res.ResOkStaking;
import wannabit.io.cosmostaion.network.res.ResOkTickersList;
import wannabit.io.cosmostaion.network.res.ResOkTokenList;
import wannabit.io.cosmostaion.network.res.ResOkUnbonding;
import wannabit.io.cosmostaion.network.res.ResStakingPool;
import wannabit.io.cosmostaion.task.FetchTask.AccountInfoTask;
import wannabit.io.cosmostaion.task.FetchTask.AllRewardsTask;
import wannabit.io.cosmostaion.task.FetchTask.BandOracleStatusTask;
import wannabit.io.cosmostaion.task.FetchTask.BnbMiniTickerTask;
import wannabit.io.cosmostaion.task.FetchTask.BnbMiniTokenListTask;
import wannabit.io.cosmostaion.task.FetchTask.BnbTickerTask;
import wannabit.io.cosmostaion.task.FetchTask.BnbTokenListTask;
import wannabit.io.cosmostaion.task.FetchTask.BondingStateTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaMarketPriceTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaPriceFeedParamTask;
import wannabit.io.cosmostaion.task.FetchTask.MoonPayTask;
import wannabit.io.cosmostaion.task.FetchTask.NodeInfoTask;
import wannabit.io.cosmostaion.task.FetchTask.OkAccountBalanceTask;
import wannabit.io.cosmostaion.task.FetchTask.OkDexTickerTask;
import wannabit.io.cosmostaion.task.FetchTask.OkStakingInfoTask;
import wannabit.io.cosmostaion.task.FetchTask.OkTokenListTask;
import wannabit.io.cosmostaion.task.FetchTask.OkUnbondingInfoTask;
import wannabit.io.cosmostaion.task.FetchTask.PushUpdateTask;
import wannabit.io.cosmostaion.task.FetchTask.StarNameConfigTask;
import wannabit.io.cosmostaion.task.FetchTask.StarNameFeeTask;
import wannabit.io.cosmostaion.task.FetchTask.StationPriceInfoTask;
import wannabit.io.cosmostaion.task.FetchTask.UnBondingStateTask;
import wannabit.io.cosmostaion.task.FetchTask.ValidatorInfoAllTask;
import wannabit.io.cosmostaion.task.FetchTask.ValidatorInfoBondedTask;
import wannabit.io.cosmostaion.task.FetchTask.ValidatorInfoUnbondedTask;
import wannabit.io.cosmostaion.task.FetchTask.ValidatorInfoUnbondingTask;
import wannabit.io.cosmostaion.task.SingleFetchTask.SingleInflationTask;
import wannabit.io.cosmostaion.task.SingleFetchTask.SingleMintParamTask;
import wannabit.io.cosmostaion.task.SingleFetchTask.SingleProvisionsTask;
import wannabit.io.cosmostaion.task.SingleFetchTask.SingleStakingPoolTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.AllRewardGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.AuthGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.BalanceGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.BondedValidatorsGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.DelegationsGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.InflationGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.IrisParamMintGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.IrisTokenListGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.NodeInfoGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.ParamMintGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.ProvisionGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.StakingPoolGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.UnBondedValidatorsGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.UnBondingValidatorsGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.UnDelegationsGrpcTask;
import wannabit.io.cosmostaion.utils.FetchCallBack;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.FETCHAI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.PERSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.FEE_BNB_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.SUPPORT_BEP3_SWAP;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_ALL_REWARDS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_BAND_ORACLE_STATUS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_BNB_FEES;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_BNB_MINI_TICKER;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_BNB_TICKER;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_PRICE_FEED_PARAM;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_TOKEN_PRICE;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_MINT_PARAM;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_NODE_INFO;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_OKEX_ALL_VALIDATORS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_OK_ACCOUNT_BALANCE;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_OK_DEX_TICKERS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_OK_STAKING_INFO;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_OK_TOKEN_LIST;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_OK_UNBONDING_INFO;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_STARNAME_CONFIG;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_STARNAME_FEE;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_ALL_REWARDS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_AUTH;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_BALANCE;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_BONDED_VALIDATORS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_DELEGATIONS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_INFLATION;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_IRIS_PARAM_MINT;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_IRIS_TOKEN_LIST;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_NODE_INFO;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_PARAM_MINT;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_PROVISION;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_STAKING_POOL;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_UNBONDED_VALIDATORS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_UNBONDING_VALIDATORS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_UNDELEGATIONS;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BAND;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_CERTIK;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OK;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SECRET;

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
        mDialogWait = new Dialog_Wait();
        mDialogWait.setCancelable(false);
        getSupportFragmentManager().beginTransaction().add(mDialogWait, "wait").commitNowAllowingStateLoss();
    }

    public void onHideWaitDialog() {
        if (mDialogWait != null) {
            mDialogWait.dismiss();
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
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }

        if (isGRPC(mBaseChain)) {
            Intent intent = new Intent(getBaseContext(), SendActivity.class);
            BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), mBaseChain, CONST_PW_TX_SIMPLE_SEND, 0);
            if ((getBaseDao().getAvailable(WDp.mainDenom(mBaseChain))).compareTo(feeAmount) <= 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
                return;
            }
            intent.putExtra("sendTokenDenom", WDp.mainDenom(mBaseChain));
            startActivity(intent);

        } else {
            Intent intent = new Intent(getBaseContext(), SendActivity.class);
            BigDecimal mainDenomAvailable = getBaseDao().availableAmount(WDp.mainDenom(mBaseChain));
            boolean hasbalance = false;
            if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
                if (mainDenomAvailable.compareTo(new BigDecimal(FEE_BNB_SEND)) > 0) {
                    hasbalance  = true;
                }
                intent.putExtra("bnbToken", WUtil.getBnbMainToken(getBaseDao().mBnbTokens));

            } else if (mBaseChain.equals(IOV_MAIN)) {
                if (mainDenomAvailable.compareTo(new BigDecimal("100000")) > 0) {
                    hasbalance  = true;
                }
                intent.putExtra("iovDenom", TOKEN_IOV);

            } else if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
                if (mainDenomAvailable.compareTo(BigDecimal.ZERO) > 0) {
                    hasbalance  = true;
                }
                intent.putExtra("sendTokenDenom", TOKEN_KAVA);

            } else if (mBaseChain.equals(BAND_MAIN)) {
                if (mainDenomAvailable.compareTo(BigDecimal.ZERO) > 0) {
                    hasbalance  = true;
                }

            } else if (mBaseChain.equals(IOV_TEST)) {
                if (mainDenomAvailable.compareTo(new BigDecimal("100000")) > 0) {
                    hasbalance  = true;
                }
                intent.putExtra("iovDenom", TOKEN_IOV_TEST);

            } else if (mBaseChain.equals(OKEX_MAIN) || mBaseChain.equals(OK_TEST)) {
                if (mainDenomAvailable.compareTo(new BigDecimal("0.002")) > 0) {
                    hasbalance  = true;
                }
                intent.putExtra("okDenom", TOKEN_OK);

            } else if (mBaseChain.equals(CERTIK_MAIN) || mBaseChain.equals(CERTIK_TEST)) {
                if (mainDenomAvailable.compareTo(new BigDecimal("5000")) > 0) {
                    hasbalance  = true;
                }
                intent.putExtra("certikDenom", TOKEN_CERTIK);

            } else if (mBaseChain.equals(SECRET_MAIN)) {
                if (mainDenomAvailable.compareTo(new BigDecimal("20000")) > 0) {
                    hasbalance  = true;
                }
                intent.putExtra("sendTokenDenom", WDp.mainDenom(mBaseChain));

            } else if (mBaseChain.equals(SENTINEL_MAIN)) {
                BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), mBaseChain, CONST_PW_TX_SIMPLE_SEND, 0);
                if (mainDenomAvailable.compareTo(feeAmount) > 0) {
                    hasbalance  = true;
                }
                intent.putExtra("sendTokenDenom", WDp.mainDenom(mBaseChain));

            } else if (mBaseChain.equals(FETCHAI_MAIN)) {
                BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), mBaseChain, CONST_PW_TX_SIMPLE_SEND, 0);
                if (mainDenomAvailable.compareTo(feeAmount) > 0) {
                    hasbalance  = true;
                }
                intent.putExtra("sendTokenDenom", WDp.mainDenom(mBaseChain));

            } else if (mBaseChain.equals(SIF_MAIN)) {
                BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), mBaseChain, CONST_PW_TX_SIMPLE_SEND, 0);
                if (mainDenomAvailable.compareTo(feeAmount) > 0) {
                    hasbalance  = true;
                }
                intent.putExtra("sendTokenDenom", WDp.mainDenom(mBaseChain));
            } else if (mBaseChain.equals(KI_MAIN)) {
                BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), mBaseChain, CONST_PW_TX_SIMPLE_SEND, 0);
                if (mainDenomAvailable.compareTo(feeAmount) > 0) {
                    hasbalance  = true;
                }
                intent.putExtra("sendTokenDenom", WDp.mainDenom(mBaseChain));
            }

            if (!hasbalance) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
                return;
            }
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
        if (mBaseChain.equals(BNB_TEST) || mBaseChain.equals(KAVA_TEST)) {
            Toast.makeText(getBaseContext(), R.string.error_bep3_swap_temporary_disable, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }

        boolean hasbalance = true;
        BigDecimal mainDenomAvailable = getBaseDao().getAvailable(WDp.mainDenom(mBaseChain));
        if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
            if (mainDenomAvailable.compareTo(new BigDecimal(FEE_BNB_SEND)) <= 0) {
                hasbalance  = false;
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
        getBaseDao().onDeleteAccount(""+id);
        getBaseDao().onSelectBalance(id);

        if(getBaseDao().onSelectAccounts().size() > 0) {
            getBaseDao().setLastUser(getBaseDao().onSelectAccounts().get(0).id);
            onStartMainActivity(0);
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

    public void onCancelWithVesting() {

    }

    public void onUpdateUserAlarm(Account account, boolean useAlarm) {
        new PushUpdateTask(getBaseApplication(), this, account, getBaseDao().getFCMToken(), useAlarm).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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

        getBaseDao().mPrices.clear();

        getBaseDao().mNodeInfo = null;
        getBaseDao().mAllValidators.clear();
        getBaseDao().mMyValidators.clear();
        getBaseDao().mTopValidators.clear();
        getBaseDao().mOtherValidators.clear();

        getBaseDao().mBalances.clear();
        getBaseDao().mMyDelegations.clear();
        getBaseDao().mMyUnbondings.clear();
        getBaseDao().mMyRewards.clear();

        getBaseDao().mStakingPool = null;
        getBaseDao().mInflation = BigDecimal.ZERO;
        getBaseDao().mProvisions = BigDecimal.ZERO;

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

        getBaseDao().mGrpcStakingPool = null;
        getBaseDao().mGrpcParamMint = null;
        getBaseDao().mGrpcInflation = BigDecimal.ZERO;
        getBaseDao().mGrpcProvision = BigDecimal.ZERO;

        getBaseDao().mGrpcIrisParamMint = null;
        getBaseDao().mGrpcIrisTokens.clear();


        //kava-5
        getBaseDao().mMyHardDeposit.clear();
        getBaseDao().mMyHardBorrow.clear();
        getBaseDao().mModuleCoins.clear();
        getBaseDao().mReserveCoins.clear();


        //binance
        getBaseDao().mBnbTokens.clear();
        getBaseDao().mBnbTickers.clear();


        if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST) ) {
            mTaskCount = 6;
            new NodeInfoTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AccountInfoTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BnbTokenListTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BnbMiniTokenListTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BnbTickerTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BnbMiniTickerTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//            new BnbFeesTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


        } else if (mBaseChain.equals(KAVA_MAIN)) {
            mTaskCount = 13;
            new NodeInfoTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoBondedTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoUnbondingTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoUnbondedTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new AccountInfoTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BondingStateTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondingStateTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AllRewardsTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new SingleMintParamTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleInflationTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleProvisionsTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleStakingPoolTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new KavaPriceFeedParamTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


        } else if (mBaseChain.equals(KAVA_TEST)) {
            mTaskCount = 13;
            new NodeInfoTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoBondedTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoUnbondingTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoUnbondedTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new AccountInfoTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BondingStateTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondingStateTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AllRewardsTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new SingleMintParamTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleInflationTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleProvisionsTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleStakingPoolTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new KavaPriceFeedParamTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


        } else if (mBaseChain.equals(IOV_MAIN) || mBaseChain.equals(IOV_TEST)) {
            mTaskCount = 14;
            new NodeInfoTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoBondedTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoUnbondingTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoUnbondedTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AccountInfoTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BondingStateTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AllRewardsTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new UnBondingStateTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleMintParamTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleInflationTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleProvisionsTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleStakingPoolTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new StarNameFeeTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new StarNameConfigTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (mBaseChain.equals(BAND_MAIN)) {
            mTaskCount = 13;
            new NodeInfoTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoBondedTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoUnbondingTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoUnbondedTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AccountInfoTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BondingStateTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AllRewardsTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new UnBondingStateTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleMintParamTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleInflationTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleProvisionsTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleStakingPoolTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new BandOracleStatusTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (mBaseChain.equals(BaseChain.OKEX_MAIN) || mBaseChain.equals(BaseChain.OK_TEST)) {
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

        } else if (mBaseChain.equals(BaseChain.CERTIK_MAIN) || mBaseChain.equals(BaseChain.CERTIK_TEST)) {
            mTaskCount = 12;
            new NodeInfoTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoBondedTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoUnbondingTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoUnbondedTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AccountInfoTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BondingStateTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AllRewardsTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new UnBondingStateTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleMintParamTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleInflationTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleProvisionsTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleStakingPoolTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (mBaseChain.equals(SECRET_MAIN)) {
            mTaskCount = 12;
            new NodeInfoTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoBondedTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoUnbondingTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoUnbondedTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AccountInfoTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BondingStateTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AllRewardsTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new UnBondingStateTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleMintParamTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleInflationTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleProvisionsTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleStakingPoolTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            Toast.makeText(getBaseContext(), "Using Figment's Data Hub API for Secret Network.", Toast.LENGTH_SHORT).show();

        } else if (mBaseChain.equals(SENTINEL_MAIN)) {
            mTaskCount = 12;
            new NodeInfoTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoBondedTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoUnbondingTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoUnbondedTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AccountInfoTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BondingStateTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AllRewardsTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new UnBondingStateTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleMintParamTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleInflationTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleProvisionsTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleStakingPoolTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (mBaseChain.equals(FETCHAI_MAIN)) {
            mTaskCount = 12;
            new NodeInfoTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoBondedTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoUnbondingTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoUnbondedTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AccountInfoTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BondingStateTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AllRewardsTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new UnBondingStateTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleMintParamTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleInflationTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleProvisionsTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleStakingPoolTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (mBaseChain.equals(SIF_MAIN)) {
            mTaskCount = 9;
            new NodeInfoTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoBondedTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoUnbondingTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoUnbondedTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AccountInfoTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BondingStateTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AllRewardsTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new UnBondingStateTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleStakingPoolTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (mBaseChain.equals(KI_MAIN)) {
            mTaskCount = 12;
            new NodeInfoTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoBondedTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoUnbondingTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoUnbondedTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AccountInfoTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BondingStateTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AllRewardsTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new UnBondingStateTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleMintParamTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleInflationTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleProvisionsTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleStakingPoolTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        }

        else if (mBaseChain.equals(COSMOS_MAIN) || mBaseChain.equals(AKASH_MAIN) || mBaseChain.equals(PERSIS_MAIN) || mBaseChain.equals(CRYPTO_MAIN) || mBaseChain.equals(COSMOS_TEST)) {
            mTaskCount = 13;
            new NodeInfoGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AuthGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BondedValidatorsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondedValidatorsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondingValidatorsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new BalanceGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new DelegationsGrpcTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnDelegationsGrpcTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AllRewardGrpcTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new StakingPoolGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ParamMintGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ProvisionGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new InflationGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


        }  else if (mBaseChain.equals(IRIS_MAIN) || mBaseChain.equals(IRIS_TEST)) {
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

            new StakingPoolGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new IrisParamMintGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new IrisTokenListGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        }

        onPriceTic(BaseChain.getChain(mAccount.baseChain));
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
        }

        mTaskCount--;
        if (result.taskType == BaseConstant.TASK_FETCH_ACCOUNT) {
            mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
            getBaseDao().mBalances = getBaseDao().onSelectBalance(mAccount.id);
            WLog.w("getBaseDao().mBalances " + getBaseDao().mBalances.size());
            mTaskCount = mTaskCount + 1;
            new StationPriceInfoTask(getBaseApplication(), this, WUtil.marketPrice(mBaseChain, getBaseDao())).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


        } else if (result.taskType == TASK_FETCH_NODE_INFO) {
            getBaseDao().mNodeInfo = (NodeInfo)result.resultData;

        } else if (result.taskType == BaseConstant.TASK_FETCH_BONDEB_VALIDATOR) {
            ArrayList<Validator> bondedValis = (ArrayList<Validator>)result.resultData;
            if (bondedValis != null) {
                getBaseDao().mTopValidators = bondedValis;
            }

        } else if (result.taskType == BaseConstant.TASK_FETCH_UNBONDING_VALIDATOR || result.taskType == BaseConstant.TASK_FETCH_UNBONDED_VALIDATOR) {
            ArrayList<Validator> unbondValis = (ArrayList<Validator>)result.resultData;
            if (unbondValis != null) {
                getBaseDao().mOtherValidators.addAll(unbondValis);
            }

        } else if (result.taskType == TASK_FETCH_OKEX_ALL_VALIDATORS) {
            ArrayList<Validator> allValis = (ArrayList<Validator>)result.resultData;
            if (allValis != null) {
                getBaseDao().mAllValidators = allValis;
            }

        } else if (result.taskType == BaseConstant.TASK_FETCH_BONDING_STATE) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mMyDelegations = (ArrayList<BondingInfo>)result.resultData;
            }

        } else if (result.taskType == BaseConstant.TASK_FETCH_UNBONDING_STATE) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mMyUnbondings = (ArrayList<UnbondingInfo>)result.resultData;
            }

        } else if (result.taskType == TASK_FETCH_ALL_REWARDS) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mMyRewards = (ArrayList<RewardInfo>)result.resultData;
            }

        } else if (result.taskType == BaseConstant.TASK_FETCH_INFLATION) {
            try {
                getBaseDao().mInflation = new BigDecimal((String)result.resultData);
            } catch (Exception e) {}

        } else if (result.taskType == BaseConstant.TASK_FETCH_PROVISIONS) {
            try {
                getBaseDao().mProvisions = new BigDecimal((String)result.resultData);
            } catch (Exception e) {}

        } else if (result.taskType == BaseConstant.TASK_FETCH_STAKING_POOL) {
            try {
                getBaseDao().mStakingPool = (ResStakingPool)result.resultData;
            } catch (Exception e) {}

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

        } else if (result.taskType == BaseConstant.TASK_FETCH_KAVA_CDP_PARAM) {
            if (result.isSuccess && result.resultData != null) {
                final CdpParam cdpParam = (CdpParam)result.resultData;
                getBaseDao().mCdpParam = cdpParam;
            }

        } else if (result.taskType == TASK_FETCH_KAVA_PRICE_FEED_PARAM) {
            getBaseDao().mKavaTokenPrices.clear();
            if (result.isSuccess && result.resultData != null) {
                final ResKavaPriceFeedParam.KavaPriceParam kavaPriceParam = (ResKavaPriceFeedParam.KavaPriceParam)result.resultData;
                if (kavaPriceParam != null && kavaPriceParam.markets != null && kavaPriceParam.markets.size() > 0) {
                    mTaskCount = mTaskCount + (kavaPriceParam.markets.size()/2);
                    for (ResKavaPriceFeedParam.KavaPriceMarket market:kavaPriceParam.markets) {
                        if (market.market_id.contains(":30")) {
                            new KavaMarketPriceTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain), market.market_id).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                        }
                    }
                }
            }

        } else if (result.taskType == TASK_FETCH_KAVA_TOKEN_PRICE) {
            if (result.isSuccess && result.resultData != null) {
                final MarketPrice price = (MarketPrice)result.resultData;
                getBaseDao().mKavaTokenPrices.put(price.market_id, price);
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
            WLog.w("getBaseDao().mBalances " + getBaseDao().mBalances.size());
            mTaskCount = mTaskCount + 1;
            new StationPriceInfoTask(getBaseApplication(), this, WUtil.marketPrice(mBaseChain, getBaseDao())).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

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

        } else if (result.taskType == TASK_FETCH_STARNAME_FEE) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mStarNameFee = ((ResIovFee.IovFee)result.resultData);
            }

        } else if (result.taskType == TASK_FETCH_STARNAME_CONFIG) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mStarNameConfig = ((ResIovConfig.IovConfig)result.resultData);
            }

        } else if (result.taskType == TASK_FETCH_BAND_ORACLE_STATUS) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mBandOracles = ((ResBandOracleStatus)result.resultData);
            }

        } else if (result.taskType == TASK_FETCH_MINT_PARAM) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mMintParam = ((ResMintParam.MintParam)result.resultData);
            }

        }

        //gRPC callback
        else if (result.taskType == TASK_GRPC_FETCH_NODE_INFO) {
            Types.DefaultNodeInfo tempNodeInfo = (Types.DefaultNodeInfo) result.resultData;
            if (tempNodeInfo != null) { getBaseDao().mGRpcNodeInfo = tempNodeInfo; }

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
                    getBaseDao().mGrpcBalance.add(new Coin(coin.getDenom(), coin.getAmount()));
                }
            } else {
                getBaseDao().mGrpcBalance.add(new Coin(WDp.mainDenom(mBaseChain), "0"));
            }
            WLog.w("getBaseDao().mGrpcBalance " + getBaseDao().mGrpcBalance.size());
            mTaskCount = mTaskCount + 1;
            new StationPriceInfoTask(getBaseApplication(), this, WUtil.marketPrice(mBaseChain, getBaseDao())).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (result.taskType == TASK_GRPC_FETCH_DELEGATIONS) {
            ArrayList<Staking.DelegationResponse> delegations = (ArrayList<Staking.DelegationResponse>) result.resultData;
            if (delegations != null) { getBaseDao().mGrpcDelegations = delegations; }

        } else if (result.taskType == TASK_GRPC_FETCH_UNDELEGATIONS) {
            ArrayList<Staking.UnbondingDelegation> undelegations = (ArrayList<Staking.UnbondingDelegation>) result.resultData;
            if (undelegations != null) { getBaseDao().mGrpcUndelegations = undelegations; }

        } else if (result.taskType == TASK_GRPC_FETCH_ALL_REWARDS) {
            ArrayList<Distribution.DelegationDelegatorReward> rewards = (ArrayList<Distribution.DelegationDelegatorReward>) result.resultData;
            if (rewards != null) { getBaseDao().mGrpcRewards = rewards; }

        } else if (result.taskType == TASK_GRPC_FETCH_STAKING_POOL) {
            getBaseDao().mGrpcStakingPool = (Staking.Pool)result.resultData;

        } else if (result.taskType == TASK_GRPC_FETCH_PARAM_MINT) {
            getBaseDao().mGrpcParamMint = (cosmos.mint.v1beta1.Mint.Params)result.resultData;

        } else if (result.taskType == TASK_GRPC_FETCH_INFLATION) {
            getBaseDao().mGrpcInflation = (BigDecimal)result.resultData;

        } else if (result.taskType == TASK_GRPC_FETCH_PROVISION) {
            getBaseDao().mGrpcProvision = (BigDecimal)result.resultData;

        } else if (result.taskType == TASK_GRPC_FETCH_IRIS_PARAM_MINT) {
            getBaseDao().mGrpcIrisParamMint = (irishub.mint.Mint.Params )result.resultData;

        } else if (result.taskType == TASK_GRPC_FETCH_IRIS_TOKEN_LIST) {
            ArrayList<TokenOuterClass.Token> tokens = (ArrayList<TokenOuterClass.Token>) result.resultData;
            if (tokens != null) { getBaseDao().mGrpcIrisTokens = tokens; }

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
//                WLog.w("mGRpcTopValidators " + getBaseDao().mGRpcTopValidators.size());
//                WLog.w("mGRpcOtherValidators " + getBaseDao().mGRpcOtherValidators.size());
//                WLog.w("mGRpcAllValidators " + getBaseDao().mGRpcAllValidators.size());
//                WLog.w("mGRpcMyValidators " + getBaseDao().mGRpcMyValidators.size());
                if (getBaseDao().mGRpcNodeInfo == null) {
                    Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();
                } else {
                    if (getBaseDao().mGRpcAccount != null && !getBaseDao().mGRpcAccount.getTypeUrl().contains(Auth.BaseAccount.getDescriptor().getFullName())) {
                        if (mBaseChain.equals(PERSIS_MAIN)) {
                            WUtil.onParsePersisVestingAccount(getBaseDao());
                        } else {
                            WUtil.onParseVestingAccount(getBaseDao());
                        }

                    }
//                    WLog.w("getBaseDao().mGRpcNodeInfo " + getBaseDao().mGRpcNodeInfo.getNetwork());
                }

            } else if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
                if (getBaseDao().mNodeInfo == null) {
                    Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();
                } else {
//                    WLog.w("getBaseDao().mNodeInfo " + getBaseDao().mNodeInfo.network);
                }
//                WLog.w("mBnbTokens " + getBaseDao().mBnbTokens.size());
//                WLog.w("mBnbTickers " + getBaseDao().mBnbTickers.size());

            } else if (mBaseChain.equals(OKEX_MAIN) || mBaseChain.equals(OK_TEST)) {
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
                } else {
//                    WLog.w("getBaseDao().mNodeInfo " + getBaseDao().mNodeInfo.network);
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

                WLog.w("mAllValidators " + getBaseDao().mAllValidators.size());
                WLog.w("mMyValidators " + getBaseDao().mMyValidators.size());
                WLog.w("mTopValidators " + getBaseDao().mTopValidators.size());
                WLog.w("mOtherValidators " + getBaseDao().mOtherValidators.size());
                WLog.w("mBalances " + getBaseDao().mBalances.size());
                WLog.w("mMyDelegations " + getBaseDao().mMyDelegations.size());
                WLog.w("mMyUnbondings " + getBaseDao().mMyUnbondings.size());
                WLog.w("mMyRewards " + getBaseDao().mMyRewards.size());

                if (getBaseDao().mNodeInfo == null) {
                    Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();
                } else {
//                    WLog.w("getBaseDao().mNodeInfo " + getBaseDao().mNodeInfo.network);
                }

            }

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

    public void onPriceTic(final BaseChain chain) {
        ApiClient.getCGCClient(getBaseContext()).getPriceTic(WUtil.getCGCId(chain)).enqueue(new Callback<ResCgcTic>() {
            @Override
            public void onResponse(Call<ResCgcTic> call, Response<ResCgcTic> response) {
                if (isFinishing()) return;
                try {
                    getBaseDao().setLastPriceTic(chain, response.body());
                } catch (Exception e) {
                    if (chain.equals(COSMOS_MAIN) || chain.equals(COSMOS_TEST)) {
                        getBaseDao().setLastAtomTic(0d);
                        getBaseDao().setLastAtomUpDown(0d);

                    } else if (chain.equals(IRIS_MAIN) || chain.equals(IRIS_TEST)) {
                        getBaseDao().setLastIrisTic(0d);
                        getBaseDao().setLastIrisUpDown(0d);

                    } else if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
                        getBaseDao().setLastBnbTic(0d);
                        getBaseDao().setLastBnbUpDown(0d);

                    } else if (chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
                        getBaseDao().setLastKavaTic(0d);
                        getBaseDao().setLastKavaUpDown(0d);

                    } else if (chain.equals(BAND_MAIN)) {
                        getBaseDao().setLastBandTic(0d);
                        getBaseDao().setLastBandUpDown(0d);

                    } else if (chain.equals(IOV_MAIN) || chain.equals(IOV_TEST)) {
                        getBaseDao().setLastIovTic(0d);
                        getBaseDao().setLastIovUpDown(0d);

                    } else if (chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST)) {
                        getBaseDao().setLastCertikTic(0d);
                        getBaseDao().setLastCertikUpDown(0d);

                    } else if (chain.equals(AKASH_MAIN)) {
                        getBaseDao().setLastAkashTic(0d);
                        getBaseDao().setLastAkashUpDown(0d);

                    } else if (chain.equals(SECRET_MAIN)) {
                        getBaseDao().setLastSecretTic(0d);
                        getBaseDao().setLastSecretUpDown(0d);

                    } else if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
                        getBaseDao().setLastOKexTic(0d);
                        getBaseDao().setLastOKexUpDown(0d);

                    } else if (chain.equals(SENTINEL_MAIN)) {
                        getBaseDao().setLastSentinelTic(0d);
                        getBaseDao().setLastSentinelUpDown(0d);

                    } else if (chain.equals(PERSIS_MAIN)) {
                        getBaseDao().setLastPersistenceTic(0d);
                        getBaseDao().setLastPersistencelUpDown(0d);

                    } else if (chain.equals(FETCHAI_MAIN)) {
                        getBaseDao().setLastFetchTic(0d);
                        getBaseDao().setLastFetchUpDown(0d);

                    } else if (chain.equals(CRYPTO_MAIN)) {
                        getBaseDao().setLastCryptoTic(0d);
                        getBaseDao().setLastCryptoUpDown(0d);

                    } else if (chain.equals(SIF_MAIN)) {
                        getBaseDao().setLastSifTic(0d);
                        getBaseDao().setLastSifUpDown(0d);

                    } else if (chain.equals(KI_MAIN)) {
                        getBaseDao().setLastSifTic(0d);
                        getBaseDao().setLastSifUpDown(0d);

                    }
                }
            }

            @Override
            public void onFailure(Call<ResCgcTic> call, Throwable t) {
                if (chain.equals(COSMOS_MAIN)) {
                    getBaseDao().setLastAtomTic(0d);
                    getBaseDao().setLastAtomUpDown(0d);

                } else if (chain.equals(IRIS_MAIN)) {
                    getBaseDao().setLastIrisTic(0d);
                    getBaseDao().setLastIrisUpDown(0d);

                } else if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
                    getBaseDao().setLastBnbTic(0d);
                    getBaseDao().setLastBnbUpDown(0d);

                } else if (chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
                    getBaseDao().setLastKavaTic(0d);
                    getBaseDao().setLastKavaUpDown(0d);

                } else if (chain.equals(BAND_MAIN)) {
                    getBaseDao().setLastBandTic(0d);
                    getBaseDao().setLastBandUpDown(0d);

                } else if (chain.equals(IOV_MAIN) || chain.equals(IOV_TEST)) {
                    getBaseDao().setLastIovTic(0d);
                    getBaseDao().setLastIovUpDown(0d);

                } else if (chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST)) {
                    getBaseDao().setLastCertikTic(0d);
                    getBaseDao().setLastCertikUpDown(0d);
                }
            }
        });
    }


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
        Dialog_Push_Enable dialog = new Dialog_Push_Enable();
        dialog.setCancelable(false);
        getSupportFragmentManager().beginTransaction().add(dialog, "wait").commitNowAllowingStateLoss();

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
        Dialog_Buy_Without_Key dialog = Dialog_Buy_Without_Key.newInstance();
        dialog.setCancelable(true);
        getSupportFragmentManager().beginTransaction().add(dialog, "wait").commitNowAllowingStateLoss();
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
