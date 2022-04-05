package wannabit.io.cosmostaion.activities;

import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_PURPOSE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_SIMPLE_CHECK;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_CLAIM_INCENTIVE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_PROFILE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIF_CLAIM_INCENTIVE;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SWP;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.fulldive.wallet.presentation.accounts.AddAccountDialogFragment;
import com.fulldive.wallet.presentation.main.history.MainHistoryFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.math.BigDecimal;
import java.util.ArrayList;

import desmos.profiles.v1beta1.ModelsProfile;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.desmos.ProfileActivity;
import wannabit.io.cosmostaion.activities.chains.desmos.ProfileDetailActivity;
import wannabit.io.cosmostaion.activities.chains.kava.ClaimIncentiveActivity;
import wannabit.io.cosmostaion.activities.chains.sif.SifIncentiveActivity;
import wannabit.io.cosmostaion.appextensions.PopupManager;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.IBusyFetchListener;
import wannabit.io.cosmostaion.base.IRefreshTabListener;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.ChainAccounts;
import wannabit.io.cosmostaion.dialog.Dialog_WalletConnect;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.fragment.MainSendFragment;
import wannabit.io.cosmostaion.fragment.MainSettingFragment;
import wannabit.io.cosmostaion.fragment.MainTokensFragment;
import wannabit.io.cosmostaion.utils.FetchCallBack;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.FadePageTransformer;
import wannabit.io.cosmostaion.widget.LockedViewPager;
import wannabit.io.cosmostaion.widget.TintableImageView;

public class MainActivity extends BaseActivity implements FetchCallBack {

    private ImageView mToolbarChainImg;
    private TextView mToolbarTitle;
    private TextView mToolbarChainName;

    public MainViewPageAdapter mPageAdapter;
    public FloatingActionButton mFloatBtn;

    private BaseChain mSelectedChain;
    private final ArrayList<ChainAccounts> mChainAccounts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbarTitle = findViewById(R.id.toolbar_title);
        mToolbarChainImg = findViewById(R.id.toolbar_net_image);
        mToolbarChainName = findViewById(R.id.toolbar_net_name);
        mFloatBtn = findViewById(R.id.btn_floating);

        Toolbar toolbar = findViewById(R.id.toolbar);
        LockedViewPager contentsPager = findViewById(R.id.view_pager);
        TabLayout tabLayer = findViewById(R.id.bottom_tab);

        mFloatBtn.setOnClickListener(v -> onStartSendMainDenom());

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mPageAdapter = new MainViewPageAdapter(getSupportFragmentManager());
        contentsPager.setPageTransformer(false, new FadePageTransformer());
        contentsPager.setOffscreenPageLimit(3);
        contentsPager.setAdapter(mPageAdapter);
        tabLayer.setupWithViewPager(contentsPager);
        tabLayer.setTabRippleColor(null);

        View tab0 = LayoutInflater.from(this).inflate(R.layout.view_tab_item, null);
        TintableImageView tabItemIcon0 = tab0.findViewById(R.id.tabItemIcon);
        TextView tabItemText0 = tab0.findViewById(R.id.tabItemText);
        tabItemIcon0.setImageResource(R.drawable.wallet_ic);
        tabItemText0.setText(R.string.str_main_wallet);
        tabLayer.getTabAt(0).setCustomView(tab0);

        View tab1 = LayoutInflater.from(this).inflate(R.layout.view_tab_item, null);
        TintableImageView tabItemIcon1 = tab1.findViewById(R.id.tabItemIcon);
        TextView tabItemText1 = tab1.findViewById(R.id.tabItemText);
        tabItemIcon1.setImageResource(R.drawable.tokens_ic);
        tabItemText1.setText(R.string.str_main_tokens);
        tabLayer.getTabAt(1).setCustomView(tab1);

        View tab2 = LayoutInflater.from(this).inflate(R.layout.view_tab_item, null);
        TintableImageView tabItemIcon2 = tab2.findViewById(R.id.tabItemIcon);
        TextView tabItemText2 = tab2.findViewById(R.id.tabItemText);
        tabItemIcon2.setImageResource(R.drawable.ts_ic);
        tabItemText2.setText(R.string.str_main_history);
        tabLayer.getTabAt(2).setCustomView(tab2);

