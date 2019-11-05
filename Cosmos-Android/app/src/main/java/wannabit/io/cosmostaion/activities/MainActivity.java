package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.IrisToken;
import wannabit.io.cosmostaion.dialog.Dialog_AddAccount;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.dialog.TopSheetBehavior;
import wannabit.io.cosmostaion.fragment.MainHistoryFragment;
import wannabit.io.cosmostaion.fragment.MainSendFragment;
import wannabit.io.cosmostaion.fragment.MainSettingFragment;
import wannabit.io.cosmostaion.fragment.MainTokensFragment;
import wannabit.io.cosmostaion.utils.FetchCallBack;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.FadePageTransformer;
import wannabit.io.cosmostaion.widget.StopViewPager;
import wannabit.io.cosmostaion.widget.TintableImageView;

import static wannabit.io.cosmostaion.base.BaseConstant.IS_TEST;

public class MainActivity extends BaseActivity implements FetchCallBack {

    private CoordinatorLayout           mRoot;
    private ImageView                   mChainBg;
    private Toolbar                     mToolbar;
    private ImageView                   mToolbarChainImg;
    private TextView                    mToolbarTitle;
    private TextView                    mToolbarChainName;

    private StopViewPager               mContentsPager;
    private TabLayout                   mTabLayer;
    private FrameLayout                 mDimLayer;
    public  MainViewPageAdapter         mPageAdapter;
    public FloatingActionButton         mFloatBtn;

    private ArrayList<Account>          mAccounts = new ArrayList<>();
    private TopSheetBehavior            mTopSheetBehavior;

    private RecyclerView                mChainRecyclerView;
    private RecyclerView                mAccountRecyclerView;
    private ChainListAdapter            mChainListAdapter;
    private AccountListAdapter          mAccountListAdapter;
    private int                         mSelectChainPosition = 0;

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
        mContentsPager          = findViewById(R.id.view_pager);
        mTabLayer               = findViewById(R.id.bottom_tab);
        mDimLayer               = findViewById(R.id.dim_layer);
        mFloatBtn               = findViewById(R.id.btn_floating);
        mChainRecyclerView      = findViewById(R.id.chain_recycler);
        mAccountRecyclerView    = findViewById(R.id.account_recycler);

        mFloatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartSendActivity();
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
                if(position != 0) mFloatBtn.hide();
                else if (!mFloatBtn.isShown()) mFloatBtn.show();

            }
        });

        mContentsPager.setCurrentItem(0, false);

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        if(mAccount == null) {
            return;
        }
        if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.cosmos_wh_main));
            mToolbarChainName.setText(getString(R.string.str_cosmos_hub));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorAtom));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorAtom));

        } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.iris_wh));
            mToolbarChainName.setText(getString(R.string.str_iris_net));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorIris));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorIris));


        } else if (mBaseChain.equals(BaseChain.BNB_MAIN)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.binance_ch_img));
            mToolbarChainName.setText(getString(R.string.str_binance_net));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorBnb));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorBnb));

        } else if (mBaseChain.equals(BaseChain.KAVA_MAIN)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.kava_img));
            mToolbarChainName.setText(getString(R.string.str_kava_net));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorKava));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorKava));

        } else if (mBaseChain.equals(BaseChain.IOV_MAIN)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.iov_img));
            mToolbarChainName.setText(getString(R.string.str_iov_net));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorIov));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorIov));

        }

        onUpdateTitle();
        onFetchAllData();
        onChainSelected(mSelectChainPosition);

    }

    private void onChainSelected(int position) {
        invalidateOptionsMenu();
        mSelectChainPosition = position;
        mChainListAdapter.notifyDataSetChanged();
        if (mSelectChainPosition == 0) {
            mAccounts = getBaseDao().onSelectAccounts();

        } else if (mSelectChainPosition == 1) {
            mAccounts = getBaseDao().onSelectAccountsByChain(BaseChain.COSMOS_MAIN);

        } else if (mSelectChainPosition == 2) {
            mAccounts = getBaseDao().onSelectAccountsByChain(BaseChain.IRIS_MAIN);

        } else if (mSelectChainPosition == 3) {
            mAccounts = getBaseDao().onSelectAccountsByChain(BaseChain.BNB_MAIN);

        } else if (mSelectChainPosition == 4) {
            mAccounts = getBaseDao().onSelectAccountsByChain(BaseChain.KAVA_MAIN);

        }
        WUtil.onSortingAccount(mAccounts);
        mAccountListAdapter.notifyDataSetChanged();
    }

    private void onUpdateTitle() {
        if(TextUtils.isEmpty(mAccount.nickName)) mToolbarTitle.setText(getString(R.string.str_my_wallet) + mAccount.id);
        else mToolbarTitle.setText(mAccount.nickName);
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

    public void onShowTopAccountsView() {
        mDimLayer.setVisibility(View.VISIBLE);
        mTopSheetBehavior.setState(TopSheetBehavior.STATE_EXPANDED);
    }

    private void onHideTopAccountsView() {
        if(mTopSheetBehavior.getState() != TopSheetBehavior.STATE_COLLAPSED)
            mTopSheetBehavior.setState(TopSheetBehavior.STATE_COLLAPSED);
        mDimLayer.setVisibility(View.GONE);
    }

    public void onFetchAllData() {
        onFetchAccountInfo(this);
    }

    public void onStartSendActivity() {
        if(mAccount == null) return;
        if(!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }

        Intent intent = new Intent(MainActivity.this, SendActivity.class);
        ArrayList<Balance> balances = getBaseDao().onSelectBalance(mAccount.id);
        boolean result = false;
        if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            for (Balance balance:balances) {
                if (!IS_TEST && balance.symbol.equals(BaseConstant.COSMOS_ATOM) && ((balance.balance.compareTo(BigDecimal.ONE)) > 0)) {
                    result  = true;
                } else if (IS_TEST && balance.symbol.equals(BaseConstant.COSMOS_MUON) && ((balance.balance.compareTo(BigDecimal.ONE)) > 0)) {
                    result  = true;
                }
            }
        } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            for (Balance balance:balances) {
                if (balance.symbol.equals(BaseConstant.COSMOS_IRIS_ATTO) && ((balance.balance.compareTo(new BigDecimal("200000000000000000"))) > 0)) {
                    result  = true;
                }
            }
            intent.putExtra("irisToken", WUtil.getIrisMainToken(mIrisTokens));

        } else if (mBaseChain.equals(BaseChain.BNB_MAIN)) {
            for (Balance balance:balances) {
                if (balance.symbol.equals(BaseConstant.COSMOS_BNB) && ((balance.balance.compareTo(new BigDecimal("0.000375"))) > 0)) {
                    result  = true;
                }
            }
            intent.putExtra("bnbToken", WUtil.getBnbMainToken(mBnbTokens));

        } else if (mBaseChain.equals(BaseChain.IOV_MAIN)) {
            for (Balance balance:balances) {
                if (balance.symbol.equals(BaseConstant.COSMOS_IOV) && ((balance.balance.compareTo(new BigDecimal("500000000"))) > 0)) {
                    result  = true;
                }
            }
            intent.putExtra("iovToken", WUtil.getIovMainToken(mIovTokens));

        } else if (mBaseChain.equals(BaseChain.KAVA_MAIN)) {
//            for (Balance balance:balances) {
//                if (balance.symbol.equals(BaseConstant.COSMOS_IOV) && ((balance.balance.compareTo(new BigDecimal("500000000"))) > 0)) {
//                    result  = true;
//                }
//            }
//            intent.putExtra("iovToken", WUtil.getIovMainToken(mIovTokens));
            return;
        }

        if(!result){
            Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(intent);
    }

    @Override
    public void fetchFinished() {
        if(!isFinishing()) {
            onHideWaitDialog();
            mPageAdapter.mCurrentFragment.onRefreshTab();
        }
    }

    @Override
    public void fetchBusy() {
        if(!isFinishing()) {
            onHideWaitDialog();
            mPageAdapter.mCurrentFragment.onBusyFetch();
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






    private class ChainListAdapter extends RecyclerView.Adapter<ChainListAdapter.ChainHolder> {

        @NonNull
        @Override
        public ChainListAdapter.ChainHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            return new ChainListAdapter.ChainHolder(getLayoutInflater().inflate(R.layout.item_accountlist_chain, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ChainListAdapter.ChainHolder holder, int position) {
            holder.chainCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mSelectChainPosition != position) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                onChainSelected(position);
                            }
                        },150);
                    }
                }
            });

            if (position == 0) {
                holder.chainLayer.setVisibility(View.GONE);
                holder.allLayer.setVisibility(View.VISIBLE);
                if (mSelectChainPosition == position) {
                    holder.chainCard.setBackground(getResources().getDrawable(R.drawable.box_chain_selected));
                    holder.chainAll.setTextColor(getColor(R.color.colorWhite));
                } else {
                    holder.chainCard.setBackground(getResources().getDrawable(R.drawable.box_chain_unselected));
                    holder.chainAll.setTextColor(getColor(R.color.colorGray4));
                }
                return;

            } else if (position == 1) {
                holder.chainLayer.setVisibility(View.VISIBLE);
                holder.allLayer.setVisibility(View.GONE);
                holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.cosmos_wh_main));
                holder.chainName.setText(getString(R.string.str_cosmos));


            } else if (position == 2) {
                holder.chainLayer.setVisibility(View.VISIBLE);
                holder.allLayer.setVisibility(View.GONE);
                holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.iris_wh));
                holder.chainName.setText(getString(R.string.str_iris));

            } else if (position == 3) {
                holder.chainLayer.setVisibility(View.VISIBLE);
                holder.allLayer.setVisibility(View.GONE);
                holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.binance_ch_img));
                holder.chainName.setText(getString(R.string.str_binance));

            } else if (position == 4) {
                holder.chainLayer.setVisibility(View.VISIBLE);
                holder.allLayer.setVisibility(View.GONE);
                holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.kava_img));
                holder.chainName.setText(getString(R.string.str_kava));

            }

            if (mSelectChainPosition == position) {
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
            return 5;
        }


        public class ChainHolder extends RecyclerView.ViewHolder {
            FrameLayout chainCard;
            LinearLayout chainLayer, allLayer;
            ImageView  chainImg;
            TextView chainName, chainAll;
            public ChainHolder(@NonNull View itemView) {
                super(itemView);
                chainCard       = itemView.findViewById(R.id.chainCard);
                chainLayer      = itemView.findViewById(R.id.chainLayer);
                allLayer        = itemView.findViewById(R.id.allLayer);
                chainImg        = itemView.findViewById(R.id.chainImg);
                chainName       = itemView.findViewById(R.id.chainName);
                chainAll        = itemView.findViewById(R.id.chainAll);
            }
        }
    }

    private class AccountListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_ACCOUNT       = 0;
        private static final int TYPE_ADD           = 1;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if(viewType == TYPE_ACCOUNT) {
                return new AccountListAdapter.AccountHolder(getLayoutInflater().inflate(R.layout.item_accountlist_account, viewGroup, false));
            } else {
                return new AccountListAdapter.AccountAddHolder(getLayoutInflater().inflate(R.layout.item_accountlist_add, viewGroup, false));
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_ACCOUNT) {
                final AccountHolder holder = (AccountHolder)viewHolder;
                final Account account = mAccounts.get(position);

                holder.accountArrowSort.setVisibility(View.GONE);
                if (account.id == mAccount.id) {
                    holder.accountCard.setBackground(getResources().getDrawable(R.drawable.box_accout_selected));
                } else {
                    holder.accountCard.setBackground(getResources().getDrawable(R.drawable.box_accout_unselected));
                }

                WDp.DpMainDenom(getBaseContext(), account.baseChain, holder.accountDenom);
                holder.accountAddress.setText(account.address);
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
                                    onResume();
                                }
                            },200);

                        }
                    }
                });

            } else if (getItemViewType(position) == TYPE_ADD) {
                final AccountAddHolder holder = (AccountAddHolder)viewHolder;
                holder.btn_account_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onHideTopAccountsView();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Bundle bundle  = new Bundle();
                                if (mSelectChainPosition == 1) {
                                    bundle.putString("chain", BaseChain.COSMOS_MAIN.getChain());
                                } else if (mSelectChainPosition == 2) {
                                    bundle.putString("chain", BaseChain.IRIS_MAIN.getChain());
                                } else if (mSelectChainPosition == 3) {
                                    bundle.putString("chain", BaseChain.BNB_MAIN.getChain());
                                } else if (mSelectChainPosition == 4) {
                                    bundle.putString("chain", BaseChain.KAVA_MAIN.getChain());
                                }
                                Dialog_AddAccount add = Dialog_AddAccount.newInstance(bundle);
                                add.setCancelable(true);
                                getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();

                            }
                        },200);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            if (mSelectChainPosition == 0) {
                return mAccounts.size();
            } else {
                if (mAccounts.size() >= 5) {
                    return mAccounts.size();
                } else {
                    return mAccounts.size() + 1;
                }
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (mSelectChainPosition == 0) {
                return TYPE_ACCOUNT;
            } else {
                if (mAccounts.size() >= 5) {
                    return TYPE_ACCOUNT;
                } else {
                    if (position < mAccounts.size()) {
                        return TYPE_ACCOUNT;
                    } else {
                        return TYPE_ADD;
                    }
                }
            }
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

        public class AccountAddHolder extends RecyclerView.ViewHolder {
            Button btn_account_add;
            public AccountAddHolder(@NonNull View itemView) {
                super(itemView);
                btn_account_add    = itemView.findViewById(R.id.btn_account_add);
            }
        }
    }
}
