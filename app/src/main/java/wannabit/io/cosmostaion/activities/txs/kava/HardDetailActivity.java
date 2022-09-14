package wannabit.io.cosmostaion.activities.txs.kava;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_HARD_MODULE_ACCOUNT;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_KAVA_HARD_INTEREST_RATE;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_KAVA_HARD_MY_BORROW;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_KAVA_HARD_MY_DEPOSIT;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_KAVA_HARD_RESERVES;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_KAVA_HARD_TOTAL_BORROW;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_KAVA_HARD_TOTAL_DEPOSIT;

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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.math.BigDecimal;
import java.util.ArrayList;

import cosmos.base.v1beta1.CoinOuterClass;
import kava.hard.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.CommonAlertDialog;
import wannabit.io.cosmostaion.model.kava.IncentiveReward;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.task.FetchTask.KavaHardModuleAccountTask;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.KavaHardInterestRateGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.KavaHardMyBorrowGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.KavaHardMyDepositGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.KavaHardReservesGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.KavaHardTotalBorrowGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.KavaHardTotalDepositGrpcTask;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.BaseHolder;
import wannabit.io.cosmostaion.widget.kava.HardDetailInfoHolder;
import wannabit.io.cosmostaion.widget.kava.HardDetailMyAvailableHolder;
import wannabit.io.cosmostaion.widget.kava.HardDetailMyStatusHolder;

public class HardDetailActivity extends BaseActivity {
    private Toolbar mToolbar;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private RelativeLayout mLoadingLayer;

    private HardDetailAdapter mAdapter;
    private Account mAccount;
    private BaseChain mBaseChain;

