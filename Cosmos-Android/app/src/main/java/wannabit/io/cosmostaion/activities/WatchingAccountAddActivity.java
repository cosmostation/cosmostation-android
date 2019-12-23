package wannabit.io.cosmostaion.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.UserTask.GenerateEmptyAccountTask;
import wannabit.io.cosmostaion.utils.WKey;

public class WatchingAccountAddActivity extends BaseActivity implements View.OnClickListener, TaskListener {

    private Toolbar mToolbar;
    private EditText mInput;
    private Button mCancel, mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watching_account_add);

        mToolbar        = findViewById(R.id.tool_bar);
        mInput          = findViewById(R.id.et_address);
        mCancel         = findViewById(R.id.btn_cancel);
        mNext           = findViewById(R.id.btn_next);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCancel.setOnClickListener(this);
        mNext.setOnClickListener(this);
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
            String userInput = mInput.getText().toString().trim();
            if (userInput.startsWith("cosmos")) {
                if(userInput.startsWith("cosmosvaloper")) {
                    Toast.makeText(getBaseContext(), R.string.error_invalid_address, Toast.LENGTH_SHORT).show();
                    return;

                } else if (WKey.isValidBech32(userInput)) {
                    onGenNewAccount(BaseChain.COSMOS_MAIN, userInput);
                    return;

                } else {
                    Toast.makeText(getBaseContext(), R.string.error_invalid_address, Toast.LENGTH_SHORT).show();
                    return;
                }

            } else if (userInput.startsWith("iaa")) {
                if (WKey.isValidBech32(userInput)) {
                    onGenNewAccount(BaseChain.IRIS_MAIN, userInput);
                    return;

                } else {
                    Toast.makeText(getBaseContext(), R.string.error_invalid_address, Toast.LENGTH_SHORT).show();
                    return;
                }

            } else if (userInput.startsWith("bnb")) {
                if (WKey.isValidBech32(userInput)) {
                    onGenNewAccount(BaseChain.BNB_MAIN, userInput);
                    return;

                } else {
                    Toast.makeText(getBaseContext(), R.string.error_invalid_address, Toast.LENGTH_SHORT).show();
                    return;
                }

            } else if (userInput.startsWith("kava")) {
                if (WKey.isValidBech32(userInput)) {
                    onGenNewAccount(BaseChain.KAVA_MAIN, userInput);
                    return;

                } else {
                    Toast.makeText(getBaseContext(), R.string.error_invalid_address, Toast.LENGTH_SHORT).show();
                    return;
                }

            } else if (userInput.startsWith("iov")) {
                if (WKey.isValidBech32(userInput)) {
                    onGenNewAccount(BaseChain.IOV_MAIN, userInput);
                    return;

                } else {
                    Toast.makeText(getBaseContext(), R.string.error_invalid_address, Toast.LENGTH_SHORT).show();
                    return;
                }

            } else {
                Toast.makeText(getBaseContext(), R.string.error_invalid_address, Toast.LENGTH_SHORT).show();
                return;
            }
        }

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
                onStartMainActivity(false);
            } else {
                if(result.errorCode == 7001) {
                    Toast.makeText(getBaseContext(), getString(R.string.error_already_imported_address), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), getString(R.string.error_import_errer), Toast.LENGTH_SHORT).show();
                }

            }
        }
    }
}
