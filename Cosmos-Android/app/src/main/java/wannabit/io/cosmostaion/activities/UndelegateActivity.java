package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.BondingState;
import wannabit.io.cosmostaion.dialog.Dialog_VestingAccount;
import wannabit.io.cosmostaion.fragment.UndelegateStep0Fragment;
import wannabit.io.cosmostaion.fragment.UndelegateStep1Fragment;
import wannabit.io.cosmostaion.fragment.UndelegateStep2Fragment;
import wannabit.io.cosmostaion.fragment.UndelegateStep3Fragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;

public class UndelegateActivity extends BaseActivity {

    private ImageView                   mChainBg;
    private Toolbar                     mToolbar;
    private TextView                    mTitle;
    private ImageView                   mIvStep;
    private TextView                    mTvStep;
    private ViewPager                   mViewPager;
    private UndelegatePageAdapter       mPageAdapter;

    public Validator                    mValidator;
    public BondingState                 mBondingState;
    public Coin                         mUnDelegateAmount;
    public String                       mUnDelegateMemo;
    public Fee                          mUnDelegateFee;
    public String                       mUnDelegateShare;

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
        mTitle.setText(getString(R.string.str_undelegate_c));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mIvStep.setImageDrawable(getDrawable(R.drawable.step_4_img_1));
        mTvStep.setText(getString(R.string.str_undelegate_step_1));

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);

        mValidator = getIntent().getParcelableExtra("validator");
        mBondingState = getBaseDao().onSelectBondingState(mAccount.id, mValidator.operator_address);

        mPageAdapter = new UndelegatePageAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mPageAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) { }

            @Override
            public void onPageSelected(int i) {
                if(i == 0) {
                    mIvStep.setImageDrawable(getDrawable(R.drawable.step_4_img_1));
                    mTvStep.setText(getString(R.string.str_undelegate_step_1));
                } else if (i == 1 ) {
                    mIvStep.setImageDrawable(getDrawable(R.drawable.step_4_img_2));
                    mTvStep.setText(getString(R.string.str_delegate_step_2));

                } else if (i == 2 ) {
                    mIvStep.setImageDrawable(getDrawable(R.drawable.step_4_img_3));
                    mTvStep.setText(getString(R.string.str_undelegate_step_3));
                    mPageAdapter.mCurrentFragment.onRefreshTab();

                } else if (i == 3 ) {
                    mIvStep.setImageDrawable(getDrawable(R.drawable.step_4_img_4));
                    mTvStep.setText(getString(R.string.str_undelegate_step_4));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) { }
        });
        mViewPager.setCurrentItem(0);

        if (mBaseChain.equals(KAVA_MAIN) && (WDp.getVestedCoin(mAccount.balances, TOKEN_KAVA).compareTo(BigDecimal.ZERO) > 0)) {
            Dialog_VestingAccount dialog = Dialog_VestingAccount.newInstance(null);
            dialog.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();
        }
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
        if(mViewPager.getCurrentItem() > 0) {
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

    public void onStartUndelegate() {
        Intent intent = new Intent(UndelegateActivity.this, PasswordCheckActivity.class);
        intent.putExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_TX_SIMPLE_UNDELEGATE);
        intent.putExtra("toAddress", mValidator.operator_address);
        if (mBaseChain.equals(COSMOS_MAIN) || mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST) ||
                mBaseChain.equals(BAND_MAIN) || mBaseChain.equals(IOV_MAIN) || mBaseChain.equals(IOV_TEST) ||
                mBaseChain.equals(CERTIK_MAIN) || mBaseChain.equals(CERTIK_TEST) || mBaseChain.equals(AKASH_MAIN) || mBaseChain.equals(SECRET_MAIN)) {
            intent.putExtra("uAmount", mUnDelegateAmount);
        } else if  (mBaseChain.equals(IRIS_MAIN)) {
            BigDecimal validatorShareRate = new BigDecimal(mValidator.delegator_shares).divide(new BigDecimal(mValidator.tokens), 18, BigDecimal.ROUND_HALF_DOWN);
            mUnDelegateAmount.amount = validatorShareRate.multiply(new BigDecimal(mUnDelegateAmount.amount)).setScale(0, RoundingMode.DOWN).toPlainString();
            intent.putExtra("uAmount", mUnDelegateAmount);
        }

        intent.putExtra("memo", mUnDelegateMemo);
        intent.putExtra("fee", mUnDelegateFee);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);

    }


    private class UndelegatePageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public UndelegatePageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(UndelegateStep0Fragment.newInstance(null));
            mFragments.add(UndelegateStep1Fragment.newInstance(null));
            mFragments.add(UndelegateStep2Fragment.newInstance(null));
            mFragments.add(UndelegateStep3Fragment.newInstance(null));
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
