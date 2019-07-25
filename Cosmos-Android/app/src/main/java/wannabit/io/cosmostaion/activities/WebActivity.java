package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;

public class WebActivity extends BaseActivity {

    private WebView     mWebview;
    private String      mTxid, mVoteId, mAddress;
    private boolean     mGoMain;
    private BaseChain   mBasechain;
    private FloatingActionButton mShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        mWebview = findViewById(R.id.webView);
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.getSettings().setDomStorageEnabled(true);
        mShare = findViewById(R.id.btn_floating);

        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        });

        mTxid  = getIntent().getStringExtra("txid");
        mVoteId = getIntent().getStringExtra("voteId");
        mAddress = getIntent().getStringExtra("address");
        mGoMain = getIntent().getBooleanExtra("goMain", false);
        mBasechain = BaseChain.getChain(getIntent().getStringExtra("chain"));





        if (mBasechain.equals(BaseChain.COSMOS_MAIN)) {
            mShare.setBackgroundTintList(getResources().getColorStateList(R.color.colorAtom));
            if (!TextUtils.isEmpty(mTxid))
                mWebview.loadUrl("https://www.mintscan.io/txs/"+mTxid);
            else if (!TextUtils.isEmpty(mVoteId))
                mWebview.loadUrl("https://www.mintscan.io/proposals/"+mVoteId);
            else if (!TextUtils.isEmpty(mAddress))
                mWebview.loadUrl("https://www.mintscan.io/account/"+mAddress);
            else
                mWebview.loadUrl("https://www.mintscan.io");

        } else if (mBasechain.equals(BaseChain.IRIS_MAIN)) {
            mShare.setBackgroundTintList(getResources().getColorStateList(R.color.colorIris));
            if (!TextUtils.isEmpty(mTxid))
                mWebview.loadUrl("https://irishub.mintscan.io/txs/"+mTxid);
            else if (!TextUtils.isEmpty(mVoteId))
                mWebview.loadUrl("https://irishub.mintscan.io/proposals/"+mVoteId);
            else if (!TextUtils.isEmpty(mAddress))
                mWebview.loadUrl("https://irishub.mintscan.io/account/"+mAddress);
            else
                mWebview.loadUrl("https://irishub.mintscan.io");
        }

        mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, mWebview.getUrl());
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent, "send"));
            }
        });
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
