package wannabit.io.cosmostaion.fragment.chains.kava;

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
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.DAppsList5Activity;
import wannabit.io.cosmostaion.activities.chains.osmosis.LabsListActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.fragment.chains.osmosis.ListPoolFragment;
import wannabit.io.cosmostaion.model.kava.SwapDeposit;
import wannabit.io.cosmostaion.model.kava.SwapParam;
import wannabit.io.cosmostaion.model.kava.SwapPool;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.widget.BaseHolder;
import wannabit.io.cosmostaion.widget.kava.MyPoolListHolder;
import wannabit.io.cosmostaion.widget.kava.OtherPoolListHolder;
import wannabit.io.cosmostaion.widget.osmosis.PoolMyHolder;
import wannabit.io.cosmostaion.widget.osmosis.PoolOtherHolder;

public class ListKavaPoolFragment extends BaseFragment {

    private RecyclerView                        mRecyclerView;
    private KavaPoolListAdapter                 mAdapter;

    public SwapParam                            mSwapParam;
    public ArrayList<SwapPool>                  mSwapPoolList = new ArrayList<>();
    public ArrayList<SwapDeposit>               mMySwapDepositList = new ArrayList<>();
    public ArrayList<SwapPool>                  mMySwapPoolList = new ArrayList<>();
    public ArrayList<SwapPool>                  mOtherSwapPoolList = new ArrayList<>();

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
        mRecyclerView           = rootView.findViewById(R.id.recycler);

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
    }

    private class KavaPoolListAdapter extends RecyclerView.Adapter<BaseHolder> {
        private static final int TYPE_MY_POOL            = 1;
        private static final int TYPE_OTHER_POOL         = 2;

        @NonNull
        @Override
        public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_MY_POOL) {
                return new MyPoolListHolder(getLayoutInflater().inflate(R.layout.item_kava_pool_list_my, viewGroup, false));
            } else if (viewType == TYPE_OTHER_POOL) {
                return new OtherPoolListHolder(getLayoutInflater().inflate(R.layout.item_kava_pool_list_other, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull BaseHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_MY_POOL) {
                final SwapPool MySwapPool = mMySwapPoolList.get(position);
                final SwapDeposit MySwapDeposit = mMySwapDepositList.get(position);
                if (MySwapDeposit.pool_id.equalsIgnoreCase(MySwapPool.name)) {
                    viewHolder.onBindMyPool(getContext(), getSActivity(), getBaseDao(), MySwapPool, MySwapDeposit);
                }
            }
            else if (getItemViewType(position) == TYPE_OTHER_POOL) {
                final SwapPool OtherSwapPool = mOtherSwapPoolList.get(position - mMySwapPoolList.size());
                viewHolder.onBindOtherPool(getContext(), getSActivity(), getBaseDao(), OtherSwapPool);
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
