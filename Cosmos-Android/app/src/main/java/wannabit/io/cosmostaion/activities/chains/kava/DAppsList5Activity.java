package wannabit.io.cosmostaion.activities.chains.kava;

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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.osmosis.ExitPoolActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_Pool_Kava;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.fragment.chains.kava.ListCdpFragment;
import wannabit.io.cosmostaion.fragment.chains.kava.ListHardFragment;
import wannabit.io.cosmostaion.fragment.chains.kava.ListKavaPoolFragment;
import wannabit.io.cosmostaion.fragment.chains.kava.ListKavaSwapFragment;
import wannabit.io.cosmostaion.model.kava.CdpParam;
import wannabit.io.cosmostaion.model.kava.CollateralParam;
import wannabit.io.cosmostaion.model.kava.HardParam;
import wannabit.io.cosmostaion.model.kava.IncentiveParam;
import wannabit.io.cosmostaion.model.kava.IncentiveReward;
import wannabit.io.cosmostaion.model.kava.KavaPriceMarket;
import wannabit.io.cosmostaion.model.kava.MarketPrice;
import wannabit.io.cosmostaion.model.kava.SwapDeposit;
import wannabit.io.cosmostaion.model.kava.SwapParam;
import wannabit.io.cosmostaion.model.kava.SwapPool;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.task.FetchTask.KavaCdpParamTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaHardParamTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaIncentiveParamTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaIncentiveRewardTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaMarketPriceTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaSwapDepositTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaSwapParamTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaSwapPoolTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_KAVA_EXIT_POOL;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_KAVA_JOIN_POOL;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_OSMOSIS_EXIT_POOL;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_CDP_PARAM;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_HARD_PARAM;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_INCENTIVE_PARAM;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_INCENTIVE_REWARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_SWAP_DEPOSIT;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_SWAP_PARAM;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_SWAP_POOL;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_TOKEN_PRICE;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OSMOSIS;

public class DAppsList5Activity extends BaseActivity implements TaskListener {

    private Toolbar                 mToolbar;
    private ViewPager               mDappPager;
    private TabLayout               mDappTapLayer;
    private KavaDApp5PageAdapter    mPageAdapter;

