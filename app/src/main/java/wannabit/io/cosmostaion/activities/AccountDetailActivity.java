package wannabit.io.cosmostaion.activities;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_NODE_INFO;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_NODE_INFO;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_WITHDRAW_ADDRESS;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.setting.MnemonicDetailActivity;
import wannabit.io.cosmostaion.activities.setting.MnemonicRestoreActivity;
import wannabit.io.cosmostaion.activities.setting.PrivateKeyCheckActivity;
import wannabit.io.cosmostaion.activities.setting.PrivateKeyRestoreActivity;
import wannabit.io.cosmostaion.activities.txs.common.RewardAddressChangeActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.MWords;
import wannabit.io.cosmostaion.dialog.ChangeNickNameDialog;
import wannabit.io.cosmostaion.dialog.CommonAlertDialog;
import wannabit.io.cosmostaion.model.NodeInfo;
import wannabit.io.cosmostaion.task.FetchTask.NodeInfoTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.NodeInfoGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.WithdrawAddressGrpcTask;
import wannabit.io.cosmostaion.utils.WDp;

public class AccountDetailActivity extends BaseActivity implements View.OnClickListener, TaskListener {

    private Toolbar mToolbar;

    private CardView mCardName;
    private ImageView mChainImg, mNameEditImg;
    private TextView mAccountName;

    private CardView mCardBody;
    private ImageView mBtnQr;
    private TextView mAccountAddress, mAccountGenTime, mAccountEthAddress;
    private TextView mAccountChain, mAccountState, mMnemonicName, mAccountPathTitle, mAccountPath, mImportMsg;
    private RelativeLayout mMnemonicLayer, mPathLayer;

    private CardView mCardRewardAddress;
    private ImageView mBtnRewardAddressChange;
    private TextView mRewardAddress;

