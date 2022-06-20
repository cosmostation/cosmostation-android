package wannabit.io.cosmostaion.fragment.chains.osmosis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import osmosis.gamm.v1beta1.BalancerPool;
import osmosis.incentives.GaugeOuterClass;
import osmosis.lockup.Lock;
import osmosis.poolincentives.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.osmosis.LabsListActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.osmosis.EarningMyHolder;
import wannabit.io.cosmostaion.widget.osmosis.EarningOtherHolder;

public class ListFarmingFragment extends BaseFragment {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private EarningListAdapter mAdapter;

    public ArrayList<BalancerPool.Pool> mTempPoolList = new ArrayList<>();
    public ArrayList<BalancerPool.Pool> mPoolList = new ArrayList<>();
    public ArrayList<BalancerPool.Pool> mMyIncentivizedPool = new ArrayList<>();
    public ArrayList<BalancerPool.Pool> mOtherIncentivizedPool = new ArrayList<>();

    public ArrayList<QueryOuterClass.IncentivizedPool> mIncentivizedPool = new ArrayList<>();
    public ArrayList<GaugeOuterClass.Gauge> mActiveGauges = new ArrayList<>();
    public ArrayList<Lock.PeriodLock> mPeriodLockUps = new ArrayList<>();


    public static ListFarmingFragment newInstance(Bundle bundle) {
        ListFarmingFragment fragment = new ListFarmingFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_farming_list, container, false);
        mSwipeRefreshLayout = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView = rootView.findViewById(R.id.recycler);

        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getSActivity(), R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getSActivity().onFetchPoolListInfo();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new EarningListAdapter();
        mRecyclerView.setAdapter(mAdapter);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mMyIncentivizedPool.clear();
        mOtherIncentivizedPool.clear();
        mTempPoolList = getBaseDao().mGrpcOsmosisPool;
        mPoolList = getSActivity().mPoolList;
        mIncentivizedPool = getSActivity().mIncentivizedPool;
        mActiveGauges = getSActivity().mActiveGauges;
        mPeriodLockUps = getSActivity().mPeriodLockUps;

        ArrayList<BalancerPool.Pool> filteredIncentivizedPool = new ArrayList<>();
        for (QueryOuterClass.IncentivizedPool incentivizedPool : mIncentivizedPool) {
            boolean already = false;
            for (BalancerPool.Pool pool : filteredIncentivizedPool) {
                if (pool.getId() == incentivizedPool.getPoolId()) {
                    already = true;
                }
            }
            if (!already) {
                filteredIncentivizedPool.add(getPoolwithID(incentivizedPool.getPoolId()));
            }
        }

        for (BalancerPool.Pool pool : filteredIncentivizedPool) {
            boolean isMaine = false;
            for (Lock.PeriodLock lockup : mPeriodLockUps) {
                String lockupDenom = lockup.getCoins(0).getDenom();
                if (!lockupDenom.startsWith("ibc/")) {
                    String tempPoolId = lockup.getCoins(0).getDenom().replaceAll("gamm/pool/", "");
                    if (pool.getId() == Long.parseLong(tempPoolId)) {
                        isMaine = true;
                    }
                }
            }
            if (isMaine) {
                mMyIncentivizedPool.add(pool);
            } else {
                mOtherIncentivizedPool.add(pool);
            }
        }
        mAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public BalancerPool.Pool getPoolwithID(long id) {
        for (BalancerPool.Pool pool : mTempPoolList) {
            if (pool.getId() == id) {
                return pool;
            }
        }
        return null;
    }


    private class EarningListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_MY_EARNING = 1;
        private static final int TYPE_OTHER_EARNING = 2;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_MY_EARNING) {
                return new EarningMyHolder(getLayoutInflater().inflate(R.layout.item_osmosis_earning_list_my, viewGroup, false));
            } else if (viewType == TYPE_OTHER_EARNING) {
                return new EarningOtherHolder(getLayoutInflater().inflate(R.layout.item_osmosis_earning_list_other, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_MY_EARNING) {
                final EarningMyHolder holder = (EarningMyHolder) viewHolder;
                final BalancerPool.Pool pool = mMyIncentivizedPool.get(position);
                final ArrayList<GaugeOuterClass.Gauge> gauges = WUtil.getGaugesByPoolId(pool.getId(), mIncentivizedPool, mActiveGauges);
                final ArrayList<Lock.PeriodLock> lockups = WUtil.getLockupByPoolId(pool.getId(), mPeriodLockUps);
                holder.onBindView(getContext(), getSActivity(), getBaseDao(), pool, lockups, gauges);

            } else if (getItemViewType(position) == TYPE_OTHER_EARNING) {
                final EarningOtherHolder holder = (EarningOtherHolder) viewHolder;
                final BalancerPool.Pool pool = mOtherIncentivizedPool.get(position - mMyIncentivizedPool.size());
                final ArrayList<GaugeOuterClass.Gauge> gauges = WUtil.getGaugesByPoolId(pool.getId(), mIncentivizedPool, mActiveGauges);
                final ArrayList<Lock.PeriodLock> lockups = WUtil.getLockupByPoolId(pool.getId(), mPeriodLockUps);
                holder.onBindView(getContext(), getSActivity(), getBaseDao(), pool, lockups, gauges);
            }
        }

        @Override
        public int getItemCount() {
            return mMyIncentivizedPool.size() + mOtherIncentivizedPool.size();
        }

        @Override
        public int getItemViewType(int position) {
            if (position < mMyIncentivizedPool.size()) {
                return TYPE_MY_EARNING;
            } else {
                return TYPE_OTHER_EARNING;
            }
        }
    }

    private LabsListActivity getSActivity() {
        return (LabsListActivity) getBaseActivity();
    }


}
