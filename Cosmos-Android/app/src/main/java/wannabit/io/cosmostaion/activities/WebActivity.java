package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;

import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BINANCE_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BINANCE_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_CERTIK;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_OKEX_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_SECRET_MAIN;

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
                mWebview.loadUrl(EXPLORER_COSMOS_MAIN + "txs/" + mTxid);
            else if (!TextUtils.isEmpty(mVoteId))
                mWebview.loadUrl(EXPLORER_COSMOS_MAIN + "proposals/" + mVoteId);
            else if (!TextUtils.isEmpty(mAddress))
                mWebview.loadUrl(EXPLORER_COSMOS_MAIN + "account/" + mAddress);
            else
                mWebview.loadUrl(EXPLORER_COSMOS_MAIN);

        } else if (mBasechain.equals(BaseChain.IRIS_MAIN)) {
            mShare.setBackgroundTintList(getResources().getColorStateList(R.color.colorIris));
            if (!TextUtils.isEmpty(mTxid))
                mWebview.loadUrl(EXPLORER_IRIS_MAIN + "txs/" + mTxid);
            else if (!TextUtils.isEmpty(mVoteId))
                mWebview.loadUrl(EXPLORER_IRIS_MAIN + "proposals/" + mVoteId);
            else if (!TextUtils.isEmpty(mAddress))
                mWebview.loadUrl(EXPLORER_IRIS_MAIN + "account/" + mAddress);
            else
                mWebview.loadUrl(EXPLORER_IRIS_MAIN);

        } else if (mBasechain.equals(BaseChain.BNB_MAIN)) {
            mShare.setBackgroundTintList(getResources().getColorStateList(R.color.colorBnb));
            if (!TextUtils.isEmpty(mTxid))
                mWebview.loadUrl(EXPLORER_BINANCE_MAIN + "txs/" + mTxid);
            else if (!TextUtils.isEmpty(mAddress))
                mWebview.loadUrl(EXPLORER_BINANCE_MAIN + "account/" + mAddress);
            else if (!TextUtils.isEmpty(mAsset))
                mWebview.loadUrl(EXPLORER_BINANCE_MAIN + "assets/" + mAsset);
            else
                mWebview.loadUrl(EXPLORER_BINANCE_MAIN);

        } else if (mBasechain.equals(BaseChain.KAVA_MAIN)) {
            mShare.setBackgroundTintList(getResources().getColorStateList(R.color.colorKava));
            if (!TextUtils.isEmpty(mTxid))
                mWebview.loadUrl(EXPLORER_KAVA_MAIN + "txs/" + mTxid);
            else if (!TextUtils.isEmpty(mVoteId))
                mWebview.loadUrl(EXPLORER_KAVA_MAIN + "proposals/" + mVoteId);
            else if (!TextUtils.isEmpty(mAddress))
                mWebview.loadUrl(EXPLORER_KAVA_MAIN + "account/"+ mAddress);
            else
                mWebview.loadUrl(EXPLORER_KAVA_MAIN);

        } else if (mBasechain.equals(BaseChain.IOV_MAIN)) {
            mShare.setBackgroundTintList(getResources().getColorStateList(R.color.colorIov));
            if (!TextUtils.isEmpty(mTxid))
                mWebview.loadUrl(EXPLORER_IOV_MAIN + "txs/" + mTxid);
            else if (!TextUtils.isEmpty(mVoteId))
                mWebview.loadUrl(EXPLORER_IOV_MAIN + "proposals/" + mVoteId);
            else if (!TextUtils.isEmpty(mAddress))
                mWebview.loadUrl(EXPLORER_IOV_MAIN + "account/"+ mAddress);
            else
                mWebview.loadUrl(EXPLORER_IOV_MAIN);

        } else if (mBasechain.equals(BaseChain.BAND_MAIN)) {
            mShare.setBackgroundTintList(getResources().getColorStateList(R.color.colorBand));
            if (!TextUtils.isEmpty(mTxid))
                mWebview.loadUrl(EXPLORER_BAND_MAIN + "tx/" + mTxid);
            else if (!TextUtils.isEmpty(mVoteId))
                mWebview.loadUrl(EXPLORER_BAND_MAIN + "proposal/" + mVoteId);
            else if (!TextUtils.isEmpty(mAddress))
                mWebview.loadUrl(EXPLORER_BAND_MAIN + "account/" + mAddress);
            else
                mWebview.loadUrl(EXPLORER_BAND_MAIN);

        } else if (mBasechain.equals(BaseChain.BNB_TEST)) {
            mShare.setBackgroundTintList(getResources().getColorStateList(R.color.colorBnb));
            if (!TextUtils.isEmpty(mTxid))
                mWebview.loadUrl(EXPLORER_BINANCE_TEST + "tx/" + mTxid);
            else if (!TextUtils.isEmpty(mAddress))
                mWebview.loadUrl(EXPLORER_BINANCE_TEST + "address/" + mAddress);
            else if (!TextUtils.isEmpty(mAsset))
                mWebview.loadUrl(EXPLORER_BINANCE_TEST + "asset/" + mAsset);
            else
                mWebview.loadUrl(EXPLORER_BINANCE_TEST);

        } else if (mBasechain.equals(BaseChain.OKEX_MAIN)) {
            mShare.setBackgroundTintList(getResources().getColorStateList(R.color.colorOK));
            if (!TextUtils.isEmpty(mTxid))
                mWebview.loadUrl(EXPLORER_OKEX_MAIN + "tx/" + mTxid);
            else if (!TextUtils.isEmpty(mAddress))
                mWebview.loadUrl(EXPLORER_OKEX_MAIN + "address/" + mAddress);
            else if (!TextUtils.isEmpty(mAsset))
                mWebview.loadUrl(EXPLORER_OKEX_MAIN + "token/" + mAsset);
            else
                mWebview.loadUrl(EXPLORER_OKEX_MAIN);

        } else if (mBasechain.equals(BaseChain.OK_TEST)) {
            mShare.setBackgroundTintList(getResources().getColorStateList(R.color.colorOK));
            if (!TextUtils.isEmpty(mTxid))
                mWebview.loadUrl(EXPLORER_OKEX_TEST + "tx/" + mTxid);
            else if (!TextUtils.isEmpty(mAddress))
                mWebview.loadUrl(EXPLORER_OKEX_TEST + "address/" + mAddress);
            else if (!TextUtils.isEmpty(mAsset))
                mWebview.loadUrl(EXPLORER_OKEX_TEST + "token/" + mAsset);
            else
                mWebview.loadUrl(EXPLORER_OKEX_TEST);

        } else if (mBasechain.equals(BaseChain.KAVA_TEST)) {
            mShare.setBackgroundTintList(getResources().getColorStateList(R.color.colorKava));
            if (!TextUtils.isEmpty(mTxid))
                mWebview.loadUrl(EXPLORER_KAVA_TEST + "txs/" + mTxid);
            else if (!TextUtils.isEmpty(mVoteId))
                mWebview.loadUrl(EXPLORER_KAVA_TEST + "proposals/" + mVoteId);
            else if (!TextUtils.isEmpty(mAddress))
                mWebview.loadUrl(EXPLORER_KAVA_TEST + "account/" + mAddress);
            else
                mWebview.loadUrl(EXPLORER_KAVA_TEST);

        } else if (mBasechain.equals(BaseChain.CERTIK_MAIN) || mBasechain.equals(BaseChain.CERTIK_TEST)) {
            mShare.setBackgroundTintList(getResources().getColorStateList(R.color.colorCertik));
            if (!TextUtils.isEmpty(mTxid))
                mWebview.loadUrl(EXPLORER_CERTIK + "Transactions/" + mTxid + "?net=" + mBasechain.getChain());
            else if (!TextUtils.isEmpty(mAddress))
                mWebview.loadUrl(EXPLORER_CERTIK + "accounts/" + mAddress + "?net=" + mBasechain.getChain());
            else if (!TextUtils.isEmpty(mVoteId))
                mWebview.loadUrl(EXPLORER_CERTIK + "governance/proposals/" + mVoteId + "?net=" + mBasechain.getChain());
            else
                mWebview.loadUrl(EXPLORER_CERTIK + "?net=" + mBaseChain.getChain());

        } else if (mBasechain.equals(BaseChain.SECRET_MAIN)) {
            mShare.setBackgroundTintList(getResources().getColorStateList(R.color.colorSecret));
            if (!TextUtils.isEmpty(mTxid))
                mWebview.loadUrl(EXPLORER_SECRET_MAIN + "transactions/" + mTxid);
            else if (!TextUtils.isEmpty(mVoteId))
                mWebview.loadUrl(EXPLORER_SECRET_MAIN + "proposals/" + mVoteId);
            else if (!TextUtils.isEmpty(mAddress))
                mWebview.loadUrl(EXPLORER_SECRET_MAIN + "account/" + mAddress);
            else
                mWebview.loadUrl(EXPLORER_SECRET_MAIN);

        } else if (mBasechain.equals(BaseChain.AKASH_MAIN)) {
            mShare.setBackgroundTintList(getResources().getColorStateList(R.color.colorAkash));
            if (!TextUtils.isEmpty(mTxid))
                mWebview.loadUrl(EXPLORER_AKASH_MAIN + "txs/" + mTxid);
            else if (!TextUtils.isEmpty(mVoteId))
                mWebview.loadUrl(EXPLORER_AKASH_MAIN + "proposals/" + mVoteId);
            else if (!TextUtils.isEmpty(mAddress))
                mWebview.loadUrl(EXPLORER_AKASH_MAIN + "account/" + mAddress);
            else
                mWebview.loadUrl(EXPLORER_AKASH_MAIN);

        } else if (mBasechain.equals(BaseChain.COSMOS_TEST)) {
            mShare.setBackgroundTintList(getResources().getColorStateList(R.color.colorAtom));
            if (!TextUtils.isEmpty(mTxid))
                mWebview.loadUrl(EXPLORER_COSMOS_TEST + "txs/" + mTxid);
            else if (!TextUtils.isEmpty(mVoteId))
                mWebview.loadUrl(EXPLORER_COSMOS_TEST + "proposals/" + mVoteId);
            else if (!TextUtils.isEmpty(mAddress))
                mWebview.loadUrl(EXPLORER_COSMOS_TEST + "account/" + mAddress);
            else
                mWebview.loadUrl(EXPLORER_COSMOS_TEST);

        } else if (mBasechain.equals(BaseChain.IRIS_TEST)) {
            mShare.setBackgroundTintList(getResources().getColorStateList(R.color.colorIris));
            if (!TextUtils.isEmpty(mTxid))
                mWebview.loadUrl(EXPLORER_IRIS_TEST + "txs/" + mTxid);
            else if (!TextUtils.isEmpty(mVoteId))
                mWebview.loadUrl(EXPLORER_IRIS_TEST + "proposals/" + mVoteId);
            else if (!TextUtils.isEmpty(mAddress))
                mWebview.loadUrl(EXPLORER_IRIS_TEST + "account/" + mAddress);
            else
                mWebview.loadUrl(EXPLORER_IRIS_TEST);

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
        if (mWebview.canGoBack()) {
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
