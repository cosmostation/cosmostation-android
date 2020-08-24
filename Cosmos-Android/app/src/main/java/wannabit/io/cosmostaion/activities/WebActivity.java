package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;

public class WebActivity extends BaseActivity {

    private WebView     mWebview;
    private String      mTxid, mVoteId, mAddress, mAsset;
    private boolean     mGoMain;
    private BaseChain   mBasechain;
    private FloatingActionButton mShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        mWebview = findViewById(R.id.webView);
        mShare = findViewById(R.id.btn_floating);
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.getSettings().setDomStorageEnabled(true);
        mWebview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
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
        mAsset = getIntent().getStringExtra("asset");
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

        } else if (mBasechain.equals(BaseChain.BNB_MAIN)) {
            mShare.setBackgroundTintList(getResources().getColorStateList(R.color.colorBnb));
            if (!TextUtils.isEmpty(mTxid))
                mWebview.loadUrl("https://binance.mintscan.io/txs/"+mTxid);
            else if (!TextUtils.isEmpty(mAddress))
                mWebview.loadUrl("https://binance.mintscan.io/account/"+mAddress);
            else if (!TextUtils.isEmpty(mAsset))
                mWebview.loadUrl("https://binance.mintscan.io/assets/"+mAsset);

        } else if (mBasechain.equals(BaseChain.KAVA_MAIN) || mBasechain.equals(BaseChain.KAVA_TEST)) {
            mShare.setBackgroundTintList(getResources().getColorStateList(R.color.colorKava));
            if (!TextUtils.isEmpty(mTxid))
                mWebview.loadUrl("https://kava.mintscan.io/txs/"+mTxid);
            else if (!TextUtils.isEmpty(mVoteId))
                mWebview.loadUrl("https://kava.mintscan.io/proposals/"+mVoteId);
            else if (!TextUtils.isEmpty(mAddress))
                mWebview.loadUrl("https://kava.mintscan.io/account/"+mAddress);
            else if (!TextUtils.isEmpty(mAsset))
                mWebview.loadUrl("https://www.kava.io/registration");
            else
                mWebview.loadUrl("https://kava.mintscan.io");

        } else if (mBasechain.equals(BaseChain.BNB_TEST)) {
            mShare.setBackgroundTintList(getResources().getColorStateList(R.color.colorBnb));
            if (!TextUtils.isEmpty(mTxid))
                mWebview.loadUrl("https://testnet-explorer.binance.org/tx/"+mTxid);
            else if (!TextUtils.isEmpty(mAddress))
                mWebview.loadUrl("https://testnet-explorer.binance.org/address/"+mAddress);
            else if (!TextUtils.isEmpty(mAsset))
                mWebview.loadUrl("https://testnet-explorer.binance.org/asset/"+mAsset);

        } else if (mBasechain.equals(BaseChain.BAND_MAIN)) {
            mShare.setBackgroundTintList(getResources().getColorStateList(R.color.colorBand));
            if (!TextUtils.isEmpty(mTxid))
                mWebview.loadUrl("https://cosmoscan.io/tx/"+mTxid);
            else if (!TextUtils.isEmpty(mAddress))
                mWebview.loadUrl("https://cosmoscan.io/account/"+mAddress);

        } else if (mBasechain.equals(BaseChain.OK_TEST)) {
            mShare.setBackgroundTintList(getResources().getColorStateList(R.color.colorOK));
            if (!TextUtils.isEmpty(mTxid))
                mWebview.loadUrl("https://www.oklink.com/okchain-test/tx/"+mTxid);
            else if (!TextUtils.isEmpty(mAddress))
                mWebview.loadUrl("https://www.oklink.com/okchain-test/address/"+mAddress);
            else if (!TextUtils.isEmpty(mAsset))
                mWebview.loadUrl("https://www.oklink.com/okchain-test/token/"+mAsset);

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
                onStartMainActivity(0);
            } else {
                super.onBackPressed();
            }

        }
    }
}
