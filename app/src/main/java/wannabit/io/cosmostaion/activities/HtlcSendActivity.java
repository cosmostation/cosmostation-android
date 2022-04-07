package wannabit.io.cosmostaion.activities;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.FEE_BNB_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_GAS_AMOUNT_BEP3;

import android.app.Activity;
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

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.IRefreshTabListener;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.fragment.HtlcSendStep0Fragment;
import wannabit.io.cosmostaion.fragment.HtlcSendStep1Fragment;
import wannabit.io.cosmostaion.fragment.HtlcSendStep2Fragment;
import wannabit.io.cosmostaion.fragment.HtlcSendStep3Fragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.network.res.ResKavaBep3Param;
import wannabit.io.cosmostaion.network.res.ResKavaSwapSupply;

public class HtlcSendActivity extends BaseActivity {

    private RelativeLayout mRootView;
    private Toolbar mToolbar;
    private TextView mTitle;
    private ImageView mIvStep;
    private TextView mTvStep;
    private ViewPager mViewPager;
    private HtlcSendPageAdapter mPageAdapter;

    public String mToSwapDenom = BNB_MAIN.getMainDenom();
    public ArrayList<Coin> mToSendCoins;
    public BaseChain mRecipientChain;
    public Account mRecipientAccount;
    public Fee mSendFee;
    public Fee mClaimFee;

    public BigDecimal mTotalCap = BigDecimal.ZERO;
    public BigDecimal mRemainCap = BigDecimal.ZERO;
    public BigDecimal mMaxOnce = BigDecimal.ZERO;

    public ResKavaBep3Param mKavaBep3Param2;
    public ResKavaSwapSupply mKavaSuppies2;

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
        mTitle.setText(R.string.str_htlc_send_c);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        account = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        baseChain = BaseChain.getChain(account.baseChain);
        mToSwapDenom = getIntent().getStringExtra("toSwapDenom");

        mIvStep.setImageResource(R.drawable.step_4_img_1);
        mTvStep.setText(R.string.str_htlc_send_step_1);

        mPageAdapter = new HtlcSendPageAdapter(getSupportFragmentManager());
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
                    mTvStep.setText(R.string.str_htlc_send_step_1);
                } else if (i == 1) {
                    mIvStep.setImageResource(R.drawable.step_4_img_2);
                    mTvStep.setText(R.string.str_htlc_send_step_2);
                    ((IRefreshTabListener) mPageAdapter.mCurrentFragment).onRefreshTab();
                } else if (i == 2) {
                    mIvStep.setImageResource(R.drawable.step_4_img_3);
                    mTvStep.setText(R.string.str_htlc_send_step_3);
                    ((IRefreshTabListener) mPageAdapter.mCurrentFragment).onRefreshTab();
                } else if (i == 3) {
                    mIvStep.setImageResource(R.drawable.step_4_img_4);
                    mTvStep.setText(R.string.str_htlc_send_step_4);
                    ((IRefreshTabListener) mPageAdapter.mCurrentFragment).onRefreshTab();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        mViewPager.setCurrentItem(0);

        mRootView.setOnClickListener(v -> ActivityExtensionsKt.hideKeyboard(this));
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


    public Fee onInitSendFee() {
        if (baseChain.equals(BNB_MAIN)) {
            Coin gasCoin = new Coin();
            gasCoin.denom = baseChain.getMainDenom();
            gasCoin.amount = FEE_BNB_SEND;
            ArrayList<Coin> gasCoins = new ArrayList<>();
            gasCoins.add(gasCoin);
            mSendFee = new Fee("", gasCoins);

        } else if (baseChain.equals(BaseChain.KAVA_MAIN)) {
            Coin gasCoin = new Coin();
            gasCoin.denom = baseChain.getMainDenom();
            gasCoin.amount = "12500";
            ArrayList<Coin> gasCoins = new ArrayList<>();
            gasCoins.add(gasCoin);
            mSendFee = new Fee(KAVA_GAS_AMOUNT_BEP3, gasCoins);
        }
        return mSendFee;
    }


    public Fee onInitClaimFee() {
        if (mRecipientChain.equals(BNB_MAIN)) {
            Coin gasCoin = new Coin();
            gasCoin.denom = mRecipientChain.getMainDenom();
            gasCoin.amount = FEE_BNB_SEND;
            ArrayList<Coin> gasCoins = new ArrayList<>();
            gasCoins.add(gasCoin);
            mClaimFee = new Fee("", gasCoins);

        } else if (mRecipientChain.equals(BaseChain.KAVA_MAIN)) {
            Coin gasCoin = new Coin();
            gasCoin.denom = mRecipientChain.getMainDenom();
            gasCoin.amount = "12500";
            ArrayList<Coin> gasCoins = new ArrayList<>();
            gasCoins.add(gasCoin);
            mClaimFee = new Fee(KAVA_GAS_AMOUNT_BEP3, gasCoins);
        }
        return mClaimFee;
    }

    public void onStartHtlcSend() {
        Intent intent = new Intent(HtlcSendActivity.this, PasswordCheckActivity.class);
        intent.putExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_SIMPLE_CHECK);
        startActivityForResult(intent, BaseConstant.CONST_PW_SIMPLE_CHECK);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
    }


    private class HtlcSendPageAdapter extends FragmentPagerAdapter {

        private final ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public HtlcSendPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(HtlcSendStep0Fragment.newInstance(null));
            mFragments.add(HtlcSendStep1Fragment.newInstance(null));
            mFragments.add(HtlcSendStep2Fragment.newInstance(null));
            mFragments.add(HtlcSendStep3Fragment.newInstance(null));
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BaseConstant.CONST_PW_SIMPLE_CHECK && resultCode == Activity.RESULT_OK) {
            Intent intent = new Intent(HtlcSendActivity.this, HtlcResultActivity.class);
            intent.putExtra("toChain", mRecipientChain.getChain());
            intent.putExtra("recipientId", "" + mRecipientAccount.id);
            intent.putParcelableArrayListExtra("amount", mToSendCoins);
            intent.putExtra("sendFee", mSendFee);
            intent.putExtra("claimFee", mClaimFee);
            startActivity(intent);
        }
    }

    public BigDecimal getAvailable() {
        if (baseChain.equals(BaseChain.KAVA_MAIN)) {
            return getBaseDao().getAvailable(mToSwapDenom);
        } else {
            return account.getTokenBalance(mToSwapDenom);
        }
    }
}
