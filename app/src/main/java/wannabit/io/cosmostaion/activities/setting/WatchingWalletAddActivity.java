package wannabit.io.cosmostaion.activities.setting;

import static wannabit.io.cosmostaion.dialog.SwapCoinListDialog.WATCH_ADDRESS;

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

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dialog.SwapCoinListDialog;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.UserTask.GenerateEmptyAccountTask;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;

public class WatchingWalletAddActivity extends BaseActivity implements View.OnClickListener, TaskListener {

    private Toolbar mToolbar;
    private EditText mInput;
    private Button mCancel, mNext;
    private LinearLayout mBtnQr, mBtnPaste, mBtnHistory;
    private List<BaseChain> mWatchAddressChainList;
    private String mUserInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watching_wallet_add);
        mToolbar = findViewById(R.id.tool_bar);
        mInput = findViewById(R.id.et_address);
        mCancel = findViewById(R.id.btn_cancel);
        mNext = findViewById(R.id.btn_next);
        mBtnQr = findViewById(R.id.btn_qr);
        mBtnPaste = findViewById(R.id.btn_paste);
        mBtnHistory = findViewById(R.id.btn_history);
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
        if (v.equals(mCancel)) {
            onBackPressed();

        } else if (v.equals(mNext)) {
            mUserInput = mInput.getText().toString().trim();
            ArrayList<BaseChain> chains = WDp.getChainsFromAddress(mUserInput);
            if (chains != null) {
                if (chains.size() == 1) {
                    onGenNewAccount(chains.get(0), mUserInput);
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("watchAddressChains", chains);
                    SwapCoinListDialog dialog = SwapCoinListDialog.newInstance(bundle);
                    dialog.setCancelable(true);
                    dialog.show(getSupportFragmentManager(), WATCH_ADDRESS);
                    dialog.setSelectChainsDialogResult(result -> {
                        mWatchAddressChainList = new Gson().fromJson(result, new TypeToken<List<BaseChain>>() {
                        }.getType());
                        for (BaseChain baseChain : mWatchAddressChainList) {
                            if (baseChain.equals(BaseChain.OKEX_MAIN)) {
                                onGenNewAccount(baseChain, mUserInput);
                            } else {
                                try {
                                    onGenNewAccount(baseChain, WKey.convertAddressEthToTender(baseChain, mUserInput));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }
            } else {
                Toast.makeText(this, R.string.error_invalid_address, Toast.LENGTH_SHORT).show();
                return;
            }

        } else if (v.equals(mBtnQr)) {
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.setOrientationLocked(true);
            integrator.initiateScan();

        } else if (v.equals(mBtnPaste)) {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboard.getPrimaryClip() != null && clipboard.getPrimaryClip().getItemCount() > 0) {
                String userPaste = clipboard.getPrimaryClip().getItemAt(0).coerceToText(this).toString().trim();
                if (TextUtils.isEmpty(userPaste)) {
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

    public void onGenNewAccount(BaseChain chain, String address) {
        onShowWaitDialog();
        new GenerateEmptyAccountTask(getBaseApplication(), this).execute(chain.getChain(), address);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (isFinishing()) return;
        onHideWaitDialog();
        if (result.taskType == BaseConstant.TASK_INIT_EMPTY_ACCOUNT) {
            if (result.isSuccess) {
                onStartMainActivity(0);
            } else {
                if (result.errorCode == 7001) {
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
        if (result != null) {
            if (result.getContents() != null) {
                mInput.setText(result.getContents().trim());
                mInput.setSelection(mInput.getText().length());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
