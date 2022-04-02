package wannabit.io.cosmostaion.activities.chains.rizon;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_RIZON_SWAP;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.IRefreshTabListener;
import wannabit.io.cosmostaion.fragment.chains.rizon.EventHorizonStep0Fragment;
import wannabit.io.cosmostaion.fragment.chains.rizon.EventHorizonStep1Fragment;


public class EventHorizonActivity extends BaseBroadCastActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView mTitle, mClearAll;
    private ImageView mIvStep;
    private TextView mTvStep;
    private ViewPager mViewPager;
    private HorizonPageAdapter mPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_horizon);
        mToolbar = findViewById(R.id.tool_bar);
        mTitle = findViewById(R.id.toolbar_title);
        mClearAll = findViewById(R.id.toolbar_clear);
        mIvStep = findViewById(R.id.send_step);
        mTvStep = findViewById(R.id.send_step_msg);
        mViewPager = findViewById(R.id.view_pager);
        mTitle.setText(getString(R.string.str_event_horizon_c));

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);

        mClearAll.setOnClickListener(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPageAdapter = new HorizonPageAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setAdapter(mPageAdapter);
        ;

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    mIvStep.setImageDrawable(getDrawable(R.drawable.step_1));
                    mTvStep.setText(getString(R.string.str_event_horizon_step_0));
                    mClearAll.setVisibility(View.VISIBLE);
                } else if (i == 1) {
                    mIvStep.setImageDrawable(getDrawable(R.drawable.step_2));
                    mTvStep.setText(getString(R.string.str_event_horizon_step_1));
                    mClearAll.setVisibility(View.GONE);
                    ((IRefreshTabListener) mPageAdapter.mCurrentFragment).onRefreshTab();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
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

    @Override
    public void onClick(View v) {
        if (v.equals(mClearAll)) {
            EventHorizonStep0Fragment.onClearAll();
            return;
        }
    }

    public void onStartSwap(String hdacTxString) {
        Intent intent = new Intent(EventHorizonActivity.this, PasswordCheckActivity.class);
        intent.putExtra(BaseConstant.CONST_PW_PURPOSE, CONST_PW_TX_RIZON_SWAP);
        intent.putExtra("hdacBurnRawTx", hdacTxString);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
    }

    private class HorizonPageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public HorizonPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(EventHorizonStep0Fragment.newInstance(null));
            mFragments.add(EventHorizonStep1Fragment.newInstance(null));
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
