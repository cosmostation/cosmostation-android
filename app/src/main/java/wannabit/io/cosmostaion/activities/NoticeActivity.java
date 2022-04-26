package wannabit.io.cosmostaion.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import wannabit.io.cosmostaion.R;

public class NoticeActivity extends AppCompatActivity {

    private WebView webView;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        webView = (WebView) findViewById(R.id.webview);
        mToolbar = findViewById(R.id.tool_bar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView.setWebViewClient(new WebViewClient()); //새 창 띄우지 않기
        webView.getSettings().setLoadWithOverviewMode(true); //WebView 화면크기에 맞추도록 설정 -> setUseWideViewPort와 같이 써야함
        webView.getSettings().setUseWideViewPort(true); //Wide viewport 설정 -> setLoadWithOverviewMode와 같이 써야함

        webView.getSettings().setSupportZoom(false); // 줌 설정 여부
        webView.getSettings().setBuiltInZoomControls(false); // 줌 확대, 축소 버튼 여부

        webView.getSettings().setJavaScriptEnabled(true); // 자바스크립트 사용여부
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true); // javascript가 window.open()을 사용할 수 있도록 설정

        String chain = getIntent().getStringExtra("id");
        webView.loadUrl("https://notice.mintscan.io/" + chain);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}