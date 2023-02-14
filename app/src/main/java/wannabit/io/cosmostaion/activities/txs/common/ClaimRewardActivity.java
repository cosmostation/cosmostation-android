package wannabit.io.cosmostaion.activities.txs.common;

import static wannabit.io.cosmostaion.base.BaseChain.getChain;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_REWARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_ALL_REWARDS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_WITHDRAW_ADDRESS;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ledger.live.ble.app.BleCosmosHelper;

import java.util.ArrayList;

import cosmos.distribution.v1beta1.Distribution;
import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.cosmos.MsgGenerator;
import wannabit.io.cosmostaion.cosmos.Signer;
import wannabit.io.cosmostaion.dialog.CommonAlertDialog;
import wannabit.io.cosmostaion.fragment.StepFeeSetFragment;
import wannabit.io.cosmostaion.fragment.StepMemoFragment;
import wannabit.io.cosmostaion.fragment.txs.common.RewardStep0Fragment;
import wannabit.io.cosmostaion.fragment.txs.common.RewardStep3Fragment;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.AllRewardGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.WithdrawAddressGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.ClaimRewardsGrpcTask;
import wannabit.io.cosmostaion.utils.LedgerManager;
import wannabit.io.cosmostaion.utils.WKey;

public class ClaimRewardActivity extends BaseBroadCastActivity implements TaskListener {

    private Toolbar mToolbar;
    private TextView mTitle;
    private ImageView mIvStep;
    private TextView mTvStep;
    private ViewPager mViewPager;
    private RewardPageAdapter mPageAdapter;

    public String mWithdrawAddress;
    private int mTaskCount;

