package wannabit.io.cosmostaion.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.bitcoinj.crypto.MnemonicCode;

import java.util.ArrayList;
import java.util.Arrays;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.crypto.EncResult;
import wannabit.io.cosmostaion.dao.MWords;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WUtil;

public class MnemonicRestoreActivity extends BaseActivity implements View.OnClickListener, TaskListener {

    private Toolbar mToolbar;
    private Button mPaste, mBtnConfirm;
    private Button[] mAlphabetBtns = new Button[26];
    private ImageButton mBtnDelete;
    private Button mBtnSpace;
    private RecyclerView mRecyclerView;
    private ImageView mChainImg;
    private TextView mClearAll, mWordCnt;

    private EditText[] mEtMnemonics = new EditText[24];
    private int mMnemonicPosition = 0;

    private ArrayList<String> mAllMnemonic;
    private MnemonicAdapter mMnemonicAdapter;
    private ArrayList<String> mWords = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_mnemonic_restore);
        mToolbar = findViewById(R.id.tool_bar);
        mPaste = findViewById(R.id.btn_paste);
        mBtnConfirm = findViewById(R.id.btn_confirm);
        mBtnDelete = findViewById(R.id.password_back);
        mBtnSpace = findViewById(R.id.btn_next);
        mRecyclerView = findViewById(R.id.recycler);
        mChainImg = findViewById(R.id.chainImg);
        mClearAll = findViewById(R.id.toolbar_clear);
        mWordCnt = findViewById(R.id.words_cnt);

        mPaste.setOnClickListener(this);
        mBtnConfirm.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
        mBtnSpace.setOnClickListener(this);
        mClearAll.setOnClickListener(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mAllMnemonic = new ArrayList<String>(MnemonicCode.INSTANCE.getWordList());
        for (int i = 0; i < mAlphabetBtns.length; i++) {
            mAlphabetBtns[i] = findViewById(getResources().getIdentifier("password_char" + i, "id", getPackageName()));
            mAlphabetBtns[i].setOnClickListener(this);
        }

        for (int i = 0; i < mEtMnemonics.length; i++) {
            final int position = i;
            mEtMnemonics[i] = findViewById(getResources().getIdentifier("tv_mnemonic_" + i, "id", getPackageName()));
            mEtMnemonics[i].setShowSoftInputOnFocus(false);
            mEtMnemonics[i].setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        mMnemonicPosition = position;
                    }
                }
            });
        }

        mEtMnemonics[0].requestFocus();
        mChainImg.setColorFilter(getColor(R.color.colorGray1), android.graphics.PorterDuff.Mode.SRC_IN);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setHasFixedSize(true);
        mMnemonicAdapter = new MnemonicAdapter();
        mRecyclerView.setAdapter(mMnemonicAdapter);

        onCheckMnemonicCnt();
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

    private void onClearAll() {
        for (int i = 0; i < mEtMnemonics.length; i++) {
            mEtMnemonics[i].setText("");
            mEtMnemonics[0].requestFocus();
        }
        onBeforeWord();
        onCheckMnemonicCnt();
    }

    private void onBeforeWord() {
        if (mMnemonicPosition > 0) {
            mEtMnemonics[mMnemonicPosition - 1].requestFocus();
            mEtMnemonics[mMnemonicPosition].setSelection(mEtMnemonics[mMnemonicPosition].getText().length());
        }
        mMnemonicAdapter.getFilter().filter(mEtMnemonics[mMnemonicPosition].getText().toString().trim());
        onCheckMnemonicCnt();
    }

    private void onNextWord() {
        if (mMnemonicPosition < 23) {
            mEtMnemonics[mMnemonicPosition + 1].requestFocus();
        }
        mMnemonicAdapter.getFilter().filter(mEtMnemonics[mMnemonicPosition].getText().toString().trim());
        onCheckMnemonicCnt();
    }

    private void onCheckMnemonicCnt() {
        mWords.clear();
        for (int i = 0; i < mEtMnemonics.length; i++) {
            if (!TextUtils.isEmpty(mEtMnemonics[i].getText().toString().trim())) {
                mWords.add(mEtMnemonics[i].getText().toString().trim());
            } else {
                break;
            }
        }

        mWordCnt.setText("" + mWords.size() + " words");
        if ((mWords.size() == 12 || mWords.size() == 16 || mWords.size() == 24) && WKey.isMnemonicWords(mWords)) {
            mWordCnt.setTextColor(ContextCompat.getColor(MnemonicRestoreActivity.this, R.color.colorAuth));
        } else {
            mWordCnt.setTextColor(ContextCompat.getColor(MnemonicRestoreActivity.this, R.color.colorRed));
        }
    }

    private boolean isValidWords() {
        if ((mWords.size() == 12 || mWords.size() == 16 || mWords.size() == 24) &&
                WKey.isMnemonicWords(mWords) && WKey.isValidStringHdSeedFromWords(mWords)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mClearAll)) {
            onClearAll();
            return;

        } else if (v.equals(mPaste)) {
            onClearAll();

            if (getBaseDao().mCopySalt != null && getBaseDao().mCopyEncResult != null) {
                String words = CryptoHelper.doDecryptData(getBaseDao().mCopySalt, getBaseDao().mCopyEncResult.getEncDataString(), getBaseDao().mCopyEncResult.getIvDataString());
                if (TextUtils.isEmpty(words)) {
                    return;
                }
                ArrayList<String> newinsert = new ArrayList<>(Arrays.asList(words.split("\\s+")));
                for (int i = 0; i < mEtMnemonics.length; i++) {
                    if (newinsert.size() > i) {
                        String toinsert = newinsert.get(i).replace(" ", "");
                        mEtMnemonics[i].setText(toinsert);
                    }
                }
                if (newinsert.size() < 23) {
                    mEtMnemonics[newinsert.size()].requestFocus();
                } else {
                    mEtMnemonics[23].requestFocus();
                }
                mEtMnemonics[mMnemonicPosition].setSelection(mEtMnemonics[mMnemonicPosition].getText().length());
                mMnemonicAdapter.getFilter().filter(mEtMnemonics[mMnemonicPosition].getText().toString().trim());
                onCheckMnemonicCnt();

            } else {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                if (clipboard.getPrimaryClip() != null && clipboard.getPrimaryClip().getItemCount() > 0) {
                    String userPaste = clipboard.getPrimaryClip().getItemAt(0).coerceToText(getBaseContext()).toString().trim();
                    if (TextUtils.isEmpty(userPaste)) {
                        Toast.makeText(this, R.string.error_clipboard_no_data, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    ArrayList<String> newinsert = new ArrayList<>(Arrays.asList(userPaste.split("\\s+")));
                    for (int i = 0; i < mEtMnemonics.length; i++) {
                        if (newinsert.size() > i) {
                            String toinsert = newinsert.get(i).replace(" ", "");
                            mEtMnemonics[i].setText(toinsert);
                        }
                    }
                    if (newinsert.size() < 23) {
                        mEtMnemonics[newinsert.size()].requestFocus();
                    } else {
                        mEtMnemonics[23].requestFocus();
                    }
                    mEtMnemonics[mMnemonicPosition].setSelection(mEtMnemonics[mMnemonicPosition].getText().length());
                    mMnemonicAdapter.getFilter().filter(mEtMnemonics[mMnemonicPosition].getText().toString().trim());
                    onCheckMnemonicCnt();

                } else {
                    Toast.makeText(this, R.string.error_clipboard_no_data, Toast.LENGTH_SHORT).show();
                }
            }
            return;

        } else if (v.equals(mBtnConfirm)) {
            mWords.clear();
            for (int i = 0; i < mEtMnemonics.length; i++) {
                if (!TextUtils.isEmpty(mEtMnemonics[i].getText().toString().trim())) {
                    mWords.add(mEtMnemonics[i].getText().toString().trim());
                } else {
                    break;
                }
            }

            String word = String.join(" ", mWords).trim();
            for (MWords words : getBaseDao().onSelectAllMnemonics()) {
                if (words.getWords(MnemonicRestoreActivity.this).equalsIgnoreCase(word)) {
                    Toast.makeText(this, R.string.error_alreay_imported_mnemonic, Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            if (isValidWords()) {
                onConfirmedWords();
            } else {
                Toast.makeText(this, R.string.error_invalid_mnemonic_count, Toast.LENGTH_SHORT).show();
            }
            return;

        } else if (v.equals(mBtnDelete)) {
            String existed = mEtMnemonics[mMnemonicPosition].getText().toString().trim();
            if (TextUtils.isEmpty(existed)) {
                onBeforeWord();
            } else {
                mEtMnemonics[mMnemonicPosition].setText(existed.substring(0, existed.length() - 1));
                mEtMnemonics[mMnemonicPosition].setSelection(mEtMnemonics[mMnemonicPosition].getText().length());
            }
            mMnemonicAdapter.getFilter().filter(mEtMnemonics[mMnemonicPosition].getText().toString().trim());
            onCheckMnemonicCnt();
            return;

        } else if (v.equals(mBtnSpace)) {
            onNextWord();
            return;

        }
        if (v instanceof Button) {
            String input = ((Button) v).getText().toString();
            mEtMnemonics[mMnemonicPosition].setText(mEtMnemonics[mMnemonicPosition].getText().toString() + input);
            mEtMnemonics[mMnemonicPosition].setSelection(mEtMnemonics[mMnemonicPosition].getText().length());
            mMnemonicAdapter.getFilter().filter(mEtMnemonics[mMnemonicPosition].getText().toString().trim());
        }
    }

    private void onConfirmedWords() {
        if (!getBaseDao().onHasPassword()) {
            Intent intent = new Intent(MnemonicRestoreActivity.this, PasswordSetActivity.class);
            startActivityForResult(intent, BaseConstant.CONST_PW_INIT);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);

        } else {
            Intent intent = new Intent(MnemonicRestoreActivity.this, PasswordCheckActivity.class);
            intent.putExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_SIMPLE_CHECK);
            startActivityForResult(intent, BaseConstant.CONST_PW_SIMPLE_CHECK);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (resultCode == Activity.RESULT_OK) {
                long id = getBaseDao().onInsertMnemonics(onGenMWords());
                if (id > 0) {
                    Intent checkintent = new Intent(MnemonicRestoreActivity.this, WalletDeriveActivity.class);
                    checkintent.putExtra("id", id);
                    startActivity(checkintent);
                    finish();
                }
            }
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

    public class MnemonicAdapter extends RecyclerView.Adapter<MnemonicAdapter.MnemonicHolder> implements Filterable {

        private ArrayList<String> mFilteredMnemonic = new ArrayList<>();

        @NonNull
        @Override
        public MnemonicHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new MnemonicHolder(getLayoutInflater().inflate(R.layout.item_suggest_menmonic, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MnemonicHolder holder, @SuppressLint("RecyclerView") final int position) {
            holder.itemMnemonic.setText(mFilteredMnemonic.get(position));
            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEtMnemonics[mMnemonicPosition].setText(mFilteredMnemonic.get(position));
                    onNextWord();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mFilteredMnemonic.size();
        }

        @Override
        public Filter getFilter() {
            return new Filter() {

                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    String charString = constraint.toString();
                    if (charString.isEmpty()) {
                        mFilteredMnemonic.clear();

                    } else {
                        ArrayList<String> filteredList = new ArrayList<>();
                        for (String word : mAllMnemonic) {
                            if (word.startsWith(charString.toLowerCase()) && !word.equals(charString.toLowerCase())) {
                                filteredList.add(word);
                            }
                        }
                        mFilteredMnemonic = filteredList;
                    }

                    FilterResults filterResults = new FilterResults();
                    filterResults.values = mFilteredMnemonic;
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    mFilteredMnemonic = (ArrayList<String>) results.values;
                    notifyDataSetChanged();
                }
            };
        }

        public class MnemonicHolder extends RecyclerView.ViewHolder {
            RelativeLayout itemRoot;
            TextView itemMnemonic;

            public MnemonicHolder(View v) {
                super(v);
                itemRoot = itemView.findViewById(R.id.root_mnemonic);
                itemMnemonic = itemView.findViewById(R.id.tv_mnemonic);
            }
        }
    }
}
