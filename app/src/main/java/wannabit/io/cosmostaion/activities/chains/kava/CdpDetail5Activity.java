package wannabit.io.cosmostaion.activities.chains.kava;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.model.kava.CdpDeposit;
import wannabit.io.cosmostaion.model.kava.CdpParam;
import wannabit.io.cosmostaion.model.kava.CollateralParam;
import wannabit.io.cosmostaion.model.kava.MyCdp;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.task.FetchTask.KavaCdpByDepositorTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaCdpByOwnerTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaTotalSupplyTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.BaseHolder;
import wannabit.io.cosmostaion.widget.CdpDetailInfoHolder;
import wannabit.io.cosmostaion.widget.CdpDetailMyAvailableHolder;
import wannabit.io.cosmostaion.widget.CdpDetailMyStatusHolder;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_CDP_DEPOSIT;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_CDP_OWENER;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_TOTAL_SUPPLY;

public class CdpDetail5Activity extends BaseActivity implements TaskListener, View.OnClickListener {
    private Toolbar             mToolbar;
    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private RecyclerView        mRecyclerView;
    private Button              mBtnOpenCdp;
    private RelativeLayout      mLoadingLayer;

    private Account             mAccount;
    private BaseChain           mBaseChain;
    private CdpDetailAdapter    mAdapter;


