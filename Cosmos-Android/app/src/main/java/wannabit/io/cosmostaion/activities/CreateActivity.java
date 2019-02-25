package wannabit.io.cosmostaion.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.task.UserTask.GenerateAccountTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class CreateActivity extends BaseActivity implements View.OnClickListener, TaskListener {

    private TextView            mAddress;
    private TextView[]          mTvWords = new TextView[24];
    private ImageView           mImgMsg;
    private TextView            mTvMsg1, mTvMsg2;
    private Button              mBtnNext;

    private ArrayList<String>   mWords = new ArrayList<>();
    private byte[]              mEntropy;
    private boolean             mCheckPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        mAddress        = findViewById(R.id.create_address);
        mImgMsg         = findViewById(R.id.create_img);
        mTvMsg1         = findViewById(R.id.create_msg1);
        mTvMsg2         = findViewById(R.id.create_msg2);
        mBtnNext        = findViewById(R.id.btn_next);
        mBtnNext.setOnClickListener(this);

        for(int i = 0; i < mTvWords.length; i++) {
            mTvWords[i] = findViewById(getResources().getIdentifier("tv_mnemonic_" + i , "id", this.getPackageName()));
        }
        mCheckPassword = false;
        onGenWords();
    }


    @Override
    protected void onResume() {
        super.onResume();
        onUpdateView();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void onGenWords() {
        mEntropy   = WKey.getEntropy();
        mWords  = new ArrayList<String>(WKey.getRandomMnemonic(mEntropy));
        mAddress.setText(WKey.getDpAddressFromEntropy(mEntropy));
    }

    private void onUpdateView() {
        if(getBaseDao().onHasPassword() && mCheckPassword) {
            for(int i = 0; i < mTvWords.length; i++) {
                mTvWords[i].setText(mWords.get(i));
            }
        } else {
            for(int i = 0; i < mTvWords.length; i++) {
                mTvWords[i].setText(mWords.get(i).replaceAll("[a-z]", "?"));
            }
        }

        if(mCheckPassword) {
            mBtnNext.setText(getString(R.string.str_create_wallet));
        } else {
            mBtnNext.setText(getString(R.string.str_show_mnemonic));
        }

    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBtnNext)) {
            if(mBtnNext.getText().equals(getString(R.string.str_show_mnemonic))) {
                if(!getBaseDao().onHasPassword()) {
                    Intent intent = new Intent(CreateActivity.this, PasswordSetActivity.class);
                    startActivityForResult(intent, BaseConstant.CONST_PW_INIT);
                    overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
                } else {
                    Intent intent = new Intent(CreateActivity.this, PasswordCheckActivity.class);
                    intent.putExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_SIMPLE_CHECK);
                    startActivityForResult(intent, BaseConstant.CONST_PW_SIMPLE_CHECK);
                    overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
                }
            } else {
                onShowWaitDialog();
                new GenerateAccountTask(getBaseApplication(), this).execute(BaseChain.GAIA_12K.getChain(), "0", WUtil.ByteArrayToHexString(mEntropy), "24");
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
        onUpdateView();
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(isFinishing()) return;
        if (result.taskType == BaseConstant.TASK_INIT_ACCOUNT) {
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
