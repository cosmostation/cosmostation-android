package wannabit.io.cosmostaion.activities.txs.kava;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_KAVA_EXIT_POOL;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_KAVA_JOIN_POOL;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_KAVA_SWAP;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_KAVA_CDP_PARAMS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_KAVA_HARD_PARAMS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_KAVA_SWAP_DEPOSITS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_KAVA_SWAP_PARAMS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_KAVA_SWAP_POOLS;

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

import cosmos.base.v1beta1.CoinOuterClass;
import kava.cdp.v1beta1.Genesis;
import kava.hard.v1beta1.Hard;
import kava.swap.v1beta1.QueryOuterClass;
import kava.swap.v1beta1.Swap;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dialog.PaddedVerticalButtonAlertDialog;
import wannabit.io.cosmostaion.fragment.txs.kava.ListCdpFragment;
import wannabit.io.cosmostaion.fragment.txs.kava.ListHardFragment;
import wannabit.io.cosmostaion.fragment.txs.kava.ListKavaPoolFragment;
import wannabit.io.cosmostaion.fragment.txs.kava.ListKavaSwapFragment;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.KavaCdpParamGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.KavaHardParamGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.KavaSwapDepositGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.KavaSwapParamsGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.KavaSwapPoolsGrpcTask;
import wannabit.io.cosmostaion.utils.WUtil;

public class DAppsList5Activity extends BaseActivity implements TaskListener {

    private Toolbar mToolbar;
    private TextView mToolbarTitle;
    private ViewPager mDappPager;
    private TabLayout mDappTapLayer;
    private KavaDApp5PageAdapter mPageAdapter;

