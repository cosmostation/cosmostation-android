package wannabit.io.cosmostaion.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dialog.Dialog_ChoiceNet;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResLcdAccountInfo;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.UserTask.GenerateEmptyAccountTask;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;

public class WatchingAccountAddActivity extends BaseActivity implements View.OnClickListener, TaskListener {

    private Toolbar mToolbar;
    private EditText mInput;
    private Button mCancel, mNext;
    private String mUserInputAddress;

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
            if (!TextUtils.isEmpty(userInput) && userInput.startsWith("cosmosvaloper") ) {
                Toast.makeText(getBaseContext(), R.string.error_invalid_address, Toast.LENGTH_SHORT).show();
                return;

            } else if (!TextUtils.isEmpty(userInput) && WKey.isValidBech32(userInput)) {
                mUserInputAddress = userInput;
                onShowNetDialog();
            } else if (!TextUtils.isEmpty(userInput) && WKey.getCosmosDpPubToDpAddress(userInput) != null) {
                mUserInputAddress = WKey.getCosmosDpPubToDpAddress(userInput);
                onShowNetDialog();

            } else {
                Toast.makeText(getBaseContext(), R.string.error_invalid_address, Toast.LENGTH_SHORT).show();
                return;
            }
        }

    }

    private void onShowNetDialog() {
        Dialog_ChoiceNet dialog = Dialog_ChoiceNet.newInstance(null);
        dialog.setCancelable(false);
        getSupportFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();
    }

    @Override
    public void onChoiceNet(BaseChain chain) {
        super.onChoiceNet(chain);
        onShowWaitDialog();
        new GenerateEmptyAccountTask(getBaseApplication(), WatchingAccountAddActivity.this).execute(chain.getChain(), mUserInputAddress);

    }

    private void onCheckInNodeAddress(final String address, final BaseChain chain) {
        onShowWaitDialog();
        ApiClient.getWannabitChain(getBaseContext(), chain).getAccountInfo(address).enqueue(new Callback<ResLcdAccountInfo>() {
            @Override
            public void onResponse(Call<ResLcdAccountInfo> call, Response<ResLcdAccountInfo> response) {
                if(response.isSuccessful() && response.body() != null) {
                    new GenerateEmptyAccountTask(getBaseApplication(), WatchingAccountAddActivity.this).execute(chain.getChain(), address);
                } else {
                    onHideWaitDialog();
                    Toast.makeText(getBaseContext(), getString(R.string.error_address_not_in_node), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResLcdAccountInfo> call, Throwable t) {
                onHideWaitDialog();
                Toast.makeText(getBaseContext(), getString(R.string.error_network), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(isFinishing()) return;
        onHideWaitDialog();
        if (result.taskType == BaseConstant.TASK_INIT_EMPTY_ACCOUNT) {
            if(result.isSuccess) {
                onStartMainActivity();
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
