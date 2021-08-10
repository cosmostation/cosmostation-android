package wannabit.io.cosmostaion.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
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

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.rizon.EventHorizonActivity;
import wannabit.io.cosmostaion.activities.chains.rizon.EventHorizonDetailActivity;
import wannabit.io.cosmostaion.activities.chains.rizon.RizonSwapStatusActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Account;
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
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.FadePageTransformer;
import wannabit.io.cosmostaion.widget.StopViewPager;
import wannabit.io.cosmostaion.widget.TintableImageView;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.ALTHEA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.FETCHAI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.MEDI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.MEDI_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OSMOSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.PERSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.RIZON_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_PURPOSE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_SIMPLE_CHECK;

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
                onStartSendMainDenom();
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
        onShowWaitDialog();
        onAccountSwitched();
    }

    private void onAccountSwitched() {
        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);

//        WLog.w("mAccount " + mAccount.id);
//        WLog.w("mAccount " + mAccount.address);
//        WLog.w("sequenceNumber " + mAccount.sequenceNumber);
//        WLog.w("accountNumber " + mAccount.accountNumber);
//        WLog.w("mBaseChain " + mBaseChain);

        mFloatBtn.setImageTintList(getResources().getColorStateList(R.color.colorWhite));
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
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.chain_bandprotocal));
            mToolbarChainName.setText(getString(R.string.str_band_chain));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorBand));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorBand));

        } else if (mBaseChain.equals(CERTIK_MAIN)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.certik_chain_img));
            mToolbarChainName.setText(getString(R.string.str_certik_chain));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorCertik));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorCertik));

        } else if (mBaseChain.equals(SECRET_MAIN)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.chainsecret));
            mToolbarChainName.setText(getString(R.string.str_secret_chain));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorSecret));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorSecret));

        } else if (mBaseChain.equals(AKASH_MAIN)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.akash_chain_img));
            mToolbarChainName.setText(getString(R.string.str_akash_chain));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorAkash));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorAkash));

        } else if (mBaseChain.equals(OKEX_MAIN)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.okex_chain_img));
            mToolbarChainName.setText(getString(R.string.str_ok_net));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorOK));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorOK));

        } else if (mBaseChain.equals(PERSIS_MAIN)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.chainpersistence));
            mToolbarChainName.setText(getString(R.string.str_persis_net));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorPersis));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorBlack));
            mFloatBtn.setImageTintList(getResources().getColorStateList(R.color.colorPersis));

        } else if (mBaseChain.equals(SENTINEL_MAIN)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.chainsentinel));
            mToolbarChainName.setText(getString(R.string.str_sentinel_net));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorSentinel));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorSentinel3));

        } else if (mBaseChain.equals(FETCHAI_MAIN)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.chainfetchai));
            mToolbarChainName.setText(getString(R.string.str_fetch_net));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorFetch));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorFetch));

        } else if (mBaseChain.equals(CRYPTO_MAIN)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.chaincrypto));
            mToolbarChainName.setText(getString(R.string.str_crypto_net));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorCryto));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorCryto2));

        } else if (mBaseChain.equals(SIF_MAIN)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.chainsifchain));
            mToolbarChainName.setText(getString(R.string.str_sif_net));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorSif));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorSif));

        } else if (mBaseChain.equals(KI_MAIN)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.chain_kifoundation));
            mToolbarChainName.setText(getString(R.string.str_ki_net));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorKi));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorKi));

        } else if (mBaseChain.equals(OSMOSIS_MAIN)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.chain_osmosis));
            mToolbarChainName.setText(getString(R.string.str_osmosis_net));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorOsmosis));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorOsmosis));

        } else if (mBaseChain.equals(MEDI_MAIN)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.chainmedibloc));
            mToolbarChainName.setText(getString(R.string.str_medi_net));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorMedi));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorWhite));
            mFloatBtn.setImageTintList(getResources().getColorStateList(R.color.colorMedi));

        }

        else if (mBaseChain.equals(COSMOS_TEST)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.chain_test_cosmos));
            mToolbarChainName.setText(getString(R.string.str_cosmos_testnet));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorAtom));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorAtom));

        } else if (mBaseChain.equals(IRIS_TEST)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.chain_test_iris));
            mToolbarChainName.setText(getString(R.string.str_iris_testnet));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorIris));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorIris));

        } else if (mBaseChain.equals(BNB_TEST)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.binancetestnet));
            mToolbarChainName.setText(getString(R.string.str_binance_test_net));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorBnb));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorBnb));
//            mFaucetBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorBnb));

        } else if (mBaseChain.equals(KAVA_TEST)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.kava_test_img));
            mToolbarChainName.setText(getString(R.string.str_kava_net_test));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorKava));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorKava));
//            mFaucetBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorKava));

        } else if (mBaseChain.equals(IOV_TEST)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.iov_testnet_img));
            mToolbarChainName.setText(getString(R.string.str_iov_net_test));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorIov));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorIov));
//            mFaucetBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorIov));

        } else if (mBaseChain.equals(OK_TEST)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.okex_testnet_img));
            mToolbarChainName.setText(getString(R.string.str_ok_net_test));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorOK));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorOK));
//            mFaucetBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorOK));

        } else if (mBaseChain.equals(CERTIK_TEST)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.certik_testnet_img));
            mToolbarChainName.setText(getString(R.string.str_certik_chain_test));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorCertik));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorCertik));
