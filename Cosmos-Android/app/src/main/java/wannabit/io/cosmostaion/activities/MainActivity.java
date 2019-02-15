package wannabit.io.cosmostaion.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BondingState;
import wannabit.io.cosmostaion.dao.Reward;
import wannabit.io.cosmostaion.dao.UnBondingState;
import wannabit.io.cosmostaion.fragment.DashBoardAtomFragment;
import wannabit.io.cosmostaion.fragment.DashBoardPhotonFragment;
import wannabit.io.cosmostaion.fragment.DashBoardTest2Fragment;
import wannabit.io.cosmostaion.fragment.MainHistoryFragment;
import wannabit.io.cosmostaion.fragment.MainRewardFragment;
import wannabit.io.cosmostaion.fragment.MainSendFragment;
import wannabit.io.cosmostaion.fragment.MainVoteFragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.task.AccountInfoTask;
import wannabit.io.cosmostaion.task.AllValidatorInfoTask;
import wannabit.io.cosmostaion.task.BondingStateTask;
import wannabit.io.cosmostaion.task.RewardFromValidatorTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.TotalRewardTask;
import wannabit.io.cosmostaion.task.UnBondingStateTask;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.widget.FadePageTransformer;
import wannabit.io.cosmostaion.widget.ScrollingPagerIndicator;
import wannabit.io.cosmostaion.widget.StopViewPager;
import wannabit.io.cosmostaion.widget.TintableImageView;

public class MainActivity extends BaseActivity implements TaskListener {

//    private ArrayList<Account>          accouts = new ArrayList<>();


    public Account                     mAccount;
    public ArrayList<Validator>        mAllValidators = new ArrayList<>();
    public ArrayList<Validator>        mMyValidators = new ArrayList<>();
    public ArrayList<Validator>        mElseValidators = new ArrayList<>();
    public ArrayList<Balance>          mBalances = new ArrayList<>();
    public ArrayList<BondingState>     mBondings = new ArrayList<>();
    public ArrayList<UnBondingState>   mUnbondings = new ArrayList<>();
    public ArrayList<Reward>           mRewards = new ArrayList<>();
    public ArrayList<Coin>             mTotalRewards = new ArrayList<>();

    private AppBarLayout                mAppbar;
    private CollapsingToolbarLayout     mCollapsingToolbarLayout;
    private ViewPager                   mDashBoardPager;
    private Toolbar                     mToolbar;
    private TextView                    mToolbarNickName;
    private TextView                    mToolbarAddress;
    private ScrollingPagerIndicator     mIndicator;

    private StopViewPager               mContentsPager;
    private TabLayout                   mTabLayer;
    private MainViewPageAdapter         mPageAdapter;
    private DashBoardPageAdapter        mDashPageAdapter;

    private int                         mTaskCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAppbar                     = findViewById(R.id.app_bar);
        mCollapsingToolbarLayout    = findViewById(R.id.collapse_layer);
        mDashBoardPager             = findViewById(R.id.dashboard_pager);
        mToolbar                    = findViewById(R.id.tool_bar);
        mToolbarNickName            = findViewById(R.id.toolbar_nickName);
        mToolbarAddress             = findViewById(R.id.toolbar_Address);
        mIndicator                  = findViewById(R.id.dashboard_indicator);

        mContentsPager              = findViewById(R.id.view_pager);
        mTabLayer                   = findViewById(R.id.bottom_tab);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mPageAdapter = new MainViewPageAdapter(getSupportFragmentManager());
        mContentsPager.setPageTransformer(false, new FadePageTransformer());
        mContentsPager.setOffscreenPageLimit(3);
        mContentsPager.setAdapter(mPageAdapter);
        mTabLayer.setupWithViewPager(mContentsPager);
        mTabLayer.setTabRippleColor(null);

        View tab0 = LayoutInflater.from(this).inflate(R.layout.view_tab_item, null);
        TintableImageView tabItemIcon0  = tab0.findViewById(R.id.tabItemIcon);
        TextView tabItemText0  = tab0.findViewById(R.id.tabItemText);
        tabItemIcon0.setImageResource(R.drawable.send_ic);
        tabItemText0.setText(R.string.str_main_send);
        mTabLayer.getTabAt(0).setCustomView(tab0);

        View tab1 = LayoutInflater.from(this).inflate(R.layout.view_tab_item, null);
        TintableImageView   tabItemIcon1  = tab1.findViewById(R.id.tabItemIcon);
        TextView            tabItemText1  = tab1.findViewById(R.id.tabItemText);
        tabItemIcon1.setImageResource(R.drawable.reward_ic);
        tabItemText1.setText(R.string.str_main_reward);
        mTabLayer.getTabAt(1).setCustomView(tab1);

