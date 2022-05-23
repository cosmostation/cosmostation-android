package wannabit.io.cosmostaion.activities;

import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_NODE_INFO;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_NODE_INFO;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_WITHDRAW_ADDRESS;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.List;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dialog.AlertDialogUtils;
import wannabit.io.cosmostaion.dialog.Dialog_AccountShow;
import wannabit.io.cosmostaion.dialog.Dialog_ChangeNickName;
import wannabit.io.cosmostaion.model.NodeInfo;
import wannabit.io.cosmostaion.task.FetchTask.NodeInfoTask;
import wannabit.io.cosmostaion.task.FetchTask.PushUpdateTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.NodeInfoGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.WithdrawAddressGrpcTask;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class AccountDetailActivity extends BaseActivity implements View.OnClickListener, TaskListener {

    private Toolbar         mToolbar;
    private View            mView;
    private Button          mBtnCheck, mBtnCheckKey, mBtnDelete;

    private CardView        mCardName;
    private ImageView       mChainImg, mNameEditImg;
    private TextView        mAccountName;

    private CardView        mCardAlarm;
    private SwitchCompat    mAlarmSwitch;
    private TextView        mAlarmMsg;

    private CardView        mCardBody;
    private ImageView       mBtnQr;
    private TextView        mAccountAddress, mAccountGenTime;
    private TextView        mAccountChain, mAccountState, mAccountPathTitle, mAccountPath, mImportMsg;
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
        mView                   = findViewById(R.id.view);
        mBtnCheckKey            = findViewById(R.id.btn_check_key);
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
        mAccountPathTitle       = findViewById(R.id.path_title);
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
        mBtnCheckKey.setOnClickListener(this);
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
        if (mAccount.hasPrivateKey) {
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
            AlertDialogUtils.showDoubleButtonDialog(this, getString(R.string.str_only_observe_title), getString(R.string.str_only_observe_msg),
                    getString(R.string.str_add_mnemonics), view -> onAddMnemonicForAccount(),
                    getString(R.string.str_close), null);
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
        WDp.showChainDp(AccountDetailActivity.this, mBaseChain, mCardName, mCardAlarm, mCardBody, mCardRewardAddress);
        WDp.getChainImg(AccountDetailActivity.this, mBaseChain, mChainImg);

        if (isGRPC(mBaseChain)) {
            new WithdrawAddressGrpcTask(getBaseApplication(), this, mBaseChain,  mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new NodeInfoGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            new NodeInfoTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }

        if (TextUtils.isEmpty(mAccount.nickName)) {
            mAccountName.setText(getString(R.string.str_my_wallet) + mAccount.id);
        } else {
            mAccountName.setText(mAccount.nickName);
        }
        mAccountAddress.setText(mAccount.address);
        mAccountGenTime.setText(WDp.getDpTime(getBaseContext(), mAccount.importTime));

        if (mAccount.hasPrivateKey && mAccount.fromMnemonic) {
            mAccountState.setText(getString(R.string.str_with_mnemonic));
            mAccountPath.setText(WDp.getPath(BaseChain.getChain(mAccount.baseChain), Integer.parseInt(mAccount.path), mAccount.customPath));
            mPathLayer.setVisibility(View.VISIBLE);
            mImportMsg.setVisibility(View.GONE);
            mBtnCheck.setVisibility(View.VISIBLE);
            mBtnCheckKey.setVisibility(View.VISIBLE);
            mBtnCheck.setText(getString(R.string.str_check_mnemonic));
            mBtnCheckKey.setText(getString(R.string.str_check_private_key));

        } else if (mAccount.hasPrivateKey && !mAccount.fromMnemonic) {
            mAccountState.setText(getString(R.string.str_with_privatekey));
            mPathLayer.setVisibility(View.GONE);
            mImportMsg.setVisibility(View.GONE);
            mBtnCheck.setVisibility(View.GONE);
            mView.setVisibility(View.GONE);
            mBtnCheckKey.setVisibility(View.VISIBLE);
            mBtnCheckKey.setText(getString(R.string.str_check_private_key));
            if (mBaseChain.equals(OKEX_MAIN)) {
                mPathLayer.setVisibility(View.VISIBLE);
                mAccountPathTitle.setText("Address Type");
                if (mAccount.customPath > 0) { mAccountPath.setText("Ethereum Type Address"); }
                else { mAccountPath.setText("Legacy Tendermint Type Address"); }
                mAccountPath.setTextColor(getResources().getColor(R.color.colorPhoton));
            }

        } else {
            mAccountState.setText(getString(R.string.str_only_address));
            mPathLayer.setVisibility(View.GONE);
            mImportMsg.setVisibility(View.VISIBLE);
            mImportMsg.setTextColor(WDp.getChainColor(getBaseContext(), mBaseChain));
            mBtnCheck.setVisibility(View.VISIBLE);
            mBtnCheckKey.setVisibility(View.VISIBLE);
            mBtnCheck.setText(getString(R.string.str_import_mnemonic));
            mBtnCheckKey.setText(getString(R.string.str_import_key));
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
                Intent restoreIntent = new Intent(AccountDetailActivity.this, RestoreActivity.class);
                restoreIntent.putExtra("chain", mBaseChain.getChain());
                startActivity(restoreIntent);
            }

        } else if (v.equals(mBtnCheckKey)) {
            if(mAccount.hasPrivateKey) {
                Intent intent = new Intent(AccountDetailActivity.this, PasswordCheckActivity.class);
                intent.putExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_CHECK_PRIVATE_KEY);
                intent.putExtra("checkid", mAccount.id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);

            } else {
                Intent restoreIntent = new Intent(AccountDetailActivity.this, RestoreKeyActivity.class);
                restoreIntent.putExtra("chain", mBaseChain.getChain());
                startActivity(restoreIntent);
            }

        } else if (v.equals(mBtnDelete)) {
            int accountSum = 0;
            for (BaseChain baseChain: getBaseDao().dpSortedChains()) {
                accountSum = accountSum + getBaseDao().onSelectAccountsByChain(baseChain).size();
            }
            if (accountSum <= 1) {
                Toast.makeText(AccountDetailActivity.this, getString(R.string.error_reserve_1_account), Toast.LENGTH_SHORT).show();
                return;
            }
            AlertDialogUtils.showDoubleButtonDialog(this, getString(R.string.str_delete_title), getString(R.string.str_delete_msg),
                    getString(R.string.str_delete), view -> onStartDeleteUser(),
                    getString(R.string.str_close), null);
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
            if (!mAccount.hasPrivateKey) {
                AlertDialogUtils.showDoubleButtonDialog(this, getString(R.string.str_only_observe_title), getString(R.string.str_only_observe_msg),
                        getString(R.string.str_add_mnemonics), view -> onAddMnemonicForAccount(),
                        getString(R.string.str_close), null);
                return;
            }

            if (TextUtils.isEmpty(mRewardAddress.getText().toString())) {
                Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();
                return;
            }

            BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), mBaseChain, CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS, 0);
            List<String> availableFeeDenomList = Lists.newArrayList();
            for (String denom : WDp.getGasDenomList(mBaseChain)) {
                if (getBaseDao().getAvailable(denom).compareTo(feeAmount) >= 0) {
                    availableFeeDenomList.add(denom);
                }
            }
            if (availableFeeDenomList.isEmpty()) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }

            AlertDialogUtils.showDoubleButtonDialog(this, getString(R.string.str_reward_address_change_title),
                    getString(R.string.str_reward_address_change_msg) +"\n\n"+ AlertDialogUtils.highlightingText(getString(R.string.str_reward_address_change_market_no)),
                    getString(R.string.str_cancel), null,
                    getString(R.string.str_continue), view -> onStartChangeRewardAddress(), true);
        }

    }


    @Override
    public void onTaskResponse(TaskResult result) {
        if (result.taskType == TASK_GRPC_FETCH_WITHDRAW_ADDRESS) {
            String rewardAddress = (String)result.resultData;
            if (!TextUtils.isEmpty(rewardAddress)) {
                mRewardAddress.setText(rewardAddress.trim());
                if (rewardAddress.equals(mAccount.address)) {
                    mRewardAddress.setTextColor(getResources().getColor(R.color.colorWhite));
                } else {
                    mRewardAddress.setTextColor(getResources().getColor(R.color.colorRed));
                }
            }

        } else if (result.taskType == TASK_FETCH_NODE_INFO) {
            NodeInfo nodeinfo = (NodeInfo)result.resultData;
            if (nodeinfo != null) {
                mAccountChain.setText(nodeinfo.network);
            }

        } else if (result.taskType == TASK_GRPC_FETCH_NODE_INFO) {
            tendermint.p2p.Types.NodeInfo nodeinfo = (tendermint.p2p.Types.NodeInfo)result.resultData;
            if (nodeinfo != null) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAccountChain.setText(nodeinfo.getNetwork());
                    }
                },100);

            }

        }  else if (result.taskType == BaseConstant.TASK_PUSH_STATUS_UPDATE) {
            if (result.isSuccess) {
                mAccount = getBaseDao().onUpdatePushEnabled(mAccount, (boolean)result.resultData);
            }
            onUpdatePushStatusUI();
            onHideWaitDialog();

        }
    }
}
