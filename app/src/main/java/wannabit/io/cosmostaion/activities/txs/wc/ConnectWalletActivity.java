package wannabit.io.cosmostaion.activities.txs.wc;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.trustwallet.walletconnect.WCClient;
import com.trustwallet.walletconnect.models.WCAccount;
import com.trustwallet.walletconnect.models.WCPeerMeta;
import com.trustwallet.walletconnect.models.cosmostation.WCCosmostationAccount;
import com.trustwallet.walletconnect.models.ethereum.WCEthereumSignMessage;
import com.trustwallet.walletconnect.models.ethereum.WCEthereumTransaction;
import com.trustwallet.walletconnect.models.keplr.WCKeplrWallet;
import com.trustwallet.walletconnect.models.session.WCSession;

import org.apache.commons.lang3.StringUtils;
import org.bitcoinj.core.ECKey;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.Sign;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthEstimateGas;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import cosmos.tx.v1beta1.TxOuterClass;
import okhttp3.OkHttpClient;
import wannabit.io.cosmostaion.BuildConfig;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.cosmos.MsgGenerator;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.CommonAlertDialog;
import wannabit.io.cosmostaion.dialog.Dialog_Wc_Account;
import wannabit.io.cosmostaion.dialog.Dialog_Wc_Raw_Data;
import wannabit.io.cosmostaion.dialog.Dialog_Wc_Raw_Data_Evmos;
import wannabit.io.cosmostaion.model.StdSignMsg;
import wannabit.io.cosmostaion.model.WcSignDirectModel;
import wannabit.io.cosmostaion.model.WcSignModel;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.network.req.ReqBroadCast;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.utils.WalletConnectManager;

public class ConnectWalletActivity extends BaseActivity {
    public final static int TYPE_TRUST_WALLET = 1;
    public final static int TYPE_COSMOS_WALLET = 2;
    public final static int TYPE_ETH_SIGN_MESSAGE = 3;
    public final static int TYPE_ETH_SIGN_TRANSACTION = 4;
    public final static int TYPE_COSMOS_SIGN_DIRECT = 5;

    public static final String WC_URL_SCHEME_HOST_WC = "wc";
    public static final String WC_URL_SCHEME_HOST_DAPP = "dapp";
    public static final String WC_URL_SCHEME_HOST_DAPP_INTERNAL = "internaldapp";
    public static final String WC_URL_SCHEME_COSMOSTATION = "cosmostation";
    public static final String INTENT_KEY_WC_URL = "wcUrl";
    public static final String INTENT_KEY_DAPP_URL = "dappUrl";

    private RelativeLayout mWcLayer, mLoadingLayer, mDappLayout;
    private LinearLayout mEmptyLayer;
    private CardView mWcCardView;
    private Button mBtnDisconnect;
    private WebView mWebView;
    private TextView mTitleText, mConnectText, mDappUrl, mWcName, mWcUrl, mWcAccount;
    private ImageView mConnectImage, mDappClose, mWcImg;
    private AppBarLayout appBarLayout;

