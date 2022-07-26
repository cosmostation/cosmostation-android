package wannabit.io.cosmostaion.activities.txs.osmosis;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_OSMOSIS_EXIT_POOL;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_OSMOSIS_JOIN_POOL;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_OSMOSIS_SWAP;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_OSMOSIS_ACTIVE_GAUGES;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_OSMOSIS_INCENTIVIZED;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_OSMOSIS_LOCKUP_STATUS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_OSMOSIS_POOL_LIST;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.math.BigDecimal;
import java.util.ArrayList;

import osmosis.gamm.v1beta1.BalancerPool;
import osmosis.incentives.GaugeOuterClass;
import osmosis.lockup.Lock;
import osmosis.poolincentives.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dialog.PaddedVerticalButtonAlertDialog;
import wannabit.io.cosmostaion.fragment.txs.osmosis.ListFarmingFragment;
import wannabit.io.cosmostaion.fragment.txs.osmosis.ListPoolFragment;
import wannabit.io.cosmostaion.fragment.txs.osmosis.ListSwapFragment;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.OsmosisActiveGaugesGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.OsmosisIncentivizedPoolsGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.OsmosisLockupStatusGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.OsmosisPoolListGrpcTask;
import wannabit.io.cosmostaion.utils.WUtil;

public class LabsListActivity extends BaseActivity implements TaskListener {

    private Toolbar mToolbar;
    private ViewPager mLabPager;
    private TabLayout mLabTapLayer;
    private OsmoLabPageAdapter mPageAdapter;

