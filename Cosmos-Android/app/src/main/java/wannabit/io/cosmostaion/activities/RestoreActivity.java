package wannabit.io.cosmostaion.activities;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
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
import wannabit.io.cosmostaion.task.GenerateAccountTask;
import wannabit.io.cosmostaion.task.InsertMnemonicTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class RestoreActivity extends BaseActivity implements View.OnClickListener, TaskListener {

    private CardView            mCard;
    private TextView[]          mTvWords = new TextView[24];
    private Button              mBtnPaste, mBtnConfirm;
    private ArrayList<String>   mWords = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restore);
        mCard = findViewById(R.id.restore_word);
        mBtnPaste = findViewById(R.id.btn_paste);
        mBtnConfirm = findViewById(R.id.btn_confirm);

        for(int i = 0; i < mTvWords.length; i++) {
            mTvWords[i] = findViewById(getResources().getIdentifier("tv_mnemonic_" + i , "id", this.getPackageName()));
        }

        mBtnPaste.setOnClickListener(this);
        mBtnConfirm.setOnClickListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        onUpdateDpWord();
    }

    private void onUpdateDpWord() {
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
                //TODO choose address 장면 나와야함.

                onShowWaitDialog();
                new InsertMnemonicTask(getBaseApplication(), this).execute(WKey.getStringHdSeedFromWords(mWords), "24");
            }
        }

    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(isFinishing()) return;
        if (result.taskType == BaseConstant.TASK_ADD_MN) {
            WLog.w("TASK_ADD_MN");
            if(result.isSuccess)
                new GenerateAccountTask(getBaseApplication(), this).execute(BaseChain.GAIA_12K.getChain(), "0", result.resultData.toString());
            else {
                WLog.w("Init Mnemonic ERROR : " + result.errorCode);
            }
        } else if (result.taskType == BaseConstant.TASK_INIT_ACCOUNT) {
            WLog.w("TASK_INIT_ACCOUNT");
            if(result.isSuccess) {
                if(getBaseDao().onSelectAccounts().size() > 1) {
                    onStartListActivity();
                } else {
                    onStartMainActivity();
                }
            } else {
                WLog.w("CREATE ACCOUNT with new mnemonic error : " + result.errorCode);
            }
        }
    }
}
