package wannabit.io.cosmostaion.activities.chains.kava;

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
import wannabit.io.cosmostaion.activities.ValidatorListActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.fragment.ValidatorAllFragment;
import wannabit.io.cosmostaion.fragment.ValidatorMyFragment;
import wannabit.io.cosmostaion.fragment.ValidatorOtherFragment;
import wannabit.io.cosmostaion.fragment.chains.kava.CdpMarketFragment;
import wannabit.io.cosmostaion.fragment.chains.kava.HarvestMarketFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class DAppsListActivity extends BaseActivity {

    private Toolbar mToolbar;
    private ViewPager mDappPager;
    private TabLayout mDappTapLayer;
    private KavaDAppPageAdapter mPageAdapter;

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

        mPageAdapter = new KavaDAppPageAdapter(getSupportFragmentManager());
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




    private class KavaDAppPageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public KavaDAppPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(CdpMarketFragment.newInstance(null));
            mFragments.add(HarvestMarketFragment.newInstance(null));
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