    private CommonAlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        mToolbar = findViewById(R.id.tool_bar);
        mTitle = findViewById(R.id.toolbar_title);
        mIvStep = findViewById(R.id.send_step);
        mTvStep = findViewById(R.id.send_step_msg);
        mViewPager = findViewById(R.id.view_pager);
        mTitle.setText(getString(R.string.str_reward_c));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mIvStep.setImageDrawable(ContextCompat.getDrawable(ClaimRewardActivity.this, R.drawable.step_4_img_1));
        mTvStep.setText(getString(R.string.str_reward_step_1));

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);
        mTxType = CONST_PW_TX_SIMPLE_REWARD;

        mValAddresses = getIntent().getStringArrayListExtra("valOpAddresses");

        mPageAdapter = new RewardPageAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mPageAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(ClaimRewardActivity.this, R.drawable.step_4_img_1));
                    mTvStep.setText(getString(R.string.str_reward_step_1));

                } else if (i == 1) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(ClaimRewardActivity.this, R.drawable.step_4_img_2));
                    mTvStep.setText(getString(R.string.str_tx_step_memo));

                } else if (i == 2) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(ClaimRewardActivity.this, R.drawable.step_4_img_3));
                    mTvStep.setText(getString(R.string.str_tx_step_fee));
                    mPageAdapter.mCurrentFragment.onRefreshTab();

                } else if (i == 3) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(ClaimRewardActivity.this, R.drawable.step_4_img_4));
                    mTvStep.setText(getString(R.string.str_tx_step_confirm));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        mViewPager.setCurrentItem(0);
        onFetchReward();
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
        if (mViewPager.getCurrentItem() < mPageAdapter.getCount() - 1) {
            onHideKeyboard();
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
        }
    }

    public void onBeforeStep() {
        if (mViewPager.getCurrentItem() > 0) {
            onHideKeyboard();
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
        } else {
            onBackPressed();
        }
    }

    private void onFetchReward() {
        if (mTaskCount > 0) return;
        mTaskCount = 2;
        new AllRewardGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new WithdrawAddressGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void onStartReward() {
        if (mAccount.isLedger()) {
            onClaimRewardByLedger();

        } else if (getBaseDao().isAutoPass()) {
            onBroadCastTx();
        } else {
            Intent intent = new Intent(ClaimRewardActivity.this, PasswordCheckActivity.class);
            activityResultLauncher.launch(intent);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
        }
    }

    private void onClaimRewardByLedger() {
        new Thread(() -> {
            ArrayList<Msg> claimRewardMsgs = MsgGenerator.genWithdrawDeleMsgs(mAccount.address, mValAddresses);
            String message = WKey.onGetLedgerMessage(getBaseDao(), mChainConfig, mAccount, claimRewardMsgs, mTxFee, mTxMemo);

            runOnUiThread(() -> LedgerManager.getInstance().pickLedgerDevice(this, new LedgerManager.ConnectListener() {
                @Override
                public void error(@NonNull LedgerManager.ErrorType errorType) {
                    if (isFinishing()) {
                        return;
                    }
                    runOnUiThread(() -> CommonAlertDialog.showDoubleButton(ClaimRewardActivity.this, getString(R.string.str_ledger_error), getString(errorType.getDescriptionResourceId()), getString(R.string.str_cancel), null, getString(R.string.str_retry), view -> onStartReward()));
                }

                @Override
                public void connected() {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(() -> {
                        mDialog = CommonAlertDialog.makeSecondImageSingleButton(ClaimRewardActivity.this, getString(R.string.str_ledger_approve_title), getString(R.string.str_ledger_approve_msg), getString(R.string.str_cancel), view -> finish(), R.drawable.icon_ledger);
                        mDialog.setCancelable(false);
                        mDialog.create();
                    }, 0);

                    BleCosmosHelper.Companion.getAddress(LedgerManager.Companion.getInstance().getBleManager(), mChainConfig.addressPrefix(), mAccount.path, new BleCosmosHelper.GetAddressListener() {
                        @Override
                        public void success(@NonNull String s, @NonNull byte[] bytes) {
                            if (isFinishing()) {
                                return;
                            }
                            LedgerManager.getInstance().setCurrentPubKey(bytes);
                            if (!mAccount.address.equals(s)) {
                                return;
                            } else {
                                runOnUiThread(() -> {
                                    mDialog.show();
                                });
                            }

                            BleCosmosHelper.Companion.sign(LedgerManager.Companion.getInstance().getBleManager(), mAccount.path, message, new BleCosmosHelper.SignListener() {
                                @Override
                                public void success(@NonNull byte[] bytes) {
                                    if (isFinishing()) {
                                        return;
                                    }
                                    new Thread(() -> {
                                        ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = Signer.getGrpcLedgerClaimRewardsReq(WKey.onAuthResponse(mBaseChain, mAccount), mValAddresses, mTxFee, mTxMemo, LedgerManager.Companion.getInstance().getCurrentPubKey(), WKey.getLedgerSigData(bytes));
                                        ServiceOuterClass.BroadcastTxResponse response = Signer.getGrpcLedgerBroadcastResponse(broadcastTxRequest, mChainConfig);
                                        TaskResult mResult = new TaskResult();
                                        mResult.resultData = response.getTxResponse().getTxhash();

                                        if (response.getTxResponse().getCode() > 0) {
                                            mResult.errorCode = response.getTxResponse().getCode();
                                            mResult.errorMsg = response.getTxResponse().getRawLog();
                                            mResult.isSuccess = false;
                                        } else {
                                            mResult.isSuccess = true;
                                        }
                                        onCommonIntentTx(ClaimRewardActivity.this, mResult);
                                    }).start();
                                }

                                @Override
                                public void error(@NonNull String s, @NonNull String s1) {
                                    if (isFinishing()) {
                                        return;
                                    }
                                    runOnUiThread(() -> {
                                        mDialog.dismiss();
                                        if (s.equalsIgnoreCase("6986")) {
                                            Toast.makeText(ClaimRewardActivity.this, R.string.str_ledger_tx_reject_msg, Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(ClaimRewardActivity.this, R.string.str_ledger_error, Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });
                        }

                        @Override
                        public void error(@NonNull String s, @NonNull String s1) {
                            if (isFinishing()) {
                                return;
                            }
                            runOnUiThread(() -> {
                                mDialog.dismiss();
                                if (s.equalsIgnoreCase("6986")) {
                                    Toast.makeText(ClaimRewardActivity.this, R.string.str_ledger_tx_reject_msg, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ClaimRewardActivity.this, R.string.str_ledger_error, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
            }));
        }).start();
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            onShowWaitDialog();
            onBroadCastTx();
        }
    });

    private void onBroadCastTx() {
        new ClaimRewardsGrpcTask(getBaseApplication(), result -> {
            onCommonIntentTx(ClaimRewardActivity.this, result);
        }, mBaseChain, mAccount, mValAddresses, mTxMemo, mTxFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        mTaskCount--;
        if (isFinishing()) return;
        if (result.taskType == TASK_GRPC_FETCH_ALL_REWARDS) {
            ArrayList<Distribution.DelegationDelegatorReward> rewards = (ArrayList<Distribution.DelegationDelegatorReward>) result.resultData;
            if (rewards != null) {
                getBaseDao().mGrpcRewards = rewards;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_WITHDRAW_ADDRESS) {
            mWithdrawAddress = (String) result.resultData;
        }

        if (mTaskCount == 0) {
            mPageAdapter.mCurrentFragment.onRefreshTab();
        }
    }

    private class RewardPageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public RewardPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(RewardStep0Fragment.newInstance());
            mFragments.add(StepMemoFragment.newInstance());
            mFragments.add(StepFeeSetFragment.newInstance());
            mFragments.add(RewardStep3Fragment.newInstance());
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
