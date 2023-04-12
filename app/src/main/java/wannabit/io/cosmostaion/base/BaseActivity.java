package wannabit.io.cosmostaion.base;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.INJ_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.NEUTRON_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;
import static wannabit.io.cosmostaion.base.BaseConstant.SUPPORT_BEP3_SWAP;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_BNB_FEES;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_BNB_MINI_TICKER;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_BNB_TICKER;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_INCENTIVE_REWARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_MINTSCAN_CW20_ASSETS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_MINTSCAN_ERC20_ASSETS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_NODE_INFO;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_OKEX_ALL_VALIDATORS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_OK_ACCOUNT_BALANCE;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_OK_STAKING_INFO;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_OK_TOKEN_LIST;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_OK_UNBONDING_INFO;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_ALL_REWARDS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_AUTH;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_BALANCE;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_BONDED_VALIDATORS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_DELEGATIONS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_KAVA_PRICES;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_NODE_INFO;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_OSMOSIS_ICNS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_STARGAZE_NS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_STARNAME_CONFIG;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_STARNAME_FEE;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_UNBONDED_VALIDATORS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_UNBONDING_VALIDATORS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_UNDELEGATIONS;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.protobuf2.Any;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;

import cosmos.auth.v1beta1.Auth;
import cosmos.base.v1beta1.CoinOuterClass;
import cosmos.distribution.v1beta1.Distribution;
import cosmos.staking.v1beta1.Staking;
import kava.pricefeed.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.AppLockActivity;
import wannabit.io.cosmostaion.activities.IntroActivity;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.activities.PasswordSetActivity;
import wannabit.io.cosmostaion.activities.TxDetailgRPCActivity;
import wannabit.io.cosmostaion.activities.setting.MnemonicRestoreActivity;
import wannabit.io.cosmostaion.activities.txs.common.SendActivity;
import wannabit.io.cosmostaion.activities.txs.kava.HtlcSendActivity;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BnbTicker;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.dao.MWords;
import wannabit.io.cosmostaion.dao.MintscanToken;
import wannabit.io.cosmostaion.dao.NameService;
import wannabit.io.cosmostaion.dao.Price;
import wannabit.io.cosmostaion.dialog.AccountShowDialog;
import wannabit.io.cosmostaion.dialog.CommonAlertDialog;
import wannabit.io.cosmostaion.dialog.FilledVerticalButtonAlertDialog;
import wannabit.io.cosmostaion.dialog.WaitDialog;
import wannabit.io.cosmostaion.model.BondingInfo;
import wannabit.io.cosmostaion.model.NodeInfo;
import wannabit.io.cosmostaion.model.UnbondingInfo;
import wannabit.io.cosmostaion.model.kava.IncentiveReward;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.res.ResBnbFee;
import wannabit.io.cosmostaion.network.res.ResOkStaking;
import wannabit.io.cosmostaion.network.res.ResOkTokenList;
import wannabit.io.cosmostaion.network.res.ResOkUnbonding;
import wannabit.io.cosmostaion.task.FetchTask.AccountInfoTask;
import wannabit.io.cosmostaion.task.FetchTask.BnbMiniTickerTask;
import wannabit.io.cosmostaion.task.FetchTask.BnbMiniTokenListTask;
import wannabit.io.cosmostaion.task.FetchTask.BnbTickerTask;
import wannabit.io.cosmostaion.task.FetchTask.BnbTokenListTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaIncentiveRewardTask;
import wannabit.io.cosmostaion.task.FetchTask.MintScanAssetsTask;
import wannabit.io.cosmostaion.task.FetchTask.MintScanCw20AssetsTask;
import wannabit.io.cosmostaion.task.FetchTask.MintScanPriceTask;
import wannabit.io.cosmostaion.task.FetchTask.MintScanUtilityParamTask;
import wannabit.io.cosmostaion.task.FetchTask.MintscanErc20AssetsTask;
import wannabit.io.cosmostaion.task.FetchTask.MoonPayTask;
import wannabit.io.cosmostaion.task.FetchTask.NodeInfoTask;
import wannabit.io.cosmostaion.task.FetchTask.OkAccountBalanceTask;
import wannabit.io.cosmostaion.task.FetchTask.OkStakingInfoTask;
import wannabit.io.cosmostaion.task.FetchTask.OkTokenListTask;
import wannabit.io.cosmostaion.task.FetchTask.OkUnbondingInfoTask;
import wannabit.io.cosmostaion.task.FetchTask.ValidatorInfoAllTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.AllRewardGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.AuthGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.BalanceGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.BondedValidatorsGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.Cw20BalanceGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.DelegationsGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.Erc20BalanceGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.KavaMarketPriceGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.NodeInfoGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.OsmosisCheckIcnsGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.StarNameGrpcConfigTask;
import wannabit.io.cosmostaion.task.gRpcTask.StarNameGrpcFeeTask;
import wannabit.io.cosmostaion.task.gRpcTask.StargazeCheckNSGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.UnBondedValidatorsGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.UnBondingValidatorsGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.UnDelegationsGrpcTask;
import wannabit.io.cosmostaion.utils.FetchCallBack;
import wannabit.io.cosmostaion.utils.LanguageUtil;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WUtil;

