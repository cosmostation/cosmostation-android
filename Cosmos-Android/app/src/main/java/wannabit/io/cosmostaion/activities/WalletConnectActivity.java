package wannabit.io.cosmostaion.activities;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.moshi.Moshi;
import com.squareup.picasso.Picasso;

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
import wannabit.io.cosmostaion.utils.WLog;

public class WalletConnectActivity extends BaseActivity implements View.OnClickListener {

    private final static int    MSG_WC_CONNECTED  = 0;
    private final static int    MSG_WC_DISCONNECTED  = 1;
    private final static int    MSG_WC_APPROVED  = 2;
    private final static int    MSG_WC_CLOSED  = 3;
    private final static int    MSG_WC_SESSION_REQUESTED  = 4;


    private Toolbar         mToolbar;
    private RelativeLayout  mWcLayer, mLoadingLayer;
    private ImageView       mWcImg;
    private TextView        mWcName, mWcUrl, mWcAccount;
    private Button          mBtnDisconnect;

    private String          mWcURL;
    private Session         mSession;
    private WcThread        mWcThread;


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
                        WLog.w("onStatus " + status.toString());
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
                        WLog.w("onMethodCall " + call.toString());
                        if (call instanceof Session.MethodCall.SessionRequest) {
                            WLog.w("SessionRequest");
                            Message msg = mHandler.obtainMessage();
                            msg.what = MSG_WC_SESSION_REQUESTED;
                            msg.obj = call;
                            mHandler.sendMessage(msg);

                        } else if (call instanceof Session.MethodCall.SessionUpdate) {
                            WLog.w("SessionUpdate");

                        } else if (call instanceof Session.MethodCall.Custom) {
                            WLog.w("Custom");

                        } else if (call instanceof Session.MethodCall.Response) {
                            WLog.w("Response");

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
}
