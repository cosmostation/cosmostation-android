package wannabit.io.cosmostaion.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.squareup.picasso.Picasso;
import com.trustwallet.walletconnect.WCClient;
import com.trustwallet.walletconnect.models.WCAccount;
import com.trustwallet.walletconnect.models.WCPeerMeta;
import com.trustwallet.walletconnect.models.cosmostation.WCCosmostationAccount;
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
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
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

import okhttp3.OkHttpClient;
import wannabit.io.cosmostaion.BuildConfig;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.cosmos.MsgGenerator;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.AlertDialogUtils;
import wannabit.io.cosmostaion.dialog.Dialog_WC_Account;
import wannabit.io.cosmostaion.dialog.Dialog_Wc_Raw_Data;
import wannabit.io.cosmostaion.model.StdSignMsg;
import wannabit.io.cosmostaion.model.WcSignModel;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.network.req.ReqBroadCast;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WUtil;

public class ConnectWalletActivity extends BaseActivity {
    public final static int TYPE_TRUST_WALLET = 1;
    public final static int TYPE_COSMOS_WALLET = 2;

    public static final String WC_URL_SCHEME_HOST_WC = "wc";
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

    private String mWcURL;
    private WCClient wcClient;
    private WCSession wcSession;
    private WCPeerMeta mWcPeerMeta;
    private Boolean isDeepLink = false;
    private Boolean isDapp = false;
    private final Map<String, Account> chainAccountMap = Maps.newHashMap();

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
            } else {
                settingForDapp(getIntent().getData().getQuery());
            }
        } else if (getIntent().hasExtra(INTENT_KEY_WC_URL)) {
            settingForWc(getIntent().getStringExtra(INTENT_KEY_WC_URL));
        } else if (getIntent().hasExtra(INTENT_KEY_DAPP_URL)) {
            settingForDapp(getIntent().getStringExtra(INTENT_KEY_DAPP_URL));
        }
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
    }

    private void settingForDapp(String url) {
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
            mConnectText.setTextColor(getColor(R.color.colorWhite));
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
            intent.putExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_SIMPLE_CHECK);
            startActivityForResult(intent, BaseConstant.CONST_PW_SIMPLE_CHECK);
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
        mWcCardView.setCardBackgroundColor(WDp.getChainBgColor(this, mBaseChain));
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
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setUserAgentString(mWebView.getSettings().getUserAgentString() + " Cosmostation/APP/Android/" + BuildConfig.VERSION_NAME);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("intent:")) {
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

                return true;
            }
        });
    }

    private void initWalletConnect() {
        OkHttpClient client = new OkHttpClient.Builder().pingInterval(100000, TimeUnit.MILLISECONDS).build();
        wcClient = new WCClient(new GsonBuilder(), client);
        WCPeerMeta meta = new WCPeerMeta(getString(R.string.str_wc_peer_name), getString(R.string.str_wc_peer_url), getString(R.string.str_wc_peer_desc), Lists.newArrayList());
        wcSession = WCSession.Companion.from(mWcURL);
        wcClient.connect(wcSession, meta, UUID.randomUUID().toString(), null);
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
                if (!isDeepLink && !isDapp) {
                    onInitView(wcPeerMeta);
                    if (BaseChain.EVMOS_MAIN.equals(mBaseChain)) {
                        wcClient.approveSession(Lists.newArrayList(WKey.generateEthAddressFromPrivateKey(getPrivateKey(chainAccountMap.get(mBaseChain.getChain())))), 9001);
                    } else {
                        wcClient.approveSession(Lists.newArrayList(chainAccountMap.get(mBaseChain.getChain()).address), 1);
                    }
                } else {
                    mWcPeerMeta = wcPeerMeta;
                    wcClient.approveSession(Lists.newArrayList(), 1);
                }
                changeDappConnectStatus(true);
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
        wcClient.setOnEthSendTransaction((id, wcEthereumTransaction) -> {
            runOnUiThread(() ->
                    AlertDialogUtils.showDoubleButtonDialog(ConnectWalletActivity.this, getString(R.string.str_wc_sign_title), wcEthereumTransaction.getData(), getString(R.string.str_cancel), view -> wcClient.rejectRequest(id, getString(R.string.str_cancel)), getString(R.string.str_confirm), view -> {
                        new Thread(() -> {
                            try {
                                EthSendTransaction sendResult = processEthSend(wcEthereumTransaction);
                                if (sendResult == null) {
                                    wcClient.rejectRequest(id, getString(R.string.str_unknown_error));
                                } else {
                                    wcClient.approveRequest(id, sendResult.getTransactionHash());
                                }
                            } catch (InterruptedException | ExecutionException e) {
                                wcClient.rejectRequest(id, getString(R.string.str_unknown_error));
                            }
                        }).start();
                    })
            );
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
        RawTransaction rawTransaction = RawTransaction.createTransaction(
                nonce,
                BigInteger.valueOf(2000000020L),
                BigInteger.valueOf(500000L),
                wcEthereumTransaction.getTo(),
                value,
                wcEthereumTransaction.getData()
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
                msgModel.value.amount = msgModel.value.getCoins();
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

    public void approveCosmosRequest(long id, String transaction) {
        JsonArray transactionJson = new Gson().fromJson(transaction, JsonArray.class);
        WcSignModel signModel = new WcSignModel(transactionJson.get(2).getAsJsonObject(), getKey(WDp.getChainTypeByChainId(transactionJson.get(0).getAsString()).getChain()));
        wcClient.approveRequest(id, Lists.newArrayList(signModel));
        Toast.makeText(getBaseContext(), getString(R.string.str_wc_request_responsed), Toast.LENGTH_SHORT).show();
        moveToBackIfNeed();
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

        Toast.makeText(getBaseContext(), getString(R.string.str_wc_connected), Toast.LENGTH_SHORT).show();
        if (!meta.getIcons().isEmpty()) {
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

        mWcCardView.setCardBackgroundColor(WDp.getChainBgColor(this, mBaseChain));
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
        Dialog_WC_Account mDialogWcAccount = Dialog_WC_Account.newInstance(bundle);
        mDialogWcAccount.setCancelable(true);
        mDialogWcAccount.setOnSelectListener((wcId, account) -> {
            chainAccountMap.put(WDp.getChainTypeByChainId(chainId).getChain(), account);
            if (mBaseChain == null) {
                mBaseChain = WDp.getChainTypeByChainId(chainId);
                onInitView(mWcPeerMeta);
            }
            wcClient.approveRequest(id, Lists.newArrayList(toKeplrWallet(account)));
            moveToBackIfNeed();
        });
        getSupportFragmentManager().beginTransaction().add(mDialogWcAccount, "dialog").commitNowAllowingStateLoss();
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
        }

        Bundle bundle = new Bundle();
        bundle.putLong("id", id);
        bundle.putString("chainName", chains.get(index));
        Dialog_WC_Account mDialogWcAccount = Dialog_WC_Account.newInstance(bundle);
        mDialogWcAccount.setCancelable(true);
        mDialogWcAccount.setOnSelectListener((wcId, account) -> {
            chainAccountMap.put(WDp.getChainTypeByChainId(chains.get(index)).getChain(), account);
            if (mBaseChain == null) {
                mBaseChain = WDp.getChainTypeByChainId(chains.get(index));
                onInitView(mWcPeerMeta);
            }
            selectedAccounts.add(account);
            onShowAccountDialog(id, chains, selectedAccounts, index + 1);
        });
        getSupportFragmentManager().beginTransaction().add(mDialogWcAccount, "dialog" + index).commitNowAllowingStateLoss();
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
        Dialog_Wc_Raw_Data dialog = Dialog_Wc_Raw_Data.newInstance(bundle, new Dialog_Wc_Raw_Data.WcSignRawDataListener() {
            @Override
            public void sign(int type, Long id, String transaction) {
                if (type == ConnectWalletActivity.TYPE_TRUST_WALLET) {
                    approveTrustRequest(id, transaction);
                } else if (type == ConnectWalletActivity.TYPE_COSMOS_WALLET) {
                    approveCosmosRequest(id, transaction);
                }
            }

            @Override
            public void reject(Long id) {
                rejectSignRequest(id);
            }
        });
        dialog.setCancelable(false);
        getSupportFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();
    }

    private void rejectSignRequest(Long id) {
        wcClient.rejectRequest(id, getString(R.string.str_cancel));

        moveToBackIfNeed();
    }

    private void onShowNoAccountsForChain() {
        AlertDialogUtils.showSingleButtonDialog(this, getString(R.string.str_error_not_support_chain_title), getString(R.string.str_error_not_support_chain_msg), getString(R.string.str_ok), null, false);
    }

    private void onShowNotSupportChain(String chainId) {
        AlertDialogUtils.showSingleButtonDialog(this, getString(R.string.str_error_not_support_chain_title), String.format(getString(R.string.str_error_not_support_msg), chainId), getString(R.string.str_ok), null, false);
    }

    private Bundle makeSignBundle(int type, Long id, String transaction) {
        Bundle bundle = new Bundle();
        bundle.putString("transaction", transaction);
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
            initWalletConnect();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BaseConstant.CONST_PW_SIMPLE_CHECK && resultCode == Activity.RESULT_OK) {
            initWalletConnect();
        } else {
            finish();
        }
    }
}
