package wannabit.io.cosmostaion.fragment.txs.kava;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_KAVA_HARD_INTEREST_RATE;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_KAVA_HARD_MY_BORROW;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_KAVA_HARD_MY_DEPOSIT;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import kava.hard.v1beta1.Hard;
import kava.hard.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.kava.DAppsList5Activity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.kava.IncentiveReward;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.KavaHardInterestRateGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.KavaHardMyBorrowGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.KavaHardMyDepositGrpcTask;
import wannabit.io.cosmostaion.widget.BaseHolder;
import wannabit.io.cosmostaion.widget.kava.HardMyStatusHolder;
import wannabit.io.cosmostaion.widget.kava.HardPoolHolder;

public class ListHardFragment extends BaseFragment implements TaskListener {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private RelativeLayout mProgress;

    private Account mAccount;
    private BaseChain mBaseChain;
    private HardPoolAdapter mAdapter;

    private Hard.Params mHardParams;
    private ArrayList<QueryOuterClass.DepositResponse> mMyDeposits = new ArrayList<>();
    private ArrayList<QueryOuterClass.BorrowResponse> mMyBorrows = new ArrayList<>();
    private ArrayList<QueryOuterClass.MoneyMarketInterestRate> mInterestRates = new ArrayList<>();
    private IncentiveReward mIncentiveRewards;

    public static ListHardFragment newInstance() {
        return new ListHardFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cdp_market, container, false);
        mSwipeRefreshLayout = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView = rootView.findViewById(R.id.recycler);
        mProgress = rootView.findViewById(R.id.reward_progress);
        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getSActivity(), R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            if (mTaskCount > 0 || mHardParams == null) {
                mSwipeRefreshLayout.setRefreshing(false);
            } else {
                onFetchHardInfo();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getSActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new HardPoolAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mAccount = getSActivity().mAccount;
        mBaseChain = getSActivity().mBaseChain;
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mHardParams = getBaseDao().mHardParams;
        mIncentiveRewards = getBaseDao().mIncentiveRewards;
        onFetchHardInfo();
    }

    private int mTaskCount = 0;
    public void onFetchHardInfo() {
        mMyDeposits.clear();
        mMyBorrows.clear();
        mInterestRates.clear();
        mTaskCount = 3;
        new KavaHardMyDepositGrpcTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaHardMyBorrowGrpcTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaHardInterestRateGrpcTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (!isAdded()) return;
        mTaskCount--;
        if (result.taskType == TASK_GRPC_FETCH_KAVA_HARD_MY_DEPOSIT) {
            if (result.isSuccess && result.resultData != null) {
                mMyDeposits = (ArrayList<QueryOuterClass.DepositResponse>) result.resultData;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_KAVA_HARD_MY_BORROW) {
            if (result.isSuccess && result.resultData != null) {
                mMyBorrows = (ArrayList<QueryOuterClass.BorrowResponse>) result.resultData;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_KAVA_HARD_INTEREST_RATE) {
            if (result.isSuccess && result.resultData != null) {
                mInterestRates = (ArrayList<QueryOuterClass.MoneyMarketInterestRate>) result.resultData;
            }

        }
        if (mTaskCount == 0) {
            mAdapter.notifyDataSetChanged();
            mProgress.setVisibility(View.GONE);
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    private class HardPoolAdapter extends RecyclerView.Adapter<BaseHolder> {
        private static final int TYPE_MY_HARD_STATUS = 1;
        private static final int TYPE_HARD_POOL = 2;

        @NonNull
        @Override
        public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_MY_HARD_STATUS) {
                return new HardMyStatusHolder(getLayoutInflater().inflate(R.layout.item_hard_list_my_status, viewGroup, false));
            } else if (viewType == TYPE_HARD_POOL) {
                return new HardPoolHolder(getLayoutInflater().inflate(R.layout.item_hard_list_pool, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull BaseHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_MY_HARD_STATUS) {
                viewHolder.onBindMyHardStatus(getContext(), getBaseDao(), mMyDeposits, mMyBorrows);

            } else if (getItemViewType(position) == TYPE_HARD_POOL) {
                Hard.MoneyMarket hardMoneyMarket = mHardParams.getMoneyMarkets(position - 1);
                viewHolder.onBindMyHardPool(getContext(), getBaseDao(), mHardParams, hardMoneyMarket, mIncentiveRewards, mInterestRates, mMyDeposits, mMyBorrows, position - 1);
            }
        }

        @Override
        public int getItemCount() {
            if (mHardParams == null || mHardParams.getMoneyMarketsList() == null) return 0;
            return mHardParams.getMoneyMarketsList().size() + 1;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return TYPE_MY_HARD_STATUS;
            } else {
                return TYPE_HARD_POOL;
            }
        }
    }

    private DAppsList5Activity getSActivity() {
        return (DAppsList5Activity) getBaseActivity();
    }
}
