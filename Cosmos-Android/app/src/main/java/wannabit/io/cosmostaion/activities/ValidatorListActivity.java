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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import cosmos.distribution.v1beta1.Distribution;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BondingState;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.fragment.ValidatorAllFragment;
import wannabit.io.cosmostaion.fragment.ValidatorMyFragment;
import wannabit.io.cosmostaion.fragment.ValidatorOtherFragment;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.utils.FetchCallBack;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.FEE_AKASH_GAS_RATE_AVERAGE;
import static wannabit.io.cosmostaion.base.BaseConstant.FEE_CERTIK_GAS_RATE_AVERAGE;
import static wannabit.io.cosmostaion.base.BaseConstant.FEE_IOV_GAS_RATE_AVERAGE;
import static wannabit.io.cosmostaion.base.BaseConstant.SECRET_GAS_FEE_RATE_AVERAGE;
import static wannabit.io.cosmostaion.base.BaseConstant.STARGATE_GAS_RATE_AVERAGE;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_AKASH;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ATOM;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BAND;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_CERTIK;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SECRET;

public class ValidatorListActivity extends BaseActivity implements FetchCallBack {

    private Toolbar                 mToolbar;
    private TextView                mToolbarTitle;
    private ViewPager               mValidatorPager;
    private TabLayout               mValidatorTapLayer;
    private ValidatorPageAdapter    mPageAdapter;

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

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);

        mPageAdapter = new ValidatorPageAdapter(getSupportFragmentManager());
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

    public void onStartRewardAll() {
        if (!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }

        ArrayList<Validator> toClaimValidators = new ArrayList<>();
        ArrayList<String> toClaimValaddr= new ArrayList<>();        // for Grpc

        if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            BigDecimal estimateReward = BigDecimal.ZERO;

            if (getBaseDao().mIrisReward == null) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
                return;
            }

            for (Validator validator:getBaseDao().mAllValidators) {
                if (getBaseDao().mIrisReward.getPerValReward(validator.operator_address).compareTo(BigDecimal.ONE) >= 0) {
                    toClaimValidators.add(validator);
                }
            }

            if (toClaimValidators.size() == 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
                return;
            }

//            WUtil.onSortIrisOnlyByReward(mMyValidators, mIrisReward);

            for (Validator validator:toClaimValidators) {
                estimateReward = estimateReward.add(getBaseDao().mIrisReward.getPerValReward(validator.operator_address));
            }

            BigDecimal estimateGasAmount = (new BigDecimal(BaseConstant.FEE_IRIS_GAS_AMOUNT_REWARD_MUX).multiply(new BigDecimal(""+toClaimValidators.size()))).add(new BigDecimal(BaseConstant.FEE_IRIS_GAS_AMOUNT_REWARD_BASE));
            BigDecimal estimateFee = estimateGasAmount.multiply(new BigDecimal(BaseConstant.FEE_IRIS_GAS_RATE_AVERAGE)).movePointRight(18).setScale(0);

