package wannabit.io.cosmostaion.activities.chains.kava;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.model.kava.HardInterestRate;
import wannabit.io.cosmostaion.model.kava.HardMyBorrow;
import wannabit.io.cosmostaion.model.kava.HardMyDeposit;
import wannabit.io.cosmostaion.model.kava.HardParam;
import wannabit.io.cosmostaion.model.kava.IncentiveReward;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.task.FetchTask.KavaHardInterestRateTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaHardModuleAccountTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaHardMyBorrowTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaHardMyDepositTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaHardReservesTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaHardTotalBorrowTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaHardTotalDepositTask;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.BaseHolder;
import wannabit.io.cosmostaion.widget.HardDetailInfoHolder;
import wannabit.io.cosmostaion.widget.HardDetailMyAvailableHolder;
import wannabit.io.cosmostaion.widget.HardDetailMyStatusHolder;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_HARD_INTEREST_RATE;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_HARD_MODULE_ACCOUNT;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_HARD_MY_BORROW;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_HARD_MY_DEPOSIT;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_HARD_RESERVES;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_HARD_TOTAL_BORROW;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_HARD_TOTAL_DEPOIST;

public class HardDetailActivity extends BaseActivity {
    private Toolbar             mToolbar;
    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private RecyclerView        mRecyclerView;
    private RelativeLayout      mLoadingLayer;

    private HardDetailAdapter   mAdapter;
    private Account             mAccount;
    private BaseChain           mBaseChain;

