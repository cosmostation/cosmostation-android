package wannabit.io.cosmostaion.activities.chains.ok;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OK;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.IBusyFetchListener;
import wannabit.io.cosmostaion.base.IRefreshTabListener;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.fragment.chains.ok.OKValidatorMyFragment;
import wannabit.io.cosmostaion.fragment.chains.ok.OKValidatorOtherFragment;
import wannabit.io.cosmostaion.fragment.chains.ok.OKValidatorTopFragment;
import wannabit.io.cosmostaion.utils.FetchCallBack;
import wannabit.io.cosmostaion.utils.WDp;

public class OKValidatorListActivity extends BaseActivity implements FetchCallBack {

    private Toolbar mToolbar;
    private TextView mToolbarTitle;
    private ViewPager mValidatorPager;
    private TabLayout mValidatorTapLayer;
    private OKValidatorPageAdapter mPageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validator_list);
        mToolbar = findViewById(R.id.toolbar);
        mToolbarTitle = findViewById(R.id.toolbar_title);
        mValidatorTapLayer = findViewById(R.id.validator_tab);
        mValidatorPager = findViewById(R.id.validator_view_pager);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbarTitle.setText(R.string.str_validator_vote);

        account = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        baseChain = BaseChain.getChain(account.baseChain);

        mPageAdapter = new OKValidatorPageAdapter(getSupportFragmentManager());
        mValidatorPager.setAdapter(mPageAdapter);
        mValidatorTapLayer.setupWithViewPager(mValidatorPager);
        mValidatorTapLayer.setTabRippleColor(null);

        View tab0 = LayoutInflater.from(this).inflate(R.layout.view_tab_myvalidator, null);
        TextView tabItemText0 = tab0.findViewById(R.id.tabItemText);
        tabItemText0.setText(R.string.str_my_validators);
        tabItemText0.setTextColor(WDp.getTabColor(this, baseChain));
        mValidatorTapLayer.getTabAt(0).setCustomView(tab0);

        View tab1 = LayoutInflater.from(this).inflate(R.layout.view_tab_myvalidator, null);
        TextView tabItemText1 = tab1.findViewById(R.id.tabItemText);
        tabItemText1.setTextColor(WDp.getTabColor(this, baseChain));
        tabItemText1.setText(R.string.str_top_100_validators);
        mValidatorTapLayer.getTabAt(1).setCustomView(tab1);

        View tab2 = LayoutInflater.from(this).inflate(R.layout.view_tab_myvalidator, null);
        TextView tabItemText2 = tab2.findViewById(R.id.tabItemText);
        tabItemText2.setTextColor(WDp.getTabColor(this, baseChain));
        tabItemText2.setText(R.string.str_other_validators);
        mValidatorTapLayer.getTabAt(2).setCustomView(tab2);

        mValidatorTapLayer.setTabIconTint(WDp.getChainTintColor(this, baseChain));
        mValidatorTapLayer.setSelectedTabIndicatorColor(WDp.getChainColor(this, baseChain));

        mValidatorPager.setOffscreenPageLimit(3);
        mValidatorPager.setCurrentItem(0, false);

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

    public void onFetchAllData() {
        onFetchAccountInfo(this);
    }

    @Override
    public void fetchFinished() {
        if (!isFinishing()) {
            hideWaitDialog();
            ((IRefreshTabListener) mPageAdapter.mCurrentFragment).onRefreshTab();
        }

    }

    @Override
    public void fetchBusy() {
        if (!isFinishing()) {
            hideWaitDialog();
            Fragment fragment = mPageAdapter.mCurrentFragment;
            if (fragment instanceof IBusyFetchListener) {
                ((IBusyFetchListener) mPageAdapter.mCurrentFragment).onBusyFetch();
            }
        }
    }

    public void onStartDirectVote() {
        if (account == null) return;
        if (!account.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            showDialog(add);
            return;
        }
        BigDecimal availableAmount = getBaseDao().availableAmount(TOKEN_OK);
        if (availableAmount.compareTo(new BigDecimal("0.1")) <= 0) {
            Toast.makeText(getBaseContext(), R.string.error_not_enough_balance_to_vote, Toast.LENGTH_SHORT).show();
            return;
        }

        BigDecimal depositAmount = getBaseDao().okDepositAmount();
        if (depositAmount.compareTo(BigDecimal.ZERO) <= 0) {
            Toast.makeText(getBaseContext(), R.string.error_only_deposit_can_vote, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(getBaseContext(), OKVoteDirectActivity.class);
        startActivity(intent);
    }


    private class OKValidatorPageAdapter extends FragmentPagerAdapter {

        private final ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public OKValidatorPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(OKValidatorMyFragment.newInstance(null));
            mFragments.add(OKValidatorTopFragment.newInstance(null));
            mFragments.add(OKValidatorOtherFragment.newInstance(null));
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
