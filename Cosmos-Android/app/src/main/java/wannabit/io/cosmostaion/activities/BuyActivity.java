package wannabit.io.cosmostaion.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.task.FetchTask.MoonPayTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class BuyActivity extends BaseActivity implements TaskListener {

    private WebView mWebview;
    private String mQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        mWebview = findViewById(R.id.webView);
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

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mQuery = "?apiKey=" + getString(R.string.moon_pay_public_key);

        if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            mQuery = mQuery + "&currencyCode=atom";
        } else if (mBaseChain.equals(BaseChain.BNB_MAIN)) {
            mQuery = mQuery + "&currencyCode=bnb";
        }
        mQuery = mQuery + "&walletAddress=" + mAccount.address;
        new MoonPayTask(getBaseApplication(), this, mQuery).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }


    @Override
    public void onTaskResponse(TaskResult result) {
        if (result.taskType == BaseConstant.TASK_MOON_PAY_SIGNATURE) {
            if (result.isSuccess) {
                mWebview.loadUrl(getString(R.string.url_moon_pay) + mQuery + "&signature=" + (String)result.resultData);
            } else {
                onBackPressed();
            }
        }
    }

}
