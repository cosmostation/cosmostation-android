package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import cosmos.staking.v1beta1.Staking;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.fragment.RedelegateStep0Fragment;
import wannabit.io.cosmostaion.fragment.RedelegateStep1Fragment;
import wannabit.io.cosmostaion.fragment.RedelegateStep4Fragment;
import wannabit.io.cosmostaion.fragment.StepFeeSetFragment;
import wannabit.io.cosmostaion.fragment.StepMemoFragment;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.BondedValidatorsGrpcTask;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_REDELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_BONDED_VALIDATORS;

public class RedelegateActivity extends BaseBroadCastActivity implements TaskListener {

    private ImageView                       mChainBg;
    private Toolbar                         mToolbar;
    private TextView                        mTitle;
    private ImageView                       mIvStep;
    private TextView                        mTvStep;
    private ViewPager                       mViewPager;
    private RedelegatePageAdapter           mPageAdapter;

    private int                             mTaskCount;
    public ArrayList<Staking.Validator>     mGRpcTopValidators = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        mChainBg            = findViewById(R.id.chain_bg);
        mToolbar            = findViewById(R.id.tool_bar);
        mTitle              = findViewById(R.id.toolbar_title);
        mIvStep             = findViewById(R.id.send_step);
        mTvStep             = findViewById(R.id.send_step_msg);
        mViewPager          = findViewById(R.id.view_pager);
        mTitle.setText(getString(R.string.str_redelegate_c));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mIvStep.setImageDrawable(getDrawable(R.drawable.step_1_img));
        mTvStep.setText(getString(R.string.str_redelegate_step_0));

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mTxType = CONST_PW_TX_SIMPLE_REDELEGATE;

        mValAddress = getIntent().getStringExtra("valOpAddress");

        mPageAdapter = new RedelegatePageAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mPageAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) { }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    mIvStep.setImageDrawable(getDrawable(R.drawable.step_1_img));
                    mTvStep.setText(getString(R.string.str_redelegate_step_0));
                } else if (i == 1 ) {
                    mIvStep.setImageDrawable(getDrawable(R.drawable.step_2_img));
                    mTvStep.setText(getString(R.string.str_redelegate_step_1));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                } else if (i == 2 ) {
                    mIvStep.setImageDrawable(getDrawable(R.drawable.step_3_img));
                    mTvStep.setText(getString(R.string.str_redelegate_step_2));
                } else if (i == 3 ) {
                    mIvStep.setImageDrawable(getDrawable(R.drawable.step_4_img));
                    mTvStep.setText(getString(R.string.str_redelegate_step_3));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                } else if (i == 4 ) {
                    mIvStep.setImageDrawable(getDrawable(R.drawable.step_5_img));
                    mTvStep.setText(getString(R.string.str_redelegate_step_4));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) { }
        });
        mViewPager.setCurrentItem(0);
        onFetchValidtors();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mAccount == null) finish();
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

    @Override
    public void onCancelWithVesting() {
        onBackPressed();
    }

    public void onStartRedelegate() {
        Intent intent = new Intent(RedelegateActivity.this, PasswordCheckActivity.class);
        intent.putExtra(BaseConstant.CONST_PW_PURPOSE, CONST_PW_TX_SIMPLE_REDELEGATE);
        intent.putExtra("fromValidatorAddr", mValAddress);
        intent.putExtra("toValidatorAddr", mToValAddress);
        intent.putExtra("rAmount", mAmount);
        intent.putExtra("memo", mTxMemo);
        intent.putExtra("fee", mTxFee);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);


    }


    private void onFetchValidtors() {
        if(mTaskCount > 0) return;
        mTaskCount = 1;
        new BondedValidatorsGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        mTaskCount--;
        if(isFinishing()) return;
        if (result.taskType == TASK_GRPC_FETCH_BONDED_VALIDATORS) {
            ArrayList<Staking.Validator> temp = (ArrayList<Staking.Validator>)result.resultData;
            if (temp != null) {
                for (Staking.Validator val:temp) {
                    if (!val.getOperatorAddress().equals(mValAddress)) {
                        mGRpcTopValidators.add(val);
                    }
                }
                WUtil.onSortByValidatorPowerV1(mGRpcTopValidators);

            } else {
                Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();
            }
        }

        if (mTaskCount == 0) {
            mPageAdapter.mCurrentFragment.onRefreshTab();
        }
    }


    private class RedelegatePageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public RedelegatePageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(RedelegateStep0Fragment.newInstance(null));
            mFragments.add(RedelegateStep1Fragment.newInstance(null));
            mFragments.add(StepMemoFragment.newInstance(null));
            mFragments.add(StepFeeSetFragment.newInstance(null));
            mFragments.add(RedelegateStep4Fragment.newInstance(null));
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
