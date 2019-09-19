package wannabit.io.cosmostaion.activities;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.squareup.moshi.Moshi;

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

public class WalletConnectActivity extends BaseActivity implements Session.Callback {

    private Toolbar     mToolbar;

    private String      mWcURL;
    private Session     mSession;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_connect);
        mToolbar            = findViewById(R.id.tool_bar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mWcURL = getIntent().getStringExtra("wcUrl");
        WLog.w("WalletConnectActivity " + mWcURL);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        onInitSession();
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
    protected void onResume() {
        super.onResume();
    }


    private void onInitSession() {
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
            mSession.addCallback(this);
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
    public void onStatus(@NotNull Session.Status status) {
        WLog.w("status " + status.toString());

        if (status == Session.Status.Connected.INSTANCE) {
            WLog.w("Connected");

        } else if (status == Session.Status.Disconnected.INSTANCE) {
            WLog.w("Disconnected");

        } else if (status == Session.Status.Approved.INSTANCE) {
            WLog.w("Approved");

        } else if (status == Session.Status.Closed.INSTANCE) {
            WLog.w("Closed");

        }


    }

    @Override
    public void onMethodCall(@NotNull Session.MethodCall call) {
        WLog.w("onMethodCall " + call.toString());

        if (call instanceof Session.MethodCall.SessionRequest) {
            WLog.w("SessionRequest");

        } else if (call instanceof Session.MethodCall.SessionUpdate) {
            WLog.w("SessionUpdate");

        } else if (call instanceof Session.MethodCall.Custom) {
            WLog.w("Custom");

        } else if (call instanceof Session.MethodCall.Response) {
            WLog.w("Response");

        }
    }
}
