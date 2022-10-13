package wannabit.io.cosmostaion.activities.txs.starname;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_DELETE_ACCOUNT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_DELETE_DOMAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.IOV_MSG_TYPE_DELETE_DOMAIN;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.activities.TxDetailgRPCActivity;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.fragment.StepFeeSetFragment;
import wannabit.io.cosmostaion.fragment.StepMemoFragment;
import wannabit.io.cosmostaion.fragment.txs.starname.DeleteStarName0Fragment;
import wannabit.io.cosmostaion.fragment.txs.starname.DeleteStarName3Fragment;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.DeleteStarnameGrpcTask;

public class DeleteStarNameActivity extends BaseBroadCastActivity {

    private RelativeLayout mRootView;
    private Toolbar mToolbar;
    private TextView mTitle;
    private ImageView mIvStep;
    private TextView mTvStep;
    private ViewPager mViewPager;

    private DeleteStarNamePageAdapter mPageAdapter;

    public long mValidTime;

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

        mStarNameDomainType = getIntent().getStringExtra("ToDeleType");
        mStarNameDomain = getIntent().getStringExtra("ToDeleDomain");
        mStarNameAccount = getIntent().getStringExtra("ToDeleAccount");
        mValidTime = getIntent().getLongExtra("Time", -1);

        if (mStarNameDomainType.equals(IOV_MSG_TYPE_DELETE_DOMAIN)) {
            mTxType = CONST_PW_TX_DELETE_DOMAIN;
            mTitle.setText(getString(R.string.str_delete_domain));
        } else {
            mTxType = CONST_PW_TX_DELETE_ACCOUNT;
            mTitle.setText(getString(R.string.str_delete_account));
        }

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mIvStep.setImageDrawable(ContextCompat.getDrawable(DeleteStarNameActivity.this, R.drawable.step_4_img_1));
        mTvStep.setText(getString(R.string.str_register_domain_step_0));

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);

        mPageAdapter = new DeleteStarNamePageAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mPageAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(DeleteStarNameActivity.this, R.drawable.step_4_img_1));
                    mTvStep.setText(getString(R.string.str_delete_starname_step_0));
                } else if (i == 1) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(DeleteStarNameActivity.this, R.drawable.step_4_img_2));
                    mTvStep.setText(getString(R.string.str_delete_starname_step_1));
                } else if (i == 2) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(DeleteStarNameActivity.this, R.drawable.step_4_img_3));
                    mTvStep.setText(getString(R.string.str_delete_starname_step_2));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                } else if (i == 3) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(DeleteStarNameActivity.this, R.drawable.step_4_img_4));
                    mTvStep.setText(getString(R.string.str_delete_starname_step_3));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        mViewPager.setCurrentItem(0);

        mRootView.setOnClickListener(v -> onHideKeyboard());

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

    public void onDeleteStarName() {
        if (getBaseDao().isAutoPass()) {
            onBroadCastTx();
        } else {
            Intent intent = new Intent(DeleteStarNameActivity.this, PasswordCheckActivity.class);
            activityResultLauncher.launch(intent);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
        }
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            onShowWaitDialog();
            onBroadCastTx();
        }
    });

    private void onBroadCastTx() {
        new DeleteStarnameGrpcTask(getBaseApplication(), new TaskListener() {
            @Override
            public void onTaskResponse(TaskResult result) {
                onTxIntent(result);
            }
        }, mAccount, mBaseChain, mStarNameDomain, mStarNameAccount, mTxMemo, mTxFee, getBaseDao().getChainIdGrpc(), mStarNameDomainType).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void onTxIntent(TaskResult result) {
        Intent txIntent = new Intent(DeleteStarNameActivity.this, TxDetailgRPCActivity.class);
        txIntent.putExtra("isGen", true);
        txIntent.putExtra("isSuccess", result.isSuccess);
        txIntent.putExtra("errorCode", result.errorCode);
        txIntent.putExtra("errorMsg", result.errorMsg);
        String hash = String.valueOf(result.resultData);
        if (!TextUtils.isEmpty(hash)) txIntent.putExtra("txHash", hash);
        startActivity(txIntent);
    }

    private class DeleteStarNamePageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public DeleteStarNamePageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(DeleteStarName0Fragment.newInstance());
            mFragments.add(StepMemoFragment.newInstance());
            mFragments.add(StepFeeSetFragment.newInstance());
            mFragments.add(DeleteStarName3Fragment.newInstance());
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
