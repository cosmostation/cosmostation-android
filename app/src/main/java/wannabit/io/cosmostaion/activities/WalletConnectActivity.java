package wannabit.io.cosmostaion.activities;

import static wannabit.io.cosmostaion.model.type.BnbParam.TYPE_CANCEL_ORDER;
import static wannabit.io.cosmostaion.model.type.BnbParam.TYPE_NEW_ORDER;
import static wannabit.io.cosmostaion.model.type.BnbParam.TYPE_TRANSFER_ORDER;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.binance.dex.api.client.domain.OrderSide;
import com.binance.dex.api.client.domain.OrderType;
import com.binance.dex.api.client.domain.TimeInForce;
import com.binance.dex.api.client.encoding.Crypto;
import com.binance.dex.api.client.encoding.EncodeUtils;
import com.binance.dex.api.client.encoding.message.BinanceDexTransactionMessage;
import com.binance.dex.api.client.encoding.message.CancelOrderMessage;
import com.binance.dex.api.client.encoding.message.NewOrderMessage;
import com.binance.dex.api.client.encoding.message.SignData;
import com.binance.dex.api.client.encoding.message.TransactionRequestAssembler;
import com.binance.dex.api.client.encoding.message.TransferMessage;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;
import com.trustwallet.walletconnect.WCClient;
import com.trustwallet.walletconnect.models.WCPeerMeta;
import com.trustwallet.walletconnect.models.binance.WCBinanceOrder;
import com.trustwallet.walletconnect.models.session.WCSession;

import org.apache.commons.lang3.StringUtils;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.DeterministicKey;

import java.math.BigInteger;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dialog.Dialog_Wc_Cancel;
import wannabit.io.cosmostaion.dialog.Dialog_Wc_Trade;
import wannabit.io.cosmostaion.dialog.Dialog_Wc_Transfer;
import wannabit.io.cosmostaion.model.type.BnbParam;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;

