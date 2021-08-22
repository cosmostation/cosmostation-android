package wannabit.io.cosmostaion.fragment.chains.osmosis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import osmosis.gamm.v1beta1.PoolOuterClass;
import osmosis.incentives.GaugeOuterClass;
import osmosis.lockup.Lock;
import osmosis.poolincentives.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.osmosis.LabsListActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.widget.BaseHolder;
import wannabit.io.cosmostaion.widget.osmosis.EarningMyHolder;
import wannabit.io.cosmostaion.widget.osmosis.EarningOtherHolder;
import wannabit.io.cosmostaion.widget.osmosis.PoolMyHolder;
import wannabit.io.cosmostaion.widget.osmosis.PoolOtherHolder;

public class ListFarmingFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private EarningListAdapter mAdapter;

    public ArrayList<PoolOuterClass.Pool>                   mPoolList = new ArrayList<>();
    public ArrayList<GaugeOuterClass.Gauge>                 mActiveGauges = new ArrayList<>();
    public ArrayList<Lock.PeriodLock>                       mPeriodLockUps = new ArrayList<>();
    public ArrayList<QueryOuterClass.IncentivizedPool>      mMyIncentivizedPool = new ArrayList<>();
    public ArrayList<QueryOuterClass.IncentivizedPool>      mOtherIncentivizedPool = new ArrayList<>();

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
        mRecyclerView = rootView.findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new EarningListAdapter();
        mRecyclerView.setAdapter(mAdapter);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mPoolList.clear();
        mActiveGauges.clear();
        mPeriodLockUps.clear();
        mMyIncentivizedPool.clear();
        mOtherIncentivizedPool.clear();
        mPoolList = getSActivity().mPoolList;
        mActiveGauges = getSActivity().mActiveGauges;
        mPeriodLockUps = getSActivity().mPeriodLockUps;

        ArrayList<QueryOuterClass.IncentivizedPool> filteredIncentivizedPool = new ArrayList<>();
        for (QueryOuterClass.IncentivizedPool incentivizedPool: getSActivity().mIncentivizedPool) {
            boolean already = false;
            for (QueryOuterClass.IncentivizedPool thisPool: filteredIncentivizedPool) {
                if (incentivizedPool.getPoolId() == thisPool.getPoolId()) {
                    already = true;
                }
            }
            if (!already) {
                filteredIncentivizedPool.add(incentivizedPool);
            }
        }

        for (QueryOuterClass.IncentivizedPool  incentivizedPool: filteredIncentivizedPool) {
            boolean isMaine = false;
            for (Lock.PeriodLock  lockup: mPeriodLockUps) {
                String tempPoolId = lockup.getCoins(0).getDenom().replaceAll("gamm/pool/", "");
                if (incentivizedPool.getPoolId() == Long.parseLong(tempPoolId)) {
                    isMaine = true;
                }
            }
            if (isMaine) {
                mMyIncentivizedPool.add(incentivizedPool);
            } else {
                mOtherIncentivizedPool.add(incentivizedPool);
            }
        }

        mAdapter.notifyDataSetChanged();
    }


    private class EarningListAdapter extends RecyclerView.Adapter<BaseHolder> {
        private static final int TYPE_MY_EARNING        = 1;
        private static final int TYPE_OTHER_EARNING     = 2;

        @NonNull
        @Override
        public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_MY_EARNING) {
                return new EarningMyHolder(getLayoutInflater().inflate(R.layout.item_osmosis_earning_list_my, viewGroup, false));
            } else if (viewType == TYPE_OTHER_EARNING) {
                return new EarningOtherHolder(getLayoutInflater().inflate(R.layout.item_osmosis_earning_list_other, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull BaseHolder holder, int position) {

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

    private LabsListActivity getSActivity() { return (LabsListActivity)getBaseActivity(); }
}