//            mFaucetBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorCertik));

        } else if (mBaseChain.equals(RIZON_TEST)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.testnet_rizon));
            mToolbarChainName.setText(getString(R.string.str_rizon_chain_test));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorRizon));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorRizon));

        } else if (mBaseChain.equals(MEDI_TEST)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.testnet_medibloc));
            mToolbarChainName.setText(getString(R.string.str_medi_chain_test));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorMedi));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorMedi));

        } else if (mBaseChain.equals(ALTHEA_TEST)) {
            mToolbarChainImg.setImageDrawable(getResources().getDrawable(R.drawable.testnet_althea));
            mToolbarChainName.setText(getString(R.string.str_althea_chain_test));
            mToolbarChainName.setTextColor(getResources().getColor(R.color.colorAlthea));
            mFloatBtn.setBackgroundTintList(getResources().getColorStateList(R.color.colorAlthea));
            mFloatBtn.setImageTintList(getResources().getColorStateList(R.color.colorBlack));
        }

        onUpdateTitle();
        onFetchAllData();
        mSelectChainPosition = getBaseDao().getLastChain();
        onChainSelected(mSelectChainPosition);
    }

    private void onChainSelected(int position) {
        invalidateOptionsMenu();
        mSelectChainPosition = position;
        getBaseDao().setLastChain(mSelectChainPosition);
        mChainListAdapter.notifyItemRangeChanged(0, mChainListAdapter.getItemCount());
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
            if (mPageAdapter.mCurrentFragment != null) mPageAdapter.mCurrentFragment.onRefreshTab();
        }
    }

    @Override
    public void fetchBusy() {
        if(!isFinishing()) {
            onHideWaitDialog();
            mPageAdapter.mCurrentFragment.onBusyFetch();
        }
    }

    public void onClickEventHorizon() {
//        if (!mAccount.hasPrivateKey) {
//            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
//            add.setCancelable(true);
//            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
//            return;
//        }

        Dialog_Rizon_Event_Horizon add = Dialog_Rizon_Event_Horizon.newInstance();
        add.setCancelable(true);
        getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
    }

    public void onCheckRizonEventHorizon() {
        if (getBaseDao().mRizonSwapStatus.size() != 0) {
            Toast.makeText(MainActivity.this, R.string.error_already_rizon_swap, Toast.LENGTH_SHORT).show();
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, RizonSwapStatusActivity.class);
                    startActivity(intent);
                }
            },2000);
        } else {
            Intent intent = new Intent(MainActivity.this, EventHorizonActivity.class);
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

    private class ChainListAdapter extends RecyclerView.Adapter<ChainListAdapter.ChainHolder> {

        @NonNull
        @Override
        public ChainListAdapter.ChainHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            return new ChainListAdapter.ChainHolder(getLayoutInflater().inflate(R.layout.item_accountlist_chain, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ChainListAdapter.ChainHolder holder, @SuppressLint("RecyclerView") int position) {
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
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.chain_bandprotocal));
                    holder.chainName.setText(getString(R.string.str_band));

                } else if (chain.equals(CERTIK_MAIN)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.certik_chain_img));
                    holder.chainName.setText(getString(R.string.str_certik_main));

                } else if (chain.equals(SECRET_MAIN)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.chainsecret));
                    holder.chainName.setText(getString(R.string.str_secret_main));

                } else if (chain.equals(AKASH_MAIN)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.akash_chain_img));
                    holder.chainName.setText(getString(R.string.str_akash_main));

                } else if (chain.equals(OKEX_MAIN)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.okex_chain_img));
                    holder.chainName.setText(getString(R.string.str_okex_main));

                } else if (chain.equals(PERSIS_MAIN)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.chainpersistence));
                    holder.chainName.setText(getString(R.string.str_persis_main));

                } else if (chain.equals(SENTINEL_MAIN)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.chainsentinel));
                    holder.chainName.setText(getString(R.string.str_sentinel_main));

                } else if (chain.equals(FETCHAI_MAIN)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.chainfetchai));
                    holder.chainName.setText(getString(R.string.str_fetch_main));

                } else if (chain.equals(CRYPTO_MAIN)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.chaincrypto));
                    holder.chainName.setText(getString(R.string.str_crypto_main));

                } else if (chain.equals(SIF_MAIN)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.chainsifchain));
                    holder.chainName.setText(getString(R.string.str_sif_main));

                } else if (chain.equals(KI_MAIN)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.chain_kifoundation));
                    holder.chainName.setText(getString(R.string.str_ki_main));

                } else if (chain.equals(OSMOSIS_MAIN)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.chain_osmosis));
                    holder.chainName.setText(getString(R.string.str_osmosis_main));

                } else if (chain.equals(MEDI_MAIN)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.chainmedibloc));
                    holder.chainName.setText(getString(R.string.str_medi_main));

                }

                else if (chain.equals(COSMOS_TEST)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.chain_test_cosmos));
                    holder.chainName.setText(getString(R.string.str_cosmos_test));

                } else if (chain.equals(IRIS_TEST)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.chain_test_iris));
                    holder.chainName.setText(getString(R.string.str_iris_test));

                } else if (chain.equals(BNB_TEST)) {
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

                } else if (chain.equals(RIZON_TEST)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.testnet_rizon));
                    holder.chainName.setText(getString(R.string.str_rizon_test));

                } else if (chain.equals(MEDI_TEST)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.testnet_medibloc));
                    holder.chainName.setText(getString(R.string.str_medi_test));

                } else if (chain.equals(ALTHEA_TEST)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.testnet_althea));
                    holder.chainName.setText(getString(R.string.str_althea_test));

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