    public ArrayList<SwapPool>      mSwapPoolList = new ArrayList<>();
    public ArrayList<String>        mAllDenoms = new ArrayList<>();
    public ArrayList<SwapDeposit>   mMySwapDepositList = new ArrayList<>();
    public ArrayList<SwapPool>      mMySwapPoolList = new ArrayList<>();
    public ArrayList<SwapPool>      mOtherSwapPoolList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dapp_list);
        mToolbar        = findViewById(R.id.tool_bar);
        mDappTapLayer   = findViewById(R.id.validator_tab);
        mDappPager      = findViewById(R.id.validator_view_pager);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);

        mPageAdapter = new KavaDApp5PageAdapter(getSupportFragmentManager());
        mDappPager.setAdapter(mPageAdapter);
        mDappTapLayer.setupWithViewPager(mDappPager);
        mDappTapLayer.setTabRippleColor(null);

        View tab0 = LayoutInflater.from(this).inflate(R.layout.view_tab_myvalidator, null);
        TextView tabItemText0 = tab0.findViewById(R.id.tabItemText);
        tabItemText0.setText(R.string.str_kava_swap_list);
        tabItemText0.setTextColor(WDp.getTabColor(this, mBaseChain));
        mDappTapLayer.getTabAt(0).setCustomView(tab0);

        View tab1 = LayoutInflater.from(this).inflate(R.layout.view_tab_myvalidator, null);
        TextView tabItemText1 = tab1.findViewById(R.id.tabItemText);
        tabItemText1.setTextColor(WDp.getTabColor(this, mBaseChain));
        tabItemText1.setText(R.string.str_kava_pool_list);
        mDappTapLayer.getTabAt(1).setCustomView(tab1);

        View tab2 = LayoutInflater.from(this).inflate(R.layout.view_tab_myvalidator, null);
        TextView tabItemText2 = tab2.findViewById(R.id.tabItemText);
        tabItemText2.setTextColor(WDp.getTabColor(this, mBaseChain));
        tabItemText2.setText(R.string.str_kava_cdp_list);
        mDappTapLayer.getTabAt(2).setCustomView(tab2);

        View tab3 = LayoutInflater.from(this).inflate(R.layout.view_tab_myvalidator, null);
        TextView tabItemText3 = tab3.findViewById(R.id.tabItemText);
        tabItemText3.setTextColor(WDp.getTabColor(this, mBaseChain));
        tabItemText3.setText(R.string.str_kava_harvest_list);
        mDappTapLayer.getTabAt(3).setCustomView(tab3);

        mDappPager.setOffscreenPageLimit(3);
        mDappPager.setCurrentItem(0, false);

        mDappPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) { }

            @Override
            public void onPageScrollStateChanged(int i) { }

            @Override
            public void onPageSelected(int i) {
                mPageAdapter.mFragments.get(i).onRefreshTab();
            }
        });
        onFetchData();

    }

    public void onCheckStartSwap(String inputCoinDenom, String outCoinDenom, SwapPool swapPool) {
        if (!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
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

    public void onClickMyPool(SwapPool mPool, SwapDeposit mDeposit) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("mKavaPool", mPool);
        bundle.putParcelable("mKavaDeposit", mDeposit);
        Dialog_Pool_Kava add = Dialog_Pool_Kava.newInstance(bundle);
        add.setCancelable(true);
        getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
    }

    public void onCheckStartJoinPool(SwapPool myPool) {
        if (!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }
        BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(DAppsList5Activity.this, mBaseChain, CONST_PW_TX_KAVA_JOIN_POOL, 0);
        String coin0Denom = myPool.coins.get(0).denom;
        String coin1Denom = myPool.coins.get(1).denom;

        BigDecimal available0MaxAmount = getBaseDao().availableAmount(coin0Denom);
        if (coin0Denom.equalsIgnoreCase(TOKEN_KAVA)) { available0MaxAmount = available0MaxAmount.subtract(feeAmount); }
        BigDecimal available1MaxAmount = getBaseDao().availableAmount(coin1Denom);
        if (coin1Denom.equalsIgnoreCase(TOKEN_KAVA)) { available1MaxAmount = available1MaxAmount.subtract(feeAmount); }

        if (available0MaxAmount.compareTo(BigDecimal.ZERO) <= 0 || available1MaxAmount.compareTo(BigDecimal.ZERO) <=0 ) {
            Toast.makeText(DAppsList5Activity.this, R.string.error_not_enough_to_pool, Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(getBaseContext(), DepositPoolActivity.class);
        intent.putExtra("mKavaPool", myPool);
        startActivity(intent);
    }

    public void onCheckStartExitPool(SwapPool otherPool, SwapDeposit swapDeposit) {
        if (!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }

        BigDecimal mainBalance = getBaseDao().availableAmount(TOKEN_KAVA);
        BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), mBaseChain, CONST_PW_TX_KAVA_EXIT_POOL, 0);

        if (mainBalance.compareTo(feeAmount) < 0) {
            Toast.makeText(getBaseContext(), R.string.error_not_enough_to_pool, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(getBaseContext(), WithDrawPoolActivity.class);
        intent.putExtra("mKavaPool", otherPool);
        intent.putExtra("mKavaDeposit", swapDeposit);
        startActivity(intent);
    }

    private int mTaskCount = 0;
    public void onFetchData() {
        mTaskCount = 7;
        onShowWaitDialog();
        mSwapPoolList.clear();
        mMySwapPoolList.clear();
        mOtherSwapPoolList.clear();
        new KavaCdpParamTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaHardParamTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaSwapParamTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaSwapDepositTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaSwapPoolTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaIncentiveParamTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaIncentiveRewardTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        mTaskCount--;
        if (result.taskType == TASK_FETCH_KAVA_CDP_PARAM) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mCdpParam = (CdpParam)result.resultData;
                for (CollateralParam collateralParam: getBaseDao().mCdpParam.collateral_params) {
                    mTaskCount = mTaskCount + 1;
                    new KavaMarketPriceTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain), collateralParam.liquidation_market_id).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
            }

        } else if (result.taskType == TASK_FETCH_KAVA_HARD_PARAM) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mHardParam = (HardParam)result.resultData;
            }

        } else if (result.taskType == TASK_FETCH_KAVA_SWAP_PARAM) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mSwapParam = (SwapParam) result.resultData;
                ArrayList<String> tempList = new ArrayList<>();
                for (KavaPriceMarket.Market kavaPriceMarket: getBaseDao().mKavaPriceMarket) {
                    if (!kavaPriceMarket.market_id.contains(":30")) {
                        tempList.add(kavaPriceMarket.market_id);
                    }
                }
                for(String market_id: tempList) {
                    mTaskCount = mTaskCount + 1;
                    new KavaMarketPriceTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain), market_id).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
            }

        } else if (result.taskType == TASK_FETCH_KAVA_SWAP_DEPOSIT) {
            if (result.isSuccess && result.resultData != null) {
                mMySwapDepositList = (ArrayList<SwapDeposit>) result.resultData;
            }

        } else if (result.taskType == TASK_FETCH_KAVA_SWAP_POOL) {
            if (result.isSuccess && result.resultData != null) {
                mSwapPoolList = (ArrayList<SwapPool>) result.resultData;
            }

        } else if (result.taskType == TASK_FETCH_KAVA_INCENTIVE_PARAM) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mIncentiveParam5 = (IncentiveParam)result.resultData;
            }

        } else if (result.taskType == TASK_FETCH_KAVA_INCENTIVE_REWARD) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mIncentiveRewards = (IncentiveReward)result.resultData;
            }

        } else if (result.taskType == TASK_FETCH_KAVA_TOKEN_PRICE) {
            if (result.isSuccess && result.resultData != null) {
                final MarketPrice price = (MarketPrice)result.resultData;
                getBaseDao().mKavaTokenPrices.put(price.market_id, price);
            }

        }
        if (mTaskCount == 0) {
            for (SwapPool swapPool : mSwapPoolList) {
                for (Coin coin : swapPool.coins) {
                    if (!mAllDenoms.contains(coin.denom)) {
                        mAllDenoms.add(coin.denom);
                        WUtil.onSortingDenom(mAllDenoms, mBaseChain);
                    }
                }
                boolean myPool = false;
                for (SwapDeposit deposit: mMySwapDepositList) {
                    if (deposit.pool_id.equalsIgnoreCase(swapPool.name)) {
                        myPool = true;
                    }
                }
                if (myPool) { mMySwapPoolList.add(swapPool); }
                else { mOtherSwapPoolList.add(swapPool); }
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