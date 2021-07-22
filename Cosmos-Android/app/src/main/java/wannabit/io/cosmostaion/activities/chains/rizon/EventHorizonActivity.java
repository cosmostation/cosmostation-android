package wannabit.io.cosmostaion.activities.chains.rizon;

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
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.fragment.chains.rizon.EventHorizonStep0Fragment;
import wannabit.io.cosmostaion.fragment.chains.rizon.EventHorizonStep1Fragment;
import wannabit.io.cosmostaion.fragment.chains.rizon.EventHorizonStep2Fragment;

public class EventHorizonActivity extends BaseBroadCastActivity implements View.OnClickListener{

    private Toolbar                     mToolbar;
    private TextView                    mTitle, mClearAll;
    private ImageView                   mIvStep;
    private TextView                    mTvStep;
    private ViewPager                   mViewPager;
    private HorizonPageAdapter          mPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_horizon);
        mToolbar            = findViewById(R.id.tool_bar);
        mTitle              = findViewById(R.id.toolbar_title);
        mClearAll           = findViewById(R.id.toolbar_clear);
        mIvStep             = findViewById(R.id.send_step);
        mTvStep             = findViewById(R.id.send_step_msg);
        mViewPager          = findViewById(R.id.view_pager);
        mTitle.setText(getString(R.string.str_event_horizon_c));

        mClearAll.setOnClickListener(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mIvStep.setImageDrawable(getDrawable(R.drawable.step_1));
        mTvStep.setText(getString(R.string.str_event_horizon_step_0));

        mPageAdapter = new HorizonPageAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mPageAdapter);;
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

    public void onNextStep() {
        onHideKeyboard();
        if(mViewPager.getCurrentItem() < 3) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
        }
    }

    public void onBeforeStep() {
        onHideKeyboard();
        if(mViewPager.getCurrentItem() > 0) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
        } else {
            onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mClearAll)) {
            return;
        }
    }

    private class HorizonPageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public HorizonPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(EventHorizonStep0Fragment.newInstance(null));
            mFragments.add(EventHorizonStep1Fragment.newInstance(null));
            mFragments.add(EventHorizonStep2Fragment.newInstance(null));
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
