package wannabit.io.cosmostaion.activities.txs.starname;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_STARNAME_ACCOUNT;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_STARNAME_DOMAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_STARNAME_RESOLVE;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import starnamed.x.starname.v1beta1.Types;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.fragment.txs.starname.MyAccountFragment;
import wannabit.io.cosmostaion.fragment.txs.starname.MyDomainFragment;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.StarNameGrpcAccountTask;
import wannabit.io.cosmostaion.task.gRpcTask.StarNameGrpcDomainTask;
import wannabit.io.cosmostaion.task.gRpcTask.StarNameGrpcResolveTask;

public class StarNameListActivity extends BaseActivity implements TaskListener {

    private Toolbar                     mToolbar;
    private ViewPager                   mNameServicePager;
    private TabLayout                   mNameServiceTapLayer;
    private StarNamePageAdapter         mPageAdapter;

    public ArrayList<Types.Domain>      mDomains_gRPC = new ArrayList<>();
    public ArrayList<Types.Account>     mDomainResolves_gRPC = new ArrayList<>();
    public ArrayList<Types.Account>     mAccounts_gRPC = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starname_list);
        mToolbar = findViewById(R.id.tool_bar);
        mNameServiceTapLayer = findViewById(R.id.name_service_tab);
        mNameServicePager = findViewById(R.id.name_service_view_pager);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);

        mPageAdapter = new StarNamePageAdapter(getSupportFragmentManager());
        mNameServicePager.setAdapter(mPageAdapter);
        mNameServiceTapLayer.setupWithViewPager(mNameServicePager);
        mNameServiceTapLayer.setTabRippleColor(null);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        createTab(mChainConfig, R.string.str_my_domain, 0);
        createTab(mChainConfig, R.string.str_my_account, 1);

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

    private void createTab(ChainConfig chainConfig, int stringResourceId, int index) {
        View tab = LayoutInflater.from(this).inflate(R.layout.view_tab_myvalidator, null);
        TextView tabItemText = tab.findViewById(R.id.tabItemText);
        tabItemText.setText(stringResourceId);
        tabItemText.setTextColor(ContextCompat.getColorStateList(this, chainConfig.chainTabColor()));
        mNameServiceTapLayer.getTabAt(index).setCustomView(tab);
        mNameServiceTapLayer.setTabIconTint(ContextCompat.getColorStateList(this, chainConfig.chainColor()));
        mNameServiceTapLayer.setSelectedTabIndicatorColor(ContextCompat.getColor(this, chainConfig.chainColor()));
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
        mDomains_gRPC.clear();
        mDomainResolves_gRPC.clear();
        mAccounts_gRPC.clear();
        new StarNameGrpcAccountTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new StarNameGrpcDomainTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    public Types.Account getDomainResolve(String domain) {
        for (Types.Account resolve: mDomainResolves_gRPC) {
            if (resolve.getDomain().equals(domain)) {
                return resolve;
            }
        }
        return null;
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        mTaskCount--;
        if (isFinishing()) return;
        if (result.taskType == TASK_GRPC_FETCH_STARNAME_ACCOUNT) {
            if (result.isSuccess && result.resultData != null) {
                mAccounts_gRPC = (ArrayList<Types.Account>)result.resultData;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_STARNAME_DOMAIN) {
            if (result.isSuccess && result.resultData != null) {
                mDomains_gRPC = (ArrayList<Types.Domain>)result.resultData;

            }
            mTaskCount = mTaskCount + mDomains_gRPC.size();
            for (Types.Domain domain: mDomains_gRPC) {
                new StarNameGrpcResolveTask(getBaseApplication(),this, mBaseChain, "", domain.getName()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }

        } else if (result.taskType == TASK_GRPC_FETCH_STARNAME_RESOLVE) {
            if (result.isSuccess && result.resultData != null) {
                mDomainResolves_gRPC.add((Types.Account)result.resultData);
            }
        }

        if (mTaskCount == 0) {
            onHideWaitDialog();
            mPageAdapter.getCurrentFragment().onRefreshTab();
        }
    }

    private class StarNamePageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public StarNamePageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(MyDomainFragment.newInstance());
            mFragments.add(MyAccountFragment.newInstance());
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
