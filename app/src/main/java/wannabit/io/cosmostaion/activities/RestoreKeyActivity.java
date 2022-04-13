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

import com.fulldive.wallet.presentation.lockscreen.SetPasswordActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.Dialog_Choice_Type_OKex;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.UserTask.GeneratePkeyAccountTask;
import wannabit.io.cosmostaion.task.UserTask.OverridePkeyAccountTask;
import wannabit.io.cosmostaion.utils.WKey;

public class RestoreKeyActivity extends BaseActivity implements View.OnClickListener, TaskListener {

    private Toolbar toolbar;
    private EditText addressEditText;
    private Button cancelButton, nextButton;
    private LinearLayout scanQRButton, pasteButton;

    private String userInput;

    private BaseChain chain;
    private String okAddress = "";
    private int okAddressType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restore_key);

        toolbar = findViewById(R.id.toolbar);
        addressEditText = findViewById(R.id.addressEditText);
        cancelButton = findViewById(R.id.cancelButton);
        nextButton = findViewById(R.id.nextButton);
        scanQRButton = findViewById(R.id.scanQRButton);
        pasteButton = findViewById(R.id.pasteButton);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cancelButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        scanQRButton.setOnClickListener(this);
        pasteButton.setOnClickListener(this);

        if (getIntent().getStringExtra("chain") != null) {
            chain = BaseChain.getChain(getIntent().getStringExtra("chain"));
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
        showWaitDialog();
        new GeneratePkeyAccountTask(getBaseApplication(), chain, this, pKey, address, customPath).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void onOverridePkeyAccount(String pKey, Account account, int customPath) {
        new OverridePkeyAccountTask(getBaseApplication(), this, pKey, account, customPath).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (isFinishing()) return;
        if ((result.taskType == BaseConstant.TASK_INIT_PKEY_ACCOUNT || result.taskType == BaseConstant.TASK_OVERRIDE_PKEY_ACCOUNT) && result.isSuccess) {
            startMainActivity(0);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(cancelButton)) {
            onBackPressed();

        } else if (v.equals(nextButton)) {
            userInput = addressEditText.getText().toString().trim();
            if (userInput.toLowerCase().startsWith("0x")) {
                userInput = userInput.substring(2);
            }

            if (!WKey.isValidStringPrivateKey(userInput)) {
                Toast.makeText(this, R.string.error_invalid_private_Key, Toast.LENGTH_SHORT).show();
                return;
            }

            if (chain.equals(BaseChain.OKEX_MAIN)) {
                Dialog_Choice_Type_OKex dialog = Dialog_Choice_Type_OKex.newInstance();
                showDialog(dialog, "dialog", false);
                return;
            }

            String address = "";
            if (chain.equals(BaseChain.INJ_MAIN)) {
                address = WKey.generateAddressFromPriv("inj", userInput);
            } else if (chain.equals(BaseChain.EVMOS_MAIN)) {
                address = WKey.generateAddressFromPriv("evmos", userInput);
            } else {
                address = WKey.getDpAddress(chain, WKey.generatePubKeyHexFromPriv(userInput));
            }
            Account account = getBaseDao().onSelectExistAccount(address, chain);
            if (account != null && account.hasPrivateKey) {
                Toast.makeText(this, R.string.error_already_imported_address, Toast.LENGTH_SHORT).show();
                return;
            }
            onCheckPassword();

        } else if (v.equals(scanQRButton)) {
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.setOrientationLocked(true);
            integrator.initiateScan();

        } else if (v.equals(pasteButton)) {
            String userPaste = "";
            addressEditText.setText("");
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
            addressEditText.setText(userPaste);
            addressEditText.setSelection(addressEditText.getText().length());
        }
    }

    private void onCheckPassword() {
        if (!getBaseDao().onHasPassword()) {
            Intent intent = new Intent(RestoreKeyActivity.this, SetPasswordActivity.class);
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
            okAddress = WKey.generateTenderAddressFromPrivateKey(userInput);
        } else {
            okAddress = WKey.generateEthAddressFromPrivateKey(userInput);
        }
        this.okAddressType = okAddressType;
        if (okAddress.isEmpty()) {
            Toast.makeText(this, R.string.error_invalid_private_Key, Toast.LENGTH_SHORT).show();
            return;
        }

        Account existAccount = getBaseDao().onSelectExistAccount(okAddress, chain);
        if (existAccount != null && existAccount.hasPrivateKey) {
            Toast.makeText(this, R.string.error_already_imported_address, Toast.LENGTH_SHORT).show();
            return;
        }
        onCheckPassword();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        IntentResult pasteResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (pasteResult != null && pasteResult.getContents() != null) {
            addressEditText.setText(pasteResult.getContents().trim());
            addressEditText.setSelection(addressEditText.getText().length());
        } else if (requestCode == BaseConstant.CONST_PW_INIT || requestCode == BaseConstant.CONST_PW_SIMPLE_CHECK) {
            String address;
            switch (chain) {
                case OKEX_MAIN:
                    address = okAddress;
                    break;
                case INJ_MAIN:
                    address = WKey.generateAddressFromPriv("inj", userInput);
                    break;
                case EVMOS_MAIN:
                    address = WKey.generateAddressFromPriv("evmos", userInput);
                    break;
                default:
                    address = WKey.getDpAddress(chain, WKey.generatePubKeyHexFromPriv(userInput));
            }
            Account account = getBaseDao().onSelectExistAccount(address, chain);

            int customPath = chain.equals(BaseChain.OKEX_MAIN) ? okAddressType : -1;
            if (account != null) {
                onOverridePkeyAccount(userInput, account, customPath);
            } else {
                onGenPkeyAccount(userInput, address, customPath);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
