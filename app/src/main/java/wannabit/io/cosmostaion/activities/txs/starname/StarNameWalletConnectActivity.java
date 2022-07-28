package wannabit.io.cosmostaion.activities.txs.starname;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;
import com.trustwallet.walletconnect.WCClient;
import com.trustwallet.walletconnect.models.WCPeerMeta;
import com.trustwallet.walletconnect.models.session.WCSession;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.AlertDialogUtils;
import wannabit.io.cosmostaion.model.ExportStarName;
import wannabit.io.cosmostaion.utils.WUtil;

public class StarNameWalletConnectActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout mWcLayer, mLoadingLayer;
    private ImageView mWcImg;
    private TextView mWcName, mWcUrl;
    private Button mBtnDisconnect;

    private String mWcURL;
    private WCClient wcClient;
    private WCSession wcSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starname_wallet_connect);
        initView();
        loadInfo();
        initWalletConnect();
    }

    private void initWalletConnect() {
        OkHttpClient client = new OkHttpClient.Builder().pingInterval(1000, TimeUnit.MILLISECONDS).build();
        wcClient = new WCClient(new GsonBuilder(), client);
        WCPeerMeta meta = new WCPeerMeta("Cosmostation", "https://cosmostation.io", "cosmostation", Lists.newArrayList());
        wcSession = WCSession.Companion.from(mWcURL);
        wcClient.connect(wcSession, meta, UUID.randomUUID().toString(), null);
        wcClient.setOnDisconnect((code, reason) -> {
            runOnUiThread(() -> {
                if (!isFinishing()) onBackPressed();
            });

            return null;
        });
        wcClient.setOnSessionRequest((id, wcPeerMeta) -> {
            runOnUiThread(() -> onInitView(wcPeerMeta));
            return null;
        });
    }

    private void initView() {
        mWcLayer = findViewById(R.id.wc_layer);
        mLoadingLayer = findViewById(R.id.loading_layer);
        mWcImg = findViewById(R.id.wc_img);
        mWcName = findViewById(R.id.wc_name);
        mWcUrl = findViewById(R.id.wc_url);
        mBtnDisconnect = findViewById(R.id.btn_disconnect);
        mBtnDisconnect.setOnClickListener(this);

        setSupportActionBar(findViewById(R.id.tool_bar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void loadInfo() {
        mWcURL = getIntent().getStringExtra("wcUrl");
        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
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
        Picasso.get()
                .load(meta.getIcons().get(0))
                .fit()
                .placeholder(R.drawable.chain_starname)
                .into(mWcImg);
        mWcName.setText(meta.getName());
        mWcUrl.setText(meta.getUrl());
        mWcLayer.setVisibility(View.VISIBLE);
        mLoadingLayer.setVisibility(View.GONE);

        ArrayList<Account> allAccounts = getBaseDao().onSelectAccounts();
        ExportStarName toExport = WUtil.getExportResource(allAccounts);
        String jsonData = new Gson().toJson(toExport);

        if (!meta.getUrl().equals("https://app.starname.me")) {
            onBackPressed();
        } else if (toExport.addresses.size() < 1) {
            Toast.makeText(getBaseContext(), R.string.error_no_address_export, Toast.LENGTH_SHORT).show();
        } else {
            AlertDialogUtils.showDoubleButtonDialog(this, getString(R.string.str_starname_walletconnect_alert_title),
                    String.format(getString(R.string.str_starname_walletconnect_alert_msg2), String.valueOf(toExport.addresses.size())),
                    getString(R.string.str_cancel), null,
                    getString(R.string.str_continue), view -> onExportAddresses(jsonData), true);
        }
    }

    public void onExportAddresses(String jsonData) {
        if (wcSession != null) {
            wcClient.approveSession(Lists.newArrayList(jsonData), -1);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnDisconnect)) {
            Toast.makeText(getBaseContext(), getString(R.string.str_wc_not_connected), Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
    }
}
