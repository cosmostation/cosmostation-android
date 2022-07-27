package wannabit.io.cosmostaion.activities;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_PURPOSE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_SIMPLE_CHECK;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_CLAIM_INCENTIVE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_PROFILE;

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

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.math.BigDecimal;
import java.util.ArrayList;

import desmos.profiles.v1beta1.ModelsProfile;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.desmos.ProfileActivity;
import wannabit.io.cosmostaion.activities.txs.desmos.ProfileDetailActivity;
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
import wannabit.io.cosmostaion.dialog.Dialog_AccountShow;
import wannabit.io.cosmostaion.fragment.DappFragment;
import wannabit.io.cosmostaion.fragment.MainHistoryFragment;
import wannabit.io.cosmostaion.fragment.MainSendFragment;
import wannabit.io.cosmostaion.fragment.MainSettingFragment;
import wannabit.io.cosmostaion.fragment.MainTokensFragment;
import wannabit.io.cosmostaion.utils.FetchCallBack;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;
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

        mFloatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartSendMainDenom();
            }
        });

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
        createTab(R.drawable.infoicon_station, R.string.str_main_dapp, 3);
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

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (mBaseChain.equals(COSMOS_MAIN)) {
            if (mAccount.pushAlarm) {
                getMenuInflater().inflate(R.menu.main_menu_alaram_on, menu);
            } else {
                getMenuInflater().inflate(R.menu.main_menu_alaram_off, menu);
            }
        } else {
            getMenuInflater().inflate(R.menu.main_menu, menu);
        }
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
            case R.id.menu_notification_off:
                onUpdateUserAlarm(mAccount, true);
                break;
            case R.id.menu_notification_on:
                onUpdateUserAlarm(mAccount, false);
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

    public void onAddressDialog() {
        Bundle bundle = new Bundle();
        bundle.putString("address", mAccount.address);
        if (TextUtils.isEmpty(mAccount.nickName))
            bundle.putString("title", getString(R.string.str_my_wallet) + mAccount.id);
        else
            bundle.putString("title", mAccount.nickName);
        Dialog_AccountShow show = Dialog_AccountShow.newInstance(bundle);
        show.setCancelable(true);
        getSupportFragmentManager().beginTransaction().add(show, "dialog").commitNowAllowingStateLoss();
    }

    public void onAccountSwitched() {
        boolean needFetch = false;
        Account supportedAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());

        if (mAccount == null) {
            needFetch = true;
        } else if (!supportedAccount.id.toString().equals(getBaseDao().getLastUser())) {
            getBaseDao().setLastUser(supportedAccount.id);
            needFetch = true;
        } else if (!getBaseDao().getLastUser().equals(mAccount.id.toString())) {
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
            ((MainViewPageAdapter) mContentsPager.getAdapter()).getItem(0).onRefreshTab();
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
        String url = "";
        if (mBaseChain.equals(OKEX_MAIN)) {
            url = mChainConfig.explorerUrl() + "address/" + mAccount.address;
        } else {
            url = mChainConfig.explorerUrl() + "account/" + mAccount.address;
        }
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    public void onFetchAllData() {
        onFetchAccountInfo(this);
    }

    public void onClickProfile() {
        if (getBaseDao().mGRpcNodeInfo != null && getBaseDao().mGRpcAccount != null) {
            if (getBaseDao().mGRpcAccount.getTypeUrl().contains(ModelsProfile.Profile.getDescriptor().getFullName())) {
                Intent airdrop = new Intent(this, ProfileDetailActivity.class);
                startActivity(airdrop);

            } else {
                if (!mAccount.hasPrivateKey) {
                    onInsertKeyDialog();
                    return;
                }
                BigDecimal available = getBaseDao().getAvailable(mChainConfig.mainDenom());
                BigDecimal txFee = WUtil.getEstimateGasFeeAmount(this, mBaseChain, CONST_PW_TX_PROFILE, 0);
                if (available.compareTo(txFee) <= 0) {
                    Toast.makeText(this, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent profile = new Intent(this, ProfileActivity.class);
                startActivity(profile);
            }
        } else {
            if (!mAccount.hasPrivateKey) {
                onInsertKeyDialog();
                return;
            }
            BigDecimal available = getBaseDao().getAvailable(mChainConfig.mainDenom());
            BigDecimal txFee = WUtil.getEstimateGasFeeAmount(this, mBaseChain, CONST_PW_TX_PROFILE, 0);
            if (available.compareTo(txFee) <= 0) {
                Toast.makeText(this, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }
            Intent profile = new Intent(this, ProfileActivity.class);
            startActivity(profile);
        }
    }

    public void onStartWalletConnect(String wcUrl) {
        Intent intent = new Intent(this, PasswordCheckActivity.class);
        intent.putExtra(CONST_PW_PURPOSE, CONST_PW_SIMPLE_CHECK);
        intent.putExtra("wcUrl", wcUrl);
        startActivityForResult(intent, CONST_PW_SIMPLE_CHECK);
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

        BigDecimal available = getBaseDao().getAvailable(WDp.mainDenom(mBaseChain));
        BigDecimal txFee = WUtil.getEstimateGasFeeAmount(this, mBaseChain, CONST_PW_TX_CLAIM_INCENTIVE, 0);
        if (available.compareTo(txFee) <= 0) {
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
            mFragments.add(MainSendFragment.newInstance(null));
            mFragments.add(MainTokensFragment.newInstance(null));
            mFragments.add(MainHistoryFragment.newInstance(null));
            mFragments.add(DappFragment.newInstance(null));
            mFragments.add(MainSettingFragment.newInstance(null));
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CONST_PW_SIMPLE_CHECK && resultCode == RESULT_OK && data != null && !TextUtils.isEmpty(data.getStringExtra("wcUrl"))) {
            Intent wIntent;
            if (mBaseChain.equals(BNB_MAIN)) {
                wIntent = new Intent(this, WalletConnectActivity.class);
            } else {
                wIntent = new Intent(this, ConnectWalletActivity.class);
            }
            wIntent.putExtra("wcUrl", data.getStringExtra("wcUrl"));
            startActivity(wIntent);

        } else {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null && result.getContents() != null) {
                onStartWalletConnect(result.getContents().trim());
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }
}
