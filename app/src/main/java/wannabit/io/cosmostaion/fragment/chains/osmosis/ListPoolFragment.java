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
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.osmosis.LabsListActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.widget.BaseHolder;
import wannabit.io.cosmostaion.widget.PoolMyHolder;
import wannabit.io.cosmostaion.widget.PoolOtherHolder;


public class ListPoolFragment extends BaseFragment {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private PoolListAdapter mAdapter;

    public ArrayList<BalancerPool.Pool> mPoolMyList = new ArrayList<>();
    public ArrayList<BalancerPool.Pool> mPoolOtherList = new ArrayList<>();

    public static ListPoolFragment newInstance(Bundle bundle) {
        ListPoolFragment fragment = new ListPoolFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pool_list, container, false);
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
        mAdapter = new PoolListAdapter();
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mPoolMyList = getSActivity().mPoolMyList;
        mPoolOtherList = getSActivity().mPoolOtherList;
        mAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private class PoolListAdapter extends RecyclerView.Adapter<BaseHolder> {
        private static final int TYPE_MY_POOL = 1;
        private static final int TYPE_OTHER_POOL = 2;

        @NonNull
        @Override
        public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_MY_POOL) {
                return new PoolMyHolder(getLayoutInflater().inflate(R.layout.item_pool_list_my, viewGroup, false));
            } else if (viewType == TYPE_OTHER_POOL) {
                return new PoolOtherHolder(getLayoutInflater().inflate(R.layout.item_pool_list_other, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull BaseHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_MY_POOL) {
                final BalancerPool.Pool myPool = mPoolMyList.get(position);
                viewHolder.onBindOsmoMyPool(getContext(), getSActivity(), getBaseDao(), myPool);
            } else if (getItemViewType(position) == TYPE_OTHER_POOL) {
                final BalancerPool.Pool otherPool = mPoolOtherList.get(position - mPoolMyList.size());
                viewHolder.onBindOsmoOtherPool(getContext(), getSActivity(), getBaseDao(), otherPool);
            }
        }

        @Override
        public int getItemCount() {
            return mPoolMyList.size() + mPoolOtherList.size();
        }

        @Override
        public int getItemViewType(int position) {
            if (position < mPoolMyList.size()) {
                return TYPE_MY_POOL;
            } else {
                return TYPE_OTHER_POOL;
            }
        }
    }

    private LabsListActivity getSActivity() {
        return (LabsListActivity) getBaseActivity();
    }
}
