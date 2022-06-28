package wannabit.io.cosmostaion.activities;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_INIT_MNEMONIC;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.UserTask.GenerateMnemonicTask;
import wannabit.io.cosmostaion.utils.WKey;

public class MnemonicCreateActivity extends BaseActivity {

    private Toolbar mToolbar;
    private LinearLayout[] mWordsLayer = new LinearLayout[24];
    private TextView[] mTvWords = new TextView[24];
    private Button mBtnDerive;

    private ArrayList<String> mWords = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_mnemonic_create);
        mToolbar = findViewById(R.id.tool_bar);
        mBtnDerive = findViewById(R.id.btn_derive);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        for (int i = 0; i < mWordsLayer.length; i++) {
            mWordsLayer[i] = findViewById(getResources().getIdentifier("layer_mnemonic_" + i, "id", this.getPackageName()));
            mTvWords[i] = findViewById(getResources().getIdentifier("tv_mnemonic_" + i, "id", this.getPackageName()));
        }
        onUpdateView();

        mBtnDerive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!getBaseDao().onHasPassword()) {
                    Intent intent = new Intent(MnemonicCreateActivity.this, PasswordSetActivity.class);
                    startActivityForResult(intent, BaseConstant.CONST_PW_INIT);
                    overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);

                } else {
                    Intent intent = new Intent(MnemonicCreateActivity.this, PasswordCheckActivity.class);
                    intent.putExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_SIMPLE_CHECK);
                    startActivityForResult(intent, BaseConstant.CONST_PW_SIMPLE_CHECK);
                    overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == BaseConstant.CONST_PW_INIT || requestCode == BaseConstant.CONST_PW_SIMPLE_CHECK) {
                new GenerateMnemonicTask(getBaseApplication(), this, mWords).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        }
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (result.taskType == TASK_INIT_MNEMONIC) {
            if (result.isSuccess) {
                Intent checkintent = new Intent(MnemonicCreateActivity.this, WalletDeriveActivity.class);
                checkintent.putExtra("id", String.valueOf(result.resultData));
                startActivity(checkintent);
            }
        }
    }

    private void onUpdateView() {
        byte[] mEntropy = WKey.getEntropy();
        mWords = new ArrayList<String>(WKey.getRandomMnemonic(mEntropy));
        for (int i = 0; i < mWords.size(); i++) {
            mTvWords[i].setText(mWords.get(i));
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
}