    public ArrayList<String> mAllDenoms = new ArrayList<>();
    public ArrayList<QueryOuterClass.PoolResponse> mSwapPoolList = new ArrayList<>();
    public ArrayList<QueryOuterClass.DepositResponse> mMySwapDepositList = new ArrayList<>();
    public ArrayList<QueryOuterClass.PoolResponse> mMySwapPoolList = new ArrayList<>();
    public ArrayList<QueryOuterClass.PoolResponse> mOtherSwapPoolList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defi_list);
        mToolbar = findViewById(R.id.tool_bar);
        mToolbarTitle = findViewById(R.id.toolbar_title);
        mDappTapLayer = findViewById(R.id.lab_tab);
        mDappPager = findViewById(R.id.lab_view_pager);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mToolbarTitle.setText(getString(R.string.str_kava_dapp));
        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);

        mPageAdapter = new KavaDApp5PageAdapter(getSupportFragmentManager());
        mDappPager.setAdapter(mPageAdapter);
        mDappTapLayer.setupWithViewPager(mDappPager);
        mDappTapLayer.setTabRippleColor(null);

        createTab(mChainConfig, R.string.str_kava_swap_list, 0);
        createTab(mChainConfig, R.string.str_kava_pool_list, 0);
        createTab(mChainConfig, R.string.str_kava_cdp_list, 0);
        createTab(mChainConfig, R.string.str_kava_harvest_list, 0);

        mDappPager.setOffscreenPageLimit(3);
        mDappPager.setCurrentItem(0, false);

        mDappPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        onShowWaitDialog();
        onFetchData();
    }

    private void createTab(ChainConfig chainConfig, int stringResourceId, int index) {
        View tab = LayoutInflater.from(this).inflate(R.layout.view_tab_myvalidator, null);
        TextView tabItemText = tab.findViewById(R.id.tabItemText);
        tabItemText.setText(stringResourceId);
        tabItemText.setTextColor(ContextCompat.getColorStateList(this, chainConfig.chainTabColor()));
        mDappTapLayer.getTabAt(0).setCustomView(tab);

        mDappTapLayer.setTabIconTint(ContextCompat.getColorStateList(this, chainConfig.chainColor()));
        mDappTapLayer.setSelectedTabIndicatorColor(ContextCompat.getColor(this, chainConfig.chainColor()));
    }

    public void onCheckStartSwap(String inputCoinDenom, String outCoinDenom, QueryOuterClass.PoolResponse swapPool) {
        if (!mAccount.hasPrivateKey) {
            onInsertKeyDialog();
            return;
        }
        BigDecimal available = getBaseDao().getAvailable(mChainConfig.mainDenom());
        BigDecimal txFee = WUtil.getEstimateGasFeeAmount(this, mBaseChain, CONST_PW_TX_KAVA_SWAP, 0);
        if (available.compareTo(txFee) <= 0) {
            Toast.makeText(this, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(DAppsList5Activity.this, StartSwapActivity.class);
        intent.putExtra("inputDenom", inputCoinDenom);
        intent.putExtra("outputDenom", outCoinDenom);
        intent.putExtra("KavaPool", swapPool);
        startActivity(intent);
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

    public void onClickMyPool(QueryOuterClass.PoolResponse mPool, QueryOuterClass.DepositResponse mDeposit) {
        PaddedVerticalButtonAlertDialog.showDoubleButton(this, null, null,
                getString(R.string.str_title_pool_join), view -> onCheckStartJoinPool(mPool),
                getString(R.string.str_title_pool_exit), view -> onCheckStartExitPool(mPool, mDeposit));
    }

    public void onCheckStartJoinPool(QueryOuterClass.PoolResponse myPool) {
        if (!mAccount.hasPrivateKey) {
            onInsertKeyDialog();
            return;
        }
        BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(DAppsList5Activity.this, mBaseChain, CONST_PW_TX_KAVA_JOIN_POOL, 0);
        String coin0Denom = myPool.getCoins(0).getDenom();
        String coin1Denom = myPool.getCoins(1).getDenom();

        BigDecimal available0MaxAmount = getBaseDao().getAvailable(coin0Denom);
        if (coin0Denom.equalsIgnoreCase(mChainConfig.mainDenom())) {
            available0MaxAmount = available0MaxAmount.subtract(feeAmount);
        }
        BigDecimal available1MaxAmount = getBaseDao().getAvailable(coin1Denom);
        if (coin1Denom.equalsIgnoreCase(mChainConfig.mainDenom())) {
            available1MaxAmount = available1MaxAmount.subtract(feeAmount);
        }

        if (available0MaxAmount.compareTo(BigDecimal.ZERO) <= 0 || available1MaxAmount.compareTo(BigDecimal.ZERO) <= 0) {
            Toast.makeText(DAppsList5Activity.this, R.string.error_not_enough_to_deposit_pool, Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(getBaseContext(), DepositPoolActivity.class);
        intent.putExtra("mKavaPool", myPool);
        startActivity(intent);
    }

    public void onCheckStartExitPool(QueryOuterClass.PoolResponse myPool, QueryOuterClass.DepositResponse myDeposit) {
        if (!mAccount.hasPrivateKey) {
            onInsertKeyDialog();
            return;
        }
        BigDecimal mainBalance = getBaseDao().getAvailable(mChainConfig.mainDenom());
        BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), mBaseChain, CONST_PW_TX_KAVA_EXIT_POOL, 0);

        if (mainBalance.compareTo(feeAmount) < 0) {
            Toast.makeText(getBaseContext(), R.string.error_not_enough_to_withdraw_pool, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(getBaseContext(), WithDrawPoolActivity.class);
        intent.putExtra("mKavaPool", myPool);
        intent.putExtra("mKavaDeposit", myDeposit);
        startActivity(intent);
    }

    private int mTaskCount = 0;
    public void onFetchData() {
        mTaskCount = 5;
        getBaseDao().mSwapParams = null;
        mSwapPoolList = new ArrayList<>();
        mMySwapPoolList = new ArrayList<>();
        mOtherSwapPoolList = new ArrayList<>();
        getBaseDao().mCdpParams = null;
        new KavaSwapParamsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaSwapPoolsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaSwapDepositGrpcTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaCdpParamGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaHardParamGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        mTaskCount--;
        if (result.taskType == TASK_GRPC_FETCH_KAVA_SWAP_PARAMS) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mSwapParams = (Swap.Params) result.resultData;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_KAVA_SWAP_POOLS) {
            if (result.isSuccess && result.resultData != null) {
                mSwapPoolList = (ArrayList<QueryOuterClass.PoolResponse>) result.resultData;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_KAVA_SWAP_DEPOSITS) {
            if (result.isSuccess && result.resultData != null) {
                mMySwapDepositList = (ArrayList<QueryOuterClass.DepositResponse>) result.resultData;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_KAVA_CDP_PARAMS) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mCdpParams = (Genesis.Params) result.resultData;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_KAVA_HARD_PARAMS) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mHardParams = (Hard.Params) result.resultData;
            }

        }

        if (mTaskCount == 0) {
            for (QueryOuterClass.PoolResponse pool : mSwapPoolList) {
                for (CoinOuterClass.Coin coin : pool.getCoinsList()) {
                    if (!mAllDenoms.contains(coin.getDenom())) {
                        mAllDenoms.add(coin.getDenom());
                        WUtil.onSortingDenom(mAllDenoms, mBaseChain);
                    }
                }
                boolean myPool = false;
                for (QueryOuterClass.DepositResponse deposit : mMySwapDepositList) {
                    if (deposit.getPoolId().equalsIgnoreCase(pool.getName())) {
                        myPool = true;
                    }
                }
                if (myPool) {
                    mMySwapPoolList.add(pool);
                } else {
                    mOtherSwapPoolList.add(pool);
                }
            }

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    onHideWaitDialog();
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                }
            }, 300);
        }
    }


    private class KavaDApp5PageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public KavaDApp5PageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(ListKavaSwapFragment.newInstance(null));
            mFragments.add(ListKavaPoolFragment.newInstance(null));
            mFragments.add(ListCdpFragment.newInstance(null));
            mFragments.add(ListHardFragment.newInstance(null));
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