package wannabit.io.cosmostaion.fragment.chains.kava;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.ClaimHardIncentiveActivity;
import wannabit.io.cosmostaion.activities.chains.kava.DAppsList5Activity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.model.kava.HardInterestRate;
import wannabit.io.cosmostaion.model.kava.HardMyBorrow;
import wannabit.io.cosmostaion.model.kava.HardMyDeposit;
import wannabit.io.cosmostaion.model.kava.HardParam;
import wannabit.io.cosmostaion.model.kava.IncentiveReward;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.task.FetchTask.KavaHardInterestRateTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaHardMyBorrowTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaHardMyDepositTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaHardTotalBorrowTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaHardTotalDepositTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.widget.BaseHolder;
import wannabit.io.cosmostaion.widget.HardIncentiveHolder;
import wannabit.io.cosmostaion.widget.HardMyStatusHolder;
import wannabit.io.cosmostaion.widget.HardPoolHolder;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_HARD_INTEREST_RATE;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_HARD_MY_BORROW;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_HARD_MY_DEPOSIT;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_HARD_TOTAL_BORROW;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_HARD_TOTAL_DEPOIST;

public class ListHardFragment extends BaseFragment implements TaskListener {
    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private RecyclerView        mRecyclerView;
    private RelativeLayout      mProgress;

    private Account             mAccount;
    private BaseChain           mBaseChain;
    private HardPoolAdapter     mAdapter;

    private HardParam                       mHardParam;
    private ArrayList<HardInterestRate>     mInterestRates = new ArrayList<>();
    private ArrayList<Coin>                 mTotalDeposit = new ArrayList<>();
    private ArrayList<Coin>                 mTotalBorrow = new ArrayList<>();
    private ArrayList<HardMyDeposit>        mMyDeposit = new ArrayList<>();
    private ArrayList<HardMyBorrow>         mMyBorrow = new ArrayList<>();
    private IncentiveReward                 mIncentiveRewards;


