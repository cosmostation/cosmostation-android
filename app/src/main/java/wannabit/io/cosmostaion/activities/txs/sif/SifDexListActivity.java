package wannabit.io.cosmostaion.activities.txs.sif;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIF_EXIT_POOL;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIF_JOIN_POOL;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIF_SWAP;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_SIF_POOL_ASSET_LIST;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_SIF_POOL_LIST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SIF;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
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
import java.util.ArrayList;

import sifnode.clp.v1.Querier;
import sifnode.clp.v1.Types;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.AlertDialogUtils;
import wannabit.io.cosmostaion.dialog.PaddedVerticalButtonAlertDialog;
import wannabit.io.cosmostaion.fragment.txs.sif.SifDexEthPoolFragment;
import wannabit.io.cosmostaion.fragment.txs.sif.SifDexIbcPoolFragment;
import wannabit.io.cosmostaion.fragment.txs.sif.SifDexSwapFragment;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.SifDexPoolAssetListGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.SifDexPoolListGrpcTask;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class SifDexListActivity extends BaseActivity {

    private Toolbar mToolbar;
    private ViewPager mLabPager;
    private TabLayout mLabTapLayer;
    private SifDexPageAdapter mPageAdapter;

    public ArrayList<Types.Pool> mPoolList = new ArrayList<>();
    public ArrayList<String> mAllDenoms = new ArrayList<>();
    public ArrayList<Types.Asset> mPoolMyAsset = new ArrayList<>();
    public ArrayList<String> mMyEthAssets = new ArrayList<>();
    public ArrayList<String> mMyIbcAssets = new ArrayList<>();

    public ArrayList<Types.Pool> mMyEthPools = new ArrayList<>();
    public ArrayList<Types.Pool> mOtherEthPools = new ArrayList<>();
    public ArrayList<Types.Pool> mMyIbcPools = new ArrayList<>();
    public ArrayList<Types.Pool> mOtherIbcPools = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sif_dex_list);
        mToolbar = findViewById(R.id.tool_bar);
        mLabTapLayer = findViewById(R.id.lab_tab);
        mLabPager = findViewById(R.id.lab_view_pager);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);

        mPageAdapter = new SifDexPageAdapter(getSupportFragmentManager());
        mLabPager.setAdapter(mPageAdapter);
        mLabTapLayer.setupWithViewPager(mLabPager);
        mLabTapLayer.setTabRippleColor(null);

        View tab0 = LayoutInflater.from(this).inflate(R.layout.view_tab_myvalidator, null);
        TextView tabItemText0 = tab0.findViewById(R.id.tabItemText);
        tabItemText0.setText(R.string.str_sif_dex_swap);
        tabItemText0.setTextColor(WDp.getTabColor(this, mBaseChain));
        mLabTapLayer.getTabAt(0).setCustomView(tab0);

        View tab1 = LayoutInflater.from(this).inflate(R.layout.view_tab_myvalidator, null);
        TextView tabItemText1 = tab1.findViewById(R.id.tabItemText);
        tabItemText1.setTextColor(WDp.getTabColor(this, mBaseChain));
        tabItemText1.setText(R.string.str_sif_dex_eth_pol);
        mLabTapLayer.getTabAt(1).setCustomView(tab1);

        View tab2 = LayoutInflater.from(this).inflate(R.layout.view_tab_myvalidator, null);
        TextView tabItemText2 = tab2.findViewById(R.id.tabItemText);
        tabItemText2.setTextColor(WDp.getTabColor(this, mBaseChain));
        tabItemText2.setText(R.string.str_sif_dex_ibc_pool);
        mLabTapLayer.getTabAt(2).setCustomView(tab2);

        mLabPager.setOffscreenPageLimit(2);
        mLabPager.setCurrentItem(0, false);

        mLabPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }

            @Override
            public void onPageSelected(int i) {
                mPageAdapter.mFragments.get(i).onRefreshTab();
            }
        });
        onShowWaitDialog();
        onFetchPoolListInfo();
    }

    public void onStartSwap(String inCoinDenom, String outCoinDenom, Types.Pool pool) {
        if (!mAccount.hasPrivateKey) {
            AlertDialogUtils.showDoubleButtonDialog(this, getString(R.string.str_only_observe_title), getString(R.string.str_only_observe_msg),
                    Html.fromHtml("<font color=\"#9C6CFF\">" + getString(R.string.str_add_mnemonics) + "</font>"), view -> onAddMnemonicForAccount(),
                    getString(R.string.str_close), null);
            return;
        }

        BigDecimal available = getBaseDao().getAvailable(WDp.mainDenom(mBaseChain));
        BigDecimal txFee = WUtil.getEstimateGasFeeAmount(this, mBaseChain, CONST_PW_TX_SIF_SWAP, 0);
        if (available.compareTo(txFee) < 0) {
            Toast.makeText(this, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
            return;
        }

        BigDecimal inputBalance = getBaseDao().getAvailable(inCoinDenom);
        if (BigDecimal.ZERO.compareTo(inputBalance) >= 0) {
            Toast.makeText(this, R.string.error_not_enough_balance_to_vote, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(SifDexListActivity.this, SifSwapActivity.class);
        intent.putExtra("inputDenom", inCoinDenom);
        intent.putExtra("outputDenom", outCoinDenom);
        intent.putExtra("sifPool", pool);
        startActivity(intent);
    }

    public void onClickMyPool(Types.Pool pool, Querier.LiquidityProviderRes myProvider) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("pool", pool);
        bundle.putSerializable("myProvider", myProvider);
        PaddedVerticalButtonAlertDialog.showDoubleButton(this, null, null,
                getString(R.string.str_title_pool_join), view -> onCheckStartDepositPool(pool),
                getString(R.string.str_title_pool_exit), view -> onCheckStartWithdrawPool(pool, myProvider));
    }

    public void onCheckStartDepositPool(Types.Pool pool) {
        if (!mAccount.hasPrivateKey) {
            AlertDialogUtils.showDoubleButtonDialog(this, getString(R.string.str_only_observe_title), getString(R.string.str_only_observe_msg),
                    Html.fromHtml("<font color=\"#9C6CFF\">" + getString(R.string.str_add_mnemonics) + "</font>"), view -> onAddMnemonicForAccount(),
                    getString(R.string.str_close), null);
            return;
        }

        BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(SifDexListActivity.this, mBaseChain, CONST_PW_TX_SIF_JOIN_POOL, 0);
        String externalDenom = pool.getExternalAsset().getSymbol();
        BigDecimal rowanAvailable = getBaseDao().getAvailable(TOKEN_SIF);
        rowanAvailable = rowanAvailable.subtract(feeAmount);
        BigDecimal externalAvailable = getBaseDao().getAvailable(externalDenom);

        if (rowanAvailable.compareTo(BigDecimal.ZERO) <= 0 || externalAvailable.compareTo(BigDecimal.ZERO) <= 0) {
            Toast.makeText(SifDexListActivity.this, R.string.error_not_enough_to_deposit_pool, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(getBaseContext(), SifDepositPoolActivity.class);
        intent.putExtra("mSifPool", pool);
        startActivity(intent);
    }

    public void onCheckStartWithdrawPool(Types.Pool pool, Querier.LiquidityProviderRes myProvider) {
        if (!mAccount.hasPrivateKey) {
            AlertDialogUtils.showDoubleButtonDialog(this, getString(R.string.str_only_observe_title), getString(R.string.str_only_observe_msg),
                    Html.fromHtml("<font color=\"#9C6CFF\">" + getString(R.string.str_add_mnemonics) + "</font>"), view -> onAddMnemonicForAccount(),
                    getString(R.string.str_close), null);
            return;
        }

        BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(SifDexListActivity.this, mBaseChain, CONST_PW_TX_SIF_EXIT_POOL, 0);
        BigDecimal rowanAvailable = getBaseDao().getAvailable(TOKEN_SIF);
        rowanAvailable = rowanAvailable.subtract(feeAmount);

        if (rowanAvailable.compareTo(BigDecimal.ZERO) <= 0) {
            Toast.makeText(SifDexListActivity.this, R.string.error_not_enough_to_withdraw_pool, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(getBaseContext(), SifWithdrawPoolActivity.class);
        intent.putExtra("mSifPool", pool);
        intent.putExtra("myProvider", myProvider);
        startActivity(intent);
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

    public void onFetchPoolListInfo() {
        mTaskCount = 2;
        mPoolList = new ArrayList<>();
        mPoolMyAsset = new ArrayList<>();
        mMyEthPools = new ArrayList<>();
        mOtherEthPools = new ArrayList<>();
        mMyIbcPools = new ArrayList<>();
        mOtherIbcPools = new ArrayList<>();
        new SifDexPoolListGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new SifDexPoolAssetListGrpcTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (isFinishing()) return;
        mTaskCount--;
        if (result.taskType == TASK_GRPC_FETCH_SIF_POOL_LIST) {
            if (result.isSuccess && result.resultData != null) {
                final ArrayList<Types.Pool> tempPoolList = (ArrayList<Types.Pool>) result.resultData;
                for (Types.Pool pool : tempPoolList) {
                    if (!pool.getExternalAsset().getSymbol().equalsIgnoreCase("ccro"))
                        mPoolList.add(pool);
                }
            }

        } else if (result.taskType == TASK_GRPC_FETCH_SIF_POOL_ASSET_LIST) {
            if (result.isSuccess && result.resultData != null) {
                mPoolMyAsset = (ArrayList<Types.Asset>) result.resultData;
                for (Types.Asset asset : mPoolMyAsset) {
                    if (!asset.getSymbol().startsWith("ibc/")) {
                        mMyEthAssets.add(asset.getSymbol());
                    } else {
                        mMyIbcAssets.add(asset.getSymbol());
                    }
                }
            }

        }
        if (mTaskCount == 0) {
            mAllDenoms.add(TOKEN_SIF);
            for (Types.Pool pool : mPoolList) {
                if (!mAllDenoms.contains(pool.getExternalAsset().getSymbol())) {
                    mAllDenoms.add(pool.getExternalAsset().getSymbol());
                }
                if (!pool.getExternalAsset().getSymbol().startsWith("ibc/")) {
                    if (mMyEthAssets.contains(pool.getExternalAsset().getSymbol())) {
                        mMyEthPools.add(pool);
                    } else {
                        mOtherEthPools.add(pool);
                    }
                } else {
                    if (mMyIbcAssets.contains(pool.getExternalAsset().getSymbol())) {
                        mMyIbcPools.add(pool);
                    } else {
                        mOtherIbcPools.add(pool);
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

    private class SifDexPageAdapter extends FragmentPagerAdapter {
        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public SifDexPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(SifDexSwapFragment.newInstance(null));
            mFragments.add(SifDexEthPoolFragment.newInstance(null));
            mFragments.add(SifDexIbcPoolFragment.newInstance(null));
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

