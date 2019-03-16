package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BondingState;
import wannabit.io.cosmostaion.dao.Reward;
import wannabit.io.cosmostaion.dao.TotalReward;
import wannabit.io.cosmostaion.fragment.RewardStep0Fragment;
import wannabit.io.cosmostaion.fragment.RewardStep1Fragment;
import wannabit.io.cosmostaion.fragment.RewardStep2Fragment;
import wannabit.io.cosmostaion.fragment.RewardStep3Fragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.task.SingleFetchTask.CheckWithdrawAddressTask;
import wannabit.io.cosmostaion.task.SingleFetchTask.SingleRewardTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.FetchTask.TotalRewardTask;
import wannabit.io.cosmostaion.utils.WLog;

public class ClaimRewardActivity extends BaseActivity implements TaskListener {

    private Toolbar                     mToolbar;
    private TextView                    mTitle;
    private ImageView                   mIvStep;
    private TextView                    mTvStep;
    private ViewPager                   mViewPager;
    private RewardPageAdapter           mPageAdapter;

    public boolean                       isAll = false;
    public Account                      mAccount;
    public Validator                    mValidator;
    public String                       mRewardMemo;
    public Fee                          mRewardFee;

    public ArrayList<BondingState>      mBondings = new ArrayList<>();
    public ArrayList<Balance>           mBalances = new ArrayList<>();
    public TotalReward                  mTotalReward;
    public Reward                       mRewards;
    public String                       mWithdrawAddress;
    private int                         mTaskCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        mToolbar            = findViewById(R.id.tool_bar);
        mTitle              = findViewById(R.id.toolbar_title);
        mIvStep             = findViewById(R.id.send_step);
        mTvStep             = findViewById(R.id.send_step_msg);
        mViewPager          = findViewById(R.id.view_pager);
        mTitle.setText(getString(R.string.str_reward_c));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mIvStep.setImageDrawable(getDrawable(R.drawable.step_4_img_1));

        mAccount    = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBalances   = getBaseDao().onSelectBalance(mAccount.id);
        mBondings   = getBaseDao().onSelectBondingStates(mAccount.id);
        mPageAdapter = new RewardPageAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mPageAdapter);

        isAll = getIntent().getBooleanExtra("isAll", false);
        if(!isAll) {
            WLog.w("single");
            mValidator = getBaseDao().getValidator();
            onFetchReward();
        } else {
            WLog.w("all");
            onFetchAllReward();
        }



        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) { }

            @Override
            public void onPageSelected(int i) {
                if(i == 0) {
                    mIvStep.setImageDrawable(getDrawable(R.drawable.step_4_img_1));
                    mTvStep.setText(getString(R.string.str_reward_step_1));

                } else if (i == 1 ) {
                    mIvStep.setImageDrawable(getDrawable(R.drawable.step_4_img_2));
                    mTvStep.setText(getString(R.string.str_reward_step_2));

                } else if (i == 2 ) {
                    mIvStep.setImageDrawable(getDrawable(R.drawable.step_4_img_3));
                    mTvStep.setText(getString(R.string.str_reward_step_3));
                    mPageAdapter.mCurrentFragment.onRefreshTab();

                } else if (i == 3 ) {
                    mIvStep.setImageDrawable(getDrawable(R.drawable.step_4_img_4));
                    mTvStep.setText(getString(R.string.str_reward_step_4));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) { }
        });
        mViewPager.setCurrentItem(0);
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
        if(mViewPager.getCurrentItem() > 0) {
            onHideKeyboard();
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


    private void onFetchAllReward() {
        if(mTaskCount > 0) return;
        mTaskCount = 2;
        ArrayList<Account> accounts = new ArrayList<Account>();
        accounts.add(mAccount);
        new TotalRewardTask(getBaseApplication(), this, accounts).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new CheckWithdrawAddressTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void onFetchReward() {
        if(mTaskCount > 0) return;
        mTaskCount = 2;
        new SingleRewardTask(getBaseApplication(), this, mAccount, mValidator.operator_address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new CheckWithdrawAddressTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    public void onStartReward() {
        Intent intent = new Intent(ClaimRewardActivity.this, PasswordCheckActivity.class);
        intent.putExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_TX_SIMPLE_REWARD);
        intent.putExtra("toAddress", mValidator.operator_address);
        intent.putExtra("memo", mRewardMemo);
        intent.putExtra("fee", mRewardFee);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
    }


    @Override
    public void onTaskResponse(TaskResult result) {
        mTaskCount--;
        if(isFinishing()) return;
        if (result.taskType == BaseConstant.TASK_FETCH_SINGLE_REWARD) {
            mRewards = (Reward)result.resultData;
            WLog.w("mRewards : " + mRewards.amount.size());
            WLog.w("mRewards : " + mRewards.validatorAddress);

        } else if (result.taskType == BaseConstant.TASK_FETCH_TOTAL_REWARDS) {
            HashMap<Long, TotalReward> temp = (HashMap<Long, TotalReward>)result.resultData;
            if(temp != null) {
                mTotalReward = temp.get(mAccount.id);
            }
        } else if (result.taskType == BaseConstant.TASK_FETCH_WITHDRAW_ADDRESS) {
            mWithdrawAddress = (String)result.resultData;
        }

        if(mTaskCount == 0) {
            WLog.w("reward fetched!!");
            mPageAdapter.mCurrentFragment.onRefreshTab();
        }
    }


    private class RewardPageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public RewardPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(RewardStep0Fragment.newInstance(null));
            mFragments.add(RewardStep1Fragment.newInstance(null));
            mFragments.add(RewardStep2Fragment.newInstance(null));
            mFragments.add(RewardStep3Fragment.newInstance(null));
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
