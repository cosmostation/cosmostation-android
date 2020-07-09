package wannabit.io.cosmostaion.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
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
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.moshi.Moshi;
import com.squareup.picasso.Picasso;

import org.bitcoinj.crypto.DeterministicKey;
import org.jetbrains.annotations.NotNull;
import org.walletconnect.Session;
import org.walletconnect.impls.FileWCSessionStore;
import org.walletconnect.impls.MoshiPayloadAdapter;
import org.walletconnect.impls.OkHttpTransport;
import org.walletconnect.impls.WCSession;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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

import static wannabit.io.cosmostaion.model.type.BnbParam.TYPE_CANCEL_ORDER;
import static wannabit.io.cosmostaion.model.type.BnbParam.TYPE_NEW_ORDER;
import static wannabit.io.cosmostaion.model.type.BnbParam.TYPE_TRANSFER_ORDER;

public class WalletConnectActivity extends BaseActivity implements View.OnClickListener {

    private final static int        MSG_WC_CONNECTED  = 0;
    private final static int        MSG_WC_DISCONNECTED  = 1;
    private final static int        MSG_WC_APPROVED  = 2;
    private final static int        MSG_WC_CLOSED  = 3;
    private final static int        MSG_WC_SESSION_REQUESTED  = 4;
    private final static int        MSG_WC_SESSION_BNB_SIGN  = 5;
    private final static int        MSG_WC_SESSION_BNB_CONFIRM  = 6;

    private final static String     METHOD_BNB_SIGN = "bnb_sign";
    private final static String     METHOD_BNB_TX_CONFIRMATION = "bnb_tx_confirmation";


    private Toolbar                     mToolbar;
    private RelativeLayout              mWcLayer, mLoadingLayer;
    private ImageView                   mWcImg;
    private TextView                    mWcName, mWcUrl, mWcAccount;
    private Button                      mBtnDisconnect;
    private Dialog_Wc_Trade             mDialogTrade;
    private Dialog_Wc_Cancel            mDialogCancel;
    private Dialog_Wc_Transfer          mDialogTransfer;

    private String                      mWcURL;
    private Session                     mSession;
    private WcThread                    mWcThread;
    private Session.MethodCall.Custom   mCustom;


    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_WC_CONNECTED:
                    Toast.makeText(getBaseContext(), getString(R.string.str_wc_connected), Toast.LENGTH_SHORT).show();
                    break;

                case MSG_WC_DISCONNECTED:
                    mSession.clearCallbacks();
                    Toast.makeText(getBaseContext(), getString(R.string.str_wc_disconnected), Toast.LENGTH_SHORT).show();
                    if (!isFinishing()) onBackPressed();
                    break;

                case MSG_WC_APPROVED:
                    Toast.makeText(getBaseContext(), getString(R.string.str_wc_approved), Toast.LENGTH_SHORT).show();
                    mWcAccount.setText(mAccount.address);
                    break;

                case MSG_WC_CLOSED:
                    Toast.makeText(getBaseContext(), getString(R.string.str_wc_closed), Toast.LENGTH_SHORT).show();
                    if (!isFinishing()) onBackPressed();
                    break;

                case MSG_WC_SESSION_REQUESTED:
                    Session.MethodCall.SessionRequest sessionRequest = (Session.MethodCall.SessionRequest)msg.obj;
                    onInitView(sessionRequest);
                    break;

                case MSG_WC_SESSION_BNB_SIGN:
                    onDismissDialog();
                    mCustom = (Session.MethodCall.Custom)msg.obj;
                    if (mCustom.getParams() != null && mCustom.getParams().size() > 0) {
//                        WLog.w("mCustom.getParams() " + mCustom.getParams().get(0).toString());
                        //TODO decoding rawSingData with memo bug!
                        String rawString = mCustom.getParams().get(0).toString();
                        String memo = "";
                        int start = rawString.indexOf("memo=");
                        int end = rawString.indexOf(",", start);
                        if (start > 0) {
                            String front = rawString.substring(0, start - 1);
                            String back = rawString.substring(end + 1, rawString.length());
                            memo = rawString.substring(start, end).replace("memo=", "");
                            rawString = front + back;
                        }
//                        WLog.w("rawString " + rawString);
//                        WLog.w("memo " + memo);
                        BnbParam bnbParam = new Gson().fromJson(rawString, BnbParam.class);
                        Bundle bundle = new Bundle();
                        bundle.putLong("id", mCustom.getId());
                        bundle.putString("param", rawString);
                        bundle.putString("memo", memo);
                        if (bnbParam.getMsgType() == TYPE_NEW_ORDER) {
                            onShowNewOrderDialog(bundle);
                        } else if (bnbParam.getMsgType() == TYPE_CANCEL_ORDER) {
                            onShowCancelDialog(bundle);
                        } else if (bnbParam.getMsgType() == TYPE_TRANSFER_ORDER) {
                            onShowTransferDialog(bundle);
                        }
                    }
                    break;

