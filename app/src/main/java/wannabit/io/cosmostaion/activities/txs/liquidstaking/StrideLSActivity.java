package wannabit.io.cosmostaion.activities.txs.liquidstaking;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_ALL_HOST_ZONE;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_EPOCH_TRACKER;

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

import stride.stakeibc.EpochTrackerOuterClass;
import stride.stakeibc.HostZoneOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.fragment.txs.liquidstaking.StrideLSFragment;
import wannabit.io.cosmostaion.fragment.txs.liquidstaking.StrideLUSFragment;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.AllHostZoneGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.EpochTrackerGrpcTask;

public class StrideLSActivity extends BaseActivity {

    private Toolbar mToolbar;
    private TextView mToolbarTitle;
    private ViewPager mLabPager;
    private TabLayout mLabTapLayer;
    private StrideLSPageAdapter mPageAdapter;

    public ArrayList<HostZoneOuterClass.HostZone> mHostZones = new ArrayList<>();
    public EpochTrackerOuterClass.EpochTracker mDayEpoch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defi_list);
        mToolbar = findViewById(R.id.tool_bar);
        mToolbarTitle = findViewById(R.id.toolbar_title);
        mToolbar = findViewById(R.id.tool_bar);
        mLabTapLayer = findViewById(R.id.lab_tab);
        mLabPager = findViewById(R.id.lab_view_pager);

        mToolbarTitle.setText(getString(R.string.str_sif_dex_title));
        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mChainConfig = ChainFactory.getChain(BaseChain.getChain(mAccount.baseChain));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPageAdapter = new StrideLSPageAdapter(getSupportFragmentManager());
        mLabPager.setAdapter(mPageAdapter);
        mLabTapLayer.setupWithViewPager(mLabPager);
        mLabTapLayer.setTabRippleColor(null);

        createTab(mChainConfig, R.string.title_liquid_staking, 0);
        createTab(mChainConfig, R.string.title_liquid_unstaking, 1);

        mLabPager.setOffscreenPageLimit(1);
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
        onShowWaitDialog();
        onFetchPoolListInfo();
    }

    private void createTab(ChainConfig chainConfig, int stringResourceId, int index) {
        View tab = LayoutInflater.from(this).inflate(R.layout.view_tab_myvalidator, null);
        TextView tabItemText = tab.findViewById(R.id.tabItemText);
        tabItemText.setTextColor(ContextCompat.getColorStateList(this, chainConfig.chainTabColor()));
        tabItemText.setText(stringResourceId);
        mLabTapLayer.getTabAt(index).setCustomView(tab);

        mLabTapLayer.setTabIconTint(ContextCompat.getColorStateList(this, chainConfig.chainColor()));
        mLabTapLayer.setSelectedTabIndicatorColor(ContextCompat.getColor(this, chainConfig.chainColor()));
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

    public void onStartStake(HostZoneOuterClass.HostZone hostZone, BigDecimal maxAvailAmount) {
        if (!mAccount.hasPrivateKey) {
            onInsertKeyDialog();
            return;
        }
        if (maxAvailAmount.compareTo(BigDecimal.ZERO) <= 0) {
            View layout = getLayoutInflater().inflate(R.layout.item_toast_msg, findViewById(R.id.toast_layout));
            TextView textView = layout.findViewById(R.id.toast_msg);
            textView.setText(getString(R.string.error_not_enough_liquid_stake));

            Toast toast = new Toast(this);
            toast.setView(layout);
            toast.show();
            return;
        }

        Intent intent = new Intent(StrideLSActivity.this, StrideLiquidActivity.class);
        intent.putExtra("chainId", hostZone.getChainId());
        intent.putExtra("stakingDenom", hostZone.getIbcDenom());
        startActivity(intent);
    }

    public void onFetchPoolListInfo() {
        mTaskCount = 2;
        mHostZones.clear();
        mDayEpoch = null;
        new AllHostZoneGrpcTask(getBaseApplication(), this, mChainConfig).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new EpochTrackerGrpcTask(getBaseApplication(), this, mChainConfig).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (isFinishing()) return;
        mTaskCount--;

        if (result.taskType == TASK_GRPC_FETCH_ALL_HOST_ZONE) {
            if (result.isSuccess && result.resultData != null) {
                mHostZones = (ArrayList<HostZoneOuterClass.HostZone>) result.resultData;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_EPOCH_TRACKER) {
            if (result.isSuccess && result.resultData != null) {
                mDayEpoch = (EpochTrackerOuterClass.EpochTracker) result.resultData;
            }
        }

        if (mTaskCount == 0) {
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                onHideWaitDialog();
                mPageAdapter.mCurrentFragment.onRefreshTab();
            }, 300);
        }
    }

    private class StrideLSPageAdapter extends FragmentPagerAdapter {
        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public StrideLSPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(StrideLSFragment.newInstance());
            mFragments.add(StrideLUSFragment.newInstance());
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
