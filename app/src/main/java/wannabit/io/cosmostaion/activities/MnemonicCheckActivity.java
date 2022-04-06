package wannabit.io.cosmostaion.activities;

import static wannabit.io.cosmostaion.base.BaseChain.getChain;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.Dialog_Safe_Copy;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WUtil;

public class MnemonicCheckActivity extends BaseActivity {

    private Toolbar mToolbar;
    private CardView mMnemonicLayer;
    private final LinearLayout[] mWordsLayer = new LinearLayout[24];
    private final TextView[] mTvWords = new TextView[24];
    private Button mCopy, mOk;

    private String mEntropy;
    private ArrayList<String> mWords = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_mnemonic_check);
        mToolbar = findViewById(R.id.toolbar);
        mMnemonicLayer = findViewById(R.id.mnemonicsCardView);
        mCopy = findViewById(R.id.btn_copy);
        mOk = findViewById(R.id.btn_ok);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        for (int i = 0; i < mWordsLayer.length; i++) {
            mWordsLayer[i] = findViewById(getResources().getIdentifier("layer_mnemonic_" + i, "id", this.getPackageName()));
            mTvWords[i] = findViewById(getResources().getIdentifier("tv_mnemonic_" + i, "id", this.getPackageName()));
        }

        mEntropy = getIntent().getStringExtra("entropy");
        Account toCheck = getBaseDao().onSelectAccount("" + getIntent().getLongExtra("checkid", -1));
        mMnemonicLayer.setCardBackgroundColor(WDp.getChainBgColor(getBaseContext(), getChain(toCheck.baseChain)));
        mWords = new ArrayList<String>(WKey.getRandomMnemonic(WUtil.hexStringToByteArray(mEntropy)));

        for (int i = 0; i < mWordsLayer.length; i++) {
            BaseChain chain = getChain(toCheck.baseChain);
            LinearLayout wordsLayout = mWordsLayer[i];
            if (chain != null) {
                wordsLayout.setBackground(AppCompatResources.getDrawable(this, chain.getMnemonicBackground()));
            }
            wordsLayout.setVisibility(i >= mWords.size() ? View.INVISIBLE : View.VISIBLE);
        }

        for (int i = 0; i < mWords.size(); i++) {
            mTvWords[i].setText(mWords.get(i));
        }

        mCopy.setOnClickListener(v -> {
            Dialog_Safe_Copy delete = Dialog_Safe_Copy.newInstance();
            showDialog(delete);
        });

        mOk.setOnClickListener(v -> onStartMainActivity(3));
    }

    public void onRawCopy() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        StringBuilder builder = new StringBuilder();
        for (String s : mWords) {
            if (builder.length() != 0)
                builder.append(" ");
            builder.append(s);
        }
        String data = builder.toString();
        ClipData clip = ClipData.newPlainText("my data", data);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getBaseContext(), R.string.str_copied, Toast.LENGTH_SHORT).show();

    }

    public void onSafeCopy() {
        StringBuilder builder = new StringBuilder();
        for (String s : mWords) {
            if (builder.length() != 0)
                builder.append(" ");
            builder.append(s);
        }
        String data = builder.toString();
        getBaseDao().mCopyEncResult = CryptoHelper.doEncryptData(getBaseDao().mCopySalt, data, false);
        Toast.makeText(getBaseContext(), R.string.str_safe_copied, Toast.LENGTH_SHORT).show();

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
}
