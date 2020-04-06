package wannabit.io.cosmostaion.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dialog.Dialog_ChoiceNet;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.UserTask.GenerateAccountTask;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class CreateActivity extends BaseActivity implements View.OnClickListener, TaskListener {

    private Toolbar             mToolbar;
    private CardView            mCardAddress, mCardMnemonics;
    private LinearLayout        mWarnLayer;

    private TextView            mAddress;
    private TextView[]          mTvWords = new TextView[24];

    private TextView            mTvWarnMsg;
    private Button              mBtnNext;

    private BaseChain           mChain;
    private ArrayList<String>   mWords = new ArrayList<>();
    private byte[]              mEntropy;
    private boolean             mCheckPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_create);
        mToolbar        = findViewById(R.id.tool_bar);
        mCardAddress    = findViewById(R.id.card_address_layer);
        mAddress        = findViewById(R.id.create_address);
        mCardMnemonics  = findViewById(R.id.card_mnemonic_layer);
        mWarnLayer      = findViewById(R.id.warn_layer);
        mTvWarnMsg      = findViewById(R.id.warn_msg);
        mBtnNext        = findViewById(R.id.btn_next);
        mBtnNext.setOnClickListener(this);

        if (getIntent().getStringExtra("chain") != null) {
            mChain = BaseChain.getChain(getIntent().getStringExtra("chain"));
        }

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        for(int i = 0; i < mTvWords.length; i++) {
            mTvWords[i] = findViewById(getResources().getIdentifier("tv_mnemonic_" + i , "id", this.getPackageName()));
        }
        mCheckPassword = false;
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (mChain == null) {
            Dialog_ChoiceNet dialog = Dialog_ChoiceNet.newInstance(null);
            dialog.setCancelable(false);
            getSupportFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();
        } else {
            onShowWaitDialog();
            onGenWords();
            onUpdateView();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void onGenWords() {
        mEntropy = WKey.getEntropy();
        mWords = new ArrayList<String>(WKey.getRandomMnemonic(mEntropy));
        mAddress.setText(WKey.getDpAddressFromEntropy(mChain, mEntropy, true));
    }


    private void onUpdateView() {
        onHideWaitDialog();
        if (mChain.equals(BaseChain.COSMOS_MAIN)) {
            mCardMnemonics.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg2));
        } else if (mChain.equals(BaseChain.IRIS_MAIN)) {
            mCardMnemonics.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg4));
        } else if (mChain.equals(BaseChain.BNB_MAIN)) {
            mCardMnemonics.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg5));
        } else if (mChain.equals(BaseChain.KAVA_MAIN)) {
            mCardMnemonics.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg7));
        } else if (mChain.equals(BaseChain.IOV_MAIN)) {
            mCardMnemonics.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg6));
        } else if (mChain.equals(BaseChain.BNB_TEST) || mChain.equals(BaseChain.KAVA_TEST)) {
            mCardMnemonics.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
        }
        for(int i = 0; i < mTvWords.length; i++) {
            if (mChain.equals(BaseChain.COSMOS_MAIN)) {
                mTvWords[i].setBackground(getDrawable(R.drawable.box_round_atom));
            } else if (mChain.equals(BaseChain.IRIS_MAIN)) {
                mTvWords[i].setBackground(getDrawable(R.drawable.box_round_iris));
            } else if (mChain.equals(BaseChain.BNB_MAIN)) {
                mTvWords[i].setBackground(getDrawable(R.drawable.box_round_bnb));
            } else if (mChain.equals(BaseChain.KAVA_MAIN)) {
                mTvWords[i].setBackground(getDrawable(R.drawable.box_round_kava));
            } else if (mChain.equals(BaseChain.IOV_MAIN)) {
                mTvWords[i].setBackground(getDrawable(R.drawable.box_round_iov));
            } else if (mChain.equals(BaseChain.BNB_TEST) || mChain.equals(BaseChain.KAVA_TEST)) {
                mTvWords[i].setBackground(getDrawable(R.drawable.box_round_darkgray));

            }
        }

        mCardAddress.setVisibility(View.VISIBLE);
        mCardMnemonics.setVisibility(View.VISIBLE);
        mWarnLayer.setVisibility(View.VISIBLE);
        mBtnNext.setVisibility(View.VISIBLE);
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
            mTvWarnMsg.setText(getString(R.string.str_create_warn1));
        } else {
            mBtnNext.setText(getString(R.string.str_show_mnemonic));
            mTvWarnMsg.setText(getString(R.string.str_create_warn0));
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
                if (mChain.equals(BaseChain.KAVA_MAIN) || mChain.equals(BaseChain.KAVA_TEST)) {
                    new GenerateAccountTask(getBaseApplication(), mChain, this, true).execute("0", WUtil.ByteArrayToHexString(mEntropy), "24");
                } else {
                    new GenerateAccountTask(getBaseApplication(), mChain, this, false).execute("0", WUtil.ByteArrayToHexString(mEntropy), "24");
                }
            }
        }

    }

    @Override
    public void onChoiceNet(BaseChain chain) {
        super.onChoiceNet(chain);
        onShowWaitDialog();

        mChain = chain;
        onGenWords();
        onUpdateView();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BaseConstant.CONST_PW_INIT && resultCode == Activity.RESULT_OK) {
            mCheckPassword = true;
        } else if (requestCode == BaseConstant.CONST_PW_SIMPLE_CHECK && resultCode == Activity.RESULT_OK) {
            mCheckPassword = true;
        }
        onUpdateView();
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(isFinishing()) return;
        onHideWaitDialog();
        if (result.taskType == BaseConstant.TASK_INIT_ACCOUNT) {
            if(result.isSuccess) {
                onStartMainActivity(false);
            } else {
                WLog.w("CREATE ACCOUNT with new mnemonic error : " + result.errorCode);
            }
        }
    }
}
