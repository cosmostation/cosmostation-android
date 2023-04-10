package wannabit.io.cosmostaion.activities.txs.common;

import static ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateRequest;
import static ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateResponse;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.getChain;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.protobuf.InvalidProtocolBufferException;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.util.ArrayList;

import cosmos.tx.v1beta1.ServiceOuterClass;
import ibc.core.channel.v1.QueryGrpc;
import ibc.lightclients.tendermint.v1.Tendermint;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.activities.TxDetailActivity;
import wannabit.io.cosmostaion.activities.TxDetailgRPCActivity;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.cosmos.MsgGenerator;
import wannabit.io.cosmostaion.cosmos.Signer;
import wannabit.io.cosmostaion.dao.Asset;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.fragment.StepFeeSetFragment;
import wannabit.io.cosmostaion.fragment.StepFeeSetOldFragment;
import wannabit.io.cosmostaion.fragment.StepMemoFragment;
import wannabit.io.cosmostaion.fragment.txs.common.SendStep0Fragment;
import wannabit.io.cosmostaion.fragment.txs.common.SendStep1Fragment;
import wannabit.io.cosmostaion.fragment.txs.common.SendStep4Fragment;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.SimpleBnbSendTask;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.SimpleSendTask;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.Cw20IBCSendGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.Cw20SendGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.Erc20SendGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.IBCTransferGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.SendGrpcTask;
import wannabit.io.cosmostaion.utils.LedgerManager;
import wannabit.io.cosmostaion.utils.WKey;

public class SendActivity extends BaseBroadCastActivity {

    private Toolbar mToolbar;
    private TextView mTitle;
    private ImageView mIvStep;
    private TextView mTvStep;
    private ViewPager mViewPager;
    private SendPageAdapter mPageAdapter;