    private Button mBtnDelete, mBtnCheckKey, mBtnCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_detail);
        mToolbar = findViewById(R.id.tool_bar);
        mCardName = findViewById(R.id.card_name);
        mChainImg = findViewById(R.id.chain_img);
        mNameEditImg = findViewById(R.id.account_edit);
        mAccountName = findViewById(R.id.account_name);
        mCardBody = findViewById(R.id.card_body);
        mBtnQr = findViewById(R.id.account_qr);
        mAccountAddress = findViewById(R.id.account_address);
        mAccountEthAddress = findViewById(R.id.eth_address);
        mAccountChain = findViewById(R.id.account_chain);
        mAccountGenTime = findViewById(R.id.account_import_time);
        mAccountState = findViewById(R.id.account_import_state);
        mMnemonicLayer = findViewById(R.id.mnemonic_name_layer);
        mMnemonicName = findViewById(R.id.mnemonic_name);
        mAccountPathTitle = findViewById(R.id.path_title);
        mAccountPath = findViewById(R.id.account_path);
        mImportMsg = findViewById(R.id.import_msg);
        mPathLayer = findViewById(R.id.account_path_layer);
        mCardRewardAddress = findViewById(R.id.card_reward_address);
        mBtnRewardAddressChange = findViewById(R.id.reward_change_btn);
        mRewardAddress = findViewById(R.id.reward_address);
        mBtnDelete = findViewById(R.id.btn_delete);
        mBtnCheckKey = findViewById(R.id.btn_check_key);
        mBtnCheck = findViewById(R.id.btn_check);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mNameEditImg.setOnClickListener(this);
        mBtnQr.setOnClickListener(this);
        mBtnRewardAddressChange.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
        mBtnCheckKey.setOnClickListener(this);
        mBtnCheck.setOnClickListener(this);

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
            onDeleteAccountExternal(mAccount);
        }
    }

    public void onStartChangeRewardAddress() {
        getBaseDao().setLastUser(mAccount.id);
        Intent changeAddress = new Intent(AccountDetailActivity.this, RewardAddressChangeActivity.class);
        changeAddress.putExtra("currentAddresses", mRewardAddress.getText().toString());
        startActivity(changeAddress);
    }

    private void onInitView() {
        if (getIntent() == null || TextUtils.isEmpty(getIntent().getStringExtra("id"))) {
            onBackPressed();
        }
        mAccount = getBaseDao().onSelectAccount(getIntent().getStringExtra("id"));
        MWords mWords = getBaseDao().onSelectMnemonicById(mAccount.mnemonicId);
        if (mAccount == null) onBackPressed();
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);

        WDp.showChainDp(this, mChainConfig, mCardName, mCardBody, mCardRewardAddress);
        mChainImg.setImageResource(mChainConfig.chainImg());

        if (BaseChain.isGRPC(mBaseChain)) {
            new WithdrawAddressGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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
        setEthAddress(mChainConfig, mAccountEthAddress);
        mAccountGenTime.setText(WDp.getDpTime(getBaseContext(), mAccount.importTime));

        if (mAccount.hasPrivateKey && mAccount.fromMnemonic) {
            mAccountState.setText(getString(R.string.str_with_mnemonic));
            mAccountPath.setText(mChainConfig.getHdPath(mAccount.customPath, mAccount.path));
            mPathLayer.setVisibility(View.VISIBLE);
            mMnemonicLayer.setVisibility(View.VISIBLE);
            mMnemonicName.setText(mWords.getName());
            mImportMsg.setVisibility(View.GONE);
            mBtnCheck.setVisibility(View.VISIBLE);
            mBtnCheckKey.setVisibility(View.VISIBLE);
            mBtnCheck.setText(getString(R.string.str_check_mnemonic));
            mBtnCheckKey.setText(getString(R.string.str_check_private_key));

        } else if (mAccount.hasPrivateKey && !mAccount.fromMnemonic) {
            mAccountState.setText(getString(R.string.str_with_privatekey));
            mPathLayer.setVisibility(View.GONE);
            mMnemonicLayer.setVisibility(View.GONE);
            mImportMsg.setVisibility(View.GONE);
            mBtnCheck.setVisibility(View.VISIBLE);
            mBtnCheckKey.setVisibility(View.VISIBLE);
            mBtnCheckKey.setText(getString(R.string.str_check_private_key));
            if (mBaseChain.equals(BaseChain.OKEX_MAIN)) {
                mPathLayer.setVisibility(View.VISIBLE);
                mAccountPathTitle.setText("Address Type");
                if (mAccount.customPath > 0) {
                    mAccountPath.setText("Ethereum Type Address");
                } else {
                    mAccountPath.setText("Legacy Tendermint Type Address");
                }
                mAccountPath.setTextColor(ContextCompat.getColor(AccountDetailActivity.this, R.color.colorPhoton));
            }

        } else {
            mAccountState.setText(getString(R.string.str_only_address));
            mPathLayer.setVisibility(View.GONE);
            mMnemonicLayer.setVisibility(View.GONE);
            mImportMsg.setVisibility(View.VISIBLE);
            mBtnCheck.setVisibility(View.VISIBLE);
            mBtnCheckKey.setVisibility(View.VISIBLE);
            mBtnCheck.setText(getString(R.string.str_import_mnemonic));
            mBtnCheckKey.setText(getString(R.string.str_insert_private_key));
        }

    }

    public void onChangeNickName(String name) {
        mAccount.nickName = name;
        if (getBaseDao().onUpdateAccount(mAccount) > 0) {
            onInitView();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnCheck)) {
            if (mAccount.hasPrivateKey) {
                if (!mAccount.fromMnemonic) {
                    Toast.makeText(this, R.string.error_no_mnemonic, Toast.LENGTH_SHORT).show();
                    return;
                }

                if (getBaseDao().isAutoPass()) {
                    Intent checkintent = new Intent(this, MnemonicDetailActivity.class);
                    checkintent.putExtra("mnemonicId", mAccount.mnemonicId);
                    startActivity(checkintent);

                } else {
                    Intent intent = new Intent(AccountDetailActivity.this, PasswordCheckActivity.class);
                    intent.putExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_CHECK_MNEMONIC);
                    intent.putExtra("checkid", mAccount.mnemonicId);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
                }

            } else {
                Intent restoreIntent = new Intent(AccountDetailActivity.this, MnemonicRestoreActivity.class);
                restoreIntent.putExtra("chain", mBaseChain.getChain());
                startActivity(restoreIntent);
            }

        } else if (v.equals(mBtnCheckKey)) {
            if (mAccount.hasPrivateKey) {
                if (getBaseDao().isAutoPass()) {
                    String entropy = CryptoHelper.doDecryptData(getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
                    Intent checkintent = new Intent(this, PrivateKeyCheckActivity.class);
                    checkintent.putExtra("checkid", mAccount.id);
                    checkintent.putExtra("entropy", entropy);
                    startActivity(checkintent);

                } else {
                    Intent intent = new Intent(AccountDetailActivity.this, PasswordCheckActivity.class);
                    intent.putExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_CHECK_PRIVATE_KEY);
                    intent.putExtra("checkid", mAccount.id);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
                }

            } else {
                Intent restoreIntent = new Intent(AccountDetailActivity.this, PrivateKeyRestoreActivity.class);
                restoreIntent.putExtra("chain", mBaseChain.getChain());
                startActivity(restoreIntent);
            }

        } else if (v.equals(mBtnDelete)) {
            CommonAlertDialog.showDoubleButton(this, getString(R.string.str_delete_title), getString(R.string.str_delete_msg),
                    CommonAlertDialog.highlightingText(getString(R.string.str_delete)), view -> onStartDeleteUser(),
                    getString(R.string.str_close), null);

        } else if (v.equals(mNameEditImg) && !this.isFinishing()) {
            Bundle bundle = new Bundle();
            bundle.putInt("title", R.string.str_change_account_nickname);
            bundle.putLong("id", mAccount.id);
            bundle.putString("name", mAccount.nickName);
            ChangeNickNameDialog dialog = ChangeNickNameDialog.newInstance(bundle);
            dialog.setCancelable(false);
            dialog.show(getSupportFragmentManager(), "dialog");

        } else if (v.equals(mBtnQr)) {
            onClickQrCopy(mChainConfig, mAccount);

        } else if (v.equals(mBtnRewardAddressChange)) {
            if (!mAccount.hasPrivateKey) {
                onInsertKeyDialog();
                return;
            }
            if (!WDp.isTxFeePayable(this, getBaseDao(), mChainConfig)) {
                Toast.makeText(this, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(mRewardAddress.getText().toString())) {
                Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();
                return;
            }

            CommonAlertDialog.showDoubleButton(this, getString(R.string.str_reward_address_change_title),
                    Html.fromHtml(getString(R.string.str_reward_address_change_msg) + "<br/><br/><font color=\"#ff0000\">" + CommonAlertDialog.highlightingText(getString(R.string.str_reward_address_change_market_no) + "</font>")),
                    getString(R.string.str_continue), view -> onStartChangeRewardAddress(), getString(R.string.str_cancel), null, true);
        }

    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (result.taskType == TASK_GRPC_FETCH_WITHDRAW_ADDRESS) {
            String rewardAddress = (String) result.resultData;
            if (!TextUtils.isEmpty(rewardAddress)) {
                mRewardAddress.setText(rewardAddress.trim());
                if (rewardAddress.equals(mAccount.address)) {
                    mRewardAddress.setTextColor(ContextCompat.getColor(AccountDetailActivity.this, R.color.colorBlackDayNight));
                } else {
                    mRewardAddress.setTextColor(ContextCompat.getColor(AccountDetailActivity.this, R.color.colorRed));
                }
            }

        } else if (result.taskType == TASK_FETCH_NODE_INFO) {
            NodeInfo nodeinfo = (NodeInfo) result.resultData;
            if (nodeinfo != null) {
                mAccountChain.setText(nodeinfo.network);
            }

        } else if (result.taskType == TASK_GRPC_FETCH_NODE_INFO) {
            tendermint.p2p.Types.NodeInfo nodeinfo = (tendermint.p2p.Types.NodeInfo) result.resultData;
            if (nodeinfo != null) {
                new Handler(Looper.getMainLooper()).postDelayed(() -> mAccountChain.setText(nodeinfo.getNetwork()), 100);

            }
        }
    }
}