        View tab2 = LayoutInflater.from(this).inflate(R.layout.view_tab_item, null);
        TintableImageView   tabItemIcon2  = tab2.findViewById(R.id.tabItemIcon);
        TextView            tabItemText2  = tab2.findViewById(R.id.tabItemText);
        tabItemIcon2.setImageResource(R.drawable.ts_ic);
        tabItemText2.setText(R.string.str_main_history);
        mTabLayer.getTabAt(2).setCustomView(tab2);

        View tab3 = LayoutInflater.from(this).inflate(R.layout.view_tab_item, null);
        TintableImageView   tabItemIcon3  = tab3.findViewById(R.id.tabItemIcon);
        TextView            tabItemText3  = tab3.findViewById(R.id.tabItemText);
        tabItemIcon3.setImageResource(R.drawable.vote_ic);
        tabItemText3.setText(R.string.str_main_vote);
        mTabLayer.getTabAt(3).setCustomView(tab3);

        mContentsPager.setCurrentItem(1, false);

        mDashPageAdapter = new DashBoardPageAdapter(getSupportFragmentManager());
        mDashBoardPager.setOffscreenPageLimit(3);
        mDashBoardPager.setAdapter(mDashPageAdapter);
        mIndicator.attachToPager(mDashBoardPager);

        mDashBoardPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) { }

            @Override
            public void onPageSelected(int i) {
                mDashPageAdapter.mCurrentFragment.onRefreshTab();
            }

            @Override
            public void onPageScrollStateChanged(int i) { }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if(getBaseDao().onSelectAccounts().size() > 0) {
//            WLog.w("first : " + getBaseDao().onSelectAccounts().get(0).id);
//        }
//
//        WLog.w("getLastUser : " + getBaseDao().getLastUser());
        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        if(mAccount == null) {
            //Show error dialog!
            WLog.w("mAccount null");
            return;
        }
//        mToolbarNickName.setText(mAccount.nickName);
        mToolbarAddress.setText(mAccount.address);



        onInitFetchAccount();
        mBalances = getBaseDao().onSelectBalance(mAccount.id);
        mBondings = getBaseDao().onSelectBondingStates(mAccount.id);
        mUnbondings = getBaseDao().onSelectUnbondingStates(mAccount.id);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_qr:
                return true;
            case R.id.menu_setting:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    private void onFetchCurrentPage(int page) {
//        if(!isFinishing() && mContentsPager.getCurrentItem() == page) {
//            mPageAdapter.mCurrentFragment.onRefreshTab();
//        }
//    }

    private void onFetchCurrentPage() {
        if(!isFinishing()) {
            mPageAdapter.mCurrentFragment.onRefreshTab();
            mDashPageAdapter.mCurrentFragment.onRefreshTab();
        }

    }




    @Override
    public void onTaskResponse(TaskResult result) {
//        WLog.w("onTaskResponse : " + result.taskType + "  " + result.isSuccess);
        mTaskCount--;
        if (result.taskType == BaseConstant.TASK_FETCH_ACCOUNT) {
            mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
            WLog.w("mAccount : " + mAccount.address);

        } else if (result.taskType == BaseConstant.TASK_FETCH_ALL_VALIDATOR) {
            mAllValidators = (ArrayList<Validator>)result.resultData;
            WLog.w("mAllValidators : " + mAllValidators.size());

        } else if(result.taskType == BaseConstant.TASK_FETCH_BONDING_STATE) {
            mBondings = getBaseDao().onSelectBondingStates(mAccount.id);
            WLog.w("mBondings : " + mBondings.size());
            mTaskCount = mTaskCount + mBondings.size();
            for(BondingState bonding:mBondings) {
                new RewardFromValidatorTask(getBaseApplication(), this, mAccount, bonding.validatorAddress).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }

        } else if (result.taskType == BaseConstant.TASK_FETCH_UNBONDING_STATE) {
            mUnbondings = getBaseDao().onSelectUnbondingStates(mAccount.id);
            WLog.w("mUnbondings : " + mUnbondings.size());

        } else if (result.taskType == BaseConstant.TASK_FETCH_TOTAL_REWARDS) {
            mTotalRewards = (ArrayList<Coin>)result.resultData;
            WLog.w("mTotalRewards : " + mTotalRewards.size());

        } else if (result.taskType == BaseConstant.TASK_FETCH_REWARDS_VALIDATOR) {
            Reward reward = (Reward)result.resultData;
            onUpdateReward(reward);
//            WLog.w("mRewards : " + mRewards.size());
        }
//        WLog.w("mTaskCount : " + mTaskCount);
        if(mTaskCount == 0) {
            WLog.w("mTaskFinished");
            mMyValidators.clear();
            mElseValidators.clear();
            for(Validator all:mAllValidators) {
                boolean already = false;
                for(BondingState bond:mBondings) {
                    if(bond.validatorAddress.equals(all.operator_address)) {
                        already = true;
                        break;
                    }
                }
                if(already)
                    mMyValidators.add(all);
                else  mElseValidators.add(all);
            }
            WLog.w("mMyValidators : " + mMyValidators.size());
            WLog.w("mElseValidators : " + mElseValidators.size());
            onFetchCurrentPage();
        }
    }


    private class MainViewPageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public MainViewPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(MainSendFragment.newInstance(null));
            mFragments.add(MainRewardFragment.newInstance(null));
            mFragments.add(MainHistoryFragment.newInstance(null));
            mFragments.add(MainVoteFragment.newInstance(null));
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


    private class DashBoardPageAdapter extends FragmentPagerAdapter {
        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public DashBoardPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(DashBoardAtomFragment.newInstance(null));
            mFragments.add(DashBoardPhotonFragment.newInstance(null));
            mFragments.add(DashBoardTest2Fragment.newInstance(null));
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



    private void onInitFetchAccount() {
        if(mTaskCount > 0) return;
        mTaskCount = 5;
        ArrayList<Account> accounts = new ArrayList<Account>();
        accounts.add(mAccount);
        new AllValidatorInfoTask(getBaseApplication(), this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new AccountInfoTask(getBaseApplication(), this, accounts).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new BondingStateTask(getBaseApplication(), this, accounts).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new UnBondingStateTask(getBaseApplication(), this, accounts).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new TotalRewardTask(getBaseApplication(), this, accounts.get(0)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }


    private void onUpdateReward(Reward reward) {
        if(mRewards == null) mRewards = new ArrayList<>();
        if(mRewards.size() == 0) {
            mRewards.add(reward);
        } else {
            int match = -1;
            for(int i = 0; i < mRewards.size(); i++) {
                if(mRewards.get(i).validatorAddress.equals(reward.validatorAddress)) {
                    match = i; break;
                }
            }
            if(match > 0) {
                mRewards.remove(match);
            }
            mRewards.add(reward);
        }
    }


















    private void onTest() {
        WLog.w("onTest");
        ArrayList<Account> accouts = getBaseDao().onSelectAccounts();
//        if(accouts.size() > 0) {
//            WLog.w("accouts : " +accouts.size());
//            WLog.w("accouts : " +accouts.get(0).address);
//
//            balances = getBaseDao().onSelectBalance(""+accouts.get(0).id);
//            WLog.w("balances : " +balances.size());
//            WLog.w("balances : " +balances.get(0).accountId);
//
//            bondings = getBaseDao().onSelectBondingStates(""+accouts.get(0).id);
//            WLog.w("bondings : " +bondings.size());
//
//            unbondings = getBaseDao().onSelectUnbondingStates(""+accouts.get(0).id);
//            WLog.w("unbondings : " +unbondings.size());
//        }
//


//        new AccountInfoTask(getBaseApplication(), this, accouts).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//        new AllValidatorInfoTask(getBaseApplication(), this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//        new BondingStateTask(getBaseApplication(), this, accouts).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//        new UnBondingStateTask(getBaseApplication(), this, accouts).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//        new TotalRewardTask(getBaseApplication(), this, accouts.get(0)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

//        CountDownLatch

//        try {
//            CountDownLatch latch = new CountDownLatch(2);
//            new AccountInfoTask(getBaseApplication(), this, accouts, latch).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//            new BondingStateTask(getBaseApplication(), this, accouts, latch).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//            latch.await();
//            WLog.w("latch");
//
//        }catch (Exception e) {
//            WLog.w("Exception : " + e.getMessage());
//        }

//        this.runOnUiThread(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                try {
//                    CountDownLatch latch = new CountDownLatch(2);
//                    new AccountInfoTask(getBaseApplication(), null, accouts, latch).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//                    new BondingStateTask(getBaseApplication(), null, accouts, latch).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//                    latch.await();
//                    WLog.w("latch");
//
//                }catch (Exception e) {
//                    WLog.w("Exception : " + e.getMessage());
//                }
//            }
//        });


    }
}
