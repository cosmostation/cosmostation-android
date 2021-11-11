package wannabit.io.cosmostaion.activities;

import android.annotation.SuppressLint;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
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

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.ClaimIncentiveActivity;
import wannabit.io.cosmostaion.activities.chains.sif.SifIncentiveActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.Dialog_AccountShow;
import wannabit.io.cosmostaion.dialog.Dialog_AddAccount;
import wannabit.io.cosmostaion.dialog.Dialog_Rizon_Event_Horizon;
import wannabit.io.cosmostaion.dialog.Dialog_WalletConnect;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.dialog.TopSheetBehavior;
import wannabit.io.cosmostaion.fragment.MainHistoryFragment;
import wannabit.io.cosmostaion.fragment.MainSendFragment;
import wannabit.io.cosmostaion.fragment.MainSettingFragment;
import wannabit.io.cosmostaion.fragment.MainTokensFragment;
import wannabit.io.cosmostaion.utils.FetchCallBack;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.FadePageTransformer;
import wannabit.io.cosmostaion.widget.StopViewPager;
import wannabit.io.cosmostaion.widget.TintableImageView;

import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_PURPOSE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_SIMPLE_CHECK;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_CLAIM_INCENTIVE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIF_CLAIM_INCENTIVE;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SWP;

public class MainActivity extends BaseActivity implements FetchCallBack {

    private CoordinatorLayout           mRoot;
    private ImageView                   mChainBg;
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
    public  MainViewPageAdapter         mPageAdapter;
    public FloatingActionButton         mFloatBtn;
    public FloatingActionButton         mFaucetBtn;
    public FloatingActionButton         mAirDropBtn;

    private TopSheetBehavior            mTopSheetBehavior;

    private RecyclerView                mChainRecyclerView;
    private RecyclerView                mAccountRecyclerView;
    private ChainListAdapter            mChainListAdapter;
    private AccountListAdapter          mAccountListAdapter;