    private String mWcURL;
    private WCClient wcClient;
    private WCSession wcSession;
    private WCPeerMeta mWcPeerMeta;
    private Boolean isDeepLink = false;
    private Boolean isDapp = false;
    private WCEthereumTransaction mWcEthereumTransaction;
    private WCEthereumSignMessage mSignMessage;
    private final Map<String, Account> chainAccountMap = Maps.newHashMap();
    private Boolean isHideToolbar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_wallet);
        initView();
        initWebView();
        settingViewFromIntent();
        settingToolbar();
    }

    private void settingViewFromIntent() {
        if (fromScheme(getIntent())) {
            if (WC_URL_SCHEME_HOST_WC.equals(getIntent().getData().getHost())) {
                isDeepLink = true;
                settingForWc(getIntent().getData().getQuery());
            } else if (WC_URL_SCHEME_HOST_DAPP.equals(getIntent().getData().getHost())) {
                settingForDappExternalScheme(getIntent().getData().getQuery());
            } else if (WC_URL_SCHEME_HOST_DAPP_INTERNAL.equals(getIntent().getData().getHost())) {
                settingForDappInternalScheme(getIntent().getData().getQuery());
            }
        } else if (getIntent().hasExtra(INTENT_KEY_WC_URL)) {
            settingForWc(getIntent().getStringExtra(INTENT_KEY_WC_URL));
        } else if (getIntent().hasExtra(INTENT_KEY_DAPP_URL)) {
            settingForDappExternalScheme(getIntent().getStringExtra(INTENT_KEY_DAPP_URL));
        }
    }

    private void settingForDappInternalScheme(String url) {
        loadBaseAccount();
        isDapp = true;
        mWebView.setVisibility(View.VISIBLE);
        mWebView.loadUrl(url);
        changeDappConnectStatus(false);
        mDappLayout.setVisibility(View.VISIBLE);
        mDappUrl.setText(url);

        mWcLayer.setVisibility(View.GONE);
        mLoadingLayer.setVisibility(View.GONE);
        mBtnDisconnect.setVisibility(View.GONE);
    }

    private void settingForWc(String url) {
        mWcURL = url;
        if (isDeepLink) {
            checkPassword();
        } else {
            loadBaseAccount();
            initWalletConnect();
        }
        mWebView.setVisibility(View.GONE);
        mDappLayout.setVisibility(View.GONE);
        mBtnDisconnect.setVisibility(View.VISIBLE);
    }

    private void settingForWcDefaultScheme(String url) {
        if (wcSession != null && wcClient.getSession() != null && wcClient != null && wcClient.isConnected()) {
            return;
        }

        mLoadingLayer.setVisibility(View.VISIBLE);
        mWcURL = url;
        isDapp = true;
        loadBaseAccount();
        initWalletConnect();
    }


    private void settingForDappExternalScheme(String url) {
        isDapp = true;
        mWebView.setVisibility(View.VISIBLE);
        mWebView.loadUrl(url);
        changeDappConnectStatus(false);
        mDappLayout.setVisibility(View.VISIBLE);
        mDappUrl.setText(url);

        mWcLayer.setVisibility(View.GONE);
        mLoadingLayer.setVisibility(View.GONE);
        mBtnDisconnect.setVisibility(View.GONE);
    }

    private void changeDappConnectStatus(Boolean connected) {
        if (connected) {
            mConnectImage.setImageResource(R.drawable.ic_passed_img);
            mConnectText.setText(R.string.str_wc_dapp_connected);
            mConnectText.setTextColor(getColor(R.color.colorBlackDayNight));
        } else {
            mConnectImage.setImageResource(R.drawable.ic_pass_gr);
            mConnectText.setText(R.string.str_wc_dapp_not_connected);
            mConnectText.setTextColor(getColor(R.color.colorGray5));
        }
    }

    private void checkPassword() {
        if (!getBaseDao().onHasPassword()) {
            mEmptyLayer.setVisibility(View.VISIBLE);
            mLoadingLayer.setVisibility(View.GONE);
            mBtnDisconnect.setText(R.string.str_dismiss);
        } else {
            Intent intent = new Intent(this, PasswordCheckActivity.class);
            connectWalletResultLauncher.launch(intent);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
        }
    }

    private void settingToolbar() {
        setSupportActionBar(findViewById(R.id.tool_bar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(!isDapp);
        mTitleText.setVisibility(isDapp ? View.GONE : View.VISIBLE);
    }

    private boolean fromScheme(Intent intent) {
        return intent.getData() != null && WC_URL_SCHEME_COSMOSTATION.equals(intent.getData().getScheme());
    }

    private void loadBaseAccount() {
        Account currentAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        chainAccountMap.put(currentAccount.baseChain, currentAccount);
        mBaseChain = BaseChain.getChain(currentAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);
        mWcCardView.setCardBackgroundColor(ContextCompat.getColor(this, mChainConfig.chainBgColor()));
    }

    private void initView() {
        mWcLayer = findViewById(R.id.wc_layer);
        mLoadingLayer = findViewById(R.id.loading_layer);
        mEmptyLayer = findViewById(R.id.empty_account);
        mWcCardView = findViewById(R.id.wc_card);
        mTitleText = findViewById(R.id.toolbar_title);
        mWcImg = findViewById(R.id.wc_img);
        mWcName = findViewById(R.id.wc_name);
        mWcUrl = findViewById(R.id.wc_url);
        mWcAccount = findViewById(R.id.wc_address);
        mDappUrl = findViewById(R.id.wc_peer);
        appBarLayout = findViewById(R.id.app_bar_layout);
        mConnectImage = findViewById(R.id.wc_light);
        mDappLayout = findViewById(R.id.dapp_layout);
        mConnectText = findViewById(R.id.wc_state);
        mDappClose = findViewById(R.id.dapp_close);
        mDappClose.setOnClickListener(view -> {
            finish();
        });
        mBtnDisconnect = findViewById(R.id.btn_disconnect);
        mBtnDisconnect.setOnClickListener(view -> {
            moveToBackIfNeed();
            finish();
        });
    }

    private void initWebView() {
        mWebView = findViewById(R.id.wc_webview);
        WebStorage.getInstance().deleteAllData();
        if (BuildConfig.DEBUG) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        mWebView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (oldScrollY > scrollY && Math.abs(scrollY - oldScrollY) > 20 && isHideToolbar) {
                isHideToolbar = false;
                getSupportActionBar().show();
            } else if (oldScrollY < scrollY && Math.abs(scrollY - oldScrollY) > 20 && !isHideToolbar) {
                isHideToolbar = true;
                getSupportActionBar().hide();
            }
        });
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setUserAgentString(mWebView.getSettings().getUserAgentString() + " Cosmostation/APP/Android/" + BuildConfig.VERSION_NAME);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                new AlertDialog.Builder(view.getContext(), R.style.DialogTheme)
                        .setMessage(message)
                        .setPositiveButton("OK", (DialogInterface dialog, int which) -> result.confirm())
                        .setOnDismissListener((DialogInterface dialog) -> result.confirm())
                        .create()
                        .show();
                return true;
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                new AlertDialog.Builder(view.getContext(), R.style.DialogTheme)
                        .setMessage(message)
                        .setPositiveButton("OK", (DialogInterface dialog, int which) -> result.confirm())
                        .setNegativeButton("Cancel", (DialogInterface dialog, int which) -> result.cancel())
                        .setOnDismissListener((DialogInterface dialog) -> result.cancel())
                        .create()
                        .show();
                return true;
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (isFinishing()) {
                    return true;
                }

                if (url.startsWith("wc:")) {
                    settingForWcDefaultScheme(url);
                    return true;
                } else if (url.startsWith("intent:")) {
                    if (url.contains("intent://wcV1")) {
                        url = url.replace("#Intent;package=com.chainapsis.keplr;scheme=keplrwallet;end;", "#Intent;package=wannabit.io.cosmostaion;scheme=cosmostation;end;");
                        url = url.replace("intent://wcV1", "intent://wc");
                        url = url.replace("scheme=keplrwallet", "scheme=cosmostation");
                    }
                    if (BuildConfig.DEBUG) {
                        url = url.replace("wannabit.io.cosmostaion", "wannabit.io.cosmostaion.debug");
                    }
                    try {
                        Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                        Intent existPackage = getPackageManager().getLaunchIntentForPackage(intent.getPackage());
                        if (existPackage != null) {
                            startActivity(intent);
                        } else {
                            Intent marketIntent = new Intent(Intent.ACTION_VIEW);
                            marketIntent.setData(Uri.parse("market://details?id=" + intent.getPackage()));
                            startActivity(marketIntent);
                        }
                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                return false;
            }
        });
    }

    private void initWalletConnect() {
        OkHttpClient client = new OkHttpClient.Builder().pingInterval(100000, TimeUnit.MILLISECONDS).build();
        wcClient = new WCClient(new GsonBuilder(), client);
        WCPeerMeta meta = new WCPeerMeta(getString(R.string.str_wc_peer_name), getString(R.string.str_wc_peer_url), getString(R.string.str_wc_peer_desc), Lists.newArrayList());
        if (!mWcURL.isEmpty()) {
            wcSession = WCSession.Companion.from(mWcURL);
            wcClient.connect(wcSession, meta, UUID.randomUUID().toString(), null);
        }
        wcClient.setOnGetAccounts(id -> {
            wcClient.approveRequest(id, makeWCAccount());
            return null;
        });
        wcClient.setOnDisconnect((code, reason) -> {
            runOnUiThread(() -> {
                Toast.makeText(getBaseContext(), getString(R.string.str_wc_not_connected), Toast.LENGTH_SHORT).show();
                if (!isFinishing() && !isDapp) finish();
                if (isDapp) changeDappConnectStatus(false);
            });
            return null;
        });
        wcClient.setOnSessionRequest((id, wcPeerMeta) -> {
            runOnUiThread(() -> {
                String url = wcPeerMeta.getUrl();
                if (isDapp) {
                    url = Uri.parse(mWebView.getUrl()).getHost();
                }
                if (WalletConnectManager.getWhiteList(this).contains(url)) {
                    processSessionRequest(wcPeerMeta);
                } else {
                    String finalUrl = url;
                    CommonAlertDialog.showDoubleButton(ConnectWalletActivity.this,
                            getString(R.string.str_wc_connect_alert_title),
                            Html.fromHtml(String.format("%s<br/><b>%s</b><br/><br/><font color=\"#ff2745\">%s</font>", getString(R.string.str_wc_connect_alert_message), url, getString(R.string.str_wc_connect_alert_guide)), Html.FROM_HTML_MODE_COMPACT),
                            getString(R.string.str_cancel),
                            view -> {
                                mLoadingLayer.postDelayed(() -> mLoadingLayer.setVisibility(View.GONE), 1000);
                                wcClient.rejectSession(getString(R.string.str_cancel));
                                if (!isFinishing() && !isDapp) finish();
                                if (isDapp) changeDappConnectStatus(false);
                            },
                            getString(R.string.str_ok),
                            view -> {
                                WalletConnectManager.addWhiteList(this, finalUrl);
                                processSessionRequest(wcPeerMeta);
                            });
                }
            });
            return null;
        });
        wcClient.setOnSignTransaction((id, wcSignTransaction) -> {
            runOnUiThread(() -> onShowSignDialog(makeSignBundle(TYPE_TRUST_WALLET, id, wcSignTransaction.getTransaction())));
            return null;
        });
        wcClient.setOnKeplrEnable((id, strings) -> {
            runOnUiThread(() -> onKeplrEnable(id, strings));
            return null;
        });
        wcClient.setOnEthSign((id, signMessage) -> {
            mSignMessage = signMessage;
            runOnUiThread(() -> onShowEvmosSignDialog(makeEvmosSignBundle(TYPE_ETH_SIGN_MESSAGE, id, null, signMessage)));
            return null;
        });
        wcClient.setOnEthSendTransaction((id, wcEthereumTransaction) -> {
            mWcEthereumTransaction = wcEthereumTransaction;
            runOnUiThread(() -> onShowEvmosSignDialog(makeEvmosSignBundle(TYPE_ETH_SIGN_TRANSACTION, id, wcEthereumTransaction, null)));
            return null;
        });
        wcClient.setOnCosmostationAccounts((id, strings) -> {
            runOnUiThread(() -> onShowAccountDialog(id, strings, Lists.newArrayList(), 0));
            return null;
        });
        wcClient.setOnCosmostationSignTx((id, jsonArray) -> {
            runOnUiThread(() -> onShowSignDialog(makeSignBundle(TYPE_COSMOS_WALLET, id, jsonArray.toString())));
            return null;
        });
        wcClient.setOnKeplrGetKeys((id, strings) -> {
            runOnUiThread(() -> {
                String chainId = strings.get(0);
                if (!chainAccountMap.containsKey(WDp.getChainTypeByChainId(chainId).getChain())) {
                    onShowKeplrAccountDialog(id, chainId);
                } else {
                    wcClient.approveRequest(id, Lists.newArrayList(toKeplrWallet(chainAccountMap.get(WDp.getChainTypeByChainId(chainId).getChain()))));
                    moveToBackIfNeed();
                }
            });
            return null;
        });
        wcClient.setOnKeplrSignAmino((id, jsonArray) -> {
            runOnUiThread(() -> onShowSignDialog(makeSignBundle(TYPE_COSMOS_WALLET, id, jsonArray.toString())));
            return null;
        });
        wcClient.setOnCosmosGetAccounts((id, strings) -> {
            runOnUiThread(() -> onShowAccountDialog(id, strings, Lists.newArrayList(), 0));
            return null;
        });
        wcClient.setOnCosmosSignAmino((id, jsonArray) -> {
            runOnUiThread(() -> onShowSignDialog(makeSignBundle(TYPE_COSMOS_WALLET, id, jsonArray.toString())));
            return null;
        });
        wcClient.setOnCosmosSignDirect((id, jsonArray) -> {
            runOnUiThread(() -> onShowSignDialog(makeSignBundle(TYPE_COSMOS_SIGN_DIRECT, id, jsonArray.toString())));
            return null;
        });
        wcClient.setOnCosmostationSignDirectTx((id, jsonArray) -> {
            runOnUiThread(() -> onShowSignDialog(makeSignBundle(TYPE_COSMOS_SIGN_DIRECT, id, jsonArray.toString())));
            return null;
        });
    }

    private void processSessionRequest(WCPeerMeta wcPeerMeta) {
        mWcPeerMeta = wcPeerMeta;
        if (!isDeepLink && !isDapp) {
            onInitView(wcPeerMeta);
            if (BaseChain.EVMOS_MAIN.equals(mBaseChain)) {
                wcClient.approveSession(Lists.newArrayList(WKey.generateEthAddressFromPrivateKey(getPrivateKey(chainAccountMap.get(mBaseChain.getChain())))), 9001);
            } else {
                wcClient.approveSession(Lists.newArrayList(chainAccountMap.get(mBaseChain.getChain()).address), 1);
            }
        } else {
            if (BaseChain.EVMOS_MAIN.equals(mBaseChain)) {
                wcClient.approveSession(Lists.newArrayList(WKey.generateEthAddressFromPrivateKey(getPrivateKey(chainAccountMap.get(mBaseChain.getChain())))), 9001);
            } else {
                wcClient.approveSession(Lists.newArrayList(), 1);
            }
            mLoadingLayer.postDelayed(() -> mLoadingLayer.setVisibility(View.GONE), 2500);

        }
        Toast.makeText(getBaseContext(), getString(R.string.str_wc_connected), Toast.LENGTH_SHORT).show();
        changeDappConnectStatus(true);
    }

    private Sign.SignatureData processEthSign(WCEthereumSignMessage signMessage) throws Exception {
        Credentials credentials = Credentials.create(getPrivateKey(chainAccountMap.get(mBaseChain.getChain())));
        return Sign.signPrefixedMessage(signMessage.getData().getBytes(), credentials.getEcKeyPair());
    }

    private EthSendTransaction processEthSend(WCEthereumTransaction wcEthereumTransaction) throws InterruptedException, ExecutionException {
        String rpcUrl;
        if (BaseChain.EVMOS_MAIN.equals(mBaseChain)) {
            rpcUrl = "https://eth.bd.evmos.org:8545";
        } else {
            return null;
        }
        Web3j web3 = Web3j.build(new HttpService(rpcUrl));
        Credentials credentials = Credentials.create(getPrivateKey(chainAccountMap.get(mBaseChain.getChain())));
        EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        BigInteger value = BigInteger.ZERO;
        if (StringUtils.isNotBlank(wcEthereumTransaction.getValue())) {
            value = new BigInteger(wcEthereumTransaction.getValue().replace("0x", ""), 16);
        }

        Transaction transaction = new Transaction(wcEthereumTransaction.getFrom(), nonce, BigInteger.ZERO, BigInteger.ZERO, wcEthereumTransaction.getTo(), value, wcEthereumTransaction.getData());
        EthEstimateGas limit = web3.ethEstimateGas(transaction).sendAsync().get();
        RawTransaction rawTransaction = RawTransaction.createTransaction(
                9001,
                nonce,
                limit.getAmountUsed(),
                wcEthereumTransaction.getTo(),
                value,
                wcEthereumTransaction.getData(),
                BigInteger.valueOf(500000000L),
                BigInteger.valueOf(27500000000L)
        );
        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);
        return web3.ethSendRawTransaction(hexValue).sendAsync().get();
    }

    private void moveToBackIfNeed() {
        if (isDeepLink) {
            moveTaskToBack(true);
        }
    }

    private List<WCAccount> makeWCAccount() {
        if (mBaseChain.equals(BaseChain.KAVA_MAIN) && chainAccountMap.containsKey(mBaseChain.getChain())) {
            return Lists.newArrayList(new WCAccount(459, Objects.requireNonNull(chainAccountMap.get(mBaseChain.getChain())).address));
        }
        return Lists.newArrayList();
    }

    public void approveTrustRequest(long id, String wcSignTransaction) {
        StdSignMsg wcStdSignMsg = new Gson().fromJson(wcSignTransaction, StdSignMsg.class);
        try {
            JSONObject transactionJson = new JSONObject(wcSignTransaction);
            JSONArray messagesArray = transactionJson.getJSONArray("messages");
            ArrayList<Msg> msgList = Lists.newArrayList();
            for (int i = 0; i < messagesArray.length(); i++) {
                JSONObject rawMessage = messagesArray.getJSONObject(i).getJSONObject("rawJsonMessage");
                Msg msgModel = new Msg();
                msgModel.type = rawMessage.getString("type");
                msgModel.value = new Gson().fromJson(rawMessage.getString("value"), Msg.Value.class);
                msgModel.value.amount = parseAmount(msgModel.value.amount);
                msgList.add(msgModel);
            }
            wcStdSignMsg.msgs = msgList;

            Account account = new Account();
            account.accountNumber = Integer.parseInt(wcStdSignMsg.account_number);
            account.sequenceNumber = Integer.parseInt(wcStdSignMsg.sequence);
            ReqBroadCast tx = MsgGenerator.getWcTrustBroadcaseReq(account, msgList, wcStdSignMsg.fee, wcStdSignMsg.memo, getKey(WDp.getChainTypeByChainId(wcStdSignMsg.chain_id).getChain()), wcStdSignMsg.chain_id);
            Gson Presenter = new GsonBuilder().disableHtmlEscaping().create();
            String result = Presenter.toJson(tx);
            wcClient.approveRequest(id, result);
            Toast.makeText(getBaseContext(), getString(R.string.str_wc_request_responsed), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Object parseAmount(Object amount) {
        try {
            return new Gson().fromJson(new Gson().toJson(amount), Coin.class);
        } catch (Exception ignored) {
        }
        try {
            return new Gson().fromJson(new Gson().toJson(amount), new TypeToken<List<Coin>>() {
            }.getType());
        } catch (Exception ignored) {
        }
        return amount;
    }

    public void approveCosmosRequest(long id, String transaction) {
        JsonArray transactionJson = new Gson().fromJson(transaction, JsonArray.class);
        WcSignModel signModel = new WcSignModel(transactionJson.get(2).getAsJsonObject(), getKey(WDp.getChainTypeByChainId(transactionJson.get(0).getAsString()).getChain()));
        wcClient.approveRequest(id, Lists.newArrayList(signModel));
        Toast.makeText(getBaseContext(), getString(R.string.str_wc_request_responsed), Toast.LENGTH_SHORT).show();
        moveToBackIfNeed();
    }

    public void approveCosmosSignDirectRequest(long id, String transaction) {
        try {
            JsonArray transactionJson = new Gson().fromJson(transaction, JsonArray.class);
            JsonObject jsonObject = transactionJson.get(1).getAsJsonObject();
            String chainId = jsonObject.get("chainId").getAsString();
            TxOuterClass.TxBody txBody = TxOuterClass.TxBody.parseFrom(Base64.decode(jsonObject.get("bodyBytes").getAsString(), Base64.DEFAULT));
            TxOuterClass.AuthInfo authInfo = TxOuterClass.AuthInfo.parseFrom(Base64.decode(jsonObject.get("authInfoBytes").getAsString(), Base64.DEFAULT));
            long accountNumber = jsonObject.get("accountNumber").getAsLong();
            TxOuterClass.SignDoc signDoc = TxOuterClass.SignDoc.newBuilder().setBodyBytes(txBody.toByteString()).setAuthInfoBytes(authInfo.toByteString()).setChainId(chainId).setAccountNumber(accountNumber).build();
            ECKey key = getKey(WDp.getChainTypeByChainId(chainId).getChain());
            WcSignDirectModel signModel = new WcSignDirectModel(signDoc.toByteArray(), jsonObject, key);
            wcClient.approveRequest(id, signModel);
            Toast.makeText(getBaseContext(), getString(R.string.str_wc_request_responsed), Toast.LENGTH_SHORT).show();
            moveToBackIfNeed();
        } catch (Exception e) {
            wcClient.rejectRequest(id, "Signing error.");
            Toast.makeText(getBaseContext(), getString(R.string.str_unknown_error), Toast.LENGTH_SHORT).show();
            moveToBackIfNeed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onInitView(WCPeerMeta meta) {
        if (isDapp) {
            return;
        }

        if (meta != null) {
            if (!CollectionUtils.isEmpty(meta.getIcons())) {
                Picasso.get()
                        .load(meta.getIcons().get(0))
                        .fit()
                        .placeholder(R.drawable.validator_none_img)
                        .into(mWcImg);
            }
            mWcName.setText(meta.getName());
            mWcUrl.setText(meta.getUrl());
            mWcLayer.setVisibility(View.VISIBLE);
            mLoadingLayer.setVisibility(View.GONE);
        }

        mWcCardView.setCardBackgroundColor(ContextCompat.getColor(this, mChainConfig.chainBgColor()));
        mWcAccount.setText(chainAccountMap.get(mBaseChain.getChain()).address);
    }

    private boolean hasAccount(String chainId) {
        BaseChain requestChain = WDp.getChainTypeByChainId(chainId);
        if (requestChain == null) {
            onShowNotSupportChain(chainId);
            return false;
        }

        ArrayList<Account> existAccount = getBaseDao().onSelectAllAccountsByChainWithKey(requestChain);
        if (existAccount.isEmpty()) {
            onShowNoAccountsForChain();
            return false;
        }

        return true;
    }

    private void onShowKeplrAccountDialog(Long id, String chainId) {
        if (!hasAccount(chainId)) {
            wcClient.approveRequest(id, Lists.newArrayList());
            moveToBackIfNeed();
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putLong("id", id);
        bundle.putString("chainName", chainId);
        if (!this.isFinishing()) {
            Dialog_Wc_Account dialog = Dialog_Wc_Account.newInstance(bundle);
            dialog.setOnSelectListener(new Dialog_Wc_Account.OnDialogSelectListener() {
                @Override
                public void onSelect(Long id, Account account) {
                    chainAccountMap.put(WDp.getChainTypeByChainId(chainId).getChain(), account);
                    if (mBaseChain == null) {
                        mBaseChain = WDp.getChainTypeByChainId(chainId);
                        mChainConfig = ChainFactory.getChain(mBaseChain);
                        onInitView(mWcPeerMeta);
                    }
                    wcClient.approveRequest(id, Lists.newArrayList(toKeplrWallet(account)));
                    moveToBackIfNeed();
                }

                @Override
                public void onCancel() {
                    wcClient.approveRequest(id, Lists.newArrayList());
                    moveToBackIfNeed();
                }
            });
            dialog.setCancelable(false);
            dialog.show(getSupportFragmentManager(), "dialog");
        }
    }

    private void onShowAccountDialog(Long id, List<String> chains, List<Account> selectedAccounts, int index) {
        if (index >= chains.size()) {
            List<WCCosmostationAccount> wcAccounts = Lists.newArrayList();
            for (Account account : selectedAccounts) {
                wcAccounts.add(toCosmosatationAccount(account));
            }

            wcClient.approveRequest(id, Lists.newArrayList(wcAccounts));

            moveToBackIfNeed();
            return;
        }

        if (!hasAccount(chains.get(index))) {
            onShowAccountDialog(id, chains, selectedAccounts, index + 1);
            return;
        } else if (chainAccountMap.containsKey(WDp.getChainTypeByChainId(chains.get(index)).getChain())) {
            Account account = chainAccountMap.get(WDp.getChainTypeByChainId(chains.get(index)).getChain());
            selectedAccounts.add(account);
            onShowAccountDialog(id, chains, selectedAccounts, index + 1);
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putLong("id", id);
        bundle.putString("chainName", chains.get(index));
        if (!this.isFinishing()) {
            Dialog_Wc_Account dialog = Dialog_Wc_Account.newInstance(bundle);
            dialog.setOnSelectListener(new Dialog_Wc_Account.OnDialogSelectListener() {
                @Override
                public void onSelect(Long id, Account account) {
                    chainAccountMap.put(WDp.getChainTypeByChainId(chains.get(index)).getChain(), account);
                    if (mBaseChain == null) {
                        mBaseChain = WDp.getChainTypeByChainId(chains.get(index));
                        mChainConfig = ChainFactory.getChain(mBaseChain);
                        onInitView(mWcPeerMeta);
                    }
                    selectedAccounts.add(account);
                    onShowAccountDialog(id, chains, selectedAccounts, index + 1);
                }

                @Override
                public void onCancel() {
                    onShowAccountDialog(id, chains, selectedAccounts, index + 1);
                }
            });
            dialog.setCancelable(false);
            dialog.show(getSupportFragmentManager(), "dialog" + index);
        }
    }


    @Override
    public void onBackPressed() {
        if (mWebView.getVisibility() == View.VISIBLE && mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private ECKey getKey(String chainId) {
        Account currentAccount = chainAccountMap.get(chainId);
        assert currentAccount != null;
        return ECKey.fromPrivate(new BigInteger(getPrivateKey(currentAccount), 16));
    }

    private String getPrivateKey(Account account) {
        if (account.fromMnemonic) {
            String entropy = CryptoHelper.doDecryptData(getString(R.string.key_mnemonic) + account.uuid, account.resource, account.spec);
            return WKey.getKeyWithPathfromEntropy(account, entropy).getPrivateKeyAsHex();
        } else {
            return CryptoHelper.doDecryptData(getString(R.string.key_private) + account.uuid, account.resource, account.spec);
        }
    }

    private WCKeplrWallet toKeplrWallet(Account account) {
        ECKey key = getKey(account.baseChain);
        return new WCKeplrWallet(
                WUtil.getWalletName(this, account),
                "secp256k1",
                key.getPubKey(),
                WKey.generateTenderAddressBytesFromPrivateKey(key.getPrivateKeyAsHex()),
                account.address,
                false);
    }

    private WCCosmostationAccount toCosmosatationAccount(Account account) {
        ECKey key = getKey(account.baseChain);
        return new WCCosmostationAccount(
                WUtil.getWalletName(this, account),
                "secp256k1",
                key.getPubKey(),
                WKey.generateTenderAddressBytesFromPrivateKey(key.getPrivateKeyAsHex()),
                account.address);
    }

    private void onShowSignDialog(Bundle bundle) {
        if (!this.isFinishing()) {
            Dialog_Wc_Raw_Data wcRawDataDialog = Dialog_Wc_Raw_Data.newInstance(bundle, new Dialog_Wc_Raw_Data.WcSignRawDataListener() {
                @Override
                public void sign(int type, Long id, String transaction) {
                    if (type == ConnectWalletActivity.TYPE_TRUST_WALLET) {
                        approveTrustRequest(id, transaction);
                    } else if (type == ConnectWalletActivity.TYPE_COSMOS_WALLET) {
                        approveCosmosRequest(id, transaction);
                    } else if (type == ConnectWalletActivity.TYPE_COSMOS_SIGN_DIRECT) {
                        approveCosmosSignDirectRequest(id, transaction);
                    }
                }

                @Override
                public void reject(Long id) {
                    rejectSignRequest(id);
                }
            });
            wcRawDataDialog.setCancelable(false);
            wcRawDataDialog.show(getSupportFragmentManager(), "dialog");
        }
    }

    private void onShowEvmosSignDialog(Bundle bundle) {
        Dialog_Wc_Raw_Data_Evmos dialog = Dialog_Wc_Raw_Data_Evmos.newInstance(bundle, new Dialog_Wc_Raw_Data_Evmos.WcEvmosSignRawDataListener() {

            @Override
            public void sign(int type, Long id, String wcEthereumTransaction, String signMessage) {
                if (type == ConnectWalletActivity.TYPE_ETH_SIGN_MESSAGE) {
                    new Thread(() -> {
                        try {
                            Sign.SignatureData signResult = processEthSign(mSignMessage);
                            wcClient.approveRequest(id, signResult);
                        } catch (Exception e) {
                            wcClient.rejectRequest(id, getString(R.string.str_unknown_error));
                        }
                    }).start();
                } else if (type == ConnectWalletActivity.TYPE_ETH_SIGN_TRANSACTION) {
                    new Thread(() -> {
                        try {
                            EthSendTransaction sendResult = processEthSend(mWcEthereumTransaction);
                            if (sendResult == null) {
                                wcClient.rejectRequest(id, getString(R.string.str_unknown_error));
                            } else {
                                wcClient.approveRequest(id, sendResult.getTransactionHash());
                            }
                        } catch (InterruptedException | ExecutionException e) {
                            wcClient.rejectRequest(id, getString(R.string.str_unknown_error));
                        }
                    }).start();
                }
            }

            @Override
            public void reject(Long id) {
                rejectSignRequest(id);
            }
        });
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), "dialog");
    }

    private void rejectSignRequest(Long id) {
        wcClient.rejectRequest(id, getString(R.string.str_cancel));

        moveToBackIfNeed();
    }

    private void onShowNoAccountsForChain() {
        CommonAlertDialog.showSingleButton(this, getString(R.string.str_error_not_support_chain_title), getString(R.string.str_error_not_support_chain_msg), getString(R.string.str_ok), null, false);
    }

    private void onShowNotSupportChain(String chainId) {
        CommonAlertDialog.showSingleButton(this, getString(R.string.str_error_not_support_chain_title), String.format(getString(R.string.str_error_not_support_msg), chainId), getString(R.string.str_ok), null, false);
    }

    private Bundle makeSignBundle(int type, Long id, String transaction) {
        Bundle bundle = new Bundle();
        bundle.putString("transaction", transaction);
        bundle.putString("url", mWcPeerMeta.getUrl());
        bundle.putLong("id", id);
        bundle.putInt("type", type);
        return bundle;
    }

    private Bundle makeEvmosSignBundle(int type, Long id, WCEthereumTransaction transaction, WCEthereumSignMessage message) {
        Bundle bundle = new Bundle();
        if (transaction == null) {
            bundle.putString("address", transaction.getFrom());
            bundle.putString("message", message.getData());
        } else {
            bundle.putString("transaction", transaction.getData());
            bundle.putString("address", transaction.getFrom());
        }
        bundle.putString("url", mWcPeerMeta.getUrl());
        bundle.putLong("id", id);
        bundle.putInt("type", type);
        return bundle;
    }

    private void onKeplrEnable(long id, List<String> strings) {
        if (hasAccount(strings.get(0))) {
            wcClient.approveRequest(id, strings);
            moveToBackIfNeed();
        } else {
            mLoadingLayer.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (fromScheme(intent) && WC_URL_SCHEME_HOST_WC.equals(intent.getData().getHost())) {
            if (wcSession != null && wcClient.getSession() != null && wcClient != null && wcClient.isConnected()) {
                return;
            }
            mWcURL = intent.getData().getQuery();
            mLoadingLayer.setVisibility(View.VISIBLE);
            initWalletConnect();
        } else if (fromScheme(intent) && (WC_URL_SCHEME_HOST_DAPP.equals(intent.getData().getHost()) || WC_URL_SCHEME_HOST_DAPP_INTERNAL.equals(intent.getData().getHost()))) {
            if (mWebView.getVisibility() != View.VISIBLE) {
                Toast.makeText(ConnectWalletActivity.this, R.string.str_unknown_error, Toast.LENGTH_SHORT).show();
                return;
            }

            mWebView.loadUrl(intent.getData().getQuery());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (wcSession != null && wcClient.getSession() != null && wcClient.isConnected()) {
            wcClient.killSession();
        } else if (wcClient != null) {
            wcClient.disconnect();
        }
    }

    private final ActivityResultLauncher<Intent> connectWalletResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            initWalletConnect();
        } else {
            finish();
        }
    });
}