        View tab3 = LayoutInflater.from(this).inflate(R.layout.view_tab_item, null);
        TintableImageView tabItemIcon3 = tab3.findViewById(R.id.tabItemIcon);
        TextView tabItemText3 = tab3.findViewById(R.id.tabItemText);
        tabItemIcon3.setImageResource(R.drawable.setting_ic);
        tabItemText3.setText(R.string.str_main_set);
        tabLayer.getTabAt(3).setCustomView(tab3);

        contentsPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }

            @Override
            public void onPageSelected(int position) {
                if (mPageAdapter != null && mPageAdapter.mCurrentFragment != null) {
                    ((IRefreshTabListener) mPageAdapter.mCurrentFragment).onRefreshTab();
                }
                if (position != 0) mFloatBtn.hide();
                else if (!mFloatBtn.isShown()) mFloatBtn.show();
            }
        });

        contentsPager.setCurrentItem(getIntent().getIntExtra("page", 0), false);
        PopupManager.INSTANCE.onAppStarted(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onAccountSwitched();
        onChainSelect(baseChain);
    }

    public void onAccountSwitched() {
        boolean needFetch = false;
        Account supportedAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());

        if (account == null) {
            needFetch = true;
        } else if (!supportedAccount.id.toString().equals(getBaseDao().getLastUser())) {
            getBaseDao().setLastUser(supportedAccount.id);
            needFetch = true;
        } else if (!getBaseDao().getLastUser().equals(account.id.toString())) {
            needFetch = true;
        }

        account = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        baseChain = BaseChain.getChain(account.baseChain);
        if (needFetch) {
            onShowWaitDialog();
            onFetchAllData();

            mToolbarChainImg.setImageResource(baseChain.getChainIcon());
            WDp.getChainHint(baseChain, mToolbarChainName);
            mToolbarChainName.setTextColor(WDp.getChainColor(this, baseChain));

            mFloatBtn.setImageTintList(ContextCompat.getColorStateList(this, baseChain.getFloatButtonColor()));
            mFloatBtn.setBackgroundTintList(ContextCompat.getColorStateList(this, baseChain.getFloatButtonBackground()));

            mSelectedChain = baseChain;
            onChainSelect(mSelectedChain);
        }

        mToolbarTitle.setText(account.getAccountTitle(this));
    }

    private void onChainSelect(BaseChain baseChain) {
        invalidateOptionsMenu();
        mChainAccounts.clear();

        ArrayList<BaseChain> displayChains = getBaseDao().dpSortedChains();
        ArrayList<BaseChain> expendedChains = getBaseDao().getExpendedChains();

        mSelectedChain = baseChain;
        getBaseDao().setLastChain(mSelectedChain.getChain());

        for (BaseChain chain : displayChains) {
            boolean opened = expendedChains.contains(chain) || mSelectedChain.equals(chain);
            mChainAccounts.add(new ChainAccounts(opened, chain, getBaseDao().onSelectAccountsByChain(chain)));
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
        if (baseChain.equals(OKEX_MAIN)) {
            url = WUtil.getExplorer(baseChain) + "address/" + account.address;
        } else {
            url = WUtil.getExplorer(baseChain) + "account/" + account.address;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    public void onChainSelected(BaseChain baseChain) {
        if (getBaseDao().onSelectAccountsByChain(baseChain).size() >= 5) {
            Toast.makeText(this, R.string.error_max_account_number, Toast.LENGTH_SHORT).show();
            return;
        }
        new Handler().postDelayed(() -> {
            showDialog(AddAccountDialogFragment.Companion.newInstance(baseChain.getChain()));
        }, 300);
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
                if (!account.hasPrivateKey) {
                    Dialog_WatchMode dialog = Dialog_WatchMode.newInstance();
                    showDialog(dialog);
                    return;
                }
                BigDecimal available = getBaseDao().getAvailable(baseChain.getMainDenom());
                BigDecimal txFee = WUtil.getEstimateGasFeeAmount(this, baseChain, CONST_PW_TX_PROFILE, 0);
                if (available.compareTo(txFee) <= 0) {
                    Toast.makeText(this, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent profile = new Intent(this, ProfileActivity.class);
                startActivity(profile);
            }
        } else {
            if (!account.hasPrivateKey) {
                Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                showDialog(add);
                return;
            }
            BigDecimal available = getBaseDao().getAvailable(baseChain.getMainDenom());
            BigDecimal txFee = WUtil.getEstimateGasFeeAmount(this, baseChain, CONST_PW_TX_PROFILE, 0);
            if (available.compareTo(txFee) <= 0) {
                Toast.makeText(this, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }
            Intent profile = new Intent(this, ProfileActivity.class);
            startActivity(profile);
        }
    }

    public void onStartBinanceWalletConnect(String wcUrl) {
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
            for (Fragment fragment : mPageAdapter.getFragments()) {
                if (fragment instanceof IRefreshTabListener) {
                    ((IRefreshTabListener) fragment).onRefreshTab();
                }
            }
        }
    }

    @Override
    public void fetchBusy() {
        if (!isFinishing()) {
            onHideWaitDialog();
            Fragment fragment = mPageAdapter.mCurrentFragment;
            if (fragment instanceof IBusyFetchListener) {
                ((IBusyFetchListener) mPageAdapter.mCurrentFragment).onBusyFetch();
            }
        }
    }

    public void onClickIncentive() {
        if (!account.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            showDialog(add);
            return;
        }

        if (baseChain.equals(KAVA_MAIN)) {
            BigDecimal available = getBaseDao().getAvailable(baseChain.getMainDenom());
            BigDecimal txFee = WUtil.getEstimateGasFeeAmount(this, baseChain, CONST_PW_TX_CLAIM_INCENTIVE, 0);
            if (available.compareTo(txFee) <= 0) {
                Toast.makeText(this, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }
            if (getBaseDao().mIncentiveRewards.getRewardSum(TOKEN_KAVA) == BigDecimal.ZERO && getBaseDao().mIncentiveRewards.getRewardSum(TOKEN_HARD) == BigDecimal.ZERO &&
                    getBaseDao().mIncentiveRewards.getRewardSum(TOKEN_SWP) == BigDecimal.ZERO) {
                Toast.makeText(this, R.string.error_no_incentive_to_claim, Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(MainActivity.this, ClaimIncentiveActivity.class);
            startActivity(intent);

        } else if (baseChain.equals(SIF_MAIN)) {
            BigDecimal available = getBaseDao().getAvailable(baseChain.getMainDenom());
            BigDecimal txFee = WUtil.getEstimateGasFeeAmount(this, baseChain, CONST_PW_TX_SIF_CLAIM_INCENTIVE, 0);
            if (available.compareTo(txFee) <= 0) {
                Toast.makeText(this, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }
            if (getBaseDao().mSifLmIncentive == null || getBaseDao().mSifLmIncentive.totalClaimableCommissionsAndClaimableRewards == 0) {
                Toast.makeText(this, R.string.error_no_incentive_to_claim, Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(MainActivity.this, SifIncentiveActivity.class);
            startActivity(intent);
        }
    }

    private static class MainViewPageAdapter extends FragmentPagerAdapter {

        private final ArrayList<Fragment> mFragments = new ArrayList<>();
        private Fragment mCurrentFragment;

        public MainViewPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(MainSendFragment.newInstance(null));
            mFragments.add(MainTokensFragment.newInstance(null));
            mFragments.add(MainHistoryFragment.Companion.newInstance());
            mFragments.add(MainSettingFragment.newInstance(null));
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            if (getCurrentFragment() != object) {
                mCurrentFragment = ((Fragment) object);
            }
            super.setPrimaryItem(container, position, object);
        }

        public Fragment getCurrentFragment() {
            return mCurrentFragment;
        }

        public ArrayList<Fragment> getFragments() {
            return mFragments;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CONST_PW_SIMPLE_CHECK && resultCode == RESULT_OK && !TextUtils.isEmpty(data.getStringExtra("wcUrl"))) {
            Intent wcIntent = new Intent(this, WalletConnectActivity.class);
            wcIntent.putExtra("wcUrl", data.getStringExtra("wcUrl"));
            startActivity(wcIntent);

        } else {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null && result.getContents() != null && result.getContents().trim().contains("wallet-bridge.binance.org")) {
                Bundle bundle = new Bundle();
                bundle.putString("wcUrl", result.getContents().trim());
                Dialog_WalletConnect dialog = Dialog_WalletConnect.newInstance(bundle);
                showDialog(dialog);
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }
}
