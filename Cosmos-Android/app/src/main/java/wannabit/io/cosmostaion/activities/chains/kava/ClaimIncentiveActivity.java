package wannabit.io.cosmostaion.activities.chains.kava;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.fragment.chains.kava.ClaimIncentiveStep0Fragment;
import wannabit.io.cosmostaion.fragment.chains.kava.ClaimIncentiveStep1Fragment;
import wannabit.io.cosmostaion.fragment.chains.kava.ClaimIncentiveStep2Fragment;
import wannabit.io.cosmostaion.fragment.chains.kava.ClaimIncentiveStep3Fragment;
import wannabit.io.cosmostaion.model.KavaClaimMultiplier;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.network.res.ResKavaIncentiveParam;
import wannabit.io.cosmostaion.network.res.ResKavaIncentiveReward;

public class ClaimIncentiveActivity extends BaseActivity {

    private RelativeLayout              mRootView;
    private ImageView                   mChainBg;
    private Toolbar                     mToolbar;
    private TextView                    mTitle;
    private ImageView                   mIvStep;
    private TextView                    mTvStep;
    private ViewPager                   mViewPager;
    private ClaimIncentivePageAdapter   mPageAdapter;

    public String                       mMemo;
    public Fee                          mFee;

    public String                                   mCollateralParamType;
    public KavaClaimMultiplier                      mSelectedMultiplier = null;
    public ResKavaIncentiveParam.IncentiveParam     mIncentiveParam;
    public ResKavaIncentiveParam.IncentiveReward    mIncentiveReward;
    public BigDecimal                               mAllIncentiveAmount;
    public BigDecimal                               mReceivableAmount;
    public ArrayList<KavaClaimMultiplier>           mClaimMultipliers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        mRootView = findViewById(R.id.root_view);
        mChainBg = findViewById(R.id.chain_bg);
        mToolbar = findViewById(R.id.tool_bar);
        mTitle = findViewById(R.id.toolbar_title);
        mIvStep = findViewById(R.id.send_step);
        mTvStep = findViewById(R.id.send_step_msg);
        mViewPager = findViewById(R.id.view_pager);
        mTitle.setText(getString(R.string.str_participate_incentive));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mIvStep.setImageDrawable(getDrawable(R.drawable.step_4_img_1));
        mTvStep.setText(getString(R.string.str_incentive_participate_step_0));

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mCollateralParamType = getIntent().getStringExtra("collateral_type");
        mIncentiveParam = getBaseDao().mKavaIncentiveParam;
        for (ResKavaIncentiveParam.IncentiveReward incentiveReward:mIncentiveParam.rewards) {
            if (incentiveReward.collateral_type.equals(mCollateralParamType)) {
                mIncentiveReward = incentiveReward;
            }
        }
        for (ResKavaIncentiveReward.IncentiveRewardClaimable incentiveClaimable:getBaseDao().mKavaUnClaimedIncentiveRewards) {
            if (mCollateralParamType.equals(incentiveClaimable.claim.collateral_type) && incentiveClaimable.claimable) {
                mAllIncentiveAmount = new BigDecimal(incentiveClaimable.claim.reward.amount);
            }
        }
        mClaimMultipliers = mIncentiveReward.claim_multipliers;
//        WLog.w("mCollateralParamType " + mCollateralParamType);
//        WLog.w("mIncentiveReward " + mIncentiveReward.collateral_type);
//        WLog.w("mAllIncentiveAmount " + mAllIncentiveAmount);
//        WLog.w("mClaimMultipliers " + mClaimMultipliers.size());

        mPageAdapter = new ClaimIncentivePageAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mPageAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) { }

            @Override
            public void onPageSelected(int i) {
                if(i == 0) {
                    mIvStep.setImageDrawable(getDrawable(R.drawable.step_4_img_1));
                    mTvStep.setText(getString(R.string.str_incentive_participate_step_0));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                } else if (i == 1 ) {
                    mIvStep.setImageDrawable(getDrawable(R.drawable.step_4_img_2));
                    mTvStep.setText(getString(R.string.str_incentive_participate_step_1));
                } else if (i == 2 ) {
                    mIvStep.setImageDrawable(getDrawable(R.drawable.step_4_img_3));
                    mTvStep.setText(getString(R.string.str_incentive_participate_step_2));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                } else if (i == 3 ) {
                    mIvStep.setImageDrawable(getDrawable(R.drawable.step_4_img_4));
                    mTvStep.setText(getString(R.string.str_incentive_participate_step_3));
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

    public void onStartIncentiveClaim() {
        Intent intent = new Intent(ClaimIncentiveActivity.this, PasswordCheckActivity.class);
        intent.putExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_TX_CLAIM_INCENTIVE);
        intent.putExtra("collateralType", mCollateralParamType);
        intent.putExtra("multiplierName", mSelectedMultiplier.name);
        intent.putExtra("fee", mFee);
        intent.putExtra("memo", mMemo);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
    }



    private class ClaimIncentivePageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public ClaimIncentivePageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(ClaimIncentiveStep0Fragment.newInstance(null));
            mFragments.add(ClaimIncentiveStep1Fragment.newInstance(null));
            mFragments.add(ClaimIncentiveStep2Fragment.newInstance(null));
            mFragments.add(ClaimIncentiveStep3Fragment.newInstance(null));
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
