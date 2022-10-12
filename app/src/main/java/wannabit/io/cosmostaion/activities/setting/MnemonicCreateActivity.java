package wannabit.io.cosmostaion.activities.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.activities.PasswordSetActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.crypto.EncResult;
import wannabit.io.cosmostaion.dao.MWords;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WUtil;

public class MnemonicCreateActivity extends BaseActivity {

    private Toolbar mToolbar;
    private LinearLayout[] mWordsLayer = new LinearLayout[24];
    private TextView[] mTvWords = new TextView[24];
    private ImageView mBtnDisplay;
    private Button mBtnDerive;

    private ArrayList<String> mWords = new ArrayList<>();

    private boolean mIsDisplay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_mnemonic_create);
        mToolbar = findViewById(R.id.tool_bar);
        mBtnDisplay = findViewById(R.id.btn_display);
        mBtnDerive = findViewById(R.id.btn_derive);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        for (int i = 0; i < mWordsLayer.length; i++) {
            mWordsLayer[i] = findViewById(getResources().getIdentifier("layer_mnemonic_" + i, "id", this.getPackageName()));
            mTvWords[i] = findViewById(getResources().getIdentifier("tv_mnemonic_" + i, "id", this.getPackageName()));
        }
        onCreateMnemonic();

        mBtnDisplay.setOnClickListener(view -> {
            mIsDisplay = !mIsDisplay;
            onUpdateView();
        });

        mBtnDerive.setOnClickListener(view -> {
            if (!getBaseDao().onHasPassword()) {
                Intent intent = new Intent(MnemonicCreateActivity.this, PasswordSetActivity.class);
                mnemonicCreateResultLauncher.launch(intent);
            } else {
                Intent intent = new Intent(MnemonicCreateActivity.this, PasswordCheckActivity.class);
                mnemonicCreateResultLauncher.launch(intent);
            }
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
        });
    }

    private final ActivityResultLauncher<Intent> mnemonicCreateResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
            long id = getBaseDao().onInsertMnemonics(onGenMWords());
            if (id > 0) {
                Intent checkIntent = new Intent(MnemonicCreateActivity.this, WalletDeriveActivity.class);
                checkIntent.putExtra("id", id);
                startActivity(checkIntent);
                finish();
            }
        }
    });

    private void onCreateMnemonic() {
        byte[] mEntropy = WKey.getEntropy();
        mWords = new ArrayList<String>(WKey.getRandomMnemonic(mEntropy));
        onUpdateView();
    }

    private void onUpdateView() {
        for (int i = 0; i < mWords.size(); i++) {
            if (mIsDisplay) mTvWords[i].setText(mWords.get(i));
            else mTvWords[i].setText("****");
        }
        if (mIsDisplay) {
            mBtnDisplay.setImageResource(R.drawable.icon_not_display);
        } else {
            mBtnDisplay.setImageResource(R.drawable.icon_display);
        }
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

    private MWords onGenMWords() {
        MWords tempMWords = MWords.getNewInstance();
        String entropy = WUtil.ByteArrayToHexString(WKey.toEntropy(mWords));
        EncResult encR = CryptoHelper.doEncryptData(getString(R.string.key_mnemonic) + tempMWords.uuid, entropy, false);

        tempMWords.resource = encR.getEncDataString();
        tempMWords.spec = encR.getIvDataString();
        tempMWords.wordsCnt = mWords.size();
        return tempMWords;
    }
}
