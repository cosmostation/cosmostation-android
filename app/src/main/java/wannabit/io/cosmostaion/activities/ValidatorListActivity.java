package wannabit.io.cosmostaion.activities;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_DELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_REWARD;

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

import cosmos.distribution.v1beta1.Distribution;
import cosmos.staking.v1beta1.Staking;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.IBusyFetchListener;
import wannabit.io.cosmostaion.base.IRefreshTabListener;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.fragment.ValidatorAllFragment;
import wannabit.io.cosmostaion.fragment.ValidatorMyFragment;
import wannabit.io.cosmostaion.fragment.ValidatorOtherFragment;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.utils.FetchCallBack;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class ValidatorListActivity extends BaseActivity implements FetchCallBack {

    private Toolbar mToolbar;
    private TextView mToolbarTitle;
    private ViewPager mValidatorPager;
    private TabLayout mValidatorTapLayer;
    private ValidatorPageAdapter mPageAdapter;

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

        account = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        baseChain = BaseChain.getChain(account.baseChain);

        mPageAdapter = new ValidatorPageAdapter(getSupportFragmentManager());
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

        mValidatorPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }

            @Override
            public void onPageSelected(int i) {
                Fragment fragment = mPageAdapter.mFragments.get(i);
                if (fragment instanceof IRefreshTabListener) {
                    ((IRefreshTabListener) fragment).onRefreshTab();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if(mAccount == null) finish();
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
        if (!account.hasPrivateKey) {
            Dialog_WatchMode dialog = Dialog_WatchMode.newInstance();
            showDialog(dialog);
            return;
        }
        String cosmostation = "";
        if (baseChain.isGRPC()) {
            BigDecimal delegatableAmount = getBaseDao().getDelegatable(baseChain.getMainDenom());
            BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), baseChain, CONST_PW_TX_SIMPLE_DELEGATE, 0);
            if (delegatableAmount.compareTo(feeAmount) < 0) {
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
        } else {
            Validator toValidator = null;
            BigDecimal delegatableAmount = getBaseDao().delegatableAmount(baseChain.getMainDenom());
            BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), baseChain, CONST_PW_TX_SIMPLE_DELEGATE, 0);
            if (delegatableAmount.compareTo(feeAmount) < 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_to_delegate, Toast.LENGTH_SHORT).show();
                return;
            }
            for (Validator validator : getBaseDao().mAllValidators) {
                if (validator.description.moniker.equalsIgnoreCase("Cosmostation")) {
                    toValidator = validator;
                }
            }
            if (toValidator != null) {
                Intent toDelegate = new Intent(ValidatorListActivity.this, DelegateActivity.class);
                toDelegate.putExtra("validator", toValidator);
                startActivity(toDelegate);
            }
        }
    }

    public void onStartRewardAll() {
        if (!account.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            showDialog(add);
            return;
        }

        if (baseChain.isGRPC()) {
            ArrayList<String> toClaimValaddr = new ArrayList<>();
            ArrayList<Distribution.DelegationDelegatorReward> toClaimRewards = new ArrayList<>();
            if (getBaseDao().mGrpcRewards == null) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
                return;
            }

            BigDecimal singlefeeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), baseChain, CONST_PW_TX_SIMPLE_REWARD, 1);
            for (Distribution.DelegationDelegatorReward reward : getBaseDao().mGrpcRewards) {
                if (getBaseDao().getReward(baseChain.getMainDenom(), reward.getValidatorAddress()).compareTo(singlefeeAmount) > 0) {
                    toClaimRewards.add(reward);
                }
            }
            if (toClaimRewards.size() == 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
                return;
            }
            WUtil.onSortRewardAmount(toClaimRewards, baseChain.getMainDenom());
            if (toClaimRewards.size() >= 17) {
                ArrayList<Distribution.DelegationDelegatorReward> temp = new ArrayList(toClaimRewards.subList(0, 16));
                toClaimRewards = temp;
                Toast.makeText(getBaseContext(), R.string.str_multi_reward_max_16, Toast.LENGTH_SHORT).show();
            }

            BigDecimal available = getBaseDao().getAvailable(baseChain.getMainDenom());
            BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), baseChain, CONST_PW_TX_SIMPLE_REWARD, toClaimRewards.size());
            if (available.compareTo(feeAmount) < 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }
            for (Distribution.DelegationDelegatorReward reward : toClaimRewards) {
                toClaimValaddr.add(reward.getValidatorAddress());
            }

            Intent claimReward = new Intent(ValidatorListActivity.this, ClaimRewardActivity.class);
            claimReward.putStringArrayListExtra("valOpAddresses", toClaimValaddr);
            startActivity(claimReward);

        } else {
            ArrayList<Validator> toClaimValidators = new ArrayList<>();
            if (getBaseDao().rewardAmount(baseChain.getMainDenom()).compareTo(BigDecimal.ZERO) <= 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
                return;
            }

            BigDecimal singlefeeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), baseChain, CONST_PW_TX_SIMPLE_REWARD, 1);
            for (Validator validator : getBaseDao().mAllValidators) {
                if (getBaseDao().rewardAmountByValidator(baseChain.getMainDenom(), validator.operator_address).compareTo(singlefeeAmount) > 0) {
                    toClaimValidators.add(validator);
                }
            }

            if (toClaimValidators.size() == 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
                return;
            }

            WUtil.onSortByOnlyReward(toClaimValidators, baseChain.getMainDenom(), getBaseDao());
            if (toClaimValidators.size() >= 17) {
                toClaimValidators = new ArrayList<>(getBaseDao().mMyValidators.subList(0, 16));
                Toast.makeText(getBaseContext(), R.string.str_multi_reward_max_16, Toast.LENGTH_SHORT).show();
            }

            BigDecimal available = account.getTokenBalance(baseChain.getMainDenom());
            BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), baseChain, CONST_PW_TX_SIMPLE_REWARD, toClaimValidators.size());

            if (available.compareTo(feeAmount) < 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }

            Intent claimReward = new Intent(ValidatorListActivity.this, ClaimRewardActivity.class);
            claimReward.putExtra("opAddresses", toClaimValidators);
            startActivity(claimReward);

        }

    }

    public void onFetchAllData() {
        onFetchAccountInfo(this);
    }

    @Override
    public void fetchFinished() {
        if (!isFinishing()) {
            onHideWaitDialog();
            ((IRefreshTabListener) mPageAdapter.mCurrentFragment).onRefreshTab();
        }

    }

    @Override
    public void fetchBusy() {
        if (!isFinishing()) {
            onHideWaitDialog();
            Fragment fragment = mPageAdapter.mCurrentFragment;
            if (fragment instanceof IBusyFetchListener) {
                ((IBusyFetchListener) mPageAdapter.mCurrentFragment).onBusyFetch();
            }
        }

    }

    private class ValidatorPageAdapter extends FragmentPagerAdapter {

        private final ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public ValidatorPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(ValidatorMyFragment.newInstance(null));
            mFragments.add(ValidatorAllFragment.newInstance(null));
            mFragments.add(ValidatorOtherFragment.newInstance(null));
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
