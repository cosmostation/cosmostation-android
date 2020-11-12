package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.net.Uri;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.ok.OKValidatorListActivity;
import wannabit.io.cosmostaion.activities.chains.ok.StakeDepositActivity;
import wannabit.io.cosmostaion.activities.chains.ok.StakeWithdrawActivity;
import wannabit.io.cosmostaion.activities.chains.starname.StarNameListActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dialog.Dialog_AddAccount;
import wannabit.io.cosmostaion.dialog.Dialog_Kava_Testnet;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.dialog.TopSheetBehavior;
import wannabit.io.cosmostaion.fragment.MainHistoryFragment;
import wannabit.io.cosmostaion.fragment.MainSendFragment;
import wannabit.io.cosmostaion.fragment.MainSettingFragment;
import wannabit.io.cosmostaion.fragment.MainTokensFragment;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.utils.FetchCallBack;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.FadePageTransformer;
import wannabit.io.cosmostaion.widget.StopViewPager;
import wannabit.io.cosmostaion.widget.TintableImageView;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.FEE_BNB_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_AKASH;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ATOM;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BAND;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_CERTIK;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IRIS_ATTO;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OK_TEST;

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
    public FloatingActionButton         mFaucetBtn;
    public FloatingActionButton         mAirDropBtn;

    private ArrayList<Account>          mAccounts = new ArrayList<>();
    private TopSheetBehavior            mTopSheetBehavior;

    private RecyclerView                mChainRecyclerView;
    private RecyclerView                mAccountRecyclerView;
    private ChainListAdapter            mChainListAdapter;
    private AccountListAdapter          mAccountListAdapter;
    private int                         mSelectChainPosition = 0;

    private boolean                     mToShowTestWarn = true;

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
        mFaucetBtn              = findViewById(R.id.btn_faucet);
        mAirDropBtn             = findViewById(R.id.btn_airdrop);
        mChainRecyclerView      = findViewById(R.id.chain_recycler);
        mAccountRecyclerView    = findViewById(R.id.account_recycler);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);

        mFloatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartSendActivity();
            }
        });

        mFaucetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGetFaucet();
            }
        });

        mAirDropBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGetAirDrop();
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

                if (position != 1 || !(mBaseChain.equals(IOV_TEST) || mBaseChain.equals(OK_TEST))) {
                    mFaucetBtn.hide();
                } else if (!mFaucetBtn.isShown()) {
                    mFaucetBtn.show();
                }

//                if (position != 1 || !(mBaseChain.equals(KAVA_MAIN)) || mAccount.accountNumber > 0) {
//                    mAirDropBtn.hide();
//                } else {
//                    mAirDropBtn.show();
//                }

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);

