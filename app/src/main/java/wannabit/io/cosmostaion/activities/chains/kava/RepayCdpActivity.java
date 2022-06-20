package wannabit.io.cosmostaion.activities.chains.kava;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_REPAY_CDP;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_KAVA_MY_CDPS;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.math.BigDecimal;
import java.util.ArrayList;

import kava.cdp.v1beta1.Genesis;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.fragment.StepFeeSetFragment;
import wannabit.io.cosmostaion.fragment.StepMemoFragment;
import wannabit.io.cosmostaion.fragment.chains.kava.RepayCdpStep0Fragment;
import wannabit.io.cosmostaion.fragment.chains.kava.RepayCdpStep3Fragment;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.KavaCdpsByOwnerGrpcTask;
import wannabit.io.cosmostaion.utils.WLog;

public class RepayCdpActivity extends BaseBroadCastActivity implements TaskListener {

    private RelativeLayout mRootView;
    private Toolbar mToolbar;
    private TextView mTitle;
    private ImageView mIvStep;
    private TextView mTvStep;
    private ViewPager mViewPager;
    private RepayCdpPageAdapter mPageAdapter;

    private String mMaketId;
    public Genesis.Params mCdpParams;
    public Genesis.CollateralParam mCollateralParam;
    public kava.cdp.v1beta1.QueryOuterClass.CDPResponse mMyCdp;

    public BigDecimal mBeforeLiquidationPrice, mBeforeRiskRate, mAfterLiquidationPrice, mAfterRiskRate, mRemainLoanAmount;

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
        mTitle.setText(getString(R.string.str_repay_cdp_c));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mIvStep.setImageDrawable(ContextCompat.getDrawable(RepayCdpActivity.this, R.drawable.step_4_img_1));
        mTvStep.setText(getString(R.string.str_repay_cdp_step_1));

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mTxType = CONST_PW_TX_REPAY_CDP;

        mCollateralType = getIntent().getStringExtra("collateralParamType");
        mMaketId = getIntent().getStringExtra("marketId");
        mCdpParams = getBaseDao().mCdpParams;
        mCollateralParam = getBaseDao().getCollateralParamByType(mCollateralType);
        if (mCdpParams == null || mCollateralParam == null) {
            WLog.e("ERROR No cdp param data");
            onBackPressed();
            return;
        }

        mPageAdapter = new RepayCdpPageAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mPageAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(RepayCdpActivity.this, R.drawable.step_4_img_1));
                    mTvStep.setText(getString(R.string.str_repay_cdp_step_1));
                } else if (i == 1) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(RepayCdpActivity.this, R.drawable.step_4_img_2));
                    mTvStep.setText(getString(R.string.str_repay_cdp_step_2));
                } else if (i == 2) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(RepayCdpActivity.this, R.drawable.step_4_img_3));
                    mTvStep.setText(getString(R.string.str_repay_cdp_step_3));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                } else if (i == 3) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(RepayCdpActivity.this, R.drawable.step_4_img_4));
                    mTvStep.setText(getString(R.string.str_repay_cdp_step_4));
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
        onFetchCdpInfo();
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

    public void onStartRepayCdp() {
        Intent intent = new Intent(RepayCdpActivity.this, PasswordCheckActivity.class);
        intent.putExtra(BaseConstant.CONST_PW_PURPOSE, CONST_PW_TX_REPAY_CDP);
        intent.putExtra("mPayment", mPayment);
        intent.putExtra("mCollateralType", mCollateralType);
        intent.putExtra("fee", mTxFee);
        intent.putExtra("memo", mTxMemo);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);

    }

    private class RepayCdpPageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public RepayCdpPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(RepayCdpStep0Fragment.newInstance(null));
            mFragments.add(StepMemoFragment.newInstance(null));
            mFragments.add(StepFeeSetFragment.newInstance(null));
            mFragments.add(RepayCdpStep3Fragment.newInstance(null));
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

    private int mTaskCount = 0;

    public void onFetchCdpInfo() {
        onShowWaitDialog();
        mTaskCount = 1;
        new KavaCdpsByOwnerGrpcTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (isFinishing()) return;
        mTaskCount--;
        if (result.taskType == TASK_GRPC_FETCH_KAVA_MY_CDPS) {
            if (result.isSuccess && result.resultData != null) {
                ArrayList<kava.cdp.v1beta1.QueryOuterClass.CDPResponse> myCdps = (ArrayList<kava.cdp.v1beta1.QueryOuterClass.CDPResponse>) result.resultData;
                for (kava.cdp.v1beta1.QueryOuterClass.CDPResponse myCdp : myCdps) {
                    if (myCdp.getType().equalsIgnoreCase(mCollateralType)) {
                        mMyCdp = myCdp;
                        break;
                    }
                }
            }
        }

        if (mTaskCount == 0) {
            onHideWaitDialog();
            if (mCdpParams == null || mMyCdp == null) {
                Toast.makeText(getBaseContext(), getString(R.string.str_network_error_title), Toast.LENGTH_SHORT).show();
                onBackPressed();
                return;
            }
            mPageAdapter.mCurrentFragment.onRefreshTab();
        }
    }

    public BigDecimal getKavaOraclePrice() {
        BigDecimal price = BigDecimal.ZERO;
        if (getBaseDao().mKavaTokenPrice != null) {
            price = getBaseDao().getKavaOraclePrice(mMaketId);
        }
        return price;
    }
}
