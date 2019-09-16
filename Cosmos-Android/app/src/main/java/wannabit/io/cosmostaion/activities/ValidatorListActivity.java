package wannabit.io.cosmostaion.activities;

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
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BondingState;
import wannabit.io.cosmostaion.dao.Reward;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.fragment.ValidatorAllFragment;
import wannabit.io.cosmostaion.fragment.ValidatorMyFragment;
import wannabit.io.cosmostaion.fragment.ValidatorOtherFragment;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.utils.FetchCallBack;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class ValidatorListActivity extends BaseActivity implements FetchCallBack {

    private Toolbar                     mToolbar;
    private ViewPager                   mValidatorPager;
    private TabLayout                   mValidatorTapLayer;
    private ValidatorPageAdapter        mPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validator_list);
        mToolbar            = findViewById(R.id.tool_bar);
        mValidatorTapLayer  = findViewById(R.id.validator_tab);
        mValidatorPager     = findViewById(R.id.validator_view_pager);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAllValidators = getIntent().getParcelableArrayListExtra("allValidators");
        mMyValidators = getIntent().getParcelableArrayListExtra("myValidators");
        mTopValidators = getIntent().getParcelableArrayListExtra("topValidators");
        mOtherValidators = getIntent().getParcelableArrayListExtra("otherValidators");
        mBondedToken = new BigDecimal(getIntent().getStringExtra("bondedToken"));
        mProvisions = new BigDecimal(getIntent().getStringExtra("provisions"));
        mRewards = getIntent().getParcelableArrayListExtra("rewards");
        mIrisPool = getIntent().getParcelableExtra("irispool");
        mIrisReward = getIntent().getParcelableExtra("irisreward");

        mPageAdapter = new ValidatorPageAdapter(getSupportFragmentManager());
        mValidatorPager.setAdapter(mPageAdapter);
        mValidatorTapLayer.setupWithViewPager(mValidatorPager);
        mValidatorTapLayer.setTabRippleColor(null);

        View tab0 = LayoutInflater.from(this).inflate(R.layout.view_tab_myvalidator, null);
        mValidatorTapLayer.getTabAt(0).setCustomView(tab0);

        View tab1 = LayoutInflater.from(this).inflate(R.layout.view_tab_myvalidator, null);
        mValidatorTapLayer.getTabAt(1).setCustomView(tab1);

        View tab2 = LayoutInflater.from(this).inflate(R.layout.view_tab_myvalidator, null);
        mValidatorTapLayer.getTabAt(2).setCustomView(tab2);
        mValidatorPager.setOffscreenPageLimit(3);
        mValidatorPager.setCurrentItem(0, false);

        mValidatorPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
    protected void onResume() {
        super.onResume();

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);

        TabLayout.Tab tab0 = mValidatorTapLayer.getTabAt(0);
        View view0 = tab0.getCustomView();
        TextView tabItemText0 = view0.findViewById(R.id.tabItemText);
        tabItemText0.setTextColor(WDp.getTabColor(this, mAccount.baseChain));
        tabItemText0.setText(getString(R.string.str_my_validators) + " (" + mMyValidators.size() + ")");

        TabLayout.Tab tab1 = mValidatorTapLayer.getTabAt(1);
        View view1 = tab1.getCustomView();
        TextView tabItemText1 = view1.findViewById(R.id.tabItemText);
        tabItemText1.setTextColor(WDp.getTabColor(this, mAccount.baseChain));
        tabItemText1.setText(getString(R.string.str_top_100_validators)+ " (" + mTopValidators.size() + ")");

        TabLayout.Tab tab2 = mValidatorTapLayer.getTabAt(2);
        View view2 = tab2.getCustomView();
        TextView tabItemText2 = view2.findViewById(R.id.tabItemText);
        tabItemText2.setTextColor(WDp.getTabColor(this, mAccount.baseChain));
        tabItemText2.setText(getString(R.string.str_other_validators)+ " (" + mOtherValidators.size() + ")");

        mValidatorTapLayer.setTabIconTint(WDp.getChainTintColor(this, mAccount.baseChain));
        mValidatorTapLayer.setSelectedTabIndicatorColor(WDp.getChainColor(this, mAccount.baseChain));

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
        intent.putExtra("bondedToken", mBondedToken.toPlainString());
        intent.putExtra("provisions", mProvisions.toPlainString());
        intent.putExtra("irispool", mIrisPool);
        startActivity(intent);
    }

    public void onStartRewardAll() {
        if(!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }

        ArrayList<Validator> myValidators = new ArrayList<>();
        ArrayList<Validator> toClaimValidators = new ArrayList<>();

        if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            if (mRewards == null) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
                return;
            }

            for(Validator validator:mAllValidators) {
                for(BondingState bond:mBondings) {
                    if(bond.validatorAddress.equals(validator.operator_address) &&
                            WDp.getValidatorReward(mRewards, validator.operator_address).compareTo(new BigDecimal("1")) >= 0) {
                        myValidators.add(validator);
                        break;
                    }
                }
            }
            if(myValidators.size() == 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
                return;
            }

            WUtil.onSortByOnlyReward(myValidators, mRewards);
            if(myValidators.size() < 17) {
                toClaimValidators = myValidators;
            } else {
                toClaimValidators = new ArrayList<>(myValidators.subList(0,16));
            }

            ArrayList<Balance> balances = getBaseDao().onSelectBalance(mAccount.id);
            boolean hasbalance = false;
            for (Balance balance:balances) {
                if(balance.symbol.equals(BaseConstant.COSMOS_ATOM) && ((balance.balance.compareTo(BigDecimal.ONE)) >= 0)) {
                    hasbalance  = true;
                }
            }
            if(!hasbalance){
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward_all, Toast.LENGTH_SHORT).show();
                return;
            }

            BigDecimal rewardSum = BigDecimal.ZERO;
            for (Reward reward:mRewards) {
                rewardSum = rewardSum.add(new BigDecimal(reward.amount.get(0).amount).setScale(0, BigDecimal.ROUND_DOWN));
            }
            if (rewardSum.compareTo(new BigDecimal("1")) <= 0) {
                Toast.makeText(getBaseContext(), R.string.error_small_reward, Toast.LENGTH_SHORT).show();
                return;
            }

            if(myValidators.size() > 16) {
                Toast.makeText(getBaseContext(), R.string.str_multi_reward_max_16, Toast.LENGTH_SHORT).show();
            }


        } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            BigDecimal estimateReward = BigDecimal.ZERO;

            if (mIrisReward == null) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
                return;
            }

            for(Validator validator:mAllValidators) {
                if (mIrisReward.getPerValReward(validator.operator_address).compareTo(BigDecimal.ONE) >= 0) {
                    toClaimValidators.add(validator);
                }
            }

            if(toClaimValidators.size() == 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
                return;
            }

            WUtil.onSortIrisOnlyByReward(myValidators, mIrisReward);

            for (Validator validator:toClaimValidators) {
                estimateReward = estimateReward.add(mIrisReward.getPerValReward(validator.operator_address));
            }

            BigDecimal estimateGasAmount = (new BigDecimal(BaseConstant.FEE_IRIS_GAS_AMOUNT_REWARD_MUX).multiply(new BigDecimal(""+toClaimValidators.size()))).add(new BigDecimal(BaseConstant.FEE_IRIS_GAS_AMOUNT_REWARD_BASE));
            BigDecimal estimateFee = estimateGasAmount.multiply(new BigDecimal(BaseConstant.FEE_IRIS_GAS_RATE_AVERAGE)).movePointRight(18).setScale(0);


            WLog.w("estimateReward " + estimateReward);
            WLog.w("estimateGasAmount " + estimateGasAmount);
            WLog.w("estimateFee " + estimateFee);

            ArrayList<Balance> balances = getBaseDao().onSelectBalance(mAccount.id);
            boolean hasbalance = false;
            for (Balance balance:balances) {
                if(balance.symbol.equals(BaseConstant.COSMOS_IRIS_ATTO) && ((balance.balance.compareTo(estimateFee)) > 0)) {
                    hasbalance  = true;
                }
            }
            if(!hasbalance){
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward_all, Toast.LENGTH_SHORT).show();
                return;
            }

            if (estimateReward.compareTo(estimateFee) <= 0) {
                Toast.makeText(getBaseContext(), R.string.error_small_reward, Toast.LENGTH_SHORT).show();
                return;
            }

        }

        Intent claimReward = new Intent(ValidatorListActivity.this, ClaimRewardActivity.class);
        claimReward.putExtra("opAddresses", toClaimValidators);
        startActivity(claimReward);
    }


    public void onFetchAllData() {
        onFetchAccountInfo(this);
    }

    @Override
    public void fetchFinished() {
        WLog.w("Validator List fetchFinished");
        if(!isFinishing()) {
            onHideWaitDialog();
            mPageAdapter.mCurrentFragment.onRefreshTab();
        }

    }

    @Override
    public void fetchBusy() {
        WLog.w("Validator List fetchBusy");
        if(!isFinishing()) {
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