//        WLog.w("mAccount " + mAccount.id);
//        WLog.w("mAccount " + mAccount.address);
//        WLog.w("sequenceNumber " + mAccount.sequenceNumber);
//        WLog.w("accountNumber " + mAccount.accountNumber);

        if (mBaseChain.equals(COSMOS_MAIN)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.cosmos_wh_main));
            mToolbarChainName.setText(getString(R.string.str_cosmos_hub));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorAtom));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorAtom));

        } else if (mBaseChain.equals(IRIS_MAIN)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.iris_wh));
            mToolbarChainName.setText(getString(R.string.str_iris_net));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorIris));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorIris));

        } else if (mBaseChain.equals(BNB_MAIN)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.binance_ch_img));
            mToolbarChainName.setText(getString(R.string.str_binance_net));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorBnb));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorBnb));

        } else if (mBaseChain.equals(KAVA_MAIN)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.kava_img));
            mToolbarChainName.setText(getString(R.string.str_kava_net));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorKava));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorKava));

        } else if (mBaseChain.equals(IOV_MAIN)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.iov_chain_img));
            mToolbarChainName.setText(getString(R.string.str_iov_net));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorIov));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorIov));

        } else if (mBaseChain.equals(BAND_MAIN)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.band_chain_img));
            mToolbarChainName.setText(getString(R.string.str_band_chain));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorBand));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorBand));

        } else if (mBaseChain.equals(CERTIK_MAIN)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.certik_chain_img));
            mToolbarChainName.setText(getString(R.string.str_certik_chain));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorCertik));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorCertik));

        } else if (mBaseChain.equals(AKASH_MAIN)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.akash_chain_img));
            mToolbarChainName.setText(getString(R.string.str_akash_chain));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorAkash));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorAkash));
        }

        else if (mBaseChain.equals(BNB_TEST)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.binancetestnet));
            mToolbarChainName.setText(getString(R.string.str_binance_test_net));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorBnb));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorBnb));
            mFaucetBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorBnb));

        } else if (mBaseChain.equals(KAVA_TEST)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.kava_test_img));
            mToolbarChainName.setText(getString(R.string.str_kava_net_test));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorKava));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorKava));
            mFaucetBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorKava));

        } else if (mBaseChain.equals(IOV_TEST)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.iov_testnet_img));
            mToolbarChainName.setText(getString(R.string.str_iov_net_test));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorIov));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorIov));
            mFaucetBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorIov));

        } else if (mBaseChain.equals(OK_TEST)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.okex_testnet_img));
            mToolbarChainName.setText(getString(R.string.str_ok_net_test));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorOK));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorOK));
            mFaucetBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorOK));

        } else if (mBaseChain.equals(CERTIK_TEST)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.certik_testnet_img));
            mToolbarChainName.setText(getString(R.string.str_certik_chain_test));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorCertik));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorCertik));
            mFaucetBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorCertik));

        }

        if (mContentsPager != null) {
            if (mContentsPager.getCurrentItem() == 1 &&
                    (mBaseChain.equals(IOV_TEST) || mBaseChain.equals(OK_TEST))) {
                if (!mFaucetBtn.isShown()) mFaucetBtn.show();
            } else {
                mFaucetBtn.hide();
            }

//            if (mContentsPager.getCurrentItem() == 1 && mBaseChain.equals(KAVA_MAIN) && mAccount.accountNumber <= 0) {
//                if (!mAirDropBtn.isShown()) mAirDropBtn.show();
//            } else {
//                mAirDropBtn.hide();
//            }
        }

        // make syn more smooth with wallet change action
        if (mContentsPager != null && mContentsPager.getCurrentItem() == 2) {
            mPageAdapter.mCurrentFragment.onRefreshTab();
        }

        onUpdateTitle();
        onFetchAllData();
        onShowTestNetWarnIfNeed();
        mSelectChainPosition = getBaseDao().getLastChain();
        onChainSelected(mSelectChainPosition);

    }

    private void onChainSelected(int position) {
        invalidateOptionsMenu();
        mSelectChainPosition = position;
        getBaseDao().setLastChain(mSelectChainPosition);
        mChainListAdapter.notifyDataSetChanged();
        if (mSelectChainPosition == 0) {
            mAccounts = getBaseDao().onSelectAccounts();

        } else {
            final BaseChain chain = BaseChain.SUPPORT_CHAINS().get(position - 1);
            mAccounts = getBaseDao().onSelectAccountsByChain(chain);
        }
        WUtil.onSortingAccount(mAccounts);
        mAccountListAdapter.notifyDataSetChanged();
    }

    public void onUpdateAccountListAdapter() {
        int lastPosition = getBaseDao().getLastChain() - 1;
        if (lastPosition < 0) {
            mAccounts = getBaseDao().onSelectAccounts();
        } else {
            BaseChain chain = BaseChain.SUPPORT_CHAINS().get(lastPosition);
            mAccounts = getBaseDao().onSelectAccountsByChain(chain);
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
        boolean hasbalance = false;
        if (mBaseChain.equals(COSMOS_MAIN)) {
            if (WDp.getAvailableCoin(balances, TOKEN_ATOM).compareTo(BigDecimal.ZERO) > 0) {
                hasbalance  = true;
            }

        } else if (mBaseChain.equals(IRIS_MAIN)) {
            if (WDp.getAvailableCoin(balances, TOKEN_IRIS_ATTO).compareTo(new BigDecimal("200000000000000000")) > 0) {
                hasbalance  = true;
            }
            intent.putExtra("irisToken", WUtil.getIrisMainToken(mIrisTokens));

        } else if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
            if (WDp.getAvailableCoin(balances, TOKEN_BNB).compareTo(new BigDecimal(FEE_BNB_SEND)) > 0) {
                hasbalance  = true;
            }
            intent.putExtra("bnbToken", WUtil.getBnbMainToken(getBaseDao().mBnbTokens));

        } else if (mBaseChain.equals(IOV_MAIN)) {
            if (WDp.getAvailableCoin(balances, TOKEN_IOV).compareTo(new BigDecimal("100000")) > 0) {
                hasbalance  = true;
            }
            intent.putExtra("iovDenom", TOKEN_IOV);

        } else if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
            if (WDp.getAvailableCoin(balances, TOKEN_KAVA).compareTo(BigDecimal.ZERO) > 0) {
                hasbalance  = true;
            }
            intent.putExtra("kavaDenom", TOKEN_KAVA);

        } else if (mBaseChain.equals(BAND_MAIN)) {
            if (WDp.getAvailableCoin(balances, TOKEN_BAND).compareTo(BigDecimal.ZERO) > 0) {
                hasbalance  = true;
            }

        } else if (mBaseChain.equals(IOV_TEST)) {
            if (WDp.getAvailableCoin(balances, TOKEN_IOV_TEST).compareTo(new BigDecimal("100000")) > 0) {
                hasbalance  = true;
            }
            intent.putExtra("iovDenom", TOKEN_IOV_TEST);

        } else if (mBaseChain.equals(OK_TEST)) {
            if (WDp.getAvailableCoin(balances, TOKEN_OK_TEST).compareTo(new BigDecimal("0.02")) > 0) {
                hasbalance  = true;
            }
            intent.putExtra("okDenom", TOKEN_OK_TEST);

        } else if (mBaseChain.equals(CERTIK_MAIN) || mBaseChain.equals(CERTIK_TEST)) {
            if (WDp.getAvailableCoin(balances, TOKEN_CERTIK).compareTo(new BigDecimal("5000")) > 0) {
                hasbalance  = true;
            }
            intent.putExtra("certikDenom", TOKEN_CERTIK);

        } else if (mBaseChain.equals(AKASH_MAIN)) {
            if (WDp.getAvailableCoin(balances, TOKEN_AKASH).compareTo(new BigDecimal("2500")) > 0) {
                hasbalance  = true;
            }
            intent.putExtra("akashDenom", TOKEN_AKASH);

        }

        if (!hasbalance) {
            Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(intent);
    }

    public void onGetFaucet() {
//        if (mBaseChain.equals(BNB_TEST)) {
//            if (mAccount.getBnbBalance().compareTo(new BigDecimal("2")) > 0) {
//                Toast.makeText(getBaseContext(), R.string.error_no_more_faucet, Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            onShowWaitDialog();
//            ApiClient.getBnbTestFaucet(getBaseContext()).getFaucet(mAccount.address).enqueue(new Callback<JSONObject>() {
//                @Override
//                public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
//                    if (response.isSuccessful()) {
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                onHideWaitDialog();
//                                onFetchAllData();
//                            }
//                        },2000);
//
//                    } else {
//                        onHideWaitDialog();
//                        Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<JSONObject> call, Throwable t) {
//                    onHideWaitDialog();
//                    Toast.makeText(getBaseContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//
//
//        } else if (mBaseChain.equals(KAVA_TEST)) {
//            if (mAccount.getKavaBalance().compareTo(new BigDecimal("5000000")) > 0) {
//                Toast.makeText(getBaseContext(), R.string.error_no_more_faucet, Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            onShowWaitDialog();
//            ApiClient.getKavaTestFaucet(getBaseContext()).getFaucet(mAccount.address).enqueue(new Callback<JSONObject>() {
//                @Override
//                public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
//                    if (response.isSuccessful()) {
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                onHideWaitDialog();
//                                onFetchAllData();
//                            }
//                        },6000);
//
//                    } else {
//                        onHideWaitDialog();
//                        Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<JSONObject> call, Throwable t) {
//                    onHideWaitDialog();
//                    Toast.makeText(getBaseContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//
//        } else
        if (mBaseChain.equals(IOV_TEST)) {
            if (mAccount.getIovBalance().compareTo(new BigDecimal("1000000000")) > 0) {
                Toast.makeText(getBaseContext(), R.string.error_no_more_faucet, Toast.LENGTH_SHORT).show();
                return;
            }

            onShowWaitDialog();
            ApiClient.getIovTestFaucet(getBaseContext()).getFaucet(mAccount.address).enqueue(new Callback<JSONObject>() {
                @Override
                public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                    if (response.isSuccessful()) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                onHideWaitDialog();
                                onFetchAllData();
                            }
                        },6000);

                    } else {
                        onHideWaitDialog();
                        Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<JSONObject> call, Throwable t) {
                    onHideWaitDialog();
                    Toast.makeText(getBaseContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else if (mBaseChain.equals(OK_TEST)) {
            Intent guideIntent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.okex.com/drawdex"));
            startActivity(guideIntent);
        }

    }

    public void onGetAirDrop() {
//        if (mAccount.accountNumber > 0) {
//            Toast.makeText(getBaseContext(), R.string.error_no_more_faucet, Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        onShowWaitDialog();
//        ApiClient.getKavaFaucet(getBaseContext()).getFaucet(mAccount.address).enqueue(new Callback<JSONObject>() {
//            @Override
//            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
//                if (response.isSuccessful()) {
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            onHideWaitDialog();
//                            onFetchAllData();
//                        }
//                    },6000);
//
//                } else {
//                    onHideWaitDialog();
//                    Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<JSONObject> call, Throwable t) {
//                onHideWaitDialog();
//                Toast.makeText(getBaseContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public void onShowTestNetWarnIfNeed() {
        if (mBaseChain.equals(BNB_TEST) || mBaseChain.equals(KAVA_TEST) || mBaseChain.equals(IOV_TEST) || mBaseChain.equals(OK_TEST) || mBaseChain.equals(CERTIK_TEST)) {
            if (mToShowTestWarn) {
                mToShowTestWarn = false;
                if(getBaseDao().getKavaWarn()) {
                    Dialog_Kava_Testnet dialog = Dialog_Kava_Testnet.newInstance(null);
                    dialog.setCancelable(true);
                    getSupportFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();
                }
            }
        }
    }

    public void onSetKavaWarn() {
        getBaseDao().setKavaWarn();
    }

    public void onStartOkDeposit() {
        if(mAccount == null) return;
        if(!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }
        ArrayList<Balance> balances = getBaseDao().onSelectBalance(mAccount.id);
        boolean hasbalance = false;
        if (mBaseChain.equals(OK_TEST)) {
            if (WDp.getAvailableCoin(balances, TOKEN_OK_TEST).compareTo(BigDecimal.ONE) > 0) {
                hasbalance  = true;
            }
        }
        if (!hasbalance) {
            Toast.makeText(getBaseContext(), R.string.error_not_enough_to_deposit, Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(getBaseContext(), StakeDepositActivity.class);
        startActivity(intent);

    }

    public void onStartOkWithdraw() {
        if(mAccount == null) return;
        if(!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }
        ArrayList<Balance> balances = getBaseDao().onSelectBalance(mAccount.id);
        boolean hasbalance = false;
        if (mBaseChain.equals(OK_TEST)) {
            if (WDp.getAvailableCoin(balances, TOKEN_OK_TEST).compareTo(BigDecimal.ONE) > 0) {
                hasbalance  = true;
            }
        }
        if (!hasbalance) {
            Toast.makeText(getBaseContext(), R.string.error_not_enough_to_deposit, Toast.LENGTH_SHORT).show();
            return;
        }
        if (WDp.getOkDepositCoin(getBaseDao().mOkDeposit).compareTo(BigDecimal.ZERO) <= 0) {
            Toast.makeText(getBaseContext(), R.string.error_not_enough_to_withdraw, Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(getBaseContext(), StakeWithdrawActivity.class);
        startActivity(intent);

    }

    public void onStartOkVote() {
        WLog.w("onStartOkVote");
        Intent intent = new Intent(getBaseContext(), OKValidatorListActivity.class);
        startActivity(intent);

    }

    public void onStarNameService() {
        Intent intent = new Intent(getBaseContext(), StarNameListActivity.class);
        startActivity(intent);

    }

    public void onStartStakeDropEvent() {
        if(mAccount == null) return;
        if(!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }

        BigDecimal delegateAmount = WDp.getAllDelegatedAmount(mBondings, mAllValidators, mBaseChain);
        BigDecimal availableAmount = WDp.getAvailableCoin(mBalances, TOKEN_ATOM);
        if (availableAmount.compareTo(new BigDecimal("3500")) < 0) {
            Toast.makeText(getBaseContext(), R.string.error_not_enough_to_balance, Toast.LENGTH_SHORT).show();
            return;
        }
        if (delegateAmount.compareTo(BigDecimal.ZERO) <= 0) {
            Toast.makeText(getBaseContext(), R.string.error_no_delegated_amount, Toast.LENGTH_SHORT).show();
            return;

        }

        Intent intent = new Intent(getBaseContext(), EventStakeDropActivity.class);
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

            } else {
                final BaseChain chain = BaseChain.SUPPORT_CHAINS().get(position - 1);
                if (chain.equals(COSMOS_MAIN)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.cosmos_wh_main));
                    holder.chainName.setText(getString(R.string.str_cosmos));

                } else if (chain.equals(IRIS_MAIN)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.iris_wh));
                    holder.chainName.setText(getString(R.string.str_iris));

                } else if (chain.equals(BNB_MAIN)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.binance_ch_img));
                    holder.chainName.setText(getString(R.string.str_binance));

                } else if (chain.equals(KAVA_MAIN)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.kava_img));
                    holder.chainName.setText(getString(R.string.str_kava));

                } else if (chain.equals(IOV_MAIN)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.iov_chain_img));
                    holder.chainName.setText(getString(R.string.str_iov));

                } else if (chain.equals(BAND_MAIN)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.band_chain_img));
                    holder.chainName.setText(getString(R.string.str_band));

                } else if (chain.equals(CERTIK_MAIN)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.certik_chain_img));
                    holder.chainName.setText(getString(R.string.str_certik_main));

                } else if (chain.equals(AKASH_MAIN)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.akash_chain_img));
                    holder.chainName.setText(getString(R.string.str_akash_main));

                }


                else if (chain.equals(BNB_TEST)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.binancetestnet));
                    holder.chainName.setText(getString(R.string.str_binance_test));

                } else if (chain.equals(KAVA_TEST)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.kava_test_img));
                    holder.chainName.setText(getString(R.string.str_kava_test));

                } else if (chain.equals(IOV_TEST)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.iov_testnet_img));
                    holder.chainName.setText(getString(R.string.str_iov_test));

                } else if (chain.equals(OK_TEST)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.okex_testnet_img));
                    holder.chainName.setText(getString(R.string.str_ok_test));

                } else if (chain.equals(CERTIK_TEST)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.certik_testnet_img));
                    holder.chainName.setText(getString(R.string.str_certik_test));

                }
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
            return BaseChain.SUPPORT_CHAINS().size() + 1;
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
                if (account.id.equals(mAccount.id)) {
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
                                    mToShowTestWarn = true;
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
                                final BaseChain selectChain = BaseChain.SUPPORT_CHAINS().get(mSelectChainPosition - 1);
                                bundle.putString("chain", selectChain.getChain());
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