                case MSG_WC_SESSION_BNB_CONFIRM:
                    Toast.makeText(getBaseContext(), getString(R.string.str_wc_sign_result_msg), Toast.LENGTH_SHORT).show();
                    new Thread(new ConfirmRunnable()).start();
                    break;
            }
            super.handleMessage(msg);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_connect);
        mToolbar            = findViewById(R.id.tool_bar);
        mWcLayer            = findViewById(R.id.wc_layer);
        mLoadingLayer       = findViewById(R.id.loading_layer);
        mWcImg              = findViewById(R.id.wc_img);
        mWcName             = findViewById(R.id.wc_name);
        mWcUrl              = findViewById(R.id.wc_url);
        mWcAccount          = findViewById(R.id.wc_address);
        mBtnDisconnect      = findViewById(R.id.btn_disconnect);
        mBtnDisconnect.setOnClickListener(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mWcURL = getIntent().getStringExtra("wcUrl");
        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);

        mWcThread = new WcThread() ;
        mWcThread.start() ;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWcThread != null){
            mWcThread.interrupt();
            mWcThread = null;
        }
    }

    private void onInitView(Session.MethodCall.SessionRequest sessionRequest) {
        Picasso.get()
                .load(sessionRequest.getPeer().getMeta().getIcons().get(0))
                .fit()
                .placeholder(R.drawable.validator_none_img)
                .into(mWcImg);
        mWcName.setText(sessionRequest.getPeer().getMeta().getName());
        mWcUrl.setText(sessionRequest.getPeer().getMeta().getUrl());
        mWcLayer.setVisibility(View.VISIBLE);
        mLoadingLayer.setVisibility(View.GONE);

    }


    @Override
    public void onClick(View v) {
         if (v.equals(mBtnDisconnect)) {
             onBackPressed();
         }
    }

    private void onShowNewOrderDialog(Bundle bundle) {
        mDialogTrade= Dialog_Wc_Trade.newInstance(bundle);
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
        if (id == mCustom.getId()) {
            new Thread(new SignRunnable()).start();
        }
    }


    class WcThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                File fleDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/build/tmp");
                if(!fleDir.exists()){
                    fleDir.mkdirs();
                }
                File file = new File(fleDir, "wc_store.json");
                file.createNewFile();

                Session.Config config = Session.Config.Companion.fromWCUri(mWcURL);
                Moshi moshi = new Moshi.Builder().build();
                MoshiPayloadAdapter adapter = new MoshiPayloadAdapter(moshi);
                FileWCSessionStore fileStore = new FileWCSessionStore(file, moshi);
                OkHttpClient client = new OkHttpClient.Builder().pingInterval(1000, TimeUnit.MILLISECONDS).build();
                OkHttpTransport.Builder transportBuilder = new OkHttpTransport.Builder(client, moshi);
                Session.PeerMeta peerMeta = new Session.PeerMeta(null, null, null, null);
                mSession = new WCSession(config, adapter,fileStore, transportBuilder, peerMeta, null);
                mSession.addCallback(new Session.Callback() {
                    @Override
                    public void onStatus(@NotNull Session.Status status) {
                        if (status == Session.Status.Connected.INSTANCE) {
                            Message msg = mHandler.obtainMessage();
                            msg.what = MSG_WC_CONNECTED;
                            mHandler.sendMessage(msg);
                        } else if (status == Session.Status.Disconnected.INSTANCE) {
                            Message msg = mHandler.obtainMessage();
                            msg.what = MSG_WC_DISCONNECTED;
                            mHandler.sendMessage(msg);
                        } else if (status == Session.Status.Approved.INSTANCE) {
                            Message msg = mHandler.obtainMessage();
                            msg.what = MSG_WC_APPROVED;
                            mHandler.sendMessage(msg);
                        } else if (status == Session.Status.Closed.INSTANCE) {
                            Message msg = mHandler.obtainMessage();
                            msg.what = MSG_WC_CLOSED;
                            mHandler.sendMessage(msg);
                        }
                    }

                    @Override
                    public void onMethodCall(@NotNull Session.MethodCall call) {
                        if (call instanceof Session.MethodCall.SessionRequest) {
                            Message msg = mHandler.obtainMessage();
                            msg.what = MSG_WC_SESSION_REQUESTED;
                            msg.obj = call;
                            mHandler.sendMessage(msg);

                        } else if (call instanceof Session.MethodCall.SessionUpdate) {

                        } else if (call instanceof Session.MethodCall.Custom) {
                            Session.MethodCall.Custom custom = (Session.MethodCall.Custom)call;
                            if (custom.getMethod().equals(METHOD_BNB_SIGN)) {
                                Message msg = mHandler.obtainMessage();
                                msg.what = MSG_WC_SESSION_BNB_SIGN;
                                msg.obj = call;
                                mHandler.sendMessage(msg);

                            } else if (custom.getMethod().equals(METHOD_BNB_TX_CONFIRMATION)) {
                                Message msg = mHandler.obtainMessage();
                                msg.what = MSG_WC_SESSION_BNB_CONFIRM;
                                msg.obj = call;
                                mHandler.sendMessage(msg);
                            }

                        } else if (call instanceof Session.MethodCall.Response) {

                        }
                    }
                });

                mSession.init();
                Thread.sleep(2000);
                List<String> approv = new ArrayList<>();
                approv.add(mAccount.address);
                mSession.approve(approv, 4);


            } catch (Exception e) {
                onBackPressed();
                return;
            }
        }

        @Override
        public void interrupt() {
            super.interrupt();
            if (mSession != null) {
                mSession.kill();
            }
        }
    }

    class SignRunnable implements Runnable {
        @Override
        public void run() {
            WLog.w("SignRunnable ");
            try {
                String entropy = CryptoHelper.doDecryptData(getBaseContext().getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
                DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(BaseChain.getChain(mAccount.baseChain), entropy, Integer.parseInt(mAccount.path), mAccount.newBip44);

                String rawString = mCustom.getParams().get(0).toString();
                String memo = "";
                int start = rawString.indexOf("memo=");
                int end = rawString.indexOf(",", start);
                if (start > 0) {
                    String front = rawString.substring(0, start - 1);
                    String back = rawString.substring(end + 1, rawString.length());
                    memo = rawString.substring(start, end).replace("memo=", "");
                    rawString = front + back;
                }
                BnbParam bnbParam = new Gson().fromJson(rawString, BnbParam.class);
                BinanceDexTransactionMessage bdtm[] = new BinanceDexTransactionMessage[1];

                if (bnbParam.getMsgType() == TYPE_NEW_ORDER) {
                    NewOrderMessage bean = NewOrderMessage.newBuilder()
                            .setSender(bnbParam.msgs.get(0).sender)
                            .setId(bnbParam.msgs.get(0).id)
                            .setSymbol(bnbParam.msgs.get(0).symbol)
                            .setOrderType(OrderType.fromValue(bnbParam.msgs.get(0).ordertype))
                            .setSide(OrderSide.fromValue(bnbParam.msgs.get(0).side))
                            .setPrice(TransactionRequestAssembler.longToDouble(bnbParam.msgs.get(0).price))
                            .setQuantity(TransactionRequestAssembler.longToDouble(bnbParam.msgs.get(0).quantity))
                            .setTimeInForce(TimeInForce.fromValue(bnbParam.msgs.get(0).timeinforce)).build();
                    bdtm[0] = bean;

                } else if (bnbParam.getMsgType() == TYPE_CANCEL_ORDER) {
                    CancelOrderMessage bean = new CancelOrderMessage();
                    bean.setRefId(bnbParam.msgs.get(0).refid);
                    bean.setSender(bnbParam.msgs.get(0).sender);
                    bean.setSymbol(bnbParam.msgs.get(0).symbol);
                    bdtm[0] = bean;

                } else if (bnbParam.getMsgType() == TYPE_TRANSFER_ORDER) {
                    JsonObject json = new Gson().fromJson(rawString, JsonObject.class);
                    TransferMessage bean =  new Gson().fromJson(json.getAsJsonArray("msgs").get(0), TransferMessage.class);
                    bdtm[0] = bean;

                } else {
                    return;
                }


                SignData sd = new SignData();
                sd.setChainId(bnbParam.chain_id);
                sd.setAccountNumber(""+bnbParam.account_number);
                sd.setSequence(""+bnbParam.sequence);
                sd.setMemo(memo);
                sd.setMsgs(bdtm);
                sd.setSource(""+bnbParam.source);

                byte[] signatureBytes = Crypto.sign(EncodeUtils.toJsonEncodeBytes(sd), deterministicKey.getPrivateKeyAsHex());
                byte[] publicKeyBytes = deterministicKey.decompress().getPubKey();

                WcCallBack callBack = new WcCallBack();
                callBack.signature = EncodeUtils.bytesToHex(signatureBytes);
                callBack.publicKey = EncodeUtils.bytesToHex(publicKeyBytes);

                String result = new Gson().toJson(callBack);
                mSession.approveRequest(mCustom.getId(), result);

            } catch (Exception e) {
                WLog.w("Ex " + e.getMessage());
            }

        }
    }

    class ConfirmRunnable implements Runnable {
        @Override
        public void run() {
            WLog.w("ConfirmRunnable ");
            mSession.approveRequest(mCustom.getId(), "");
        }
    }
}

class WcCallBack {
    public String signature;
    public String publicKey;
}


