package wannabit.io.cosmostaion.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;
import com.trustwallet.walletconnect.WCClient;
import com.trustwallet.walletconnect.models.WCAccount;
import com.trustwallet.walletconnect.models.WCPeerMeta;
import com.trustwallet.walletconnect.models.cosmostation.WCCosmostationAccount;
import com.trustwallet.walletconnect.models.keplr.WCKeplrWallet;
import com.trustwallet.walletconnect.models.session.WCSession;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.DeterministicKey;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.OkHttpClient;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.cosmos.MsgGenerator;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.Dialog_Empty_Chain;
import wannabit.io.cosmostaion.dialog.Dialog_Not_Support_Chain;
import wannabit.io.cosmostaion.dialog.Dialog_WC_Account;
import wannabit.io.cosmostaion.dialog.Dialog_Wc_Raw_Data;
import wannabit.io.cosmostaion.model.StdSignMsg;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.model.type.Signature;
import wannabit.io.cosmostaion.network.req.ReqBroadCast;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class ConnectWalletActivity extends BaseActivity implements View.OnClickListener {
    public final static int TYPE_TRUST_WALLET = 1;
    public final static int TYPE_KEPLR_WALLET = 2;

    private RelativeLayout mWcLayer, mLoadingLayer;
    private LinearLayout mEmptyLayer;
    private CardView mWcCardView;
    private ImageView mWcImg;
    private TextView mWcName, mWcUrl, mWcAccount;
    private Button mBtnDisconnect;
    private WebView mWebView;
    private TextView mTitleText;
    private Dialog_Wc_Raw_Data mDialogWcRawData;
    private RelativeLayout mStateLayout;
    private TextView mStateText;
    private View mStateLight;
    private TextView mStatePeer;

    private String mWcURL;
    private WCClient wcClient;
    private WCSession wcSession;
    private WCPeerMeta mWcPeerMeta;
    private JsonArray mjsonArray;
    private Boolean isDeepLink = false;
    private Boolean isDapp = false;
    private final Map<String, Account> chainAccountMap = Maps.newHashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_wallet);
        initView();

        if (fromScheme(getIntent())) {
            if ("wc".equals(getIntent().getData().getHost())) {
                isDeepLink = true;
                mWcURL = getIntent().getData().getQuery();
                if (!getBaseDao().onHasPassword()) {
                    mEmptyLayer.setVisibility(View.VISIBLE);
                    mLoadingLayer.setVisibility(View.GONE);
                    mBtnDisconnect.setText("Dismiss");
                    return;
                } else {
                    Intent intent = new Intent(this, PasswordCheckActivity.class);
                    intent.putExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_SIMPLE_CHECK);
                    startActivityForResult(intent, BaseConstant.CONST_PW_SIMPLE_CHECK);
                    overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
                }
                mWebView.setVisibility(View.GONE);
            } else {
                settingForDaap(getIntent().getData().getQuery());
            }
        } else if (getIntent().hasExtra("wcUrl")) {
            mWcURL = getIntent().getStringExtra("wcUrl");
            loadInfo();
            initWalletConnect();
            getKey(mBaseChain.getChain());
            mWebView.setVisibility(View.GONE);
        } else if (getIntent().hasExtra("dappUrl")) {
            settingForDaap(getIntent().getStringExtra("dappUrl"));
        }
    }

    private boolean fromScheme(Intent intent) {
        return intent.getData() != null && "cosmostation".equals(intent.getData().getScheme());
    }

    private void settingForDaap(String url) {
        isDapp = true;
        mWebView.setVisibility(View.VISIBLE);
        mWcLayer.setVisibility(View.GONE);
        mLoadingLayer.setVisibility(View.GONE);
        mWebView.loadUrl(url);
        mBtnDisconnect.setVisibility(View.GONE);
        mTitleText.setText("Browswer");
        mStateLayout.setVisibility(View.VISIBLE);
        mStateLight.setBackgroundColor(Color.parseColor("#ff0000"));
        mStateText.setText("Disconnect");
        mStatePeer.setText(url);
        mStatePeer.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (fromScheme(intent) && "wc".equals(intent.getData().getHost())) {
            if (wcSession != null && wcClient.getSession() != null && wcClient != null && wcClient.isConnected()) {
                return;
            }
            mWcURL = intent.getData().getQuery();
            initWalletConnect();
        }
    }

    private void loadInfo() {
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
        mBtnDisconnect = findViewById(R.id.btn_disconnect);
        mBtnDisconnect.setOnClickListener(this);
        mStatePeer = findViewById(R.id.wc_peer);
        mStateLight = findViewById(R.id.wc_light);
        mStateLayout = findViewById(R.id.wc_state_layout);
        mStateText = findViewById(R.id.wc_state);

        mWebView = findViewById(R.id.wc_webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setUserAgentString(mWebView.getSettings().getUserAgentString() + "/Cosmostation_Android_Dapp");
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
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

        setSupportActionBar(findViewById(R.id.tool_bar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initWalletConnect() {
        OkHttpClient client = new OkHttpClient.Builder().pingInterval(100000, TimeUnit.MILLISECONDS).build();
        wcClient = new WCClient(new GsonBuilder(), client);
        WCPeerMeta meta = new WCPeerMeta("", "", "", Lists.newArrayList());
        wcSession = WCSession.Companion.from(mWcURL);
        wcClient.connect(wcSession, meta, UUID.randomUUID().toString(), null);
        wcClient.setOnGetAccounts(id -> {
            wcClient.approveRequest(id, makeWCAccount());
            return null;
        });
        wcClient.setOnDisconnect((code, reason) -> {
            runOnUiThread(() -> {
                Toast.makeText(getBaseContext(), getString(R.string.str_wc_disconnected), Toast.LENGTH_SHORT).show();
                if (!isFinishing()) onBackPressed();
            });
            return null;
        });
        wcClient.setOnSessionRequest((id, wcPeerMeta) -> {
            runOnUiThread(() -> {
                if (!isDeepLink && !isDapp) {
                    onInitView(wcPeerMeta);
                    wcClient.approveSession(Lists.newArrayList(chainAccountMap.get(mBaseChain.getChain()).address), 1);
                } else {
                    mWcPeerMeta = wcPeerMeta;
                    wcClient.approveSession(Lists.newArrayList(), 1);
                }
                mStateLight.setBackgroundColor(Color.parseColor("#00d690"));
                mStateText.setText("Connect");
            });
            return null;
        });
        wcClient.setOnSignTransaction((id, wcSignTransaction) -> {
            runOnUiThread(() -> onShowRawDataDialog(processTrust(id, wcSignTransaction.getTransaction())));
            return null;
        });
        wcClient.setOnKeplrEnable((id, strings) -> {
            runOnUiThread(() -> onKeplrEnable(id, strings));
            return null;
        });
        wcClient.setOnCosmostationAccounts((id, strings) -> {
            runOnUiThread(() -> {
                onShowAccountDialog(id, strings);
            });
            return null;
        });
        wcClient.setOnCosmostationSignTx((id, jsonArray) -> {
            mjsonArray = jsonArray;
            runOnUiThread(() -> onShowRawDataDialog(processKeplr(id)));
            return null;
        });
        wcClient.setOnKeplrGetKeys((id, strings) -> {
            runOnUiThread(() -> {
                wcClient.approveRequest(id, Lists.newArrayList(onKeplrGetKey(chainAccountMap.get(mBaseChain.getChain()))));
            });
            return null;
        });
        wcClient.setOnKeplrSignAmino((id, jsonArray) -> {
            mjsonArray = jsonArray;
            runOnUiThread(() -> onShowRawDataDialog(processKeplr(id)));
            return null;
        });
    }

    private ECKey getKey(String chainId) {
        ECKey mEcKey;
        Account currentAccount = chainAccountMap.get(chainId);
        if (currentAccount.fromMnemonic) {
            String entropy = CryptoHelper.doDecryptData(getString(R.string.key_mnemonic) + currentAccount.uuid, currentAccount.resource, currentAccount.spec);
            DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(currentAccount, entropy);
            mEcKey = ECKey.fromPrivate(new BigInteger(deterministicKey.getPrivateKeyAsHex(), 16));
        } else {
            String privateKey = CryptoHelper.doDecryptData(getString(R.string.key_private) + currentAccount.uuid, currentAccount.resource, currentAccount.spec);
            mEcKey = ECKey.fromPrivate(new BigInteger(privateKey, 16));
        }
        return mEcKey;
    }

    private List<WCAccount> makeWCAccount() {
        if (mBaseChain.equals(BaseChain.KAVA_MAIN)) {
            return Lists.newArrayList(new WCAccount(459, chainAccountMap.get(mBaseChain.getChain()).address));
        }
        return Lists.newArrayList();
    }

    public void approveTrustRequest(long id, String wcSignTransaction) {
        WLog.w("Trust Request");
        StdSignMsg wcStdSignMsg = new Gson().fromJson(wcSignTransaction, StdSignMsg.class);
        try {
            JSONObject transactionJson = new JSONObject(wcSignTransaction);
            JSONArray messagesArray = transactionJson.getJSONArray("messages");
            ArrayList<Msg> msgList = Lists.newArrayList();
            for (int i = 0; i < messagesArray.length(); i++) {
                JSONObject rawMessage = messagesArray.getJSONObject(0).getJSONObject("rawJsonMessage");
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
            ReqBroadCast tx = MsgGenerator.getWcTrustBroadcaseReq(account, msgList, wcStdSignMsg.fee, wcStdSignMsg.memo, getKey(wcStdSignMsg.chain_id), wcStdSignMsg.chain_id);
            Gson Presenter = new GsonBuilder().disableHtmlEscaping().create();
            String result = Presenter.toJson(tx);
            wcClient.approveRequest(id, result);
            Toast.makeText(getBaseContext(), getString(R.string.str_wc_request_responsed), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void approveKeplrRequest(long id) {
        WLog.w("Keplr Request");
        SignModel signModel = new SignModel(mjsonArray.get(2).getAsJsonObject(), getKey(WDp.getChainTypeByChainId(mjsonArray.get(0).getAsString()).getChain()));
        wcClient.approveRequest(id, Lists.newArrayList(signModel));
        Toast.makeText(getBaseContext(), getString(R.string.str_wc_request_responsed), Toast.LENGTH_SHORT).show();

        if (isDeepLink) {
            moveTaskToBack(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
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

    private void onInitView(WCPeerMeta meta) {
        if (isDapp) {
            return;
        }

        Toast.makeText(getBaseContext(), getString(R.string.str_wc_connected), Toast.LENGTH_SHORT).show();
        Picasso.get()
                .load(meta.getIcons().get(0))
                .fit()
                .placeholder(R.drawable.validator_none_img)
                .into(mWcImg);
        mWcName.setText(meta.getName());
        mWcUrl.setText(meta.getUrl());
        mWcLayer.setVisibility(View.VISIBLE);
        mLoadingLayer.setVisibility(View.GONE);

        mWcCardView.setCardBackgroundColor(WDp.getChainBgColor(this, mBaseChain));
        mWcAccount.setText(chainAccountMap.get(mBaseChain.getChain()).address);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnDisconnect)) {
            if (isDeepLink) {
                moveTaskToBack(true);
            }
            onBackPressed();
        }
    }

    private Bundle processTrust(Long id, String wcSignTransaction) {
        if (mDialogWcRawData != null && mDialogWcRawData.isAdded()) mDialogWcRawData.dismiss();
        if (wcSignTransaction != null) {
            Bundle bundle = new Bundle();
            bundle.putString("transaction", wcSignTransaction);
            bundle.putLong("id", id);
            bundle.putInt("type", TYPE_TRUST_WALLET);
            return bundle;
        } else {
            return null;
        }
    }

    private Bundle processKeplr(Long id) {
        if (mDialogWcRawData != null && mDialogWcRawData.isAdded()) mDialogWcRawData.dismiss();
        Bundle bundle = new Bundle();
        bundle.putString("transaction", mjsonArray.toString());
        bundle.putLong("id", id);
        bundle.putInt("type", TYPE_KEPLR_WALLET);
        return bundle;
    }

    private void onShowRawDataDialog(Bundle bundle) {
        mDialogWcRawData = Dialog_Wc_Raw_Data.newInstance(bundle);
        mDialogWcRawData.setCancelable(false);
        getSupportFragmentManager().beginTransaction().add(mDialogWcRawData, "dialog").commitNowAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        if (mWebView.getVisibility() == View.VISIBLE && mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private void onKeplrEnable(long id, List<String> strings) {
        BaseChain requestChain = WDp.getChainTypeByChainId(strings.get(0));
        if (requestChain != null) {
            ArrayList<Account> existAccount = getBaseDao().onSelectAllAccountsByChainWithKey(WDp.getChainTypeByChainId(strings.get(0)));
            if (existAccount.size() <= 0) {
                mWcLayer.setVisibility(View.GONE);
                mLoadingLayer.setVisibility(View.GONE);
                onShowNoAccountsForChain();
            } else {
                wcClient.approveRequest(id, strings);
            }
        } else {
            onShowNotSupportChain(strings.get(0));
        }
    }

    private boolean hasAccount(String chainId) {
        BaseChain requestChain = WDp.getChainTypeByChainId(chainId);
        if (requestChain == null) {
            onShowNotSupportChain(chainId);
            return false;
        } else {
            ArrayList<Account> existAccount = getBaseDao().onSelectAllAccountsByChainWithKey(requestChain);
            if (existAccount.isEmpty()) {
                onShowNoAccountsForChain();
                return false;
            } else {
                return true;
            }
        }
    }

    private WCKeplrWallet onKeplrGetKey(Account account) {
        WCKeplrWallet keplr = new WCKeplrWallet(
                WUtil.getWalletName(this, account),
                "secp256k1",
                getKey(account.baseChain).getPublicKeyAsHex(),
                WKey.generateTenderAddressFromPrivateKey(getKey(account.baseChain).getPrivateKeyAsHex()),
                account.address,
                false);
        return keplr;
    }

    private WCCosmostationAccount onCosmostationAccount(Account account) {
        return new WCCosmostationAccount(
                WUtil.getWalletName(this, account),
                "secp256k1",
                getKey(account.baseChain).getPublicKeyAsHex(),
                WKey.generateTenderAddressFromPrivateKey(getKey(account.baseChain).getPrivateKeyAsHex()),
                account.address);
    }

    private void onShowNoAccountsForChain() {
        Dialog_Empty_Chain mDialogEmptyChain = Dialog_Empty_Chain.newInstance();
        mDialogEmptyChain.setCancelable(false);
        getSupportFragmentManager().beginTransaction().add(mDialogEmptyChain, "dialog").commitNowAllowingStateLoss();
    }

    private void onShowNotSupportChain(String chainId) {
        Bundle bundle = new Bundle();
        bundle.putString("chainId", chainId);
        Dialog_Not_Support_Chain mDialogNotSupportChain = Dialog_Not_Support_Chain.newInstance(bundle);
        mDialogNotSupportChain.setCancelable(false);
        getSupportFragmentManager().beginTransaction().add(mDialogNotSupportChain, "dialog").commitNowAllowingStateLoss();
    }

    private void onShowAccountDialog(Long id, List<String> chains) {
        List<WCCosmostationAccount> accounts = Lists.newArrayList();
        AtomicInteger accountCheckCount = new AtomicInteger();
        for (String chain : chains) {
            if (!hasAccount(chain)) {
                accountCheckCount.getAndIncrement();
                //@TOBE : need refactoring
                if (accountCheckCount.get() == chains.size()) {
                    wcClient.approveRequest(id, Lists.newArrayList(accounts));
                    if (isDeepLink) {
                        moveTaskToBack(true);
                    }
                }
                continue;
            }

            Bundle bundle = new Bundle();
            bundle.putLong("id", id);
            bundle.putString("chainName", chain);
            Dialog_WC_Account mDialogWcAccount = Dialog_WC_Account.newInstance(bundle);
            mDialogWcAccount.setCancelable(true);
            mDialogWcAccount.setOnSelectListener((wcId, account) -> {
                accountCheckCount.getAndIncrement();
                chainAccountMap.put(WDp.getChainTypeByChainId(chain).getChain(), account);
                if (mBaseChain == null) {
                    mBaseChain = WDp.getChainTypeByChainId(chain);
                    onInitView(mWcPeerMeta);
                }
                accounts.add(onCosmostationAccount(account));
                if (accountCheckCount.get() == chains.size()) {
                    wcClient.approveRequest(id, Lists.newArrayList(accounts));
                    if (isDeepLink) {
                        moveTaskToBack(true);
                    }
                }
            });
            getSupportFragmentManager().beginTransaction().add(mDialogWcAccount, "dialog" + chain).commitNowAllowingStateLoss();
        }
    }

    class SignModel {
        TreeMap<String, Object> signed;
        Signature signature;

        public SignModel(JsonObject txMsg, ECKey key) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

            try {
                this.signed = mapper.readValue(txMsg.toString(), TreeMap.class);
            } catch (IOException e) {
                this.signed = Maps.newTreeMap();
                e.printStackTrace();
            }
            this.signature = MsgGenerator.getWcKeplrBroadcaseReq(key, txMsg);
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
