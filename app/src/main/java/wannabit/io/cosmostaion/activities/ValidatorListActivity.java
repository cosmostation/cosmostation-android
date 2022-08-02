package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.os.Bundle;
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

import cosmos.distribution.v1beta1.Distribution;
import cosmos.staking.v1beta1.Staking;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.common.ClaimRewardActivity;
import wannabit.io.cosmostaion.activities.txs.common.DelegateActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.fragment.ValidatorAllFragment;
import wannabit.io.cosmostaion.fragment.ValidatorMyFragment;
import wannabit.io.cosmostaion.fragment.ValidatorOtherFragment;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.utils.FetchCallBack;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class ValidatorListActivity extends BaseActivity implements FetchCallBack {

    private Toolbar mToolbar;
    private ViewPager mValidatorPager;
    private TabLayout mValidatorTapLayer;
    private ValidatorPageAdapter mPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validator_list);
        mToolbar = findViewById(R.id.tool_bar);
        mValidatorTapLayer = findViewById(R.id.validator_tab);
        mValidatorPager = findViewById(R.id.validator_view_pager);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);

        mPageAdapter = new ValidatorPageAdapter(getSupportFragmentManager());
        mValidatorPager.setAdapter(mPageAdapter);
        mValidatorTapLayer.setupWithViewPager(mValidatorPager);
        mValidatorTapLayer.setTabRippleColor(null);

        createTab(mChainConfig, R.string.str_my_validators, 0);
        createTab(mChainConfig, R.string.str_top_100_validators, 1);
        createTab(mChainConfig, R.string.str_other_validators, 2);

        mValidatorPager.setOffscreenPageLimit(3);
        mValidatorPager.setCurrentItem(0, false);

        mValidatorPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
    }

    private void createTab(ChainConfig chainConfig, int stringResourceId, int index) {
        View tab = LayoutInflater.from(this).inflate(R.layout.view_tab_myvalidator, null);
        TextView tabItemText = tab.findViewById(R.id.tabItemText);
        tabItemText.setText(stringResourceId);
        tabItemText.setTextColor(ContextCompat.getColorStateList(this, chainConfig.chainTabColor()));
        mValidatorTapLayer.getTabAt(index).setCustomView(tab);

        mValidatorTapLayer.setTabIconTint(ContextCompat.getColorStateList(this, chainConfig.chainColor()));
        mValidatorTapLayer.setSelectedTabIndicatorColor(ContextCompat.getColor(this, chainConfig.chainColor()));
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

    public void onStartValidatorDetail(Validator validator) {
        Intent intent = new Intent(ValidatorListActivity.this, ValidatorActivity.class);
        intent.putExtra("validator", validator);
        startActivity(intent);
    }

    public void onStartValidatorDetailV1(String opAddress) {
        Intent intent = new Intent(ValidatorListActivity.this, ValidatorActivity.class);
        intent.putExtra("valOpAddress", opAddress);
        startActivity(intent);
    }

    public void onStartDelegate() {
        if (!mAccount.hasPrivateKey) {
            onInsertKeyDialog();
            return;
        }
        if (!WDp.isTxFeePayable(this, getBaseDao(), mChainConfig)) {
            Toast.makeText(this, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
            return;
        }

        String cosmostation = "";
        BigDecimal delegatableAmount = getBaseDao().getDelegatable(mBaseChain, mChainConfig.mainDenom());
        if (BigDecimal.ZERO.compareTo(delegatableAmount) >= 0) {
            Toast.makeText(getBaseContext(), R.string.error_not_enough_to_delegate, Toast.LENGTH_SHORT).show();
            return;
        }
        for (Staking.Validator validator : getBaseDao().mGRpcAllValidators) {
            if (validator.getDescription().getMoniker().equalsIgnoreCase("Cosmostation")) {
                cosmostation = validator.getOperatorAddress();
            }
        }
        if (!cosmostation.isEmpty()) {
            Intent toDelegate = new Intent(ValidatorListActivity.this, DelegateActivity.class);
            toDelegate.putExtra("valOpAddress", cosmostation);
            startActivity(toDelegate);
        }
    }

    public void onStartRewardAll() {
        if (!mAccount.hasPrivateKey) {
            onInsertKeyDialog();
            return;
        }
        if (!WDp.isTxFeePayable(this, getBaseDao(), mChainConfig)) {
            Toast.makeText(this, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<String> toClaimValaddr = new ArrayList<>();
        ArrayList<Distribution.DelegationDelegatorReward> toClaimRewards = new ArrayList<>();
        if (getBaseDao().mGrpcRewards == null) {
            Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
            return;
        }

        for (Distribution.DelegationDelegatorReward reward : getBaseDao().mGrpcRewards) {
            if (getBaseDao().getReward(mChainConfig.mainDenom(), reward.getValidatorAddress()).compareTo(BigDecimal.ZERO) > 0) {
                toClaimRewards.add(reward);
            }
        }
        if (toClaimRewards.size() == 0) {
            Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
            return;
        }
        WUtil.onSortRewardAmount(toClaimRewards, mChainConfig.mainDenom());
        if (toClaimRewards.size() >= 17) {
            ArrayList<Distribution.DelegationDelegatorReward> temp = new ArrayList(toClaimRewards.subList(0, 16));
            toClaimRewards = temp;
            Toast.makeText(getBaseContext(), R.string.str_multi_reward_max_16, Toast.LENGTH_SHORT).show();
        }

        for (Distribution.DelegationDelegatorReward reward : toClaimRewards) {
            toClaimValaddr.add(reward.getValidatorAddress());
        }

        Intent claimReward = new Intent(ValidatorListActivity.this, ClaimRewardActivity.class);
        claimReward.putStringArrayListExtra("valOpAddresses", toClaimValaddr);
        startActivity(claimReward);
    }

    public void onFetchAllData() {
        onFetchAccountInfo(this);
    }

    @Override
    public void fetchFinished() {
        if (!isFinishing()) {
            onHideWaitDialog();
            mPageAdapter.mCurrentFragment.onRefreshTab();
        }

    }

    @Override
    public void fetchBusy() {
        if (!isFinishing()) {
            onHideWaitDialog();
            mPageAdapter.mCurrentFragment.onBusyFetch();
        }

    }

    private class ValidatorPageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public ValidatorPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(ValidatorMyFragment.newInstance());
            mFragments.add(ValidatorAllFragment.newInstance());
            mFragments.add(ValidatorOtherFragment.newInstance());
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