public class BaseActivity extends AppCompatActivity implements TaskListener {

    protected BaseApplication mApplication;
    protected BaseData mData;
    protected WaitDialog mDialogWait;
    protected boolean mNeedLeaveTime = true;

    public View mRootview;
    public Account mAccount;
    public BaseChain mBaseChain;
    public ChainConfig mChainConfig;
    public ArrayList<NameService> mNameServices = new ArrayList<>();

    protected int mTaskCount;
    private FetchCallBack mFetchCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootview = findViewById(android.R.id.content);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!(this instanceof PasswordSetActivity) && !(this instanceof PasswordCheckActivity) && !(this instanceof IntroActivity)) {
            if (getBaseApplication().needShowLockScreen()) {
                Intent intent = new Intent(BaseActivity.this, AppLockActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LanguageUtil.updateResources(newBase));
    }

    public BaseApplication getBaseApplication() {
        if (mApplication == null) mApplication = (BaseApplication) getApplication();
        return mApplication;
    }

    public BaseData getBaseDao() {
        if (mData == null) mData = getBaseApplication().getBaseDao();
        return mData;
    }

    public void onShowWaitDialog() {
        if (mDialogWait == null) {
            mDialogWait = new WaitDialog();
        }
        if (getSupportFragmentManager().findFragmentByTag("wait") != null && getSupportFragmentManager().findFragmentByTag("wait").isAdded()) {
            return;
        }
        mDialogWait.setCancelable(false);
        mDialogWait.show(getSupportFragmentManager(), "wait");
    }

    public void onHideWaitDialog() {
        if (mDialogWait != null && mDialogWait.isVisible()) {
            mDialogWait.dismissAllowingStateLoss();
        }
    }

    @Override
    protected void onDestroy() {
        onHideWaitDialog();
        super.onDestroy();
    }

    public void onInsertKeyDialog() {
        CommonAlertDialog.showDoubleButton(this, getString(R.string.str_only_observe_title), getString(R.string.str_only_observe_msg), getString(R.string.str_add_mnemonics), view -> onAddMnemonicForAccount(), getString(R.string.str_close), null);
    }

    public void onAddMnemonicForAccount() {
        startActivity(new Intent(BaseActivity.this, MnemonicRestoreActivity.class));
    }

    public void setAccountKeyStatus(Context c, Account account, ChainConfig chainConfig, ImageView keyState) {
        if (account.hasPrivateKey) {
            keyState.setImageResource(R.drawable.key_off);
            keyState.setColorFilter(ContextCompat.getColor(c, chainConfig.chainColor()), android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            if (account.isLedger()) {
                keyState.setImageResource(R.drawable.icon_ledger_wallet);
                keyState.setColorFilter(ContextCompat.getColor(c, chainConfig.chainColor()), android.graphics.PorterDuff.Mode.SRC_IN);
            } else {
                keyState.setImageResource(R.drawable.watchmode);
                keyState.setColorFilter(null);
            }
        }
    }

    public void setEthAddress(ChainConfig chainConfig, TextView ethTxt) {
        if (chainConfig.evmSupport()) {
            ethTxt.setVisibility(View.VISIBLE);
            try {
                ethTxt.setText("(" + WKey.convertBech32ToEvm(mAccount.address) + ")");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ethTxt.setVisibility(View.GONE);
        }
    }

    public void onClickQrCopy(ChainConfig chainConfig, Account account) {
        String nickName;
        if (TextUtils.isEmpty(account.nickName))
            nickName = getString(R.string.str_my_wallet) + account.id;
        else nickName = account.nickName;

        if (chainConfig.evmSupport()) {
            try {
                String ethAddress = WKey.convertBech32ToEvm(account.address);
                CommonAlertDialog.showDoubleButton(this, getString(R.string.str_address_type), "", getString(R.string.str_tender_type), view -> onClickShowAccountDialog(account.address, nickName), getString(R.string.str_eth_type), view -> onClickShowAccountDialog(ethAddress, nickName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            onClickShowAccountDialog(account.address, nickName);
        }
    }

    private void onClickShowAccountDialog(String address, String nickName) {
        Bundle bundle = new Bundle();
        bundle.putString("title", nickName);
        bundle.putString("address", address);
        if (!this.isFinishing()) {
            AccountShowDialog dialog = AccountShowDialog.newInstance(bundle);
            dialog.show(getSupportFragmentManager(), "dialog");
        }
    }

    public void onHideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
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
        if (!mAccount.hasPrivateKey && !mAccount.isLedger()) {
            onInsertKeyDialog();
            return;
        }
        if (!WDp.isTxFeePayable(this, getBaseDao(), mChainConfig)) {
            Toast.makeText(this, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
            return;
        }

        if (isGRPC(mBaseChain)) {
            BigDecimal mainAvailable = getBaseDao().getAvailable(mChainConfig.mainDenom());
            if (BigDecimal.ZERO.compareTo(mainAvailable) >= 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(getBaseContext(), SendActivity.class);
            intent.putExtra("sendTokenDenom", mChainConfig.mainDenom());
            startActivity(intent);

        } else {
            BigDecimal mainAvailable = getBaseDao().availableAmount(mChainConfig.mainDenom());
            if (BigDecimal.ZERO.compareTo(mainAvailable) >= 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(getBaseContext(), SendActivity.class);
            intent.putExtra("sendTokenDenom", mChainConfig.mainDenom());
            startActivity(intent);
        }

    }

    public void onStartHTLCSendActivity(String sendDenom) {
        if (mAccount == null) return;
        if (!SUPPORT_BEP3_SWAP) {
            Toast.makeText(getBaseContext(), R.string.error_bep3_swap_temporary_disable, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!mAccount.hasPrivateKey) {
            onInsertKeyDialog();
            return;
        }
        if (!WDp.isTxFeePayable(this, getBaseDao(), mChainConfig)) {
            Toast.makeText(this, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(getBaseContext(), HtlcSendActivity.class);
        intent.putExtra("toSwapDenom", sendDenom);
        startActivity(intent);
    }

    public void onChoiceStarnameResourceAddress(String address) {
    }

    public void onShare(boolean isText, String address) {
        if (isText) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, address);
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent, "send"));

        } else {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            try {
                final Bitmap mBitmap = WUtil.toBitmap(qrCodeWriter.encode(address, BarcodeFormat.QR_CODE, 480, 480));
                new TedPermission(this).setPermissionListener(new PermissionListener() {
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
                }).setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE).setRationaleMessage(getString(R.string.str_permission_qr)).check();

            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
    }

    public void onShareType(String address) {
        FilledVerticalButtonAlertDialog.showDoubleButton(this, null, null, getString(R.string.str_with_qr), view -> onShare(false, address), null, getString(R.string.str_with_text), view -> onShare(true, address), null);
    }

    public void onDeleteAccount(Account account) {
        try {
            CryptoHelper.deleteKey(getString(R.string.key_mnemonic) + getBaseDao().onSelectAccount("" + account.id).uuid);
        } catch (Exception e) {
        }
        try {
            CryptoHelper.deleteKey(getString(R.string.key_private) + getBaseDao().onSelectAccount("" + account.id).uuid);
        } catch (Exception e) {
        }
        getBaseDao().onDeleteAccount(account);
    }

    public void onDeleteAccountExternal(Account account) {
        onDeleteAccount(account);

        if (getBaseDao().onSelectAccounts().size() > 0) {
            if (mAccount.id.equals(account.id)) {
                getBaseDao().setLastUser(getBaseDao().onSelectAccounts().get(0).id);
            } else {
                getBaseDao().setLastUser(mAccount.id);
            }
            onStartMainActivity(0);

        } else {
            getBaseDao().setLastUser(-1);
            Intent intent = new Intent(this, IntroActivity.class);
            intent.setFlags(FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        for (BaseChain baseChain : getBaseDao().dpSortedChains()) {
            int accountNum = getBaseDao().onSelectAccountsByChain(baseChain).size();
            if (accountNum > 0) {
                getBaseDao().setLastUser(getBaseDao().onSelectAccountsByChain(baseChain).get(0).id);
                break;
            }
        }
    }

    public void onDeleteMnemonic(MWords mWords) {
        ArrayList<Account> linkedAccounts = getBaseDao().onSelectAccountsByMnemonic(mWords.id);
        for (Account account : linkedAccounts) {
            onDeleteAccount(account);
        }
        getBaseDao().onDeleteMnemonic(mWords);

        if (getBaseDao().onSelectAccounts().size() > 0) {
            Account lastAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
            if (lastAccount != null) {
                getBaseDao().setLastUser(lastAccount.id);
            } else {
                getBaseDao().setLastUser(getBaseDao().onSelectAccounts().get(0).id);
            }
            onStartMainActivity(0);

        } else {
            getBaseDao().setLastUser(-1);
            Intent intent = new Intent(this, IntroActivity.class);
            intent.setFlags(FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    public void onCommonIntentTx(Context context, TaskResult result) {
        Intent txIntent = new Intent(context, TxDetailgRPCActivity.class);
        txIntent.putExtra("isGen", true);
        txIntent.putExtra("isSuccess", result.isSuccess);
        txIntent.putExtra("errorCode", result.errorCode);
        txIntent.putExtra("errorMsg", result.errorMsg);
        String hash = String.valueOf(result.resultData);
        if (!TextUtils.isEmpty(hash)) txIntent.putExtra("txHash", hash);
        startActivity(txIntent);
    }

    public void onFetchAccountInfo(FetchCallBack callback) {
        if (mTaskCount > 0) {
            callback.fetchBusy();
        }
        mFetchCallback = callback;

        getBaseDao().mParam = null;
        getBaseDao().mAssets.clear();
        getBaseDao().mCw20Tokens.clear();
        getBaseDao().mCw20MyTokens.clear();
        getBaseDao().mErc20Tokens.clear();
        getBaseDao().mErc20MyTokens.clear();

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
        mNameServices.clear();


        if (mBaseChain.equals(BNB_MAIN)) {
            mTaskCount = 6;
            new NodeInfoTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AccountInfoTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BnbTokenListTask(getBaseApplication(), this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BnbMiniTokenListTask(getBaseApplication(), this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BnbTickerTask(getBaseApplication(), this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BnbMiniTickerTask(getBaseApplication(), this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (mBaseChain.equals(BaseChain.OKEX_MAIN)) {
            mTaskCount = 7;
            getBaseDao().mOkStaking = null;
            getBaseDao().mOkUnbonding = null;
            getBaseDao().mOkTokenList = null;
            new NodeInfoTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ValidatorInfoAllTask(getBaseApplication(), this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new AccountInfoTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new OkAccountBalanceTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new OkTokenListTask(getBaseApplication(), this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new OkStakingInfoTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new OkUnbondingInfoTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        }

        // grpc
        if (mBaseChain.equals(IOV_MAIN)) {
            mTaskCount = 11;
            new NodeInfoGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AuthGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BondedValidatorsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondedValidatorsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondingValidatorsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new BalanceGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new DelegationsGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnDelegationsGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AllRewardGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new StarNameGrpcFeeTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new StarNameGrpcConfigTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (mBaseChain.equals(KAVA_MAIN)) {
            mTaskCount = 11;
            new NodeInfoGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AuthGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BondedValidatorsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondedValidatorsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondingValidatorsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new BalanceGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new DelegationsGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnDelegationsGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AllRewardGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new KavaMarketPriceGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new KavaIncentiveRewardTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (mBaseChain.equals(NEUTRON_TEST)) {
            mTaskCount = 3;
            new NodeInfoGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AuthGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BalanceGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (isGRPC(mBaseChain)) {
            mTaskCount = 9;
            new NodeInfoGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AuthGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BondedValidatorsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondedValidatorsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondingValidatorsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new BalanceGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new DelegationsGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnDelegationsGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new AllRewardGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (isFinishing()) return;
        if (result.taskType == BaseConstant.TASK_FETCH_MINTSCAN_PRICES) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mPrices.clear();
                for (Price price : (ArrayList<Price>) result.resultData) {
                    getBaseDao().mPrices.add(price);
                }
                getBaseDao().setLastPriceTime();
            }
        }

        mTaskCount--;
        if (result.taskType == BaseConstant.TASK_FETCH_ACCOUNT) {
            mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
            getBaseDao().mBalances = getBaseDao().onSelectBalance(mAccount.id);

        } else if (result.taskType == TASK_FETCH_NODE_INFO) {
            getBaseDao().mNodeInfo = (NodeInfo) result.resultData;
            if (getBaseDao().mNodeInfo != null) {
                mTaskCount = mTaskCount + 1;
                new MintscanErc20AssetsTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }

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
                getBaseDao().mBalances = getBaseDao().onSelectBalance(mAccount.id);
            }

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

        }


        //gRPC callback
        else if (result.taskType == TASK_GRPC_FETCH_NODE_INFO) {
            tendermint.p2p.Types.NodeInfo tempNodeInfo = (tendermint.p2p.Types.NodeInfo) result.resultData;
            if (tempNodeInfo != null) {
                getBaseDao().mGRpcNodeInfo = tempNodeInfo;
                mTaskCount = mTaskCount + 5;
                new MintScanAssetsTask(getBaseApplication(), this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                new MintScanCw20AssetsTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                new MintscanErc20AssetsTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                new MintScanUtilityParamTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                new OsmosisCheckIcnsGrpcTask(getBaseApplication(), this, mChainConfig, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//                new StargazeCheckNSGrpcTask(getBaseApplication(), this, mChainConfig, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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
                        if (coin.getDenom().equalsIgnoreCase(mChainConfig.mainDenom())) {
                            getBaseDao().mGrpcBalance.add(new Coin(coin.getDenom(), coin.getAmount()));
                        }
                    }
                }
            }
            if (getBaseDao().mGrpcBalance.size() <= 0 || getBaseDao().getAvailable(mChainConfig.mainDenom()).compareTo(BigDecimal.ZERO) <= 0) {
                getBaseDao().mGrpcBalance.add(new Coin(mChainConfig.mainDenom(), "0"));
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
                getBaseDao().mCw20Tokens = (ArrayList<MintscanToken>) result.resultData;
                if (getBaseDao().mCw20Tokens != null && getBaseDao().mCw20Tokens.size() > 0) {
                    getBaseDao().setMyTokens(mChainConfig, mAccount.address);
                    for (MintscanToken asset : getBaseDao().mCw20MyTokens) {
                        mTaskCount = mTaskCount + 1;
                        new Cw20BalanceGrpcTask(getBaseApplication(), this, mBaseChain, mAccount, asset.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    }
                }
            }

        } else if (result.taskType == TASK_FETCH_MINTSCAN_ERC20_ASSETS) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mErc20Tokens = (ArrayList<MintscanToken>) result.resultData;
                if (getBaseDao().mErc20Tokens != null && getBaseDao().mErc20Tokens.size() > 0) {
                    getBaseDao().setMyTokens(mChainConfig, mAccount.address);
                    String url = mChainConfig.rpcUrl();
                    if (!url.isEmpty()) {
                        Web3j web3 = Web3j.build(new HttpService(url));
                        for (MintscanToken asset : getBaseDao().mErc20MyTokens) {
                            mTaskCount = mTaskCount + 1;
                            new Erc20BalanceGrpcTask(getBaseApplication(), this, mAccount, web3, asset.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                        }
                    }
                }
            }

        } else if (result.taskType == TASK_GRPC_FETCH_OSMOSIS_ICNS) {
            if (result.isSuccess && result.resultData != null) {
                String icnsName = (String) result.resultData;
                if (!TextUtils.isEmpty(icnsName)) {
                    mNameServices.add(new NameService(NameService.NameServiceType.ICNS, icnsName, mAccount.address));
                }
            }

        } else if (result.taskType == TASK_GRPC_FETCH_STARGAZE_NS) {
            if (result.isSuccess && result.resultData != null) {
                String icnsName = (String) result.resultData;
                if (!TextUtils.isEmpty(icnsName)) {
                    mNameServices.add(new NameService(NameService.NameServiceType.STARGAZE, icnsName, mAccount.address));
                }
            }
        }

        if (mTaskCount == 0) {
            if (isGRPC(mBaseChain)) {
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
                        WUtil.onParseVestingAccount(getBaseDao());
                    }
                    ArrayList<Balance> snapBalance = new ArrayList<>();
                    for (Coin coin : getBaseDao().mGrpcBalance) {
                        snapBalance.add(new Balance(mAccount.id, coin.denom, coin.amount, Calendar.getInstance().getTime().getTime(), "0", "0"));
                    }
                    getBaseDao().onUpdateBalances(mAccount.id, snapBalance);
                }

            } else if (mBaseChain.equals(BNB_MAIN)) {
                if (getBaseDao().mNodeInfo == null) {
                    Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();
                }

            } else if (mBaseChain.equals(OKEX_MAIN)) {
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
                    Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();
                }

            }
            new MintScanPriceTask(getBaseApplication(), this, getBaseDao().getCurrencyString()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            //callback with delay fix gRPC  timming issue
            mHandler.postDelayed(() -> {
                if (mFetchCallback != null) {
                    mFetchCallback.fetchFinished();
                }
            }, 300);
        }
    }

    private Handler mHandler = new Handler(Looper.getMainLooper());

    public void onShowBuyWarnNoKey() {
        CommonAlertDialog.showDoubleButton(this, getString(R.string.str_only_observe_title), getString(R.string.str_buy_without_key_msg), getString(R.string.str_continue), view -> onShowCryptoPay(), getString(R.string.str_cancel), null);
    }

    public void onShowCryptoPay() {
        if (mChainConfig.moonPaySupport() && mChainConfig.kadoMoneySupport()) {
            FilledVerticalButtonAlertDialog.showDoubleButton(this, "", "", getString(R.string.str_moonPay), view -> onStartMoonPaySignature(), null, getString(R.string.str_kadoMoney), view -> onShowBuyKado(), null);
        } else {
            if (mChainConfig.moonPaySupport()) {
                onStartMoonPaySignature();
            } else if (mChainConfig.kadoMoneySupport()) {
                onShowBuyKado();
            }
        }
    }

    public void onShowBuyKado() {
        String query = "?apiKey=" + getString(R.string.kado_money_public_key) + "&network=" + mChainConfig.chainName() + "&networkList=" + mChainConfig.chainName() + "&onToAddress=" + mAccount.address;
        if (mChainConfig.baseChain().equals(INJ_MAIN)) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url_kado_money) + query + "&onRevCurrency=" + "USDT"));
            startActivity(intent);
        } else if(mChainConfig.baseChain().equals(COSMOS_MAIN)) {
            String cosmosQuery = "?apiKey=" + getString(R.string.kado_money_public_key) + "&network=" + "cosmos hub" + "&networkList=" + "cosmos hub" + "&onToAddress=" + mAccount.address;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url_kado_money) + cosmosQuery + "&onRevCurrency=" + "ATOM"));
            startActivity(intent);
        } else {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url_kado_money) + query));
            startActivity(intent);
        }
    }

    public void onStartMoonPaySignature() {
        String query = "?apiKey=" + getString(R.string.moon_pay_public_key);
        if (mChainConfig.moonPaySupport()) {
            query = query + "&currencyCode=" + mChainConfig.mainSymbol().toLowerCase();
        }
        query = query + "&walletAddress=" + mAccount.address;
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