    public ArrayList<BalancerPool.Pool> mPoolList = new ArrayList<>();
    public ArrayList<String> mAllDenoms = new ArrayList<>();
    public ArrayList<BalancerPool.Pool> mPoolMyList = new ArrayList<>();
    public ArrayList<BalancerPool.Pool> mPoolOtherList = new ArrayList<>();
    public ArrayList<QueryOuterClass.IncentivizedPool> mIncentivizedPool = new ArrayList<>();
    public ArrayList<GaugeOuterClass.Gauge> mActiveGauges = new ArrayList<>();
    public ArrayList<Lock.PeriodLock> mPeriodLockUps = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labs_list);
        initView();
        loadData();
    }

    public void initView() {
        mToolbar = findViewById(R.id.tool_bar);
        mLabTapLayer = findViewById(R.id.lab_tab);
        mLabPager = findViewById(R.id.lab_view_pager);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPageAdapter = new LabsListActivity.OsmoLabPageAdapter(getSupportFragmentManager());
        mLabPager.setAdapter(mPageAdapter);
        mLabTapLayer.setupWithViewPager(mLabPager);
        mLabTapLayer.setTabRippleColor(null);

        View tab0 = LayoutInflater.from(this).inflate(R.layout.view_tab_myvalidator, null);
        TextView tabItemText0 = tab0.findViewById(R.id.tabItemText);
        tabItemText0.setText(R.string.str_swap);
        tabItemText0.setTextColor(ContextCompat.getColorStateList(this, R.color.color_tab_myvalidator_osmosis));
        mLabTapLayer.getTabAt(0).setCustomView(tab0);

        View tab1 = LayoutInflater.from(this).inflate(R.layout.view_tab_myvalidator, null);
        TextView tabItemText1 = tab1.findViewById(R.id.tabItemText);
        tabItemText1.setTextColor(ContextCompat.getColorStateList(this, R.color.color_tab_myvalidator_osmosis));
        tabItemText1.setText(R.string.str_pool);
        mLabTapLayer.getTabAt(1).setCustomView(tab1);

        View tab2 = LayoutInflater.from(this).inflate(R.layout.view_tab_myvalidator, null);
        TextView tabItemText2 = tab2.findViewById(R.id.tabItemText);
        tabItemText2.setTextColor(ContextCompat.getColorStateList(this, R.color.color_tab_myvalidator_osmosis));
        tabItemText2.setText(R.string.str_osmosis_earning);
        mLabTapLayer.getTabAt(2).setCustomView(tab2);

        mLabPager.setOffscreenPageLimit(2);
        mLabPager.setCurrentItem(0, false);

        mLabPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }

            @Override
            public void onPageSelected(int i) {
                mPageAdapter.mFragments.get(i).onRefreshTab();
            }
        });
    }

    public void loadData() {
        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);

        onShowWaitDialog();
        onFetchPoolListInfo();
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

    public void onClickMyPool(long poolId) {
        PaddedVerticalButtonAlertDialog.showDoubleButton(this, null, null,
                getString(R.string.str_title_pool_join), view -> onCheckStartJoinPool(poolId),
                getString(R.string.str_title_pool_exit), view -> onCheckStartExitPool(poolId));
    }

    public void onStartSwap(String inputCoinDenom, String outCoinDenom, long poolId) {
        if (!mAccount.hasPrivateKey) {
            onInsertKeyDialog();
            return;
        }

        BigDecimal available = getBaseDao().getAvailable(mChainConfig.mainDenom());
        BigDecimal txFee = WUtil.getEstimateGasFeeAmount(this, mBaseChain, CONST_PW_TX_OSMOSIS_SWAP, 0);
        if (available.compareTo(txFee) < 0) {
            Toast.makeText(this, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(LabsListActivity.this, SwapActivity.class);
        intent.putExtra("inputDenom", inputCoinDenom);
        intent.putExtra("outputDenom", outCoinDenom);
        intent.putExtra("mPoolId", poolId);
        startActivity(intent);
    }

    public void onCheckStartJoinPool(long poolId) {
        if (!mAccount.hasPrivateKey) {
            onInsertKeyDialog();
            return;
        }

        BalancerPool.Pool tempPool = null;
        for (BalancerPool.Pool pool : mPoolList) {
            if (pool.getId() == poolId) {
                tempPool = pool;
            }
        }
        String coin0denom = tempPool.getPoolAssets(0).getToken().getDenom();
        String coin1Denom = tempPool.getPoolAssets(1).getToken().getDenom();

        BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(LabsListActivity.this, mBaseChain, CONST_PW_TX_OSMOSIS_JOIN_POOL, 0);
        BigDecimal coin0Available = getBaseDao().getAvailable(coin0denom);
        BigDecimal coin1Available = getBaseDao().getAvailable(coin1Denom);

        if (coin0denom.equalsIgnoreCase(mChainConfig.mainDenom())) {
            coin0Available = coin0Available.subtract(feeAmount);
        }
        if (coin1Denom.equalsIgnoreCase(mChainConfig.mainDenom())) {
            coin1Available = coin1Available.subtract(feeAmount);
        }

        if (coin0Available.compareTo(BigDecimal.ZERO) <= 0 || coin1Available.compareTo(BigDecimal.ZERO) <= 0) {
            Toast.makeText(LabsListActivity.this, R.string.error_not_enough_to_deposit_pool, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(getBaseContext(), JoinPoolActivity.class);
        intent.putExtra("mPoolId", poolId);
        startActivity(intent);
    }

    public void onCheckStartExitPool(long poolId) {
        if (!mAccount.hasPrivateKey) {
           onInsertKeyDialog();
           return;
        }

        BigDecimal mainBalance = getBaseDao().getAvailable(mChainConfig.mainDenom());
        BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), mBaseChain, CONST_PW_TX_OSMOSIS_EXIT_POOL, 0);

        if (mainBalance.compareTo(feeAmount) < 0) {
            Toast.makeText(getBaseContext(), R.string.error_not_enough_to_withdraw_pool, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(getBaseContext(), ExitPoolActivity.class);
        intent.putExtra("mPoolId", poolId);
        startActivity(intent);
    }


    public void onFetchPoolListInfo() {
        mTaskCount = 4;
        mPoolList = new ArrayList<>();
        mPoolMyList = new ArrayList<>();
        mPoolOtherList = new ArrayList<>();
        mIncentivizedPool = new ArrayList<>();
        mActiveGauges = new ArrayList<>();
        mPeriodLockUps = new ArrayList<>();
        new OsmosisPoolListGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new OsmosisIncentivizedPoolsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new OsmosisActiveGaugesGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new OsmosisLockupStatusGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (isFinishing()) return;
        mTaskCount--;
        if (result.taskType == TASK_GRPC_FETCH_OSMOSIS_POOL_LIST) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mGrpcOsmosisPool = (ArrayList<BalancerPool.Pool>) result.resultData;
                for (BalancerPool.Pool pool : getBaseDao().mGrpcOsmosisPool) {
                    if (getBaseDao().mChainParam != null && getBaseDao().mChainParam.isPoolEnabled(pool.getId())) {
                        mPoolList.add(pool);
                        for (BalancerPool.Pool swap : mPoolList) {
                            for (BalancerPool.PoolAsset poolAsset : swap.getPoolAssetsList()) {
                                if (!mAllDenoms.contains(poolAsset.getToken().getDenom())) {
                                    mAllDenoms.add(poolAsset.getToken().getDenom());
                                }
                            }
                        }

                        if (getBaseDao().getAvailable("gamm/pool/" + pool.getId()) != BigDecimal.ZERO) {
                            mPoolMyList.add(pool);
                        } else {
                            mPoolOtherList.add(pool);
                        }
                    }
                }
            }

        } else if (result.taskType == TASK_GRPC_FETCH_OSMOSIS_INCENTIVIZED) {
            if (result.isSuccess && result.resultData != null) {
                mIncentivizedPool = (ArrayList<QueryOuterClass.IncentivizedPool>) result.resultData;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_OSMOSIS_ACTIVE_GAUGES) {
            if (result.isSuccess && result.resultData != null) {
                mActiveGauges = (ArrayList<GaugeOuterClass.Gauge>) result.resultData;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_OSMOSIS_LOCKUP_STATUS) {
            if (result.isSuccess && result.resultData != null) {
                mPeriodLockUps = (ArrayList<Lock.PeriodLock>) result.resultData;
            }
        }

        if (mTaskCount == 0) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    onHideWaitDialog();
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                }
            }, 300);
        }
    }

    private class OsmoLabPageAdapter extends FragmentPagerAdapter {
        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public OsmoLabPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(ListSwapFragment.newInstance(null));
            mFragments.add(ListPoolFragment.newInstance(null));
            mFragments.add(ListFarmingFragment.newInstance(null));
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
