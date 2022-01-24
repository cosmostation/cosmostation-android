package wannabit.io.cosmostaion.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import desmos.profiles.v1beta1.ModelsProfile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.desmos.ProfileActivity;
import wannabit.io.cosmostaion.activities.chains.desmos.ProfileDetailActivity;
import wannabit.io.cosmostaion.activities.chains.kava.ClaimIncentiveActivity;
import wannabit.io.cosmostaion.activities.chains.sif.SifIncentiveActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.Dialog_AccountShow;
import wannabit.io.cosmostaion.dialog.Dialog_AddAccount;
import wannabit.io.cosmostaion.dialog.Dialog_WalletConnect;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.dialog.TopSheetBehavior;
import wannabit.io.cosmostaion.fragment.MainHistoryFragment;
import wannabit.io.cosmostaion.fragment.MainSendFragment;
import wannabit.io.cosmostaion.fragment.MainSettingFragment;
import wannabit.io.cosmostaion.fragment.MainTokensFragment;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.req.ReqBroadAirDrop;
import wannabit.io.cosmostaion.utils.FetchCallBack;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.FadePageTransformer;
import wannabit.io.cosmostaion.widget.StopViewPager;
import wannabit.io.cosmostaion.widget.TintableImageView;
import wannabit.io.cosmostaion.widget.mainWallet.ManageChainSwitchHolder;

import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_PURPOSE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_SIMPLE_CHECK;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_CLAIM_INCENTIVE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIF_CLAIM_INCENTIVE;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SWP;

public class MainActivity extends BaseActivity implements FetchCallBack {

    private Toolbar                     mToolbar;
    private ImageView                   mToolbarChainImg;
    private TextView                    mToolbarTitle;
    private TextView                    mToolbarChainName;

    private CardView                    mCardView;
    private ImageView                   itemKeyStatus;
    private TextView                    mWalletAddress;
    private TextView                    mTotalValue;

    private StopViewPager               mContentsPager;
    private TabLayout                   mTabLayer;
    private FrameLayout                 mDimLayer;
    public MainViewPageAdapter          mPageAdapter;
    public FloatingActionButton         mFloatBtn;

    private TopSheetBehavior            mTopSheetBehavior;

    private RecyclerView                mAccountRecyclerView;
    private AccountListAdapter          mAccountListAdapter;

    private BaseChain                   mSelectedChain;
    private ArrayList<BaseChain>        mExpendedChains = new ArrayList<>();
    private ArrayList<ChainAccounts>    mChainAccounts = new ArrayList<>();

    private String                      mAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar                = findViewById(R.id.tool_bar);
        mToolbarTitle           = findViewById(R.id.toolbar_title);
        mToolbarChainImg        = findViewById(R.id.toolbar_net_image);
        mToolbarChainName       = findViewById(R.id.toolbar_net_name);
        mCardView               = findViewById(R.id.card_root);
        itemKeyStatus           = findViewById(R.id.img_account);
        mWalletAddress          = findViewById(R.id.wallet_address);
        mTotalValue             = findViewById(R.id.total_value);
        mContentsPager          = findViewById(R.id.view_pager);
        mTabLayer               = findViewById(R.id.bottom_tab);
        mDimLayer               = findViewById(R.id.dim_layer);
        mFloatBtn               = findViewById(R.id.btn_floating);
        mAccountRecyclerView    = findViewById(R.id.account_recycler);

        mFloatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartSendMainDenom();
            }
        });

        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("address", mAddress);
                if (TextUtils.isEmpty(mAccount.nickName))
                    bundle.putString("title", getString(R.string.str_my_wallet) + mAccount.id);
                else
                    bundle.putString("title", mAccount.nickName);
                Dialog_AccountShow show = Dialog_AccountShow.newInstance(bundle);
                show.setCancelable(true);
                getSupportFragmentManager().beginTransaction().add(show, "dialog").commitNowAllowingStateLoss();
            }
        });

        mAccountRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAccountRecyclerView.setHasFixedSize(true);
        mAccountListAdapter = new AccountListAdapter();
        mAccountRecyclerView.setAdapter(mAccountListAdapter);

        mDimLayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTopSheetBehavior.getState() != TopSheetBehavior.STATE_COLLAPSED)
                    mTopSheetBehavior.setState(TopSheetBehavior.STATE_COLLAPSED);
                mDimLayer.setVisibility(View.GONE);
                setExpendChains();
            }
        });

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mPageAdapter = new MainViewPageAdapter(getSupportFragmentManager());
        mContentsPager.setPageTransformer(false, new FadePageTransformer());
        mContentsPager.setOffscreenPageLimit(3);
        mContentsPager.setAdapter(mPageAdapter);
        mTabLayer.setupWithViewPager(mContentsPager);
        mTabLayer.setTabRippleColor(null);

        View tab0 = LayoutInflater.from(this).inflate(R.layout.view_tab_item, null);
        TintableImageView tabItemIcon0  = tab0.findViewById(R.id.tabItemIcon);
        TextView tabItemText0  = tab0.findViewById(R.id.tabItemText);
        tabItemIcon0.setImageResource(R.drawable.wallet_ic);
        tabItemText0.setText(R.string.str_main_wallet);
        mTabLayer.getTabAt(0).setCustomView(tab0);

        View tab1 = LayoutInflater.from(this).inflate(R.layout.view_tab_item, null);
        TintableImageView   tabItemIcon1  = tab1.findViewById(R.id.tabItemIcon);
        TextView            tabItemText1  = tab1.findViewById(R.id.tabItemText);
        tabItemIcon1.setImageResource(R.drawable.tokens_ic);
        tabItemText1.setText(R.string.str_main_tokens);
        mTabLayer.getTabAt(1).setCustomView(tab1);

        View tab2 = LayoutInflater.from(this).inflate(R.layout.view_tab_item, null);
        TintableImageView   tabItemIcon2  = tab2.findViewById(R.id.tabItemIcon);
        TextView            tabItemText2  = tab2.findViewById(R.id.tabItemText);
        tabItemIcon2.setImageResource(R.drawable.ts_ic);
        tabItemText2.setText(R.string.str_main_history);
        mTabLayer.getTabAt(2).setCustomView(tab2);

        View tab3 = LayoutInflater.from(this).inflate(R.layout.view_tab_item, null);
        TintableImageView   tabItemIcon3  = tab3.findViewById(R.id.tabItemIcon);
        TextView            tabItemText3  = tab3.findViewById(R.id.tabItemText);
        tabItemIcon3.setImageResource(R.drawable.setting_ic);
        tabItemText3.setText(R.string.str_main_set);
        mTabLayer.getTabAt(3).setCustomView(tab3);

        mContentsPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) { }

            @Override
            public void onPageScrollStateChanged(int i) { }

            @Override
            public void onPageSelected(int position) {
                if(mPageAdapter != null && mPageAdapter.mCurrentFragment != null) {
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                }
                if (position != 0) mFloatBtn.hide();
                else if (!mFloatBtn.isShown()) mFloatBtn.show();
                if (position == 3) {
                    mCardView.setVisibility(View.GONE);
                } else {
                    mCardView.setVisibility(View.VISIBLE);
                }
                if (mBaseChain != null) {
                    mTotalValue.setText(WDp.dpAllAssetValueUserCurrency(mBaseChain, getBaseDao()));
                }
            }
        });

        mContentsPager.setCurrentItem(getIntent().getIntExtra("page", 0), false);

        View sheet = findViewById(R.id.top_sheet);
        sheet.setNestedScrollingEnabled(false);
        mTopSheetBehavior = TopSheetBehavior.from(sheet);
        mTopSheetBehavior.setState(TopSheetBehavior.STATE_HIDDEN);
        mTopSheetBehavior.setTopSheetCallback(new TopSheetBehavior.TopSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == TopSheetBehavior.STATE_COLLAPSED) {
                    mDimLayer.setVisibility(View.GONE);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset, Boolean isOpening) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        onAccountSwitched();
        mAccountRecyclerView.scrollToPosition(getBaseDao().dpSortedChains().indexOf(mSelectedChain));
        onChainSelect(mBaseChain);
    }

    private void setExpendChains() {
        mExpendedChains.clear();
        for (ChainAccounts chainAccounts: mChainAccounts) {
            if (chainAccounts.opened) {
                mExpendedChains.add(chainAccounts.baseChain);
            }
        }
        getBaseDao().setExpendedChains(mExpendedChains);
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

        if (needFetch) {
            onShowWaitDialog();
            onFetchAllData();

            mFloatBtn.setImageTintList(getResources().getColorStateList(R.color.colorWhite));
            WDp.getChainImg(MainActivity.this, mBaseChain, mToolbarChainImg);
            WDp.getChainTitle(MainActivity.this, mBaseChain, mToolbarChainName);
            mToolbarChainName.setTextColor(WDp.getChainColor(MainActivity.this, mBaseChain));
            WDp.getFloatBtn(MainActivity.this, mBaseChain, mFloatBtn);

            mSelectedChain = mBaseChain;
            mAccountRecyclerView.setAdapter(mAccountListAdapter);
            mAccountRecyclerView.scrollToPosition(getBaseDao().dpSortedChains().indexOf(mSelectedChain));
            onChainSelect(mSelectedChain);
        }
        onUpdateTitle();
    }

    private void onChainSelect(BaseChain baseChain) {
        invalidateOptionsMenu();
        mChainAccounts.clear();
        ArrayList<BaseChain> mDisplayChains = new ArrayList<>();
        mDisplayChains = getBaseDao().dpSortedChains();
        mExpendedChains = getBaseDao().getExpendedChains();
        mSelectedChain = baseChain;
        getBaseDao().setLastChain(mSelectedChain.getChain());

        for (BaseChain chain: mDisplayChains) {
            if (mExpendedChains.contains(chain) || mSelectedChain.equals(chain)) {
                mChainAccounts.add(new ChainAccounts(true, chain, getBaseDao().onSelectAccountsByChain(chain)));
            } else {
                mChainAccounts.add(new ChainAccounts(false, chain, getBaseDao().onSelectAccountsByChain(chain)));
            }
        }
        mAccountListAdapter.notifyDataSetChanged();
    }

    public void onUpdateTitle() {
        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);

        if(TextUtils.isEmpty(mAccount.nickName)) mToolbarTitle.setText(getString(R.string.str_my_wallet) + mAccount.id);
        else mToolbarTitle.setText(mAccount.nickName);

        mCardView.setCardBackgroundColor(WDp.getChainBgColor(MainActivity.this, mBaseChain));
        if (mAccount.hasPrivateKey) {
            itemKeyStatus.setColorFilter(WDp.getChainColor(MainActivity.this, mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            itemKeyStatus.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.colorGray0), android.graphics.PorterDuff.Mode.SRC_IN);
        }
        try {
            if (mBaseChain.equals(OKEX_MAIN) || mBaseChain.equals(OK_TEST)) {
                if (mAccount.address.startsWith("ex1")) {
                    mAddress = WKey.convertAddressOkexToEth(mAccount.address);
                } else {
                    mAddress = mAccount.address;
                }
            } else {
                mAddress = mAccount.address;
            }
        } catch (Exception e) { }
        mWalletAddress.setText(mAddress);
    }

    @Override
    public void onBackPressed() {
        if(mTopSheetBehavior.getState() != TopSheetBehavior.STATE_COLLAPSED) {
            mTopSheetBehavior.setState(TopSheetBehavior.STATE_COLLAPSED);
            return;
        } else {
            moveTaskToBack(true);
        }
    }

    public void onExplorerView() {
        String url = "";
        if (mBaseChain.equals(OKEX_MAIN) || mBaseChain.equals(OK_TEST)) {
            url = WUtil.getExplorer(mBaseChain) + "address/" + mAccount.address;
        } else {
            url = WUtil.getExplorer(mBaseChain) + "account/" + mAccount.address;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    public void onChainSelected(BaseChain baseChain) {
        if (getBaseDao().onSelectAccountsByChain(baseChain).size() >= 5) {
            Toast.makeText(this, R.string.error_max_account_number, Toast.LENGTH_SHORT).show();
            return;
        }
        onHideTopAccountsView();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Bundle bundle = new Bundle();
                bundle.putString("chain", baseChain.getChain());
                Dialog_AddAccount add = Dialog_AddAccount.newInstance(bundle);
                add.setCancelable(true);
                getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            }
        }, 300);
    }

    public void onShowTopAccountsView() {
        mDimLayer.setVisibility(View.VISIBLE);
        mTopSheetBehavior.setState(TopSheetBehavior.STATE_EXPANDED);
        onChainSelect(mSelectedChain);
    }

    public void onHideTopAccountsView() {
        if(mTopSheetBehavior.getState() != TopSheetBehavior.STATE_COLLAPSED)
            mTopSheetBehavior.setState(TopSheetBehavior.STATE_COLLAPSED);
        mDimLayer.setVisibility(View.GONE);
        setExpendChains();
    }

    public void onFetchAllData() {
        onFetchAccountInfo(this);
    }

    public void onSetKavaWarn() {
        getBaseDao().setKavaWarn();
    }

    public void onClickProfile() {
        if (getBaseDao().mGRpcNodeInfo != null && getBaseDao().mGRpcAccount != null) {
            if (getBaseDao().mGRpcAccount.getTypeUrl().contains(ModelsProfile.Profile.getDescriptor().getFullName())) {
                Intent airdrop = new Intent(this, ProfileDetailActivity.class);
                startActivity(airdrop);

            } else {
                if (!mAccount.hasPrivateKey) {
                    Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                    add.setCancelable(true);
                    getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
                    return;
                }
                Intent profile = new Intent(this, ProfileActivity.class);
                startActivity(profile);
            }

        } else {
            if (!mAccount.hasPrivateKey) {
                Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                add.setCancelable(true);
                getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
                return;
            }
            onDesmosFeeCheck(mAccount.address);
        }
    }

    public void onDesmosFeeCheck(String address) {
        onShowWaitDialog();
        ReqBroadAirDrop reqBroadAirDrop = new ReqBroadAirDrop(address);
        if (reqBroadAirDrop != null) {
            ApiClient.getAirDrop(getBaseContext()).broadAirDrop(reqBroadAirDrop).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(isFinishing()) return;
                    String value = null;
                    if (response.isSuccessful()) {
                        value = response.body();
                    } else {
                        try {
                            value = response.errorBody().string();
                        } catch (IOException e) { e.printStackTrace(); }
                    }
                    onHideWaitDialog();
                    LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    View view = inflater.inflate(R.layout.dialog_grant_airdrop, null);
                    TextView txt_msg = view.findViewById(R.id.grant_msg);
                    TextView btn_ok = view.findViewById(R.id.btn_ok);
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setView(view);
                    txt_msg.setText(value);

                    AlertDialog dialog = builder.create();
                    dialog.setCancelable(false);
                    dialog.show();

                    btn_ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onShowWaitDialog();
                            dialog.dismiss();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    onFetchAllData();
                                }
                            },8000);
                        }
                    });
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    WLog.w("error : " + t.getMessage());
                }
            });
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
            if (mPageAdapter.getItem(0) != null) mPageAdapter.getItem(0).onRefreshTab();
            if (mPageAdapter.getItem(1) != null) mPageAdapter.getItem(1).onRefreshTab();
            if (mPageAdapter.getItem(2) != null) mPageAdapter.getItem(2).onRefreshTab();
            mTotalValue.setText(WDp.dpAllAssetValueUserCurrency(mBaseChain, getBaseDao()));
        }
    }

    @Override
    public void fetchBusy() {
        if(!isFinishing()) {
            onHideWaitDialog();
            mPageAdapter.mCurrentFragment.onBusyFetch();
        }
    }

    public void onClickIncentive() {
        if (!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }

        if (mBaseChain.equals(KAVA_MAIN)) {
            BigDecimal available = getBaseDao().getAvailable(WDp.mainDenom(mBaseChain));
            BigDecimal txFee = WUtil.getEstimateGasFeeAmount(this, mBaseChain, CONST_PW_TX_CLAIM_INCENTIVE, 0);
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

        } else if (mBaseChain.equals(SIF_MAIN)) {
            BigDecimal available = getBaseDao().getAvailable(WDp.mainDenom(mBaseChain));
            BigDecimal txFee = WUtil.getEstimateGasFeeAmount(this, mBaseChain, CONST_PW_TX_SIF_CLAIM_INCENTIVE, 0);
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

    private class MainViewPageAdapter extends FragmentPagerAdapter {

        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public MainViewPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(MainSendFragment.newInstance(null));
            mFragments.add(MainTokensFragment.newInstance(null));
            mFragments.add(MainHistoryFragment.newInstance(null));
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
                dialog.setCancelable(true);
                getSupportFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();

            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }

    }

    private class AccountListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            return new ManageChainSwitchHolder(getLayoutInflater().inflate(R.layout.item_account_list, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            final ManageChainSwitchHolder holder = (ManageChainSwitchHolder) viewHolder;
            holder.onBindChainSwitch(MainActivity.this, mChainAccounts.get(position), mAccount);
        }

        @Override
        public int getItemCount() {
            return mChainAccounts.size();
        }
    }

    public class ChainAccounts {
        public boolean opened = false;
        public BaseChain baseChain;
        public ArrayList<Account> accounts;

        public ChainAccounts(boolean opened, BaseChain baseChain, ArrayList<Account> accounts) {
            this.opened = opened;
            this.baseChain = baseChain;
            this.accounts = accounts;
        }
    }
}
