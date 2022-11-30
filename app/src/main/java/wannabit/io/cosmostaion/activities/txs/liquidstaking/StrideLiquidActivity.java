package wannabit.io.cosmostaion.activities.txs.liquidstaking;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_STRIDE_LIQUID_STAKING;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_STRIDE_LIQUID_UNSTAKING;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.activities.TxDetailgRPCActivity;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.fragment.StepFeeSetFragment;
import wannabit.io.cosmostaion.fragment.StepMemoFragment;
import wannabit.io.cosmostaion.fragment.txs.liquidstaking.StrideLiquidStep0Fragment;
import wannabit.io.cosmostaion.fragment.txs.liquidstaking.StrideLiquidStep1Fragment;
import wannabit.io.cosmostaion.fragment.txs.liquidstaking.StrideLiquidStep3Fragment;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.LiquidStakingGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.LiquidUnStakingGrpcTask;

public class StrideLiquidActivity extends BaseBroadCastActivity {

    private RelativeLayout mRootView;
    private Toolbar mToolbar;
    private TextView mTitle;
    private ImageView mIvStep;
    private TextView mTvStep;
    private ViewPager mViewPager;
    private LiquidStakingPageAdapter mPageAdapter;

    public String mChainId;
    public String mInputDenom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        mRootView = findViewById(R.id.root_view);
        mToolbar = findViewById(R.id.tool_bar);
        mTitle = findViewById(R.id.toolbar_title);
        mIvStep = findViewById(R.id.send_step);
        mTvStep = findViewById(R.id.send_step_msg);
        mViewPager = findViewById(R.id.view_pager);

