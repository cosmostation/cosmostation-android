package wannabit.io.cosmostaion.activities.txs.kava;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_PURPOSE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_WITHDRAW_HARD;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.fragment.StepFeeSetFragment;
import wannabit.io.cosmostaion.fragment.StepMemoFragment;
import wannabit.io.cosmostaion.fragment.txs.kava.WithdrawHardStep0Fragment;
import wannabit.io.cosmostaion.fragment.txs.kava.WithdrawHardStep3Fragment;

public class WithdrawHardActivity extends BaseBroadCastActivity {

    private RelativeLayout mRootView;
    private Toolbar mToolbar;
    private TextView mTitle;
    private ImageView mIvStep;
    private TextView mTvStep;
    private ViewPager mViewPager;
    private WithdrawHarvestPageAdapter mPageAdapter;

    public String mHardMoneyMarketDenom;

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
        mTitle.setText(getString(R.string.str_withdraw_harvest));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mIvStep.setImageDrawable(ContextCompat.getDrawable(WithdrawHardActivity.this, R.drawable.step_4_img_1));
        mTvStep.setText(getString(R.string.str_withdraw_harvest_step_1));

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mTxType = CONST_PW_TX_WITHDRAW_HARD;
        mHardMoneyMarketDenom = getIntent().getStringExtra("hardPoolDemon");

        mPageAdapter = new WithdrawHarvestPageAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mPageAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(WithdrawHardActivity.this, R.drawable.step_4_img_1));
                    mTvStep.setText(getString(R.string.str_withdraw_harvest_step_1));
                } else if (i == 1) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(WithdrawHardActivity.this, R.drawable.step_4_img_2));
                    mTvStep.setText(getString(R.string.str_withdraw_harvest_step_2));
                } else if (i == 2) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(WithdrawHardActivity.this, R.drawable.step_4_img_3));
                    mTvStep.setText(getString(R.string.str_withdraw_harvest_step_3));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                } else if (i == 3) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(WithdrawHardActivity.this, R.drawable.step_4_img_4));
                    mTvStep.setText(getString(R.string.str_withdraw_harvest_step_4));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
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
        if (mViewPager.getCurrentItem() > 0) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
        } else {
            super.onBackPressed();
        }
    }

    public void onNextStep() {
        if (mViewPager.getCurrentItem() < mViewPager.getChildCount()) {
            onHideKeyboard();
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
        }
    }

    public void onBeforeStep() {
        if (mViewPager.getCurrentItem() > 0) {
            onHideKeyboard();
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
        } else {
            onBackPressed();
        }
    }

    public void onStartWithdrawHarvest() {
        Intent intent = new Intent(WithdrawHardActivity.this, PasswordCheckActivity.class);
        intent.putExtra(CONST_PW_PURPOSE, CONST_PW_TX_WITHDRAW_HARD);
        intent.putExtra("hardPoolCoins", mHardPoolCoins);
        intent.putExtra("fee", mTxFee);
        intent.putExtra("memo", mTxMemo);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);

    }

    private class WithdrawHarvestPageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public WithdrawHarvestPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(WithdrawHardStep0Fragment.newInstance(null));
            mFragments.add(StepMemoFragment.newInstance(null));
            mFragments.add(StepFeeSetFragment.newInstance(null));
            mFragments.add(WithdrawHardStep3Fragment.newInstance(null));
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
