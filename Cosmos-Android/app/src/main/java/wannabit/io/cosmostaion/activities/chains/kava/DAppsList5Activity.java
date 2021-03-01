package wannabit.io.cosmostaion.activities.chains.kava;

import android.os.AsyncTask;
import android.os.Bundle;
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
import wannabit.io.cosmostaion.fragment.chains.kava.ListAuctionFragment;
import wannabit.io.cosmostaion.fragment.chains.kava.ListCdpFragment;
import wannabit.io.cosmostaion.fragment.chains.kava.ListHardFragment;
import wannabit.io.cosmostaion.network.res.ResKavaAuctionParam;
import wannabit.io.cosmostaion.network.res.ResKavaCdpParam;
import wannabit.io.cosmostaion.network.res.ResKavaHardParam;
import wannabit.io.cosmostaion.network.res.ResKavaIncentiveParam5;
import wannabit.io.cosmostaion.task.FetchTask.KavaAuctionParamTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaCdpParamTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaHardParamTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaIncentiveParamTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_AUCTION_PARAM;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_CDP_PARAM;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_HARD_PARAM;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_INCENTIVE_PARAM;

public class DAppsList5Activity extends BaseActivity implements TaskListener {

    private Toolbar mToolbar;
    private ViewPager mDappPager;
    private TabLayout mDappTapLayer;
    private KavaDApp5PageAdapter mPageAdapter;

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
        tabItemText0.setText(R.string.str_kava_cdp_list);
        tabItemText0.setTextColor(WDp.getTabColor(this, mBaseChain));
        mDappTapLayer.getTabAt(0).setCustomView(tab0);

        View tab1 = LayoutInflater.from(this).inflate(R.layout.view_tab_myvalidator, null);
        TextView tabItemText1 = tab1.findViewById(R.id.tabItemText);
        tabItemText1.setTextColor(WDp.getTabColor(this, mBaseChain));
        tabItemText1.setText(R.string.str_kava_harvest_list);
        mDappTapLayer.getTabAt(1).setCustomView(tab1);

        View tab2 = LayoutInflater.from(this).inflate(R.layout.view_tab_myvalidator, null);
        TextView tabItemText2 = tab2.findViewById(R.id.tabItemText);
        tabItemText2.setTextColor(WDp.getTabColor(this, mBaseChain));
        tabItemText2.setText(R.string.str_kava_auction_list);
        mDappTapLayer.getTabAt(2).setCustomView(tab2);

        mDappPager.setOffscreenPageLimit(2);
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
        mTaskCount = 4;
        new KavaCdpParamTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaHardParamTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaAuctionParamTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaIncentiveParamTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    @Override
    public void onTaskResponse(TaskResult result) {
        mTaskCount--;
        if (result.taskType == TASK_FETCH_KAVA_CDP_PARAM) {
            getBaseDao().mCdpParam = (ResKavaCdpParam.CdpParam)result.resultData;

        } else if (result.taskType == TASK_FETCH_KAVA_HARD_PARAM) {
            getBaseDao().mHardParam = (ResKavaHardParam.HardParam)result.resultData;

        } else if (result.taskType == TASK_FETCH_KAVA_AUCTION_PARAM) {
            getBaseDao().mAuctionParam = (ResKavaAuctionParam.AuctionParam)result.resultData;

        } else if (result.taskType == TASK_FETCH_KAVA_INCENTIVE_PARAM) {
            getBaseDao().mIncentiveParam5 = (ResKavaIncentiveParam5.IncentiveParam5)result.resultData;

        }
        if (mTaskCount == 0) {
            mPageAdapter.mCurrentFragment.onRefreshTab();
        }

    }


    private class KavaDApp5PageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public KavaDApp5PageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(ListCdpFragment.newInstance(null));
            mFragments.add(ListHardFragment.newInstance(null));
            mFragments.add(ListAuctionFragment.newInstance(null));
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