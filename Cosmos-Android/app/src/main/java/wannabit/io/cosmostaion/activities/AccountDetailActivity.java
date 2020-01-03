package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dialog.Dialog_AccountShow;
import wannabit.io.cosmostaion.dialog.Dialog_ChangeNickName;
import wannabit.io.cosmostaion.dialog.Dialog_DeleteConfirm;
import wannabit.io.cosmostaion.dialog.Dialog_RewardAddressChangeInfo;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.task.FetchTask.PushUpdateTask;
import wannabit.io.cosmostaion.task.SingleFetchTask.CheckWithdrawAddressTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_ATOM;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_IRIS_ATTO;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_KAVA;

public class AccountDetailActivity extends BaseActivity implements View.OnClickListener, TaskListener {


    private Toolbar         mToolbar;
    private Button          mBtnCheck, mBtnDelete;

    private CardView        mCardName;
    private ImageView       mChainImg, mNameEditImg;
    private TextView        mAccountName;

    private CardView        mCardAlarm;
    private SwitchCompat    mAlarmSwitch;
    private TextView        mAlarmMsg;

    private CardView        mCardBody;
    private ImageView       mBtnQr;
    private TextView        mAccountAddress, mAccountGenTime;
    private TextView        mAccountChain, mAccountState, mAccountPath, mImportMsg;
    private RelativeLayout  mPathLayer;