    private String              mCollateralType;
    private CdpParam            mCdpParam;
    private MyCdp               mMyCdp;
    private BigDecimal          mDebtAmount = BigDecimal.ZERO;
    private BigDecimal          mSelfDepositAmount = BigDecimal.ZERO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cdp_detail5);
        mToolbar                        = findViewById(R.id.tool_bar);
        mSwipeRefreshLayout             = findViewById(R.id.layer_refresher);
        mRecyclerView                   = findViewById(R.id.recycler);
        mLoadingLayer                   = findViewById(R.id.loadingLayer);
        mBtnOpenCdp                     = findViewById(R.id.btn_open_cdp);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);

        mCdpParam = getBaseDao().mCdpParam;
        mCollateralType = getIntent().getStringExtra("collateralParamType");

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mTaskCount > 0) {
                    mSwipeRefreshLayout.setRefreshing(false);
                } else {
                    onFetchCdpData();
                }
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new CdpDetailAdapter();
        mRecyclerView.setAdapter(mAdapter);

        onFetchCdpData();
        mBtnOpenCdp.setOnClickListener(this);
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

    private int mTaskCount = 0;
    public void onFetchCdpData() {
        mTaskCount = 3;
        mMyCdp = null;
        new KavaCdpByOwnerTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain), mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaCdpByDepositorTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain), mAccount.address, mCollateralType).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaTotalSupplyTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(isFinishing()) return;
        mTaskCount--;
        if (result.taskType == TASK_FETCH_KAVA_CDP_OWENER) {
            if (result.isSuccess && result.resultData != null) {
                ArrayList<MyCdp> myCdps = (ArrayList<MyCdp>)result.resultData;
                for (MyCdp myCdp: myCdps) {
                    if (myCdp.cdp.type.equals(mCollateralType)) {
                        mMyCdp = myCdp;
                        break;
                    }
                }
            }

        }  else if (result.taskType == TASK_FETCH_KAVA_CDP_DEPOSIT) {
            if (result.isSuccess && result.resultData != null) {
                ArrayList<CdpDeposit> deposits = (ArrayList<CdpDeposit>)result.resultData;
                for (CdpDeposit deposit: deposits) {
                    if (deposit.depositor.equals(mAccount.address)) {
                        mSelfDepositAmount =  new BigDecimal(deposit.amount.amount);
                    }
                }
            }

        } else if (result.taskType == TASK_FETCH_KAVA_TOTAL_SUPPLY) {
            if (result.isSuccess && result.resultData != null) {
                ArrayList<Coin> supplies = (ArrayList<Coin>)result.resultData;
                for (Coin coin: supplies) {
                    if (coin.denom.equals("debt")) {
                        mDebtAmount = new BigDecimal(coin.amount);
                        break;
                    }
                }
            }

        }

        if (mTaskCount == 0) {
            if (mMyCdp == null) {
                mBtnOpenCdp.setVisibility(View.VISIBLE);
            } else {
                mBtnOpenCdp.setVisibility(View.GONE);
            }

            mAdapter.notifyDataSetChanged();
            mLoadingLayer.setVisibility(View.GONE);
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }


    @Override
    public void onClick(View v) {
        if (v.equals(mBtnOpenCdp)) {
            onCheckStartCreateCdp();
        }

    }

    public void onCheckStartCreateCdp() {
        if (!onCommonCheck()) return;

        final CollateralParam collateralParam   = mCdpParam.getCollateralParamByType(mCollateralType);
        final String cDenom                     = collateralParam.denom;
        final String pDenom                     = collateralParam.debt_limit.denom;
        final BigDecimal currentPrice           = new BigDecimal(getBaseDao().mKavaTokenPrices.get(collateralParam.liquidation_market_id).price);
        final BigDecimal cAvailable             = WUtil.getTokenBalance(getBaseDao().mBalances, cDenom) == null ? BigDecimal.ZERO : WUtil.getTokenBalance(getBaseDao().mBalances, cDenom).balance;
        BigDecimal principalMinAmount           = new BigDecimal(mCdpParam.debt_param.debt_floor);
        BigDecimal collateralMinAmount          = principalMinAmount.movePointLeft(WUtil.getKavaCoinDecimal(pDenom) - WUtil.getKavaCoinDecimal(cDenom)).multiply(new BigDecimal("1.05263157895")).multiply(new BigDecimal(collateralParam.liquidation_ratio)).divide(currentPrice, 0, RoundingMode.UP);
        if (collateralMinAmount.compareTo(cAvailable) > 0) {
            Toast.makeText(getBaseContext(), R.string.error_less_than_min_deposit, Toast.LENGTH_SHORT).show();
            return;
        }
        if (mCdpParam.getGlobalDebtAmount().compareTo(mDebtAmount) <= 0) {
            Toast.makeText(getBaseContext(), R.string.error_no_more_debt_kava, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, CreateCdpActivity.class);
        intent.putExtra("collateralParamType", mCollateralType);
        intent.putExtra("marketId", collateralParam.liquidation_market_id);
        startActivity(intent);
    }

    public void onCheckStartDepositCdp() {
        if (!onCommonCheck()) return;

        final CollateralParam collateralParam   = mCdpParam.getCollateralParamByType(mCollateralType);
        final String cDenom                     = collateralParam.denom;
        final BigDecimal cAvailable             = WUtil.getTokenBalance(getBaseDao().mBalances, cDenom) == null ? BigDecimal.ZERO : WUtil.getTokenBalance(getBaseDao().mBalances, cDenom).balance;
        if (cAvailable.compareTo(BigDecimal.ZERO) <= 0) {
            Toast.makeText(getBaseContext(), R.string.error_not_enought_deposit_asset, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, DepositCdpActivity.class);
        intent.putExtra("collateralParamType", mCollateralType);
        intent.putExtra("marketId", collateralParam.liquidation_market_id);
        startActivity(intent);
    }

    public void onCheckStartWithdrawCdp() {
        if (!onCommonCheck()) return;

        final CollateralParam collateralParam   = mCdpParam.getCollateralParamByType(mCollateralType);
        final BigDecimal currentPrice           = new BigDecimal(getBaseDao().mKavaTokenPrices.get(collateralParam.liquidation_market_id).price);
        final BigDecimal maxWithdrawableAmount  = mMyCdp.getWithdrawableAmount(getBaseContext(), collateralParam, currentPrice, mSelfDepositAmount);
        if (maxWithdrawableAmount.compareTo(BigDecimal.ZERO) <= 0) {
            Toast.makeText(getBaseContext(), R.string.error_not_enought_withdraw_asset, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, WithdrawCdpActivity.class);
        intent.putExtra("collateralParamType", mCollateralType);
        intent.putExtra("marketId", collateralParam.liquidation_market_id);
        startActivity(intent);
    }

    public void onCheckStartDrawDebtCdp() {
        if (!onCommonCheck()) return;

        final CollateralParam collateralParam   = mCdpParam.getCollateralParamByType(mCollateralType);
        if (mMyCdp.getMoreLoanableAmount(getBaseContext(), collateralParam).compareTo(BigDecimal.ZERO) <= 0) {
            Toast.makeText(getBaseContext(), R.string.error_can_not_draw_debt, Toast.LENGTH_SHORT).show();
            return;
        }
        if (mCdpParam.getGlobalDebtAmount().compareTo(mDebtAmount) <= 0) {
            Toast.makeText(getBaseContext(), R.string.error_no_more_debt_kava, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, BorrowCdpActivity.class);
        intent.putExtra("collateralParamType", mCollateralType);
        intent.putExtra("marketId", collateralParam.liquidation_market_id);
        startActivity(intent);

    }

    public void onCheckStartRepayCdp() {
        if (!onCommonCheck()) return;

        final CollateralParam collateralParam   = mCdpParam.getCollateralParamByType(mCollateralType);
        final String pDenom                     = collateralParam.debt_limit.denom;
        final BigDecimal pAvailable             = WUtil.getTokenBalance(getBaseDao().mBalances, pDenom) == null ? BigDecimal.ZERO : WUtil.getTokenBalance(getBaseDao().mBalances, pDenom).balance;

        if (pAvailable.compareTo(BigDecimal.ZERO) <= 0) {
            Toast.makeText(getBaseContext(), R.string.error_not_enought_principal_asset, Toast.LENGTH_SHORT).show();
            return;
        }
        boolean repayAll = true;
        boolean repayPart = true;
        BigDecimal debtFloor = new BigDecimal(mCdpParam.debt_param.debt_floor);
        BigDecimal rawDebt =  mMyCdp.getPrincipalAmount();
        BigDecimal totalDebt =  mMyCdp.getEstimatedTotalDebt(getBaseContext(), collateralParam);
        if (totalDebt.compareTo(pAvailable) > 0) { repayAll = false; }
        if (rawDebt.compareTo(debtFloor) <= 0) { repayPart = false; }
        if (!repayAll && !repayPart) {
            Toast.makeText(getBaseContext(), R.string.error_can_not_repay, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, RepayCdpActivity.class);
        intent.putExtra("collateralParamType", mCollateralType);
        intent.putExtra("marketId", collateralParam.liquidation_market_id);
        startActivity(intent);
    }


    private boolean onCommonCheck() {
        if(!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return false;
        }

        if (mCdpParam.circuit_breaker) {
            Toast.makeText(getBaseContext(), R.string.error_circuit_breaker, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private class CdpDetailAdapter extends RecyclerView.Adapter<BaseHolder> {
        private static final int TYPE_CDP_INFO          = 0;
        private static final int TYPE_MY_STATUS         = 1;
        private static final int TYPE_MY_AVAILABLE      = 2;

        @NonNull
        @Override
        public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_CDP_INFO) {
                return new CdpDetailInfoHolder(getLayoutInflater().inflate(R.layout.item_cdp_detail_info, viewGroup, false));
            } else if (viewType == TYPE_MY_STATUS) {
                return new CdpDetailMyStatusHolder(getLayoutInflater().inflate(R.layout.item_cdp_detail_my_status, viewGroup, false));
            } else if (viewType == TYPE_MY_AVAILABLE) {
                return new CdpDetailMyAvailableHolder(getLayoutInflater().inflate(R.layout.item_cdp_detail_my_available, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull BaseHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_CDP_INFO) {
                viewHolder.onBindCdpDetailInfo(CdpDetail5Activity.this, getBaseDao(), mMyCdp, mCollateralType, mDebtAmount);

            } else if (getItemViewType(position) == TYPE_MY_STATUS) {
                viewHolder.onBindCdpDetailMyStatus(CdpDetail5Activity.this, getBaseDao(), mMyCdp, mCollateralType, mSelfDepositAmount);

            } else if (getItemViewType(position) == TYPE_MY_AVAILABLE) {
                viewHolder.onBindCdpDetailAvailable(CdpDetail5Activity.this, getBaseDao(), mCollateralType);
            }
        }

        @Override
        public int getItemCount() {
            if (mMyCdp == null) { return 2; }
            else { return 3; }
        }

        @Override
        public int getItemViewType(int position) {
            if (mMyCdp == null) {
                if (position == 0) {
                    return TYPE_CDP_INFO;
                } else {
                    return TYPE_MY_AVAILABLE;
                }

            } else {
                if (position == 0) {
                    return TYPE_CDP_INFO;
                } else if (position == 1) {
                    return TYPE_MY_STATUS;
                } else {
                    return TYPE_MY_AVAILABLE;
                }
            }
        }
    }
}
