package wannabit.io.cosmostaion.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;
import com.trustwallet.walletconnect.WCClient;
import com.trustwallet.walletconnect.models.WCAccount;
import com.trustwallet.walletconnect.models.WCPeerMeta;
import com.trustwallet.walletconnect.models.keplr.WCKeplrWallet;
import com.trustwallet.walletconnect.models.session.WCSession;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.DeterministicKey;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.cosmos.MsgGenerator;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
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
    private CardView mWcCardView;
    private ImageView mWcImg;
    private TextView mWcName, mWcUrl, mWcAccount;
    private Button mBtnDisconnect;
    private Dialog_Wc_Raw_Data mDialogWcRawData;

    private String mWcURL;
    private WCClient wcClient;
    private WCSession wcSession;
    private JsonArray mjsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_wallet);
        initView();
        loadInfo();
        getKey();
        initWalletConnect();
    }

    private void loadInfo() {
        if (getIntent().getData() != null && "cosmostation".equals(getIntent().getData().getScheme())) {
            mWcURL = getIntent().getData().getQuery();
        } else {
            mWcURL = getIntent().getStringExtra("wcUrl");
        }
        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mWcCardView.setCardBackgroundColor(WDp.getChainBgColor(this, mBaseChain));
    }

    private void initView() {
        mWcLayer = findViewById(R.id.wc_layer);
        mLoadingLayer = findViewById(R.id.loading_layer);
        mWcCardView = findViewById(R.id.wc_card);
        mWcImg = findViewById(R.id.wc_img);
        mWcName = findViewById(R.id.wc_name);
        mWcUrl = findViewById(R.id.wc_url);
        mWcAccount = findViewById(R.id.wc_address);
        mBtnDisconnect = findViewById(R.id.btn_disconnect);
        mBtnDisconnect.setOnClickListener(this);

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
            runOnUiThread(() -> onInitView(wcPeerMeta));
            return null;
        });
        wcClient.setOnSignTransaction((id, wcSignTransaction) -> {
            runOnUiThread(() -> onShowRawDataDialog(processTrust(id, wcSignTransaction.getTransaction())));
            return null;
        });
        wcClient.setOnKeplrEnable((id, strings) -> {
            wcClient.approveRequest(id, strings);
            return null;
        });
        wcClient.setOnKeplrGetKey((id, strings) -> {
            WCKeplrWallet keplr = new WCKeplrWallet(
                    WUtil.getWalletName(this, mAccount),
                    "secp256k1",
                    getKey().getPublicKeyAsHex(),
                    WKey.generateTenderAddressFromPrivateKey(getKey().getPrivateKeyAsHex()),
                    mAccount.address,
                    false);
            wcClient.approveRequest(id, Lists.newArrayList(keplr));
            return null;
        });
        wcClient.setOnKeplrSignAmino((id, jsonArray) -> {
            mjsonArray = jsonArray;
            runOnUiThread(() -> onShowRawDataDialog(processKeplr(id)));
            return null;
        });
    }

    private ECKey getKey() {
        ECKey mEcKey;
        if (mAccount.fromMnemonic) {
            String entropy = CryptoHelper.doDecryptData(getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
            DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(mAccount, entropy);
            mEcKey = ECKey.fromPrivate(new BigInteger(deterministicKey.getPrivateKeyAsHex(), 16));
        } else {
            String privateKey = CryptoHelper.doDecryptData(getString(R.string.key_private) + mAccount.uuid, mAccount.resource, mAccount.spec);
            mEcKey = ECKey.fromPrivate(new BigInteger(privateKey, 16));
        }
        return mEcKey;
    }

    private List<WCAccount> makeWCAccount() {
        if (mBaseChain.equals(BaseChain.KAVA_MAIN)) {
            return Lists.newArrayList(new WCAccount(459, mAccount.address));
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
            ReqBroadCast tx = MsgGenerator.getWcTrustBroadcaseReq(account, msgList, wcStdSignMsg.fee, wcStdSignMsg.memo, getKey(), wcStdSignMsg.chain_id);
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
        SignModel signModel = new SignModel(mjsonArray.get(2).getAsJsonObject(), MsgGenerator.getWcKeplrBroadcaseReq(getKey(), mjsonArray.get(2).getAsJsonObject()));
        wcClient.approveRequest(id, Lists.newArrayList(signModel));
        Toast.makeText(getBaseContext(), getString(R.string.str_wc_request_responsed), Toast.LENGTH_SHORT).show();
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
        } else {
            wcClient.disconnect();
        }
    }

    private void onInitView(WCPeerMeta meta) {
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

        mWcAccount.setText(mAccount.address);
        wcClient.approveSession(Lists.newArrayList(mAccount.address), 1);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnDisconnect)) {
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
        mDialogWcRawData.setCancelable(true);
        getSupportFragmentManager().beginTransaction().add(mDialogWcRawData, "dialog").commitNowAllowingStateLoss();
    }

    class SignModel {
        TreeMap<String, Object> signed;
        Signature signature;

        public SignModel(JsonObject signed, Signature signature) {
            this.signed = new Gson().fromJson(mjsonArray.get(2).getAsJsonObject(), TreeMap.class);
            this.signature = signature;
        }
    }

}