    private CardView        mCardRewardAddress;
    private ImageView       mBtnRewardAddressChange;
    private TextView        mRewardAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_detail);
        mToolbar                = findViewById(R.id.tool_bar);
        mBtnCheck               = findViewById(R.id.btn_check);
        mBtnDelete              = findViewById(R.id.btn_delete);
        mCardName               = findViewById(R.id.card_name);
        mChainImg               = findViewById(R.id.chain_img);
        mNameEditImg            = findViewById(R.id.account_edit);
        mAccountName            = findViewById(R.id.account_name);
        mCardAlarm              = findViewById(R.id.card_alarm);
        mAlarmSwitch            = findViewById(R.id.switch_using_alarm);
        mAlarmMsg               = findViewById(R.id.account_alarm_msg);
        mCardBody               = findViewById(R.id.card_body);
        mBtnQr                  = findViewById(R.id.account_qr);
        mAccountAddress         = findViewById(R.id.account_address);
        mAccountChain           = findViewById(R.id.account_chain);
        mAccountGenTime         = findViewById(R.id.account_import_time);
        mAccountState           = findViewById(R.id.account_import_state);
        mAccountPath            = findViewById(R.id.account_path);
        mImportMsg              = findViewById(R.id.import_msg);
        mPathLayer              = findViewById(R.id.account_path_layer);
        mCardRewardAddress      = findViewById(R.id.card_reward_address);
        mBtnRewardAddressChange = findViewById(R.id.reward_change_btn);
        mRewardAddress          = findViewById(R.id.reward_address);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mBtnCheck.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
        mNameEditImg.setOnClickListener(this);
        mBtnQr.setOnClickListener(this);
        mBtnRewardAddressChange.setOnClickListener(this);

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
    protected void onResume() {
        super.onResume();
        onInitView();
    }

    public void onStartDeleteUser() {
        if(mAccount.hasPrivateKey) {
            Intent intent = new Intent(AccountDetailActivity.this, PasswordCheckActivity.class);
            intent.putExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_DELETE_ACCOUNT);
            intent.putExtra("id", mAccount.id);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
        } else {
            onDeleteAccount(mAccount.id);
        }
    }

    public void onStartChangeRewardAddress() {
        if (!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }

        ArrayList<Balance> balances = getBaseDao().onSelectBalance(mAccount.id);
        boolean hasbalance = false;
        if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            if (WDp.getAvailableCoin(balances, COSMOS_ATOM).compareTo(BigDecimal.ONE) > 0) {
                hasbalance  = true;
            }

        } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            if (WDp.getAvailableCoin(balances, COSMOS_IRIS_ATTO).compareTo(new BigDecimal("400000000000000000")) > 0) {
                hasbalance  = true;
            }

        } else if (mBaseChain.equals(BaseChain.KAVA_MAIN)) {
            if (WDp.getAvailableCoin(balances, COSMOS_KAVA).compareTo(BigDecimal.ONE) > 0) {
                hasbalance  = true;
            }
        }

        if(!hasbalance){
            Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
            return;
        }

        getBaseDao().setLastUser(mAccount.id);
        Intent changeAddress = new Intent(AccountDetailActivity.this, RewardAddressChangeActivity.class);
        changeAddress.putExtra("currentAddresses", mRewardAddress.getText().toString());
        startActivity(changeAddress);
    }

    private void onInitView() {
        if(getIntent() == null || TextUtils.isEmpty(getIntent().getStringExtra("id"))) {
            onBackPressed();
        }
        mAccount = getBaseDao().onSelectAccount(getIntent().getStringExtra("id"));
        if(mAccount == null)  onBackPressed();
        mBaseChain = BaseChain.getChain(mAccount.baseChain);

        onUpdatePushStatusUI();

        if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            mCardName.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg2));
            mCardAlarm.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg2));
            mCardBody.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg2));
            mCardRewardAddress.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg2));
            mChainImg.setImageDrawable(getResources().getDrawable(R.drawable.cosmos_wh_main));
        } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            mCardName.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg4));
            mCardAlarm.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg4));
            mCardAlarm.setVisibility(View.GONE);
            mCardBody.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg4));
            mCardRewardAddress.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg4));
            mChainImg.setImageDrawable(getResources().getDrawable(R.drawable.iris_wh));
        } else if (mBaseChain.equals(BaseChain.BNB_MAIN)) {
            mCardName.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg5));
            mCardAlarm.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg5));
            mCardAlarm.setVisibility(View.GONE);
            mCardBody.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg5));
            mCardRewardAddress.setVisibility(View.GONE);
            mChainImg.setImageDrawable(getResources().getDrawable(R.drawable.binance_ch_img));
        } else if (mBaseChain.equals(BaseChain.KAVA_MAIN)) {
            mCardName.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg7));
            mCardAlarm.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg7));
            mCardAlarm.setVisibility(View.GONE);
            mCardBody.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg7));
            mCardRewardAddress.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg7));
            mCardRewardAddress.setVisibility(View.GONE);
            mChainImg.setImageDrawable(getResources().getDrawable(R.drawable.kava_img));

        }

        new CheckWithdrawAddressTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        if(TextUtils.isEmpty(mAccount.nickName)) {
            mAccountName.setText(getString(R.string.str_my_wallet) + mAccount.id);
        } else {
            mAccountName.setText(mAccount.nickName);
        }

        mAccountAddress.setText(mAccount.address);
        mAccountGenTime.setText(WDp.getDpTime(getBaseContext(), mAccount.importTime));
        mAccountChain.setText(mBaseChain.getChain());

        if(mAccount.hasPrivateKey) {
            mAccountState.setText(getString(R.string.str_with_mnemonic));
            mAccountPath.setText(WDp.getPath(BaseChain.getChain(mAccount.baseChain), Integer.parseInt(mAccount.path)));
            mPathLayer.setVisibility(View.VISIBLE);
            mImportMsg.setVisibility(View.GONE);
            mBtnCheck.setText(getString(R.string.str_check_mnemonic));

        } else {
            mAccountState.setText(getString(R.string.str_only_address));
            mPathLayer.setVisibility(View.GONE);
            mImportMsg.setVisibility(View.VISIBLE);
            mBtnCheck.setText(getString(R.string.str_import_mnemonic));
        }

    }

    private void onUpdatePushStatusUI() {
        if (mAccount.pushAlarm && isNotificationsEnabled()) {
            mAlarmSwitch.setChecked(true);
            mAlarmMsg.setText(getString(R.string.str_alarm_enabled));
        } else {
            mAlarmSwitch.setChecked(false);
            mAlarmMsg.setText(getString(R.string.str_alarm_disabled));
        }

        mAlarmSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isPressed()) {
                    if (!isNotificationsEnabled()) {
                        onShowPushEnableDialog();
                        buttonView.setEnabled(false);
                        return;
                    }
                    new PushUpdateTask(getBaseApplication(), AccountDetailActivity.this, mAccount, getBaseDao().getFCMToken(), isChecked).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    onShowWaitDialog();
                }
            }
        });
    }

    public void onChangeNickName(String name) {
        mAccount.nickName = name;
        if(getBaseDao().onUpdateAccount(mAccount) > 0) {
            onInitView();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnCheck)) {
            if(mAccount.hasPrivateKey) {
                Intent intent = new Intent(AccountDetailActivity.this, PasswordCheckActivity.class);
                intent.putExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_CHECK_MNEMONIC);
                intent.putExtra("checkid", mAccount.id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);

            } else {
                startActivity(new Intent(AccountDetailActivity.this, RestoreActivity.class));
            }

        } else if (v.equals(mBtnDelete)) {
            Bundle bundle = new Bundle();
            bundle.putLong("id", mAccount.id);
            Dialog_DeleteConfirm delete = Dialog_DeleteConfirm.newInstance(bundle);
            delete.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(delete, "dialog").commitNowAllowingStateLoss();

        } else if (v.equals(mNameEditImg)) {
            Bundle bundle = new Bundle();
            bundle.putLong("id", mAccount.id);
            bundle.putString("name", mAccount.nickName);
            Dialog_ChangeNickName delete = Dialog_ChangeNickName.newInstance(bundle);
            delete.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(delete, "dialog").commitNowAllowingStateLoss();

        } else if (v.equals(mBtnQr)) {
            Bundle bundle = new Bundle();
            bundle.putString("address", mAccount.address);
            bundle.putString("title", mAccountName.getText().toString());
            Dialog_AccountShow show = Dialog_AccountShow.newInstance(bundle);
            show.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(show, "dialog").commitNowAllowingStateLoss();

        } else if (v.equals(mBtnRewardAddressChange)) {
            if(!mAccount.hasPrivateKey) {
                Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                add.setCancelable(true);
                getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
                return;
            }

            if(TextUtils.isEmpty(mRewardAddress.getText().toString())) {
                Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();
                return;
            }

            Dialog_RewardAddressChangeInfo change = Dialog_RewardAddressChangeInfo.newInstance();
            change.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(change, "dialog").commitNowAllowingStateLoss();

        }

    }


    @Override
    public void onTaskResponse(TaskResult result) {
        if (result.taskType == BaseConstant.TASK_FETCH_WITHDRAW_ADDRESS) {
            String rewardAddress = (String)result.resultData;
            if(!TextUtils.isEmpty(rewardAddress)) {
                mRewardAddress.setText(rewardAddress.trim());
                if(rewardAddress.equals(mAccount.address)) {
                    mRewardAddress.setTextColor(getResources().getColor(R.color.colorWhite));
                } else {
                    mRewardAddress.setTextColor(getResources().getColor(R.color.colorRed));
                }
            }

        } else if (result.taskType == BaseConstant.TASK_PUSH_STATUS_UPDATE) {
            if (result.isSuccess) {
                mAccount = getBaseDao().onUpdatePushEnabled(mAccount, (boolean)result.resultData);
            }
            onUpdatePushStatusUI();
            onHideWaitDialog();

        }
    }
}