    private String                          mHardMoneyMarketDenom;
    private HardParam                       mHardParam;
    private HardParam.HardMoneyMarket       mHardMoneyMarket;
    private ArrayList<HardInterestRate>     mInterestRates = new ArrayList<>();
    private ArrayList<Coin>                 mModuleCoins = new ArrayList<>();
    private ArrayList<Coin>                 mReserveCoins = new ArrayList<>();
    private ArrayList<Coin>                 mTotalDeposit = new ArrayList<>();
    private ArrayList<Coin>                 mTotalBorrow = new ArrayList<>();
    private ArrayList<HardMyDeposit>        mMyDeposit = new ArrayList<>();
    private ArrayList<HardMyBorrow>         mMyBorrow = new ArrayList<>();
    private IncentiveReward                 mIncentiveRewards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_detail);
        mToolbar                        = findViewById(R.id.tool_bar);
        mSwipeRefreshLayout             = findViewById(R.id.layer_refresher);
        mRecyclerView                   = findViewById(R.id.recycler);
        mLoadingLayer                   = findViewById(R.id.loadingLayer);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount                = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain              = BaseChain.getChain(mAccount.baseChain);
        mHardMoneyMarketDenom   = getIntent().getStringExtra("hard_money_market_denom");
        mHardParam              = getBaseDao().mHardParam;
        mIncentiveRewards       = getBaseDao().mIncentiveRewards;
        mHardMoneyMarket        = mHardParam.getHardMoneyMarket(mHardMoneyMarketDenom);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mTaskCount > 0) {
                    mSwipeRefreshLayout.setRefreshing(false);
                } else {
                    onFetchHardInfo();
                }
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new HardDetailAdapter();
        mRecyclerView.setAdapter(mAdapter);

        onFetchHardInfo();
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
    public void onFetchHardInfo() {
        mModuleCoins.clear();
        mReserveCoins.clear();
        mInterestRates.clear();
        mTotalDeposit.clear();
        mTotalBorrow.clear();
        mMyDeposit.clear();
        mMyBorrow.clear();
        mTaskCount = 7;
        new KavaHardInterestRateTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaHardModuleAccountTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaHardReservesTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaHardTotalDepositTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaHardTotalBorrowTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaHardMyDepositTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaHardMyBorrowTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(isFinishing()) return;
        mTaskCount--;
        if (result.taskType == TASK_FETCH_KAVA_HARD_INTEREST_RATE) {
            if (result.isSuccess && result.resultData != null) {
                mInterestRates = (ArrayList<HardInterestRate>)result.resultData;
            }

        } else if (result.taskType == TASK_FETCH_KAVA_HARD_MODULE_ACCOUNT) {
            if (result.isSuccess && result.resultData != null) {
                mModuleCoins = (ArrayList<Coin>)result.resultData;
                getBaseDao().mModuleCoins = mModuleCoins;
            }

        } else if (result.taskType == TASK_FETCH_KAVA_HARD_RESERVES) {
            if (result.isSuccess && result.resultData != null) {
                mReserveCoins = (ArrayList<Coin>)result.resultData;
                getBaseDao().mReserveCoins = mReserveCoins;
            }

        } else if (result.taskType == TASK_FETCH_KAVA_HARD_TOTAL_DEPOIST) {
            if (result.isSuccess && result.resultData != null) {
                mTotalDeposit = (ArrayList<Coin>)result.resultData;
            }

        } else if (result.taskType == TASK_FETCH_KAVA_HARD_TOTAL_BORROW) {
            if (result.isSuccess && result.resultData != null) {
                mTotalBorrow = (ArrayList<Coin>)result.resultData;
            }

        } else if (result.taskType == TASK_FETCH_KAVA_HARD_MY_DEPOSIT) {
            if (result.isSuccess && result.resultData != null) {
                mMyDeposit = (ArrayList<HardMyDeposit>)result.resultData;
                getBaseDao().mMyHardDeposit = mMyDeposit;
            }

        } else if (result.taskType == TASK_FETCH_KAVA_HARD_MY_BORROW) {
            if (result.isSuccess && result.resultData != null) {
                mMyBorrow = (ArrayList<HardMyBorrow>)result.resultData;
                getBaseDao().mMyHardBorrow = mMyBorrow;
            }
        }

        if (mTaskCount == 0) {
            mAdapter.notifyDataSetChanged();
            mLoadingLayer.setVisibility(View.GONE);
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }



//    public boolean hasHardPool() {
//        boolean result = false;
//        if (mMyDeposit != null && mMyDeposit.size() > 0) {
//            for (Coin coin: mMyDeposit.get(0).amount) {
//                if (coin.denom.equals(mHardMoneyMarket.denom)) {
//                    result = true;
//                }
//            }
//        }
//
//        return result;
//    }

    public void onHardDeposit() {
        if (!onCommonCheck()) return;
        if ((getBaseDao().availableAmount(mHardMoneyMarketDenom)).compareTo(BigDecimal.ZERO) <= 0) {
            Toast.makeText(getBaseContext(), R.string.error_no_available_to_deposit, Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, DepositHardActivity.class);
        intent.putExtra("hardPoolDemon", mHardMoneyMarketDenom);
        startActivity(intent);

    }

    public void onHardWithdraw() {
        if (!onCommonCheck()) return;
        if (WUtil.getHardSuppliedValueByDenom(this, getBaseDao(), mHardMoneyMarketDenom, mMyDeposit).compareTo(BigDecimal.ZERO) <= 0) {
            Toast.makeText(getBaseContext(), R.string.error_no_deposited_asset, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, WithdrawHardActivity.class);
        intent.putExtra("hardPoolDemon", mHardMoneyMarketDenom);
        startActivity(intent);

    }

    public void onHardBorrow() {
        if (!onCommonCheck()) return;
        BigDecimal borrowAble = WUtil.getHardBorrowableAmountByDenom(this, getBaseDao(), mHardMoneyMarketDenom, mMyDeposit, mMyBorrow, mModuleCoins, mReserveCoins);
        if (borrowAble.compareTo(BigDecimal.ZERO) <= 0) {
            Toast.makeText(getBaseContext(), R.string.error_no_borrowable_asset, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, BorrowHardActivity.class);
        intent.putExtra("hardPoolDemon", mHardMoneyMarketDenom);
        startActivity(intent);

    }

    public void onHardRepay() {
        if (!onCommonCheck()) return;
        if ((getBaseDao().availableAmount(mHardMoneyMarketDenom)).compareTo(BigDecimal.ZERO) <= 0) {
            Toast.makeText(getBaseContext(), R.string.error_not_enough_to_balance, Toast.LENGTH_SHORT).show();
            return;
        }
        if (WUtil.getHardBorrowedValueByDenom(this, getBaseDao(), mHardMoneyMarketDenom, mMyBorrow).compareTo(BigDecimal.TEN) <= 0) {
            Toast.makeText(getBaseContext(), R.string.error_noting_repay_asset, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, RepayHardActivity.class);
        intent.putExtra("hardPoolDemon", mHardMoneyMarketDenom);
        startActivity(intent);
    }

    private boolean onCommonCheck() {
        if(!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return false;
        }
        return true;
    }

    private class HardDetailAdapter extends RecyclerView.Adapter<BaseHolder> {
        private static final int TYPE_HARD_INFO         = 0;
        private static final int TYPE_MY_STATUS         = 1;
        private static final int TYPE_MY_AVAILABLE      = 2;

        @NonNull
        @Override
        public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_HARD_INFO) {
                return new HardDetailInfoHolder(getLayoutInflater().inflate(R.layout.item_hard_detail_info, viewGroup, false));
            } else if (viewType == TYPE_MY_STATUS) {
                return new HardDetailMyStatusHolder(getLayoutInflater().inflate(R.layout.item_hard_detail_my_status, viewGroup, false));
            } else if (viewType == TYPE_MY_AVAILABLE) {
                return new HardDetailMyAvailableHolder(getLayoutInflater().inflate(R.layout.item_hard_detail_my_available, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull BaseHolder holder, int position) {
            if (getItemViewType(position) == TYPE_HARD_INFO) {
                holder.onBindHardDetailInfo(HardDetailActivity.this, mBaseChain, getBaseDao(), mHardMoneyMarketDenom, mIncentiveRewards, mInterestRates, mTotalDeposit, mTotalBorrow, mModuleCoins, mReserveCoins);
            } else if (getItemViewType(position) == TYPE_MY_STATUS) {
                holder.onBindHardDetailMyStatus(HardDetailActivity.this, getBaseDao(), mBaseChain, mHardMoneyMarketDenom, mMyDeposit, mMyBorrow, mModuleCoins, mReserveCoins);
            } else if (getItemViewType(position) == TYPE_MY_AVAILABLE) {
                holder.onBindHardDetailAvailable(HardDetailActivity.this, getBaseDao(), mBaseChain, mHardMoneyMarketDenom);
            }
        }

        @Override
        public int getItemCount() {
            return 3;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return TYPE_HARD_INFO;
            } else if (position == 1)  {
                return TYPE_MY_STATUS;
            } else {
                return TYPE_MY_AVAILABLE;
            }
        }
    }
}