    private BaseChain                   mSelectedChain;
    private ArrayList<BaseChain>        mDisplayChains = new ArrayList<>();
    private ArrayList<Account>          mDisplayAccounts = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRoot                   = findViewById(R.id.root);
        mChainBg                = findViewById(R.id.chain_bg);
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
        mFaucetBtn              = findViewById(R.id.btn_faucet);
        mAirDropBtn             = findViewById(R.id.btn_airdrop);
        mChainRecyclerView      = findViewById(R.id.chain_recycler);
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
                String Address = "";
                try {
                    if (mBaseChain.equals(OKEX_MAIN) || mBaseChain.equals(OK_TEST)) {
                        Address = WKey.convertAddressOkexToEth(mAccount.address);
                    } else {
                        Address = mAccount.address;
                    }
                } catch (Exception e) { }
                Bundle bundle = new Bundle();
                bundle.putString("address", Address);
                if (TextUtils.isEmpty(mAccount.nickName))
                    bundle.putString("title", getString(R.string.str_my_wallet) + mAccount.id);
                else
                    bundle.putString("title", mAccount.nickName);
                Dialog_AccountShow show = Dialog_AccountShow.newInstance(bundle);
                show.setCancelable(true);
                getSupportFragmentManager().beginTransaction().add(show, "dialog").commitNowAllowingStateLoss();
            }
        });

        mChainRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mChainRecyclerView.setHasFixedSize(true);
        mChainListAdapter = new ChainListAdapter();
        mChainRecyclerView.setAdapter(mChainListAdapter);

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
            public void onSlide(@NonNull View bottomSheet, float slideOffset, Boolean isOpening) { }
        });
        onAccountSwitched();
        onShowWaitDialog();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!getBaseDao().getLastUser().equals(mAccount.id.toString())) {
            onShowWaitDialog();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getBaseDao().setLastUser(Long.parseLong(getBaseDao().getLastUser()));
                    onAccountSwitched();
                }
            },200);
        } else {
            onUpdateTitle();
        }
        onChainSelect(getBaseDao().getLastChain());
    }

    private void onAccountSwitched() {
        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);

        mFloatBtn.setImageTintList(getResources().getColorStateList(R.color.colorWhite));
        WDp.getChainImg(MainActivity.this, mBaseChain, mToolbarChainImg);
        WDp.getChainTitle(MainActivity.this, mBaseChain, mToolbarChainName);
        mToolbarChainName.setTextColor(WDp.getChainColor(MainActivity.this, mBaseChain));
        WDp.getFloatBtn(MainActivity.this, mBaseChain, mFloatBtn);

        onUpdateTitle();
        onFetchAllData();
        mSelectedChain = getBaseDao().getLastChain();
        onChainSelect(mSelectedChain);
    }

    private void onChainSelect(BaseChain baseChain) {
        invalidateOptionsMenu();
        mDisplayChains = getBaseDao().dpSortedChains();
        mSelectedChain = baseChain;
        getBaseDao().setLastChain(mSelectedChain.getChain());
        mDisplayAccounts = getBaseDao().onSelectAccountsByChain(mSelectedChain);
        mChainRecyclerView.scrollToPosition(mDisplayChains.indexOf(mSelectedChain));

        mChainListAdapter.notifyDataSetChanged();
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
                mWalletAddress.setText(WKey.convertAddressOkexToEth(mAccount.address));
            } else {
                mWalletAddress.setText(mAccount.address);
            }
        } catch (Exception e) { }
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
        String url;
        if (mBaseChain.equals(SECRET_MAIN)) {
            url = WUtil.getExplorer(mBaseChain) + "accounts/" + mAccount.address;
        } else {
            url  = WUtil.getExplorer(mBaseChain) + "account/" + mAccount.address;
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
        mChainRecyclerView.setAdapter(mChainListAdapter);
        mChainRecyclerView.scrollToPosition(mDisplayChains.indexOf(getBaseDao().getLastChain()));
    }

    private void onHideTopAccountsView() {
        if(mTopSheetBehavior.getState() != TopSheetBehavior.STATE_COLLAPSED)
            mTopSheetBehavior.setState(TopSheetBehavior.STATE_COLLAPSED);
        mDimLayer.setVisibility(View.GONE);
    }

    public void onFetchAllData() {
        onFetchAccountInfo(this);
    }

    public void onSetKavaWarn() {
        getBaseDao().setKavaWarn();
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
            BigDecimal available = getBaseDao().availableAmount(WDp.mainDenom(mBaseChain));
            BigDecimal txFee = WUtil.getEstimateGasFeeAmount(this, mBaseChain, CONST_PW_TX_CLAIM_INCENTIVE, 0);
            if (available.compareTo(txFee) < 0) {
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
            if (available.compareTo(txFee) < 0) {
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

    public void onClickEventHorizon() {
        if (!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }

        Dialog_Rizon_Event_Horizon add = Dialog_Rizon_Event_Horizon.newInstance();
        add.setCancelable(true);
        getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
    }

    public void onCheckRizonEventHorizon() {
        onStartEventHorizon();
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

    private class ChainListAdapter extends RecyclerView.Adapter<ChainListAdapter.ChainHolder> {

        @NonNull
        @Override
        public ChainListAdapter.ChainHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            return new ChainListAdapter.ChainHolder(getLayoutInflater().inflate(R.layout.item_accountlist_chain, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ChainListAdapter.ChainHolder holder, @SuppressLint("RecyclerView") int position) {
            BaseChain chain = mDisplayChains.get(position);
            holder.chainCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (chain != mSelectedChain) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                onChainSelect(chain);
                                getBaseDao().setUserSortedChains(mDisplayChains);
                            }
                        },150);
                    }
                }
            });
            WDp.getChainImg(MainActivity.this, chain, holder.chainImg);
            WDp.getChainTitle2(MainActivity.this, chain, holder.chainName);

            if (mSelectedChain.equals(chain)) {
                holder.chainCard.setBackground(getResources().getDrawable(R.drawable.box_chain_selected));
                holder.chainImg.setAlpha(1f);
                holder.chainName.setTextColor(getColor(R.color.colorWhite));
            } else {
                holder.chainCard.setBackground(getResources().getDrawable(R.drawable.box_chain_unselected));
                holder.chainImg.setAlpha(0.1f);
                holder.chainName.setTextColor(getColor(R.color.colorGray4));
            }

        }

        @Override
        public int getItemCount() {
            return mDisplayChains.size();
        }

        public class ChainHolder extends RecyclerView.ViewHolder {
            FrameLayout chainCard;
            LinearLayout chainLayer;
            ImageView  chainImg;
            TextView chainName;
            public ChainHolder(@NonNull View itemView) {
                super(itemView);
                chainCard       = itemView.findViewById(R.id.chainCard);
                chainLayer      = itemView.findViewById(R.id.chainLayer);
                chainImg        = itemView.findViewById(R.id.chainImg);
                chainName       = itemView.findViewById(R.id.chainName);
            }
        }
    }

    private class AccountListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            return new AccountListAdapter.AccountHolder(getLayoutInflater().inflate(R.layout.item_accountlist_account, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            final AccountHolder holder = (AccountHolder)viewHolder;
            final Account account = mDisplayAccounts.get(position);

            holder.accountArrowSort.setVisibility(View.GONE);
            if (account.id.equals(mAccount.id)) {
                holder.accountCard.setBackground(getResources().getDrawable(R.drawable.box_accout_selected));
            } else {
                holder.accountCard.setBackground(getResources().getDrawable(R.drawable.box_account_unselected));
            }
            WDp.DpMainDenom(getBaseContext(), account.baseChain, holder.accountDenom);
            if (BaseChain.getChain(account.baseChain).equals(OKEX_MAIN)) {
                try {
                    holder.accountAddress.setText(WKey.convertAddressOkexToEth(account.address));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                holder.accountAddress.setText(account.address);
            }
            holder.accountAvailable.setText(account.getLastTotal(getBaseContext(), BaseChain.getChain(account.baseChain)));
            holder.accountKeyState.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorGray0), android.graphics.PorterDuff.Mode.SRC_IN);
            if (account.hasPrivateKey) {
                holder.accountKeyState.setColorFilter(WDp.getChainColor(getBaseContext(), BaseChain.getChain(account.baseChain)), android.graphics.PorterDuff.Mode.SRC_IN);
            }

            if (TextUtils.isEmpty(account.nickName)){
                holder.accountName.setText(getString(R.string.str_my_wallet) + account.id);
            } else {
                holder.accountName.setText(account.nickName);
            }

            holder.accountCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(account.id == mAccount.id) {
                        onHideTopAccountsView();
                        return;
                    } else {
                        onHideTopAccountsView();
                        onShowWaitDialog();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getBaseDao().setLastUser(account.id);
                                onAccountSwitched();
                            }
                        },200);

                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDisplayAccounts.size();
        }

        public class AccountHolder extends RecyclerView.ViewHolder {
            FrameLayout accountCard;
            LinearLayout accountContent;
            ImageView  accountArrowSort, accountKeyState;
            TextView accountName, accountAddress, accountAvailable, accountDenom;
            public AccountHolder(@NonNull View itemView) {
                super(itemView);
                accountCard         = itemView.findViewById(R.id.accountCard);
                accountArrowSort    = itemView.findViewById(R.id.accountArrowSort);
                accountContent      = itemView.findViewById(R.id.accountContent);
                accountKeyState     = itemView.findViewById(R.id.accountKeyState);
                accountName         = itemView.findViewById(R.id.accountName);
                accountAddress      = itemView.findViewById(R.id.accountAddress);
                accountAvailable    = itemView.findViewById(R.id.accountAvailable);
                accountDenom        = itemView.findViewById(R.id.accountDenom);
            }
        }
    }
}
