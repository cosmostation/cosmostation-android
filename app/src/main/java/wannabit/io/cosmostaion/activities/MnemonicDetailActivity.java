package wannabit.io.cosmostaion.activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dialog.AlertDialogUtils;
import wannabit.io.cosmostaion.dialog.Dialog_ChangeNickName;
import wannabit.io.cosmostaion.utils.WLog;

public class MnemonicDetailActivity extends BaseActivity implements View.OnClickListener{

    private Toolbar mToolbar;
    private CardView mMnemonicLayer;
    private LinearLayout[] mWordsLayer = new LinearLayout[24];
    private TextView[] mTvWords = new TextView[24];
    private ImageView mEdit;
    private TextView mMnemonicName;
    private Button mCopy, mDerive, mDelete;

    private String mEntropy;
    private ArrayList<String> mWords = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_mnemonic_detail);
        mToolbar = findViewById(R.id.tool_bar);
        mMnemonicLayer = findViewById(R.id.card_mnemonic_layer);
        mEdit = findViewById(R.id.mnemonic_name_edit);
        mMnemonicName = findViewById(R.id.mnemonic_name);
        mCopy = findViewById(R.id.btn_copy);
        mDerive = findViewById(R.id.btn_derive);
        mDelete = findViewById(R.id.btn_delete);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        for (int i = 0; i < mWordsLayer.length; i++) {
            mWordsLayer[i] = findViewById(getResources().getIdentifier("layer_mnemonic_" + i, "id", this.getPackageName()));
            mTvWords[i] = findViewById(getResources().getIdentifier("tv_mnemonic_" + i, "id", this.getPackageName()));
        }

//        mEntropy = getIntent().getStringExtra("entropy");
//        mWords = new ArrayList<String>(WKey.getRandomMnemonic(WUtil.HexStringToByteArray(mEntropy)));
//
//        for (int i = 0; i < mWordsLayer.length; i++) {
//            if (i >= mWords.size()) mWordsLayer[i].setVisibility(View.INVISIBLE);
//            else mWordsLayer[i].setVisibility(View.VISIBLE);
//        }
//
//        for (int i = 0; i < mWords.size(); i++) {
//            mTvWords[i].setText(mWords.get(i));
//        }

        mEdit.setOnClickListener(this);
        mCopy.setOnClickListener(this);
        mDerive.setOnClickListener(this);
        mDelete.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        if (v.equals(mEdit)) {
            Bundle bundle = new Bundle();
            Dialog_ChangeNickName delete = Dialog_ChangeNickName.newInstance(bundle);
            delete.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(delete, "dialog").commitNowAllowingStateLoss();

        } else if (v.equals(mCopy)) {
            AlertDialogUtils.showDoubleButtonDialog(MnemonicDetailActivity.this, getString(R.string.str_safe_copy_title), getString(R.string.str_safe_copy_msg),
                    getString(R.string.str_raw_copy), view -> onRawCopy(),
                    getString(R.string.str_safe_copy), view -> onSafeCopy());

        } else if (v.equals(mDerive)) {
            startActivity(new Intent(MnemonicDetailActivity.this, WalletDeriveActivity.class));

        } else if (v.equals(mDelete)) {
            WLog.w("onClickDelte");
        }
    }
}
