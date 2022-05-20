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
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.FilledVerticalButtonAlertDialog;
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
    private String          okAddress = "";
    private int             mOkAddressType;

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
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onGenPkeyAccount(String pKey, String address, int customPath) {
        onShowWaitDialog();
        new GeneratePkeyAccountTask(getBaseApplication(), mChain, this, pKey, address, customPath).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void onOverridePkeyAccount(String pKey, Account account, int customPath) {
        new OverridePkeyAccountTask(getBaseApplication(),this, pKey, account, customPath).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(isFinishing()) return;
        if ((result.taskType == BaseConstant.TASK_INIT_PKEY_ACCOUNT || result.taskType == BaseConstant.TASK_OVERRIDE_PKEY_ACCOUNT) && result.isSuccess) {
            onStartMainActivity(0);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mCancel)) {
            onBackPressed();

        } else if (v.equals(mNext)) {
            mUserInput = mInput.getText().toString().trim();
            if (mUserInput.toLowerCase().startsWith("0x")) {
                mUserInput = mUserInput.substring(2);
            }

            if (!WKey.isValidStringPrivateKey(mUserInput)) {
                Toast.makeText(this, R.string.error_invalid_private_Key, Toast.LENGTH_SHORT).show();
                return;
            }

            if (mChain.equals(BaseChain.OKEX_MAIN)) {
                FilledVerticalButtonAlertDialog.showDoubleButton(this,getString(R.string.str_okex_address_title),null,
                        getString(R.string.str_okex_old_address), view -> onCheckOecAddressType(0), null,
                        getString(R.string.str_okex_new_address), view -> onCheckOecAddressType(1), null,
                        false);
                return;
            }

            String address = "";
            if (mChain.equals(BaseChain.INJ_MAIN)) {
                address = WKey.generateAddressFromPriv("inj", mUserInput);
            } else if (mChain.equals(BaseChain.EVMOS_MAIN)) {
                address = WKey.generateAddressFromPriv("evmos", mUserInput);
            } else {
                address = WKey.getDpAddress(mChain, WKey.generatePubKeyHexFromPriv(mUserInput));
            }
            Account account = getBaseDao().onSelectExistAccount(address, mChain);
            if (account != null && account.hasPrivateKey) {
                Toast.makeText(this, R.string.error_already_imported_address, Toast.LENGTH_SHORT).show();
                return;
            }
            onCheckPassword();

        } else if (v.equals(mBtnQr)) {
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.setOrientationLocked(true);
            integrator.initiateScan();

        } else if (v.equals(mBtnPaste)) {
            String userPaste = "";
            mInput.setText("");
            if (getBaseDao().mCopySalt != null && getBaseDao().mCopyEncResult != null) {
                userPaste = CryptoHelper.doDecryptData(getBaseDao().mCopySalt, getBaseDao().mCopyEncResult.getEncDataString(), getBaseDao().mCopyEncResult.getIvDataString());
                if (TextUtils.isEmpty(userPaste)) {
                    Toast.makeText(this, R.string.error_clipboard_no_data, Toast.LENGTH_SHORT).show();
                    return;
                }

            } else {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                if (clipboard.getPrimaryClip() != null && clipboard.getPrimaryClip().getItemCount() > 0) {
                    userPaste = clipboard.getPrimaryClip().getItemAt(0).coerceToText(this).toString().trim();
                    if (TextUtils.isEmpty(userPaste)) {
                        Toast.makeText(this, R.string.error_clipboard_no_data, Toast.LENGTH_SHORT).show();
                        return;
                    }

                } else {
                    Toast.makeText(this, R.string.error_clipboard_no_data, Toast.LENGTH_SHORT).show();
                }
            }
            mInput.setText(userPaste);
            mInput.setSelection(mInput.getText().length());
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

    public void onCheckOecAddressType(int okAddressType) {
        if (okAddressType == 0) {
            okAddress = WKey.generateTenderAddressFromPrivateKey(mUserInput);
        } else {
            okAddress = WKey.generateEthAddressFromPrivateKey(mUserInput);
        }
        mOkAddressType = okAddressType;
        if (okAddress.isEmpty()) {
            Toast.makeText(this, R.string.error_invalid_private_Key, Toast.LENGTH_SHORT).show();
            return;
        }

        Account existAccount = getBaseDao().onSelectExistAccount(okAddress, mChain);
        if (existAccount != null && existAccount.hasPrivateKey) {
            Toast.makeText(this, R.string.error_already_imported_address, Toast.LENGTH_SHORT).show();
            return;
        }
        onCheckPassword();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK) { return; }

        IntentResult pasteResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (pasteResult != null && pasteResult.getContents() != null) {
            mInput.setText(pasteResult.getContents().trim());
            mInput.setSelection(mInput.getText().length());
        } else if (requestCode == BaseConstant.CONST_PW_INIT || requestCode == BaseConstant.CONST_PW_SIMPLE_CHECK) {
            String address = "";
            if (mChain.equals(BaseChain.OKEX_MAIN)) {
                address = okAddress;
            } else if (mChain.equals(BaseChain.INJ_MAIN)) {
                address = WKey.generateAddressFromPriv("inj", mUserInput);
            } else if (mChain.equals(BaseChain.EVMOS_MAIN)) {
                address = WKey.generateAddressFromPriv("evmos", mUserInput);
            } else {
                address = WKey.getDpAddress(mChain, WKey.generatePubKeyHexFromPriv(mUserInput));
            }
            Account account = getBaseDao().onSelectExistAccount(address, mChain);
            if (account != null) {
                if (mChain.equals(BaseChain.OKEX_MAIN)) {
                    onOverridePkeyAccount(mUserInput, account, mOkAddressType);
                } else {
                    onOverridePkeyAccount(mUserInput, account, -1);
                }
            } else {
                if (mChain.equals(BaseChain.OKEX_MAIN)) {
                    onGenPkeyAccount(mUserInput, address, mOkAddressType);
                } else {
                    onGenPkeyAccount(mUserInput, address, -1);
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
