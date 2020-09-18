package wannabit.io.cosmostaion.activities;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dialog.Dialog_Choice_Iov;
import wannabit.io.cosmostaion.dialog.Dialog_Choice_Kava;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.UserTask.GenerateEmptyAccountTask;
import wannabit.io.cosmostaion.utils.WKey;

import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;

public class WatchingAccountAddActivity extends BaseActivity implements View.OnClickListener, TaskListener {

    private Toolbar mToolbar;
    private EditText mInput;
    private Button mCancel, mNext;
    private LinearLayout mBtnQr, mBtnPaste, mBtnHistory;

    private String mUserInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watching_account_add);

        mToolbar        = findViewById(R.id.tool_bar);
        mInput          = findViewById(R.id.et_address);
        mCancel         = findViewById(R.id.btn_cancel);
        mNext           = findViewById(R.id.btn_next);
        mBtnQr          = findViewById(R.id.btn_qr);
        mBtnPaste       = findViewById(R.id.btn_paste);
        mBtnHistory     = findViewById(R.id.btn_history);
        mBtnHistory.setVisibility(View.GONE);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCancel.setOnClickListener(this);
        mNext.setOnClickListener(this);
        mBtnQr.setOnClickListener(this);
        mBtnPaste.setOnClickListener(this);
        mBtnHistory.setOnClickListener(this);
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
    public void onClick(View v) {
        if(v.equals(mCancel)) {
            onBackPressed();

        } else if (v.equals(mNext)) {
            mUserInput = mInput.getText().toString().trim();
            if (mUserInput.startsWith("cosmos")) {
                if(mUserInput.startsWith("cosmosvaloper")) {
                    Toast.makeText(getBaseContext(), R.string.error_invalid_address, Toast.LENGTH_SHORT).show();
                    return;

                } else if (WKey.isValidBech32(mUserInput)) {
                    onGenNewAccount(BaseChain.COSMOS_MAIN, mUserInput);
                    return;

                } else {
                    Toast.makeText(getBaseContext(), R.string.error_invalid_address, Toast.LENGTH_SHORT).show();
                    return;
                }

            } else if (mUserInput.startsWith("iaa")) {
                if (WKey.isValidBech32(mUserInput)) {
                    onGenNewAccount(BaseChain.IRIS_MAIN, mUserInput);
                    return;

                } else {
                    Toast.makeText(getBaseContext(), R.string.error_invalid_address, Toast.LENGTH_SHORT).show();
                    return;
                }

            } else if (mUserInput.startsWith("bnb")) {
                if (WKey.isValidBech32(mUserInput)) {
                    onGenNewAccount(BaseChain.BNB_MAIN, mUserInput);
                    return;

                } else {
                    Toast.makeText(getBaseContext(), R.string.error_invalid_address, Toast.LENGTH_SHORT).show();
                    return;
                }

            } else if (mUserInput.startsWith("kava")) {
                if (WKey.isValidBech32(mUserInput)) {
                    if (BaseChain.SUPPORT_CHAINS().contains(KAVA_TEST)) {
                        Dialog_Choice_Kava dialog = Dialog_Choice_Kava.newInstance(null);
                        dialog.setCancelable(false);
                        getSupportFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();
                        return;

                    } else {
                        onGenNewAccount(BaseChain.KAVA_MAIN, mUserInput);
                        return;
                    }

                } else {
                    Toast.makeText(getBaseContext(), R.string.error_invalid_address, Toast.LENGTH_SHORT).show();
                    return;
                }

            } else if (mUserInput.startsWith("star")) {
                if (WKey.isValidBech32(mUserInput)) {
                    if (BaseChain.SUPPORT_CHAINS().contains(IOV_TEST)) {
                        Dialog_Choice_Iov dialog = Dialog_Choice_Iov.newInstance(null);
                        dialog.setCancelable(false);
                        getSupportFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();
                        return;

                    } else {
                        onGenNewAccount(BaseChain.IOV_MAIN, mUserInput);
                        return;
                    }

                } else {
                    Toast.makeText(getBaseContext(), R.string.error_invalid_address, Toast.LENGTH_SHORT).show();
                    return;
                }

            } else if (mUserInput.startsWith("tbnb")) {
                if (WKey.isValidBech32(mUserInput)) {
                    onGenNewAccount(BaseChain.BNB_TEST, mUserInput);
                    return;

                } else {
                    Toast.makeText(getBaseContext(), R.string.error_invalid_address, Toast.LENGTH_SHORT).show();
                    return;
                }

            } else if (mUserInput.startsWith("band")) {
                if (WKey.isValidBech32(mUserInput)) {
                    onGenNewAccount(BaseChain.BAND_MAIN, mUserInput);
                    return;

                } else {
                    Toast.makeText(getBaseContext(), R.string.error_invalid_address, Toast.LENGTH_SHORT).show();
                    return;
                }

            } else if (mUserInput.startsWith("okexchain")) {
                if (WKey.isValidBech32(mUserInput)) {
                    onGenNewAccount(BaseChain.OK_TEST, mUserInput);
                    return;

                } else {
                    Toast.makeText(getBaseContext(), R.string.error_invalid_address, Toast.LENGTH_SHORT).show();
                    return;
                }

            } else {
                Toast.makeText(getBaseContext(), R.string.error_invalid_address, Toast.LENGTH_SHORT).show();
                return;
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

    @Override
    public void onChoiceNet(BaseChain chain) {
        super.onChoiceNet(chain);
        onGenNewAccount(chain, mUserInput);
    }

    public void onGenNewAccount(BaseChain chain, String address) {
        onShowWaitDialog();
        new GenerateEmptyAccountTask(getBaseApplication(), WatchingAccountAddActivity.this).execute(chain.getChain(), address);

    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(isFinishing()) return;
        onHideWaitDialog();
        if (result.taskType == BaseConstant.TASK_INIT_EMPTY_ACCOUNT) {
            if(result.isSuccess) {
                onStartMainActivity(0);
            } else {
                if(result.errorCode == 7001) {
                    Toast.makeText(getBaseContext(), getString(R.string.error_already_imported_address), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), getString(R.string.error_import_errer), Toast.LENGTH_SHORT).show();
                }

            }
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
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
