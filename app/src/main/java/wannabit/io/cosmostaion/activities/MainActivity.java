package wannabit.io.cosmostaion.activities;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.zxing.client.android.Intents;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.kava.ClaimIncentiveActivity;
import wannabit.io.cosmostaion.activities.txs.wc.ConnectWalletActivity;
import wannabit.io.cosmostaion.activities.txs.wc.WalletConnectActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.base.chains.Kava;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.ChainAccounts;
import wannabit.io.cosmostaion.fragment.DappFragment;
import wannabit.io.cosmostaion.fragment.MainHistoryFragment;
import wannabit.io.cosmostaion.fragment.MainSendFragment;
import wannabit.io.cosmostaion.fragment.MainSettingFragment;
import wannabit.io.cosmostaion.fragment.MainTokensFragment;
import wannabit.io.cosmostaion.utils.FetchCallBack;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.FadePageTransformer;
import wannabit.io.cosmostaion.widget.StopViewPager;
import wannabit.io.cosmostaion.widget.TintableImageView;

public class MainActivity extends BaseActivity implements FetchCallBack {

    private Toolbar mToolbar;
    private ImageView mToolbarChainImg;
    private TextView mToolbarTitle;

    private StopViewPager mContentsPager;
    private TabLayout mTabLayer;
    public MainViewPageAdapter mPageAdapter;
    public FloatingActionButton mFloatBtn;

