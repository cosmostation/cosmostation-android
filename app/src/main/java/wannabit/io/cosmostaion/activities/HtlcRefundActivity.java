package wannabit.io.cosmostaion.activities;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_HTLS_REFUND;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.fulldive.wallet.extensions.ActivityExtensionsKt;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.IRefreshTabListener;
import wannabit.io.cosmostaion.fragment.HtlcRefundStep0Fragment;
import wannabit.io.cosmostaion.fragment.HtlcRefundStep3Fragment;
import wannabit.io.cosmostaion.fragment.StepFeeSetOldFragment;
import wannabit.io.cosmostaion.fragment.StepMemoFragment;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResBnbSwapInfo;
import wannabit.io.cosmostaion.network.res.ResKavaSwapInfo;
import wannabit.io.cosmostaion.utils.WLog;

public class HtlcRefundActivity extends BaseBroadCastActivity {

    private RelativeLayout mRootView;
    private Toolbar mToolbar;
    private TextView mTitle;
    private ImageView mIvStep;
    private TextView mTvStep;
    private ViewPager mViewPager;
    private HtlcRefundPageAdapter mPageAdapter;

    public ResKavaSwapInfo mResKavaSwapInfo;
    public ResBnbSwapInfo mResBnbSwapInfo;
    public String mSwapId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        mRootView = findViewById(R.id.root_view);
        mToolbar = findViewById(R.id.toolbar);
        mTitle = findViewById(R.id.toolbar_title);
        mIvStep = findViewById(R.id.send_step);
        mTvStep = findViewById(R.id.send_step_msg);
        mViewPager = findViewById(R.id.view_pager);
        mTitle.setText(R.string.str_htlc_refund_c);

        mSwapId = getIntent().getStringExtra("swapId");

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mIvStep.setImageResource(R.drawable.step_4_img_1);
        mTvStep.setText(R.string.str_htlc_refund_step_0);

        account = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        baseChain = BaseChain.getChain(account.baseChain);
        mTxType = CONST_PW_TX_HTLS_REFUND;

        mPageAdapter = new HtlcRefundPageAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mPageAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    mIvStep.setImageResource(R.drawable.step_4_img_1);
                    mTvStep.setText(R.string.str_htlc_refund_step_0);
                } else if (i == 1) {
                    mIvStep.setImageResource(R.drawable.step_4_img_2);
                    mTvStep.setText(R.string.str_htlc_refund_step_1);
                } else if (i == 2) {
                    mIvStep.setImageResource(R.drawable.step_4_img_3);
                    mTvStep.setText(R.string.str_htlc_refund_step_2);
                    ((IRefreshTabListener) mPageAdapter.mCurrentFragment).onRefreshTab();
                } else if (i == 3) {
                    mIvStep.setImageResource(R.drawable.step_4_img_4);
                    mTvStep.setText(R.string.str_htlc_refund_step_3);
                    ((IRefreshTabListener) mPageAdapter.mCurrentFragment).onRefreshTab();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        mViewPager.setCurrentItem(0);

        mRootView.setOnClickListener(v -> ActivityExtensionsKt.hideKeyboard(this));
        showWaitDialog();
        onFetchHtlcStatus(mSwapId);
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
        ActivityExtensionsKt.hideKeyboard(this);
        if (mViewPager.getCurrentItem() > 0) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
        } else {
            super.onBackPressed();
        }
    }

    public void onNextStep() {
        if (mViewPager.getCurrentItem() < mViewPager.getChildCount()) {
            ActivityExtensionsKt.hideKeyboard(this);
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
        }
    }

    public void onBeforeStep() {
        if (mViewPager.getCurrentItem() > 0) {
            ActivityExtensionsKt.hideKeyboard(this);
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
        } else {
            onBackPressed();
        }
    }


    public void onStartHtlcRefund() {
        Intent intent = new Intent(HtlcRefundActivity.this, PasswordCheckActivity.class);
        intent.putExtra(BaseConstant.CONST_PW_PURPOSE, CONST_PW_TX_HTLS_REFUND);
        intent.putExtra("swapId", mSwapId);
        intent.putExtra("fee", mTxFee);
        intent.putExtra("memo", mTxMemo);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);

    }


    private class HtlcRefundPageAdapter extends FragmentPagerAdapter {

        private final ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public HtlcRefundPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(HtlcRefundStep0Fragment.newInstance(null));
            mFragments.add(StepMemoFragment.newInstance(null));
            mFragments.add(StepFeeSetOldFragment.newInstance(null));
            mFragments.add(HtlcRefundStep3Fragment.newInstance(null));
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


    private void onFetchHtlcStatus(String swapId) {
        if (baseChain.equals(BaseChain.KAVA_MAIN)) {
            ApiClient.getKavaChain(getBaseContext()).getSwapById(swapId).enqueue(new Callback<ResKavaSwapInfo>() {
                @Override
                public void onResponse(Call<ResKavaSwapInfo> call, Response<ResKavaSwapInfo> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        hideWaitDialog();
                        mResKavaSwapInfo = response.body();
                        ((IRefreshTabListener) mPageAdapter.mCurrentFragment).onRefreshTab();
                    } else {
                        onBackPressed();
                    }
                }

                @Override
                public void onFailure(Call<ResKavaSwapInfo> call, Throwable t) {
                    WLog.w("onFetchHtlcStatus " + t.getMessage());
                    onBackPressed();
                }
            });

        } else if (baseChain.equals(BaseChain.BNB_MAIN)) {
            ApiClient.getBnbChain(getBaseContext()).getSwapById(swapId).enqueue(new Callback<ResBnbSwapInfo>() {
                @Override
                public void onResponse(Call<ResBnbSwapInfo> call, Response<ResBnbSwapInfo> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        hideWaitDialog();
                        mResBnbSwapInfo = response.body();
                        ((IRefreshTabListener) mPageAdapter.mCurrentFragment).onRefreshTab();
                    } else {
                        onBackPressed();
                    }
                }

                @Override
                public void onFailure(Call<ResBnbSwapInfo> call, Throwable t) {
                    WLog.w("onFetchHtlcStatus " + t.getMessage());
                    onBackPressed();
                }
            });
        }
    }
}