    private String mHardMoneyMarketDenom;
    private ArrayList<QueryOuterClass.MoneyMarketInterestRate> mInterestRates = new ArrayList<>();
    private ArrayList<Coin> mModuleCoins = new ArrayList<>();
    private ArrayList<CoinOuterClass.Coin> mReserveCoins = new ArrayList<>();
    private ArrayList<CoinOuterClass.Coin> mTotalDeposit = new ArrayList<>();
    private ArrayList<CoinOuterClass.Coin> mTotalBorrow = new ArrayList<>();
    private ArrayList<QueryOuterClass.DepositResponse> mMyDeposit = new ArrayList<>();
    private ArrayList<QueryOuterClass.BorrowResponse> mMyBorrow = new ArrayList<>();
    private IncentiveReward mIncentiveRewards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_detail);
        initView();
        loadData();
    }

    public void initView() {
        mToolbar = findViewById(R.id.tool_bar);
        mSwipeRefreshLayout = findViewById(R.id.layer_refresher);
        mRecyclerView = findViewById(R.id.recycler);
        mLoadingLayer = findViewById(R.id.loadingLayer);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(HardDetailActivity.this, R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            if (mTaskCount > 0) {
                mSwipeRefreshLayout.setRefreshing(false);
            } else {
                onFetchHardInfo();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new HardDetailAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    public void loadData() {
        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);
        mHardMoneyMarketDenom = getIntent().getStringExtra("hard_money_market_denom");
        mIncentiveRewards = getBaseDao().mIncentiveRewards;

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
        mInterestRates.clear();
        mModuleCoins.clear();
        mReserveCoins.clear();
        mTotalDeposit.clear();
        mTotalBorrow.clear();
        mMyDeposit.clear();
        mMyBorrow.clear();
        mTaskCount = 7;
        new KavaHardInterestRateGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaHardModuleAccountTask(getBaseApplication(), this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaHardReservesGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaHardTotalDepositGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaHardTotalBorrowGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaHardMyDepositGrpcTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaHardMyBorrowGrpcTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (isFinishing()) return;
        mTaskCount--;
        if (result.taskType == TASK_GRPC_FETCH_KAVA_HARD_INTEREST_RATE) {
            if (result.isSuccess && result.resultData != null) {
                mInterestRates = (ArrayList<QueryOuterClass.MoneyMarketInterestRate>) result.resultData;
            }

        } else if (result.taskType == TASK_FETCH_KAVA_HARD_MODULE_ACCOUNT) {
            if (result.isSuccess && result.resultData != null) {
                mModuleCoins = (ArrayList<Coin>) result.resultData;
                getBaseDao().mModuleCoins = mModuleCoins;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_KAVA_HARD_RESERVES) {
            if (result.isSuccess && result.resultData != null) {
                mReserveCoins = (ArrayList<CoinOuterClass.Coin>) result.resultData;
                getBaseDao().mReserveCoins = mReserveCoins;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_KAVA_HARD_TOTAL_DEPOSIT) {
            if (result.isSuccess && result.resultData != null) {
                mTotalDeposit = (ArrayList<CoinOuterClass.Coin>) result.resultData;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_KAVA_HARD_TOTAL_BORROW) {
            if (result.isSuccess && result.resultData != null) {
                mTotalBorrow = (ArrayList<CoinOuterClass.Coin>) result.resultData;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_KAVA_HARD_MY_DEPOSIT) {
            if (result.isSuccess && result.resultData != null) {
                mMyDeposit = (ArrayList<QueryOuterClass.DepositResponse>) result.resultData;
                getBaseDao().mMyHardDeposits = mMyDeposit;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_KAVA_HARD_MY_BORROW) {
            if (result.isSuccess && result.resultData != null) {
                mMyBorrow = (ArrayList<QueryOuterClass.BorrowResponse>) result.resultData;
                getBaseDao().mMyHardBorrows = mMyBorrow;
            }

        }

        if (mTaskCount == 0) {
            mAdapter.notifyDataSetChanged();
            mLoadingLayer.setVisibility(View.GONE);
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }


    public void onHardDeposit() {
        if (!onCommonCheck()) return;
        if ((getBaseDao().getAvailable(mHardMoneyMarketDenom)).compareTo(BigDecimal.ZERO) <= 0) {
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
        if ((getBaseDao().getAvailable(mHardMoneyMarketDenom)).compareTo(BigDecimal.ZERO) <= 0) {
            Toast.makeText(getBaseContext(), R.string.error_not_enough_to_balance, Toast.LENGTH_SHORT).show();
            return;
        }
        if (WUtil.getHardBorrowedValueByDenom(this, getBaseDao(), mHardMoneyMarketDenom, mMyBorrow).compareTo(BigDecimal.ZERO) <= 0) {
            Toast.makeText(getBaseContext(), R.string.error_noting_repay_asset, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, RepayHardActivity.class);
        intent.putExtra("hardPoolDemon", mHardMoneyMarketDenom);
        startActivity(intent);
    }

    private boolean onCommonCheck() {
        if (!mAccount.hasPrivateKey) {
            CommonAlertDialog.showDoubleButton(this, getString(R.string.str_only_observe_title), getString(R.string.str_only_observe_msg),
                    getString(R.string.str_close), null, getString(R.string.str_add_mnemonics), view -> onAddMnemonicForAccount());
            return false;
        }
        return true;
    }

    private class HardDetailAdapter extends RecyclerView.Adapter<BaseHolder> {
        private static final int TYPE_HARD_INFO = 0;
        private static final int TYPE_MY_STATUS = 1;
        private static final int TYPE_MY_AVAILABLE = 2;

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
                holder.onBindHardDetailInfo(HardDetailActivity.this, getBaseDao(), mHardMoneyMarketDenom, mIncentiveRewards, mInterestRates, mTotalDeposit, mTotalBorrow, mModuleCoins, mReserveCoins);
            } else if (getItemViewType(position) == TYPE_MY_STATUS) {
                holder.onBindHardDetailMyStatus(HardDetailActivity.this, getBaseDao(), mHardMoneyMarketDenom, mMyDeposit, mMyBorrow, mModuleCoins, mReserveCoins);
            } else if (getItemViewType(position) == TYPE_MY_AVAILABLE) {
                holder.onBindHardDetailAvailable(HardDetailActivity.this, getBaseDao(), mHardMoneyMarketDenom);
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
            } else if (position == 1) {
                return TYPE_MY_STATUS;
            } else {
                return TYPE_MY_AVAILABLE;
            }
        }
    }
}
