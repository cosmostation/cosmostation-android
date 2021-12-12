package wannabit.io.cosmostaion.activities;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.UserTask.GeneratePkeyAccountTask;
import wannabit.io.cosmostaion.task.UserTask.OverridePkeyAccountTask;
import wannabit.io.cosmostaion.utils.WKey;

public class RestoreKeyActivity extends BaseActivity implements View.OnClickListener, TaskListener {

    private Toolbar         mToolbar;
    private EditText        mInput;
    private Button          mCancel, mNext;
    private LinearLayout    mBtnQr, mBtnPaste;

    private String          mUserInput;

    private BaseChain       mChain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restore_key);

        mToolbar        = findViewById(R.id.tool_bar);
        mInput          = findViewById(R.id.et_address);
        mCancel         = findViewById(R.id.btn_cancel);
        mNext           = findViewById(R.id.btn_next);
        mBtnQr          = findViewById(R.id.btn_qr);
        mBtnPaste       = findViewById(R.id.btn_paste);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCancel.setOnClickListener(this);
        mNext.setOnClickListener(this);
        mBtnQr.setOnClickListener(this);
        mBtnPaste.setOnClickListener(this);

        if (getIntent().getStringExtra("chain") != null) {
            mChain = BaseChain.getChain(getIntent().getStringExtra("chain"));
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

    private void onGenPkeyAccount(String pKey, String address) {
        onShowWaitDialog();
        new GeneratePkeyAccountTask(getBaseApplication(), mChain, this, pKey, address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void onOverridePkeyAccount(String pKey, Account account) {
        new OverridePkeyAccountTask(getBaseApplication(),this, pKey, account).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(isFinishing()) return;
        if (result.taskType == BaseConstant.TASK_INIT_ACCOUNT) {
            if(result.isSuccess) {
                onStartMainActivity(0);
            }

        } else if (result.taskType == BaseConstant.TASK_OVERRIDE_ACCOUNT) {
            if(result.isSuccess) {
                onStartMainActivity(0);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mCancel)) {
            onBackPressed();

        } else if (v.equals(mNext)) {
            mUserInput = mInput.getText().toString().trim();
            if (!WKey.isValidStringPrivateKey(mUserInput)) {
                Toast.makeText(this, R.string.error_invalid_private_Key, Toast.LENGTH_SHORT).show();
                return;
            } else {
                String address = "";
                if (mUserInput.startsWith("0x") || mUserInput.startsWith("0X")) {
                    address = WKey.getDpAddress(mChain, WKey.generatePubKeyHexFromPriv(mUserInput.substring(2)));
                } else {
                    address = WKey.getDpAddress(mChain, WKey.generatePubKeyHexFromPriv(mUserInput));
                }
                Account account = getBaseDao().onSelectExistAccount(address, mChain);
                if (account != null) {
                    if (account.hasPrivateKey) {
                        Toast.makeText(this, R.string.error_already_imported_address, Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                onCheckPassword();
            }

        } else if (v.equals(mBtnQr)) {
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.setOrientationLocked(true);
            integrator.initiateScan();

        } else if (v.equals(mBtnPaste)) {
            ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
            if(clipboard.getPrimaryClip() != null && clipboard.getPrimaryClip().getItemCount() > 0) {
                String userPaste = clipboard.getPrimaryClip().getItemAt(0).coerceToText(this).toString().trim();
                if(TextUtils.isEmpty(userPaste)) {
                    Toast.makeText(this, R.string.error_clipboard_no_data, Toast.LENGTH_SHORT).show();
                    return;
                }
                mInput.setText(userPaste);
                mInput.setSelection(mInput.getText().length());

            } else {
                Toast.makeText(this, R.string.error_clipboard_no_data, Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void onCheckPassword() {
        if(!getBaseDao().onHasPassword()) {
            Intent intent = new Intent(RestoreKeyActivity.this, PasswordSetActivity.class);
            startActivityForResult(intent, BaseConstant.CONST_PW_INIT);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
        } else {
            Intent intent = new Intent(RestoreKeyActivity.this, PasswordCheckActivity.class);
            intent.putExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_SIMPLE_CHECK);
            startActivityForResult(intent, BaseConstant.CONST_PW_SIMPLE_CHECK);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() != null) {
                mInput.setText(result.getContents().trim());
                mInput.setSelection(mInput.getText().length());
            }
        } else if (resultCode == Activity.RESULT_OK) {
            String address = "";
            if (mUserInput.startsWith("0x") || mUserInput.startsWith("0X")) {
                address = WKey.getDpAddress(mChain, WKey.generatePubKeyHexFromPriv(mUserInput.substring(2)));
            } else {
                address = WKey.getDpAddress(mChain, WKey.generatePubKeyHexFromPriv(mUserInput));
            }
            Account account = getBaseDao().onSelectExistAccount(address, mChain);
            if (account != null) {
                onOverridePkeyAccount(mUserInput, account);
            } else {
                onGenPkeyAccount(mUserInput, address);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