    public static ListHardFragment newInstance(Bundle bundle) {
        ListHardFragment fragment = new ListHardFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cdp_market, container, false);
        mSwipeRefreshLayout     = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView           = rootView.findViewById(R.id.recycler);
        mProgress               = rootView.findViewById(R.id.reward_progress);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mTaskCount > 0 || mHardParam == null) {
                    mSwipeRefreshLayout.setRefreshing(false);
                } else {
                    onFetchHardInfo();
                }
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new HardPoolAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mAccount = getSActivity().mAccount;
        mBaseChain = getSActivity().mBaseChain;
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mHardParam = getBaseDao().mHardParam;
        mIncentiveRewards = getBaseDao().mIncentiveRewards;
        onFetchHardInfo();
    }

    private int mTaskCount = 0;
    public void onFetchHardInfo() {
        mInterestRates.clear();
        mTotalDeposit.clear();
        mTotalBorrow.clear();
        mMyDeposit.clear();
        mMyBorrow.clear();
        mTaskCount = 5;
        new KavaHardInterestRateTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaHardTotalDepositTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaHardTotalBorrowTask(getBaseApplication(), this, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaHardMyDepositTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaHardMyBorrowTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(!isAdded()) return;
        mTaskCount--;
        if (result.taskType == TASK_FETCH_KAVA_HARD_INTEREST_RATE) {
            if (result.isSuccess && result.resultData != null) {
                mInterestRates = (ArrayList<HardInterestRate>)result.resultData;
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
            }

        } else if (result.taskType == TASK_FETCH_KAVA_HARD_MY_BORROW) {
            if (result.isSuccess && result.resultData != null) {
                mMyBorrow = (ArrayList<HardMyBorrow>)result.resultData;
            }
        }

        if (mTaskCount == 0) {
            mAdapter.notifyDataSetChanged();
            mProgress.setVisibility(View.GONE);
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }


    public void onCheckStartClaimIncentive() {
        WLog.w("ListHardFragment onCheckStartClaimIncentive");
        if (!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }


        Intent intent = new Intent(getContext(), ClaimHardIncentiveActivity.class);
        startActivity(intent);


//        if (mCdpParams.circuit_breaker) {
//            Toast.makeText(getContext(), R.string.error_circuit_breaker, Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        Intent intent = new Intent(getContext(), ClaimIncentiveActivity.class);
////        intent.putExtra("collateral_type", mCollateralParam.type);
//        startActivity(intent);
    }

    private class HardPoolAdapter extends RecyclerView.Adapter<BaseHolder> {
        private static final int TYPE_INCENTIVE             = 0;
        private static final int TYPE_MY_HARD_STATUS        = 1;
        private static final int TYPE_HARD_POOL             = 2;

        @NonNull
        @Override
        public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_INCENTIVE) {
                return new HardIncentiveHolder(getLayoutInflater().inflate(R.layout.item_hard_list_incentive, viewGroup, false));
            } else if (viewType == TYPE_MY_HARD_STATUS) {
                return new HardMyStatusHolder(getLayoutInflater().inflate(R.layout.item_hard_list_my_status, viewGroup, false));
            } else if (viewType == TYPE_HARD_POOL) {
                return new HardPoolHolder(getLayoutInflater().inflate(R.layout.item_hard_list_pool, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull BaseHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_INCENTIVE) {
                viewHolder.onBindHardIncentive(getContext(), ListHardFragment.this, mIncentiveRewards);

            } else if (getItemViewType(position) == TYPE_MY_HARD_STATUS) {
                viewHolder.onBindMyHardStatus(getContext(), getBaseDao(), mMyDeposit, mMyBorrow);

            } else if (getItemViewType(position) == TYPE_HARD_POOL) {
                HardParam.HardMoneyMarket hardMoneyMarket;
                if (mIncentiveRewards != null && (mIncentiveRewards.getHardPoolRewardCnt() > 0) &&
                        (mIncentiveRewards.getHardPoolHardRewardAmount().compareTo(BigDecimal.ZERO) > 0 || mIncentiveRewards.getHardPoolKavaRewardAmount().compareTo(BigDecimal.ZERO) > 0)) {
                    hardMoneyMarket = mHardParam.money_markets.get(position - 2);
                } else {
                    hardMoneyMarket = mHardParam.money_markets.get(position - 1);
                }
                viewHolder.onBindMyHardPool(getContext(), mBaseChain, getBaseDao(), mHardParam, hardMoneyMarket, mIncentiveRewards, mInterestRates, mMyDeposit, mMyBorrow);
            }
        }

        @Override
        public int getItemCount() {
            if (mHardParam == null || mHardParam.money_markets == null) return 0;
            if (mIncentiveRewards != null && (mIncentiveRewards.getHardPoolRewardCnt() > 0) &&
                    (mIncentiveRewards.getHardPoolHardRewardAmount().compareTo(BigDecimal.ZERO) > 0 || mIncentiveRewards.getHardPoolKavaRewardAmount().compareTo(BigDecimal.ZERO) > 0)) {
                return mHardParam.money_markets.size() + 2;
            } else  {
                return mHardParam.money_markets.size() + 1;
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (mIncentiveRewards != null && (mIncentiveRewards.getHardPoolRewardCnt() > 0) &&
                    (mIncentiveRewards.getHardPoolHardRewardAmount().compareTo(BigDecimal.ZERO) > 0 || mIncentiveRewards.getHardPoolKavaRewardAmount().compareTo(BigDecimal.ZERO) > 0)) {
                if (position == 0) {
                    return TYPE_INCENTIVE;
                } else if (position == 1) {
                    return TYPE_MY_HARD_STATUS;
                } else {
                    return TYPE_HARD_POOL;
                }

            } else {
                if (position == 0) {
                    return TYPE_MY_HARD_STATUS;
                } else {
                    return TYPE_HARD_POOL;
                }
            }

        }
    }



    private DAppsList5Activity getSActivity() {
        return (DAppsList5Activity)getBaseActivity();
    }


}
