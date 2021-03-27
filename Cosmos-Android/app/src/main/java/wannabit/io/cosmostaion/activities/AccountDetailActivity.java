package wannabit.io.cosmostaion.activities;

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

import java.math.BigDecimal;
import java.util.ArrayList;

import tendermint.p2p.Types;
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
import wannabit.io.cosmostaion.model.NodeInfo;
import wannabit.io.cosmostaion.task.FetchTask.NodeInfoTask;
import wannabit.io.cosmostaion.task.FetchTask.PushUpdateTask;
import wannabit.io.cosmostaion.task.SingleFetchTask.CheckWithdrawAddressTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.NodeInfoGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.WithdrawAddressGrpcTask;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.PERSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_NODE_INFO;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_NODE_INFO;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_WITHDRAW_ADDRESS;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_CERTIK;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SECRET;

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
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }

        if (isGRPC(mBaseChain)) {
            BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), mBaseChain, CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS, 0);
            if (getBaseDao().getAvailable(WDp.mainDenom(mBaseChain)).compareTo(feeAmount) < 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
                return;
            }

            getBaseDao().setLastUser(mAccount.id);
            Intent changeAddress = new Intent(AccountDetailActivity.this, RewardAddressChangeActivity.class);
            changeAddress.putExtra("currentAddresses", mRewardAddress.getText().toString());
            startActivity(changeAddress);


        } else {
            ArrayList<Balance> balances = getBaseDao().onSelectBalance(mAccount.id);
            boolean hasbalance = false;
            if (mBaseChain.equals(IOV_MAIN)) {
                if (WDp.getAvailableCoin(balances, TOKEN_IOV).compareTo(new BigDecimal("100000")) > 0) {
                    hasbalance  = true;
                }

            } else if (mBaseChain.equals(IOV_TEST)) {
                if (WDp.getAvailableCoin(balances, TOKEN_IOV_TEST).compareTo(new BigDecimal("100000")) > 0) {
                    hasbalance  = true;
                }

            } else if (mBaseChain.equals(CERTIK_MAIN) || mBaseChain.equals(CERTIK_TEST)) {
                if (WDp.getAvailableCoin(balances, TOKEN_CERTIK).compareTo(new BigDecimal("5000")) > 0) {
                    hasbalance  = true;
                }

            } else if (mBaseChain.equals(SECRET_MAIN)) {
                if (WDp.getAvailableCoin(balances, TOKEN_SECRET).compareTo(new BigDecimal("20000")) > 0) {
                    hasbalance  = true;
                }

            } else {
                Toast.makeText(getBaseContext(), R.string.error_not_yet, Toast.LENGTH_SHORT).show();
                return;

            }

            if (!hasbalance){
                Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
                return;
            }

            getBaseDao().setLastUser(mAccount.id);
            Intent changeAddress = new Intent(AccountDetailActivity.this, RewardAddressChangeActivity.class);
            changeAddress.putExtra("currentAddresses", mRewardAddress.getText().toString());
            startActivity(changeAddress);
        }


    }

    private void onInitView() {
        if(getIntent() == null || TextUtils.isEmpty(getIntent().getStringExtra("id"))) {
            onBackPressed();
        }
        mAccount = getBaseDao().onSelectAccount(getIntent().getStringExtra("id"));
        if(mAccount == null)  onBackPressed();
        mBaseChain = BaseChain.getChain(mAccount.baseChain);

        onUpdatePushStatusUI();

        if (mBaseChain.equals(COSMOS_MAIN)) {
            mCardName.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgCosmos));
            mCardAlarm.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgCosmos));
            mCardBody.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgCosmos));
            mCardRewardAddress.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgCosmos));
            mChainImg.setImageDrawable(getResources().getDrawable(R.drawable.cosmos_wh_main));

        } else if (mBaseChain.equals(IRIS_MAIN)) {
            mCardName.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgIris));
            mCardAlarm.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgIris));
            mCardAlarm.setVisibility(View.GONE);
            mCardBody.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgIris));
            mCardRewardAddress.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgIris));
            mChainImg.setImageDrawable(getResources().getDrawable(R.drawable.iris_wh));

        } else if (mBaseChain.equals(BNB_MAIN)) {
            mCardName.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgBinance));
            mCardAlarm.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgBinance));
            mCardAlarm.setVisibility(View.GONE);
            mCardBody.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgBinance));
            mCardRewardAddress.setVisibility(View.GONE);
            mChainImg.setImageDrawable(getResources().getDrawable(R.drawable.binance_ch_img));

        } else if (mBaseChain.equals(KAVA_MAIN)) {
            mCardName.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgKava));
            mCardAlarm.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgKava));
            mCardAlarm.setVisibility(View.GONE);
            mCardBody.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgKava));
            mCardRewardAddress.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgKava));
            mCardRewardAddress.setVisibility(View.GONE);
            mChainImg.setImageDrawable(getResources().getDrawable(R.drawable.kava_img));

        } else if (mBaseChain.equals(IOV_MAIN)) {
            mCardName.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgStarname));
            mCardAlarm.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgStarname));
            mCardAlarm.setVisibility(View.GONE);
            mCardBody.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgStarname));
            mCardRewardAddress.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgStarname));
            mCardRewardAddress.setVisibility(View.VISIBLE);
            mChainImg.setImageDrawable(getResources().getDrawable(R.drawable.iov_chain_img));

        } else if (mBaseChain.equals(BAND_MAIN)) {
            mCardName.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgBand));
            mCardAlarm.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgBand));
            mCardAlarm.setVisibility(View.GONE);
            mCardBody.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgBand));
            mCardRewardAddress.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgBand));
            mCardRewardAddress.setVisibility(View.VISIBLE);
            mChainImg.setImageDrawable(getResources().getDrawable(R.drawable.band_chain_img));

        } else if (mBaseChain.equals(CERTIK_MAIN)) {
            mCardName.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgCertik));
            mCardAlarm.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgCertik));
            mCardAlarm.setVisibility(View.GONE);
            mCardBody.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgCertik));
            mCardRewardAddress.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgCertik));
            mCardRewardAddress.setVisibility(View.VISIBLE);
            mChainImg.setImageDrawable(getResources().getDrawable(R.drawable.certik_chain_img));

        } else if (mBaseChain.equals(SECRET_MAIN)) {
            mCardName.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgSecret));
            mCardAlarm.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgSecret));
            mCardAlarm.setVisibility(View.GONE);
            mCardBody.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgSecret));
            mCardRewardAddress.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgSecret));
            mCardRewardAddress.setVisibility(View.VISIBLE);
            mChainImg.setImageDrawable(getResources().getDrawable(R.drawable.chainsecret));

        } else if (mBaseChain.equals(AKASH_MAIN)) {
            mCardName.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgAkash));
            mCardAlarm.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgAkash));
            mCardAlarm.setVisibility(View.GONE);
            mCardBody.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgAkash));
            mCardRewardAddress.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgAkash));
            mCardRewardAddress.setVisibility(View.VISIBLE);
            mChainImg.setImageDrawable(getResources().getDrawable(R.drawable.akash_chain_img));

        } else if (mBaseChain.equals(OKEX_MAIN)) {
            mCardName.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgOkex));
            mCardAlarm.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgOkex));
            mCardAlarm.setVisibility(View.GONE);
            mCardBody.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgOkex));
            mCardRewardAddress.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgOkex));
            mCardRewardAddress.setVisibility(View.GONE);
            mChainImg.setImageDrawable(getResources().getDrawable(R.drawable.okex_chain_img));

        } else if (mBaseChain.equals(PERSIS_MAIN)) {
            mCardName.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgPersis));
            mCardAlarm.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgPersis));
            mCardAlarm.setVisibility(View.GONE);
            mCardBody.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgPersis));
            mCardRewardAddress.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgPersis));
            mCardRewardAddress.setVisibility(View.VISIBLE);
            mChainImg.setImageDrawable(getResources().getDrawable(R.drawable.chainpersistence));

        } else if (mBaseChain.equals(SENTINEL_MAIN)) {
            mCardName.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgSentinel));
            mCardAlarm.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgSentinel));
            mCardAlarm.setVisibility(View.GONE);
            mCardBody.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgSentinel));
            mCardRewardAddress.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgSentinel));
            mCardRewardAddress.setVisibility(View.VISIBLE);
            mChainImg.setImageDrawable(getResources().getDrawable(R.drawable.chainsentinel));

        }


        else if (mBaseChain.equals(COSMOS_TEST)) {
            mCardName.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            mCardAlarm.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            mCardAlarm.setVisibility(View.GONE);
            mCardBody.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            mCardRewardAddress.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            mCardRewardAddress.setVisibility(View.VISIBLE);
            mChainImg.setImageDrawable(getResources().getDrawable(R.drawable.chain_test_cosmos));

        } else if (mBaseChain.equals(IRIS_TEST)) {
            mCardName.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            mCardAlarm.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            mCardAlarm.setVisibility(View.GONE);
            mCardBody.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            mCardRewardAddress.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            mCardRewardAddress.setVisibility(View.VISIBLE);
            mChainImg.setImageDrawable(getResources().getDrawable(R.drawable.chain_test_iris));

        } else if (mBaseChain.equals(BNB_TEST)) {
            mCardName.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            mCardAlarm.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            mCardAlarm.setVisibility(View.GONE);
            mCardBody.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            mCardRewardAddress.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            mCardRewardAddress.setVisibility(View.GONE);
            mChainImg.setImageDrawable(getResources().getDrawable(R.drawable.binancetestnet));

        } else if (mBaseChain.equals(KAVA_TEST)) {
            mCardName.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            mCardAlarm.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            mCardAlarm.setVisibility(View.GONE);
            mCardBody.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            mCardRewardAddress.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            mCardRewardAddress.setVisibility(View.VISIBLE);
            mChainImg.setImageDrawable(getResources().getDrawable(R.drawable.kava_test_img));

        } else if (mBaseChain.equals(IOV_TEST)) {
            mCardName.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            mCardAlarm.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            mCardAlarm.setVisibility(View.GONE);
            mCardBody.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            mCardRewardAddress.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            mCardRewardAddress.setVisibility(View.VISIBLE);
            mChainImg.setImageDrawable(getResources().getDrawable(R.drawable.iov_testnet_img));

        } else if (mBaseChain.equals(OK_TEST)) {
            mCardName.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            mCardAlarm.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            mCardAlarm.setVisibility(View.GONE);
            mCardBody.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            mCardRewardAddress.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            mCardRewardAddress.setVisibility(View.GONE);
            mChainImg.setImageDrawable(getResources().getDrawable(R.drawable.okex_testnet_img));

        } else if (mBaseChain.equals(CERTIK_TEST)) {
            mCardName.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            mCardAlarm.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            mCardAlarm.setVisibility(View.GONE);
            mCardBody.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            mCardRewardAddress.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            mCardRewardAddress.setVisibility(View.VISIBLE);
            mChainImg.setImageDrawable(getResources().getDrawable(R.drawable.certik_testnet_img));

        }

        if (isGRPC(mBaseChain)) {
            new WithdrawAddressGrpcTask(getBaseApplication(), this, mBaseChain,  mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new NodeInfoGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else {
            new CheckWithdrawAddressTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new NodeInfoTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }

        if (TextUtils.isEmpty(mAccount.nickName)) {
            mAccountName.setText(getString(R.string.str_my_wallet) + mAccount.id);
        } else {
            mAccountName.setText(mAccount.nickName);
        }

        mAccountAddress.setText(mAccount.address);
        mAccountGenTime.setText(WDp.getDpTime(getBaseContext(), mAccount.importTime));

        if (mAccount.hasPrivateKey) {
            mAccountState.setText(getString(R.string.str_with_mnemonic));
            mAccountPath.setText(WDp.getPath(BaseChain.getChain(mAccount.baseChain), Integer.parseInt(mAccount.path), mAccount.newBip44));
            mPathLayer.setVisibility(View.VISIBLE);
            mImportMsg.setVisibility(View.GONE);
            mBtnCheck.setText(getString(R.string.str_check_mnemonic));

        } else {
            mAccountState.setText(getString(R.string.str_only_address));
            mPathLayer.setVisibility(View.GONE);
            mImportMsg.setVisibility(View.VISIBLE);
            mImportMsg.setTextColor(WDp.getChainColor(getBaseContext(), mBaseChain));
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
                Intent restoreIntent = new Intent(AccountDetailActivity.this, RestoreActivity.class);
                restoreIntent.putExtra("chain", mBaseChain.getChain());
                startActivity(restoreIntent);
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
            if (!mAccount.hasPrivateKey) {
                Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                add.setCancelable(true);
                getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
                return;
            }

            if (TextUtils.isEmpty(mRewardAddress.getText().toString())) {
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
            if (!TextUtils.isEmpty(rewardAddress)) {
                mRewardAddress.setText(rewardAddress.trim());
                if(rewardAddress.equals(mAccount.address)) {
                    mRewardAddress.setTextColor(getResources().getColor(R.color.colorWhite));
                } else {
                    mRewardAddress.setTextColor(getResources().getColor(R.color.colorRed));
                }
            }

        } else if (result.taskType == TASK_GRPC_FETCH_WITHDRAW_ADDRESS) {
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
            Types.DefaultNodeInfo nodeinfo = (Types.DefaultNodeInfo)result.resultData;
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
