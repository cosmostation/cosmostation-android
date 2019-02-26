package wannabit.io.cosmostaion.activities;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dialog.Dialog_ChoiceNet;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class RestoreActivity extends BaseActivity implements View.OnClickListener, TaskListener {

    private Toolbar             mToolbar;
    private CardView            mCard;
    private TextView[]          mTvWords = new TextView[24];
    private Button              mBtnPaste, mBtnConfirm;
    private ArrayList<String>   mWords = new ArrayList<>();
    private boolean             mCheckPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restore);

        mToolbar        = findViewById(R.id.tool_bar);
        mCard = findViewById(R.id.restore_word);
        mBtnPaste = findViewById(R.id.btn_paste);
        mBtnConfirm = findViewById(R.id.btn_confirm);

        for(int i = 0; i < mTvWords.length; i++) {
            mTvWords[i] = findViewById(getResources().getIdentifier("tv_mnemonic_" + i , "id", this.getPackageName()));
        }

        mBtnPaste.setOnClickListener(this);
        mBtnConfirm.setOnClickListener(this);
        mCheckPassword = false;

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
    protected void onResume() {
        super.onResume();
        onUpdateDpWord();
    }

    private void onUpdateDpWord() {
        if(mWords == null || mWords.size() > 24) return;

        for (int i = 0; i < mWords.size(); i++) {
            mTvWords[i].setText(mWords.get(i));
        }

        for (int i = 0; i < mTvWords.length; i++) {
            String inputWord = mTvWords[i].getText().toString().trim().toLowerCase();
            if(!TextUtils.isEmpty(inputWord)) {
                if (WKey.isMnemonicWord(inputWord)) {
                    mTvWords[i].setBackground(getDrawable(R.drawable.box_round_atom));
                } else {
                    mTvWords[i].setBackground(getDrawable(R.drawable.box_round_red));
                }
            } else {
                mTvWords[i].setBackground(getDrawable(R.drawable.box_round_atom));
            }
        }

        if( (mWords.size() == 12 || mWords.size() == 16 || mWords.size() == 24) &&
            WKey.isMnemonicWords(mWords)) {
            mCard.setBackgroundColor(getResources().getColor(R.color.colorTransBg2));
            mBtnConfirm.setTextColor(getResources().getColor(R.color.colorPhoton));
        } else {
            mCard.setBackgroundColor(getResources().getColor(R.color.colorTransBg));
            mBtnConfirm.setTextColor(getResources().getColor(R.color.colorWhite));
        }

    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnPaste)) {
            for(int i = 0; i < mTvWords.length; i++) {
                mTvWords[i].setText("");
            }
            ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
            if(clipboard.getPrimaryClip() != null && clipboard.getPrimaryClip().getItemCount() > 0) {
                String userPaste = clipboard.getPrimaryClip().getItemAt(0).coerceToText(getBaseContext()).toString();
                if(TextUtils.isEmpty(userPaste)) return;

                mWords.clear();
                ArrayList<String> newinsert = new ArrayList<>(Arrays.asList(userPaste.split("\\s+")));
                for(String text:newinsert) {
                    text = text.replace(",","").replace(" ", "");
                    mWords.add(text);
                }
                onUpdateDpWord();

            } else {
                Toast.makeText(this, R.string.error_clipboard_no_data, Toast.LENGTH_SHORT).show();
            }
        } else if (v.equals(mBtnConfirm)) {
            if(mBtnConfirm.getCurrentTextColor() == getResources().getColor(R.color.colorWhite)) {
                Toast.makeText(this, R.string.error_invalid_mnemonic_count, Toast.LENGTH_SHORT).show();

            } else if (mBtnConfirm.getCurrentTextColor() == getResources().getColor(R.color.colorPhoton)) {
                if(!getBaseDao().onHasPassword()) {
                    Intent intent = new Intent(RestoreActivity.this, PasswordSetActivity.class);
                    startActivityForResult(intent, BaseConstant.CONST_PW_INIT);
                    overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
                } else {
                    Intent intent = new Intent(RestoreActivity.this, PasswordCheckActivity.class);
                    intent.putExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_SIMPLE_CHECK);
                    startActivityForResult(intent, BaseConstant.CONST_PW_SIMPLE_CHECK);
                    overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
                }
            }
        }

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BaseConstant.CONST_PW_INIT && resultCode == Activity.RESULT_OK) {
            WLog.w("onActivityResult REQ_INIT_PASSWORD RESULT_OK");
            mCheckPassword = true;
        } else if (requestCode == BaseConstant.CONST_PW_SIMPLE_CHECK && resultCode == Activity.RESULT_OK) {
            WLog.w("onActivityResult CONST_PW_SIMPLE_CHECK RESULT_OK");
            mCheckPassword = true;
        }
        Dialog_ChoiceNet dialog = Dialog_ChoiceNet.newInstance(null);
        dialog.setCancelable(false);
        getSupportFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();
    }


    @Override
    public void onChoiceNet(BaseChain chain) {
        super.onChoiceNet(chain);
        Intent intent = new Intent(RestoreActivity.this, RestorePathActivity.class);
        intent.putExtra("HDseed", WKey.getStringHdSeedFromWords(mWords));
        intent.putExtra("entorpy", WUtil.ByteArrayToHexString(WKey.toEntropy(mWords)));
        intent.putExtra("size", mWords.size());
        intent.putExtra("chain", chain.getChain());
        startActivity(intent);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(isFinishing()) return;
        if (result.taskType == BaseConstant.TASK_INIT_ACCOUNT) {
            WLog.w("TASK_INIT_ACCOUNT");
            if(result.isSuccess) {
//                if(getBaseDao().onSelectAccounts().size() > 1) {
//                    onStartListActivity();
//                } else {
                    onStartMainActivity();
//                }
            } else {
                WLog.w("CREATE ACCOUNT with new mnemonic error : " + result.errorCode);
            }
        }
    }
}