    public BnbToken mBnbToken;
    public Asset mAsset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        mToolbar = findViewById(R.id.tool_bar);
        mTitle = findViewById(R.id.toolbar_title);
        mIvStep = findViewById(R.id.send_step);
        mTvStep = findViewById(R.id.send_step_msg);
        mViewPager = findViewById(R.id.view_pager);
        mTitle.setText(getString(R.string.str_send_c));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTvStep.setText(getString(R.string.str_send_step_0));
        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);

        mDenom = getIntent().getStringExtra("sendTokenDenom");
        if (mBaseChain.equals(BaseChain.BNB_MAIN)) {
            mBnbToken = getBaseDao().getBnbToken(mDenom);
        }

        mPageAdapter = new SendPageAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(mPageAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(SendActivity.this, R.drawable.step_1_img));
                    mTvStep.setText(getString(R.string.str_send_step_0));
                } else if (i == 1) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(SendActivity.this, R.drawable.step_2_img));
                    mTvStep.setText(getString(R.string.str_send_step_1));
                } else if (i == 2) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(SendActivity.this, R.drawable.step_3_img));
                    mTvStep.setText(getString(R.string.str_tx_step_memo));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                } else if (i == 3) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(SendActivity.this, R.drawable.step_4_img));
                    mTvStep.setText(getString(R.string.str_tx_step_fee));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                } else if (i == 4) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(SendActivity.this, R.drawable.step_5_img));
                    mTvStep.setText(getString(R.string.str_tx_step_confirm));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        mViewPager.setCurrentItem(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAccount == null) finish();
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
    public void onBackPressed() {
        onHideKeyboard();
        if (mViewPager.getCurrentItem() > 0) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
        } else {
            super.onBackPressed();
        }
    }

    public void onNextStep() {
        onHideKeyboard();
        if (mViewPager.getCurrentItem() < 4) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
        }
    }

    public void onBeforeStep() {
        onHideKeyboard();
        if (mViewPager.getCurrentItem() > 0) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
        } else {
            onBackPressed();
        }
    }

    public void onStartSend() {
        if (mAccount.isLedger()) {
            onSendByLedger();
        } else if (getBaseDao().isAutoPass()) {
            onBroadCastSendTx();
        } else {
            Intent intent = new Intent(this, PasswordCheckActivity.class);
            activityResultSendLauncher.launch(intent);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
        }
    }

    public void onStartIbcSend() {
        if (mAccount.isLedger()) {
            onIbcTransferByLedger();
        } else if (getBaseDao().isAutoPass()) {
            onBroadCastIbcSendTx();
        } else {
            Intent intent = new Intent(this, PasswordCheckActivity.class);
            activityResultIbcSendLauncher.launch(intent);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
        }
    }

    public void onStartSendContract() {
        if (getBaseDao().isAutoPass()) {
            onBroadCastSendContractTx();
        } else {
            Intent intent = new Intent(this, PasswordCheckActivity.class);
            activityResultSendContractLauncher.launch(intent);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
        }
    }

    public void onStartIBCContract() {
        if (getBaseDao().isAutoPass()) {
            onBroadCastIbcSendContractTx();
        } else {
            Intent intent = new Intent(this, PasswordCheckActivity.class);
            activityResultIbcSendContractLauncher.launch(intent);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
        }
    }

    public void onStartEVMSend() {
        if (getBaseDao().isAutoPass()) {
            onBroadCastEvmSendsTx();
        } else {
            Intent intent = new Intent(this, PasswordCheckActivity.class);
            activityResultEvmSendLauncher.launch(intent);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
        }
    }

    ActivityResultLauncher<Intent> activityResultSendLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            onShowWaitDialog();
            onBroadCastSendTx();
        }
    });

    ActivityResultLauncher<Intent> activityResultIbcSendLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            onShowWaitDialog();
            onBroadCastIbcSendTx();
        }
    });

    ActivityResultLauncher<Intent> activityResultSendContractLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            onShowWaitDialog();
            onBroadCastSendContractTx();
        }
    });

    ActivityResultLauncher<Intent> activityResultIbcSendContractLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            onShowWaitDialog();
            onBroadCastIbcSendContractTx();
        }
    });

    ActivityResultLauncher<Intent> activityResultEvmSendLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            onShowWaitDialog();
            onBroadCastEvmSendsTx();
        }
    });

    private void onBroadCastSendTx() {
        if (isGRPC(mBaseChain)) {
            new SendGrpcTask(getBaseApplication(), result -> onIntentTx(result), mBaseChain, mAccount, mToAddress, mAmounts, mTxMemo, mTxFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (mBaseChain.equals(BNB_MAIN)) {
            new SimpleBnbSendTask(getBaseApplication(), result -> onIntentTx(result), mAccount, mToAddress, mAmounts, mTxMemo, mTxFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else {
            new SimpleSendTask(getBaseApplication(), result -> onIntentTx(result), mAccount, mToAddress, mAmounts, mTxMemo, mTxFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    private void onBroadCastIbcSendTx() {
        new IBCTransferGrpcTask(getBaseApplication(), result -> onIntentTx(result), mAccount, mBaseChain, mAccount.address, mToAddress, mAmounts.get(0).denom, mAmounts.get(0).amount, mAssetPath, mTxFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void onBroadCastSendContractTx() {
        new Cw20SendGrpcTask(getBaseApplication(), result -> onIntentTx(result), mAccount, mBaseChain, mAccount.address, mToAddress, mMintscanToken.address, mAmounts, mTxMemo, mTxFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void onBroadCastIbcSendContractTx() {
        new Cw20IBCSendGrpcTask(getBaseApplication(), result -> onIntentTx(result), mAccount, mBaseChain, mAccount.address, mToAddress, mMintscanToken.address, mAssetPath, mAmounts, mTxMemo, mTxFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void onBroadCastEvmSendsTx() {
        String url = mChainConfig.rpcUrl();
        if (!url.isEmpty()) {
            Web3j web3 = Web3j.build(new HttpService(url));
            new Erc20SendGrpcTask(getBaseApplication(), result -> onIntentTx(result), web3, mHexValue).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    private void onSendByLedger() {
        runOnUiThread(() -> LedgerManager.getInstance().signAndBroadcast(SendActivity.this, mAccount, new LedgerManager.LedgerSignListener() {
            @NonNull
            @Override
            public String getMessage() {
                ArrayList<Msg> txMsgs = new ArrayList<>();
                Msg singleSendMsg = MsgGenerator.genTransferMsg(mAccount.address, mToAddress, mAmounts, getChain(mAccount.baseChain));
                txMsgs.add(singleSendMsg);
                return WKey.onGetLedgerMessage(getBaseDao(), mChainConfig, mAccount, txMsgs, mTxFee, mTxMemo);
            }

            @NonNull
            @Override
            public ServiceOuterClass.BroadcastTxRequest makeBroadcastTxRequest(@NonNull byte[] currentPubKey) {
                return Signer.getGrpcLedgerSendReq(WKey.onAuthResponse(mBaseChain, mAccount), mToAddress, mAmounts, mTxFee, mTxMemo, LedgerManager.Companion.getInstance().getCurrentPubKey(), WKey.getLedgerSigData(currentPubKey));
            }

            @Override
            public void processResponse(@NonNull TaskResult mResult, @NonNull ServiceOuterClass.BroadcastTxResponse response) {
                onIntentTx(mResult);
            }
        }));
    }

    private void onIbcTransferByLedger() {
        runOnUiThread(() -> LedgerManager.getInstance().signAndBroadcast(SendActivity.this, mAccount, new LedgerManager.LedgerSignListener() {
            @NonNull
            @Override
            public String getMessage() {
                QueryGrpc.QueryBlockingStub txService = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
                QueryChannelClientStateRequest req = QueryChannelClientStateRequest.newBuilder().setChannelId(mAssetPath.channel).setPortId(mAssetPath.port).build();
                QueryChannelClientStateResponse res = txService.channelClientState(req);
                Tendermint.ClientState value = null;
                try {
                    value = Tendermint.ClientState.parseFrom(res.getIdentifiedClientState().getClientState().getValue());
                } catch (InvalidProtocolBufferException e) { }
                ArrayList<Msg> ibcTransferMsgs = MsgGenerator.genIbcTransferMsgs(mAccount.address, mToAddress, mAmounts.get(0), mAssetPath, value.getLatestHeight());
                return WKey.onGetLedgerMessage(getBaseDao(), mChainConfig, mAccount, ibcTransferMsgs, mTxFee, mTxMemo);
            }

            @NonNull
            @Override
            public ServiceOuterClass.BroadcastTxRequest makeBroadcastTxRequest(@NonNull byte[] currentPubKey) {
                QueryGrpc.QueryBlockingStub txService = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
                QueryChannelClientStateRequest req = QueryChannelClientStateRequest.newBuilder().setChannelId(mAssetPath.channel).setPortId(mAssetPath.port).build();
                QueryChannelClientStateResponse res = txService.channelClientState(req);
                Tendermint.ClientState value = null;
                try {
                    value = Tendermint.ClientState.parseFrom(res.getIdentifiedClientState().getClientState().getValue());
                } catch (InvalidProtocolBufferException e) { }
                return Signer.getGrpcLedgerIbcTransferSimulReq(WKey.onAuthResponse(mBaseChain, mAccount), mAccount.address, mToAddress, mAmounts.get(0).denom, mAmounts.get(0).amount, mAssetPath, value.getLatestHeight(), mTxFee, mTxMemo, LedgerManager.Companion.getInstance().getCurrentPubKey(), WKey.getLedgerSigData(currentPubKey));
            }

            @Override
            public void processResponse(@NonNull TaskResult mResult, @NonNull ServiceOuterClass.BroadcastTxResponse response) {
                onIntentTx(mResult);
            }
        }));
    }

    private void onIntentTx(TaskResult result) {
        Intent txIntent;
        if (isGRPC(mBaseChain) || mTxType == BaseConstant.CONST_PW_TX_EVM_TRANSFER) {
            txIntent = new Intent(SendActivity.this, TxDetailgRPCActivity.class);
        } else {
            txIntent = new Intent(SendActivity.this, TxDetailActivity.class);
        }
        txIntent.putExtra("isGen", true);
        txIntent.putExtra("isSuccess", result.isSuccess);
        txIntent.putExtra("errorCode", result.errorCode);
        txIntent.putExtra("errorMsg", result.errorMsg);
        String hash = String.valueOf(result.resultData);
        if (!TextUtils.isEmpty(hash)) {
            if (mTxType == BaseConstant.CONST_PW_TX_EVM_TRANSFER)
                txIntent.putExtra("ethTxHash", hash);
            else txIntent.putExtra("txHash", hash);
        }
        startActivity(txIntent);
    }

    private class SendPageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public SendPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(new SendStep0Fragment());
            mFragments.add(SendStep1Fragment.newInstance());
            mFragments.add(StepMemoFragment.newInstance());
            if (isGRPC(mBaseChain)) {
                mFragments.add(StepFeeSetFragment.newInstance());
            } else {
                mFragments.add(StepFeeSetOldFragment.newInstance());
            }
            mFragments.add(SendStep4Fragment.newInstance());
        }

        @Override
        public BaseFragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            if (getCurrentFragment() != object) {
                mCurrentFragment = ((BaseFragment) object);
            }
            super.setPrimaryItem(container, position, object);
        }

        public BaseFragment getCurrentFragment() {
            return mCurrentFragment;
        }

        public ArrayList<BaseFragment> getFragments() {
            return mFragments;
        }
    }
}
