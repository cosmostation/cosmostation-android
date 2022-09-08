package wannabit.io.cosmostaion.activities.setting;

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

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.MWords;
import wannabit.io.cosmostaion.dialog.ChangeNickNameDialog;
import wannabit.io.cosmostaion.dialog.CommonAlertDialog;

public class MnemonicDetailActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView mMnemonicNick;
    private ImageView mBtnEditNick, mBtnDisplay;
    private LinearLayout[] mWordsLayer = new LinearLayout[24];
    private TextView[] mTvWords = new TextView[24];
    private Button mCopy, mDerive, mDelete;

    private MWords mWords;
    private ArrayList<String> mWordsList = new ArrayList<>();

    private boolean mIsDisplay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_mnemonic_detail);
        mToolbar = findViewById(R.id.tool_bar);
        mMnemonicNick = findViewById(R.id.mnemonic_nickname);
        mBtnEditNick = findViewById(R.id.btn_nickname_edit);
        mBtnDisplay = findViewById(R.id.btn_display);
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
        mWords = getBaseDao().onSelectMnemonicById(getIntent().getLongExtra("mnemonicId", -1));
        mMnemonicNick.setText(mWords.getName());
        mWordsList = mWords.getMnemonicWords(MnemonicDetailActivity.this);

        onPhraseStatusChange();

        mBtnEditNick.setOnClickListener(this);
        mBtnDisplay.setOnClickListener(this);
        mCopy.setOnClickListener(this);
        mDerive.setOnClickListener(this);
        mDelete.setOnClickListener(this);
    }

    public void onRawCopy() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        StringBuilder builder = new StringBuilder();
        for (String s : mWordsList) {
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
        for (String s : mWordsList) {
            if (builder.length() != 0)
                builder.append(" ");
            builder.append(s);
        }
        String data = builder.toString();
        getBaseDao().mCopyEncResult = CryptoHelper.doEncryptData(getBaseDao().mCopySalt, data, false);
        Toast.makeText(getBaseContext(), R.string.str_safe_copied, Toast.LENGTH_SHORT).show();

    }

    public void onChangeNickName(String name) {
        mWords.nickName = name;
        getBaseDao().onUpdateMnemonic(mWords);
        mMnemonicNick.setText(name);
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

    public void onStartDeleteMnemonic() {
        Intent intent = new Intent(MnemonicDetailActivity.this, PasswordCheckActivity.class);
        intent.putExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_DELETE_ACCOUNT);
        intent.putExtra("mWordId", mWords.id);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnEditNick) && !this.isFinishing()) {
            Bundle bundle = new Bundle();
            bundle.putInt("title", R.string.str_change_mnemonic_nickname);
            bundle.putLong("id", mWords.id);
            bundle.putString("name", mWords.getName());
            ChangeNickNameDialog deleteDialog = ChangeNickNameDialog.newInstance(bundle);
            deleteDialog.setCancelable(false);
            deleteDialog.show(getSupportFragmentManager(), "dialog");

        } else if (v.equals(mBtnDisplay)) {
            mIsDisplay = !mIsDisplay;
            onPhraseStatusChange();

        } else if (v.equals(mCopy)) {
            CommonAlertDialog.showDoubleButton(MnemonicDetailActivity.this, getString(R.string.str_safe_copy_title), getString(R.string.str_safe_copy_msg),
                    CommonAlertDialog.highlightingText(getString(R.string.str_raw_copy)), view -> onRawCopy(),
                    getString(R.string.str_safe_copy), view -> onSafeCopy());

        } else if (v.equals(mDerive)) {
            Intent intent = new Intent(MnemonicDetailActivity.this, WalletDeriveActivity.class);
            intent.putExtra("id", mWords.id);
            intent.putExtra("isDetailMode", true);
            startActivity(intent);

        } else if (v.equals(mDelete)) {
            CommonAlertDialog.showDoubleButton(MnemonicDetailActivity.this, getString(R.string.str_mnemonic_delete),
                    String.format(getString(R.string.str_mnemonic_delete_msg), String.valueOf(mWords.getLinkedWalletCnt(getBaseDao()))),
                    CommonAlertDialog.highlightingText(getString(R.string.str_delete)), view -> onStartDeleteMnemonic(),
                    getString(R.string.str_close), null);
        }
    }

    private void onPhraseStatusChange() {
        for (int i = 0; i < mWordsList.size(); i++) {
            if (mIsDisplay) mTvWords[i].setText(mWordsList.get(i));
            else mTvWords[i].setText("****");
        }
        if (mIsDisplay) {
            mBtnDisplay.setImageResource(R.drawable.icon_not_display);
        } else {
            mBtnDisplay.setImageResource(R.drawable.icon_display);
        }
    }
}
