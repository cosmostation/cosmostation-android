package wannabit.io.cosmostaion.activities.chains.starname;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;
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
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.Dialog_StarName_Export_Confirm;
import wannabit.io.cosmostaion.model.ExportStarName;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class StarNameWalletConnectActivity  extends BaseActivity implements View.OnClickListener {

    private final static int        MSG_WC_CONNECTED            = 0;
    private final static int        MSG_WC_DISCONNECTED         = 1;
    private final static int        MSG_WC_APPROVED             = 2;
    private final static int        MSG_WC_CLOSED               = 3;
    private final static int        MSG_WC_SESSION_REQUESTED    = 4;
    private final static int        MSG_ACTIVITY_BACK           = 5;

    private Toolbar         mToolbar;
    private RelativeLayout  mWcLayer, mLoadingLayer;
    private ImageView       mWcImg;
    private TextView        mWcName, mWcUrl;
    private Button          mBtnDisconnect;

    private String                          mWcURL;
    private Session                         mSession;
    private StarNameWcThread                mWcThread;

    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_WC_DISCONNECTED:
                    mSession.clearCallbacks();
                    Toast.makeText(getBaseContext(), getString(R.string.str_wc_disconnected), Toast.LENGTH_SHORT).show();
                    if (!isFinishing()) onBackPressed();
                    break;

                case MSG_WC_CLOSED:
                    Toast.makeText(getBaseContext(), getString(R.string.str_wc_closed), Toast.LENGTH_SHORT).show();
                    if (!isFinishing()) onBackPressed();
                    break;

                case MSG_WC_SESSION_REQUESTED:
                    Session.MethodCall.SessionRequest sessionRequest = (Session.MethodCall.SessionRequest)msg.obj;
                    onInitView(sessionRequest);
                    break;

                case MSG_ACTIVITY_BACK:
                    onBackPressed();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starname_wallet_connect);
        mToolbar            = findViewById(R.id.tool_bar);
        mWcLayer            = findViewById(R.id.wc_layer);
        mLoadingLayer       = findViewById(R.id.loading_layer);
        mWcImg              = findViewById(R.id.wc_img);
        mWcName             = findViewById(R.id.wc_name);
        mWcUrl              = findViewById(R.id.wc_url);
        mBtnDisconnect      = findViewById(R.id.btn_disconnect);
        mBtnDisconnect.setOnClickListener(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mWcURL = getIntent().getStringExtra("wcUrl");
        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);

        mWcThread = new StarNameWcThread() ;
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
                .placeholder(R.drawable.chain_starname)
                .into(mWcImg);
        mWcName.setText(sessionRequest.getPeer().getMeta().getName());
        mWcUrl.setText(sessionRequest.getPeer().getMeta().getUrl());
        mWcLayer.setVisibility(View.VISIBLE);
        mLoadingLayer.setVisibility(View.GONE);

        ArrayList<Account> allAccounts = getBaseDao().onSelectAccounts();
        ExportStarName toExport = WUtil.getExportResource(allAccounts);
        String jsonData = new Gson().toJson(toExport);
//        WLog.w("allAccounts "+ allAccounts.size());
//        WLog.w("jsonData " + jsonData);


        if (!sessionRequest.getPeer().getMeta().getUrl().equals("https://app.starname.me")) {
            onBackPressed();
        } else if (toExport.addresses.size() < 1) {
            Toast.makeText(getBaseContext(), R.string.error_no_address_export, Toast.LENGTH_SHORT).show();
        } else {
            Bundle bundle = new Bundle();
            WLog.w("Bundle " + String.valueOf(toExport.addresses.size()));
            bundle.putString("msg", String.valueOf(toExport.addresses.size()));
            bundle.putString("jsonData", jsonData);
            Dialog_StarName_Export_Confirm exportDialog= Dialog_StarName_Export_Confirm.newInstance(bundle);
            exportDialog.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(exportDialog, "dialog").commitNowAllowingStateLoss();
        }
    }

    public void onExportAddresses(String jsonData) {
        List<String> approv = new ArrayList<>();
        approv.add(jsonData);
        if (mSession != null) {
            mSession.approve(approv, -1);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnDisconnect)) {
            onBackPressed();
        }
    }

    class StarNameWcThread extends Thread {
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
                        if (status == Session.Status.Disconnected.INSTANCE) {
                            Message msg = mHandler.obtainMessage();
                            msg.what = MSG_WC_DISCONNECTED;
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
                        }
                    }
                });

                mSession.init();
                Thread.sleep(2000);


            } catch (Exception e) {
                Message msg = mHandler.obtainMessage();
                msg.what = MSG_ACTIVITY_BACK;
                mHandler.sendMessage(msg);
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
