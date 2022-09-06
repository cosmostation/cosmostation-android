package wannabit.io.cosmostaion.activities.txs.starname;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_PURPOSE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_REPLACE_STARNAME;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_STARNAME_DOMAIN_INFO;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_STARNAME_RESOLVE;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
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

import cosmos.tx.v1beta1.ServiceOuterClass;
import starnamed.x.starname.v1beta1.Types;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.cosmos.Signer;
import wannabit.io.cosmostaion.fragment.StepFeeSetFragment;
import wannabit.io.cosmostaion.fragment.StepMemoFragment;
import wannabit.io.cosmostaion.fragment.txs.starname.ReplaceStarName0Fragment;
import wannabit.io.cosmostaion.fragment.txs.starname.ReplaceStarName3Fragment;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.StarNameGrpcDomainInfoTask;
import wannabit.io.cosmostaion.task.gRpcTask.StarNameGrpcResolveTask;
import wannabit.io.cosmostaion.utils.StarnameResourceWrapper;

public class ReplaceStarNameActivity extends BaseBroadCastActivity {

    private RelativeLayout mRootView;
    private Toolbar mToolbar;
    private TextView mTitle;
    private ImageView mIvStep;
    private TextView mTvStep;
    private ViewPager mViewPager;

    private ReplaceStarNamePageAdapter mPageAdapter;
    public Types.Domain mDomain_gRPC;
    public Types.Account mAccountResolve_gRPC;

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
        mTitle.setText(getString(R.string.str_replace_starname));

        mIsDomain = getIntent().getBooleanExtra("IsDomain", false);
        mStarNameDomain = getIntent().getStringExtra("ToReplaceDomain");
        mStarNameAccount = getIntent().getStringExtra("ToReplaceAccount");

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mIvStep.setImageDrawable(ContextCompat.getDrawable(ReplaceStarNameActivity.this, R.drawable.step_4_img_1));
        mTvStep.setText(getString(R.string.str_replace_starname_step_0));

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);
        mTxType = CONST_PW_TX_REPLACE_STARNAME;

        mPageAdapter = new ReplaceStarNamePageAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mPageAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(ReplaceStarNameActivity.this, R.drawable.step_4_img_1));
                    mTvStep.setText(getString(R.string.str_replace_starname_step_0));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                } else if (i == 1) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(ReplaceStarNameActivity.this, R.drawable.step_4_img_2));
                    mTvStep.setText(getString(R.string.str_replace_starname_step_1));
                } else if (i == 2) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(ReplaceStarNameActivity.this, R.drawable.step_4_img_3));
                    mTvStep.setText(getString(R.string.str_replace_starname_step_2));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                } else if (i == 3) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(ReplaceStarNameActivity.this, R.drawable.step_4_img_4));
                    mTvStep.setText(getString(R.string.str_replace_starname_step_3));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        mViewPager.setCurrentItem(0);

        mRootView.setOnClickListener(v -> onHideKeyboard());

        onShowWaitDialog();
        onFetchData();
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

    public void onFetchData() {
        mTaskCount = 2;
        new StarNameGrpcDomainInfoTask(getBaseApplication(), this, mBaseChain, mStarNameDomain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        if (mIsDomain) {
            new StarNameGrpcResolveTask(getBaseApplication(), this, mBaseChain, "", mStarNameDomain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            new StarNameGrpcResolveTask(getBaseApplication(), this, mBaseChain, mStarNameAccount, mStarNameDomain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    public void onStartReplaceResource() {
        if (getBaseDao().isAutoPass()) {
            ArrayList<Types.Resource> resources = new ArrayList();
            StarnameResourceWrapper wrapper = new StarnameResourceWrapper(mStarNameResources);
            if (wrapper != null) {
                resources = wrapper.array;
            }
            ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = Signer.getGrpcReplaceResourceReq(getAuthResponse(mBaseChain, mAccount), mStarNameDomain, mStarNameAccount, mAccount.address,
                    resources, mTxFee, mTxMemo, getEcKey(mAccount), getBaseDao().getChainIdGrpc());
            onBroadcastGrpcTx(mBaseChain, broadcastTxRequest);

        } else {
            Intent intent = new Intent(ReplaceStarNameActivity.this, PasswordCheckActivity.class);
            intent.putExtra(CONST_PW_PURPOSE, mTxType);
            intent.putExtra("domain", mStarNameDomain);
            intent.putExtra("name", TextUtils.isEmpty(mStarNameAccount) ? "" : mStarNameAccount);
            StarnameResourceWrapper wrapper = new StarnameResourceWrapper(mStarNameResources);
            intent.putExtra("resource", wrapper);
            intent.putExtra("memo", mTxMemo);
            intent.putExtra("fee", mTxFee);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
        }
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        mTaskCount--;
        if (isFinishing()) return;
        if (result.taskType == TASK_GRPC_FETCH_STARNAME_DOMAIN_INFO) {
            if (result.isSuccess && result.resultData != null) {
                mDomain_gRPC = (Types.Domain) result.resultData;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_STARNAME_RESOLVE) {
            if (result.isSuccess && result.resultData != null) {
                mAccountResolve_gRPC = (Types.Account) result.resultData;
            }
        }
        if (mTaskCount == 0) {
            onHideWaitDialog();
            mPageAdapter.mCurrentFragment.onRefreshTab();
        }
    }


    private class ReplaceStarNamePageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public ReplaceStarNamePageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(ReplaceStarName0Fragment.newInstance());
            mFragments.add(StepMemoFragment.newInstance());
            mFragments.add(StepFeeSetFragment.newInstance());
            mFragments.add(ReplaceStarName3Fragment.newInstance());
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
