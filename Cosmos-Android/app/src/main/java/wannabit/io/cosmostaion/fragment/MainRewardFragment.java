package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WLog;

public class MainRewardFragment extends BaseFragment {

    public final static int SELECT_All_VALIDATOR_SORTING = 6002;
    public final static int SELECT_MY_VALIDATOR_SORTING = 6003;

    private ViewPager                   mValidatorPager;
    private TabLayout                   mValidatorTapLayer;
    private ValidatorPageAdapter        mPageAdapter;


    public static MainRewardFragment newInstance(Bundle bundle) {
        MainRewardFragment fragment = new MainRewardFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_reward, container, false);
        mValidatorTapLayer = rootView.findViewById(R.id.validator_tab);
        mValidatorPager = rootView.findViewById(R.id.validator_view_pager);

        mPageAdapter = new ValidatorPageAdapter(getChildFragmentManager());
        mValidatorPager.setAdapter(mPageAdapter);
        mValidatorTapLayer.setupWithViewPager(mValidatorPager);
        mValidatorTapLayer.setTabRippleColor(null);

        View tab0 = LayoutInflater.from(getContext()).inflate(R.layout.view_tab_myvalidator, null);
        TextView tabItemText0  = tab0.findViewById(R.id.tabItemText);
        tabItemText0.setText(getString(R.string.str_my_validators) + "(" + getMainActivity().mMyValidators.size() + ")");
        mValidatorTapLayer.getTabAt(0).setCustomView(tab0);

        View tab1 = LayoutInflater.from(getContext()).inflate(R.layout.view_tab_myvalidator, null);
        TextView            tabItemText1  = tab1.findViewById(R.id.tabItemText);
        tabItemText1.setText(getString(R.string.str_top_100_validators)+ "(" + getMainActivity().mTopValidators.size() + ")");
        mValidatorTapLayer.getTabAt(1).setCustomView(tab1);

        View tab2 = LayoutInflater.from(getContext()).inflate(R.layout.view_tab_myvalidator, null);
        TextView            tabItemText2  = tab2.findViewById(R.id.tabItemText);
        tabItemText2.setText(getString(R.string.str_other_validators)+ "(" + getMainActivity().mOtherValidators.size() + ")");
        mValidatorTapLayer.getTabAt(2).setCustomView(tab2);
        mValidatorPager.setOffscreenPageLimit(3);
        mValidatorPager.setCurrentItem(0, false);

        mValidatorPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) { }

            @Override
            public void onPageScrollStateChanged(int i) { }

            @Override
            public void onPageSelected(int i) {
                WLog.w("onPageSelected : " + i);
                mPageAdapter.mFragments.get(i).onRefreshTab();
                getMainActivity().invalidateOptionsMenu();
            }
        });

        return rootView;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if(mValidatorPager.getCurrentItem() == 2) {
            inflater.inflate(R.menu.reward_other, menu);

        } else {
            inflater.inflate(R.menu.reward_menu, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_sorting :
                if(mValidatorPager.getCurrentItem() == 1) {
                    ((ValidatorAllFragment)mPageAdapter.getCurrentFragment()).onShowAllValidatorSort();

                } else if(mValidatorPager.getCurrentItem() == 0){
                    ((ValidatorMyFragment)mPageAdapter.getCurrentFragment()).onShowMyValidatorSort();
                }
                break;
            case R.id.menu_accounts :
                getMainActivity().onShowTopAccountsView();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefreshTab() {
        if(!isAdded()) return;
        mPageAdapter.getCurrentFragment().onRefreshTab();
        onUpdateTab();
    }

    private void onUpdateTab() {
        TabLayout.Tab tab0 = mValidatorTapLayer.getTabAt(0);
        View view0 = tab0.getCustomView();
        TextView tabItemText0 = view0.findViewById(R.id.tabItemText);
        tabItemText0.setText(getString(R.string.str_my_validators) + " (" + getMainActivity().mMyValidators.size() + ")");

        TabLayout.Tab tab1 = mValidatorTapLayer.getTabAt(1);
        View view1 = tab1.getCustomView();
        TextView tabItemText1 = view1.findViewById(R.id.tabItemText);
        tabItemText1.setText(getString(R.string.str_top_100_validators)+ " (" + getMainActivity().mTopValidators.size() + ")");

        TabLayout.Tab tab2 = mValidatorTapLayer.getTabAt(2);
        View view2 = tab2.getCustomView();
        TextView tabItemText2 = view2.findViewById(R.id.tabItemText);
        tabItemText2.setText(getString(R.string.str_other_validators)+ " (" + getMainActivity().mOtherValidators.size() + ")");

    }

    public MainActivity getMainActivity() {
        return (MainActivity)getBaseActivity();
    }

    private class ValidatorPageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public ValidatorPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(ValidatorMyFragment.newInstance(null));
            mFragments.add(ValidatorAllFragment.newInstance(null));
            mFragments.add(ValidatorOtherFragment.newInstance(null));
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