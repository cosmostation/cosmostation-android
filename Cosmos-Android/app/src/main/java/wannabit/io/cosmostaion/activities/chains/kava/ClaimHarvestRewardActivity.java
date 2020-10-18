package wannabit.io.cosmostaion.activities.chains.kava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.fragment.chains.kava.ClaimHarvestStep0Fragment;
import wannabit.io.cosmostaion.fragment.chains.kava.ClaimHarvestStep1Fragment;
import wannabit.io.cosmostaion.fragment.chains.kava.ClaimHarvestStep2Fragment;
import wannabit.io.cosmostaion.fragment.chains.kava.ClaimHarvestStep3Fragment;
import wannabit.io.cosmostaion.model.KavaClaimMultiplier;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.network.res.ResKavaHarvestParam;
import wannabit.io.cosmostaion.network.res.ResKavaHarvestReward;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_PURPOSE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_CLAIM_HARVEST_REWARD;

public class ClaimHarvestRewardActivity extends BaseActivity {

    private RelativeLayout                  mRootView;
    private Toolbar                         mToolbar;
    private TextView                        mTitle;
    private ImageView                       mIvStep;
    private TextView                        mTvStep;
    private ViewPager                       mViewPager;
    private ClaimHarvestRewardPageAdapter   mPageAdapter;

    public String                           mMemo;
    public Fee                              mFee;

    public String                                   mHarvestDepositDenom;
    public String                                   mHarvestDepositType;            // "lp" or "stake"
    public ResKavaHarvestParam                      mHarvestParam;
    public BigDecimal                               mAllRewardAmount;
    public BigDecimal                               mReceivableAmount;
    public ArrayList<KavaClaimMultiplier>           mClaimMultipliers;
    public KavaClaimMultiplier                      mSelectedMultiplier = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        mRootView = findViewById(R.id.root_view);
        mToolbar = findViewById(R.id.tool_bar);
        mTitle = findViewById(R.id.toolbar_title);
        mIvStep = findViewById(R.id.send_step);
        mTvStep = findViewById(R.id.send_step_msg);
        mViewPager = findViewById(R.id.view_pager);
        mTitle.setText(getString(R.string.str_claim_harvest_reward));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mIvStep.setImageDrawable(getDrawable(R.drawable.step_4_img_1));
        mTvStep.setText(getString(R.string.str_harvest_reward_step_0));

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mHarvestDepositDenom = getIntent().getStringExtra("harvest_deposit_denom");
        mHarvestDepositType = getIntent().getStringExtra("harvest_deposit_type");
        mHarvestParam = getBaseDao().mHarvestParam;
        if (mHarvestDepositType.equals("stake")) {
            mClaimMultipliers = mHarvestParam.getKavaStakerSchedule().distribution_schedule.claim_multipliers;
        } else {
            for (ResKavaHarvestParam.DistributionSchedule dschedule: mHarvestParam.result.liquidity_provider_schedules) {
                if (dschedule.deposit_denom.equals(mHarvestDepositDenom)) {
                    mClaimMultipliers = dschedule.claim_multipliers;
                }
            }
        }
        for (ResKavaHarvestReward.HarvestReward reward : getBaseDao().mHavestRewards) {
            if (reward.deposit_denom.equals(mHarvestDepositDenom) && reward.type.equals(mHarvestDepositType)) {
                mAllRewardAmount = new BigDecimal(reward.amount.amount);
            }
        }
//        WLog.w("mHarvestDepositDenom " + mHarvestDepositDenom);
//        WLog.w("mHarvestDepositType " + mHarvestDepositType);
//        WLog.w("mAllRewardAmount " + mAllRewardAmount);
//        WLog.w("mClaimMultipliers " + mClaimMultipliers.size());

        mPageAdapter = new ClaimHarvestRewardPageAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mPageAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) { }

            @Override
            public void onPageSelected(int i) {
                if(i == 0) {
                    mIvStep.setImageDrawable(getDrawable(R.drawable.step_4_img_1));
                    mTvStep.setText(getString(R.string.str_harvest_reward_step_0));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                } else if (i == 1 ) {
                    mIvStep.setImageDrawable(getDrawable(R.drawable.step_4_img_2));
                    mTvStep.setText(getString(R.string.str_harvest_reward_step_1));
                } else if (i == 2 ) {
                    mIvStep.setImageDrawable(getDrawable(R.drawable.step_4_img_3));
                    mTvStep.setText(getString(R.string.str_harvest_reward_step_2));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                } else if (i == 3 ) {
                    mIvStep.setImageDrawable(getDrawable(R.drawable.step_4_img_4));
                    mTvStep.setText(getString(R.string.str_harvest_reward_step_3));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) { }
        });
        mViewPager.setCurrentItem(0);

        mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onHideKeyboard();
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

    @Override
    public void onBackPressed() {
        onHideKeyboard();
        if(mViewPager.getCurrentItem() > 0) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
        } else {
            super.onBackPressed();
        }
    }

    public void onNextStep() {
        if(mViewPager.getCurrentItem() < mViewPager.getChildCount()) {
            onHideKeyboard();
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
        }
    }

    public void onBeforeStep() {
        if(mViewPager.getCurrentItem() > 0) {
            onHideKeyboard();
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
        } else {
            onBackPressed();
        }
    }


    public void onStartHarvestRewardClaim() {
        Intent intent = new Intent(ClaimHarvestRewardActivity.this, PasswordCheckActivity.class);
        intent.putExtra(CONST_PW_PURPOSE, CONST_PW_TX_CLAIM_HARVEST_REWARD);
        intent.putExtra("depositDenom", mHarvestDepositDenom);
        intent.putExtra("depositType", mHarvestDepositType);
        intent.putExtra("multiplierName", mSelectedMultiplier.name);
        intent.putExtra("fee", mFee);
        intent.putExtra("memo", mMemo);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
    }

    private class ClaimHarvestRewardPageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public ClaimHarvestRewardPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(ClaimHarvestStep0Fragment.newInstance(null));
            mFragments.add(ClaimHarvestStep1Fragment.newInstance(null));
            mFragments.add(ClaimHarvestStep2Fragment.newInstance(null));
            mFragments.add(ClaimHarvestStep3Fragment.newInstance(null));
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