public class WalletConnectActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout mWcLayer, mLoadingLayer;
    private ImageView mWcImg;
    private TextView mWcName, mWcUrl, mWcAccount;
    private Button mBtnDisconnect;
    private Dialog_Wc_Trade mDialogTrade;
    private Dialog_Wc_Cancel mDialogCancel;
    private Dialog_Wc_Transfer mDialogTransfer;

    private String mWcURL;
    private WCClient wcClient;
    private WCSession wcSession;
    private WCBinanceOrder mOrder;
    private Long mOrderId;

    private ECKey mEcKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_connect);
        initView();
        loadInfo();
        initWalletConnect();
    }

    private void loadInfo() {
        mWcURL = getIntent().getStringExtra("wcUrl");
        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
    }

    private void initView() {
        mWcLayer = findViewById(R.id.wc_layer);
        mLoadingLayer = findViewById(R.id.loading_layer);
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
        OkHttpClient client = new OkHttpClient.Builder().pingInterval(1000, TimeUnit.MILLISECONDS).build();
        wcClient = new WCClient(new GsonBuilder(), client);
        WCPeerMeta meta = new WCPeerMeta("Cosmostation", "https://cosmostation.io", "cosmostation", Lists.newArrayList());
        wcSession = WCSession.Companion.from(mWcURL);
        wcClient.connect(wcSession, meta, UUID.randomUUID().toString(), null);
        wcClient.setOnDisconnect((code, reason) -> {
            runOnUiThread(() -> {
                Toast.makeText(getBaseContext(), getString(R.string.str_wc_not_connected), Toast.LENGTH_SHORT).show();
                if (!isFinishing()) onBackPressed();
            });
            return null;
        });
        wcClient.setOnSessionRequest((id, wcPeerMeta) -> {
            runOnUiThread(() -> onInitView(wcPeerMeta));
            return null;
        });
        wcClient.setOnBnbTransfer((id, order) -> {
            runOnUiThread(() -> {
                onShowTransferDialog(processBnb(id, order));
            });
            return null;
        });
        wcClient.setOnBnbTxConfirm((id, order) -> {
            runOnUiThread(() -> {
                Toast.makeText(getBaseContext(), getString(R.string.str_wc_sign_result_msg), Toast.LENGTH_SHORT).show();
            });
            new Thread(new ConfirmRunnable()).start();
            return null;
        });
        wcClient.setOnBnbTrade((id, order) -> {
            runOnUiThread(() -> {
                onShowNewOrderDialog(processBnb(id, order));
            });
            return null;
        });
        wcClient.setOnBnbCancel((id, order) -> {
            runOnUiThread(() -> {
                onShowCancelDialog(processBnb(id, order));
            });
            return null;
        });
    }

    private Bundle processBnb(Long id, WCBinanceOrder order) {
        onDismissDialog();
        mOrderId = id;
        mOrder = order;
        if (order.getMsgs() != null && order.getMsgs().size() > 0) {
            Bundle bundle = new Bundle();
            bundle.putLong("id", id);
            bundle.putString("param", new Gson().toJson(order));
            bundle.putString("memo", order.getMemo());
            return bundle;
        } else {
            return null;
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
        if (wcSession != null && wcClient.isConnected()) {
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
        wcClient.approveSession(Lists.newArrayList(mAccount.address), 4);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnDisconnect)) {
            onBackPressed();
        }
    }

    private void onShowNewOrderDialog(Bundle bundle) {
        mDialogTrade = Dialog_Wc_Trade.newInstance(bundle);
        mDialogTrade.setCancelable(true);
        getSupportFragmentManager().beginTransaction().add(mDialogTrade, "dialog").commitNowAllowingStateLoss();
    }

    private void onShowCancelDialog(Bundle bundle) {
        mDialogCancel = Dialog_Wc_Cancel.newInstance(bundle);
        mDialogCancel.setCancelable(true);
        getSupportFragmentManager().beginTransaction().add(mDialogCancel, "dialog").commitNowAllowingStateLoss();
    }

    private void onShowTransferDialog(Bundle bundle) {
        mDialogTransfer = Dialog_Wc_Transfer.newInstance(bundle);
        mDialogTransfer.setCancelable(true);
        getSupportFragmentManager().beginTransaction().add(mDialogTransfer, "dialog").commitNowAllowingStateLoss();
    }

    private void onDismissDialog() {
        if (mDialogTrade != null && mDialogTrade.isAdded()) mDialogTrade.dismiss();
        if (mDialogCancel != null && mDialogCancel.isAdded()) mDialogCancel.dismiss();
        if (mDialogTransfer != null && mDialogTransfer.isAdded()) mDialogTransfer.dismiss();
    }

    public void onBnbSign(long id) {
        if (id == mOrderId) {
            new Thread(new SignRunnable()).start();
        }
    }

    class SignRunnable implements Runnable {
        @Override
        public void run() {
            WLog.w("SignRunnable ");
            try {
                if (mAccount.fromMnemonic) {
                    String entropy = CryptoHelper.doDecryptData(getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
                    DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(mAccount, entropy);
                    mEcKey = ECKey.fromPrivate(new BigInteger(deterministicKey.getPrivateKeyAsHex(), 16));
                } else {
                    String privateKey = CryptoHelper.doDecryptData(getString(R.string.key_private) + mAccount.uuid, mAccount.resource, mAccount.spec);
                    mEcKey = ECKey.fromPrivate(new BigInteger(privateKey, 16));
                }
                BnbParam bnbParam = new Gson().fromJson(new Gson().toJson(mOrder), BnbParam.class);

                BinanceDexTransactionMessage bdtm[] = new BinanceDexTransactionMessage[1];

                if (bnbParam.getMsgType() == TYPE_NEW_ORDER) {
                    NewOrderMessage bean = NewOrderMessage.newBuilder()
                            .setSender(bnbParam.msgs.get(0).sender)
                            .setId(bnbParam.msgs.get(0).id)
                            .setSymbol(bnbParam.msgs.get(0).symbol)
                            .setOrderType(OrderType.fromValue(bnbParam.msgs.get(0).orderType))
                            .setSide(OrderSide.fromValue(bnbParam.msgs.get(0).side))
                            .setPrice(TransactionRequestAssembler.longToDouble(bnbParam.msgs.get(0).price))
                            .setQuantity(TransactionRequestAssembler.longToDouble(bnbParam.msgs.get(0).quantity))
                            .setTimeInForce(TimeInForce.fromValue(bnbParam.msgs.get(0).timeInforce)).build();
                    bdtm[0] = bean;

                } else if (bnbParam.getMsgType() == TYPE_CANCEL_ORDER) {
                    CancelOrderMessage bean = new CancelOrderMessage();
                    bean.setRefId(bnbParam.msgs.get(0).refid);
                    bean.setSender(bnbParam.msgs.get(0).sender);
                    bean.setSymbol(bnbParam.msgs.get(0).symbol);
                    bdtm[0] = bean;

                } else if (bnbParam.getMsgType() == TYPE_TRANSFER_ORDER) {
                    JsonObject json = new Gson().fromJson(new Gson().toJson(mOrder), JsonObject.class);
                    TransferMessage bean = new Gson().fromJson(json.getAsJsonArray("msgs").get(0), TransferMessage.class);
                    bdtm[0] = bean;

                } else {
                    return;
                }


                SignData sd = new SignData();
                sd.setChainId(bnbParam.chain_id);
                sd.setAccountNumber("" + bnbParam.account_number);
                sd.setSequence("" + bnbParam.sequence);
                sd.setMemo(mOrder.getMemo());
                sd.setMsgs(bdtm);
                sd.setSource("" + bnbParam.source);

                byte[] signatureBytes = Crypto.sign(EncodeUtils.toJsonEncodeBytes(sd), mEcKey.getPrivateKeyAsHex());
                byte[] publicKeyBytes = mEcKey.decompress().getPubKey();

                WcCallBack callBack = new WcCallBack();
                callBack.signature = EncodeUtils.bytesToHex(signatureBytes);
                callBack.publicKey = EncodeUtils.bytesToHex(publicKeyBytes);

                String result = new Gson().toJson(callBack);
                wcClient.approveRequest(mOrderId, result);

            } catch (Exception e) {
                WLog.e("Ex " + e.getMessage());
            }

        }
    }

    class ConfirmRunnable implements Runnable {
        @Override
        public void run() {
            WLog.w("ConfirmRunnable ");
            wcClient.approveRequest(mOrderId, StringUtils.EMPTY);
        }
    }
}

class WcCallBack {
    public String signature;
    public String publicKey;
}