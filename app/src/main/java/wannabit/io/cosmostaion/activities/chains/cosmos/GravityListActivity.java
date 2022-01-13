package wannabit.io.cosmostaion.activities.chains.cosmos;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import cosmos.base.v1beta1.CoinOuterClass;
import tendermint.liquidity.v1beta1.Liquidity;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_Pool_Gravity_Dex;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.fragment.chains.cosmos.GravityPoolListFragment;
import wannabit.io.cosmostaion.fragment.chains.cosmos.GravitySwapFragment;
import wannabit.io.cosmostaion.model.GDexManager;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.GravityDexManagerGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.GravityDexParamGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.GravityDexPoolGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.TotalSupplyGrpcTask;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_GDEX_DEPOSIT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_GDEX_SWAP;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_GDEX_WITHDRAW;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_GRAVITY_MANAGER;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_GRAVITY_PARAM;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_GRAVITY_POOL_LIST;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_TOTAL_SUPPLY;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ATOM;

public class GravityListActivity extends BaseActivity {

    private Toolbar                     mToolbar;
    private TextView                    mTitle;
    private ViewPager                   mLabPager;
    private TabLayout                   mLabTapLayer;
    private CosmosGravityPageAdapter    mPageAdapter;

    public ArrayList<Liquidity.Pool>                        mPoolList = new ArrayList<>();
    public ArrayList<String>                                mAllDenoms = new ArrayList<>();
    public ArrayList<Liquidity.Pool>                        mPoolMyList = new ArrayList<>();
    public ArrayList<Liquidity.Pool>                        mPoolOtherList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labs_list);
        mToolbar = findViewById(R.id.tool_bar);
        mTitle = findViewById(R.id.toolbar_title);
        mLabTapLayer = findViewById(R.id.lab_tab);
        mLabPager = findViewById(R.id.lab_view_pager);
        mTitle.setText(getString(R.string.str_gravity_dex));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);

        mPageAdapter = new CosmosGravityPageAdapter(getSupportFragmentManager());
        mLabPager.setAdapter(mPageAdapter);
        mLabTapLayer.setupWithViewPager(mLabPager);
        mLabTapLayer.setTabRippleColor(null);

        View tab0 = LayoutInflater.from(this).inflate(R.layout.view_tab_myvalidator, null);
        TextView tabItemText0 = tab0.findViewById(R.id.tabItemText);
        tabItemText0.setText(R.string.str_swap);
        tabItemText0.setTextColor(WDp.getTabColor(this, mBaseChain));
        mLabTapLayer.getTabAt(0).setCustomView(tab0);

        View tab1 = LayoutInflater.from(this).inflate(R.layout.view_tab_myvalidator, null);
        TextView tabItemText1 = tab1.findViewById(R.id.tabItemText);
        tabItemText1.setTextColor(WDp.getTabColor(this, mBaseChain));
        tabItemText1.setText(R.string.str_pool);
        mLabTapLayer.getTabAt(1).setCustomView(tab1);

        mLabPager.setOffscreenPageLimit(1);
        mLabPager.setCurrentItem(0, false);

        mLabPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) { }
            @Override
            public void onPageScrollStateChanged(int i) { }
            @Override
            public void onPageSelected(int i) {
                mPageAdapter.mFragments.get(i).onRefreshTab();
            }
        });
        onShowWaitDialog();
        onFetchPoolListInfo();

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

    public void onStartSwap(String inputCoinDenom, String outCoinDenom, Liquidity.Pool pool) {
        if (!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }

        BigDecimal available = getBaseDao().getAvailable(WDp.mainDenom(mBaseChain));
        BigDecimal txFee = WUtil.getEstimateGasFeeAmount(this, mBaseChain, CONST_PW_TX_GDEX_SWAP, 0);
        if (available.compareTo(txFee) < 0) {
            Toast.makeText(this, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(GravityListActivity.this, GravitySwapActivity.class);
        intent.putExtra("inputDenom", inputCoinDenom);
        intent.putExtra("outputDenom", outCoinDenom);
        intent.putExtra("gDexPool", pool);
        startActivity(intent);
    }

    public void onClickMyPool(long poolId) {
        WLog.w("onClickMyPool " + poolId);
        Bundle bundle = new Bundle();
        bundle.putLong("poolId", poolId);
        Dialog_Pool_Gravity_Dex bottomSheetDialog = Dialog_Pool_Gravity_Dex.getInstance();
        bottomSheetDialog.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(bottomSheetDialog, "dialog").commitNowAllowingStateLoss();

    }

    public void onCheckStartDepositPool(long poolId) {
        if (!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }

        Liquidity.Pool tempPool = null;
        for (Liquidity.Pool pool: mPoolList) {
            if (pool.getId() == poolId) { tempPool = pool; }
        }
        String coin0denom = tempPool.getReserveCoinDenoms(0);
        String coin1Denom = tempPool.getReserveCoinDenoms(1);

        BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(GravityListActivity.this, mBaseChain, CONST_PW_TX_GDEX_DEPOSIT, 0);
        BigDecimal coin0Available = getBaseDao().getAvailable(coin0denom);
        BigDecimal coin1Available = getBaseDao().getAvailable(coin1Denom);

        if (coin1Denom.equalsIgnoreCase(TOKEN_ATOM)) { coin1Available = coin1Available.subtract(feeAmount); }

        if (coin0Available.compareTo(BigDecimal.ZERO) <= 0 || coin1Available.compareTo(BigDecimal.ZERO) <=0 ) {
            Toast.makeText(GravityListActivity.this, R.string.error_not_enough_to_deposit_pool, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(getBaseContext(), GravityDepositPoolActivity.class);
        intent.putExtra("mPoolId", poolId);
        startActivity(intent);
    }

    public void onCheckStartWithdrawPool(long poolId) {
        WLog.w("onCheckStartExitPool " + poolId);
        if (!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }

        BigDecimal mainBalance = getBaseDao().getAvailable(TOKEN_ATOM);
        BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), mBaseChain, CONST_PW_TX_GDEX_WITHDRAW, 0);

        if (mainBalance.compareTo(feeAmount) < 0) {
            Toast.makeText(getBaseContext(), R.string.error_not_enough_to_withdraw_pool, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(getBaseContext(), GravityWithdrawPoolActivity.class);
        intent.putExtra("mPoolId", poolId);
        startActivity(intent);
    }

    public void onFetchPoolListInfo() {
        mTaskCount = 3;
        mPoolList .clear();
        mPoolMyList.clear();
        mPoolOtherList.clear();
        getBaseDao().mGDexPoolTokens.clear();
        new GravityDexPoolGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new GravityDexParamGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new TotalSupplyGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (isFinishing()) return;
        mTaskCount--;
        if (result.taskType == TASK_GRPC_FETCH_GRAVITY_POOL_LIST) {
            if (result.isSuccess && result.resultData != null) {
                for (Liquidity.Pool pool : getBaseDao().mGrpcGravityPools) {
                    if (getBaseDao().mChainParam.isPoolEnabled(pool.getId())) {
                        mPoolList.add(pool);
                    }
                }
            }
            mTaskCount = mTaskCount + 1;
            for (Liquidity.Pool pool: mPoolList ) {
                new GravityDexManagerGrpcTask(getBaseApplication(), this, mBaseChain, pool.getReserveAccountAddress()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                if (getBaseDao().getAvailable(pool.getPoolCoinDenom()) != BigDecimal.ZERO) {
                    mPoolMyList.add(pool);
                } else {
                    mPoolOtherList.add(pool);
                }
            }

        } else if (result.taskType == TASK_GRPC_FETCH_GRAVITY_PARAM) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mParams = (Liquidity.Params) result.resultData;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_GRAVITY_MANAGER) {
            if (result.isSuccess && result.resultData != null && result.resultData2 != null) {
                getBaseDao().mGDexManager.add(new GDexManager(result.resultData2, (List<CoinOuterClass.Coin>) result.resultData));
            }

        } else if (result.taskType == TASK_GRPC_FETCH_TOTAL_SUPPLY) {
            if (result.isSuccess && result.resultData != null) {
                ArrayList<CoinOuterClass.Coin> supplys = (ArrayList<CoinOuterClass.Coin>) result.resultData;
                for (CoinOuterClass.Coin coin: supplys) {
                    if (coin.getDenom().startsWith("pool")) {
                        getBaseDao().mGDexPoolTokens.add((new Coin(coin.getDenom(), coin.getAmount())));
                    }
                }
            }
        }
        if (mTaskCount == 0) {
            mAllDenoms.add(TOKEN_ATOM);
            for (Liquidity.Pool pool: mPoolList ) {
                for (String denom: pool.getReserveCoinDenomsList()) {
                    if (!mAllDenoms.contains(denom)) {
                        mAllDenoms.add(denom);
                    }
                }
            }
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    onHideWaitDialog();
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                }
            }, 300);
        }
    }

    public GDexManager getGDexManager(String address) {
        for (GDexManager gDexManager: getBaseDao().mGDexManager) {
            if (gDexManager.address.equalsIgnoreCase(address)) {
                return gDexManager;
            }
        }
        return null;
    }

    public BigDecimal getLpAmount (String address, String denom) {
        BigDecimal result = BigDecimal.ZERO;
        if (getGDexManager(address) != null) {
            for (Coin coin: getGDexManager(address).reserve) {
                if (coin.denom.equalsIgnoreCase(denom)) {
                    result = new BigDecimal(coin.amount);
                }
            }
        }
        return result;
    }

    public BigDecimal getGdexPoolValue(Liquidity.Pool pool) {
        String coin0Denom = pool.getReserveCoinDenoms(0);
        String coin1Denom = pool.getReserveCoinDenoms(1);
        String coin0BaseDenom = getBaseDao().getBaseDenom(coin0Denom);
        String coin1BaseDenom = getBaseDao().getBaseDenom(coin1Denom);
        BigDecimal coin0Amount = getLpAmount(pool.getReserveAccountAddress(), coin0Denom);
        BigDecimal coin1Amount = getLpAmount(pool.getReserveAccountAddress(), coin1Denom);
        int coin0Decimal = WUtil.getCosmosCoinDecimal(getBaseDao(), coin0Denom);
        int coin1Decimal = WUtil.getCosmosCoinDecimal(getBaseDao(), coin1Denom);
        BigDecimal coin0Price = BigDecimal.ZERO;
        BigDecimal coin1Price = BigDecimal.ZERO;
        if (coin0BaseDenom != null) {
            coin0Price = WDp.perUsdValue(getBaseDao(), coin0BaseDenom);
        }
        if (coin1BaseDenom != null) {
            coin1Price = WDp.perUsdValue(getBaseDao(), coin1BaseDenom);
        }
        BigDecimal coin0Value = coin0Amount.multiply(coin0Price).movePointLeft(coin0Decimal).setScale(2, RoundingMode.DOWN);
        BigDecimal coin1Value = coin1Amount.multiply(coin1Price).movePointLeft(coin1Decimal).setScale(2, RoundingMode.DOWN);
        return coin0Value.add(coin1Value);
    }

    public BigDecimal getGdexLpTokenPerUsdPrice(Liquidity.Pool pool) {
        BigDecimal poolValue = getGdexPoolValue(pool);
        BigDecimal totalShare = BigDecimal.ZERO;
        for (Coin coin: getBaseDao().mGDexPoolTokens) {
            if (coin.denom.equalsIgnoreCase(pool.getPoolCoinDenom())) {
                totalShare = new BigDecimal(coin.amount);
            }
        }
        return poolValue.divide(totalShare.movePointLeft(6).setScale(24, RoundingMode.DOWN), 18, RoundingMode.DOWN);
    }

    public BigDecimal getGdexMyShareAmount(Liquidity.Pool pool, String denom) {
        BigDecimal result = BigDecimal.ZERO;
        BigDecimal myShare = getBaseDao().getAvailable(pool.getPoolCoinDenom());
        BigDecimal totalShare = BigDecimal.ZERO;
        for (Coin coin: getBaseDao().mGDexPoolTokens) {
            if (coin.denom.equalsIgnoreCase(pool.getPoolCoinDenom())) {
                totalShare = new BigDecimal(coin.amount);
            }
        }
        BigDecimal coinAmount = getLpAmount(pool.getReserveAccountAddress(), denom);
        result = coinAmount.multiply(myShare).divide(totalShare, 18, RoundingMode.DOWN);
        return result;
    }

    public BigDecimal getGdexMyShareValue(Liquidity.Pool pool) {
        BigDecimal lpPrice = getGdexLpTokenPerUsdPrice(pool);
        BigDecimal myShare = getBaseDao().getAvailable(pool.getPoolCoinDenom());
        return myShare.movePointLeft(6).setScale(6, RoundingMode.DOWN).multiply(lpPrice).setScale(18, RoundingMode.DOWN);
    }

    private class CosmosGravityPageAdapter extends FragmentPagerAdapter {
        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public CosmosGravityPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(GravitySwapFragment.newInstance(null));
            mFragments.add(GravityPoolListFragment.newInstance(null));
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
