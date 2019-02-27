package wannabit.io.cosmostaion.activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WUtil;

public class MnemonicCheckActivity extends BaseActivity {

    private Toolbar             mToolbar;
    private TextView[]          mTvWords = new TextView[24];
    private Button              mCopy;

    private String              mEntropy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mnemonic_check);
        mToolbar    = findViewById(R.id.tool_bar);
        mCopy       = findViewById(R.id.btn_copy);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        for(int i = 0; i < mTvWords.length; i++) {
            mTvWords[i] = findViewById(getResources().getIdentifier("tv_mnemonic_" + i , "id", this.getPackageName()));
        }

        mEntropy = getIntent().getStringExtra("entropy");
        long id = getIntent().getLongExtra("checkid", -1);
        final ArrayList<String> mWords = new ArrayList<String>(WKey.getRandomMnemonic(WUtil.HexStringToByteArray(mEntropy)));

        for(int i = 0; i < mTvWords.length; i++) {
            if(i > mWords.size()) mTvWords[i].setVisibility(View.GONE);
            else mTvWords[i].setVisibility(View.VISIBLE);
        }

        for(int i = 0; i < mWords.size(); i++) {
            mTvWords[i].setText(mWords.get(i));
        }

        mCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                StringBuilder builder = new StringBuilder();
                for(String s : mWords) {
                    if(builder.length() != 0)
                        builder.append(" ");
                    builder.append(s);
                }
                String data = builder.toString();
                ClipData clip = ClipData.newPlainText("my data", data);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getBaseContext(), R.string.str_copied, Toast.LENGTH_SHORT).show();
            }
        });
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
