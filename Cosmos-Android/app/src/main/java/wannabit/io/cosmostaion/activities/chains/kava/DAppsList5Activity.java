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

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.fragment.chains.kava.ListCdpFragment;
import wannabit.io.cosmostaion.fragment.chains.kava.ListHardFragment;
import wannabit.io.cosmostaion.fragment.chains.kava.ListKavaPoolFragment;
import wannabit.io.cosmostaion.fragment.chains.kava.ListKavaSwapFragment;
import wannabit.io.cosmostaion.model.kava.AuctionParam;
import wannabit.io.cosmostaion.model.kava.CdpParam;
import wannabit.io.cosmostaion.model.kava.CollateralParam;
import wannabit.io.cosmostaion.model.kava.HardParam;
import wannabit.io.cosmostaion.model.kava.IncentiveParam;
import wannabit.io.cosmostaion.model.kava.IncentiveReward;
import wannabit.io.cosmostaion.model.kava.MarketPrice;
import wannabit.io.cosmostaion.model.kava.SwapParam;
import wannabit.io.cosmostaion.model.kava.SwapPool;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.task.FetchTask.KavaCdpParamTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaHardParamTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaIncentiveParamTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaIncentiveRewardTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaMarketPriceTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaSwapParamTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaSwapPoolTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_AUCTION_PARAM;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_CDP_PARAM;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_HARD_PARAM;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_INCENTIVE_PARAM;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_INCENTIVE_REWARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_SWAP_PARAM;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_SWAP_POOL;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_TOKEN_PRICE;

public class DAppsList5Activity extends BaseActivity implements TaskListener {

    private Toolbar                 mToolbar;
    private ViewPager               mDappPager;
    private TabLayout               mDappTapLayer;
    private KavaDApp5PageAdapter    mPageAdapter;

    public ArrayList<SwapPool>      mSwapPoolList = new ArrayList<>();
    public ArrayList<String>        mAllDenoms = new ArrayList<>();

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

    private int mTaskCount = 0;
    public void onFetchData() {
        mTaskCount = 6;
        onShowWaitDialog();
        new KavaCdpParamTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaHardParamTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaSwapParamTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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

        } else if (result.taskType == TASK_FETCH_KAVA_AUCTION_PARAM) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mAuctionParam = (AuctionParam)result.resultData;
            }

        } else if (result.taskType == TASK_FETCH_KAVA_SWAP_PARAM) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mSwapParam = (SwapParam) result.resultData;
            }

        } else if (result.taskType == TASK_FETCH_KAVA_SWAP_POOL) {
            if (result.isSuccess && result.resultData != null) {
                mSwapPoolList = (ArrayList<SwapPool>) result.resultData;
                for (SwapPool swapPool : mSwapPoolList) {
                    for (Coin coin : swapPool.coins) {
                        if (!mAllDenoms.contains(coin.denom)) {
                            mAllDenoms.add(coin.denom);
                            WUtil.onSortingDenom(mAllDenoms, mBaseChain);
                        }
                    }
                }
                WLog.w("mAllDenoms : " + mAllDenoms.size());
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