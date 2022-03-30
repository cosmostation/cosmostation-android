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
import com.squareup.picasso.Picasso;
import com.trustwallet.walletconnect.WCClient;
import com.trustwallet.walletconnect.models.WCAccount;
import com.trustwallet.walletconnect.models.WCPeerMeta;
import com.trustwallet.walletconnect.models.session.WCSession;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.DeterministicKey;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.cosmos.MsgGenerator;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.StdSignMsg;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.model.type.Signature;
import wannabit.io.cosmostaion.network.req.ReqBroadCast;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WUtil;

public class ConnectWalletActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout mWcLayer, mLoadingLayer;
    private CardView mWcCardView;
    private ImageView mWcImg;
    private TextView mWcName, mWcUrl, mWcAccount;
    private Button mBtnDisconnect;

    private String mWcURL;
    private WCClient wcClient;
    private WCSession wcSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_wallet);
        initView();
        loadInfo();
        initWalletConnect();
    }

    private void loadInfo() {
        mWcURL = getIntent().getStringExtra("wcUrl");
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
        WCPeerMeta meta = new WCPeerMeta("Cosmostation", "https://cosmostation.io", "cosmostation", Lists.newArrayList());
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
            StdSignMsg wcStdSignMsg = new Gson().fromJson(wcSignTransaction.getTransaction(), StdSignMsg.class);
            try {
                JSONObject transactionJson = new JSONObject(wcSignTransaction.getTransaction());
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

                ECKey mEcKey;
                if (mAccount.fromMnemonic) {
                    String entropy = CryptoHelper.doDecryptData(getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
                    DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(mAccount, entropy);
                    mEcKey = ECKey.fromPrivate(new BigInteger(deterministicKey.getPrivateKeyAsHex(), 16));
                } else {
                    String privateKey = CryptoHelper.doDecryptData(getString(R.string.key_private) + mAccount.uuid, mAccount.resource, mAccount.spec);
                    mEcKey = ECKey.fromPrivate(new BigInteger(privateKey, 16));
                }

                Account account = new Account();
                account.accountNumber = Integer.parseInt(wcStdSignMsg.account_number);
                account.sequenceNumber = Integer.parseInt(wcStdSignMsg.sequence);
                ReqBroadCast tx = MsgGenerator.getWcBroadcaseReq(account, msgList, wcStdSignMsg.fee, wcStdSignMsg.memo, mEcKey, wcStdSignMsg.chain_id);
                Gson Presenter = new GsonBuilder().disableHtmlEscaping().create();
                String result = Presenter.toJson(tx);
                wcClient.approveRequest(id, result);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        });
        wcClient.setOnKeplrEnable((id, strings) -> {
            wcClient.approveRequest(id, strings);
            return null;
        });
        wcClient.setOnKeplrGetKey((id, strings) -> {
            ECKey ecKey;
            if (mAccount.fromMnemonic) {
                String entropy = CryptoHelper.doDecryptData(getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
                DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(mAccount, entropy);
                ecKey = ECKey.fromPrivate(new BigInteger(deterministicKey.getPrivateKeyAsHex(), 16));
            } else {
                String privateKey = CryptoHelper.doDecryptData(getString(R.string.key_private) + mAccount.uuid, mAccount.resource, mAccount.spec);
                ecKey = ECKey.fromPrivate(new BigInteger(privateKey, 16));
            }


            KeplrWallet keplr = new KeplrWallet(
                    WUtil.getWalletName(this, mAccount),
                    "secp256k1",
                    ecKey.getPublicKeyAsHex(),
                    WKey.generateTenderAddressFromPrivateKey(ecKey.getPrivateKeyAsHex()),
                    mAccount.address,
                    false);
            wcClient.approveRequest(id, Lists.newArrayList(keplr));
            return null;
        });
        wcClient.setOnKeplrSignAmino((id, jsonArray) -> {
            StdSignMsg signMsg = new Gson().fromJson(jsonArray.get(2), StdSignMsg.class);
            for (int i = 0; i < signMsg.msgs.size(); i++) {
                signMsg.msgs.get(i).value.amount = signMsg.msgs.get(i).value.getCoins();
            }
            ECKey mEcKey;
            if (mAccount.fromMnemonic) {
                String entropy = CryptoHelper.doDecryptData(getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
                DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(mAccount, entropy);
                mEcKey = ECKey.fromPrivate(new BigInteger(deterministicKey.getPrivateKeyAsHex(), 16));
            } else {
                String privateKey = CryptoHelper.doDecryptData(getString(R.string.key_private) + mAccount.uuid, mAccount.resource, mAccount.spec);
                mEcKey = ECKey.fromPrivate(new BigInteger(privateKey, 16));
            }

            Account account = new Account();
            account.accountNumber = Integer.parseInt(signMsg.account_number);
            account.sequenceNumber = Integer.parseInt(signMsg.sequence);
            ReqBroadCast tx = MsgGenerator.getWcBroadcaseReq(account, signMsg.msgs, signMsg.fee, signMsg.memo, mEcKey, signMsg.chain_id);
            Signature signature = tx.tx.signatures.get(0);
            SignModel model3 = new SignModel(signMsg, signature);
            wcClient.approveRequest(id, Lists.newArrayList(model3));
            return null;
        });
    }

    class SignModel {
        StdSignMsg signed;
        Signature signature;

        public SignModel(StdSignMsg signed, Signature signature) {
            this.signed = signed;
            this.signature = signature;
        }
    }

    class KeplrWallet {
        String name;
        String algo;
        String pubKey;
        String address;
        String bech32Address;
        Boolean isNanoLedger;

        public KeplrWallet(String name, String algo, String pubKey, String address, String bech32Address, Boolean isNanoLedger) {
            this.name = name;
            this.algo = algo;
            this.pubKey = pubKey;
            this.address = address;
            this.bech32Address = bech32Address;
            this.isNanoLedger = isNanoLedger;
        }
    }

    private List<WCAccount> makeWCAccount() {
        if (mBaseChain.equals(BaseChain.KAVA_MAIN)) {
            return Lists.newArrayList(new WCAccount(459, mAccount.address));
        }
        return Lists.newArrayList();
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
}
