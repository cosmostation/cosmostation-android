package wannabit.io.cosmostaion.activities.chains.starname;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.fragment.chains.starname.MyAccountFragment;
import wannabit.io.cosmostaion.fragment.chains.starname.MyDomainFragment;
import wannabit.io.cosmostaion.model.StarNameAccount;
import wannabit.io.cosmostaion.model.StarNameDomain;
import wannabit.io.cosmostaion.network.res.ResIovStarNameResolve;
import wannabit.io.cosmostaion.task.FetchTask.StarNameMyAccountTask;
import wannabit.io.cosmostaion.task.FetchTask.StarNameMyDomainTask;
import wannabit.io.cosmostaion.task.FetchTask.StarNameResolveTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_MY_STARNAME_ACCOUNT;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_MY_STARNAME_DOMAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_STARNAME_RESOLVE;

public class StarNameListActivity extends BaseActivity implements TaskListener {

    private Toolbar             mToolbar;
    private TextView            mToolbarTitle;
    private ViewPager           mNameServicePager;
    private TabLayout           mNameServiceTapLayer;
    private StarNamePageAdapter mPageAdapter;

    public ArrayList<StarNameDomain>                        mMyStarNameDomains = new ArrayList<>();
    public ArrayList<ResIovStarNameResolve.NameAccount>     mMyStarNameDomainResolves = new ArrayList<>();
    public ArrayList<StarNameAccount>                       mMyStarNameAccounts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starname_list);
        mToolbar            = findViewById(R.id.tool_bar);
        mToolbarTitle       = findViewById(R.id.toolbar_title);
        mNameServiceTapLayer  = findViewById(R.id.name_service_tab);
        mNameServicePager     = findViewById(R.id.name_service_view_pager);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);

        mPageAdapter = new StarNamePageAdapter(getSupportFragmentManager());
        mNameServicePager.setAdapter(mPageAdapter);
        mNameServiceTapLayer.setupWithViewPager(mNameServicePager);
        mNameServiceTapLayer.setTabRippleColor(null);


        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        View tab0 = LayoutInflater.from(this).inflate(R.layout.view_tab_myvalidator, null);
        TextView tabItemText0 = tab0.findViewById(R.id.tabItemText);
        tabItemText0.setText(R.string.str_my_domain);
        tabItemText0.setTextColor(WDp.getTabColor(this, mBaseChain));
        mNameServiceTapLayer.getTabAt(0).setCustomView(tab0);

        View tab1 = LayoutInflater.from(this).inflate(R.layout.view_tab_myvalidator, null);
        TextView tabItemText1 = tab1.findViewById(R.id.tabItemText);
        tabItemText1.setTextColor(WDp.getTabColor(this, mBaseChain));
        tabItemText1.setText(R.string.str_my_account);
        mNameServiceTapLayer.getTabAt(1).setCustomView(tab1);

        mNameServiceTapLayer.setTabIconTint(WDp.getChainTintColor(this, mBaseChain));
        mNameServiceTapLayer.setSelectedTabIndicatorColor(WDp.getChainColor(this, mBaseChain));

        mNameServicePager.setOffscreenPageLimit(2);
        mNameServicePager.setCurrentItem(0, false);
        mNameServicePager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) { }

            @Override
            public void onPageScrollStateChanged(int i) { }

            @Override
            public void onPageSelected(int i) {
                mPageAdapter.mFragments.get(i).onRefreshTab();
            }
        });

        onShowWaitDialog();
        onFetch();

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


    public void onFetch() {
        if (mTaskCount > 0) {
            mPageAdapter.getCurrentFragment().onBusyFetch();
        }
        mTaskCount = 2;
        new StarNameMyAccountTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new StarNameMyDomainTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    public ResIovStarNameResolve.NameAccount getDomainResolve(String domain) {
        for (ResIovStarNameResolve.NameAccount resolve:mMyStarNameDomainResolves) {
            if (resolve.domain.equals(domain)) {
                return resolve;
            }
        }
        return null;

    }

    @Override
    public void onTaskResponse(TaskResult result) {
        mTaskCount--;
        if (isFinishing()) return;
        if (result.taskType == TASK_FETCH_MY_STARNAME_ACCOUNT) {
            mMyStarNameAccounts.clear();
            mMyStarNameAccounts = (ArrayList<StarNameAccount>)result.resultData;

        } else if (result.taskType == TASK_FETCH_MY_STARNAME_DOMAIN) {
            mMyStarNameDomains.clear();
            mMyStarNameDomainResolves.clear();
            mMyStarNameDomains = (ArrayList<StarNameDomain>)result.resultData;
            mTaskCount = mTaskCount + mMyStarNameDomains.size();
            for (StarNameDomain domain:mMyStarNameDomains) {
                new StarNameResolveTask(getBaseApplication(),this, mBaseChain, "*" + domain.name).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }

        } else if (result.taskType == TASK_FETCH_STARNAME_RESOLVE) {
            if (result.isSuccess) {
                ResIovStarNameResolve.NameAccount temp = (ResIovStarNameResolve.NameAccount)result.resultData;
                mMyStarNameDomainResolves.add(temp);
            }

        }

        if (mTaskCount == 0) {
            onHideWaitDialog();
            mPageAdapter.getCurrentFragment().onRefreshTab();
//            WLog.w("mMyStarNameAccounts " + mMyStarNameAccounts.size());
//            WLog.w("mMyStarNameDomains " + mMyStarNameDomains.size());
        }
    }

    private class StarNamePageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public StarNamePageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(MyDomainFragment.newInstance(null));
            mFragments.add(MyAccountFragment.newInstance(null));
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
