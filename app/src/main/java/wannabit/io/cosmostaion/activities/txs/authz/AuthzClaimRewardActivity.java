package wannabit.io.cosmostaion.activities.txs.authz;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_AUTHZ_CLAIM_REWARD;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import cosmos.distribution.v1beta1.Distribution;
import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.cosmos.Signer;
import wannabit.io.cosmostaion.fragment.StepFeeSetFragment;
import wannabit.io.cosmostaion.fragment.StepMemoFragment;
import wannabit.io.cosmostaion.fragment.txs.authz.AuthzClaimRewardStep0Fragment;
import wannabit.io.cosmostaion.fragment.txs.authz.AuthzClaimRewardStep3Fragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.WithdrawAddressGrpcTask;

public class AuthzClaimRewardActivity extends BaseBroadCastActivity {

    private Toolbar mToolbar;
    private TextView mTitle;
    private ImageView mIvStep;
    private TextView mTvStep;
    private ViewPager mViewPager;
    private AuthzRewardPageAdapter mPageAdapter;

    public Coin mGranterRewardSum;
    public ArrayList<Distribution.DelegationDelegatorReward> mGranterRewards = new ArrayList<>();
    public String mWithdrawAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        mToolbar = findViewById(R.id.tool_bar);
        mTitle = findViewById(R.id.toolbar_title);
        mIvStep = findViewById(R.id.send_step);
        mTvStep = findViewById(R.id.send_step_msg);
        mViewPager = findViewById(R.id.view_pager);
        mTitle.setText(getString(R.string.str_authz_reward_title));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mIvStep.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.step_1_img));
        mTvStep.setText(getString(R.string.str_authz_reward_step_0));

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);
        mTxType = CONST_PW_TX_AUTHZ_CLAIM_REWARD;

        mGranterRewardSum = getIntent().getParcelableExtra("granterRewardSum");
        mGranter = getIntent().getStringExtra("granter");
        mGranterRewards = (ArrayList<Distribution.DelegationDelegatorReward>) getIntent().getSerializableExtra("granterRewards");

        mPageAdapter = new AuthzRewardPageAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mPageAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(AuthzClaimRewardActivity.this, R.drawable.step_4_img_1));
                    mTvStep.setText(getString(R.string.str_authz_reward_step_0));

                } else if (i == 1) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(AuthzClaimRewardActivity.this, R.drawable.step_4_img_2));
                    mTvStep.setText(getString(R.string.str_tx_step_memo));

                } else if (i == 2) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(AuthzClaimRewardActivity.this, R.drawable.step_4_img_3));
                    mTvStep.setText(getString(R.string.str_tx_step_fee));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                } else if (i == 3) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(AuthzClaimRewardActivity.this, R.drawable.step_4_img_4));
                    mTvStep.setText(getString(R.string.str_tx_step_confirm));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        mViewPager.setCurrentItem(0);
        onFetchReward();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAccount == null) finish();
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

    private void onFetchReward() {
        new WithdrawAddressGrpcTask(getBaseApplication(), new TaskListener() {
            @Override
            public void onTaskResponse(TaskResult result) {
                if (result.isSuccess && result.resultData != null) {
                    mWithdrawAddress = (String) result.resultData;
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                }
            }
        }, mBaseChain, mGranter).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void onAuthzClaimReward() {
        if (getBaseDao().isAutoPass()) {
            ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = Signer.getGrpcAuthzClaimRewardReq(getAuthResponse(mBaseChain, mAccount), mAccount.address, mGranter, mValAddresses,
                    mTxFee, mTxMemo, getEcKey(mAccount), getBaseDao().getChainIdGrpc());
            onBroadcastGrpcTx(mBaseChain, broadcastTxRequest);

        } else {
            Intent intent = new Intent(AuthzClaimRewardActivity.this, PasswordCheckActivity.class);
            intent.putExtra(BaseConstant.CONST_PW_PURPOSE, mTxType);
            intent.putExtra("granter", mGranter);
            intent.putExtra("valOpAddresses", mValAddresses);
            intent.putExtra("memo", mTxMemo);
            intent.putExtra("fee", mTxFee);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
        }
    }

    private class AuthzRewardPageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public AuthzRewardPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(AuthzClaimRewardStep0Fragment.newInstance());
            mFragments.add(StepMemoFragment.newInstance());
            mFragments.add(StepFeeSetFragment.newInstance());
            mFragments.add(AuthzClaimRewardStep3Fragment.newInstance());
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
