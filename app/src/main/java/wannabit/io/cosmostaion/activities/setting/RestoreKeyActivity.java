package wannabit.io.cosmostaion.activities.setting;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
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
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.activities.PasswordSetActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.utils.WKey;

public class RestoreKeyActivity extends BaseActivity implements View.OnClickListener, TaskListener {

    private Toolbar         mToolbar;
    private EditText        mInput;
    private Button          mCancel, mNext;
    private LinearLayout    mBtnQr, mBtnPaste;

    private String          mUserInput;

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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mCancel)) {
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
        if (!getBaseDao().onHasPassword()) {
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
        if (resultCode != Activity.RESULT_OK) { return; }

        IntentResult pasteResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (pasteResult != null && pasteResult.getContents() != null) {
            mInput.setText(pasteResult.getContents().trim());
            mInput.setSelection(mInput.getText().length());

        } else if (requestCode == BaseConstant.CONST_PW_INIT || requestCode == BaseConstant.CONST_PW_SIMPLE_CHECK) {
            Intent checkintent = new Intent(RestoreKeyActivity.this, WalletDeriveActivity.class);
            checkintent.putExtra("privateKey", mUserInput);
            checkintent.putExtra("privateKeyMode", true);
            startActivity(checkintent);

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