        if (getIntent().getIntExtra("txType", -1) != CONST_PW_TX_STRIDE_LIQUID_UNSTAKING) {
            mTitle.setText(getString(R.string.str_liquid_staking));
            mTxType = CONST_PW_TX_STRIDE_LIQUID_STAKING;
            mIvStep.setImageDrawable(ContextCompat.getDrawable(StrideLiquidActivity.this, R.drawable.step_4_img_1));
            mViewPager.setOffscreenPageLimit(3);

        } else {
            mTitle.setText(getString(R.string.str_liquid_unstaking));
            mTxType = CONST_PW_TX_STRIDE_LIQUID_UNSTAKING;
            mIvStep.setImageDrawable(ContextCompat.getDrawable(StrideLiquidActivity.this, R.drawable.step_1_img));
            mViewPager.setOffscreenPageLimit(4);
        }
        mChainId = getIntent().getStringExtra("chainId");
        mInputDenom = getIntent().getStringExtra("stakingDenom");

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTvStep.setText(getString(R.string.str_authz_send_step_1));

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);

        mPageAdapter = new LiquidStakingPageAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPageAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                if (mTxType == CONST_PW_TX_STRIDE_LIQUID_STAKING) {
                    if (i == 0) {
                        mIvStep.setImageDrawable(ContextCompat.getDrawable(StrideLiquidActivity.this, R.drawable.step_4_img_1));
                        mTvStep.setText(getString(R.string.str_authz_send_step_1));
                        mPageAdapter.mCurrentFragment.onRefreshTab();
                    } else if (i == 1) {
                        mIvStep.setImageDrawable(ContextCompat.getDrawable(StrideLiquidActivity.this, R.drawable.step_4_img_2));
                        mTvStep.setText(getString(R.string.str_tx_step_memo));
                    } else if (i == 2) {
                        mIvStep.setImageDrawable(ContextCompat.getDrawable(StrideLiquidActivity.this, R.drawable.step_4_img_3));
                        mTvStep.setText(getString(R.string.str_tx_step_fee));
                        mPageAdapter.mCurrentFragment.onRefreshTab();
                    } else if (i == 3) {
                        mIvStep.setImageDrawable(ContextCompat.getDrawable(StrideLiquidActivity.this, R.drawable.step_4_img_4));
                        mTvStep.setText(getString(R.string.str_tx_step_confirm));
                        mPageAdapter.mCurrentFragment.onRefreshTab();
                    }

                } else {
                    if (i == 0) {
                        mIvStep.setImageDrawable(ContextCompat.getDrawable(StrideLiquidActivity.this, R.drawable.step_1_img));
                        mTvStep.setText(getString(R.string.str_authz_send_step_1));
                    } else if (i == 1) {
                        mIvStep.setImageDrawable(ContextCompat.getDrawable(StrideLiquidActivity.this, R.drawable.step_2_img));
                        mTvStep.setText(getString(R.string.str_authz_send_step_0));
                    } else if (i == 2) {
                        mIvStep.setImageDrawable(ContextCompat.getDrawable(StrideLiquidActivity.this, R.drawable.step_3_img));
                        mTvStep.setText(getString(R.string.str_tx_step_memo));
                        mPageAdapter.mCurrentFragment.onRefreshTab();
                    } else if (i == 3) {
                        mIvStep.setImageDrawable(ContextCompat.getDrawable(StrideLiquidActivity.this, R.drawable.step_4_img));
                        mTvStep.setText(getString(R.string.str_tx_step_fee));
                        mPageAdapter.mCurrentFragment.onRefreshTab();
                    } else if (i == 4) {
                        mIvStep.setImageDrawable(ContextCompat.getDrawable(StrideLiquidActivity.this, R.drawable.step_5_img));
                        mTvStep.setText(getString(R.string.str_tx_step_confirm));
                        mPageAdapter.mCurrentFragment.onRefreshTab();
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        mViewPager.setCurrentItem(0);
        mRootView.setOnClickListener(v -> onHideKeyboard());
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

    public void onStartLiquidStaking() {
        if (getBaseDao().isAutoPass()) {
            onBroadCastTx();
        } else {
            Intent intent = new Intent(StrideLiquidActivity.this, PasswordCheckActivity.class);
            activityResultLauncher.launch(intent);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
        }
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            onShowWaitDialog();
            onBroadCastTx();
        }
    });

    private void onBroadCastTx() {
        if (mTxType == CONST_PW_TX_STRIDE_LIQUID_STAKING) {
            new LiquidStakingGrpcTask(getBaseApplication(), this::intentInfo, mAccount, mBaseChain, mAccount.address, mSwapInCoin.amount, mHostZone.getHostDenom(),mTxMemo, mTxFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            new LiquidUnStakingGrpcTask(getBaseApplication(), this::intentInfo, mAccount, mBaseChain, mAccount.address, mSwapInCoin.amount, mHostZone.getChainId(), mToAddress, mTxMemo, mTxFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    private void intentInfo(TaskResult result) {
        Intent txIntent = new Intent(StrideLiquidActivity.this, TxDetailgRPCActivity.class);
        txIntent.putExtra("isGen", true);
        txIntent.putExtra("isSuccess", result.isSuccess);
        txIntent.putExtra("errorCode", result.errorCode);
        txIntent.putExtra("errorMsg", result.errorMsg);
        String hash = String.valueOf(result.resultData);
        if (!TextUtils.isEmpty(hash)) txIntent.putExtra("txHash", hash);
        startActivity(txIntent);
    }

    private class LiquidStakingPageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public LiquidStakingPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            if (mTxType == CONST_PW_TX_STRIDE_LIQUID_STAKING) {
                mFragments.add(StrideLiquidStep0Fragment.newInstance());
                mFragments.add(StepMemoFragment.newInstance());
                mFragments.add(StepFeeSetFragment.newInstance());
                mFragments.add(StrideLiquidStep3Fragment.newInstance());

            } else {
                mFragments.add(StrideLiquidStep0Fragment.newInstance());
                mFragments.add(StrideLiquidStep1Fragment.newInstance());
                mFragments.add(StepMemoFragment.newInstance());
                mFragments.add(StepFeeSetFragment.newInstance());
                mFragments.add(StrideLiquidStep3Fragment.newInstance());
            }
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
