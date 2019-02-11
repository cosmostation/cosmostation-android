package wannabit.io.cosmostaion.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BondingState;
import wannabit.io.cosmostaion.dao.Reward;
import wannabit.io.cosmostaion.dao.UnBondingState;
import wannabit.io.cosmostaion.fragment.MainHistoryFragment;
import wannabit.io.cosmostaion.fragment.MainRewardFragment;
import wannabit.io.cosmostaion.fragment.MainSendFragment;
import wannabit.io.cosmostaion.fragment.MainVoteFragment;
import wannabit.io.cosmostaion.task.AccountInfoTask;
import wannabit.io.cosmostaion.task.AllValidatorInfoTask;
import wannabit.io.cosmostaion.task.BondingStateTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.TotalRewardTask;
import wannabit.io.cosmostaion.task.UnBondingStateTask;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.widget.StopViewPager;

public class MainActivity extends BaseActivity implements TaskListener{

//    private ArrayList<Account>          accouts = new ArrayList<>();


    private Account                     mAccount;
    private ArrayList<Balance>          mBalances = new ArrayList<>();
    private ArrayList<BondingState>     mBondings = new ArrayList<>();
    private ArrayList<UnBondingState>   mUnbondings = new ArrayList<>();
    private ArrayList<Reward>           mRewards = new ArrayList<>();


    private StopViewPager           mViewPager;
    private TabLayout               mTabLayer;
    private MainViewPageAdapter     mPageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager                  = findViewById(R.id.view_pager);
        mTabLayer                   = findViewById(R.id.bottom_tab);


        mPageAdapter = new MainViewPageAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mPageAdapter);
        mTabLayer.setupWithViewPager(mViewPager);

        mViewPager.setCurrentItem(0, false);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        getBaseDao().setLastUser(1);
//        WLog.w("getLastUser " + getBaseDao().getLastUser());
//
//        if(mAccount == null) {
//            WLog.w("mAccount null");
//        }
////        WLog.w("mAccount : " + mAccount.address + " " + mAccount.getBalances().get(0).balance);
//
//        WLog.w("size : " + getBaseDao().onSelectAccounts().size());
//        WLog.w("size : " + getBaseDao().onSelectAccounts().get(0).id);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        if(mAccount == null) {
            //Show error dialog!
            return;
        }

        onInitFetchAccount();
        mBalances = getBaseDao().onSelectBalance(mAccount.id);
        mBondings = getBaseDao().onSelectBondingStates(mAccount.id);
        mUnbondings = getBaseDao().onSelectUnbondingStates(mAccount.id);

    }




    @Override
    public void onTaskResponse(TaskResult result) {
        WLog.w("onTaskResponse : " + result.taskType + "  " + result.isSuccess);
        if(result.taskType == BaseConstant.TASK_FETCH_BONDING_STATE) {

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




    private void onInitFetchAccount() {
        ArrayList<Account> accounts = new ArrayList<Account>();
        accounts.add(mAccount);
        new AccountInfoTask(getBaseApplication(), this, accounts).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new BondingStateTask(getBaseApplication(), this, accounts).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new UnBondingStateTask(getBaseApplication(), this, accounts).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new TotalRewardTask(getBaseApplication(), this, accounts.get(0)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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
