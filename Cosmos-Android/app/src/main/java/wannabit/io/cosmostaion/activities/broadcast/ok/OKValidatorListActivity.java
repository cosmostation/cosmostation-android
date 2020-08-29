package wannabit.io.cosmostaion.activities.broadcast.ok;

import android.content.Intent;
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
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.fragment.broadcast.ok.OKValidatorMyFragment;
import wannabit.io.cosmostaion.fragment.broadcast.ok.OKValidatorOtherFragment;
import wannabit.io.cosmostaion.fragment.broadcast.ok.OKValidatorTopFragment;
import wannabit.io.cosmostaion.utils.FetchCallBack;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OK_TEST;

public class OKValidatorListActivity extends BaseActivity implements FetchCallBack {

    private Toolbar                 mToolbar;
    private TextView                mToolbarTitle;
    private ViewPager               mValidatorPager;
    private TabLayout               mValidatorTapLayer;
    private OKValidatorPageAdapter  mPageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validator_list);
        mToolbar            = findViewById(R.id.tool_bar);
        mToolbarTitle       = findViewById(R.id.toolbar_title);
        mValidatorTapLayer  = findViewById(R.id.validator_tab);
        mValidatorPager     = findViewById(R.id.validator_view_pager);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbarTitle.setText(R.string.str_validator_vote);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);

        mPageAdapter = new OKValidatorPageAdapter(getSupportFragmentManager());
        mValidatorPager.setAdapter(mPageAdapter);
        mValidatorTapLayer.setupWithViewPager(mValidatorPager);
        mValidatorTapLayer.setTabRippleColor(null);

        View tab0 = LayoutInflater.from(this).inflate(R.layout.view_tab_myvalidator, null);
        TextView tabItemText0 = tab0.findViewById(R.id.tabItemText);
        tabItemText0.setText(R.string.str_my_validators);
        tabItemText0.setTextColor(WDp.getTabColor(this, mBaseChain));
        mValidatorTapLayer.getTabAt(0).setCustomView(tab0);

        View tab1 = LayoutInflater.from(this).inflate(R.layout.view_tab_myvalidator, null);
        TextView tabItemText1 = tab1.findViewById(R.id.tabItemText);
        tabItemText1.setTextColor(WDp.getTabColor(this, mBaseChain));
        tabItemText1.setText(R.string.str_top_100_validators);
        mValidatorTapLayer.getTabAt(1).setCustomView(tab1);

        View tab2 = LayoutInflater.from(this).inflate(R.layout.view_tab_myvalidator, null);
        TextView tabItemText2 = tab2.findViewById(R.id.tabItemText);
        tabItemText2.setTextColor(WDp.getTabColor(this, mBaseChain));
        tabItemText2.setText(R.string.str_other_validators);
        mValidatorTapLayer.getTabAt(2).setCustomView(tab2);

        mValidatorTapLayer.setTabIconTint(WDp.getChainTintColor(this, mBaseChain));
        mValidatorTapLayer.setSelectedTabIndicatorColor(WDp.getChainColor(this, mBaseChain));

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
        if(!isFinishing()) {
            onHideWaitDialog();
            mPageAdapter.mCurrentFragment.onRefreshTab();
        }

    }

    @Override
    public void fetchBusy() {
        if(!isFinishing()) {
            onHideWaitDialog();
            mPageAdapter.mCurrentFragment.onBusyFetch();
        }
    }

    public void onStartDirectVote() {
        if (mAccount == null) return;
        if (!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }
        ArrayList<Balance> balances = getBaseDao().onSelectBalance(mAccount.id);
        boolean hasbalance = false;
        if (mBaseChain.equals(BaseChain.OK_TEST)) {
            if (WDp.getAvailableCoin(balances, TOKEN_OK_TEST).compareTo(BigDecimal.ONE) > 0) {
                hasbalance  = true;
            }
        }
        if (!hasbalance) {
            Toast.makeText(getBaseContext(), R.string.error_not_enough_balance_to_vote, Toast.LENGTH_SHORT).show();
            return;
        }

        BigDecimal depositAmount = WDp.getOkDepositCoin(getBaseDao().mOkDeposit);
        if (depositAmount.compareTo(BigDecimal.ZERO) <= 0) {
            Toast.makeText(getBaseContext(), R.string.error_only_deposit_can_vote, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(getBaseContext(), OKVoteDirectActivity.class);
        startActivity(intent);
    }


    private class OKValidatorPageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
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