//            WLog.w("estimateReward " + estimateReward);
//            WLog.w("estimateGasAmount " + estimateGasAmount);
//            WLog.w("estimateFee " + estimateFee);

            ArrayList<Balance> balances = getBaseDao().onSelectBalance(mAccount.id);
            boolean hasbalance = false;
            for (Balance balance:balances) {
                if(balance.symbol.equals(BaseConstant.TOKEN_IRIS_ATTO) && ((balance.balance.compareTo(estimateFee)) > 0)) {
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

        } else if (mBaseChain.equals(BaseChain.KAVA_MAIN) || mBaseChain.equals(BaseChain.KAVA_TEST)) {
            if (getBaseDao().mRewards == null) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
                return;
            }

            BigDecimal rewardSum = BigDecimal.ZERO;
            for (BondingState bond:getBaseDao().mBondings) {
                if (WDp.getValidatorReward(getBaseDao().mRewards, bond.validatorAddress, TOKEN_KAVA).compareTo(BigDecimal.ONE) >= 0) {
                    if (WUtil.selectValidatorByAddr(getBaseDao().mMyValidators, bond.validatorAddress) != null) {
                        toClaimValidators.add(WUtil.selectValidatorByAddr(getBaseDao().mMyValidators, bond.validatorAddress));
                        rewardSum = rewardSum.add(WDp.getValidatorReward(getBaseDao().mRewards, bond.validatorAddress, TOKEN_KAVA));
                    }
                }
            }

            if (toClaimValidators.size() == 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
                return;
            }

            WUtil.onSortByOnlyReward(toClaimValidators, getBaseDao().mRewards, TOKEN_KAVA);
            if (toClaimValidators.size() >= 17) {
                toClaimValidators = new ArrayList<>(getBaseDao().mMyValidators.subList(0,16));
                Toast.makeText(getBaseContext(), R.string.str_multi_reward_max_16, Toast.LENGTH_SHORT).show();
            }

            if (rewardSum.compareTo(BigDecimal.ONE) <= 0) {
                Toast.makeText(getBaseContext(), R.string.error_small_reward, Toast.LENGTH_SHORT).show();
                return;
            }

        } else if (mBaseChain.equals(BaseChain.BAND_MAIN)) {
            if (getBaseDao().mRewards == null) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
                return;
            }

            BigDecimal rewardSum = BigDecimal.ZERO;
            for (BondingState bond:getBaseDao().mBondings) {
                if (WDp.getValidatorReward(getBaseDao().mRewards, bond.validatorAddress, TOKEN_BAND).compareTo(BigDecimal.ONE) >= 0) {
                    if (WUtil.selectValidatorByAddr(getBaseDao().mMyValidators, bond.validatorAddress) != null) {
                        toClaimValidators.add(WUtil.selectValidatorByAddr(getBaseDao().mMyValidators, bond.validatorAddress));
                        rewardSum = rewardSum.add(WDp.getValidatorReward(getBaseDao().mRewards, bond.validatorAddress, TOKEN_BAND));
                    }
                }
            }
            if (toClaimValidators.size() == 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
                return;
            }
            WUtil.onSortByOnlyReward(toClaimValidators, getBaseDao().mRewards, TOKEN_BAND);
            if (toClaimValidators.size() >= 17) {
                toClaimValidators = new ArrayList<>(getBaseDao().mMyValidators.subList(0,16));
                Toast.makeText(getBaseContext(), R.string.str_multi_reward_max_16, Toast.LENGTH_SHORT).show();
            }

            if (rewardSum.compareTo(BigDecimal.ONE) <= 0) {
                Toast.makeText(getBaseContext(), R.string.error_small_reward, Toast.LENGTH_SHORT).show();
                return;
            }

        } else if (mBaseChain.equals(BaseChain.IOV_MAIN)) {
            //only collect over 0.15 iov
            if (getBaseDao().mRewards == null) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
                return;
            }
            BigDecimal rewardSum = BigDecimal.ZERO;
            for (BondingState bond:getBaseDao().mBondings) {
                if (WDp.getValidatorReward(getBaseDao().mRewards, bond.validatorAddress, TOKEN_IOV).compareTo(new BigDecimal("150000")) >= 0) {
                    if (WUtil.selectValidatorByAddr(getBaseDao().mMyValidators, bond.validatorAddress) != null) {
                        toClaimValidators.add(WUtil.selectValidatorByAddr(getBaseDao().mMyValidators, bond.validatorAddress));
                        rewardSum = rewardSum.add(WDp.getValidatorReward(getBaseDao().mRewards, bond.validatorAddress, TOKEN_IOV));
                    }
                }
            }
            if (toClaimValidators.size() == 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
                return;
            }
            WUtil.onSortByOnlyReward(toClaimValidators, getBaseDao().mRewards, TOKEN_IOV);
            if (toClaimValidators.size() >= 17) {
                toClaimValidators = new ArrayList<>(getBaseDao().mMyValidators.subList(0,16));
                Toast.makeText(getBaseContext(), R.string.str_multi_reward_max_16, Toast.LENGTH_SHORT).show();
            }

            ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.gas_multi_reward_kava)));
            BigDecimal estimateGasAmount = new BigDecimal(rewardGasFees.get(toClaimValidators.size() - 1));
            BigDecimal estimateFeeAmount = estimateGasAmount.multiply(new BigDecimal(FEE_IOV_GAS_RATE_AVERAGE)).setScale(0);
            BigDecimal available = mAccount.getIovBalance();

            if (available.compareTo(estimateFeeAmount) <= 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }

        } else if (mBaseChain.equals(BaseChain.IOV_TEST)) {
            //only collect over 0.15 iov
            if (getBaseDao().mRewards == null) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
                return;
            }
            BigDecimal rewardSum = BigDecimal.ZERO;
            for (BondingState bond:getBaseDao().mBondings) {
                if (WDp.getValidatorReward(getBaseDao().mRewards, bond.validatorAddress, TOKEN_IOV_TEST).compareTo(new BigDecimal("150000")) >= 0) {
                    if (WUtil.selectValidatorByAddr(getBaseDao().mMyValidators, bond.validatorAddress) != null) {
                        toClaimValidators.add(WUtil.selectValidatorByAddr(getBaseDao().mMyValidators, bond.validatorAddress));
                        rewardSum = rewardSum.add(WDp.getValidatorReward(getBaseDao().mRewards, bond.validatorAddress, TOKEN_IOV_TEST));
                    }
                }
            }
            if (toClaimValidators.size() == 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
                return;
            }
            WUtil.onSortByOnlyReward(toClaimValidators, getBaseDao().mRewards, TOKEN_IOV_TEST);
            if (toClaimValidators.size() >= 17) {
                toClaimValidators = new ArrayList<>(getBaseDao().mMyValidators.subList(0,16));
                Toast.makeText(getBaseContext(), R.string.str_multi_reward_max_16, Toast.LENGTH_SHORT).show();
            }

            ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.gas_multi_reward_kava)));
            BigDecimal estimateGasAmount = new BigDecimal(rewardGasFees.get(toClaimValidators.size() - 1));
            BigDecimal estimateFeeAmount = estimateGasAmount.multiply(new BigDecimal(FEE_IOV_GAS_RATE_AVERAGE)).setScale(0);
            BigDecimal available = mAccount.getIovBalance();

            if (available.compareTo(estimateFeeAmount) <= 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }

        } else if (mBaseChain.equals(BaseChain.CERTIK_MAIN) || mBaseChain.equals(BaseChain.CERTIK_TEST)) {
            if (getBaseDao().mRewards == null) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
                return;
            }
            BigDecimal rewardSum = BigDecimal.ZERO;
            for (BondingState bond:getBaseDao().mBondings) {
                if (WDp.getValidatorReward(getBaseDao().mRewards, bond.validatorAddress, TOKEN_CERTIK).compareTo(new BigDecimal("7500")) >= 0) {
                    if (WUtil.selectValidatorByAddr(getBaseDao().mMyValidators, bond.validatorAddress) != null) {
                        toClaimValidators.add(WUtil.selectValidatorByAddr(getBaseDao().mMyValidators, bond.validatorAddress));
                        rewardSum = rewardSum.add(WDp.getValidatorReward(getBaseDao().mRewards, bond.validatorAddress, TOKEN_CERTIK));
                    }
                }
            }
            if (toClaimValidators.size() == 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
                return;
            }
            WUtil.onSortByOnlyReward(toClaimValidators, getBaseDao().mRewards, TOKEN_CERTIK);
            if (toClaimValidators.size() >= 17) {
                toClaimValidators = new ArrayList<>(getBaseDao().mMyValidators.subList(0,16));
                Toast.makeText(getBaseContext(), R.string.str_multi_reward_max_16, Toast.LENGTH_SHORT).show();
            }

            ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.gas_multi_reward_kava)));
            BigDecimal estimateGasAmount = new BigDecimal(rewardGasFees.get(toClaimValidators.size() - 1));
            BigDecimal estimateFeeAmount = estimateGasAmount.multiply(new BigDecimal(FEE_CERTIK_GAS_RATE_AVERAGE)).setScale(0);
            BigDecimal available = mAccount.getTokenBalance(TOKEN_CERTIK);

            if (available.compareTo(estimateFeeAmount) <= 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }

        } else if (mBaseChain.equals(SECRET_MAIN)) {
            if (getBaseDao().mRewards == null) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
                return;
            }
            BigDecimal rewardSum = BigDecimal.ZERO;
            for (BondingState bond:getBaseDao().mBondings) {
                if (WDp.getValidatorReward(getBaseDao().mRewards, bond.validatorAddress, TOKEN_SECRET).compareTo(new BigDecimal("37500")) >= 0) {
                    if (WUtil.selectValidatorByAddr(getBaseDao().mMyValidators, bond.validatorAddress) != null) {
                        toClaimValidators.add(WUtil.selectValidatorByAddr(getBaseDao().mMyValidators, bond.validatorAddress));
                        rewardSum = rewardSum.add(WDp.getValidatorReward(getBaseDao().mRewards, bond.validatorAddress, TOKEN_SECRET));
                    }
                }
            }
            if (toClaimValidators.size() == 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
                return;
            }
            WUtil.onSortByOnlyReward(toClaimValidators, getBaseDao().mRewards, TOKEN_SECRET);
            if (toClaimValidators.size() >= 17) {
                toClaimValidators = new ArrayList<>(getBaseDao().mMyValidators.subList(0,16));
                Toast.makeText(getBaseContext(), R.string.str_multi_reward_max_16, Toast.LENGTH_SHORT).show();
            }

            ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.gas_multi_reward_kava)));
            BigDecimal estimateGasAmount = new BigDecimal(rewardGasFees.get(toClaimValidators.size() - 1));
            BigDecimal estimateFeeAmount = estimateGasAmount.multiply(new BigDecimal(SECRET_GAS_FEE_RATE_AVERAGE)).setScale(0);
            BigDecimal available = mAccount.getTokenBalance(TOKEN_SECRET);

            if (available.compareTo(estimateFeeAmount) <= 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }

        } else if (mBaseChain.equals(AKASH_MAIN)) {
            if (getBaseDao().mRewards == null) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
                return;
            }
            BigDecimal rewardSum = BigDecimal.ZERO;
            for (BondingState bond:getBaseDao().mBondings) {
                if (WDp.getValidatorReward(getBaseDao().mRewards, bond.validatorAddress, TOKEN_AKASH).compareTo(new BigDecimal("3750")) >= 0) {
                    if (WUtil.selectValidatorByAddr(getBaseDao().mMyValidators, bond.validatorAddress) != null) {
                        toClaimValidators.add(WUtil.selectValidatorByAddr(getBaseDao().mMyValidators, bond.validatorAddress));
                        rewardSum = rewardSum.add(WDp.getValidatorReward(getBaseDao().mRewards, bond.validatorAddress, TOKEN_AKASH));
                    }
                }
            }
            if (toClaimValidators.size() == 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
                return;
            }
            WUtil.onSortByOnlyReward(toClaimValidators, getBaseDao().mRewards, TOKEN_AKASH);
            if (toClaimValidators.size() >= 17) {
                toClaimValidators = new ArrayList<>(getBaseDao().mMyValidators.subList(0,16));
                Toast.makeText(getBaseContext(), R.string.str_multi_reward_max_16, Toast.LENGTH_SHORT).show();
            }

            ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.gas_multi_reward_kava)));
            BigDecimal estimateGasAmount = new BigDecimal(rewardGasFees.get(toClaimValidators.size() - 1));
            BigDecimal estimateFeeAmount = estimateGasAmount.multiply(new BigDecimal(FEE_AKASH_GAS_RATE_AVERAGE)).setScale(0);
            BigDecimal available = mAccount.getTokenBalance(TOKEN_AKASH);

            if (available.compareTo(estimateFeeAmount) <= 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }

        } else if (mBaseChain.equals(COSMOS_MAIN) || mBaseChain.equals(COSMOS_TEST) || mBaseChain.equals(IRIS_TEST)) {
            ArrayList<Distribution.DelegationDelegatorReward> toClaimRewards = new ArrayList<>();
            if (getBaseDao().mGrpcRewards == null) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
                return;
            }
            for (Distribution.DelegationDelegatorReward reward: getBaseDao().mGrpcRewards) {
                if (getBaseDao().getReward(WDp.mainDenom(mBaseChain), reward.getValidatorAddress()).compareTo(new BigDecimal("3750")) >= 0) {
                    toClaimRewards.add(reward);
                }
            }
            if (toClaimRewards.size() == 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
                return;
            }
            WUtil.onSortRewardAmount(toClaimRewards, WDp.mainDenom(mBaseChain));
            if (toClaimRewards.size() >= 17) {
                ArrayList<Distribution.DelegationDelegatorReward> temp = new ArrayList(toClaimValidators.subList(0,16));
                toClaimRewards = temp;
                Toast.makeText(getBaseContext(), R.string.str_multi_reward_max_16, Toast.LENGTH_SHORT).show();
            }
            ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.gas_multi_reward)));
            BigDecimal estimateGasAmount = new BigDecimal(rewardGasFees.get(toClaimRewards.size() - 1));
            BigDecimal estimateFeeAmount = estimateGasAmount.multiply(new BigDecimal(STARGATE_GAS_RATE_AVERAGE)).setScale(0);
            BigDecimal available = getBaseDao().getAvailable(WDp.mainDenom(mBaseChain));

            if (available.compareTo(estimateFeeAmount) <= 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }


            for (Distribution.DelegationDelegatorReward reward: toClaimRewards) {
                toClaimValaddr.add(reward.getValidatorAddress());
            }

        } else {
            Toast.makeText(getBaseContext(), R.string.error_not_yet, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent claimReward = new Intent(ValidatorListActivity.this, ClaimRewardActivity.class);
        claimReward.putExtra("opAddresses", toClaimValidators);
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
