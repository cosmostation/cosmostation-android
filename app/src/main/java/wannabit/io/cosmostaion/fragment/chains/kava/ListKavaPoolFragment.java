package wannabit.io.cosmostaion.fragment.chains.kava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import kava.swap.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.DAppsList5Activity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.widget.BaseHolder;
import wannabit.io.cosmostaion.widget.PoolMyHolder;
import wannabit.io.cosmostaion.widget.PoolOtherHolder;

public class ListKavaPoolFragment extends BaseFragment {

    private SwipeRefreshLayout                  mSwipeRefreshLayout;
    private RecyclerView                        mRecyclerView;
    private KavaPoolListAdapter                 mAdapter;

    public ArrayList<QueryOuterClass.DepositResponse>       mMySwapDepositList = new ArrayList<>();
    public ArrayList<QueryOuterClass.PoolResponse>          mMySwapPoolList = new ArrayList<>();
    public ArrayList<QueryOuterClass.PoolResponse>          mOtherSwapPoolList = new ArrayList<>();

    public static ListKavaPoolFragment newInstance(Bundle bundle) {
        ListKavaPoolFragment fragment = new ListKavaPoolFragment();
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
        mSwipeRefreshLayout     = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView           = rootView.findViewById(R.id.recycler);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() { getSActivity().onFetchData(); }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new KavaPoolListAdapter();
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mMySwapDepositList = getSActivity().mMySwapDepositList;
        mMySwapPoolList = getSActivity().mMySwapPoolList;
        mOtherSwapPoolList = getSActivity().mOtherSwapPoolList;
        mAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private class KavaPoolListAdapter extends RecyclerView.Adapter<BaseHolder> {
        private static final int TYPE_MY_POOL            = 1;
        private static final int TYPE_OTHER_POOL         = 2;

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
                final QueryOuterClass.PoolResponse MySwapPool = mMySwapPoolList.get(position);
                final QueryOuterClass.DepositResponse MySwapDeposit = mMySwapDepositList.get(position);
                if (MySwapDeposit.getPoolId().equalsIgnoreCase(MySwapPool.getName())) {
                    viewHolder.onBindKavaMyPool(getContext(), getSActivity(), getBaseDao(), MySwapPool, MySwapDeposit);
                }

            } else if (getItemViewType(position) == TYPE_OTHER_POOL) {
                final QueryOuterClass.PoolResponse OtherSwapPool = mOtherSwapPoolList.get(position - mMySwapPoolList.size());
                viewHolder.onBindKavaOtherPool(getContext(), getSActivity(), getBaseDao(), OtherSwapPool);
            }
        }

        @Override
        public int getItemCount() {
            return mMySwapPoolList.size() + mOtherSwapPoolList.size();
        }

        @Override
        public int getItemViewType(int position) {
            if (position < mMySwapPoolList.size()) {
                return TYPE_MY_POOL;
            } else {
                return TYPE_OTHER_POOL;
            }
        }
    }

    private DAppsList5Activity getSActivity() { return (DAppsList5Activity)getBaseActivity(); }
}