    private BaseChain mSelectedChain;
    private ArrayList<BaseChain> mExpendedChains = new ArrayList<>();
    private ArrayList<ChainAccounts> mChainAccounts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.tool_bar);
        mToolbarTitle = findViewById(R.id.toolbar_title);
        mToolbarChainImg = findViewById(R.id.toolbar_net_image);
        mContentsPager = findViewById(R.id.view_pager);
        mTabLayer = findViewById(R.id.bottom_tab);
        mFloatBtn = findViewById(R.id.btn_floating);

        mFloatBtn.setOnClickListener(v -> onStartSendMainDenom());

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mPageAdapter = new MainViewPageAdapter(getSupportFragmentManager());
        mContentsPager.setPageTransformer(false, new FadePageTransformer());
        mContentsPager.setOffscreenPageLimit(5);
        mContentsPager.setAdapter(mPageAdapter);
        mTabLayer.setupWithViewPager(mContentsPager);
        mTabLayer.setTabRippleColor(null);

        createTab(R.drawable.wallet_ic, R.string.str_main_wallet, 0);
        createTab(R.drawable.tokens_ic, R.string.str_main_tokens, 1);
        createTab(R.drawable.ts_ic, R.string.str_main_history, 2);
        createDappTab(R.string.str_main_dapp, 3);
        createTab(R.drawable.setting_ic, R.string.str_main_set, 4);

        mContentsPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }

            @Override
            public void onPageSelected(int position) {
                if (mPageAdapter != null && mPageAdapter.mCurrentFragment != null) {
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                }
                if (position != 0) mFloatBtn.hide();
                else if (!mFloatBtn.isShown()) mFloatBtn.show();
            }
        });

        mContentsPager.setCurrentItem(getIntent().getIntExtra("page", 0), false);
    }

    private void createTab(int iconResourceId, int stringResourceId, int index) {
        View tab = LayoutInflater.from(this).inflate(R.layout.view_tab_item, null);
        TintableImageView icon = tab.findViewById(R.id.tabItemIcon);
        TextView titleText = tab.findViewById(R.id.tabItemText);
        icon.setImageResource(iconResourceId);
        titleText.setText(stringResourceId);
        mTabLayer.getTabAt(index).setCustomView(tab);
    }

    private void createDappTab(int stringResourceId, int index) {
        View tab = LayoutInflater.from(this).inflate(R.layout.view_tab_item_dapp, null);
        TextView titleText = tab.findViewById(R.id.tabItemText);
        titleText.setText(stringResourceId);
        mTabLayer.getTabAt(index).setCustomView(tab);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_accounts:
                onClickSwitchWallet();
                break;
            case R.id.menu_explorer:
                onExplorerView();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onAccountSwitched();
        onChainSelect(mBaseChain);
    }

    public void onAccountSwitched() {
        boolean needFetch = false;
        Account supportedAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());

        if (mAccount == null) {
            needFetch = true;
        } else if (!String.valueOf(supportedAccount.id).equals(getBaseDao().getLastUser())) {
            getBaseDao().setLastUser(supportedAccount.id);
            needFetch = true;
        } else if (!getBaseDao().getLastUser().equals(String.valueOf(mAccount.id))) {
            needFetch = true;
        }

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);

        if (needFetch) {
            onShowWaitDialog();
            onFetchAllData();

            mToolbarChainImg.setImageResource(mChainConfig.chainImg());
            mFloatBtn.setBackgroundTintList(ContextCompat.getColorStateList(this, mChainConfig.sendBgColor()));
            mFloatBtn.setImageTintList(ContextCompat.getColorStateList(this, mChainConfig.sendImgColor()));

            mSelectedChain = mBaseChain;
            onChainSelect(mSelectedChain);
        }

        if (TextUtils.isEmpty(mAccount.nickName))
            mToolbarTitle.setText(getString(R.string.str_my_wallet) + mAccount.id);
        else mToolbarTitle.setText(mAccount.nickName);

        if (mPageAdapter.mCurrentFragment != null) {
            mPageAdapter.getCurrentFragment().onRefreshTab();
        }
    }

    private void onChainSelect(BaseChain baseChain) {
        invalidateOptionsMenu();
        mChainAccounts.clear();
        ArrayList<BaseChain> mDisplayChains = new ArrayList<>();
        mDisplayChains = getBaseDao().dpSortedChains();
        mExpendedChains = getBaseDao().getExpendedChains();
        mSelectedChain = baseChain;
        getBaseDao().setLastChain(mSelectedChain.getChain());

        for (BaseChain chain : mDisplayChains) {
            if (mExpendedChains.contains(chain) || mSelectedChain.equals(chain)) {
                mChainAccounts.add(new ChainAccounts(true, chain, getBaseDao().onSelectAccountsByChain(chain)));
            } else {
                mChainAccounts.add(new ChainAccounts(false, chain, getBaseDao().onSelectAccountsByChain(chain)));
            }
        }
    }

    public void onClickSwitchWallet() {
        startActivity(new Intent(this, WalletSwitchActivity.class));
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
        onChainSelect(mSelectedChain);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void onExplorerView() {
        String url = mChainConfig.explorerAccountLink() + mAccount.address;
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    public void onFetchAllData() {
        onFetchAccountInfo(this);
    }

    public void onStartWalletConnect(String wcUrl) {
        Intent intent = new Intent(this, PasswordCheckActivity.class);
        intent.putExtra("wcUrl", wcUrl);
        walletConnectResultLauncher.launch(intent);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
    }

    @Override
    public void fetchFinished() {
        if (!isFinishing()) {
            onHideWaitDialog();
            if (mPageAdapter.mCurrentFragment != null) mPageAdapter.mCurrentFragment.onRefreshTab();
            if (mPageAdapter.getItem(0) != null && mPageAdapter.mCurrentFragment != mPageAdapter.getItem(0))
                ((MainViewPageAdapter) mContentsPager.getAdapter()).getItem(0).onRefreshTab();
            if (mPageAdapter.getItem(1) != null && mPageAdapter.mCurrentFragment != mPageAdapter.getItem(1))
                ((MainViewPageAdapter) mContentsPager.getAdapter()).getItem(1).onRefreshTab();
            if (mPageAdapter.getItem(2) != null && mPageAdapter.mCurrentFragment != mPageAdapter.getItem(2))
                ((MainViewPageAdapter) mContentsPager.getAdapter()).getItem(2).onRefreshTab();
        }
    }

    @Override
    public void fetchBusy() {
        if (!isFinishing()) {
            onHideWaitDialog();
            if (mPageAdapter.mCurrentFragment != null) mPageAdapter.mCurrentFragment.onBusyFetch();
        }
    }

    public void onClickIncentive() {
        if (!mAccount.hasPrivateKey) {
            onInsertKeyDialog();
            return;
        }
        if (!WDp.isTxFeePayable(this, getBaseDao(), mChainConfig)) {
            Toast.makeText(this, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
            return;
        }

        if (getBaseDao().mIncentiveRewards.getRewardSum(mChainConfig.mainDenom()) == BigDecimal.ZERO && getBaseDao().mIncentiveRewards.getRewardSum(Kava.KAVA_HARD_DENOM) == BigDecimal.ZERO &&
                getBaseDao().mIncentiveRewards.getRewardSum(Kava.KAVA_SWP_DENOM) == BigDecimal.ZERO) {
            Toast.makeText(this, R.string.error_no_incentive_to_claim, Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(MainActivity.this, ClaimIncentiveActivity.class);
        startActivity(intent);
    }

    private class MainViewPageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public MainViewPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(MainSendFragment.newInstance());
            mFragments.add(MainTokensFragment.newInstance());
            mFragments.add(MainHistoryFragment.newInstance());
            mFragments.add(DappFragment.newInstance(null));
            mFragments.add(MainSettingFragment.newInstance());
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

    public final ActivityResultLauncher<Intent> walletConnectResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null && !TextUtils.isEmpty(result.getData().getStringExtra("wcUrl"))) {
            Intent wIntent;
            if (mBaseChain.equals(BNB_MAIN)) {
                wIntent = new Intent(MainActivity.this, WalletConnectActivity.class);
            } else {
                wIntent = new Intent(MainActivity.this, ConnectWalletActivity.class);
            }
            wIntent.putExtra("wcUrl", result.getData().getStringExtra("wcUrl"));
            startActivity(wIntent);

        } else if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
            onStartWalletConnect(result.getData().getStringExtra(Intents.Scan.RESULT).trim());
        }
    });
}
