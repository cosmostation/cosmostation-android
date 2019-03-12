package wannabit.io.cosmostaion.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;

public class WebActivity extends BaseActivity {

    private WebView     mWebview;
    private String      mTxid, mVoteId;
    private boolean         mGoMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        mWebview = findViewById(R.id.webView);
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.getSettings().setDomStorageEnabled(true);

        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        });

        mTxid  = getIntent().getStringExtra("txid");
        mVoteId = getIntent().getStringExtra("voteId");
        mGoMain = getIntent().getBooleanExtra("goMain", true);

        if (!TextUtils.isEmpty(mTxid))
            mWebview.loadUrl("https://www.mintscan.io/txs/"+mTxid);
        else if (!TextUtils.isEmpty(mVoteId))
            mWebview.loadUrl("https://www.mintscan.io/proposals/"+mVoteId);
        else
            mWebview.loadUrl("https://www.mintscan.io");
    }

    @Override
    public void onBackPressed() {
        if(mWebview.canGoBack()) {
            mWebview.goBack();
        } else {
            if(mGoMain) {
                onStartMainActivity();
            } else {
                super.onBackPressed();
            }

        }
    }
}
