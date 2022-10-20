package wannabit.io.cosmostaion.activities.txs.kava;

import static wannabit.io.cosmostaion.base.BaseConstant.BASE_GAS_AMOUNT;
import static wannabit.io.cosmostaion.base.BaseConstant.FEE_BNB_SEND;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.fragment.txs.kava.HtlcSendStep0Fragment;
import wannabit.io.cosmostaion.fragment.txs.kava.HtlcSendStep1Fragment;
import wannabit.io.cosmostaion.fragment.txs.kava.HtlcSendStep2Fragment;
import wannabit.io.cosmostaion.fragment.txs.kava.HtlcSendStep3Fragment;
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

    public String mToSwapDenom;
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

    public ChainConfig mToChainConfig;

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
        mTitle.setText(getString(R.string.str_htlc_send_c));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);
        mToSwapDenom = getIntent().getStringExtra("toSwapDenom");

        mIvStep.setImageDrawable(ContextCompat.getDrawable(HtlcSendActivity.this, R.drawable.step_4_img_1));
        mTvStep.setText(getString(R.string.str_htlc_send_step_1));

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
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(HtlcSendActivity.this, R.drawable.step_4_img_1));
                    mTvStep.setText(getString(R.string.str_htlc_send_step_1));
                } else if (i == 1) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(HtlcSendActivity.this, R.drawable.step_4_img_2));
                    mTvStep.setText(getString(R.string.str_htlc_send_step_2));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                } else if (i == 2) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(HtlcSendActivity.this, R.drawable.step_4_img_3));
                    mTvStep.setText(getString(R.string.str_htlc_send_step_3));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                } else if (i == 3) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(HtlcSendActivity.this, R.drawable.step_4_img_4));
                    mTvStep.setText(getString(R.string.str_tx_step_confirm));
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
        if (mViewPager.getCurrentItem() < mPageAdapter.getCount() - 1) {
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

    public Fee onInitSendFee() {
        if (mBaseChain.equals(BaseChain.BNB_MAIN)) {
            Coin gasCoin = new Coin(mChainConfig.mainDenom(), FEE_BNB_SEND);
            ArrayList<Coin> gasCoins = new ArrayList<>();
            gasCoins.add(gasCoin);
            mSendFee = new Fee("", gasCoins);

        } else if (mBaseChain.equals(BaseChain.KAVA_MAIN)) {
            Coin gasCoin = new Coin(mChainConfig.mainDenom(), "12500");
            ArrayList<Coin> gasCoins = new ArrayList<>();
            gasCoins.add(gasCoin);
            mSendFee = new Fee(BASE_GAS_AMOUNT, gasCoins);
        }
        return mSendFee;
    }

    public Fee onInitClaimFee() {
        mToChainConfig = ChainFactory.getChain(mRecipientChain);
        if (mRecipientChain.equals(BaseChain.BNB_MAIN)) {
            Coin gasCoin = new Coin(mToChainConfig.mainDenom(), FEE_BNB_SEND);
            ArrayList<Coin> gasCoins = new ArrayList<>();
            gasCoins.add(gasCoin);
            mClaimFee = new Fee("", gasCoins);

        } else if (mRecipientChain.equals(BaseChain.KAVA_MAIN)) {
            Coin gasCoin = new Coin(mToChainConfig.mainDenom(), "12500");
            ArrayList<Coin> gasCoins = new ArrayList<>();
            gasCoins.add(gasCoin);
            mClaimFee = new Fee(BASE_GAS_AMOUNT, gasCoins);
        }
        return mClaimFee;
    }

    public void onStartHtlcSend() {
        if (getBaseDao().isAutoPass()) {
            onIntentHtlcResult();
        } else {
            Intent intent = new Intent(HtlcSendActivity.this, PasswordCheckActivity.class);
            activityResultLauncher.launch(intent);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
        }
    }

    private void onIntentHtlcResult() {
        Intent intent = new Intent(HtlcSendActivity.this, HtlcResultActivity.class);
        intent.putExtra("toChain", mRecipientChain.getChain());
        intent.putExtra("recipientId", "" + mRecipientAccount.id);
        intent.putParcelableArrayListExtra("amount", mToSendCoins);
        intent.putExtra("sendFee", mSendFee);
        intent.putExtra("claimFee", mClaimFee);
        startActivity(intent);
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            onShowWaitDialog();
            onIntentHtlcResult();
        }
    });

    private class HtlcSendPageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public HtlcSendPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(HtlcSendStep0Fragment.newInstance());
            mFragments.add(HtlcSendStep1Fragment.newInstance());
            mFragments.add(HtlcSendStep2Fragment.newInstance());
            mFragments.add(HtlcSendStep3Fragment.newInstance());
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

    public BigDecimal getAvailable() {
        if (mBaseChain.equals(BaseChain.KAVA_MAIN)) {
            return getBaseDao().getAvailable(mToSwapDenom);
        } else {
            return mAccount.getTokenBalance(mToSwapDenom);
        }
    }
}
